package assignment01;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * MatrixJUnitTester - rigorously tests the methods from the class Matrix. 
 * 
 * 08/29/2016
 * 
 * @author Kyle Price
 */
public class MatrixJUnitTester {

	Matrix threeByTwo, twoByThree, twoByTwoResult, threeByTwoCopy, threeByTwoSum, timesSimpleTest,
	timesSimpleTestBasicMultiplier, bigMatrix, singleColumn, bigMatrixCopy, bigMatrixSum;
	
	@Before
	public void setup() {
		threeByTwo = new Matrix(new int[][]
                {{1, 2, 3},
				 {2, 5, 6}});
		threeByTwoCopy = new Matrix(new int[][]
                {{1, 2, 3},
				 {2, 5, 6}});
		threeByTwoSum = new Matrix(new int[][]
                {{2, 4, 6},
				 {4, 10, 12}});
		twoByThree = new Matrix(new int[][]
                {{4, 5},
				 {3, 2},
				 {1, 1}});
		timesSimpleTest = new Matrix(new int[][]
                {{1, 2, 3},
				 {4, 5, 6}});
		timesSimpleTestBasicMultiplier = new Matrix(new int[][]
				{{7, 8},
				{9, 10},
				{11, 12}});
		bigMatrix = new Matrix(new int[][]
                {{1, 2, 3},
				 {2, 5, 6}, {7, 8, 9}, {10, 11, 12}});
		bigMatrixCopy = new Matrix(new int[][]
                {{1, 2, 3},
				 {2, 5, 6}, {7, 8, 9}, {10, 11, 12}});
		bigMatrixSum = new Matrix(new int[][]
                {{2, 4, 6},
				 {4, 10, 12}, {14, 16, 18}, {20, 22, 24}});
		twoByTwoResult = new Matrix(new int[][]
                {{13, 12},
				{29, 26}});
		singleColumn = new Matrix(new int[][]
                {{4},
				 {3},
				 {1}});
	}
	
	// toString Test
	
	@Test
	public void twoByTwoToString() {
		String resultString = "13 12 " + "\n" + "29 26 ";
		Assert.assertEquals(resultString, twoByTwoResult.toString());
	}
	@Test
	public void threeByTwoToString() {
		String resultString = "1 2 3 " + "\n" + "2 5 6 ";
		Assert.assertEquals(resultString, threeByTwo.toString());
	}
	@Test
	public void singleColumnToString() {
		String resultString = "4 " + "\n" + "3 " + "\n" + "1 ";
		Assert.assertEquals(resultString, singleColumn.toString());
	}
	@Test
	public void twoByThreeToString() {
		String resultString = "4 5 " + "\n" + "3 2 " + "\n" + "1 1 ";
		Assert.assertEquals(resultString, twoByThree.toString());
	}
	
	// equals tests
	
	@Test
	public void twoByThreeEquals() {
		Assert.assertEquals(false, twoByThree.equals(threeByTwo));
	}
	@Test
	public void shouldBeTrueEquals() {
		Assert.assertEquals(true, threeByTwoCopy.equals(threeByTwo));
	}
	@Test
	public void shouldBeTrueOnItselfEquals() {
		Assert.assertEquals(true, twoByThree.equals(twoByThree));
	}
	@Test
	public void singleAndBigMatrixEquals() {
		Assert.assertEquals(false, bigMatrix.equals(singleColumn));
	}
	@Test
	public void BigMatrixCopyEquals() {
		Assert.assertEquals(true, bigMatrix.equals(bigMatrixCopy));
	}
	
	// plus tests
	
	@Test
	public void plusBasicTest() {
		Assert.assertEquals(threeByTwoSum, threeByTwo.plus(threeByTwoCopy));
	}
	@Test
	public void plusNull() {
		Assert.assertEquals(null, twoByThree.plus(threeByTwoCopy));
	}
	@Test
	public void plusLargeSum() {
		Assert.assertEquals(bigMatrixSum, bigMatrix.plus(bigMatrixCopy));
	}
	@Test
	public void plusNullClose() {
		Assert.assertEquals(null, twoByThree.plus(threeByTwoSum));
	}
	
	// times tests
	
	@Test
	public void timesBasic2By3() {
		Matrix matrix2By2 = new Matrix(new int [][] {{58, 64}, {139, 154}});
		Assert.assertEquals(matrix2By2, timesSimpleTest.times(timesSimpleTestBasicMultiplier));
	}
	@Test (expected = IllegalArgumentException.class)
	public void timesException() {
		bigMatrix.times(threeByTwo);
		}
	@Test (expected = IllegalArgumentException.class)
	public void timesWithUnbalancedDimensions(){
		twoByTwoResult.times(singleColumn);
	}
	@Test
	public void equalsAndTimes() {
		Matrix matrixProduct = threeByTwo.times(twoByThree);
		Assert.assertTrue(twoByTwoResult.equals(matrixProduct));
	}
	@Test
	public void bigMatrixTimes() {
		Matrix newProduct = new Matrix(new int[][]
                 {{13, 12},
				 {29, 26}, 
				 {61, 60}, 
				 {85, 84}});
		Matrix matrixProduct = bigMatrix.times(twoByThree);
		Assert.assertEquals(newProduct, matrixProduct);
	}
}
