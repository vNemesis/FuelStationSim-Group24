package com.aston.group24.model;

import java.util.ArrayList;

import com.aston.group24.people.Person;

public class Shop{

	private int shopTime;
	private int tillTime;
	private ArrayList<Person> shopFloor; //Array for storing people currently on the shop floor.
	private ArrayList<Till> tills; //Array for storing tills.

public Shop(int numTills)
  {

    tills = new ArrayList<Till>();

    createTills(numTills);

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

  protected void removePersonFromTills(Person p, Till t)
  {
	  t.removePerson(p);

  }

  protected void removePersonBrowsing(Person p)
  {
	  shopFloor.remove(p);

  }

  protected void addPersonToTills(Person p, Till t)
  {
	  t.addPerson(p);

  }

  protected Person getFinishedBrowsing(Person p)
  {
	  if(p.getShopTime() == shopTime)
	  {
		  return p;
	  }
	  else return null;
  }

  protected Person getFinishedPaying(Person p)
  {
	  if(p.getTillTime() == tillTime)
	  {
		  return p;
	  }
	  else return null;

  }

  protected Till tillWithShortesQueue(Person p)
  {

	   
  }

}
