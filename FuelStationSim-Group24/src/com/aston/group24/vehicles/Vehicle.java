package com.aston.group24.vehicles;

import java.math.BigDecimal;

import com.aston.group24.people.Person;

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
	private double probOfOccurance;
	private double probOfShop;
	
	public Vehicle(String model, int tankSize, int AOF, double pSize, double probO, double probS)
	{
		this.model = model;
		fuelTankSize = tankSize;
		amountOfFuel = AOF;
		size = pSize;
		probOfOccurance = probO;
		probOfShop = probS;
	}
	
	//Getter methods
	
	public BigDecimal getRefuelCost()
	{
		BigDecimal bd = new BigDecimal("fuelTankSize * 1.20"); //Added Method
		return bd;
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
	
	public double getProbOfOccurance()
	{
		return probOfOccurance;
	}
	
	public double getProbOfShop()
	{
		return probOfShop;
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
