package com.lot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lot.model.Lager_Product;
import com.lot.repository.Lager_ProductRepository;

@Service
public class Lager_ProductService {
	
	@Autowired
	Lager_ProductRepository lager_ProductRepository;
	Lager_Product lg = new Lager_Product();
	
//	public Lager_Product saveLagerProduct() {
//		
//		return lager_ProductRepository.insert();
//	}


}
