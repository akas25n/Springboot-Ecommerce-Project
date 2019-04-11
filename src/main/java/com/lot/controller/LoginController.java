package com.lot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lot.model.Lot;
import com.lot.model.MailRequest;
import com.lot.model.SliderImages;
import com.lot.model.User;
import com.lot.repository.LotRepository;
import com.lot.repository.OrderRepository;
import com.lot.repository.SliderImagesRepository;
import com.lot.service.EmailService;
import com.lot.service.LotService;
import com.lot.service.ShippingAddressService;
import com.lot.service.UserService;
import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;



@Controller
public class LoginController {
	
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
    private UserService userService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private LotRepository lotRepository;
	
	@Autowired
	private LotService lotService;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ShippingAddressService shippingAddressService;
	
	@Autowired
	private SliderImagesRepository sliderRepo;
	
	
    //constructor
	@Autowired
    public LoginController(BCryptPasswordEncoder bCryptPasswordEncoder, UserService userService,
			EmailService emailService, LotRepository lotRepository) {
		super();
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.userService = userService;
		this.emailService = emailService;
		this.lotRepository = lotRepository;
	}

	@RequestMapping("/")
    public ModelAndView showWelcome() {
    	ModelAndView mv = new ModelAndView();
    	mv.setViewName("welcome-page");
    	return mv;
    }

    @RequestMapping(value={"/lot/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("loginPage");
        return modelAndView;
    }

    // return register form
    @RequestMapping(value="/lot/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registerPage");
        return modelAndView;
    }

    // process form input data
    @RequestMapping(value = "/lot/registration", method = RequestMethod.POST)
    public ModelAndView confirmRegistration(@Valid User user, BindingResult bindingResult, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        
        // look-up if the user is exists in the database
        User userExists = userService.findUserByEmail(user.getEmail());
        
        System.out.println(userExists);
        
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        
     
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registerPage");
        } else {
        	//new user, so we create the user and send a confirmation email
        	//disable user until they click on the confirmation link
        	
        	user.setEnabled(false);
        	
        	//generate 36-character random String token generator
        	user.setConfirmationToken(UUID.randomUUID().toString());
        	
        	//save the user
            userService.saveUser(user);
            
            //--------------------------------------------------------------------------------------------------------------------------------
            
            Map<String, Object> model = new HashMap<>();
            model.put("name", user.getFirst_name() + " " + user.getLast_name());
            model.put("message1", "Thank you for your registration. \n\nTo confirm your regitration, please click the link below:\n"
            						+ "http://localhost:8090" + "/confirm?token=" + user.getConfirmationToken());
            MailRequest mailRequest = new MailRequest();
            mailRequest.setName(user.getFirst_name());
            mailRequest.setFrom("noreply@domain.com");
            mailRequest.setTo(user.getEmail());
            mailRequest.setSubject("Registration confirmation");
            
            emailService.sendEmail_reg(mailRequest, model);
             
            
            modelAndView.addObject("confirmationMessage", "A confirmation e-mail has been sent to " + user.getEmail());
            //------------------------------------------------------------------------------------------------------------------------------
            
            modelAndView.setViewName("registerPage");

        }
        return modelAndView;
    }// end of create  confirmRegistration
    
    
   
    
    // confirming the confirmation link---GET 
    @RequestMapping(value="/confirm", method=RequestMethod.GET)
    public ModelAndView confirmRegistration(@RequestParam("token") String token) {
    	
    	ModelAndView modelAndView = new ModelAndView();
    	User user = userService.findByConfirmationToken(token);
    	
    	if(user == null) { // if no token found in database
    		modelAndView.addObject("invalidToken", "Oops! This is an invalid confirmaion link.");
    	}else {// token found
    		modelAndView.addObject("confirmationToken", user.getConfirmationToken());
    	}
    	
    	modelAndView.setViewName("confirm");
    	return modelAndView;
    }// end of confirmRegistration
    
    
    //process confirmation link---POST
    @RequestMapping(value="/confirm", method=RequestMethod.POST)
    public ModelAndView confirmRegistration(ModelAndView modelAndView, BindingResult bindingResult,@RequestParam Map<String, String> requestParams, RedirectAttributes redirectAttributes) {
    	
    	//BindingResult need to put just beside the ModelAndView
    	modelAndView.setViewName("confirm");
    	
    	//Password strength checker
    	Zxcvbn passowrdCheck = new Zxcvbn(); 
    	Strength strength = passowrdCheck.measure(requestParams.get("password"));
    	
    	
    	if (strength.getScore()< 3) {
    		//error message..your password is too weak.
    		bindingResult.reject("password");
    		
    		redirectAttributes.addFlashAttribute("errorMessage", "Your password is too weak. Please choose a stronger one.\n"
    				+ "\nThe password should contain Captail letter, symbol, numbers");
    		
    		modelAndView.setViewName("redirect:confirm?token=" + requestParams.get("token"));
    		System.out.println(requestParams.get("token"));
    		
    		return modelAndView;
			
		}
    	
    	//find the user associated with the reset token
    	User user = userService.findByConfirmationToken(requestParams.get("token"));
    	
    	//set new password
    	user.setPassword(bCryptPasswordEncoder.encode(requestParams.get("password")));
    	
    	//set user to enabled
    	//user.setEnabled(true);
    	
    	//save the user
    	userService.saveUser(user);
    	
    	modelAndView.addObject("successMessage", "Your password has been set!");
//      ----------------------------------------------------------------------------------------------------testing---	 	
    	modelAndView.setViewName("loginPage");
//    ----------------------------------------------------------------------------------------------------testing---	
    	return modelAndView;
    	
    }
    

    @RequestMapping(value="/lot/home", method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
      //*************************************************************************************************
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
        //**************************************************************************************************
        
        List<SliderImages> images= sliderRepo.findAll();
        
        List<Lot> lots =lotService.findByEnabled();
        modelAndView.addObject("lots", lots);
        modelAndView.addObject("img", images);
        
        modelAndView.addObject("adminMessage","Only active lot will be displayed");
       // System.out.println("*****************************************************************"+ lots + "**************************************************************************");
        modelAndView.setViewName("index");
        return modelAndView;
    }
    
}
