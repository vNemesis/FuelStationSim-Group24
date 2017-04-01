package com.aston.group24.model;

/*
 * Class to run simulation and display GUI
 * 
 * @version 0.0.1
 */
public class SimulationApplication {
	
	/*
	 * Ran by application
	 * 
	 * @param args[0] Number of pumps
	 * @param args[1] Number of tills
	 */
	public static void main(String[] args)
	{ 
		// create sim with default values
		Simulation s = new Simulation(3, 2, 0.5, 0.5, 10);
		
		@SuppressWarnings("unused") FuelStationSimGUI fsg = new FuelStationSimGUI(s);
	}

}
