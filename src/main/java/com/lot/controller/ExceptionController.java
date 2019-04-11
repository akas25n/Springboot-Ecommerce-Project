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

	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ModelAndView resourceNotFound(ResourceNotFoundException ex) {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("message", ex.getMessage());
		mv.setViewName("exceptions/resource-not-found");
		return mv;
		
	}
}
