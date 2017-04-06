package com.aston.group24.vehicles;

import java.math.BigDecimal;
import java.util.Random;

/*
 * Vehicle library Super class
 * @version - 0.0.2  
 * 
 */
public abstract class Vehicle {
	
	private String model;
	private int fuelTankSize;
	private int amountOfFuel;
	private double size;
	private Random gener = new Random();
	
	public Vehicle(String model, int tankSize, int AOF, double pSize)
	{
		this.model = model;
		fuelTankSize = tankSize;
		amountOfFuel = AOF;
		size = pSize;
	}
	
	/*
	 * Constructor - Models omitted, Fixed tank size
	 * 
	 */
	public Vehicle(int tankSize, int AOF, double pSize)
	{
		model = "N/A";
		fuelTankSize = tankSize;
		amountOfFuel = AOF;
		size = pSize;
	}
	
	//Getter methods
	
	public BigDecimal getRefuelCost()
	{
		BigDecimal bd = new BigDecimal("fuelTankSize * 1.20"); //Added Method
		return bd;
	}
	
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
	
	public String getmodel()
	{
		return model;
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
		return amountOfFuel;
	}
	
	/*
	 * Fill the vehicle with fuel
	 * 	@param amount the amount of fuel to fill at one time
	 */
	public void fillCar(int amount)
	{
		amountOfFuel += amount;
	}
	
	/*Setter Methods
	
		protected void setFuelTankSize(int s)
		{
			fuelTankSize = s;
		}
		
		protected void setSize(double size)
		{
			this.size = size;
		}
		
		protected void setProbOfOccurance(double p)
		{
			probOfOccurance = p;
		}
		
		protected void setProbOfShop(double p)
		{
			probOfShop = p;
		}
		
		protected void setAmountOfFuel(int a)
		{
			amountOfFuel = a;
		}
	*/

}
