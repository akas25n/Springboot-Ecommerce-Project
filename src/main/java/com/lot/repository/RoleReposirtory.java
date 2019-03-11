package com.lot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lot.model.Role_User;

@Repository("roleRepository")
public interface RoleReposirtory extends JpaRepository<Role_User, Integer> {
	
	Role_User findByRole(String role);
}
