package com.lot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lot.model.ProductQuantity;

public interface ProductQuantityRepository extends JpaRepository<ProductQuantity, Long> {

	@Query(value="SELECT * FROM db_a2000_1.product_quantity l where l.ean=?1", nativeQuery=true)
	public ProductQuantity find_BY_EAN(long a_ean);

}
