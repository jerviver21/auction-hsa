package edu.auctionhsa.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.auctionhsa.model.Item;
import edu.auctionhsa.model.User;

@Repository
public class ItemDAOImpl extends GenericDAOImpl<Item, Long> implements ItemDAO {
	
	@Autowired
	UserDAO userDAO;
	
	public ItemDAOImpl(){
		super(Item.class);
	}
	
	@Override
	public List<Item> findAll() {
		return em.createQuery("SELECT i FROM Item i WHERE i.isPublished = true").getResultList();
	}
	
	@Override
	public List<Item> findByUser(String usr) {
		User user = userDAO.findByUsr(usr);
		return em.createQuery("SELECT i FROM Item i WHERE i.seller =:usr")
				.setParameter("usr", user).getResultList();
	}
	
	@Override 
	public Item save(Item item){
		item.setSeller(em.getReference(User.class, item.getSeller().getId()));
		return em.merge(item);
	}
	

}
