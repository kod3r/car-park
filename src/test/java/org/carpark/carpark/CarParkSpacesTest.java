package org.carpark.carpark;

import junit.framework.TestCase;

/**
 * Test CarParkSpaces class
 * @author Marjan Rahimi - jrahi02
 * @version March 2005
 */
public class CarParkSpacesTest extends TestCase {
	CarParkSpaces carParkSpaces;
	
	
	/**
	 * test constructor
	 */
	public void testCarParkSpaces(){
		try{
			carParkSpaces = new CarParkSpaces(0,3847);
		}
		catch(IllegalArgumentException e){}
		assertNull(carParkSpaces);
		try{
			carParkSpaces = new CarParkSpaces(34,-3);
		}
		catch(IllegalArgumentException e){}
		assertNull(carParkSpaces);
		
	}
	/**
	 * test getId
	 */
	public void testGetId() {
		carParkSpaces = new CarParkSpaces(30, 9857);
		assertTrue(carParkSpaces.getId() == 30);
		
	}

	/**
	 * Test getNumSpaces method
	 */
	public void testGetNumSpaces() {
		carParkSpaces = new CarParkSpaces(30, 9857);
		assertTrue(carParkSpaces.getNumSpaces() == 9857);
	}
	
	/**
	 * Test equal method
	 */
	public void testEquals(){
		CarParkSpaces cps1 = new CarParkSpaces(2,456);
		assertEquals(cps1, cps1);
		assertEquals(cps1,new CarParkSpaces(2, 456));
		CarParkSpaces cps2 = new CarParkSpaces(2, 456);
		assertEquals(cps1, cps2);
		cps2 = new CarParkSpaces(4,874);
		assertTrue(!cps2.equals(cps1));
		
	}
}
