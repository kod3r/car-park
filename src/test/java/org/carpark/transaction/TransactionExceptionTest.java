package org.carpark.transaction;

/**
 Thrown by Transaction.
 
 @filename TransactionExceptionTest.java
 @author Ross Huggett 
 @date 30 April 2005
*/

import junit.framework.TestCase;

public class TransactionExceptionTest extends TestCase {
	private TransactionException exception;
	/**
	 @see TestCase#setUp()
	*/
	protected void setUp() throws Exception {
		exception = new TransactionException(""); 
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
	public TransactionExceptionTest(String arg0) {
		super(arg0);
	}

	public void testgetMessage() {
		assertEquals(exception.getMessage(), "Unknown Trasaction Exception");
	}
}
