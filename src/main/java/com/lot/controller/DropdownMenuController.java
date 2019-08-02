package com.lot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lot.model.Lot;
import com.lot.model.SliderImages;
import com.lot.model.User;
import com.lot.repository.LotRepository;
import com.lot.repository.SliderImagesRepository;
import com.lot.service.UserService;

import lombok.Getter;

@RestController
@RequestMapping("/lot")
public class DropdownMenuController {

	@Autowired
	LotRepository lotRepository;

	@Autowired
	SliderImagesRepository sliderRepo;

	@Autowired
	UserService userService;

	/*
	 * --------------------------------Gender.............43
	 */
	@GetMapping("/men")
	public ModelAndView selectMen() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByMen();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("men", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/gender/men");
		return mv;
	}

	@GetMapping("/women")
	public ModelAndView selectWoen() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByWomen();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("women", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/gender/women");
		return mv;
	}

	@GetMapping("/kids")
	public ModelAndView selectKids() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByKids();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("kids", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/gender/kids");
		return mv;
	}

	/*
	 * ----------------------Brand............................44
	 */
	@GetMapping("/dreimaster")
	public ModelAndView selectByBrand() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByDreimaster();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("dreimaster", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/brands/dreimaster");
		return mv;
	}

	@GetMapping("/schmuddelwedda")
	public ModelAndView selectSchmuddelwedda() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllBySchuddelwedda();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("schmuddelwedda", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/brands/schmuddelwedda");
		return mv;
	}

	@GetMapping("/mymo")
	public ModelAndView selectMyMo() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByMyMo();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("mymo", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/brands/mymo");
		return mv;
	}

	@GetMapping("/mo")
	public ModelAndView selectMo() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByMyMo();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("mo", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/brands/mo");
		return mv;
	}

	@GetMapping("/homebase")
	public ModelAndView selectHomebase() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByHomebase();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("homebase", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/brands/homebase");
		return mv;
	}

	@GetMapping("/brandalised")
	public ModelAndView selectBrand() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByBrandalise();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("brandalised", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/brands/brandalised");
		return mv;
	}

	@GetMapping("/icebound")
	public ModelAndView selectIcebound() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByIceBound();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("icebound", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/brands/icebound");
		return mv;
	}

	@GetMapping("/izia")
	public ModelAndView selectIzia() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByIzia();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("izia", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/brands/izia");
		return mv;
	}

	@GetMapping("/hawke")
	public ModelAndView selectHawke() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByHawke();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("hawke", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/brands/hawke");
		return mv;
	}

	@GetMapping("/menuEsTorrent")
	public ModelAndView selectMenuEsTorrent() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByMenu();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("menuEsTorrent", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/brands/menuEsTorrent");
		return mv;
	}

	@GetMapping("/mumAndCo")
	public ModelAndView selectMumAndCo() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByMum();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("mumAndCo", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/brands/mumAndCo");
		return mv;
	}

	@GetMapping("/superfly")
	public ModelAndView selectSuperfly() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllBySuperfly();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("superfly", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/brands/superfly");
		return mv;
	}

	@GetMapping("/usha")
	public ModelAndView selectUsha() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByUsha();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("usha", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/brands/usha");
		return mv;
	}

	@GetMapping("/plusEighteen")
	public ModelAndView selectPlusEighteen() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByPlusEighteen();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("plusEighteen", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/brands/plusEighteen");
		return mv;
	}

	@GetMapping("/soulstar")
	public ModelAndView selectSoulstar() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllBySoulstar();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("soulstar", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/brands/soulstar");
		return mv;
	}

	@GetMapping("/dryLaundry")
	public ModelAndView selectDryLaundry() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByDryL();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("dryLaundry", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/brands/dryLaundry");
		return mv;
	}

	@GetMapping("/roosevelt")
	public ModelAndView selectRoosevelt() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByRoosevelt();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("roosevelt", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/brands/roosevelt");
		return mv;
	}

	@GetMapping("/petrolIndustries")
	public ModelAndView selectPetrolIndustries() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByPetrolInd();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("petrolIndustries", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/brands/petrolIndustries");
		return mv;
	}

	@GetMapping("/tuffskull")
	public ModelAndView selectTuffskull() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByTuffskul();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("tuffskull", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/brands/tuffskull");
		return mv;
	}

	@GetMapping("/oldskull")
	public ModelAndView selectOldskull() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByOldskull();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("oldskull", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/brands/oldskull");
		return mv;
	}

	@GetMapping("/nolie")
	public ModelAndView selectNoile() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByNolie();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("nolie", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/brands/noile");
		return mv;
	}

	@GetMapping("/risa")
	public ModelAndView selectRisa() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByRisa();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("risa", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/brands/risa");
		return mv;
	}

	@GetMapping("/blonda")
	public ModelAndView selectBlonda() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByBlonda();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("blonda", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/brands/blonda");
		return mv;
	}

	@GetMapping("/faina")
	public ModelAndView selectFaina() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByFaina();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("faina", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/brands/faina");
		return mv;
	}

	@GetMapping("/talence")
	public ModelAndView selectTalence() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByTalence();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("talence", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/brands/talence");
		return mv;
	}

	@GetMapping("/taddy")
	public ModelAndView selectTaddy() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByTaddy();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("taddy", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/brands/taddy");
		return mv;
	}

	@GetMapping("/felipa")
	public ModelAndView selectFelipa() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByFelipa();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("felipa", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/brands/felipa");
		return mv;
	}

	@GetMapping("/mixed/brands")
	public ModelAndView selectMixedBrand() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByMixed();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("mixed", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/brands/mixed");
		return mv;
	}

	/*
	 * ................................Seasons...................45
	 */

	@GetMapping("/men/ss")
	public ModelAndView selectMenSS() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByMenSS();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("men_ss", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/seasons/men-ss");
		return mv;
	}

	@GetMapping("/men/fw")
	public ModelAndView selectMenFw() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByMenFW();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("men_fw", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/seasons/men-fw");
		return mv;
	}

	@GetMapping("/women/ss")
	public ModelAndView selectWomenSS() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByWomenSS();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("women_ss", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/seasons/women-ss");
		return mv;
	}

	@GetMapping("/women/fw")
	public ModelAndView selectWomenFw() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByWomenFW();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("women_fw", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/seasons/women-fw");
		return mv;
	}

	@GetMapping("/kid/ss")
	public ModelAndView selectKidSS() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByKidSS();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("kid_ss", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/seasons/kid-ss");
		return mv;
	}

	@GetMapping("/kid/fw")
	public ModelAndView selectKidFw() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByKidFW();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("kid_fw", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/seasons/kid-fw");
		return mv;
	}

	@GetMapping("/mixed/seasons")
	public ModelAndView selectMixedSeasons() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByMixedSeasons();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("mixed_season", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/seasons/mixed");
		return mv;
	}

	/*
	 * 
	 * ...............................men.......products................46
	 * 
	 */
	@GetMapping("/men/shirt")
	public ModelAndView selectMenShirt() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByMenShirt();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("men_shirt", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/menCategory/men_shirt");
		return mv;
	}

	@GetMapping("/men/jeans")
	public ModelAndView selectMenJeans() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByMenJeans();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("men_jeans", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/menCategory/men_jeans");
		return mv;
	}

	@GetMapping("/men/jacket")
	public ModelAndView selectMenJacket() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByMenJacket();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("men_jacket", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/menCategory/men_jacket");
		return mv;
	}

	@GetMapping("/men/trousers")
	public ModelAndView selectMen_trousers() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByMentrousers();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("men_trousers", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/menCategory/men_trousers");
		return mv;
	}

	@GetMapping("/men/sweat/shirt")
	public ModelAndView selectMen_sweat_shirt() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByMenSweat();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("men_sweat_shirt", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/menCategory/men_sweat_shirt");
		return mv;
	}

	@GetMapping("/men/pullover")
	public ModelAndView selectMen_pullover() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByMenPullover();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("men_pullover", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/menCategory/men_pullover");
		return mv;
	}

	@GetMapping("/men/accessoires")
	public ModelAndView selectMen_accessoires() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByMenAccessoires();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("men_accessoires", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/menCategory/men_accessoires");
		return mv;
	}

	@GetMapping("/men/mixed")
	public ModelAndView selectMen_mixed() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByMenMixedProduct();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("men_mixed", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/menCategory/men_mixed");
		return mv;
	}

	/*
	 * 
	 * ..........................women.......products......................47
	 */
	@GetMapping("/women/shirt")
	public ModelAndView selectWoenShirt() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByWomenShirt();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("women_shirt", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/womenCategory/women_shirt");
		return mv;
	}

	@GetMapping("/women/jeans")
	public ModelAndView selectWoMenJeans() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByWomenJeans();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("women_jeans", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/womenCategory/women_jeans");
		return mv;
	}

	@GetMapping("/women/jacket")
	public ModelAndView selectWoMenJacket() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByWomenJacket();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("women_jacket", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/womenCategory/women_jacket");
		return mv;
	}

	@GetMapping("/women/blouse")
	public ModelAndView selectWoMen_Blouse() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByWomenBlouse();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("women_blouse", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/womenCategory/women_blouse");
		return mv;
	}

	@GetMapping("/women/trousers")
	public ModelAndView selectWoMen_trousers() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByWomentrousers();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("women_trousers", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/womenCategory/women_trousers");
		return mv;
	}

	@GetMapping("/women/sweat/shirt")
	public ModelAndView selectWoMen_sweat_shirt() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByWomenSweat();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("women_sweat_shirt", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/womenCategory/women_sweat_shirt");
		return mv;
	}

	@GetMapping("/women/pullover")
	public ModelAndView selectWoMen_pullover() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByWomenPullover();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("women_pullover", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/womenCategory/women_pullover");
		return mv;
	}

	@GetMapping("/women/tops")
	public ModelAndView selectWoMenTops() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByWomenTops();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("women_tops", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/womenCategory/women_tops");
		return mv;
	}

	@GetMapping("/women/accessoires")
	public ModelAndView selectWoMen_accessoires() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByWomenAccessoires();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("women_accessoires", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/womenCategory/women_accessoires");
		return mv;
	}

	@GetMapping("/women/shoes")
	public ModelAndView selectWoMen_Shoes() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByWomenShoes();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("women_shoes", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/womenCategory/women_shoes");
		return mv;
	}

	@GetMapping("/women/mixed")
	public ModelAndView selectWoMen_mixed() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByWomenMixedProduct();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("women_mixed", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/womenCategory/women_mixed");
		return mv;
	}

	/*
	 * 
	 * ..................kid..........category.............................48
	 */
	@GetMapping("/kid/shirt")
	public ModelAndView selectKidShirt() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByKidShirt();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("kid_shirt", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/kidCategory/kid_shirt");
		return mv;
	}

	@GetMapping("/kid/jeans")
	public ModelAndView selectKidJeans() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByKidJeans();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("kid_jeans", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/kidCategory/kid_jeans");
		return mv;
	}

	@GetMapping("/kid/jacket")
	public ModelAndView selectKidJacket() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByKidJacket();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("kid_jacket", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/kidCategory/kid_jacket");
		return mv;
	}

	@GetMapping("/kid/trousers")
	public ModelAndView selectKid_trousers() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByKidtrousers();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("kid_trousers", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/kidCategory/kid_trousers");
		return mv;
	}

	@GetMapping("/kid/sweat/shirt")
	public ModelAndView selectKid_sweat_shirt() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllBykidSweat();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("kid_sweat_shirt", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/kidCategory/kid_sweat_shirt");
		return mv;
	}

	@GetMapping("/kid/pullover")
	public ModelAndView selectKid_pullover() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByKidPullover();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("kid_pullover", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/kidCategory/kid_pullover");
		return mv;
	}

	@GetMapping("/kid/jumper")
	public ModelAndView selectKidJumper() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByKidJumper();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("kid_jumper", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/kidCategory/kid_jumper");
		return mv;
	}

	@GetMapping("/kid/mixed")
	public ModelAndView selectKid_mixed() {
		ModelAndView mv = new ModelAndView();
		// *************************************************************************************************
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		mv.addObject("userName", user.getFirst_name() + " " + user.getLast_name());
		// **************************************************************************************************

		List<Lot> lot = (List<Lot>) lotRepository.findAllByKidMixedProduct();

		List<SliderImages> images = sliderRepo.findAll();
		mv.addObject("img", images);

		mv.addObject("kid_mixed", lot);
		mv.addObject("img", images);

		mv.setViewName("/dropdownFilterMenu/kidCategory/kid_mixed");
		return mv;
	}

}// end of the class
