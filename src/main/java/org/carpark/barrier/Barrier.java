package org.carpark.barrier;

import java.util.Date;
import java.util.*;

/**
 * Abstract class Barrier - superclass for EntryBarrier and ExitBarrier
 *
 * @author Sian Turner
 * @version 12/04/05
 */

public abstract class Barrier extends Observable implements Runnable  {
    

    private String name;
    private boolean raised;
    private Date time;
    private ThreadGroup threadGroup;

    /**
     * Constructor of objects of type Barrier.
     * @param tg  the threadgroup
     * @param name  the name of the Barrier
     */
    public Barrier(ThreadGroup tg, String name){
        this.name = name;
        threadGroup = tg;
        raised = false;
        time = new Date(System.currentTimeMillis());
    }

    /**
     * Abstract run() method inherited from Runnable, must be implemented in subclasses.
     * 
     * @see java.lang.Runnable#run()
     */
    public abstract void run();


    /**
     * Procedure to raise a barrier.
     */
    public void raiseBarrier(){
        raised = true;
        //System.out.println("Barrier " + name + " has been raised, please pass through");
    }

    /**
     * Procedure to lower a barrier.
     */
    public void lowerBarrier(){
        raised = false;
        //System.out.println("Barrier " + name + " has been lowered");
    }

     /**
     * Procedure to check if a barrier is raised.
     * @return  Raised or not raised
     */
    public boolean isRaised(){
        return raised;
    }

    /**
     * Procedure through which the car park can set the time at the barrier.
     * 
     * @param newTime  the new time to be set
     */
    public void setTime(Date newTime){
        time = newTime;
    }
    
    
    /**
     * Accessor method to get the time.
     * 
     * @return  the time
     */
    public Date getTime(){
        return time;
    }
    
    /**
     * Accessor Method returns the name/ID of the barrier.
     * 
     * @return  the name of the Barrier
     */
    public String getName() {
        return name;
    }
    
    /**
     * Abstract method to disable barriers if the carpark is full. Implemented in subclasses.
     */
    public abstract void disableBarrier();
    
    /**
     * Abstract method to enable barriers when there are spaces in the carpark. Implemented in subclasses.
     */
    public abstract void enableBarrier();
        

}
