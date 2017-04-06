package com.aston.group24.vehicles;

import java.math.BigDecimal;
import java.util.Random;

/*
 * Vehicle library Super class
 * @version - 0.0.2  
 * 
 */
public abstract class Vehicle {
	
	private int fuelTankSize;
	private int currentFuel;
	private double size;
	private Random gener = new Random();
	
	public Vehicle(long seed)
	{
		gener.setSeed(seed);
	}
	
	/*
	 * Constructor - Models omitted, Fixed tank size
	 * 
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
	
	//Getter methods
	
	public BigDecimal getRefuelCost()
	{
		BigDecimal bd = new BigDecimal("fuelTankSize * 1.20"); //Added Method
		return bd;
	}
	
	/*
	 * Randomise the Fuel tank size between two values - should be used at instantiation
	 * 
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
	
	/*
	 * Randomise the amount of fuel in the vehicle - should be used at instantiation
	 */
	public void randomiseInitialFuel()
	{
		int upperLimit = this.getFuelTankSize() - 2;
		int newFuel = gener.nextInt(upperLimit) + 1;
		
		currentFuel = newFuel;
		
	}
	
	public int getFuelTankSize()
	{
		return fuelTankSize;
	}
	
	public double getSize()
	{
		return size;
	}
	
	public int getCurrentFuel()
	{
		return currentFuel;
	}
	
	/*
	 * Fill the vehicle with fuel
	 * 	@param amount the amount of fuel to fill at one time
	 */
	public void fillCar(int amount)
	{
		currentFuel += amount;
	}
	
	//Setter Methods
	
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
