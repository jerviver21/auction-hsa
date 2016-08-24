package edu.auctionhsa.dao;

import java.util.List;

import edu.auctionhsa.model.Item;
import edu.auctionhsa.model.User;


public interface ItemDAO extends GenericDAO<Item, Long> {
	
	public List<Item> findByUser(String usr);

}
