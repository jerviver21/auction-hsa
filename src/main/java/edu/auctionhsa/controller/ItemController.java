package edu.auctionhsa.controller;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.auctionhsa.dao.ItemDAO;
import edu.auctionhsa.model.Item;
import edu.auctionhsa.model.User;


@RestController
public class ItemController {
	
	@Autowired
	ItemDAO itemDao;
	
	@RequestMapping(value="/items", method=RequestMethod.GET)
	public List<Item> getItems(){
		//Calendar
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 1);
		
		
		//User
		User usr = new User();
		usr.setName("Jerson");
		usr.setNumId("16942249");
		usr.setUsr("jerviver21");
		usr.setPwd("clave123");
		
		
		Item item = new Item();
		item.setAuctionEnd(calendar.getTime());
		item.setAudUsrModified("Usr1");
		item.setIdSeller(usr);
		item.setName("Item1");
		itemDao.save(item);
		return itemDao.findAll();
	}

}
