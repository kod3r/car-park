package org.carpark.paymentmachine;

import org.carpark.carpark.CarParkCharge;

/**
	PaymentMachine unit test class.
	@author	Charles Boyle
 */
public class PaymentMachineTest extends junit.framework.TestCase {

    private PaymentMachine pm;

	/**
		Default constructor.
	*/
    public PaymentMachineTest() {
    }

	/**
		Set up test fixture.
	*/
    protected void setUp() {
        pm = PaymentMachineFactory.getNewPaymentMachine();
        pm.setTariffs(new CarParkCharge(1,0,0,0,0,0,0,0,0,0,0));
    }

	/**
		Tear down test fixture.
	*/
    protected void tearDown() {
    }

    public void testCalculateCost() {
		assertNotNull(pm.calculateCost(1));
	}
}