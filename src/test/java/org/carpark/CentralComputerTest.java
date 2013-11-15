package org.carpark;

import junit.framework.TestCase;
import org.carpark.carpark.CarPark;
import org.carpark.carpark.CarParkCharge;
import org.carpark.carpark.CarParkSpaces;
import org.carpark.paymentmachine.PaymentMachine;
import org.carpark.transaction.Transaction;

import java.util.Hashtable;
import java.util.*;

/**
 * Test CentralComputer class
 * @author Marjan Rahimi
 * @version March 2005
 */
public class CentralComputerTest extends TestCase {
	CentralComputer centralComputer;
	CarParkCharge cpCharge;
	CarParkCharge cpCharge1;
	CarPark cp;
	DirectionSign ds;
	PaymentMachine pm;
	protected void setUp() throws Exception {
		super.setUp();
		centralComputer = CentralComputer.getInstance();
		cpCharge = new CarParkCharge(10,20,30,40,50,60,70,80,90,100,110); 
	}
	
	/**
	 * Test getInstance static method
	 */
	public void testGetInstance() {
		assertNotNull(centralComputer);
		CentralComputer cc2 = CentralComputer.getInstance();
		assertNotNull(cc2);
		assertEquals(centralComputer, cc2);
	}

	/**
	 * Test setCarParkCharge and getCarParkCharge methods
	 */
	public void testSetAndGetCarParkCharge() {
		//Test with null CarParkCharge
		boolean isCatched = false;
		try{
			centralComputer.setCarParkCharge(cpCharge1);
		}catch(NullPointerException e){isCatched = true;}
		assertTrue(isCatched);
		
		//Test successful set
		centralComputer.setCarParkCharge(cpCharge);
		assertEquals(cpCharge, centralComputer.getCarParkCharge());
	}


	/**
	 * Test addCarPark method
	 */
	public void testAddCarPark() {
		//Test adding null CarPark
		boolean isCatched = false;
		try{
			centralComputer.addCarPark(cp);
		}catch(NullPointerException e){isCatched = true;}
		assertTrue(isCatched);
		assertTrue(centralComputer.getCarParksList().isEmpty());
		
		//Test successful adding
		CarPark cp1 = new CarPark(new ThreadGroup(""), "", 500, 1);
		centralComputer.addCarPark(cp1);
		assertEquals(centralComputer.getCarParksList().size(),1);
		CarPark cp2 = new CarPark(new ThreadGroup(""), "", 600, 2);
		centralComputer.addCarPark(cp2);
		CarPark cp3 = new CarPark(new ThreadGroup(""), "", 700, 3);
		centralComputer.addCarPark(cp3);
		assertEquals(centralComputer.getCarParksList().size(),3);
		
		Hashtable<Integer, CarPark> carParks = centralComputer.getCarParksList();		
		assertEquals(carParks.get(1),cp1);
		assertEquals(carParks.get(2),cp2);	
		assertEquals(carParks.get(3),cp3);
		System.out.println(centralComputer.toString()); 
	}

	/**
	 * Test addDirectionSign method
	 */
	public void testAddDirectionSign() {
		//Test adding null DirectionSign
		boolean isCatched = false;
		try{
			centralComputer.addDirectionSign(ds);
		}catch(NullPointerException e){isCatched = true;}
		assertTrue(isCatched);
		assertTrue(centralComputer.getDirectionSignList().isEmpty());
		
		//Test successful adding
		Hashtable<Integer, Location> cpLocations = new Hashtable<Integer, Location>();
		cpLocations.put(1,new Location(8374, Direction.LEFT));
		cpLocations.put(1,new Location(374, Direction.RIGHT));
		cpLocations.put(1,new Location(74, Direction.STRAIGHT));
		cpLocations.put(1,new Location(4, Direction.LEFT));		
		DirectionSign ds1 = new DirectionSign(cpLocations);
		centralComputer.addDirectionSign(ds1);
		assertEquals(1, centralComputer.getDirectionSignList().size());
		
		cpLocations = new Hashtable<Integer, Location>();
		cpLocations.put(1,new Location(2345, Direction.LEFT));
		cpLocations.put(1,new Location(345, Direction.RIGHT));
		cpLocations.put(1,new Location(45, Direction.STRAIGHT));
		cpLocations.put(1,new Location(5, Direction.LEFT));
		DirectionSign ds2 = new DirectionSign(cpLocations);
		centralComputer.addDirectionSign(ds2);
		assertEquals(2, centralComputer.getDirectionSignList().size());
		
		Vector<DirectionSign> dss = centralComputer.getDirectionSignList();
		assertEquals(dss.get(0), ds1);
		assertEquals(dss.get(1), ds2);
		System.out.println(centralComputer.toString()); 
	}
	/**
	 * Test addPaymentMachine method
	 */
	public void testAddPaymentMachine(){
		//Test adding null PaymentMachine
		boolean isCatched = false;
		try{
			centralComputer.addPaymentMachine(pm);
		}catch(NullPointerException e){isCatched = true;}
		assertTrue(isCatched);
		assertTrue(centralComputer.getPaymentMachinesList().isEmpty());
		
		//Test successful adding
		ThreadGroup tg = new ThreadGroup("test");
		PaymentMachine pm1 = new PaymentMachine(tg, "First");
		centralComputer.addPaymentMachine(pm1);
		assertEquals(1, centralComputer.getPaymentMachinesList().size());
		PaymentMachine pm2 = new PaymentMachine(tg, "Second");
		centralComputer.addPaymentMachine(pm2);
		PaymentMachine pm3 = new PaymentMachine(tg, "Third");
		centralComputer.addPaymentMachine(pm3);
		assertEquals(3, centralComputer.getPaymentMachinesList().size());
		
		Vector <PaymentMachine> paymentMachines = centralComputer.getPaymentMachinesList();		
		assertEquals(pm1, paymentMachines.get(0));
		assertEquals(pm2, paymentMachines.get(1));	
		assertEquals(pm3, paymentMachines.get(2));
		System.out.println(centralComputer.toString()); 
	}
	
	/**
	 * Test getTransactionArchive method
	 */
	public void testGetTransactionArchive(){
		Vector<Transaction> tList = centralComputer.getTransactionArchive();
		assertTrue(centralComputer.getTransactionArchive().isEmpty());
		
	}
	/**
	 * Test getMotoristRecords method
	 */
	public void testGetMotoristRecords(){
		Hashtable<Integer, Counter> mRecords = centralComputer.getMotoristRecords();
		assertTrue(centralComputer.getMotoristRecords().isEmpty());
		
	}
	
	/**
	 * Test getSpaceAvailablityTable method
	 */
	public void testGetSpaceAvailablityTable(){
		Hashtable<Date, CarParkSpaces[]>  sTable = centralComputer.getSpaceAvailabilityTable();
		assertTrue(centralComputer.getSpaceAvailabilityTable().isEmpty());
		
	}
	
	/**
	 * Test toString method
	 */
	public void testToString(){
		System.out.println(centralComputer.toString());
	}
}//TestCentralComputer
