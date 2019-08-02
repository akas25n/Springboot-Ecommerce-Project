package com.lot.scheduler;


import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.lot.model.NonExistingProduct;
import com.lot.model.ProductQuantity;
import com.lot.repository.NonExistingProductRepository;
import com.lot.repository.ProductQuantityRepository;

@Component
public class ScheduledTasks {

	DateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	Date last_upload_diff;
	Date last_upload_komplett;
	
	int check_latest_diff;
	int check_latest_komplett;
	
	@Autowired
	private ProductQuantityRepository productQuantityRepository;
	@Autowired
	NonExistingProductRepository nonExistingProductRepository;
	
	//@Autowired
	//private Product_block_and_available_repository product_block_and_available_repository;
	
	
	
	
	// FTPFile latest_bestand_diff_file;
     //FTPFile latest_bestand_komplett_file;

	
	@Scheduled(fixedRate = 30000)//..................................................................UPDATE bestand_diff
	@SuppressWarnings({ "resource", "unused" })
	public void performTask() {
		//String localTimeZone="Europe/Berlin";
		//dateFormater.setTimeZone(TimeZone.getTimeZone(localTimeZone));
		System.out.println("******************************************************************************************");
		
		System.out.println("Regular task performed at "		+ dateFormater.format(new Date()));
		
		//#####start
		String server = "ftp.motion-fashion.de";
        int port = 21;
        String user = "mfne_live5";
        String pass = "oYt73uKE";
 
        FTPClient ftpClient = new FTPClient();
        
        FTPClientConfig conf = new FTPClientConfig();
        conf.setServerTimeZoneId("UTC");

        ftpClient.configure(conf);
        
        
        try {
        	
        	
        	
			ftpClient.connect(server, port);
			
			 ftpClient.login(user, pass);
			
		       
		        FTPFile[] files = ftpClient.listFiles("/htdocs/stock");
		         
		       
		        List<FTPFile> bestand_diff_file = new ArrayList<>();
		        List<FTPFile> bestand_komplett_file = new ArrayList<>();
		       
		       
		       
		        for (FTPFile file : files) {
		        	
		        	
		            String details = file.getName();
		            
		          //****************
				      boolean is_bestand_diff = details.indexOf("bestand_diff") !=-1? true: false; //true
				      if (is_bestand_diff == true)
				    	  bestand_diff_file.add(file);
				      boolean is_bestand_komplett = details.indexOf("bestand_komplett") !=-1? true: false; //true
				      if (is_bestand_komplett==true)
				    	  bestand_komplett_file.add(file);
			        //****************
		            if (file.isDirectory()) {
		                details = "[" + details + "]";
		            }
		            details += "\t\t" + file.getSize();
		            details += "\t\t" + dateFormater.format(file.getTimestamp().getTime());
		           // details += "\t\t" + dateFormater.format(file.);
		            if(is_bestand_diff == true)
		            	 details += "\t\t" +"bestand_diff";
		            else if (is_bestand_komplett==true)
		            	details += "\t\t" +"bestand_komplett";
		            System.out.println(details);
		        }
		        System.out.println("5:-");
		        System.out.println("bestand_diff_file :"+bestand_diff_file.size());
		        System.out.println("bestand_komplett_file :"+bestand_komplett_file.size());
				//**************************************************************************************************************
		        
		        String details_diff=bestand_diff_file.get(0).getName(); 
		        details_diff += "\t\t"+ bestand_diff_file.get(0).getSize();
		        details_diff += "\t\t" + dateFormater.format(bestand_diff_file.get(0).getTimestamp().getTime());
		        
		        String details_diff_latest=bestand_diff_file.get(bestand_diff_file.size()-1).getName(); 
		        details_diff_latest += "\t\t"+ bestand_diff_file.get(bestand_diff_file.size()-1).getSize();  
		        details_diff_latest += "\t\t" + dateFormater.format(bestand_diff_file.get(bestand_diff_file.size()-1).getTimestamp().getTime());
		        details_diff_latest += "\t\t"+ "LATEST";
		        
		        String details_komplett=bestand_komplett_file.get(0).getName();
		        details_komplett += "\t\t"+ bestand_komplett_file.get(0).getSize();
		        details_komplett += "\t\t" + dateFormater.format(bestand_komplett_file.get(0).getTimestamp().getTime());
		        
		        String details_komplett_latest=bestand_komplett_file.get(bestand_komplett_file.size()-1).getName();
		        details_komplett_latest += "\t\t"+ bestand_komplett_file.get(bestand_komplett_file.size()-1).getSize();
		        details_komplett_latest += "\t\t" + dateFormater.format(bestand_komplett_file.get(bestand_komplett_file.size()-1).getTimestamp().getTime());
		        details_komplett_latest += "\t\t"+ "LATEST";
		        
		        
			       String date1 = dateFormater.format(bestand_diff_file.get(bestand_diff_file.size()-1).getTimestamp().getTime());
			       String date2 = dateFormater.format(bestand_diff_file.get(bestand_diff_file.size()-2).getTimestamp().getTime());
			       System.out.println("6:-");
			       System.out.println("testing date comparisn");
			       System.out.println("date1 :"+date1);
			       System.out.println("date2 :"+date2);
		        Date d1 = dateFormater.parse(date1, new ParsePosition(0));
		        Date d2= dateFormater.parse(date2, new ParsePosition(0));
		        
		        
		        check_latest_diff =d1.compareTo(d2);
		        if(check_latest_diff ==1)// latest data available
		        {
		        	// upload will comance
		        	//**************************************************************************************************************
		        	ftpClient.enterLocalPassiveMode();
		        	ftpClient.changeWorkingDirectory("/htdocs/stock/");  
		        	InputStream inputStream = ftpClient.retrieveFileStream(bestand_diff_file.get(bestand_diff_file.size()-1).getName());
		        	 String split[];
		            Scanner sc = new Scanner(inputStream).useDelimiter("\\A");
		        	 //Scanner sc = new Scanner(inputStream);
		            String result = sc.hasNext() ? sc.next() : "";
		            split = result.split("\\n");
		            System.out.println(" **********  ");
		            System.out.println("split :"+split.length);
		            System.out.println(" **********  ");
		            
		            for (int i =0;i<split.length;i++)
		            {
		            	System.out.println(" split["+i+"]"+split[i]);
		            	    //String tmp = 
		            	    String temp_csv_data =split[i].trim();
		            	    String[] ean_quantity = temp_csv_data.split(";");
		            	    if(ean_quantity[0] ==null)
		            	    	continue;
		            	    System.out.println("ean_quantity :["+ean_quantity[0]+"]"+"["+ean_quantity[1]+"]");
				            long a_ean = Long.parseLong(ean_quantity[0]);
				            int quantity = Integer.parseInt(ean_quantity[1]);
				            System.out.println("ean :"+a_ean);
				            System.out.println("quantity :"+quantity);
				            
				            System.out.println("77:-");
				          
				            ProductQuantity product_quentity_obj =productQuantityRepository.find_BY_EAN(a_ean);
				            
				         
				            if (product_quentity_obj==null)
				            {
				            	
				            	NonExistingProduct nonExistingProduct_object = nonExistingProductRepository.find_BY_EAN(a_ean);
				            		
				            			if(nonExistingProduct_object == null) {
				            				System.out.println("new NonExistingProduct created");
				            				NonExistingProduct nonExistingProduct_new_object = new NonExistingProduct();
				            				nonExistingProduct_new_object.setEan(a_ean);
				            				nonExistingProductRepository.save(nonExistingProduct_new_object);
				            			}
				            			
				            			else
				            			{
				            				System.out.println("NonExistingProduct exists");
				            				continue;
				            			}
				            			
				       
				            	System.out.println("99:-");
				            	System.out.println(" Object empty !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!  ");
				            }
				            
				            else if (product_quentity_obj!=null)
				            {
//				            	 //Product_block_and_available product_block_and_available = product_block_and_available_repository.find_By_a_ean(a_ean);
//				    			
//				    			 int available_quantity =quantity-product_block_and_available.getProduct_block_quantity();
//				    			 product_block_and_available.setTotal_product_quantity(quantity);
//				    			 product_block_and_available.setProduct_available_quantity(available_quantity);
//				    			 product_block_and_available_repository.save(product_block_and_available);
//				            	
//				            	   product_quentity_obj.setQuentity(quantity);
//						           productQuantityRepository.save(product_quentity_obj);
     
				            }  
				        
				            
		            }
		            
		            System.out.println(" **********  ");
		            System.out.println("  result :"+result);
		            System.out.println(" **********  ");
		            //Reading the file line by line and printing the same
		            while (sc.hasNextLine()) 
		            	
		            
		            System.out.println(sc.nextLine());
		           
		           
		            
		            //fatch data from quantity table and update them
		            //Closing the channels
		            sc.close();
		            inputStream.close();
		            //**************************************************************************************************************        
		        }

		        else if (check_latest_diff ==0)
		        {
		        	// no latest data available
		        }
		        
		        else if (check_latest_diff ==-1)
		        {
		        	// latest data is older then old data
		        }
		        
		        System.out.println("result :"+d1.compareTo(d2)); 
		        System.out.println(details_diff);
		        System.out.println(details_diff_latest);
		        System.out.println(details_komplett);
		        System.out.println(details_komplett_latest);
		        
		        
		        Date currentISTime =bestand_komplett_file.get(bestand_komplett_file.size()-1).getTimestamp().getTime();

			  //**************************************************************************************************************
    
		        ftpClient.logout();
		        ftpClient.disconnect();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
		//#####start
		
		
	}
	
	//int mili = 
	
	@SuppressWarnings("unused")
	@Scheduled(fixedRate = 300000)//..................................................................UPDATE bestand_komplett
	public void performDelayedTask() {

	
		
		//String localTimeZone="Europe/Berlin";
				//dateFormater.setTimeZone(TimeZone.getTimeZone(localTimeZone));
				System.out.println("******************************************************************************************");
				String start_time = dateFormater.format(new Date());
				
				System.out.println("Regular task performed at "		+ dateFormater.format(new Date()));
				
				//#####start
				String server = "ftp.motion-fashion.de";
		        int port = 21;
		        String user = "mfne_live5";
		        String pass = "oYt73uKE";
		 
		        FTPClient ftpClient = new FTPClient();
		        
		        FTPClientConfig conf = new FTPClientConfig();
		        conf.setServerTimeZoneId("UTC");

		        ftpClient.configure(conf);
		        
		        
		        try {
		        	
		        	
		        	
					ftpClient.connect(server, port);
					
					 ftpClient.login(user, pass);
					
				       
				        FTPFile[] files = ftpClient.listFiles("/htdocs/stock");
				         
				       
				       
				        List<FTPFile> bestand_komplett_file = new ArrayList<>();
				       
				       
				       
				        for (FTPFile file : files) {
				        	
				        	
				            String details = file.getName();
				            
				          //****************
						      boolean is_bestand_komplett = details.indexOf("bestand_komplett") !=-1? true: false; //true
						      if (is_bestand_komplett==true)
						    	  bestand_komplett_file.add(file);
					        //****************
				            if (file.isDirectory()) {
				                details = "[" + details + "]";
				            }
				            details += "\t\t" + file.getSize();
				            details += "\t\t" + dateFormater.format(file.getTimestamp().getTime());
				          
				          
				             if (is_bestand_komplett==true)
				            	details += "\t\t" +"bestand_komplett";
				            System.out.println(details);
				        }

				        System.out.println("bestand_komplett_file :"+bestand_komplett_file.size());
				      //**************************************************************************************************************

				        String details_komplett=bestand_komplett_file.get(0).getName();
				        details_komplett += "\t\t"+ bestand_komplett_file.get(0).getSize();
				        details_komplett += "\t\t" + dateFormater.format(bestand_komplett_file.get(0).getTimestamp().getTime());
				        
				        String details_komplett_latest=bestand_komplett_file.get(bestand_komplett_file.size()-1).getName();
				        details_komplett_latest += "\t\t"+ bestand_komplett_file.get(bestand_komplett_file.size()-1).getSize();
				        details_komplett_latest += "\t\t" + dateFormater.format(bestand_komplett_file.get(bestand_komplett_file.size()-1).getTimestamp().getTime());
				        details_komplett_latest += "\t\t"+ "LATEST";
				        
				        
					       String date1 = dateFormater.format(bestand_komplett_file.get(bestand_komplett_file.size()-1).getTimestamp().getTime());
					       String date2 = dateFormater.format(bestand_komplett_file.get(bestand_komplett_file.size()-2).getTimestamp().getTime());
				        Date d1 = dateFormater.parse(date1, new ParsePosition(0));
				        Date d2= dateFormater.parse(date2, new ParsePosition(0));
				        
				        
				        check_latest_komplett =d1.compareTo(d2);
				        if(check_latest_komplett ==1)// latest data available
				        {
				        	// upload will comance
				        	//**************************************************************************************************************
				        	ftpClient.enterLocalPassiveMode();
				        	ftpClient.changeWorkingDirectory("/htdocs/stock/");  
				        	InputStream inputStream = ftpClient.retrieveFileStream(bestand_komplett_file.get(bestand_komplett_file.size()-1).getName());
				        	 String split[];
				            Scanner sc = new Scanner(inputStream).useDelimiter("\\A");
				        	 //Scanner sc = new Scanner(inputStream);
				            String result = sc.hasNext() ? sc.next() : "";
				            split = result.split("\\n");

				            for (int i =0;i<split.length;i++)
				            {
				            	System.out.println(" split["+i+"]"+split[i]);
				            	    //String tmp = 
				            	    String temp_csv_data =split[i].trim();
				            	   
				            	    String[] ean_quantity = temp_csv_data.split(";");
				            	    
				            	    System.out.println("ean_quantity :["+ean_quantity[0]+"]"+"["+ean_quantity[1]+"]");
				            	    
				            	    
				            	    
				            	    if(ean_quantity[0].equals(""))
				            	    {
		System.out.println("NULL product found continue  ---------------#################################################################--------1:-");
				            	    	continue;
				            	    }
				            	    	
				            	    if(ean_quantity[0].equals(null))
				            	    	
				            	    {
		System.out.println("NULL product found continue  ---------------#################################################################--------2:-");				            	    	continue;
				            	    }
				            	    
						            long a_ean = Long.parseLong(ean_quantity[0]);
						            int quantity = Integer.parseInt(ean_quantity[1]);
						            System.out.println("ean :"+a_ean);
						            System.out.println("quantity :"+quantity);
						            
						            System.out.println("77:-");                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          
						          
						           ProductQuantity product_quentity_obj =productQuantityRepository.find_BY_EAN(a_ean);
						            
						         
						            if (product_quentity_obj==null)
						            {
						            	
						            	NonExistingProduct nonExistingProduct_object = nonExistingProductRepository.find_BY_EAN(a_ean);
						            		
						            			if(nonExistingProduct_object == null) {
						            				System.out.println("new NonExistingProduct created");
						            				NonExistingProduct nonExistingProduct_new_object = new NonExistingProduct();
						            				nonExistingProduct_new_object.setEan(a_ean);
						            				nonExistingProductRepository.save(nonExistingProduct_new_object);
						            			}
						            			
						            			else
						            			{
						            				System.out.println("NonExistingProduct exists");
						            				continue;
						            			}

						            	System.out.println(" Object empty !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!  ");
						            }
						            
						            else if (product_quentity_obj!=null)
						            {
//						            	 Product_block_and_available product_block_and_available = product_block_and_available_repository.find_By_a_ean(a_ean);
//						    			
//						    			 int available_quantity =quantity-product_block_and_available.getProduct_block_quantity();
//						    			 product_block_and_available.setTotal_product_quantity(quantity);
//						    			 product_block_and_available.setProduct_available_quantity(available_quantity);
//						    			 product_block_and_available_repository.save(product_block_and_available);
//						            	
//						            	   product_quentity_obj.setQuentity(quantity);
//								           productQuantityRepository.save(product_quentity_obj);
		     
						            }  
						        
						            
				            }

				            //Reading the file line by line and printing the same
				            while (sc.hasNextLine()) 
				            	
				            
				            System.out.println(sc.nextLine());

				            sc.close();
				            inputStream.close();
				            //**************************************************************************************************************        
				        }

				        else if (check_latest_diff ==0)
				        {
				        	// no latest data available
				        }
				        
				        else if (check_latest_diff ==-1)
				        {
				        	// latest data is older then old data
				        }
				        
				        System.out.println("result :"+d1.compareTo(d2)); 
				        System.out.println(details_komplett);
				        System.out.println(details_komplett_latest);
				        
				        
				        Date currentISTime =bestand_komplett_file.get(bestand_komplett_file.size()-1).getTimestamp().getTime();

					  //**************************************************************************************************************
		    
				        ftpClient.logout();
				        ftpClient.disconnect();
				} catch (SocketException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		       
				//#####start
		
		        String end_time = dateFormater.format(new Date());
		        
		        
		        System.out.println("************   Job sechudler is completed     ************  ");
		        System.out.println("   Starting       "+start_time);
		        System.out.println("   ending         "+end_time);
		        
	}
	
}

