package com.aston.group24.people;

import java.math.BigDecimal;
import java.util.Random;

import com.aston.group24.model.Simulation;
import com.aston.group24.vehicles.SmallCar;

public class SmallCarDriver extends Person{
	
	public SmallCarDriver(long seed)
	{
		super(seed);
		vehicle = new SmallCar();
		shopTime = rnd.nextInt(13) + 12;	//2-4 minutes (12-24 ticks)
		spendingMoney = new BigDecimal((rnd.nextInt(501) + 500) / 100.00); //£5.00-£10.00
	}
	
	@Override
	public boolean wantsToShop()
	{
		//Probability of 0.3 if the refill is done is <5 minutes (30 ticks)
		if(timeAtStation < 30 && rnd.nextInt(10) < 3) return true;
		else return false;
		
	}
	
}
