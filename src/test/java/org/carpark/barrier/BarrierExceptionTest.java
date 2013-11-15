package org.carpark.barrier;

/**
 * The test class BarrierExceptionTest.
 *
 * @author  Sian Turner
 * @version 12/04/05
 */
public class BarrierExceptionTest extends junit.framework.TestCase {

    private BarrierException exception;
    /**
     * Default constructor for test class BarrierExceptionTest
     */
    public BarrierExceptionTest(){
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    protected void setUp(){
        exception = new BarrierException("full");
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    protected void tearDown(){
    }
    
    public void testgetMessage(){
		
		assertEquals(exception.getMessage(), "Sorry, the car park is full.  Ticket cannot be printed");
	}
}

