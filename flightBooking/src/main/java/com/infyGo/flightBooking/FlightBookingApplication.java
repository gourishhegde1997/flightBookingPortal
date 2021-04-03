package com.infyGo.flightBooking;

import java.time.LocalDate;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.AbstractApplicationContext;

import com.infyGo.flightBooking.model.FlightDTO;
import com.infyGo.flightBooking.service.FlightService;

@SpringBootApplication
public class FlightBookingApplication {

	public static void main(String[] args) {
		AbstractApplicationContext context = (AbstractApplicationContext) SpringApplication
				.run(FlightBookingApplication.class, args);
		FlightService flightService = (FlightService) context.getBean("flightService");
		boolean exit = false;

		System.out.println("=======================================================");
		System.out.println("*                Flight Service Menu                  *");
		System.out.println("=======================================================");
		Scanner scanner = new Scanner(System.in);
		while (!exit) {
			System.out.println("A - Add Flight \nB - Search Flight\n");
			System.out.println("Select any option");
			System.out.println();
			String choice = scanner.nextLine().trim();
			choice = choice.toUpperCase();
			switch (choice) {
			case "A":
				System.out.println("Please enter below details,");
				System.out.println("From : ");
				String source = scanner.nextLine().trim();
				System.out.println("To : ");
				String destination = scanner.nextLine().trim();
				System.out.println("Airlines : ");
				String airlines = scanner.nextLine().trim();
				System.out.println("Journey Date (In YYYY-MM-DD Format) : ");
				String dateString = scanner.nextLine().trim();
				LocalDate journeyDate = LocalDate.parse(dateString);
				System.out.println("Fare : ");
				Double fare = scanner.nextDouble();
				System.out.println("Number Of Seats Available :");
				Integer seatCount = scanner.nextInt();
				FlightDTO flight = new FlightDTO();
				flight.setSource(source);
				flight.setDestination(destination);
				flight.setAirlines(airlines);
				flight.setFare(fare);
				flight.setJourneyDate(journeyDate);
				flight.setSeatCount(seatCount);
				String message = flightService.addFlight(flight);
				System.out.println(message);
				System.out.println();
				break;

			case "B":
				System.out.println("Please enter below details");
				System.out.println("From : ");
				String src = scanner.nextLine().trim();
				System.out.println("To : ");
				String dest = scanner.nextLine().trim();
				System.out.println("Journey Date (In YYYY-MM-DD Format) : ");
				String dateStr = scanner.nextLine().trim();
				LocalDate jrnyDate = LocalDate.parse(dateStr);
				FlightDTO searchedFlight = flightService.searchFlight(src, dest, jrnyDate);
				if (searchedFlight != null) {
					System.out.println("Searched flight details is as below,");
					System.out.println(searchedFlight.toString());
					System.out.println();
				} else {
					System.out.println("Sorry. No such flight available..!");
					System.out.println();
				}
				break;

			default:
				System.out.println("Invalid choice..");
				break;
			}

			scanner.reset();
			System.out.println("Would you like to continue?(Y/N)");
			String continueChar = scanner.nextLine();
			if (continueChar.equalsIgnoreCase("N")) {
				exit = true;
				System.out.println("Thank you. Have a nice day..");
			}
				
			System.out.println();
		}
		scanner.close();
	}

}
