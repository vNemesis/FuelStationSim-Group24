package com.aston.group24.vehicles;

public class Motorbike extends Vehicle {
	
	public Motorbike(int minTankSize, int maxTankSize, int AOF, double pSize, boolean randomiseTank, boolean randomiseFuel, long seed) 
	{
		super(minTankSize, maxTankSize, AOF, pSize, randomiseTank, randomiseFuel, seed);
		
		// fuel should be 5
	}
	
	public Motorbike(long seed) 
	{
		super(seed);
		
		this.setFuelTankSize(5); 		// set fuel tank size
		this.setSize(0.75);				// set size of car
		this.setAmountOfFuel(0);		// set amount of fuel
	}
}
