/*
 * Here is a starting point for your Matrix tester. You will have to fill in the rest of "main" with
 * more code to sufficiently test your Matrix class. We will be using our own MatrixTester for grading. 
*/
package assignment01;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class MatrixJUnitTester {

	Matrix threeByTwo, twoByThree, twoByTwoResult, oneByOne, twoByTwoIdentity, twoByThreeSecond;
	/*Initialize some matrices we can play with for every test!*/
	
	@Before
	public void setup() {
		twoByThree = new Matrix(new int[][]
                {{1, 2, 3},
				 {2, 5, 6}});
		twoByThreeSecond = new Matrix(new int[][] {{1,2,3},{2,5,6}});
		threeByTwo = new Matrix(new int[][]
                {{4, 5},
				 {3, 2},
				 {1, 1}});
		// this is the known correct result of multiplying M1 by M2
		twoByTwoResult = new Matrix(new int[][]
                                    {{13, 12},
									 {29, 26}});
		oneByOne = new Matrix(new int[][] {{1}});
		twoByTwoIdentity = new Matrix(new int[][] {{1,0},{0,1}});
	}
	
	@Test
	public void testMatrixEqualItself(){
		Assert.assertTrue(twoByThree.equals(twoByThree));
	}
	
	@Test
	public void testMatrixEqualityDifferentMatrices(){
		Assert.assertTrue(twoByThree.equals(twoByThreeSecond));
	}
	
	@Test
	public void testMatrixEqualityDifferentDimensions(){
		Assert.assertFalse(twoByThree.equals(threeByTwo));
	}
	
	@Test
	public void testMatrixEqualityDifferentNumbers(){
		Assert.assertFalse(twoByTwoResult.equals(twoByTwoIdentity));
	}
	
	@Test
	public void twoByTwoToString() {
		String resultString = "13 12 \n29 26 \n";
		Assert.assertEquals(resultString, twoByTwoResult.toString());
	}
	
	@Test
	public void timesWithBalancedDimensions() {
		Matrix matrixProduct = twoByThree.times(threeByTwo);
		Assert.assertTrue(twoByTwoResult.equals(matrixProduct));
	}
	
	@Test
	public void timesWithIdentityMatrix(){
		Matrix matrixProduct1 = threeByTwo.times(twoByTwoIdentity);
		Matrix matrixProduct2 = twoByTwoIdentity.times(twoByThree);
		Assert.assertTrue(matrixProduct1.equals(threeByTwo) && matrixProduct2.equals(twoByThree));
	}
	
	@Test
	public void timesWithOneByOneMatrixBalanced(){
		Matrix matrixProduct = oneByOne.times(oneByOne);
		Assert.assertTrue(matrixProduct.equals(oneByOne));
	}
	
	@Test
	public void timesWithUnbalancedDimensions(){
		Matrix matrixProduct = twoByThree.times(twoByTwoResult);
		Assert.assertTrue(matrixProduct == null);
	}
	
	@Test
	public void unbalancedTimesWithOneByOneMatrix(){
		Matrix matrixProduct = twoByThree.times(oneByOne);
		Assert.assertEquals(null, matrixProduct);
	}
}
