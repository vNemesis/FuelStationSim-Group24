package com.aston.group24.people;

import java.math.BigDecimal;
import com.aston.group24.vehicles.Motorbike;

public class MotorbikeDriver extends Person{

	public MotorbikeDriver(long seed)
	{
		super(seed);
		vehicle = new Motorbike();
		shopTime = 0; 						//Motorbike drivers never visit the shop
		spendingMoney = new BigDecimal(0); 	//Motorbike drivers never visit the shop
	}

	@Override
	public boolean wantsToShop()
	{
		//Always false
		return false;
	}
}
