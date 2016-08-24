package edu.auctionhsa.dao;

import edu.auctionhsa.model.User;

public interface UserDAO extends GenericDAO<User, Long>{
	
	public User findByUsr(String usr);

}
