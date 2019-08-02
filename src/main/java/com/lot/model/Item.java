package com.lot.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="item")
@XmlType(propOrder = {"tb_id","chanel_id","sku","ean","quantity","billing_text","transfer_price","item_price","date_created"})
public class Item {
	
	long tb_id;
	long chanel_id;
	long sku;
	long ean;
	int quantity;
	String billing_text;
	double transfer_price;
	double item_price;
	String date_created;
	/**
	 * 
	 */
	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param tb_id
	 * @param chanel_id
	 * @param sku
	 * @param ean
	 * @param quantity
	 * @param billing_text
	 * @param transfer_price
	 * @param item_price
	 * @param date_created
	 */
	public Item(long tb_id, long chanel_id, long sku, long ean, int quantity, String billing_text,
			double transfer_price, double item_price, String date_created) {
		super();
		this.tb_id = tb_id;
		this.chanel_id = chanel_id;
		this.sku = sku;
		this.ean = ean;
		this.quantity = quantity;
		this.billing_text = billing_text;
		this.transfer_price = transfer_price;
		this.item_price = item_price;
		this.date_created = date_created;
	}
	public long getTb_id() {
		return tb_id;
	}
	public void setTb_id(long tb_id) {
		this.tb_id = tb_id;
	}
	public long getChanel_id() {
		return chanel_id;
	}
	public void setChanel_id(long chanel_id) {
		this.chanel_id = chanel_id;
	}
	public long getSku() {
		return sku;
	}
	public void setSku(long sku) {
		this.sku = sku;
	}
	public long getEan() {
		return ean;
	}
	public void setEan(long ean) {
		this.ean = ean;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getBilling_text() {
		return billing_text;
	}
	public void setBilling_text(String billing_text) {
		this.billing_text = billing_text;
	}
	public double getTransfer_price() {
		return transfer_price;
	}
	public void setTransfer_price(double transfer_price) {
		this.transfer_price = transfer_price;
	}
	public double getItem_price() {
		return item_price;
	}
	public void setItem_price(double item_price) {
		this.item_price = item_price;
	}
	public String getDate_created() {
		return date_created;
	}
	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}
	
	
}
