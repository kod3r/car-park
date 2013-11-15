package org.carpark.transaction;

/**
 A Car Park Transaction.

 @filename Transaction.java
 @author Ross Huggett
 @date 25 March 2005
*/

import java.util.Date;

public class Transaction extends Thread {
	/** 
	 Has the ticket been paid for? This is used instead of time. See below. 
	*/
	private boolean is_paid;

	/** 
	 Has the ticket been used? Has vehicle has left on this transaction?
	*/
	private boolean is_used;

	/**
	 The id of the carpark that issued the ticket.
	*/
	private int carpark_id;
	
	/**
	 The id of the transaction.
	*/
	private Integer transaction_id;
	
	/**
	 The arrival time of the car.
	*/
	private Date arrival_time;
	
	/**
	 The departure time of the car.
	*/
	private Date depart_time;
			
	/**
	 Constructor which sets payment to false.
	*/
	public Transaction(int carpark_id, Integer transaction_id){
		is_paid = false;
		is_used = false;
		this.carpark_id = carpark_id;
		this.transaction_id = transaction_id;
		this.arrival_time = new Date();
	}
	
	public Transaction(ThreadGroup tg, String name) {
		super(tg, name);
	}
	
	/**
	 Mutator method that sets the payment of the ticket to true.
	*/
	public void pay() {
		is_paid = true;
	}
	
	/**
	 Accessor method to check the ticket been paid.
	 @return is_paid 
	*/
	public boolean getPaymentStatus() {
		return is_paid;
	}
	
	/**
	 Mutator method that sets the use of the ticket to true.
	*/
	public void setUsed() {
		is_used = true;
	}
	
	/**
	 Accessor method to check the ticket been used.
	 @return is_used 
	*/
	public boolean isUsed() {
		return is_used;
	}
	
	/**
	 Accessor method to get the carpark id which was used to create the ticket.
	 @return carpark_id the carpark id
	*/
	public int getCarPark() {
		return carpark_id;
	}
	
	/**
 	 Accessor method to get the transaction id.
 	 @return transaction_id the transaction id
 	*/
	public Integer getTransaction() {
		return transaction_id;
	}
	
	/**
	 Accessor method to get the stay time.
	 @return the difference between departure time and arrival time
	*/
	public Date getStayTime() {
		long duration;
		if (depart_time == null) duration = new Date().getTime() - arrival_time.getTime();
		else duration = depart_time.getTime() - arrival_time.getTime();
		return new Date(duration);
	}
	
	/**
	 Mutator method to set the departure time.
	*/
	public void setDepartureTime(Date time){
		this.depart_time = time;
	}
	
	/**
         Accessor method to get the arrival time.
         @return arrival_time the arrival time
	*/
	public Date getArrivalTime(){
		return arrival_time;
	}
	
	/**
 	 Run method for Thread support.
 	*/
	public void run() {
	//
	}
}
