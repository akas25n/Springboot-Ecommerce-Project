package com.lot.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lot.model.BillingAddress;
import com.lot.model.CustomizedProduct;
import com.lot.model.DeliveryStatus;
import com.lot.model.Lot;
import com.lot.model.Lot_Lager;
import com.lot.model.MailRequest;
import com.lot.model.Lot_Order;
import com.lot.model.Product;
import com.lot.model.ShippingAddress;
import com.lot.model.User;
import com.lot.repository.BillingAddressRepository;
import com.lot.repository.DeliveryStatusRepository;
import com.lot.repository.LotRepository;
import com.lot.repository.Lot_LagerRepository;
import com.lot.repository.LotOrderRepository;
import com.lot.repository.ProductRepository;
import com.lot.repository.ShippingAddressRepository;
import com.lot.service.EmailService;
import com.lot.service.LotOrderService;
import com.lot.service.UserService;

@RestController
@RequestMapping("/order")
public class OrderDetailsController {
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private LotRepository lotRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LotOrderRepository orderRepository;
	
	@Autowired
	BillingAddressRepository billRepo;
	@Autowired
	ShippingAddressRepository shippingAddressRepository;
	
	@Autowired
	DeliveryStatusRepository deliveryStatusRepository;
	
	@Autowired
	private Lot_LagerRepository lot_lagerrepository;
	
	@Autowired
	LotOrderService lotOrderService;
	
	/*
	 * .......................................33
	 */
	//******************************************************************************Order_data details*****************************************************************
	@RequestMapping(value = "/details/{lotId}",method=RequestMethod.GET)
	public ModelAndView showOrderdetails(@PathVariable("lotId") long lotId)
	{
		ModelAndView mv = new ModelAndView();
		//********************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
        //********************************************************************************************
        int user_id = user.getId();
        BillingAddress billAddress= billRepo.findByUserId(user_id);
        ShippingAddress shippAddress = shippingAddressRepository.findByUserId(user_id); 
		Optional<Lot> new_obj = lotRepository.findById(lotId);
		Lot lots= new_obj.get();
		List<Lot_Lager> product = lot_lagerrepository.findAllByLotId(lotId);
		mv.addObject("lots",lots);
		mv.addObject("products", product);
		mv.addObject("billAddress", billAddress);
		mv.addObject("shippAddress", shippAddress);
		mv.addObject("message1", "Please add a Shipping address");
		mv.addObject("message2", "Please add a billing address");
		mv.setViewName("orderDetails");
		return mv;

	}
	
	@RequestMapping(value="/AGB", method=RequestMethod.GET)
	public ModelAndView showAGB() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("AGB");
		return mv;
	}
	
	@RequestMapping(value="/wiederruf", method=RequestMethod.GET)
	public ModelAndView showWiederruf() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("wiederruf");
		return mv;
	}
	

	/*
	 * ...........................................34
	 */
	//******************************************************************************Order_data confirmation*****************************************************************
	@RequestMapping(value = "/confirmation/{lotId}",method=RequestMethod.GET)
	public ModelAndView shwOrderdetails(@PathVariable("lotId") long lotId){

		ModelAndView mv = new ModelAndView();
		//********************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
        //********************************************************************************************
       int user_id = user.getId();
       ShippingAddress shippAddress = shippingAddressRepository.findByUserId(user_id);
       Optional<Lot> new_obj = lotRepository.findById(lotId);
       Lot lots= new_obj.get();
		List<Lot_Lager> product = lot_lagerrepository.findAllByLotId(lotId);
        //----------------------------------------------------------------------------------------------------------------------
        Map<String, Object> model = new HashMap<>();
        model.put("name", user.getFirst_name() + user.getLast_name());
        model.put("message1", "Thank you for your purchage. You can see the current status of your order_data at any time under " + "http://stocklots.motion-fashion.de/order/list"+ "." + 
				"\nIf you have any questions about your customer account or your order_data, \nplease send us an e-mail.");
        MailRequest mailRequest = new MailRequest();
        mailRequest.setName(user.getFirst_name());
        mailRequest.setFrom("noreply@domain.com");
        mailRequest.setTo(user.getEmail());
        mailRequest.setSubject("Order confirmation");
        emailService.sendEmail_confirm_order(mailRequest, model);
        //----------------------------------------------------------------------------------------------------------------------
		mv.addObject("lots",lots);
		mv.addObject("products", product);
		mv.addObject("shippAddress", shippAddress);
		mv.addObject("msg", "Thank you for your purchase!");
		mv.setViewName("order-confirmation");
		// here i have to delete/transfer the lot data to another table with all the article in the lot
		return mv;

	}
	
/*
 * ...........................35
 */
	//******************************************************************************Order_data list according to lotId*****************************************************************
	@RequestMapping(value="/list/{lotId}", method=RequestMethod.GET)
	public ModelAndView showAllOrders(@PathVariable("lotId") Long lotId) throws IOException {
		ModelAndView mv = new ModelAndView();
		//**********************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
        //***********************************************************************
        int user_id = user.getId();
      //----------------------------------------------------------------------------------------  
        ShippingAddress shipAdd = shippingAddressRepository.findByUserId(user_id);
        BillingAddress billAdd= billRepo.findByUserId(user_id);
      //--------------------------------------------------------------------------------------  
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		Optional<Lot> newLot = lotRepository.findById(lotId);
		Lot lot = newLot.get();
		Lot_Order obj = orderRepository.findByUserAndLot(user_id, lotId);
		if (obj == null) {
			Lot_Order orderObj = new Lot_Order();
			orderObj.setOrder_status(true);
			orderObj.setOrderDate(formatter.format(date));
			orderObj.setLot(lot);
			orderObj.setUser(user);
			orderObj.setShippingAddress(shipAdd);
			orderObj.setBillingAddress(billAdd);
			orderObj.setDelivery_status("Working on your order");
			orderRepository.save(orderObj);
			orderObj.getLot().setLot_status(0);
			//lot.setLot_status(0);
			lotRepository.save(lot);
			mv.addObject("lt", newLot);
			long id = orderObj.getOrderId();
			
			/*
			 * --------------------------------------------Sending order XML file to SFTP
			 */
			//lotOrderService.send_XML_to_SFTP(id);
			
			/*
			 * --------------------------------------------Sending order XML file to local file
			 */
			//lotOrderService.create_order_xml(id);
			
		}
		mv.setViewName("orderDetails");
		return new ModelAndView("redirect:/order/confirmation/" + lotId);
	}
	
	/*
	 * ..................................36
	 */
	//******************************************************************************Order_data list according to user*****************************************************************
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView showOrderList(@RequestParam(defaultValue="0") int page) {
		ModelAndView mv = new ModelAndView();
		//**************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
		//*************************************************************************
		int user_id = user.getId();
		List<Lot_Order> oderList = orderRepository.findByUserId(user_id);
		mv.addObject("orderLists", oderList);
		mv.setViewName("/my_account/user/your_orders");
		return mv;
		
	}
	
	/*
	 * ...................................37
	 */
	@GetMapping("/lot/details/{lotId}")
	public ModelAndView orderlotView(@PathVariable long lotId) {
		ModelAndView view = new ModelAndView();
		//**************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		view.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
		//*************************************************************************
		Optional<Lot> obj = lotRepository.findById(lotId);
		Lot lots = obj.get();
		List<Lot_Lager> product = lot_lagerrepository.findAllByLotId(lotId);
		//------------------------------------------------------------------------------counting volume
		int vol= 0;
		int i=0;
		product.get(i).getBESTAND();
		for(i = 0; i< product.size(); i++) {
			int st =product.get(i).getBESTAND();
			vol =vol + (st); 	
		}
		//------------------------------------------------------------------------------counting volume
		double price= 0;
		int in=0;
		product.get(in).getPREIS();
		product.get(in).getBESTAND();
		for(in = 0; in< product.size(); in++) {
			double st =product.get(in).getPREIS();
			int qn =  product.get(in).getBESTAND();
			price = price + (st * qn); 	
		}
		double retailPrice= 0;
		int inm=0;
		product.get(inm).getUVP();
		product.get(inm).getBESTAND();
		for(inm = 0; inm< product.size(); inm++) {	
			double st =product.get(inm).getUVP();
			int qn =  product.get(inm).getBESTAND();
			retailPrice = retailPrice + (st * qn); 	
		}
		double totalDiscount = retailPrice - price;
		double avgBuyingPrice = price / vol;
		double avgRetailPrice = retailPrice / vol;
		double percentageOfDiscount = ( totalDiscount / retailPrice ) * 100;
		/*
		 * numberOfSKU-------------------
		 * avgQuantityPerSKU----------
		 */
		//----------------------------------------------------------------------------------------
		lots.setLotPrice(price);
		lots.setVolume(vol); // setiing lot volume
		lots.setActualPrice(retailPrice);
		lots.setTotalDiscount(totalDiscount);
		lots.setAverageBuyingPrice(avgBuyingPrice);
		lots.setAverageRetailPrice(avgRetailPrice);
		lots.setPercentageOfDiscountOfRetailPrice(percentageOfDiscount);
		//------------------------------------------------------------------------------------
		List<String> imageList = new ArrayList<String>();
		for(Lot_Lager set : product) {
			if(!(set.getIMAGE_1()).isEmpty()) {
			}
			if(!(set.getIMAGE_2()).isEmpty()) {
				imageList.add(set.getIMAGE_2());
			}
			if(!(set.getIMAGE_3()).isEmpty()) {
				imageList.add(set.getIMAGE_3());
			}	
		}
		
		//***********************************************************************************************************************Test
		List<String> articleNumbers = new ArrayList<>();
		
		for (Lot_Lager lotLager : product) {
				if(!articleNumbers.contains(lotLager.getART_NR()))
					articleNumbers.add(lotLager.getART_NR());
			}

		List<Lot_Lager> lager = new ArrayList<Lot_Lager>();
		List<CustomizedProduct> customisedProduct = new ArrayList<CustomizedProduct>();
		for (String art_nr : articleNumbers) {	
			lager = lot_lagerrepository.findAllArtNumber(art_nr);
			CustomizedProduct obj1 = new CustomizedProduct();
			obj1.setArticle_number(art_nr);
			obj1.setProduct(lager);
			obj1.setUVP(lager.get(0).getUVP());
			obj1.setPRICE(lager.get(0).getPREIS());
			obj1.setIMAGE(lager.get(0).getIMAGE_1());
			obj1.setPROD_MATERIAL(lager.get(0).getPROD_MATERIAL());
			obj1.setPROD_TEXT(lager.get(0).getPROD_TEXT());
			obj1.setBRAND(lager.get(0).getBRAND());
			obj1.setGENDER(lager.get(0).getGENDER());
			customisedProduct.add(obj1);
		}
		//***********************************************************************************************************************
		lotRepository.save(lots); //updating the lot in the database
		view.addObject("lots",lots);
		view.addObject("products", product);
		view.addObject("images", imageList);
		view.addObject("lg", customisedProduct);
		//view.addObject("order", lot_Order);
		view.setViewName("sold-lot-view");
		return view;
	}
	
	/*
	 * ................................38
	 */
	@GetMapping("/create_xml/{orderId}")
	public String create_xml_file(@PathVariable long orderId) throws IOException{
		Lot_Order lot_Order = orderRepository.findById(orderId);
		lotOrderService.create_order_xml(lot_Order.getOrderId());
		return "Order XML has been imported";
	}
	
	
}
