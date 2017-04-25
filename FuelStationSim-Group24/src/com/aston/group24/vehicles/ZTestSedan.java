package com.aston.group24.vehicles;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class ZTestSedan {
	
	private Sedan sd1;
	private Sedan sd2;
	private Sedan sd3;
	
	@Before
	public void setUp() throws Exception {
		
		// instantiate 2 vehicles with 2 separate seeds to test randomisation logic
		sd1 = new Sedan(12, 18, 7, 1.5, true, false, 10);
		sd2 = new Sedan(12, 18, 7, 1.5, true, true, 11);
		sd3 = new Sedan(10);
	}
	
	public ArrayList<Vehicle> createCars(int num)
	{
		ArrayList<Vehicle> cars = new ArrayList<Vehicle>();
		
		for (int i = 0; i < num; i++)
		{
			long seed = new Random().nextLong();
			
			Sedan car = new Sedan(12, 18, 7, 1.5, true, true, seed);
			System.out.println("--------------------------");
			System.out.println("Tank size:    " + car.getFuelTankSize());
			System.out.println("Current Fuel: " + car.getCurrentFuel());
		}
		
		
		return cars;
		
	}

	@Test
	public void testSedan() {
		
		createCars(20); // Test fuel tank randomiser
		System.out.println();
		System.out.println();
		System.out.println("--------------------------");
		System.out.println("Randomised tank size for Sedan 1: " + sd1.getFuelTankSize());
		System.out.println("--------------------------");
		System.out.println("Randomised tank size for Sedan 2: " + sd2.getFuelTankSize());
		assertEquals(sd1.getFuelTankSize(), 14); // test if randomised fuel tank is correct
		assertEquals(sd2.getFuelTankSize(), 17); // test if randomised fuel tank is correct
		assertEquals(sd1.getSize(), 1.5, 0.1); // test size is 1.5
		assertEquals(sd1.getCurrentFuel(), 7); // test fuel is 7
		assertTrue(sd2.getCurrentFuel() >= 0 && sd2.getCurrentFuel() <= 18); // test fuel is between 0 and 18
		
		System.out.println();
		System.out.println();
		System.out.println("--------------------------");
		System.out.println("Sedan 3 tank size is: " + sd3.getFuelTankSize());
		System.out.println("Sedan 3 fuel is: " + sd3.getCurrentFuel());
		System.out.println("Sedan 3 size is: " + sd3.getSize());
		System.out.println("--------------------------");
		assertTrue(sd3.getFuelTankSize() >= 12 && sd3.getFuelTankSize() <= 18); // test tank size is between 12 and 18
		assertEquals(sd3.getCurrentFuel(), 0); // test current fuel is 0
		assertEquals(sd3.getSize(), 1.5, 0.1); // test size is 1.5
	}

}
