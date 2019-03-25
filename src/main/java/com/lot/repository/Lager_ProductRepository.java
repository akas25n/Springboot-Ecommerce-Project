package com.lot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lot.model.Lager_Product;
import com.lot.model.Product;

@Repository
public interface Lager_ProductRepository extends JpaRepository<Lager_Product, Long>{
	
	@Query(value="select * from lot_db.lager_product p where p.ean=?1", nativeQuery=true)
	public Lager_Product find_BY_ean(long id);
	
	@Query(value = "SELECT * FROM lager_product p LEFT JOIN lot_product cup on p.ean = cup.product_a_ean_fk LEFT JOIN lot l on l.lot_id = cup.lot_id_fk WHERE l.lot_id =?1", nativeQuery=true)
	public List<Lager_Product> findAllByLotId(long id);
	
	/*@Query(value="Insert into lager_product(ean,articleNumber,color,size,quantity,productName,brand,targetGroup,productText,productMaterial,articleImage1,"
			+ "articleImage2,articleImage3,articleImage4,articleImage5)"
			+ " select lot_lager.EAN, lot_lager.ART_NR, lot_lager.FARBE, lot_lager.GROESSE, lot_lager.BESTAND, product.p_name, product.p_brand, product.p_comp_zielgruppe_, "
			+ "product.p_text, product.p_comp_material_, product.a_media_image_0_, product.a_media_image_1_, product.a_media_image_2_, product.a_media_image_3_,product.a_media_image_4_ "
			+ " from lot_lager"
			+ " join product on lot_lager.EAN = product.a_ean", nativeQuery=true)
	public Lager_Product insert();
	

	
	@Query(value="Insert into test(ean, productName)"
			+ " select lot_lager.EAN, product.p_name"
			+ " from lot_lager"
			+ " join product on lot_lager.EAN = product.a_ean", nativeQuery=true)
	public void insertTest();*/
	
	
	//public Lager_Product testSelection(Lager_Product lg);
	//@Query(value="select lot_lager.EAN, product.p_name from lot_lager join product on lot_lager.EAN = product.a_ean", nativeQuery=true)
	//public Optional<Lager_Product> testSelection();

	
}
