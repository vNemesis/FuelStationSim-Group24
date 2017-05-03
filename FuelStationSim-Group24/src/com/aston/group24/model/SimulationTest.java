package com.aston.group24.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SimulationTest {

	Simulation sim;
	
	@Before
	public void setUp(){
		sim = new Simulation(4, 2, 0.5, 0.5, false, 1);
	}
	
	@Test
	public void ensureTruckHappinessIsLimited(){
		//TruckHappiness value should not exceed original value (0.02)
		
		//Try to increase TruckHappiness 50 times
		for(int i = 0; i<50; i++){
			sim.increaseTruckHappiness();
		}
		
		//Check that TruckHappiness is 0.02
		assertEquals(0.02, sim.getTruckHappiness(), 0);
	}
	
	@Test
	public void testIncreaseTruckHappiness(){
		//TruckHappiness should be increased by 5% up to the limit
		
		//Decrease TruckHappiness 20 times
		for(int i = 0; i<20; i++){
			sim.decreaseTruckHappiness();
		}
		
		//Increase TruckHappiness 20 times
		for(int i = 0; i<20; i++){
			double TruckHappinessBefore = sim.getTruckHappiness(); //Record TruckHappiness before
			sim.increaseTruckHappiness();
			double increasePercentage = (sim.getTruckHappiness() - TruckHappinessBefore) / sim.getTruckHappiness();
			
			//Check increase is 5%
			assertEquals(0.05, increasePercentage, 0.01);
		}
	}
	
	@Test
	public void testDecreaseTruckHappiness(){
		//TruckHappiness should be increased by 5% up to the limit
		
		//Decrease TruckHappiness 20 times
		for(int i = 0; i<20; i++){
			double TruckHappinessBefore = sim.getTruckHappiness(); //Record TruckHappiness before
			sim.decreaseTruckHappiness();
			double decreasePercentage = (TruckHappinessBefore - sim.getTruckHappiness()) / TruckHappinessBefore;
			
			//Check increase is 5%
			assertEquals(0.2, decreasePercentage, 0.01);
		}
	}
	
	@Test
	public void testExecuteSim(){
		
		sim.runSim(1440, false);
	}


}
