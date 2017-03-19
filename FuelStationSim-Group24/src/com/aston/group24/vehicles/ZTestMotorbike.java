package com.aston.group24.vehicles;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ZTestMotorbike {
	
	private Motorbike sc;

	@Before
	public void setUp() throws Exception {
		
		sc = new Motorbike("Suzuki", 4, 2, 1.5, 1, 0.5);
	}

	@Test
	public void testMotorbike() {
		
		assertEquals(sc.getmodel(), "Suzuki");
		assertEquals(sc.getFuelTankSize(), 4);
		assertEquals(sc.getCurrentFuel(), 2);
		assertEquals(sc.getSize(), 1.5, 0.1);
		assertEquals(sc.getProbOfOccurance(), 1, 0.1);
		assertEquals(sc.getProbOfShop(), 0.5, 0.1);
	}

}
