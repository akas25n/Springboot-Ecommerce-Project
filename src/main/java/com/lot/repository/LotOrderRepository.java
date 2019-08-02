package com.lot.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lot.model.Lot_Order;

public interface LotOrderRepository extends JpaRepository<Lot_Order, Integer> {
	
	
	List<Lot_Order> findAll();

	//@Query(value="SELECT * FROM order_table WHERE order_status=1", nativeQuery=true)
	@Query(value="SELECT * FROM order_table WHERE order_status=1", nativeQuery=true)
	List<Lot_Order> findAllOrder();

	@Query(value="SELECT * FROM order_table WHERE lotId = ?1", nativeQuery=true)
	Lot_Order findByLotId(Long lotId);
	
	@Query(value="SELECT * FROM order_table WHERE user_id = ?1", nativeQuery=true)
	List<Lot_Order> findByUserId(Integer user_id);
	
	@Query(value="SELECT * FROM order_table WHERE user_id = ?1  and lotId = ?1", nativeQuery=true)
	Lot_Order findByUserAndLot(Integer user_id, Long lotId);

	@Query(value="SELECT * FROM order_table WHERE order_id=?1", nativeQuery=true)
	Lot_Order findById(long orderId);

}
