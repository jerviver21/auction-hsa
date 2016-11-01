package edu.auctionhsa.dao;

import edu.auctionhsa.model.Bid;
import edu.auctionhsa.model.Item;
import edu.auctionhsa.model.exception.InvalidAmountException;

public interface BidDAO extends GenericDAO<Bid, Long> {
	public Bid save(Item item, Long amount, String usrName)throws InvalidAmountException;
}
