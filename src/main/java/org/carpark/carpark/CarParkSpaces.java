package org.carpark.carpark;



/**
 * Stores number of spaces available in a car park
 * @author Marjan Rahimi
 * @version March 2005
 */
public class CarParkSpaces{

	protected int id;//car park id
	protected int numSpaces;//number of spaces available
	
	/**
	 * @param carParkId A car park id
	 * @param numSpaces The number of spaces
	 * @throws IllegalArgumentException
	 */
	public CarParkSpaces(int carParkId, int numSpaces)throws IllegalArgumentException{
		if(carParkId < 1)
			throw new IllegalArgumentException("Car Park ID must be greater than 1");
		if(numSpaces < 0)
			throw new IllegalArgumentException("Number of spaces available in a car park can not be negative");
		this.id = carParkId;
		this.numSpaces = numSpaces;
	}


	/**
	 * @return Returns the car park id.
	 */
	public int getId() {
		return id;
	}
	

	/**
	 * @return Returns the number of Spaces available.
	 */
	public int getNumSpaces() {
		return numSpaces;
	}


	/** 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object anObject) {
		if(anObject instanceof CarParkSpaces){
			CarParkSpaces theCarParkSpace = (CarParkSpaces)anObject;
			return id == theCarParkSpace.id && numSpaces == theCarParkSpace.numSpaces;
		}
		return false;
	}
	

}
