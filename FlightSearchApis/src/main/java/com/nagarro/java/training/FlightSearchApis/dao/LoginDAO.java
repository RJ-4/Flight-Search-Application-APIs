package com.nagarro.java.training.FlightSearchApis.dao;

import com.nagarro.java.training.FlightSearchApis.models.User;

public interface LoginDAO {

	public User fetchUser(User user) throws Exception;
}
