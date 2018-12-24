package com.lot.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lot.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
	
	
	List<Order> findAll();

	//@Query(value="SELECT * FROM order_table WHERE order_status=1", nativeQuery=true)
	@Query(value="SELECT * FROM order_table WHERE order_status=1", nativeQuery=true)
	List<Order> findAllOrder();

	@Query(value="SELECT * FROM order_table WHERE lotId = ?1", nativeQuery=true)
	Order findByLotId(Long lotId);
	
	@Query(value="SELECT * FROM order_table WHERE user_id = ?1", nativeQuery=true)
	List<Order> findByUserId(Integer user_id);
	
	@Query(value="SELECT * FROM order_table WHERE user_id = ?1  and lotId = ?1", nativeQuery=true)
	Order findByUserAndLot(Integer user_id, Long lotId);
	
	/*
	//@Query(value="SELECT * FROM order_table WHERE order_status=1", nativeQuery=true)
	@Query(value="SELECT * FROM order_table WHERE order_status=1", nativeQuery=true)
	List<Order> findAllOrder();

//	@Query(value="SELECT * FROM order_table WHERE lotId = ?1", nativeQuery=true)
//	Order findByLotId(Long lotId);
	
	@Query(value = "SELECT * FROM order o LEFT JOIN lot_order cup on o.orderId = cup.orderId LEFT JOIN lot l on l.lotId = cup.lotId WHERE l.lotId =?1", nativeQuery=true)
	public List<Order> findAllByLotId(long id);
	
	@Query(value="SELECT * FROM order_table WHERE user_id = ?1", nativeQuery=true)
	List<Order> findByUserId(Integer user_id);
	
//	@Query(value="SELECT * FROM order_table WHERE user_id = ?1", nativeQuery=true)
//	Order findByUserAndLot(Integer user_id);
	*/
}
