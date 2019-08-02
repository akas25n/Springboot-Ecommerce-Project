package com.lot.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="shipment")
public class Shipment {
	
	double price;

	/**
	 * @param price
	 */
	public Shipment(double price) {
		super();
		this.price = price;
	}

	/**
	 * 
	 */
	public Shipment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	
}
