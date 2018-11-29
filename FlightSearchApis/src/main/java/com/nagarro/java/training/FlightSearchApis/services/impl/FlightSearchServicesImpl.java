package com.nagarro.java.training.FlightSearchApis.services.impl;

import static com.nagarro.java.training.FlightSearchApis.constants.Constants.*;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.java.training.FlightSearchApis.dao.FlightSearchDAO;
import com.nagarro.java.training.FlightSearchApis.dto.FlightSearchInputDTO;
import com.nagarro.java.training.FlightSearchApis.dto.FlightSearchOutputDTO;
import com.nagarro.java.training.FlightSearchApis.models.Flight;
import com.nagarro.java.training.FlightSearchApis.modifyFare.ModifyFare;
import com.nagarro.java.training.FlightSearchApis.services.CurrencyService;
import com.nagarro.java.training.FlightSearchApis.services.FlightSearchServices;
import com.nagarro.java.training.FlightSearchApis.sort.SortResults;
import com.nagarro.java.training.FlightSearchApis.sort.impl.SortByFare;
import com.nagarro.java.training.FlightSearchApis.sort.impl.SortByFareAndDuration;

@Service
public class FlightSearchServicesImpl implements FlightSearchServices {

	@Autowired
	FlightSearchInputDTO flightSearchInputDTO;
	
	FlightSearchOutputDTO flightSearchOutputDTO;
	
	@Autowired
	FlightSearchDAO flightSearchDAO;
	
	@Autowired
	ModifyFare modifyFare;
	
	@Autowired
	CurrencyService currencyService;
	
	String outputPreference;
	
	SortResults sortResults;
	
	@Transactional
	@Override
	public List<FlightSearchOutputDTO> getFilteredFlights(FlightSearchInputDTO flightSearchParams, 
															String currencyCode) {
	
		List<Flight> filteredFlights = new ArrayList<Flight>();
		
		List<Flight> connectingFlights1 = new ArrayList<Flight>();
		
		List<Flight> connectingFlights2 = new ArrayList<Flight>();
		
		boolean areDirectFlightsFound = true;
		
		try {
			
			filteredFlights = flightSearchDAO.getFilteredFlights(flightSearchParams);
			
			if(filteredFlights.isEmpty()) {
				
				connectingFlights1 = flightSearchDAO.getFirstHalfOfConnectingFlights(flightSearchParams);
				
				connectingFlights2 = flightSearchDAO.getSecondHalfOfConnectingFlights(flightSearchParams);
				
				for(int i = 0; i < connectingFlights1.size(); i++) {
					
					filteredFlights.add(connectingFlights1.get(i));
					
					filteredFlights.add(connectingFlights2.get(i));
				}
				
				areDirectFlightsFound = false;
				
			}
		
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		List<FlightSearchOutputDTO> filteredFlightsDTO = new ArrayList<FlightSearchOutputDTO>();
		
		for(Flight filteredFlight: filteredFlights) {
		
			filteredFlightsDTO.add(initializeOutputDTO(filteredFlight));
			
		}
		
		filteredFlightsDTO = modifyFare(filteredFlightsDTO, flightSearchParams.getFlightClass());
		
		filteredFlightsDTO = changeCurrencyRate(filteredFlightsDTO, currencyCode);
		
		if(areDirectFlightsFound == false) {
			
			return filteredFlightsDTO;
		}
		
		filteredFlightsDTO = sortFlights(filteredFlightsDTO, flightSearchParams);
		
		return filteredFlightsDTO;
	}
	
	private List<FlightSearchOutputDTO> changeCurrencyRate(List<FlightSearchOutputDTO> filteredFlightsDTO,
																String currencyCode) {
		
		double selectedCurrencyRate = currencyService.getSelectedCurrency(currencyCode).getCurrencyRate();
		
		for(FlightSearchOutputDTO filteredFlight: filteredFlightsDTO) {
			
			filteredFlight.setFare(filteredFlight.getFare() * selectedCurrencyRate);
		}
		
		return filteredFlightsDTO;
	}
	
	
	private List<FlightSearchOutputDTO> sortFlights(List<FlightSearchOutputDTO> filteredFlightsDTO, 
														FlightSearchInputDTO flightSearchParams) {
		
		if(flightSearchParams.getOutputPreference().equals(SORT_BY_FARE)) {
			
			sortResults = new SortByFare();
			
			filteredFlightsDTO = sortResults.sortFlightList(filteredFlightsDTO);
		
		} else if(flightSearchParams.getOutputPreference().equals(SORT_BY_FARE_AND_DURATION)) {
			
			sortResults = new SortByFareAndDuration();
			
			filteredFlightsDTO = sortResults.sortFlightList(filteredFlightsDTO);
		
		}
		
		return filteredFlightsDTO;
	}

	private List<FlightSearchOutputDTO> modifyFare(List<FlightSearchOutputDTO> filteredFlights, 
														String flightClass) {
		
		for(FlightSearchOutputDTO filteredFlight: filteredFlights) {
			
			if(flightClass.equals("B")) {
				
				filteredFlight.setFare(modifyFare.modifyFare(filteredFlight.getFare()));
			}
			
		}
		
		return filteredFlights;
	}
	
	private FlightSearchOutputDTO initializeOutputDTO(Flight filteredFlight) {
		
		flightSearchOutputDTO = new FlightSearchOutputDTO();
		
		flightSearchOutputDTO.setDepartureLocation(filteredFlight.getDepartureLocation());
		
		flightSearchOutputDTO.setArrivalLocation(filteredFlight.getArrivalLocation());
		
		flightSearchOutputDTO.setValidTill(filteredFlight.getValidTill());
		
		flightSearchOutputDTO.setFlightNumber(filteredFlight.getFlightNumber());
		
		flightSearchOutputDTO.setFlightTime(filteredFlight.getFlightTime());
		
		flightSearchOutputDTO.setFlightDuration(filteredFlight.getFlightDuration());
		
		flightSearchOutputDTO.setFare(filteredFlight.getFare());
		
		flightSearchOutputDTO.setSeatAvailability(filteredFlight.getSeatAvailability());
		
		flightSearchOutputDTO.setFlightClass(filteredFlight.getFlightClass());
		
		return flightSearchOutputDTO;
		
	}


	
	@Override
	@Transactional
	public List<FlightSearchOutputDTO> getFlightsPaginated(FlightSearchInputDTO flightSearchParams, 
																String currencyCode, int start, int size) {

		List<FlightSearchOutputDTO> filteredFlights = getFilteredFlights(flightSearchParams, currencyCode);
		
		if((start + size) > filteredFlights.size()) {
			
			return new ArrayList<FlightSearchOutputDTO>();
		
		}
		
		return filteredFlights.subList(start, start + size);
	}
	
}
