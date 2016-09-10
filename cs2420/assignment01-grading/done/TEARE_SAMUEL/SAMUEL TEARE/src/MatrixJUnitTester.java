/*
 * Here is a starting point for your Matrix tester. You will have to fill in the rest of "main" with
 * more code to sufficiently test your Matrix class. We will be using our own MatrixTester for grading. 
*/
package assignment01;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Program for testing the Matrix class.
 * @author Mariah Meyer and Samuel Teare
 * <br>Last Updated: 08/29/2016
 */
public class MatrixJUnitTester {

	Matrix threeByTwo, threeByTwoDuplicate, twoByThree1, twoByThree2, twoByThreeResult, twoByTwoResult, threeByThreeResult;
	/*Initialize some matrices we can play with for every test!*/
	String randomString;
	
	@Before
	public void setup() {
		threeByTwo = new Matrix(new int[][]
                {{1, 2, 3},
				 {2, 5, 6}});
		threeByTwoDuplicate = new Matrix(new int[][]
                {{1, 2, 3},
				 {2, 5, 6}});
		twoByThree1 = new Matrix(new int[][]
                {{4, 5},
				 {3, 2},
				 {1, 1}});
		twoByThree2 = new Matrix(new int[][]
                {{3, 10},
				 {10, 3},
				 {5, 7}});
		twoByThreeResult = new Matrix(new int[][]
                {{7, 15},
				 {13, 5},
				 {6, 8}});
		// this is the known correct result of multiplying threeByTwo by threeByTwo
		twoByTwoResult = new Matrix(new int[][]
                                    {{13, 12},
									 {29, 26}});
		// this is the known correct result of multiplying twoByThree2 by threeByTwo
		threeByThreeResult = new Matrix(new int[][]
                                    {{23, 56, 69},
									 {16, 35, 48},
                                     {19, 45, 57}});
		
		randomString = "This is just a test";
	}
	
	@Test
	public void timesWithBalancedDimensionsTwoByTwoResult() {
		Matrix matrixProduct = threeByTwo.times(twoByThree1);
		Assert.assertTrue(twoByTwoResult.equals(matrixProduct));
	}
	
	@Test
	public void timesWithBalancedDimensionsThreeByThreeResult() {
		Matrix matrixProduct = twoByThree2.times(threeByTwo);
		Assert.assertTrue(threeByThreeResult.equals(matrixProduct));
	}
	
	@Test
	public void timesWithUnbalancedDimensions(){
		Assert.assertTrue(twoByThree1.times(twoByTwoResult) == null);
	}
	
	@Test
	public void twoByTwoToString() {
		String resultString = "13 12 \n29 26 ";
		Assert.assertEquals(resultString, twoByTwoResult.toString());
	}
	
	@Test
	public void twoByThreeToString() {
		String resultString = "4 5 \n3 2 \n1 1 ";
		Assert.assertEquals(resultString, twoByThree1.toString());
	}
	
	@Test
	public void plusWithBalancedDimensions() {
		Matrix matrixSum = twoByThree1.plus(twoByThree2);
		Assert.assertTrue(twoByThreeResult.equals(matrixSum));
	}
	
	@Test
	public void plusWithUnbalancedDimensions() {
		Assert.assertTrue(twoByThree1.plus(twoByTwoResult) == null);
	}
	
	@Test
	public void equalCheckWithTwoEqualMatricesOfSameDimensions() {
		Assert.assertTrue(threeByTwo.equals(threeByTwoDuplicate));
	}
	
	@Test
	public void equalCheckWithTwoInequalMatricesOfSameDimensions() {
		Assert.assertFalse(twoByThree1.equals(twoByThree2));
	}
	
	@Test
	public void equalCheckWithTwoMatricesOfDifferentDimensions() {
		Assert.assertFalse(twoByThree1.equals(threeByTwo));
	}
	
	@Test
	public void equalCheckWithMatrixAndNonMatrixObject() {
		Assert.assertFalse(twoByThree1.equals(randomString));
	}
}
