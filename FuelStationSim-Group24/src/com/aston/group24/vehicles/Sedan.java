package com.aston.group24.vehicles;

public class Sedan extends Vehicle {

	public Sedan(int tankSize, int AOF, double pSize, boolean randomiseTank) 
	{
		super(tankSize, AOF, pSize);
		
		if (randomiseTank)
		{
			//Randomise the tank size between 12 and 18
			this.randomiseTankSize(12, 18);
			
		}
	}	
	

}
