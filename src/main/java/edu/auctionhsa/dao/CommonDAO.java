package edu.auctionhsa.dao;

import java.util.List;

import edu.auctionhsa.model.Divipola;

public interface CommonDAO {
	
	public List<Divipola> getRegions();
	
	public List<Divipola> getCities(Integer idRegion);

}
