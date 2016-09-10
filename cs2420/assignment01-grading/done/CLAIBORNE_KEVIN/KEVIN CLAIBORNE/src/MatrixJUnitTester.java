/*
 * Here is a starting point for your Matrix tester. You will have to fill in the rest of "main" with
 * more code to sufficiently test your Matrix class. We will be using our own MatrixTester for grading. 
*/
package assignment01;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class MatrixJUnitTester {

	Matrix threeByTwo, twoByThree, twoByTwoResult;
	/*Initialize some matrices we can play with for every test!*/
	
	@Before
	public void setup() {
		threeByTwo = new Matrix(new int[][]
                {{1, 2, 3},
				 {2, 5, 6}});
		twoByThree = new Matrix(new int[][]
                {{4, 5},
				 {3, 2},
				 {1, 1}});
		// this is the known correct result of multiplying M1 by M2
		twoByTwoResult = new Matrix(new int[][]
                                    {{13, 12},
									 {29, 26}});
	}
	
	@Test
	public void equal() {
		Assert.assertTrue(threeByTwo.equals(threeByTwo));
	}
	
	@Test
	public void notEqual() {
		Matrix testThreeByTwo = new Matrix(new int[][]
                {{1, 1, 3},
				 {2, 5, 6}});
		Assert.assertFalse(threeByTwo.equals(testThreeByTwo));
	}
	
	@Test
	public void timesWithBalancedDimensions() {
		Matrix matrixProduct = threeByTwo.times(twoByThree);
		Assert.assertTrue(twoByTwoResult.equals(matrixProduct));
	}
	
	@Test
	public void timesWithUnbalancedDimensions(){
		Matrix twoByTwo = new Matrix(new int[][]
				{{8, 10},
				{2, 2}});
		Matrix matrixProduct = threeByTwo.times(twoByTwo);
		Assert.assertNull(matrixProduct);
	}
	
	@Test
	public void twoByTwoToString() {
		String resultString = "13 12 \n29 26 \n";
		Assert.assertEquals(resultString, twoByTwoResult.toString());
	}
	
	@Test
	public void addWithEqualDimensions() {
		Matrix correctSum = new Matrix(new int[][]
				{{8, 10},
				{6, 4},
				{2, 2}});
		Matrix sumResult = twoByThree.plus(twoByThree);
		Assert.assertTrue(correctSum.equals(sumResult));
	}
	
	@Test
	public void addWithUnequalDimensions() {
		Matrix sumResult = twoByThree.plus(threeByTwo);
		Assert.assertNull(sumResult);
	}
}
