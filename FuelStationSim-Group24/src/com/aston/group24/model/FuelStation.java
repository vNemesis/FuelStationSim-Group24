package com.aston.group24.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;

import com.aston.group24.people.Person;
import com.aston.group24.vehicles.Vehicle;

/*
 * Fuel Station
 * - Will contain fuel pumps and manager these pumps
 * 
 * @version 0.0.2
 * 
 * Changes - 08/03/2017
 * 	- Add fuel amount counter
 * 
 */
public class FuelStation {
	
	private ArrayList<FuelPump> pumps;					// Fuel pumps in the station
	private ArrayList<Person> people;					// List of all people in station (ONLY used for incrementing time in simulation each tick)
	private Shop shop;
	
	private int gallonsFueled;							// How much fuel has been pumped total
	private final int pumpSupplyRate = 1;				// How many gallons do the pumps fuel per tick
	private final int pumpSpaceCapacity = 3;			// How much space is at each pump

	public FuelStation(int numOfPumps) 
	{
		pumps = new ArrayList<FuelPump>();
		people = new ArrayList<Person>();
		shop = new Shop();
		
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
			pumps.add(new FuelPump(i, pumpSpaceCapacity, pumpSupplyRate));
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
			gallonsFueled += (fp.carsFuelThisTick() * pumpSupplyRate);
			fp.resetFuelTickCounter();
		}
	}
	
	/*
	 * Add a vehicle to the station at a pump
	 */
	protected void addPerson(Person p)
	{
		pumpWithShortestLine(p).addPerson(p);;
	}
	
	/*
	 * Sort Pumps with space in Descending order
	 * 
	 * TODO - Revise this method,  Use Que's instead
	 */
	protected FuelPump pumpWithShortestLine(Person p)
	{
			
		ArrayList<FuelPump> pumpsWithSpace = new ArrayList<FuelPump>();
		
		//Check pumps with space for the vehicle
		for(FuelPump fp : pumps)
		{
			if(fp.getCSA() >= p.getVehicle().getSize())
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
	
	/*
	 * Returns people that finished refuelling on the last tick
	 */
	private ArrayList<Person> getPeopleJustRefulled()
	{
		ArrayList<Person> returnPeople = new ArrayList<Person>();
		
		for(FuelPump fp : pumps)
		{
			for(Person p : fp.getPeople())
			{
				//Check if vehicle is refulled but not yet marked as refulled
				if(p.getVehicle().getCurrentFuel() == p.getVehicle().getFuelTankSize() && !p.getRefuelled())
				{
					p.setRefuelled(true);
					returnPeople.add(p);
				}
			}
		}
		return returnPeople;
	}
	
	/*
	 * Move people between stages and return people that need removing from the simulation
	 */
	public ArrayList<Person> movePeople(){
		
		ArrayList<Person> removeList;
		
		//Add refuelled people to shop (Keeping them at the pump as they are still "using" the space)
		ArrayList<Person> refulled = getPeopleJustRefulled();
		for(Person p : refulled)
		{
			if(p.wantsToShop())
			{
				shop.addPersonToFloor();
			}
			else
			{
				removeList.add(p);
				fp.removePerson(p);
			}	
		}
		
		//Move people finished browsing to tills queue
		ArrayList<Person> shopFinished = shop.getFinishedBrowsing();
		for(Person p : shopFinished)
		{
			shop.removePersonBrowsing(p);
			shop.addPersonToTills(p);
		}
		
		//Remove people finished paying from tills queue
		ArrayList<Person> tillsFinished = shop.getFinishedPaying();
		for(Person p : tillsFinished)
		{
			shop.removePersonFromTills(p);
			fp.removePerson(p);
			removeList.add(p);
		}
		
		return removeList;
			
	}
	
	/*
	 * Remove a person from the arraylist of all people
	 */
	public void removePerson(Person p)
	{
		people.remove(p);
	}
	
	public void simulate(){
		//Pump fuel to cars
		//Add time to people in shop
		//Add time to people paying at till
		
		//Increment time of each person
		for(Person p : people)
		{
			p.incrementTime();
		}
	}
	
	
	//------------------------------------------------------------------DEBUGGING--------------------------------------------------------------------
	
	/*
	 * For testing purposes
	 * 
	 */
	protected void testing(Person v1, Person v2, Person v3)
	{
		
		pumps.get(0).addPerson(v1); //1.5
		pumps.get(2).addPerson(v2); //1
		pumps.get(2).addPerson(v3); //1.5
		
		//pump 0 = 1.5 free space
		//pump 1 = 0.5 free space
		//pump 2 - 3.0 free space
	}
	
	protected int numberOfPumps()
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
