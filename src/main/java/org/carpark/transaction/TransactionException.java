package org.carpark.transaction;

/**
 Thrown by Transaction.
 
 @author Ross Huggett 
*/

public class TransactionException extends Exception {
	/**
	 Message Type Keyword
	*/       
	private String type;
    
	/**
	 Message Content
	*/       
	private String message;

	/**
	 Constructor for objects of class TransactionException
	 @param type the type of the error that has occurred
	*/
	public TransactionException(String type) {
		this.type = type;
		setMessage();
	}

	/**
	 Private method to set the message
	*/
	private void setMessage() {
		message = "Unknown Trasaction Exception" + type;
	}

	/**
	 Accessor method to return the reason for the exception
	*/      
	public String getMessage() {
		return message;
	}
}
