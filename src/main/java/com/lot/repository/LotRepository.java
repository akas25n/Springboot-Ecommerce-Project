package com.lot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lot.model.Lot;

@Repository
public interface LotRepository extends JpaRepository<Lot, Long>{
	
	@Query(value="SELECT * FROM db_a2000_1.lot_table l where l.lot_id = ?1", nativeQuery = true )
	public Lot findByLotId(long id);
	
	@Query(value="SELECT * FROM db_a2000_1.lot_table l ORDER BY lot_id DESC", nativeQuery = true )
	public List<Lot> findAll();

	@Query(value="SELECT * FROM db_a2000_1.lot_table l where l.lot_status = 1 ORDER BY lot_id DESC",nativeQuery=true)
	public List<Lot> findAllEnabled();
	

	
	
	/*
	 * drop down filter menu
	 * 
	 * 
	 * ...................brands
	 */
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.brand = 'dreimaster' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByDreimaster();
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.brand = 'schmuddelwedda' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllBySchuddelwedda();
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.brand = 'homebase' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByHomebase();
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.brand = 'mymo' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByMyMo();
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.brand = 'mo' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByMo();
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.brand = 'brandalisedHomebase' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByBrandalise();
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.brand = 'iceBound' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByIceBound();
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.brand = 'izia' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByIzia();
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.brand = 'hawkeAndco' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByHawke();
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.brand = 'menuEsTorrent' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByMenu();
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.brand = 'mumAndco' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByMum();
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.brand = 'superfly' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllBySuperfly();
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.brand = 'usha' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByUsha();
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.brand = 'plus-eighteen' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByPlusEighteen();
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.brand = 'soulstar' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllBySoulstar();
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.brand = 'dryLaundry' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByDryL();
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.brand = 'roosevelt' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByRoosevelt();
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.brand = 'petrolIndustries' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByPetrolInd();
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.brand = 'tuffskull' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByTuffskul();
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.brand = 'oldskull' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByOldskull();
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.brand = 'nolie' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByNolie();
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.brand = 'risa' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByRisa();
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.brand = 'blonda' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByBlonda();
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.brand = 'faina' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByFaina();
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.brand = 'talence' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByTalence();
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.brand = 'taddy' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByTaddy();
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.brand = 'felipa' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByFelipa();
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.brand = 'mixed' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByMixed();
	
	
	/*
	 * ..................Gender
	 */
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.gender = 'men' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByMen();
	
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.gender = 'women' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByWomen();
	
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.gender = 'kids' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByKids();
	
	/*
	 * ..................Seasons
	 */
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.seasons = 'men-ss' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByMenSS();
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.seasons = 'men-fw' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByMenFW();
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.seasons = 'women-ss' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByWomenSS();
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.seasons = 'women-fw' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByWomenFW();
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.seasons = 'kid-ss' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByKidSS();
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.seasons = 'kid-fw' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByKidFW();
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.seasons = 'mixed' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByMixedSeasons();
	
	/*
	 * ........................men ............products
	 */
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.category = 'men_shirt' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByMenShirt();
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.category = 'men_jeans' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByMenJeans();
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.category = 'men_jacket' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByMenJacket();
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.category = 'men_trousers' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByMentrousers();
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.category = 'men_sweat_shirt' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByMenSweat()
	;@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.category = 'men_pullover' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByMenPullover();
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.category = 'men_accessoires' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByMenAccessoires();
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.category = 'men_mixed' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByMenMixedProduct();
	
	/*
	 * ....................women...............products
	 */
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.category = 'women_shirt' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByWomenShirt();
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.category = 'women_jeans' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByWomenJeans();
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.category = 'women_jacket' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByWomenJacket();
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.category = 'women_blouse' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByWomenBlouse();
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.category = 'women_trousers' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByWomentrousers();
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.category = 'women_sweat_shirt' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByWomenSweat();
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.category = 'women_pullover' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByWomenPullover();
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.category = 'women_tops' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByWomenTops();
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.category = 'women_accessoires' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByWomenAccessoires();
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.category = 'women_shoes' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByWomenShoes();
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.category = 'women_mixed' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByWomenMixedProduct();
	
	/*
	 * .......................kids..............products
	 */
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.category = 'kid_shirt' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByKidShirt();
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.category = 'kid_jeans' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByKidJeans();
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.category = 'kid_jacket' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByKidJacket();
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.category = 'kid_trousers' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByKidtrousers();
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.category = 'kid_sweat_shirt' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllBykidSweat()
	;@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.category = 'kid_pullover' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByKidPullover();
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.category = 'kid_jumper' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByKidJumper();
	@Query(value="select * from db_a2000_1.lot_table l where l.lot_status = '1' and l.category = 'kid_mixed' order by lot_id DESC", nativeQuery = true)
	public List<Lot> findAllByKidMixedProduct();

	/*
	 * .............................search box
	 */
	@Query(value="SELECT * FROM lot_table l Where l.lot_status = '1' AND (l.Lot_Name LIKE ?1% OR l.lot_Description LIKE ?1% OR l.gender LIKE ?1% OR l.brand LIKE ?1% OR l.category LIKE ?1% OR l.seasons LIKE ?1%)" , nativeQuery = true)
	public List<Lot> searchLot(@Param("searchTerm")String searchTerm);
		
}
