package com.aston.group24.people;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.aston.group24.vehicles.Truck;

/**
 * TruckDriver.java
 * Model of a truck driver
 * @author James Shorthouse
 * @version 0.1
 */
public class TruckDriver extends Person{
	private static final double INITIAL_HAPPINESS = 0.02;
	private static double happiness = INITIAL_HAPPINESS;	//Global happiness of all truck drivers
	
	/**
	 * Constructor
	 * @param seed Seed used for random generation
	 */
	public TruckDriver(long seed)
	{
		super(seed);
		vehicle = new Truck(seed);
		shopTime = rnd.nextInt(13) + 24; //4-6 minutes (24-36 ticks)
		spendingMoney = new BigDecimal((rnd.nextInt(501) + 1500) / 100.00).setScale(2, RoundingMode.HALF_UP); //£15.00-£20.00
	}
	
	/**
	 * Returns whether the person wants to visit the shop at the current point in the simulation
	 * @return true if the person wants to visit the shop
	 */
	@Override
	public boolean wantsToShop()
	{
		//Will always shop if the refill took 8 minutes or less (48 ticks)
		if(timeAtStation <= 48) return true;
		else return false;
	}
	
	//Static methods
	
	/**
	 * Increases the global happiness of all truck drivers
	 */
	public static void increaseHappiness()
	{
		//Happy truck driver increases happiness by 5%, up to the original value
		happiness = Math.min((happiness * 1.05), INITIAL_HAPPINESS);
		System.out.println(happiness);
	}
	
	/**
	 * Decreases the global happiness of all truck drivers
	 */
	public static void decreaseHappiness()
	{
		//Unhappy truck driver reduces happiness by 20%
		happiness = happiness * 0.8;
	}
	
	/**
	 * Returns the current global happiness of truck drivers
	 * @return happiness value
	 */
	public static double getHappiness()
	{
		return happiness;
	}
	
}
