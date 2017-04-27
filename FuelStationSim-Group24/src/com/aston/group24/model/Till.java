package com.aston.group24.model;
import java.util.LinkedList;
import java.util.Queue;

import com.aston.group24.people.Person;;

public class Till{

private Queue<Person> tillQueue; 
	
	
    public Till()
    {
        tillQueue = new LinkedList<Person>();
    }

    protected void addPerson(Person p)
    {
        tillQueue.add(p);
    }

    protected void removePerson(Person p)
    {
        tillQueue.remove(p);
    }

    protected int queueLength()
    {
        return tillQueue.size();
    }


}
