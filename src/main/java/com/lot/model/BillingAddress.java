package com.lot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="billing_address")
public class BillingAddress {

	@Id
	@Column(name="billing_add_id", length=11)
	@NotNull
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int billing_add_id;
	
	@NotNull
	@Column(name="contact_person", length=255)
	private String contact_person;
	
	@NotNull
	@Column(name="street", length=255)
	private String street;
	
	
	@NotNull
	@Column(name="city", length=255)
	private String city;
	
	
	@NotNull
	@Column(name="zip_code", length=11)
	private int zip_code;
	
	
	@NotNull
	@Column(name="country", length=255)
	private String country;
	
	@NotNull
	@Column(name="phone_number", length=20)
	private String phone_number;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id", nullable=false)
	private User_Lot user_Lot;

	public BillingAddress() {
		super();
	}

	public BillingAddress(@NotNull int billing_add_id, @NotNull String contact_person, @NotNull String street,
			@NotNull String city, @NotNull int zip_code, @NotNull String country, @NotNull String phone_number,
			User_Lot user_Lot) {
		super();
		this.billing_add_id = billing_add_id;
		this.contact_person = contact_person;
		this.street = street;
		this.city = city;
		this.zip_code = zip_code;
		this.country = country;
		this.phone_number = phone_number;
		this.user_Lot = user_Lot;
	}

	public int getBilling_add_id() {
		return billing_add_id;
	}

	public void setBilling_add_id(int billing_add_id) {
		this.billing_add_id = billing_add_id;
	}

	public String getContact_person() {
		return contact_person;
	}

	public void setContact_person(String contact_person) {
		this.contact_person = contact_person;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getZip_code() {
		return zip_code;
	}

	public void setZip_code(int zip_code) {
		this.zip_code = zip_code;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public User_Lot getUser() {
		return user_Lot;
	}

	public void setUser(User_Lot user_Lot) {
		this.user_Lot = user_Lot;
	}


}
