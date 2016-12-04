package com.klm.flights.data;

import java.util.HashMap;

import com.klm.flights.data.airports.AirportData;

public class FlightFaresFullResponse {
	
	private HashMap<String, AirportData> airports;
	
	private FlightFareResult flightFare;

	public FlightFaresFullResponse() {
		super();
	}

	public FlightFaresFullResponse(HashMap<String, AirportData> airports, FlightFareResult flightFare) {
		super();
		this.airports = airports;
		this.flightFare = flightFare;
	}

	/**
	 * @return the airports
	 */
	public HashMap<String, AirportData> getAirports() {
		return airports;
	}

	/**
	 * @param airports the airports to set
	 */
	public void setAirports(HashMap<String, AirportData> airports) {
		this.airports = airports;
	}

	/**
	 * @return the flightFare
	 */
	public FlightFareResult getFlightFare() {
		return flightFare;
	}

	/**
	 * @param flightFare the flightFare to set
	 */
	public void setFlightFare(FlightFareResult flightFare) {
		this.flightFare = flightFare;
	}
	

}
