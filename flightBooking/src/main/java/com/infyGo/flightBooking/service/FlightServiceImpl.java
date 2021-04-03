package com.infyGo.flightBooking.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.infyGo.flightBooking.model.FlightDTO;
import com.infyGo.flightBooking.repository.FlightRepository;

@Service("flightService")
@Scope("singleton")
public class FlightServiceImpl implements FlightService {
	
	private FlightRepository flightRepo;
	
	@Autowired
	public FlightServiceImpl(FlightRepository flightRepo) {
		super();
		this.flightRepo = flightRepo;
	}

	@Override
	public String addFlight(FlightDTO flight) {
		return flightRepo.addFlight(flight);
	}

	@Override
	public FlightDTO searchFlight(String source, String destination, LocalDate date) {
		return flightRepo.searchFlight(source, destination, date);
	}

}
