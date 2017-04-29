package com.aston.group24.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

import com.aston.group24.people.Person;

public class Shop{

	private HashMap<Person, Double> shopTime;
	private HashMap<Person, Double> tillTime;
	private ArrayList<Person> shopFloor; //Array for storing people currently on the shop floor.
	private ArrayList<Till> tills; //Array for storing tills.

public Shop(int numTills)
  {	
	shopTime = new HashMap<Person, Double>();
	tillTime = new HashMap<Person, Double>();
    tills = new ArrayList<Till>();
 
    createTills(numTills);
  }

  protected void rndShopTime(Person p)
  {
	  Random r = new Random(2-3);
	  Double time = r.nextDouble();
	  
	  shopTime.put(p, time);  
	
  }
  
  protected void rndTillTime(Person p)
  {
	  Random r = new Random(2-3);
	  Double time = r.nextDouble();
	  
	  tillTime.put(p, time);  
  } 
  
  

  protected void createTills(int ammountOfTills)
  {
      for(int i = 0; i < ammountOfTills; i++)
      {
          tills.add(new Till());

      }
  }
 
  protected void addPersonToFloor(Person p)
  {		   	   
	  shopFloor.add(p);
	  
  }
  
  protected boolean addPersonToTills(Person p)
  {
	  Till validTill = tillWithShortesQueue();
	
	  if(validTill != null)
	  {
		  tillWithShortesQueue().addPerson(p);
		  return true;
	  }
	  else 
	  {
		  return false;
	  }
	  
  }
  
  protected void removePersonFromTills(Person p, Till t)
  {
	  t.removePerson(p);
  }
  
  protected void removePersonBrowsing(Person p)
  {
	  shopFloor.remove(p);
	  
  }
  
  //TODO Needs to return a list of the people that have finished, at the moment just returns the one person in the parameter
  protected ArrayList<Person> getFinishedBrowsing(Person p)
  {
	 ArrayList<Person> finishedBrowsing = new ArrayList<Person>();

		 if(p.getShopTime() == shopTime.get(p))
		 {
			 finishedBrowsing.add(p);
		 }
		 return finishedBrowsing;
  }
  
  protected ArrayList<Person> getFinishedPaying(Person p)
  {
	  ArrayList<Person> finishedPaying = new ArrayList<Person>();
	  
	  if(p.getTillTime() == tillTime.get(p))
	  {
		  finishedPaying.add(p);
	  }
	  return finishedPaying;
	  
  }
 
  
  protected Till tillWithShortesQueue()
  {  
	  
	 ArrayList<Integer> sortedTills = new ArrayList<Integer>();
	 
	 for(Till t : tills)
	 {
		sortedTills.add(t.queueLength());
		
	 }
	 
	 Collections.sort(sortedTills, Collections.reverseOrder());
	  
	 for(Till t : tills)
		 if(t.queueLength() == sortedTills.get(0))
		 {
			 return t;
		 }
	return null;
	  
  }
  
   
}
