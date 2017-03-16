package com.aston.group24.vehicles;

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
	 * Fill the car with fuel
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
