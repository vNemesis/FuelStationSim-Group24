package com.aston.group24.people;

import java.math.BigDecimal;
import java.util.Random;

import com.aston.group24.vehicles.Vehicle;

public abstract class Person {
	
	protected Vehicle vehicle;				//The vehicle they own
	protected int timeAtStation;			//Time they have spend at the station (will start from 0 and update)
	protected int shopTime;					//Time they will spend at the shop
	private int tillTime;					//Time they will spend at the till
	protected BigDecimal spendingMoney;		//Money they will spend in the shop
	private Boolean refuelled;				//If they have refuelled or not
	private Boolean visitedShop;			//If they have visited the shop or not (to make an additional purchase)
	
	protected Random rnd;
	
	
	public Person(long seed)
	{
		rnd = new Random(seed);
		timeAtStation = 0;
		refuelled = false;
		visitedShop = false;
		tillTime = rnd.nextInt(7) + 12; 	//2-3 minutes (12-18 ticks)
	}
	
	public BigDecimal getMoneySpent()
	{
		BigDecimal money = new BigDecimal(0);
		if(visitedShop) money.add(spendingMoney);
		if(refuelled) money.add(vehicle.getRefuelCost());	//getRefuelCost needs to be implemented in vehicle class (tankSize * PRICE_PER_GALLON)
		return money;
	}
	
	public BigDecimal getMoneyLost()						//Code is kind of duplicated between these two methods,
	{														//replace if you can think of a more elegant solution
		BigDecimal money = new BigDecimal(0);
		if(!visitedShop) money.add(spendingMoney);
		if(!refuelled) money.add(vehicle.getRefuelCost());
		return money;
	}
	
	//Abstract methods
	abstract public boolean wantsToShop();
	
	
	//Getter methods
	public Vehicle getVehicle()
	{
		return vehicle;
	} 
	
	public int getTimeAtStation()
	{
		return timeAtStation;
	}
	
	public int getShopTime()
	{
		return shopTime;
	}
	
	public int getTillTime()
	{
		return tillTime;
	}
	
	public BigDecimal getSpendingMoney()
	{
		return spendingMoney;
	}
	
	public boolean getRefuelled()
	{
		return refuelled;
	}
	
	public boolean getVisitedShop()
	{
		return visitedShop;
	}
	
	//Setter methods
	public void setRefuelled(boolean bool){
		refuelled = bool;
	}
	
	public void setVisitedShop(boolean bool){
		visitedShop = bool;
	}
	
	public void incrementTime(){
		timeAtStation++;
	}
	
	
}
