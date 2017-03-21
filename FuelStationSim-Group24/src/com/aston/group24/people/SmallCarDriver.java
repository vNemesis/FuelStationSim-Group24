package com.aston.group24.people;

import java.math.BigDecimal;
import java.util.Random;

import com.aston.group24.vehicles.SmallCar;

public class SmallCarDriver extends Person{
	private Random rnd;
	
	public SmallCarDriver()
	{
		super();
		vehicle = new SmallCar();
		shopTime = 10; 						//TODO Needs to be randomly generated (2-4 minutes (12-24 ticks))
		spendingMoney = new BigDecimal(10); //TODO Needs to be randomly generated (£5-£10)
	}
	
	@Override
	public boolean wantsToShop()
	{
		//TODO
		//Probability of 0.3 if the refill is done is <5 minutes (30 ticks)
		return true;
	}
	
}
