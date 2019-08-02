package com.lot.model;

import java.util.List;
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
@Table(name="lot_table")
public class Lot {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	@Column(name =" lot_Id")
	private long lotId;
	
	
	@NotNull
	@Column(name = "Lot_Name", length = 30)
	private String lotName;
	
	@NotNull
	@Column(name = "Lot_Description", columnDefinition="LONGTEXT")
	private String lotDescription;
	
	@NotNull
	@Column(name="Lot_Price")
	private double lotPrice;
	
	
	@Column(name="actual_Price")
	private double actualPrice;

	@NotNull
	@Column(name = "lot_status")
	private int lot_status;
	
	@Column
	private String createdAt;
	
	@Column(name="volume")
	private int volume;
	
	@Column(name= "Teaser_Image", columnDefinition = "LONGTEXT", nullable=true)
	private String teaserImage;
	
	//for dropdown filter menu
	@NotNull
	@Column(name="brand")
	private String brand;
	@NotNull
	@Column(name="gender")
	private String gender;
	@NotNull
	@Column(name="category")
	private String category;
	
	@NotNull
	@Column(name="seasons")
	String seasons;
		
		
	
	double totalDiscount;
	double averageBuyingPrice;
	double averageRetailPrice;
	double percentageOfDiscountOfRetailPrice;
	int numberOfSKU;
	double averageQuantityPerSKU;
	
	
	

	
	@Column(name="Created_At")
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;	
	}
	
	//@JoinTbale..is the owning entity side in many to many association..so Lot entity is the owning side of the relationship
	//@mappedBy is the inverse side...so product is the inverse side
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)  
	@JoinTable(name = "lot_product", joinColumns=@JoinColumn(name ="lot_id_fk", referencedColumnName="lot_id"), inverseJoinColumns=@JoinColumn(name ="product_a_ean_fk", referencedColumnName="EAN"))
	@JsonIgnore
	private List<Stocklots_Offer> productList;
	
	
	/*
	 * @ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	 * 
	 * @JoinTable(name = "lot_lager_product", joinColumns=@JoinColumn(name
	 * ="lot_id_fk", referencedColumnName="lot_id"),
	 * inverseJoinColumns=@JoinColumn(name ="lager_ean_fk",
	 * referencedColumnName="EAN"))
	 * 
	 * @JsonIgnore private List<Lot_Lager> lagerProductList;
	 */
	
	public Lot() {
		super();
	}

	

	

	/*
	 * public List<Lot_Lager> getLagerProductList() { return lagerProductList; }
	 * 
	 * 
	 * 
	 * public void setLagerProductList(List<Lot_Lager> lagerProductList) {
	 * this.lagerProductList = lagerProductList; }
	 */


	/**
	 * @param lotId
	 * @param lotName
	 * @param lotDescription
	 * @param lotPrice
	 * @param actualPrice
	 * @param lot_status
	 * @param createdAt
	 * @param volume
	 * @param teaserImage
	 * @param totalDiscount
	 * @param averageBuyingPrice
	 * @param averageRetailPrice
	 * @param percentageOfDiscountOfRetailPrice
	 * @param numberOfSKU
	 * @param averageQuantityPerSKU
	 * @param brand
	 * @param gender
	 * @param category
	 * @param productList
	 */
	public Lot(@NotNull long lotId, @NotNull String lotName, @NotNull String lotDescription, @NotNull double lotPrice,
			double actualPrice, @NotNull int lot_status, String createdAt, int volume, String teaserImage,
			double totalDiscount, double averageBuyingPrice, double averageRetailPrice,
			double percentageOfDiscountOfRetailPrice, int numberOfSKU, double averageQuantityPerSKU, String brand,
			String gender, String category, String seasons, List<Stocklots_Offer> productList) {
		super();
		this.lotId = lotId;
		this.lotName = lotName;
		this.lotDescription = lotDescription;
		this.lotPrice = lotPrice;
		this.actualPrice = actualPrice;
		this.lot_status = lot_status;
		this.createdAt = createdAt;
		this.volume = volume;
		this.teaserImage = teaserImage;
		this.totalDiscount = totalDiscount;
		this.averageBuyingPrice = averageBuyingPrice;
		this.averageRetailPrice = averageRetailPrice;
		this.percentageOfDiscountOfRetailPrice = percentageOfDiscountOfRetailPrice;
		this.numberOfSKU = numberOfSKU;
		this.averageQuantityPerSKU = averageQuantityPerSKU;
		this.brand = brand;
		this.gender = gender;
		this.category = category;
		this.productList = productList;
		this.seasons = seasons;
	}

	
	public String getSeasons() {
		return seasons;
	}

	public void setSeasons(String seasons) {
		this.seasons = seasons;
	}

	public double getTotalDiscount() {
		return totalDiscount;
	}

	public void setTotalDiscount(double totalDiscount) {
		this.totalDiscount = totalDiscount;
	}

	public double getAverageBuyingPrice() {
		return averageBuyingPrice;
	}

	public void setAverageBuyingPrice(double averageBuyingPrice) {
		this.averageBuyingPrice = averageBuyingPrice;
	}

	public double getAverageRetailPrice() {
		return averageRetailPrice;
	}

	public void setAverageRetailPrice(double averageRetailPrice) {
		this.averageRetailPrice = averageRetailPrice;
	}

	public double getPercentageOfDiscountOfRetailPrice() {
		return percentageOfDiscountOfRetailPrice;
	}

	public void setPercentageOfDiscountOfRetailPrice(double percentageOfDiscountOfRetailPrice) {
		this.percentageOfDiscountOfRetailPrice = percentageOfDiscountOfRetailPrice;
	}

	public int getNumberOfSKU() {
		return numberOfSKU;
	}

	public void setNumberOfSKU(int numberOfSKU) {
		this.numberOfSKU = numberOfSKU;
	}

	public double getAverageQuantityPerSKU() {
		return averageQuantityPerSKU;
	}

	public void setAverageQuantityPerSKU(double averageQuantityPerSKU) {
		this.averageQuantityPerSKU = averageQuantityPerSKU;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public double getActualPrice() {
		return actualPrice;
	}

	public void setActualPrice(double actualPrice) {
		this.actualPrice = actualPrice;
	}

	public int getLot_status() {
		return lot_status;
	}

	public void setLot_status(int lot_status) {
		this.lot_status = lot_status;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public String getTeaserImage() {
		return teaserImage;
	}

	public void setTeaserImage(String teaserImage) {
		this.teaserImage = teaserImage;
	}

	public List<Stocklots_Offer> getProductList() {
		return productList;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setProductList(List<Stocklots_Offer> productList) {
		
		this.productList = productList;
	}

	
}
