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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="order_table")
public class Order {
	
	@Id
	@NotNull
	@Column(name="order_id", length= 20)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long orderId;
	
	@Column(name="order_date")
	private String orderDate;
	
	@Column(name= "oder_status")
	private boolean order_status = false;
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="lotId", nullable=false)
	private Lot lot;
	

	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable=false)
	private User user;
	
	
	
/*	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "shipping_add_id", nullable=false)
	private ShippingAddress shippingAddress;
	
	
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "billing_add_id", nullable=false)
	private BillingAddress billingAddress;
	*/

	public Order() {
		
	}



	public Order(@NotNull long orderId, String orderDate, boolean order_status, Lot lot, User user) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.order_status = order_status;
		this.lot = lot;
		this.user = user;
	}



	public long getOrderId() {
		return orderId;
	}



	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}



	public String getOrderDate() {
		return orderDate;
	}



	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}



	public boolean isOrder_status() {
		return order_status;
	}



	public void setOrder_status(boolean order_status) {
		this.order_status = order_status;
	}



	public Lot getLot() {
		return lot;
	}



	public void setLot(Lot lot) {
		this.lot = lot;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}

	


	/*	public ShippingAddress getShippingAddress() {
		return shippingAddress;
	}


	public void setShippingAddress(ShippingAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}


	public BillingAddress getBillingAddress() {
		return billingAddress;
	}


	public void setBillingAddress(BillingAddress billingAddress) {
		this.billingAddress = billingAddress;
	}*/

	
}
