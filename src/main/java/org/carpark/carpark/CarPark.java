package org.carpark.carpark;

/**
 Maintains space count and controls exit barriers.

 @filename CarPark.java
 @author Ross Huggett and Charles Boyle
 @date 13 March 2005
*/

import org.carpark.barrier.EntryBarrier;
import org.carpark.barrier.ExitBarrier;
import org.carpark.transaction.Transaction;
import org.carpark.barrier.Barrier;

import java.util.*;

public class CarPark extends Thread implements Observer {
	/**
	 The id of the carpark.
	*/
	private int id;
	
	/**
     	 The number of unoccupied spaces.
	*/	
	private int spaceCount;
    
	/**
         The capacity of the car park.
	*/
	private int capacity;
    
	/**
	 A vector of Transactions.
	*/
	private Vector archive;

	/**
         A vector of Barriers.
	*/
	private Vector barriers;

	/**
         The time.
	*/
	private Date time;

	/**
     	 Constructor for the Car Park.
	*/
	public CarPark(ThreadGroup tg, String name, int capacity, int id) {
		super(tg, name);
        	this.capacity = capacity;
        	spaceCount = capacity;
	        archive = new Vector();
        	barriers = new Vector();
	        this.id = id;
    	}
    
	/**
     	 Procedure for creating a new transaction.
     	 @param transaction_id The Transaction id
    	*/
    	public void newTransaction(Integer transaction_id) throws CarParkException {
    		if(isFull()) throw new CarParkException("full");
		System.out.println("CarPark " + id + " New Transaction " + transaction_id);
        	Transaction trans = new Transaction(id, transaction_id);
        	archive.addElement(trans);
    	}

	/**
	 Accessor method for querying whether the car park is full.
	*/
	public boolean isFull() {
		return spaceCount == 0;
	}
    
	/**
     	 Accessor method for getting spaces.
     	 @return Number of spaces
	*/
	public int getSpaces() {
        	return spaceCount;
	}
    
	/**
     	 Accessor method for getting transactions.
     	 @return An array of Transaction objects
	*/
	public Vector getTransactions() {
		Vector transactionData = archive;
		archive = new Vector();
		return transactionData;
	}
    
	/**
     	 Mutator method for setting time.
     	 @param time The time
	*/
	public void setClock(Date time) {
        	this.time = time;
	}

	/**
     	 Mutator method for setting spaces.
     	 @param count Number of spaces
	*/
	public void setSpaces(int count) {
        	spaceCount = count;
	}
    
	/**
     	 Provides the occupancy versus capacity of the carpark.
     	 @return a representation of the car park as a string
	*/
	public String toString() {
        	return spaceCount + "/" + capacity;
	}
    
	/**
 	 Accessor method for getting the carpark id.
 	 @return carpark id
 	*/
	public int getCarparkId() {
    		return id;
	}

	/**
     	 Mutator method for adding an entry barrier.
	 @param entry An entry barrier
	*/
	public void addEntryBarrier(EntryBarrier entry) {
		entry.addObserver(this);
		barriers.addElement(entry);
	}

	/**
	 Mutator method for adding an exit barrier.
	 @param exit An exit barrier
	 */
	public void addExitBarrier(ExitBarrier exit) {
		exit.addObserver(this);
		barriers.addElement(exit);
	}

	/**
	 Mutator method for adding and removing cars.
	 @param obs The observable
	 @param arg The object
	*/
	public void update(Observable obs, Object arg) {
		if (arg.getClass().getName() == "java.lang.Integer") {
			try {	
				newTransaction((Integer)arg);
			}
			catch (CarParkException c) { 
				c.getMessage();
			}
		}
		else if(arg.toString() == "addCar") {
			System.out.println("Car added to car park");
			try { 
				decrementSpaceCount();
			}
			catch (CarParkException c) {
				c.getMessage();
			}
		}
		else if(arg.toString() == "removeCar") {
			System.out.println("Car removed from car park");
			incrementSpaceCount();
		}
	}
	
	/**
	 Mutator method for decrementing the spacecount.
	*/
	public void decrementSpaceCount() throws CarParkException {
		spaceCount--;
			if(isFull()) {
				disableBarriers();
				throw new CarParkException("full");
			}
	}
	
	/**
	 Mutator method for incrementing the spacecount.
	 */
	public void incrementSpaceCount() {
		if(isFull())
			enableBarriers();
		spaceCount++;
	}
		
	/**
	 Mutator method for disabling the barriers.
	*/
	public void disableBarriers(){
		for (Iterator i = barriers.iterator(); i.hasNext();) {
			Barrier b = (Barrier) (i.next());
			b.disableBarrier();
		}
	}
	
	/**
	 Mutator method for enabling the barriers.
	*/
	public void enableBarriers(){
		for (Iterator i = barriers.iterator(); i.hasNext();) {
			 Barrier b = (Barrier) (i.next());
			 b.enableBarrier();
		}	
	}
	
	/**
	 Run method for Thread support.
	*/
	public void run() {
	//
	}
}
