package com.nagarro.java.training.FlightSearchApis.services;

import java.util.List;

import com.nagarro.java.training.FlightSearchApis.models.Currency;

public interface CurrencyService {

	List<Currency> getAllCurrencies();
	
	Currency getSelectedCurrency(String currencyCode);
}
