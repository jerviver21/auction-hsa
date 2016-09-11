package edu.auctionhsa.controller;

import java.io.IOException;
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
import edu.auctionhsa.model.Item;
import edu.auctionhsa.model.User;


@RestController
public class ItemController {
	
	@Autowired
	ItemDAO itemDAO;
	
	@RequestMapping(value="/items", method=RequestMethod.GET)
	public List<Item> getItems(){
		String usr = "jerviver21";//Mientras implementamos seguridad de usuarios
		List<Item> items = itemDAO.findByUser(usr);
		return items;
	}
	
	@RequestMapping(value="/items/{id}", method=RequestMethod.GET)
	public Item getItem(@PathVariable Long id){
		Item item = null;
		item = itemDAO.findEagerly(id);
		return item;
	}
	
	@RequestMapping(value="/items", method=RequestMethod.POST)
	public Item saveItem(@RequestBody @Valid Item item){
		Item itemResp = null;
		item.setSeller(new User(1L));
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
