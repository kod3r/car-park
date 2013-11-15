package org.carpark.carpark;

/**
 Thrown by CarPark.
  
 @author Ross Huggett 
*/

public class CarParkException extends Exception {
	/**
	 Message Type Keyword.
	*/       
	private String type;
    
	/**
	 Message Content.
	*/       
    	private String message;

	/**
	 Constructor for objects of class CarParkException.
	 @param type  the type of the error that has occurred
	*/
    	public CarParkException(String type) {
		this.type = type;
		setMessage();
	}

	/**
	 Private method to set the message.
	*/
	private void setMessage() {
		if(type == "full") message = "The carpark is full.";
		else message = "Unknown CarPark Exception: " + type;
	}

	/**
	 Accessor method to return the reason for the exception.
	*/      
	public String getMessage() {
		return message;
	}
}
