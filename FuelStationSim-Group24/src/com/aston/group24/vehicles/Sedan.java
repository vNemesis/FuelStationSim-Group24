package com.aston.group24.vehicles;

public class Sedan extends Vehicle {

	public Sedan(int minTankSize, int maxTankSize, int AOF, double pSize, boolean randomiseTank, boolean randomiseFuel, long seed) 
	{
		super(minTankSize, maxTankSize, AOF, pSize, randomiseTank, randomiseFuel, seed);
		
		// fuel should be 12 - 18
	}
	
	public Sedan(long seed) 
	{
		super(seed);
		
		this.randomiseTankSize(12, 18);; 		// set fuel tank size
		this.setSize(1.5);				// set size of car
		this.setAmountOfFuel(0);		// set amount of fuel	
	}	
}
