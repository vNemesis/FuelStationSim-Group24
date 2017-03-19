package com.aston.group24.model;

/*
 * Main Simulation class
 * 
 * all code to run the Simulation including time and variables will go here
 * 
 * @version 0.0.1
 * 
 */
public class Simulation {
	
	private boolean finished; 							// Whether the simulation has finished or not
	private int tick; 									// Current simulation tick
	
	public static void main(String[] args)
	{
		
	}
	
	public Simulation()
	{
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
	

}
