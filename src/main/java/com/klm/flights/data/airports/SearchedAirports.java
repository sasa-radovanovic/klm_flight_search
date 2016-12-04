package com.klm.flights.data.airports;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * Searched airports array entity
 * 
 * @author Sasa Radovanovic
 *
 */
public class SearchedAirports {
	
	@JsonProperty("locations")
	private ArrayList<AirportData> airports;

	public SearchedAirports() {
		super();
	}

	public SearchedAirports(ArrayList<AirportData> airports) {
		super();
		this.airports = airports;
	}

	/**
	 * @return the airports
	 */
	public ArrayList<AirportData> getAirports() {
		return airports;
	}

	/**
	 * @param airports the airports to set
	 */
	public void setAirports(ArrayList<AirportData> airports) {
		this.airports = airports;
	}

}
