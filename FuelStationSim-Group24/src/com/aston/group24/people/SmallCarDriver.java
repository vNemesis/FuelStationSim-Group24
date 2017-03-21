package com.aston.group24.people;

import java.math.BigDecimal;

import com.aston.group24.vehicles.SmallCar;

public class SmallCarDriver extends Person{

	public SmallCarDriver()
	{
		super();
		vehicle = new SmallCar();
		shopTime = 10; 						//Needs to be randomly generated
		spendingMoney = new BigDecimal(10); //Needs to be randomly generated
	}
	
	@Override
	public boolean wantsToShop()
	{
		return true;
	}
	
}
