package org.carpark.carpark;

/**
 Maintains space count and controls exit barriers.
 
 @author Ross Huggett
*/

public class CarParkFactory {
	/**
	 The ThreadGroup for CarPark Factory.
	*/
	private static ThreadGroup tGroup;

	/**
	 Car Park counter. Counting Threads in a ThreadGroup is only an estimate.
	*/
	private static int counter = 0;

	/**
	 Returns the Car Park ThreadGroup.
	 @return the Car Park ThreadGroup
	*/
	public static ThreadGroup getThreadGroup() {
		if (tGroup == null) {
			tGroup = new ThreadGroup("CarParks");
		}
		return tGroup;
	}

	/**
	 Creates a new instance of Car Park in the Factory ThreadGroup, and returns it.
	 @return a new instance of Car Park
	*/
	public static CarPark getNewCarPark(int capacity) {
		counter++;
		CarPark cp = new CarPark(getThreadGroup(), "CarPark " + counter, capacity, counter);
		cp.start();
		return cp;
	}

	/**
	 Method for accessing a count of active Car Parks.
	 @return an estimate of the number of active Car Park threads
    	*/
	public static int getCarParkCount() {
		return counter;
	}
}
