package com.lot.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lot.model.Role;
import com.lot.model.User;
import com.lot.repository.RoleReposirtory;
import com.lot.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleReposirtory roleReposirtory;
	@Autowired
	
	public UserService(UserRepository userRepository, RoleReposirtory roleReposirtory) {
		
		this.userRepository = userRepository;
		this.roleReposirtory = roleReposirtory;
		
	}
	
	  public User findUserByEmail(String email) {
	        return userRepository.findByEmail(email);
	  }
	  
	  public Optional<User> findUserByUser_id(int user_id) {
		return userRepository.findById(user_id);
		  
	  }
	  
	  public User findByConfirmationToken(String confirmationToken) {
		  return userRepository.findByConfirmationToken(confirmationToken);
	  }
	  
	  
	  public User saveUser(User user) {    
	      //----------------------------------------------------------------------------------------------------changed
	       // user.setActive(1);
	        Role userRole = roleReposirtory.findByRole("USER");  // ADMIN or USER
	        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
	        return userRepository.save(user);
	    }

}
