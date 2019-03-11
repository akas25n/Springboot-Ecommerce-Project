package com.lot.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.lot.model.BillingAddress;
import com.lot.model.Lot;
import com.lot.model.MailRequest;
import com.lot.model.Order;
import com.lot.model.Product;
import com.lot.model.ShippingAddress;
import com.lot.model.SliderImages;
import com.lot.model.User_Lot;
import com.lot.repository.BillingAddressRepository;
import com.lot.repository.LotRepository;
import com.lot.repository.OrderRepository;
import com.lot.repository.ProductRepository;
import com.lot.repository.ShippingAddressRepository;
import com.lot.repository.SliderImagesRepository;
import com.lot.repository.UserRepository;
import com.lot.service.BillingAddressService;
import com.lot.service.EmailService;
import com.lot.service.LotService;
import com.lot.service.ProductService;
import com.lot.service.ShippingAddressService;
import com.lot.service.UserService;




@RestController
@RequestMapping("/admin")
public class Admin_Controller {

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private LotRepository lotRepository;
	
	@Autowired
	LotService lotService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BillingAddressRepository billingAddressRepository;
	
	@Autowired
	private ShippingAddressRepository shippingAddressRepository;
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ShippingAddressService customerShippingAddressInfoService;
	
	@Autowired
	private BillingAddressService customerBillingAddressInfoService;

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	private static String fileLocation;
	
	@Autowired
	private SliderImagesRepository sliderImagesRepoaitory;
	
	 
    @RequestMapping(value="/lot/home", method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
      //*************************************************************************************************
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User_Lot user_Lot = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName",user_Lot.getFirst_name() + " " + user_Lot.getLast_name());
        //**************************************************************************************************
        List<SliderImages> images = sliderImagesRepoaitory.findAll();
        
        List<Lot> lots =lotService.findByEnabled();
        modelAndView.addObject("lots", lots);
        modelAndView.addObject("img", images);
        modelAndView.addObject("adminMessage","Only active lot will be displayed");
       // System.out.println("*****************************************************************"+ lots + "**************************************************************************");
        modelAndView.setViewName("admin-index");
        return modelAndView;
    }
    
	@RequestMapping("/account")
	public ModelAndView show() {
		ModelAndView mv = new ModelAndView();
		
		//************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User_Lot user_Lot = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user_Lot.getFirst_name() + " " + user_Lot.getLast_name());
        //************************************************************************************

        mv.addObject("users", user_Lot);
        mv.setViewName("/my_account/admin/admin-account");
		return mv;
	}
	
	
	@RequestMapping(value="/orders/list", method=RequestMethod.GET)
	public ModelAndView showOrderList(@RequestParam(defaultValue="0") int page) {
		ModelAndView mv = new ModelAndView();
		//**************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User_Lot user_Lot = userService.findUserByEmail(auth.getName());
		mv.addObject("userName",user_Lot.getFirst_name() + " " + user_Lot.getLast_name());
		//*************************************************************************
		
		int user_id = user_Lot.getId();
		
		List<Order> oderList = orderRepository.findByUserId(user_id);
		mv.addObject("orderLists", oderList);
		
		
		mv.setViewName("/my_account/admin/your_orders");
		
		return mv;
		
	}

	
	@RequestMapping(value = "/account/edit/general/info/{user_id}", method= RequestMethod.GET)
	public ModelAndView showInfo(@PathVariable int user_id) {
		ModelAndView mv = new ModelAndView();
		//************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User_Lot user_Lot = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user_Lot.getFirst_name() + " " + user_Lot.getLast_name());
        //************************************************************************************
        
        Optional<User_Lot> obj = userRepository.findById(user_id);
        User_Lot user_Lots = obj.get();
        

        mv.addObject("users", user_Lots);
		mv.setViewName("/my_account/admin/edit-user-info");
        
		return mv;
	}
	
	@RequestMapping(value ="/account/save/changes/{user_id}", method=RequestMethod.POST)
	public ModelAndView saveChanges(@PathVariable int user_id,
									@RequestParam String first_name,
									@RequestParam String last_name,
									@RequestParam String company
									//@RequestParam String email
									){
			
			
		ModelAndView mv = new ModelAndView();
	
		Optional<User_Lot> obj = userRepository.findById(user_id); 
		User_Lot usrs = obj.get();

		usrs.setFirst_name(first_name);
		usrs.setLast_name(last_name);
		usrs.setCompany(company);
		//usrs.setEmail(email);
		
		userRepository.save(usrs);
		
		mv.addObject("users", usrs);
		mv.addObject("msg", "Info have been updated");
		
		//mv.setViewName("/my_account/user/loginDetails");
		
		return new ModelAndView("redirect:/admin/account");
	}
	
	@RequestMapping(value = "/account/edit/login/details/{user_id}", method= RequestMethod.GET)
	public ModelAndView showLogin(@PathVariable int user_id) {
		ModelAndView mv = new ModelAndView();
		//************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User_Lot user_Lot = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user_Lot.getFirst_name() + " " + user_Lot.getLast_name());
        //************************************************************************************
       
       Optional<User_Lot> obj = userRepository.findById(user_id);
       User_Lot user_Lots = obj.get();
       
       mv.addObject("users", user_Lots);
		mv.setViewName("/my_account/admin/loginDetails");
		return mv;
	}
	
	
	@RequestMapping(value = "/account/update/login/details/{user_id}", method= RequestMethod.GET)
	public ModelAndView updateLogin(@PathVariable int user_id,
									@RequestParam String password) {
		ModelAndView mv = new ModelAndView();
		//************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User_Lot user_Lot = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user_Lot.getFirst_name() + " " + user_Lot.getLast_name());
        //************************************************************************************
       
       Optional<User_Lot> obj = userRepository.findById(user_id);
       User_Lot user_Lots = obj.get();
       
       user_Lots.setPassword(password);
       
       userRepository.save(user_Lots);
       
       mv.addObject("users", user_Lots);
       mv.addObject("msg", "Info have been updated");
       
       mv.setViewName("/my_account/user/loginDetails");
       
       return new ModelAndView("redirect:/lot/login");
		
	}
	
	
	@RequestMapping(value = "/account/addresses/{user_id}", method= RequestMethod.GET)
	public ModelAndView showDetails(@PathVariable int user_id) {
		
		ModelAndView mv = new ModelAndView();
		//************************************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User_Lot user_Lot = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user_Lot.getFirst_name() + " " + user_Lot.getLast_name());
        //************************************************************************************************************************
        Optional<User_Lot> usr = userRepository.findById(user_id);
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
		
			mv.setViewName("/my_account/admin/my-addresses");
      
		return mv;
	}
	
	
	@RequestMapping(value = "/account/billing/address",method=RequestMethod.GET)
	public ModelAndView billingAddress()
	{
		ModelAndView mv = new ModelAndView();
		
		//********************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User_Lot user_Lot = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user_Lot.getFirst_name() + " " + user_Lot.getLast_name());
        //********************************************************************************************
      
        BillingAddress billingAddressInfo = new BillingAddress();
        
        mv.addObject("billingAddressInfo", billingAddressInfo);
		
		mv.setViewName("/my_account/admin/billing_address");
		
		return mv;

	}
	
	@RequestMapping(value = "/account/shipping/address",method=RequestMethod.GET)
	public ModelAndView showOrderdetails()
	{
		ModelAndView mv = new ModelAndView();
		//********************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User_Lot user_Lot = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user_Lot.getFirst_name() + " " + user_Lot.getLast_name());
        //********************************************************************************************

        ShippingAddress customerShippingAddressInfo = new ShippingAddress();
      
        mv.addObject("customerShippingAddressInfo", customerShippingAddressInfo);
		
		mv.setViewName("/my_account/admin/shipping_address");
		
		return mv;

	}
	
	

	@RequestMapping(value = "/account/edit/billing/address",method=RequestMethod.GET)
	public ModelAndView editBillAddress()
	{
		ModelAndView mv = new ModelAndView();
		//********************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User_Lot user_Lot = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user_Lot.getFirst_name() + " " + user_Lot.getLast_name());
        //********************************************************************************************

        int user_id = user_Lot.getId();
        BillingAddress billAddress = billingAddressRepository.findByUserId(user_id);

        mv.addObject("ba", billAddress);
		
		mv.setViewName("/my_account/admin/edit-bill-address");
		
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
        User_Lot user_Lot = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user_Lot.getFirst_name() + " " + user_Lot.getLast_name());
        //******************************************************************************************** 
		int user_id = user_Lot.getId();
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
		
		return new ModelAndView("redirect:/admin/account/addresses/" + user_id);
	}
	
	
	@RequestMapping(value = "/account/billing/address/{lotId}",method=RequestMethod.GET)
	public ModelAndView billingAddressLot(@PathVariable long lotId)
	{
		ModelAndView mv = new ModelAndView();
		
		//********************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User_Lot user_Lot = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user_Lot.getFirst_name() + " " + user_Lot.getLast_name());
        //********************************************************************************************
      
        Optional<Lot> obj = lotRepository.findById(lotId);
        Lot lot = obj.get();
        
        BillingAddress billingAddressInfo = new BillingAddress();
        
        mv.addObject("billingAddressInfo", billingAddressInfo);
        mv.addObject("lots", lot);
		
		mv.setViewName("/my_account/admin/billing-address-lot");
		
		return mv;

	}
	
	//post method
		@RequestMapping(value="/account/billing/address/test/{lotId}", method=RequestMethod.POST)
		public ModelAndView saveAddressLot(@PathVariable long lotId,@Valid BillingAddress customerBillingAddressInfo, BindingResult bindingResult) {
			ModelAndView mv = new ModelAndView();
			//********************************************************************************************
					Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			        User_Lot user_Lot = userService.findUserByEmail(auth.getName());
			        mv.addObject("userName",user_Lot.getFirst_name() + " " + user_Lot.getLast_name());
			//********************************************************************************************        
			
			if (bindingResult.hasErrors()) {
				mv.setViewName("/my_account/admin/billing_address");
				
			}
			
			//int user_id = user.getId();
			
			Optional<Lot> obj = lotRepository.findById(lotId);
	        Lot lot = obj.get();
	        
			customerBillingAddressInfo.setUser(user_Lot);
			customerBillingAddressInfoService.saveBillInfo(customerBillingAddressInfo);
			
			mv.addObject("message", "Address have been successfully saved");
			mv.addObject("customerBillingAddressInfo", new BillingAddress());
			mv.addObject("lt", lot);
			
			return new ModelAndView("redirect:/admin/account/show/billing/address/" + lotId);
		}
		
		
		@RequestMapping(value = "/account/edit/billing/address/{lotId}",method=RequestMethod.GET)
		public ModelAndView upBillAddress(@PathVariable long lotId)
		{
			ModelAndView mv = new ModelAndView();
			//********************************************************************************************
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        User_Lot user_Lot = userService.findUserByEmail(auth.getName());
	        mv.addObject("userName",user_Lot.getFirst_name() + " " + user_Lot.getLast_name());
	        //********************************************************************************************

	        int user_id = user_Lot.getId();
	        BillingAddress billAddress = billingAddressRepository.findByUserId(user_id);

	        Optional<Lot> obj = lotRepository.findById(lotId);
	        Lot lot = obj.get();
	        mv.addObject("ba", billAddress);
	        mv.addObject("lots", lot);
			mv.setViewName("/my_account/admin/edit-bill-add-lot");
			
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
	        User_Lot user_Lot = userService.findUserByEmail(auth.getName());
	        mv.addObject("userName",user_Lot.getFirst_name() + " " + user_Lot.getLast_name());
	        //******************************************************************************************** 
			int user_id = user_Lot.getId();
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
			
			return new ModelAndView("redirect:/admin/account/show/billing/address/" + lotId);
		}
		
		@RequestMapping(value="/account/billing/address/test", method=RequestMethod.POST)
		public ModelAndView saveAddress(@Valid BillingAddress customerBillingAddressInfo, BindingResult bindingResult) {
			ModelAndView mv = new ModelAndView();
			//********************************************************************************************
					Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			        User_Lot user_Lot = userService.findUserByEmail(auth.getName());
			        mv.addObject("userName",user_Lot.getFirst_name() + " " + user_Lot.getLast_name());
			//********************************************************************************************        
			
			if (bindingResult.hasErrors()) {
				mv.setViewName("/my_account/user/billing_address");
				
			}
			
			int user_id = user_Lot.getId();
			
			customerBillingAddressInfo.setUser(user_Lot);
			customerBillingAddressInfoService.saveBillInfo(customerBillingAddressInfo);
			
			mv.addObject("message", "Address have been successfully saved");
			mv.addObject("customerBillingAddressInfo", new BillingAddress());
			
			return new ModelAndView("redirect:/admin/account/addresses/" + user_id);
		}
		
		// displaying billing address if exist
		@SuppressWarnings("unused")
		@RequestMapping(value="/account/show/billing/address/{lotId}")
		public ModelAndView showBillAdd(@Valid BillingAddress billingAddress, BindingResult bindingResult, @PathVariable("lotId") long lotId) {
			
		
			ModelAndView mv = new ModelAndView();
			//********************************************************************************************
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        User_Lot user_Lot = userService.findUserByEmail(auth.getName());
	        mv.addObject("userName",user_Lot.getFirst_name() + " " + user_Lot.getLast_name());
	        //********************************************************************************************
	        
	        Optional<Lot> newLot = lotRepository.findById(lotId);
	        Lot lots = newLot.get();
	        
	        int user_id=user_Lot.getUser_id();
			
	        billingAddress = (@Valid BillingAddress) billingAddressRepository.findByUserId(user_id);
			
			if (billingAddress != null) {
				mv.addObject("billAddress", billingAddress);
			} else {
				mv.addObject("message", "Please add a billing address");
			}
			
			mv.addObject("lots", lots);
			mv.setViewName("/my_account/admin/addressBill");
			
			return mv;
			
		}
		
		@RequestMapping(value = "/account/edit/shipping/address",method=RequestMethod.GET)
		public ModelAndView editShipAddress()
		{
			ModelAndView mv = new ModelAndView();
			//********************************************************************************************
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        User_Lot user_Lot = userService.findUserByEmail(auth.getName());
	        mv.addObject("userName",user_Lot.getFirst_name() + " " + user_Lot.getLast_name());
	        //********************************************************************************************

	        int user_id = user_Lot.getId();
	        ShippingAddress customerShippingAddressInfo = shippingAddressRepository.findByUserId(user_id);
	        
	        //System.out.println("-------------------------------------------------------------------------------------------------" + shipping_add_id);
	        
	        mv.addObject("sa", customerShippingAddressInfo);
			
			mv.setViewName("/my_account/admin/edit-ship-address");
			
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
	        User_Lot user_Lot = userService.findUserByEmail(auth.getName());
	        mv.addObject("userName",user_Lot.getFirst_name() + " " + user_Lot.getLast_name());
	        //******************************************************************************************** 
			int user_id = user_Lot.getId();
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
			
			return new ModelAndView("redirect:/admin/account/addresses/" + user_id);
		}
		
		@RequestMapping(value="/account/shipping/address/test", method=RequestMethod.POST)
		public ModelAndView saveSHippingAddress(@Valid ShippingAddress customerShippingAddressInfo, BindingResult bindingResult) {
			ModelAndView mv = new ModelAndView();
			
			//********************************************************************************************
					Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			        User_Lot user_Lot = userService.findUserByEmail(auth.getName());
			        mv.addObject("userName",user_Lot.getFirst_name() + " " + user_Lot.getLast_name());
			        //********************************************************************************************
			        
			if (bindingResult.hasErrors()) {
				mv.setViewName("/my_account/admin/billing_address");
				
			}
			
			int user_id=user_Lot.getId();
			customerShippingAddressInfo.setUser(user_Lot);
			customerShippingAddressInfoService.saveShipInfo(customerShippingAddressInfo);
			
			mv.addObject("message", "Address have been successfully saved");
			mv.addObject("customerShippingAddressInfo", new ShippingAddress());
			
			return new ModelAndView("redirect:/admin/account/addresses/" + user_id);
		}
		
		@RequestMapping(value = "/account/shipping/address/{lotId}",method=RequestMethod.GET)
		public ModelAndView showOrderdetailsLot(@PathVariable long lotId)
		{
			ModelAndView mv = new ModelAndView();
			//********************************************************************************************
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    User_Lot user_Lot = userService.findUserByEmail(auth.getName());
		    mv.addObject("userName",user_Lot.getFirst_name() + " " + user_Lot.getLast_name());
		    //********************************************************************************************

		    Optional<Lot> obj = lotRepository.findById(lotId);
		    Lot lot = obj.get();
		    
		    ShippingAddress customerShippingAddressInfo = new ShippingAddress();
		  
		    mv.addObject("customerShippingAddressInfo", customerShippingAddressInfo);
			mv.addObject("lots", lot);
			mv.setViewName("/my_account/admin/shipping-address-lot");
			
			return mv;

		}

		
		@RequestMapping(value = "/account/edit/shipping/address/{lotId}",method=RequestMethod.GET)
		public ModelAndView editShipAddressLot(@PathVariable long lotId)
		{
			ModelAndView mv = new ModelAndView();
			//********************************************************************************************
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    User_Lot user_Lot = userService.findUserByEmail(auth.getName());
		    mv.addObject("userName",user_Lot.getFirst_name() + " " + user_Lot.getLast_name());
		    //********************************************************************************************

		    Optional<Lot> obj = lotRepository.findById(lotId);
		    Lot lot = obj.get();
		    
		    int user_id = user_Lot.getId();
		    ShippingAddress customerShippingAddressInfo = shippingAddressRepository.findByUserId(user_id);
		    
		    //System.out.println("-------------------------------------------------------------------------------------------------" + shipping_add_id);
		    
		    mv.addObject("sa", customerShippingAddressInfo);
			mv.addObject("lots", lot);
			mv.setViewName("/my_account/admin/edit-ship-add-lot");
			
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
		    User_Lot user_Lot = userService.findUserByEmail(auth.getName());
		    mv.addObject("userName",user_Lot.getFirst_name() + " " + user_Lot.getLast_name());
		    //******************************************************************************************** 
			int user_id = user_Lot.getId();
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
			
			return new ModelAndView("redirect:/admin/account/show/shipping/address/" + lotId);
		}
		
		@RequestMapping(value="/account/shipping/address/test/{lotId}", method=RequestMethod.POST)
		public ModelAndView saveSHippingAddressLot(@PathVariable long lotId, @Valid ShippingAddress customerShippingAddressInfo, BindingResult bindingResult) {
			ModelAndView mv = new ModelAndView();
			
			//********************************************************************************************
					Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			        User_Lot user_Lot = userService.findUserByEmail(auth.getName());
			        mv.addObject("userName",user_Lot.getFirst_name() + " " + user_Lot.getLast_name());
			        //********************************************************************************************
			        
			if (bindingResult.hasErrors()) {
				mv.setViewName("/my_account/admin/billing_address");
				
			}
			
			  Optional<Lot> obj = lotRepository.findById(lotId);
			  Lot lot = obj.get();
			    
			//int user_id=user.getId();
			  
			customerShippingAddressInfo.setUser(user_Lot);
			customerShippingAddressInfoService.saveShipInfo(customerShippingAddressInfo);
			
			mv.addObject("message", "Address have been successfully saved");
			mv.addObject("customerShippingAddressInfo", new ShippingAddress());
			mv.addObject("lt", lot);
			return new ModelAndView("redirect:/admin/account/show/shipping/address/" + lotId);
		}
		
		
		// displaying billing address if exist
		@SuppressWarnings("unused")
		@RequestMapping(value="/account/show/shipping/address/{lotId}")
		public ModelAndView showShipAdd(@Valid ShippingAddress shippingAddress, BindingResult bindingResult, @PathVariable("lotId") long lotId) {
			

			ModelAndView mv = new ModelAndView();
			//********************************************************************************************
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    User_Lot user_Lot = userService.findUserByEmail(auth.getName());
		    mv.addObject("userName",user_Lot.getFirst_name() + " " + user_Lot.getLast_name());
		    //********************************************************************************************
		    
		    Optional<Lot> newLot = lotRepository.findById(lotId);
		    Lot lots = newLot.get();
		    
		    int user_id=user_Lot.getUser_id();
			
		    shippingAddress = (@Valid ShippingAddress) shippingAddressRepository.findByUserId(user_id);
			
			if (shippingAddress != null) {
				mv.addObject("shippAddress", shippingAddress);
			} else {
				mv.addObject("msg", "Please add a shipping address");
			}
			
			mv.addObject("lots", lots);
			mv.setViewName("/my_account/admin/addressShip");
			
			return mv;
			
		}
		
		
		@RequestMapping(value = "/lot/details/{lotId}",method=RequestMethod.GET)
		public ModelAndView showLotdetails(@PathVariable("lotId") long lotId)
		{
			ModelAndView mv = new ModelAndView();
			
			//********************************************************************************************
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        User_Lot user_Lot = userService.findUserByEmail(auth.getName());
	        mv.addObject("userName",user_Lot.getFirst_name() + " " + user_Lot.getLast_name());
	        //********************************************************************************************
	        
			Optional<Lot> new_obj = lotRepository.findById(lotId);
			Lot lots= new_obj.get();
			
	
	
			//List<Product> product = productRepository.findAllByLotId(lotId);
			List<Product> product = productRepository.findAllByLotId(lotId);
			
			//------------------------------------------------------------------------------counting volume
			int vol= 0;
			int i=0;
			product.get(i).getA_stock();
			
			for(i = 0; i< product.size(); i++) {
				
				String st =product.get(i).getA_stock();
				
				vol =vol + (Integer.parseInt(st)); 	
			}
			//----------------------------------------------------------------------------------------
			
			
		
			lots.setVolume(vol); // setiing lot volume
			
			//------------------------------------------------------------------------------------
			Set<Product> set_product = lots.getProductList();
			
			
			List<String> imageList = new ArrayList<String>();
			
			for(Product set : set_product) {
				if(!(set.getA_media_image_0_()).isEmpty()) {
					imageList.add(set.getA_media_image_0_());
				}
				
				if(!(set.getA_media_image_1_()).isEmpty()) {
					imageList.add(set.getA_media_image_1_());
				}
				
				if(!(set.getA_media_image_2_()).isEmpty()) {
					imageList.add(set.getA_media_image_2_());
				}
				
			}
			
			
			
			lotRepository.save(lots); // updating the lot in the database
			
			mv.addObject("lots",lots);
			mv.addObject("products", product);
			mv.addObject("images", imageList);
			
	
			mv.setViewName("/my_account/admin/lotDetails");
			return mv;

		}
	
	
		@RequestMapping(value = "/order/details/{lotId}",method=RequestMethod.GET)
		public ModelAndView showOrderdetails(@PathVariable("lotId") long lotId)
		{
			ModelAndView mv = new ModelAndView();
			
			//********************************************************************************************
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        User_Lot user_Lot = userService.findUserByEmail(auth.getName());
	        mv.addObject("userName",user_Lot.getFirst_name() + " " + user_Lot.getLast_name());
	        //********************************************************************************************
	        int user_id = user_Lot.getId();
	        
	        BillingAddress billAddress= billingAddressRepository.findByUserId(user_id);
	        
	        ShippingAddress shippAddress = shippingAddressRepository.findByUserId(user_id);
	        
			Optional<Lot> new_obj = lotRepository.findById(lotId);
			Lot lots= new_obj.get();
			
			List<Product> product = productRepository.findAllByLotId(lotId);
			
			
			

			mv.addObject("lots",lots);
			mv.addObject("products", product);
			mv.addObject("billAddress", billAddress);
			mv.addObject("shippAddress", shippAddress);
			
			mv.addObject("message1", "Please add a Shipping address");
			mv.addObject("message2", "Please add a billing address");
			
			mv.setViewName("admin-orderDetails");
			return mv;

		}
		
		@RequestMapping(value = "/order/confirmation/{lotId}",method=RequestMethod.GET)
		public ModelAndView shwOrderdetails(@PathVariable("lotId") long lotId)
		{
			ModelAndView mv = new ModelAndView();
			
			//********************************************************************************************
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        User_Lot user_Lot = userService.findUserByEmail(auth.getName());
	        mv.addObject("userName",user_Lot.getFirst_name() + " " + user_Lot.getLast_name());
	        //********************************************************************************************
	        
	       int user_id = user_Lot.getId();
	       ShippingAddress shippAddress = shippingAddressRepository.findByUserId(user_id);
	        
			Optional<Lot> new_obj = lotRepository.findById(lotId);
			Lot lots= new_obj.get();
			
			List<Product> product = productRepository.findAllByLotId(lotId);
			
			 //----------------------------------------------------------------------------------------------------------------------
	        Map<String, Object> model = new HashMap<>();
	        model.put("name", user_Lot.getFirst_name() + " " + user_Lot.getLast_name());
	        model.put("message1", "Thank you for your purchage. You can see the current status of your order at any time under " + "http://localhost:8090/admin/orders/list"+ "." + 
	        						"\nIf you have any questions about your customer account or your order, \nplease send us an e-mail.");
	        
	   
	        MailRequest mailRequest = new MailRequest();
	        mailRequest.setName(user_Lot.getFirst_name());
	        mailRequest.setFrom("noreply@domain.com");
	        mailRequest.setTo(user_Lot.getEmail());
	        mailRequest.setSubject("Order confirmation");
	        
	        emailService.sendEmail_confirm_order(mailRequest, model);
	        //----------------------------------------------------------------------------------------------------------------------

			mv.addObject("lots",lots);
			mv.addObject("products", product);
			mv.addObject("shippAddress", shippAddress);
			
			mv.addObject("msg", "Thank you for your purchase!");
			
			mv.setViewName("admin-order-confirmation");
			return mv;

		}
	
		
		//******************************************************************************Order list according to lotId*****************************************************************
		@RequestMapping(value="/orders/list/{lotId}", method=RequestMethod.GET)
		public ModelAndView showAllOrders(@PathVariable("lotId") Long lotId) {
			ModelAndView mv = new ModelAndView();
			//**********************************************************************
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        User_Lot user_Lot = userService.findUserByEmail(auth.getName());
	        mv.addObject("userName",user_Lot.getFirst_name() + " " + user_Lot.getLast_name());
	        //***********************************************************************
	        
	        int user_id = user_Lot.getId();
	        
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			
			Optional<Lot> newLot = lotRepository.findById(lotId);
			Lot lot = newLot.get();
			
			//----------------------------------------------------------------------------------------  
	        ShippingAddress shipAdd = shippingAddressRepository.findByUserId(user_id);
	        BillingAddress billAdd= billingAddressRepository.findByUserId(user_id);
	      //--------------------------------------------------------------------------------------  
			
			Order obj = orderRepository.findByUserAndLot(user_id, lotId);
			
			
			if (obj == null) {
				
			
				Order orderObj = new Order();
				
				orderObj.setOrder_status(true);
				orderObj.setOrderDate(formatter.format(date));
				orderObj.setLot(lot);
				orderObj.setUser(user_Lot);
				orderObj.setBillingAddress(billAdd);
				orderObj.setShippingAddress(shipAdd);
				orderRepository.save(orderObj);
				
				orderObj.getLot().setLot_status(0);
				//lot.setLot_status(0);
				lotRepository.save(lot);
				
			}
			mv.setViewName("admin-orderDetails");
			
			
			return new ModelAndView("redirect:/admin/order/confirmation/" + lotId);
		}
		
		
	//''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	@RequestMapping(value="/create/new/lot", method = RequestMethod.GET)
	public ModelAndView createLot() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("lot", new Lot());
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User_Lot user_Lot = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user_Lot.getFirst_name() + " " + user_Lot.getLast_name());
        
		mv.setViewName("/my_account/admin/create_new_lot");
		
		return mv;
	}
	
	
	@RequestMapping(value = "/save/lot",method=RequestMethod.POST) // testing  // success
	public ModelAndView save_lot( 
									@RequestParam("lotName") String lotName,
									@RequestParam("lotDescription") String lotDescription,
									@RequestParam("teaserImage")  MultipartFile teaserImage,
									@RequestParam("lotPrice") double lotPrice,
									@RequestParam("actualPrice") double actualPrice,
									@RequestParam("lot_status") int lot_status
									)
									
					
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(); 
		ModelAndView mv = new ModelAndView();
		
		Lot lot = new Lot();
		
		try {
			
			byte[] buffer = teaserImage.getBytes();
			String base64Image = Base64.getEncoder().encodeToString(buffer);
			lot.setTeaserImage(base64Image);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//------------------------------------------------------------------end of images test
		mv.addObject("lot",lot);
		lot.setLotName(lotName);
		lot.setLotDescription(lotDescription);
		lot.setActualPrice(actualPrice);
		lot.setLotPrice(lotPrice);
		lot.setLot_status(lot_status);
		
		lot.setCreatedAt(formatter.format(date));
		//lot.setTeaserImage(teaserImage);
		
		
		
		
		mv.setViewName("/my_account/admin/create_new_lot");
		
		lotRepository.save(lot);
		//imagesRepository.saveAll(images);
		
		//return "data saved";
		return new ModelAndView("redirect:/admin/lot/product/" + lot.getLotId()) ;
	}
	
	
	@RequestMapping(value="/lot/product/{lotId}", method=RequestMethod.GET)
	public ModelAndView addProduct(@PathVariable long lotId) {
		ModelAndView mv = new ModelAndView();
		
		//****************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User_Lot user_Lot = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user_Lot.getFirst_name() + " " + user_Lot.getLast_name());
		//*****************************************************************************************************
        
		List<Product> products = productRepository.findAll();
		Optional<Lot> newLot = lotRepository.findById(lotId);
		
		Lot lot=newLot.get();
		mv.addObject("products", products);
		mv.addObject("lot", lot);
		
		
		mv.setViewName("/my_account/admin/products");
		System.out.println("#####################################################################" + lotId + "##############################################################");
		return mv;
		
	}
	
	
	@RequestMapping(value="/add/lot/product", method=RequestMethod.POST)
	public ModelAndView addProductInLot(@RequestParam("idChecked") List<String> idrates, @RequestParam(value ="lot_id") Long lot_id) {
		
		new ModelAndView();
		
		
		System.out.println("############################################################test add product in lot method##########################");
		
		
		Optional<Lot> newLot = lotRepository.findById(lot_id);
		Lot lot = newLot.get();
		
		new Product();
		
		ArrayList<Long> al = new ArrayList<Long>();
		

		if (idrates !=null) {
			Set<Product> productList = new HashSet<>();
			productList = lot.getProductList();
			
			for(String idrateStr: idrates) {
				Long idrate = Long.parseLong(idrateStr);
				al.add(idrate);
				
				Product newProduct = productRepository.find_BY_EAN(idrate);
				
				if (newProduct.equals(null)) {
					return null;
				}
				else {
					productList.add(newProduct);
				}
				
			}
			lot.setProductList(productList);
			lotRepository.save(lot);
			
		}
		
		return new ModelAndView("redirect:/admin/lot/details/" + lot_id);
		
	} 
	
	
	@RequestMapping(value="/lot/list", method = RequestMethod.GET)
	public ModelAndView lotList(@RequestParam(defaultValue="0") int page) {
		ModelAndView mv = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User_Lot user_Lot = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user_Lot.getFirst_name() + " " + user_Lot.getLast_name());
        
       List<Lot> lots = (List<Lot>)lotRepository.findAll();
       mv.addObject("lots", lots);
//        mv.addObject("lots", lotRepository.findAll(new PageRequest(page, 25)));
//        mv.addObject("currentPage", page);
		mv.setViewName("/my_account/admin/lot_list");
		
		return mv;
	}

	  
	//delete a lot
	// first need to find the lot to delete by id
	//we need to set the productList null in the lot
	// then delete the lot from the lotRepository
	@RequestMapping(value="/delete/lot/{lotId}")
	public ModelAndView deleteLot(@PathVariable("lotId") long lotId) {
		ModelAndView mv = new ModelAndView();
		
		//Order obj = orderRepository.findByLotId(lotId);
		Optional<Lot> newLot = lotRepository.findById(lotId);
		Lot lots = newLot.get();
		
		
		//obj.setLot(null);
		lots.setProductList(null);
		lotRepository.deleteById(lotId);
		
		
//		orderRepository.save(obj);
//		lotRepository.save(lots);
		mv.addObject("lot", lots);

		return new ModelAndView("redirect:/admin/lot/list");
	}
	
	//####################################################################################### product ###########################
	@RequestMapping(value = "/upload/product", method= RequestMethod.GET)
	public ModelAndView uploadProduct() {
		ModelAndView mv = new ModelAndView();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User_Lot user_Lot = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user_Lot.getFirst_name() + " " + user_Lot.getLast_name());
        
		mv.setViewName("/my_account/admin/product_upload");
		return mv;
	}
	
	
	@RequestMapping(value = "/product/process", method= RequestMethod.POST)
	public String processUploadProduct(MultipartFile multi_file) throws IOException {
		ModelAndView mv = new ModelAndView();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User_Lot user_Lot = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user_Lot.getFirst_name() + " " + user_Lot.getLast_name());
        
        try {
        	
        	
			fileLocation = productService.upload_file(multi_file);
			
			
			System.out.println("start set_index");
			productService.set_index();
			System.out.println("stop set_index");
			
			System.out.println("start save_product");
			productService.save_product();
			System.out.println("stop save_product");
			
			return "Data have been saved";
		} catch (IOException e) {
			
			return "Data not saved";
			
			
		}
        
   
		
	}
	
	
	@RequestMapping(value = "/product/list", method= RequestMethod.GET)
	public ModelAndView productList(@RequestParam(defaultValue="0") int page) {
		ModelAndView mv = new ModelAndView();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User_Lot user_Lot = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user_Lot.getFirst_name() + " " + user_Lot.getLast_name());
        
        List<Product> product = productRepository.findAll();
      
        mv.addObject("products", product);
        
//        mv.addObject("products", productRepository.findAll(new PageRequest(page, 50)));
//        mv.addObject("currentPage", page);
		mv.setViewName("/my_account/admin/product_list");
		return mv;
	}
	
	
	public File convert(MultipartFile file) throws IOException{
		File convFile = new File(file.getOriginalFilename());
		convFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
		
	}
	
	//####################################################################################### product ###########################
	
	@RequestMapping(value = "/account/all/list", method= RequestMethod.GET)
	public ModelAndView showAllList() {
		ModelAndView mv = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User_Lot user_Lot = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user_Lot.getFirst_name() + " " + user_Lot.getLast_name());
        
        List<User_Lot> user_Lots = userRepo.findAll();     
        mv.addObject("users", user_Lots);
        
		mv.setViewName("/my_account/admin/showAll");
		return mv;
	}

	
	@RequestMapping(value = "/account/user/list", method= RequestMethod.GET)
	public ModelAndView showUserList() {
		ModelAndView mv = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User_Lot user_Lot = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user_Lot.getFirst_name() + " " + user_Lot.getLast_name());
        
        List<User_Lot> user_Lots = userRepo.findAllUser();     
        mv.addObject("users", user_Lots);
        
		mv.setViewName("/my_account/admin/user-list");
		return mv;
	}
	
	
	@RequestMapping(value = "/account/administrators", method= RequestMethod.GET)
	public ModelAndView showAdmin() {
		ModelAndView mv = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User_Lot user_Lot = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user_Lot.getFirst_name() + " " + user_Lot.getLast_name());
        
        List<User_Lot> user_Lots = userRepo.findAllAdmin();     
        mv.addObject("users", user_Lots);
        
		mv.setViewName("/my_account/admin/administrators");
		return mv;
	}
	
	// get method for editing user details from admin view
	@RequestMapping(value = "/edit/user/{user_id}", method= RequestMethod.GET)
	public ModelAndView edit(@PathVariable (value="user_id") int user_id) {
		ModelAndView mv = new ModelAndView();
		
		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		/*Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());*/
      //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        
        Optional<User_Lot> obj = userRepo.findById(user_id);
        User_Lot user_Lots = obj.get();
        
        mv.addObject("user", user_Lots);
		mv.setViewName("/my_account/admin/edit-user-details");
		
		return mv;
	} // end of method edit
	
	// update user details from admin view
		@RequestMapping(value = "/update/user/{user_id}", method= RequestMethod.POST)
		public ModelAndView editPost(@PathVariable (value="user_id") int user_id,
										@RequestParam String first_name,
										@RequestParam String last_name,
										@RequestParam String company,
										@RequestParam String email) {
	
			ModelAndView mv = new ModelAndView();
			
			//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			/*Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        User user = userService.findUserByEmail(auth.getName());
	        mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());*/
			//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	        
	        Optional<User_Lot> obj = userRepo.findById(user_id);
	        User_Lot user_Lots = obj.get(); 
	        
	        user_Lots.setFirst_name(first_name);
	        user_Lots.setLast_name(last_name);
	        user_Lots.setCompany(company);
	        user_Lots.setEmail(email);
	        
	        userRepo.save(user_Lots);
	        
	        mv.addObject("usr", user_Lots);
			mv.setViewName("/my_account/admin/edit-user-details");
			
			return new ModelAndView("redirect:/admin/account//all/list");
		} // end of method edit
		
		
		//*****************************************************************************************************************
		
		@RequestMapping(value="/update_user_role", method=RequestMethod.POST)
		public ModelAndView userRole(@RequestParam int user_id, @RequestParam String attribute_select) {
			ModelAndView mv = new ModelAndView();
			
			String user_role = "USER";
			String admin_role = "ADMIN";
			
			if (attribute_select.matches(admin_role)) {
				userRepo.update_user_role(1, user_id);
			} else if (attribute_select.matches(user_role)) {
				userRepo.update_user_role(2, user_id);
			}
			
			return new ModelAndView("redirect:/admin/account/all/list");
		}
	

	@RequestMapping(value = "/edit/adminn/{user_id}", method= RequestMethod.GET)
	public ModelAndView editAdmin(@PathVariable int user_id) {
		ModelAndView mv = new ModelAndView();
		//----------------------------------------------------------------------------------
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User_Lot user_Lot = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user_Lot.getFirst_name() + " " + user_Lot.getLast_name());
        //--------------------------------------------------------------------------------
        
        Optional<User_Lot> obj = userRepo.findById(user_id);
        User_Lot user_Lots = obj.get();
        
        mv.addObject("user", user_Lots);
		mv.setViewName("/my_account/admin/edit-admin-details");
		return mv;
	}
	
	// update admin details from admin view//******************************OKAY
			@RequestMapping(value = "/update/adminn/{user_id}", method= RequestMethod.POST)
			public ModelAndView editAdmin(@PathVariable (value="user_id") int user_id,
											@RequestParam String first_name,
											@RequestParam String last_name,
											@RequestParam String company) {
		
				ModelAndView mv = new ModelAndView();
				
				//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
				/*Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		        User user = userService.findUserByEmail(auth.getName());
		        mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());*/
				//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		        
		        Optional<User_Lot> obj = userRepo.findById(user_id);
		        User_Lot user_Lots = obj.get(); 
		        
		        user_Lots.setFirst_name(first_name);
		        user_Lots.setLast_name(last_name);
		        user_Lots.setCompany(company);
		      
		        
		        userRepo.save(user_Lots);
		        
		        mv.addObject("usr", user_Lots);
				mv.setViewName("/my_account/admin/edit-admin-details");
				
				return new ModelAndView("redirect:/admin/account/all/list");
			} // end of method edit
			
	
	@RequestMapping(value = "/account/paid/invoices", method= RequestMethod.GET)
	public ModelAndView showPaidInvoices() {
		ModelAndView mv = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User_Lot user_Lot = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user_Lot.getFirst_name() + " " + user_Lot.getLast_name());
		mv.setViewName("/my_account/admin/paid_invoice");
		return mv;
	}
	
	@RequestMapping(value = "/account/unpaid/invoices", method= RequestMethod.GET)
	public ModelAndView showUnpaidInvoices() {
		ModelAndView mv = new ModelAndView();
		//**************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User_Lot user_Lot = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user_Lot.getFirst_name() + " " + user_Lot.getLast_name());
        //*************************************************************************
        
		mv.setViewName("/my_account/admin/unpaid_invoice");
		return mv;
	}

	
	//****************************************************************************************Order*********************************************************
	
	@RequestMapping(value="/order/list", method=RequestMethod.GET)
	public ModelAndView showOrderList() {
		ModelAndView mv = new ModelAndView();
		//**************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User_Lot user_Lot = userService.findUserByEmail(auth.getName());
		mv.addObject("userName",user_Lot.getFirst_name() + " " + user_Lot.getLast_name());
		//*************************************************************************
		
		List<Order> oderList = orderRepository.findAll();
		
		//int user_id = user.getId();
		List<Lot> lots = (List<Lot>)lotRepository.findAll();
	    mv.addObject("lots", lots);

		mv.addObject("orderLists", oderList);
		
		mv.setViewName("/my_account/admin/order");
		
		return mv;
		
	}
	
	
	@RequestMapping(value="/order/info", method=RequestMethod.GET)
	public ModelAndView viewOrderList(@RequestParam(defaultValue="0") int page) {
		ModelAndView mv = new ModelAndView();
		//**************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User_Lot user_Lot = userService.findUserByEmail(auth.getName());
		mv.addObject("userName",user_Lot.getFirst_name() + " " + user_Lot.getLast_name());
		//*************************************************************************
		
		
		
		List<Order> oderList = orderRepository.findAll();
		mv.addObject("orderLists", oderList);

		mv.setViewName("/my_account/admin/order-info");
		
		return mv;
		
	}
	
	//****************************************************************************************Slider images*********************************************************
	
	@SuppressWarnings("unused")
	@RequestMapping(value="/upload/slider/images", method=RequestMethod.GET)
	public ModelAndView uploadSliderImages() {
		ModelAndView mv = new ModelAndView();
		//**************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User_Lot user_Lot = userService.findUserByEmail(auth.getName());
		mv.addObject("userName",user_Lot.getFirst_name() + " " + user_Lot.getLast_name());
		//*************************************************************************
		
		SliderImages images = new SliderImages();

		mv.addObject("img", images);
		mv.setViewName("/my_account/admin/slider-images");
		return mv;
	}
	
	
	@RequestMapping(value = "/save/slider/images",method=RequestMethod.POST) // testing  // success
	public ModelAndView saveImages( 	
									@RequestParam("image1")  MultipartFile image1,
									@RequestParam("image2")  MultipartFile image2,
									@RequestParam("image3")  MultipartFile image3) {

 
		ModelAndView mv = new ModelAndView();
		SliderImages images = new SliderImages();
		
		try {
			
			byte[] buffer1 = image1.getBytes();
			String base64Image = Base64.getEncoder().encodeToString(buffer1);
			images.setImage1(base64Image);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {	
				byte[] buffer2 = image2.getBytes();
				String base64Image = Base64.getEncoder().encodeToString(buffer2);
				images.setImage2(base64Image);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		try {
			
			byte[] buffer3 = image3.getBytes();
			String base64Image = Base64.getEncoder().encodeToString(buffer3);
			images.setImage3(base64Image);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		sliderImagesRepoaitory.save(images);
		
		mv.addObject("image", images);
		//imagesRepository.saveAll(images);
		
		//return "data saved";
		return new ModelAndView("redirect:/admin/test/slider/images") ;
	}
	
	
	@RequestMapping(value="/test/slider/images", method=RequestMethod.GET)
	public ModelAndView testSliderImages() {
		ModelAndView mv = new ModelAndView();
		//**************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User_Lot user_Lot = userService.findUserByEmail(auth.getName());
		mv.addObject("userName",user_Lot.getFirst_name() + " " + user_Lot.getLast_name());
		//*************************************************************************
		
		List<SliderImages> images = sliderImagesRepoaitory.findAll();
		
		
		mv.addObject("img", images);
		mv.setViewName("/my_account/admin/slider-test");
		
		return mv;
	}
	
	@RequestMapping(value="/slider/image/list", method = RequestMethod.GET)
	public ModelAndView sliderImg(@RequestParam(defaultValue="0") int page) {
		ModelAndView mv = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User_Lot user_Lot = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user_Lot.getFirst_name() + " " + user_Lot.getLast_name());
        
    
       
       List<SliderImages> slider = sliderImagesRepoaitory.findAll();
       
       mv.addObject("slider", slider);
//        mv.addObject("lots", lotRepository.findAll(new PageRequest(page, 25)));
//        mv.addObject("currentPage", page);
		mv.setViewName("/my_account/admin/slider-image-table");
		
		return mv;
	}
	
	
	
	//delete a lot
	// first need to find the lot to delete by id
	//we need to set the productList null in the lot
	// then delete the lot from the lotRepository
	@RequestMapping(value="/slider/delete/{id}", method= RequestMethod.GET)
	public ModelAndView deleteSliderIm(@PathVariable(value="id") int id) {
		
		ModelAndView mv = new ModelAndView();
		
		Optional<SliderImages> obj = sliderImagesRepoaitory.findById(id);
		SliderImages slider = obj.get();
		
		System.out.println("###############################################################################################" + id);
		sliderImagesRepoaitory.deleteById(id);
		
		mv.addObject("slid", slider);
		//mv.setViewName("/my_account/admin/slider-image-table");
		return new ModelAndView("redirect:/admin/upload/slider/images");
	}
	
	
	//########################################################## Update order delivery status #######################################################
	
	@RequestMapping(value="/order/delivery/status", method=RequestMethod.GET)
	public ModelAndView orderStatust() {
		ModelAndView mv = new ModelAndView();
		//**************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User_Lot user_Lot = userService.findUserByEmail(auth.getName());
		mv.addObject("userName",user_Lot.getFirst_name() + " " + user_Lot.getLast_name());
		//*************************************************************************
		
		
		mv.setViewName("/my_account/admin/delivery-status");
		
		return mv;
		
	}
	
	//###############################################################################################################################################
	
	@RequestMapping(value="/order/AGB", method=RequestMethod.GET)
	public ModelAndView showAGB() {
		ModelAndView mv = new ModelAndView();

		mv.setViewName("AGB");
		
		return mv;
		
	}
	
	@RequestMapping(value="/order/wiederruf", method=RequestMethod.GET)
	public ModelAndView showwiederruf() {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("wiederruf");
		
		return mv;
		
	}

}// end of admin controller
	
	
