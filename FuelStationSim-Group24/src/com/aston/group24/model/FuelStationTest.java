package com.aston.group24.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.aston.group24.vehicles.SmallCar;

public class FuelStationTest {
	
	private FuelStation fs;
	private SmallCar sc1;
	private SmallCar sc2;
	private SmallCar sc3;
	private SmallCar sc4;

	@Before
	public void setUp() throws Exception {
		
		fs = new FuelStation(3);
		sc1 = new SmallCar( "Honda", 6, 2, 1.5, 0.7, 0.5); 		//size 1.5
		sc2 = new SmallCar( "Seat", 7, 3, 1.0, 0.7, 0.5); 		//size 1
		sc3 = new SmallCar( "Ford", 8, 2, 1.5, 0.7, 0.5); 		//size 1.5
		sc4 = new SmallCar( "Dodge", 8, 2, 1.0, 0.7, 0.5); 		//size 1.0
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
