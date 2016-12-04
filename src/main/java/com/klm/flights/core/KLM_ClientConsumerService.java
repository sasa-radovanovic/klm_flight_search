package com.klm.flights.core;

import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

import com.klm.flights.data.FlightFareResult;
import com.klm.flights.data.airports.AirportData;
import com.klm.flights.data.airports.PagedSearchAirportData;

/**
 * 
 * Communication engine with simple-travel-api-mock
 * NOTE that OAuth2 settings are done in @KLM_OAuth2Config
 * 
 * URL endpoints are pull from .properties file
 * 
 * @author Sasa Radovanovic
 *
 */
@Service
public class KLM_ClientConsumerService {
	
	
	@Value("${services.airportDetails}")
	private String urlAirportDetails;
	
	@Value("${services.fares}")
	private String urlFares;
	
	@Value("${services.partialSearch}")
	private String urlPartialSearch;

	
	@Autowired
	private OAuth2RestOperations restOps;
	
	Logger logger = LoggerFactory.getLogger(KLM_ClientConsumerService.class);
	
	/**
	 * 
	 * Async task for retrieving fares for certain origin-destination pair
	 * 
	 * @param origin - Origin airport (i.e. AMS)
	 * @param destination - Destination airport (i.e. BEG)
	 * @param currency - currency (EUR, USD)
	 * @return Future holding @FlightFareResult object
	 * @throws HttpStatusCodeException
	 */
	@Async
	public Future<FlightFareResult> retrieveFares (String origin, String destination, String currency) throws HttpStatusCodeException {
		
		logger.info(" | Retrieving fares " + origin + " " + destination);
		
		FlightFareResult flightFare = restOps.getForObject(urlFares, FlightFareResult.class, origin, destination, currency);
		
		logger.info(" | Fares retrieved " + origin + " " + destination);
		
		return new AsyncResult<FlightFareResult>(flightFare);
	}
	
	/**
	 * 
	 * Async task used to retrieve list of airports according to partial search term
	 * 
	 * @param term - term for partial searching
	 * @param lang - language (en or nl)
	 * @param size - Size of list
	 * @param page - Given page
	 * @return Future holding @PagedSearchAirportData
	 * @throws HttpStatusCodeException
	 */
	@Async
	public Future<PagedSearchAirportData> retrieveListOfAirports (String term, String lang, Integer size, Integer page) 
			throws HttpStatusCodeException {
		
		logger.info(" | Retrieving search data for term " + term);
		
		PagedSearchAirportData retList = restOps.getForObject(urlPartialSearch, PagedSearchAirportData.class, size, page, lang, term);
		
		logger.info(" | Search data retrieved " + term);
		
		return new AsyncResult<PagedSearchAirportData>(retList);
	}
	
	/**
	 * 
	 * Async task used to retrieve airport data
	 * 
	 * @param airportCode (i.e. AMS)
	 * @return Future holding @AirportData object
	 * @throws HttpStatusCodeException
	 */
	@Async
	public Future<AirportData> retrieveAirportData (String airportCode) throws HttpStatusCodeException {
		
		AirportData aData;
		
		logger.info(" | Retrieving airport data [ " + airportCode + " ]  from client...");
		
		// Check if given airport data is stored in "cache". If so, return it from there, otherwise
		// retrieve data from target endpoint and store it in cache.
		if (KLM_InMemoryCache.airportDataCache.containsKey(airportCode)) {
			logger.info(" | Found airport data [ " + airportCode + " ] in cache...");
			aData = KLM_InMemoryCache.airportDataCache.get(airportCode);
		} else {
			logger.info(" | Sending request for [ " + airportCode + " ]");
			aData = restOps.getForObject(urlAirportDetails, AirportData.class, airportCode);
			logger.info(" | Received response for [ " + airportCode + " ]");
			KLM_InMemoryCache.airportDataCache.put(airportCode, aData);
		} 
		
		return new AsyncResult<>(aData);
	}
	
	
}
