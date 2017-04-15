package com.aston.group24.model;

import java.util.ArrayList;

import com.aston.group24.people.Person;

public class Shop{

  private ArrayList<Till> tills;

//Shop constructor
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


  protected Till tillWithShortesQueue(Person p)
  {
	return null; // Replace with your own code
  }

}
