package com.lot.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name ="product")
public class Product {
	
	
	
	/*private long a_id;
	private long p_id;
	private String p_nr=null;
	private String a_stock=null;
	private String a_nr=null;
	private String a_comp_farbe_=null;
	private String a_comp_groesse_=null;
	private String a_prodnr=null;
	private String a_active=null;
	private String a_media_image_5_=null;
	*/
	
	@Id
	private long a_ean;
	private String p_name=null;
	
	@Column(columnDefinition="LONGTEXT")
	private String p_text=null;
	
	private String p_brand=null;
	
	private String a_media_image_0_=null;
	private String a_media_image_1_=null;
	private String a_media_image_2_=null;
	private String a_media_image_3_=null;
	private String a_media_image_4_=null;
	private double a_uvp_cuglago_1_;//retail price
	
	
	private String p_comp_material_=null;
	
	private String p_comp_zielgruppe_=null;
	/*
		//@ManyToMany(mappedBy="productList", fetch= FetchType.EAGER)
		@ManyToMany(mappedBy="productList", fetch= FetchType.EAGER)
		@JsonIgnore
		private Set<Lot> lot;

		public String getA_nr() {
			return a_nr;
		}

		public void setA_nr(String a_nr) {
			this.a_nr = a_nr;
		}*/

		public long getA_ean() {
			return a_ean;
		}

		public void setA_ean(long a_ean) {
			this.a_ean = a_ean;
		}
		
		
/*		public long getA_id() {
			return a_id;
		}

		public void setA_id(long a_id) {
			this.a_id = a_id;
		}

		public long getP_id() {
			return p_id;
		}

		public void setP_id(long p_id) {
			this.p_id = p_id;
		}

		public String getP_nr() {
			return p_nr;
		}

		public void setP_nr(String p_nr) {
			this.p_nr = p_nr;
		}*/

		public Double getA_uvp_cuglago_1_() {
			return a_uvp_cuglago_1_;
		}

		public void setA_uvp_cuglago_1_(Double a_uvp_cuglago_1_) {
			this.a_uvp_cuglago_1_ = a_uvp_cuglago_1_;
		}

		public String getP_name() {
			return p_name;
		}

		public void setP_name(String p_name) {
			this.p_name = p_name;
		}

		public String getP_text() {
			return p_text;
		}

		public void setP_text(String p_text) {
			this.p_text = p_text;
		}

	/*public String getA_active() {
			return a_active;
		}

		public void setA_active(String a_active) {
			this.a_active = a_active;
		}*/

		public String getP_brand() {
			return p_brand;
		}

		public void setP_brand(String p_brand) {
			this.p_brand = p_brand;
		}

		/*public String getA_prodnr() {
			return a_prodnr;
		}

		public void setA_prodnr(String a_prodnr) {
			this.a_prodnr = a_prodnr;
		}

		public String getA_comp_farbe_() {
			return a_comp_farbe_;
		}

		public void setA_comp_farbe_(String a_comp_farbe_) {
			this.a_comp_farbe_ = a_comp_farbe_;
		}

		public String getA_comp_groesse_() {
			return a_comp_groesse_;
		}

		public void setA_comp_groesse_(String a_comp_groesse_) {
			this.a_comp_groesse_ = a_comp_groesse_;
		}*/

		public String getA_media_image_0_() {
			return a_media_image_0_;
		}

		public void setA_media_image_0_(String a_media_image_0_) {
			this.a_media_image_0_ = a_media_image_0_;
		}

		public String getA_media_image_1_() {
			return a_media_image_1_;
		}

		public void setA_media_image_1_(String a_media_image_1_) {
			this.a_media_image_1_ = a_media_image_1_;
		}

		public String getA_media_image_2_() {
			return a_media_image_2_;
		}

		public void setA_media_image_2_(String a_media_image_2_) {
			this.a_media_image_2_ = a_media_image_2_;
		}

		public String getA_media_image_3_() {
			return a_media_image_3_;
		}

		public void setA_media_image_3_(String a_media_image_3_) {
			this.a_media_image_3_ = a_media_image_3_;
		}

		public String getA_media_image_4_() {
			return a_media_image_4_;
		}

		public void setA_media_image_4_(String a_media_image_4_) {
			this.a_media_image_4_ = a_media_image_4_;
		}
		
		/*
		public String getA_media_image_5_() {
			return a_media_image_5_;
		}

		public void setA_media_image_5_(String a_media_image_5_) {
			this.a_media_image_5_ = a_media_image_5_;
		}

		public String getA_stock() {
			return a_stock;
		}

		public void setA_stock(String a_stock) {
			this.a_stock = a_stock;
		}
*/
		public String getP_comp_material_() {
			return p_comp_material_;
		}

		public void setP_comp_material_(String p_comp_material_) {
			this.p_comp_material_ = p_comp_material_;
		}

		public String getP_comp_zielgruppe_() {
			return p_comp_zielgruppe_;
		}

		public void setP_comp_zielgruppe_(String p_comp_zielgruppe_) {
			this.p_comp_zielgruppe_ = p_comp_zielgruppe_;
		}

		/*public Set<Lot> getLot() {
			return lot;
		}

		public void setLot(Set<Lot> lot) {
			this.lot = lot;
		}*/
		
		
		
		
// private String a_nr2=null;
//private String p_prefix=null;	
//	private String p_name_proper=null;
//	private String p_name_keyword=null;
//	private String p_supplement=null;	
//	private String p_feature=null;
//	private String p_comp_absatzart_=null;
//	private String p_comp_absatzhöhe=null;
//	private String p_comp_altersgruppe_=null;
//	private String p_comp_anzahl_schichten_=null;
//	private String p_comp_aermelart_=null;
//	private String p_comp_aermellaenge_=null;
//	private String p_comp_Artikelnummer_=null;
//	private String p_comp_atmungsaktivitaet_=null;
//	private String p_comp_Ausschnitt_=null;
//	private String p_comp_beininnenlaenge_=null;
//	private String p_comp_Beinlänge_=null;
//	private String p_comp_bh_bügel_=null;
//	private String p_comp_bh_schale_=null;
//	private String p_comp_bh_style_=null;
//	private String p_comp_bh_traegerart_=null;
//	private String p_comp_breite_=null;
//	private String p_comp_buegellaenge_=null;
//	private String p_comp_cluster_=null;
//	private String p_comp_decksohle_=null;
//	private String p_comp_decksohle_schuhe_=null;
//	private String p_comp_details_=null;
//	private String p_comp_durchmesser_=null;
//	private String p_comp_farbe_=null;
//	private String p_comp_form_=null;
//	private String p_comp_fransen_=null;
//	private String p_comp_funktionen_=null;
//	private String p_comp_futter_=null;
//	private String p_comp_gesamtlaenge_=null;
//	private String p_comp_gesamtlaengexxl_=null;
//	private String p_comp_glasbreite_=null;
//	private String p_comp_glashoehe_=null;
//	private String p_comp_groesse_=null;
//	private String p_comp_groessenflag_=null;
//	private String p_comp_strap_length_=null;
//	private String p_comp_hemdkragen_=null;
//	private String p_comp_Herstellungsland_und_region_=null;
//	private String p_comp_hoehe_=null;
//	private String p_comp_jeans_waschung_=null;
//	private String p_comp_kapuze_=null;
//	private String p_comp_koerbchengroesse_=null;
//	private String p_comp_kragen_=null;
//	private String p_comp_lagen_=null;
//	private String p_comp_länge_=null;
//	private String p_comp_laenge_=null;
//	private String p_comp_laufsohle_schuhe_=null;
//	private String p_comp_leibhoehe_=null;
//	private String p_comp_Linsentechnik_=null;
//	private String p_comp_Marke_=null;	
//	private String p_comp_material_aermelfutter_=null;
//	private String p_comp_material_aermeloberstoff_=null;
//	private String p_comp_material_fuellung_=null;
//	private String p_comp_material_innenjacke_=null;
//	private String p_comp_material_kontrast_=null;
//	private String p_comp_material_oberstoff_=null;
//	private String p_comp_material_rippe_=null;
//	private String p_comp_material_webpelz_=null;
//	private String p_comp_material_eigenschaft_=null;
//	private String p_comp_muster_=null;
//	private String p_comp_obermaterial_schuhe_=null;
//	private String p_comp_oberteillänge_=null;
//	private String p_comp_ohrring_stil_=null;
//	private String p_comp_passform_=null;
//	private String p_comp_pflegehinweise_=null;
//	private String p_comp_plateauhoehe_=null;
//	private String p_comp_produkttyp_=null;
//	private String p_comp_Rahmenmaterial_=null;
//	private String p_comp_rock_stil_=null;
//	private String p_comp_bag_size_=null;
//	private String p_comp_saison_=null;
//	private String p_comp_saum_=null;
//	private String p_comp_schichten_=null;
//	private String p_comp_schnitt_=null;
//	private String p_comp_schuhspitze_=null;
//	private String p_comp_Schutz_=null;
//	private String p_comp_sohle_=null;
//	private String p_comp_sportart_=null;
//	private String p_comp_stegbreite_=null;
//	private String p_comp_Stil_=null;
//	private String p_comp_Stil_Hose_=null;
//	private String p_comp_Stil_Jacken_Mäntel_=null;
//	private String p_comp_stil_jeans_=null;
//	private String p_comp_stil_srtick_=null;
//	private String p_comp_Stil_Strick_=null;
//	private String p_comp_taschen_=null;
//	private String p_comp_thema_=null;
//	private String p_comp_tiefe_=null;
//	private String p_comp_traeger_=null;
//	private String p_comp_trägerart_=null;
//	private String p_comp_umfang_=null;
//	private String p_comp_unterteillaenge_=null;
//	private String p_comp_ursprungsland_=null;
//	private String p_comp_vek_=null;
//	private String p_comp_vek_online_=null;
//	private String p_comp_verschluss_=null;
//	private String p_comp_volumen_=null;
//	private String p_comp_waisthoehe_=null;
//	private String p_comp_Wasserdichtigkeit_=null;
//	private String p_comp_wassersaeule_=null;
//	private String p_comp_Winddichtigkeit_=null;


//	private String p_active_lade_=null;
//	private String p_active_cuglago1_=null;
//	private String p_catpri_cuglago1_=null;
//	private String p_catsec_cuglago1_0_=null;
//	private String p_bullet_0_=null;
//	private String p_bullet_1_=null;
//	private String p_bullet_2_=null;
//	private String p_bullet_3_=null;
//	private String p_bullet_4_=null;
//	private String p_bullet_5_=null;
//	private String p_bullet_6_=null;
//	private String p_bullet_7_=null;
//	private String p_bullet_8_=null;
//	private String p_bullet_9_=null;
//	private String p_bullet_10_=null;
//	private String p_bullet_11_=null;
//	private String p_bullet_12_=null;
//	private String p_bullet_13_=null;
//	private String p_bullet_14_=null;
//	private String p_bullet_15_=null;
//	private String p_bullet_16_=null;
//	private String p_bullet_17_=null;
//	private String p_bullet_18_=null;
//	private String p_bullet_19_=null;
//	private String p_bullet_20_=null;
//	private String p_bullet_21_=null;
//	private String p_bullet_22_=null;
//	private String p_bullet_23_=null;
//	private String p_bullet_24_=null;
//	private String p_bullet_25_=null;
//	private String p_bullet_26_=null;
//	private String p_bullet_27_=null;
//	private String p_bullet_28_=null;
//	private String p_bullet_29_=null;
//	private String p_bullet_30_=null;
//	private String p_bullet_31_=null;
//	private String p_bullet_32_=null;
//	private String a_nr=null;	
//	private String Artikelbezeichnung=null;
//	private String a_comp_koerbchengroesse_=null;
//	private String a_comp_laenge_=null;
//	private String a_comp_otto_weite_=null;
//	private String ap_comp_ebay_produkt_name_=null;
//	private String ap_comp_ursprungsland_=null;
//	private String a_media_image_6_=null;
//	private String a_media_image_7_=null;
//	private String a_media_image_8_=null;
//	private String a_media_image_9_=null;
//	private String a_media_image_10_=null;
//	private String a_media_image_11_=null;
//	private String a_media_image_12_=null;
//	private String a_media_image_13_=null;
//	private String a_media_image_14_=null;
//	private String a_media_image_15_=null;
//	private String a_media_image_16_=null;
//	private String a_media_image_17_=null;
//	private String a_media_image_18_=null;
//	private String a_media_image_19_=null;
//	private String a_media_image_20_=null;
//	private String a_media_image_21_=null;
//	private String a_media_image_22_=null;
//	private String a_media_image_23_=null;
//	private String a_media_image_24_=null;
//	private String a_media_image_25_=null;
//	private String a_ek=null;
//	
//	private String a_separate=null;
//	private String a_maxsep=null;
//	private String a_shipping_type=null;
//	private String a_width=null;
//	private String a_height=null;
//	private String a_weight=null;
//	private String a_length=null;
//	private String a_intrastat=null;
//	private String a_org_country=null;
	/*
	
	
	

	public long getA_ean() {
		return a_ean;
	}

	public void setA_ean(long a_ean) {
		this.a_ean = a_ean;
	}

	public long getA_id() {
		return a_id;
	}

	public void setA_id(long a_id) {
		this.a_id = a_id;
	}

	public long getP_id() {
		return p_id;
	}

	public void setP_id(long p_id) {
		this.p_id = p_id;
	}

	public String getP_nr() {
		return p_nr;
	}

	public void setP_nr(String p_nr) {
		this.p_nr = p_nr;
	}

	public String getP_prefix() {
		return p_prefix;
	}

	public void setP_prefix(String p_prefix) {
		this.p_prefix = p_prefix;
	}

	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	public String getP_name_proper() {
		return p_name_proper;
	}

	public void setP_name_proper(String p_name_proper) {
		this.p_name_proper = p_name_proper;
	}

	public String getP_name_keyword() {
		return p_name_keyword;
	}

	public void setP_name_keyword(String p_name_keyword) {
		this.p_name_keyword = p_name_keyword;
	}

	public String getP_supplement() {
		return p_supplement;
	}

	public void setP_supplement(String p_supplement) {
		this.p_supplement = p_supplement;
	}

	public String getP_text() {
		return p_text;
	}

	public void setP_text(String p_text) {
		this.p_text = p_text;
	}

	public String getP_feature() {
		return p_feature;
	}

	public void setP_feature(String p_feature) {
		this.p_feature = p_feature;
	}

	public String getP_comp_absatzart_() {
		return p_comp_absatzart_;
	}

	public void setP_comp_absatzart_(String p_comp_absatzart_) {
		this.p_comp_absatzart_ = p_comp_absatzart_;
	}

	public String getP_comp_absatzhöhe() {
		return p_comp_absatzhöhe;
	}

	public void setP_comp_absatzhöhe(String p_comp_absatzhöhe) {
		this.p_comp_absatzhöhe = p_comp_absatzhöhe;
	}

	public String getP_comp_altersgruppe_() {
		return p_comp_altersgruppe_;
	}

	public void setP_comp_altersgruppe_(String p_comp_altersgruppe_) {
		this.p_comp_altersgruppe_ = p_comp_altersgruppe_;
	}

	public String getP_comp_anzahl_schichten_() {
		return p_comp_anzahl_schichten_;
	}

	public void setP_comp_anzahl_schichten_(String p_comp_anzahl_schichten_) {
		this.p_comp_anzahl_schichten_ = p_comp_anzahl_schichten_;
	}

	public String getP_comp_aermelart_() {
		return p_comp_aermelart_;
	}

	public void setP_comp_aermelart_(String p_comp_aermelart_) {
		this.p_comp_aermelart_ = p_comp_aermelart_;
	}

	public String getP_comp_aermellaenge_() {
		return p_comp_aermellaenge_;
	}

	public void setP_comp_aermellaenge_(String p_comp_aermellaenge_) {
		this.p_comp_aermellaenge_ = p_comp_aermellaenge_;
	}

	public String getP_comp_Artikelnummer_() {
		return p_comp_Artikelnummer_;
	}

	public void setP_comp_Artikelnummer_(String p_comp_Artikelnummer_) {
		this.p_comp_Artikelnummer_ = p_comp_Artikelnummer_;
	}

	public String getP_comp_atmungsaktivitaet_() {
		return p_comp_atmungsaktivitaet_;
	}

	public void setP_comp_atmungsaktivitaet_(String p_comp_atmungsaktivitaet_) {
		this.p_comp_atmungsaktivitaet_ = p_comp_atmungsaktivitaet_;
	}

	public String getP_comp_Ausschnitt_() {
		return p_comp_Ausschnitt_;
	}

	public void setP_comp_Ausschnitt_(String p_comp_Ausschnitt_) {
		this.p_comp_Ausschnitt_ = p_comp_Ausschnitt_;
	}

	public String getP_comp_beininnenlaenge_() {
		return p_comp_beininnenlaenge_;
	}

	public void setP_comp_beininnenlaenge_(String p_comp_beininnenlaenge_) {
		this.p_comp_beininnenlaenge_ = p_comp_beininnenlaenge_;
	}

	public String getP_comp_Beinlänge_() {
		return p_comp_Beinlänge_;
	}

	public void setP_comp_Beinlänge_(String p_comp_Beinlänge_) {
		this.p_comp_Beinlänge_ = p_comp_Beinlänge_;
	}

	public String getP_comp_bh_bügel_() {
		return p_comp_bh_bügel_;
	}

	public void setP_comp_bh_bügel_(String p_comp_bh_bügel_) {
		this.p_comp_bh_bügel_ = p_comp_bh_bügel_;
	}

	public String getP_comp_bh_schale_() {
		return p_comp_bh_schale_;
	}

	public void setP_comp_bh_schale_(String p_comp_bh_schale_) {
		this.p_comp_bh_schale_ = p_comp_bh_schale_;
	}

	public String getP_comp_bh_style_() {
		return p_comp_bh_style_;
	}

	public void setP_comp_bh_style_(String p_comp_bh_style_) {
		this.p_comp_bh_style_ = p_comp_bh_style_;
	}

	public String getP_comp_bh_traegerart_() {
		return p_comp_bh_traegerart_;
	}

	public void setP_comp_bh_traegerart_(String p_comp_bh_traegerart_) {
		this.p_comp_bh_traegerart_ = p_comp_bh_traegerart_;
	}

	public String getP_comp_breite_() {
		return p_comp_breite_;
	}

	public void setP_comp_breite_(String p_comp_breite_) {
		this.p_comp_breite_ = p_comp_breite_;
	}

	public String getP_comp_buegellaenge_() {
		return p_comp_buegellaenge_;
	}

	public void setP_comp_buegellaenge_(String p_comp_buegellaenge_) {
		this.p_comp_buegellaenge_ = p_comp_buegellaenge_;
	}

	public String getP_comp_cluster_() {
		return p_comp_cluster_;
	}

	public void setP_comp_cluster_(String p_comp_cluster_) {
		this.p_comp_cluster_ = p_comp_cluster_;
	}

	public String getP_comp_decksohle_() {
		return p_comp_decksohle_;
	}

	public void setP_comp_decksohle_(String p_comp_decksohle_) {
		this.p_comp_decksohle_ = p_comp_decksohle_;
	}

	public String getP_comp_decksohle_schuhe_() {
		return p_comp_decksohle_schuhe_;
	}

	public void setP_comp_decksohle_schuhe_(String p_comp_decksohle_schuhe_) {
		this.p_comp_decksohle_schuhe_ = p_comp_decksohle_schuhe_;
	}

	public String getP_comp_details_() {
		return p_comp_details_;
	}

	public void setP_comp_details_(String p_comp_details_) {
		this.p_comp_details_ = p_comp_details_;
	}

	public String getP_comp_durchmesser_() {
		return p_comp_durchmesser_;
	}

	public void setP_comp_durchmesser_(String p_comp_durchmesser_) {
		this.p_comp_durchmesser_ = p_comp_durchmesser_;
	}

	public String getP_comp_farbe_() {
		return p_comp_farbe_;
	}

	public void setP_comp_farbe_(String p_comp_farbe_) {
		this.p_comp_farbe_ = p_comp_farbe_;
	}

	public String getP_comp_form_() {
		return p_comp_form_;
	}

	public void setP_comp_form_(String p_comp_form_) {
		this.p_comp_form_ = p_comp_form_;
	}

	public String getP_comp_fransen_() {
		return p_comp_fransen_;
	}

	public void setP_comp_fransen_(String p_comp_fransen_) {
		this.p_comp_fransen_ = p_comp_fransen_;
	}

	public String getP_comp_funktionen_() {
		return p_comp_funktionen_;
	}

	public void setP_comp_funktionen_(String p_comp_funktionen_) {
		this.p_comp_funktionen_ = p_comp_funktionen_;
	}

	public String getP_comp_futter_() {
		return p_comp_futter_;
	}

	public void setP_comp_futter_(String p_comp_futter_) {
		this.p_comp_futter_ = p_comp_futter_;
	}

	public String getP_comp_gesamtlaenge_() {
		return p_comp_gesamtlaenge_;
	}

	public void setP_comp_gesamtlaenge_(String p_comp_gesamtlaenge_) {
		this.p_comp_gesamtlaenge_ = p_comp_gesamtlaenge_;
	}

	public String getP_comp_gesamtlaengexxl_() {
		return p_comp_gesamtlaengexxl_;
	}

	public void setP_comp_gesamtlaengexxl_(String p_comp_gesamtlaengexxl_) {
		this.p_comp_gesamtlaengexxl_ = p_comp_gesamtlaengexxl_;
	}

	public String getP_comp_glasbreite_() {
		return p_comp_glasbreite_;
	}

	public void setP_comp_glasbreite_(String p_comp_glasbreite_) {
		this.p_comp_glasbreite_ = p_comp_glasbreite_;
	}

	public String getP_comp_glashoehe_() {
		return p_comp_glashoehe_;
	}

	public void setP_comp_glashoehe_(String p_comp_glashoehe_) {
		this.p_comp_glashoehe_ = p_comp_glashoehe_;
	}

	public String getP_comp_groesse_() {
		return p_comp_groesse_;
	}

	public void setP_comp_groesse_(String p_comp_groesse_) {
		this.p_comp_groesse_ = p_comp_groesse_;
	}

	public String getP_comp_groessenflag_() {
		return p_comp_groessenflag_;
	}

	public void setP_comp_groessenflag_(String p_comp_groessenflag_) {
		this.p_comp_groessenflag_ = p_comp_groessenflag_;
	}

	public String getP_comp_strap_length_() {
		return p_comp_strap_length_;
	}

	public void setP_comp_strap_length_(String p_comp_strap_length_) {
		this.p_comp_strap_length_ = p_comp_strap_length_;
	}

	public String getP_comp_hemdkragen_() {
		return p_comp_hemdkragen_;
	}

	public void setP_comp_hemdkragen_(String p_comp_hemdkragen_) {
		this.p_comp_hemdkragen_ = p_comp_hemdkragen_;
	}

	public String getP_comp_Herstellungsland_und_region_() {
		return p_comp_Herstellungsland_und_region_;
	}

	public void setP_comp_Herstellungsland_und_region_(String p_comp_Herstellungsland_und_region_) {
		this.p_comp_Herstellungsland_und_region_ = p_comp_Herstellungsland_und_region_;
	}

	public String getP_comp_hoehe_() {
		return p_comp_hoehe_;
	}

	public void setP_comp_hoehe_(String p_comp_hoehe_) {
		this.p_comp_hoehe_ = p_comp_hoehe_;
	}

	public String getP_comp_jeans_waschung_() {
		return p_comp_jeans_waschung_;
	}

	public void setP_comp_jeans_waschung_(String p_comp_jeans_waschung_) {
		this.p_comp_jeans_waschung_ = p_comp_jeans_waschung_;
	}

	public String getP_comp_kapuze_() {
		return p_comp_kapuze_;
	}

	public void setP_comp_kapuze_(String p_comp_kapuze_) {
		this.p_comp_kapuze_ = p_comp_kapuze_;
	}

	public String getP_comp_koerbchengroesse_() {
		return p_comp_koerbchengroesse_;
	}

	public void setP_comp_koerbchengroesse_(String p_comp_koerbchengroesse_) {
		this.p_comp_koerbchengroesse_ = p_comp_koerbchengroesse_;
	}

	public String getP_comp_kragen_() {
		return p_comp_kragen_;
	}

	public void setP_comp_kragen_(String p_comp_kragen_) {
		this.p_comp_kragen_ = p_comp_kragen_;
	}

	public String getP_comp_lagen_() {
		return p_comp_lagen_;
	}

	public void setP_comp_lagen_(String p_comp_lagen_) {
		this.p_comp_lagen_ = p_comp_lagen_;
	}

	public String getP_comp_länge_() {
		return p_comp_länge_;
	}

	public void setP_comp_länge_(String p_comp_länge_) {
		this.p_comp_länge_ = p_comp_länge_;
	}

	public String getP_comp_laenge_() {
		return p_comp_laenge_;
	}

	public void setP_comp_laenge_(String p_comp_laenge_) {
		this.p_comp_laenge_ = p_comp_laenge_;
	}

	public String getP_comp_laufsohle_schuhe_() {
		return p_comp_laufsohle_schuhe_;
	}

	public void setP_comp_laufsohle_schuhe_(String p_comp_laufsohle_schuhe_) {
		this.p_comp_laufsohle_schuhe_ = p_comp_laufsohle_schuhe_;
	}

	public String getP_comp_leibhoehe_() {
		return p_comp_leibhoehe_;
	}

	public void setP_comp_leibhoehe_(String p_comp_leibhoehe_) {
		this.p_comp_leibhoehe_ = p_comp_leibhoehe_;
	}

	public String getP_comp_Linsentechnik_() {
		return p_comp_Linsentechnik_;
	}

	public void setP_comp_Linsentechnik_(String p_comp_Linsentechnik_) {
		this.p_comp_Linsentechnik_ = p_comp_Linsentechnik_;
	}

	public String getP_comp_Marke_() {
		return p_comp_Marke_;
	}

	public void setP_comp_Marke_(String p_comp_Marke_) {
		this.p_comp_Marke_ = p_comp_Marke_;
	}

	public String getP_comp_material_() {
		return p_comp_material_;
	}

	public void setP_comp_material_(String p_comp_material_) {
		this.p_comp_material_ = p_comp_material_;
	}

	public String getP_comp_material_aermelfutter_() {
		return p_comp_material_aermelfutter_;
	}

	public void setP_comp_material_aermelfutter_(String p_comp_material_aermelfutter_) {
		this.p_comp_material_aermelfutter_ = p_comp_material_aermelfutter_;
	}

	public String getP_comp_material_aermeloberstoff_() {
		return p_comp_material_aermeloberstoff_;
	}

	public void setP_comp_material_aermeloberstoff_(String p_comp_material_aermeloberstoff_) {
		this.p_comp_material_aermeloberstoff_ = p_comp_material_aermeloberstoff_;
	}

	public String getP_comp_material_fuellung_() {
		return p_comp_material_fuellung_;
	}

	public void setP_comp_material_fuellung_(String p_comp_material_fuellung_) {
		this.p_comp_material_fuellung_ = p_comp_material_fuellung_;
	}

	public String getP_comp_material_innenjacke_() {
		return p_comp_material_innenjacke_;
	}

	public void setP_comp_material_innenjacke_(String p_comp_material_innenjacke_) {
		this.p_comp_material_innenjacke_ = p_comp_material_innenjacke_;
	}

	public String getP_comp_material_kontrast_() {
		return p_comp_material_kontrast_;
	}

	public void setP_comp_material_kontrast_(String p_comp_material_kontrast_) {
		this.p_comp_material_kontrast_ = p_comp_material_kontrast_;
	}

	public String getP_comp_material_oberstoff_() {
		return p_comp_material_oberstoff_;
	}

	public void setP_comp_material_oberstoff_(String p_comp_material_oberstoff_) {
		this.p_comp_material_oberstoff_ = p_comp_material_oberstoff_;
	}

	public String getP_comp_material_rippe_() {
		return p_comp_material_rippe_;
	}

	public void setP_comp_material_rippe_(String p_comp_material_rippe_) {
		this.p_comp_material_rippe_ = p_comp_material_rippe_;
	}

	public String getP_comp_material_webpelz_() {
		return p_comp_material_webpelz_;
	}

	public void setP_comp_material_webpelz_(String p_comp_material_webpelz_) {
		this.p_comp_material_webpelz_ = p_comp_material_webpelz_;
	}

	public String getP_comp_material_eigenschaft_() {
		return p_comp_material_eigenschaft_;
	}

	public void setP_comp_material_eigenschaft_(String p_comp_material_eigenschaft_) {
		this.p_comp_material_eigenschaft_ = p_comp_material_eigenschaft_;
	}

	public String getP_comp_muster_() {
		return p_comp_muster_;
	}

	public void setP_comp_muster_(String p_comp_muster_) {
		this.p_comp_muster_ = p_comp_muster_;
	}

	public String getP_comp_obermaterial_schuhe_() {
		return p_comp_obermaterial_schuhe_;
	}

	public void setP_comp_obermaterial_schuhe_(String p_comp_obermaterial_schuhe_) {
		this.p_comp_obermaterial_schuhe_ = p_comp_obermaterial_schuhe_;
	}

	public String getP_comp_oberteillänge_() {
		return p_comp_oberteillänge_;
	}

	public void setP_comp_oberteillänge_(String p_comp_oberteillänge_) {
		this.p_comp_oberteillänge_ = p_comp_oberteillänge_;
	}

	public String getP_comp_ohrring_stil_() {
		return p_comp_ohrring_stil_;
	}

	public void setP_comp_ohrring_stil_(String p_comp_ohrring_stil_) {
		this.p_comp_ohrring_stil_ = p_comp_ohrring_stil_;
	}

	public String getP_comp_passform_() {
		return p_comp_passform_;
	}

	public void setP_comp_passform_(String p_comp_passform_) {
		this.p_comp_passform_ = p_comp_passform_;
	}

	public String getP_comp_pflegehinweise_() {
		return p_comp_pflegehinweise_;
	}

	public void setP_comp_pflegehinweise_(String p_comp_pflegehinweise_) {
		this.p_comp_pflegehinweise_ = p_comp_pflegehinweise_;
	}

	public String getP_comp_plateauhoehe_() {
		return p_comp_plateauhoehe_;
	}

	public void setP_comp_plateauhoehe_(String p_comp_plateauhoehe_) {
		this.p_comp_plateauhoehe_ = p_comp_plateauhoehe_;
	}

	public String getP_comp_produkttyp_() {
		return p_comp_produkttyp_;
	}

	public void setP_comp_produkttyp_(String p_comp_produkttyp_) {
		this.p_comp_produkttyp_ = p_comp_produkttyp_;
	}

	public String getP_comp_Rahmenmaterial_() {
		return p_comp_Rahmenmaterial_;
	}

	public void setP_comp_Rahmenmaterial_(String p_comp_Rahmenmaterial_) {
		this.p_comp_Rahmenmaterial_ = p_comp_Rahmenmaterial_;
	}

	public String getP_comp_rock_stil_() {
		return p_comp_rock_stil_;
	}

	public void setP_comp_rock_stil_(String p_comp_rock_stil_) {
		this.p_comp_rock_stil_ = p_comp_rock_stil_;
	}

	public String getP_comp_bag_size_() {
		return p_comp_bag_size_;
	}

	public void setP_comp_bag_size_(String p_comp_bag_size_) {
		this.p_comp_bag_size_ = p_comp_bag_size_;
	}

	public String getP_comp_saison_() {
		return p_comp_saison_;
	}

	public void setP_comp_saison_(String p_comp_saison_) {
		this.p_comp_saison_ = p_comp_saison_;
	}

	public String getP_comp_saum_() {
		return p_comp_saum_;
	}

	public void setP_comp_saum_(String p_comp_saum_) {
		this.p_comp_saum_ = p_comp_saum_;
	}

	public String getP_comp_schichten_() {
		return p_comp_schichten_;
	}

	public void setP_comp_schichten_(String p_comp_schichten_) {
		this.p_comp_schichten_ = p_comp_schichten_;
	}

	public String getP_comp_schnitt_() {
		return p_comp_schnitt_;
	}

	public void setP_comp_schnitt_(String p_comp_schnitt_) {
		this.p_comp_schnitt_ = p_comp_schnitt_;
	}

	public String getP_comp_schuhspitze_() {
		return p_comp_schuhspitze_;
	}

	public void setP_comp_schuhspitze_(String p_comp_schuhspitze_) {
		this.p_comp_schuhspitze_ = p_comp_schuhspitze_;
	}

	public String getP_comp_Schutz_() {
		return p_comp_Schutz_;
	}

	public void setP_comp_Schutz_(String p_comp_Schutz_) {
		this.p_comp_Schutz_ = p_comp_Schutz_;
	}

	public String getP_comp_sohle_() {
		return p_comp_sohle_;
	}

	public void setP_comp_sohle_(String p_comp_sohle_) {
		this.p_comp_sohle_ = p_comp_sohle_;
	}

	public String getP_comp_sportart_() {
		return p_comp_sportart_;
	}

	public void setP_comp_sportart_(String p_comp_sportart_) {
		this.p_comp_sportart_ = p_comp_sportart_;
	}

	public String getP_comp_stegbreite_() {
		return p_comp_stegbreite_;
	}

	public void setP_comp_stegbreite_(String p_comp_stegbreite_) {
		this.p_comp_stegbreite_ = p_comp_stegbreite_;
	}

	public String getP_comp_Stil_() {
		return p_comp_Stil_;
	}

	public void setP_comp_Stil_(String p_comp_Stil_) {
		this.p_comp_Stil_ = p_comp_Stil_;
	}

	public String getP_comp_Stil_Hose_() {
		return p_comp_Stil_Hose_;
	}

	public void setP_comp_Stil_Hose_(String p_comp_Stil_Hose_) {
		this.p_comp_Stil_Hose_ = p_comp_Stil_Hose_;
	}

	public String getP_comp_Stil_Jacken_Mäntel_() {
		return p_comp_Stil_Jacken_Mäntel_;
	}

	public void setP_comp_Stil_Jacken_Mäntel_(String p_comp_Stil_Jacken_Mäntel_) {
		this.p_comp_Stil_Jacken_Mäntel_ = p_comp_Stil_Jacken_Mäntel_;
	}

	public String getP_comp_stil_jeans_() {
		return p_comp_stil_jeans_;
	}

	public void setP_comp_stil_jeans_(String p_comp_stil_jeans_) {
		this.p_comp_stil_jeans_ = p_comp_stil_jeans_;
	}

	public String getP_comp_stil_srtick_() {
		return p_comp_stil_srtick_;
	}

	public void setP_comp_stil_srtick_(String p_comp_stil_srtick_) {
		this.p_comp_stil_srtick_ = p_comp_stil_srtick_;
	}

	public String getP_comp_Stil_Strick_() {
		return p_comp_Stil_Strick_;
	}

	public void setP_comp_Stil_Strick_(String p_comp_Stil_Strick_) {
		this.p_comp_Stil_Strick_ = p_comp_Stil_Strick_;
	}

	public String getP_comp_taschen_() {
		return p_comp_taschen_;
	}

	public void setP_comp_taschen_(String p_comp_taschen_) {
		this.p_comp_taschen_ = p_comp_taschen_;
	}

	public String getP_comp_thema_() {
		return p_comp_thema_;
	}

	public void setP_comp_thema_(String p_comp_thema_) {
		this.p_comp_thema_ = p_comp_thema_;
	}

	public String getP_comp_tiefe_() {
		return p_comp_tiefe_;
	}

	public void setP_comp_tiefe_(String p_comp_tiefe_) {
		this.p_comp_tiefe_ = p_comp_tiefe_;
	}

	public String getP_comp_traeger_() {
		return p_comp_traeger_;
	}

	public void setP_comp_traeger_(String p_comp_traeger_) {
		this.p_comp_traeger_ = p_comp_traeger_;
	}

	public String getP_comp_trägerart_() {
		return p_comp_trägerart_;
	}

	public void setP_comp_trägerart_(String p_comp_trägerart_) {
		this.p_comp_trägerart_ = p_comp_trägerart_;
	}

	public String getP_comp_umfang_() {
		return p_comp_umfang_;
	}

	public void setP_comp_umfang_(String p_comp_umfang_) {
		this.p_comp_umfang_ = p_comp_umfang_;
	}

	public String getP_comp_unterteillaenge_() {
		return p_comp_unterteillaenge_;
	}

	public void setP_comp_unterteillaenge_(String p_comp_unterteillaenge_) {
		this.p_comp_unterteillaenge_ = p_comp_unterteillaenge_;
	}

	public String getP_comp_ursprungsland_() {
		return p_comp_ursprungsland_;
	}

	public void setP_comp_ursprungsland_(String p_comp_ursprungsland_) {
		this.p_comp_ursprungsland_ = p_comp_ursprungsland_;
	}

	public String getP_comp_vek_() {
		return p_comp_vek_;
	}

	public void setP_comp_vek_(String p_comp_vek_) {
		this.p_comp_vek_ = p_comp_vek_;
	}

	public String getP_comp_vek_online_() {
		return p_comp_vek_online_;
	}

	public void setP_comp_vek_online_(String p_comp_vek_online_) {
		this.p_comp_vek_online_ = p_comp_vek_online_;
	}

	public String getP_comp_verschluss_() {
		return p_comp_verschluss_;
	}

	public void setP_comp_verschluss_(String p_comp_verschluss_) {
		this.p_comp_verschluss_ = p_comp_verschluss_;
	}

	public String getP_comp_volumen_() {
		return p_comp_volumen_;
	}

	public void setP_comp_volumen_(String p_comp_volumen_) {
		this.p_comp_volumen_ = p_comp_volumen_;
	}

	public String getP_comp_waisthoehe_() {
		return p_comp_waisthoehe_;
	}

	public void setP_comp_waisthoehe_(String p_comp_waisthoehe_) {
		this.p_comp_waisthoehe_ = p_comp_waisthoehe_;
	}

	public String getP_comp_Wasserdichtigkeit_() {
		return p_comp_Wasserdichtigkeit_;
	}

	public void setP_comp_Wasserdichtigkeit_(String p_comp_Wasserdichtigkeit_) {
		this.p_comp_Wasserdichtigkeit_ = p_comp_Wasserdichtigkeit_;
	}

	public String getP_comp_wassersaeule_() {
		return p_comp_wassersaeule_;
	}

	public void setP_comp_wassersaeule_(String p_comp_wassersaeule_) {
		this.p_comp_wassersaeule_ = p_comp_wassersaeule_;
	}

	public String getP_comp_Winddichtigkeit_() {
		return p_comp_Winddichtigkeit_;
	}

	public void setP_comp_Winddichtigkeit_(String p_comp_Winddichtigkeit_) {
		this.p_comp_Winddichtigkeit_ = p_comp_Winddichtigkeit_;
	}

	public String getP_comp_zielgruppe_() {
		return p_comp_zielgruppe_;
	}

	public void setP_comp_zielgruppe_(String p_comp_zielgruppe_) {
		this.p_comp_zielgruppe_ = p_comp_zielgruppe_;
	}

	public String getP_brand() {
		return p_brand;
	}

	public void setP_brand(String p_brand) {
		this.p_brand = p_brand;
	}

	public String getP_active_lade_() {
		return p_active_lade_;
	}

	public void setP_active_lade_(String p_active_lade_) {
		this.p_active_lade_ = p_active_lade_;
	}

	public String getP_active_cuglago1_() {
		return p_active_cuglago1_;
	}

	public void setP_active_cuglago1_(String p_active_cuglago1_) {
		this.p_active_cuglago1_ = p_active_cuglago1_;
	}

	public String getP_catpri_cuglago1_() {
		return p_catpri_cuglago1_;
	}

	public void setP_catpri_cuglago1_(String p_catpri_cuglago1_) {
		this.p_catpri_cuglago1_ = p_catpri_cuglago1_;
	}

	public String getP_catsec_cuglago1_0_() {
		return p_catsec_cuglago1_0_;
	}

	public void setP_catsec_cuglago1_0_(String p_catsec_cuglago1_0_) {
		this.p_catsec_cuglago1_0_ = p_catsec_cuglago1_0_;
	}

	public String getP_bullet_0_() {
		return p_bullet_0_;
	}

	public void setP_bullet_0_(String p_bullet_0_) {
		this.p_bullet_0_ = p_bullet_0_;
	}

	public String getP_bullet_1_() {
		return p_bullet_1_;
	}

	public void setP_bullet_1_(String p_bullet_1_) {
		this.p_bullet_1_ = p_bullet_1_;
	}

	public String getP_bullet_2_() {
		return p_bullet_2_;
	}

	public void setP_bullet_2_(String p_bullet_2_) {
		this.p_bullet_2_ = p_bullet_2_;
	}

	public String getP_bullet_3_() {
		return p_bullet_3_;
	}

	public void setP_bullet_3_(String p_bullet_3_) {
		this.p_bullet_3_ = p_bullet_3_;
	}

	public String getP_bullet_4_() {
		return p_bullet_4_;
	}

	public void setP_bullet_4_(String p_bullet_4_) {
		this.p_bullet_4_ = p_bullet_4_;
	}

	public String getP_bullet_5_() {
		return p_bullet_5_;
	}

	public void setP_bullet_5_(String p_bullet_5_) {
		this.p_bullet_5_ = p_bullet_5_;
	}

	public String getP_bullet_6_() {
		return p_bullet_6_;
	}

	public void setP_bullet_6_(String p_bullet_6_) {
		this.p_bullet_6_ = p_bullet_6_;
	}

	public String getP_bullet_7_() {
		return p_bullet_7_;
	}

	public void setP_bullet_7_(String p_bullet_7_) {
		this.p_bullet_7_ = p_bullet_7_;
	}

	public String getP_bullet_8_() {
		return p_bullet_8_;
	}

	public void setP_bullet_8_(String p_bullet_8_) {
		this.p_bullet_8_ = p_bullet_8_;
	}

	public String getP_bullet_9_() {
		return p_bullet_9_;
	}

	public void setP_bullet_9_(String p_bullet_9_) {
		this.p_bullet_9_ = p_bullet_9_;
	}

	public String getP_bullet_10_() {
		return p_bullet_10_;
	}

	public void setP_bullet_10_(String p_bullet_10_) {
		this.p_bullet_10_ = p_bullet_10_;
	}

	public String getP_bullet_11_() {
		return p_bullet_11_;
	}

	public void setP_bullet_11_(String p_bullet_11_) {
		this.p_bullet_11_ = p_bullet_11_;
	}

	public String getP_bullet_12_() {
		return p_bullet_12_;
	}

	public void setP_bullet_12_(String p_bullet_12_) {
		this.p_bullet_12_ = p_bullet_12_;
	}

	public String getP_bullet_13_() {
		return p_bullet_13_;
	}

	public void setP_bullet_13_(String p_bullet_13_) {
		this.p_bullet_13_ = p_bullet_13_;
	}

	public String getP_bullet_14_() {
		return p_bullet_14_;
	}

	public void setP_bullet_14_(String p_bullet_14_) {
		this.p_bullet_14_ = p_bullet_14_;
	}

	public String getP_bullet_15_() {
		return p_bullet_15_;
	}

	public void setP_bullet_15_(String p_bullet_15_) {
		this.p_bullet_15_ = p_bullet_15_;
	}

	public String getP_bullet_16_() {
		return p_bullet_16_;
	}

	public void setP_bullet_16_(String p_bullet_16_) {
		this.p_bullet_16_ = p_bullet_16_;
	}

	public String getP_bullet_17_() {
		return p_bullet_17_;
	}

	public void setP_bullet_17_(String p_bullet_17_) {
		this.p_bullet_17_ = p_bullet_17_;
	}

	public String getP_bullet_18_() {
		return p_bullet_18_;
	}

	public void setP_bullet_18_(String p_bullet_18_) {
		this.p_bullet_18_ = p_bullet_18_;
	}

	public String getP_bullet_19_() {
		return p_bullet_19_;
	}

	public void setP_bullet_19_(String p_bullet_19_) {
		this.p_bullet_19_ = p_bullet_19_;
	}

	public String getP_bullet_20_() {
		return p_bullet_20_;
	}

	public void setP_bullet_20_(String p_bullet_20_) {
		this.p_bullet_20_ = p_bullet_20_;
	}

	public String getP_bullet_21_() {
		return p_bullet_21_;
	}

	public void setP_bullet_21_(String p_bullet_21_) {
		this.p_bullet_21_ = p_bullet_21_;
	}

	public String getP_bullet_22_() {
		return p_bullet_22_;
	}

	public void setP_bullet_22_(String p_bullet_22_) {
		this.p_bullet_22_ = p_bullet_22_;
	}

	public String getP_bullet_23_() {
		return p_bullet_23_;
	}

	public void setP_bullet_23_(String p_bullet_23_) {
		this.p_bullet_23_ = p_bullet_23_;
	}

	public String getP_bullet_24_() {
		return p_bullet_24_;
	}

	public void setP_bullet_24_(String p_bullet_24_) {
		this.p_bullet_24_ = p_bullet_24_;
	}

	public String getP_bullet_25_() {
		return p_bullet_25_;
	}

	public void setP_bullet_25_(String p_bullet_25_) {
		this.p_bullet_25_ = p_bullet_25_;
	}

	public String getP_bullet_26_() {
		return p_bullet_26_;
	}

	public void setP_bullet_26_(String p_bullet_26_) {
		this.p_bullet_26_ = p_bullet_26_;
	}

	public String getP_bullet_27_() {
		return p_bullet_27_;
	}

	public void setP_bullet_27_(String p_bullet_27_) {
		this.p_bullet_27_ = p_bullet_27_;
	}

	public String getP_bullet_28_() {
		return p_bullet_28_;
	}

	public void setP_bullet_28_(String p_bullet_28_) {
		this.p_bullet_28_ = p_bullet_28_;
	}

	public String getP_bullet_29_() {
		return p_bullet_29_;
	}

	public void setP_bullet_29_(String p_bullet_29_) {
		this.p_bullet_29_ = p_bullet_29_;
	}

	public String getP_bullet_30_() {
		return p_bullet_30_;
	}

	public void setP_bullet_30_(String p_bullet_30_) {
		this.p_bullet_30_ = p_bullet_30_;
	}

	public String getP_bullet_31_() {
		return p_bullet_31_;
	}

	public void setP_bullet_31_(String p_bullet_31_) {
		this.p_bullet_31_ = p_bullet_31_;
	}

	public String getP_bullet_32_() {
		return p_bullet_32_;
	}

	public void setP_bullet_32_(String p_bullet_32_) {
		this.p_bullet_32_ = p_bullet_32_;
	}

	public String getA_nr() {
		return a_nr;
	}

	public void setA_nr(String a_nr) {
		this.a_nr = a_nr;
	}

	public String getA_active() {
		return a_active;
	}

	public void setA_active(String a_active) {
		this.a_active = a_active;
	}

	public String getA_nr2() {
		return a_nr2;
	}

	public void setA_nr2(String a_nr2) {
		this.a_nr2 = a_nr2;
	}

	public String getA_prodnr() {
		return a_prodnr;
	}

	public void setA_prodnr(String a_prodnr) {
		this.a_prodnr = a_prodnr;
	}

	public String getArtikelbezeichnung() {
		return Artikelbezeichnung;
	}

	public void setArtikelbezeichnung(String artikelbezeichnung) {
		Artikelbezeichnung = artikelbezeichnung;
	}

	public String getA_comp_farbe_() {
		return a_comp_farbe_;
	}

	public void setA_comp_farbe_(String a_comp_farbe_) {
		this.a_comp_farbe_ = a_comp_farbe_;
	}

	public String getA_comp_groesse_() {
		return a_comp_groesse_;
	}

	public void setA_comp_groesse_(String a_comp_groesse_) {
		this.a_comp_groesse_ = a_comp_groesse_;
	}

	public String getA_comp_koerbchengroesse_() {
		return a_comp_koerbchengroesse_;
	}

	public void setA_comp_koerbchengroesse_(String a_comp_koerbchengroesse_) {
		this.a_comp_koerbchengroesse_ = a_comp_koerbchengroesse_;
	}

	public String getA_comp_laenge_() {
		return a_comp_laenge_;
	}

	public void setA_comp_laenge_(String a_comp_laenge_) {
		this.a_comp_laenge_ = a_comp_laenge_;
	}

	public String getA_comp_otto_weite_() {
		return a_comp_otto_weite_;
	}

	public void setA_comp_otto_weite_(String a_comp_otto_weite_) {
		this.a_comp_otto_weite_ = a_comp_otto_weite_;
	}

	public String getAp_comp_ebay_produkt_name_() {
		return ap_comp_ebay_produkt_name_;
	}

	public void setAp_comp_ebay_produkt_name_(String ap_comp_ebay_produkt_name_) {
		this.ap_comp_ebay_produkt_name_ = ap_comp_ebay_produkt_name_;
	}

	public String getAp_comp_ursprungsland_() {
		return ap_comp_ursprungsland_;
	}

	public void setAp_comp_ursprungsland_(String ap_comp_ursprungsland_) {
		this.ap_comp_ursprungsland_ = ap_comp_ursprungsland_;
	}

	public String getA_media_image_0_() {
		return a_media_image_0_;
	}

	public void setA_media_image_0_(String a_media_image_0_) {
		this.a_media_image_0_ = a_media_image_0_;
	}

	public String getA_media_image_1_() {
		return a_media_image_1_;
	}

	public void setA_media_image_1_(String a_media_image_1_) {
		this.a_media_image_1_ = a_media_image_1_;
	}

	public String getA_media_image_2_() {
		return a_media_image_2_;
	}

	public void setA_media_image_2_(String a_media_image_2_) {
		this.a_media_image_2_ = a_media_image_2_;
	}

	public String getA_media_image_3_() {
		return a_media_image_3_;
	}

	public void setA_media_image_3_(String a_media_image_3_) {
		this.a_media_image_3_ = a_media_image_3_;
	}

	public String getA_media_image_4_() {
		return a_media_image_4_;
	}

	public void setA_media_image_4_(String a_media_image_4_) {
		this.a_media_image_4_ = a_media_image_4_;
	}

	public String getA_media_image_5_() {
		return a_media_image_5_;
	}

	public void setA_media_image_5_(String a_media_image_5_) {
		this.a_media_image_5_ = a_media_image_5_;
	}

	public String getA_media_image_6_() {
		return a_media_image_6_;
	}

	public void setA_media_image_6_(String a_media_image_6_) {
		this.a_media_image_6_ = a_media_image_6_;
	}

	public String getA_media_image_7_() {
		return a_media_image_7_;
	}

	public void setA_media_image_7_(String a_media_image_7_) {
		this.a_media_image_7_ = a_media_image_7_;
	}

	public String getA_media_image_8_() {
		return a_media_image_8_;
	}

	public void setA_media_image_8_(String a_media_image_8_) {
		this.a_media_image_8_ = a_media_image_8_;
	}

	public String getA_media_image_9_() {
		return a_media_image_9_;
	}

	public void setA_media_image_9_(String a_media_image_9_) {
		this.a_media_image_9_ = a_media_image_9_;
	}

	public String getA_media_image_10_() {
		return a_media_image_10_;
	}

	public void setA_media_image_10_(String a_media_image_10_) {
		this.a_media_image_10_ = a_media_image_10_;
	}

	public String getA_media_image_11_() {
		return a_media_image_11_;
	}

	public void setA_media_image_11_(String a_media_image_11_) {
		this.a_media_image_11_ = a_media_image_11_;
	}

	public String getA_media_image_12_() {
		return a_media_image_12_;
	}

	public void setA_media_image_12_(String a_media_image_12_) {
		this.a_media_image_12_ = a_media_image_12_;
	}

	public String getA_media_image_13_() {
		return a_media_image_13_;
	}

	public void setA_media_image_13_(String a_media_image_13_) {
		this.a_media_image_13_ = a_media_image_13_;
	}

	public String getA_media_image_14_() {
		return a_media_image_14_;
	}

	public void setA_media_image_14_(String a_media_image_14_) {
		this.a_media_image_14_ = a_media_image_14_;
	}

	public String getA_media_image_15_() {
		return a_media_image_15_;
	}

	public void setA_media_image_15_(String a_media_image_15_) {
		this.a_media_image_15_ = a_media_image_15_;
	}

	public String getA_media_image_16_() {
		return a_media_image_16_;
	}

	public void setA_media_image_16_(String a_media_image_16_) {
		this.a_media_image_16_ = a_media_image_16_;
	}

	public String getA_media_image_17_() {
		return a_media_image_17_;
	}

	public void setA_media_image_17_(String a_media_image_17_) {
		this.a_media_image_17_ = a_media_image_17_;
	}

	public String getA_media_image_18_() {
		return a_media_image_18_;
	}

	public void setA_media_image_18_(String a_media_image_18_) {
		this.a_media_image_18_ = a_media_image_18_;
	}

	public String getA_media_image_19_() {
		return a_media_image_19_;
	}

	public void setA_media_image_19_(String a_media_image_19_) {
		this.a_media_image_19_ = a_media_image_19_;
	}

	public String getA_media_image_20_() {
		return a_media_image_20_;
	}

	public void setA_media_image_20_(String a_media_image_20_) {
		this.a_media_image_20_ = a_media_image_20_;
	}

	public String getA_media_image_21_() {
		return a_media_image_21_;
	}

	public void setA_media_image_21_(String a_media_image_21_) {
		this.a_media_image_21_ = a_media_image_21_;
	}

	public String getA_media_image_22_() {
		return a_media_image_22_;
	}

	public void setA_media_image_22_(String a_media_image_22_) {
		this.a_media_image_22_ = a_media_image_22_;
	}

	public String getA_media_image_23_() {
		return a_media_image_23_;
	}

	public void setA_media_image_23_(String a_media_image_23_) {
		this.a_media_image_23_ = a_media_image_23_;
	}

	public String getA_media_image_24_() {
		return a_media_image_24_;
	}

	public void setA_media_image_24_(String a_media_image_24_) {
		this.a_media_image_24_ = a_media_image_24_;
	}

	public String getA_media_image_25_() {
		return a_media_image_25_;
	}

	public void setA_media_image_25_(String a_media_image_25_) {
		this.a_media_image_25_ = a_media_image_25_;
	}

	public String getA_ek() {
		return a_ek;
	}

	public void setA_ek(String a_ek) {
		this.a_ek = a_ek;
	}

	public String getA_stock() {
		return a_stock;
	}

	public void setA_stock(String a_stock) {
		this.a_stock = a_stock;
	}

	public String getA_separate() {
		return a_separate;
	}

	public void setA_separate(String a_separate) {
		this.a_separate = a_separate;
	}

	public String getA_maxsep() {
		return a_maxsep;
	}

	public void setA_maxsep(String a_maxsep) {
		this.a_maxsep = a_maxsep;
	}

	public String getA_shipping_type() {
		return a_shipping_type;
	}

	public void setA_shipping_type(String a_shipping_type) {
		this.a_shipping_type = a_shipping_type;
	}

	public String getA_width() {
		return a_width;
	}

	public void setA_width(String a_width) {
		this.a_width = a_width;
	}

	public String getA_height() {
		return a_height;
	}

	public void setA_height(String a_height) {
		this.a_height = a_height;
	}

	public String getA_weight() {
		return a_weight;
	}

	public void setA_weight(String a_weight) {
		this.a_weight = a_weight;
	}

	public String getA_length() {
		return a_length;
	}

	public void setA_length(String a_length) {
		this.a_length = a_length;
	}

	public String getA_intrastat() {
		return a_intrastat;
	}

	public void setA_intrastat(String a_intrastat) {
		this.a_intrastat = a_intrastat;
	}

	public String getA_org_country() {
		return a_org_country;
	}

	public void setA_org_country(String a_org_country) {
		this.a_org_country = a_org_country;
	}

	public Set<Lot> getLot() {
		return lot;
	}

	public void setLot(Set<Lot> lot) {
		this.lot = lot;
	}*/
	
	

}
