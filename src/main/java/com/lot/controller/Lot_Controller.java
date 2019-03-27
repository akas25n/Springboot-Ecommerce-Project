package com.lot.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lot.model.Lager_Product;
import com.lot.model.Lot;
import com.lot.model.Product;
import com.lot.model.User;
import com.lot.repository.Lager_ProductRepository;
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
		
		@Autowired
		private Lager_ProductRepository  lager_ProductRepository;
		
		
		
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
			List<Lager_Product> product = lager_ProductRepository.findAllByLotId(lotId);
			
			//------------------------------------------------------------------------------counting volume
			int vol= 0;
			int i=0;
			product.get(i).getQuantity();
			
			for(i = 0; i< product.size(); i++) {
				
				String st =product.get(i).getQuantity();
				
				vol =vol + (Integer.parseInt(st)); 	
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
			
			lots.setLotPrice(price);
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
	
			mv.setViewName("lotDetails");
			return mv;

		}
	
	}
	


