package com.nagarro.java.training.FlightSearchApis.sort.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.nagarro.java.training.FlightSearchApis.dto.FlightSearchOutputDTO;
import com.nagarro.java.training.FlightSearchApis.sort.SortResults;

@Component
public class SortByFare implements SortResults {

	@Override
	public List<FlightSearchOutputDTO> sortFlightList(List<FlightSearchOutputDTO> filteredFlights) {

		Collections.sort(filteredFlights, byFareComparator);
		
		return filteredFlights;
		
	}

	private Comparator<FlightSearchOutputDTO> byFareComparator = new Comparator<FlightSearchOutputDTO>(){
		
		public int compare(FlightSearchOutputDTO flight1, FlightSearchOutputDTO flight2){
			
			Double fare1 = flight1.getFare();
			
			Double fare2 = flight2.getFare();
			
			return fare1.compareTo(fare2);
		}
	};
}
