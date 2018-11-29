package com.nagarro.java.training.FlightSearchApis.dao;

import com.nagarro.java.training.FlightSearchApis.models.User;

public interface SignUpDAO {

	public User insertNewUser(User user) throws Exception;
}
