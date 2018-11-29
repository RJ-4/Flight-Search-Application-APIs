package com.nagarro.java.training.FlightSearchApis.dao;

import java.util.List;

import com.nagarro.java.training.FlightSearchApis.dto.FlightSearchInputDTO;
import com.nagarro.java.training.FlightSearchApis.models.Flight;

public interface FlightSearchDAO {

	List<Flight> getFilteredFlights(FlightSearchInputDTO flightSearchInputDTO) throws Exception;
	
	List<Flight> getFirstHalfOfConnectingFlights(FlightSearchInputDTO flightSearchInputDTO) throws Exception;
	
	List<Flight> getSecondHalfOfConnectingFlights(FlightSearchInputDTO flightSearchInputDTO) throws Exception;
}
