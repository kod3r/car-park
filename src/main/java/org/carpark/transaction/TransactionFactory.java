package org.carpark.transaction;

/**
 A Car Park Transaction.

 @author Ross Huggett
*/

public class TransactionFactory {
	/**
	 The ThreadGroup for Transaction Factory.
	*/
	private static ThreadGroup tGroup;

	/**
	 Transaction counter.Counting Threads in a ThreadGroup is only an estimate.
	*/
	private static int counter = 0;

	/**
	 Returns the Transaction ThreadGroup.
	 @return	the Transaction ThreadGroup.
	*/
	public static ThreadGroup getThreadGroup() {
		if (tGroup == null) {
			tGroup = new ThreadGroup("Transactions");
		}
		return tGroup;
	}

	/**
	 Creates a new instance of Transaction in the Factory ThreadGroup, and returns it.
	 @return	a new instance of Transaction.
	*/
	public static Transaction getNewTransaction() {
		counter++;
		Transaction t = new Transaction(getThreadGroup(), "Transaction " + counter);
		t.start();
		return t;
	}

	/**
	 Method for accessing a count of active Transaction.
	 @return an estimate of the number of active Transaction threads.
	*/
	public static int getTransactionCount() {
		return counter;
	}
}
