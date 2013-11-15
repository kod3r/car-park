package org.carpark;

import junit.framework.TestCase;
import org.carpark.carpark.Status;

import java.util.Hashtable;


/**
 * Test DirectionSign class
 * @author Marjan Rahimi
 * @version March 2005
 */
public class DirectionSignTest extends TestCase {
	Hashtable<Integer, Location> carParksLocation;
	DirectionSign directionSign;
	DirectionSign ds1;
	Hashtable<Integer, Status>carParksStatus;
	
	protected void setUp() throws Exception {
		super.setUp();
		Direction left  = Direction.LEFT;
		Direction right = Direction.RIGHT;
		Direction straight = Direction.STRAIGHT;
		carParksLocation = new Hashtable<Integer, Location>();
		Location aLocation;
		aLocation = new Location(23456, left);
		carParksLocation.put(1,aLocation);
		aLocation = new Location(564, right);
		carParksLocation.put(2,aLocation);
		aLocation = new Location(2, straight);
		carParksLocation.put(3,aLocation);
		aLocation = new Location(6785, left);
		carParksLocation.put(4,aLocation);
		aLocation = new Location(342, right);
		carParksLocation.put(5,aLocation);
		aLocation = new Location(857, straight);
		carParksLocation.put(6,aLocation);
		aLocation = new Location(987, left);
		carParksLocation.put(7,aLocation);
		directionSign = new DirectionSign(carParksLocation);
		carParksStatus = new Hashtable<Integer, Status>();
		carParksStatus.put(1, Status.SPACES);		
		carParksStatus.put(2, Status.SPACES);
		carParksStatus.put(3, Status.FULL);
		carParksStatus.put(4, Status.SPACES);
		carParksStatus.put(5, Status.FULL);
		carParksStatus.put(6, Status.SPACES);
		carParksStatus.put(7, Status.FULL);
	}

	/**
	 * Test Constructing DirectionSign
	 */
	public void testDirectionSign() {
		//Test with empty hashtable
		boolean isCatched = false;
		Hashtable<Integer, Location> cpLocation = new Hashtable<Integer, Location>();
		try{
			ds1 = new DirectionSign(cpLocation);
		}catch(IllegalArgumentException e){isCatched = true;}
		assertTrue(isCatched);
		assertNull(ds1);
	}

	/**
	 * Test updateSign method
	 */
	public void testUpdateSign() {
		boolean isCatched = false;
		Hashtable<Integer, Status> cps = new Hashtable<Integer, Status>();
		//Test when carParkStatus is empty
		try{
			directionSign.updateSign(cps);
		}catch(IllegalArgumentException e){isCatched = true;}
		assertTrue(isCatched);
		assertEquals(Direction.UNDEFINED, directionSign.getBestDirection());
		
		//Test when car park is mismatched
		isCatched = false;
		cps.put(1, Status.SPACES);
		try{
			directionSign.updateSign(cps);
		}catch(IllegalArgumentException  e){isCatched = true;}
		assertTrue(isCatched);
		assertEquals(Direction.UNDEFINED, directionSign.getBestDirection());
		
		//Test successful update
		directionSign.updateSign(carParksStatus);
		assertEquals(Direction.RIGHT, directionSign.getBestDirection());
	}

	/**
	 * Test resetGeographicData method
	 */
	public void testResetGeographicData() {
		//Test with empty hashtable
		boolean isCatched = false;
		Hashtable<Integer, Location> cpLocation = new Hashtable<Integer, Location>();
		try{
			directionSign.resetGeographicData(cpLocation);
		}catch(IllegalArgumentException e){isCatched = true;}
		assertTrue(isCatched);
		
		//Test successful Reset
		directionSign.updateSign(carParksStatus);
		Location theLocation = new Location(68,Direction.STRAIGHT);
		carParksLocation.put(8, theLocation);
		directionSign.resetGeographicData(carParksLocation);
		assertEquals(Direction.UNDEFINED,directionSign.getBestDirection());
		carParksStatus.put(8, Status.SPACES);
		directionSign.updateSign(carParksStatus);
		assertEquals(Direction.STRAIGHT, directionSign.getBestDirection());
	}


	/**
	 * Test toString method
	 */
	public void testToString() {
		directionSign.updateSign(carParksStatus);
		assertEquals("RIGHT", directionSign.toString());
	}

}
