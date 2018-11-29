package com.nagarro.java.training.FlightSearchApis.controller;

import java.util.List;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.java.training.FlightSearchApis.dto.FlightSearchInputDTO;
import com.nagarro.java.training.FlightSearchApis.dto.FlightSearchOutputDTO;
import com.nagarro.java.training.FlightSearchApis.services.FlightSearchServices;
import static com.nagarro.java.training.FlightSearchApis.constants.Constants.*;

@RestController
@CrossOrigin
public class FlightSearchController {

	@Autowired
	FlightSearchServices flightSearchServices;
	
	@PostMapping("/api/flights")
	public List<FlightSearchOutputDTO> getFilteredFlights(@RequestBody FlightSearchInputDTO flightSearchParams,
															@QueryParam("start") Integer start,
															@QueryParam("size") Integer size,
															@RequestHeader("Currency-Code") String currencyCode) {
		
		if(currencyCode == null || currencyCode.equals("")) {
		
			currencyCode = DEFAULT_CURRENCY_CODE;
		}
		
		try {

			if(start >= 0 && size > 0) {
	
				return flightSearchServices.getFlightsPaginated(flightSearchParams, currencyCode, 
																	start, size); 

			} else {
				
				return flightSearchServices.getFilteredFlights(flightSearchParams, currencyCode);
			}
		
		} catch (NullPointerException e) {
	
			return flightSearchServices.getFilteredFlights(flightSearchParams, currencyCode);
		}
	}
	
}
