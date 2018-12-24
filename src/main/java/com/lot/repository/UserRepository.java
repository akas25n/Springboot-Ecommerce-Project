package com.lot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lot.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer>{

	User findByEmail(String email);
	User findByConfirmationToken(String confirmationToken);
}
