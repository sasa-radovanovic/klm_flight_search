package com.klm.flights.data.airports;

import java.util.Set;

/**
 * 
 * Airport data entity
 * 
 * @author Sasa Radovanovic
 *
 */
public class AirportData {
	
	private String code, name, description;
	
	private AirportData parent;
	
	private Set<AirportData> children;
	
	private Coordinates coordinates;
	

	public AirportData () {
	}

	public AirportData(String code, String name, String description, AirportData parent, Set<AirportData> children,
			Coordinates coordinates) {
		super();
		this.code = code;
		this.name = name;
		this.description = description;
		this.parent = parent;
		this.children = children;
		this.coordinates = coordinates;
	}



	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the parent
	 */
	public AirportData getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(AirportData parent) {
		this.parent = parent;
	}

	/**
	 * @return the children
	 */
	public Set<AirportData> getChildren() {
		return children;
	}

	/**
	 * @param children the children to set
	 */
	public void setChildren(Set<AirportData> children) {
		this.children = children;
	}

	/**
	 * @return the coordinates
	 */
	public Coordinates getCoordinates() {
		return coordinates;
	}

	/**
	 * @param coordinates the coordinates to set
	 */
	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}
	
}
