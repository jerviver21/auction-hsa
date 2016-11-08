package edu.auctionhsa.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import edu.auctionhsa.Application;


@RunWith(SpringRunner.class)
@WebMvcTest(LoginController.class)
@ContextConfiguration(classes = Application.class)
public class LoginControllerTest {
	
final long ID = 1L;
	
	@Autowired
    private MockMvc mvc;
	
	
	@Test
    public void testGetUser() throws Exception{
        mvc.perform(get("/user")
        	.with(user("jerviver21"))
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.principal.username", is("jerviver21")));
    }


}
