package com.lot.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lot.model.Lot;
import com.lot.model.Product;
import com.lot.model.User;
import com.lot.repository.LotRepository;
import com.lot.repository.ProductRepository;
import com.lot.service.UserService;


@RestController
@RequestMapping("/lot")
public class Lot_Controller {
	
	
		
		@Autowired
		private UserService userService;
		
		@Autowired
		private LotRepository lotRepository;
		
		@Autowired
		private ProductRepository productRepository;
		
		
		
		/*	
		@RequestMapping(value="/details", method = RequestMethod.GET)
		public ModelAndView showArticle() {
			ModelAndView mv = new ModelAndView();
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        User user = userService.findUserByEmail(auth.getName());
	        mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
	     
	      List<Lot> lots = lotRepository.findAll();
	      mv.addObject("lots", lots);
			mv.setViewName("lotDetails");
			
			return mv;
		}
		*/
		
		
		
		

		@RequestMapping(value = "/details/{lotId}",method=RequestMethod.GET)
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
			
			lotRepository.save(lots); // updating the lot in the database
			
			mv.addObject("lots",lots);
			mv.addObject("products", product);
	
			mv.setViewName("lotDetails");
			return mv;

		}
	
	}
	


