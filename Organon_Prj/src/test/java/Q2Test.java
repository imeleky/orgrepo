import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by melek on 26.03.2017.
 */
public class Q2Test extends TestCase {

    @Test
    public void testCreateMatrix() throws Exception {
        int M = 5;
        int N = 7;

        Q2 q2=new Q2();
        int[][] matrix = q2.createMatrix(M,N);

        assertEquals(M, matrix.length);
        assertEquals(N, matrix[0].length);
    }

    @Test
    public void testMaxSequenceSum() throws Exception {
        int[] arr = new int[]{-2,1,7,8,3,3,-8};  // max sum : 22 start: 1 end: 5
        Q2 q2=new Q2();
        Q2.MaxSumInfo retSumInfo =  q2.maxSequenceSum(arr);

        assertEquals( retSumInfo.sum, 22);
        assertEquals( retSumInfo.start, 1);
        assertEquals( retSumInfo.end, 5);

        arr = new int[]{-2,-5,-2,1,1,-4, 7,-2,11,-5,10,3,-4,-3,-8};  // max sum : 24 start: 6 end: 11
        retSumInfo =  q2.maxSequenceSum(arr);

        assertEquals( retSumInfo.sum, 24);
        assertEquals( retSumInfo.start, 6);
        assertEquals( retSumInfo.end, 11);

        arr = new int[]{-1,-1,-2,-1,-1,-1, 10,-5,-1,-1,-1,-1,-1,-1};  // max sum : 10 start: 6 end: 6
        retSumInfo =  q2.maxSequenceSum(arr);

        assertEquals( retSumInfo.sum, 10);
        assertEquals( retSumInfo.start,6);
        assertEquals( retSumInfo.end, 6);

    }

    @Test
    public void testFindMaxSum() throws Exception {

        /***************************************************************/
        int[][] matrix = new int[][]{
                {2,1,-3,-4,5},
                {0,6,3,4,1},
                {2,-2,-1,4,-5},
                {-3,3,1,0,3}
        };

        Q2 q2 = new Q2();
        Q2.MaxSumInfo retSumInfo = q2.findMaxSum(matrix);

        /**
         * Submatrix has largest sum-->18 from-->(1,3) to-->(1,3)
         6	  3	  4
         -2	 -1	  4
         3	  1	  0
         */

        assertEquals( retSumInfo.sum, 18);
        assertEquals( retSumInfo.start,1);
        assertEquals( retSumInfo.end, 3);
        assertEquals( retSumInfo.left, 1);
        assertEquals( retSumInfo.right,3);

        /***************************************************************/

        matrix = new int[][]{
                {-8,-3,4,2,1},
                {1,2,-1,-4,-20},
                {1,1,0,0,1},
                {3,-8,10,1,3},
                {-4,-1,1,7,-6}
        };

        retSumInfo = q2.findMaxSum(matrix);

        /**
         * Submatrix has largest sum-->20 from-->(0,2) to-->(4,3)
         4	 2
         -1	-4
         0	 0
         10	 1
         1	 7
         */

        assertEquals( retSumInfo.sum, 20);
        assertEquals( retSumInfo.start,0);
        assertEquals( retSumInfo.end, 4);
        assertEquals( retSumInfo.left, 2);
        assertEquals( retSumInfo.right,3);

        /***************************************************************/

        matrix = new int[][]{
                {1, 1, 1, 1, 1},
                {2, 2, 2, 2, 2},
                {3, 8, 6, 7, 3},
                {4, 4, 4, 4, 4},
                {5, 5, 5, 5, 5}
        };

        retSumInfo = q2.findMaxSum(matrix);

        /**
         * Submatrix has largest sum-->87 from-->(0,0) to-->(4,4)
         1	 1	 1	 1	 1
         2	 2	 2	 2	 2
         3	 8	 6	 7	 3
         4	 4	 4	 4	 4
         5	 5	 5	 5	 5
         */

        assertEquals( retSumInfo.sum, 87);
        assertEquals( retSumInfo.start,0);
        assertEquals( retSumInfo.end, 4);
        assertEquals( retSumInfo.left, 0);
        assertEquals( retSumInfo.right,4);

        /***************************************************************/

        matrix = new int[][]{
                {-8},
                {3},
                {1},
                {8},
                {-1},
                {1},
                {-6},
                {-8},
                {1}
        };

        retSumInfo = q2.findMaxSum(matrix);

        /**
         * Submatrix has largest sum-->12 from-->(1,0) to-->(3,0)
         3
         1
         8
         */

        assertEquals( retSumInfo.sum, 12);
        assertEquals( retSumInfo.start,1);
        assertEquals( retSumInfo.end, 3);
        assertEquals( retSumInfo.left, 0);
        assertEquals( retSumInfo.right,0);

        /***************************************************************/

        matrix = new int[][]{
                {4,-2,1,8,-1,-8,-2,3,1}};

        retSumInfo = q2.findMaxSum(matrix);

        /**
         * Submatrix has largest sum-->11 from-->(0,0) to-->(0,3)
         4	-2	 1	 8
         */

        assertEquals( retSumInfo.sum, 11);
        assertEquals( retSumInfo.start,0);
        assertEquals( retSumInfo.end, 0);
        assertEquals( retSumInfo.left, 0);
        assertEquals( retSumInfo.right,3);

        /***************************************************************/
        matrix = new int[][]{
                {-4,	-3,	-3},
                {5,	 -1,	 4},
                {-7,	-8,	 3},
                {-5,	-2,	 0},
                {7, 	-2,	 0},
                {-1,	 1,	 4}};


        retSumInfo = q2.findMaxSum(matrix);

        /**
         * Submatrix has largest sum-->11 from-->(1,2) to-->(5,2)
         4
         3
         0
         0
         4
         */

        assertEquals( retSumInfo.sum, 11);
        assertEquals( retSumInfo.start,1);
        assertEquals( retSumInfo.end, 5);
        assertEquals( retSumInfo.left, 2);
        assertEquals( retSumInfo.right,2);

        /***************************************************************/

        matrix = new int[][]{
                {0,	-9,	-9,	 4,	 3,	-6,	 9},
                {3,	-9,	-8,	-2,	 2,	 2,	-5},
                {2,	 7,	-7,	 7,	 5,	-8,	-2},
                {7,	 3,	 0,	-1,	 2,	 7,	-2},
                {-5,-4,	 0,	-9,	-1,	 5,	-2},
                {0,	 4,	-6,	-5,	 0,	-8,	-7},
                {9,	 6,	 3,	-5,	-7,	 8,	 9},
                {-8, 6,	-4,	 0,	-1,	 9,	 0},
                {4,	-3,	 2,	-5,	-3,	-9,	 7}};


        retSumInfo = q2.findMaxSum(matrix);

        /**
         * Submatrix has largest sum-->29 from-->(2,0) to-->(6,1)
         2	 7
         7	 3
         -5	-4
         0	 4
         9	 6
         */

        assertEquals( retSumInfo.sum, 29);
        assertEquals( retSumInfo.start,2);
        assertEquals( retSumInfo.end, 6);
        assertEquals( retSumInfo.left, 0);
        assertEquals( retSumInfo.right,1);

        /***************************************************************/
    }
}