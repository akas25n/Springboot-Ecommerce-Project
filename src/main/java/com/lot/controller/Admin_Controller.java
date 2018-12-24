package com.lot.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.lot.model.Lot;
import com.lot.model.Order;
import com.lot.model.Product;
import com.lot.model.User;
import com.lot.repository.LotRepository;
import com.lot.repository.OrderRepository;
import com.lot.repository.ProductRepository;
import com.lot.service.LotService;
import com.lot.service.ProductService;
import com.lot.service.UserService;




@RestController
@RequestMapping("/admin")
public class Admin_Controller {

	@Autowired
	private LotRepository lotRepository;
	
	@Autowired
	LotService lotService;
	
	@Autowired
	private UserService userService;
	

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	private static String fileLocation;
	
	 
    @RequestMapping(value="/lot/home", method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
      //*************************************************************************************************
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
        //**************************************************************************************************
        
        List<Lot> lots =lotService.findByEnabled();
        modelAndView.addObject("lots", lots);
        modelAndView.addObject("adminMessage","Only active lot will be displayed");
       // System.out.println("*****************************************************************"+ lots + "**************************************************************************");
        modelAndView.setViewName("admin-index");
        return modelAndView;
    }
    

	

	@RequestMapping(value="/create/new/lot", method = RequestMethod.GET)
	public ModelAndView createLot() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("lot", new Lot());
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
        
		mv.setViewName("/my_account/admin/create_new_lot");
		
		return mv;
	}
	
	
	@RequestMapping(value = "/save/lot",method=RequestMethod.POST) // testing  // success
	public ModelAndView save_lot( 
									@RequestParam("lotName") String lotName,
									@RequestParam("lotDescription") String lotDescription,
									@RequestParam("teaserImage")  MultipartFile teaserImage,
									@RequestParam("lotPrice") double lotPrice,
									@RequestParam("lot_status") int lot_status
									)
									
					
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(); 
		ModelAndView mv = new ModelAndView();
		
		Lot lot = new Lot();
		
		try {
			
			byte[] buffer = teaserImage.getBytes();
			String base64Image = Base64.getEncoder().encodeToString(buffer);
			lot.setTeaserImage(base64Image);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//------------------------------------------------------------------end of images test
		mv.addObject("lot",lot);
		lot.setLotName(lotName);
		lot.setLotDescription(lotDescription);
		lot.setLotPrice(lotPrice);
		lot.setLot_status(lot_status);
		
		lot.setCreatedAt(formatter.format(date));
		//lot.setTeaserImage(teaserImage);
		
		
		
		
		mv.setViewName("/my_account/admin/create_new_lot");
		
		lotRepository.save(lot);
		//imagesRepository.saveAll(images);
		
		//return "data saved";
		return new ModelAndView("redirect:/admin/lot/product/" + lot.getLotId()) ;
	}
	
	
	@RequestMapping(value="/lot/product/{lotId}", method=RequestMethod.GET)
	public ModelAndView addProduct(@PathVariable long lotId) {
		ModelAndView mv = new ModelAndView();
		
		//****************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
		//*****************************************************************************************************
        
		List<Product> products = productRepository.findAll();
		Optional<Lot> newLot = lotRepository.findById(lotId);
		
		Lot lot=newLot.get();
		mv.addObject("products", products);
		mv.addObject("lot", lot);
		
		
		mv.setViewName("/my_account/admin/products");
		System.out.println("#####################################################################" + lotId + "##############################################################");
		return mv;
		
	}
	
	
	@RequestMapping(value="/add/lot/product", method=RequestMethod.POST)
	public ModelAndView addProductInLot(@RequestParam("idChecked") List<String> idrates, @RequestParam(value ="lot_id") Long lot_id) {
		
		new ModelAndView();
		
		
		System.out.println("############################################################test add product in lot method##########################");
		
		
		Optional<Lot> newLot = lotRepository.findById(lot_id);
		Lot lot = newLot.get();
		
		new Product();
		
		ArrayList<Long> al = new ArrayList<Long>();
		

		if (idrates !=null) {
			Set<Product> productList = new HashSet<>();
			productList = lot.getProductList();
			
			for(String idrateStr: idrates) {
				Long idrate = Long.parseLong(idrateStr);
				al.add(idrate);
				
				Product newProduct = productRepository.find_BY_EAN(idrate);
				
				if (newProduct.equals(null)) {
					return null;
				}
				else {
					productList.add(newProduct);
				}
				
			}
			lot.setProductList(productList);
			lotRepository.save(lot);
			
		}
		
		
		
		
		
		return new ModelAndView("redirect:/admin/lot/list");
		
	} 
	
	
	@RequestMapping(value="/lot/list", method = RequestMethod.GET)
	public ModelAndView lotList(@RequestParam(defaultValue="0") int page) {
		ModelAndView mv = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
        
       List<Lot> lots = (List<Lot>)lotRepository.findAll();
       mv.addObject("lots", lots);
//        mv.addObject("lots", lotRepository.findAll(new PageRequest(page, 25)));
//        mv.addObject("currentPage", page);
		mv.setViewName("/my_account/admin/lot_list");
		
		return mv;
	}
	
/*
	@RequestMapping(value = "/edit/lot",method=RequestMethod.POST) // testing  // success
	public ModelAndView Editlot( 
									@RequestParam("lotName") String lotName,
									@RequestParam("lotDescription") String lotDescription,
									@RequestParam("teaserImage")  MultipartFile teaserImage,
									@RequestParam("lotPrice") double lotPrice,
									@RequestParam("lot_status") int lot_status
									)
									
					
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(); 
		ModelAndView mv = new ModelAndView();
		
		Lot lot = new Lot();
		
		try {
			
			byte[] buffer = teaserImage.getBytes();
			String base64Image = Base64.getEncoder().encodeToString(buffer);
			lot.setTeaserImage(base64Image);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//------------------------------------------------------------------end of images test
		mv.addObject("lot",lot);
		lot.setLotName(lotName);
		lot.setLotDescription(lotDescription);
		lot.setLotPrice(lotPrice);
		lot.setLot_status(lot_status);
		
		lot.setCreatedAt(formatter.format(date));
		//lot.setTeaserImage(teaserImage);
		
		
		
		
		mv.setViewName("/my_account/admin/create_new_lot");
		
		lotRepository.save(lot);
		//imagesRepository.saveAll(images);
		
		//return "data saved";
		return new ModelAndView("redirect:/admin/lot/product/" + lot.getLotId()) ;
	}
		

	//working*************************************************************************************************working
	@RequestMapping(value="/edit/lot/{lotId}")
	public ModelAndView editLot(@PathVariable("lotId") long lotId) {
		ModelAndView mv = new ModelAndView();
		
		Optional<Lot> newLot = lotRepository.findById(lotId);
		Lot lots = newLot.get();
		
		lots.setProductList(null);
		lotRepository.deleteById(lotId);
		
		mv.addObject("lot", lots);

		mv.setViewName("/my_account/admin/edit-lot");
		return mv;
	}

	
	
	  @PostMapping("/edit/lot")
	    public ModelAndView  edit_1(
	                	@RequestParam Long lotId ,
	                	@RequestParam("lotName") String lotName,
	                  @RequestParam("lotDescription") String lotDescription,
	                  @RequestParam("teaserImage")  MultipartFile teaserImage,
	                  @RequestParam("lotPrice") double lotPrice,
	                  @RequestParam("lot_status") int lot_status) throws IOException
	        
	    {
	      
	      new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	      new Date(); 
	      ModelAndView mv = new ModelAndView();
	      Optional<Lot> newLot = lotRepository.findById(lotId);
	      Lot lot = newLot.get();
	        
	      mv.addObject("lot",lot);
	 
	      if(teaserImage.getBytes()!=null)
	      {
	    
	            try {
	              
	              byte[] buffer = teaserImage.getBytes();
	              Base64.getEncoder().encodeToString(buffer);  
	            } catch (IOException e) {
	             
	              e.printStackTrace();
	            }
	      }

	      else
	      {
	        lot.getTeaserImage();
	      }
	        
	      lot.setLotName(lotName);
	      lot.setLotDescription(lotDescription);
	      lot.setLotPrice(lotPrice);
	      lot.setLot_status(lot_status);
	      
	      mv.addObject("lot",lot);
	         
	      lotRepository.save(lot);
	      
	      System.out.println("********************************************************test**********************************************************");
	      mv.setViewName("/my_account/admin/create_new_lot");
	      
	      return new ModelAndView("redirect:/admin/lot/list") ;
	      
	    }
	  */
	  
	//delete a lot
	// first need to find the lot to delete by id
	//we need to set the productList null in the lot
	// then delete the lot from the lotRepository
	@RequestMapping(value="/delete/lot/{lotId}")
	public ModelAndView deleteLot(@PathVariable("lotId") long lotId) {
		ModelAndView mv = new ModelAndView();
		
		Optional<Lot> newLot = lotRepository.findById(lotId);
		Lot lots = newLot.get();
		
		lots.setProductList(null);
		lotRepository.deleteById(lotId);
		
		mv.addObject("lot", lots);

		return new ModelAndView("redirect:/admin/lot/list");
	}
	
	//####################################################################################### product ###########################
	@RequestMapping(value = "/upload/product", method= RequestMethod.GET)
	public ModelAndView uploadProduct() {
		ModelAndView mv = new ModelAndView();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
        
		mv.setViewName("/my_account/admin/product_upload");
		return mv;
	}
	
	
	@RequestMapping(value = "/product/process", method= RequestMethod.POST)
	public String processUploadProduct(MultipartFile multi_file) throws IOException {
		ModelAndView mv = new ModelAndView();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
        
        try {
        	
        	
			fileLocation = productService.upload_file(multi_file);
			
			
			System.out.println("start set_index");
			productService.set_index();
			System.out.println("stop set_index");
			
			System.out.println("start save_product");
			productService.save_product();
			System.out.println("stop save_product");
			
			return "Data have been saved";
		} catch (IOException e) {
			
			return "Data not saved";
			
			
		}
        
   
		
	}
	
	
	@RequestMapping(value = "/product/list", method= RequestMethod.GET)
	public ModelAndView productList(@RequestParam(defaultValue="0") int page) {
		ModelAndView mv = new ModelAndView();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
        
        List<Product> product = productRepository.findAll();
      
        mv.addObject("products", product);
        
//        mv.addObject("products", productRepository.findAll(new PageRequest(page, 50)));
//        mv.addObject("currentPage", page);
		mv.setViewName("/my_account/admin/product_list");
		return mv;
	}
	
	
	public File convert(MultipartFile file) throws IOException{
		File convFile = new File(file.getOriginalFilename());
		convFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
		
	}
	
	//####################################################################################### product ###########################
	

	
	@RequestMapping(value = "/account/user/list", method= RequestMethod.GET)
	public ModelAndView showUserList() {
		ModelAndView mv = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
		mv.setViewName("/my_account/admin/user-list");
		return mv;
	}
	
	
	@RequestMapping(value = "/account/administrators", method= RequestMethod.GET)
	public ModelAndView showAdmin() {
		ModelAndView mv = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
		mv.setViewName("/my_account/admin/administrators");
		return mv;
	}
	

	
	@RequestMapping(value = "/account/paid/invoices", method= RequestMethod.GET)
	public ModelAndView showPaidInvoices() {
		ModelAndView mv = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
		mv.setViewName("/my_account/admin/paid_invoice");
		return mv;
	}
	
	@RequestMapping(value = "/account/unpaid/invoices", method= RequestMethod.GET)
	public ModelAndView showUnpaidInvoices() {
		ModelAndView mv = new ModelAndView();
		//**************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
        //*************************************************************************
        
		mv.setViewName("/my_account/admin/unpaid_invoice");
		return mv;
	}

	
	//****************************************************************************************Order*********************************************************
	
	@RequestMapping(value="/order/list", method=RequestMethod.GET)
	public ModelAndView showOrderList() {
		ModelAndView mv = new ModelAndView();
		//**************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
		//*************************************************************************
		
		//int user_id = user.getId();
		
		List<Order> oderList = orderRepository.findAll();
		
		mv.addObject("orderLists", oderList);
		
		mv.setViewName("/my_account/admin/order");
		
		return mv;
		
	}
	
	
	@RequestMapping(value="/order/info", method=RequestMethod.GET)
	public ModelAndView viewOrderList(@RequestParam(defaultValue="0") int page) {
		ModelAndView mv = new ModelAndView();
		//**************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName",user.getFirst_name() + " " + user.getLast_name());
		//*************************************************************************
		
		//int user_id = user.getId();
		
		List<Order> oderList = orderRepository.findAll();
		mv.addObject("orderLists", oderList);
//		mv.addObject("orderLists", orderRepository.findAll(new PageRequest(page, 50)));
//		mv.addObject("currentPage", page);
		//mv.addObject("orderLists", oderList);
		mv.setViewName("/my_account/admin/order-info");
		
		return mv;
		
	}
	
	//****************************************************************************************Order*********************************************************


	
}// end of admin controller
	
	
