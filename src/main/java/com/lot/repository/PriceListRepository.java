package com.lot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lot.model.PriceList;

public interface PriceListRepository extends JpaRepository<PriceList, Integer>{

}
