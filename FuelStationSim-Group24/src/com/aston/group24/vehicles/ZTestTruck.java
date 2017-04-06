package com.aston.group24.vehicles;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class ZTestTruck {
	
	private Truck truck;

	@Before
	public void setUp() throws Exception {
		
		truck = new Truck(15, 3, 2, 2.0, true, true, 10);
	}
	
	public ArrayList<Vehicle> createCars(int num)
	{
		ArrayList<Vehicle> cars = new ArrayList<Vehicle>();
		
		for (int i = 0; i < num; i++)
		{
			long seed = new Random().nextLong();
			
			Truck car = new Truck(30, 40, 3, 2.0, true, true, seed);
			System.out.println(car.getFuelTankSize());
			System.out.println("--------------------------");
			System.out.println("Tank size:    " + car.getFuelTankSize());
			System.out.println("Current Fuel: " + car.getCurrentFuel());
			
		}
		
		
		return cars;
		
	}

	@Test
	public void testTruck() {
		
		createCars(20);
		assertEquals(truck.getSize(), 2.0, 0.1);
	}

}
