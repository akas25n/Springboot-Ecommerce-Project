package com.lot.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.lot.model.Lot_Lager;
import com.lot.model.Stocklots_Offer;

public interface Stocklots_Offer_Repository extends JpaRepository<Stocklots_Offer, Long>{

	@Query(value="SELECT * FROM db_a2000_1.stocklots_offer l where l.ean=?1", nativeQuery=true)
	public Stocklots_Offer find_BY_EAN(long id);
	
	
	@Query(value = "SELECT * FROM stocklots_offer p LEFT JOIN lot_products cup on p.ean = cup.ean_fk LEFT JOIN lot_table l on l.lot_id = cup.lot_id_fk WHERE l.lot_id =?1", nativeQuery=true)
	public List<Stocklots_Offer> findAllByLotId(long id);
	
	@Transactional
	@Modifying(flushAutomatically = true, clearAutomatically = true)
	@Query(value="delete from stocklots_offer", nativeQuery = true)
	public void deleteAll();


	//public void save(List<? extends Stocklots_Offer> offer);
}
