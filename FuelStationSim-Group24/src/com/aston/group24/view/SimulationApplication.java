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
	 * @param args[8] Log debugging information?
	 */
	public static void main(String[] args)
	{ 
		
		if (args.length == 0)
		{
			// Create simulation with default values and launch GUI
			Simulation s = new Simulation(3, 2, 0.05, 0.05, false, 10);
			@SuppressWarnings("unused") FuelStationSimGUI fsg = new FuelStationSimGUI(s);
		}
		else if (args.length == 9)
		{	
			if (!validateArguments(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8]))
			{
				System.out.println("Arguments Invalid. Please Try again");
				System.out.println("You gave " + args.length + " arguemnts");
				System.out.println("");
				System.out.println("They are ");
				
				for (int i = 0; i < args.length; i++)
				{
					int j = i + 1;
					System.out.print("Argument " + j + ": ");
					System.out.println(args[i]);
				}
			}
			else
			{
				// Pass arguments to simulation
				Simulation s = new Simulation(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Double.parseDouble(args[2]),  Double.parseDouble(args[3]), Boolean.parseBoolean(args[4]), Long.parseLong(args[5]));
				
				//run simulation
				s.runSim(Integer.parseInt(args[6]), Boolean.parseBoolean(args[8]));
				
				// if true, print to file
				if(Boolean.parseBoolean(args[7]) == true)
				{
					s.PrintOutputToFile("Simulation Output");
				}
				
				System.out.print(s.reportStats());
			}
		}
		else
		{
			System.out.println("Invalid Number of arguemnts given. Give either 0 or 8");
			System.out.println("You gave " + args.length + " arguemnts");
			System.out.println("");
			System.out.println("They are ");
			
			for (int i = 0; i < args.length; i++)
			{
				int j = i + 1;
				System.out.print("Argument " + j + ": ");
				System.out.println(args[i]);
			}
			
			System.out.println("");
			System.out.println("Format is (int numOfPumps, int numOfTills, double probabilityP, double probabilityQ, boolean trucksEnabled, long seed, int ticksToRunSim, boolean printOutputToFile)");
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
	 */
	private static boolean validateArguments(String numPumps, String numTills, String probP, String probQ, String trucks, String seed, String ticks, String print, String debug)
	{
		 // check num of pumps is valid
		 try { 
		        Integer.parseInt(numPumps); 
		    } catch(NumberFormatException e) {
		    	System.out.println("Number of pumps is not a valid Integer");
			    System.out.println("");
		        return false; 
		    } catch(NullPointerException e) {
		    	System.out.println("Number of Pumps is null! set an Integer value");
			    System.out.println("");
		        return false;
		    }
		 
		 // check number of tills is valid
		 try { 
		        Integer.parseInt(numTills); 
		    } catch(NumberFormatException e) {
		    	System.out.println("Number of tills is not a valid Integer");
			    System.out.println("");
		        return false; 
		    } catch(NullPointerException e) {
		    	System.out.println("Number of tills is null! set an Integer value");
			    System.out.println("");
		        return false;
		    }
		 
		 
		 // check seed is valid
		 try { 
		        Integer.parseInt(seed); 
		    } catch(NumberFormatException e) {
		    	System.out.println("Number of ticks is not a valid Integer");
			    System.out.println("");
		        return false; 
		    } catch(NullPointerException e) {
		    	System.out.println("Number of ticks is null! set an Integer value");
			    System.out.println("");
		        return false;
		    }
		 
		 // check probability of p is valid
		 try { 
		        Double.parseDouble(probP); 
		    } catch(NumberFormatException e) {
		    	System.out.println("Number of ticks is not a valid double value (i.e 0.2)");
			    System.out.println("");
		        return false; 
		    } catch(NullPointerException e) {
		    	System.out.println("Number of ticks is null! set an Integer value");
			    System.out.println("");
		        return false;
		    }
		 
		 if (Double.parseDouble(probP) < 0 || Double.parseDouble(probP) > 1)
		 {
			 System.out.println("Probibility of P needs to be between 0 and 1");
			 System.out.println("");
		     return false; 
		 }
		 
		 // check probability of q is valid
		 try { 
		        Double.parseDouble(probQ); 
		    } catch(NumberFormatException e) {
		    	System.out.println("Number of ticks is not a valid double value (i.e 0.2)");
			    System.out.println("");
		        return false; 
		    } catch(NullPointerException e) {
		    	System.out.println("Number of ticks is null! set an double value");
			    System.out.println("");
		        return false;
		    }
		 
		 if (Double.parseDouble(probQ) < 0 || Double.parseDouble(probQ) > 1)
		 {
			 System.out.println("Probibility of Q needs to be between 0 and 1");
		     System.out.println("");
		     return false; 
		 }
		 
		 //Check ticks value is valid 
		 try { 
		        Integer.parseInt(ticks); 
		    } catch(NumberFormatException e) {
		    	System.out.println("Number of ticks is not a valid Integer");
			    System.out.println("");
		        return false; 
		    } catch(NullPointerException e) {
		    	System.out.println("Number of ticks is null! set an Integer");
			    System.out.println("");
		        return false;
		    }
		 
		 if((trucks.equals("false") || trucks.equals("true")) == false)
		 {
			 System.out.println("Trucks is not a valid boolean");
			 System.out.println("'" + trucks + "' does not equal 'true' or 'false'");
			 return false;
		 }
		 
		 if((print.equals("false") || print.equals("true")) == false)
		 {
			 System.out.println("print is not a valid boolean");
			 return false;
		 }
		 
		 if((debug.equals("false") || debug.equals("true")) == false)
		 {
			 System.out.println("debug is not a valid boolean");
			 return false;
		 }
		 
		 // Return true is data is valid
		 return true;
	}

}
