/*
 * Here is a starting point for your Matrix tester. You will have to fill in the rest of "main" with
 * more code to sufficiently test your Matrix class. We will be using our own MatrixTester for grading. 
*/
package assignment01;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class MatrixJUnitTester {

	Matrix threeByTwo, twoByThree, twoByTwoResult, additionTwoByTwo, additionTwoByTwo1, additionResultTwoByTwo, multiResultTwoByTwo, 
	multiResultTwoByTwo1, fourByThree;
	/*Initialize some matrices we can play with for every test!*/
	
	@Before
	public void setup() {
		//3x2 matrix
		threeByTwo = new Matrix(new int[][]{{1, 2, 3},{2, 5, 6}});
		//2x3 matrix
		twoByThree = new Matrix(new int[][]{{4, 5},{3, 2},{1, 1}});
		//result of threeByTwo x twoByThree
		multiResultTwoByTwo = new Matrix(new int[][] {{13, 12}, {37, 36}});
		// this is the known correct result of multiplying M1 by M2
		twoByTwoResult = new Matrix(new int[][]{{13, 12},{29, 26}});
		//2x2 matrix for addition test
		additionTwoByTwo = new Matrix(new int[][]{{5, 10},{3,8}});
		//2nd 2x2 matrix for addition test
		additionTwoByTwo1 = new Matrix(new int[][]{{7, 13},{4,12}});
		// 2x2 addition result
		additionResultTwoByTwo = new Matrix(new int[][]{{17,23},{7,20}});
		// result of additionTwoByTwo x additionTwoByTwo1
		multiResultTwoByTwo1 = new Matrix(new int[][]{{75, 185},{53, 135}});
		//four by two. Used to insure the times method returns null. See times method for details.
		// Also used to check toString method.
		fourByThree = new Matrix(new int[][]{{1,2,3,4},{5,6,7,8},{9, 10, 11, 12}});
	}
	@Test
	public void timesWithBalancedDimensions() {
		Matrix matrixProduct = threeByTwo.times(twoByThree);
		Assert.assertTrue(twoByTwoResult.equals(matrixProduct));
	}
	
	@Test
	public void timesWithUnbalancedDimensions(){
		Assert.assertEquals(null, additionTwoByTwo.times(fourByThree));
	}
	
	@Test
	public void twoByThreeToString(){
		Assert.assertEquals("4 5 \n"  + "3 2 \n" + "1 1 \n", twoByThree.toString());
	}
	@Test
	public void threeByTwoToString() {
		Assert.assertEquals("1 2 3 \n"  + "2 5 6 \n", threeByTwo.toString());
	}
	@Test
	public void toStringTest(){
		Assert.assertEquals("13 12 \n"  + "29 26 \n", twoByTwoResult.toString());
	}
	@Test
	public void toStringTest1(){
		Assert.assertEquals("1 2 3 4 \n" + "5 6 7 8 \n" + "9 10 11 12 \n", fourByThree.toString());
	}
	@Test
	public void equalsTest(){
		Assert.assertTrue(twoByTwoResult.equals(twoByTwoResult));
	}
	@Test
	public void equalsTest1(){
		Assert.assertFalse(twoByTwoResult.equals(twoByThree));
	}
	@Test
	public void additionTest1(){
		Assert.assertEquals(additionResultTwoByTwo, additionTwoByTwo.plus(additionTwoByTwo1));
	}
	@Test
	public void multiTest1(){
		Assert.assertEquals(multiResultTwoByTwo, threeByTwo.times(twoByThree));
	}
	@Test
	public void multiTest2(){
		Assert.assertEquals(multiResultTwoByTwo1, additionTwoByTwo.times(additionTwoByTwo1));
	}
	
}
