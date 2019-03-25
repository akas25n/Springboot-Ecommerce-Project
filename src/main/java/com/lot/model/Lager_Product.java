package com.lot.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="lager_product")
public class Lager_Product {
	
	//these will come from lot_lager table
	@Id
	long ean;
	String articleNumber;
	String color;
	String size;
	String quantity;
	Long price;
	//these will come from product table
	
	String productName;
	String brand;
	String targetGroup;
	
	@Column(columnDefinition="LONGTEXT")
	String productText;
	String productMaterial;
	
	String articleImage1;
	String articleImage2;
	String articleImage3;
	String articleImage4;
	String articleImage5;
	
	@ManyToMany(mappedBy="productList", fetch= FetchType.EAGER)
	@JsonIgnore
	private Set<Lot> lot;
	
	
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public Set<Lot> getLot() {
		return lot;
	}
	public void setLot(Set<Lot> lot) {
		this.lot = lot;
	}
	public long getEan() {
		return ean;
	}
	public void setEan(long ean) {
		this.ean = ean;
	}
	public String getArticleNumber() {
		return articleNumber;
	}
	public void setArticleNumber(String articleNumber) {
		this.articleNumber = articleNumber;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getTargetGroup() {
		return targetGroup;
	}
	public void setTargetGroup(String targetGroup) {
		this.targetGroup = targetGroup;
	}
	public String getProductText() {
		return productText;
	}
	public void setProductText(String productText) {
		this.productText = productText;
	}
	public String getProductMaterial() {
		return productMaterial;
	}
	public void setProductMaterial(String productMaterial) {
		this.productMaterial = productMaterial;
	}
	public String getArticleImage1() {
		return articleImage1;
	}
	public void setArticleImage1(String articleImage1) {
		this.articleImage1 = articleImage1;
	}
	public String getArticleImage2() {
		return articleImage2;
	}
	public void setArticleImage2(String articleImage2) {
		this.articleImage2 = articleImage2;
	}
	public String getArticleImage3() {
		return articleImage3;
	}
	public void setArticleImage3(String articleImage3) {
		this.articleImage3 = articleImage3;
	}
	public String getArticleImage4() {
		return articleImage4;
	}
	public void setArticleImage4(String articleImage4) {
		this.articleImage4 = articleImage4;
	}
	public String getArticleImage5() {
		return articleImage5;
	}
	public void setArticleImage5(String articleImage5) {
		this.articleImage5 = articleImage5;
	}
	
	
}
