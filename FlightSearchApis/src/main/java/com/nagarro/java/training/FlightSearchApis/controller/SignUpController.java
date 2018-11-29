package com.nagarro.java.training.FlightSearchApis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.java.training.FlightSearchApis.models.User;
import com.nagarro.java.training.FlightSearchApis.services.SignUpService;

@RestController
@CrossOrigin
public class SignUpController {

	@Autowired
	SignUpService signUpService;
	
	@PostMapping("/api/sign-up")
	public User signUpUser(@RequestBody User newUser) {
		
		return signUpService.registerNewUser(newUser);
	}
}
