package org.carpark.barrier;
/**
 * Class to display a sign at an Entry Barrier.
 * 
 * @author Sian Turner 
 * @version 12/04/05
 */
public class EntryBarrierSign {

    private String message;

    /**
     * Constructor for objects of class EntryBarrierSign
     * @param newMessage the message to be displayed on the sign
     */
    public EntryBarrierSign(String newMessage) {
        message = newMessage;
    }

    /**
     * Procedure to set the message the sign displays.
     * @param newMessage the message to be displayed on the sign
     */

    public void setMessage(Object newMessage) {
        message = newMessage.toString();
    }
    
    /**
     * Accessor to return the message currently held by the sign;
     * @return message
     */
    
    public String getMessage() {
        return message;
    }
    
    /**
     * Procedure to illuminate the sign.
     * Prints a message.
     */
    
    public void illuminate() {
        System.out.println(message);
    }
}
