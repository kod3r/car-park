package org.carpark.paymentmachine;

/**
	PaymentMachineException unit test class.
	@author	Charles Boyle
 */
public class PaymentMachineExceptionTest extends junit.framework.TestCase {

    private PaymentMachineException exception;

	/**
		Default constructor.
	*/
    public PaymentMachineExceptionTest() {
    }

	/**
		Set up test fixture.
	*/
    protected void setUp() {
        exception = new PaymentMachineException("paid");
    }

	/**
		Tear down the test fixture.
	*/
    protected void tearDown() {
    }
    
    public void testgetMessage() {
		assertEquals(exception.getMessage(), "This ticket has been paid.");
	}
}