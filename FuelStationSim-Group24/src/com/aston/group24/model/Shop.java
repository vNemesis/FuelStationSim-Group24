package com.aston.group24.model;

package com.aston.group24.till.Till;

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
          tills.add(new Till);

      }
  }


  protected Till tillWithShortesQueue(Person p)
  {


  }

}
