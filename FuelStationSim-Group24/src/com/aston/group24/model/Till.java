

import java.util.ArrayList;

import com.aston.group24.people.Person;;

public class Till{

private ArrayList<Person> tillQueue;

    public Till()
    {
        tillQueue = new ArrayList<Person>();
    }

    protected void addPerson()
    {
        tillQueue.add(Person);
    }

    protected void removePerson(Person p)
    {
        tillQueue.remove(p);
    }

    protected int queueLength()
    {
        return tillQueue.size;
    }



}
