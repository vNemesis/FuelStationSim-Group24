package com.aston.group24.model;

import java.math.BigDecimal;

/*
 * Main Simulation class
 * 
 * all code to run the Simulation including time and variables will go here
 * 
 * @version 0.0.2
 * 
 */
public class Simulation {
	
	@SuppressWarnings("unused")
	private boolean finished; 							// Whether the simulation has finished or not
	@SuppressWarnings("unused")
	private int tick; 									// Current simulation tick
	
	private FuelStation fs;								// Fuel Station for simulation
	//private Shop shop;								// Shop for simulation
	
	//Constructor
	public Simulation(int numOfPumps, int numOfTills)
	{
		fs = new FuelStation(numOfPumps);
		
		finished = false;
	}
	
	/*
	 * Run simulation for an amount of time
	 * @param time Time to run simulation in ticks (1 tick = 10 minutes)
	 */
	public void runSim(int ticks)
	{
		for (int i = 0; i < ticks; i++)
		{
			simulate();
		}
		
		finished = true;
	}

	/*
	 * What to do per tick
	 * TO-DO - discuss implementation
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
	
	//GUI Integration
	
	public int getCurrentTick()
	{
		return tick;
	}
	

}
