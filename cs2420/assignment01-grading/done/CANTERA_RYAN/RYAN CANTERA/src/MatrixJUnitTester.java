package assignment01;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Ryan Cantera
 * u0855101
 * 8/31/2016
 * This is the tester class for Matrix.java. 
 */
public class MatrixJUnitTester {

	Matrix threeByTwo, twoByThree, anotherTwoByThree, twoByTwo, oneByOne, oneByTwo, twoByTwoResult, 
	twoByThreePlusTwoByThree, timesByOne;
	
	@Before
	public void setup() {
		threeByTwo = new Matrix(new int[][]
                {{1, 2, 3},
				 {2, 5, 6}});
		twoByThree = new Matrix(new int[][]
                {{4, 5},
				 {3, 2},
				 {1, 1}});
		anotherTwoByThree = new Matrix(new int[][]
				{{2, 6},
				 {1, 3},
				 {7, 2}});
		twoByTwo = new Matrix(new int[][]
				{{2, 3},
				 {4, 5}});
		oneByOne = new Matrix(new int[][]{{5}});
		
		oneByTwo = new Matrix(new int[][] {{2, 3}});
		
		twoByThreePlusTwoByThree = new Matrix(new int[][]
				{{6, 11},
			     {4, 5},
			     {8, 3}});
		twoByTwoResult = new Matrix(new int[][]
                                    {{13, 12},
									 {29, 26}});
		timesByOne = new Matrix(new int[][]
				{{10, 15}});
		
	}
	
	@Test
	public void timesWithBalancedDimensions() {
		Matrix matrixProduct = threeByTwo.times(twoByThree);
		Assert.assertTrue(twoByTwoResult.equals(matrixProduct));
		Matrix matrixProduct2 = oneByOne.times(oneByTwo);
		Assert.assertTrue(timesByOne.equals(matrixProduct2));
	}
	
	@Test
	public void timesWithUnbalancedDimensions(){
		Matrix matrixProduct = threeByTwo.times(twoByTwo);
		Assert.assertTrue(matrixProduct == null);
		Matrix matrixProduct2 = oneByOne.times(threeByTwo);
		Assert.assertTrue(matrixProduct2 == null);
	}
	
	@Test
	public void plusWithBalancedDimensions() {
		Matrix matrixSum = twoByThree.plus(anotherTwoByThree);
		Assert.assertTrue(twoByThreePlusTwoByThree.equals(matrixSum));
	}
	@Test
	public void plusWithUnbalancedDimensions() {
		Matrix matrixSum = threeByTwo.plus(twoByThree);
		Assert.assertTrue(matrixSum == null);
	}
	@Test
	public void twoByTwoToString() {
		String resultString = "13 12 \n29 26 \n";
		Assert.assertEquals(resultString, twoByTwoResult.toString());
	}
}
