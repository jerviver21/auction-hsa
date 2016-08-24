package edu.auctionhsa.controller;

import javax.persistence.NoResultException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@RequestMapping(value="/users/find", method=RequestMethod.GET)
	public User getUser(String usr){
		User user = null;
		try{
			user = userDAO.findByUsr(usr);
		}catch(NoResultException e1){
			System.out.println("El usuario no existe");
		}catch(Exception e2){
			e2.printStackTrace();
		}
		return user;
	}
	
	@RequestMapping(value="/users", method=RequestMethod.POST)
	public boolean saveUser(@RequestBody @Valid User usr){
		boolean success = false;
		try{
			userDAO.save(usr);
			success = true;
		}catch(Exception e2){
			e2.printStackTrace();
		}
		return success;
	}
	
	

}
