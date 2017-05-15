/**
 * Created by melek on 25.03.2017.
 */

import java.util.*;

/**
 * Write a method that reverses the element of a List< T > collection. Do not create
 a new list. Write a unit test associated with it.
 * */

public class Q1<T> {

    public List<T> reverseList(List<T> lst) {

        int size = lst.size();
        int index = 0;

        //System.out.println("before"+lst);
        while(index<size/2)
        {
           T temp = lst.get(index);
           lst.set(index, lst.get(size - index - 1));
           lst.set(size - index - 1, temp);
           index++;
        }
        //System.out.println("after"+lst);

        return lst;
    }
}
