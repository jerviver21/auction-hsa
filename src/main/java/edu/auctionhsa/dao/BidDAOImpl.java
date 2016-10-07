package edu.auctionhsa.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.auctionhsa.model.Bid;
import edu.auctionhsa.model.User;

@Repository
public class BidDAOImpl extends GenericDAOImpl<Bid, Long> implements BidDAO{
	
	@Autowired
	UserDAO usrDAO;
	
	public BidDAOImpl(){
		super(Bid.class);
	}
	
	@Override 
	public Bid save(Bid bid, String usrName){
		
		User usr = usrDAO.findByUsr(usrName);
		if(usr == null){
			usr = new User();
			usr.setUsr(usrName);
			usr = em.merge(usr);
		}
		
		bid.setUser(usr);
		return em.merge(bid);
	}

}
