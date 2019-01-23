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
	
	@Query(value = "Select * from lot_db.product p where p.a_ean=?1", nativeQuery=true)
	public Product find_BY_EAN(long id);
	
	
	@Query(value = "SELECT * FROM product p LEFT JOIN lot_product cup on p.a_ean = cup.product_a_ean_fk LEFT JOIN lot l on l.lot_id = cup.lot_id_fk WHERE l.lot_id =?1", nativeQuery=true)
	public List<Product> findAllByLotId(long id);
	
	/*
	 @Query(value="SELECT * FROM campaigns c  left join campaign_user_private cup on c.campaign_id = cup.campaign_Foreign_k_id left join user u on u.user_id = cup.user_Foreign_k_id where u.user_id=?1",nativeQuery = true)
	public List<Campaign> findAllPrivate_ById(int id);//client
	 */
}
