package com.lot.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lot.model.User_Lot;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User_Lot, Integer>{

	User_Lot findByEmail(String email);
	
	User_Lot findByConfirmationToken(String confirmationToken);
	
	public List<User_Lot> findAll();
	
	@Query(value="SELECT * FROM lot_db.user u left join lot_db.user_role ur on u.user_id = ur.user_id left join lot_db.role r on ur.role_id = r.role_id WHere r.role_id = '1'", nativeQuery=true)
	public List<User_Lot> findAllAdmin();
	
	@Query(value="SELECT * FROM lot_db.user u left join lot_db.user_role ur on u.user_id = ur.user_id left join lot_db.role r on ur.role_id = r.role_id WHere r.role_id = '2'", nativeQuery=true)
	public List<User_Lot> findAllUser();
	
	
	@Transactional
	@Modifying
	@Query(value="UPDATE user u left join user_role ur on u.user_id = ur.user_id SET ur.role_id=?1 WHERE u.user_id = ?2", nativeQuery = true)
	public void update_user_role(Integer role_status, Integer user_id);
}
