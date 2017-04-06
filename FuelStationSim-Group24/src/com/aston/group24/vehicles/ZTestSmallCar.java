package com.aston.group24.vehicles;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class ZTestSmallCar {
	
	private SmallCar sc;

	@Before
	public void setUp() throws Exception {
		
		sc = new SmallCar(7, 9, 4, 1.5, true, true, 10);
	}
	
	public ArrayList<Vehicle> createCars(int num)
	{
		ArrayList<Vehicle> cars = new ArrayList<Vehicle>();
		
		for (int i = 0; i < num; i++)
		{
			long seed = new Random().nextLong();
			
			SmallCar car = new SmallCar(7, 9, 4, 2, true, true, seed);
			System.out.println("--------------------------");
			System.out.println("Tank size:    " + car.getFuelTankSize());
			System.out.println("Current Fuel: " + car.getCurrentFuel());
		}
		
		
		return cars;
		
	}

	@Test
	public void testSmallCar() {
		
		createCars(20);
		System.out.println(sc.getFuelTankSize());
		assertEquals(sc.getSize(), 1.5, 0.1);
	}

}
