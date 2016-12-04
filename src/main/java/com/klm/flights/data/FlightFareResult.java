package com.klm.flights.data;

public class FlightFareResult {
	
	private double amount;
	
	private String currency;
	
	private String origin;
	
	private String destination;

	public FlightFareResult() {
		super();
	}

	public FlightFareResult(double amount, String currency, String origin, String destination) {
		super();
		this.amount = amount;
		this.currency = currency;
		this.origin = origin;
		this.destination = destination;
	}

	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * @return the origin
	 */
	public String getOrigin() {
		return origin;
	}

	/**
	 * @param origin the origin to set
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}

	/**
	 * @return the destination
	 */
	public String getDestination() {
		return destination;
	}

	/**
	 * @param destination the destination to set
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}

}
