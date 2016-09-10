/*
 * Here is a starting point for your Matrix tester. You will have to fill in the rest of "main" with
 * more code to sufficiently test your Matrix class. We will be using our own MatrixTester for grading. 
*/
package assignment01;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class MatrixJUnitTester {

	Matrix threeByTwo, twoByThree, twoByTwoResult, twoByTwo, twoByTwoPlusResult, twoByThreeWithOneDifferentNumber;
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
		twoByTwo = new Matrix(new int[][]
				{{1, 2},
				 {3, 4}});
		//This is the result of adding twoByTwo by itself
		twoByTwoPlusResult = new Matrix(new int[][]
				{{2, 4},
				 {6, 8}});
		twoByThreeWithOneDifferentNumber = new Matrix(new int[][]
				{{4,5},
				 {3,2},
				 {1,9}});
	}
	
	@Test
	public void timesWithBalancedDimensions() {
		Matrix matrixProduct = threeByTwo.times(twoByThree);
		Assert.assertTrue(twoByTwoResult.equals(matrixProduct));
	}
	
	@Test
	public void timesWithUnbalancedDimensions(){
		Matrix matrixProduct = twoByTwoResult.times(twoByThree);
		Assert.assertEquals(matrixProduct, null);
	}
	
	@Test
	public void twoByTwoToString() {
		String resultString = "13 12 \n29 26 \n";
		Assert.assertEquals(resultString, twoByTwoResult.toString());
	}
	
	@Test
	public void plusWithBalancedDimensions(){
		Matrix matrixSum = twoByTwo.plus(twoByTwo);
		Assert.assertTrue(twoByTwoPlusResult.equals(matrixSum));
	}
	
	@Test
	public void plusWithUnbalancedDimensions(){
		Matrix matrixSum = twoByTwo.plus(twoByThree);
		Assert.assertEquals(matrixSum, null);
	}
	
	@Test
	public void equalsWithIdenticalMatricies(){
		boolean equalMatrix = twoByTwo.equals(twoByTwo);
		Assert.assertEquals(equalMatrix, true);
	}
	
	@Test
	public void equalsWithDifferentMatriciesOfSameSize(){
		boolean equalMatrix = twoByThree.equals(twoByThreeWithOneDifferentNumber);
		Assert.assertEquals(equalMatrix, false);
	}
	
	@Test
	public void equalsWithDifferentMatriciesOfDifferentSize(){
		boolean equalMatrix = twoByThree.equals(twoByTwo);
		Assert.assertEquals(equalMatrix, false);
	}
	
	@Test
	public void equalsWIthNonMatrixObject(){
		//Creating a string object to test if it recognizes it as not a matrix
		String nonMatrixObject = "";
		
		boolean notAMatrix = twoByThree.equals(nonMatrixObject);
		Assert.assertEquals(notAMatrix, false);
	}
	
	@Test
	public void equalsWithNonObject(){
		//Creating a int to make sure it returns false with a non object
		int notAnObject = 4;
		
		boolean equalsNonObject = twoByThree.equals(notAnObject);
		Assert.assertEquals(equalsNonObject, false);
	}
	
	
}
