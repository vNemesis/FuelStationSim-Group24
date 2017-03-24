package com.aston.group24.model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	@SuppressWarnings("unused")
	private Simulation s;
	
	public FuelStationSimGUI(Simulation s)
	{
		this.s = s;
		
		// Step 1: create the components
		JButton runButton = new JButton();
		JButton exitButton = new JButton();
		
		JTextField pumpsNumInput = new JTextField("3");
		JTextField tillsNumInput = new JTextField("2");
		JTextField ticksNumInput = new JTextField("2");
		
		JLabel pumpsLabel = new JLabel("Number of pumps");
		JLabel tillsLabel = new JLabel("Number of Tills");
		JLabel ticksLabel = new JLabel("Number of Ticks to run Simulation 1 tick = 10 seconds");
		JLabel logLabel = new JLabel("Simulation Log");
		
		log = new JTextArea();
		log.setEditable(false);
		JScrollPane listScroller = new JScrollPane(log);
		listScroller.setPreferredSize(new Dimension(300, 300));
		listScroller.setMinimumSize(new Dimension(200,200));
		
		// Step 2: Set the properties of the components
		runButton.setText("Run");
		runButton.setToolTipText("Run the Simulation");
		
		exitButton.setText("Exit");
		exitButton.setToolTipText("Exit Program");
				
		// Step 3: Create containers to hold the components
		mainFrame = new JFrame("DOME");
		mainFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
		JPanel commandBox = new JPanel();
		JPanel inputBox = new JPanel();
		JPanel dataBox = new JPanel();
		JPanel logBox = new JPanel();
				
		// Step 4: Specify LayoutManagers
		
		mainFrame.setLayout(new BorderLayout());
		
		dataBox.setLayout(new BorderLayout());
		inputBox.setLayout(new GridLayout(3,2));
		commandBox.setLayout(new FlowLayout());
		logBox.setLayout(new BorderLayout());
				
		// Step 5: Add components to containers
		inputBox.add(pumpsNumInput);
		inputBox.add(pumpsLabel);
		inputBox.add(tillsNumInput);
		inputBox.add(tillsLabel);
		inputBox.add(ticksNumInput);
		inputBox.add(ticksLabel);
		
		logBox.add(logLabel, BorderLayout.NORTH);
		logBox.add(listScroller, BorderLayout.SOUTH);
		
		commandBox.add(runButton);
		commandBox.add(exitButton);
		
		dataBox.add(inputBox, BorderLayout.NORTH);
		dataBox.add(logBox, BorderLayout.SOUTH);
		
		mainFrame.add(dataBox, BorderLayout.NORTH);
		mainFrame.add(commandBox, BorderLayout.SOUTH);
				
		// Step 6: Arrange to handle events in the user interface
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				exitApp();
			}
		});
		
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exitApp();
			}
		});
		
		runButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				log.setText("");
				log.setForeground(Color.black);
				
				// Check if values are valid, if they are run sim if not throw error in log
				if(!validateData(pumpsNumInput.getText(), tillsNumInput.getText(), ticksNumInput.getText()))
				{
					log.append("Please review input data and try again");
					log.append("\n");
					log.setForeground(Color.red);
				}
				else
				{
					s.reset();																										// Reset sim values for next run
					s.setFuelStationVal(Integer.parseInt(pumpsNumInput.getText()), Integer.parseInt(tillsNumInput.getText()));		// set Fuel station values according to text inputs
					s.runSim(Integer.parseInt(ticksNumInput.getText()));															// Run sim for x Ticks
					log.append(s.reportStatus());
				}	
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
	
	/*
	 * Method to test whether the data entered is valid
	 * 
	 */
	private boolean validateData(String numPumps, String numTills, String ticks)
	{
		 try { 
		        Integer.parseInt(numPumps); 
		    } catch(NumberFormatException e) {
		    	log.append("Number of pumps is not a valid Integer");
		    	log.append("\n");
		        return false; 
		    } catch(NullPointerException e) {
		    	log.append("Number of Pumps is null! set an Integer value");
		    	log.append("\n");
		        return false;
		    }
		 
		 try { 
		        Integer.parseInt(numTills); 
		    } catch(NumberFormatException e) {
		    	log.setText("Number of tills is not a valid Integer");
		    	log.append("\n");
		        return false; 
		    } catch(NullPointerException e) {
		    	log.setText("Number of tills is null! set an Integer value");
		    	log.append("\n");
		        return false;
		    }
		 
		 try { 
		        Integer.parseInt(ticks); 
		    } catch(NumberFormatException e) {
		    	log.setText("Number of ticks is not a valid Integer");
		    	log.append("\n");
		        return false; 
		    } catch(NullPointerException e) {
		    	log.setText("Number of ticks is null! set an Integer value");
		    	log.append("\n");
		        return false;
		    }
		 
		 // Return true is data is valid
		 return true;
	}

}
