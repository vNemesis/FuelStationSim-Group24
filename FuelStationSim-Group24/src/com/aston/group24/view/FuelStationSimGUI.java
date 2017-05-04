//FuelStationSimGUI.java
package com.aston.group24.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import com.aston.group24.model.*;
import com.aston.group24.util.StringToFile;

/**
 * GUI class for the simulation
 * 
 * @version 03.05.2017/1750
 * @author HarmanU
 */
public class FuelStationSimGUI {
	
	private JFrame mainFrame;
	private JTextArea log;
	
	//Inputs
	private JTextField commandInput;
	private JTextField pumpsNumInput;
	private JTextField tillsNumInput;
	private JTextField probPInput;
	private JTextField probQInput;
	private JTextField seedInput;
	private JTextField ticksInput;
	private JCheckBox chckbx_toggleTrucks;
	private JCheckBox chckbx_printToFile;
	private JCheckBox chckbx_printToFileCSV;
	
	private Simulation s;						//Simulation itself
	private int run;							//Run of simulation
	private Random gener;
	private String averageRunString;
	
	public FuelStationSimGUI(Simulation s)
	{
		this.s = s;
		run = 0;
		
		int blankSpace = 5;
		
		// Step 1: create the components
		JButton runButton = new JButton();
		JButton exitButton = new JButton();
		JButton commandButton = new JButton();
		
		pumpsNumInput = new JTextField("3");
		tillsNumInput = new JTextField("2");
		probPInput = new JTextField("0.05");
		probQInput = new JTextField("0.05");
		seedInput = new JTextField("10");
		ticksInput = new JTextField("1440");
		commandInput = new JTextField("");
		
		JLabel title = new JLabel("Fuel Station Simulator");

		title.setFont(new Font(title.getFont().getName(), title.getFont().getStyle(), 20));
		
		JLabel pumpsLabel = new JLabel(" (Integer) Number of pumps");
		JLabel tillsLabel = new JLabel(" (Integer) Number of Tills");
		JLabel probPLabel = new JLabel(" (Double) Probibility of a small car arriving (P) (Number between 0 and 1)");
		JLabel probQLabel = new JLabel(" (Double) Probibility of a sedan arriving (Q) (Number between 0 and 1)");
		JLabel seedLabel = new JLabel(" (Integer) Simulation random seed");
		JLabel ticksLabel = new JLabel(" (Integer) Number of Ticks to run simulation for (1 tick = 10 seconds)");
		JLabel commandLabel = new JLabel("Enter commands here");
		chckbx_toggleTrucks = new JCheckBox("Allow Trucks?");
		chckbx_printToFile = new JCheckBox("Print to File as txt? (Named 'Simulation Output' by default, use commandline to print with different name)");
		chckbx_printToFileCSV = new JCheckBox("Print to File as CSV? (Named 'Simulation OutputCSV' by default, use commandline to print with different name)");
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
		
		commandButton.setText("Execute command");
		commandButton.setToolTipText("Execute commands entered into the command box");
				
		// Step 3: Create containers to hold the components
		mainFrame = new JFrame("Fuel Station Simulation");
		mainFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		mainFrame.setMinimumSize(new Dimension(850, 650));
		
		JPanel commandBox = new JPanel();
		JPanel inputBox = new JPanel();
		JPanel dataBox = new JPanel();
		JPanel logBox = new JPanel();
		JPanel commandLineBox = new JPanel();
		JPanel commandButtonBox = new JPanel();
		JPanel commandCheckBox = new JPanel();
				
		// Step 4: Specify LayoutManagers
		
		mainFrame.setLayout(new BorderLayout());
		((JPanel)mainFrame.getContentPane()).setBorder(new EmptyBorder(blankSpace, blankSpace, blankSpace, blankSpace));
		
		dataBox.setLayout(new BorderLayout());
		inputBox.setLayout(new GridLayout(7,2));
		commandBox.setLayout(new BorderLayout());
		commandLineBox.setLayout(new GridLayout(1,2));
		commandButtonBox.setLayout(new GridLayout(1,3));
		commandCheckBox.setLayout(new GridLayout(2,1));
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
		inputBox.add(chckbx_toggleTrucks);
		
		logBox.add(logLabel, BorderLayout.NORTH);
		logBox.add(listScroller, BorderLayout.CENTER);
		
		// set up the command box
		commandBox.add(commandLineBox);
		commandBox.add(commandButtonBox);
		
		commandLineBox.add(commandInput);
		commandLineBox.add(commandLabel);
		commandButtonBox.add(runButton);
		commandButtonBox.add(commandButton);
		commandButtonBox.add(exitButton);
		commandCheckBox.add(chckbx_printToFile);
		commandCheckBox.add(chckbx_printToFileCSV);

		commandBox.add(commandCheckBox, BorderLayout.NORTH);
		commandBox.add(commandLineBox, BorderLayout.CENTER);
		commandBox.add(commandButtonBox, BorderLayout.SOUTH);
		
		dataBox.add(inputBox, BorderLayout.NORTH);
		dataBox.add(logBox, BorderLayout.SOUTH);
		
		mainFrame.add(title, BorderLayout.NORTH);
		mainFrame.add(dataBox, BorderLayout.CENTER);
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
					
					// Execute the simulation
						executeSim(1);
					
				}
			});
			
			// execute command when the button is pressed
			commandButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					log.setForeground(Color.black);
					log.append("\n");
					executeCommand();
					commandInput.setText("");
				}
			});
		
		// Step 7: Display the GUI
		mainFrame.pack();
		mainFrame.setVisible(true);
		
		//Inistalise variables
		gener = new Random(Integer.parseInt(seedInput.getText()));
		
	}
	
	//---------------------------------------------------------------------------------------------- Methods ------------------------------------------------------------
	
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
	
	/**
	 * Method to execute simulations after validating data inputed in GUI
	 */
	private void executeSim(int runAmount)
	{
		// Check if values are valid, if they are run simulation if not throw error in log
		if(!validateData(pumpsNumInput.getText(), tillsNumInput.getText(), probPInput.getText(), probQInput.getText(), seedInput.getText(), ticksInput.getText()))
		{
			log.append("Please review input data and try again");
			log.append("\n");
			log.setForeground(Color.red);
		}
		else if (runAmount == 1)
		{
			s.reset();																// Reset sim values for next run
			s.setFuelStationVal(Integer.parseInt(pumpsNumInput.getText()), Integer.parseInt(tillsNumInput.getText()), Integer.parseInt(seedInput.getText()), Double.parseDouble(probPInput.getText()), Double.parseDouble(probQInput.getText()), chckbx_toggleTrucks.isSelected());		// set Fuel station values according to text inputs
			s.runSim(Integer.parseInt(ticksInput.getText()), false);						// Run sim for x Ticks
			log.setText("");
			log.append("\n");
			log.append(s.reportStats());
			System.out.print(s.reportStats());
			
			// If check box is selected print to file automatically
			if(chckbx_printToFile.isSelected())
			{
				s.PrintOutputToFile("Simulation Output" + run);
			}
			
			if(chckbx_printToFileCSV.isSelected())
			{
				s.PrintOutputToFileCSV("Simulation OutputCSV" + run);
			}
			
			run++;
		}
		else if (runAmount > 1)
		{
			// run sim more than once
			
			StringBuilder sb = new StringBuilder();
			
			log.append("\n");
			log.append("\n Number of Gallons Fueled, Number of Customers Served, Number of Small Cars, Number of Sedans, Number of Motorbikes, Number of Trucks, Number of Customers Lost, Profit, Loss");
			sb.append("Number of Gallons Fueled,Number of Customers Served,Number of Small Cars,Number of Sedans,Number of Motorbikes,Number of Trucks,Number of Customers Lost,Profit,Loss");
			
			for (int i = 0; i < runAmount; i++)
			{
				long newSeed  = gener.nextLong();
				s.reset();																// Reset sim values for next run
				s.setFuelStationVal(Integer.parseInt(pumpsNumInput.getText()), Integer.parseInt(tillsNumInput.getText()), newSeed, Double.parseDouble(probPInput.getText()), Double.parseDouble(probQInput.getText()), chckbx_toggleTrucks.isSelected());		// set Fuel station values according to text inputs
				s.runSim(Integer.parseInt(ticksInput.getText()), false);						// Run sim for x Ticks
				
				sb.append("\n" + s.formatResultsInCSV());
				log.append("\n");
				log.append("\n" + s.formatResultsInCSV());
			}
			
			// If check box is selected print to file automatically
			if(chckbx_printToFile.isSelected())
			{
				try {
					StringToFile.sendToFile(sb.toString(), "AverageRunCSVResults" + run);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
			if(chckbx_printToFileCSV.isSelected())
			{
				try {
					StringToFile.sendToFileCSV(sb.toString(), "AverageRunCSVResults" + run);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
			run++;
			
			averageRunString = sb.toString();
		}
	}
	
	/**
	 * Runs the simulation for a number of different scenarios and outputs the results to a file
	 */
	private void getResults(){
		int numRuns = 10;
		int simulationsRun = 0;
		
		double pValues[] = {0.01, 0.02, 0.03, 0.04, 0.05};
		double qValues[] = {0.01, 0.02, 0.03, 0.04, 0.05};
		int numPumps[] = {1, 2, 4};
		int numTills[] = {1, 2, 4};
		boolean trucks[] = {true, false};
		
		StringBuilder output = new StringBuilder();
		output.append("p,q,pumps,tills,trucks,averageProfit,averageLoss\n");
		
		for(int p = 0; p<pValues.length; p++){
			for(int q = 0; q<qValues.length; q++){
				for(int pumps = 0; p<numPumps.length; p++){
					for(int tills = 0; tills<numTills.length; tills++){
						for(int truck = 0; truck<trucks.length; truck++){
							BigDecimal totalProfit = new BigDecimal(0.00);
							BigDecimal totalLoss = new BigDecimal(0.00);
							
							for(int run = 0; run < numRuns; run++){
								simulationsRun++;
								Simulation sim = new Simulation(numPumps[pumps],numTills[tills],pValues[p],qValues[q],trucks[truck],gener.nextLong());
								sim.runSim(1440, false);
								totalProfit.add(sim.getProfit());
								totalLoss.add(sim.getLoss());
								
								BigDecimal averageProfit = totalProfit.divide(new BigDecimal(numRuns));
								BigDecimal averageLoss = totalProfit.divide(new BigDecimal(numRuns));
								
								output.append(pValues[p] + "," + qValues[q] + "," + numTills[tills] + "," + trucks[truck] + "," + averageProfit + "," + averageLoss + "\n");
							}
						}
					}
				}
			}
		}
		System.out.println("Ran " + simulationsRun + " simulations, writing results to file");
		try {
			StringToFile.sendToFileCSV(output.toString(), "simulationOutput");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Method to test whether the data entered is valid
	 * @param numPumps Check number of pumps
	 * @param numTills Check number of tills
	 * @param probP Check Prob of P
	 * @param probQ Check Prob of Q
	 * @param seed Check seed for simulation
	 * @param ticks Check number of Ticks to run simulation
	 * 
	 * @return Returns boolean whether data is valid or not
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
		 
		 // check number of tills is valid
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
	
	/**
	 * Validates an String is an integer
	 * @param integerToValidate
	 * @return Returns true if yes, false if not
	 */
	protected boolean validateInt(String integerToValidate)
	{
		 //Check ticks value is valid 
		 try { 
		        Integer.parseInt(integerToValidate); 
		    } catch(NumberFormatException e) {
		        return false; 
		    } catch(NullPointerException e) {
		        return false;
		    }
		 
		 return true;
	}
	
	//-------------------------------------------------------------------------Command Line-------------------------------------------------------------------
	
	/**
	 * Method to read commands and act accordingly
	 */
	private void executeCommand()
	{
		String command = commandInput.getText();
		
		// check number of words
		String trimmedText = commandInput.getText().trim();
		int words = trimmedText.isEmpty() ? 0 : trimmedText.split("\\s+").length;
		
		// Parse commands and executes accordingly
		if(words == 1)
		{
			if(command.equals("help"))
			{
				log.append("\n");
				log.append("The current valid commands are: help, clear, info, print-txt, print-csv, run, run-average");
				log.append("\n to learn about a command type help followed by the command (For example: help clear).");
			}
			else if(command.equals("clear"))
			{
				log.setText("");
			}
			else if(command.equals("print-txt"))
			{
				log.append("\n");
				log.append("'print-txt' requires a second arguemnt as the name (For example ' print-csv simulationOutput ')");
			}
			else if(command.equals("print-csv"))
			{
				log.append("\n");
				log.append("'print-csv' requires a second arguemnt as the name (For example ' print-csv simulationOutput ')");
			}
			else if(command.equals("info"))
			{
				log.append("\n");
				log.append("This simulation models a fuel stations with configurable options.");
				log.append("\n Enter custom data above or run the simulation with default values by clicking the 'Run' button or typing run.");
			}
			else if(command.equals("run"))
			{
				log.append("\n Simulation ran one time");
				log.append("\n");
				executeSim(1);
			}
			else
			{
				log.append("\n");
				log.append(" Command: " + command + " is not valid. Type ' help ' for help.");
			}
		}
		else if(words == 2)
		{
			int runsAmount = Integer.parseInt(trimmedText.split("\\s+")[1]);
			
			if(command.equals("help clear"))
			{
				log.append("\n");
				log.append("The ' clear ' command is used to clear the log view.");
			}
			else if(command.equals("help info"))
			{
				log.append("\n");
				log.append("The ' info ' command is used to display info about this application.");
			}
			else if(command.equals("help run"))
			{
				log.append("\n");
				log.append("Run the simulation with the given data inputted at the top once.");
			}
			else if(command.equals("help run-average"))
			{
				log.append("\n");
				log.append("Run the simulation with the given data inputted at the top a number of times, average results and randomise seed each time. ' run-average 3 ' will run the simulation 3 times.");
			}
			else if(command.equals("help print"))
			{
				log.append("\n");
				log.append("Print simulation log to file. Requires 2 arguments' print-txt/print-csv nameOfFile ' (For example ' print-txt simulationOutput ')");
			}
			else if(command.startsWith("print-txt"))
			{
				if (runsAmount == 1)
				{
					s.PrintOutputToFile(trimmedText.split("\\s+")[1]);
					log.append("\n");
					log.append("Printed output to file: " + trimmedText.split("\\s+")[1]);
				}
				else if (runsAmount > 1)
				{
					try {
						StringToFile.sendToFile(averageRunString.toString(), trimmedText.split("\\s+")[1]);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					log.append("\n");
					log.append("Printed output to file: " + trimmedText.split("\\s+")[1]);
				}
				
				log.append("\n");
				log.append("Printed output to file: " + trimmedText.split("\\s+")[1]);
			}
			else if(command.startsWith("print-csv"))
			{
				if (runsAmount == 1)
				{
					s.PrintOutputToFileCSV(trimmedText.split("\\s+")[1]);
					log.append("\n");
					log.append("Printed output to file: " + trimmedText.split("\\s+")[1]);
				}
				else if (runsAmount > 1)
				{
					try {
						StringToFile.sendToFileCSV(averageRunString.toString(), trimmedText.split("\\s+")[1]);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					log.append("\n");
					log.append("Printed output to file: " + trimmedText.split("\\s+")[1]);
				}

			}
			else if(command.startsWith("run-average") && validateInt(trimmedText.split("\\s+")[1]) && Integer.parseInt(trimmedText.split("\\s+")[1]) > 1 )
			{
				log.append("\n");
				log.append("The simulation ran " + trimmedText.split("\\s+")[1] + " times and randomised the seed each time");
				executeSim(Integer.parseInt(trimmedText.split("\\s+")[1]));
			}
			else
			{
				log.append("\n");
				log.append("Command: " + command + " is not valid. Type ' help ' for help.");
			}
		}
		else if(words == 0 || words > 2)
		{
			log.append("\n");
			log.append("The command: " + command + " contains more than 2 words or command box is empty. Type ' help ' for help.");
		}
	}

}
