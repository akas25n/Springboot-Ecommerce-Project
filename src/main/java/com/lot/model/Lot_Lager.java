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
	private String LAGERPLATZ = null;
	private String BESTAND = null;
	private String RESERVIERT = null;
	private long PREIS;
	
	public long getPREIS() {
		return PREIS;
	}
	public void setPREIS(long pREIS) {
		PREIS = pREIS;
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
	public String getLAGERPLATZ() {
		return LAGERPLATZ;
	}
	public void setLAGERPLATZ(String lAGERPLATZ) {
		LAGERPLATZ = lAGERPLATZ;
	}
	public String getBESTAND() {
		return BESTAND;
	}
	public void setBESTAND(String bESTAND) {
		BESTAND = bESTAND;
	}
	public String getRESERVIERT() {
		return RESERVIERT;
	}
	public void setRESERVIERT(String rESERVIERT) {
		RESERVIERT = rESERVIERT;
	}
	
	

}
