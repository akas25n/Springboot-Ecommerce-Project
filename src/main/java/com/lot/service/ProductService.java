package com.lot.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lot.model.Product;
import com.lot.model.ResourceNotFoundException;
import com.lot.repository.ProductRepository;
import com.opencsv.CSVReader;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	private String fileLocation; // for deleting
	private String fileNewLocation; // for reading
	
	private String msg;
	
	//----------------------------------------------------------index----------------------------------------------
	int index_a_ean;
	int index_a_id;
	int index_p_id;
	int index_p_nr;
	int index_p_prefix;
	int index_p_name;
	int index_p_name_proper;
	int index_p_name_keyword;
	int index_p_supplement;
	int index_p_text;
	int index_p_feature;
	int index_p_comp_absatzart_;
	int index_p_comp_absatzhöhe;
	int index_p_comp_altersgruppe_;
	int index_p_comp_anzahl_schichten_;
	int index_p_comp_aermelart_;
	int index_p_comp_aermellaenge_;
	int index_p_comp_Artikelnummer_;
	int index_p_comp_atmungsaktivitaet_;
	int index_p_comp_Ausschnitt_;
	int index_p_comp_beininnenlaenge_;
	int index_p_comp_Beinlänge_;
	int index_p_comp_bh_bügel_;
	int index_p_comp_bh_schale_;
	int index_p_comp_bh_style_;
	int index_p_comp_bh_traegerart_;
	int index_p_comp_breite_;
	int index_p_comp_buegellaenge_;
	int index_p_comp_cluster_;
	int index_p_comp_decksohle_;
	int index_p_comp_decksohle_schuhe_;
	int index_p_comp_details_;
	int index_p_comp_durchmesser_;
	int index_p_comp_farbe_;
	int index_p_comp_form_;
	int index_p_comp_fransen_;
	int index_p_comp_funktionen_;
	int index_p_comp_futter_;
	int index_p_comp_gesamtlaenge_;
	int index_p_comp_gesamtlaengexxl_;
	int index_p_comp_glasbreite_;
	int index_p_comp_glashoehe_;
	int index_p_comp_groesse_;
	int index_p_comp_groessenflag_;
	int index_p_comp_strap_length_;
	int index_p_comp_hemdkragen_;
	int index_p_comp_Herstellungsland_und_region_;
	int index_p_comp_hoehe_;
	int index_p_comp_jeans_waschung_;
	int index_p_comp_kapuze_;
	int index_p_comp_koerbchengroesse_;
	int index_p_comp_kragen_;
	int index_p_comp_lagen_;
	int index_p_comp_länge_;
	int index_p_comp_laenge_;
	int index_p_comp_laufsohle_schuhe_;
	int index_p_comp_leibhoehe_;
	int index_p_comp_Linsentechnik_;
	int index_p_comp_Marke_;
	int index_p_comp_material_;
	int index_p_comp_material_aermelfutter_;
	int index_p_comp_material_aermeloberstoff_;
	int index_p_comp_material_fuellung_;
	int index_p_comp_material_innenjacke_;
	int index_p_comp_material_kontrast_;
	int index_p_comp_material_oberstoff_;
	int index_p_comp_material_rippe_;
	int index_p_comp_material_webpelz_;
	int index_p_comp_material_eigenschaft_;
	int index_p_comp_muster_;
	int index_p_comp_obermaterial_schuhe_;
	int index_p_comp_oberteillänge_;
	int index_p_comp_ohrring_stil_;
	int index_p_comp_passform_;
	int index_p_comp_pflegehinweise_;
	int index_p_comp_plateauhoehe_;
	int index_p_comp_produkttyp_;
	int index_p_comp_Rahmenmaterial_;
	int index_p_comp_rock_stil_;
	int index_p_comp_bag_size_;
	int index_p_comp_saison_;
	int index_p_comp_saum_;
	int index_p_comp_schichten_;
	int index_p_comp_schnitt_;
	int index_p_comp_schuhspitze_;
	int index_p_comp_Schutz_;
	int index_p_comp_sohle_;
	int index_p_comp_sportart_;
	int index_p_comp_stegbreite_;
	int index_p_comp_Stil_;
	int index_p_comp_Stil_Hose_;
	int index_p_comp_Stil_Jacken_Mäntel_;
	int index_p_comp_stil_jeans_;
	int index_p_comp_stil_srtick_;
	int index_p_comp_Stil_Strick_;
	int index_p_comp_taschen_;
	int index_p_comp_thema_;
	int index_p_comp_tiefe_;
	int index_p_comp_traeger_;
	int index_p_comp_trägerart_;
	int index_p_comp_umfang_;
	int index_p_comp_unterteillaenge_;
	int index_p_comp_ursprungsland_;
	int index_p_comp_vek_;
	int index_p_comp_vek_online_;
	int index_p_comp_verschluss_;
	int index_p_comp_volumen_;
	int index_p_comp_waisthoehe_;
	int index_p_comp_Wasserdichtigkeit_;
	int index_p_comp_wassersaeule_;
	int index_p_comp_Winddichtigkeit_;
	int index_p_comp_zielgruppe_;
	int index_p_brand;
	int index_p_active_lade_;
	int index_p_active_cuglago1_;
	int index_p_catpri_cuglago1_;
	int index_p_catsec_cuglago1_0_;
	int index_p_bullet_0_;
	int index_p_bullet_1_;
	int index_p_bullet_2_;
	int index_p_bullet_3_;
	int index_p_bullet_4_;
	int index_p_bullet_5_;
	int index_p_bullet_6_;
	int index_p_bullet_7_;
	int index_p_bullet_8_;
	int index_p_bullet_9_;
	int index_p_bullet_10_;
	int index_p_bullet_11_;
	int index_p_bullet_12_;
	int index_p_bullet_13_;
	int index_p_bullet_14_;
	int index_p_bullet_15_;
	int index_p_bullet_16_;
	int index_p_bullet_17_;
	int index_p_bullet_18_;
	int index_p_bullet_19_;
	int index_p_bullet_20_;
	int index_p_bullet_21_;
	int index_p_bullet_22_;
	int index_p_bullet_23_;
	int index_p_bullet_24_;
	int index_p_bullet_25_;
	int index_p_bullet_26_;
	int index_p_bullet_27_;
	int index_p_bullet_28_;
	int index_p_bullet_29_;
	int index_p_bullet_30_;
	int index_p_bullet_31_;
	int index_p_bullet_32_;
	int index_a_nr;
	int index_a_active;
	int index_a_nr2;
	int index_a_prodnr;
	int index_Artikelbezeichnung;
	int index_a_comp_farbe_;
	int index_a_comp_groesse_;
	int index_a_comp_koerbchengroesse_;
	int index_a_comp_laenge_;
	int index_a_comp_otto_weite_;
	int index_ap_comp_ebay_produkt_name_;
	int index_ap_comp_ursprungsland_;
	int index_a_media_image_0_;
	int index_a_media_image_1_;
	int index_a_media_image_2_;
	int index_a_media_image_3_;
	int index_a_media_image_4_;
	int index_a_media_image_5_;
	int index_a_media_image_6_;
	int index_a_media_image_7_;
	int index_a_media_image_8_;
	int index_a_media_image_9_;
	int index_a_media_image_10_;
	int index_a_media_image_11_;
	int index_a_media_image_12_;
	int index_a_media_image_13_;
	int index_a_media_image_14_;
	int index_a_media_image_15_;
	int index_a_media_image_16_;
	int index_a_media_image_17_;
	int index_a_media_image_18_;
	int index_a_media_image_19_;
	int index_a_media_image_20_;
	int index_a_media_image_21_;
	int index_a_media_image_22_;
	int index_a_media_image_23_;
	int index_a_media_image_24_;
	int index_a_media_image_25_;
	int index_a_ek;
	int index_a_stock;
	int index_a_separate;
	int index_a_maxsep;
	int index_a_shipping_type;
	int index_a_width;
	int index_a_height;
	int index_a_weight;
	int index_a_length;
	int index_a_intrastat;
	int index_a_org_country;
	
	int temp;
	//-----------------------------------------------------------------index---------------------------------------------------
	
	public String upload_file(MultipartFile file) throws IOException{
		File convFile = new File(file.getOriginalFilename());
		convFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convFile);
		fileLocation=convFile.getAbsolutePath();
		
		fos.write(file.getBytes());
		fos.close();
		
		fileNewLocation = fileLocation.replace("\\", "/");
		return fileNewLocation;
	}
	
	
	public String save_product() {
		String[] data = null;
		List<String[]> allRows=null;
		//System.out.println("start try \n");
		try {
			@SuppressWarnings({"deprecation", "resource"})
			CSVReader reader = new CSVReader(new FileReader(fileNewLocation),';'); 
			
			allRows = reader.readAll();
			
			//System.out.println("start for loop \n");
			//---------------------------------------------------------------------------------------------problem is coming from here
			for(int i = 1; i< allRows.size(); i++) {
				
				data= allRows.get(i);
				long check_ean = Long.parseLong(data[index_a_ean]);
				Product product_obj = productRepository.find_BY_EAN(check_ean);
				
				if(product_obj == null) {
					product_create(data);
				}
				else if(!(product_obj == null)) {
					product_update(data);
					
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("error in save method \n");
		}
		
		
		//return "data saved";
		return msg;	
	}
	
	
	public String product_create(String[] data) {
		Product new_product = new Product();
		
		new_product.setA_id(Long.parseLong(data[index_a_id]));//311
		new_product.setA_ean(Long.parseLong(data[index_a_ean]));//315
		
		new_product.setP_id(Long.parseLong(data[index_p_id]));
		 new_product.setP_nr(data[index_p_nr]);
		 new_product.setP_prefix(data[index_p_prefix]);
		 new_product.setP_name(data[index_p_name]);
		 new_product.setP_name_proper(data[index_p_name_proper]);
		 new_product.setP_name_keyword(data[index_p_name_keyword]);
		 new_product.setP_supplement(data[index_p_supplement]);
		 new_product.setP_text(data[index_p_text]);
		 new_product.setP_feature(data[index_p_feature]);
		 new_product.setP_comp_absatzart_(data[index_p_comp_absatzart_]);
		 new_product.setP_comp_absatzhöhe(data[index_p_comp_absatzhöhe]);
		 new_product.setP_comp_altersgruppe_(data[index_p_comp_altersgruppe_]);
		 new_product.setP_comp_anzahl_schichten_(data[index_p_comp_anzahl_schichten_]);
		 new_product.setP_comp_aermelart_(data[index_p_comp_aermelart_]);
		 new_product.setP_comp_aermellaenge_(data[index_p_comp_aermellaenge_]);
		 new_product.setP_comp_Artikelnummer_(data[index_p_comp_Artikelnummer_]);
		 new_product.setP_comp_atmungsaktivitaet_(data[index_p_comp_atmungsaktivitaet_]);
		 new_product.setP_comp_Ausschnitt_(data[index_p_comp_Ausschnitt_]);
		 new_product.setP_comp_beininnenlaenge_(data[index_p_comp_beininnenlaenge_]);
		 new_product.setP_comp_Beinlänge_(data[index_p_comp_Beinlänge_]);
		 new_product.setP_comp_bh_bügel_(data[index_p_comp_bh_bügel_]);
		 new_product.setP_comp_bh_schale_(data[index_p_comp_bh_schale_]);
		 new_product.setP_comp_bh_style_(data[index_p_comp_bh_style_]);
		 new_product.setP_comp_bh_traegerart_(data[index_p_comp_bh_traegerart_]);
		 new_product.setP_comp_breite_(data[index_p_comp_breite_]);
		 new_product.setP_comp_buegellaenge_(data[index_p_comp_buegellaenge_]);
		 new_product.setP_comp_cluster_(data[index_p_comp_cluster_]);
		 new_product.setP_comp_decksohle_(data[index_p_comp_decksohle_]);
		 new_product.setP_comp_decksohle_schuhe_(data[index_p_comp_decksohle_schuhe_]);
		 new_product.setP_comp_details_(data[index_p_comp_details_]);
		 new_product.setP_comp_durchmesser_(data[index_p_comp_durchmesser_]);
		 new_product.setP_comp_farbe_(data[index_p_comp_farbe_]);
		 new_product.setP_comp_form_(data[index_p_comp_form_]);
		 new_product.setP_comp_fransen_(data[index_p_comp_fransen_]);
		 new_product.setP_comp_futter_(data[index_p_comp_futter_]);
		 new_product.setP_comp_gesamtlaenge_(data[index_p_comp_gesamtlaenge_]);
		 new_product.setP_comp_gesamtlaengexxl_(data[index_p_comp_gesamtlaengexxl_]);
		 new_product.setP_comp_glasbreite_(data[index_p_comp_glasbreite_]);
		 new_product.setP_comp_glashoehe_(data[index_p_comp_glashoehe_]);
		 new_product.setP_comp_groesse_(data[index_p_comp_groesse_]);
		 new_product.setP_comp_groessenflag_(data[index_p_comp_groessenflag_]);
		 new_product.setP_comp_strap_length_(data[index_p_comp_strap_length_]);
		 new_product.setP_comp_hemdkragen_(data[index_p_comp_hemdkragen_]);
		 new_product.setP_comp_Herstellungsland_und_region_(data[index_p_comp_Herstellungsland_und_region_]);
		 new_product.setP_comp_hoehe_(data[index_p_comp_hoehe_]);
		 new_product.setP_comp_jeans_waschung_(data[index_p_comp_jeans_waschung_]);
		 new_product.setP_comp_kapuze_(data[index_p_comp_kapuze_]);
		 new_product.setP_comp_koerbchengroesse_(data[index_p_comp_koerbchengroesse_]);
		 new_product.setP_comp_kragen_(data[index_p_comp_kragen_]);
		 new_product.setP_comp_lagen_(data[index_p_comp_lagen_]);
		 new_product.setP_comp_länge_(data[index_p_comp_länge_]);
		 new_product.setP_comp_laenge_(data[index_p_comp_laenge_]);
		 new_product.setP_comp_laufsohle_schuhe_(data[index_p_comp_laufsohle_schuhe_]);
		 new_product.setP_comp_leibhoehe_(data[index_p_comp_leibhoehe_]);
		 new_product.setP_comp_Linsentechnik_(data[index_p_comp_Linsentechnik_]);
		 new_product.setP_comp_Marke_(data[index_p_comp_Marke_]);
		 new_product.setP_comp_material_(data[index_p_comp_material_]);
		 new_product.setP_comp_material_aermelfutter_(data[index_p_comp_material_aermelfutter_]);
		 new_product.setP_comp_material_aermeloberstoff_(data[index_p_comp_material_aermeloberstoff_]);
		 new_product.setP_comp_material_fuellung_(data[index_p_comp_material_fuellung_]);
		 new_product.setP_comp_material_innenjacke_(data[index_p_comp_material_innenjacke_]);
		 new_product.setP_comp_material_kontrast_(data[index_p_comp_material_kontrast_]);
		 new_product.setP_comp_material_oberstoff_(data[index_p_comp_material_oberstoff_]);
		 new_product.setP_comp_material_rippe_(data[index_p_comp_material_rippe_]);
		 new_product.setP_comp_material_webpelz_(data[index_p_comp_material_webpelz_]);
		 new_product.setP_comp_material_eigenschaft_(data[index_p_comp_material_eigenschaft_]);
		 new_product.setP_comp_muster_(data[index_p_comp_muster_]);
		 new_product.setP_comp_obermaterial_schuhe_(data[index_p_comp_obermaterial_schuhe_]);
		 new_product.setP_comp_oberteillänge_(data[index_p_comp_oberteillänge_]);
		 new_product.setP_comp_ohrring_stil_(data[index_p_comp_ohrring_stil_]);
		 new_product.setP_comp_passform_(data[index_p_comp_passform_]);
		 new_product.setP_comp_pflegehinweise_(data[index_p_comp_pflegehinweise_]);
		 new_product.setP_comp_plateauhoehe_(data[index_p_comp_plateauhoehe_]);
		 new_product.setP_comp_produkttyp_(data[index_p_comp_produkttyp_]);
		 new_product.setP_comp_Rahmenmaterial_(data[index_p_comp_Rahmenmaterial_]);
		 new_product.setP_comp_rock_stil_(data[index_p_comp_rock_stil_]);
		 new_product.setP_comp_bag_size_(data[index_p_comp_bag_size_]);
		 new_product.setP_comp_saison_(data[index_p_comp_saison_]);
		 new_product.setP_comp_saum_(data[index_p_comp_saum_]);
		 new_product.setP_comp_schichten_(data[index_p_comp_schichten_]);
		 new_product.setP_comp_schnitt_(data[index_p_comp_schnitt_]);
		 new_product.setP_comp_schuhspitze_(data[index_p_comp_schuhspitze_]);
		 new_product.setP_comp_Schutz_(data[index_p_comp_Schutz_]);
		 new_product.setP_comp_sohle_(data[index_p_comp_sohle_]);
		 new_product.setP_comp_sportart_(data[index_p_comp_sportart_]);
		 new_product.setP_comp_stegbreite_(data[index_p_comp_stegbreite_]);
		 new_product.setP_comp_Stil_(data[index_p_comp_Stil_]);
		 new_product.setP_comp_Stil_Hose_(data[index_p_comp_Stil_Hose_]);
		 new_product.setP_comp_Stil_Jacken_Mäntel_(data[index_p_comp_Stil_Jacken_Mäntel_]);
		 new_product.setP_comp_stil_jeans_(data[index_p_comp_stil_jeans_]);
		 new_product.setP_comp_stil_srtick_(data[index_p_comp_stil_srtick_]);
		 new_product.setP_comp_Stil_Strick_(data[index_p_comp_Stil_Strick_]);
		 new_product.setP_comp_taschen_(data[index_p_comp_taschen_]);
		 new_product.setP_comp_thema_(data[index_p_comp_thema_]);
		 new_product.setP_comp_tiefe_(data[index_p_comp_tiefe_]);
		 new_product.setP_comp_traeger_(data[index_p_comp_traeger_]);
		 new_product.setP_comp_trägerart_(data[index_p_comp_trägerart_]);
		 new_product.setP_comp_umfang_(data[index_p_comp_umfang_]);
		 new_product.setP_comp_unterteillaenge_(data[index_p_comp_unterteillaenge_]);
		 new_product.setP_comp_ursprungsland_(data[index_p_comp_ursprungsland_]);
		 new_product.setP_comp_vek_(data[index_p_comp_vek_]);
		 new_product.setP_comp_vek_online_(data[index_p_comp_vek_online_]);
		 new_product.setP_comp_verschluss_(data[index_p_comp_verschluss_]);
		 new_product.setP_comp_volumen_(data[index_p_comp_volumen_]);
		 new_product.setP_comp_waisthoehe_(data[index_p_comp_waisthoehe_]);
		 new_product.setP_comp_Wasserdichtigkeit_(data[index_p_comp_Wasserdichtigkeit_]);
		 new_product.setP_comp_wassersaeule_(data[index_p_comp_wassersaeule_]);
		 new_product.setP_comp_Winddichtigkeit_(data[index_p_comp_Winddichtigkeit_]);
		 new_product.setP_comp_zielgruppe_(data[index_p_comp_zielgruppe_]);
		 new_product.setP_brand(data[index_p_brand]);
		 new_product.setP_active_lade_(data[index_p_active_lade_]);//142
		 new_product.setP_active_cuglago1_(data[index_p_active_cuglago1_]);//143
		 new_product.setP_catpri_cuglago1_(data[index_p_catpri_cuglago1_]);//144
		 new_product.setP_catsec_cuglago1_0_(data[index_p_catsec_cuglago1_0_]);//145
		 new_product.setP_bullet_0_(data[index_p_bullet_0_]);//278
		 new_product.setP_bullet_1_(data[index_p_bullet_1_]);//279
		 new_product.setP_bullet_2_(data[index_p_bullet_2_]);//280
		 new_product.setP_bullet_3_(data[index_p_bullet_3_]);//281
		 new_product.setP_bullet_4_(data[index_p_bullet_4_]);//282
		 new_product.setP_bullet_5_(data[index_p_bullet_5_]);//283
		 new_product.setP_bullet_6_(data[index_p_bullet_6_]);//284
		 new_product.setP_bullet_7_(data[index_p_bullet_7_]);//285
		 new_product.setP_bullet_8_(data[index_p_bullet_8_]);//286
		 new_product.setP_bullet_9_(data[index_p_bullet_9_]);//287
		 new_product.setP_bullet_10_(data[index_p_bullet_10_]);//288
		 new_product.setP_bullet_11_(data[index_p_bullet_11_]);//289
		 new_product.setP_bullet_12_(data[index_p_bullet_12_]);//290
		 new_product.setP_bullet_13_(data[index_p_bullet_13_]);//291
		 new_product.setP_bullet_14_(data[index_p_bullet_14_]);//292
		 new_product.setP_bullet_15_(data[index_p_bullet_15_]);//293
		 new_product.setP_bullet_16_(data[index_p_bullet_16_]);//294
		 new_product.setP_bullet_17_(data[index_p_bullet_17_]);//295
		 new_product.setP_bullet_18_(data[index_p_bullet_18_]);//296
		 new_product.setP_bullet_19_(data[index_p_bullet_19_]);//297
		 new_product.setP_bullet_20_(data[index_p_bullet_20_]);//298
		 new_product.setP_bullet_21_(data[index_p_bullet_21_]);//299
		 new_product.setP_bullet_22_(data[index_p_bullet_22_]);//300
		 new_product.setP_bullet_23_(data[index_p_bullet_23_]);//301
		 new_product.setP_bullet_24_(data[index_p_bullet_24_]);//302
		 new_product.setP_bullet_25_(data[index_p_bullet_25_]);//303
		 new_product.setP_bullet_26_(data[index_p_bullet_26_]);//304
		 new_product.setP_bullet_27_(data[index_p_bullet_27_]);//305
		 new_product.setP_bullet_28_(data[index_p_bullet_28_]);//306
		 new_product.setP_bullet_29_(data[index_p_bullet_29_]);//307
		 new_product.setP_bullet_30_(data[index_p_bullet_30_]);//308
		 new_product.setP_bullet_31_(data[index_p_bullet_31_]);//309
		 new_product.setP_bullet_32_(data[index_p_bullet_32_]);//310
		 new_product.setA_nr(data[index_a_nr]);//312
		 new_product.setA_active(data[index_a_active]);//313
		 new_product.setA_nr2(data[index_a_nr2]);//314
		 
		 new_product.setA_prodnr(data[index_a_prodnr]);//316
		 new_product.setArtikelbezeichnung(data[index_Artikelbezeichnung]);//317
		 new_product.setA_comp_farbe_(data[index_a_comp_farbe_]);//318
		 new_product.setA_comp_groesse_(data[index_a_comp_groesse_]);//319
		 new_product.setA_comp_koerbchengroesse_(data[index_a_comp_koerbchengroesse_]);//320
		 new_product.setA_comp_laenge_(data[index_a_comp_laenge_]);//321
		 new_product.setA_comp_otto_weite_(data[index_a_comp_otto_weite_]);//322
		 new_product.setAp_comp_ebay_produkt_name_(data[index_ap_comp_ebay_produkt_name_]);//323
		 new_product.setAp_comp_ursprungsland_(data[index_ap_comp_ursprungsland_]);//324
		 new_product.setA_media_image_0_(data[index_a_media_image_0_]);//545
		 new_product.setA_media_image_1_(data[index_a_media_image_1_]);//546
		 new_product.setA_media_image_2_(data[index_a_media_image_2_]);//547
		 new_product.setA_media_image_3_(data[index_a_media_image_3_]);//548
		 new_product.setA_media_image_4_(data[index_a_media_image_4_]);//549
		 new_product.setA_media_image_5_(data[index_a_media_image_5_]);//550
		 new_product.setA_media_image_6_(data[index_a_media_image_6_]);//551
		 new_product.setA_media_image_7_(data[index_a_media_image_7_]);//552
		 new_product.setA_media_image_8_(data[index_a_media_image_8_]);//553
		 new_product.setA_media_image_9_(data[index_a_media_image_9_]);//554
		 new_product.setA_media_image_10_(data[index_a_media_image_10_]);//555
		 new_product.setA_media_image_11_(data[index_a_media_image_11_]);//556
		 new_product.setA_media_image_12_(data[index_a_media_image_12_]);//557
		 new_product.setA_media_image_13_(data[index_a_media_image_13_]);//558
		 new_product.setA_media_image_14_(data[index_a_media_image_14_]);//559
		 new_product.setA_media_image_15_(data[index_a_media_image_15_]);//560
		 new_product.setA_media_image_16_(data[index_a_media_image_16_]);//561
		 new_product.setA_media_image_17_(data[index_a_media_image_17_]);//562
		 new_product.setA_media_image_18_(data[index_a_media_image_18_]);//563
		 new_product.setA_ek(data[index_a_ek]);//601
		 //--------------------------------------------------------------------------------------------------------Available stock
		 new_product.setA_stock(data[index_a_stock]);//602
		 
		 new_product.setA_separate(data[index_a_separate]);//607
		 new_product.setA_maxsep(data[index_a_maxsep]);//608
		 new_product.setA_shipping_type(data[index_a_shipping_type]);//609
		 new_product.setA_width(data[index_a_width]);//611
		 new_product.setA_height(data[index_a_height]);//612
		 new_product.setA_weight(data[index_a_weight]);//613
		 new_product.setA_length(data[index_a_length]);//614
		 new_product.setA_intrastat(data[index_a_intrastat]);//615
		 new_product.setA_org_country(data[index_a_org_country]);//616
		 
		 
		 productRepository.save(new_product);
		 
		 return "Products have been created";
			
	}
	
	
	public String product_update(String[] data) {
		
		long check_ean = Long.parseLong(data[index_a_ean]);
		
		Optional<Product> product_obj = productRepository.findById(check_ean);
		Product new_product = product_obj.get();
		
		new_product.setA_id(Long.parseLong(data[index_a_id]));//311
		 new_product.setP_id(Long.parseLong(data[index_p_id]));
		 new_product.setP_nr(data[index_p_nr]);
		 new_product.setP_prefix(data[index_p_prefix]);
		 new_product.setP_name(data[index_p_name]);
		 new_product.setP_name_proper(data[index_p_name_proper]);
		 new_product.setP_name_keyword(data[index_p_name_keyword]);
		 new_product.setP_supplement(data[index_p_supplement]);
		 new_product.setP_text(data[index_p_text]);
		 new_product.setP_feature(data[index_p_feature]);
		 new_product.setP_comp_absatzart_(data[index_p_comp_absatzart_]);
		 new_product.setP_comp_absatzhöhe(data[index_p_comp_absatzhöhe]);
		 new_product.setP_comp_altersgruppe_(data[index_p_comp_altersgruppe_]);
		 new_product.setP_comp_anzahl_schichten_(data[index_p_comp_anzahl_schichten_]);
		 new_product.setP_comp_aermelart_(data[index_p_comp_aermelart_]);
		 new_product.setP_comp_aermellaenge_(data[index_p_comp_aermellaenge_]);
		 new_product.setP_comp_Artikelnummer_(data[index_p_comp_Artikelnummer_]);
		 new_product.setP_comp_atmungsaktivitaet_(data[index_p_comp_atmungsaktivitaet_]);
		 new_product.setP_comp_Ausschnitt_(data[index_p_comp_Ausschnitt_]);
		 new_product.setP_comp_beininnenlaenge_(data[index_p_comp_beininnenlaenge_]);
		 new_product.setP_comp_Beinlänge_(data[index_p_comp_Beinlänge_]);
		 new_product.setP_comp_bh_bügel_(data[index_p_comp_bh_bügel_]);
		 new_product.setP_comp_bh_schale_(data[index_p_comp_bh_schale_]);
		 new_product.setP_comp_bh_style_(data[index_p_comp_bh_style_]);
		 new_product.setP_comp_bh_traegerart_(data[index_p_comp_bh_traegerart_]);
		 new_product.setP_comp_breite_(data[index_p_comp_breite_]);
		 new_product.setP_comp_buegellaenge_(data[index_p_comp_buegellaenge_]);
		 new_product.setP_comp_cluster_(data[index_p_comp_cluster_]);
		 new_product.setP_comp_decksohle_(data[index_p_comp_decksohle_]);
		 new_product.setP_comp_decksohle_schuhe_(data[index_p_comp_decksohle_schuhe_]);
		 new_product.setP_comp_details_(data[index_p_comp_details_]);
		 new_product.setP_comp_durchmesser_(data[index_p_comp_durchmesser_]);
		 new_product.setP_comp_farbe_(data[index_p_comp_farbe_]);
		 new_product.setP_comp_form_(data[index_p_comp_form_]);
		 new_product.setP_comp_fransen_(data[index_p_comp_fransen_]);
		 new_product.setP_comp_futter_(data[index_p_comp_futter_]);
		 new_product.setP_comp_gesamtlaenge_(data[index_p_comp_gesamtlaenge_]);
		 new_product.setP_comp_gesamtlaengexxl_(data[index_p_comp_gesamtlaengexxl_]);
		 new_product.setP_comp_glasbreite_(data[index_p_comp_glasbreite_]);
		 new_product.setP_comp_glashoehe_(data[index_p_comp_glashoehe_]);
		 new_product.setP_comp_groesse_(data[index_p_comp_groesse_]);
		 new_product.setP_comp_groessenflag_(data[index_p_comp_groessenflag_]);
		 new_product.setP_comp_strap_length_(data[index_p_comp_strap_length_]);
		 new_product.setP_comp_hemdkragen_(data[index_p_comp_hemdkragen_]);
		 new_product.setP_comp_Herstellungsland_und_region_(data[index_p_comp_Herstellungsland_und_region_]);
		 new_product.setP_comp_hoehe_(data[index_p_comp_hoehe_]);
		 new_product.setP_comp_jeans_waschung_(data[index_p_comp_jeans_waschung_]);
		 new_product.setP_comp_kapuze_(data[index_p_comp_kapuze_]);
		 new_product.setP_comp_koerbchengroesse_(data[index_p_comp_koerbchengroesse_]);
		 new_product.setP_comp_kragen_(data[index_p_comp_kragen_]);
		 new_product.setP_comp_lagen_(data[index_p_comp_lagen_]);
		 new_product.setP_comp_länge_(data[index_p_comp_länge_]);
		 new_product.setP_comp_laenge_(data[index_p_comp_laenge_]);
		 new_product.setP_comp_laufsohle_schuhe_(data[index_p_comp_laufsohle_schuhe_]);
		 new_product.setP_comp_leibhoehe_(data[index_p_comp_leibhoehe_]);
		 new_product.setP_comp_Linsentechnik_(data[index_p_comp_Linsentechnik_]);
		 new_product.setP_comp_Marke_(data[index_p_comp_Marke_]);
		 new_product.setP_comp_material_(data[index_p_comp_material_]);
		 new_product.setP_comp_material_aermelfutter_(data[index_p_comp_material_aermelfutter_]);
		 new_product.setP_comp_material_aermeloberstoff_(data[index_p_comp_material_aermeloberstoff_]);
		 new_product.setP_comp_material_fuellung_(data[index_p_comp_material_fuellung_]);
		 new_product.setP_comp_material_innenjacke_(data[index_p_comp_material_innenjacke_]);
		 new_product.setP_comp_material_kontrast_(data[index_p_comp_material_kontrast_]);
		 new_product.setP_comp_material_oberstoff_(data[index_p_comp_material_oberstoff_]);
		 new_product.setP_comp_material_rippe_(data[index_p_comp_material_rippe_]);
		 new_product.setP_comp_material_webpelz_(data[index_p_comp_material_webpelz_]);
		 new_product.setP_comp_material_eigenschaft_(data[index_p_comp_material_eigenschaft_]);
		 new_product.setP_comp_muster_(data[index_p_comp_muster_]);
		 new_product.setP_comp_obermaterial_schuhe_(data[index_p_comp_obermaterial_schuhe_]);
		 new_product.setP_comp_oberteillänge_(data[index_p_comp_oberteillänge_]);
		 new_product.setP_comp_ohrring_stil_(data[index_p_comp_ohrring_stil_]);
		 new_product.setP_comp_passform_(data[index_p_comp_passform_]);
		 new_product.setP_comp_pflegehinweise_(data[index_p_comp_pflegehinweise_]);
		 new_product.setP_comp_plateauhoehe_(data[index_p_comp_plateauhoehe_]);
		 new_product.setP_comp_produkttyp_(data[index_p_comp_produkttyp_]);
		 new_product.setP_comp_Rahmenmaterial_(data[index_p_comp_Rahmenmaterial_]);
		 new_product.setP_comp_rock_stil_(data[index_p_comp_rock_stil_]);
		 new_product.setP_comp_bag_size_(data[index_p_comp_bag_size_]);
		 new_product.setP_comp_saison_(data[index_p_comp_saison_]);
		 new_product.setP_comp_saum_(data[index_p_comp_saum_]);
		 new_product.setP_comp_schichten_(data[index_p_comp_schichten_]);
		 new_product.setP_comp_schnitt_(data[index_p_comp_schnitt_]);
		 new_product.setP_comp_schuhspitze_(data[index_p_comp_schuhspitze_]);
		 new_product.setP_comp_Schutz_(data[index_p_comp_Schutz_]);
		 new_product.setP_comp_sohle_(data[index_p_comp_sohle_]);
		 new_product.setP_comp_sportart_(data[index_p_comp_sportart_]);
		 new_product.setP_comp_stegbreite_(data[index_p_comp_stegbreite_]);
		 new_product.setP_comp_Stil_(data[index_p_comp_Stil_]);
		 new_product.setP_comp_Stil_Hose_(data[index_p_comp_Stil_Hose_]);
		 new_product.setP_comp_Stil_Jacken_Mäntel_(data[index_p_comp_Stil_Jacken_Mäntel_]);
		 new_product.setP_comp_stil_jeans_(data[index_p_comp_stil_jeans_]);
		 new_product.setP_comp_stil_srtick_(data[index_p_comp_stil_srtick_]);
		 new_product.setP_comp_Stil_Strick_(data[index_p_comp_Stil_Strick_]);
		 new_product.setP_comp_taschen_(data[index_p_comp_taschen_]);
		 new_product.setP_comp_thema_(data[index_p_comp_thema_]);
		 new_product.setP_comp_tiefe_(data[index_p_comp_tiefe_]);
		 new_product.setP_comp_traeger_(data[index_p_comp_traeger_]);
		 new_product.setP_comp_trägerart_(data[index_p_comp_trägerart_]);
		 new_product.setP_comp_umfang_(data[index_p_comp_umfang_]);
		 new_product.setP_comp_unterteillaenge_(data[index_p_comp_unterteillaenge_]);
		 new_product.setP_comp_ursprungsland_(data[index_p_comp_ursprungsland_]);
		 new_product.setP_comp_vek_(data[index_p_comp_vek_]);
		 new_product.setP_comp_vek_online_(data[index_p_comp_vek_online_]);
		 new_product.setP_comp_verschluss_(data[index_p_comp_verschluss_]);
		 new_product.setP_comp_volumen_(data[index_p_comp_volumen_]);
		 new_product.setP_comp_waisthoehe_(data[index_p_comp_waisthoehe_]);
		 new_product.setP_comp_Wasserdichtigkeit_(data[index_p_comp_Wasserdichtigkeit_]);
		 new_product.setP_comp_wassersaeule_(data[index_p_comp_wassersaeule_]);
		 new_product.setP_comp_Winddichtigkeit_(data[index_p_comp_Winddichtigkeit_]);
		 new_product.setP_comp_zielgruppe_(data[index_p_comp_zielgruppe_]);
		 new_product.setP_brand(data[index_p_brand]);
		 new_product.setP_active_lade_(data[index_p_active_lade_]);//142
		 new_product.setP_active_cuglago1_(data[index_p_active_cuglago1_]);//143
		 new_product.setP_catpri_cuglago1_(data[index_p_catpri_cuglago1_]);//144
		 new_product.setP_catsec_cuglago1_0_(data[index_p_catsec_cuglago1_0_]);//145
		 new_product.setP_bullet_0_(data[index_p_bullet_0_]);//278
		 new_product.setP_bullet_1_(data[index_p_bullet_1_]);//279
		 new_product.setP_bullet_2_(data[index_p_bullet_2_]);//280
		 new_product.setP_bullet_3_(data[index_p_bullet_3_]);//281
		 new_product.setP_bullet_4_(data[index_p_bullet_4_]);//282
		 new_product.setP_bullet_5_(data[index_p_bullet_5_]);//283
		 new_product.setP_bullet_6_(data[index_p_bullet_6_]);//284
		 new_product.setP_bullet_7_(data[index_p_bullet_7_]);//285
		 new_product.setP_bullet_8_(data[index_p_bullet_8_]);//286
		 new_product.setP_bullet_9_(data[index_p_bullet_9_]);//287
		 new_product.setP_bullet_10_(data[index_p_bullet_10_]);//288
		 new_product.setP_bullet_11_(data[index_p_bullet_11_]);//289
		 new_product.setP_bullet_12_(data[index_p_bullet_12_]);//290
		 new_product.setP_bullet_13_(data[index_p_bullet_13_]);//291
		 new_product.setP_bullet_14_(data[index_p_bullet_14_]);//292
		 new_product.setP_bullet_15_(data[index_p_bullet_15_]);//293
		 new_product.setP_bullet_16_(data[index_p_bullet_16_]);//294
		 new_product.setP_bullet_17_(data[index_p_bullet_17_]);//295
		 new_product.setP_bullet_18_(data[index_p_bullet_18_]);//296
		 new_product.setP_bullet_19_(data[index_p_bullet_19_]);//297
		 new_product.setP_bullet_20_(data[index_p_bullet_20_]);//298
		 new_product.setP_bullet_21_(data[index_p_bullet_21_]);//299
		 new_product.setP_bullet_22_(data[index_p_bullet_22_]);//300
		 new_product.setP_bullet_23_(data[index_p_bullet_23_]);//301
		 new_product.setP_bullet_24_(data[index_p_bullet_24_]);//302
		 new_product.setP_bullet_25_(data[index_p_bullet_25_]);//303
		 new_product.setP_bullet_26_(data[index_p_bullet_26_]);//304
		 new_product.setP_bullet_27_(data[index_p_bullet_27_]);//305
		 new_product.setP_bullet_28_(data[index_p_bullet_28_]);//306
		 new_product.setP_bullet_29_(data[index_p_bullet_29_]);//307
		 new_product.setP_bullet_30_(data[index_p_bullet_30_]);//308
		 new_product.setP_bullet_31_(data[index_p_bullet_31_]);//309
		 new_product.setP_bullet_32_(data[index_p_bullet_32_]);//310
		 new_product.setA_nr(data[index_a_nr]);//312
		 new_product.setA_active(data[index_a_active]);//313
		 new_product.setA_nr2(data[index_a_nr2]);//314
		 
		 new_product.setA_prodnr(data[index_a_prodnr]);//316
		 new_product.setArtikelbezeichnung(data[index_Artikelbezeichnung]);//317
		 new_product.setA_comp_farbe_(data[index_a_comp_farbe_]);//318
		 new_product.setA_comp_groesse_(data[index_a_comp_groesse_]);//319
		 new_product.setA_comp_koerbchengroesse_(data[index_a_comp_koerbchengroesse_]);//320
		 new_product.setA_comp_laenge_(data[index_a_comp_laenge_]);//321
		 new_product.setA_comp_otto_weite_(data[index_a_comp_otto_weite_]);//322
		 new_product.setAp_comp_ebay_produkt_name_(data[index_ap_comp_ebay_produkt_name_]);//323
		 new_product.setAp_comp_ursprungsland_(data[index_ap_comp_ursprungsland_]);//324
		 new_product.setA_media_image_0_(data[index_a_media_image_0_]);//545
		 new_product.setA_media_image_1_(data[index_a_media_image_1_]);//546
		 new_product.setA_media_image_2_(data[index_a_media_image_2_]);//547
		 new_product.setA_media_image_3_(data[index_a_media_image_3_]);//548
		 new_product.setA_media_image_4_(data[index_a_media_image_4_]);//549
		 new_product.setA_media_image_5_(data[index_a_media_image_5_]);//550
		 new_product.setA_media_image_6_(data[index_a_media_image_6_]);//551
		 new_product.setA_media_image_7_(data[index_a_media_image_7_]);//552
		 new_product.setA_media_image_8_(data[index_a_media_image_8_]);//553
		 new_product.setA_media_image_9_(data[index_a_media_image_9_]);//554
		 new_product.setA_media_image_10_(data[index_a_media_image_10_]);//555
		 new_product.setA_media_image_11_(data[index_a_media_image_11_]);//556
		 new_product.setA_media_image_12_(data[index_a_media_image_12_]);//557
		 new_product.setA_media_image_13_(data[index_a_media_image_13_]);//558
		 new_product.setA_media_image_14_(data[index_a_media_image_14_]);//559
		 new_product.setA_media_image_15_(data[index_a_media_image_15_]);//560
		 new_product.setA_media_image_16_(data[index_a_media_image_16_]);//561
		 new_product.setA_media_image_17_(data[index_a_media_image_17_]);//562
		 new_product.setA_media_image_18_(data[index_a_media_image_18_]);//563
		 new_product.setA_ek(data[index_a_ek]);//601
		 new_product.setA_stock(data[index_a_stock]);//602
		 new_product.setA_separate(data[index_a_separate]);//607
		 new_product.setA_maxsep(data[index_a_maxsep]);//608
		 new_product.setA_shipping_type(data[index_a_shipping_type]);//609
		 new_product.setA_width(data[index_a_width]);//611
		 new_product.setA_height(data[index_a_height]);//612
		 new_product.setA_weight(data[index_a_weight]);//613
		 new_product.setA_length(data[index_a_length]);//614
		 new_product.setA_intrastat(data[index_a_intrastat]);//615
		 new_product.setA_org_country(data[index_a_org_country]);//616
		 
		 return "Products have been created";
		
	}
	
	
	public void set_index() {
		
		String[] data = null;
		String[] data_check = null;
		List<String[]> allRows = null;
		
		try {
			@SuppressWarnings({"deprecation", "resource"})
			CSVReader reader = new CSVReader(new FileReader(fileNewLocation), ';');
			
			allRows = reader.readAll();
			
			data = allRows.get(0);
			data_check = allRows.get(1);
			
			for (int i = 0; i < data.length; ) {
				int temp;
				
				String dta = data[i];
				
				
				 if(dta.equals("a_ean"))
				 {
					 index_a_ean=i; 
				 }
				
				 else if(dta.equals("a_id"))
				 {
					 index_a_id=i;
				 }
				 
				 
				 
				 
				 else if(dta.equals("p_id"))
				 {
					 index_p_id=i; 
				 }
					 
				 
				 
				 else if(dta.equals("p_nr"))
				 {
					 index_p_nr=i;
				 }
				 
				 else if(dta.equals("p_prefix"))
				 {
					 index_p_prefix=i;
				 }
				 else if(dta.equals("p_name"))
				 {
					 index_p_name=i;
				 }
				 else if(dta.equals("p_name_proper"))
				 {
					 index_p_name_proper=i;
				 }
				 else if(dta.equals("p_name_keyword"))
				 {
					 index_p_name_keyword=i;
				 }
				 else if(dta.equals("p_supplement"))
				 {
					 index_p_supplement=i;
				 }
				 else if(dta.equals("p_text"))
				 {
					 index_p_text=i;
				 }
				 else if(dta.equals("p_feature"))
				 {
					 index_p_feature=i;
				 }
				 
				 
				 else if(dta.equals("p_comp[absatzart]"))
				 {
					 index_p_comp_absatzart_=i;
				 }
				//--------------------------------------------------------------------------------------
				 else if(dta.equals("p_comp[absatzh?he]"))
				 {
					 index_p_comp_absatzhöhe=i;
				 }
				//--------------------------------------------------------------------------------------
				 else if(dta.equals("p_comp[altersgruppe]"))
				 {
					 index_p_comp_altersgruppe_=i;
				 }
				 
				 
				 
				 else if(dta.equals("p_comp[anzahl_schichten]"))
				 {
					 index_p_comp_anzahl_schichten_=i;
				 }
				 else if(dta.equals("p_comp[aermelart]"))
				 {
					 index_p_comp_aermelart_=i;
				 }
				 else if(dta.equals("p_comp[aermellaenge]"))
				 {
					 index_p_comp_aermellaenge_=i;
				 }
				 else if(dta.equals("p_comp[Artikelnummer]"))
				 {
					 index_p_comp_Artikelnummer_=i;
				 }
				 else if(dta.equals("p_comp[atmungsaktivitaet]"))
				 {
					 index_p_comp_atmungsaktivitaet_=i;
				 }
				 else if(dta.equals("p_comp[Ausschnitt]"))
				 {
					 index_p_comp_Ausschnitt_=i;
				 }
				 else if(dta.equals("p_comp[beininnenlaenge]"))
				 {
					 index_p_comp_beininnenlaenge_=i;
				 }
				 else if(dta.equals("p_comp[Beinlänge]"))
				 {
					 index_p_comp_Beinlänge_=i;
				 }
				//--------------------------------------------------------------------------------------
				 else if(dta.equals("p_comp[bh-b?gel]"))
				 {
					 index_p_comp_bh_bügel_=i;
				 }
				//--------------------------------------------------------------------------------------
				 else if(dta.equals("p_comp[bh-schale]"))
				 {
					 index_p_comp_bh_schale_=i;
				 }
				 else if(dta.equals("p_comp[bh-style]"))
				 {
					 index_p_comp_bh_style_=i;
				 }
				 else if(dta.equals("p_comp[bh-traegerart]"))
				 {
					 index_p_comp_bh_traegerart_=i;
				 }
				 else if(dta.equals("p_comp[breite]"))
				 {
					 index_p_comp_breite_=i;
				 }
				 else if(dta.equals("p_comp[buegellaenge]"))
				 {
					 index_p_comp_buegellaenge_=i;
				 }
				 else if(dta.equals("p_comp[cluster]"))
				 {
					 index_p_comp_cluster_=i;
				 }
				 else if(dta.equals("p_comp[decksohle]"))
				 {
					 index_p_comp_decksohle_=i;
				 }
				 else if(dta.equals("p_comp[decksohle_schuhe]"))
				 {
					 index_p_comp_decksohle_schuhe_=i;
				 }
				 else if(dta.equals("p_comp[details]"))
				 {
					 index_p_comp_details_=i;
				 }
				 else if(dta.equals("p_comp[durchmesser]"))
				 {
					 index_p_comp_durchmesser_=i;
				 }
				 else if(dta.equals("p_comp[farbe]"))
				 {
					 index_p_comp_farbe_=i;
				 }
				 else if(dta.equals("p_comp[form]"))
				 {
					 index_p_comp_form_=i;
				 }
				 else if(dta.equals("p_comp[fransen]"))
				 {
					 index_p_comp_fransen_=i;
				 }
				 else if(dta.equals("p_comp[funktionen]"))
				 {
					 index_p_comp_funktionen_=i;
				 }
				 else if(dta.equals("p_comp[futter]"))
				 {
					 index_p_comp_futter_=i;
				 }
				 else if(dta.equals("p_comp[gesamtlaenge]"))
				 {
					 index_p_comp_gesamtlaenge_=i;
				 }
				 else if(dta.equals("p_comp[gesamtlaengexxl]"))
				 {
					 index_p_comp_gesamtlaengexxl_=i;
				 }
				 else if(dta.equals("p_comp[glasbreite]"))
				 {
					 index_p_comp_glasbreite_=i;
				 }
				 else if(dta.equals("p_comp[glashoehe]"))
				 {
					 index_p_comp_glashoehe_=i;
				 }
				 else if(dta.equals("p_comp[groesse]"))
				 {
					 index_p_comp_groesse_=i;
				 }
				 else if(dta.equals("p_comp[groessenflag]"))
				 {
					 index_p_comp_groessenflag_=i;
				 }
				 else if(dta.equals("p_comp[strap_length]"))
				 {
					 index_p_comp_strap_length_=i;
				 }
				 else if(dta.equals("p_comp[hemdkragen]"))
				 {
					 index_p_comp_hemdkragen_=i;
				 }
				 else if(dta.equals("p_comp[Herstellungsland und -region]"))
				 {
					 index_p_comp_Herstellungsland_und_region_=i;
				 }
				 else if(dta.equals("p_comp[hoehe]"))
				 {
					 index_p_comp_hoehe_=i;
				 }
				 else if(dta.equals("p_comp[jeans_waschung]"))
				 {
					 index_p_comp_jeans_waschung_=i;
				 }
				 else if(dta.equals("p_comp[kapuze]"))
				 {
					 index_p_comp_kapuze_=i;
				 }
				 else if(dta.equals("p_comp[koerbchengroesse]"))
				 {
					 index_p_comp_koerbchengroesse_=i;
				 }
				 else if(dta.equals("p_comp[kragen]"))
				 {
					 index_p_comp_kragen_=i;
				 }
				 else if(dta.equals("p_comp[lagen]"))
				 {
					 index_p_comp_lagen_=i;
				 }
				 
				 //--------------------------------------------------------------------------------------
				 else if(dta.equals("p_comp[l?nge]"))
				 {
					 index_p_comp_länge_=i;
				 }
				 //--------------------------------------------------------------------------------------
				 else if(dta.equals("p_comp[laenge]"))
				 {
					 index_p_comp_laenge_=i;
				 }
				 else if(dta.equals("p_comp[laufsohle_schuhe]"))
				 {
					 index_p_comp_laufsohle_schuhe_=i;
				 }
				 else if(dta.equals("p_comp[leibhoehe]"))
				 {
					 index_p_comp_leibhoehe_=i;
				 }
				 else if(dta.equals("p_comp[Linsentechnik]"))
				 {
					 index_p_comp_Linsentechnik_=i;
				 }
				 else if(dta.equals("p_comp[Marke]"))
				 {
					 index_p_comp_Marke_=i;
				 }
				 else if(dta.equals("p_comp[material]"))
				 {
					 index_p_comp_material_=i;
				 }
				 else if(dta.equals("p_comp[material_aermelfutter]"))
				 {
					 index_p_comp_material_aermelfutter_=i;
				 }
				 else if(dta.equals("p_comp[material_aermeloberstoff]"))
				 {
					 index_p_comp_material_aermeloberstoff_=i;
				 }
				 else if(dta.equals("p_comp[material_fuellung]"))
				 {
					 index_p_comp_material_fuellung_=i;
				 }
				 else if(dta.equals("p_comp[material_innenjacke]"))
				 {
					 index_p_comp_material_innenjacke_=i;
				 }
				 else if(dta.equals("p_comp[material_kontrast]"))
				 {
					 index_p_comp_material_kontrast_=i;
				 }
				 else if(dta.equals("p_comp[material_oberstoff]"))
				 {
					 index_p_comp_material_oberstoff_=i;
				 }
				 else if(dta.equals("p_comp[material_rippe]"))
				 {
					 index_p_comp_material_rippe_=i;
				 }
				 else if(dta.equals("p_comp[material_webpelz]"))
				 {
					 index_p_comp_material_webpelz_=i;
				 }
				 else if(dta.equals("p_comp[material_eigenschaft]"))
				 {
					 index_p_comp_material_eigenschaft_=i;
				 }
				 else if(dta.equals("p_comp[muster]"))
				 {
					 index_p_comp_muster_=i;
				 }
				 else if(dta.equals("p_comp[obermaterial_schuhe]"))
				 {
					 index_p_comp_obermaterial_schuhe_=i;
				 }
				 //--------------------------------------------------------------------------------------
				 else if(dta.equals("p_comp[oberteill?nge]"))
				 {
					 index_p_comp_oberteillänge_=i;
				 }
				 //--------------------------------------------------------------------------------------
				 else if(dta.equals("p_comp[ohrring-stil]"))
				 {
					 index_p_comp_ohrring_stil_=i;
				 }
				 else if(dta.equals("p_comp[passform]"))
				 {
					 index_p_comp_passform_=i;
				 }
				 else if(dta.equals("p_comp[pflegehinweise]"))
				 {
					 index_p_comp_pflegehinweise_=i;
				 }
				 else if(dta.equals("p_comp[plateauhoehe]"))
				 {
					 index_p_comp_plateauhoehe_=i;
				 }
				 else if(dta.equals("p_comp[produkttyp]"))
				 {
					 index_p_comp_produkttyp_=i;
				 }
				 else if(dta.equals("p_comp[Rahmenmaterial]"))
				 {
					 index_p_comp_Rahmenmaterial_=i;
				 }
				 else if(dta.equals("p_comp[rock_stil]"))
				 {
					 index_p_comp_rock_stil_=i;
				 }
				 else if(dta.equals("p_comp[bag_size]"))
				 {
					 index_p_comp_bag_size_=i;
				 }
				 else if(dta.equals("p_comp[saison]"))
				 {
					 index_p_comp_saison_=i;
				 }
				 else if(dta.equals("p_comp[saum]"))
				 {
					 index_p_comp_saum_=i;
				 }
				 else if(dta.equals("p_comp[schichten]"))
				 {
					 index_p_comp_schichten_=i;
				 }
				 else if(dta.equals("p_comp[schnitt]"))
				 {
					 index_p_comp_schnitt_=i;
				 }
				 else if(dta.equals("p_comp[schuhspitze]"))
				 {
					 index_p_comp_schuhspitze_=i;
				 }
				 else if(dta.equals("p_comp[Schutz]"))
				 {
					 index_p_comp_Schutz_=i;
				 }
				 else if(dta.equals("p_comp[sohle]"))
				 {
					 index_p_comp_sohle_=i;
				 }
				 else if(dta.equals("p_comp[sportart]"))
				 {
					 index_p_comp_sportart_=i;
				 }
				 else if(dta.equals("p_comp[stegbreite]"))
				 {
					 index_p_comp_stegbreite_=i;
				 }
				 else if(dta.equals("p_comp[Stil]"))
				 {
					 index_p_comp_Stil_=i;
				 }
				 else if(dta.equals("p_comp[Stil Hose]"))
				 {
					 index_p_comp_Stil_Hose_=i;
				 }
				 //--------------------------------------------------------------------------------------
				 else if(dta.equals("p_comp[Stil Jacken + M?ntel]"))
				 {
					 index_p_comp_Stil_Jacken_Mäntel_=i;
				 }
				 //--------------------------------------------------------------------------------------
				 else if(dta.equals("p_comp[stil_jeans]"))
				 {
					 index_p_comp_stil_jeans_=i;
				 }
				 else if(dta.equals("p_comp[stil srtick]"))
				 {
					 index_p_comp_stil_srtick_=i;
				 }
				 else if(dta.equals("p_comp[Stil Strick]"))
				 {
					 index_p_comp_Stil_Strick_=i;
				 }
				 else if(dta.equals("p_comp[taschen]"))
				 {
					 index_p_comp_taschen_=i;
				 }
				 else if(dta.equals("p_comp[thema]"))
				 {
					 index_p_comp_thema_=i;
				 }
				 else if(dta.equals("p_comp[tiefe]"))
				 {
					 index_p_comp_tiefe_=i;
				 }
				 else if(dta.equals("p_comp[traeger]"))
				 {
					 index_p_comp_traeger_=i;
				 }
				 //--------------------------------------------------------------------------------------
				 else if(dta.equals("p_comp[tr?gerart]"))
				 {
					 index_p_comp_trägerart_=i;
				 }
				 //--------------------------------------------------------------------------------------
				 else if(dta.equals("p_comp[umfang]"))
				 {
					 index_p_comp_umfang_=i;
				 }
				 else if(dta.equals("p_comp[unterteillaenge]"))
				 {
					 index_p_comp_unterteillaenge_=i;
				 }
				 else if(dta.equals("p_comp[ursprungsland]"))
				 {
					 index_p_comp_ursprungsland_=i;
				 }
				 else if(dta.equals("p_comp[vek]"))
				 {
					 index_p_comp_vek_=i;
				 }
				 else if(dta.equals("p_comp[vek_online]"))
				 {
					 index_p_comp_vek_online_=i;
				 }
				 else if(dta.equals("p_comp[verschluss]"))
				 {
					 index_p_comp_verschluss_=i;
				 }
				 else if(dta.equals("p_comp[volumen]"))
				 {
					 index_p_comp_volumen_=i;
				 }
				 else if(dta.equals("p_comp[waisthoehe]"))
				 {
					 index_p_comp_waisthoehe_=i;
				 }
				 else if(dta.equals("p_comp[Wasserdichtigkeit]"))
				 {
					 index_p_comp_Wasserdichtigkeit_=i;
				 }
				 else if(dta.equals("p_comp[wassersaeule]"))
				 {
					 index_p_comp_wassersaeule_=i;
				 }
				 else if(dta.equals("p_comp[Winddichtigkeit]"))
				 {
					 index_p_comp_Winddichtigkeit_=i;
				 }
				 else if(dta.equals("p_comp[zielgruppe]"))
				 {
					 index_p_comp_zielgruppe_=i;
				 }
				 else if(dta.equals("p_brand"))
				 {
					 index_p_brand=i;
				 }
				 else if(dta.equals("p_active[lade]"))
				 {
					 index_p_active_lade_=i;
				 }
				 else if(dta.equals("p_active[cuglago1]"))
				 {
					 index_p_active_cuglago1_=i;
				 }
				 else if(dta.equals("p_catpri[cuglago1]"))
				 {
					 index_p_catpri_cuglago1_=i;
				 }
				 else if(dta.equals("p_catsec[cuglago1]{0}"))
				 {
					 index_p_catsec_cuglago1_0_=i;
				 }
				  else if(dta.equals("p_bullet{0}"))
				 {
					  index_p_bullet_0_=i;
				 }

				  else if(dta.equals("p_bullet{1}"))
				 {
					  index_p_bullet_1_=i;
				 }
				 else if(dta.equals("p_bullet{2}"))
				 {
					 index_p_bullet_2_=i;
				 }
				  else if(dta.equals("p_bullet{3}"))
				 {
					  index_p_bullet_3_=i;
				 }
				 else if(dta.equals("p_bullet{4}"))
				 {
					 index_p_bullet_4_=i;
				 }
				  else if(dta.equals("p_bullet{5}"))
				 {
					  index_p_bullet_5_=i;
				 }
				 else if(dta.equals("p_bullet{6}"))
				 {
					 index_p_bullet_5_=i;
				 }
				  else if(dta.equals("p_bullet{7}"))
				 {
					  index_p_bullet_5_=i;
				 }
				 else if(dta.equals("p_bullet{8}"))
				 {
					 index_p_bullet_5_=i;
				 }
				  else if(dta.equals("p_bullet{9}"))
				 {
					  index_p_bullet_5_=i;
				 }
				 else if(dta.equals("p_bullet{10}"))
				 {
					 index_p_bullet_1_=i;
				 }
				  else if(dta.equals("p_bullet{11}"))
				 {
					  index_p_bullet_1_=i;
				 }
				 else if(dta.equals("p_bullet{12}"))
				 {
					 index_p_bullet_1_=i;
				 }
				  else if(dta.equals("p_bullet{13}"))
				 {
					  index_p_bullet_1_=i;
				 }
				 else if(dta.equals("p_bullet{14}"))
				 {
					 index_p_bullet_1_=i;
				 }
				  else if(dta.equals("p_bullet{15}"))
				 {
					  index_p_bullet_1_=i;
				 }
				 else if(dta.equals("p_bullet{16}"))
				 {
					 index_p_bullet_1_=i;
				 }
				  else if(dta.equals("p_bullet{17}"))
				 {
					  index_p_bullet_1_=i;
				 }
				 else if(dta.equals("p_bullet{18}"))
				 {
					 index_p_bullet_1_=i;
				 }
				  else if(dta.equals("p_bullet{19}"))
				 {
					  index_p_bullet_1_=i;
				 }
				 else if(dta.equals("p_bullet{20}"))
				 {
					 index_p_bullet_21_=i;
				 }
				  else if(dta.equals("p_bullet{21}"))
				 {
					  index_p_bullet_22_=i;
				 }
				 else if(dta.equals("p_bullet{22}"))
				 {
					 index_p_bullet_23_=i;
				 }
				  else if(dta.equals("p_bullet{23}"))
				 {
					  index_p_bullet_24_=i;
				 }
				 else if(dta.equals("p_bullet{24}"))
				 {
					 index_p_bullet_25_=i;
				 }
				  else if(dta.equals("p_bullet{25}"))
				 {
					  index_p_bullet_26_=i;
				 }
				 else if(dta.equals("p_bullet{26}"))
				 {
					 index_p_bullet_27_=i;
				 }
				  else if(dta.equals("p_bullet{27}"))
				 {
					  index_p_bullet_28_=i;
				 }
				 else if(dta.equals("p_bullet{28}"))
				 {
					 index_p_bullet_29_=i;
				 }
				  else if(dta.equals("p_bullet{29}"))
				 {
					  index_p_bullet_30_=i;
				 }
				 else if(dta.equals("p_bullet{30}"))
				 {
					 index_p_bullet_30_=i;
				 }
				  else if(dta.equals("p_bullet{31}"))
				 {
					  index_p_bullet_31_=i;
				 }
				 else if(dta.equals("p_bullet{32}"))
				 {
					 index_p_bullet_32_=i;
				 }
				  else if(dta.equals("a_id"))
				 {
					  index_a_id=i;
				 }
				
				  else if(dta.equals("a_nr"))
				 {
					  index_a_nr=i;
				 }
				 else if(dta.equals("a_active"))
				 {
					 index_a_active=i;
				 }
				  else if(dta.equals("a_nr2"))
				 {
					  index_a_nr2=i;
				 }
				 else if(dta.equals("a_prodnr"))
				 {
					 index_a_prodnr=i;
				 }
				  else if(dta.equals("Artikelbezeichnung"))
				 {
					  index_Artikelbezeichnung=i;
				 }
				 else if(dta.equals("a_comp[farbe]"))
				 {
					 index_a_comp_farbe_=i;
				 }
				  else if(dta.equals("a_comp[groesse]"))
				 {
					  index_a_comp_groesse_=i;
				 }
				 else if(dta.equals("a_comp[koerbchengroesse]"))
				 {
					 index_a_comp_koerbchengroesse_=i;
				 }
				  else if(dta.equals("a_comp[laenge]"))
				 {
					  index_a_comp_laenge_=i;
				 }
				 else if(dta.equals("a_comp[otto_weite]"))
				 {
					 index_a_comp_otto_weite_=i;
				 }
				  else if(dta.equals("ap_comp[ebay_produkt_name]"))
				 {
					  index_ap_comp_ebay_produkt_name_=i;
				 }
				 else if(dta.equals("ap_comp[ursprungsland]"))
				 {
					 index_ap_comp_ursprungsland_=i;
				 }
				  else if(dta.equals("a_media[image]{0}"))
				 {
					  index_a_media_image_0_=i;
				 }
				 else if(dta.equals("a_media[image]{1}"))
				 {
					 index_a_media_image_1_=i;
				 }
				  else if(dta.equals("a_media[image]{2}"))
				 {
					  index_a_media_image_2_=i;
				 }
				 else if(dta.equals("a_media[image]{3}"))
				 {
					 index_a_media_image_3_=i;
				 }
				  else if(dta.equals("a_media[image]{4}"))
				 {
					  index_a_media_image_4_=i;
				 }
				 else if(dta.equals("a_media[image]{5}"))
				 {
					 index_a_media_image_5_=i;
				 }
				  else if(dta.equals("a_media[image]{6}"))
				 {
					  index_a_media_image_6_=i;
				 }
				 else if(dta.equals("a_media[image]{7}"))
				 {
					 index_a_media_image_7_=i;
				 }
				  else if(dta.equals("a_media[image]{8}"))
				 {
					  index_a_media_image_8_=i;
				 }
				 else if(dta.equals("a_media[image]{9}"))
				 {
					 index_a_media_image_9_=i;
				 }
				  else if(dta.equals("a_media[image]{10}"))
				 {
					  index_a_media_image_10_=i;
				 }
				 else if(dta.equals("a_media[image]{11}"))
				 {
					 index_a_media_image_11_=i;
				 }
				  else if(dta.equals("a_media[image]{12}"))
				 {
					  index_a_media_image_12_=i;
				 }
				 else if(dta.equals("a_media[image]{13}"))
				 {
					 index_a_media_image_13_=i;
				 }
				  else if(dta.equals("a_media[image]{14}"))
				 {
					  index_a_media_image_14_=i;
				 }
				 else if(dta.equals("a_media[image]{15}"))
				 {
					 index_a_media_image_15_=i;
				 }
				  else if(dta.equals("a_media[image]{16}"))
				 {
					  index_a_media_image_16_=i;
				 }
				 else if(dta.equals("a_media[image]{17}"))
				 {
					 index_a_media_image_17_=i;
				 }
				  else if(dta.equals("a_media[image]{18}"))
				 {
					  index_a_media_image_18_=i;
				 }
				  else if(dta.equals("a_ek"))
				 {
					  index_a_ek=i;
				 }
				 else if(dta.equals("a_stock"))
				 {
					 index_a_stock=i;
				 }
				  else if(dta.equals("a_separate"))
				 {
					  index_a_separate=i;
				 }
				 else if(dta.equals("a_maxsep"))
				 {
					 index_a_maxsep=i;
				 }
				  else if(dta.equals("a_shipping_type"))
				 {
					  index_a_shipping_type=i;
				 }
				 else if(dta.equals("a_width"))
				 {
					 index_a_width=i;
				 }
				  else if(dta.equals("a_height"))
				 {
					  index_a_height=i;
				 }
				 else if(dta.equals("a_weight"))
				 {
					 index_a_weight=i;
				 }
				  else if(dta.equals("a_length"))
				 {
					  index_a_length=i;
				 }
				 else if(dta.equals("a_intrastat"))
				 {
					 index_a_intrastat=i;
				 }
				 else if(dta.equals("a_org_country"))
				 {
					 index_a_org_country=i;
				 }
				
				 else
					 temp = i;
					i++;
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		//long check_ean = Long.parseLong(data_check[index_a_ean]);
		/*if(!data_check[index_a_ean].matches("\\d*")) {
			throw new ResourceNotFoundException("id error in String");
		}*/
		
	}
	
	

	public String deleteFile()
	{
		File file = new File(fileLocation); 
        
        if(file.delete()) 
        { 
            System.out.println("File deleted successfully"); 
            return "file deleted";
        } 
        else
        { 
            System.out.println("Failed to delete the file"); 
            
            return "file deleted failed";
        } 
	}
	
	
	
	public String[] test() {
		
		String[] data = null;
		List<String[]> allRows = null;
		
		try {
			@SuppressWarnings({"deprecation","resource"})
			CSVReader reader = new CSVReader(new FileReader(fileNewLocation));
			
			allRows = reader.readAll();
			data = allRows.get(2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return data;
	}
	

}//end of service class
