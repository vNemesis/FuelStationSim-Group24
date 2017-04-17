package com.aston.group24.people;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

/**
 * MotorbikeDriverTest.java
 * Unit test for MotorbikeDriver class
 * @author James Shorthouse
 * @version 0.1
 */
public class MotorbikeDriverTest {
	
	MotorbikeDriver[] drivers;
	Random rnd;
	
	@Before
	public void setup(){
		//Generate array of drivers
		
		rnd = new Random(22);
		
		drivers = new MotorbikeDriver[128];
		for(int i = 0; i<drivers.length ; i++){
			drivers[i] = new MotorbikeDriver(rnd.nextLong()); //Use random seed for each driver
		}
	}
	
	@Test
	public void ensureNeverShops(){
		//When first created
		for(MotorbikeDriver driver : drivers){
			assertEquals(false, driver.wantsToShop());
		}
		
		//Simulate 1000 ticks passing
		for(MotorbikeDriver driver: drivers){
			for(int i = 0; i<1000; i++){
				driver.incrementTime();
			}
		}
		
		//Check again
		for(MotorbikeDriver driver : drivers){
			assertEquals(false, driver.wantsToShop());
		}
	}
	
	@Test
	public void testTimeAtStation(){
		//Iterate through array of drivers
		for(MotorbikeDriver driver : drivers){
			int time = rnd.nextInt(501); //Generate random time 0 - 500
			for(int i=0 ; i<time ; i++){ //Simulate this many ticks
				driver.incrementTime(); 
			}
			assertEquals(time, driver.getTimeAtStation()); //Check that time at station equals the simulated time
		}
	}
	
	@Test
	public void testGetRefulled(){
		//Iterate through drivers
		for(MotorbikeDriver driver : drivers){
			boolean refuelled = rnd.nextBoolean(); //Generate true or false visited value
			driver.setRefuelled(refuelled);
			assertEquals(refuelled, driver.getRefuelled());
		}
	}
	
	@Test
	public void testGetVisitedShop(){
		//Iterate through drivers
		for(MotorbikeDriver driver : drivers){
			boolean visited = rnd.nextBoolean(); //Generate true or false visited value
			driver.setVisitedShop(visited);
			assertEquals(visited, driver.getVisitedShop());
		}
	}
	
	@Test
	public void testMoneySpent(){
		//Randomly set some drivers to have refuelled
		for(MotorbikeDriver driver : drivers){
			boolean refuelled = rnd.nextBoolean(); //Generate true or false visited value
			driver.setRefuelled(refuelled);
		}
		
		//Test money is calculated correctly
		for(MotorbikeDriver driver : drivers){
			if(driver.getRefuelled()){
				assertEquals(driver.getVehicle().getRefuelCost(), driver.getMoneySpent()); //If refuelled check money spent is equal to refuel cost
			} else {
				assertEquals(new BigDecimal(0.00), driver.getMoneySpent()); //Else check money spent is equal to zero
			}
		}
	}
	
	@Test
	public void testMoneyLost(){
		//Randomly set some drivers to have refuelled
		for(MotorbikeDriver driver : drivers){
			boolean visited = rnd.nextBoolean(); //Generate true or false visited value
			driver.setVisitedShop(visited);
		}
		
		//Test money is calculated correctly
		for(MotorbikeDriver driver : drivers){
			if(driver.getRefuelled()){
				assertEquals(new BigDecimal(0), driver.getMoneyLost()); //If refuelled check money lost is equal to zero
			} else {
				assertEquals(driver.getVehicle().getRefuelCost(), driver.getMoneyLost()); //Else check money lost is equal to refuel cost
			}
		}
	}
	

}
