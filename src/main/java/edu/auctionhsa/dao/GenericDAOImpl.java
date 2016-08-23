package edu.auctionhsa.dao;

import static javax.persistence.LockModeType.NONE;
import static javax.persistence.LockModeType.OPTIMISTIC;
import static javax.persistence.LockModeType.OPTIMISTIC_FORCE_INCREMENT;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class GenericDAOImpl<T, ID extends Serializable> implements GenericDAO<T, ID> {

	// Spring will inject here the entity manager object
	@PersistenceContext
    protected EntityManager em;
	
    protected Class<T> entityClass;
    
    protected GenericDAOImpl() {
    }

    protected GenericDAOImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public T findById(ID id) {
        return findById(id, NONE);
    }

    @Override
    public T findById(ID id, LockModeType lockModeType) {
        return em.find(entityClass, id, lockModeType);
    }

    @Override
    public T findReferenceById(ID id) {
        return em.getReference(entityClass, id);
    }

    @Override
    public List<T> findAll() {
        CriteriaQuery<T> c = em.getCriteriaBuilder().createQuery(entityClass);
        c.select(c.from(entityClass));
        return em.createQuery(c).getResultList();
    }

    @Override
    public Long getCount() {
        CriteriaQuery<Long> c = em.getCriteriaBuilder().createQuery(Long.class);
        c.select(em.getCriteriaBuilder().count(c.from(entityClass)));
        return em.createQuery(c).getSingleResult();
    }

    @Override
    public void checkVersion(T entity) {
        em.lock(entity, OPTIMISTIC_FORCE_INCREMENT );
    }

    @Override
    public T save(T instance) {
        // merge() handles transient AND detached instances
        return em.merge(instance);
    }

    @Override
    public void remove(T instance) {
        em.remove(instance);
    }
}
