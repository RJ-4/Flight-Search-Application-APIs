package com.nagarro.java.training.FlightSearchApis.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "currency")
public class Currency {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "currency_code")
	private String currencyCode;
	
	@Column(name = "currency_rate")
	private double currencyRate;
	
	public int getId() {
		
		return id;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public double getCurrencyRate() {
		return currencyRate;
	}

	public void setCurrencyRate(double currencyRate) {
		this.currencyRate = currencyRate;
	}

	public void setId(int id) {
		this.id = id;
	}

}
