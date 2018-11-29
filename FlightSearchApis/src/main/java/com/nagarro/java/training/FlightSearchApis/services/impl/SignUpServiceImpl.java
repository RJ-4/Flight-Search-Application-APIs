package com.nagarro.java.training.FlightSearchApis.services.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.java.training.FlightSearchApis.dao.SignUpDAO;
import com.nagarro.java.training.FlightSearchApis.exceptions.UsernameAlreadyTakenException;
import com.nagarro.java.training.FlightSearchApis.models.User;
import com.nagarro.java.training.FlightSearchApis.services.SignUpService;

@Service
public class SignUpServiceImpl implements SignUpService {

	@Autowired
	SignUpDAO signUpDAO;
	
	@Override
	@Transactional
	public User registerNewUser(User user) {
		
		try {
			
			return signUpDAO.insertNewUser(user);
		
		} catch (Exception e) {

			e.printStackTrace();
			
			throw new UsernameAlreadyTakenException();
		}
	}

}
