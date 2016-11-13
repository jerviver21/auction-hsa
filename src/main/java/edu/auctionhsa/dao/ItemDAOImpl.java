package edu.auctionhsa.dao;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import edu.auctionhsa.Constants;
import edu.auctionhsa.model.Image;
import edu.auctionhsa.model.Item;
import edu.auctionhsa.model.User;
import edu.auctionhsa.utils.FileManager;

@Repository
public class ItemDAOImpl extends GenericDAOImpl<Item, Long> implements ItemDAO {
	
	@Autowired
	UserDAO userDAO;
	
	public ItemDAOImpl(){
		super(Item.class);
	}
	
	public ItemDAOImpl(EntityManager em, UserDAO userDAO){
		super(Item.class, em);
		this.userDAO = userDAO;
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

	@Override
	public Item saveImage(Long id, MultipartFile io)throws IOException {
		Item item = em.getReference(Item.class, id);
		Set<Image> images = item.getImages();
		String fullpath = new FileManager().saveFile(Constants.PATH_ITEMS_IMAGES, "img_"+id+"_"+(images.size()+1), io);
		Image image = new Image();
		image.setPath(Constants.RELATIVE_ITEMS_IMAGES+fullpath.replaceAll(".*/(.*)", "$1"));
		images.add(image);
		em.merge(item);
		return item;
	}
	
	public Item findEagerly(Long id){
		TypedQuery<Item> query = em.createQuery("SELECT i FROM Item i WHERE i.id =:id", Item.class).setParameter("id", id);
		Item item = query.getSingleResult();
		item.getImages().size();
		item.getBidsCollection().size();
		item.getSeller().getUsr();
		return item;
	}
	

}
