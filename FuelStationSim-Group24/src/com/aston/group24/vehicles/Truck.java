package com.aston.group24.vehicles;

public class Truck extends Vehicle {

	public Truck(int minTankSize, int maxTankSize, int AOF, double pSize, boolean randomiseTank, boolean randomiseFuel, long seed) 
	{
		super(minTankSize, maxTankSize, AOF, pSize, randomiseTank, randomiseFuel, seed);
		
		// fuel should be 30 - 40
	}

	public Truck(long seed) 
	{
		super(seed);
		
		this.randomiseTankSize(30, 40);; 		// set fuel tank size
		this.setSize(2.0);				// set size of car
		this.setAmountOfFuel(0);		// set amount of fuel
	}
}
