package com.lot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lot.model.Order;
import com.lot.model.ShippingAddress;

@Repository
public interface ShippingAddressRepository extends JpaRepository<ShippingAddress, Integer> {

	/*
	@Query(value="SELECT * FROM customerShippingAddressInfo c LEFT JOIN user_shipping_address cup on c.shipping_add_id = cup.shipping_add_id_fk LEFT JOIN user u on u.user_id = cup.user_id_fk WHERE u.user_id =?1", nativeQuery=true)
	public ShippingAddress findAllByShipId(int id);*/
	
	@Query(value="SELECT * FROM shipping_address WHERE user_id = ?1", nativeQuery=true)
	ShippingAddress findByUserId(Integer user_id);

}
