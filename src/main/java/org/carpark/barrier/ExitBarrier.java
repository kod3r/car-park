package org.carpark.barrier;

import org.carpark.transaction.Transaction;

/**
 * Manages the exit of cars from a car park.
 * Subclass of Barrier.
 * @author Sian Turner
 * @version 12/04/05
 */

public class ExitBarrier extends Barrier {

    /**
     * Constructor for objects of class ExitBarrier
     * @param tg  the ThreadGroup
     * @param name  the name of the Barrier
     */
    public ExitBarrier(ThreadGroup tg, String name) {
        super(tg, name);
    }

    /**
     * Overridden method inherited from Runnable.
     * @see java.lang.Runnable#run()
     */
    public void run() {}


    /**
     * Procedure to insert a ticket into exit barrier to be validated so can exit the carpark
     */
	public void insertTicket(Transaction ticket) throws BarrierException {
		if (ticket.isUsed()) throw new BarrierException("used");
		else if (ticket.getPaymentStatus()) {
			raiseBarrier();
			ticket.setUsed();
		}
		else throw new BarrierException("unpaid");
	}

    /**
     * Procedure for passing a Barrier. Lower Barrier and inform carpark that a car has left.
     * Notifies its observers that a car has left.
     * @throws BarrierException if the Barrier is not raised
     */
    public void passBarrier() throws BarrierException {
        if(isRaised()) {
            setChanged();
            notifyObservers("removeCar");
            //System.out.println("Car has left carpark at barrier " + getName());
            lowerBarrier();
        }
        else throw new BarrierException("ExitBarrier");
    }

    /**
     * Overridden abstract method of superclass Barrier.
     */
    public void disableBarrier() {}
    
   /**
     * Overridden abstract method of superclass Barrier.
     */
    public void enableBarrier() {}
    
    
}
