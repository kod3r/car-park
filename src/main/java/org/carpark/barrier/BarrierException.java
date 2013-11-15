package org.carpark.barrier;

/**
 * Thrown when an error occurs within the Barrier classes.
 * 
 * @author Sian Turner
 * @version 12/04/05
 * @see java.lang.Exception
 */
    
public class BarrierException extends Exception {
     

     private String type;
     private String message;

    /**
     * Constructor for objects of class BarrierException
     * @param type  the type of the error that has occurred
     */       
     public BarrierException(String type) {
         this.type = type;
         setMessage();
     }
     
     /**
      * Private method to set the message;
      */
     private void setMessage() {
		if(type == "EntryBarrier")
			message = "You must request and then remove a ticket to proceed";
		else if(type == "ExitBarrier")
			message = "You must insert your ticket for the barrier to raise";
		else if(type == "used")
			message = "This ticket has been used.";
		else if(type == "unpaid")
			message = "You must pay for your stay at a payment machine for the barrier to raise";
		else if(type == "proceed")
			message = "You must now proceed through the barrier";
		else if(type == "request")
			message = "You must request a ticket before you can remove it";
		else if(type == "remove")
			message = "You must remove your ticket before proceeding";
		else if(type == "full")
			message = "Sorry, the car park is full.  Ticket cannot be printed";
		else
			message = "Type not known";
     }
         

    /**
     * Accessor method to return the reason for the exception
     */      
     public String getMessage() {
         return message;
     }
         
}

    