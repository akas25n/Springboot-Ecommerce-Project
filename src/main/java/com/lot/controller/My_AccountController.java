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
	
	
	@RequestMapping(value = "/account/addresses/{user_id}", method= RequestMethod.GET)
	public ModelAndView showDetails(@PathVariable int user_id) {
		
		ModelAndView mv = new ModelAndView();
		//************************************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
        //************************************************************************************************************************
        Optional<User> usr = userRepository.findById(user_id);
        //User users = obj.get();
        //Note: If i dont use obj.get() then i have do..mv.addObject("user", usr);
        //if i user like User user= usr.get();---then i dont need mv.addObject();..i have use user directly int the link. 
        //Otherwise it will show user_id can not be found on null error
        
        BillingAddress billAddress= billingAddressRepository.findByUserId(user_id);
        
        ShippingAddress shippAddress = shippingAddressRepository.findByUserId(user_id);
        
        
        mv.addObject("users",usr);
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
	
	@RequestMapping(value = "/account/edit/billing/address",method=RequestMethod.GET)
	public ModelAndView editBillAddress()
	{
		ModelAndView mv = new ModelAndView();
		//********************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
        //********************************************************************************************

        int user_id = user.getId();
        BillingAddress billAddress = billingAddressRepository.findByUserId(user_id);

        mv.addObject("ba", billAddress);
		
		mv.setViewName("/my_account/user/edit-bill-address");
		
		return mv;
	}
	
	//updating billing address...tested..working
	@RequestMapping(value ="/account/update/billing/address", method=RequestMethod.POST)
	public ModelAndView saveBillAddChanges(@RequestParam String contact_person,
									@RequestParam String street,
									@RequestParam String city,
									@RequestParam int zip_code,
									@RequestParam String country,
									@RequestParam String phone_number
									){
			
			
		ModelAndView mv = new ModelAndView();
		//********************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
        //******************************************************************************************** 
		int user_id = user.getId();
		BillingAddress bAdd = billingAddressRepository.findByUserId(user_id);

		Lot lot = new Lot();
		
		bAdd.setContact_person(contact_person);
		bAdd.setStreet(street);
		bAdd.setCity(city);
		bAdd.setZip_code(zip_code);
		bAdd.setCountry(country);
		bAdd.setPhone_number(phone_number);
		
		billingAddressRepository.save(bAdd);
		
		mv.addObject("bAd", bAdd);
		mv.addObject("msg", "Info have been updated");
		mv.addObject("lot", lot);
		//mv.setViewName("/my_account/user/loginDetails");
		
		return new ModelAndView("redirect:/my/account/addresses/" + user_id);
	}
	
	//############################################################################################################################# Important
	//The bellow code i need to axecute when i will add or edit addresses from lot details
	//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::starts of adding and editing biiling address from order details
	
	
	@RequestMapping(value = "/account/billing/address/{lotId}",method=RequestMethod.GET)
	public ModelAndView billingAddressLot(@PathVariable long lotId)
	{
		ModelAndView mv = new ModelAndView();
		
		//********************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
        //********************************************************************************************
      
        Optional<Lot> obj = lotRepository.findById(lotId);
        Lot lot = obj.get();
        
        BillingAddress billingAddressInfo = new BillingAddress();
        
        mv.addObject("billingAddressInfo", billingAddressInfo);
        mv.addObject("lots", lot);
		
		mv.setViewName("/my_account/user/billing-address-lot");
		
		return mv;

	}
	
	
	//post method
	@RequestMapping(value="/account/billing/address/test/{lotId}", method=RequestMethod.POST)
	public ModelAndView saveAddressLot(@PathVariable long lotId,@Valid BillingAddress customerBillingAddressInfo, BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView();
		//********************************************************************************************
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		        User user = userService.findUserByEmail(auth.getName());
		        mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
		//********************************************************************************************        
		
		if (bindingResult.hasErrors()) {
			mv.setViewName("/my_account/user/billing_address");
			
		}
		
		//int user_id = user.getId();
		
		Optional<Lot> obj = lotRepository.findById(lotId);
        Lot lot = obj.get();
        
		customerBillingAddressInfo.setUser(user);
		customerBillingAddressInfoService.saveBillInfo(customerBillingAddressInfo);
		
		mv.addObject("message", "Address have been successfully saved");
		mv.addObject("customerBillingAddressInfo", new BillingAddress());
		mv.addObject("lt", lot);
		
		return new ModelAndView("redirect:/my/account/show/billing/address/" + lotId);
	}
	
	
	@RequestMapping(value = "/account/edit/billing/address/{lotId}",method=RequestMethod.GET)
	public ModelAndView upBillAddress(@PathVariable long lotId)
	{
		ModelAndView mv = new ModelAndView();
		//********************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
        //********************************************************************************************

        int user_id = user.getId();
        BillingAddress billAddress = billingAddressRepository.findByUserId(user_id);

        Optional<Lot> obj = lotRepository.findById(lotId);
        Lot lot = obj.get();
        mv.addObject("ba", billAddress);
        mv.addObject("lots", lot);
		mv.setViewName("/my_account/user/edit-bill-add-lot");
		
		return mv;
	}
	
	//updating billing address...tested..working
	@RequestMapping(value ="/account/update/billing/address/{lotId}", method=RequestMethod.POST)
	public ModelAndView updateBillAddChanges(@RequestParam String contact_person,
									@RequestParam String street,
									@RequestParam String city,
									@RequestParam int zip_code,
									@RequestParam String country,
									@RequestParam String phone_number,
									@PathVariable long lotId
									){
			
			
		ModelAndView mv = new ModelAndView();
		//********************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
        //******************************************************************************************** 
		int user_id = user.getId();
		BillingAddress bAdd = billingAddressRepository.findByUserId(user_id);

		Optional<Lot> lot = lotRepository.findById(lotId);
		//Lot lot=obj.get();
		
		bAdd.setContact_person(contact_person);
		bAdd.setStreet(street);
		bAdd.setCity(city);
		bAdd.setZip_code(zip_code);
		bAdd.setCountry(country);
		bAdd.setPhone_number(phone_number);
		
		billingAddressRepository.save(bAdd);
		
		mv.addObject("bAd", bAdd);
		mv.addObject("msg", "Info have been updated");
		mv.addObject("lot", lot);
		//mv.setViewName("/my_account/user/loginDetails");
		
		return new ModelAndView("redirect:/my/account/show/billing/address/" + lotId);
	}//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: end of adding and editing biiling address from order details
	
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
		
		int user_id = user.getId();
		
		customerBillingAddressInfo.setUser(user);
		customerBillingAddressInfoService.saveBillInfo(customerBillingAddressInfo);
		
		mv.addObject("message", "Address have been successfully saved");
		mv.addObject("customerBillingAddressInfo", new BillingAddress());
		
		return new ModelAndView("redirect:/my/account/addresses/" + user_id);
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
        ShippingAddress customerShippingAddressInfo = shippingAddressRepository.findByUserId(user_id);
        
        //System.out.println("-------------------------------------------------------------------------------------------------" + shipping_add_id);
        
        mv.addObject("sa", customerShippingAddressInfo);
		
		mv.setViewName("/my_account/user/edit-ship-address");
		
		return mv;
	}
	
	
	//updating shipping address...tested..working
	@RequestMapping(value ="/account/update/shipping/address", method=RequestMethod.POST)
	public ModelAndView saveShipAddChanges(@RequestParam String contact_person,
									@RequestParam String street,
									@RequestParam String city,
									@RequestParam int zip_code,
									@RequestParam String country,
									@RequestParam String phone_number
									){
			
			
		ModelAndView mv = new ModelAndView();
		//********************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
        //******************************************************************************************** 
		int user_id = user.getId();
		ShippingAddress sAdd = shippingAddressRepository.findByUserId(user_id);

	
		
		sAdd.setContact_person(contact_person);
		sAdd.setStreet(street);
		sAdd.setCity(city);
		sAdd.setZip_code(zip_code);
		sAdd.setCountry(country);
		sAdd.setPhone_number(phone_number);
		
		shippingAddressRepository.save(sAdd);
		
		mv.addObject("sAd", sAdd);
		mv.addObject("msg", "Info have been updated");
		//mv.addObject("lotId", lot.getLotId());
		
		//mv.setViewName("/my_account/user/loginDetails");
		
		return new ModelAndView("redirect:/my/account/addresses/" + user_id);
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
		
		int user_id=user.getId();
		customerShippingAddressInfo.setUser(user);
		customerShippingAddressInfoService.saveShipInfo(customerShippingAddressInfo);
		
		mv.addObject("message", "Address have been successfully saved");
		mv.addObject("customerShippingAddressInfo", new ShippingAddress());
		
		return new ModelAndView("redirect:/my/account/addresses/" + user_id);
	}

//::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: starts of adding and editing shipping add from order
@RequestMapping(value = "/account/shipping/address/{lotId}",method=RequestMethod.GET)
public ModelAndView showOrderdetailsLot(@PathVariable long lotId)
{
	ModelAndView mv = new ModelAndView();
	//********************************************************************************************
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User user = userService.findUserByEmail(auth.getName());
    mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
    //********************************************************************************************

    Optional<Lot> obj = lotRepository.findById(lotId);
    Lot lot = obj.get();
    
    ShippingAddress customerShippingAddressInfo = new ShippingAddress();
  
    mv.addObject("customerShippingAddressInfo", customerShippingAddressInfo);
	mv.addObject("lots", lot);
	mv.setViewName("/my_account/user/shipping-address-lot");
	
	return mv;

}

@RequestMapping(value = "/account/edit/shipping/address/{lotId}",method=RequestMethod.GET)
public ModelAndView editShipAddressLot(@PathVariable long lotId)
{
	ModelAndView mv = new ModelAndView();
	//********************************************************************************************
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User user = userService.findUserByEmail(auth.getName());
    mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
    //********************************************************************************************

    Optional<Lot> obj = lotRepository.findById(lotId);
    Lot lot = obj.get();
    int user_id = user.getId();
    ShippingAddress customerShippingAddressInfo = shippingAddressRepository.findByUserId(user_id);
    
    //System.out.println("-------------------------------------------------------------------------------------------------" + shipping_add_id);
    
    mv.addObject("sa", customerShippingAddressInfo);
	mv.addObject("lots", lot);
	mv.setViewName("/my_account/user/edit-ship-add-lot");
	
	return mv;
}


//updating shipping address...tested..working
@RequestMapping(value ="/account/update/shipping/address/{lotId}", method=RequestMethod.POST)
public ModelAndView saveShipAddChangesLot(@PathVariable long lotId, 
								@RequestParam String contact_person,
								@RequestParam String street,
								@RequestParam String city,
								@RequestParam int zip_code,
								@RequestParam String country,
								@RequestParam String phone_number
								){
		
		
	ModelAndView mv = new ModelAndView();
	//********************************************************************************************
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User user = userService.findUserByEmail(auth.getName());
    mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
    //******************************************************************************************** 
	int user_id = user.getId();
	ShippingAddress sAdd = shippingAddressRepository.findByUserId(user_id);


	Optional<Lot> obj = lotRepository.findById(lotId);
	Lot lot = obj.get();
	
	sAdd.setContact_person(contact_person);
	sAdd.setStreet(street);
	sAdd.setCity(city);
	sAdd.setZip_code(zip_code);
	sAdd.setCountry(country);
	sAdd.setPhone_number(phone_number);
	
	shippingAddressRepository.save(sAdd);
	
	mv.addObject("sAd", sAdd);
	mv.addObject("lt", lot);
	mv.addObject("msg", "Info have been updated");
	//mv.addObject("lotId", lot.getLotId());
	
	//mv.setViewName("/my_account/user/loginDetails");
	
	return new ModelAndView("redirect:/my/account/show/shipping/address/" + lotId);
}

//************************************************************************************************end of shipping address**********************

@RequestMapping(value="/account/shipping/address/test/{lotId}", method=RequestMethod.POST)

public ModelAndView saveSHippingAddressLot(@PathVariable long lotId, @Valid ShippingAddress customerShippingAddressInfo, BindingResult bindingResult) {
	ModelAndView mv = new ModelAndView();
	
	//********************************************************************************************
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        User user = userService.findUserByEmail(auth.getName());
	        mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
	        //********************************************************************************************
	        
	if (bindingResult.hasErrors()) {
		mv.setViewName("/my_account/user/billing_address");
		
	}
	
	  Optional<Lot> obj = lotRepository.findById(lotId);
	  Lot lot = obj.get();
	    
	//int user_id=user.getId();
	  
	customerShippingAddressInfo.setUser(user);
	customerShippingAddressInfoService.saveShipInfo(customerShippingAddressInfo);
	
	mv.addObject("message", "Address have been successfully saved");
	mv.addObject("customerShippingAddressInfo", new ShippingAddress());
	mv.addObject("lt", lot);
	return new ModelAndView("redirect:/my/account/show/shipping/address/" + lotId);
}
//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::ends of adding and editing shipping add from order


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

	
	

