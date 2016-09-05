package edu.auctionhsa.dao;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;

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
		String path = new FileManager().saveFile("img_"+id+"_"+(images.size()+1), io);
		Image image = new Image();
		image.setPath(path);
		images.add(image);
		em.merge(item);
		return item;
	}
	

}
