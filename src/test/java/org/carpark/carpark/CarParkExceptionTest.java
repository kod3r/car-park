package org.carpark.carpark;

/**
 @filename CarParkExceptionTest.java
 @author Ross Huggett
 @date 13 March 2005
*/

import junit.framework.TestCase;

public class CarParkExceptionTest extends TestCase {
	private CarParkException exception;
	/**
	 @see TestCase#setUp()
	*/
	protected void setUp() throws Exception {
		exception = new CarParkException("full");
	}

	/**
	 @see TestCase#tearDown()
	*/
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testgetMessage() {
		assertEquals(exception.getMessage(), "The carpark is full.");
	}
}
