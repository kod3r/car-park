package org.carpark.barrier;


/**
 * Factory to create new EntryBarriers and ExitBarriers
 * 
 * @author Sian Turner
 * @version 12/04/05
 */
public class BarrierFactory {
    
    /**
     *  The ThreadGroup for Barriers.
     */
    private static ThreadGroup tGroup;

    /**
     *  Returns the Barrier ThreadGroup.
     *  @return the Barrier ThreadGroup.
     */
    public static ThreadGroup getThreadGroup() {
        if (tGroup == null) {
            tGroup = new ThreadGroup("Barriers");
        }
        return tGroup;
    }
     
    /**
     * Creates a new instance of an EntryBarrier in the Factory ThreadGroup, and returns it.
     * @param name the name of the new EntryBarrier
     * @return a new instance of EntryBarrier.
     */
    public static EntryBarrier getNewEntryBarrier(String name) {
        EntryBarrier entry = new EntryBarrier (getThreadGroup(), name);
        Thread t = new Thread(getThreadGroup(), entry);
        t.start();
        return entry;
    }
    
    /**
     *  Creates a new instance of an ExitBarrier in the Factory ThreadGroup, and returns it.
     *  @param name the name of the new ExitBarrier
     *  @return a new instance of ExitBarrier.
     */
    public static ExitBarrier getNewExitBarrier(String name) {
        ExitBarrier exit = new ExitBarrier (getThreadGroup(), name);
        Thread t = new Thread(getThreadGroup(), exit);
        t.start();
        return exit;
    }

}
