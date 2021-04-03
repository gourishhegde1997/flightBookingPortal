package com.infyGo.flightBooking.service;

import java.time.LocalDate;

import com.infyGo.flightBooking.model.FlightDTO;

public interface FlightService {

	public String addFlight(FlightDTO flight);

	public FlightDTO searchFlight(String source, String destination, LocalDate date);

}
