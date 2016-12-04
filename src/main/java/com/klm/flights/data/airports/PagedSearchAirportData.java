package com.klm.flights.data.airports;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * Paged data search entity
 * 
 * @author Sasa Radovanovic
 *
 */
public class PagedSearchAirportData {
	
	@JsonProperty("_embedded")
	private SearchedAirports airports;
	
	@JsonProperty("page")
	private Page page;

	public PagedSearchAirportData() {
		super();
	}


	public PagedSearchAirportData(SearchedAirports airports, Page page) {
		super();
		this.airports = airports;
		this.page = page;
	}
	
	

	/**
	 * @return the airports
	 */
	public SearchedAirports getAirports() {
		return airports;
	}


	/**
	 * @param airports the airports to set
	 */
	public void setAirports(SearchedAirports airports) {
		this.airports = airports;
	}


	/**
	 * @return the page
	 */
	public Page getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(Page page) {
		this.page = page;
	}

}
