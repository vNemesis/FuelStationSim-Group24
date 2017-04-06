package com.aston.group24.model;

import java.util.LinkedList;

import com.aston.group24.people.Person;;

public class FuelPump {
	
	private double space;
	private double CSO; // Current space occupied
	private double CSA; //Current space Available
	private int fuelSupply;
	private int pumpID;
	private LinkedList<Person> currentPeople;
	private int fueledThisTick;
	
	public FuelPump(int pumpID, double space, int fuelSupply)
	{
		this.pumpID = pumpID;
		this.space = space;
		this.fuelSupply = fuelSupply;
		currentPeople = new LinkedList<Person>();
		CSA = space;
		CSO = 0;
	}
	
	/*
	 * Add vehicle to pumps list
	 */
	protected void addPerson(Person p)
	{
		currentPeople.add(p);
		CSO += p.getVehicle().getSize();
		CSA = space - CSO;
	}
	
	protected void removePerson(Person p)
	{
		currentPeople.remove(p);
		CSO -= p.getVehicle().getSize();
		CSA = space + CSO;
	}
	
	protected int getID()
	{
		return pumpID;
	}
	
	/*
	 * return the current space Available at the pump
	 */
	protected double getCSA()
	{
		return CSA;
	}
	
	/*
	 * return max space at pump 
	 */
	protected double getMaxSpace()
	{
		return space;
	}
	
	protected LinkedList<Person> getPeople()
	{
		return currentPeople;
	}
	
	/*
	 * supply fuel to first car at the pump
	 */
	protected void supplyFuel()
	{
		if (currentPeople.size() != 0)
		{
			Person p = currentPeople.getFirst(); // get first car / person
			
			if(p.getRefuelled() == false)			// if they have not refuelled,
			{
				p.getVehicle().fillCar(fuelSupply);	// , then refuel the car
				fueledThisTick++;
			}
		}
	}
	
	/*
	 * How many vehicles has this pump fuel this tick
	 */
	protected int carsFuelThisTick()
	{
		return fueledThisTick;
	}
	
	/*
	 * resets the counter ready for next tick
	 */
	protected void resetFuelTickCounter()
	{
		fueledThisTick = 0;
	}

}
