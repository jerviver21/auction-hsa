package edu.auctionhsa.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.auctionhsa.Application;
import edu.auctionhsa.dao.UserDAO;
import edu.auctionhsa.model.User;



/**
 * Integration test:
 * These integration test are used to verify that all the classes work together
 * the data used is rollbacked, thus all the data is created and removed it.
 * 
 * @author Jerson Viveros
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Application.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@Transactional
public class UserControllerDBIntTest{ 
	@Autowired
	UserController userController;
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired 
	ObjectMapper mapper;
	
	@PersistenceContext
    protected EntityManager em;
		
	//Data to test
	long ID1;
	long ID2;
	
	final String USR1 = "yanethgn1";
	final String USR2 = "yanethgn2";
	final String USR3 = "yanethgn3";
	
	User user1;
	User user2;
	
	@Before
	public void setup(){
		user1 = new User();
		user1.setUsr(USR1);
		
		user2 = new User();
		user2.setUsr(USR2);
		
		user1 = em.merge(user1);
		user2 = em.merge(user2);    
		
		ID1 = user1.getId();
		ID2 = user2.getId();
	}
	
	@Test
    public void testGetUser() throws Exception{
		User user =  userController.getUser(ID1); 
		assertThat(user.getUsr()).isEqualTo(USR1);
    }
	
	@Test
    public void testGetAll() throws Exception{
        List<User> users =  userController.getAll();
        assertThat(users.contains(user1)).isEqualTo(true);
        assertThat(users.contains(user2)).isEqualTo(true);
    }

	@Test
    public void testRemove() throws Exception{
        userController.removeUser(ID1); 
        User user =  userController.getUser(ID1); 
        assertThat(user).isNull();
    }
	
	@Test(expected=InvalidDataAccessApiUsageException.class)
    public void testRemoveFail() throws Exception{
        userController.removeUser(3L); 
    }
	
	@Test
    public void testSaveUser() throws Exception{
		User user3 = new User();
		user3.setUsr(USR3);
		user3 = userController.saveUser(user3);
		assertThat(user3).isNotNull();
		long ID3 = user3.getId();
		assertThat(ID3).isNotNull();		
    }
}


