package com.nagarro.java.training.FlightSearchApis.services;

import java.util.List;

import com.nagarro.java.training.FlightSearchApis.dto.FlightSearchInputDTO;
import com.nagarro.java.training.FlightSearchApis.dto.FlightSearchOutputDTO;

public interface FlightSearchServices {
	
	public List<FlightSearchOutputDTO> getFilteredFlights(FlightSearchInputDTO flightSearchParams, String currencyCode);
	
	List<FlightSearchOutputDTO> getFlightsPaginated(FlightSearchInputDTO flightSearchParams, 
														String currencyCode, int start, int size);
	
}
