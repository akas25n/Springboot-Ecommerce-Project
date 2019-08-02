package com.lot.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="order")
@XmlType(propOrder = {"order_data", "sell_to", "ship_to", "shipment", "payment","items"})
public class TestXML {

	Lot_Order order_data;
	BillingAddress billingAddress;
	ShippingAddress shippingAddress;
	Shipment shipment;
	Payment payment;
	Items items;
	/**
	 * @param order_data
	 * @param billingAddress
	 * @param shippingAddress
	 * @param shipment
	 * @param payment
	 * @param items
	 */
	public TestXML(Lot_Order order_data, BillingAddress billingAddress, ShippingAddress shippingAddress,
			Shipment shipment, Payment payment, Items items) {
		super();
		this.order_data = order_data;
		this.billingAddress = billingAddress;
		this.shippingAddress = shippingAddress;
		this.shipment = shipment;
		this.payment = payment;
		this.items = items;
	}
	/**
	 * 
	 */
	public TestXML() {
		super();
	}
	
	@XmlElement(name="order_data")
	public Lot_Order getOrder_data() {
		return order_data;
	}
	public void setOrder_data(Lot_Order order_data) {
		this.order_data = order_data;
	}
	@XmlElement(name="sell_to")
	public BillingAddress getBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(BillingAddress billingAddress) {
		this.billingAddress = billingAddress;
	}
	
	@XmlElement(name="ship_to")
	public ShippingAddress getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(ShippingAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	
	@XmlElement(name="shipment")
	public Shipment getShipment() {
		return shipment;
	}
	public void setShipment(Shipment shipment) {
		this.shipment = shipment;
	}
	
	@XmlElement(name="payment")
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	@XmlElement(name="items")
	public Items getItems() {
		return items;
	}
	public void setItems(Items items) {
		this.items = items;
	}

}
