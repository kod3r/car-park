package org.carpark.barrier;

import org.carpark.TicketPrinter;

/**
 * Manages the entry of cars to a car park.
 * Subclass of Barrier.
 * @author Sian Turner
 * @version 12/04/05
 */
public class EntryBarrier extends Barrier {

    private EntryBarrierSign sign;
    private final String FULL = "CAR PARK FULL";
    private final String SPACES = "SPACES AVAILABLE";
    private boolean enabled;
    private boolean ticketPrinted;


    /**
     * Constructor for objects of class EntryBarrier
     * @param tg  the ThreadGroup
     * @param name  the name of the Barrier
     */
    public EntryBarrier(ThreadGroup tg, String name) {

        super(tg, name);
        sign = new EntryBarrierSign(SPACES);
        enabled = true;
        ticketPrinted = false;
    }
    
    /**
     * Overridden method inherited from Runnable.
     * @see java.lang.Runnable#run()
     */
    public void run() {
    }


    /**
     * Procedure to request a ticket for entry to the car park
     * Sends a message to the car park to create a new transaction.
     * @throws org.carpark.barrier.BarrierException if ticket requested when car park is full
     * @throws org.carpark.barrier.BarrierException if ticket has been printed and not removed
     * @throws org.carpark.barrier.BarrierException if ticket has been printed and taken and the car has not passsed through
     */
    public int requestTicket() throws BarrierException {
		if (isRaised()) throw new BarrierException("proceed");
		if (ticketPrinted) throw new BarrierException("remove");
		if (enabled) {
            int ticketnumber = printTicket();
            createNewTransaction(ticketnumber);
            //System.out.println("Please remove ticket");
            return ticketnumber;
		}
		else throw new BarrierException("full");
     }


    /**
     * Procedure for ticket removal. Causes the barrier to be raised.
     * @throws BarrierException if ticket has not yet been printed
     * @throws BarrierException if car has not yet proceeded
     */
    public void removeTicket() throws BarrierException {
		if (isRaised()) throw new BarrierException("proceed");
		if(ticketPrinted) {
			raiseBarrier();
			ticketPrinted = false;
		}
		else throw new BarrierException("request");
	}


    /**
     * Procedure for passing through the Barrier.
     * Notifies observers that a car has entered the carpark.
     * @throws BarrierException if Barrier is not raised
     */
    public void passBarrier() throws BarrierException {
        if(isRaised()) {            
            setChanged();
            notifyObservers("addCar");
            //System.out.println("Car has entered car park at barrier " + getName());
            lowerBarrier();
        }
        else throw new BarrierException("EntryBarrier");
    }


    /**
     * Procedure to disable barrier when carpark is full.
     */
    public void disableBarrier(){
        sign.setMessage(FULL);
        sign.illuminate();
        enabled = false;
    }

    /**
     * Procedure for enabling entry barriers.
     */
    public void enableBarrier(){
        sign.setMessage(SPACES);
        sign.illuminate();
        enabled = true;
    }
    
    /**
     * Accessor method to get the status of the barrier - whether it is enabled or not
     * 
     * @return Enabled or disabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Private procedure called when a ticket is requested.  Prints the ticket.
     */

    private int printTicket(){
        int ticketnumber = TicketPrinter.getInstance().print();
        ticketPrinted = true;
        return ticketnumber;
    }


    /**
     * Private procedure called to tell car park to create a new transaction.
     * @param  ticketnumber the ticket number
     */
    private void createNewTransaction(Integer ticketnumber) {
        setChanged();
        notifyObservers(ticketnumber);
    }

}
