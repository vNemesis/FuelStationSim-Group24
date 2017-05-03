//TruckDriver.java
package com.aston.group24.people;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.aston.group24.vehicles.Truck;

/**
 * Model of a truck driver
 * @author JShorthouse
 * @version 20.04.2017/2055
 */
public class TruckDriver extends Person{
	
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
}
