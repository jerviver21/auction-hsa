package edu.auctionhsa.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.auctionhsa.dao.BidDAO;
import edu.auctionhsa.dao.ItemDAO;
import edu.auctionhsa.model.Bid;
import edu.auctionhsa.model.Item;
import edu.auctionhsa.model.exception.InvalidAmountException;

@RestController
public class AuctionController {
	
	@Autowired
	ItemDAO itemDAO;
	
	@Autowired
	BidDAO bidDAO;
	
	
	@RequestMapping(value="/auctions", method=RequestMethod.GET)
	public List<Item> getItemsForAuction(){
		List<Item> items = itemDAO.findAll();
		return items;
	}
	
	@RequestMapping(value="/auctions/{id}", method=RequestMethod.GET)
	public Item getItem(@PathVariable Long id){
		Item item = itemDAO.findEagerly(id);
		return item;
	}
	
	
	@RequestMapping(value="/auctions/{amount}", method=RequestMethod.POST)
	public Bid placeBid(@RequestBody @Valid Item item, @PathVariable Long amount, Principal principal)throws InvalidAmountException{
		System.out.println(item + " - "+amount+" - "+principal.getName() );
        Bid bid = bidDAO.save(item, amount, principal.getName());
		return bid;
	}

}
