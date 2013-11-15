package org.carpark;

/**
 * Count from zero to Maximum Integer
 * @author Marjan Rahimi
 * @version March 2005
 */
public class Counter {
	long count;
	
	/**
	 * Construct a counter initilized with zero
	 */
	public Counter(){
		count = 0;
	}

	/**
	 * Add one to the counter
	 * @throws ArithmeticException
	 */
	public void increment()throws ArithmeticException{
		if (count == Integer.MAX_VALUE  )
			throw new ArithmeticException("Over Flow");
		count++;
	}
	/**
	 * Decrease the counter by one
	 * @throws ArithmeticException
	 */
	public void decrement()throws ArithmeticException{
		if(count == 0)
			throw new ArithmeticException("Under Flow");
		count--;
	}
	/**
	 * @return the total
	 */
	public long getTotal(){
		return count;
	}

	/** 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object anObject) {
		if(anObject instanceof Counter){
			Counter theCounter = (Counter)anObject;
			return count == theCounter.count;
		}
		return false;
	}

}
