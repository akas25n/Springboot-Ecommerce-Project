package com.lot.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lot.model.Lot;
import com.lot.model.Stocklots_Offer;
import com.lot.repository.LotRepository;
import com.lot.repository.Stocklots_Offer_Repository;
import com.opencsv.CSVReader;

@Service
public class Stocklots_Offer_Service {
	
	@Autowired
	Stocklots_Offer_Repository stocklots_Offer_Repository;
	
	@Autowired
	LotRepository lotRepository;
	
	private String fileLocation; // for deleting
	private String fileNewLocation; // for reading
	
	private String msg;

	
	//----------------------------------------------------------index----------------------------------------------
	int index_EAN;
	int index_ART_NR;
	int index_GROESSE;
	//----------------------------
	int index_PROD_NAME;
	//----------------------------
	int index_FARBE;
	int index_UVP;
	int index_BESTAND;
	int index_PREIS;
	//----------------------------
	int index_BRAND;
	int index_GENDER;
	int index_PROD_MATERIAL;
	int index_PROD_TEXT;
	int index_IMAGE_1;
	int index_IMAGE_2;
	int index_IMAGE_3;
	//----------------------------
	int index_ANGEBOT_NR;
	
	int temp;
	
	public File upload_file(MultipartFile file) throws IOException{
		
		File targetFile = new File(System.getProperty("java.io.tmpdir") + "/" + System.currentTimeMillis() + file.getOriginalFilename());
        file.transferTo(targetFile);
        
		return targetFile;
	}
	

	public String save_offer(File file) {
		String[] data = null;
		List<String[]> allRows=null;
		//System.out.println("start try \n");
		try {
			@SuppressWarnings({"deprecation", "resource"})
			CSVReader reader = new CSVReader(new FileReader(file),';'); 
			
			allRows = reader.readAll();
			
			//System.out.println("start for loop \n");
			//---------------------------------------------------------------------------------------------problem is coming from here
			for(int i = 1; i< allRows.size(); i++) {
				
				data= allRows.get(i);
				long ch_ean = Long.parseLong(data[index_EAN]);
				Stocklots_Offer offer = stocklots_Offer_Repository.find_BY_EAN(ch_ean);
				
				if (offer == null) {
					product_create(data);
				}
				else if (!(offer == null)) {
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
		
		Stocklots_Offer offer = new Stocklots_Offer();
		
		offer.setEAN(Long.parseLong(data[index_EAN]));
		offer.setART_NR(data[index_ART_NR]);
		offer.setGROESSE(data[index_GROESSE]);
		offer.setFARBE(data[index_FARBE]);
		//--------------------------------------------
		offer.setPROD_NAME(data[index_PROD_NAME]);
		//--------------------------------------------
		offer.setBESTAND(Integer.parseInt(data[index_BESTAND]));
		//lager.setRESERVIERT(data[index_RESERVIERT]);
		String temp_price = data[index_PREIS].replace(",", ".");
		
		offer.setPREIS(Double.parseDouble(temp_price));
		String temp_uvp =data[index_UVP].replace(",", ".");
		offer.setUVP(Double.parseDouble(temp_uvp));
		
		//---------------------------------------------
		offer.setBRAND(data[index_BRAND]);
		offer.setGENDER(data[index_GENDER]);
		offer.setPROD_MATERIAL(data[index_PROD_MATERIAL]);
		
		// replacing special characters
		String temp_text = data[index_PROD_TEXT].replace("ß,ü,ö,ä","?");
		offer.setPROD_TEXT(temp_text);
		
		offer.setIMAGE_1(data[index_IMAGE_1]);
		offer.setIMAGE_2(data[index_IMAGE_2]);
		offer.setIMAGE_3(data[index_IMAGE_3]);
		//--------------------------------------------
		offer.setANGEBOT_NR(data[index_ANGEBOT_NR]);
		
		stocklots_Offer_Repository.save(offer);
		
		return "Products have been created";
	}
	
	
	public String product_update(String[] data) {
		
		long check_ean = Long.parseLong(data[index_EAN]);
		
		Optional<Stocklots_Offer> obj = stocklots_Offer_Repository.findById(check_ean);
		Stocklots_Offer offer = obj.get();
		
		offer.setEAN(Long.parseLong(data[index_EAN]));
		offer.setART_NR(data[index_ART_NR]);
		offer.setGROESSE(data[index_GROESSE]);
		offer.setFARBE(data[index_FARBE]);
		//--------------------------------------------
		offer.setPROD_NAME(data[index_PROD_NAME]);
		//--------------------------------------------
		offer.setBESTAND(Integer.parseInt(data[index_BESTAND]));
		//lager.setRESERVIERT(data[index_RESERVIERT]);
		
		String temp_price = data[index_PREIS].replace(",", ".");
		offer.setPREIS(Double.parseDouble(temp_price));
		
		String temp_uvp =data[index_UVP].replace(",", ".");
		offer.setUVP(Double.parseDouble(temp_uvp));
		
		//---------------------------------------------
		offer.setBRAND(data[index_BRAND]);
		offer.setGENDER(data[index_GENDER]);
		offer.setPROD_MATERIAL(data[index_PROD_MATERIAL]);
		
		// replacing special characters
		String temp_text = data[index_PROD_TEXT].replace("ß,ü,ö,ä","?");
		offer.setPROD_TEXT(temp_text);
		
		//offer.setPROD_TEXT(data[index_PROD_TEXT]);
		offer.setIMAGE_1(data[index_IMAGE_1]);
		offer.setIMAGE_2(data[index_IMAGE_2]);
		offer.setIMAGE_3(data[index_IMAGE_3]);
		//--------------------------------------------
		offer.setANGEBOT_NR(data[index_ANGEBOT_NR]);
		
		return "Products have been created";
	}
	
	public void set_index(File file) {
		
		String[] data = null;
		String[] data_check = null;
		List<String[]> allRows = null;
		
		try {
			@SuppressWarnings({"deprecation", "resource"})
			CSVReader reader = new CSVReader(new FileReader(file), ';');
			
			allRows = reader.readAll();
			
			data = allRows.get(0);
			data_check = allRows.get(1);
			
			for (int i = 0; i < data.length; ) {
				int temp;
				
				String dta = data[i];
				
				
				 if(dta.equals("EAN"))
				 {
					 index_EAN=i; 
				 }
				
				 else if(dta.equals("ART_NR"))
				 {
					 index_ART_NR=i;
				 } 
				 else if(dta.equals("GROESSE"))
				 {
					 index_GROESSE=i; 
				 }
				 else if(dta.equals("FARBE"))
				 {
					 index_FARBE=i;
				 }
				 
				 else if(dta.equals("UVP"))
				 {
					 index_UVP=i;
				 }
				 else if(dta.equals("BESTAND"))
				 {
					 index_BESTAND=i;
				 }
				 //-----------------------------------------
				 else if(dta.equals("PROD_NAME"))
				 {
					 index_PROD_NAME=i;
				 }
				 else if(dta.equals("BRAND"))
				 {
					 index_BRAND=i;
				 }
				 else if(dta.equals("GENDER"))
				 {
					 index_GENDER=i;
				 }
				 else if(dta.equals("PROD_MATERIAL"))
				 {
					 index_PROD_MATERIAL=i;
				 }
				 else if(dta.equals("PROD_TEXT"))
				 {
					 index_PROD_TEXT=i;
				 }
				 else if(dta.equals("IMAGE_1"))
				 {
					 index_IMAGE_1=i;
				 }
				 else if(dta.equals("IMAGE_2"))
				 {
					 index_IMAGE_2=i;
				 }
				 else if(dta.equals("IMAGE_3"))
				 {
					 index_IMAGE_3=i;
				 }			
				 else if(dta.equals("PREIS"))
				 {
					 index_PREIS=i;
				 }
				
				 else if(dta.equals("ANGEBOT_NR"))
				 {
					 index_ANGEBOT_NR=i;
				 }
				 else
					 temp = i;
					i++;
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
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

}
	
	
	

