package com.lot.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lot.model.Lot;

@Repository
public interface LotRepository extends JpaRepository<Lot, Long>{
	
	
	@Query(value="SELECT * FROM lot_db.lot l ORDER BY lot_id DESC", nativeQuery = true )
	public List<Lot> findAll();

	@Query(value="SELECT * FROM lot_db.lot l where l.lot_status = 1 ORDER BY lot_id DESC",nativeQuery=true)
	public List<Lot> findAllEnabled();
	
}
