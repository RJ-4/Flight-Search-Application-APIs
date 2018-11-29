package com.nagarro.java.training.FlightSearchApis.dao.impl;

import static com.nagarro.java.training.FlightSearchApis.constants.Constants.*;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nagarro.java.training.FlightSearchApis.dao.CurrencyDAO;
import com.nagarro.java.training.FlightSearchApis.models.Currency;

@Repository
public class CurrencyDAOImpl implements CurrencyDAO {

	@Autowired
	SessionFactory factory;
	
	@Override
	public List<Currency> getAllCurrencies() {

		Session session = factory.getCurrentSession();
		
		String getAllCurrenciesQuery = GET_ALL_CURRENCIES_QUERY;
		
		@SuppressWarnings("unchecked")
		Query<Currency> query = session.createQuery(getAllCurrenciesQuery);
		
		List<Currency> currencies = query.getResultList();
		
		return currencies;
	}

	@Override
	public Currency getSelectedCurrency(String currencyCode) {

		Session session = factory.getCurrentSession();
		
		String getSelectedCurrencyQuery = GET_SELECTED_CURRENCY_QUERY;
		
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery(getSelectedCurrencyQuery);
		
		query.setParameter(CURRENCY_CODE_PARAM, currencyCode);
		
		Currency selectedCurrency = (Currency) query.getSingleResult();
		
		return selectedCurrency;
	}

}
