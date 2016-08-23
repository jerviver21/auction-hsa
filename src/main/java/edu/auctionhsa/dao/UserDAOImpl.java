package edu.auctionhsa.dao;

import edu.auctionhsa.model.User;

public class UserDAOImpl extends GenericDAOImpl<User, Long> implements UserDAO{
	
	public UserDAOImpl(){
		super(User.class);
	}

}
