import org.openjdk.jmh.annotations.Setup;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by melek on 25.03.2017.
 */

/**
 * Given an M *N square matrix of positive and negative integers, write code to nd
 the submatrix with the largest possible sum. For Testing: Generate an M  N matrix of
 numbers, and print the result.
 * */
public class Q2 {

    /**
     * Inner class to store submatrix sum and index info
     * */

    static class MaxSumInfo
    {
        int sum;
        int start;
        int end;
        int left;
        int right;

        public MaxSumInfo(int sum, int start, int end, int left, int right) {
            this.sum = sum;
            this.start = start;
            this.end = end;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * To create matrix include +/- integers according to parameter M,N
     * */
    @Setup
    public int[][] createMatrix(int M,int N)
    {
        int i,j;
        int [] [] matrix = new int [M] [N];
        Random r = new Random();

        for (i=0; i<matrix.length; i++) {
            for (j=0; j<matrix[i].length; j++) {
                matrix[i][j] = (int) (Math.random()*10);
                if(r.nextBoolean())
                    matrix[i][j] *= -1;
            }
        }
        return matrix;
    }

    /**
     * Implementing Kadane's algorithm to find max sequence in a 1D array
     * */
    public MaxSumInfo maxSequenceSum(int[] arr)
    {
        int maxSum = arr[0], currentSum = arr[0];
        int start=0;
        int end = 0;
        int temp = start;

        for (int i = 1; i < arr.length; i++)
        {
            /* calculate currentSum */
            if (currentSum < 0) {
                temp = i;
                currentSum = arr[i];
            }
            else
                currentSum += arr[i];
            /* calculate maxSum */
            if (currentSum > maxSum) //= idi
            {
                start = temp;
                maxSum = currentSum;
                end = i;
            }
        }

        return new MaxSumInfo(maxSum,start,end,-1,-1);
    }

    /**
     * To find submatrix that has maxsimum sum
     * */
    MaxSumInfo findMaxSum (int matrix[][])
    {
        MaxSumInfo maxSumInfo = new MaxSumInfo(-1000,-1,-1,-1,-1);

        int numRows_M = matrix.length;
        int numCols_N = matrix[0].length;
        int[] rowArr = new int[numRows_M];

        // to check if matrix has only one row
        if( 1 == numRows_M)
        {
            MaxSumInfo sumInfo = maxSequenceSum(matrix[0]);
            maxSumInfo = new MaxSumInfo(sumInfo.sum,0,0,sumInfo.start,sumInfo.end);
        }
        else
        {
            for (int left = 0; left < numCols_N; left++)
            {
                Arrays.fill(rowArr,0);
                for (int right = left; right < numCols_N; right++)
                {
                    // Find sum of every row between left and right columns
                    for (int i = 0; i < numRows_M; ++i)
                        rowArr[i] += matrix[i][right];
                    // Find the maximum sum subArray in rowArr[]
                    MaxSumInfo sumInfo = maxSequenceSum(rowArr);
                    if (sumInfo.sum > maxSumInfo.sum)
                        maxSumInfo = new MaxSumInfo(sumInfo.sum,sumInfo.start,sumInfo.end,left,right);
                }
            }
        }
        return maxSumInfo;
    }

    public void printMatrixToConsole(int[][] matrix, int start, int end, int left, int right)
    {
        for (int i=start; i<end; i++) {
            for (int j=left; j< right; j++)
                System.out.print((String.format("%2d", matrix[i][j]))+"\t");
            System.out.println();
        }
    }

    public static void main( String[] args )
    {
        // Create random matrix size M,N
        int M = (int) (Math.random()*10);
        int N = (int) (Math.random()*10);

        // Check if any dimension 0 size
        M = (M == 0) ? 1 : M;
        N = (N == 0) ? 1 : N;

        System.out.println("--> Randomly created  M:"+M+" N:"+N);
        System.out.println("--> Randomly created  M*N matrix"+"\n");

        Q2 q2=new Q2();

        // Create Random Matrix of size M*N
        int[][] matrix = q2.createMatrix(M,N);
        q2.printMatrixToConsole(matrix,0,M,0,N);

        // Find submatrix has largest sum
        MaxSumInfo retInfo = q2.findMaxSum(matrix);
        System.out.println("\n"+"--> Calculated SubMatrix Largest Sum --> "+retInfo.sum+" from --> "+retInfo.start+","+retInfo.left+" to --> "+retInfo.end+","+retInfo.right+"\n");

        // Print sub matrix to console
        q2.printMatrixToConsole(matrix,retInfo.start,retInfo.end+1, retInfo.left,retInfo.right+1);
    }
}
