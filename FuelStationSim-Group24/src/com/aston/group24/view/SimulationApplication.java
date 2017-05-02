package com.aston.group24.view;

import com.aston.group24.model.Simulation;

/**
 * Class to run simulation and display GUI
 * 
 * @version 02.05.2017/2329
 * @author HarmanU
 */
public class SimulationApplication {
	
	/**
	 * Ran by application
	 * 
	 * @param args[0] Number of pumps
	 * @param args[1] Number of tills
	 */
	public static void main(String[] args)
	{ 
		// create sim with default values
		Simulation s = new Simulation(3, 2, 0.5, 0.5, false, 10);
		
		@SuppressWarnings("unused") FuelStationSimGUI fsg = new FuelStationSimGUI(s);
	}

}
