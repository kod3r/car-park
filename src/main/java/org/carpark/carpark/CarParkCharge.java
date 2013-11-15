package org.carpark.carpark;

/**
 * Stores tariff data for the car parks
 * @author Marjan Rahimi
 * @version March 2005
 */
public class CarParkCharge {
	private int upTo10min;
	private int upTo1hour;
	private int upTo2hours;
	private int upTo3hours;
	private int upTo4hours;
	private int upTo5hours;
	private int upTo6hours;
	private int upTo12hours;
	private int upTo18hours;
	private int upTo24hours;
	private int perDay;
	
	
	/**
	 * All prices are in pence
	 * @param upTo10min charge for up to 10 minutes stay
	 * @param upTo1hour charge for up to 1 hour stay
	 * @param upTo2hours charge for up to 2 hours stay
	 * @param upTo3hours charge for up to 3 hours stay
	 * @param upTo4hours charge for up to 4 hours stay
	 * @param upTo5hours charge for up to 5 hours stay
	 * @param upTo6hours charge for up to 6 hours stay
	 * @param upTo12hours charge for up to 12 hours stay
	 * @param upTo18hours charge for up to 18 hours stay
	 * @param upTo24hours charge for up to 24 hours stay
	 * @param perDay charge per day 
	 */
	public CarParkCharge(int upTo10min, int upTo1hour, int upTo2hours,
						 int upTo3hours, int upTo4hours, int upTo5hours,
						 int upTo6hours, int upTo12hours, int upTo18hours, 
						 int upTo24hours, int perDay) {

		this.upTo10min = upTo10min;
		this.upTo1hour = upTo1hour;
		this.upTo2hours = upTo2hours;
		this.upTo3hours = upTo3hours;
		this.upTo4hours = upTo4hours;
		this.upTo5hours = upTo5hours;
		this.upTo6hours = upTo6hours;
		this.upTo12hours = upTo12hours;
		this.upTo18hours = upTo18hours;
		this.upTo24hours = upTo24hours;
		this.perDay = perDay;
	}

	/**
	 * @return Returns the price perDay of stay.
	 */
	public int getPerDay() {
		return perDay;
	}
	
	/**
	 * @return Returns the price of upTo10min stay.
	 */
	public int getUpTo10min() {
		return upTo10min;
	}
	
	/**
	 * @return Returns the  price of upTo12hours stay.
	 */
	public int getUpTo12hours() {
		return upTo12hours;
	}
	
	/**
	 * @return Returns the  price of upTo18hours stay.
	 */
	public int getUpTo18hours() {
		return upTo18hours;
	}
	
	/**
	 * @return Returns the  price of upTo1hour stay.
	 */
	public int getUpTo1hour() {
		return upTo1hour;
	}
	
	/**
	 * @return Returns the  price of upTo24hours stay.
	 */
	public int getUpTo24hours() {
		return upTo24hours;
	}
	
	/**
	 * @return Returns the  price of upTo2hours stay.
	 */
	public int getUpTo2hours() {
		return upTo2hours;
	}
	
	/**
	 * @return Returns the  price of upTo3hours stay.
	 */
	public int getUpTo3hours() {
		return upTo3hours;
	}
	
	/**
	 * @return Returns the  price of upTo4hours stay.
	 */
	public int getUpTo4hours() {
		return upTo4hours;
	}
	
	/**
	 * @return Returns the  price of upTo5hours stay.
	 */
	public int getUpTo5hours() {
		return upTo5hours;
	}
	
	/**
	 * @return Returns the  price of upTo6hours stay.
	 */
	public int getUpTo6hours() {
		return upTo6hours;
	}




		

}
