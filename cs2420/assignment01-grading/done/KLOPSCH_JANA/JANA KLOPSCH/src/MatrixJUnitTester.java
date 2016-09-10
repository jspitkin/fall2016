package assignment01;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class MatrixJUnitTester {

	Matrix threeByTwo, twoByThree, twoByTwoResult, twoByTwoResultCopy, boundaryOneByOne, threeByTwoAdd, threeByTwoSum, nullTwoByTwo, zeroTwoByTwo;
	
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
		twoByTwoResultCopy = new Matrix(new int[][]
                					{{13, 12},
				 					 {29, 26}});
		boundaryOneByOne = new Matrix(new int[][] 
				{{0}});
		threeByTwoAdd = new Matrix(new int[][]
                {{-1, 4, 3},
				 {2, 5, -6}});
		threeByTwoSum = new Matrix(new int[][]
				{{0, 6, 6},
				 {4, 10, 0}});
		nullTwoByTwo = new Matrix(new int[2][2]);
		
		zeroTwoByTwo = new Matrix(new int[][]
                {{0, 0},
				 {0, 0}});
	}
	
	
	
	//Tests for equals method
	@Test
	public void equalsWithUnbalancedDimensions(){
		Assert.assertFalse(threeByTwo.equals(twoByThree));
	}
	@Test
	public void equalsWithSameMatrix(){
		Assert.assertTrue(threeByTwo.equals(threeByTwo));
	}
	@Test
	public void equalsWithBoundaryMatrix(){
		Assert.assertFalse(threeByTwo.equals(boundaryOneByOne));
	}
	@Test
	public void equalsWithSameValuesMatrices(){
		Assert.assertTrue(twoByTwoResult.equals(twoByTwoResultCopy));
	}
	@Test
	public void equalsZeroWithNullMatrix(){
		Assert.assertTrue(zeroTwoByTwo.equals(nullTwoByTwo));
	}
	
	//Tests for toString method
	@Test
	public void twoByTwoToString() {
		String resultString = "13 12 " + "\n" + "29 26 ";
		Assert.assertEquals(resultString, twoByTwoResult.toString());
	}
	@Test
	public void boundaryToStringBasic(){
		String boundaryAnswer = "0 ";
		Assert.assertEquals(boundaryAnswer, boundaryOneByOne.toString());
	}
	@Test
	public void columnsLargerThanRowsToString(){
		String colLarger = "1 2 3 " + "\n" + "2 5 6 ";
		Assert.assertEquals(colLarger, threeByTwo.toString());
	}
	@Test
	public void moreThanTwoRowsToString(){
		String rowsLarge = "4 5 " + "\n" + "3 2 " + "\n" + "1 1 ";
		Assert.assertEquals(rowsLarge, twoByThree.toString());
	}
	@Test
	public void zeroMatrixToString(){
		String zeros = "0 0 " + "\n" + "0 0 ";
		Assert.assertEquals(zeros, zeroTwoByTwo.toString());
	}
	@Test
	public void nullMatrixToString(){
		String nulls = "0 0 " + "\n" + "0 0 ";
		Assert.assertEquals(nulls, nullTwoByTwo.toString());
	}
	
	//Tests for plus method
	@Test
	public void plusBasicTest(){
		Matrix matrixSum = threeByTwo.plus(threeByTwoAdd);
		Assert.assertTrue(threeByTwoSum.equals(matrixSum));
	}
	@Test
	public void unbalancedMatrixPlus(){
		Assert.assertEquals(null, threeByTwo.plus(twoByThree));
	}
	@Test
	public void matrixPlusZeroTest(){
		Assert.assertEquals(twoByTwoResult, twoByTwoResult.plus(zeroTwoByTwo));
	}
	@Test
	public void matrixPlusNullTest(){
		Assert.assertEquals(twoByTwoResult, twoByTwoResult.plus(nullTwoByTwo));
	}
	
	//Tests for times method
	@Test
	public void timesWithBalancedDimensions() {
		Matrix matrixProduct = threeByTwo.times(twoByThree);
		Assert.assertTrue(twoByTwoResult.equals(matrixProduct));
	}
	@Test
	public void timesWithUnbalancedDimensions(){
		Assert.assertEquals(null, twoByThree.times(twoByThree));
	}
	@Test
	public void timesZeroMatrix(){
		Assert.assertEquals(zeroTwoByTwo, zeroTwoByTwo.times(twoByTwoResult));
	}
	@Test
	public void timesNullMatrix(){
		Assert.assertEquals(zeroTwoByTwo, nullTwoByTwo.times(twoByTwoResult));
	}
	@Test
	public void timesUnbalancedZeroMatrices(){
		Assert.assertEquals(null, zeroTwoByTwo.times(boundaryOneByOne));
	}
	

}
