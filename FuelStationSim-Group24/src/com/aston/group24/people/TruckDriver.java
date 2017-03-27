package com.aston.group24.people;

import java.math.BigDecimal;
import java.util.Random;

import com.aston.group24.vehicles.Truck;

public class TruckDriver extends Person{
	private Random rnd;
	private static final double INITIAL_HAPPINESS = 0.02;
	private static double happiness = INITIAL_HAPPINESS;	//Global happiness of all truck drivers
	
	
	public TruckDriver(long seed)
	{
		super(seed);
		vehicle = new Truck();
		shopTime = rnd.nextInt(13) + 24; 	//Needs to be randomly generated (4-6 minutes (24-36 ticks))
		spendingMoney = new BigDecimal((rnd.nextInt(501) + 1500) / 100.00); //Needs to be randomly generated (£15 - £20)
	}
	
	@Override
	public boolean wantsToShop()
	{
		//Will always shop if the refill took 8 minutes or less (48 ticks)
		if(timeAtStation <= 48) return true;
		else return false;
	}
	
	//Static methods
	public static void increaseHappiness()
	{
		//Happy truck driver increases happiness by 5%, up to the original value
		happiness = Math.min((happiness * 1.05), INITIAL_HAPPINESS);
	}
	
	public static void decreaseHappiness()
	{
		//Unhappy truck driver reduces happiness by 20%
		happiness = happiness * 0.8;
	}
	
	public static double getHappiness()
	{
		return happiness;
	}
	
}
