package edu.auctionhsa.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.auctionhsa.model.Item;

@Repository
@Transactional
public class ItemDAO {

	// Spring will inject here the entity manager object
	@PersistenceContext
	private EntityManager em;
	
	
	public List<Item> findAll(){
		return em.createNamedQuery("Items.findAll").getResultList();
	}
	
	
	
}
