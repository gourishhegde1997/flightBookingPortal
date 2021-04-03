package com.infyGo.flightBooking.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.infyGo.flightBooking.model.FlightDTO;

@Repository("inMemoryFlightRepo")
@Scope("singleton")
public class FlightRepositoryImpl implements FlightRepository {

	List<FlightDTO> flightsList = null;

	@PostConstruct
	public void initializr() {
		FlightDTO flight = new FlightDTO();
		flight.setSource("Hubli");
		flight.setDestination("Sirsi");
		flight.setAirlines("SpiceJet");
		flight.setFare(800.00);
		flight.setSeatCount(120);
		flight.setJourneyDate(LocalDate.of(2021, 12, 12));
		flightsList = new ArrayList<>();
		flightsList.add(flight);
	}

	@Override
	public String addFlight(FlightDTO flight) {
		if (!flightsList.contains(flight)) {
			flightsList.add(flight);
			return "Flight added successfully";
		} else {
			return "Flight already exist";
		}
	}

	@Override
	public FlightDTO searchFlight(String source, String destination, LocalDate date) {

		FlightDTO searchedFlight = flightsList
				.stream().filter(c -> c.getSource().equalsIgnoreCase(source)
						&& c.getDestination().equalsIgnoreCase(destination) && c.getJourneyDate().equals(date))
				.findFirst().orElse(null);
		if (searchedFlight != null) {
			if (searchedFlight.getJourneyDate().getMonth().getValue() == 12
					|| searchedFlight.getJourneyDate().getMonth().getValue() == 01) {
				searchedFlight.setFare(searchedFlight.getFare() * 1.20);
			}
		}
		return searchedFlight;
	}

}
