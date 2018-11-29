package com.nagarro.java.training.FlightSearchApis.dao;

import java.util.List;

import com.nagarro.java.training.FlightSearchApis.models.Currency;

public interface CurrencyDAO {

	List<Currency> getAllCurrencies();

	Currency getSelectedCurrency(String currencyCode);
}
