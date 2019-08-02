package com.lot.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="lot_product_quantity")
public class ProductQuantity {
	
	@Id
	private long ean;
	
	private int quantity;

	/**
	 * 
	 */
	public ProductQuantity() {
		super();
	}

	/**
	 * @param ean
	 * @param quantity
	 */
	public ProductQuantity(long ean, int quantity) {
		super();
		this.ean = ean;
		this.quantity = quantity;
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
	
}
