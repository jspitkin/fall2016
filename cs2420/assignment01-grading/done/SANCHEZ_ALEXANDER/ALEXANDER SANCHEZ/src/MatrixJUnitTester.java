/*
 * Here is a starting point for your Matrix tester. You will have to fill in the rest of "main" with
 * more code to sufficiently test your Matrix class. We will be using our own MatrixTester for grading. 
*/
package assignment01;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MatrixJUnitTester {

	Matrix threeByTwo, twoByThree, twoByTwoResult, threeByTwoNotEqual, threeByTwoAddition, twoByThreeAddition,
			twoByThreeSecond;
	/* Initialize some matrices we can play with for every test! */

	@Before
	public void setup() {
		threeByTwo = new Matrix(new int[][] { { 1, 2, 3 }, { 2, 5, 6 } });
		threeByTwoNotEqual = new Matrix(new int[][] { { 1, 8, 3 }, { 2, 5, 6 } });

		threeByTwoAddition = new Matrix(new int[][] { { 2, 10, 6 }, { 4, 10, 12 } });

		twoByThree = new Matrix(new int[][] { { 4, 5 }, { 3, 2 }, { 1, 1 } });
		twoByThreeSecond = new Matrix(new int[][] { { 4, 7 }, { 1, 0 }, { 7, 10 } });
		twoByThreeAddition = new Matrix(new int[][] { { 8, 12 }, { 4, 2 }, { 8, 11 } });

		// this is the known correct result of multiplying M1 by M2
		twoByTwoResult = new Matrix(new int[][] { { 13, 12 }, { 29, 26 } });
	}

	@Test
	public void equalsPass() {

		Assert.assertTrue(twoByTwoResult.equals(twoByTwoResult));
	}

	@Test
	public void equalsDimensionsNotEqual() {

		Assert.assertFalse(twoByTwoResult.equals(twoByThree));
	}

	@Test
	public void equalsElementsNotEqual() {

		Assert.assertFalse(threeByTwo.equals(threeByTwoNotEqual));
	}

	@Test
	public void timesWithBalancedDimensions() {
		Matrix matrixProduct = threeByTwo.times(twoByThree);
		Assert.assertTrue(twoByTwoResult.equals(matrixProduct));
	}

	@Test
	public void timesWithUnbalancedDimensions() {
		Assert.assertNull(threeByTwo.times(twoByTwoResult));
	
	}

	@Test
	public void plusWithUnbalancedDimensions() {
		Assert.assertNull(threeByTwo.plus(twoByThree));
	}

	@Test
	public void plusWithBalancedDimensionsThreeByTwo() {
		Matrix matrixAddition = threeByTwo.plus(threeByTwoNotEqual);
		Assert.assertTrue(matrixAddition.equals(threeByTwoAddition));
	}

	@Test
	public void plusWithBalancedDimensionsTwoByThree() {
		Matrix matrixAddition = twoByThree.plus(twoByThreeSecond);
		Assert.assertTrue(matrixAddition.equals(twoByThreeAddition));
	}

	@Test
	public void twoByTwoToString() {
		String resultString = "13 12 \n29 26 \n";
		Assert.assertEquals(resultString, twoByTwoResult.toString());
	}
}
