package com.aston.group24.model;

import java.util.ArrayList;
import com.aston.group24.vehicles.Vehicle;;

public class FuelPump {
	
	private double space;
	private double CSO; // Current space occupied
	private double CSA; //Current space Available
	private int fuelSupply;
	private int pumpID;
	private ArrayList<Vehicle> currentCars;
	private int fueledThisTick;
	
	public FuelPump(int pumpID, double space, int fuelSupply)
	{
		this.pumpID = pumpID;
		this.space = space;
		this.fuelSupply = fuelSupply;
		currentCars = new ArrayList<Vehicle>();
		CSA = space;
		CSO = 0;
	}
	
	/*
	 * Add vehicle to pumps list
	 */
	protected void addVehicle(Vehicle v)
	{
		currentCars.add(v);
		CSO += v.getSize();
		CSA = space - CSO;
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
	
	/*
	 * supply fuel to all cars at the pump
	 */
	protected void supplyFuel()
	{
		if (currentCars.size() != 0)
		{
			for(Vehicle v : currentCars)
			{
				v.fillCar(fuelSupply);
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
