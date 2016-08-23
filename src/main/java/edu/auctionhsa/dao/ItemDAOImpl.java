package edu.auctionhsa.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import edu.auctionhsa.model.Item;

@Repository
public class ItemDAOImpl extends GenericDAOImpl<Item, Long> implements ItemDAO {
	
	public ItemDAOImpl(){
		super(Item.class);
	}
	
	public List<Item> findAll() {
		return em.createQuery("SELECT i FROM Item i WHERE i.isPublished = true").getResultList();
	}
}
