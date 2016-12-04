package edu.auctionhsa.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.auctionhsa.Application;
import edu.auctionhsa.GeneralTest;
import edu.auctionhsa.dao.BidDAO;
import edu.auctionhsa.dao.ItemDAO;
import edu.auctionhsa.model.Bid;
import edu.auctionhsa.model.Item;
import edu.auctionhsa.model.User;
import edu.auctionhsa.model.exception.InvalidAmountException;

@RunWith(SpringRunner.class) 
@WebMvcTest(AuctionController.class)
@ContextConfiguration(classes = Application.class)
public class AuctionControllerTest extends GeneralTest{
	
	@MockBean
	ItemDAO itemDAO;
	
	@MockBean
	BidDAO bidDAO;
	
	@Autowired
    private MockMvc mvc;
	
	@Autowired 
	ObjectMapper mapper;
	
	
	//Test variables
	Long ID1 = 1L;
	Long ID2 = 2L;
	Long ID3 = 3L;
	
	String N1 = "I1";
	String N2 = "I2";
	String N3 = "I3";
	
	List<Item> items = new ArrayList<>();
	Item i1 = new Item();
	Item i2 = new Item();
	Item i3 = new Item();
	
	Bid b1 = new Bid(500L, i1);
	Bid b2 = new Bid(300L, i1);
	
	@Before
	public void setup()throws Exception{
		i1.setId(ID1);
		i2.setId(ID2);
		i3.setId(ID3);
		
		i1.setName(N1);
		i2.setName(N2);
		i3.setName(N3);
		
		i1.setAuctionEnd(new Date(new Date().getTime()+24*60*60*5));
		i1.setInitialPrice(400L);
		
		User usr1 = new User();
		usr1.setId(ID1);
		usr1.setUsr("usr");
		i1.setSeller(usr1);
		
		items.add(i1);
		items.add(i2);
		items.add(i3);
		
		b1.setId(ID1);
		b2.setId(ID2);
		
		given(itemDAO.findAll()).willReturn(items);
		given(itemDAO.findEagerly(ID1)).willReturn(i1);
		given(bidDAO.save(i1, 500L, "usr1")).willReturn(b1);
	}
	
	@Test
	@WithMockUser
	public void testGetAll()throws Exception{
		mvc.perform(get("/auctions")
	            .accept(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.length()").value(3))
	            .andExpect(jsonPath("$[0].name", is(N1)))
	            .andExpect(jsonPath("$[1].name", is(N2)));;    
	}
	
	@Test
	@WithMockUser
	public void testGetItem()throws Exception{
		mvc.perform(get("/auctions/{id}", ID1)
	            .accept(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.id", is(1)))
	            .andExpect(jsonPath("$.name", is(N1)));  

	}
	
	@Test
	@WithMockUser
	public void testPlaceBid()throws Exception{
		
		String requestJson=mapper.writeValueAsString(i1) ;
		
        mvc.perform(post("/auctions/{amount}", 500)
        	.contentType(MediaType.APPLICATION_JSON)
        	.content(requestJson)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

	}
	




}
