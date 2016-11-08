package edu.auctionhsa.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import edu.auctionhsa.dao.ItemDAO;
import edu.auctionhsa.dao.UserDAO;
import edu.auctionhsa.model.Item;
import edu.auctionhsa.model.User;


@RestController
public class ItemController {
	
	@Autowired
	ItemDAO itemDAO;
	
	@Autowired
	UserDAO userDAO;
	
	@RequestMapping(value="/items", method=RequestMethod.GET)
	public List<Item> getItems(Principal principal){
		List<Item> items = itemDAO.findByUser(principal.getName());
		return items;
	}
	
	@RequestMapping(value="/items/{id}", method=RequestMethod.GET)
	public Item getItem(@PathVariable Long id){
		Item item = null;
		item = itemDAO.findEagerly(id);
		return item;
	}
	
	@RequestMapping(value="/items", method=RequestMethod.POST)
	public Item saveItem(@RequestBody @Valid Item item, Principal principal){
		Item itemResp = null;
		item.setSeller(userDAO.findByUsr(principal.getName()));
		itemResp = itemDAO.save(item);
		return itemResp;
	}
	
	@RequestMapping(value="/items/{id}", method=RequestMethod.DELETE)
	public boolean removeIem(@PathVariable Long id){
		itemDAO.remove(itemDAO.findById(id));
		return true;
	}
	
	@RequestMapping(value="/items/image/upload", method=RequestMethod.POST)
	public Item updateImage(MultipartFile file, @RequestParam(value = "params", required = false) Long id)throws IOException{
		Item item = itemDAO.saveImage(id, file);
		return item;
	}
	
	

}
