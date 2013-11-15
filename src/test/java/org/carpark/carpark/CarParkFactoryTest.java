package org.carpark.carpark;

/**
	CarParkFactory unit test class.
	@author	Ross Huggett
 */

public class CarParkFactoryTest extends junit.framework.TestCase {
	/**
	 Default constructor.
	*/
	public CarParkFactoryTest() {
	}

	/**
	 Set up test fixture.
	*/
	protected void setUp() {
	}

	/**
	 Tear down test fixture.
	*/
	protected void tearDown() {
	}

	public void testGetThreadGroup() {
		assertNotNull(CarParkFactory.getThreadGroup());
	}

	public void testGetNewPaymentMachine() {
		assertNotNull(CarParkFactory.getNewCarPark(500));
	}
	
	public void testGetPaymentMachineCount() {
		Integer i = new Integer(CarParkFactory.getCarParkCount());
		assertNotNull(i);
	}
}
