package com.klm.flights.core.rest_controllers;

import java.util.HashMap;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;

import com.klm.flights.core.KLM_ClientConsumerService;
import com.klm.flights.core.KLM_InMemoryCache;
import com.klm.flights.data.FlightFareResult;
import com.klm.flights.data.FlightFaresFullResponse;
import com.klm.flights.data.airports.AirportData;
import com.klm.flights.data.airports.PagedSearchAirportData;

/**
 * 
 * REST endpoint for handling flight search requests
 * 
 * @author Sasa Radovanovic
 *
 */
@RestController
public class KLM_FlightsRestCtrl {

	@Autowired
	private KLM_ClientConsumerService clientConsumer;

	Logger logger = LoggerFactory.getLogger(KLM_FlightsRestCtrl.class);

	@SuppressWarnings("unchecked")
	@RequestMapping(value="/rest/api/fares/{startAirport}/{endAirport}/{currency}",
			method = RequestMethod.GET)
	public ResponseEntity<FlightFaresFullResponse> getFares(@PathVariable("startAirport") String startAirport,
			@PathVariable("endAirport") String endAirport, @PathVariable("currency") String currency) {

		long start = System.currentTimeMillis();

		try {

			FlightFaresFullResponse flightFareFullResponse = new FlightFaresFullResponse();

			Future<FlightFareResult> flightFare_F = clientConsumer.retrieveFares(startAirport.toUpperCase(), endAirport.toUpperCase(), currency.toUpperCase());
			Future<AirportData> airportDataOrigin_F = clientConsumer.retrieveAirportData(startAirport.toUpperCase());
			Future<AirportData> airportDataDest_F = clientConsumer.retrieveAirportData(endAirport.toUpperCase());

			while (!flightFare_F.isDone() && !airportDataOrigin_F.isDone() && !airportDataDest_F.isDone()) {
				Thread.sleep(20);
			}

			flightFareFullResponse.setFlightFare(flightFare_F.get());
			HashMap<String, AirportData> airports =  new HashMap<>();
			airports.put(startAirport.toUpperCase(), airportDataOrigin_F.get());
			airports.put(endAirport.toUpperCase(), airportDataDest_F.get());
			flightFareFullResponse.setAirports(airports);
			writeStats(200, start, System.currentTimeMillis());
			return new ResponseEntity<FlightFaresFullResponse>(flightFareFullResponse, HttpStatus.OK);
		} catch (HttpStatusCodeException e) {
			return handleHttpStatusCodeException(start, System.currentTimeMillis(), e);
		} catch (Exception e) {
			writeStats(500, start, System.currentTimeMillis());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@SuppressWarnings("unchecked")
	@RequestMapping(value="/rest/api/partial/{size}/{page}/{lang}/{searchTerm}",
			method = RequestMethod.GET)
	public ResponseEntity<PagedSearchAirportData> getAirports(@PathVariable("size") Integer size,
			@PathVariable("page") Integer page,
			@PathVariable("lang") String lang,
			@PathVariable("searchTerm") String searchTerm) {
		long start = System.currentTimeMillis();

		logger.info(" | Partial search for term " + searchTerm + " called");

		try {

			Future<PagedSearchAirportData> airportDataClbc = clientConsumer.retrieveListOfAirports(searchTerm, lang, size, page);

			while (!airportDataClbc.isDone()) {
				Thread.sleep(20);
			}

			PagedSearchAirportData psad = airportDataClbc.get();
			writeStats(200, start, System.currentTimeMillis());
			return new ResponseEntity<PagedSearchAirportData>(psad, HttpStatus.OK);

		} catch (HttpStatusCodeException e) {
			return handleHttpStatusCodeException(start, System.currentTimeMillis(), e);
		} catch (Exception e) {
			writeStats(500, start, System.currentTimeMillis());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value="/rest/api/{airport}",
			method = RequestMethod.GET)
	public ResponseEntity<AirportData> getAirport(@PathVariable("airport") String airport) {

		logger.info(" getAirport | called " + airport);

		long start = System.currentTimeMillis();

		try {

			Future<AirportData> airportDataClbc = null;
			airportDataClbc = clientConsumer.retrieveAirportData(airport.toUpperCase());

			while (!airportDataClbc.isDone()) {
				Thread.sleep(20);
			}

			AirportData returnData = null;

			returnData = airportDataClbc.get();
			if (returnData == null) {
				writeStats(500, start, System.currentTimeMillis());
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}

			writeStats(200, start, System.currentTimeMillis());
			return new ResponseEntity<AirportData>(returnData, HttpStatus.OK);
		} catch (HttpStatusCodeException e) {
			return handleHttpStatusCodeException(start, System.currentTimeMillis(), e);
		} catch (Exception e) {
			writeStats(500, start, System.currentTimeMillis());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	/**
	 * Write stats to "in-memory" cache
	 * 
	 * @param status - HTTP status returned to frontend (2xx, 4xx, 5xx)
	 * @param start - start time of request handling in milliseconds
	 * @param end - end time of request handling in milliseconds
	 */
	@Async
	private void writeStats (int status, long start, long end) {
		long rspTime = end - start;
		if (KLM_InMemoryCache.REQ_TOTAL_NUM == 0) {
			KLM_InMemoryCache.MIN_RESPONSE_TIME = rspTime;
		}
		
		KLM_InMemoryCache.TOTAL_RESPONSE_TIME = 
				(KLM_InMemoryCache.REQ_TOTAL_NUM * KLM_InMemoryCache.TOTAL_RESPONSE_TIME + rspTime) / (KLM_InMemoryCache.REQ_TOTAL_NUM + 1);
		KLM_InMemoryCache.REQ_TOTAL_NUM ++;
		KLM_InMemoryCache.MAX_RESPONSE_TIME = Math.max(KLM_InMemoryCache.MAX_RESPONSE_TIME, rspTime);
		KLM_InMemoryCache.MIN_RESPONSE_TIME = Math.min(KLM_InMemoryCache.MIN_RESPONSE_TIME, rspTime);
		if (status == 200) {
			KLM_InMemoryCache.OK_REQUESTS ++;
			return;
		} else if (status == 400) {
			KLM_InMemoryCache.ERROR_NUM_4XX ++;
			return;
		} else if (status == 500) {
			KLM_InMemoryCache.ERROR_NUM_5XX ++;
			return;
		}
	}
	
	/**
	 * Utility method for creating ResponseEntity if HttpStatusCodeException occurs
	 * 
	 * @param start - start time of request handling
	 * @param end - end time of request handling
	 * @param e - HttpStatusCodeException
	 * @return ResponseEntity to be returned to the frontend
	 */
	@SuppressWarnings("rawtypes")
	private ResponseEntity handleHttpStatusCodeException (long start, long end, HttpStatusCodeException e) {
		if (e.getStatusCode().is4xxClientError()) {
			writeStats(400, start, System.currentTimeMillis());
			return new ResponseEntity<>(e.getStatusCode());
		} else {
			writeStats(500, start, System.currentTimeMillis());
			return new ResponseEntity<>(e.getStatusCode());
		}
	} 


}
