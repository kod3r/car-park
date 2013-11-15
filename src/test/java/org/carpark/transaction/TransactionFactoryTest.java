package org.carpark.transaction;

/**
 A Car Park Transaction.

 @author Ross Huggett
*/

public class TransactionFactoryTest extends junit.framework.TestCase {
	/**
	 Default constructor.
	*/
	public TransactionFactoryTest() {
	}

	/**
	 Set up test fixture.
	*/
	protected void setUp() {
	}

	/**
	 Tear down test fixture.
	*/
	protected void tearDown() {
	}

	public void testGetThreadGroup() {
		assertNotNull(TransactionFactory.getThreadGroup());
	}

	public void testGetNewTransaction() {
		assertNotNull(TransactionFactory.getNewTransaction());
	}
	
	public void testGetTransactionCount() {
		Integer i = new Integer(TransactionFactory.getTransactionCount());
		assertNotNull(i);
	}
}
