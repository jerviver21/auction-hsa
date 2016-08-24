package edu.auctionhsa.controller;

import java.util.List;

import javax.persistence.NoResultException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.auctionhsa.dao.ItemDAO;
import edu.auctionhsa.model.Item;
import edu.auctionhsa.model.User;


@RestController
public class ItemController {
	
	@Autowired
	ItemDAO itemDAO;
	
	@RequestMapping(value="/items", method=RequestMethod.GET)
	public List<Item> getItems(){
		List<Item> items = null;
		try{
			String usr = "jerviver21";//Mientras implementamos seguridad de usuarios
			items = itemDAO.findByUser(usr);
		}catch(Exception e2){
			e2.printStackTrace();
		}
		return items;
	}
	
	@RequestMapping(value="/items/find", method=RequestMethod.GET)
	public Item getItem(Long id){
		Item item = null;
		try{
			item = itemDAO.findById(id);
		}catch(NoResultException e1){
			System.out.println("El item no existe");
		}catch(Exception e2){
			e2.printStackTrace();
		}
		return item;
	}
	
	@RequestMapping(value="/items", method=RequestMethod.POST)
	public boolean saveItem(@RequestBody @Valid Item item){
		boolean saveSucessful = false;
		try{
			item.setSeller(new User(1L));
			itemDAO.save(item);
			saveSucessful = true;
		}catch(Exception e2){
			e2.printStackTrace();
		}
		return saveSucessful;
	}
	
	@RequestMapping(value="/items", method=RequestMethod.DELETE)
	public boolean removeIem(Long id){
		boolean success = false;
		try{
			itemDAO.remove(itemDAO.findById(id));
			success = true;
		}catch(Exception e2){
			e2.printStackTrace();
		}
		return success;
	}
	
	

}
