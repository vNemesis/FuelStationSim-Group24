package com.aston.group24.people;

import java.math.BigDecimal;
import java.util.Random;

import com.aston.group24.vehicles.Truck;

public class TruckDriver extends Person{
	private Random rnd;
	private static double happiness = 0.02;	//Global happiness of all truck drivers
	
	
	public TruckDriver()
	{
		super();
		vehicle = new Truck();
		shopTime = 10; 						//TODO Needs to be randomly generated (4-6 minutes (24-36 ticks))
		spendingMoney = new BigDecimal(10); //TODO Needs to be randomly generated (£15 - £20)
	}
	
	@Override
	public boolean wantsToShop()
	{
		//TODO
		//Will always shop if the refill took 8 minutes or less
		return true;
	}
	
	//Static methods
	public static void increaseHappiness()
	{
		
	}
	
	public static void decreaseHappiness()
	{
		
	}
	
	public static double getHappiness()
	{
		return happiness;
	}
	
}
