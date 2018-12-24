package com.lot.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lot.model.BillingAddress;
import com.lot.model.Lot;
import com.lot.model.Order;
import com.lot.model.ShippingAddress;
import com.lot.model.User;
import com.lot.repository.BillingAddressRepository;
import com.lot.repository.ShippingAddressRepository;
import com.lot.repository.UserRepository;
import com.lot.service.BillingAddressService;
import com.lot.service.ShippingAddressService;
import com.lot.service.UserService;

@Controller
@RequestMapping("/my")
public class My_AccountController {
	
	@Autowired
	private ShippingAddressService customerShippingAddressInfoService;
	
	@Autowired
	private BillingAddressService customerBillingAddressInfoService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private UserService userService;
	
	@Autowired
	private BillingAddressRepository billingAddressRepository;
	
	@Autowired
	private ShippingAddressRepository shippingAddressRepository;
	
	
	@RequestMapping("/account")
	public ModelAndView show() {
		ModelAndView mv = new ModelAndView();
		
		//************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
        //************************************************************************************
  
        mv.addObject("users", user);
		mv.setViewName("/my_account/myAccount");
		return mv;
	}
	
/*	@RequestMapping("/account/{id}")
	public ModelAndView show(@PathVariable("id") Integer user_id) {
		ModelAndView mv = new ModelAndView();
		
		//************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
        //************************************************************************************
        
        Optional<User> users = userRepository.findById(user_id);
        
        mv.addObject("user", users);
      
		mv.setViewName("/my_account/myAccount");
		return mv;
	}
	*/
	
	
	
	@RequestMapping(value = "/account/orders", method= RequestMethod.GET)
	public ModelAndView showOders() {
		ModelAndView mv = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
		mv.setViewName("/my_account/user/your_orders");
		return mv;
	}
	
/*	@RequestMapping(value = "/account/edit", method= RequestMethod.GET)
	public ModelAndView showLogin() {
		ModelAndView mv = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
		mv.setViewName("/my_account/user/loginDetails");
		return mv;
	}
*/
	
/*	@RequestMapping(value = "/account/details/{id}", method= RequestMethod.GET)
	public ModelAndView showDetails(@PathVariable("id") Integer user_id) {
		
		ModelAndView mv = new ModelAndView();
		//************************************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
        //************************************************************************************************************************
       Optional<User> users=userRepository.findById(user_id);
       
			mv.addObject("user",users);
			mv.setViewName("/my_account/user/common2");
      
		return mv;
	}
	
	
	@RequestMapping(value = "/account/details/update/{id}", method= RequestMethod.POST)
	public ModelAndView updateDetails(@PathVariable("id") Integer user_id, @Valid User users, BindingResult bindingResult ) {
		
		ModelAndView mv = new ModelAndView();
		//************************************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
        //************************************************************************************************************************
        
        
        if (bindingResult.hasErrors()) {
        	users.setId(user_id);
        	mv.setViewName("/my_account/user/common2");
			
		}
         
      userRepository.save(users);
       
			mv.addObject("user",userRepository.findAll());
			mv.setViewName("lotDetails");
      
		return mv;
	}
	*/
	
	
	@RequestMapping(value = "/account/details", method= RequestMethod.GET)
	public ModelAndView showDetails() {
		
		ModelAndView mv = new ModelAndView();
		//************************************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
        //************************************************************************************************************************
        mv.addObject("user",user);
			mv.setViewName("/my_account/user/common2");
      
		return mv;
	}
	
	
	
	
/*	@RequestMapping(value = "/my/account/billing/address/{id}",method=RequestMethod.GET)
	public ModelAndView showLotdetails(@PathVariable("id") int user_id)
	{
		ModelAndView mv = new ModelAndView();
		
		//********************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
        //********************************************************************************************
        
        //user=userRepository.findById(id);
		Optional<CustomerBillingAddressInfo> customerBillingAddressinfo = customerBillingAddressInfoService.findByCustomerBillingAddressInfoId(user_id);

		mv.addObject("users",user);
		mv.addObject("customerBillingAddressinfo", customerBillingAddressinfo);
		
		
		System.out.println("**********************************************************************************test user id: " + user_id + "***********************");
		
		mv.setViewName("/my_account/user/billing_address");
		return mv;
	}
	*/
	
	//************************************************************************************************Getting billing address**********************  OK
	@RequestMapping(value = "/account/billing/address",method=RequestMethod.GET)
	public ModelAndView billingAddress()
	{
		ModelAndView mv = new ModelAndView();
		
		//********************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
        //********************************************************************************************
      
      
        BillingAddress billingAddressInfo = new BillingAddress();
        
        mv.addObject("billingAddressInfo", billingAddressInfo);
		
		mv.setViewName("/my_account/user/billing_address");
		
		return mv;

	}
	

	

	//************************************************************************************************end of billing address**********************
	
	@RequestMapping(value="/account/billing/address/test", method=RequestMethod.POST)
	public ModelAndView saveAddress(@Valid BillingAddress customerBillingAddressInfo, BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView();
		
		//********************************************************************************************
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		        User user = userService.findUserByEmail(auth.getName());
		        mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
		//********************************************************************************************
		        
		
		if (bindingResult.hasErrors()) {
			mv.setViewName("/my_account/user/billing_address");
			
		}
		
		customerBillingAddressInfo.setUser(user);
		customerBillingAddressInfoService.saveBillInfo(customerBillingAddressInfo);
		
		mv.addObject("message", "Address have been successfully saved");
		mv.addObject("customerBillingAddressInfo", new BillingAddress());
		
		return new ModelAndView("redirect:/my/account");
	}
	
	// displaying billing address if exist
	@SuppressWarnings("unused")
	@RequestMapping(value="/account/show/billing/address")
	public ModelAndView showBillAdd(@Valid BillingAddress billingAddress, BindingResult bindingResult) {
		
	
		ModelAndView mv = new ModelAndView();
		//********************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
        //********************************************************************************************
        
        int user_id=user.getUser_id();
		
        billingAddress = (@Valid BillingAddress) billingAddressRepository.findByUserId(user_id);
		
		if (billingAddress != null) {
			mv.addObject("billAddress", billingAddress);
		} else {
			mv.addObject("message", "Please add a billing address");
		}
		
		
		mv.setViewName("/my_account/user/addressBill");
		
		return mv;
		
	}
	
	//************************************************************************************************Getting shipping address**********************  OK
	@RequestMapping(value = "/account/shipping/address",method=RequestMethod.GET)
	public ModelAndView showOrderdetails()
	{
		ModelAndView mv = new ModelAndView();
		//********************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
        //********************************************************************************************

        ShippingAddress customerShippingAddressInfo = new ShippingAddress();
        
        mv.addObject("customerShippingAddressInfo", customerShippingAddressInfo);
		
		mv.setViewName("/my_account/user/shipping_address");
		
		return mv;

	}
	//************************************************************************************************end of shipping address**********************
	
@RequestMapping(value="/account/shipping/address/test", method=RequestMethod.POST)
	
	public ModelAndView saveSHippingAddress(@Valid ShippingAddress customerShippingAddressInfo, BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView();
		
		
		//********************************************************************************************
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		        User user = userService.findUserByEmail(auth.getName());
		        mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
		        //********************************************************************************************
		        
		if (bindingResult.hasErrors()) {
			mv.setViewName("/my_account/user/billing_address");
			
		}
		
		customerShippingAddressInfo.setUser(user);
		customerShippingAddressInfoService.saveShipInfo(customerShippingAddressInfo);
		
		mv.addObject("message", "Address have been successfully saved");
		mv.addObject("customerShippingAddressInfo", new ShippingAddress());
		
		return new ModelAndView("redirect:/my/account/billing/address");
	}


// displaying billing address if exist
@SuppressWarnings("unused")
@RequestMapping(value="/account/show/shipping/address")
public ModelAndView showShipAdd(@Valid ShippingAddress shippingAddress, BindingResult bindingResult) {
	

	ModelAndView mv = new ModelAndView();
	//********************************************************************************************
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User user = userService.findUserByEmail(auth.getName());
    mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
    //********************************************************************************************
    
    int user_id=user.getUser_id();
	
    shippingAddress = (@Valid ShippingAddress) shippingAddressRepository.findByUserId(user_id);
	
	if (shippingAddress != null) {
		mv.addObject("shippAddress", shippingAddress);
	} else {
		mv.addObject("msg", "Please add a shipping address");
	}
	
	
	mv.setViewName("/my_account/user/addressShip");
	
	return mv;
	
}

}// end of the MyAccountController 

	
	

