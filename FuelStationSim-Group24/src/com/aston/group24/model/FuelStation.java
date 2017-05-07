//FuelStation.java

//Fuel Station Simulation Group 24

package com.aston.group24.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import com.aston.group24.people.Person;
import com.aston.group24.util.Logger;

/**
 * Fuel Station
 * - Will contain fuel pumps and manager these pumps
 * 
 * @version 03.05.2017/1752
 * @author HarmanU, JShorthouse
 */
public class FuelStation implements Logger{
	
	private LinkedList<FuelPump> pumps;					// Fuel pumps in the station
	private ArrayList<Person> people;					// List of all people in station (ONLY used for incrementing time in simulation each tick)
	private Shop shop;
	
	private int gallonsFueled;							// How much fuel has been pumped total
	private final int pumpSupplyRate = 1;				// How many gallons do the pumps fuel per tick
	private final int pumpSpaceCapacity = 3;			// How much space is at each pump
	private int numberOfCustomersServed;

	public FuelStation(int numOfPumps, int numOfTills) 
	{
		pumps = new LinkedList<FuelPump>();
		people = new ArrayList<Person>();
		shop = new Shop(numOfTills);
		
		createPumps(numOfPumps);
	}
	
	/**
	 * Create X number of pumps at the station
	 * 
	 * @param amountOfPumps Amount of pumps to create
	 */
	protected void createPumps(int amountOfPumps)
	{
		for(int i = 0; i < amountOfPumps; i++)
		{
			//Pumps with 3.0 space and can pump 1 gallon at a time
			pumps.add(new FuelPump(i, pumpSpaceCapacity, pumpSupplyRate));
		}
	}
	
	/**
	 * Add a vehicle to the station at a pump
	 * 
	 * @param p Person to add
	 * 
	 * @return Returns true if person was added, false if not
	 */
	protected boolean addPerson(Person p)
	{
		FuelPump validPump = pumpWithShortestLine(p);
		
		if (validPump != null)
		{
			pumpWithShortestLine(p).addPerson(p);
			numberOfCustomersServed++;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	/**
	 * Sort Pumps with space in Descending order
	 * @param p person to add
	 * @return Returns a Fuel pump that has the shortest queue
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
		
		//System.out.println("Before Ordering");
		ArrayList<Double> unsortedValues = new ArrayList<Double>();
		for(FuelPump fp : pumpsWithSpace)
		{
			unsortedValues.add(fp.getCSA());
		}
		//System.out.println(unsortedValues.toString());
		
		// List to store sorted values
		ArrayList<Double> sortedValues = new ArrayList<Double>();
		
		// Add values to list then sort in Ascending order
		for(FuelPump fp : pumpsWithSpace)
		{
			sortedValues.add(fp.getCSA());
		}
		
		Collections.sort(sortedValues, Collections.reverseOrder()); // Sort values
		
		//System.out.println("After Ordering");
		//System.out.println(sortedValues.toString());
		
		for(FuelPump fp : pumpsWithSpace)
		{
			if(fp.getCSA() == sortedValues.get(0))
			{
				return fp;
			}
		}
		
		return null;
		
		
	}
	
	/**
	 * Move people between stages and return people that need removing from the simulation
	 * @return Returns arrayList of people to move.
	 */
	public ArrayList<Person> movePeople(){
		
		ArrayList<Person> removeList = new ArrayList<Person>();
		
		//Add refuelled people to shop (Keeping them at the pump as they are still "using" the space)
		ArrayList<Person> refulled = getPeopleJustRefulled();
		for(Person p : refulled)
		{
			if(p.wantsToShop())
			{
				shop.addPersonToFloor(p);
			}
			else
			{
				shop.addPersonToTills(p);
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
			
			for(FuelPump fp : pumps)
			{
				fp.removePerson(p);
			}
			
			removeList.add(p);
		}
		
		return removeList;
			
	}
	
	/**
	 * Remove a person from the arrayList of all people
	 * @param p Person to be removed
	 */
	public void removePerson(Person p)
	{
		people.remove(p);
	}
	
	/**
	 * Run methods needed to simulate activity in the station
	 */
	public void simulate(){
		//Pump fuel to cars
		for(FuelPump fp : pumps)
		{
			fp.simulate();
			gallonsFueled += (fp.carsFuelThisTick() * pumpSupplyRate);
			fp.resetFuelTickCounter();
		}
		
		//Add time to people in shop (browsing and at tills)
		shop.simulate();
		
		//Increment time of each person
		for(Person p : people)
		{
			p.incrementTime();
		}
	}
	
	/**
	 * Logs information
	 * @return returns stringBuilder to send to console and GUI as a log
	 */
	public String Log()
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append("\n The total amount of fuel pumped was " + getGallonsFueled() + " gallon(s) of fuel.");
		sb.append("\n The number of customers who used a fuel pump was " + numberOfCustomersServed + ".");
		
		return sb.toString();
	}
	
	//------------------------------------------------------------ Getter methods -----------------------------------------------------------
	
	/**
	 * Returns total amount this station has provided
	 * @return Returns int for gallons of fuel pumped
	 */
	protected int getGallonsFueled()
	{
		return gallonsFueled;
	}
	
	/**
	 * Returns total amount of customers served
	 * @return Returns int for number of customers
	 */
	protected int getCustomersServed()
	{
		return numberOfCustomersServed;
	}
	
	/**
	 * get number of pumps
	 * @return returns int for size of pumps list
	 */
	protected int numberOfPumps()
	{
		return pumps.size();
		
	}
	
	/**
	 * Returns shop at station
	 * @return shop at station
	 */
	protected Shop getShop()
	{
		return shop;
	}
	
	/**
	 * Returns the fuel pumps at this station
	 * @see FuelPump
	 * @return Returns a LinkedList of Fuel pumps
	 */
	protected LinkedList<FuelPump> getFuelPumps()
	{
		return pumps;
	}
	
	/**
	 * Returns people that finished refuelling on the last tick
	 * 
	 * @return Returns an arrayList with the people who refueled
	 */
	private ArrayList<Person> getPeopleJustRefulled()
	{
		ArrayList<Person> returnPeople = new ArrayList<Person>();
		
		for(FuelPump fp : pumps)
		{
			for(Person p : fp.getPeople())
			{
				//Check if vehicle is refuelled but not yet marked as refuelled
				if(p.getVehicle().getCurrentFuel() == p.getVehicle().getFuelTankSize() && !p.getRefuelled())
				{
					p.setRefuelled(true);
					returnPeople.add(p);
				}
			}
		}
		return returnPeople;
	}
}
