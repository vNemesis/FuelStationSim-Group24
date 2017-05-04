//Till.java
package com.aston.group24.model;
import java.util.LinkedList;
import java.util.Queue;

import com.aston.group24.people.Person;;

/**
 * Till
 * @author Jake Turner
 * @version 20.04.2017/2055
 */
public class Till{

private Queue<Person> tillQueue;						// Queue in till
	
	/**
	 * Constructor for till - initialises tillQueue
	 */
    public Till()
    {
        tillQueue = new LinkedList<Person>();
    }

    /**
     * Add a person at the back of the till queue
     * @param p Person to add to till
     */
    protected void addPerson(Person p)
    {
        tillQueue.add(p);
    }
    
    /**
     * Remove the person at the front of the till queue
     * @param p Person to remove from till
     */
    protected void removePerson(Person p)
    {
        tillQueue.remove(p);
    }

    /**
     * Return the till queue size
     * @return Returns the length of the queue at this till
     */
    protected int queueLength()
    {
        return tillQueue.size();
    }
    
    /**
     * Return true if the till queue contains person p or false if not
     * @param p Person to check
     * @return Returns true if Person is in queue, false if not
     */
    protected boolean containsPerson(Person p)
    {
    	if(tillQueue.contains(p))
    	{
    		return true;
    	}
    	else return false;
    	
    }
    
    /**
     * Returns the person at the front of the queue
     * @return Returns Person whos is first in the queue
     */
    protected Person getFirstInQueue()
    {
    	return tillQueue.peek();
    }

}
