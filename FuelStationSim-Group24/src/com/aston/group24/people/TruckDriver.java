package com.aston.group24.people;

import java.math.BigDecimal;
import java.util.Random;

import com.aston.group24.vehicles.Truck;

public class TruckDriver extends Person{
	private static double happiness = 0.02;	//Global happiness of all truck drivers
	private Random rnd;
	
	public TruckDriver()
	{
		super();
		vehicle = new Truck();
		shopTime = 10; 						//Needs to be randomly generated
		spendingMoney = new BigDecimal(10); //Needs to be randomly generated
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

	@Override
	public boolean wantsToShop()
	{
		return true;
	}
	
}
