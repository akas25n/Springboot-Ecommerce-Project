package com.lot.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="lot_lager")
public class Lot_Lager {
	
	@Id
	private long EAN;
	private String ART_NR = null;
	private String GROESSE = null;
	private String FARBE = null;
	private int BESTAND;
	
	private double UVP;
	private double PREIS;
	
	
	public double getUVP() {
		return UVP;
	}
	public void setUVP(double uVP) {
		UVP = uVP * BESTAND;
	}
	public double getPREIS() {
		return PREIS;
	}
	public void setPREIS(double pREIS) {
		PREIS = pREIS * BESTAND;
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
	public String getGROESSE() {
		return GROESSE;
	}
	public void setGROESSE(String gROESSE) {
		GROESSE = gROESSE;
	}
	public String getFARBE() {
		return FARBE;
	}
	public void setFARBE(String fARBE) {
		FARBE = fARBE;
	}

	public int getBESTAND() {
		return BESTAND;
	}
	public void setBESTAND(int bESTAND) {
		BESTAND = bESTAND;
	}

	

}
