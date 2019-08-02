package com.lot.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="non_existing_product")
public class NonExistingProduct {
	
	@Id
	private long ean;
	
	private int quantity;

	/**
	 * 
	 */
	public NonExistingProduct() {
		super();
	}

	/**
	 * @param ean
	 * @param quantity
	 */
	public NonExistingProduct(long ean, int quantity) {
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
