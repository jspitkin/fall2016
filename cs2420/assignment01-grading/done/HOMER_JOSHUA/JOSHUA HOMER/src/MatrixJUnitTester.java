/*
 * Here is a starting point for your Matrix tester. You will have to fill in the rest of "main" with
 * more code to sufficiently test your Matrix class. We will be using our own MatrixTester for grading. 
 */
package assignment01;

/**
 * 
 *@author Joshua Homer
 *unid: u0915498
 *assignment: assignment01
 *MatrixJUnitTester.java
 *
 */

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class MatrixJUnitTester {

	Matrix threeByTwo, twoByThree, twoByTwoResult, threeByTwoResult, threeByTwo2, threebyTwo3;
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
		//this is the known correct result of adding threeByTwo to itself
		threeByTwoResult = new Matrix(new int[][]
				{{2, 4, 6},
			{4, 10, 12}});
		//a matrix with identical values to threeByTwo, to test the equals method
		threeByTwo2 = new Matrix(new int[][]
				{{1, 2, 3},
			{2, 5, 6}});
		//another threeByTwo matrix with different values, also to test the equals method
		threebyTwo3 = new Matrix(new int[][]
				{{1, 2, 3},
			{2, 5, 7}});
	}

	@Test
	public void timesWithBalancedDimensions() {
		Matrix matrixProduct = threeByTwo.times(twoByThree);
		Assert.assertTrue(twoByTwoResult.equals(matrixProduct));
	}

	@Test
	public void timesWithUnbalancedDimensions(){
		Matrix resultant = threeByTwo.times(threeByTwo);
		Assert.assertNull(resultant);
	}

	@Test
	public void additionWithBalancedDimensions(){
		Matrix resultant = threeByTwo.plus(threeByTwo);
		Assert.assertTrue(resultant.equals(threeByTwoResult));
	}

	@Test
	public void additionWithUnbalancedDimensions(){
		Matrix resultant = threeByTwo.plus(twoByThree);
		Assert.assertNull(resultant);
	}

	@Test
	public void twoByTwoToString() {
		String resultString = "13 12 \n29 26 \n";
		Assert.assertEquals(resultString, twoByTwoResult.toString());
	}

	@Test
	public void twoByThreeToString() {
		String resultString = "4 5 \n3 2 \n1 1 \n";
		Assert.assertEquals(resultString, twoByThree.toString());
	}

	@Test
	public void testEquals(){
		Assert.assertTrue(threeByTwo.equals(threeByTwo2));
		//checking for transitivity
		Assert.assertTrue(threeByTwo2.equals(threeByTwo));
		//making sure it doesn't always return true
		Assert.assertFalse(threeByTwo.equals(twoByThree));
		//same dimensions but different values
		Assert.assertFalse(threebyTwo3.equals(threeByTwo));
	}

	//initial testing was done in the main method of Matrix.java
}
