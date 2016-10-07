package edu.auctionhsa.dao;

import edu.auctionhsa.model.Bid;

public interface BidDAO extends GenericDAO<Bid, Long> {
	public Bid save(Bid bid, String usrName);
}
