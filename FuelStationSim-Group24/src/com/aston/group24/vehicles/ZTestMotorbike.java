//ZTestMotorbike.java

package com.aston.group24.vehicles;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for Motorbike
 * @see Motorbike
 * @version - 03.05.2017/1750 
 * @author HarmanU
 */

public class ZTestMotorbike {
	
	private Motorbike mb;

	@Before
	public void setUp() throws Exception {
		
		mb = new Motorbike(5, 2, 0, 1.5, true, false, 10);
	}

	@Test
	public void testMotorbike() {
		
		assertEquals(mb.getFuelTankSize(), 5);
		assertEquals(mb.getCurrentFuel(), 2);
		assertEquals(mb.getSize(), 1.5, 0.1);

	}

}
