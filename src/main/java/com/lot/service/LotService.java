package com.lot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lot.model.Lot;
import com.lot.model.Lot_Order;
import com.lot.repository.LotRepository;
import com.lot.repository.LotOrderRepository;

@Service
public class LotService {

	@Autowired
	private LotRepository lotRepository;
	
	@Autowired
	private LotOrderRepository orderRepository;
	
	public Lot update(Lot lot) {
		return lotRepository.save(lot);
		
	}


	public Optional<Lot> findLotByLotId(long lotId) {
		
		return lotRepository.findById(lotId);
	}


	
	public List<Lot> findByEnabled(){

		return lotRepository.findAllEnabled();
	}
	
}// end of lot service

