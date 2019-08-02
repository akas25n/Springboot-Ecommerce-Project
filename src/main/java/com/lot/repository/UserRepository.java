package com.lot.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lot.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer>{

	User findByEmail(String email);
	
	User findByConfirmationToken(String confirmationToken);
	
	public List<User> findAll();
	
	@Query(value="SELECT * FROM db_a2000_1.user u left join db_a2000_1.user_role ur on u.user_id = ur.user_id left join db_a2000_1.role r on ur.role_id = r.role_id WHere r.role_id = '1' and u.enabled='1'", nativeQuery=true)
	public List<User> findAllAdmin();
	
	@Query(value="SELECT * FROM db_a2000_1.user u left join db_a2000_1.user_role ur on u.user_id = ur.user_id left join db_a2000_1.role r on ur.role_id = r.role_id WHere r.role_id = '2' and u.enabled='1'", nativeQuery=true)
	public List<User> findAllUser();
	
	@Query(value="SELECT * FROM db_a2000_1.user u  WHere u.enabled='0'", nativeQuery=true)
	public List<User> findInactiveUser();
	
//	@Modifying
//	@Transactional
//	@Query(value="delete FROM user where user_id = ?1", nativeQuery = true)
//	void deleteUser(int user_id);
	
	
	@Transactional
	@Modifying
	@Query(value="UPDATE user u left join user_role ur on u.user_id = ur.user_id SET ur.role_id=?1 WHERE u.user_id = ?2", nativeQuery = true)
	public void update_user_role(Integer role_status, Integer user_id);
}
