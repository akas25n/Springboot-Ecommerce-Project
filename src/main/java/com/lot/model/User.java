package com.lot.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user")
public class User {

	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "user_id")
	    private int user_id;
	    
	    @Column(name = "email")
	    @Email(message = "*Please provide a valid e-mail")
	    @NotEmpty(message = "*Please provide an e-mail")
	    private String email;
	    
	    @Column(name = "password")
	    //@Transient
	    private String password;
	    
	    
	    @Column(name="company")
		@NotEmpty(message="*Please provide your company name")
		private String company;
	    
	    @Column(name = "first_name")
		@NotEmpty(message = "*Please provide your first name")
		private String first_name;
	    
	    @Column(name ="last_name")
		@NotEmpty(message = "*Please provide your last name")
		private String last_name;
	  //------------------------------------------------------------------------------------------------------------------changed  
	    @Column(name = "enabled")
	    private int enabled;
	    
	    
	    @Column(name = "confirmation_token")
		private String confirmationToken;
	  //--------------------------------------------------------------------------------------------------------------------changed
	    
		
	    
	    @ManyToMany(cascade = CascadeType.ALL)
	    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	    private Set<Role> roles;
	    

		public int getUser_id() {
			return user_id;
		}


		public void setUser_id(int user_id) {
			this.user_id = user_id;
		}


		

		public int getId() {
			return user_id;
		}


		public void setId(int id) {
			this.user_id = id;
		}


		public String getEmail() {
			return email;
		}


		public void setEmail(String email) {
			this.email = email;
		}


		public String getPassword() {
			return password;
		}


		public void setPassword(String password) {
			this.password = password;
		}


		public String getCompany() {
			return company;
		}


		public void setCompany(String company) {
			this.company = company;
		}


		public String getFirst_name() {
			return first_name;
		}


		public void setFirst_name(String first_name) {
			this.first_name = first_name;
		}


		public String getLast_name() {
			return last_name;
		}


		public void setLast_name(String last_name) {
			this.last_name = last_name;
		}

		public int getEnabled() {
			return enabled;
		}


		public void setEnabled(int enabled) {
			this.enabled = enabled;
		}


		public String getConfirmationToken() {
			return confirmationToken;
		}


		public void setConfirmationToken(String confirmationToken) {
			this.confirmationToken = confirmationToken;
		}


		public Set<Role> getRoles() {
			return roles;
		}


		public void setRoles(Set<Role> roles) {
			this.roles = roles;
		}
    
}
