package com.lot.service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

	// ----------------------------------------------------------index----------------------------------------------
	int index_EAN;
	int index_ART_NR;
	int index_GROESSE;
	// ----------------------------
	int index_PROD_NAME;
	// ----------------------------
	int index_FARBE;
	int index_UVP;
	int index_BESTAND;
<<<<<<< HEAD
=======
	//int index_RESERVIERT;
>>>>>>> 24fd5d7109fa729315c24432dfff3db1654da8a4
	int index_PREIS;
	// ----------------------------
	int index_BRAND;
	int index_GENDER;
	int index_PROD_MATERIAL;
	int index_PROD_TEXT;
	int index_IMAGE_1;
	int index_IMAGE_2;
	int index_IMAGE_3;
	// ----------------------------
	int index_ANGEBOT_NR;

	int temp;
	// -----------------------------------------------------------------index---------------------------------------------------

	public File upload_file(MultipartFile file) throws IOException {

		File targetFile = new File(
				"/tmp/tomcat8-tomcat8-tmp/" + System.currentTimeMillis() + file.getOriginalFilename());
		file.transferTo(targetFile);

//		File convFile = new File(System.file.getOriginalFilename());
//		convFile.createNewFile();
//		FileOutputStream fos = new FileOutputStream(convFile);
//		fileLocation=convFile.getAbsolutePath();
//		
//		fos.write(file.getBytes());
//		fos.close();
//		
//		fileNewLocation = fileLocation.replace("\\", "/");
//		return convFile;
		return targetFile;
	}

	public String save_lager(MultipartFile file) {
		String[] data = null;
		List<String[]> allRows = null;
		// System.out.println("start try \n");
		try {
			@SuppressWarnings({ "deprecation", "resource" })
			CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()));

			allRows = reader.readAll();

			// System.out.println("start for loop \n");
			// ---------------------------------------------------------------------------------------------problem
			// is coming from here
			for (int i = 1; i < allRows.size(); i++) {

				data = allRows.get(i);
				long check_ean = Long.parseLong(data[index_EAN]);
				Lot_Lager product_obj = lot_LagerRepository.find_BY_EAN(check_ean);

				if (product_obj == null) {
					product_create(data);
				} else if (!(product_obj == null)) {
					product_update(data);

				}

			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("error in save method \n");
		}

		// return "data saved";
		return msg;
	}

	public String product_create(String[] data) {
		Lot_Lager lager = new Lot_Lager();

		lager.setEAN(Long.parseLong(data[index_EAN]));
		lager.setART_NR(data[index_ART_NR]);
		lager.setGROESSE(data[index_GROESSE]);
		lager.setFARBE(data[index_FARBE]);
<<<<<<< HEAD
		// --------------------------------------------
		lager.setPROD_NAME(data[index_PROD_NAME]);
		// --------------------------------------------
		lager.setBESTAND(Integer.parseInt(data[index_BESTAND]));
		// lager.setRESERVIERT(data[index_RESERVIERT]);
		lager.setPREIS(Double.parseDouble(data[index_PREIS]));
		lager.setUVP(Double.parseDouble(data[index_UVP]));

		// ---------------------------------------------
		lager.setBRAND(data[index_BRAND]);
		lager.setGENDER(data[index_GENDER]);
		lager.setPROD_MATERIAL(data[index_PROD_MATERIAL]);
		lager.setPROD_TEXT(data[index_PROD_TEXT]);
		lager.setIMAGE_1(data[index_IMAGE_1]);
		lager.setIMAGE_2(data[index_IMAGE_2]);
		lager.setIMAGE_3(data[index_IMAGE_3]);
		// --------------------------------------------
		lager.setANGEBOT_NR(data[index_ANGEBOT_NR]);

=======
		
		lager.setBESTAND(Integer.parseInt(data[index_BESTAND]));
		//lager.setRESERVIERT(data[index_RESERVIERT]);
		lager.setPREIS(Double.parseDouble(data[index_PREIS]));
		lager.setUVP(Double.parseDouble(data[index_UVP]));
>>>>>>> 24fd5d7109fa729315c24432dfff3db1654da8a4
		lot_LagerRepository.save(lager);

		return "Products have been created";

	}

	public String product_update(String[] data) {

		long check_ean = Long.parseLong(data[index_EAN]);

		Optional<Lot_Lager> obj = lot_LagerRepository.findById(check_ean);
		Lot_Lager lager = obj.get();

		lager.setEAN(Long.parseLong(data[index_EAN]));
		lager.setART_NR(data[index_ART_NR]);
		lager.setGROESSE(data[index_GROESSE]);
		lager.setFARBE(data[index_FARBE]);
<<<<<<< HEAD
		// --------------------------------------------
		lager.setPROD_NAME(data[index_PROD_NAME]);
		// --------------------------------------------
		lager.setBESTAND(Integer.parseInt(data[index_BESTAND]));
		// lager.setRESERVIERT(data[index_RESERVIERT]);
=======
		
		lager.setBESTAND(Integer.parseInt(data[index_BESTAND]));
		//lager.setRESERVIERT(data[index_RESERVIERT]);
		lager.setUVP(Double.parseDouble(data[index_UVP]));
>>>>>>> 24fd5d7109fa729315c24432dfff3db1654da8a4
		lager.setPREIS(Double.parseDouble(data[index_PREIS]));
		lager.setUVP(Double.parseDouble(data[index_UVP]));

		// ---------------------------------------------
		lager.setBRAND(data[index_BRAND]);
		lager.setGENDER(data[index_GENDER]);
		lager.setPROD_MATERIAL(data[index_PROD_MATERIAL]);
		lager.setPROD_TEXT(data[index_PROD_TEXT]);
		lager.setIMAGE_1(data[index_IMAGE_1]);
		lager.setIMAGE_2(data[index_IMAGE_2]);
		lager.setIMAGE_3(data[index_IMAGE_3]);
		// --------------------------------------------
		lager.setANGEBOT_NR(data[index_ANGEBOT_NR]);

		return "Products have been created";

	}

	public void set_index() {

		String[] data = null;
		String[] data_check = null;
		List<String[]> allRows = null;

		try {
			@SuppressWarnings({ "deprecation", "resource" })
			CSVReader reader = new CSVReader(new FileReader(fileNewLocation), ';');

			allRows = reader.readAll();

			data = allRows.get(0);
			data_check = allRows.get(1);

			for (int i = 0; i < data.length;) {
				int temp;

				String dta = data[i];
<<<<<<< HEAD

				if (dta.equals("EAN")) {
					index_EAN = i;
				}

				else if (dta.equals("ART_NR")) {
					index_ART_NR = i;
				} else if (dta.equals("GROESSE")) {
					index_GROESSE = i;
				} else if (dta.equals("FARBE")) {
					index_FARBE = i;
				}

				else if (dta.equals("UVP")) {
					index_UVP = i;
				} else if (dta.equals("BESTAND")) {
					index_BESTAND = i;
				}
				// -----------------------------------------
				else if (dta.equals("PROD_NAME")) {
					index_PROD_NAME = i;
				} else if (dta.equals("BRAND")) {
					index_BRAND = i;
				} else if (dta.equals("GENDER")) {
					index_GENDER = i;
				} else if (dta.equals("PROD_MATERIAL")) {
					index_PROD_MATERIAL = i;
				} else if (dta.equals("PROD_TEXT")) {
					index_PROD_TEXT = i;
				} else if (dta.equals("IMAGE_1")) {
					index_IMAGE_1 = i;
				} else if (dta.equals("IMAGE_2")) {
					index_IMAGE_2 = i;
				} else if (dta.equals("IMAGE_3")) {
					index_IMAGE_3 = i;
				}
				// -----------------------------------------
=======
				
				
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
				 
>>>>>>> 24fd5d7109fa729315c24432dfff3db1654da8a4
//				 else if(dta.equals("RESERVIERT"))
//				 {
//					 index_RESERVIERT=i;
//				 }
<<<<<<< HEAD

				else if (dta.equals("PREIS")) {
					index_PREIS = i;
				}

				else if (dta.equals("ANGEBOT_NR")) {
					index_ANGEBOT_NR = i;
				} else
					temp = i;
				i++;

=======
				
				 else if(dta.equals("PREIS"))
				 {
					 index_PREIS=i;
				 }
				
				 else
					 temp = i;
					i++;
				
>>>>>>> 24fd5d7109fa729315c24432dfff3db1654da8a4
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String deleteFile() {
		File file = new File(fileLocation);

		if (file.delete()) {
			System.out.println("File deleted successfully");
			return "file deleted";
		} else {
			System.out.println("Failed to delete the file");

			return "file deleted failed";
		}
	}

	public String[] test() {

		String[] data = null;
		List<String[]> allRows = null;

		try {
			@SuppressWarnings({ "deprecation", "resource" })
			CSVReader reader = new CSVReader(new FileReader(fileNewLocation));

			allRows = reader.readAll();
			data = allRows.get(2);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return data;
	}

}
