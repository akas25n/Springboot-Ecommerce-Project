package com.lot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lot.model.BillingAddress;
import com.lot.repository.BillingAddressRepository;

@Service
public class BillingAddressService {

	@Autowired
	private BillingAddressRepository customerBillingAddressInfoRepository;

	public Optional<BillingAddress> findByCustomerBillingAddressInfoId(int id) {
		return customerBillingAddressInfoRepository.findById(id);
	}

	public BillingAddress saveBillInfo(BillingAddress customerBillingAddressInfo) {

		customerBillingAddressInfo.setContact_person(customerBillingAddressInfo.getContact_person());
		customerBillingAddressInfo.setStreet(customerBillingAddressInfo.getStreet());
		customerBillingAddressInfo.setCity(customerBillingAddressInfo.getCity());
		customerBillingAddressInfo.setZip_code(customerBillingAddressInfo.getZip_code());
		customerBillingAddressInfo.setCountry(customerBillingAddressInfo.getCountry());
		customerBillingAddressInfo.setPhone_number(customerBillingAddressInfo.getPhone_number());

		return customerBillingAddressInfoRepository.save(customerBillingAddressInfo);
	}

}
