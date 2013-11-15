package org.carpark;
/**
 * Prints a ticket when requested to.
 * Assigns the ticket the next ID number it holds.
 * 
 * @author Sian Turner
 * @version 12/04/05
 */
public class TicketPrinter {

	private static TicketPrinter ticketPrinter;

    private static int ticketnumber = 1;
    
    /**
     * Constructor for objects of class TicketPrinter
     */
    public TicketPrinter() {
	}

    /**
     * Returns a static instance of the TicketPrinter
	 * @return a static instance of the TicketPrinter
     */
    public static TicketPrinter getInstance() {
		if (ticketPrinter == null) {
			ticketPrinter = new TicketPrinter();
		}
		return ticketPrinter;
    }

    /**
     * Procedure to print a ticket.
     * @return ticketID
     */
    public int print() {
		return ticketnumber++;
    }
    
}
