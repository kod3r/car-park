package org.carpark.barrier;

/**
 * The test class BarrierFactoryTest.
 *
 * @author  Sian Turner
 * @version 12/04/05
 */
public class BarrierFactoryTest extends junit.framework.TestCase {
    /**
     * Default constructor for test class BarrierFactoryTest
     */
    public BarrierFactoryTest() {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    protected void setUp() {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    protected void tearDown() {
    }

	public void testGetNewEntryBarrier() {
		assertNotNull(BarrierFactory.getNewEntryBarrier("1"));
	}
	
	public void testGetNewExitBarrier() {
		assertNotNull(BarrierFactory.getNewExitBarrier("1"));
	}

	public void testGetThreadGroup() {
		assertNotNull(BarrierFactory.getThreadGroup());
	}
}


