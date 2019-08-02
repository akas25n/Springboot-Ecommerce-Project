package com.lot.repository;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lot.model.Lot_Lager;
import com.opencsv.CSVWriter;

public interface Lot_LagerRepository extends JpaRepository<Lot_Lager, Long>{

	@Query(value="SELECT * FROM db_a2000_1.lot_lager l where l.ean=?1", nativeQuery=true)
	public Lot_Lager find_BY_EAN(long id);
	
	
	@Query(value = "SELECT * FROM lot_lager WHERE lot_id =?1", nativeQuery=true)
	public List<Lot_Lager> findAllByLotId(long id);
	
	//----------------------test

	@Query(value="select * from lot_lager where ART_NR = ?1", nativeQuery = true)
	public List<Lot_Lager> findAllArtNr(String art);
	
	@Query(value="select * from lot_lager where ART_NR = ?1", nativeQuery = true)
	public List<Lot_Lager> findAllArtNumber(String articleNumber);
	
	@Query(value="select * from lot_lager where ART_NR = ?1 LIMIT 1", nativeQuery = true)
	public Lot_Lager findSpecificArtNumber(String articleNumber);
	

	//creating CSV file of the product list in a lot
	public static boolean createCsv(List<Lot_Lager> lager, ServletContext context) {
		String filePath = context.getRealPath("/resources/reports");
		boolean exists = new File(filePath).exists();
		if (!exists) {
			new File(filePath).mkdirs();
		}
		
		File file = new File(filePath+"/"+File.separator+"stocklots.csv");
		
		try {
			FileWriter fileWriter = new FileWriter(file);
			CSVWriter writer = new CSVWriter(fileWriter);
			List<String[]> data = new ArrayList<String[]>();
			data.add(new String[] {"EAN", "Article Number", "Prodcut Name", "Brand", "Product Material", "Gender", 
									"Color", "Size", "Quantity", "Retail Price", "Offer Price"});
			for (Lot_Lager lagers : lager ) {
				data.add(new String[] { String.valueOf(lagers.getEAN()),lagers.getART_NR(), lagers.getPROD_NAME(), lagers.getBRAND(), lagers.getPROD_MATERIAL(), 
						lagers.getGENDER(), lagers.getFARBE(), lagers.getGROESSE(), String.valueOf(lagers.getBESTAND()), 
						String.valueOf(lagers.getUVP()), String.valueOf(lagers.getPREIS())});
			}
			writer.writeAll(data);
			writer.close();
			return true;
			
			
		} catch (Exception e) {
			return false;
		}

	}
}
