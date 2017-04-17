package com.aston.group24.people;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;


/**
 * SmallCarDriverTest.java
 * Unit test for SmallCarDriver class
 * @author James Shorthouse
 * @version 0.1
 */
public class SmallCarDriverTest {

	SmallCarDriver[] drivers;
	Random rnd;
	
	@Before
	public void setup(){
		//Generate array of drivers
		
		rnd = new Random(22);
		
		drivers = new SmallCarDriver[4096];
		for(int i = 0; i<drivers.length ; i++){
			drivers[i] = new SmallCarDriver(rnd.nextLong()); //Use random seed for each driver
		}
	}
	
	@Test
	public void ensureShopTimeUpToLimits(){
		//Drivers should shop for 2-4 minutes (12-24 ticks)
		
		//Create min and maxTime and set to the value of the first driver
		int maxTime = drivers[0].getShopTime();
		int minTime = drivers[0].getShopTime();
		
		//Iterate through the rest of the drivers and check their times
		for(int i = 1; i<drivers.length;i++){
			if(drivers[i].getShopTime() > maxTime) maxTime = drivers[i].getShopTime();
			else if(drivers[i].getShopTime() < minTime) minTime = drivers[i].getShopTime();
		}
		
		//Ensure that min and max are equal to the limits
		assertEquals(12, minTime);
		assertEquals(24, maxTime);	
	}
	
	@Test
	public void ensureTillTimeUpToLimits(){
		//Drivers should be at the till for 2-3 minutes (12-18 ticks)
		
		//Create min and maxTime and set to the value of the first driver
		int maxTime = drivers[0].getTillTime();
		int minTime = drivers[0].getTillTime();
		
		//Iterate through the rest of the drivers and check their times
		for(int i = 1; i<drivers.length;i++){
			if(drivers[i].getTillTime() > maxTime) maxTime = drivers[i].getTillTime();
			else if(drivers[i].getTillTime() < minTime) minTime = drivers[i].getTillTime();
		}
		
		//Ensure that min and max are equal to the limits
		assertEquals(12, minTime);
		assertEquals(18, maxTime);	
	}
	
	@Test
	public void ensureSpendingMoneyUpToLimits(){
		//Drivers should have £5.00-£10.00 to spend
		
		//Create min and max money and set to value of first driver
		BigDecimal minMoney = drivers[0].getSpendingMoney();
		BigDecimal maxMoney = drivers[0].getSpendingMoney();
		
		//Iterate through the rest of the drivers and check their money
		
		
		for(int i = 1; i<drivers.length;i++){
			if(drivers[i].getSpendingMoney().compareTo(maxMoney) > 0) maxMoney = drivers[i].getSpendingMoney();
			else if(drivers[i].getSpendingMoney().compareTo(minMoney) < 0) minMoney = drivers[i].getSpendingMoney();
		}
			
		//Ensure that min and max are equal to the limits
		assertEquals(new BigDecimal(5.00).setScale(2, RoundingMode.HALF_UP), minMoney);
		assertEquals(new BigDecimal(10.00).setScale(2, RoundingMode.HALF_UP), maxMoney);
	}
	
	@Test
	public void testTimeAtStation(){
		//Iterate through array of drivers
		for(SmallCarDriver driver : drivers){
			int time = rnd.nextInt(501); //Generate random time 0 - 500
			for(int i=0 ; i<time ; i++){ //Simulate this many ticks
				driver.incrementTime(); 
			}
			assertEquals(time, driver.getTimeAtStation()); //Check that time at station equals the simulated time
		}
	}
	
	@Test
	public void checkWontShopOverTime(){
		//Driver should not shop if time in station is >= 5 minutes (30 ticks)
		
		//Iterate through drivers and simulate 30 - 530 ticks in station
		for(SmallCarDriver driver : drivers){
			for(int i = 0 ; i< 30 + rnd.nextInt(501) ; i++){
				driver.incrementTime();
			}
		}
		
		//Check that none want to shop
		for(SmallCarDriver driver : drivers){
			assertEquals(false, driver.wantsToShop());
		}
	}
	
	@Test
	public void checkWillShopWithProbabilityWithinTime(){
		//Driver should shop with 0.3 probability when time in station is < 5 minutes (0-29 ticks)
		
		//Iterate through drivers and simulate 0 - 29 ticks in station
		for(SmallCarDriver driver : drivers){
			for(int i = 0 ; i< rnd.nextInt(30) ; i++){
				driver.incrementTime();
			}
		}
		
		int wantsToShopCount = 0;
		
		//Iterate though drivers and count how many want to shop
		for(SmallCarDriver driver : drivers){
			if(driver.wantsToShop()) wantsToShopCount++;
		}
		
		//Calculate probability
		double shopProbability = (double) wantsToShopCount / drivers.length;
		
		assertEquals(0.300, shopProbability, 0.01);
	}
	
	
	@Test
	public void testGetRefulled(){
		//Iterate through drivers
		for(SmallCarDriver driver : drivers){
			boolean refuelled = rnd.nextBoolean(); //Generate true or false visited value
			driver.setRefuelled(refuelled);
			assertEquals(refuelled, driver.getRefuelled());
		}
	}
	
	@Test
	public void testGetVisitedShop(){
		//Iterate through drivers
		for(SmallCarDriver driver : drivers){
			boolean visited = rnd.nextBoolean(); //Generate true or false visited value
			driver.setVisitedShop(visited);
			assertEquals(visited, driver.getVisitedShop());
		}
	}
	
	@Test
	public void testMoneySpent(){
		//Randomly set some drivers to have refuelled
		for(SmallCarDriver driver : drivers){
			boolean refuelled = rnd.nextBoolean(); //Generate true or false visited value
			driver.setRefuelled(refuelled);
		}
		
		//Randomly set some drivers to have visited shop
		for(SmallCarDriver driver : drivers){
			boolean visitedShop = rnd.nextBoolean(); //Generate true or false visited value
			driver.setVisitedShop(visitedShop);
		}
		
		//Test money spent is calculated correctly
		for(SmallCarDriver driver : drivers){
			
			BigDecimal expectedMoney = new BigDecimal(0.00);
			if(driver.getRefuelled()) expectedMoney = expectedMoney.add(driver.getVehicle().getRefuelCost());
			if(driver.getVisitedShop()) expectedMoney = expectedMoney.add(driver.getSpendingMoney());
			
			assertEquals(expectedMoney, driver.getMoneySpent());
		}
	}
	
	@Test
	public void testMoneyLost(){
		//Randomly set some drivers to have refuelled
		for(SmallCarDriver driver : drivers){
			boolean refuelled = rnd.nextBoolean(); //Generate true or false visited value
			driver.setRefuelled(refuelled);
		}
		
		//Randomly set some drivers to have visited shop
		for(SmallCarDriver driver : drivers){
			boolean visitedShop = rnd.nextBoolean(); //Generate true or false visited value
			driver.setVisitedShop(visitedShop);
		}
		
		//Test money lost is calculated correctly
		for(SmallCarDriver driver : drivers){
			
			BigDecimal expectedMoney = new BigDecimal(0.00);
			if(!driver.getRefuelled()) expectedMoney = expectedMoney.add(driver.getVehicle().getRefuelCost());
			if(!driver.getVisitedShop()) expectedMoney = expectedMoney.add(driver.getSpendingMoney());
			
			assertEquals(expectedMoney, driver.getMoneyLost());
		}
	}
	
}