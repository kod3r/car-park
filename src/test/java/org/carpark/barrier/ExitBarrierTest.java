package org.carpark.barrier;

import org.carpark.transaction.Transaction;

/**
 * The test class ExitBarrierTest.
 *
 * @author  Sian Turner
 * @version 12/04/05
 */
public class ExitBarrierTest extends junit.framework.TestCase {

    private ExitBarrier exit;

    /**
     * Default constructor for test class ExitBarrierTest
     */
    public ExitBarrierTest() {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    protected void setUp() {
        exit = BarrierFactory.getNewExitBarrier("1");
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    protected void tearDown() {
    }

    public void testInsertTicket() {
        Transaction ticket = new Transaction(1, 1);
        ticket.pay();
        try {
            exit.insertTicket(ticket);
        } catch (BarrierException e) {
            e.getMessage();
        }
        assertEquals(true, exit.isRaised());
    }

    public void testpassBarrier() {
        try {
            exit.passBarrier();
        } catch (BarrierException e) {
            e.getMessage();
        }
        assertEquals(false, exit.isRaised());
    }
}

