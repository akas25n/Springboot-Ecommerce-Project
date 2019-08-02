package com.lot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lot.model.NonExistingProduct;

public interface NonExistingProductRepository extends JpaRepository<NonExistingProduct, Long>{

	@Query(value="SELECT * FROM db_a2000_1.non_existing_product l where l.ean=?1", nativeQuery=true)
	public NonExistingProduct find_BY_EAN(long a_ean);

}
