package com.lot.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lot.model.Role_User;
import com.lot.model.User_Lot;
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
	
	  public User_Lot findUserByEmail(String email) {
	        return userRepository.findByEmail(email);
	  }
	  
	  public Optional<User_Lot> findUserByUser_id(int user_id) {
		return userRepository.findById(user_id);
		  
	  }
	  
	  public User_Lot findByConfirmationToken(String confirmationToken) {
		  return userRepository.findByConfirmationToken(confirmationToken);
	  }
	  
	  
	  public User_Lot saveUser(User_Lot user_Lot) {    
	      //----------------------------------------------------------------------------------------------------changed
	       // user.setActive(1);
	        Role_User userRole = roleReposirtory.findByRole("USER");  // ADMIN or USER
	        user_Lot.setRoles(new HashSet<Role_User>(Arrays.asList(userRole)));
	        return userRepository.save(user_Lot);
	    }

}
