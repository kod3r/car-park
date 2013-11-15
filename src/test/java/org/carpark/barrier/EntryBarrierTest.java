package org.carpark.barrier;

import java.util.Date;
/**
 * The test class EntryBarrierTest.
 *
 * @author  Sian Turner
 * @version 12/04/05
 */
public class EntryBarrierTest extends junit.framework.TestCase {
    
    private EntryBarrier ent1;
    /**
     * Default constructor for test class EntryBarrierTest
     */
    public EntryBarrierTest() {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    protected void setUp(){
        
        ent1 = BarrierFactory.getNewEntryBarrier("1");
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    protected void tearDown() {
    }

    public void testenableBarrier() {
        ent1.enableBarrier();
        assertEquals(true, ent1.isEnabled());
    }

    public void testdisableBarrier() {
        ent1.disableBarrier();
        assertEquals(false, ent1.isEnabled());
    }

    public void testrequestTicket() {
        try {
            assertNotNull(ent1.requestTicket());
        } catch (BarrierException e) {
            e.getMessage();
        }
    }
    
    public void testRemoveTicket() {
        try {
            ent1.requestTicket();
            ent1.removeTicket();
        } catch (BarrierException e) {
            e.getMessage();
        }
        assertEquals(true, ent1.isRaised());
    }
    
    public void testpassBarrier() {
        try{
            ent1.passBarrier();
           } catch (BarrierException e) {
               e.getMessage();
           }
        assertEquals(false, ent1.isRaised());
    }
    

    public void testGetEnabled() {
        assertNotNull(ent1.isEnabled());
    }
    

    public void testSetTime() {
        Date currtime = new Date();
        currtime.setTime(System.currentTimeMillis());
        ent1.setTime(currtime);
        assertEquals(ent1.getTime(), currtime);
    }

    public void testGetTime() {
        Date currtime = new Date();
        currtime.setTime(System.currentTimeMillis());
        ent1.setTime(currtime);
        assertEquals(currtime, ent1.getTime());
    }

    public void testGetName() {
        assertEquals("1", ent1.getName());
    }
}







