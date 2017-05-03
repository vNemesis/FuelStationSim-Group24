//Person.java
package com.aston.group24.people;

import java.math.BigDecimal;
import java.util.Random;

import com.aston.group24.vehicles.Vehicle;

/**
 * Person superclass
 * @author JShorthouse
 * @version 20.04.2017/2055
 */
public abstract class Person {
	
	protected Vehicle vehicle;				//The vehicle they own
	protected int timeAtStation;			//Time they have spend at the station (will start from 0 and update)
	protected int shopTime;					//Time they will spend at the shop
	private int tillTime;					//Time they will spend at the till
	protected BigDecimal spendingMoney;		//Money they will spend in the shop
	private Boolean refuelled;				//If they have refuelled or not
	private Boolean visitedShop;			//If they have visited the shop or not (to make an additional purchase)
	
	protected Random rnd;
	
	/**
	 * Constructor.
	 * @param seed Seed used for random generation
	 */
	public Person(long seed)
	{
		rnd = new Random(seed);
		timeAtStation = 0;
		refuelled = false;
		visitedShop = false;
		tillTime = rnd.nextInt(7) + 12; 	//2-3 minutes (12-18 ticks)
	}
	
	/**
	 * Returns the money spent by the customer
	 * @return money spent
	 */
	public BigDecimal getMoneySpent()
	{
		BigDecimal money = new BigDecimal(0);
		if(visitedShop) money = money.add(spendingMoney);
		if(refuelled) money = money.add(vehicle.getRefuelCost());	//getRefuelCost needs to be implemented in vehicle class (tankSize * PRICE_PER_GALLON)
		return money;
	}
	
	/**
	 * Returns the potential money that could have been spent by the customer but wasn't
	 * @return money lost
	 */
	public BigDecimal getMoneyLost()
	{
		BigDecimal money = new BigDecimal(0.00);
		if(!visitedShop) money = money.add(spendingMoney);
		if(!refuelled) money = money.add(vehicle.getRefuelCost());
		return money;
	}
	
	//Abstract methods
	
	/**
	 * Returns whether the person wants to visit the shop at the current point in the simulation
	 * @return true if the person wants to visit the shop
	 */
	abstract public boolean wantsToShop();
	
	
	//Getter methods
	
	/**
	 * Returns the person's vehicle
	 * @return vehicle
	 */
	public Vehicle getVehicle()
	{
		return vehicle;
	} 
	
	/**
	 * Returns the time that the person has been in the simulation for
	 * @return time in ticks
	 */
	public int getTimeAtStation()
	{
		return timeAtStation;
	}
	
	/**
	 * Returns the time that the person wishes to spend in the shop
	 * @return time in ticks
	 */
	public int getShopTime()
	{
		return shopTime;
	}
	
	/**
	 * Returns the time that the person will spend at the the till
	 * @return time in ticks
	 */
	public int getTillTime()
	{
		return tillTime;
	}
	
	/**
	 * Returns the money the customer has to spend in the store
	 * @return money
	 */
	public BigDecimal getSpendingMoney()
	{
		return spendingMoney;
	}
	
	/**
	 * Returns whether the customer has refuelled
	 * @return true if refuelled
	 */
	public boolean getRefuelled()
	{
		return refuelled;
	}
	
	/**
	 * Returns whether the customer has visited the shop
	 * @return true if the customer has visited
	 */
	public boolean getVisitedShop()
	{
		return visitedShop;
	}
	
	//Setter methods
	
	/**
	 * Changes whether the customer has refuelled
	 * @param refuelled true or false
	 */
	public void setRefuelled(boolean refuelled){
		this.refuelled = refuelled;
	}
	
	/**
	 * Changes whether the customer has visited the shop or not
	 * @param visitedShop true or false
	 */
	public void setVisitedShop(boolean visitedShop){
		this.visitedShop = visitedShop;
	}
	
	/**
	 * Increments the time the person has been in the simulation by 1 tick
	 */
	public void incrementTime(){
		timeAtStation++;
	}
	
	
}
