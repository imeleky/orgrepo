import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Setup;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by melek on 26.03.2017.
 */
public class Q4 {


    @Param({"1000", "10000", "100000","1000000"})
    private int size;
    List<Double> dbs = new ArrayList<>();

    @Setup
    public void setup() {

        dbs = new Random().doubles(size, 0, (1000))
                .mapToObj(Double::new)
                .collect (Collectors.toList());
        System.out.println();

    }

    @Benchmark
    public Set<Double> sortWhileInsert() {
        Set<Double> ret = new TreeSet<Double>(); // note: simplified for readability
        for (Double db : dbs)
            ret.add(db);
        return ret;
    }

    @Benchmark
    public List<Double> sortAfterInsert() {
        List<Double> ret = new ArrayList<Double>(); // note: simplified for readability
        for (Double db : dbs)
            ret.add(db);

        Arrays.sort(ret.toArray());
        return ret;
    }

    @Benchmark
    public List<Double> sortAfterInsertByStreamWithoutDistControl() {
        return dbs.stream()
                .sorted().limit(100000)
                .collect(Collectors.toList());
    }


    @Benchmark
    public List<Double> sortAfterInsertByStreamWithDistControl() {
        return dbs.stream()
                .distinct().sorted()
                .collect(Collectors.toList());
    }

    @Benchmark
    public Set<Double> sortAfterInsertByTreeSetStream() {
        return dbs.stream()
                .collect(Collectors.toCollection(TreeSet::new));
    }

    public static void main( String[] args )
    {
        Q4 q4 = new Q4();

        q4.size = 100000;
        q4.setup();
        int n=10;

        while(n-->0)
        {
            System.out.println("size = "+q4.size );

            long startTime = System.nanoTime();
            Set mySet = q4.sortWhileInsert();
            long estimatedTime = System.nanoTime() - startTime;


            System.out.println(" Tree Set Loop estimated Time in micro second                                 " + estimatedTime/1000000);

            startTime = System.nanoTime();
            mySet = q4.sortAfterInsertByTreeSetStream();
            estimatedTime = System.nanoTime() - startTime;

            System.out.println(" TreeSet stream  estimated Time in micro second                               " + estimatedTime/1000000);

            System.out.println();

            startTime = System.nanoTime();
            List myList = q4.sortAfterInsert();
            estimatedTime = System.nanoTime() - startTime;

            System.out.println(" List & Sort estimated Time in micro second                                   " + estimatedTime/1000000);

            startTime = System.nanoTime();
            myList = q4.sortAfterInsertByStreamWithoutDistControl();
            estimatedTime = System.nanoTime() - startTime;

            System.out.println(" List & Stream Sort  without Distinct Control estimated Time in micro second  " + estimatedTime/1000000);

            startTime = System.nanoTime();
            myList = q4.sortAfterInsertByStreamWithDistControl();
            estimatedTime = System.nanoTime() - startTime;

            System.out.println(" List & Stream Sort with Distinct Control estimated Time in micro second      " + estimatedTime/1000000);


        }


    }

}
