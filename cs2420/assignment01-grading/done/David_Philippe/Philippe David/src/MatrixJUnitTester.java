package assignment01;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * MatrixJUnitTester.java -- A program that uses the JUnit testing framework
 * to test the other program required in this assignment.
 * <p>
 * Written to complete assignment 1 for CS 2420.
 * 
 * @author Philippe David, last update 9/29/16
 */
public class MatrixJUnitTester {
	
	// Private member variables that will hold the majority of the matrices used in these test.
	
	private Matrix oneBySixAddedToItself, twoByThreeAddedToItself, twoByTwo, sixByOne, oneBySix, 
	               matrixOneHundredByOneHundred, threeByThreeTwo, oneByOneTwoIdentical, threeByTwoIdentical, 
	               threeByTwoAddedToItself, oneByOneTwo, oneByOne, threeByTwo, twoByThree, threeByThree,
	               twoByTwoMultiplicationResult;
	
	/*Initialize some matrices we can play with for every test!*/	
	@Before
	public void setup() {
		
		oneByOne = new Matrix(new int[][]
				{{1}});
		
		oneByOneTwo = new Matrix(new int[][]
				{{3}});
	
		// This matrix is the same as above, used for comparison
		oneByOneTwoIdentical = new Matrix(new int[][]
				{{3}});		
		
		twoByTwo = new Matrix(new int[][]
                {{1, 2},
				 {2, 5}});
		
		threeByTwo = new Matrix(new int[][]
                {{1, 2, 3},
				 {2, 5, 6}});
		
		// This matrix is the same as above, used for comparison
		threeByTwoIdentical = new Matrix(new int[][]
                {{1, 2, 3},
				 {2, 5, 6}});
		
		// This matrix is the result of adding the matrix threeByTwo with itself
		threeByTwoAddedToItself = new Matrix(new int[][]
                {{2, 4, 6},
				 {4, 10, 12}});		
		
		twoByThree = new Matrix(new int[][]
                {{4, 5},
				 {3, 2},
				 {1, 1}});
		
		// This matrix is the result of adding the matrix twoByThree with itself
		twoByThreeAddedToItself = new Matrix(new int[][]
                {{8, 10},
				 {6, 4},
				 {2, 2}});
		
		threeByThree = new Matrix(new int[][]
                {{1, 4, 5},
				 {1, 3, 2},
				 {2, 1, 1}});
		
		threeByThreeTwo = new Matrix(new int[][]
				{{1, 42, 5}, 
			     {1, 30, 2}, 	
			     {29, 1, 1}});
		
		// This is the  correct result of multiplying the matrices threeByTwo by twoByThree (computed using Matlab)
		twoByTwoMultiplicationResult = new Matrix(new int[][]
                {{13, 12},
				 {29, 26}});
		
		oneBySix = new Matrix(new int[][]
				{{1}, 
			     {2}, 	
			     {29},
			     {1}, 
			     {12}, 	
			     {21}});
		
		// This matrix is the result of adding the matrix oneBySix with itself
		oneBySixAddedToItself = new Matrix(new int[][]
				{{2}, 
			     {4}, 	
			     {58},
			     {2}, 
			     {24}, 	
			     {42}});
		
		sixByOne = new Matrix(new int[][]
				{{1,2,3,4,5,6}});	
		
		/*
		 *  The last few lines here create a 100X100 matrix with values of one in each cell.
		 */
		int[][] twoDimensionArray = new int[100][100];
		
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				twoDimensionArray[i][j] = 1;
			}
		}
		
		matrixOneHundredByOneHundred = new Matrix(twoDimensionArray);
			    
		}
	
	/* 
	 * These seven tests examine the case for when two matrices are multiplied together 
	 * with compatible dimensions.
	 */
	@Test
	public void multiplicationWithBalancedDimensions() {
		Matrix matrixProduct = threeByTwo.times(twoByThree);
		Assert.assertTrue(twoByTwoMultiplicationResult.equals(matrixProduct));
	}	
	@Test
	public void multiplicationWithBalancedDimensionsTwo() {
		// One by one matrix edge case
		Matrix matrixProduct = oneByOne.times(oneByOneTwo);
		Assert.assertTrue(oneByOneTwo.equals(matrixProduct));
	}	
	@Test
	public void multiplicationWithBalancedDimensionsThree() {
		Matrix matrixProduct = oneByOneTwo.times(oneByOneTwo);
		Matrix calculatedMatrixProduct = new Matrix(new int[][]
				{{9}});
		Assert.assertTrue(calculatedMatrixProduct.equals(matrixProduct));
	}	
	@Test
	public void multiplicationWithBalancedDimensionsFour() {
		Matrix matrixProduct = threeByThree.times(threeByThree);
		Matrix calculatedMatrixProduct = new Matrix(new int[][]
				{{15, 21, 18}, {8, 15, 13}, {5, 12, 13}});	
		Assert.assertTrue(calculatedMatrixProduct.equals(matrixProduct));
	}	
	@Test
	public void multiplicationWithBalancedDimensionsFive() {
		Matrix matrixProduct = threeByThree.times(threeByThreeTwo);
		Matrix calculatedMatrixProduct = new Matrix(new int[][]
				{{150, 167, 18}, {62, 134, 13}, {32, 115, 13}});	
		Assert.assertTrue(calculatedMatrixProduct.equals(matrixProduct));
	}	
    @Test
	public void multiplicationWithBalancedDimensionsSix() {
		Matrix matrixProduct = threeByThree.times(threeByThreeTwo);
		Matrix calculatedMatrixProduct = new Matrix(new int[][]
				{{150, 167, 18}, {62, 134, 13}, {32, 115, 13}});	
		Assert.assertTrue(calculatedMatrixProduct.equals(matrixProduct));
	}	
    @Test
	public void multiplicationWithBalancedDimensionsSeven() {
    	//using example on canvas page
    	Matrix mOne = new Matrix(new int[][]
				{{1,2,3}, {4,5,6}});
    	
    	Matrix mTwo = new Matrix(new int[][]
				{{7,8}, {9,10}, {11,12}});
    	
    	Matrix mOneTimesmTwo = new Matrix(new int[][]
				{{58,64}, {139,154}});
    				
		Assert.assertTrue(mOneTimesmTwo.equals(mOne.times(mTwo)));
	}
	@Test
	public void multiplicationWithBalancedDimensionsLarge() {
		
		int[][] m1 = new int[100][100];
		
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				m1[i][j] = 1;
			}
		}
		

		int[][] m2 = new int[100][100];
		
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				m2[i][j] = 2;
			}
		}
		
		int[][] mProduct = new int[100][100];
		
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				mProduct[i][j] = 200;
			}
		}
		
		
		Matrix matrixOne = new Matrix(m1);

		Matrix matrixTwo = new Matrix(m2);

		Matrix calculatedMatrixProduct = new Matrix(mProduct);

		Matrix matrixProduct = matrixOne.times(matrixTwo);

		Assert.assertTrue(calculatedMatrixProduct.equals(matrixProduct));
	}
	
	/* 
	 * These five  tests examine the case for when two matrices are multiplied together 
	 * with incompatible dimensions.
	 */
	@Test
	public void multiplicationWithUnbalancedDimensions(){
		Assert.assertEquals(null,twoByThree.times(threeByThree));
	}
	@Test
	public void multiplicationWithUnbalancedDimensionsTwo(){
		Assert.assertEquals(null,oneByOne.times(threeByThree));
	}
	@Test
	public void multiplicationWithUnbalancedDimensionsThree(){
		// One by one matrix edge case
		Assert.assertEquals(null,oneByOne.times(threeByTwo));
	}
	@Test
	public void multiplicationWithUnbalancedDimensionsFour(){
		Assert.assertEquals(null,twoByTwo.times(twoByThree));
	}
	@Test
	public void multiplicationWithUnbalancedDimensionsFive(){
		Assert.assertEquals(null,matrixOneHundredByOneHundred.times(threeByTwo));
	}
	
	/* 
	 * These five  tests examine the case for when two matrices are added together 
	 * with compatible dimensions.
	 */
	@Test
	public void threeByTwoPlusTwoByThree() {
		Assert.assertEquals(null,threeByTwo.plus(twoByThree));
	}
	@Test
	public void additionWithBalancedDimensions() {
		Matrix matrixAdditionResult = threeByTwo.plus(threeByTwo);
		Assert.assertTrue(threeByTwoAddedToItself.equals(matrixAdditionResult));
	}
	@Test
	public void additionWithBalancedDimensionsTwo() {
		// One by one matrix edge case

		Matrix matrixAdditionResult = oneByOne.plus(oneByOneTwo);
		Matrix easyCalculatedAddition = new Matrix(new int[][]
				{{4}});
		Assert.assertTrue(easyCalculatedAddition.equals(matrixAdditionResult));
	}
	@Test
	public void additionWithBalancedDimensionsThree() {
		Matrix matrixAdditionResultStepOne = oneByOne.plus(oneByOne);
		Matrix matrixAdditionResultStepTwo = oneByOne.plus(matrixAdditionResultStepOne);
		Assert.assertTrue(matrixAdditionResultStepTwo.equals(oneByOneTwo));
	}
	@Test
	public void additionWithBalancedDimensionsFour() {
		Matrix matrixAdditionResult = twoByThree.plus(twoByThree);
		Assert.assertTrue(matrixAdditionResult.equals(twoByThreeAddedToItself));
	}
	@Test
	public void additionWithBalancedDimensionsFive() {
		Matrix matrixAdditionResult = oneBySix.plus(oneBySix);
		Assert.assertTrue(matrixAdditionResult.equals(oneBySixAddedToItself));
	}
	
	/* 
	 * These six tests examine the case for when two matrices are added together 
	 * with incompatible dimensions.
	 */
	@Test
	public void additionWithUnbalancedDimensions(){
		Assert.assertEquals(null,twoByThree.plus(threeByThree));
	}
	@Test
	public void additionWithUnbalancedDimensionsTwo(){
		Assert.assertEquals(null,oneByOne.plus(threeByThree));
	}
	@Test
	public void additionWithUnbalancedDimensionsThree(){
		Assert.assertEquals(null,oneByOne.plus(oneBySix));
	}
	@Test
	public void additionWithUnbalancedDimensionsFour(){
		Assert.assertTrue(null == threeByThree.plus(oneBySix));
	}	
	@Test
	public void additionWithUnbalancedDimensionsFive(){
		Assert.assertTrue(null == sixByOne.plus(oneBySix));
	}
	@Test
	public void additionWithUnbalancedDimensionsSox(){
		Assert.assertTrue(null == oneByOne.plus(oneBySix));
	}
	
	/* 
	 * These six tests examine the toString() method on a number of different matrices.
	 */
	@Test
	public void twoByTwoToString() {
		String resultString = "13 12 " + "\n" + "29 26 " + "\n";
		Assert.assertEquals(resultString, twoByTwoMultiplicationResult.toString());
	}
	@Test
	public void toStringTestOne() {
		String resultString = "1 4 5 " + "\n" + "1 3 2 " + "\n" + "2 1 1 " + "\n";
		Assert.assertEquals(resultString, threeByThree.toString());
	}
	@Test
	public void toStringTestTwo() {
		// A 1d Matrix (edge case)
		String resultString = "1 " + "\n";
		Assert.assertEquals(resultString, oneByOne.toString());
	}
	@Test
	public void toStringTestThree() {
		String resultString = "2 4 6 " + "\n" + "4 10 12 " + "\n";
		Assert.assertEquals(resultString, threeByTwoAddedToItself.toString());
	}
	@Test
	public void toStringTestFour() {
		String resultString = "1 " + "\n" + "2 " + "\n" + "29 "+ "\n" + "1 " + "\n" + "12 "+ "\n" + "21 " + "\n";
		Assert.assertEquals(resultString, oneBySix.toString());
	}
	@Test
	public void toStringTestFive() {
		String resultString = "1 2 3 4 5 6 " + "\n";
		Assert.assertEquals(resultString, sixByOne.toString());
	}
	
	/* 
	 * These six tests examine the equals() method on a number of different matrices.
	 */
	@Test // This was an example test provided
	public void threeByTwoEquals() {
		
		// Testing two matrices that are equal.
		Assert.assertEquals(true,threeByTwo.equals(threeByTwoIdentical));
		
		// Testing two matrices that are not equal.
		Assert.assertEquals(false,threeByTwo.equals(twoByThree));
		
	}	
	@Test
	public void equalsTestOne() {
		
		// Testing two matrices that are equal.
		Assert.assertEquals(true,oneByOneTwo.equals(oneByOneTwoIdentical));
		
		// Testing two matrices that are not equal.
		Assert.assertEquals(false,oneByOneTwo.equals(oneByOne));
		
	}	
	@Test
	public void equalsTestTwo() {
		
		// Testing two matrices that are equal.
		Assert.assertEquals(true,sixByOne.equals(sixByOne));
		
		// Testing two matrices that are not equal.
		Assert.assertEquals(false,sixByOne.equals(oneBySix));
		
	}
	@Test
	public void equalsTestThree() {
		
		// Testing two matrices that are equal.
		Assert.assertEquals(true,threeByThree.equals(threeByThree));
		
		// Testing two matrices that are not equal.
		Assert.assertEquals(false,sixByOne.equals(threeByThree));
		
	}
	@Test
	public void equalsTestFour() {
		
		// Testing two matrices that are equal.
		Assert.assertEquals(true,twoByThree.equals(twoByThree));
		
		// Testing two matrices that are not equal.
		Assert.assertEquals(false,twoByThree.equals(oneByOne));
		
	}
	@Test
	public void equalsTestFive() {
		
		// Testing two matrices that are equal.
		Assert.assertEquals(true,oneByOne.equals(oneByOne));
		
		// Testing two matrices that are not equal.
		Assert.assertEquals(false,oneByOneTwo.equals(oneByOne));
		
	}
}
