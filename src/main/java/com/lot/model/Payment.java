package com.lot.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="payment")
@XmlType(propOrder = {"type", "costs","directdebit"})
public class Payment {

	String type;
	double costs;
	String directdebit;
	/**
	 * 
	 */
	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param type
	 * @param costs
	 * @param directdebit
	 */
	public Payment(String type, double costs, String directdebit) {
		super();
		this.type = type;
		this.costs = costs;
		this.directdebit = directdebit;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getCosts() {
		return costs;
	}
	public void setCosts(double costs) {
		this.costs = costs;
	}
	public String getDirectdebit() {
		return directdebit;
	}
	public void setDirectdebit(String directdebit) {
		this.directdebit = directdebit;
	}
	
}
