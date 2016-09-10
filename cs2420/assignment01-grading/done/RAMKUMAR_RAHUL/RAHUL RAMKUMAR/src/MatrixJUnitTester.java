/*
 * Here is a starting point for your Matrix tester. You will have to fill in the rest of "main" with
 * more code to sufficiently test your Matrix class. We will be using our own MatrixTester for grading.
*/
package assignment01;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class MatrixJUnitTester {

	Matrix threeByTwo1, threeByTwo2, twoByThree, twoByFour, twoByTwoResult, twoByTwoSum;
	/*Initialize some matrices we can play with for every test!*/

	@Before
	public void setup() {
		threeByTwo1 = new Matrix(new int[][] //M1
                {{1, 2, 3},
				 {2, 5, 6}});

        threeByTwo2 = new Matrix(new int[][]
                {{1, 2, 3},
                 {2, 5, 6}});

        twoByFour = new Matrix(new int[][]
                {{1,2},
                 {2,3},
                 {3,4},
                 {4,5} });

		twoByThree = new Matrix(new int[][] //M2
                {{4, 5},
				 {3, 2},
				 {1, 1}});
		// this is the known correct result of multiplying M1 by M2
		twoByTwoResult = new Matrix(new int[][]
                                    {{13, 12},
									 {29, 26}});
        twoByTwoSum = new Matrix(new int[][]
                {{2, 4, 6},
                 {4, 10, 12}});
	}

	@Test
	public void timesWithBalancedDimensions() {
		Matrix matrixProduct = threeByTwo1.times(twoByThree);
		Assert.assertTrue(twoByTwoResult.equals(matrixProduct));
	}

	@Test
	public void timesWithUnbalancedDimensions(){
		Assert.assertNull(twoByThree.times(twoByFour));
	}

	@Test
    public void plusWithBalancedDimensions(){
	    Matrix matrixSum = threeByTwo1.plus(threeByTwo2);
        Assert.assertTrue(matrixSum.equals(twoByTwoSum));
    }

    @Test
    public void plusWithUnbalancedDimensions(){
        Matrix matrixSum = threeByTwo1.plus(twoByThree);
        Assert.assertNull(matrixSum);
    }
	@Test
	public void twoByTwoToString() {
		String resultString = "13 12 \n29 26 \n";
		Assert.assertEquals(resultString, twoByTwoResult.toString());
	}

	@Test
	public void testEquals(){
        Assert.assertTrue(threeByTwo1.equals(threeByTwo2));
	}

}
