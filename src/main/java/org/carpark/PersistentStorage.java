package org.carpark;

/**
@filename	PersistentStorage.java
@author		Charles Boyle
@date		01 March 2005
*/

import org.carpark.carpark.CarParkSpaces;
import org.carpark.transaction.Transaction;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

/**
	Persistent Storage Object.
*/
public class PersistentStorage {

	/**
		The DBMS account URL.
	*/
	private final static String dbmsUrl = "jdbc:oracle:thin:@hermes:1522:main";
	/**
		The DBMS account login username.
	*/
	private final static String dbmsLogin = "oopftproj1";
	/**
		The DBMS account password.
	*/
	private final static String dbmsPassword = "msc2004";

	/**
		Whether the Oracle Driver has been loaded successfully.
	*/
	private boolean oracleSupport = false;

	/**
		Default Constructor, loads JDBC Class for Oracle.
	*/
	public PersistentStorage() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			oracleSupport = true;
		} catch (ClassNotFoundException e) {
			System.out.println("Oracle JDBC driver not found in classpath.");
		}
	}

	/**
		Save Motorists Records.
		@param motoristRecords Usage statistics.
	*/
	public void saveRecords(Hashtable<Integer, Counter> motoristRecords) {
		System.out.println("Unparsed Motorist Records:");
		Enumeration<Integer> idEnum = motoristRecords.keys();
		while(idEnum.hasMoreElements()) {
			Integer id = idEnum.nextElement();
			System.out.println(id + " " + motoristRecords.get(id).getTotal());
		}
	}

	/**
		Save Transaction Archive.
		@param transactionArchive Transaction history.
	*/
	public void saveArchive(Vector<Transaction> transactionArchive) {
		if (oracleSupport) {
			uploadArchive(transactionArchive);
			return;
		}
		SimpleDateFormat aFormatter = new SimpleDateFormat("dd MMM yyyy, HH:mm");
		SimpleDateFormat bFormatter = new SimpleDateFormat("HH:mm");
		for(Transaction t: transactionArchive){
			System.out.println(t.getTransaction() + ": Car Park " + t.getCarPark() +
								": Arrived: " + aFormatter.format(t.getArrivalTime()) + 
								", Stayed: " + bFormatter.format(t.getStayTime()) +
								" " + (t.getPaymentStatus()?"Paid":"Unpaid") + ", " + 
								(t.isUsed()?"Used":"Unused"));
		}
	}

	/**
		Upload Transaction Archive to DBMS.
		@param transactionArchive Transaction history.
	*/
	public void uploadArchive(Vector<Transaction> transactionArchive) {
		try {
			Connection dbms = DriverManager.getConnection(dbmsUrl, dbmsLogin, dbmsPassword);
			Statement statement = dbms.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet result = statement.executeQuery("SELECT * FROM TRANSACTIONS");
			for(Transaction t: transactionArchive) {
				result.moveToInsertRow();
				result.updateInt("TRANS_ID", t.getTransaction());
				result.updateInt("CAR_PARK_ID", t.getCarPark());
				result.updateTimestamp("ARRIVAL", new Timestamp(t.getArrivalTime().getTime()));
				result.updateTime("DURATION", new Time(t.getStayTime().getTime()));
				result.updateBoolean("PAID_STATUS", t.getPaymentStatus());
				result.updateBoolean("USED_STATUS", t.isUsed());
				result.insertRow();
				result.beforeFirst();
			}
			result.close();
			statement.close();
			dbms.close();
		} catch(SQLException e) {
			System.err.println("Exception occurred uploading to DBMS: " + e.getMessage());
		}
	}

	/**
		Save Space Availability Table.
		@param spaceAvailabilityTable Space usage table.
	*/
	public void saveSpaceTable(Hashtable<Date, CarParkSpaces[]> spaceAvailabilityTable) {
		System.out.println("Space Availability Table");
		Enumeration<Date> dateEnum = spaceAvailabilityTable.keys();
		while(dateEnum.hasMoreElements()) {
			Date timestamp = dateEnum.nextElement();
			SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy, HH:mm");		
			System.out.println(formatter.format(timestamp) + " : ");
			CarParkSpaces[] spaceTable = spaceAvailabilityTable.get(timestamp);
			for (int i=0; i < spaceTable.length ; i++) {
				System.out.println("Car Park ID: " + spaceTable[i].getId() + " - " + spaceTable[i].getNumSpaces() + " free spaces.");
			}
		}
	}

}