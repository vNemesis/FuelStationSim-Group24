package com.aston.group24.vehicles;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

/**
 * Vehicle library Super class
 * @version - 0.0.4 
 * @author HarmanU
 * 
 */
public abstract class Vehicle {
	
	private int fuelTankSize;					// Tank size of vehicle
	private int currentFuel;					// Current fuel of vehicle
	private double size;						// Size of vehicle
	private Random gener = new Random();		// Random Generator
	
	/**
	 * Create a vehicle with a set random seed
	 * 
	 * @param seed Set the vehicles random seed
	 */
	public Vehicle(long seed)
	{
		gener.setSeed(seed);
	}
	
	/**
	 * Constructor - Fixed tank size
	 * 
	 * @param minTankSize Minimum fuel tank size
	 * @param maxTankSize Maximum fuel tank size
	 * @param AOF Amount of fuel to start with
	 * @param pSize Size of vehicle
	 * @param randomiseTank Whether or not to randomise the fuel tanks size between the min and max values
	 * @param randomiseFuel Whether or not to randomise the amount of fuel the vehicle starts with
	 * @param seed Seed for the random Generator
	 */
	public Vehicle(int minTankSize, int maxTankSize, int AOF, double pSize, boolean randomiseTank, boolean randomiseFuel, long seed)
	{
		fuelTankSize = maxTankSize;
		currentFuel = AOF;
		size = pSize;
		gener.setSeed(seed);
		
		// if true randomise initial fuel
		if (randomiseFuel)
		{
			//Randomise the tank size between 1 and 1 below its max
			this.randomiseInitialFuel();
			
		}
		
		// if true randomise tank size
		if (randomiseTank)
		{
			// Randomise the tank size between 7 and 9 gallons
			this.randomiseTankSize(minTankSize, maxTankSize);
		}
	}
	
	
	/**
	 * Randomise the Fuel tank size between two values - should be used at instantiation
	 * 
	 * @param lowerLimit The least a tank can be
	 * @param upperLimit The largest a tank can be
	 */
	public void randomiseTankSize(int lowerLimit, int upperLimit)
	{
		int upperL = upperLimit;
		int lowerL = lowerLimit;
		
		// Swap values if one is higher
		if(lowerL > upperL)
		{
			int tempInt = lowerL;
			lowerL = upperL;
			upperL = tempInt;
		}
		// else if they are equal then increase upper by 1
		else if (lowerL == upperL)
		{
			upperL++;
		}
		
		int difference = (upperL - lowerL) + 1;		
		
		fuelTankSize = (gener.nextInt(difference) + lowerL);
		
	}
	
	/**
	 * Randomise the amount of fuel in the vehicle - should be used at instantiation
	 */
	public void randomiseInitialFuel()
	{
		int upperLimit = this.getFuelTankSize() - 2;
		int newFuel = gener.nextInt(upperLimit) + 1;
		
		currentFuel = newFuel;
		
	}
	
	/**
	 * Fill the vehicle with fuel
	 * @param amount the amount of fuel to fill at one time
	 */
	public void fillCar(int amount)
	{
		currentFuel += amount;
	}
	
	//--------------------------------------------- Getter Methods
	
	/**
	 * Gets the amount of fuel sold to the person
	 * 
	 * @return Return the cost to fill this vehicle's tank to the top
	 */
	public BigDecimal getRefuelCost()
	{
		BigDecimal bd = new BigDecimal(fuelTankSize * 1.20).setScale(2, RoundingMode.HALF_UP); //Added Method
		return bd;
	}
	
	/**
	 * Return the vehicles tank size
	 * 
	 * @return The vehicles fuel tank size
	 */
	public int getFuelTankSize()
	{
		return fuelTankSize;
	}
	
	/**
	 * Return the vehicle's size
	 * 
	 * @return The vehicle's size
	 */
	public double getSize()
	{
		return size;
	}
	
	/**
	 * Return the Vehicle's current fuel level
	 * 
	 * @return The vehicle's current fuel
	 */
	public int getCurrentFuel()
	{
		return currentFuel;
	}
	
	//--------------------------------------------- Setter Methods
	
		protected void setFuelTankSize(int s)
		{
			fuelTankSize = s;
		}
		
		protected void setSize(double size)
		{
			this.size = size;
		}
		
		
		protected void setAmountOfFuel(int a)
		{
			currentFuel = a;
		}
		
		protected void setSeed(long seed)
		{
			gener.setSeed(seed);
		}
}
