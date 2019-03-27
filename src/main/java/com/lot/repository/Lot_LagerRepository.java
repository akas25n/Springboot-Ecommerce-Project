package com.lot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lot.model.Lot_Lager;

public interface Lot_LagerRepository extends JpaRepository<Lot_Lager, Long>{

	@Query(value="SELECT * FROM lot_db.lot_lager l where l.ean=?1", nativeQuery=true)
	public Lot_Lager find_BY_EAN(long id);
	
	
	/*@Query(value="insert INTO test(ean, pName)\r\n" + 
			"select lot_lager.EAN, product.p_name from lot_lager inner join product on lot_lager.EAN = product.a_ean where lot_lager.EAN = ?1 and product.a_ean=?2", nativeQuery= true )
	public List<Lot_Lager> intsert();*/
	
}
