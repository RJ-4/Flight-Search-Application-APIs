package com.nagarro.java.training.FlightSearchApis.services.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.java.training.FlightSearchApis.dao.LoginDAO;
import com.nagarro.java.training.FlightSearchApis.exceptions.UserNotFoundException;
import com.nagarro.java.training.FlightSearchApis.models.User;
import com.nagarro.java.training.FlightSearchApis.services.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	LoginDAO loginDAO;
	
	@Override
	@Transactional
	public User checkUser(User user) {
		
		try {
			
			return loginDAO.fetchUser(user);
		
		} catch (Exception e) {
		
			e.printStackTrace();

			throw new UserNotFoundException("Invalid credentials");
		}
		
	}

}
