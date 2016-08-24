package edu.auctionhsa.dao;

import org.springframework.stereotype.Repository;

import edu.auctionhsa.model.Bid;

@Repository
public class BidDAOImpl extends GenericDAOImpl<Bid, Long> implements BidDAO{
	
	public BidDAOImpl(){
		super(Bid.class);
	}

}
