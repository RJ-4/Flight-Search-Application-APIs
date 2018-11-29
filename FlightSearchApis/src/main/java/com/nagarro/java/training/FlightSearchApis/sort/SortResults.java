package com.nagarro.java.training.FlightSearchApis.sort;

import java.util.List;

import com.nagarro.java.training.FlightSearchApis.dto.FlightSearchOutputDTO;

public interface SortResults {

	public List<FlightSearchOutputDTO> sortFlightList(List<FlightSearchOutputDTO> filteredFlights);

}
