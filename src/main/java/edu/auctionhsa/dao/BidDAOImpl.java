package edu.auctionhsa.dao;

import org.springframework.stereotype.Repository;

import edu.auctionhsa.model.Bid;
import edu.auctionhsa.model.Item;
import edu.auctionhsa.model.User;

@Repository
public class BidDAOImpl extends GenericDAOImpl<Bid, Long> implements BidDAO{
	
	public BidDAOImpl(){
		super(Bid.class);
	}
	
	@Override 
	public Bid save(Bid bid, Long idUsr){
		bid.setUser(em.getReference(User.class, idUsr));
		return em.merge(bid);
	}

}
