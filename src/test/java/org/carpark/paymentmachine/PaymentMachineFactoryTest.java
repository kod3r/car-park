package org.carpark.paymentmachine;

/**
	PaymentMachineFactory unit test class.
	@author	Charles Boyle
 */
public class PaymentMachineFactoryTest extends junit.framework.TestCase {
	/**
		Default constructor.
	*/
    public PaymentMachineFactoryTest() {
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
		assertNotNull(PaymentMachineFactory.getThreadGroup());
	}

	public void testGetNewPaymentMachine() {
		assertNotNull(PaymentMachineFactory.getNewPaymentMachine());
	}
	
	public void testGetPaymentMachineCount() {
		assertNotNull(PaymentMachineFactory.getPaymentMachineCount());
	}
}