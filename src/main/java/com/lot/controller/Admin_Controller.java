package com.lot.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.lot.model.BillingAddress;
import com.lot.model.CustomizedProduct;
import com.lot.model.Lot;
import com.lot.model.Lot_Lager;
import com.lot.model.MailRequest;
import com.lot.model.Lot_Order;
import com.lot.model.Product;
import com.lot.model.ProductNotFoundException;
import com.lot.model.ShippingAddress;
import com.lot.model.SliderImages;
import com.lot.model.Stocklots_Offer;
import com.lot.model.User;
import com.lot.repository.BillingAddressRepository;
import com.lot.repository.LotRepository;
import com.lot.repository.Lot_LagerRepository;
import com.lot.repository.LotOrderRepository;
import com.lot.repository.ProductRepository;
import com.lot.repository.ShippingAddressRepository;
import com.lot.repository.SliderImagesRepository;
import com.lot.repository.Stocklots_Offer_Repository;
import com.lot.repository.UserRepository;
import com.lot.service.BillingAddressService;
import com.lot.service.EmailService;
import com.lot.service.LotService;
import com.lot.service.Lot_LagerService;
import com.lot.service.ProductService;
import com.lot.service.ShippingAddressService;
import com.lot.service.Stocklots_Offer_Service;
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
	private BillingAddressRepository billingAddressRepository;

	@Autowired
	private ShippingAddressRepository shippingAddressRepository;
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private LotOrderRepository orderRepository;

	private static String fileLocation;

	@Autowired
	private Lot_LagerService lot_LagerService;

	@Autowired
	private Lot_LagerRepository lot_LagerRepository;

	@Autowired
<<<<<<< HEAD
	private SliderImagesRepository sliderImagesRepoaitory;
=======
	private SliderImagesRepository sliderImageRepository;
	
	 
    @RequestMapping(value="/lot/home", method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
      //*************************************************************************************************
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
        //**************************************************************************************************
        List<SliderImages> images = sliderImageRepository.findAll();
        
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
        User user = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
        //************************************************************************************

        mv.addObject("users", user);
        mv.setViewName("/my_account/admin/admin-account");
		return mv;
	}
	
	
	@RequestMapping(value="/orders/list", method=RequestMethod.GET)
	public ModelAndView showOrderList(@RequestParam(defaultValue="0") int page) {
		ModelAndView mv = new ModelAndView();
		//**************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
		//*************************************************************************
		
		int user_id = user.getId();
		
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
        User user = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
        //************************************************************************************
        
        Optional<User> obj = userRepository.findById(user_id);
        User users = obj.get();
        

        mv.addObject("users", users);
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
		
		return new ModelAndView("redirect:/admin/account");
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
		mv.setViewName("/my_account/admin/loginDetails");
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
		
			mv.setViewName("/my_account/admin/my-addresses");
      
		return mv;
	}
	
	
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
		
		mv.setViewName("/my_account/admin/billing_address");
		
		return mv;
>>>>>>> 24fd5d7109fa729315c24432dfff3db1654da8a4

	@Autowired
	Stocklots_Offer_Service stocklots_Offer_Service;

	@Autowired
	Stocklots_Offer_Repository stocklots_Offer_Repository;

	/*
	 * ......................................49
	 */
	@RequestMapping(value = "/lot/home", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************
		List<SliderImages> images = sliderImagesRepoaitory.findAll();
		List<Lot> lots = lotService.findByEnabled();
		modelAndView.addObject("lots", lots);
		modelAndView.addObject("img", images);
		modelAndView.addObject("adminMessage", "Only active lot will be displayed");
		// System.out.println("*****************************************************************"+
		// lots +
		// "**************************************************************************");
		modelAndView.setViewName("admin-index");
		return modelAndView;
	}
<<<<<<< HEAD
=======
	
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
>>>>>>> 24fd5d7109fa729315c24432dfff3db1654da8a4

	/**
	 * --------------------------------------------50
	 * 
	 * @return
	 */
	@RequestMapping("/account")
	public ModelAndView show() {
		ModelAndView mv = new ModelAndView();
		// ************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// ************************************************************************************
		mv.addObject("users", user);
		mv.setViewName("/my_account/admin/admin-account");
		return mv;
	}

	/*
	 * @RequestMapping(value="/orders/list", method=RequestMethod.GET) public
	 * ModelAndView showOrderList(@RequestParam(defaultValue="0") int page) {
	 * ModelAndView mv = new ModelAndView();
	 * //**************************************************************************
	 * Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	 * User user = userService.findUserByEmail(auth.getName());
	 * mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
	 * //*************************************************************************
	 * 
	 * int user_id = user.getId();
	 * 
	 * List<Order_data> oderList = orderRepository.findByUserId(user_id);
	 * mv.addObject("orderLists", oderList);
	 * 
	 * 
	 * mv.setViewName("/my_account/admin/your_orders");
	 * 
	 * return mv;
	 * 
	 * }
	 * 
	 * 
	 * @RequestMapping(value = "/account/edit/general/info/{user_id}", method=
	 * RequestMethod.GET) public ModelAndView showInfo(@PathVariable int user_id) {
	 * ModelAndView mv = new ModelAndView();
	 * //***************************************************************************
	 * ********* Authentication auth =
	 * SecurityContextHolder.getContext().getAuthentication(); User user =
	 * userService.findUserByEmail(auth.getName());
	 * mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
	 * //***************************************************************************
	 * *********
	 * 
	 * Optional<User> obj = userRepository.findById(user_id); User users =
	 * obj.get();
	 * 
	 * 
	 * mv.addObject("users", users);
	 * mv.setViewName("/my_account/admin/edit-user-info");
	 * 
	 * return mv; }
	 */

	/*
	 * @RequestMapping(value ="/account/save/changes/{user_id}",
	 * method=RequestMethod.POST) public ModelAndView saveChanges(@PathVariable int
	 * user_id,
	 * 
	 * @RequestParam String first_name,
	 * 
	 * @RequestParam String last_name,
	 * 
	 * @RequestParam String company //@RequestParam String email ){ ModelAndView mv
	 * = new ModelAndView();
	 * 
	 * Optional<User> obj = userRepository.findById(user_id); User usrs = obj.get();
	 * 
	 * usrs.setFirst_name(first_name); usrs.setLast_name(last_name);
	 * usrs.setCompany(company); //usrs.setEmail(email);
	 * 
	 * userRepository.save(usrs);
	 * 
	 * mv.addObject("users", usrs); mv.addObject("msg", "Info have been updated");
	 * 
	 * //mv.setViewName("/my_account/user/loginDetails");
	 * 
	 * return new ModelAndView("redirect:/admin/account"); }
	 * 
	 * @RequestMapping(value = "/account/edit/login/details/{user_id}", method=
	 * RequestMethod.GET) public ModelAndView showLogin(@PathVariable int user_id) {
	 * ModelAndView mv = new ModelAndView();
	 * //***************************************************************************
	 * ********* Authentication auth =
	 * SecurityContextHolder.getContext().getAuthentication(); User user =
	 * userService.findUserByEmail(auth.getName());
	 * mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
	 * //***************************************************************************
	 * *********
	 * 
	 * Optional<User> obj = userRepository.findById(user_id); User users =
	 * obj.get();
	 * 
	 * mv.addObject("users", users);
	 * mv.setViewName("/my_account/admin/loginDetails"); return mv; }
	 * 
	 * 
	 * @RequestMapping(value = "/account/update/login/details/{user_id}", method=
	 * RequestMethod.GET) public ModelAndView updateLogin(@PathVariable int user_id,
	 * 
	 * @RequestParam String password) { ModelAndView mv = new ModelAndView();
	 * //***************************************************************************
	 * ********* Authentication auth =
	 * SecurityContextHolder.getContext().getAuthentication(); User user =
	 * userService.findUserByEmail(auth.getName());
	 * mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
	 * //***************************************************************************
	 * *********
	 * 
	 * Optional<User> obj = userRepository.findById(user_id); User users =
	 * obj.get();
	 * 
	 * users.setPassword(password);
	 * 
	 * userRepository.save(users);
	 * 
	 * mv.addObject("users", users); mv.addObject("msg", "Info have been updated");
	 * 
	 * mv.setViewName("/my_account/user/loginDetails");
	 * 
	 * return new ModelAndView("redirect:/lot/login");
	 * 
	 * }
	 * 
	 * 
	 * @RequestMapping(value = "/account/addresses/{user_id}", method=
	 * RequestMethod.GET) public ModelAndView showDetails(@PathVariable int user_id)
	 * {
	 * 
	 * ModelAndView mv = new ModelAndView();
	 * //***************************************************************************
	 * ********************************************* Authentication auth =
	 * SecurityContextHolder.getContext().getAuthentication(); User user =
	 * userService.findUserByEmail(auth.getName());
	 * mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
	 * //***************************************************************************
	 * ********************************************* Optional<User> usr =
	 * userRepository.findById(user_id); //User users = obj.get(); //Note: If i dont
	 * use obj.get() then i have do..mv.addObject("user", usr); //if i user like
	 * User user= usr.get();---then i dont need mv.addObject();..i have use user
	 * directly int the link. //Otherwise it will show user_id can not be found on
	 * null error
	 * 
	 * BillingAddress billAddress= billingAddressRepository.findByUserId(user_id);
	 * 
	 * ShippingAddress shippAddress =
	 * shippingAddressRepository.findByUserId(user_id);
	 * 
	 * 
	 * mv.addObject("users",usr); mv.addObject("billAddress", billAddress);
	 * mv.addObject("shippAddress", shippAddress);
	 * 
	 * mv.addObject("message1", "Please add a Shipping address");
	 * mv.addObject("message2", "Please add a billing address");
	 * 
	 * mv.setViewName("/my_account/admin/my-addresses");
	 * 
	 * return mv; }
	 */

<<<<<<< HEAD
	/*
	 * @RequestMapping(value = "/account/billing/address",method=RequestMethod.GET)
	 * public ModelAndView billingAddress() { ModelAndView mv = new ModelAndView();
	 * 
	 * //***************************************************************************
	 * ***************** Authentication auth =
	 * SecurityContextHolder.getContext().getAuthentication(); User user =
	 * userService.findUserByEmail(auth.getName());
	 * mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
	 * //***************************************************************************
	 * *****************
	 * 
	 * BillingAddress billingAddressInfo = new BillingAddress();
	 * 
	 * mv.addObject("billingAddressInfo", billingAddressInfo);
	 * 
	 * mv.setViewName("/my_account/admin/billing_address");
	 * 
	 * return mv;
	 * 
	 * }
	 * 
	 * @RequestMapping(value = "/account/shipping/address",method=RequestMethod.GET)
	 * public ModelAndView showOrderdetails() { ModelAndView mv = new
	 * ModelAndView();
	 * //***************************************************************************
	 * ***************** Authentication auth =
	 * SecurityContextHolder.getContext().getAuthentication(); User user =
	 * userService.findUserByEmail(auth.getName());
	 * mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
	 * //***************************************************************************
	 * *****************
	 * 
	 * ShippingAddress customerShippingAddressInfo = new ShippingAddress();
	 * 
	 * mv.addObject("customerShippingAddressInfo", customerShippingAddressInfo);
	 * 
	 * mv.setViewName("/my_account/admin/shipping_address");
	 * 
	 * return mv;
	 * 
	 * }
	 * 
	 * 
	 * 
	 * @RequestMapping(value =
	 * "/account/edit/billing/address",method=RequestMethod.GET) public ModelAndView
	 * editBillAddress() { ModelAndView mv = new ModelAndView();
	 * //***************************************************************************
	 * ***************** Authentication auth =
	 * SecurityContextHolder.getContext().getAuthentication(); User user =
	 * userService.findUserByEmail(auth.getName());
	 * mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
	 * //***************************************************************************
	 * *****************
	 * 
	 * int user_id = user.getId(); BillingAddress billAddress =
	 * billingAddressRepository.findByUserId(user_id);
	 * 
	 * mv.addObject("ba", billAddress);
	 * 
	 * mv.setViewName("/my_account/admin/edit-bill-address");
	 * 
	 * return mv; }
	 * 
	 * //updating billing address...tested..working
	 * 
	 * @RequestMapping(value ="/account/update/billing/address",
	 * method=RequestMethod.POST) public ModelAndView
	 * saveBillAddChanges(@RequestParam String contact_person,
	 * 
	 * @RequestParam String street,
	 * 
	 * @RequestParam String city,
	 * 
	 * @RequestParam int zip_code,
	 * 
	 * @RequestParam String country,
	 * 
	 * @RequestParam String phone_number ){
	 * 
	 * 
	 * ModelAndView mv = new ModelAndView();
	 * //***************************************************************************
	 * ***************** Authentication auth =
	 * SecurityContextHolder.getContext().getAuthentication(); User user =
	 * userService.findUserByEmail(auth.getName());
	 * mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
	 * //***************************************************************************
	 * ***************** int user_id = user.getId(); BillingAddress bAdd =
	 * billingAddressRepository.findByUserId(user_id);
	 * 
	 * Lot lot = new Lot();
	 * 
	 * bAdd.setContact_person(contact_person); bAdd.setStreet(street);
	 * bAdd.setCity(city); bAdd.setZip_code(zip_code); bAdd.setCountry(country);
	 * bAdd.setPhone_number(phone_number);
	 * 
	 * billingAddressRepository.save(bAdd);
	 * 
	 * mv.addObject("bAd", bAdd); mv.addObject("msg", "Info have been updated");
	 * mv.addObject("lot", lot); //mv.setViewName("/my_account/user/loginDetails");
	 * 
	 * return new ModelAndView("redirect:/admin/account/addresses/" + user_id); }
	 * 
	 * 
	 * @RequestMapping(value =
	 * "/account/billing/address/{lotId}",method=RequestMethod.GET) public
	 * ModelAndView billingAddressLot(@PathVariable long lotId) { ModelAndView mv =
	 * new ModelAndView();
	 * 
	 * //***************************************************************************
	 * ***************** Authentication auth =
	 * SecurityContextHolder.getContext().getAuthentication(); User user =
	 * userService.findUserByEmail(auth.getName());
	 * mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
	 * //***************************************************************************
	 * *****************
	 * 
	 * Optional<Lot> obj = lotRepository.findById(lotId); Lot lot = obj.get();
	 * 
	 * BillingAddress billingAddressInfo = new BillingAddress();
	 * 
	 * mv.addObject("billingAddressInfo", billingAddressInfo); mv.addObject("lots",
	 * lot);
	 * 
	 * mv.setViewName("/my_account/admin/billing-address-lot");
	 * 
	 * return mv;
	 * 
	 * }
	 * 
	 * //post method
	 * 
	 * @RequestMapping(value="/account/billing/address/test/{lotId}",
	 * method=RequestMethod.POST) public ModelAndView saveAddressLot(@PathVariable
	 * long lotId,@Valid BillingAddress customerBillingAddressInfo, BindingResult
	 * bindingResult) { ModelAndView mv = new ModelAndView();
	 * //***************************************************************************
	 * ***************** Authentication auth =
	 * SecurityContextHolder.getContext().getAuthentication(); User user =
	 * userService.findUserByEmail(auth.getName());
	 * mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
	 * //***************************************************************************
	 * *****************
	 * 
	 * if (bindingResult.hasErrors()) {
	 * mv.setViewName("/my_account/admin/billing_address");
	 * 
	 * }
	 * 
	 * //int user_id = user.getId();
	 * 
	 * Optional<Lot> obj = lotRepository.findById(lotId); Lot lot = obj.get();
	 * 
	 * customerBillingAddressInfo.setUser(user);
	 * customerBillingAddressInfoService.saveBillInfo(customerBillingAddressInfo);
	 * 
	 * mv.addObject("message", "Address have been successfully saved");
	 * mv.addObject("customerBillingAddressInfo", new BillingAddress());
	 * mv.addObject("lt", lot);
	 * 
	 * return new ModelAndView("redirect:/admin/account/show/billing/address/" +
	 * lotId); }
	 * 
	 * 
	 * @RequestMapping(value =
	 * "/account/edit/billing/address/{lotId}",method=RequestMethod.GET) public
	 * ModelAndView upBillAddress(@PathVariable long lotId) { ModelAndView mv = new
	 * ModelAndView();
	 * //***************************************************************************
	 * ***************** Authentication auth =
	 * SecurityContextHolder.getContext().getAuthentication(); User user =
	 * userService.findUserByEmail(auth.getName());
	 * mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
	 * //***************************************************************************
	 * *****************
	 * 
	 * int user_id = user.getId(); BillingAddress billAddress =
	 * billingAddressRepository.findByUserId(user_id);
	 * 
	 * Optional<Lot> obj = lotRepository.findById(lotId); Lot lot = obj.get();
	 * mv.addObject("ba", billAddress); mv.addObject("lots", lot);
	 * mv.setViewName("/my_account/admin/edit-bill-add-lot");
	 * 
	 * return mv; }
	 * 
	 * //updating billing address...tested..working
	 * 
	 * @RequestMapping(value ="/account/update/billing/address/{lotId}",
	 * method=RequestMethod.POST) public ModelAndView
	 * updateBillAddChanges(@RequestParam String contact_person,
	 * 
	 * @RequestParam String street,
	 * 
	 * @RequestParam String city,
	 * 
	 * @RequestParam int zip_code,
	 * 
	 * @RequestParam String country,
	 * 
	 * @RequestParam String phone_number,
	 * 
	 * @PathVariable long lotId ){
	 * 
	 * 
	 * ModelAndView mv = new ModelAndView();
	 * //***************************************************************************
	 * ***************** Authentication auth =
	 * SecurityContextHolder.getContext().getAuthentication(); User user =
	 * userService.findUserByEmail(auth.getName());
	 * mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
	 * //***************************************************************************
	 * ***************** int user_id = user.getId(); BillingAddress bAdd =
	 * billingAddressRepository.findByUserId(user_id);
	 * 
	 * Optional<Lot> lot = lotRepository.findById(lotId); //Lot lot=obj.get();
	 * 
	 * bAdd.setContact_person(contact_person); bAdd.setStreet(street);
	 * bAdd.setCity(city); bAdd.setZip_code(zip_code); bAdd.setCountry(country);
	 * bAdd.setPhone_number(phone_number);
	 * 
	 * billingAddressRepository.save(bAdd);
	 * 
	 * mv.addObject("bAd", bAdd); mv.addObject("msg", "Info have been updated");
	 * mv.addObject("lot", lot); //mv.setViewName("/my_account/user/loginDetails");
	 * 
	 * return new ModelAndView("redirect:/admin/account/show/billing/address/" +
	 * lotId); }
	 * 
	 * @RequestMapping(value="/account/billing/address/test",
	 * method=RequestMethod.POST) public ModelAndView saveAddress(@Valid
	 * BillingAddress customerBillingAddressInfo, BindingResult bindingResult) {
	 * ModelAndView mv = new ModelAndView();
	 * //***************************************************************************
	 * ***************** Authentication auth =
	 * SecurityContextHolder.getContext().getAuthentication(); User user =
	 * userService.findUserByEmail(auth.getName());
	 * mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
	 * //***************************************************************************
	 * *****************
	 * 
	 * if (bindingResult.hasErrors()) {
	 * mv.setViewName("/my_account/user/billing_address");
	 * 
	 * }
	 * 
	 * int user_id = user.getId();
	 * 
	 * customerBillingAddressInfo.setUser(user);
	 * customerBillingAddressInfoService.saveBillInfo(customerBillingAddressInfo);
	 * 
	 * mv.addObject("message", "Address have been successfully saved");
	 * mv.addObject("customerBillingAddressInfo", new BillingAddress());
	 * 
	 * return new ModelAndView("redirect:/admin/account/addresses/" + user_id); }
	 * 
	 * // displaying billing address if exist
	 * 
	 * @SuppressWarnings("unused")
	 * 
	 * @RequestMapping(value="/account/show/billing/address/{lotId}") public
	 * ModelAndView showBillAdd(@Valid BillingAddress billingAddress, BindingResult
	 * bindingResult, @PathVariable("lotId") long lotId) {
	 * 
	 * 
	 * ModelAndView mv = new ModelAndView();
	 * //***************************************************************************
	 * ***************** Authentication auth =
	 * SecurityContextHolder.getContext().getAuthentication(); User user =
	 * userService.findUserByEmail(auth.getName());
	 * mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
	 * //***************************************************************************
	 * *****************
	 * 
	 * Optional<Lot> newLot = lotRepository.findById(lotId); Lot lots =
	 * newLot.get();
	 * 
	 * int user_id=user.getUser_id();
	 * 
	 * billingAddress = (@Valid BillingAddress)
	 * billingAddressRepository.findByUserId(user_id);
	 * 
	 * if (billingAddress != null) { mv.addObject("billAddress", billingAddress); }
	 * else { mv.addObject("message", "Please add a billing address"); }
	 * 
	 * mv.addObject("lots", lots); mv.setViewName("/my_account/admin/addressBill");
	 * 
	 * return mv;
	 * 
	 * }
	 * 
	 * @RequestMapping(value =
	 * "/account/edit/shipping/address",method=RequestMethod.GET) public
	 * ModelAndView editShipAddress() { ModelAndView mv = new ModelAndView();
	 * //***************************************************************************
	 * ***************** Authentication auth =
	 * SecurityContextHolder.getContext().getAuthentication(); User user =
	 * userService.findUserByEmail(auth.getName());
	 * mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
	 * //***************************************************************************
	 * *****************
	 * 
	 * int user_id = user.getId(); ShippingAddress customerShippingAddressInfo =
	 * shippingAddressRepository.findByUserId(user_id);
	 * 
	 * //System.out.println(
	 * "-------------------------------------------------------------------------------------------------"
	 * + shipping_add_id);
	 * 
	 * mv.addObject("sa", customerShippingAddressInfo);
	 * 
	 * mv.setViewName("/my_account/admin/edit-ship-address");
	 * 
	 * return mv; }
	 * 
	 * //updating shipping address...tested..working
	 * 
	 * @RequestMapping(value ="/account/update/shipping/address",
	 * method=RequestMethod.POST) public ModelAndView
	 * saveShipAddChanges(@RequestParam String contact_person,
	 * 
	 * @RequestParam String street,
	 * 
	 * @RequestParam String city,
	 * 
	 * @RequestParam int zip_code,
	 * 
	 * @RequestParam String country,
	 * 
	 * @RequestParam String phone_number ){
	 * 
	 * 
	 * ModelAndView mv = new ModelAndView();
	 * //***************************************************************************
	 * ***************** Authentication auth =
	 * SecurityContextHolder.getContext().getAuthentication(); User user =
	 * userService.findUserByEmail(auth.getName());
	 * mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
	 * //***************************************************************************
	 * ***************** int user_id = user.getId(); ShippingAddress sAdd =
	 * shippingAddressRepository.findByUserId(user_id);
	 * 
	 * sAdd.setContact_person(contact_person); sAdd.setStreet(street);
	 * sAdd.setCity(city); sAdd.setZip_code(zip_code); sAdd.setCountry(country);
	 * sAdd.setPhone_number(phone_number);
	 * 
	 * shippingAddressRepository.save(sAdd);
	 * 
	 * mv.addObject("sAd", sAdd); mv.addObject("msg", "Info have been updated");
	 * //mv.addObject("lotId", lot.getLotId());
	 * 
	 * //mv.setViewName("/my_account/user/loginDetails");
	 * 
	 * return new ModelAndView("redirect:/admin/account/addresses/" + user_id); }
	 * 
	 * @RequestMapping(value="/account/shipping/address/test",
	 * method=RequestMethod.POST) public ModelAndView saveSHippingAddress(@Valid
	 * ShippingAddress customerShippingAddressInfo, BindingResult bindingResult) {
	 * ModelAndView mv = new ModelAndView();
	 * 
	 * //***************************************************************************
	 * ***************** Authentication auth =
	 * SecurityContextHolder.getContext().getAuthentication(); User user =
	 * userService.findUserByEmail(auth.getName());
	 * mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
	 * //***************************************************************************
	 * *****************
	 * 
	 * if (bindingResult.hasErrors()) {
	 * mv.setViewName("/my_account/admin/billing_address");
	 * 
	 * }
	 * 
	 * int user_id=user.getId(); customerShippingAddressInfo.setUser(user);
	 * customerShippingAddressInfoService.saveShipInfo(customerShippingAddressInfo);
	 * 
	 * mv.addObject("message", "Address have been successfully saved");
	 * mv.addObject("customerShippingAddressInfo", new ShippingAddress());
	 * 
	 * return new ModelAndView("redirect:/admin/account/addresses/" + user_id); }
	 * 
	 * @RequestMapping(value =
	 * "/account/shipping/address/{lotId}",method=RequestMethod.GET) public
	 * ModelAndView showOrderdetailsLot(@PathVariable long lotId) { ModelAndView mv
	 * = new ModelAndView();
	 * //***************************************************************************
	 * ***************** Authentication auth =
	 * SecurityContextHolder.getContext().getAuthentication(); User user =
	 * userService.findUserByEmail(auth.getName());
	 * mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
	 * //***************************************************************************
	 * *****************
	 * 
	 * Optional<Lot> obj = lotRepository.findById(lotId); Lot lot = obj.get();
	 * 
	 * ShippingAddress customerShippingAddressInfo = new ShippingAddress();
	 * 
	 * mv.addObject("customerShippingAddressInfo", customerShippingAddressInfo);
	 * mv.addObject("lots", lot);
	 * mv.setViewName("/my_account/admin/shipping-address-lot");
	 * 
	 * return mv;
	 * 
	 * }
	 * 
	 * 
	 * @RequestMapping(value =
	 * "/account/edit/shipping/address/{lotId}",method=RequestMethod.GET) public
	 * ModelAndView editShipAddressLot(@PathVariable long lotId) { ModelAndView mv =
	 * new ModelAndView();
	 * //***************************************************************************
	 * ***************** Authentication auth =
	 * SecurityContextHolder.getContext().getAuthentication(); User user =
	 * userService.findUserByEmail(auth.getName());
	 * mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
	 * //***************************************************************************
	 * *****************
	 * 
	 * Optional<Lot> obj = lotRepository.findById(lotId); Lot lot = obj.get();
	 * 
	 * int user_id = user.getId(); ShippingAddress customerShippingAddressInfo =
	 * shippingAddressRepository.findByUserId(user_id);
	 * 
	 * //System.out.println(
	 * "-------------------------------------------------------------------------------------------------"
	 * + shipping_add_id);
	 * 
	 * mv.addObject("sa", customerShippingAddressInfo); mv.addObject("lots", lot);
	 * mv.setViewName("/my_account/admin/edit-ship-add-lot");
	 * 
	 * return mv; }
	 * 
	 * //updating shipping address...tested..working
	 * 
	 * @RequestMapping(value ="/account/update/shipping/address/{lotId}",
	 * method=RequestMethod.POST) public ModelAndView
	 * saveShipAddChangesLot(@PathVariable long lotId,
	 * 
	 * @RequestParam String contact_person,
	 * 
	 * @RequestParam String street,
	 * 
	 * @RequestParam String city,
	 * 
	 * @RequestParam int zip_code,
	 * 
	 * @RequestParam String country,
	 * 
	 * @RequestParam String phone_number ){
	 * 
	 * 
	 * ModelAndView mv = new ModelAndView();
	 * //***************************************************************************
	 * ***************** Authentication auth =
	 * SecurityContextHolder.getContext().getAuthentication(); User user =
	 * userService.findUserByEmail(auth.getName());
	 * mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
	 * //***************************************************************************
	 * ***************** int user_id = user.getId(); ShippingAddress sAdd =
	 * shippingAddressRepository.findByUserId(user_id);
	 * 
	 * 
	 * Optional<Lot> obj = lotRepository.findById(lotId); Lot lot = obj.get();
	 * 
	 * sAdd.setContact_person(contact_person); sAdd.setStreet(street);
	 * sAdd.setCity(city); sAdd.setZip_code(zip_code); sAdd.setCountry(country);
	 * sAdd.setPhone_number(phone_number);
	 * 
	 * shippingAddressRepository.save(sAdd);
	 * 
	 * mv.addObject("sAd", sAdd); mv.addObject("lt", lot); mv.addObject("msg",
	 * "Info have been updated"); //mv.addObject("lotId", lot.getLotId());
	 * 
	 * //mv.setViewName("/my_account/user/loginDetails");
	 * 
	 * return new ModelAndView("redirect:/admin/account/show/shipping/address/" +
	 * lotId); }
	 * 
	 * @RequestMapping(value="/account/shipping/address/test/{lotId}",
	 * method=RequestMethod.POST) public ModelAndView
	 * saveSHippingAddressLot(@PathVariable long lotId, @Valid ShippingAddress
	 * customerShippingAddressInfo, BindingResult bindingResult) { ModelAndView mv =
	 * new ModelAndView();
	 * 
	 * //***************************************************************************
	 * ***************** Authentication auth =
	 * SecurityContextHolder.getContext().getAuthentication(); User user =
	 * userService.findUserByEmail(auth.getName());
	 * mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
	 * //***************************************************************************
	 * *****************
	 * 
	 * if (bindingResult.hasErrors()) {
	 * mv.setViewName("/my_account/admin/billing_address");
	 * 
	 * }
	 * 
	 * Optional<Lot> obj = lotRepository.findById(lotId); Lot lot = obj.get();
	 * 
	 * //int user_id=user.getId();
	 * 
	 * customerShippingAddressInfo.setUser(user);
	 * customerShippingAddressInfoService.saveShipInfo(customerShippingAddressInfo);
	 * 
	 * mv.addObject("message", "Address have been successfully saved");
	 * mv.addObject("customerShippingAddressInfo", new ShippingAddress());
	 * mv.addObject("lt", lot); return new
	 * ModelAndView("redirect:/admin/account/show/shipping/address/" + lotId); }
	 * 
	 * 
	 * // displaying billing address if exist
	 * 
	 * @SuppressWarnings("unused")
	 * 
	 * @RequestMapping(value="/account/show/shipping/address/{lotId}") public
	 * ModelAndView showShipAdd(@Valid ShippingAddress shippingAddress,
	 * BindingResult bindingResult, @PathVariable("lotId") long lotId) {
	 * 
	 * 
	 * ModelAndView mv = new ModelAndView();
	 * //***************************************************************************
	 * ***************** Authentication auth =
	 * SecurityContextHolder.getContext().getAuthentication(); User user =
	 * userService.findUserByEmail(auth.getName());
	 * mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
	 * //***************************************************************************
	 * *****************
	 * 
	 * Optional<Lot> newLot = lotRepository.findById(lotId); Lot lots =
	 * newLot.get();
	 * 
	 * int user_id=user.getUser_id();
	 * 
	 * shippingAddress = (@Valid ShippingAddress)
	 * shippingAddressRepository.findByUserId(user_id);
	 * 
	 * if (shippingAddress != null) { mv.addObject("shippAddress", shippingAddress);
	 * } else { mv.addObject("msg", "Please add a shipping address"); }
	 * 
	 * mv.addObject("lots", lots); mv.setViewName("/my_account/admin/addressShip");
	 * 
	 * return mv;
	 * 
	 * }
	 * 
	 */
=======
		
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
			
			return new ModelAndView("redirect:/admin/account/show/shipping/address/" + lotId);
		}
		
		@RequestMapping(value="/account/shipping/address/test/{lotId}", method=RequestMethod.POST)
		public ModelAndView saveSHippingAddressLot(@PathVariable long lotId, @Valid ShippingAddress customerShippingAddressInfo, BindingResult bindingResult) {
			ModelAndView mv = new ModelAndView();
			
			//********************************************************************************************
					Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			        User user = userService.findUserByEmail(auth.getName());
			        mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
			        //********************************************************************************************
			        
			if (bindingResult.hasErrors()) {
				mv.setViewName("/my_account/admin/billing_address");
				
			}
			
			  Optional<Lot> obj = lotRepository.findById(lotId);
			  Lot lot = obj.get();
			    
			//int user_id=user.getId();
			  
			customerShippingAddressInfo.setUser(user);
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
			mv.setViewName("/my_account/admin/addressShip");
			
			return mv;
		}
		
		
		@RequestMapping(value = "/lot/details/{lotId}",method=RequestMethod.GET)
		public ModelAndView showLotdetails(@PathVariable("lotId") long lotId)
		{
			ModelAndView mv = new ModelAndView();
			
			//********************************************************************************************
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        User user = userService.findUserByEmail(auth.getName());
	        mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
	        //********************************************************************************************
	        
			Optional<Lot> new_obj = lotRepository.findById(lotId);
			Lot lots= new_obj.get();
			
			if(lots == null) {
				throw new ProductNotFoundException("testing"); 
			}
	
			//List<Product> product = productRepository.findAllByLotId(lotId);
			List<Lager_Product> product = lager_ProductRepository.findAllByLotId(lotId);
			
			//------------------------------------------------------------------------------counting volume
			int vol= 0;
			int i=0;
			product.get(i).getQuantity();
			
			for(i = 0; i< product.size(); i++) {
				
				int st =product.get(i).getQuantity();
				
				vol =vol + (st); 	
			}
			//----------------------------------------------------------------------------------------
			
			//------------------------------------------------------------------------------counting volume
			double price= 0;
			int in=0;
			product.get(in).getPrice();
			
			for(in = 0; in< product.size(); in++) {
				
				double st =product.get(in).getPrice();
				
				price = price + (st); 	
			}
			//----------------------------------------------------------------------------------------
			
			//------------------------------------------------------------------------------counting volume
			/*double retailPrice= 0;
			int inn=0;
			product.get(in).getPrice();
			
			for(inn = 0; inn< product.size(); inn++) {
				
				double stt =product.get(inn).getRetialPrice();
				
				retailPrice = retailPrice + (stt); 	
			}*/
			//----------------------------------------------------------------------------------------
			//double pr = price * vol;
			lots.setLotPrice(price);
			//lots.setActualPrice(retailPrice);
			lots.setVolume(vol); // setiing lot volume
			
			//------------------------------------------------------------------------------------
			Set<Lager_Product> prod = lots.getProductList();
			
			
			List<String> imageList = new ArrayList<String>();
			
			for(Lager_Product set : prod) {
				if(!(set.getArticleImage1()).isEmpty()) {
					imageList.add(set.getArticleImage1());
				}
				
				if(!(set.getArticleImage2()).isEmpty()) {
					imageList.add(set.getArticleImage2());
				}
				
				if(!(set.getArticleImage3()).isEmpty()) {
					imageList.add(set.getArticleImage3());
				}
				
			}
			
			
			
			lotRepository.save(lots); // updating the lot in the database
			
			mv.addObject("lots",lots);
			mv.addObject("products", product);
			mv.addObject("images", imageList);
			
	
			mv.setViewName("/my_account/admin/lotDetails");
			return mv;
>>>>>>> 24fd5d7109fa729315c24432dfff3db1654da8a4

	/*
	 * @RequestMapping(value =
	 * "/order_data/details/{lotId}",method=RequestMethod.GET) public ModelAndView
	 * showOrderdetails(@PathVariable("lotId") long lotId) { ModelAndView mv = new
	 * ModelAndView();
	 * 
	 * //***************************************************************************
	 * ***************** Authentication auth =
	 * SecurityContextHolder.getContext().getAuthentication(); User user =
	 * userService.findUserByEmail(auth.getName());
	 * mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
	 * //***************************************************************************
	 * ***************** int user_id = user.getId();
	 * 
	 * BillingAddress billAddress= billingAddressRepository.findByUserId(user_id);
	 * 
	 * ShippingAddress shippAddress =
	 * shippingAddressRepository.findByUserId(user_id);
	 * 
	 * Optional<Lot> new_obj = lotRepository.findById(lotId); Lot lots=
	 * new_obj.get();
	 * 
	 * List<Lot_Lager> product = lot_LagerRepository.findAllByLotId(lotId);
	 * 
	 * 
	 * 
	 * 
	 * mv.addObject("lots",lots); mv.addObject("products", product);
	 * mv.addObject("billAddress", billAddress); mv.addObject("shippAddress",
	 * shippAddress);
	 * 
	 * mv.addObject("message1", "Please add a Shipping address");
	 * mv.addObject("message2", "Please add a billing address");
	 * 
	 * mv.setViewName("admin-orderDetails"); return mv;
	 * 
	 * }
	 * 
	 * @RequestMapping(value =
	 * "/order_data/confirmation/{lotId}",method=RequestMethod.GET) public
	 * ModelAndView shwOrderdetails(@PathVariable("lotId") long lotId) throws
	 * JAXBException { ModelAndView mv = new ModelAndView();
	 * 
	 * //***************************************************************************
	 * ***************** Authentication auth =
	 * SecurityContextHolder.getContext().getAuthentication(); User user =
	 * userService.findUserByEmail(auth.getName());
	 * mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
	 * //***************************************************************************
	 * *****************
	 * 
	 * int user_id = user.getId(); ShippingAddress shippAddress =
	 * shippingAddressRepository.findByUserId(user_id);
	 * 
	 * Optional<Lot> new_obj = lotRepository.findById(lotId); Lot lots=
	 * new_obj.get();
	 * 
	 * List<Lot_Lager> product = lot_LagerRepository.findAllByLotId(lotId);
	 * 
	 * //---------------------------------------------------------------------------
	 * ------------------------------------------- Map<String, Object> model = new
	 * HashMap<>(); model.put("name", user.getFirst_name() + " " +
	 * user.getLast_name()); model.put("message1",
	 * "Thank you for your purchage. You can see the current status of your order_data at any time under "
	 * + "http://stocklots.motion-fashion.de/admin/orders/list"+ "." +
	 * "\nIf you have any questions about your customer account or your order_data, \nplease send us an e-mail."
	 * );
	 * 
	 * 
	 * MailRequest mailRequest = new MailRequest();
	 * mailRequest.setName(user.getFirst_name());
	 * mailRequest.setFrom("noreply@domain.com");
	 * mailRequest.setTo(user.getEmail());
	 * mailRequest.setSubject("Order_data confirmation");
	 * 
	 * emailService.sendEmail_confirm_order(mailRequest, model);
	 * //---------------------------------------------------------------------------
	 * -------------------------------------------
	 * 
	 * mv.addObject("lots",lots); mv.addObject("products", product);
	 * mv.addObject("shippAddress", shippAddress);
	 * 
	 * mv.addObject("msg", "Thank you for your purchase!");
	 * 
	 * ------------------------------------------------------- Order_data order_data
	 * = orderRepository.findByLotId(lotId); mv.addObject("order_xml", order_data);
	 * 
	 * JAXBContext jaxbContext = JAXBContext.newInstance(Order_data.class);
	 * Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	 * 
	 * jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	 * 
	 * jaxbMarshaller.marshal(order_data, System.out);
	 * jaxbMarshaller.marshal(order_data, new File("c:/temp/order_data.xml"));
	 * -------------------------------------------------------
	 * 
	 * mv.setViewName("admin-order_data-confirmation"); return mv;
	 * 
	 * }
	 * 
	 * 
	 * //***************************************************************************
	 * ***Order_data list according to
	 * lotId*****************************************************************
	 * 
	 * @RequestMapping(value="/orders/list/{lotId}", method=RequestMethod.GET)
	 * public ModelAndView showAllOrders(@PathVariable("lotId") Long lotId) {
	 * ModelAndView mv = new ModelAndView();
	 * //**********************************************************************
	 * Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	 * User user = userService.findUserByEmail(auth.getName());
	 * mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
	 * //***********************************************************************
	 * 
	 * int user_id = user.getId();
	 * 
	 * SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 * Date date = new Date();
	 * 
	 * Optional<Lot> newLot = lotRepository.findById(lotId); Lot lot = newLot.get();
	 * 
	 * //Product prod = (Product) productRepository.findAllByLotId(lotId);
	 * 
	 * 
	 * //---------------------------------------------------------------------------
	 * ------------- ShippingAddress shipAdd =
	 * shippingAddressRepository.findByUserId(user_id); BillingAddress billAdd=
	 * billingAddressRepository.findByUserId(user_id);
	 * //---------------------------------------------------------------------------
	 * -----------
	 * 
	 * Order_data obj = orderRepository.findByUserAndLot(user_id, lotId);
	 * 
	 * 
	 * if (obj == null) {
	 * 
	 * 
	 * Order_data orderObj = new Order_data();
	 * 
	 * orderObj.setOrder_status(true);
	 * orderObj.setOrderDate(formatter.format(date)); orderObj.setLot(lot);
	 * orderObj.setUser(user); orderObj.setBillingAddress(billAdd);
	 * orderObj.setShippingAddress(shipAdd);
	 * 
	 * orderRepository.save(orderObj);
	 * 
	 * orderObj.getLot().setLot_status(0); //((Product)
	 * orderObj.getLot().getProductList()).setA_stock(null); //lot.setLot_status(0);
	 * lotRepository.save(lot);
	 * 
	 * } mv.setViewName("admin-orderDetails");
	 * 
	 * 
	 * return new ModelAndView("redirect:/admin/order_data/confirmation/" + lotId);
	 * }
	 */

	/**
	 * ......................................51
	 * 
	 * @return
	 */
	// ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	@RequestMapping(value = "/create/new/lot", method = RequestMethod.GET)
	public ModelAndView createLot() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("lot", new Lot());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		mv.setViewName("/my_account/admin/create_new_lot");
		return mv;
	}

	/**
	 * ------------------------------------------------52
	 * 
	 * @param lotName
	 * @param lotDescription
	 * @param teaserImage
	 * @param lot_status
	 * @param brand
	 * @param gender
	 * @param category
	 * @param seasons
	 * @return
	 */
	@RequestMapping(value = "/save/lot", method = RequestMethod.POST)
	public ModelAndView save_lot(@RequestParam("lotName") String lotName,
			@RequestParam("lotDescription") String lotDescription,
			@RequestParam("teaserImage") MultipartFile teaserImage, @RequestParam("lot_status") int lot_status,
			@RequestParam("brand") String brand, @RequestParam("gender") String gender,
			@RequestParam("category") String category, @RequestParam("seasons") String seasons)

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
		mv.addObject("lot", lot);
		lot.setLotName(lotName);
		lot.setLotDescription(lotDescription);
		lot.setLot_status(lot_status);
		lot.setBrand(brand);
		lot.setGender(gender);
		lot.setCategory(category);
		lot.setSeasons(seasons);
		lot.setCreatedAt(formatter.format(date));
		// lot.setTeaserImage(teaserImage);
		mv.setViewName("/my_account/admin/create_new_lot");
		lotRepository.save(lot);
		// imagesRepository.saveAll(images);
		// return "data saved";
		return new ModelAndView("redirect:/admin/lot/product/" + lot.getLotId());
	}

	/**
	 * -------------------------------------------53
	 * 
	 * @param lotId
	 * @return
	 */
	@RequestMapping(value = "/lot/product/{lotId}", method = RequestMethod.GET)
	public ModelAndView addProduct(@PathVariable long lotId) {
		ModelAndView mv = new ModelAndView();
		// ****************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// *****************************************************************************************************
		List<Stocklots_Offer> products = stocklots_Offer_Repository.findAll();
		Optional<Lot> newLot = lotRepository.findById(lotId);
		Lot lot = newLot.get();
		mv.addObject("products", products);
		mv.addObject("lot", lot);
		mv.setViewName("/my_account/admin/products");
		return mv;

	}

	/**
	 * ------------------------------------------54
	 * 
	 * @param idrates
	 * @param lot_id
	 * @return
	 */
	@RequestMapping(value = "/add/lot/product", method = RequestMethod.POST)
	public ModelAndView addProductInLot(@RequestParam("idChecked") List<String> idrates,
			@RequestParam(value = "lot_id") Long lot_id) {
		new ModelAndView();
		Optional<Lot> newLot = lotRepository.findById(lot_id);
		Lot lot = newLot.get();
		new Stocklots_Offer();
		ArrayList<Long> al = new ArrayList<Long>();
		if (idrates != null) {
			List<Stocklots_Offer> productList = new ArrayList<>();
			productList = lot.getProductList();
			for (String idrateStr : idrates) {
				Long idrate = Long.parseLong(idrateStr);
				al.add(idrate);
				Stocklots_Offer newProduct = stocklots_Offer_Repository.find_BY_EAN(idrate);
				if (newProduct.equals(null)) {
					return null;
				} else {
					productList.add(newProduct);
				}
			}
			lot.setProductList(productList);
			lotRepository.save(lot);
		}
<<<<<<< HEAD
		// transferring records from stocklots_offer to lot_lager
		Lot_Lager lager = new Lot_Lager();
		List<Stocklots_Offer> offer = lot.getProductList();
		for (int i = 0; i < offer.size(); i++) {
			lager.setEAN(offer.get(i).getEAN());
			lager.setART_NR(offer.get(i).getART_NR());
			lager.setFARBE(offer.get(i).getFARBE());
			lager.setGROESSE(offer.get(i).getGROESSE());
			lager.setPROD_NAME(offer.get(i).getPROD_NAME());
			lager.setBESTAND(offer.get(i).getBESTAND());
			lager.setPREIS(offer.get(i).getPREIS());
			lager.setUVP(offer.get(i).getUVP());
			lager.setBRAND(offer.get(i).getBRAND());
			lager.setGENDER(offer.get(i).getGENDER());
			lager.setPROD_MATERIAL(offer.get(i).getPROD_MATERIAL());
			lager.setPROD_TEXT(offer.get(i).getPROD_TEXT());
			lager.setIMAGE_1(offer.get(i).getIMAGE_1());
			lager.setIMAGE_2(offer.get(i).getIMAGE_2());
			lager.setIMAGE_3(offer.get(i).getIMAGE_3());
			lager.setANGEBOT_NR(offer.get(i).getANGEBOT_NR());
			lager.setLot(lot);
			lot_LagerRepository.save(lager);
		}
		stocklots_Offer_Repository.deleteAll();
		// ------------------------------------------------------------------
=======
		
		
		
		
		
>>>>>>> 24fd5d7109fa729315c24432dfff3db1654da8a4
		return new ModelAndView("redirect:/admin/lot/details/" + lot_id);
	}

	/**
	 * ------------------------------------------------55
	 * 
	 * @param lotId
	 * @return
	 */
	@RequestMapping(value = "/lot/details/{lotId}", method = RequestMethod.GET)
	public ModelAndView showLotdetails(@PathVariable("lotId") long lotId) {
		ModelAndView mv = new ModelAndView();
		// ********************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// ********************************************************************************************
		Optional<Lot> new_obj = lotRepository.findById(lotId);
		Lot lots = new_obj.get();
		List<Lot_Lager> product = lot_LagerRepository.findAllByLotId(lotId);
		// ------------------------------------------------------------------------------counting
		// volume
		int volume = 0;
		int i = 0;
		for (i = 0; i < product.size(); i++) {
			int vol = product.get(i).getBESTAND();
			volume = volume + (vol);
		}
		// ----------------------------------------------------------------------------------------

		// ------------------------------------------------------------------------------counting
		// volume
		double price = 0;
		int in = 0;
		product.get(in).getPREIS();
		product.get(in).getBESTAND();
		for (in = 0; in < product.size(); in++) {
			double pr = product.get(in).getPREIS();
			int qn = product.get(in).getBESTAND();
			price = price + (pr * qn);
		}

		double retailPrice = 0;
		int inm = 0;
		product.get(inm).getUVP();
		product.get(inm).getBESTAND();
		for (inm = 0; inm < product.size(); inm++) {
			double st = product.get(inm).getUVP();
			int qn = product.get(inm).getBESTAND();
			retailPrice = retailPrice + (st * qn);
		}

		double totalDiscount = retailPrice - price;
		double avgBuyingPrice = price / volume;
		double avgRetailPrice = retailPrice / volume;
		double percentageOfDiscount = (totalDiscount / retailPrice) * 100;
		/*
		 * numberOfSKU------------------- avgQuantityPerSKU----------
		 */

		// ----------------------------------------------------------------------------------------
		lots.setLotPrice(price);
		lots.setVolume(volume); // setiing lot volume
		lots.setActualPrice(retailPrice);
		lots.setTotalDiscount(totalDiscount);
		lots.setAverageBuyingPrice(avgBuyingPrice);
		lots.setAverageRetailPrice(avgRetailPrice);
		lots.setPercentageOfDiscountOfRetailPrice(percentageOfDiscount);
		// ----------------------------------------------------------------------------------------
		lots.setLotPrice(price);
		lots.setVolume(volume); // setiing lot volume
		// ------------------------------------------------------------------------------------
		List<Lot_Lager> prod = lot_LagerRepository.findAllByLotId(lotId);
		List<String> imageList = new ArrayList<String>();
		for (Lot_Lager set : prod) {
			if (!(set.getIMAGE_1()).isEmpty()) {
				imageList.add(set.getIMAGE_1());
			}

			if (!(set.getIMAGE_2()).isEmpty()) {
				imageList.add(set.getIMAGE_2());
			}

			if (!(set.getIMAGE_3()).isEmpty()) {
				imageList.add(set.getIMAGE_3());
			}
		}

		// ***********************************************************************************************************************Test
		List<String> articleNumbers = new ArrayList<>();
		for (Lot_Lager lotLager : product) {
			if (!articleNumbers.contains(lotLager.getART_NR()))
				articleNumbers.add(lotLager.getART_NR());
		}
		List<Lot_Lager> lager = new ArrayList<Lot_Lager>();
		List<CustomizedProduct> customisedProduct = new ArrayList<CustomizedProduct>();
		for (String art_nr : articleNumbers) {
			lager = lot_LagerRepository.findAllArtNumber(art_nr);
			CustomizedProduct obj = new CustomizedProduct();
			obj.setArticle_number(art_nr);
			obj.setProduct(lager);
			obj.setUVP(lager.get(0).getUVP());
			obj.setPRICE(lager.get(0).getPREIS());
			obj.setIMAGE(lager.get(0).getIMAGE_1());
			obj.setPROD_MATERIAL(lager.get(0).getPROD_MATERIAL());
			obj.setPROD_TEXT(lager.get(0).getPROD_TEXT());
			obj.setBRAND(lager.get(0).getBRAND());
			obj.setGENDER(lager.get(0).getGENDER());
			customisedProduct.add(obj);
		}

		// ***********************************************************************************************************************
		lotRepository.save(lots); // updating the lot in the database
		mv.addObject("lots", lots);
		mv.addObject("products", product);
		mv.addObject("images", imageList);
		mv.addObject("lg", customisedProduct);
		mv.setViewName("/my_account/admin/lotDetails");
		return mv;
	}

	/**
	 * -----------------------------------------56
	 * 
	 * @return
	 */
	@RequestMapping(value = "/lot/list", method = RequestMethod.GET)
	public ModelAndView lotList() {
		ModelAndView mv = new ModelAndView();
		// --------------------------------------------------------------------------------
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// --------------------------------------------------------------------------------
		List<Lot> lots = (List<Lot>) lotRepository.findAll();
		mv.addObject("lots", lots);
		mv.setViewName("/my_account/admin/lot_list");
		return mv;
	}

	/**
	 * ----------------------------------------------57
	 * 
	 * @param lotId
	 * @return
	 */
	@GetMapping(value = "/edit/lot/{lotId}")
	public ModelAndView editLot(@PathVariable long lotId) {
		ModelAndView mv = new ModelAndView();
		// --------------------------------------------------------------------------------
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// --------------------------------------------------------------------------------
		Optional<Lot> obj = lotRepository.findById(lotId);
		Lot gettingLot = obj.get();
		mv.addObject("lot", gettingLot);
		mv.setViewName("/my_account/admin/edit-lot");
		return mv;
	}

	/**
	 * --------------------------------------------------58
	 * 
	 * @param lotId
	 * @param lotName
	 * @param lotDescription
	 * @param lot_status
	 * @return
	 */
	@RequestMapping(value = "/save/edited/lot/{lotId}", method = RequestMethod.POST)
	public ModelAndView saveEditedLot(@PathVariable long lotId, @RequestParam String lotName,
			@RequestParam String lotDescription, @RequestParam int lot_status) {
		ModelAndView mv = new ModelAndView();
		Optional<Lot> obj = lotRepository.findById(lotId);
		Lot lots = obj.get();
		// List<Lot_Lager> lager = lot_LagerRepository.findAllByLotId(lotId);
		lots.setLotName(lotName);
		lots.setLotDescription(lotDescription);
		lots.setLot_status(lot_status);
		// lots.setProductList(lager);
		lotRepository.save(lots);
		mv.addObject("lots", lots);
		mv.setViewName("/my_account/admin/edit-lot");
		return new ModelAndView("redirect:/admin/lot/list");
	}

	/**
	 * ----------------------------------------------59
	 * 
	 * @param lotId
	 * @return
	 */
	// delete a lot
	// first need to find the lot to delete by id
	// we need to set the productList null in the lot
	// then delete the lot from the lotRepository
	@RequestMapping(value = "/delete/lot/{lotId}")
	public ModelAndView deleteLot(@PathVariable("lotId") long lotId) {
		ModelAndView mv = new ModelAndView();
		Optional<Lot> newLot = lotRepository.findById(lotId);
		Lot lots = newLot.get();
		// obj.setLot(null);
		lots.setProductList(null);
		lotRepository.deleteById(lotId);
		mv.addObject("lot", lots);
		return new ModelAndView("redirect:/admin/lot/list");
	}

	/**
	 * ..............................................60
	 * 
	 * @return
	 */
	// Product
	@RequestMapping(value = "/upload/product", method = RequestMethod.GET)
	public ModelAndView uploadProduct() {
		ModelAndView mv = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		mv.setViewName("/my_account/admin/product_upload");
		return mv;
	}

	/**
	 * ------------------------------------------------------61
	 * 
	 * @param multi_file
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/product/process", method = RequestMethod.POST, headers = "content-type=multipart/form-data")
	public ModelAndView processUploadProduct(MultipartFile multi_file) throws IOException {
		ModelAndView mv = new ModelAndView();
		// ----------------------------------------------------------------------------------------------------------
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// ------------------------------------------------------------------------------------------------------------
		try {
			fileLocation = productService.upload_file(multi_file);
			System.out.println("start set_index");
			productService.set_index();
			System.out.println("stop set_index");
			System.out.println("start save_product");
			productService.save_product();
			System.out.println("stop save_product");
<<<<<<< HEAD
			mv.addObject("message", "*****************************Data have been uploaded****************************");
			mv.setViewName("/my_account/admin/product_upload");
		} catch (IOException e) {
			mv.addObject("failureMessage",
					"*****************************Data have not been uploaded****************************");
			mv.setViewName("/my_account/admin/product_upload");
=======
			
			mv.addObject("successMessage","*****************************Data have been uploaded****************************");
			mv.setViewName("/my_account/admin/product_upload");
		} catch (IOException e) {
			
			mv.addObject("message","*****************************Product data failed to upload****************************");
			mv.setViewName("exceptions/product-not-found");
			
>>>>>>> 24fd5d7109fa729315c24432dfff3db1654da8a4
		}
		return mv;
	}

	/**
	 * .............................................................62
	 * 
	 * @return
	 */
	@RequestMapping(value = "/upload/lager", method = RequestMethod.GET)
	public ModelAndView uploadLager() {
		ModelAndView mv = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		mv.setViewName("/my_account/admin/lager-upload");
		return mv;
	}

	/**
	 * --------------------------------------------------------------63
	 * 
	 * @param multi_file
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/lager/process", method = RequestMethod.POST, headers = "content-type=multipart/form-data")
	public ModelAndView processUploadLager(MultipartFile multi_file) throws IOException {
		ModelAndView mv = new ModelAndView();
		// ----------------------------------------------------------------------------------------------------------
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// ------------------------------------------------------------------------------------------------------------
		// Lager_Product lProduct = new Lager_Product();
		try {
			// File file = lot_LagerService.upload_file(multi_file);
			System.out.println("start set_index");
			lot_LagerService.set_index();
			System.out.println("stop set_index");
			System.out.println("start save_product");
			lot_LagerService.save_lager(multi_file);
			// lager_ProductRepository.saveLager(lProduct);
			System.out.println("stop save_product");
<<<<<<< HEAD
			mv.addObject("message", "*****************************Data have been uploaded****************************");
			mv.setViewName("/my_account/admin/lager-upload");
			// System.out.println(file.getAbsolutePath());
		} catch (Exception e) {
			mv.addObject("alertMessage", "Data have not been saved");
			mv.setViewName("/my_account/admin/lager-upload");
		}
		return mv;
=======
			
			mv.addObject("successMessage","*****************************Data have been uploaded****************************");
			mv.setViewName("/my_account/admin/lager-upload");
			
		} catch (IOException e) {
			
			mv.addObject("message","Data have not been saved");
			mv.setViewName("exceptions/product-not-found");
			
		}	
        
        return mv;
>>>>>>> 24fd5d7109fa729315c24432dfff3db1654da8a4
	}

	/**
	 * .........................................................64
	 * 
	 * @return
	 */
	// Offer
	@RequestMapping(value = "/upload/offer", method = RequestMethod.GET)
	public ModelAndView uploadOffer() {
		ModelAndView mv = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		mv.setViewName("/my_account/admin/offer-upload");
		return mv;
	}

	/**
	 * -----------------------------------------------------65
	 * 
	 * @param multi_file
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/offer/process", method = RequestMethod.POST, headers = "content-type=multipart/form-data")
	public ModelAndView processUploadOffer(MultipartFile multi_file) throws IOException {
		ModelAndView mv = new ModelAndView();
		// ----------------------------------------------------------------------------------------------------------
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// ------------------------------------------------------------------------------------------------------------
		// Lager_Product lProduct = new Lager_Product();
		try {
			File file = stocklots_Offer_Service.upload_file(multi_file);
			System.out.println("start set_index");
			stocklots_Offer_Service.set_index(file);
			System.out.println("stop set_index");
			System.out.println("start save_product");
			stocklots_Offer_Service.save_offer(file);
			System.out.println("stop save_product");
			mv.addObject("message", "*****************************Data have been uploaded****************************");
			mv.setViewName("/my_account/admin/offer-upload");
			System.out.println("uploaded file :" + file.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("alertMessage", e.toString());
			mv.setViewName("/my_account/admin/offer-upload");
		}
		return mv;
	}

	/**
	 * ------------------------------------------------------66
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/offer/lot/list", method = RequestMethod.GET)
	public ModelAndView offerList(@RequestParam(defaultValue = "0") int page) {
		ModelAndView mv = new ModelAndView();
		// ------------------------------------------------------------------------
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// ------------------------------------------------------------------------
		List<Stocklots_Offer> offer = stocklots_Offer_Repository.findAll();
		mv.addObject("lotOffer", offer);
		mv.setViewName("/my_account/admin/stocklots-offer-list");
		return mv;
	}

	/**
	 * ---------------------------------------------------67
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/lager/lot/list", method = RequestMethod.GET)
	public ModelAndView lagerList(@RequestParam(defaultValue = "0") int page) {
		ModelAndView mv = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		List<Lot_Lager> lager = lot_LagerRepository.findAll();
		mv.addObject("lotLager", lager);
		mv.setViewName("/my_account/admin/lager-list");
		return mv;
	}

	/**
	 * -----------------------------------------------------------68
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/product/list", method = RequestMethod.GET)
	public ModelAndView productList(@RequestParam(defaultValue = "0") int page) {
		ModelAndView mv = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());

		List<Product> product = productRepository.findAll();
		mv.addObject("products", product);
		mv.setViewName("/my_account/admin/product_list");
		return mv;
	}

	public File convert(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		convFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;

	}

	/**
	 * ---------------------------------------------------69
	 * 
	 * @return
	 */
	@RequestMapping(value = "/account/all/list", method = RequestMethod.GET)
	public ModelAndView showAllList() {
		ModelAndView mv = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// --------------------------------------------------------------------------
		int user_id = user.getUser_id();
		ShippingAddress shipAdd = shippingAddressRepository.findByUserId(user_id);
		List<User> users = userRepo.findAll();
		mv.addObject("users", users);
		mv.addObject("shipA", shipAdd);
		mv.setViewName("/my_account/admin/showAll");
		return mv;
	}

	/**
	 * -----------------------------------------------------------70
	 * 
	 * @return
	 */
	@RequestMapping(value = "/account/administrators", method = RequestMethod.GET)
	public ModelAndView showAdmin() {
		ModelAndView mv = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());

		List<User> users = userRepo.findAllAdmin();
		mv.addObject("users", users);
		mv.setViewName("/my_account/admin/administrators");
		return mv;
	}

	/**
	 * ------------------------------------------------------------71
	 * 
	 * @return
	 */
	@RequestMapping(value = "/account/user/list", method = RequestMethod.GET)
	public ModelAndView showUserList() {
		ModelAndView mv = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());

		List<User> users = userRepo.findAllUser();
		mv.addObject("users", users);
		mv.setViewName("/my_account/admin/user-list");
		return mv;
	}

	/**
	 * ------------------------------------------------------72
	 * 
	 * @return
	 */
	@RequestMapping(value = "/account/inactive/users", method = RequestMethod.GET)
	public ModelAndView showInactiveUsers() {
		ModelAndView mv = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// ---------------------------------------------------------------------------
		int user_id = user.getUser_id();
		ShippingAddress shipAdd = shippingAddressRepository.findByUserId(user_id);
		List<User> users = userRepo.findInactiveUser();
		mv.addObject("users", users);
		mv.addObject("shipA", shipAdd);
		mv.setViewName("/my_account/admin/inactive-user-list");
		return mv;
	}

	/**
	 * ---------------------------------------------------------73
	 * 
	 * @return
	 */
	@RequestMapping(value = "/account/shipping/address/list", method = RequestMethod.GET)
	public ModelAndView listShipAddress() {
		ModelAndView mv = new ModelAndView();
		// ********************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// ********************************************************************************************
		List<ShippingAddress> shipAdd = shippingAddressRepository.findAll();
		mv.addObject("shippingAdd", shipAdd);
		mv.setViewName("/my_account/admin/shipping-add-list");
		return mv;

	}

	/**
	 * -----------------------------------------------------74
	 * 
	 * @return
	 */
	@RequestMapping(value = "/account/billing/address/list", method = RequestMethod.GET)
	public ModelAndView listBillAddress() {
		ModelAndView mv = new ModelAndView();
		// ********************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// ********************************************************************************************
		List<BillingAddress> billAdd = billingAddressRepository.findAll();
		mv.addObject("billingAdd", billAdd);
		mv.setViewName("/my_account/admin/billing-add-list");
		return mv;
	}

	/**
	 * ---------------------------------------------75
	 * 
	 * @param user_id
	 * @return
	 */
	// get method for editing user details from admin view
	@RequestMapping(value = "/activate/user/{user_id}", method = RequestMethod.GET)
	public ModelAndView editInactiveUser(@PathVariable(value = "user_id") int user_id) {
		ModelAndView mv = new ModelAndView();
		Optional<User> obj = userRepo.findById(user_id);
		User users = obj.get();
		ShippingAddress shipAdd = shippingAddressRepository.findByUserId(user_id);
		mv.addObject("user", users);
		mv.addObject("sAdd", shipAdd);
		mv.setViewName("/my_account/admin/activate-user");
		return mv;
	} // end of method edit

	/**
	 * --------------------------------------------76
	 * 
	 * @param user_id
	 * @param first_name
	 * @param last_name
	 * @param company
	 * @param email
	 * @param enabled
	 * @return
	 */
	// update user details from admin view
	@RequestMapping(value = "/activate/user/{user_id}", method = RequestMethod.POST)
	public ModelAndView editPostInactiveUser(@PathVariable(value = "user_id") int user_id,
			@RequestParam String first_name, @RequestParam String last_name, @RequestParam String company,
			@RequestParam String email, @RequestParam int enabled) {
		ModelAndView mv = new ModelAndView();
		Optional<User> obj = userRepo.findById(user_id);
		User users = obj.get();
		users.setFirst_name(first_name);
		users.setLast_name(last_name);
		users.setCompany(company);
		users.setEmail(email);
		users.setEnabled(enabled);
		userRepo.save(users);
		mv.addObject("usr", users);
		mv.setViewName("/my_account/admin/edit-user-details");
		// ----------------------------------------------------------------------------------------------------------------------
		Map<String, Object> model = new HashMap<>();
		model.put("name2", users.getFirst_name() + users.getLast_name());
		model.put("message2", "Congratulations!! After verifying your account details, We have activated your account. "
				+ "Now you can login and see our special offers. To login, please click on the given link:  "
				+ "http://stocklots.motion-fashion.de" + " ."
				+ "\nIf you have any questions about your customer account or your order_data, \nplease send us an e-mail.");

		MailRequest mailRequest = new MailRequest();
		mailRequest.setName(users.getFirst_name());
		mailRequest.setFrom("noreply@domain.com");
		mailRequest.setTo(users.getEmail());
		mailRequest.setSubject("User account activation confirmation");
		emailService.sendEmail_confirm_account(mailRequest, model);
		// ----------------------------------------------------------------------------------------------------------------------
		return new ModelAndView("redirect:/admin/account/all/list");
	} // end of method edit

	/**
	 * ----------------------------------------------------------77
	 * 
	 * @param user_id
	 * @return
	 */
	// get method for editing user details from admin view
	@RequestMapping(value = "/edit/user/{user_id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(value = "user_id") int user_id) {
		ModelAndView mv = new ModelAndView();
		Optional<User> obj = userRepo.findById(user_id);
		User users = obj.get();
		mv.addObject("user", users);
		mv.setViewName("/my_account/admin/edit-user-details");
		return mv;
	} // end of method edit

	/**
	 * .......................................................78
	 * 
	 * @param user_id
	 * @param first_name
	 * @param last_name
	 * @param company
	 * @param email
	 * @param enabled
	 * @return
	 */
	// update user details from admin view
<<<<<<< HEAD
	@RequestMapping(value = "/update/user/{user_id}", method = RequestMethod.POST)
	public ModelAndView editPost(@PathVariable(value = "user_id") int user_id, @RequestParam String first_name,
			@RequestParam String last_name, @RequestParam String company, @RequestParam String email,
			@RequestParam int enabled) {
		ModelAndView mv = new ModelAndView();
		Optional<User> obj = userRepo.findById(user_id);
		User users = obj.get();
		users.setFirst_name(first_name);
		users.setLast_name(last_name);
		users.setCompany(company);
		users.setEmail(email);
		users.setEnabled(enabled);
		userRepo.save(users);
		// ----------------------------------------------------------------------------------------------------------------------
		Map<String, Object> model = new HashMap<>();
		model.put("name", users.getFirst_name() + users.getLast_name());
		model.put("message1",
				"Thank you for your purchase. You can see the current status of your order_data at any time under "
						+ "http://stocklots.motion-fashion.de/order_data/list" + "."
						+ "\nIf you have any questions about your customer account or your order_data, \nplease send us an e-mail.");
		MailRequest mailRequest = new MailRequest();
		mailRequest.setName(users.getFirst_name());
		mailRequest.setFrom("noreply@domain.com");
		mailRequest.setTo(users.getEmail());
		mailRequest.setSubject("User account activation confirmation");
		emailService.sendEmail_confirm_order(mailRequest, model);
		// ----------------------------------------------------------------------------------------------------------------------
		mv.addObject("usr", users);
		mv.setViewName("/my_account/admin/edit-user-details");
		return new ModelAndView("redirect:/admin/account/all/list");
	} // end of method edit
		// *****************************************************************************************************************

	/**
	 * --------------------------------------------------------------------79
	 * 
	 * @param user_id
	 * @param attribute_select
	 * @return
	 */
	@RequestMapping(value = "/update_user_role", method = RequestMethod.POST)
	public ModelAndView userRole(@RequestParam int user_id, @RequestParam String attribute_select) {
		new ModelAndView();
		String user_role = "USER";
		String admin_role = "ADMIN";
		if (attribute_select.matches(admin_role)) {
			userRepo.update_user_role(1, user_id);
		} else if (attribute_select.matches(user_role)) {
			userRepo.update_user_role(2, user_id);
=======
		@RequestMapping(value = "/update/user/{user_id}", method= RequestMethod.POST)
		public ModelAndView editPost(@PathVariable (value="user_id") int user_id,
										@RequestParam String first_name,
										@RequestParam String last_name,
										@RequestParam String company,
										@RequestParam String email,
										@RequestParam boolean enabled) {
	
			ModelAndView mv = new ModelAndView();
			
			//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			/*Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        User user = userService.findUserByEmail(auth.getName());
	        mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());*/
			//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	        
	        Optional<User> obj = userRepo.findById(user_id);
	        User users = obj.get(); 
	        
	        users.setFirst_name(first_name);
	        users.setLast_name(last_name);
	        users.setCompany(company);
	        users.setEmail(email);
	        users.setEnabled(enabled);
	        
	        userRepo.save(users);
	        
	        mv.addObject("usr", users);
			mv.setViewName("/my_account/admin/edit-user-details");
			
			return new ModelAndView("redirect:/admin/account/all/list");
		} // end of method edit
		
		
		//*****************************************************************************************************************
		
		@RequestMapping(value="/update_user_role", method=RequestMethod.POST)
		public ModelAndView userRole(@RequestParam int user_id, @RequestParam String attribute_select) {
			new ModelAndView();
			
			String user_role = "USER";
			String admin_role = "ADMIN";
			
			if (attribute_select.matches(admin_role)) {
				userRepo.update_user_role(1, user_id);
			} else if (attribute_select.matches(user_role)) {
				userRepo.update_user_role(2, user_id);
			}
			
			return new ModelAndView("redirect:/admin/account/all/list");
>>>>>>> 24fd5d7109fa729315c24432dfff3db1654da8a4
		}
		return new ModelAndView("redirect:/admin/account/all/list");
	}

	/**
	 * ------------------------------------------------------------------80
	 * 
	 * @param user_id
	 * @return
	 */
	@RequestMapping(value = "/edit/adminn/{user_id}", method = RequestMethod.GET)
	public ModelAndView editAdmin(@PathVariable int user_id) {
		ModelAndView mv = new ModelAndView();
		// ----------------------------------------------------------------------------------
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// --------------------------------------------------------------------------------
		Optional<User> obj = userRepo.findById(user_id);
		User users = obj.get();
		mv.addObject("user", users);
		mv.setViewName("/my_account/admin/edit-admin-details");
		return mv;
	}

	/**
	 * ..........................................................81
	 */
	// update admin details from admin view//******************************OKAY
	@RequestMapping(value = "/update/adminn/{user_id}", method = RequestMethod.POST)
	public ModelAndView editAdmin(@PathVariable(value = "user_id") int user_id, @RequestParam String first_name,
			@RequestParam String last_name, @RequestParam String company, @RequestParam int enabled) {
		ModelAndView mv = new ModelAndView();
		Optional<User> obj = userRepo.findById(user_id);
		User users = obj.get();
		users.setFirst_name(first_name);
		users.setLast_name(last_name);
		users.setCompany(company);
		users.setEnabled(enabled);
		userRepo.save(users);
		mv.addObject("usr", users);
		mv.setViewName("/my_account/admin/edit-admin-details");
		return new ModelAndView("redirect:/admin/account/all/list");
	} // end of method edit

//	@RequestMapping(value = "/delete/user/{user_id}")
//	public ModelAndView deleteUser(@PathVariable("user_id") int user_id) {
//		ModelAndView mv = new ModelAndView();
//
//		Optional<User> obj = userRepository.findById(user_id);
//		User user = obj.get();
//
//		shippingAddressRepository.deleteByUserId(user_id);
//		billingAddressRepository.deleteByUserId(user_id);
//		
//		userRepository.deleteUser(user_id);
//
//		mv.addObject("user", user);
//
//		return new ModelAndView("redirect:/admin/account/all/list");
//	}

	/**
	 * ....................................................82
	 * 
	 * @return
	 */
	@RequestMapping(value = "/account/paid/invoices", method = RequestMethod.GET)
	public ModelAndView showPaidInvoices() {
		ModelAndView mv = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// ----------------------------------------------------------------------------
		mv.setViewName("/my_account/admin/paid_invoice");
		return mv;
	}

	/**
	 * ....................................................83
	 * 
	 * @return
	 */
	@RequestMapping(value = "/account/unpaid/invoices", method = RequestMethod.GET)
	public ModelAndView showUnpaidInvoices() {
		ModelAndView mv = new ModelAndView();
		// **************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// *************************************************************************
		mv.setViewName("/my_account/admin/unpaid_invoice");
		return mv;
	}

	// ****************************************************************************************Order_data*********************************************************
	/*
	 * @RequestMapping(value = "/order_data/list", method = RequestMethod.GET)
	 * public ModelAndView showOrderList() { ModelAndView mv = new ModelAndView();
	 * // **************************************************************************
	 * Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	 * User user = userService.findUserByEmail(auth.getName());
	 * mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
	 * // *************************************************************************
	 * 
	 * List<Order_data> oderList = orderRepository.findAll();
	 * 
	 * // int user_id = user.getId(); List<Lot> lots = (List<Lot>)
	 * lotRepository.findAll(); mv.addObject("lots", lots);
	 * 
	 * mv.addObject("orderLists", oderList);
	 * 
	 * mv.setViewName("/my_account/admin/order_data");
	 * 
	 * return mv;
	 * 
	 * }
	 */

	/**
	 * -------------------------------------------------------84
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/order/info", method = RequestMethod.GET)
	public ModelAndView viewOrderList(@RequestParam(defaultValue = "0") int page) {
		ModelAndView mv = new ModelAndView();
		// **************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// *************************************************************************
		List<Lot_Order> oderList = orderRepository.findAll();
		mv.addObject("orderLists", oderList);
		mv.setViewName("/my_account/admin/order-info");
		return mv;
	}

	/**
	 * ...............................................85
	 * 
	 * @param orderId
	 * @return
	 */
	// delivery status
	@RequestMapping(value = "/order/delivery/status/{orderId}", method = RequestMethod.GET)
	public ModelAndView orderStatust(@PathVariable long orderId) {
		ModelAndView mv = new ModelAndView();
		// **************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// *************************************************************************
		Lot_Order lot_Order = orderRepository.findById(orderId);
		mv.addObject("order", lot_Order);
		mv.setViewName("/my_account/admin/delivery-status");
		return mv;
	}

	/**
	 * .....................................................86
	 * 
	 * @param orderId
	 * @param delivery_status
	 * @return
	 */
	@RequestMapping(value = "/update/order/delivery/status/{orderId}", method = RequestMethod.POST)
	public ModelAndView updateOrderStatust(@PathVariable long orderId, @RequestParam String delivery_status) {
		ModelAndView mv = new ModelAndView();
		// **************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// *************************************************************************
		Lot_Order lot_Order = orderRepository.findById(orderId);
		lot_Order.setDelivery_status(delivery_status);
		orderRepository.save(lot_Order);
		mv.addObject("order", lot_Order);
		mv.setViewName("/my_account/admin/delivery-status");
		return new ModelAndView("redirect:/admin/order/info/");
	}

	/**
	 * ----------------------------------------------87
	 * 
	 * @return
	 */
	// *******************************************Slider images**********
	@SuppressWarnings("unused")
	@RequestMapping(value = "/upload/slider/images", method = RequestMethod.GET)
	public ModelAndView uploadSliderImages() {
		ModelAndView mv = new ModelAndView();
		// **************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// *************************************************************************
		SliderImages images = new SliderImages();
		mv.addObject("img", images);
		mv.setViewName("/my_account/admin/slider-images");
		return mv;
	}

	/**
	 * .............................................88
	 * 
	 * @param image1
	 * @param image2
	 * @param image3
	 * @return
	 */
	@RequestMapping(value = "/save/slider/images", method = RequestMethod.POST) // testing // success
	public ModelAndView saveImages(@RequestParam("image1") MultipartFile image1,
			@RequestParam("image2") MultipartFile image2, @RequestParam("image3") MultipartFile image3) {
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
<<<<<<< HEAD
		sliderImagesRepoaitory.save(images);
=======
		
		
		sliderImageRepository.save(images);
		
>>>>>>> 24fd5d7109fa729315c24432dfff3db1654da8a4
		mv.addObject("image", images);
		// imagesRepository.saveAll(images);

		// return "data saved";
		return new ModelAndView("redirect:/admin/test/slider/images");
	}

	/**
	 * .............................................89
	 * 
	 * @return
	 */
	@RequestMapping(value = "/test/slider/images", method = RequestMethod.GET)
	public ModelAndView testSliderImages() {
		ModelAndView mv = new ModelAndView();
		// **************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
<<<<<<< HEAD
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// *************************************************************************
		List<SliderImages> images = sliderImagesRepoaitory.findAll();
=======
		mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
		//*************************************************************************
		
		List<SliderImages> images = sliderImageRepository.findAll();
		
		
>>>>>>> 24fd5d7109fa729315c24432dfff3db1654da8a4
		mv.addObject("img", images);
		mv.setViewName("/my_account/admin/slider-test");
		return mv;
	}

	/**
	 * ...................................................90
	 * 
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/slider/image/list", method = RequestMethod.GET)
	public ModelAndView sliderImg(@RequestParam(defaultValue = "0") int page) {
		ModelAndView mv = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
<<<<<<< HEAD
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// -----------------------------------------------------------------------------
		List<SliderImages> slider = sliderImagesRepoaitory.findAll();
		mv.addObject("slider", slider);
=======
        User user = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
        
    
       
       List<SliderImages> slider = sliderImageRepository.findAll();
       
       mv.addObject("slider", slider);
//        mv.addObject("lots", lotRepository.findAll(new PageRequest(page, 25)));
//        mv.addObject("currentPage", page);
>>>>>>> 24fd5d7109fa729315c24432dfff3db1654da8a4
		mv.setViewName("/my_account/admin/slider-image-table");
		return mv;
	}

	/**
	 * .....................................................91
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/slider/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteSliderIm(@PathVariable(value = "id") int id) {
		ModelAndView mv = new ModelAndView();
<<<<<<< HEAD
		Optional<SliderImages> obj = sliderImagesRepoaitory.findById(id);
		SliderImages slider = obj.get();
		sliderImagesRepoaitory.deleteById(id);
=======
		
		Optional<SliderImages> obj = sliderImageRepository.findById(id);
		SliderImages slider = obj.get();
		
		System.out.println("###############################################################################################" + id);
		sliderImageRepository.deleteById(id);
		
>>>>>>> 24fd5d7109fa729315c24432dfff3db1654da8a4
		mv.addObject("slid", slider);
		return new ModelAndView("redirect:/admin/upload/slider/images");
	}

	/**
	 * .....................................................92
	 * 
	 * @return
	 */
	@RequestMapping(value = "/order_data/AGB", method = RequestMethod.GET)
	public ModelAndView showAGB() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("AGB");
		return mv;
	}

	/**
	 * ..................................................93
	 * 
	 * @return
	 */
	@RequestMapping(value = "/order_data/wiederruf", method = RequestMethod.GET)
	public ModelAndView showwiederruf() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("wiederruf");
<<<<<<< HEAD
=======
		
		return mv;
		
	}
	
	@RequestMapping(value="/payment/option")
	public ModelAndView showPayment() {
		ModelAndView mv = new ModelAndView();
		
		//**************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
		//*************************************************************************
		
		//lot_LagerRepository.intsert();
		
		mv.setViewName("/my_account/admin/payment");
		
>>>>>>> 24fd5d7109fa729315c24432dfff3db1654da8a4
		return mv;
	}

	// ----------------------------------------------------------test

}// end of admin controller
