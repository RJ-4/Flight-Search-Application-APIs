package com.nagarro.java.training.FlightSearchApis.dao.impl;

import static com.nagarro.java.training.FlightSearchApis.constants.Constants.*;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nagarro.java.training.FlightSearchApis.dao.FlightSearchDAO;
import com.nagarro.java.training.FlightSearchApis.dto.FlightSearchInputDTO;
import com.nagarro.java.training.FlightSearchApis.models.Flight;

@Repository
public class FlightSearchDAOImpl implements FlightSearchDAO {

	@Autowired
	SessionFactory factory;
	
	@Override
	public List<Flight> getFilteredFlights(FlightSearchInputDTO flightSearchInputDTO) throws Exception {
		
		Session session = factory.getCurrentSession();
		
		String getFlightsQuery = GET_FLIGHTS_QUERY;
	
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery(getFlightsQuery);
		
		query.setParameter(DEAPARTURE_LOCATION_QUERY_LABEL, flightSearchInputDTO.getDepartureLocation());
		
		query.setParameter(ARRIVAL_LOCATION_QUERY_LABEL, flightSearchInputDTO.getArrivalLocation());
		
		query.setParameter(VALID_TILL_QUERY_LABEL, flightSearchInputDTO.getDepartureDate());
		
		query.setParameter(FLIGHT_CLASS_QUERY_LABEL, "%" + flightSearchInputDTO.getFlightClass().charAt(0) + "%");
		
		@SuppressWarnings("unchecked")
		List<Flight> filteredFlights = (List<Flight>)query.getResultList();
		
		return filteredFlights;
	
	}

	@Override
	public List<Flight> getFirstHalfOfConnectingFlights(FlightSearchInputDTO flightSearchInputDTO) throws Exception {

		Session session = factory.getCurrentSession();
		
		String getConnectedFlightsQuery = GET_CONNECTING_FLIGHTS_1_QUERY;
		
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery(getConnectedFlightsQuery);
		
		query.setParameter(DEPARTURE_LOCATION_PARAM, flightSearchInputDTO.getDepartureLocation());
		
		query.setParameter(ARRIVAL_LOCATION_PARAM, flightSearchInputDTO.getArrivalLocation());
		
		query.setParameter(DEPARTURE_DATE_PARAM, flightSearchInputDTO.getDepartureDate());
		
		query.setParameter(FLIGHT_CLASS_PARAM, "%" + flightSearchInputDTO.getFlightClass() + "%");
		
		@SuppressWarnings("unchecked")
		List<Flight> connectingFlights1 = query.getResultList();
		
		return connectingFlights1;
	}
	

	@Override
	public List<Flight> getSecondHalfOfConnectingFlights(FlightSearchInputDTO flightSearchInputDTO) throws Exception {

		Session session = factory.getCurrentSession();
		
		String getConnectedFlightsQuery = GET_CONNECTING_FLIGHTS_2_QUERY;
		
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery(getConnectedFlightsQuery);
		
		query.setParameter(DEPARTURE_LOCATION_PARAM, flightSearchInputDTO.getDepartureLocation());
		
		query.setParameter(ARRIVAL_LOCATION_PARAM, flightSearchInputDTO.getArrivalLocation());
		
		query.setParameter(DEPARTURE_DATE_PARAM, flightSearchInputDTO.getDepartureDate());
		
		query.setParameter(FLIGHT_CLASS_PARAM, "%" + flightSearchInputDTO.getFlightClass() + "%");
		
		@SuppressWarnings("unchecked")
		List<Flight> connectingFlights2 = query.getResultList();
		
		return connectingFlights2;
	}

}
