package com.aston.group24.model;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/*
 * GUI class for the simulation
 * 
 * @version 0.0.1
 */
public class FuelStationSimGUI {
	
	private JFrame mainFrame;
	private JTextArea log;
	private Simulation s;
	
	public FuelStationSimGUI()
	{
		
		// Step 1: create the components
		JButton runButton = new JButton();
		
		JTextField pumpsNumInput = new JTextField("Numer of Pumps");
		JTextField tillsNumInput = new JTextField("Number of Tills");
		
		log = new JTextArea();
		log.setEditable(false);
		JScrollPane listScroller = new JScrollPane(log);
		listScroller.setPreferredSize(new Dimension(300, 300));
		listScroller.setMinimumSize(new Dimension(200,200));
		
		// Step 2: Set the properties of the components
		runButton.setText("Run");
		runButton.setToolTipText("Run the Simulation");
				
		// Step 3: Create containers to hold the components
		mainFrame = new JFrame("DOME");
		mainFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
		JPanel commandBox = new JPanel();
		JPanel inputBox = new JPanel();
		JPanel dataBox = new JPanel();
				
		// Step 4: Specify LayoutManagers
		
		mainFrame.setLayout(new BorderLayout());
		
		dataBox.setLayout(new BorderLayout());
		inputBox.setLayout(new FlowLayout());
		commandBox.setLayout(new FlowLayout());
				
		// Step 5: Add components to containers
		inputBox.add(pumpsNumInput);
		inputBox.add(tillsNumInput);
		
		commandBox.add(runButton);
		
		dataBox.add(inputBox, BorderLayout.NORTH);
		dataBox.add(listScroller, BorderLayout.SOUTH);
		
		mainFrame.add(dataBox, BorderLayout.NORTH);
		mainFrame.add(commandBox, BorderLayout.SOUTH);
				
		// Step 6: Arrange to handle events in the user interface
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				exitApp();
			}
		});
				
		// Step 7: Display the GUI
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
	
	/**
	 * Helper method to ensure consistency in leaving application.
	 */
	private void exitApp() {
		// Display confirmation dialog before exiting application
		int response = JOptionPane.showConfirmDialog(mainFrame, 
				"Do you really want to quit?",
				"Quit?",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if (response == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
		
		// Don't quit
	}

}
