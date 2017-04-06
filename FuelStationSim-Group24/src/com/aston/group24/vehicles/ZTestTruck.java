package com.aston.group24.vehicles;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class ZTestTruck {
	
	private Truck truck;

	@Before
	public void setUp() throws Exception {
		
		truck = new Truck(15, 3, 2, true);
	}
	
	public ArrayList<Vehicle> createCars(int num)
	{
		ArrayList<Vehicle> cars = new ArrayList<Vehicle>();
		
		for (int i = 0; i < num; i++)
		{
			Truck car = new Truck(15, 3, 2.0, true);
			System.out.println(car.getFuelTankSize());
		}
		
		
		return cars;
		
	}

	@Test
	public void testTruck() {
		
		createCars(20);
		assertEquals(truck.getCurrentFuel(), 3);
		assertEquals(truck.getSize(), 2.0, 0.1);
	}

}
