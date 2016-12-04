package com.klm.flights.core;

import java.util.HashMap;

import com.klm.flights.data.airports.AirportData;

/**
 * 
 * The "In-memory cache" storing variables for statistics and cache for airport data
 * 
 * @author Sasa Radovanovic
 *
 */
public class KLM_InMemoryCache {
	
	/**
	 * In reality - this is not the way it would be implemented, but there would certainly be
	 * some kind of write through structure for data which takes stochastic amount of time (and are slowly changing) to retrieve.
	 * From the top of my head - Cache solutions like Infinispan, Ehcache, 
	 * redis or Hazelcast could possibly be used here. I did not have time to include them this time.
	 * Why do this? Airport data is rarely changed (i.e. airport code is almost never changed) so why 
	 * pull data from remote source every time?
	 */
	public static HashMap<String, AirportData> airportDataCache;
	
	public static Integer ERROR_NUM_4XX = 0;
	public static Integer ERROR_NUM_5XX = 0;
	public static Integer OK_REQUESTS = 0;
	public static Integer REQ_TOTAL_NUM = 0;
	public static double TOTAL_RESPONSE_TIME = 0;
	public static double MIN_RESPONSE_TIME =  0;
	public static double MAX_RESPONSE_TIME = 0;

}
