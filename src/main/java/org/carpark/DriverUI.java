package org.carpark;

import org.carpark.barrier.BarrierException;
import org.carpark.carpark.CarPark;
import org.carpark.carpark.CarParkCharge;
import org.carpark.paymentmachine.PaymentMachineException;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

/**
	Graphical User Interface support for the Driver class.
*/
public class DriverUI extends JFrame {

	/**
		An instance of the Driver.
	*/
	private static Driver driver;

	/**
		The Driver User Interface.
	*/
	public DriverUI(Driver driver) {
		super("Car Park Project");
		this.driver = driver;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BorderLayout());
		contentPanel.add(initTopButtons(), BorderLayout.NORTH);
		contentPanel.add(initTabs(), BorderLayout.CENTER);
		contentPanel.add(initBottomButtons(), BorderLayout.SOUTH);
		getContentPane().add(contentPanel);
		pack();
	}

	/**
		Initialise Top Button Panel Interface Component.
	*/
	private JPanel initTopButtons() {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(2, 1));

		JPanel entryButtonPanel = new JPanel();
		entryButtonPanel.setLayout(new GridLayout(1, 3));
		entryButtonPanel.setBorder(new TitledBorder("Entry"));

		JButton requestTicketButton = new JButton("Request Ticket");
		requestTicketButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionevent) {
				try {
					driver.getEntryBarrier().requestTicket();
				} catch (BarrierException e) {
					MessageDialog("Entry Barrier", e.getMessage());
				}
			}
		});
		entryButtonPanel.add(requestTicketButton);

		JButton removeTicketButton = new JButton("Take Ticket");
		removeTicketButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionevent) {
				try {
					driver.getEntryBarrier().removeTicket();
				} catch (BarrierException e) {
					MessageDialog("Entry Barrier", e.getMessage());
				}
			}
		});
		entryButtonPanel.add(removeTicketButton);

		JButton passBarrierButton = new JButton("Enter Car Park");
		passBarrierButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionevent) {
				try {
					driver.getEntryBarrier().passBarrier();
				} catch (BarrierException e) {
					MessageDialog("Entry Barrier", e.getMessage());
				}
			}
		});
		entryButtonPanel.add(passBarrierButton);

		buttonPanel.add(entryButtonPanel);

		JPanel exitButtonPanel = new JPanel();
		exitButtonPanel.setLayout(new GridLayout(1, 3));
		exitButtonPanel.setBorder(new TitledBorder("Exit"));

		JButton payTicketButton = new JButton("Pay Ticket");
		payTicketButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionevent) {
				if (driver.getTicket() == null) {
					MessageDialog("Transaction", "No active transactions.");
				} else {
					try {
						CentralComputer.getInstance().getPaymentMachinesList().lastElement().insertTicket(driver.getTicket());
					} catch (PaymentMachineException e) {
						MessageDialog("Payment Machine", e.getMessage());
					}
				}
			}
		});
		exitButtonPanel.add(payTicketButton);

		JButton insertTicketButton = new JButton("Insert Ticket");
		insertTicketButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionevent) {
				if (driver.getTicket() == null) {
					MessageDialog("Transaction", "No active transactions.");
				} else {
					try {
						driver.getExitBarrier().insertTicket(driver.getTicket());
					} catch (BarrierException e) {
						MessageDialog("Exit Barrier", e.getMessage());
					}
				}
			}
		});
		exitButtonPanel.add(insertTicketButton);

		JButton exitBarrierButton = new JButton("Exit Car Park");
		exitBarrierButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionevent) {
				try {
					driver.getExitBarrier().passBarrier();
				} catch (BarrierException e) {
					MessageDialog("Exit Barrier", e.getMessage());
				}
			}
		});
		exitButtonPanel.add(exitBarrierButton);

		buttonPanel.add(exitButtonPanel);

		return buttonPanel;
	}

	/**
		Initialise Buttom Button Panel Interface Component.
	*/
	private JPanel initBottomButtons() {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1, 4));
		buttonPanel.setBorder(new TitledBorder("Data & Statistics"));
		JButton motoristRecordsButton = new JButton("Motorist Records");
		motoristRecordsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionevent) {
				System.out.println("Motorist Records");
				CentralComputer.getStore().saveRecords(CentralComputer.getInstance().getMotoristRecords());
			}
		});
		buttonPanel.add(motoristRecordsButton);

		JButton spaceAvailabilityTableButton = new JButton("Space Availability Table");
		spaceAvailabilityTableButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionevent) {
				CentralComputer.getStore().saveSpaceTable(CentralComputer.getInstance().getSpaceAvailabilityTable());
			}
		});
		buttonPanel.add(spaceAvailabilityTableButton);

		JButton transactionArchiveButton = new JButton("Transaction Archive");
		transactionArchiveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionevent) {
				CentralComputer.getStore().saveArchive(CentralComputer.getInstance().getTransactionArchive());
			}
		});
		buttonPanel.add(transactionArchiveButton);

		JButton viewLogButton = new JButton("Print Log");
		viewLogButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionevent) {
				System.out.println(CentralComputer.getInstance());
			}
		});
		buttonPanel.add(viewLogButton);

		return buttonPanel;
	}

	/**
		Initialise Tabbed Pane Interface Component.
	*/
	private JTabbedPane initTabs() {
		JTabbedPane tabbedPane = new JTabbedPane();
		javax.swing.ImageIcon icon = null;

		// Car Parks
		JScrollPane carparksPanel = new JScrollPane();
		carparksPanel.setBorder(new TitledBorder("Car Parks"));
		Vector carParks = new Vector();
		Hashtable<Integer, CarPark> carParksList = CentralComputer.getInstance().getCarParksList();
		Enumeration<Integer> idEnum = carParksList.keys();
		while(idEnum.hasMoreElements()) {
			Integer id = idEnum.nextElement();
			carParks.addElement("ID: " + id + " - " + carParksList.get(id));
		}
		JList carparksList = new JList(carParks);
		carparksPanel.setViewportView(carparksList);
		tabbedPane.addTab("Car Parks", icon, carparksPanel,
						 "Car Park List");

		// Car Park Charges
		JPanel chargesPanel = new JPanel();
		chargesPanel.setBorder(new TitledBorder("Car Park Pricing"));
		chargesPanel.setLayout(new GridLayout(12, 2));
		CarParkCharge chargeData = CentralComputer.getInstance().getCarParkCharge();
		chargesPanel.add(new JLabel("<html><u>Up to:</u></html>"));
		chargesPanel.add(new JLabel("<html><u>Cost:</u></html>"));
		chargesPanel.add(new JLabel("10 mins"));
		chargesPanel.add(new JLabel(new Integer(chargeData.getUpTo10min()).toString()));
		chargesPanel.add(new JLabel("1 hour"));
		chargesPanel.add(new JLabel(new Integer(chargeData.getUpTo1hour()).toString()));
		chargesPanel.add(new JLabel("2 hours"));
		chargesPanel.add(new JLabel(new Integer(chargeData.getUpTo2hours()).toString()));
		chargesPanel.add(new JLabel("3 hours"));
		chargesPanel.add(new JLabel(new Integer(chargeData.getUpTo3hours()).toString()));
		chargesPanel.add(new JLabel("4 hours"));
		chargesPanel.add(new JLabel(new Integer(chargeData.getUpTo4hours()).toString()));
		chargesPanel.add(new JLabel("5 hours"));
		chargesPanel.add(new JLabel(new Integer(chargeData.getUpTo5hours()).toString()));
		chargesPanel.add(new JLabel("6 hours"));
		chargesPanel.add(new JLabel(new Integer(chargeData.getUpTo6hours()).toString()));
		chargesPanel.add(new JLabel("12 hours"));
		chargesPanel.add(new JLabel(new Integer(chargeData.getUpTo12hours()).toString()));
		chargesPanel.add(new JLabel("18 hours"));
		chargesPanel.add(new JLabel(new Integer(chargeData.getUpTo18hours()).toString()));
		chargesPanel.add(new JLabel("24 hours"));
		chargesPanel.add(new JLabel(new Integer(chargeData.getUpTo24hours()).toString()));
		chargesPanel.add(new JLabel("Per day"));
		chargesPanel.add(new JLabel(new Integer(chargeData.getPerDay()).toString()));
		tabbedPane.addTab("Charges", icon, chargesPanel,
						 "Car Park Charge Details");

		// Direction Signs
		JScrollPane signsPanel = new JScrollPane();
		signsPanel.setBorder(new TitledBorder("Direction Sign Displays"));
		JList signsList = new JList(CentralComputer.getInstance().getDirectionSignList());
		signsPanel.setViewportView(signsList);
		tabbedPane.addTab("Direction Signs", icon, signsPanel,
						"Direction Sign Displays");

		return tabbedPane;
	}

	/**
		A utility method for displaying message dialogs.
	*/
	private void MessageDialog(String title, String label) {
		JOptionPane.showMessageDialog(null, label, title , 2);
	}

}