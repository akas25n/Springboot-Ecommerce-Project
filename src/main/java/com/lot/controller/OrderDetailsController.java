package com.lot.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lot.model.BillingAddress;
import com.lot.model.Lot;
import com.lot.model.Order;
import com.lot.model.Product;
import com.lot.model.ShippingAddress;
import com.lot.model.User;
import com.lot.repository.BillingAddressRepository;
import com.lot.repository.LotRepository;
import com.lot.repository.OrderRepository;
import com.lot.repository.ProductRepository;
import com.lot.repository.ShippingAddressRepository;
import com.lot.service.UserService;

@RestController
@RequestMapping("/order")
public class OrderDetailsController {
	
	@Autowired
	private LotRepository lotRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	BillingAddressRepository billRepo;
	@Autowired
	ShippingAddressRepository shippingAddressRepository;
	
	//******************************************************************************Order details*****************************************************************
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
		
		List<Product> product = productRepository.findAllByLotId(lotId);
		
		
		

		mv.addObject("lots",lots);
		mv.addObject("products", product);
		mv.addObject("billAddress", billAddress);
		mv.addObject("shippAddress", shippAddress);
		
		mv.addObject("message1", "Please add a Shipping address");
		mv.addObject("message2", "Please add a billing address");
		
		mv.setViewName("orderDetails");
		return mv;

	}
	

	
	//******************************************************************************Order confirmation*****************************************************************
	@RequestMapping(value = "/confirmation/{lotId}",method=RequestMethod.GET)
	public ModelAndView shwOrderdetails(@PathVariable("lotId") long lotId)
	{
		ModelAndView mv = new ModelAndView();
		
		//********************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
        //********************************************************************************************
        
       
        
		Optional<Lot> new_obj = lotRepository.findById(lotId);
		Lot lots= new_obj.get();
		
		List<Product> product = productRepository.findAllByLotId(lotId);

		mv.addObject("lots",lots);
		mv.addObject("products", product);
		
		mv.setViewName("order-confirmation");
		return mv;

	}
	

	//******************************************************************************Order list according to lotId*****************************************************************
	@RequestMapping(value="/list/{lotId}", method=RequestMethod.GET)
	public ModelAndView showAllOrders(@PathVariable("lotId") Long lotId) {
		ModelAndView mv = new ModelAndView();
		//**********************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
        //***********************************************************************
        
        int user_id = user.getId();
        
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		
		Optional<Lot> newLot = lotRepository.findById(lotId);
		Lot lot = newLot.get();
		
		
		Order obj = orderRepository.findByUserAndLot(user_id, lotId);
		
		
		if (obj == null) {
			
		
			Order orderObj = new Order();
			
			orderObj.setOrder_status(true);
			orderObj.setOrderDate(formatter.format(date));
			orderObj.setLot(lot);
			orderObj.setUser(user);
			
			orderRepository.save(orderObj);
			
			orderObj.getLot().setLot_status(0);
			//lot.setLot_status(0);
			lotRepository.save(lot);
			
		}
		mv.setViewName("orderDetails");
		
		System.out.println("*******************************************************************************" + user_id + "*******************************");
		return new ModelAndView("redirect:/order/confirmation/" + lotId);
	}
	
	
	//******************************************************************************Order list according to user*****************************************************************
	@RequestMapping(value="/list", method=RequestMethod.GET)
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
		
		
		mv.setViewName("/my_account/user/your_orders");
		
		return mv;
		
	}
	

	
	
}
