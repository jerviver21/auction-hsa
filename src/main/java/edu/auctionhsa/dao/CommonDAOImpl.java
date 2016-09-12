package edu.auctionhsa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.auctionhsa.model.Divipola;

@Repository
@Transactional
public class CommonDAOImpl implements CommonDAO {
	
	// Spring will inject here the entity manager object
	@PersistenceContext
    protected EntityManager em;
	
	public List<Divipola> getRegions(){
		return em.createQuery("SELECT d FROM Divipola d WHERE d.department is null").getResultList();
	}
	
	public List<Divipola> getCities(Integer idRegion){
		return em.createQuery("SELECT d FROM Divipola d WHERE d.department.id =:idRegion")
				.setParameter("idRegion",idRegion).getResultList();
	}

}
