package edu.auctionhsa.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.auctionhsa.dao.UserDAO;
import edu.auctionhsa.model.User;


@RestController
public class UserController {
	
	@Autowired
	UserDAO userDAO;
	
	@RequestMapping(value="/users", method=RequestMethod.GET)
	public List<User> getAll(){
		List<User> users = userDAO.findAll();
		return users;
	}
	
	@RequestMapping(value="/users/{id}", method=RequestMethod.GET)
	public User getUser(@PathVariable Long id){
		User user = userDAO.findById(id);
		return user;
	}
	
	@RequestMapping(value="/users/{id}", method=RequestMethod.DELETE)
	public boolean removeUser(@PathVariable Long id){
		userDAO.remove(userDAO.findById(id));
		return true;
	}
	
	@RequestMapping(value="/users", method=RequestMethod.POST)
	public User saveUser(@RequestBody @Valid User usr){
		User user=userDAO.save(usr);
		return user;
	}
	
	

}
