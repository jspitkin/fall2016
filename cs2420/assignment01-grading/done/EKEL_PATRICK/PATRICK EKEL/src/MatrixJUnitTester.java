/*
 * Here is a starting point for your Matrix tester. You will have to fill in the rest of "main" with
 * more code to sufficiently test your Matrix class. We will be using our own MatrixTester for grading. 
*/
package assignment01;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
/**
 * this class tests the matrix class
 * @author pat ekel
 * 8/31/16
 *
 */
public class MatrixJUnitTester {

	Matrix nullMatrix, oneByOne, twoByOneResult, twoByOne, twoByOneClone, twoByOneSum, threeByTwo, twoByThree, twoByTwo, threeByTwoRightSide, threeByTwoResult, threeByThree, threeByThreeClone;
	/* Initialize some matrices we can play with for every test! */

	@Before
	public void setup() {
		//matrices to use during tests
		nullMatrix = new Matrix(new int[][] {{}, {}});
		twoByThree = new Matrix(new int[][] { { 1, 2, 3 }, { 2, 5, 6 } });
		threeByTwo = new Matrix(new int[][] { { 4, 5 }, { 3, 2 }, { 1, 1 } });
		twoByOne = new Matrix(new int[][] {{2}, {1}});
		oneByOne = new Matrix(new int [][] {{3}});
		twoByOneResult = new Matrix(new int[][] {{6}, {3}});
		twoByOneClone = new Matrix(new int[][] {{2}, {1}});
		twoByOneSum = new Matrix(new int [][] {{4}, {2}});
		threeByThree = new Matrix(new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 1, 1, 1 } });
		threeByThreeClone = new Matrix(new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 1, 1, 1 } });
		threeByTwoRightSide = new Matrix(new int[][] {{ 4, 5 }, { 3, 2 }, { 1, 1 } });
		twoByTwo = new Matrix(new int[][] { { 13, 12 }, { 29, 26 } });
	}
	
	//equals() tests
	@Test
	public void equalsDiffRowAndColumnDim() {
		assertFalse(threeByTwo.equals(twoByThree));
	}
	@Test
	public void equalsDiffColumnDim() {
		assertFalse(threeByTwo.equals(twoByThree));
	}
	@Test
	public void equalsDiffRowDim() {
		assertFalse(threeByTwo.equals(twoByOne));
	}
	@Test
	public void equalSelf() {
		assertTrue(threeByTwo.equals(threeByTwo));
	}
	@Test
	public void equalsSmall() {
		assertEquals(twoByOne, twoByOneClone);
	}

	@Test
	public void equalslarge() {
		assertTrue(threeByThree.equals(threeByThreeClone));
	}
	
	//toString() tests
	@Test
	 public void toStringTwoByOne(){
		 String twoByOneString = "2 " + "\n" + "1 " + "\n";
		 assertTrue(twoByOne.toString().equals(twoByOneString));
	 }
	 @Test
	 public void toStringTwoByTwo() {
	 String twoByTwoString = "13 12 " + "\n" + "29 26 " + "\n";
	 assertEquals(twoByTwoString, twoByTwo.toString());
	 }
	 
	 @Test
	 public void toStringThreeByThree(){
		 String threeByThreeString = "1 2 3 " + "\n" + "4 5 6 " + "\n" + "1 1 1 " + "\n";
		 assertEquals(threeByThreeString, threeByThree.toString());
	 }
	 @Test
	 public void toStringMultiplied(){
		 String twoByThreeTimesThreeByThreeString = "13 12 " + "\n" + "29 26 " + "\n";
		 assertEquals(twoByThreeTimesThreeByThreeString, twoByThree.times(threeByTwoRightSide).toString());
	 }
	 
	 //plus() tests
	 @Test
	 public void plusTwoByOnePlusNull(){
		assertEquals(null, nullMatrix.plus(twoByOne)); 
	 }
	 @Test
	 public void plusSameSizes(){
		assertTrue(twoByOneSum.equals(twoByOne.plus(twoByOne))); 
	 }
	 @Test
	 public void plusDifferentSizes(){
		assertEquals(null, threeByThree.plus(twoByOne)); 
	 }
	 @Test
	 public void plusWithNonBalancedDimensions() {
	 assertEquals(null, twoByTwo.plus(twoByOne));
	 }
	 @Test
	 public void plusWithNonBalancedDimensionss() {
	 assertTrue((twoByTwo.plus(twoByOne))==(null));
	 }
	 
	 //times() tests
	 @Test
	 public void timesWithBalancedDimensions() {
	 Matrix matrixProduct = twoByThree.times(threeByTwo);
	 assertEquals(twoByTwo, matrixProduct);
	 }
	 @Test
	 public void timesWithBalancedDimensionsSmall() {
	 Matrix matrixProduct = twoByOne.times(oneByOne);
	 assertEquals(twoByOneResult, matrixProduct);
	 }
	 @Test
	 public void timesWithNonBalancedDimensions() {
	 Matrix matrixProduct = twoByThree.times(twoByTwo);
	 assertEquals(null, matrixProduct);
	 }
	 @Test
	 public void timesSelf() {
	 Matrix matrixProduct = twoByOne.times(twoByTwo);
	 assertEquals(null, matrixProduct);
	 }
	 @Test
	 public void timesANullMatrix() {
	 Matrix matrixProduct = twoByThree.times(nullMatrix);
	 assertEquals(null, matrixProduct);
	 }
}
