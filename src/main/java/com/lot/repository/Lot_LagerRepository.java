package com.lot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lot.model.Lot_Lager;

public interface Lot_LagerRepository extends JpaRepository<Lot_Lager, Long>{

	@Query(value="SELECT * FROM lot_db.lot_lager l where l.ean=?1", nativeQuery=true)
	public Lot_Lager find_BY_EAN(long id);
	
}
