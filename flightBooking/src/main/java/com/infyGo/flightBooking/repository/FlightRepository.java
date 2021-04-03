package com.infyGo.flightBooking.repository;

import java.time.LocalDate;

import com.infyGo.flightBooking.model.FlightDTO;

public interface FlightRepository {

	public String addFlight(FlightDTO flight);
	
	public FlightDTO searchFlight(String source, String destination, LocalDate date);
	
}
