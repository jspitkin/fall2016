/*
 * Here is a starting point for your Matrix tester. You will have to fill in the rest of "main" with
 * more code to sufficiently test your Matrix class. We will be using our own MatrixTester for grading. 
*/
package assignment01;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class MatrixJUnitTester {

	Matrix threeByTwo, twoByThree, twoByTwoResult, twoByTwo, oneByTwo, oneByOne, twoByTwo1, plusTwoByTwoResult, threeByTwo1, timesTwoByTwoResult;
	/*Initialize some matrices we can play with for every test!*/
	int twoByTwoInt[][]; 
	@Before
	public void setup() {
		twoByThree = new Matrix(new int[][]
                {{1, 2, 3},
				 {2, 5, 6}});
		threeByTwo = new Matrix(new int[][]
                {{4, 5},
				 {3, 2},
				 {1, 1}});
		twoByTwo = new Matrix(new int[][] 
				{{3, 5},
				 {9, 1}});
		twoByTwo1 = new Matrix(new int[][]
				{{2, 9},
				 {3, 1}}); 
		oneByTwo = new Matrix(new int[][]
				{{1, 6}});
		oneByOne = new Matrix(new int[][]
				{{8}});
		threeByTwo1 = new Matrix(new int[][]
                {{6, 2},
				 {8, 1},
				 {4, 5}});
		// this is the known correct result of multiplying M1 by M2
		twoByTwoResult = new Matrix(new int[][]
				{{13, 12},
			     {29, 26}});
		plusTwoByTwoResult = new Matrix(new int[][]
				{{5, 14},
			     {12, 2}});
		timesTwoByTwoResult = new Matrix(new int[][]
				{{24, 37}, 
				 {30, 83}});
	}
	
	
	
	@Test
	public void timesWithBalancedDimensions() {
		Matrix matrixProduct = twoByThree.times(threeByTwo);
		Assert.assertTrue(twoByTwoResult.toString().equals(matrixProduct.toString()));
	}
	
	@Test
	public void timesWithUnbalancedDimensions(){
		Matrix matrixProduct = twoByTwo.times(oneByOne);
		Assert.assertFalse(twoByTwoResult.equals(matrixProduct));
	}
	
	@Test
	public void plusWithBalancedDimensions() {
		Matrix matrixSum = twoByTwo.plus(twoByTwo1);
		Assert.assertTrue(plusTwoByTwoResult.toString().equals(matrixSum.toString()));
	}
	
	@Test
	public void plusWithUnbalancedDimensions() {
		Matrix matrixSum = twoByTwo.plus(threeByTwo);
		Assert.assertFalse(plusTwoByTwoResult.equals(matrixSum));
	}
		
	@Test
	public void matricesEqualityCheck() { 
		String matrixCheck = twoByTwo.toString();
		Assert.assertTrue(matrixCheck.equals(twoByTwo.toString()));
	}
	
	@Test
	public void matricesNotEqual() {
		String matrixCheck = threeByTwo.toString();
		Assert.assertFalse(matrixCheck.equals(oneByTwo.toString()));
	}
	
	@Test
	public void twoByTwoToString() {
		String resultString = "13 12 \n"  + "29 26 \n";
		Assert.assertEquals(resultString, twoByTwoResult.toString());
	}
	
	@Test
	public void threeByTwoString() {
		String resultString = "4 5 \n" + "3 2 \n" + "1 1 \n";
		Assert.assertEquals(resultString, threeByTwo.toString());
	}
}