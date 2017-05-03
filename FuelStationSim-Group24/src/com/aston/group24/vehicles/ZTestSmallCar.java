package com.aston.group24.vehicles;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for Small Car
 * @see SmallCar
 * @version - 03.05.2017/1750 
 * @author HarmanU
 */

public class ZTestSmallCar {
	
	private SmallCar sc1;
	private SmallCar sc2;
	private SmallCar sc3;

	@Before
	public void setUp() throws Exception {
		
		// instantiate 2 vehicles with 2 separate seeds to test randomisation logic
		sc1 = new SmallCar(7, 9, 4, 1.0, true, false, 10);
		sc2 = new SmallCar(7, 9, 4, 1.0, true, true, 11);
		sc3 = new SmallCar(10);
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
		System.out.println();
		System.out.println();
		System.out.println("--------------------------");
		System.out.println("Randomised tank size for Small car 1: " + sc1.getFuelTankSize());
		System.out.println("--------------------------");
		System.out.println("Randomised tank size for Small car 2: " + sc2.getFuelTankSize());
		assertEquals(sc1.getFuelTankSize(), 7); // test if randomised fuel tank is correct
		assertEquals(sc2.getFuelTankSize(), 9); // test if randomised fuel tank is correct
		assertEquals(sc1.getSize(), 1.0, 0.1); // test size is 1.0
		assertEquals(sc1.getCurrentFuel(), 4); // test fuel is 4
		assertTrue(sc2.getCurrentFuel() >= 0 && sc2.getCurrentFuel() <= 9); // test fuel is between 0 and 9
		
		System.out.println();
		System.out.println();
		System.out.println("--------------------------");
		System.out.println("Small car 3 tank size is: " + sc3.getFuelTankSize());
		System.out.println("Small car 3 fuel is: " + sc3.getCurrentFuel());
		System.out.println("Small car 3 size is: " + sc3.getSize());
		System.out.println("--------------------------");
		assertTrue(sc3.getFuelTankSize() >= 7 && sc3.getFuelTankSize() <= 9); // test tank size is between 7 and 9
		assertEquals(sc3.getCurrentFuel(), 0); // test current fuel is 0
		assertEquals(sc3.getSize(), 1.0, 0.1); // test size is 1.0
	}

}
