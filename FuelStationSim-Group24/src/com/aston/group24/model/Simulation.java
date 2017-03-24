package com.aston.group24.model;

/*
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
	//private Shop shop;								// Shop for simulation
	public static long seed;
	
	private int numOfPumps;								// Number of pumps
	private int numOfTills;								// Number of tills
	
	//Constructor
	public Simulation(int numOfPumps, int numOfTills, int seed)
	{
		
		this.numOfPumps = numOfPumps;
		this.numOfTills = numOfTills;
		
		finished = false;
		
		this.seed = seed;
	}
	
	/*
	 * Run simulation for an amount of time
	 * @param time Time to run simulation in ticks (1 tick = 10 seconds)
	 */
	public void runSim(int ticks)
	{
		fs = new FuelStation(numOfPumps);
		
		for (int i = 0; i < ticks; i++)
		{
			simulate();
			tick++;;
		}
		
		finished = true;
	}

	/*
	 * What to do per tick
	 * TODO - discuss implementation
	 * 
	 */
	private void simulate() 
	{
	}
	
	/*
	 *Check whether simulation has finished running 
	 */
	public boolean isFinished()
	{
		if(finished)
		{
			return true;
		}
		
		return false;
	}
	
	/*
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
	
	/*
	 * Report status of Simulation - Mainly debug can be ommited in actual release
	 */
	protected String reportStatus()
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append("Fuel station with " + fs.numberOfPumps() + " Pumps was created");
		// b.append("Shop with " + numOfTills + "Has been created"); TODO - add when shop is ready
		return sb.toString();
	}
	
	/*
	 * Set the values for the fuel station
	 * @param numPumps set the number of pumps
	 * @param numTills set the number of tills
	 */
	protected void setFuelStationVal(int numPumps, int numTills)
	{
		numOfPumps = numPumps;
		numOfTills = numTills;
	}
	
	

}
