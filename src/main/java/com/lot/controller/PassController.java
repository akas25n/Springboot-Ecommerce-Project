package com.lot.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.converters.ByteArrayConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lot.model.MailRequest;
import com.lot.model.User_Lot;
import com.lot.service.EmailService;
import com.lot.service.UserService;
import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;

@Controller
public class PassController {
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	EmailService emailService;
	
	//display reset password form
	@RequestMapping(value="/reset/password", method=RequestMethod.GET)
	public ModelAndView showForm() {
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("reset-password");
		
		return mv;
		
	}
	
	
	// process the forgot password form submission
    @RequestMapping(value="/reset/password", method= RequestMethod.POST)
    public ModelAndView processForm(@RequestParam ("email") String email, HttpServletRequest request) {
    	ModelAndView mv = new ModelAndView();
    	
    	User_Lot user_Lot = userService.findUserByEmail(email);
    	
    	if (user_Lot == null){
    		mv.addObject("message", "Oops, we did not find an account for this email address.");	
		}else {
			user_Lot.getClass();
			
			// generate random number token
			user_Lot.setConfirmationToken(UUID.randomUUID().toString());
			
			// set token to database
			userService.saveUser(user_Lot);
			
			//String appUrl = request.getScheme() + "://" + request.getServerName();

		     //--------------------------------------------------------------------------------------------------------------------------------
            Map<String, Object> model = new HashMap<>();
            model.put("name", user_Lot.getFirst_name() + " " + user_Lot.getLast_name());
            model.put("message1", "To reset your password, please click the link below:\n"
            						+ "http://localhost:8090" + "/confirm?token=" + user_Lot.getConfirmationToken());
            
            MailRequest mailRequest = new MailRequest();
            mailRequest.setName(user_Lot.getFirst_name());
            mailRequest.setFrom("noreply@domain.com");
            mailRequest.setTo(user_Lot.getEmail());
            mailRequest.setSubject("Reset Password");
            
            emailService.sendEmailResetPass(mailRequest, model);
             
            mv.addObject("confirmationMessage", "A password reset link has been sent to " + user_Lot.getEmail());
            //------------------------------------------------------------------------------------------------------------------------------
            mv.setViewName("reset-password");
		}
    	
    	mv.setViewName("reset-password");
    	
		return mv;
    	
    }
    
    
    
    // confirming the confirmation link---GET 
    @RequestMapping(value="/confirm/password", method=RequestMethod.GET)
    public ModelAndView confirPass(@RequestParam("token") String token) {
    	
    	ModelAndView modelAndView = new ModelAndView();
    	User_Lot user_Lot = userService.findByConfirmationToken(token);
    	
    	if(user_Lot == null) { // if no token found in database
    		modelAndView.addObject("invalidToken", "Oops! This is an invalid confirmaion link.");
    	}else {// token found
    		modelAndView.addObject("confirmationToken", user_Lot.getConfirmationToken());
    	}
    	
    	modelAndView.setViewName("confirm-password");
    	return modelAndView;
    }// end of confirmRegistration
    
    
  //process confirmation link---POST
    @RequestMapping(value="/confirm/password", method=RequestMethod.POST)
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
    	User_Lot user_Lot = userService.findByConfirmationToken(requestParams.get("token"));
    	
    	//set new password
    	user_Lot.setPassword(bCryptPasswordEncoder.encode(requestParams.get("password")));
    	
    	//set user to enabled
    	user_Lot.setEnabled(true);
    	
    	//save the user
    	userService.saveUser(user_Lot);
    	
    	modelAndView.addObject("successMessage", "Your password has been changed!");
//      ----------------------------------------------------------------------------------------------------testing---	 	
    	modelAndView.setViewName("loginPage");
//    ----------------------------------------------------------------------------------------------------testing---	
    	return modelAndView;
    	
    }
}
