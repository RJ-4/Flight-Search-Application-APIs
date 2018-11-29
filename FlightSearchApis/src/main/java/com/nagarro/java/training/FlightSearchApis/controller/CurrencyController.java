package com.nagarro.java.training.FlightSearchApis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.java.training.FlightSearchApis.models.Currency;
import com.nagarro.java.training.FlightSearchApis.services.CurrencyService;

@RestController
@CrossOrigin
public class CurrencyController {

	@Autowired
	CurrencyService currencyService;
	
	@GetMapping("/currencies")
	public List<Currency> getAllCurrencies() {
		
		return currencyService.getAllCurrencies();
	}
}
