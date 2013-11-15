package org.carpark.carpark;

/**
 Maintains space count and controls exit barriers.

 @filename CarParkTest.java
 @author Ross Huggett and Charles Boyle
 @date 13 March 2005
*/

import junit.framework.TestCase;
import org.carpark.barrier.EntryBarrier;
import org.carpark.barrier.ExitBarrier;
import org.carpark.barrier.BarrierException;
import org.carpark.barrier.BarrierFactory;
import org.carpark.transaction.Transaction;

import java.util.Date;
import java.util.Vector;


public class CarParkTest extends TestCase {
	private CarPark carpark;
	/**
	 @see TestCase#setUp()
	*/
	protected void setUp() throws Exception {
		carpark = CarParkFactory.getNewCarPark(500);
	}

	/**
	 @see TestCase#tearDown()
	*/
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 Constructor for CarParkTest.
	 @param arg0
	*/
	public CarParkTest(String arg0) {
		super(arg0);
	}

	/*
	 Test creation of the carpark and the number of spaces
	*/
	public void testCarPark() {
		assertTrue(carpark.getSpaces() == 500);
		System.out.println("new carpark created with id " + carpark.getCarparkId() + " and capacity " + carpark.getSpaces());
	}
	
	/**
	 Test creating a new transaction 
	*/
	public void testNewTransaction() {
		Integer i = new Integer(10);
		try {	carpark.newTransaction(i);
		} 
		catch (CarParkException c) {
			c.getMessage();
		}
		Vector archive;
		archive = carpark.getTransactions();
		Transaction t = (Transaction) archive.firstElement();
		System.out.println("new transaction created with id " + t.getTransaction());
	}
	
	/**
	 Test setting the carpark spaces
	*/
	public void testSetSpaces() {
		carpark.setSpaces(0);
		assertTrue(carpark.getSpaces() == 0);
	}
	
	/**
	 Test if the carpark is full
	*/
	public void testIsFull() {
		carpark.setSpaces(0);
		assertTrue(carpark.isFull());
	}
	
	/**
	 Test getting the carpark spaces
	*/
	public void testGetSpaces() {
		carpark.setSpaces(0);
		assertTrue(carpark.getSpaces() == 0);
	}
	
	/**
 	 Test setting the time
 	*/
	public void testSetClock() {
		Date time = new Date();
		System.out.println("setting the time to " + time);
		carpark.setClock(time);
	}
	
	/**
	 Test of toString method
	*/
	public void testToString() {
		System.out.println("spaceCount + '/' + capacity = " + carpark.toString());
	}
	
	/**
 	 Test of addEntryBarrier method
 	*/
	public void testAddEntryBarrier() {
		EntryBarrier entryBarrier = BarrierFactory.getNewEntryBarrier("1");
		carpark.addEntryBarrier(entryBarrier);
	}
	
	/**
	 Test of addExitBarrier method
	*/
	public void testAddExitBarrier() {
		ExitBarrier exitBarrier = BarrierFactory.getNewExitBarrier("1");
		carpark.addExitBarrier(exitBarrier);
	}
	
	/**
  	 Test of update method
	*/
	public void testUpdate() {
		//EntryBarrier entryBarrier = BarrierFactory.getNewEntryBarrier("1");
		ExitBarrier exitBarrier = BarrierFactory.getNewExitBarrier("1");
		try {	//entryBarrier.requestTicket();
				//entryBarrier.passBarrier();
				exitBarrier.passBarrier();
        	}
		catch (BarrierException b) {
            		b.getMessage();
        	}
	}
	
	/**
	 Test of decrementSpaceCount method
 	*/
	public void testDecrementSpaceCount() {
		int i = carpark.getSpaces();
		try {	carpark.decrementSpaceCount();
		} 
		catch (CarParkException c) {
			c.getMessage();
		}
		assertTrue(i == carpark.getSpaces() + 1);
		carpark.setSpaces(1);
		try {	carpark.decrementSpaceCount();
		} 
		catch (CarParkException c) {
			c.getMessage();
		}
		assertTrue(carpark.isFull());
	}
	
	/**
	 Test of incrementSpaceCount method
	*/
	public void testIncrementSpaceCount() {
		int i = carpark.getSpaces();
		carpark.incrementSpaceCount();
		assertTrue(i == carpark.getSpaces() - 1);
		carpark.setSpaces(0);
		carpark.incrementSpaceCount();
		assertFalse(carpark.isFull());
	}
	
	/**
	 Test of disableBarriers method
	*/
	public void testDisableBarriers() {
		carpark.disableBarriers();
	}
	
	/**
	 Test of enableBarriers method
	*/
	public void testEnableBarriers() {
		carpark.enableBarriers();
	}
}
