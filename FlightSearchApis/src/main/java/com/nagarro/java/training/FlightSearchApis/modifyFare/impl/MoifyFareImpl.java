package com.nagarro.java.training.FlightSearchApis.modifyFare.impl;

import org.springframework.stereotype.Component;

import com.nagarro.java.training.FlightSearchApis.modifyFare.ModifyFare;

@Component
public class MoifyFareImpl implements ModifyFare {

	@Override
	public double modifyFare(double currentFare) {

		double newFare = currentFare + ((currentFare * 40) / 100);
		
		return newFare;
	}

}
