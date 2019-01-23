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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lot.model.BillingAddress;
import com.lot.model.Lot;
import com.lot.model.Order;
import com.lot.model.ResourceNotFoundException;
import com.lot.model.ShippingAddress;
import com.lot.model.User;
import com.lot.repository.BillingAddressRepository;
import com.lot.repository.LotRepository;
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
	
	@Autowired
	private LotRepository lotRepository;
	
	
	@RequestMapping("/account")
	public ModelAndView show(@RequestParam String attribute_select) {
		ModelAndView mv = new ModelAndView();
		
		//************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
        //************************************************************************************
  
        String user_role = "USER";
		String admin_role = "ADMIN";
		
		
		if(attribute_select.matches(admin_role))
		{
			mv.setViewName("/my_account/admin/admin-account");
		}
		else if (attribute_select.matches(user_role))
		{
			mv.setViewName("/my_account/user/user-account\"");
		}
		
        mv.addObject("users", user);
		
		return mv;
	}
	

	@RequestMapping(value = "/account/orders", method= RequestMethod.GET)
	public ModelAndView showOders() {
		ModelAndView mv = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
        
        // user can see his order list
		mv.setViewName("/my_account/user/your_orders");
		return mv;
	}
	
	@RequestMapping(value = "/account/edit/login/details/{user_id}", method= RequestMethod.GET)
	public ModelAndView showLogin(@PathVariable int user_id) {
		ModelAndView mv = new ModelAndView();
		//************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
        //************************************************************************************
       
       Optional<User> obj = userRepository.findById(user_id);
       User users = obj.get();
       
       mv.addObject("users", users);
		mv.setViewName("/my_account/user/loginDetails");
		return mv;
	}
	
	
	@RequestMapping(value = "/account/update/login/details/{user_id}", method= RequestMethod.GET)
	public ModelAndView updateLogin(@PathVariable int user_id,
									@RequestParam String password) {
		ModelAndView mv = new ModelAndView();
		//************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
        //************************************************************************************
       
       Optional<User> obj = userRepository.findById(user_id);
       User users = obj.get();
       
       users.setPassword(password);
       
       userRepository.save(users);
       
       mv.addObject("users", users);
       mv.addObject("msg", "Info have been updated");
       
       mv.setViewName("/my_account/user/loginDetails");
       
       return new ModelAndView("redirect:/lot/login");
		
	}
	
	
	//------------------------------------------------------updating user general info----------------------STARTS---------------
	
	@RequestMapping(value = "/account/edit/general/info/{user_id}", method= RequestMethod.GET)
	public ModelAndView showInfo(@PathVariable int user_id) {
		ModelAndView mv = new ModelAndView();
		//************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
        //************************************************************************************
        
        Optional<User> obj = userRepository.findById(user_id);
        User users = obj.get();
        

        mv.addObject("users", users);
		mv.setViewName("/my_account/user/edit-user-info");
        
		return mv;
	}
	
	// updating user general info
	@RequestMapping(value ="/account/save/changes/{user_id}", method=RequestMethod.POST)
	public ModelAndView saveChanges(@PathVariable int user_id,
									@RequestParam String first_name,
									@RequestParam String last_name,
									@RequestParam String company
									//@RequestParam String email
									){
			
			
		ModelAndView mv = new ModelAndView();
	
		Optional<User> obj = userRepository.findById(user_id); 
		User usrs = obj.get();

		usrs.setFirst_name(first_name);
		usrs.setLast_name(last_name);
		usrs.setCompany(company);
		//usrs.setEmail(email);
		
		userRepository.save(usrs);
		
		mv.addObject("users", usrs);
		mv.addObject("msg", "Info have been updated");
		
		//mv.setViewName("/my_account/user/loginDetails");
		
		return new ModelAndView("redirect:/my/account");
	}
	//------------------------------------------------------updating user general info-------------------ENDS---------------------
	
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
	
	
	@RequestMapping(value = "/account/addresses", method= RequestMethod.GET)
	public ModelAndView showDetails() {
		
		ModelAndView mv = new ModelAndView();
		//************************************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
        //************************************************************************************************************************
        int user_id = user.getId();
        
        BillingAddress billAddress= billingAddressRepository.findByUserId(user_id);
        
        ShippingAddress shippAddress = shippingAddressRepository.findByUserId(user_id);
        
        
        mv.addObject("user",user);
        mv.addObject("billAddress", billAddress);
		mv.addObject("shippAddress", shippAddress);
		
		mv.addObject("message1", "Please add a Shipping address");
		mv.addObject("message2", "Please add a billing address");
		
			mv.setViewName("/my_account/user/my-addresses");
      
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
	
	
	@RequestMapping(value = "/account/edit/billing/address/{billing_add_id}",method=RequestMethod.GET)
	public ModelAndView editBillingAddress(@PathVariable int billing_add_id) {
	
		ModelAndView mv = new ModelAndView();
		
		//********************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
        //********************************************************************************************
      
        Optional<BillingAddress> obj = billingAddressRepository.findById(billing_add_id);
        BillingAddress billAdd = obj.get();
        
        mv.addObject("billAdd", billAdd);
		
        System.out.println("--------------------------------------------------------------------------" + billing_add_id);
		mv.setViewName("/my_account/user/edit-bill-address");
		
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
		
		return new ModelAndView("redirect:/my/account/addresses");
	}
	
	// displaying billing address if exist
	@SuppressWarnings("unused")
	@RequestMapping(value="/account/show/billing/address/{lotId}")
	public ModelAndView showBillAdd(@Valid BillingAddress billingAddress, BindingResult bindingResult, @PathVariable("lotId") long lotId) {
		
	
		ModelAndView mv = new ModelAndView();
		//********************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
        //********************************************************************************************
        
        Optional<Lot> newLot = lotRepository.findById(lotId);
        Lot lots = newLot.get();
        
        int user_id=user.getUser_id();
		
        billingAddress = (@Valid BillingAddress) billingAddressRepository.findByUserId(user_id);
		
		if (billingAddress != null) {
			mv.addObject("billAddress", billingAddress);
		} else {
			mv.addObject("message", "Please add a billing address");
		}
		
		mv.addObject("lots", lots);
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
	
	@RequestMapping(value = "/account/edit/shipping/address",method=RequestMethod.GET)
	public ModelAndView editShipAddress()
	{
		ModelAndView mv = new ModelAndView();
		//********************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
        //********************************************************************************************

        int user_id = user.getId();
        Optional<ShippingAddress> customerShippingAddressInfo = shippingAddressRepository.findById(user_id);
        
        //System.out.println("-------------------------------------------------------------------------------------------------" + shipping_add_id);
        
        mv.addObject("sa", customerShippingAddressInfo);
		
		mv.setViewName("/my_account/user/edit-ship-address");
		
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
		
		return new ModelAndView("redirect:/my/account/addresses");
	}


// displaying billing address if exist
@SuppressWarnings("unused")
@RequestMapping(value="/account/show/shipping/address/{lotId}")
public ModelAndView showShipAdd(@Valid ShippingAddress shippingAddress, BindingResult bindingResult, @PathVariable("lotId") long lotId) {
	

	ModelAndView mv = new ModelAndView();
	//********************************************************************************************
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User user = userService.findUserByEmail(auth.getName());
    mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
    //********************************************************************************************
    
    Optional<Lot> newLot = lotRepository.findById(lotId);
    Lot lots = newLot.get();
    
    int user_id=user.getUser_id();
	
    shippingAddress = (@Valid ShippingAddress) shippingAddressRepository.findByUserId(user_id);
	
	if (shippingAddress != null) {
		mv.addObject("shippAddress", shippingAddress);
	} else {
		mv.addObject("msg", "Please add a shipping address");
	}
	
	mv.addObject("lots", lots);
	mv.setViewName("/my_account/user/addressShip");
	
	return mv;
	
}

}// end of the MyAccountController 

	
	

