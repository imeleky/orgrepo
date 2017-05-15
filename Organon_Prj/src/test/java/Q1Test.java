import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by melek on 25.03.2017.
 */
public class Q1Test extends TestCase {

    @Test
    public void testReverse_NullList() throws Exception {
        List<Integer> inList= new ArrayList<Integer>();
        List<Integer> revList = new ArrayList<Integer>();

        Q1<Integer> q1 = new Q1<Integer>();
        q1.reverseList(inList);

    }
    @Test
    public void testReverse_OneElementList() throws Exception {
        List<Integer> inList= Arrays.asList(7);
        List<Integer> revList = Arrays.asList(7);

        Q1<Integer> q1 = new Q1<Integer>();
        q1.reverseList(inList);

    }

    @Test
    public void testIntegerReverse_EvenSize() throws Exception {
        List<Integer> inList = Arrays.asList( 7,6,5,4,3,2,1,0 );
        List<Integer> revList = Arrays.asList( 0,1,2,3,4,5,6,7 );

        Q1<Integer> q1 = new Q1<Integer>();
        assertEquals(q1.reverseList(inList), revList);
    }

    @Test
    public void testIntegerReverse_OddSize() throws Exception {
        List<Integer> inList = Arrays.asList( 10,9,8,7,6,5,4,3,2,1,0 );
        List<Integer> revList = Arrays.asList( 0,1,2,3,4,5,6,7,8,9,10 );

        Q1<Integer> q1 = new Q1<Integer>();
        assertEquals(q1.reverseList(inList),revList);
    }

    @Test
    public void testStringReverse_OddSize() throws Exception {
        List<String> inList = Arrays.asList("Hello", "World!", "How", "Are", "You");
        List<String> revList = Arrays.asList("You","Are","How", "World!","Hello");

        Q1<String> q1 = new Q1<String>();
        assertEquals(q1.reverseList(inList),revList);
    }

    @Test
    public void testStringReverse_EvenSize() throws Exception {
        List<String> inList = Arrays.asList("Hello", "World!", "How", "Are", "You","Now");
        List<String> revList = Arrays.asList("Now","You","Are","How", "World!","Hello");

        Q1<String> q1 = new Q1<String>();
        assertEquals(q1.reverseList(inList),revList);
    }

    @Test
    public void testDoubleReverse_OddSize() throws Exception {
        List<Double> inList = Arrays.asList(0.1, 0.2, 0.3, 0.4, 0.5);
        List<Double> revList = Arrays.asList(0.5,0.4,0.3,0.2,0.1);

        Q1<Double> q1 = new Q1<Double>();
        assertEquals(q1.reverseList(inList),revList);
    }

    @Test
    public void testDoubleReverse_EvenSize() throws Exception {
        List<Double> inList = Arrays.asList(0.1, 0.2, 0.3, 0.4, 0.5,0.6,0.7,0.8);
        List<Double> revList = Arrays.asList(0.8,0.7,0.6,0.5,0.4,0.3,0.2,0.1);

        Q1<Double> q1 = new Q1<Double>();
        assertEquals(q1.reverseList(inList),revList);
    }


}