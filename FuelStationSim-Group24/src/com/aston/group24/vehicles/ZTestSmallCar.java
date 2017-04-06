package com.aston.group24.vehicles;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class ZTestSmallCar {
	
	private SmallCar sc;

	@Before
	public void setUp() throws Exception {
		
		sc = new SmallCar(5, 4, 1.5, true);
	}
	
	public ArrayList<Vehicle> createCars(int num)
	{
		ArrayList<Vehicle> cars = new ArrayList<Vehicle>();
		
		for (int i = 0; i < num; i++)
		{
			SmallCar car = new SmallCar(8, 2, 2, true);
			System.out.println(car.getFuelTankSize());
		}
		
		
		return cars;
		
	}

	@Test
	public void testSmallCar() {
		
		createCars(20);
		System.out.println(sc.getFuelTankSize());
		assertEquals(sc.getCurrentFuel(), 4);
		assertEquals(sc.getSize(), 1.5, 0.1);
	}

}
