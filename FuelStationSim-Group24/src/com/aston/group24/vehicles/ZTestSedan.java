package com.aston.group24.vehicles;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class ZTestSedan {
	
	private Sedan sd;

	@Before
	public void setUp() throws Exception {
		
		sd = new Sedan(8, 2, 2, true);
	}
	
	public ArrayList<Vehicle> createCars(int num)
	{
		ArrayList<Vehicle> cars = new ArrayList<Vehicle>();
		
		for (int i = 0; i < num; i++)
		{
			Sedan car = new Sedan(8, 2, 2, true);
			System.out.println(car.getFuelTankSize());
		}
		
		
		return cars;
		
	}

	@Test
	public void testSedan() {
		
		createCars(20); // Test fuel tank randomiser
		System.out.println(sd.getFuelTankSize());
		assertEquals(sd.getCurrentFuel(), 2);
		assertEquals(sd.getSize(), 2, 0.1);
	}

}
