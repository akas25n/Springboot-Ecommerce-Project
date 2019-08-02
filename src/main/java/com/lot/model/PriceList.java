package com.lot.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="lot_price_list")
public class PriceList {
	
	@Id
	@GeneratedValue
	@NotNull
	private int id;
	
	@NotNull
	private long ean;
	
	private double price;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="lot_id")
	@NotFound(action = NotFoundAction.IGNORE)
	@JsonIgnore
	private Lot lot;

	/**
	 * @param id
	 * @param ean
	 * @param price
	 * @param lot
	 */
	public PriceList(@NotNull int id, @NotNull long ean, double price, Lot lot) {
		super();
		this.id = id;
		this.ean = ean;
		this.price = price;
		this.lot = lot;
	}

	/**
	 * 
	 */
	public PriceList() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getEan() {
		return ean;
	}

	public void setEan(long ean) {
		this.ean = ean;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Lot getLot() {
		return lot;
	}

	public void setLot(Lot lot) {
		this.lot = lot;
	}
	

}
