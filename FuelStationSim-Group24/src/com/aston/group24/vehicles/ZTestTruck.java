package com.aston.group24.vehicles;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class ZTestTruck {
	
	private Truck t1;
	private Truck t2;
	private Truck t3;

	@Before
	public void setUp() throws Exception {
		
		// instantiate 2 vehicles with 2 separate seeds to test randomisation logic
		t1 = new Truck(30, 40, 2, 2.0, true, false, 12);
		t2 = new Truck(30, 40, 2, 2.0, true, true, 11);
		t3 = new Truck(10);
	}
	
	public ArrayList<Vehicle> createTrucks(int num)
	{
		ArrayList<Vehicle> trucks = new ArrayList<Vehicle>();
		
		for (int i = 0; i < num; i++)
		{
			long seed = new Random().nextLong();
			
			Truck truck = new Truck(30, 40, 3, 2.0, true, true, seed);
			System.out.println(truck.getFuelTankSize());
			System.out.println("--------------------------");
			System.out.println("Tank size:    " + truck.getFuelTankSize());
			System.out.println("Current Fuel: " + truck.getCurrentFuel());
			
		}
		
		
		return trucks;
		
	}

	@Test
	public void testTruck() {
		
		createTrucks(20);
		System.out.println();
		System.out.println();
		System.out.println("--------------------------");
		System.out.println("Randomised tank size for Truck 1: " + t1.getFuelTankSize());
		System.out.println("--------------------------");
		System.out.println("Randomised tank size for Truck 2: " + t2.getFuelTankSize());
		System.out.println("--------------------------");
		assertEquals(t1.getFuelTankSize(), 40); // test if randomised fuel tank is correct
		assertEquals(t2.getFuelTankSize(), 35); // test if randomised fuel tank is correct
		assertEquals(t1.getSize(), 2.0, 0.1); // Tests size of vehicle is correct
		assertEquals(t1.getCurrentFuel(), 2); // test fuel is 2
		assertTrue(t2.getCurrentFuel() >= 0 && t2.getCurrentFuel() <= 40); // test fuel is between 0 and 40
		
		System.out.println();
		System.out.println();
		System.out.println("--------------------------");
		System.out.println("Truck 3 tank size is: " + t3.getFuelTankSize());
		System.out.println("Truck 3 fuel is: " + t3.getCurrentFuel());
		System.out.println("Truck 3 size is: " + t3.getSize());
		System.out.println("--------------------------");
		assertTrue(t3.getFuelTankSize() >= 30 && t3.getFuelTankSize() <= 40); // test tank size is between 30 and 40
		assertEquals(t3.getCurrentFuel(), 0); // test current fuel is 0
		assertEquals(t3.getSize(), 2.0, 0.1); // test size is 2.0
		
		
	}

}
