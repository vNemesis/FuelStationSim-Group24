package com.aston.group24.people;

import java.math.BigDecimal;
import com.aston.group24.vehicles.Motorbike;

public class MotorbikeDriver extends Person{

	public MotorbikeDriver()
	{
		super();
		vehicle = new Motorbike();
		shopTime = 10; 						//Needs to be randomly generated
		spendingMoney = new BigDecimal(10); //Needs to be randomly generated
	}

	@Override
	public boolean wantsToShop() {
		return false;
	}
}
