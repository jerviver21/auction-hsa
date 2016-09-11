package edu.auctionhsa.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import edu.auctionhsa.model.Item;
import edu.auctionhsa.model.User;


public interface ItemDAO extends GenericDAO<Item, Long> {
	
	public List<Item> findByUser(String usr);
	
	public Item saveImage(Long id, MultipartFile file)throws IOException;

	public Item findEagerly(Long id);

}
