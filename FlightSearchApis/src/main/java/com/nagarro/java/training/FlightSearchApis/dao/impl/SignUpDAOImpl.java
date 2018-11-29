package com.nagarro.java.training.FlightSearchApis.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nagarro.java.training.FlightSearchApis.dao.SignUpDAO;
import com.nagarro.java.training.FlightSearchApis.models.User;

@Repository
public class SignUpDAOImpl implements SignUpDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public User insertNewUser(User user) throws Exception{
		
		Session session = sessionFactory.getCurrentSession();
		
		session.save(user);
		
		return user;
	}

}
