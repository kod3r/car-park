package org.carpark.paymentmachine;

/**
@filename	PaymentMachineFactory.java
@author		Charles Boyle
@date		01 March 2005
*/

/**
	Factory class for creating Payment Machines.
*/
public class PaymentMachineFactory {

	/**
		The ThreadGroup for Payment Machines.
	*/
	private static ThreadGroup tGroup;

	/**
		Payment Machines counter.Counting Threads in a ThreadGroup is only an estimate.
	*/
	private static int counter = 0;

	/**
		Returns the Payment Machine ThreadGroup.
		@return	the Payment Machine ThreadGroup.
	*/
	public static ThreadGroup getThreadGroup() {
		if (tGroup == null) {
			tGroup = new ThreadGroup("PaymentMachines");
		}
		return tGroup;
	}

	/**
		Creates a new instance of Payment Machine in the Factory ThreadGroup, and returns it.
		@return	a new instance of Payment Machine.
	*/
	public static PaymentMachine getNewPaymentMachine() {
		counter++;
		PaymentMachine pm = new PaymentMachine(getThreadGroup(), "PaymentMachine " + counter);
		pm.start();
		return pm;
	}

	/**
		Method for accessing a count of active Payment Machines.
		@return	an estimate of the number of active Payment Machine threads.
	*/
	public static int getPaymentMachineCount() {
		return counter;
	}

}