import org.openjdk.jmh.annotations.Setup;

import java.util.Random;

/**
 * Created by melek on 27.03.2017.
 */
public class Q6 {

    private final int size = 1000000;
    private long sum;
    private int[] xArr = new int[size];
    private int[] yArr = new int[size];

    native long cCalculateDotProduct(int[] intArray1, int[] intArray2);

    // load C library as a static initialization
    static
    {
        System.loadLibrary("mynativelib");//Linking the native library
    }

    @Setup
    public void setup() {

        xArr = new Random().ints(size, 0, 100).toArray();
        yArr = new Random().ints(size, 0, 100).toArray();
        System.out.println();
    }

    // Managed dot plot calculation
    public long calculateDotProduct(int[] arr1, int[] arr2)
    {
        long sum = 0;
        for(int i=0;i<arr1.length;i++)
            sum+= arr1[i]*arr2[i];
        return sum;
    }

    public static void main(String args[])
    {
        Q6 obj = new Q6();
        obj.setup();
        System.out.println("*************Managed********************");
        long startTime = System.nanoTime();
        obj.sum = obj.calculateDotProduct(obj.xArr,obj.yArr);
        long estimatedTime = System.nanoTime() - startTime;

        System.out.println("Dot Product  on Managed Method   --> "+obj.sum);
        System.out.println(" Time Cost   on Managed Method   --> "+estimatedTime+"\n");

        System.out.println("*************UnManaged********************");
        // pass int array to C
        long cSum = obj.cCalculateDotProduct(obj.xArr, obj.yArr);
        System.out.println("Dot Product  on UnManaged Method   --> "+cSum);
        //printf(" Time Cost   on UnManaged Method   --> %lu",time);
    }
}
