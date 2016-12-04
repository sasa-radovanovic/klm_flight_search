package com.klm.flights.data.airports;

/**
 * 
 * Page data entity
 * 
 * @author Sasa Radovanovic
 *
 */
public class Page {
	
	private Integer size;
	
	private Integer totalElements;
	
	private Integer totalPages;
	
	private Integer number;

	public Page() {
		super();
	}

	public Page(Integer size, Integer totalElements, Integer totalPages, Integer number) {
		super();
		this.size = size;
		this.totalElements = totalElements;
		this.totalPages = totalPages;
		this.number = number;
	}

	/**
	 * @return the size
	 */
	public Integer getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(Integer size) {
		this.size = size;
	}

	/**
	 * @return the totalElements
	 */
	public Integer getTotalElements() {
		return totalElements;
	}

	/**
	 * @param totalElements the totalElements to set
	 */
	public void setTotalElements(Integer totalElements) {
		this.totalElements = totalElements;
	}

	/**
	 * @return the totalPages
	 */
	public Integer getTotalPages() {
		return totalPages;
	}

	/**
	 * @param totalPages the totalPages to set
	 */
	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	/**
	 * @return the number
	 */
	public Integer getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}

}
