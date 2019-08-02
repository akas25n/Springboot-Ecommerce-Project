package com.lot.model;

import java.util.ArrayList;
import java.util.List;

public class CustomizedProduct {

	private List<Lot_Lager> product = new ArrayList<Lot_Lager>();
	private String article_number;
	private double UVP;
	private double PRICE;
	private String IMAGE;
	private String PROD_MATERIAL;
	private String PROD_TEXT;
	private String BRAND;
	private int BESTAND;
	private String GENDER;
	private long ean;
	
	public CustomizedProduct() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public long getEan() {
		return ean;
	}



	public void setEan(long ean) {
		this.ean = ean;
	}



	public int getBESTAND() {
		return BESTAND;
	}

	public void setBESTAND(int bESTAND) {
		BESTAND = bESTAND;
	}

	public void setGENDER(String gender2) {
		GENDER = gender2;
		
	}

	public String getGENDER() {
		return GENDER;
	}

	public String getBRAND() {
		return BRAND;
	}

	public void setBRAND(String brand2) {
		BRAND = brand2;
		
	}

	public String getPROD_TEXT() {
		return PROD_TEXT;
	}


	public void setPROD_TEXT(String pROD_TEXT) {
		PROD_TEXT = pROD_TEXT;
	}


	public String getIMAGE() {
		return IMAGE;
	}


	public void setIMAGE(String iMAGE) {
		IMAGE = iMAGE;
	}


	public String getPROD_MATERIAL() {
		return PROD_MATERIAL;
	}


	public void setPROD_MATERIAL(String pROD_MATERIAL) {
		PROD_MATERIAL = pROD_MATERIAL;
	}


	public double getUVP() {
		return UVP;
	}


	public void setUVP(double uVP) {
		UVP = uVP;
	}


	public double getPRICE() {
		return PRICE;
	}


	public void setPRICE(double pRICE) {
		PRICE = pRICE;
	}


	public List<Lot_Lager> getProduct() {
		return product;
	}

	public void setProduct(List<Lot_Lager> product) {
		this.product = product;
	}

	public String getArticle_number() {
		return article_number;
	}
	public void setArticle_number(String article_number) {
		this.article_number = article_number;
	}


	
}
