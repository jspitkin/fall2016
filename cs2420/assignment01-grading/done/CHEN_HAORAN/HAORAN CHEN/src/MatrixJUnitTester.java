/*
 * Here is a starting point for your Matrix tester. You will have to fill in the rest of "main" with
 * more code to sufficiently test your Matrix class. We will be using our own MatrixTester for grading. 
*/
package assignment01;

/**
 * A program testing the methods for Matrix.java
 * 
 * @author Haoran Chen
 * @Date 08/31/2016
 */

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
	
	/**
	 * equals
	 */
	@Test 
	public void twoByTwoEquals() {
		Matrix twoByTwoTest = new Matrix(new int[][]
                {{13, 12},
				 {29, 26}});
		Assert.assertTrue(twoByTwoResult.equals(twoByTwoTest));
	}
	
	@Test
	public void threeByTwoEquals() {
		Matrix threeByTwoTest = new Matrix(new int[][]
				{{1, 2, 3},
			     {2, 5, 6}});
		Assert.assertTrue(threeByTwo.equals(threeByTwoTest));
	}
	
	@Test
	public void twoByTwoNotEquals() {
		Matrix twoByTwoTest = new Matrix(new int[][]
                {{13, 12},
				 {29, 29}});
		Assert.assertFalse(twoByTwoResult.equals(twoByTwoTest));
	}
	
	@Test
	public void twoByTwoThreeByTwoNotEquals() {
		Assert.assertFalse(twoByTwoResult.equals(threeByTwo));
	}
	
	@Test
	public void notMatrixEquals() {
		String twoByTworesultString = "13 12 \n29 26 ";
		Assert.assertFalse(twoByTwoResult.equals(twoByTworesultString));
	}
	
	/**
	 * toString
	 */
	@Test
	public void twoByTwoToString() {
		String twoByTworesultString = "13 12 \n29 26 \n";
		Assert.assertEquals(twoByTworesultString, twoByTwoResult.toString());
	}
	
	@Test
	public void threeByTwoToString() {
		String threeByTworesultString = "1 2 3 \n2 5 6 \n";
		Assert.assertEquals(threeByTworesultString, threeByTwo.toString());
	}
	
	@Test
	public void twoByOneToString() {
		Matrix twoByOneTest = new Matrix(new int[][]
				{{1, 2}});
		String twoByOneresultString = "1 2 \n";
		Assert.assertEquals(twoByOneresultString, twoByOneTest.toString());
	}
	
	/**
	 * times
	 */
	@Test
	public void timesWithBalancedDimensions() {
		Matrix matrixProduct = threeByTwo.times(twoByThree);
		Assert.assertTrue(twoByTwoResult.equals(matrixProduct));
	}
	
	@Test
	public void timesWithUnbalancedDimensions(){
		Matrix timesTester = new Matrix(new int[][]
                {{7, 8, 9, 15},
				 {10, 11, 12, 15}});
		Assert.assertNull(threeByTwo.times(timesTester));
	}
	
	@Test
	public void timesWithBalancedDimensionsExtra(){
		Matrix timesTester1 = new Matrix(new int[][]
                {{7, 8, 9, 15},
				 {10, 11, 12, 15}});
		Matrix timesTester2 = new Matrix(new int[][]
                {{7, -8, 9, 15, 17, 19},
				 {10, 11, 12, 15, 21, 26},
				 {12, 9, 3, -2, 0, -9},
				 {-10, 11, 0, 13, 21, -4}});
		Matrix timesProduct = new Matrix(new int[][]
                {{87, 278, 186, 402, 602, 200},
				 {174, 314, 258, 486, 716, 308}});
		Assert.assertTrue(timesProduct.equals(timesTester1.times(timesTester2)));
	}
	
	/**
	 * plus
	 */
	@Test
	public void plusWithBalancedDimensions(){
		Matrix addTester = new Matrix(new int[][]
				{{1, 2, 3},
			     {2, 5, 6}});
		Matrix addResult = new Matrix(new int[][]
                {{2, 4, 6},
				 {4, 10, 12}});
		Assert.assertTrue(addResult.equals(threeByTwo.plus(addTester)));
	}
	
	@Test
	public void plusWithUnbalancedDimensions(){
		Matrix addTester = new Matrix(new int[][]
                {{7, 8, 9, 15},
				 {10, 11, 12, 15}});
		Assert.assertNull(threeByTwo.plus(addTester));
	}
	
}
