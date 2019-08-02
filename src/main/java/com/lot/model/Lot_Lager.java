package com.lot.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="lot_lager")
public class Lot_Lager {
	
	@Id
	private long EAN;
	private String ART_NR;
	private String FARBE;
	private String GROESSE;
	//--------------------------------------
	private String PROD_NAME;
	//--------------------------------------
	private int BESTAND;
	private double PREIS;
	private double UVP;
	
	//------------------------------------
	private String BRAND;
	private String GENDER;
	private String PROD_MATERIAL;
	@Column(columnDefinition="LONGTEXT")
	private String PROD_TEXT;
	private String IMAGE_1;
	private String IMAGE_2;
	private String IMAGE_3;
	//------------------------------------
	
	private String ANGEBOT_NR;
	
	/*
	 * @ManyToMany(mappedBy="lagerProductList", fetch= FetchType.LAZY)
	 * 
	 * @JsonIgnore private Set<Lot> lot;
	 */
	

	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="lot_id")
	@NotFound(action = NotFoundAction.IGNORE)
	@JsonIgnore
	private Lot lot;
	
	
	/*
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)  
	@JoinTable(name = "lot_lager_product", joinColumns=@JoinColumn(name ="lager_ean_fk", referencedColumnName="EAN"), inverseJoinColumns=@JoinColumn(name ="lot_id_fk", referencedColumnName="lot_id"))
	@JsonIgnore
	private List<Lot> lagerProductList;
	
	*/
	
	public Lot_Lager() {

	}


	/**
	 * @param eAN
	 * @param aRT_NR
	 * @param fARBE
	 * @param gROESSE
	 * @param pROD_NAME
	 * @param bESTAND
	 * @param pREIS
	 * @param uVP
	 * @param bRAND
	 * @param gENDER
	 * @param pROD_MATERIAL
	 * @param pROD_TEXT
	 * @param iMAGE_1
	 * @param iMAGE_2
	 * @param iMAGE_3
	 * @param aNGEBOT_NR
	 * @param lot
	 */
	public Lot_Lager(long eAN, String aRT_NR, String fARBE, String gROESSE, String pROD_NAME, int bESTAND, double pREIS,
			double uVP, String bRAND, String gENDER, String pROD_MATERIAL, String pROD_TEXT, String iMAGE_1,
			String iMAGE_2, String iMAGE_3, String aNGEBOT_NR, Lot lot) {
		super();
		EAN = eAN;
		ART_NR = aRT_NR;
		FARBE = fARBE;
		GROESSE = gROESSE;
		PROD_NAME = pROD_NAME;
		BESTAND = bESTAND;
		PREIS = pREIS;
		UVP = uVP;
		BRAND = bRAND;
		GENDER = gENDER;
		PROD_MATERIAL = pROD_MATERIAL;
		PROD_TEXT = pROD_TEXT;
		IMAGE_1 = iMAGE_1;
		IMAGE_2 = iMAGE_2;
		IMAGE_3 = iMAGE_3;
		ANGEBOT_NR = aNGEBOT_NR;
		this.lot = lot;
	}


	public long getEAN() {
		return EAN;
	}


	public void setEAN(long eAN) {
		EAN = eAN;
	}


	public String getART_NR() {
		return ART_NR;
	}


	public void setART_NR(String aRT_NR) {
		ART_NR = aRT_NR;
	}


	public String getFARBE() {
		return FARBE;
	}


	public void setFARBE(String fARBE) {
		FARBE = fARBE;
	}


	public String getGROESSE() {
		return GROESSE;
	}


	public void setGROESSE(String gROESSE) {
		GROESSE = gROESSE;
	}


	public String getPROD_NAME() {
		return PROD_NAME;
	}


	public void setPROD_NAME(String pROD_NAME) {
		PROD_NAME = pROD_NAME;
	}


	public int getBESTAND() {
		return BESTAND;
	}


	public void setBESTAND(int bESTAND) {
		BESTAND = bESTAND;
	}


	public double getPREIS() {
		return PREIS;
	}


	public void setPREIS(double pREIS) {
		PREIS = pREIS;
	}


	public double getUVP() {
		return UVP;
	}


	public void setUVP(double uVP) {
		UVP = uVP;
	}


	public String getBRAND() {
		return BRAND;
	}


	public void setBRAND(String bRAND) {
		BRAND = bRAND;
	}


	public String getGENDER() {
		return GENDER;
	}


	public void setGENDER(String gENDER) {
		GENDER = gENDER;
	}


	public String getPROD_MATERIAL() {
		return PROD_MATERIAL;
	}


	public void setPROD_MATERIAL(String pROD_MATERIAL) {
		PROD_MATERIAL = pROD_MATERIAL;
	}


	public String getPROD_TEXT() {
		return PROD_TEXT;
	}


	public void setPROD_TEXT(String pROD_TEXT) {
		PROD_TEXT = pROD_TEXT;
	}


	public String getIMAGE_1() {
		return IMAGE_1;
	}


	public void setIMAGE_1(String iMAGE_1) {
		IMAGE_1 = iMAGE_1;
	}


	public String getIMAGE_2() {
		return IMAGE_2;
	}


	public void setIMAGE_2(String iMAGE_2) {
		IMAGE_2 = iMAGE_2;
	}


	public String getIMAGE_3() {
		return IMAGE_3;
	}


	public void setIMAGE_3(String iMAGE_3) {
		IMAGE_3 = iMAGE_3;
	}


	public String getANGEBOT_NR() {
		return ANGEBOT_NR;
	}


	public void setANGEBOT_NR(String aNGEBOT_NR) {
		ANGEBOT_NR = aNGEBOT_NR;
	}


	public Lot getLot() {
		return lot;
	}


	public void setLot(Lot lot) {
		this.lot = lot;
	}
	
	
	
}
