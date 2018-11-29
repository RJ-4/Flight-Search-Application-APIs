package com.nagarro.java.training.FlightSearchApis.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nagarro.java.training.FlightSearchApis.dao.LoginDAO;
import com.nagarro.java.training.FlightSearchApis.models.User;

import static com.nagarro.java.training.FlightSearchApis.constants.Constants.*;

@Repository
public class LoginDAOImpl implements LoginDAO {

	@Autowired
	SessionFactory factory;
	
	@Override
	public User fetchUser(User user) throws Exception {

		Session session = factory.getCurrentSession();
		
		String loginQuery = LOGIN_QUERY;
		
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery(loginQuery);
		
		query.setParameter(USERNAME_LABEL, user.getUsername());
		
		query.setParameter(PASSWORD_LABEL, user.getPassword());
		
		User loggedInUser = (User)query.getSingleResult();
		
		return loggedInUser;
	}

}
