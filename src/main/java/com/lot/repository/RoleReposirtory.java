package com.lot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lot.model.Role;

@Repository("roleRepository")
public interface RoleReposirtory extends JpaRepository<Role, Integer> {
	
	Role findByRole(String role);
}
