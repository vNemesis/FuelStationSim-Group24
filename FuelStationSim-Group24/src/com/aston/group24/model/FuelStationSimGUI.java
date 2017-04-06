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
import javax.swing.border.EmptyBorder;

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
		
		int blankSpace = 5;
		
		// Step 1: create the components
		JButton runButton = new JButton();
		JButton exitButton = new JButton();
		
		JTextField pumpsNumInput = new JTextField("3");
		JTextField tillsNumInput = new JTextField("2");
		JTextField probPInput = new JTextField("0.5");
		JTextField probQInput = new JTextField("0.5");
		JTextField seedInput = new JTextField("10");
		JTextField ticksInput = new JTextField("1440");
		
		JLabel pumpsLabel = new JLabel(" (Integer) Number of pumps");
		JLabel tillsLabel = new JLabel(" (Integer) Number of Tills");
		JLabel probPLabel = new JLabel(" (Double) Probibility of P (Number between 0 and 1)");
		JLabel probQLabel = new JLabel(" (Double) Probibility of Q (Number between 0 and 1)");
		JLabel seedLabel = new JLabel(" (Integer) Simulation random seed");
		JLabel ticksLabel = new JLabel(" (Integer) Number of Ticks to run simulation for (1 tick = 10 seconds)");
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
		mainFrame = new JFrame("Fuel Station Simulation");
		mainFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
		JPanel commandBox = new JPanel();
		JPanel inputBox = new JPanel();
		JPanel dataBox = new JPanel();
		JPanel logBox = new JPanel();
				
		// Step 4: Specify LayoutManagers
		
		mainFrame.setLayout(new BorderLayout());
		((JPanel)mainFrame.getContentPane()).setBorder(new EmptyBorder(blankSpace, blankSpace, blankSpace, blankSpace));
		
		dataBox.setLayout(new BorderLayout());
		inputBox.setLayout(new GridLayout(6,2));
		commandBox.setLayout(new FlowLayout());
		logBox.setLayout(new BorderLayout());
				
		// Step 5: Add components to containers
		inputBox.add(pumpsNumInput);
		inputBox.add(pumpsLabel);
		inputBox.add(tillsNumInput);
		inputBox.add(tillsLabel);
		inputBox.add(probPInput);
		inputBox.add(probPLabel);
		inputBox.add(probQInput);
		inputBox.add(probQLabel);
		inputBox.add(seedInput);
		inputBox.add(seedLabel);
		inputBox.add(ticksInput);
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
				if(!validateData(pumpsNumInput.getText(), tillsNumInput.getText(), probPInput.getText(), probQInput.getText(), seedInput.getText(), ticksInput.getText()))
				{
					log.append("Please review input data and try again");
					log.append("\n");
					log.setForeground(Color.red);
				}
				else
				{
					s.reset();																// Reset sim values for next run
					s.setFuelStationVal(Integer.parseInt(pumpsNumInput.getText()), Integer.parseInt(tillsNumInput.getText()), Integer.parseInt(seedInput.getText()), Double.parseDouble(probPInput.getText()), Double.parseDouble(probQInput.getText()));		// set Fuel station values according to text inputs
					s.runSim(Integer.parseInt(ticksInput.getText()));						// Run sim for x Ticks
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
	private boolean validateData(String numPumps, String numTills, String probP, String probQ, String seed, String ticks)
	{
		 // check num of pumps is valid
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
		 
		 // check num of tills is valid
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
		 
		 
		 // check seed is valid
		 try { 
		        Integer.parseInt(seed); 
		    } catch(NumberFormatException e) {
		    	log.setText("Number of ticks is not a valid Integer");
		    	log.append("\n");
		        return false; 
		    } catch(NullPointerException e) {
		    	log.setText("Number of ticks is null! set an Integer value");
		    	log.append("\n");
		        return false;
		    }
		 
		 // check probability of p is valid
		 try { 
		        Double.parseDouble(probP); 
		    } catch(NumberFormatException e) {
		    	log.setText("Number of ticks is not a valid double value (i.e 0.2)");
		    	log.append("\n");
		        return false; 
		    } catch(NullPointerException e) {
		    	log.setText("Number of ticks is null! set an Integer value");
		    	log.append("\n");
		        return false;
		    }
		 
		 if (Double.parseDouble(probP) < 0 || Double.parseDouble(probP) > 1)
		 {
			 log.setText("Probibility of P needs to be between 0 and 1");
		     log.append("\n");
		     return false; 
		 }
		 
		 // check probability of q is valid
		 try { 
		        Double.parseDouble(probQ); 
		    } catch(NumberFormatException e) {
		    	log.setText("Number of ticks is not a valid double value (i.e 0.2)");
		    	log.append("\n");
		        return false; 
		    } catch(NullPointerException e) {
		    	log.setText("Number of ticks is null! set an double value");
		    	log.append("\n");
		        return false;
		    }
		 
		 if (Double.parseDouble(probQ) < 0 || Double.parseDouble(probQ) > 1)
		 {
			 log.setText("Probibility of Q needs to be between 0 and 1");
		     log.append("\n");
		     return false; 
		 }
		 
		 //Check ticks value is valid
		 try { 
		        Integer.parseInt(ticks); 
		    } catch(NumberFormatException e) {
		    	log.setText("Number of ticks is not a valid Integer");
		    	log.append("\n");
		        return false; 
		    } catch(NullPointerException e) {
		    	log.setText("Number of ticks is null! set an Integer");
		    	log.append("\n");
		        return false;
		    }
		 
		 // Return true is data is valid
		 return true;
	}

}
