package com.lot.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "order")
@XmlType(propOrder = {"order_data", "sell_To", "ship_To", "shipment", "payment", "items"})
public class Order {
	
	Order_data order_data;
	Sell_To sell_To;
	Ship_To ship_To;
	Shipment shipment;
	Payment payment;
	Items items;
	/**
	 * 
	 */
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param order_data
	 * @param sell_To
	 * @param ship_To
	 * @param shipment
	 * @param payment
	 * @param items
	 */
	public Order(Order_data order_data, Sell_To sell_To, Ship_To ship_To, Shipment shipment, Payment payment,
			Items items) {
		super();
		this.order_data = order_data;
		this.sell_To = sell_To;
		this.ship_To = ship_To;
		this.shipment = shipment;
		this.payment = payment;
		this.items = items;
	}
	public Order_data getOrder_data() {
		return order_data;
	}
	public void setOrder_data(Order_data order_data) {
		this.order_data = order_data;
	}
	public Sell_To getSell_To() {
		return sell_To;
	}
	public void setSell_To(Sell_To sell_To) {
		this.sell_To = sell_To;
	}
	public Ship_To getShip_To() {
		return ship_To;
	}
	public void setShip_To(Ship_To ship_To) {
		this.ship_To = ship_To;
	}
	public Shipment getShipment() {
		return shipment;
	}
	public void setShipment(Shipment shipment) {
		this.shipment = shipment;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	public Items getItems() {
		return items;
	}
	public void setItems(Items items) {
		this.items = items;
	}
	
	
}
