package com.lot.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "order")
@XmlType(propOrder = {"order_data", "sell_To", "ship_To", "shipment", "payment", "items"})
public class Order {
	
<<<<<<< HEAD
	Order_data order_data;
	Sell_To sell_To;
	Ship_To ship_To;
	Shipment shipment;
	Payment payment;
	Items items;
	/**
	 * 
	 */
=======
	@Id
	@NotNull
	@Column(name="order_id", length= 20)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long orderId;
	
	@Column(name="order_date")
	private String orderDate;
	
	@Column(name= "oder_status")
	private boolean order_status = false;
	
	@Column(name="delivery_status")
	private String delivery_status;
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="lotId", nullable=false)
	private Lot lot;
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable=false)
	private User user;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "shipping_add_id", nullable=false)
	private ShippingAddress shippingAddress;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "billing_add_id", nullable=false)
	private BillingAddress billingAddress;
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "order_delivery_staus", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "status_id"))
    private Set<DeliveryStatus> status;
	

>>>>>>> 24fd5d7109fa729315c24432dfff3db1654da8a4
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
<<<<<<< HEAD
	public Order_data getOrder_data() {
		return order_data;
=======

	public long getOrderId() {
		return orderId;
>>>>>>> 24fd5d7109fa729315c24432dfff3db1654da8a4
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
