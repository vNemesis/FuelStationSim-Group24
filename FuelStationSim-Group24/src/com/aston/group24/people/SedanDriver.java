package com.aston.group24.people;

import java.math.BigDecimal;
import java.util.Random;

import com.aston.group24.vehicles.Sedan;

public class SedanDriver extends Person{
	private Random rnd;
	private static 

	public SedanDriver() 
	{
		super();
		vehicle = new Sedan();
		shopTime = 10; 						//TODO Needs to be randomly generated (2-5 minutes (12-30 ticks))
		spendingMoney = new BigDecimal(10); //TODO Needs to be randomly generated (£8-£16)
		 
	}
	@Override
	public boolean wantsToShop() 
	{
		//TODO
		//Probability of 0.4 if the refill is done in <10 minutes (60 ticks)
		return false;
	}

}
