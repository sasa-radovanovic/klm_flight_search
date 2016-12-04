package com.klm.flights.core;

import java.util.HashMap;
import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;


/**
 * 
 * Main application entry point
 * 
 * @author Sasa Radovanovic
 *
 */
@SpringBootApplication
@EnableAsync
public class KLM_FlightsApplication extends AsyncConfigurerSupport implements CommandLineRunner {

	Logger logger = LoggerFactory.getLogger(KLM_FlightsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(KLM_FlightsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info(" | Initializing Airport Data in-memory cache...");
		KLM_InMemoryCache.airportDataCache = new HashMap<>();
	}
	
	
	/**
	 * Set properties for asynchronous executors
	 */
	@Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("async-task-");
        executor.initialize();
        return executor;
    }
	
}
