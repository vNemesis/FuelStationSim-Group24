package com.aston.group24.vehicles;

public class SmallCar extends Vehicle {

	public SmallCar(int minTankSize, int maxTankSize, int AOF, double pSize, boolean randomiseTank, boolean randomiseFuel, long seed) 
	{
		super(minTankSize, maxTankSize, AOF, pSize, randomiseTank, randomiseFuel, seed);
		
		// fuel should be 7 - 9
	}
	
	public SmallCar(long seed) 
	{
		super(seed);
		
		this.randomiseTankSize(7, 9);; 		// set fuel tank size
		this.setSize(1.0);				// set size of car
		this.setAmountOfFuel(0);		// set amount of fuel
	}
}
