package com.aston.group24.view;

import com.aston.group24.model.Simulation;

/**
 * Class to run simulation and display GUI
 * 
 * @version - 03.05.2017/1750
 * @author HarmanU, HuzaifahR
 */
public class SimulationApplication {
	
	/**
	 * Ran by application
	 * 
	 * @param args[0] Number of pumps
	 * @param args[1] Number of tills
	 * @param args[2] Prob of P
	 * @param args[3] Prob of Q
	 * @param args[4] Enable trucks?
	 * @param args[5] Simulation seed
	 * @param args[6] Ticks to run Simulation
	 * @param args[7] Print output to file?
	 */
	public static void main(String[] args)
	{ 
		
		if (args.length == 0)
		{
			// Create simulation with default values and launch GUI
			Simulation s = new Simulation(3, 2, 0.05, 0.05, false, 10);
			@SuppressWarnings("unused") FuelStationSimGUI fsg = new FuelStationSimGUI(s);
		}
		else if (args.length == 8)
		{
			// Pass arguments to simulation
			Simulation s = new Simulation(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Double.parseDouble(args[2]),  Double.parseDouble(args[3]), Boolean.parseBoolean(args[4]), Long.parseLong(args[5]));
			s.runSim(Integer.parseInt(args[6]));
			
			if(Boolean.parseBoolean(args[7]))
			{
				s.PrintOutputToFile();
			}
			
			System.out.print(s.reportStartStatus());
			System.out.print(s.reportStats());
			
		}
		else
		{
			System.out.println("Invalid Number of arguemnts given. either 0 or 7");
			System.out.println("Format is (int numOfPumps, int numOfTills, double probabilityP, double probabilityQ, boolean trucksEnabled, long seed, int ticksToRunSim, boolean printOutputToFile)");
		}
	}

}
