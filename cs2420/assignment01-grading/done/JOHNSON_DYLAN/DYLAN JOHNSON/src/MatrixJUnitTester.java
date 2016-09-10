/*
 * Here is a starting point for your Matrix tester. You will have to fill in the rest of "main" with
 * more code to sufficiently test your Matrix class. We will be using our own MatrixTester for grading. 
 */
package assignment01;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MatrixJUnitTester {

	private Matrix nullMatrix, oneByOne, threeByTwo, twoByThree, twoByTwoResult, fourByTwo, sixByFourAllOnes, twoBySixResult;
	/* Initialize some matrices we can play with for every test! */
	private int[][] allOnes = new int[4][6]; 
	
	@Before
	public void setup() { // run before every test
		nullMatrix = null;
		oneByOne = new Matrix(new int[][] { { 3 } });
		threeByTwo = new Matrix(new int[][] { { 1, 2, 3 }, { 2, 5, 6 } });
		twoByThree = new Matrix(new int[][] { { 4, 5 }, { 3, 2 }, { 1, 1 } });
		fourByTwo = new Matrix (new int[][] { { 1, 2, 3, 4 }, {4, 6, 2, 1 } }); 
		for (int i = 0; i < allOnes.length; i++) {
			for (int j = 0; j < allOnes[0].length; j++) {
				allOnes[i][j] = 1;
			}
		}
		sixByFourAllOnes = new Matrix(allOnes);
		
		// this is the known correct result of multiplying threeByTwo by twoByThree
		twoByTwoResult = new Matrix(new int[][] { { 13, 12 }, { 29, 26 } });
		// this is the known correct result of multiplying fourByTwo by sixByFourAllOnes
		twoBySixResult = new Matrix(new int[][] { { 10, 10, 10, 10, 10, 10 }, 
												  { 13, 13, 13, 13, 13, 13 } });
	}

	@Test
	public void twoByTwoToString() {
		String resultString = "13 12 \n29 26 \n";
		Assert.assertEquals(resultString, twoByTwoResult.toString());
	}
	
	@Test 
	public void EqualsWithTwoIdenticalMatrices() {
		Matrix duplicateOfThreeByTwo = new Matrix(new int[][] { { 1, 2, 3 }, { 2, 5, 6 } });
		Assert.assertTrue(threeByTwo.equals(duplicateOfThreeByTwo));
	}
	
	@Test 
	public void EqualsWithUnequalMatrices() {
		Assert.assertFalse(twoByThree.equals(sixByFourAllOnes));
	}
	
	@Test
	public void EqualsWithNullParameter() {
		Assert.assertFalse(twoByThree.equals(nullMatrix));
	}
	
	@Test
	public void timesWithUnbalancedDimensions() {
		Assert.assertEquals(null, fourByTwo.times(threeByTwo));
	}
	
	@Test
	public void timesWithOneByOneMatrices() {
		Assert.assertEquals(new Matrix(new int[][]{{9}}), oneByOne.times(oneByOne));
	}
	
	@Test
	public void timesWithBalancedDimensions() {
		Matrix matrixProduct = threeByTwo.times(twoByThree);
		Assert.assertTrue(twoByTwoResult.equals(matrixProduct));
	}
	
	@Test
	public void timesWithBalancedDimesionsAndMediumSize() {
		// to ensure that times works with arrays larger than 3 x 3
		Assert.assertEquals(twoBySixResult, fourByTwo.times(sixByFourAllOnes));
	}
	
	@Test
	public void TimesAndPlusWithNullParamater() {
		Assert.assertEquals(null, twoByThree.times(nullMatrix));
		Assert.assertEquals(null, twoByThree.plus(nullMatrix));
	}
	
	@Test
	public void plusWithUnbalancedDimensions() {
		Assert.assertEquals(null, threeByTwo.plus(twoByThree));
	}
	
	@Test
	public void plusWithOneByOneMatrices() {
		Assert.assertEquals(new Matrix(new int[][]{{6}}), oneByOne.plus(oneByOne));
	}
	
	@Test
	public void plusWithBalancedDimensions() {
		// this is the known correct result of adding twoByThree to itself
		Matrix matrixSum = new Matrix(new int[][] { { 8, 10 }, { 6, 4 }, { 2, 2 } });
		Assert.assertEquals(matrixSum, twoByThree.plus(twoByThree));
	}
	
}
