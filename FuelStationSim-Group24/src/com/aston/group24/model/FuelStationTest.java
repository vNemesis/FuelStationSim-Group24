//FuelStationTest.java
package com.aston.group24.model;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import com.aston.group24.people.Person;
import com.aston.group24.people.SmallCarDriver;


/**
 * Fuel Station Test Class
 * 
 * This class will test the Fuel Station class
 * 
 * @see FuelStation
 * 
 * @version 03.05.2017/1752
 * @author HarmanU
 */
public class FuelStationTest {
	
	private FuelStation fs;
	private SmallCarDriver sc1;
	private SmallCarDriver sc2;
	private SmallCarDriver sc3;
	private SmallCarDriver sc4;
	
	LinkedList<FuelPump> pumps;

	@Before
	public void setUp() throws Exception {
		
		fs = new FuelStation(3, 2);
		pumps = fs.getFuelPumps();
		sc1 = new SmallCarDriver(6); 		//size 1.0
		sc2 = new SmallCarDriver(7); 		//size 1.0
		sc3 = new SmallCarDriver(8); 		//size 1.0
		sc4 = new SmallCarDriver(9); 		//size 1.0
	}
	
	/**
	 * Testing method for fuel station sorting
	 * @param v1 vehicle 1
	 * @param v2 vehicle 2
	 * @param v3 vehicle 3
	 */
	protected void testing(Person v1, Person v2, Person v3)
	{
		
		pumps.get(0).addPerson(v1); //1.5
		pumps.get(2).addPerson(v2); //1
		pumps.get(2).addPerson(v3); //1.5
		
		//pump 0 = 2.0 free space
		//pump 1 = 3.0 free space
		//pump 2 - 1.0 free space
	}
	
	/**
	 * Returns a string with the current space available for the pump
	 * @return String containing pumps current space available
	 */
	public String getPumpsCSA()
	{
		StringBuilder sb = new StringBuilder();
		
		for(FuelPump fp : pumps)
		{
			sb.append(fp.getCSA());
			sb.append("\n");
		}
		
		return sb.toString();
	}

	@Test
	public void test() 
	{
		System.out.println("This Test will create 3 Pumps, Sort them and then Print them to console");
		System.out.println("Number of Pumps: " + fs.numberOfPumps());
		testing(sc1, sc2, sc3); // test with 3 small cars
		
		FuelPump pumpWithMostSpace = fs.pumpWithShortestLine(sc4); //find pump with space for sc4
		
		//System.out.println("Pump" + fs.pumpsCSA());
		
		System.out.println("Pump number with the most space before ordering (Pump Id's Start at 0): " + pumpWithMostSpace.getID()); // ID of pump with most space
	}

}
