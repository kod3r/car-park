package org.carpark.paymentmachine;

import org.carpark.transaction.Transaction;
import org.carpark.carpark.CarParkCharge;

import java.util.Date;

/**
@filename	PaymentMachine.java
@author		Charles Boyle
@userid		jboyl02
@date		01 March 2005
*/

/**
	Payment Machine Object
*/
public class PaymentMachine extends Thread {

	/**
		The Car Park tariff aata.
	*/
	private CarParkCharge tariffData;
	/**
		Last Clock Syncronisation.
	*/
	private Date lastSync;

	/**
		Constructor
	*/
	public PaymentMachine(ThreadGroup tg, String name) {
		super(tg, name);
	}

	/**
		Procedure for ticket insertion event notification
		@throws PaymentMachineException presenting a ticket that has already been paid
	*/
	public void insertTicket(Transaction t) throws PaymentMachineException{
		if (t.getPaymentStatus()) throw new PaymentMachineException("paid");
		int duration = new Date(new Date().getTime() - t.getArrivalTime().getTime()).getMinutes();
		System.out.println(calculateCost(duration));
		t.pay();
	}

	/**
		Procedure for calculating the cost of a stay.
		@param duration Time of Stay in minutes
		@return Cost of stay in pence
	*/
	public int calculateCost(int duration) {
		int cost = 0;
		if (duration <= 10) {
			cost = tariffData.getUpTo10min();
		} else if (duration <= 60) {
			cost = tariffData.getUpTo1hour();
		} else if (duration <= 120) {
			cost = tariffData.getUpTo2hours();
		} else if (duration <= 180) {
			cost = tariffData.getUpTo3hours();
		} else if (duration <= 240) {
			cost = tariffData.getUpTo4hours();
		} else if (duration <= 300) {
			cost = tariffData.getUpTo5hours();
		} else if (duration <= 360) {
			cost = tariffData.getUpTo6hours();
		} else if (duration <= 720) {
			cost = tariffData.getUpTo12hours();
		} else if (duration <= 1080) {
			cost = tariffData.getUpTo18hours();
		} else if (duration <= 1440) {
			cost = tariffData.getUpTo24hours();
		} else while (duration > 0) {
			cost += tariffData.getPerDay();
			duration -= 1440;
		}
		return cost;
	}

	/**
		Mutator method for setting the time
		@param	time	The time
	*/
	public void setClock(java.util.Date time) {
		lastSync = time;
	}

	/**
		Mutator method for setting tariff data
		@param	tariffData	The tariff data object
	*/
	public void setTariffs(CarParkCharge tariffData) {
		this.tariffData = tariffData;
	}

	/**
		Run method for Thread support
	*/
	public void run() {
		//
	}

}