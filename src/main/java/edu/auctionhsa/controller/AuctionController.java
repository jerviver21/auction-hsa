package edu.auctionhsa.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
		List<Item> items = null;
		try{
			items = itemDAO.findAll();
		}catch(Exception e2){
			e2.printStackTrace();
		}
		return items;
	}
	
	@Transactional
	@RequestMapping(value="/auctions/bid", method=RequestMethod.POST)
	public boolean placeBid(@RequestBody @Valid Item item, Long amount){
		boolean success = false;
		try{
			if (!item.isValidBidAmount(amount)) {
	            return false;
	        }
	        itemDAO.checkVersion(item);
	        bidDAO.save(new Bid(amount, item));
		}catch(Exception ex1){
			ex1.printStackTrace();
		}
		return success;
	}

}
