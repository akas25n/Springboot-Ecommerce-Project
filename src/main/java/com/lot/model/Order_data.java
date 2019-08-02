package com.lot.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="order_data")
@XmlType(propOrder = {"order_date", "tb_id", "channel_sign", "channel_id", "channel_no", "paid", "approved", "item_count", "total_item_amount", "date_created"})
public class Order_data {
	
	String order_date;
	long tb_id;
	String channel_sign;
	long channel_id;
	long channel_no;
	double paid;
	int approved;
	int item_count;
	double total_item_amount;
	String date_created;
	
	/**
	 * 
	 */
	public Order_data() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param order_date
	 * @param tb_id
	 * @param channel_sign
	 * @param channel_id
	 * @param channel_no
	 * @param paid
	 * @param approved
	 * @param item_count
	 * @param total_item_amount
	 * @param date_created
	 */
	public Order_data(String order_date, long tb_id, String channel_sign, long channel_id, long channel_no, double paid,
			int approved, int item_count, double total_item_amount, String date_created) {
		super();
		this.order_date = order_date;
		this.tb_id = tb_id;
		this.channel_sign = channel_sign;
		this.channel_id = channel_id;
		this.channel_no = channel_no;
		this.paid = paid;
		this.approved = approved;
		this.item_count = item_count;
		this.total_item_amount = total_item_amount;
		this.date_created = date_created;
	}

	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	public long getTb_id() {
		return tb_id;
	}
	public void setTb_id(long tb_id) {
		this.tb_id = tb_id;
	}
	public String getChannel_sign() {
		return channel_sign;
	}
	public void setChannel_sign(String channel_sign) {
		this.channel_sign = channel_sign;
	}
	public long getChannel_id() {
		return channel_id;
	}
	public void setChannel_id(long channel_id) {
		this.channel_id = channel_id;
	}
	public long getChannel_no() {
		return channel_no;
	}
	public void setChannel_no(long channel_no) {
		this.channel_no = channel_no;
	}
	public double getPaid() {
		return paid;
	}
	public void setPaid(double paid) {
		this.paid = paid;
	}
	public int getApproved() {
		return approved;
	}
	public void setApproved(int approved) {
		this.approved = approved;
	}
	public int getItem_count() {
		return item_count;
	}
	public void setItem_count(int item_count) {
		this.item_count = item_count;
	}
	public double getTotal_item_amount() {
		return total_item_amount;
	}
	public void setTotal_item_amount(double total_item_amount) {
		this.total_item_amount = total_item_amount;
	}
	public String getDate_created() {
		return date_created;
	}
	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}
	
	
	
}
