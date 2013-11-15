package org.carpark.barrier;


/**
 * The test class EntryBarrierSignTest.
 *
 * @author  Sian Turner
 * @version 12/04/05
 */
public class EntryBarrierSignTest extends junit.framework.TestCase {
    
    private EntryBarrierSign sign;
    /**
     * Default constructor for test class EntryBarrierSignTest
     */
    public EntryBarrierSignTest() {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    protected void setUp() {
        sign = new EntryBarrierSign("1");
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    protected void tearDown() {
    }

    public void testsetMessage() {
        final String FULL = "CAR PARK FULL";
		sign.setMessage(FULL);
		assertEquals(FULL, sign.getMessage());
	}

	public void testgetMessage() {
	    final String FULL = "CAR PARK FULL";
	    sign.setMessage(FULL);
		assertEquals(FULL, sign.getMessage());
	}
}


