//Simulation.java
package com.aston.group24.model;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;

import com.aston.group24.people.MotorbikeDriver;
import com.aston.group24.people.Person;
import com.aston.group24.people.SedanDriver;
import com.aston.group24.people.SmallCarDriver;
import com.aston.group24.people.TruckDriver;
import com.aston.group24.util.StringToFile;
import com.aston.group24.vehicles.Motorbike;
import com.aston.group24.vehicles.Sedan;
import com.aston.group24.vehicles.SmallCar;
import com.aston.group24.vehicles.Truck;

/**
 * Main Simulation class
 * 
 * all code to run the Simulation including time and variables will go here
 * 
 * 
 * @version 02.05.2017/2329
 * 
 * @author HarmanU, JShorthouse
 */
public class Simulation {
	
	private boolean finished; 							// Whether the simulation has finished or not
	private int tick; 									// Current simulation tick
	
	private FuelStation fs;								// Fuel Station for simulation
	public long seed;
	
	private int numOfPumps;								// Number of pumps
	private int numOfTills;								// Number of tills
	
	private double probabilityP;						// Probability for small cars and motorbikes
	private double probabilityQ;						// Probability for sedans
	private BigDecimal profit;
	private BigDecimal loss;
	private double maxTruckDriverHappiness;
	private double truckDriverHappiness;				// Happiness of all truck drivers
	private boolean trucksEnabled;						// Whether truck drivers will enter the station or not
	
	// Number of each type of vehicle that was served at the station
	private int numOfSmallCars;
	private int numOfSedans;
	private int numOfMotorbikes;
	private int numOfTrucks;
	private int numOfLossedCustomers;
	
	private Random rnd;
	
	//Constructor
	public Simulation(int numOfPumps, int numOfTills, double probabilityP, double probabilityQ, boolean trucksEnabled, long seed)
	{
		this.numOfPumps = numOfPumps;
		this.numOfTills = numOfTills;
		this.probabilityP = probabilityP;
		this.probabilityQ = probabilityQ;
		this.seed = seed;
		rnd = new Random(seed);
		finished = false;
		profit = new BigDecimal(0);
		loss = new BigDecimal(0);
		this.trucksEnabled = trucksEnabled;
		
		
		truckDriverHappiness = maxTruckDriverHappiness = 0.02;
	}
	
	/**
	 * Run simulation for an amount of time
	 * @param ticks Time to run simulation in ticks (1 tick = 10 seconds)
	 * @param logDebug Whether or not to log debug information
	 */
	public void runSim(int ticks, boolean logDebug)
	{
		fs = new FuelStation(numOfPumps, numOfTills);
		
		for (int i = 0; i < ticks; i++)
		{
			simulate();
			
			// if true, log debugging information
			if(logDebug)
			{
				for(FuelPump fp : fs.getFuelPumps())
				{
					fp.debugInfo();
				}
				
				fs.getShop().debugInfo();
			}
		}
		
		finished = true;
	}

	/**
	 * Runs one tick of the simulation 
	 * 
	 */
	private void simulate() 
	{
		//System.out.println("Running tick: " + tick);
		//Create list of people to be removed from the simulation
		ArrayList<Person> removeList = new ArrayList<Person>();
		
		//Move people between stages and get list of people to remove
		ArrayList<Person> peopleToRemove = fs.movePeople();
		
		//Add these people to remove list
		for(Person p: peopleToRemove)
		{
			removeList.add(p);
		}
		
		//Run simulation
		fs.simulate();

		//Generate new person
		Person newPerson = generatePerson();
		
		//Add new person to simulation
		if(newPerson != null)
		{
			//If station couldn't add a person will return false, add person to remove list else carry on
			if(!fs.addPerson(newPerson))
			{
				removeList.add(newPerson);
				numOfLossedCustomers++;
			}
			else
			{
				//System.out.println("Added new car to simulation");
				// Checks what vehicle the person has then increments a variable to keep track of how many where served
				if (newPerson.getVehicle() instanceof SmallCar)
				{
					numOfSmallCars++;
				}
				else if (newPerson.getVehicle() instanceof Sedan)
				{
					numOfSedans++;
				}
				else if (newPerson.getVehicle() instanceof Motorbike)
				{
					numOfMotorbikes++;
				}
				else if (newPerson.getVehicle() instanceof Truck)
				{
					numOfTrucks++;
				}
			}
		}
		
		//Remove people in removeList
		for(Person p : removeList)
		{
			//Add person's profit and loss to total
			profit = profit.add(p.getMoneySpent());
			loss = loss.add(p.getMoneyLost());
			
			//If truck driver, change happiness value
			if(p instanceof TruckDriver)
			{
				if(p.getVisitedShop())
				{
					increaseTruckHappiness();
				}
				else
				{
					decreaseTruckHappiness();
				}
				//System.out.println("Truck happiness: " + truckDriverHappiness);
			}
			
			//Remove from simulation
			fs.removePerson(p);			
		}
		
		//Increment tick number
		tick++;
	}
	
	/**
	 * 
	 * Generate a random person with seed generated from a main seed
	 * @return the person generated
	 * 
	 */
	private Person generatePerson(){
		double num = rnd.nextDouble();
		long personSeed = rnd.nextLong(); //Generate unique seed for each person from main seed
		
		if(num <= probabilityP)
		{
			return new MotorbikeDriver(personSeed);
		}
		else if(num > probabilityP && num <= 2*probabilityP)
		{
			return new SmallCarDriver(personSeed);
		}
		else if(num > 2*probabilityP && num <= 2*probabilityP + probabilityQ)
		{
			return new SedanDriver(personSeed);
		}
		else if(trucksEnabled && (num > 2*probabilityP + probabilityQ && num <= 2*probabilityP + probabilityQ + getTruckHappiness()))
		{
			return new TruckDriver(personSeed);
		}
		else
		{
			return null;
		}
	}
	
	/**
	 *Check whether simulation has finished running 
	 *@return Returns Boolean for whether the simulation has finished or not
	 */
	public boolean isFinished()
	{
		return finished;
	}
	
	/**
	 * To execute when the simulation has finished running (May not be needed)
	 */
	public void postSimulationRun()
	{

	}
	
	public void reset()
	{
		truckDriverHappiness = maxTruckDriverHappiness;
		fs = null;
		tick = 0;
	}
	
	/**
	 * Increases the happiness of all truck drivers
	 */
	protected void increaseTruckHappiness()
	{
		//Happy truck driver increases truckDriverHappiness by 5%, up to the original value
		truckDriverHappiness = Math.min((truckDriverHappiness * 1.05), maxTruckDriverHappiness);
		//System.out.println(truckDriverHappiness);
	}
	
	/**
	 * Decreases the happiness of all truck drivers
	 */
	protected void decreaseTruckHappiness()
	{
		//Unhappy truck driver reduces truckDriverHappiness by 20%
		truckDriverHappiness = truckDriverHappiness * 0.8;
	}
	
	/**
	 * Returns the current happiness of all truck drivers
	 * @return truckDriverHappiness value
	 */
	protected double getTruckHappiness()
	{
		return truckDriverHappiness;
	}
	// ----------------------------------------------- Output Integration ----------------------------------------------
	
	/**
	 * Print output to file
	 * @param filename Name of file to print output to
	 */
	public void PrintOutputToFile(String filename)
	{
		// print status to file	
		StringBuilder sb = new StringBuilder();
		
		sb.append(reportStats());
		
		try {
			StringToFile.sendToFile(sb.toString(), filename);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	// ------------------------------------------------ GUI Integration ------------------------------------------------
	
	// return current tick of simulation
	public int getCurrentTick()
	{
		return tick;
	}
	
	/**
	 * Reports final statistics and then send output to a file
	 * @return Returns String which contains information about the simulation outcome
	 */
	public String reportStats()
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append(" A fuel station with " + fs.numberOfPumps() + " fuel pumps was created.");
		sb.append("\n A shop with " + numOfTills + " tills has been created.");
		sb.append("\n Spawn trucks has been set to: " + trucksEnabled);
		sb.append("\n");
		
		sb.append(fs.Log());
		sb.append("\n A total of " + numOfSmallCars + " Small car(s), " + numOfSedans + " Sedan(s), " + numOfMotorbikes + " Motorbike(s) and " + numOfTrucks + " Truck(s) were served.");
		sb.append("\n A total of " + numOfLossedCustomers + " customer(s) were lost due to no space at the pumps.");
		sb.append("\n The Station made " + profit + " GBP worth of profit and missed " + loss + " GBP worth of sales.");
		
		return sb.toString();
	}
	
	/**
	 * Set the values for the fuel station
	 * @param numPumps set the number of pumps
	 * @param numTills set the number of tills
	 * @param seed Seed for simulation
	 * @param probabilityP Probability for P
	 * @param probabilityQ Probability for Q
	 * @param createTrucks Whether to create trucks or not
	 */
	public void setFuelStationVal(int numPumps, int numTills, long seed, double probabilityP, double probabilityQ, boolean createTrucks)
	{
		numOfPumps = numPumps;
		numOfTills = numTills;
		this.probabilityP = probabilityP;
		this.probabilityQ = probabilityQ;
		this.seed = seed;
		trucksEnabled = createTrucks;
	}
}