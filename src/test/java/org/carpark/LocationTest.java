package org.carpark;

import junit.framework.TestCase;


/**
 * Test Location class
 * @author Marjan Rahimi
 * @version March 2005
 */
public class LocationTest extends TestCase {

	Location location, location1, location2;
	Direction left, right, straight;
	
	protected void setUp() throws Exception {
		super.setUp();
		left  = Direction.LEFT;
		right = Direction.RIGHT;
		straight = Direction.STRAIGHT;
		location = new Location(3456,left);
		
	}

	/**
	 * Test the constructor
	 */
	public void testLocation() {
		try{
			location1 = new Location(-1, left);
		}catch(IllegalArgumentException e){}
		assertNull(location1);
		try{
			location1 = new  Location(345, Direction.UNDEFINED);
		}catch(IllegalArgumentException e){}
		assertNull(location1);
		location1 = new Location(456, right);
		assertNotNull(location1);
	}

	/**
	 * Test getDirection method
	 */
	public void testGetDirection() {
		assertEquals(Direction.LEFT, location.getDirection());
	}

	/**
	 * Test getDistance method
	 */
	public void testGetDistance() {
		assertEquals(3456, location.getDistance());
	}

	/**
	 * Test equal method
	 */
	public void testEquals() {
		location1 = new Location(2345, left);
		assertEquals(location1,location1);
		assertEquals(location1, new Location(2345, left));
		location2 = new Location(2345, left);
		assertEquals(location1, location2);
		location2 = new Location(5746, right);
		assertTrue(!location1.equals(location2));
		location2 = new Location(5746, left);
		assertTrue(!location1.equals(location2));		
		location2 = new Location(2345, right);
		assertTrue(!location1.equals(location2));		
	}

	/**
	 * Test toString method
	 */
	public void testToString() {
		assertEquals("Distance: 3456, Direction: LEFT", location.toString()); 
	}

}
