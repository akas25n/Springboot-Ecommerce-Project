package com.lot.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import com.lot.model.ProductNotFoundException;
import com.lot.model.ResourceNotFoundException;

@RestControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView productNotFound(ProductNotFoundException ex) {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("message", ex.getMessage());
		mv.setViewName("/exceptions/product-not-found");
		
		return mv;
	}

<<<<<<< HEAD
=======
	
>>>>>>> 24fd5d7109fa729315c24432dfff3db1654da8a4
	@ExceptionHandler(ResourceNotFoundException.class)
	public ModelAndView resourceNotFound(ResourceNotFoundException ex) {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("message", ex.getMessage());
<<<<<<< HEAD
		mv.setViewName("/exceptions/resource-not-found");
=======
		mv.setViewName("exceptions/resource-not-found");
>>>>>>> 24fd5d7109fa729315c24432dfff3db1654da8a4
		return mv;
		
	}
}
