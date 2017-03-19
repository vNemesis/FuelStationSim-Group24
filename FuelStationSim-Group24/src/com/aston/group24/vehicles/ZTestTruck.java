package com.aston.group24.vehicles;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ZTestTruck {
	
	private Truck sc;

	@Before
	public void setUp() throws Exception {
		
		sc = new Truck("Mercades-Benz", 15, 3, 1.5, 1, 0.5);
	}

	@Test
	public void testTruck() {
		
		assertEquals(sc.getmodel(), "Mercades-Benz");
		assertEquals(sc.getFuelTankSize(), 15);
		assertEquals(sc.getCurrentFuel(), 3);
		assertEquals(sc.getSize(), 1.5, 0.1);
		assertEquals(sc.getProbOfOccurance(), 1, 0.1);
		assertEquals(sc.getProbOfShop(), 0.5, 0.1);
	}

}
