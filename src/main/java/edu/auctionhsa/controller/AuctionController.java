package edu.auctionhsa.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.auctionhsa.dao.BidDAO;
import edu.auctionhsa.dao.ItemDAO;
import edu.auctionhsa.model.Bid;
import edu.auctionhsa.model.Item;

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
		Item item = null;
		item = itemDAO.findEagerly(id);
		return item;
	}
	
	@Transactional
	@RequestMapping(value="/auctions/{amount}", method=RequestMethod.POST)
	public boolean placeBid(@RequestBody @Valid Item item, @PathVariable Long amount){
		System.out.println(item + " - "+amount );
		if (!item.isValidBidAmount(amount)) {
            return false;
        }
        itemDAO.checkVersion(item);
        bidDAO.save(new Bid(amount, item), 1L);
		return true;
	}

}
