package edu.auctionhsa.dao;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.auctionhsa.model.Bid;
import edu.auctionhsa.model.Item;
import edu.auctionhsa.model.User;
import edu.auctionhsa.model.exception.InvalidAmountException;

@Repository
public class BidDAOImpl extends GenericDAOImpl<Bid, Long> implements BidDAO{
	
	@Autowired
	UserDAO usrDAO;
	
	@Autowired
	ItemDAO itemDAO;
	
	public BidDAOImpl(){
		super(Bid.class);
	}
	
	public BidDAOImpl(EntityManager em, UserDAO userDAO, ItemDAO itemDAO){
		super(Bid.class, em);
		this.usrDAO = userDAO;
		this.itemDAO = itemDAO;
	}
	
	@Override 
	@Transactional
	public Bid save(Item item, Long amount, String usrName)throws InvalidAmountException{
		
		if (!item.isValidBidAmount(amount)) {
            throw new InvalidAmountException();
        }
        itemDAO.checkVersion(item);
		
		User usr = usrDAO.findByUsr(usrName);
		if(usr == null){
			usr = new User();
			usr.setUsr(usrName);
			usr = em.merge(usr);
		}
		
		Bid bid = new Bid(amount, item);
		bid.setUser(usr);
		return em.merge(bid);
	}

}
