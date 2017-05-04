//Truck.java

package com.aston.group24.vehicles;

/**
 * Models a truck via Vehicles super class
 * @see Vehicle
 * @version 03.05.2017/1750
 * @author HarmanU, HuzaifahR
 */

public class Truck extends Vehicle {

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
	public Truck(int minTankSize, int maxTankSize, int AOF, double pSize, boolean randomiseTank, boolean randomiseFuel, long seed) 
	{
		super(minTankSize, maxTankSize, AOF, pSize, randomiseTank, randomiseFuel, seed);
		
	}

	/**
	 * Constructor 2 - Instance of the vehicle class with set values
	 * 
	 * @param seed Seed for the random Generator
	 */
	public Truck(long seed) 
	{
		super(seed);
		
		this.randomiseTankSize(30, 40);; 		// set fuel tank size - fuel should be 30 - 40
		this.setSize(2.0);						// set size of car
		this.setAmountOfFuel(0);				// set amount of fuel
	}
}
