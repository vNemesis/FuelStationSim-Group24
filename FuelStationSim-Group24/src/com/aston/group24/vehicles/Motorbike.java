//Motorbike.java

package com.aston.group24.vehicles;

/**
 * Models a motorbike via Vehicles super class
 * @see Vehicle
 * @version 03.05.2017/1750 
 * @author HarmanU, HuzaifahR
 */

public class Motorbike extends Vehicle {
	
	/**
	 * Constructor 1 - Instance of the vehicle class with dynamic values
	 * 
	 * @param minTankSize Minimum fuel tank size
	 * @param maxTankSize Maximum fuel tank size
	 * @param AOF Amount of fuel to start with
	 * @param pSize Size of vehicle
	 * @param randomiseTank Whether or not to randomise the fuel tanks size between the min and max values
	 * @param randomiseFuel Whether or not to randomise the amount of fuel the vehicle starts with
	 * @param seed Seed for the random Generator
	 */
	public Motorbike(int minTankSize, int maxTankSize, int AOF, double pSize, boolean randomiseTank, boolean randomiseFuel, long seed) 
	{
		super(minTankSize, maxTankSize, AOF, pSize, randomiseTank, randomiseFuel, seed);
		
	}
	
	/**
	 * Constructor 2 - Instance of the vehicle class with set values
	 * 
	 * @param seed Seed for the random Generator
	 */
	public Motorbike(long seed) 
	{
		super(seed);
		
		this.setFuelTankSize(5); 		// set fuel tank size - fuel should be 5
		this.setSize(0.75);				// set size of car
		this.setAmountOfFuel(0);		// set amount of fuel
	}
}
