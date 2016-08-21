package edu.auctionhsa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.auctionhsa.model.Item;
import edu.auctionhsa.service.ItemDAO;


@RestController
public class ItemController {
	
	@Autowired
	ItemDAO itemDao;
	
	@RequestMapping(value="/items", method=RequestMethod.GET)
	public List<Item> getItems(){
		return itemDao.findAll();
	}

}
