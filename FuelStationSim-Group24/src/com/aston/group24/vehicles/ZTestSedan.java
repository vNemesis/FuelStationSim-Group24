package com.aston.group24.vehicles;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class ZTestSedan {
	
	private Sedan sd;

	@Before
	public void setUp() throws Exception {
		
		sd = new Sedan(12, 18, 7, 1.5, true, true, 10);
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
		System.out.println(sd.getFuelTankSize());
		assertEquals(sd.getSize(), 1.5, 0.1);
	}

}
