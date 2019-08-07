package com.lot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lot.model.ShippingAddress;
import com.lot.repository.ShippingAddressRepository;

@Service
public class ShippingAddressService {

	@Autowired
	private ShippingAddressRepository customerShippingAddressInfoRepository;

//	public Optional<ShippingAddress> findShipInfoById(int user_id) {
//		return customerShippingAddressInfoRepository.findById(user_id);
//		
//	}

	public ShippingAddress saveShipInfo(ShippingAddress customerShippingAddressInfo) {

		customerShippingAddressInfo.setContact_person(customerShippingAddressInfo.getContact_person());
		customerShippingAddressInfo.setStreet(customerShippingAddressInfo.getStreet());
		customerShippingAddressInfo.setCity(customerShippingAddressInfo.getCity());
		customerShippingAddressInfo.setZip_code(customerShippingAddressInfo.getZip_code());
		customerShippingAddressInfo.setCountry(customerShippingAddressInfo.getCountry());
		customerShippingAddressInfo.setPhone_number(customerShippingAddressInfo.getPhone_number());

		return customerShippingAddressInfoRepository.save(customerShippingAddressInfo);

	}

}
