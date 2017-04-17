package com.aston.group24.people;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.aston.group24.vehicles.SmallCar;

/**
 * SmallCarDriver.java
 * Model of a small car driver
 * @author James Shorthouse
 * @version 0.1
 */
public class SmallCarDriver extends Person{
	
	/**
	 * Constructor
	 * @param seed Seed used for random generation
	 */
	public SmallCarDriver(long seed)
	{
		super(seed);
		vehicle = new SmallCar(seed);
		shopTime = rnd.nextInt(13) + 12; //2-4 minutes (12-24 ticks)
		spendingMoney = new BigDecimal((rnd.nextInt(501) + 500) / 100.00).setScale(2, RoundingMode.HALF_UP); //£5.00-£10.00
	}
	
	/**
	 * Returns whether the person wants to visit the shop at the current point in the simulation
	 * @return true if the person wants to visit the shop
	 */
	@Override
	public boolean wantsToShop()
	{
		//Probability of 0.3 if the refill is done is <5 minutes (30 ticks)
		if(timeAtStation < 30 && rnd.nextInt(10) < 3) return true;
		else return false;
		
	}
	
}
