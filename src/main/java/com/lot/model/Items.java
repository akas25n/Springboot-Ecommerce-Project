package com.lot.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="items")
public class Items {

	List<Item> item;

	/**
	 * @param item
	 */
	public Items(List<Item> item) {
		super();
		this.item = item;
	}

	/**
	 * 
	 */
	public Items() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<Item> getItem() {
		return item;
	}

	public void setItem(List<Item> item) {
		this.item = item;
	}
	
	
	
}