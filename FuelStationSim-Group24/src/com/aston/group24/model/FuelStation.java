package com.aston.group24.model;

import java.util.ArrayList;
import java.util.Collections;

import com.aston.group24.vehicles.Vehicle;

/*
 * Fuel Station
 * - Will contain fuel pumps and manager these pumps
 * 
 * @version 0.0.2
 * 
 */
public class FuelStation {
	
	private ArrayList<FuelPump> pumps;

	public FuelStation(int numOfPumps) 
	{
		pumps = new ArrayList<FuelPump>();
		
		createPumps(numOfPumps);
	}
	
	/*
	 * Create X number of pumps at the station
	 */
	protected void createPumps(int amountOfPumps)
	{
		for(int i = 0; i < amountOfPumps; i++)
		{
			//Pumps with 3.0 space and can pump 1 gallon at a time
			pumps.add(new FuelPump(i, 3, 1));
		}
	}
	
	/*
	 * All pumps at the station pump fuel to cars
	 */
	protected void allPumpFuel()
	{
		for(FuelPump fp : pumps)
		{
			fp.supplyFuel();
		}
	}
	
	/*
	 * Add a vehicle to the station at a pump
	 */
	protected void addVehicleToStation(Vehicle v)
	{
		pumpWithShortestLine(v).addVehicle(v);;
	}
	
	/*
	 * Sort Pumps with space in Descending order
	 * 
	 * TODO - Revise this method,  possibly use custom container
	 */
	protected FuelPump pumpWithShortestLine(Vehicle v)
	{
			
		ArrayList<FuelPump> pumpsWithSpace = new ArrayList<FuelPump>();
		
		//Check pumps with space for the vehicle
		for(FuelPump fp : pumps)
		{
			if(fp.getCSA() >= v.getSize())
			{
				pumpsWithSpace.add(fp);
			}
		}
		
		//If there is no pumps return null, else if there is only one space then return the pump
		if(pumpsWithSpace.size() == 0)
		{
			return null;
		}
		else if(pumpsWithSpace.size() == 1)
		{
			return pumpsWithSpace.get(0);
		}
		
		System.out.println("Before Ordering");
		System.out.println(pumpsWithSpace.toString());
		System.out.println("Before Ordering");
		
		// List to store sorted values
		ArrayList<Double> sortedValues = new ArrayList<Double>();
		
		// Add values to list then sort in Ascending order
		for(FuelPump fp : pumpsWithSpace)
		{
			sortedValues.add(fp.getCSA());
		}
		
		Collections.sort(sortedValues, Collections.reverseOrder()); // Sort values
		
		System.out.println("After Ordering");
		System.out.println(sortedValues.toString());
		
		for(FuelPump fp : pumpsWithSpace)
		{
			if(fp.getCSA() == sortedValues.get(0))
			{
				return fp;
			}
		}
		
		return null;
		
		
	}
	
	//------------------------------------------------------------------DEBUGGING--------------------------------------------------------------------
	
	/*
	 * For testing purposes
	 * 
	 */
	protected void testing(Vehicle v1, Vehicle v2, Vehicle v3)
	{
		
		pumps.get(0).addVehicle(v1); //1.5
		pumps.get(2).addVehicle(v2); //1
		pumps.get(2).addVehicle(v3); //1.5
		
		//pump 0 = 1.5 free space
		//pump 1 = 0.5 free space
		//pump 2 - 3.0 free space
	}
	
	public int numberOfPumps()
	{
		return pumps.size();
		
	}
	
	public String pumpsCSA()
	{
		StringBuilder sb = new StringBuilder();
		
		for(FuelPump fp : pumps)
		{
			sb.append(fp.getCSA());
			sb.append("\n");
		}
		
		return sb.toString();
	}

}
