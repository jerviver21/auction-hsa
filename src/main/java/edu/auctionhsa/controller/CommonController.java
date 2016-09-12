package edu.auctionhsa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.auctionhsa.dao.CommonDAO;
import edu.auctionhsa.model.Divipola;

@RestController
public class CommonController {
	
	@Autowired
	CommonDAO commonDAO;
	
	
	@RequestMapping(value="/commons/regions", method=RequestMethod.GET)
	public List<Divipola> getRegions(){
		List<Divipola> regions = commonDAO.getRegions();
		return regions;
	}
	
	@RequestMapping(value="/commons/cities/{id}", method=RequestMethod.GET)
	public List<Divipola> getCities(@PathVariable Integer id){
		List<Divipola> cities = commonDAO.getCities(id);
		return cities;
	}

}
