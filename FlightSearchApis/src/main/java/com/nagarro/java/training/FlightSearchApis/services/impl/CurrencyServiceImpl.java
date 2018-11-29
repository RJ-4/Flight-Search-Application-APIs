package com.nagarro.java.training.FlightSearchApis.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.java.training.FlightSearchApis.dao.CurrencyDAO;
import com.nagarro.java.training.FlightSearchApis.models.Currency;
import com.nagarro.java.training.FlightSearchApis.services.CurrencyService;

@Service
public class CurrencyServiceImpl implements CurrencyService {

	@Autowired
	CurrencyDAO currencyDAO;
	
	@Override
	@Transactional
	public List<Currency> getAllCurrencies() {
	
		return currencyDAO.getAllCurrencies();
	}

	@Override
	@Transactional
	public Currency getSelectedCurrency(String currencyCode) {

		return currencyDAO.getSelectedCurrency(currencyCode);
	}

}
