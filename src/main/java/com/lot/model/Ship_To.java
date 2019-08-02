package com.lot.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="ship_to")
@XmlType(propOrder = {"tb_id", "name", "street_no", "zip", "city", "country", "phone_office", "email"})
public class Ship_To {

	long tb_id;
	String name;
	String street_no;
	int zip;
	String city;
	String country;
	String email;
	String phone_office;
	/**
	 * 
	 */
	public Ship_To() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param tb_id
	 * @param name
	 * @param street_no
	 * @param zip
	 * @param city
	 * @param country
	 * @param email
	 * @param phone_office
	 */
	public Ship_To(long tb_id, String name, String street_no, int zip, String city, String country, String email,
			String phone_office) {
		super();
		this.tb_id = tb_id;
		this.name = name;
		this.street_no = street_no;
		this.zip = zip;
		this.city = city;
		this.country = country;
		this.email = email;
		this.phone_office = phone_office;
	}
	public long getTb_id() {
		return tb_id;
	}
	public void setTb_id(long tb_id) {
		this.tb_id = tb_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStreet_no() {
		return street_no;
	}
	public void setStreet_no(String street_no) {
		this.street_no = street_no;
	}
	public int getZip() {
		return zip;
	}
	public void setZip(int zip) {
		this.zip = zip;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone_office() {
		return phone_office;
	}
	public void setPhone_office(String phone_office) {
		this.phone_office = phone_office;
	}
		
	
}
