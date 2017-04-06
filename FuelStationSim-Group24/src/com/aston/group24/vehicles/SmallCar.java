package com.aston.group24.vehicles;

public class SmallCar extends Vehicle {

	public SmallCar(int tankSize, int AOF, double pSize, boolean randomiseTank) 
	{
		super(tankSize, AOF, pSize);
		
		// if true randomise tank size
		if (randomiseTank)
		{
			// Randomise the tank size between 7 and 9 gallons
			this.randomiseTankSize(7, 9);
		}
	}
	
	

}
