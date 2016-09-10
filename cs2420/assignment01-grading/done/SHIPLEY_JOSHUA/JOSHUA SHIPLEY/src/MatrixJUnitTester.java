/*
 * Here is a starting point for your Matrix tester. You will have to fill in the rest of "main" with
 * more code to sufficiently test your Matrix class. We will be using our own MatrixTester for grading. 
*/
package assignment01;

import static org.junit.Assert.*;

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
	public void equalsTest()
	{
		assertFalse(threeByTwo.equals(twoByThree));
		assertFalse(twoByTwoResult.equals(new int[2][2]));
		Matrix twoByTwoEqual = new Matrix(new int[][]
											{{13, 12},
				 							{29, 26}});
		assertTrue(twoByTwoResult.equals(twoByTwoEqual));
	}
	
	@Test
	public void plusTest(){
		assertEquals(null, threeByTwo.plus(twoByThree));
		Matrix threeByTwoAddition = new Matrix(new int[][]
                								{{10, 20, 30},
				 								{20, 50, 60}});
		Matrix threeByTwoSum = new Matrix(new int[][]
											{{11, 22, 33},
				 							{22, 55, 66}});
		assertEquals(threeByTwoSum, threeByTwo.plus(threeByTwoAddition));
	}
	
	@Test(expected = NullPointerException.class)
	public void plusNullExceptionTest(){
		threeByTwo.plus(null);
	}
	
	@Test
	public void timesWithBalancedDimensions() {
		Matrix matrixProduct = threeByTwo.times(twoByThree);
		Assert.assertTrue(twoByTwoResult.equals(matrixProduct));
	}
	
	@Test
	public void timesWithUnbalancedDimensions(){
		Matrix oneByOne = new Matrix(new int[1][1]);
		assertEquals(null, threeByTwo.times(oneByOne));
	}
	
	@Test(expected = NullPointerException.class)
	public void timesNullExceptionTest(){
		threeByTwo.times(null);
	}
	
	@Test
	public void twoByTwoToString() {
		String resultString = "13 12 \n29 26 \n";
		Assert.assertEquals(resultString, twoByTwoResult.toString());
	}
}
