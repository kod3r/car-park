package org.carpark;

/**
 * The test class TicketPrinterTest.
 *
 * @author  Sian Turner
 * @version 12/04/05
 */
public class TicketPrinterTest extends junit.framework.TestCase {
    
    private TicketPrinter tp;
    /**
     * Default constructor for test class TicketPrinterTest
     */
    public TicketPrinterTest() {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    protected void setUp(){
        tp = new TicketPrinter();
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    protected void tearDown() {
    }



	public void testPrint() {
		assertNotNull(tp.print());
	}
}


