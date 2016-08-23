package edu.auctionhsa.dao;

import edu.auctionhsa.model.Bid;

public class BidDAOImpl extends GenericDAOImpl<Bid, Long> implements BidDAO{
	
	public BidDAOImpl(){
		super(Bid.class);
	}

}
