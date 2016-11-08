package edu.auctionhsa.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.auctionhsa.Application;
import edu.auctionhsa.dao.UserDAO;
import edu.auctionhsa.model.User;


/**
 * Integration test:
 * These integration test are used to verify the spring mvc controllers, the json data
 * and even could be test the authentication and autorization issues.
 * @author Jerson Viveros
 */

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@ContextConfiguration(classes = Application.class)
public class UserControllerTest {
	final long ID1 = 1L;
	final long ID2 = 2L;
	final long ID3 = 3L;
	
	@Autowired
    private MockMvc mvc;
	
	@MockBean
	UserDAO userDAO;
	
	@Autowired 
	ObjectMapper mapper;
	
	@Before
	public void setup(){

		User user1 = new User();
		user1.setId(ID1);
		user1.setUsr("jerviver21");
		
		User user2 = new User();
		user2.setId(ID2);
		user2.setUsr("yanethgn1");
		
		User user3 = new User();
		user3.setUsr("ragnar");
		
		User user3WithId = new User();
		user3WithId.setId(ID3);
		user3WithId.setUsr("ragnar");
		
		List<User> users = new ArrayList<>();
		users.add(user1);
		users.add(user2);

		given(userDAO.findById(ID1)).willReturn(user1);
		given(userDAO.findAll()).willReturn(users);
		willDoNothing().given(userDAO).remove(user1);
		given(userDAO.save(user3)).willReturn(user3WithId);
	}
	
	@Test
	@WithMockUser
    public void testGetUser() throws Exception{
        mvc.perform(get("/users/{id}", ID1)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(1)))
            .andExpect(jsonPath("$.usr", is("jerviver21")));  
    }
	
	@Test
	@WithMockUser
    public void testGetAll() throws Exception{
        mvc.perform(get("/users")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(2))
            .andExpect(jsonPath("$[0].usr", is("jerviver21")))
            .andExpect(jsonPath("$[1].usr", is("yanethgn1")));;  
    }
	
	@Test
	@WithMockUser
    public void testRemove() throws Exception{
        mvc.perform(delete("/users/{id}", ID1)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());  
    }
	
	@Test
	@WithMockUser
    public void testSaveUser() throws Exception{
		User user3 = new User();
		user3.setUsr("ragnar");
		
		User user3WithId = new User();
		user3WithId.setId(ID3);
		user3WithId.setUsr("ragnar");
		

	    String requestJson=mapper.writeValueAsString(user3 );
		
        mvc.perform(post("/users")
        	.contentType(MediaType.APPLICATION_JSON)
        	.content(requestJson)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(3)))
            .andExpect(jsonPath("$.usr", is("ragnar")));  
    }
	
	



}
