package org.carpark;

import junit.framework.TestCase;

/**
 * Test Counter class
 * @author Marjan Rahimi
 * @version March 2005
 */
public class CounterTest extends TestCase {
	
	Counter counter;
	/** 
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		counter = new Counter();
		
	}

	/**
	 * Test the constructor
	 */
	public void testCounter(){
		assertEquals (0, counter.getTotal());
		
	}
	/**
	 * Test increment method
	 */
	public void testIncrement() {

		for(int i = 0; i < 100; i++)
			counter.increment();
		assertTrue(counter.getTotal()== 100);
	}

	/**
	 * Test decrement method
	 */
	public void testDecrement() {

		int i;
		for(i = 0; i < 100; i++)
			counter.increment();
		for(i = 100; i > 50; i--)
			counter.decrement();
		assertTrue(counter.getTotal()== 50);
	}
	/**
	 * Test the equal method
	 */
	public void testEquals(){
		Counter counter1 = new Counter();
		assertNotNull(counter1);
		assertEquals(counter1, new Counter());
		Counter counter2 = new Counter();
		assertEquals(counter1,counter2);
		for(int i = 0; i < 50; i++){
			counter1.increment();
			counter2.increment();
		}
		assertEquals(counter1,counter2);
		for(int i = 50; i > 25; i--){
			counter1.decrement();
			counter2.decrement();
		}
		assertEquals(counter1,counter2);
		for(int i = 25; i < 40; i++)
			counter2.increment();
		assertTrue(!counter1.equals(counter2));
	}
}
