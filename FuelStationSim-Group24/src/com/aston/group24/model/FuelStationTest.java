package com.aston.group24.model;

import static org.junit.Assert.*;

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
		sc1 = new SmallCarDriver(6); 		//size 1.5
		sc2 = new SmallCarDriver(7); 		//size 1
		sc3 = new SmallCarDriver(8); 		//size 1.5
		sc4 = new SmallCarDriver(9); 		//size 1.0
	}

	@Test
	public void test() 
	{

		fs.testing(sc1, sc2, sc3);
		
		FuelPump pumpWithMostSpace = fs.pumpWithShortestLine(sc4); //find pump with space for sc4
		
		System.out.println(fs.numberOfPumps());
		System.out.println(fs.pumpsCSA());
		
		System.out.println(pumpWithMostSpace.getID()); // ID of pump with most space
	}

}
