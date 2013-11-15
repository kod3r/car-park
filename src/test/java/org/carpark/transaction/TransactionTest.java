package org.carpark.transaction;

/**
 A Car Park Transaction.

 @filename TransactionTest.java
 @author Ross Huggett
 @date 25 March 2005
*/

import junit.framework.TestCase;

import java.util.Date;

public class TransactionTest extends TestCase {
	private Transaction transaction;

	/**
	 @see TestCase#setUp()
	*/
	protected void setUp() throws Exception {
		Integer i = new Integer(1);
		transaction = new Transaction(1,i); 
	}

	/**
	 @see TestCase#tearDown()
	*/
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 Constructor for TransactionTest.
	 @param arg0
	*/
	public TransactionTest(String arg0) {
		super(arg0);
	}

	/**
	 This tests if the ticket is created as unpaid
	*/
	public void testTransaction() {
		assertFalse(transaction.getPaymentStatus());
	}

	/**
	 This tests the payment of a ticket
 	*/
	public void testPayTransaction() {
		transaction.pay();
		assertTrue(transaction.getPaymentStatus());
	}

	/**
	 This tests the carpark is the correct id
	*/
	public void testGetCarPark() {
		assertTrue(transaction.getCarPark() == 1);
	}

	/**
	 This tests the transaction is the correct id
	*/
	public void testGetTransaction() {
		Integer i = new Integer(1);
		assertTrue(i.equals(transaction.getTransaction()));
	}
	
	/**
	 This tests the getStayTime method
 	*/
	public void testGetStayTime() {
		Date now = new Date();
		int i = now.getMinutes();
		i += 10;
		now.setMinutes(i);
		transaction.setDepartureTime(now);
		System.out.println(transaction.getStayTime());
	}
	
	/**
	 This tests the setDepartureTime method
	*/
	public void testSetDepartureTime() {
		Date now = new Date();
		transaction.setDepartureTime(now);
	}
	
	/**
	 This tests the getArrivalTime method
	*/
	public void testGetArrivalTime() {
		assertNotNull(transaction.getArrivalTime());
	}
}
