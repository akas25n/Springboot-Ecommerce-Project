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

import com.lot.model.Lot_Lager;
import com.lot.repository.Lot_LagerRepository;
import com.opencsv.CSVReader;



@Service
public class Lot_LagerService {

	@Autowired
	private Lot_LagerRepository lot_LagerRepository; 
	
	private String fileLocation; // for deleting
	private String fileNewLocation; // for reading
	
	private String msg;
	
	//----------------------------------------------------------index----------------------------------------------
	int index_EAN;
	int index_ART_NR;
	int index_GROESSE;
	int index_FARBE;
	int index_LAGERPLATZ;
	int index_BESTAND;
	int index_RESERVIERT;
	int index_PREIS;
	
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
	
	
	public String save_lager() {
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
				long check_ean = Long.parseLong(data[index_EAN]);
				Lot_Lager product_obj = lot_LagerRepository.find_BY_EAN(check_ean);
				
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
		Lot_Lager lager = new Lot_Lager();
		
		lager.setEAN(Long.parseLong(data[index_EAN]));
		lager.setART_NR(data[index_ART_NR]);
		lager.setGROESSE(data[index_GROESSE]);
		lager.setFARBE(data[index_FARBE]);
		lager.setLAGERPLATZ(data[index_LAGERPLATZ]);
		lager.setBESTAND(data[index_BESTAND]);
		lager.setRESERVIERT(data[index_RESERVIERT]);
		lager.setPREIS(Long.parseLong(data[index_PREIS]));
		
		lot_LagerRepository.save(lager);
		 
		
		 
		 return "Products have been created";
			
	}
	
	
	public String product_update(String[] data) {
		
		long check_ean = Long.parseLong(data[index_EAN]);
		
		Optional<Lot_Lager> obj = lot_LagerRepository.findById(check_ean);
		Lot_Lager lager= obj.get();
		
		lager.setEAN(Long.parseLong(data[index_EAN]));
		lager.setART_NR(data[index_ART_NR]);
		lager.setGROESSE(data[index_GROESSE]);
		lager.setFARBE(data[index_FARBE]);
		lager.setLAGERPLATZ(data[index_LAGERPLATZ]);
		lager.setBESTAND(data[index_BESTAND]);
		lager.setRESERVIERT(data[index_RESERVIERT]);
		lager.setPREIS(Long.parseLong(data[index_PREIS]));
		
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
				 
				 else if(dta.equals("LAGERPLATZ"))
				 {
					 index_LAGERPLATZ=i;
				 }
				 else if(dta.equals("BESTAND"))
				 {
					 index_BESTAND=i;
				 }
				 
				 else if(dta.equals("RESERVIERT"))
				 {
					 index_RESERVIERT=i;
				 }
				
				 else if(dta.equals("PREIS"))
				 {
					 index_PREIS=i;
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


	
	

