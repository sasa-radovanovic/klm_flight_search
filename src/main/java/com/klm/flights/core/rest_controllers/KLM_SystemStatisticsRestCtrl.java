package com.klm.flights.core.rest_controllers;

import java.util.concurrent.ExecutionException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.klm.flights.core.KLM_InMemoryCache;
import com.klm.flights.data.statistics.SystemStatistics;

/**
 * 
 * REST endpoint for handling System Statistics
 * 
 * @author Sasa Radovanovic
 *
 */
@RestController
public class KLM_SystemStatisticsRestCtrl {

	
	@RequestMapping(value="/rest/system_stats",
			method = RequestMethod.GET)
	public ResponseEntity<SystemStatistics> getStats() 
								throws InterruptedException, ExecutionException {
		
		SystemStatistics stats = new SystemStatistics(KLM_InMemoryCache.REQ_TOTAL_NUM, 
				KLM_InMemoryCache.OK_REQUESTS, 
				KLM_InMemoryCache.ERROR_NUM_4XX, 
				KLM_InMemoryCache.ERROR_NUM_5XX, 
				KLM_InMemoryCache.TOTAL_RESPONSE_TIME, 
				KLM_InMemoryCache.MIN_RESPONSE_TIME, 
				KLM_InMemoryCache.MAX_RESPONSE_TIME);
		return new ResponseEntity<SystemStatistics>(stats, HttpStatus.OK);
	}
}
