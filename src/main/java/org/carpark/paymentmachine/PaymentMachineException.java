package org.carpark.paymentmachine;

/**
 * Thrown by PaymentMachines.
 * 
 * @author Charles Boyle & Sian Turner
 * @version 12/04/05
 * @see java.lang.Exception
 */

public class PaymentMachineException extends Exception {

    /**
     * Message Type Keyword
     */       
     private String type;
    /**
     * Message Content
     */       
     private String message;

    /**
     * Constructor for objects of class PaymentMachineException
     * @param type  the type of the error that has occurred
     */
     public PaymentMachineException(String type) {
         this.type = type;
         setMessage();
     }

     /**
      * Private method to set the message
      */
     private void setMessage() {
		if(type == "paid") message = "This ticket has been paid.";
		else message = "Unknown Payment Machine Exception: " + type;
	}

    /**
     * Accessor method to return the reason for the exception
     */      
     public String getMessage() {
         return message;
     }

}