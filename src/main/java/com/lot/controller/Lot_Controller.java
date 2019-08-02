package com.lot.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lot.model.CustomizedProduct;
import com.lot.model.Lot;
import com.lot.model.Lot_Lager;
import com.lot.model.SliderImages;
import com.lot.model.User;
import com.lot.repository.LotRepository;
import com.lot.repository.Lot_LagerRepository;
import com.lot.repository.ProductRepository;
import com.lot.repository.SliderImagesRepository;
import com.lot.service.Lot_LagerService;
import com.lot.service.UserService;


@RestController
@RequestMapping("/lot")
public class Lot_Controller {
	
		@Autowired
		private UserService userService;
		
		@Autowired
		private LotRepository lotRepository;
		
		@Autowired
		private ProductRepository productRepository;
		
		@Autowired
		private Lot_LagerRepository lot_LagerRepository;
		
		@Autowired
		private ServletContext context;
		
		@Autowired
		SliderImagesRepository sliderImagesRepository; 

		/*
		 * ...............................30
		 */
		@RequestMapping(value = "/details/{lotId}",method=RequestMethod.GET)
		public ModelAndView showLotdetails(@PathVariable("lotId") long lotId)
		{
			
			ModelAndView mv = new ModelAndView();
			
			//********************************************************************************************
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        User user = userService.findUserByEmail(auth.getName());
	        mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
	        //********************************************************************************************
	        
			Optional<Lot> new_obj = lotRepository.findById(lotId);
			Lot lots= new_obj.get();
			
			//System.out.println("---------------------------------------------------------testing " + lots.getProductList()); // tested OK
			
			List<Lot_Lager> product = lot_LagerRepository .findAllByLotId(lotId);
			
			//------------------------------------------------------------------------------counting volume
			int vol= 0;
			int i=0;
			product.get(i).getBESTAND();
			
			for(i = 0; i< product.size(); i++) {
				
				int st =product.get(i).getBESTAND();
				
				vol =vol + (st); 	
			}
			//----------------------------------------------------------------------------------------
			
			//------------------------------------------------------------------------------counting volume
			double price= 0;
			int in=0;
			product.get(in).getPREIS();
			product.get(in).getBESTAND();
			for(in = 0; in< product.size(); in++) {
				
				double st =product.get(in).getPREIS();
				int qn =  product.get(in).getBESTAND();
				price = price + (st * qn); 	
			}
			
			double retailPrice= 0;
			int inm=0;
			product.get(inm).getUVP();
			product.get(inm).getBESTAND();
			for(inm = 0; inm< product.size(); inm++) {
				
				double st =product.get(inm).getUVP();
				int qn =  product.get(inm).getBESTAND();
				retailPrice = retailPrice + (st * qn); 	
			}
			
			double totalDiscount = retailPrice - price;
			double avgBuyingPrice = price / vol;
			double avgRetailPrice = retailPrice / vol;
			double percentageOfDiscount = ( totalDiscount / retailPrice ) * 100;
			
			/*
			 * numberOfSKU-------------------
			 * avgQuantityPerSKU----------
			 */
			
			
			//----------------------------------------------------------------------------------------
			
			lots.setLotPrice(price);
			lots.setVolume(vol); // setiing lot volume
			lots.setActualPrice(retailPrice);
			lots.setTotalDiscount(totalDiscount);
			lots.setAverageBuyingPrice(avgBuyingPrice);
			lots.setAverageRetailPrice(avgRetailPrice);
			lots.setPercentageOfDiscountOfRetailPrice(percentageOfDiscount);
			
	
			//------------------------------------------------------------------------------------
			
			
			List<String> imageList = new ArrayList<String>();
			
			for(Lot_Lager set : product) {
				if(!(set.getIMAGE_1()).isEmpty()) {
				}
				
				if(!(set.getIMAGE_2()).isEmpty()) {
					imageList.add(set.getIMAGE_2());
				}
				
				if(!(set.getIMAGE_3()).isEmpty()) {
					imageList.add(set.getIMAGE_3());
				}
				
			}
			
			//***********************************************************************************************************************Test
			List<String> articleNumbers = new ArrayList<>();
			
			for (Lot_Lager lotLager : product) {
					if(!articleNumbers.contains(lotLager.getART_NR()))
						articleNumbers.add(lotLager.getART_NR());
					
				}

			List<Lot_Lager> lager = new ArrayList<Lot_Lager>();
			
			List<CustomizedProduct> customisedProduct = new ArrayList<CustomizedProduct>();
			
			for (String art_nr : articleNumbers) {
				
				lager = lot_LagerRepository.findAllArtNumber(art_nr);
				
				CustomizedProduct obj = new CustomizedProduct();
				
				obj.setArticle_number(art_nr);
				obj.setProduct(lager);
				obj.setUVP(lager.get(0).getUVP());
				obj.setPRICE(lager.get(0).getPREIS());
				obj.setIMAGE(lager.get(0).getIMAGE_1());
				obj.setPROD_MATERIAL(lager.get(0).getPROD_MATERIAL());
				obj.setPROD_TEXT(lager.get(0).getPROD_TEXT());
				obj.setBRAND(lager.get(0).getBRAND());
				obj.setGENDER(lager.get(0).getGENDER());
				
				customisedProduct.add(obj);
			}
			
		
			//***********************************************************************************************************************
			
			lotRepository.save(lots); //updating the lot in the database
			
			mv.addObject("lots",lots);
			mv.addObject("products", product);
			mv.addObject("images", imageList);
			mv.addObject("lg", customisedProduct);
			
			mv.setViewName("lotDetails");
			return mv;

		}
		
		/*
		 * ..............................31
		 */
		//downloading the  CSV file of the product list in a lot
		@GetMapping(value="/download/csv/{lotId}")
		public void createCSVs(HttpServletRequest request, HttpServletResponse response, @PathVariable long lotId) {
			
			List<Lot_Lager> lager = lot_LagerRepository.findAllByLotId(lotId);
			boolean isFlag= Lot_LagerRepository.createCsv(lager, context);
			
			if (isFlag) {
				String fullPath = request.getServletContext().getRealPath("/resources/reports/" + "stocklots.csv");
				fileDownload(fullPath,response, "stocklots.csv");
			}
			
		}
		
		
		private void fileDownload(String fullPath, HttpServletResponse response, String fileName) {
			File file = new File(fullPath);
			final int BUFFER_SIZE = 4096;
			
			if (file.exists()) {
				try {
					FileInputStream inputStream = new FileInputStream(file);
					String mimeType = context.getMimeType(fullPath);
					response.setContentType(mimeType);
					response.setHeader("content-disposition", "attachment; filename=" + fileName);
					OutputStream outputStream = response.getOutputStream();
					byte[] buffer = new byte[BUFFER_SIZE];
					int bytesRead = -1;
					
					while ((bytesRead = inputStream.read(buffer))!= -1) {
						outputStream.write(buffer, 0, bytesRead);
					}
					inputStream.close();
					outputStream.close();
					file.delete();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
	
			}
		}
		
		/*.............................32
		 * ...........................search box
		 */
		@GetMapping("/search")
		public ModelAndView search(@RequestParam("searchTerm") String searchTerm) {
			ModelAndView view = new ModelAndView();
			//********************************************************************************************
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        User user = userService.findUserByEmail(auth.getName());
	        view.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
	        //********************************************************************************************
		
	        List<SliderImages> images = sliderImagesRepository.findAll();
			List<Lot> list = lotRepository.searchLot(searchTerm);
		
			view.addObject("img", images);
			view.addObject("lot", list);
			
			view.setViewName("searchIndex");
			return view;
			
		}

		
		
	
	}// end of Lot_Controller
	


