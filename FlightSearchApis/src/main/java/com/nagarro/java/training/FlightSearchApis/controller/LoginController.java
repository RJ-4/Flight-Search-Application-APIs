package com.nagarro.java.training.FlightSearchApis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.java.training.FlightSearchApis.models.User;
import com.nagarro.java.training.FlightSearchApis.services.LoginService;

@RestController
@CrossOrigin
public class LoginController {

	@Autowired
	LoginService loginService;
	
	@PostMapping("/api/login")
	public User loginUser(@RequestBody User currentUser) {
		
		return loginService.checkUser(currentUser);
	}
}
