package com.klm.flights.data.statistics;

/**
 * 
 * System Statistics entity
 * 
 * @author Sasa Radovanovic
 *
 */
public class SystemStatistics {
	
	private Integer totalRequests;
	
	private Integer total_OK;
	
	private Integer total_4xx;
	
	private Integer total_5xx;
	
	private double avgRspTime;
	
	private double minRspTime;
	
	private double maxRspTime;

	public SystemStatistics() {
		super();
	}

	public SystemStatistics(Integer totalRequests, Integer total_OK, Integer total_4xx, Integer total_5xx,
			double avgRspTime, double minRspTime, double maxRspTime) {
		super();
		this.totalRequests = totalRequests;
		this.total_OK = total_OK;
		this.total_4xx = total_4xx;
		this.total_5xx = total_5xx;
		this.avgRspTime = avgRspTime;
		this.minRspTime = minRspTime;
		this.maxRspTime = maxRspTime;
	}

	/**
	 * @return the totalRequests
	 */
	public Integer getTotalRequests() {
		return totalRequests;
	}

	/**
	 * @param totalRequests the totalRequests to set
	 */
	public void setTotalRequests(Integer totalRequests) {
		this.totalRequests = totalRequests;
	}

	/**
	 * @return the total_OK
	 */
	public Integer getTotal_OK() {
		return total_OK;
	}

	/**
	 * @param total_OK the total_OK to set
	 */
	public void setTotal_OK(Integer total_OK) {
		this.total_OK = total_OK;
	}

	/**
	 * @return the total_4xx
	 */
	public Integer getTotal_4xx() {
		return total_4xx;
	}

	/**
	 * @param total_4xx the total_4xx to set
	 */
	public void setTotal_4xx(Integer total_4xx) {
		this.total_4xx = total_4xx;
	}

	/**
	 * @return the total_5xx
	 */
	public Integer getTotal_5xx() {
		return total_5xx;
	}

	/**
	 * @param total_5xx the total_5xx to set
	 */
	public void setTotal_5xx(Integer total_5xx) {
		this.total_5xx = total_5xx;
	}

	/**
	 * @return the avgRspTime
	 */
	public double getAvgRspTime() {
		return avgRspTime;
	}

	/**
	 * @param avgRspTime the avgRspTime to set
	 */
	public void setAvgRspTime(double avgRspTime) {
		this.avgRspTime = avgRspTime;
	}

	/**
	 * @return the minRspTime
	 */
	public double getMinRspTime() {
		return minRspTime;
	}

	/**
	 * @param minRspTime the minRspTime to set
	 */
	public void setMinRspTime(double minRspTime) {
		this.minRspTime = minRspTime;
	}

	/**
	 * @return the maxRspTime
	 */
	public double getMaxRspTime() {
		return maxRspTime;
	}

	/**
	 * @param maxRspTime the maxRspTime to set
	 */
	public void setMaxRspTime(double maxRspTime) {
		this.maxRspTime = maxRspTime;
	}

}
