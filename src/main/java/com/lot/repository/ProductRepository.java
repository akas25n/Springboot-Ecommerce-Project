package com.lot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lot.model.Product;



@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	List<Product> findAll();
	
	@Query(value = "Select * from db_a2000_1.product p where p.a_ean=?1", nativeQuery=true)
	public Product find_BY_EAN(long id);
	
	
	@Query(value = "SELECT * FROM product p LEFT JOIN lot_product cup on p.a_ean = cup.product_a_ean_fk LEFT JOIN lot_table l on l.lot_id = cup.lot_id_fk WHERE l.lot_id =?1", nativeQuery=true)
	public List<Product> findAllByLotId(long id);
	
}
