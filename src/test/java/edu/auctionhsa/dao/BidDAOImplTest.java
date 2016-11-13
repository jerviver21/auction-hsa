package edu.auctionhsa.dao;

import static org.junit.Assert.fail;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;

import edu.auctionhsa.model.Bid;
import edu.auctionhsa.model.Item;
import edu.auctionhsa.model.User;
import edu.auctionhsa.model.exception.InvalidAmountException;

public class BidDAOImplTest {	
	//Mocks
	ItemDAO itemDAO = mock(ItemDAO.class);
	UserDAO userDAO = mock(UserDAO.class);
	EntityManager em = mock(EntityManager.class);
	
	//Test objects
	Long ID1 = 1L;
	String NI1 = "NI1";
	String US1 = "US1";
	Long IA1 = 400L;
	Long O1 = 500L;
	Long O2 = 300L;
	
	Item i1 = new Item();
	Bid b1 = new Bid(500L, i1);
	User u1 = new User();

	@Before
	public void setUp() throws Exception {	
		
		b1.setUser(u1);
		
		Bid b2 = new Bid(b1.getAmount(), b1.getItem());
		b2.setUser(b1.getUser());
		b2.setId(ID1);
		
		u1.setId(ID1);
		u1.setUsr(US1);
		
		i1.setId(ID1);
		i1.setName(NI1);
		i1.setSeller(new User());
		i1.setInitialPrice(IA1);
		i1.setAuctionEnd(new Date(new Date().getTime()+5*24*60*60*1000));
		
		willDoNothing().given(itemDAO).checkVersion(i1);
		when(em.merge(b1)).thenReturn(b2);
		when(em.merge(u1)).thenReturn(u1);
		when(userDAO.findByUsr(US1)).thenReturn(u1);
	}

	@Test
	public void testSave() throws Exception{
		BidDAO bidDAO = new BidDAOImpl(em, userDAO, itemDAO);
		Bid bid = bidDAO.save(i1, O1, US1);

		assertThat(bid.getAmount() == O1);
		assertThat(bid.getItem().equals(i1));
		assertThat(bid.getUser().equals(u1));
	}
	
	@Test(expected=InvalidAmountException.class)
	public void testSaveException() throws Exception{
		BidDAO bidDAO = new BidDAOImpl(em, userDAO, itemDAO);
		Bid bid = bidDAO.save(i1, O2, US1);
	}
	
	

}
