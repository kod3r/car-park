/**
 * Project: CarPark
 * File: Location.java
 */
package org.carpark;
import java.lang.IllegalArgumentException;

/**
 * Store distance and direction of a car park
 * @author Marjan Rahimi
 * @version March 2005
 */
public class Location {

    private int distance;
    private Direction direction;

	/**
	 * Construct a location
	 * @param distance the distance of the car park
	 * @param direction the direction of the car park
	 * @exception IllegalArgumentException
	 */
	public Location(int distance, Direction direction)throws IllegalArgumentException {

		if (distance < 0 )
			throw new IllegalArgumentException("Distance must be greater than 0!");
		if (direction == Direction.UNDEFINED )
			throw new IllegalArgumentException("Direction cannot be undefined!");

		this.distance = distance;
		this.direction = direction;
	}

	
	/**
	 * @return Returns the direction of the car park.
	 */
	public Direction getDirection() {
		return direction;
	}
	
	/**
	 * @return Returns the distance of the car park.
	 */
	public int getDistance() {
		return distance;
	}


	/** 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object anObject) {
		if(anObject instanceof Location){
			Location location = (Location)anObject;
			return location.direction == direction && location.distance == distance;			
		}			
		return false;
	}


	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Distance: " + distance + ", " + "Direction: " + direction;
	}
	
    
}
