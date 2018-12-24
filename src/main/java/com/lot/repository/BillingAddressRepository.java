package com.lot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lot.model.BillingAddress;

@Repository
public interface BillingAddressRepository extends JpaRepository<BillingAddress, Integer>{

	@Query(value="SELECT * FROM billing_address WHERE user_id = ?1", nativeQuery=true)
	public BillingAddress findByUserId(Integer user_id);
}
