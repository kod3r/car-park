package org.carpark;
import org.carpark.carpark.Status;

import java.util.Enumeration;
import java.util.Hashtable;



/**
 * Control the direction sign to direct to the nearest car park with spaces availble.
 * @author Marjan Rahimi
 * @version March 2005
 */
public class DirectionSign
{
    private Hashtable<Integer, Location> geographicData;
    private Direction bestDirection; 

    /**
     * Construct a DirectionSign
     * @param allCarparksLocation a list of all car parks' location
     * @throws IllegalArgumentException if allCarparksLocation table is empty
     */
    public DirectionSign(Hashtable<Integer, Location> allCarparksLocation)throws IllegalArgumentException{
		if(allCarparksLocation.isEmpty())
			throw new IllegalArgumentException("Car park's location list cannot be empty");
		bestDirection = Direction.UNDEFINED;		
		geographicData = allCarparksLocation;	
    }
    
 
    /**
     * Update the sign according to car parks status.
     * @param allCarparksStatus a hashtable of car parks status
     * @throws NullPointerException 
     * @throws IllegalArgumentException if allCarparksStatus table is empty 
     * 			or a car park cannot be found in the table
     */
    public void updateSign(Hashtable<Integer, Status> allCarparksStatus)throws NullPointerException ,IllegalArgumentException{
		if(allCarparksStatus.isEmpty())
			throw new IllegalArgumentException("Car parks status list cannot be empty");
		
		// find the nearest car park with available spaces
		Integer shortestDistance = Integer.MAX_VALUE;
        Direction sign = Direction.UNDEFINED;
		Enumeration<Integer> carparkIds = geographicData.keys();
		while (carparkIds.hasMoreElements()){
			Integer id = carparkIds.nextElement();
			Location carparkLocation = geographicData.get(id);
			Status carparkState = allCarparksStatus.get(id);
			if(carparkState == null) 
				throw new IllegalArgumentException("Car park id (" + id + ") was not found in the car parks status list");
			Integer theDistance = carparkLocation.getDistance();
            if(carparkState == Status.SPACES &&  theDistance < shortestDistance){
                shortestDistance = theDistance;
                sign = carparkLocation.getDirection();
            }
		}
		bestDirection = sign;		   
    }

	
	/**
	 * Reset the information about the location and direction of car parks
	 * and turn off the direction sign.
	 * @param allCarparksLocation a vector of the location of all car parks
	 * @throws IllegalArgumentException if allCarparksLocation table is empty
	 */
	public void resetGeographicData(Hashtable<Integer, Location> allCarparksLocation)throws IllegalArgumentException{
		if(allCarparksLocation.isEmpty())
			throw new IllegalArgumentException("Car park's location list cannot be empty");
       bestDirection = Direction.UNDEFINED;
	   geographicData = allCarparksLocation;      
   }

	/**
	 * @return Returns the Direction of the nearest car park.
	 */
	public Direction getBestDirection() {
		return bestDirection;
	}


	/** 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return bestDirection.toString();
	}
   
}//class DirectionSign
