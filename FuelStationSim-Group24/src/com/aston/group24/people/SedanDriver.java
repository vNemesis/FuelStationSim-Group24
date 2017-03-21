package com.aston.group24.people;

import java.math.BigDecimal;

import com.aston.group24.vehicles.Sedan;

public class SedanDriver extends Person{

	public SedanDriver() 
	{
		super();
		vehicle = new Sedan();
		shopTime = 10; 						//TODO Needs to be randomly generated 
		spendingMoney = new BigDecimal(10); //TODO Needs to be randomly generated
		 
	}
	@Override
	public boolean wantsToShop() 
	{
		// TODO Auto-generated method stub
		return false;
	}

}
