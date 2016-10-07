package edu.auctionhsa.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import edu.auctionhsa.model.User;

@Repository
public class UserDAOImpl extends GenericDAOImpl<User, Long> implements UserDAO{
	

	public UserDAOImpl(){
		super(User.class);
	}
	
	@Override
	public User findByUsr(String usr) {
		List<User> list = em.createQuery("SELECT u FROM User u WHERE u.usr =:usr")
		   .setParameter("usr", usr).getResultList();
		return list.isEmpty()?null:list.get(0);
	}
}
