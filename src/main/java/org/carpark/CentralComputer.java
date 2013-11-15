package org.carpark;

import org.carpark.carpark.CarPark;
import org.carpark.carpark.CarParkCharge;
import org.carpark.carpark.CarParkSpaces;
import org.carpark.carpark.Status;
import org.carpark.paymentmachine.PaymentMachine;
import org.carpark.transaction.Transaction;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * CentralComputer controls Direction Signs
 * updates Payment Machines
 * maintains Transaction Database
 * monitors Car Parks
 * @author Marjan Rahimi
 * @version March 2005
 */
public class CentralComputer {

	//instance of the CentralComputer class -- Singleton
	private static CentralComputer instance = new CentralComputer();

	private static PersistentStorage database;// added by Charles Boyle

	private Hashtable<Date, CarParkSpaces[]> spaceAvailabilityTable;
	private Vector<DirectionSign> directionSignList;
	private Hashtable<Integer, CarPark> carParksList;
	private CarParkCharge carParkCharge;
	private Vector<Transaction> transactionArchive;
	private Hashtable<Integer, Counter> motoristRecords;
	private Vector<PaymentMachine> paymentMachinesList;
	
	private String log;

	/**
	 * @return an instance of the Central Computer
	 */
	public static CentralComputer getInstance(){
		return instance;
	}

	/**
	 * added by Charles Boyle
	 * @return an instance of the Persistent Storage 
	 */
	public static PersistentStorage getStore(){
		return database;
	}

	/**
	 * Construct the Central Computer
	 */
	public CentralComputer(){
		//Initialize Persistent Storage Database  
		database = new PersistentStorage();// added by Charles Boyle - jboyl02

		//Initialize fields
		carParksList = new Hashtable<Integer, CarPark>();
		directionSignList = new Vector<DirectionSign>();
		spaceAvailabilityTable = new Hashtable<Date, CarParkSpaces[]>();
		transactionArchive = new Vector<Transaction>();
		CarParkCharge cpc = new CarParkCharge(50,50,50,50,50,50,50,50,50,50,50);
		setCarParkCharge(cpc);
		motoristRecords = new Hashtable<Integer, Counter>();
		paymentMachinesList = new Vector<PaymentMachine>();
		updateLog("Central Computer was initialized.");
		//Schedule Background threads
		final int MILISECONS = 1000;
		final int SECONDS = 60;
		final int MINUTES = 60;
		final boolean DAEMON = true;

		long delay = 0;
//		long interval = MINUTES * SECONDS * MILISECONS;//1 hour
		long interval = 5 * MILISECONS;// 15 seconds
		new Timer(DAEMON).scheduleAtFixedRate(new RecordSpacesTask() ,delay , interval);

		delay = 0;
		interval = 12 * MINUTES * SECONDS * MILISECONS;//12 hours
		new Timer(DAEMON).scheduleAtFixedRate(new ClockUpdateTask() ,delay , interval);

		delay = 0;
//		interval = 5 * MINUTES * SECONDS * MILISECONS;// 5 hours
		interval = 10 * MILISECONS;// 5 seconds
		new Timer(DAEMON).scheduleAtFixedRate(new TransactionAccumulationTask() ,delay , interval);

		delay = 0;
//		interval = 5 * SECONDS * MILISECONS;//5 minutes
		interval = 10 * MILISECONS;// 10 seconds
		new Timer(DAEMON).scheduleAtFixedRate(new UpdateSignTask(),delay, interval);
	}


	/**
	 * Add a car park to the Central Computer
	 * @param aCarpark to be added
	 * @throws NullPointerException if aCarPark is null
	 */
	public void addCarPark(CarPark aCarpark)throws NullPointerException{
		if (aCarpark == null)
			throw new NullPointerException("CarPark is null and cannot be added to the Central Computer");
		int carParkId = carParksList.size() + 1;
		carParksList.put(carParkId,aCarpark);
		updateLog("New car park (Id = " + carParkId + ") was added to the central computer. Total number of car parks = " + carParksList.size());
	}

	/**
	 * Add a direction sign to the Central Computer
	 * @param aDirectionSign to be added
	 * @throws NullPointerException if aDirectionSign is null
	 */
	public void addDirectionSign(DirectionSign aDirectionSign)throws NullPointerException {
		if (aDirectionSign == null)
			throw new NullPointerException("DirectionSign is null and cannot be added to the Central Computer");
		directionSignList.add(aDirectionSign);
		updateLog("New direction sign was added to the central computer. Total number of direction signs = " + directionSignList.size());
	}

	/**
	 * Add a payment machine to the Central Computer
	 * @param aPaymentMachine to be added
	 * @throws NullPointerException if aPaymentMachine is null
	 */
	public void addPaymentMachine(PaymentMachine aPaymentMachine)throws NullPointerException{
		if (aPaymentMachine == null)
			throw new NullPointerException("PaymentMachine is null and cannot be added to the Central Computer");
		paymentMachinesList.add(aPaymentMachine);
		updateLog("New payment machine was added to the central computer. Total number of payment machines = " + paymentMachinesList.size());
	}

	/**
	 * A task that records space availability of the car parks
	 */
	class RecordSpacesTask extends TimerTask {
        /**
         * @see java.lang.Runnable#run()
         */
		public void run() {
            Date today = new Date();

			//Make an array of the spaces available in each car park
			CarParkSpaces[] carParksSpaces = new CarParkSpaces[carParksList.size()];
			Enumeration<Integer> carParkIds = carParksList.keys();
			for(int i = 0; carParkIds.hasMoreElements(); i++) {
				Integer id = carParkIds.nextElement();
				carParksSpaces[i] = new CarParkSpaces(id, carParksList.get(id).getSpaces());
			}
			//Add the carParksSpaces with current date and time
			//to the space availability table
			spaceAvailabilityTable.put(today, carParksSpaces);
			updateLog("Space Availability Table was updated.");
        }
	}//class RecordSpacesTask

	
	
	/**
	 * A task that updates Direction Signs
	 */
	class UpdateSignTask extends TimerTask {
		/**
		 * @see java.lang.Runnable#run()
		 */
		public void run() {

			//make a list of Carparks Status
			Enumeration<Integer> carParkIds = carParksList.keys();
			Hashtable<Integer, Status> carParkStatusList = new Hashtable<Integer, Status>();
			while(carParkIds.hasMoreElements()){
				Integer id = carParkIds.nextElement();
				CarPark  carPark = carParksList.get(id);
				Status status;
				if (carPark.isFull())
					status = Status.FULL;
				else
					status = Status.SPACES ;
				carParkStatusList.put(id, status);
			}
			
			//update all direction signs with the carParkStatusList
			for(DirectionSign dSign: directionSignList){
				dSign.updateSign(carParkStatusList);
			}
			updateLog("Direction Signs were updated.");
		}
	}//class UpdateSignTask

	/**
	 * A task that Updates the clock of the car parks and the payment machines
	 */
	class ClockUpdateTask extends TimerTask {
        /**
         * @see java.lang.Runnable#run()
         */
		public void run() {
            Date now = new Date();
			
			//update the car parks
			Enumeration<Integer> carParkIds = carParksList.keys();
			while(carParkIds.hasMoreElements()){
				Integer id = carParkIds.nextElement();
				carParksList.get(id).setClock(now);
			}
			updateLog("Car parks' clock were updated.");
			//update the payment machines
			for(int i = 0; i < paymentMachinesList.size(); i++){
				paymentMachinesList.get(i).setClock(now);
			}
			updateLog("Payment machines' clock were updated.");
        }
	}//class ClockUpdateTask

	/**
	 * A task that accumulate all transactions from car parks
	 * and records the number of motorist parking for one hour, two hours, three hours, etc.
	 */
	class TransactionAccumulationTask extends TimerTask {
        /**
         * @see java.lang.Runnable#run()
         */
		public void run() {
			Enumeration<Integer> carParkIds = carParksList.keys();
			while(carParkIds.hasMoreElements()){
				Integer id = carParkIds.nextElement();
				Vector<Transaction> transactions = carParksList.get(id).getTransactions();
				transactionArchive.addAll(transactions);
				updateNumberOfMotorist(transactions);
			}
			updateLog("Transaction archive was updated.");
        }
		
		/**
		 * Add number of motorist parking for specific hours
		 * @param transactions a list of transactions
		 */
		private void updateNumberOfMotorist(Vector<Transaction> transactions){
			for(Transaction t: transactions){
				int stayHours = t.getStayTime().getHours();
				if (motoristRecords.contains(stayHours))
					motoristRecords.get(stayHours).increment();
				else
					motoristRecords.put(stayHours,new Counter());
			}
		}
	}//class ClockUpdateTask

	/**
	 * A thread that update car park charges 
	 */
	class UpdatePaymentMachinesThread extends Thread{
		/**
		 * @see java.lang.Runnable#run()
		 */
		public void run(){
			for(PaymentMachine paymentMachine: paymentMachinesList)
				paymentMachine.setTariffs(carParkCharge);
			updateLog("Payment machines' tariff were updated.");
		}
		
	}

	/**
	 * @param carParkCharge The car park charge to set.
	 * @throws NullPointerException if carParkCharge is null
	 */
	public synchronized void  setCarParkCharge(CarParkCharge carParkCharge)throws NullPointerException {
		if(carParkCharge == null)
			throw new NullPointerException("carParkCharge is null");
		this.carParkCharge = carParkCharge;
		new UpdatePaymentMachinesThread().start();
	}

	/**
	 * @return Returns the car park charge.
	 */
	public synchronized CarParkCharge getCarParkCharge() {
		return carParkCharge;
	}

	/**
	 * @return Returns the carParksList.
	 */
	public Hashtable<Integer, CarPark> getCarParksList() {
		return (Hashtable<Integer, CarPark>)carParksList.clone();
	}

	/**
	 * @return Returns the directionSignList.
	 */
	public Vector<DirectionSign> getDirectionSignList() {
		return (Vector<DirectionSign>)directionSignList.clone();
	}

	/**
	 * @return Returns the paymentMachinesList.
	 */
	public Vector<PaymentMachine> getPaymentMachinesList() {
		return (Vector<PaymentMachine>) paymentMachinesList.clone();
	}
	
	/**
	 * @return Returns and empty the motoristRecords.
	 */
	public Hashtable<Integer, Counter> getMotoristRecords() {
		Hashtable<Integer, Counter> mrTable = (Hashtable<Integer, Counter>) motoristRecords.clone();
		motoristRecords.clear();
		updateLog("Motorist records table was cleared.");
		return mrTable;
	}

	/**
	 * @return Returns and empty the spaceAvailabilityTable.
	 */
	public Hashtable<Date, CarParkSpaces[]> getSpaceAvailabilityTable() {
		Hashtable<Date, CarParkSpaces[]> saTable = (Hashtable<Date, CarParkSpaces[]>)spaceAvailabilityTable.clone();
		spaceAvailabilityTable.clear();
		updateLog("Space availability table is cleared.");
		return saTable;
	}

	/**
	 * @return Returns and empty the transactionArchive.
	 */
	public Vector<Transaction> getTransactionArchive() {
		return transactionArchive;
	}

	/**
	 * Add message with time stamp to the log
	 * @param message
	 */
	private void updateLog(String message){
		if (log == null)
			log = new String();
		Date today = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy, HH:mm");		
		log = log + formatter.format(today) + " - " + message + "\n";
	}

	/** 
	 * Return log information
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return log;
	}

}//class Central Computer