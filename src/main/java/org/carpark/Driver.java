package org.carpark;

import org.carpark.barrier.BarrierFactory;
import org.carpark.barrier.EntryBarrier;
import org.carpark.barrier.ExitBarrier;
import org.carpark.carpark.CarPark;
import org.carpark.carpark.CarParkCharge;
import org.carpark.carpark.CarParkFactory;
import org.carpark.paymentmachine.PaymentMachineFactory;
import org.carpark.transaction.Transaction;

import javax.swing.*;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

/**
	Driver class for interacting with the Car Park components.
*/
public class Driver
{
	/**
		The main method to start the Driver.
	*/
	public static void main(String args[]) {
		new Driver();
	}

	/**
		Vector of entryBarriers, for user interface operation.
	*/
	private static Vector<EntryBarrier> entryBarriers;
	/**
		Vector of exitBarriers, for user interface operation.
	*/
	private static Vector<ExitBarrier> exitBarriers;
	/**
		Most recently used Transaction is cached.
	*/
	private Transaction cachedTransaction;

    /**
     * Driver Constructor.
     */
	public Driver() {
		entryBarriers = new Vector<EntryBarrier>();
		exitBarriers = new Vector<ExitBarrier>();

		CentralComputer computer = new CentralComputer().getInstance();
		initCarParks();
		initPaymentMachines();
		initDirectionSigns();
		computer.setCarParkCharge(new CarParkCharge(10,50,60,70,80,90,100,150,200,250,300));

		new DriverUI(this).show();
	}

    /**
     * Initialise the Car Parks.
     */
	private void initCarParks() {
		int value = InputDialog("Car Parks", "Car Parks", "1");
		int spaces;
		String id;
		for(int i = 0; i < value; i++) {
			id = new Integer(i + 1).toString();
			spaces = InputDialog("Car Park " + id, "No. of Spaces", "4");
			CarPark carpark = CarParkFactory.getNewCarPark(spaces);
			entryBarriers.addElement(BarrierFactory.getNewEntryBarrier("Car Park " + id + " Entry Barrier " + "1"));
			carpark.addEntryBarrier(entryBarriers.lastElement());
			exitBarriers.addElement(BarrierFactory.getNewExitBarrier("Car Park " + id + " Exit Barrier " + "1"));
			carpark.addExitBarrier(exitBarriers.lastElement());
			CentralComputer.getInstance().addCarPark(carpark);
		}
	}

    /**
     * Initialise the Payment Machines.
     */
	private void initPaymentMachines() {
		int value = InputDialog("Payment Machines", "Payment Machines", "1");
		for(int i = 0; i < value; i++) {
			CentralComputer.getInstance().addPaymentMachine(PaymentMachineFactory.getNewPaymentMachine());
		}
	}

    /**
     * Initialise the Location Data.
     */
	private Hashtable<Integer, Location> initLocationData() {
		// Build All Car Park Location Data;
		Hashtable<Integer, Location> locationDataTable = new Hashtable<Integer, Location>();
		Hashtable<Integer, CarPark> carParkTable = CentralComputer.getInstance().getCarParksList();
		Enumeration<Integer> idEnum = carParkTable.keys();
		while(idEnum.hasMoreElements()) {
			Integer id = idEnum.nextElement();
			locationDataTable.put(id, new Location(2, Direction.STRAIGHT));
		}
		return locationDataTable;
	}

    /**
     * Initialise the Direction Signs
	 */
	private void initDirectionSigns() {
		Hashtable<Integer, Location> locationData = initLocationData();
		int value = InputDialog("Direction Signs", "No. of Direction Signs", "1");
		for(int i = 0; i < value; i++) {
			CentralComputer.getInstance().addDirectionSign(new DirectionSign(locationData));
		}
	}

    /**
     * Function for displaying an input dialog for numeric input.
     * @return  the numerical result
     */
	private int InputDialog(String title, String label, String value) {
		boolean valid = false;
		int val = 0;
		while (!valid) {
		String result = (String)JOptionPane.showInputDialog(null, label, title , 3, null, null, value);
			try {
				val = new Integer(result).intValue();
				if (val > 0) valid = true;
			} catch (Exception e) {
				System.out.println("Bad input data");
			}
		};
		return val;
	}

    /**
     * Get an Entry Barrier.Theoretically many can be supported, but here is just the most recent.
     * @return  the time
     */
	public EntryBarrier getEntryBarrier() {
		return entryBarriers.lastElement();
	}

    /**
     * Get an Exit Barrier.Theoretically many can be supported, but here is just the most recent.
     * @return  the time
     */
	public ExitBarrier getExitBarrier() {
		return exitBarriers.lastElement();
	}

    /**
     * Get a ticket.Theoretically many can be supported, but here is just the most recent.
     * @return  the time
     */
	public Transaction getTicket() {
		if (cachedTransaction != null && !cachedTransaction.isUsed()) {
			return cachedTransaction;
		} else {
			cachedTransaction = null;
			for (Transaction t: CentralComputer.getInstance().getTransactionArchive()) {
				if (!t.getPaymentStatus()) {
					cachedTransaction = t;
					return t; 
				}
			}
		}
		return null;
	}

}