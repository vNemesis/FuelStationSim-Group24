package com.aston.group24.model;

import org.junit.Before;
import org.junit.Test;

import com.aston.group24.people.SmallCarDriver;

public class FuelStationTest {
	
	private FuelStation fs;
	private SmallCarDriver sc1;
	private SmallCarDriver sc2;
	private SmallCarDriver sc3;
	private SmallCarDriver sc4;

	@Before
	public void setUp() throws Exception {
		
		fs = new FuelStation(3, 2);
		sc1 = new SmallCarDriver(6); 		//size 1.0
		sc2 = new SmallCarDriver(7); 		//size 1.0
		sc3 = new SmallCarDriver(8); 		//size 1.0
		sc4 = new SmallCarDriver(9); 		//size 1.0
	}

	@Test
	public void test() 
	{
		System.out.println("This Test will create 3 Pumps, Sort them and then Print them to console");
		System.out.println("Number of Pumps: " + fs.numberOfPumps());
		fs.testing(sc1, sc2, sc3); // test with 3 small cars
		
		FuelPump pumpWithMostSpace = fs.pumpWithShortestLine(sc4); //find pump with space for sc4
		
		//System.out.println("Pump" + fs.pumpsCSA());
		
		System.out.println("Pump number with the most space before ordering (Pump Id's Start at 0): " + pumpWithMostSpace.getID()); // ID of pump with most space
	}

}
