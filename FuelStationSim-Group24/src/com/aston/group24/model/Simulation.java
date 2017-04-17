package com.aston.group24.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;

import com.aston.group24.people.MotorbikeDriver;
import com.aston.group24.people.Person;
import com.aston.group24.people.SedanDriver;
import com.aston.group24.people.SmallCarDriver;
import com.aston.group24.people.TruckDriver;

/**
 * Main Simulation class
 * 
 * all code to run the Simulation including time and variables will go here
 * 
 * @version 0.0.2
 * 
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
	
	private Random rnd;
	
	//Constructor
	public Simulation(int numOfPumps, int numOfTills, double probabilityP, double probabilityQ, long seed)
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
	}
	
	/**
	 * Run simulation for an amount of time
	 * @param time Time to run simulation in ticks (1 tick = 10 seconds)
	 */
	public void runSim(int ticks)
	{
		fs = new FuelStation(numOfPumps, numOfTills);
		
		for (int i = 0; i < ticks; i++)
		{
			simulate();
		}
		
		finished = true;
	}

	/**
	 * Runs one tick of the simulation 
	 * 
	 */
	private void simulate() 
	{
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
			}
		}
		
		//Remove people in removeList
		for(Person p : removeList)
		{
			//Add person's profit and loss to total
			profit = profit.add(p.getMoneySpent());
			loss = loss.add(p.getMoneyLost());
			
			//Remove from simulation
			fs.removePerson(p);			
		}
		
		//Increment tick number
		tick++;
	}
	
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
		else if(num > 2*probabilityP + probabilityQ && num <= 2*probabilityP + probabilityQ + TruckDriver.getHappiness())
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
	
	protected void reset()
	{
		fs = null;
		tick = 0;
	}
	
	// ------------------------------------------------ GUI Integration ------------------------------------------------
	
	// return current tick of simulation
	public int getCurrentTick()
	{
		return tick;
	}
	
	/**
	 * Report status of Simulation - Mainly debug can be omitted in actual release
	 */
	protected String reportStatus()
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append("Fuel station with " + fs.numberOfPumps() + " Pumps was created");
		sb.append("Shop with " + numOfTills + "Has been created");
		return sb.toString();
	}
	
	/**
	 * Set the values for the fuel station
	 * @param numPumps set the number of pumps
	 * @param numTills set the number of tills
	 */
	protected void setFuelStationVal(int numPumps, int numTills, long seed, double probabilityP, double probabilityQ)
	{
		numOfPumps = numPumps;
		numOfTills = numTills;
		this.probabilityP = probabilityP;
		this.probabilityQ = probabilityQ;
		this.seed = seed;
	}
	
	

}
