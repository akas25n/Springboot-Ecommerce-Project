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

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="lot")
public class Lot {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	@Column(name =" lot_Id")
	private long lotId;
	
	
	@NotNull
	@Column(name = "Lot_Name", length = 255)
	private String lotName;
	
	@NotNull
	@Column(name = "Lot_Description", columnDefinition="LONGTEXT")
	private String lotDescription;
	
	@NotNull
	@Column(name="Lot_Price")
	private double lotPrice;
	


	@NotNull
	@Column(name = "lot_status")
	private int lot_status;
	
	@Column
	private String createdAt;
	
	@Column(name="volume")
	private int volume;
	
	@Column(name= "Teaser_Image", columnDefinition = "LONGTEXT", nullable=true)
	private String teaserImage;

	
	@Column(name="Created_At")
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;	
	}
	
	
	
	//@JoinTbale..is the owning entity side in many to many association..so Lot entity is the owning side of the relationship
	//@mappedBy is the inverse side...so product is the inverse side
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)  
	@JoinTable(name = "lot_product", joinColumns=@JoinColumn(name ="lot_id_fk", referencedColumnName="lot_id"), inverseJoinColumns=@JoinColumn(name ="product_a_ean_fk", referencedColumnName="a_ean"))
	@JsonIgnore
	private Set<Product> productList;
	


	public Lot() {
		super();
	}


	public Lot(@NotNull long lotId, @NotNull String lotName, @NotNull String lotDescription, @NotNull double lotPrice,
			@NotNull int lot_status, String createdAt, String teaserImage, Set<Product> productList) {
		super();
		this.lotId = lotId;
		this.lotName = lotName;
		this.lotDescription = lotDescription;
		this.lotPrice = lotPrice;
		this.lot_status = lot_status;
		this.createdAt = createdAt;
		this.teaserImage = teaserImage;
		this.productList = productList;
		volume = 0;
		
	}


	public int getVolume() {
		return volume;
	}


	public void setVolume(int volume) {
		this.volume = volume;
	}


	public long getLotId() {
		return lotId;
	}


	public void setLotId(long lotId) {
		this.lotId = lotId;
	}


	public String getLotName() {
		return lotName;
	}


	public void setLotName(String lotName) {
		this.lotName = lotName;
	}


	public String getLotDescription() {
		return lotDescription;
	}


	public void setLotDescription(String lotDescription) {
		this.lotDescription = lotDescription;
	}


	public double getLotPrice() {
		return lotPrice;
	}


	public void setLotPrice(double lotPrice) {
		this.lotPrice = lotPrice;
	}


	public int getLot_status() {
		return lot_status;
	}


	public void setLot_status(int lot_status) {
		this.lot_status = lot_status;
	}


	public String getTeaserImage() {
		return teaserImage;
	}


	public void setTeaserImage(String teaserImage) {
		this.teaserImage = teaserImage;
	}


	public Set<Product> getProductList() {
		return productList;
	}


	public void setProductList(Set<Product> productList) {
		this.productList = productList;
	}





	public String getCreatedAt() {
		return createdAt;
	}
	
//	@ManyToMany(fetch=FetchType.EAGER)
//	private Set<User> userList;
//	
//	@OneToOne(fetch=FetchType.EAGER,
//			cascade=CascadeType.ALL,
//			mappedBy="lot")
//	private Order order;
//	

	
	
	
}
