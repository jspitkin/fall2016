package assignment01;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit Tester class for the Matrix.java class
 * testing for null pointers, end cases and so forth
 * for the following functions inside Matrix:
 * -toString
 * -times
 * -equal
 * -plus
 * 
 * @author cs2420 Teaching Staff and Spencer Jewett
 * last update: 8/30/2016
 *
 */
public class MatrixJUnitTester {

	Matrix threeByTwo, twoByThree, twoByTwoResult, threeByTwoAResult;
	/*Initialize some matrices we can play with for every test!*/
	
	@Before
	public void setup() {
		threeByTwo = new Matrix(new int[][]
                {{1, 2, 3},
				 {2, 5, 6}});
		threeByTwoAResult = new Matrix(new int[][]
                {{2, 4, 6},
				 {4, 10, 12}});
		twoByThree = new Matrix(new int[][]
                {{4, 5},
				 {3, 2},
				 {1, 1}});
		// this is the known correct result of multiplying M1 by M2
		twoByTwoResult = new Matrix(new int[][]
                                    {{13, 12},
									 {29, 26}});
	}
	
	
	//Times
	@Test
	public void timesWithBalancedDimensions() {
		Matrix matrixProduct = threeByTwo.times(twoByThree);
		Assert.assertTrue(twoByTwoResult.equals(matrixProduct));
	}
	
	@Test (expected=NullPointerException.class)
	public void timesWithUnbalancedDimensionsNullPointer(){
		Matrix maProd = twoByThree.times(twoByThree);
		Assert.assertFalse(maProd.equals(twoByTwoResult));
	}
	
	@Test
	public void timesWithUnbalancedDimensions(){
		Matrix maProd = twoByThree.times(twoByThree);
		Assert.assertFalse(twoByTwoResult.equals(maProd));
	}
	
	@Test
	public void timesWithBalancedDimensionsNegatives() {
		Matrix bravo = new Matrix(new int[][]
                {{-1, -2, -3},
				 {-2, -5, -6}});
		Matrix omega = new Matrix(new int[][]
                {{-13, -12},
				 {-29, -26}});
		Matrix matrixProduct = bravo.times(twoByThree);
		Assert.assertTrue(omega.equals(matrixProduct));
	}
	
	//toString
	@Test
	public void twoByTwoToString() {
		String resultString = "13 12 " + "\n" + "29 26 " + "\n";
		Assert.assertEquals(resultString, twoByTwoResult.toString());
	}
	
	@Test
	public void threeByTwoToString() {
		String resultString = "1 2 3 " + "\n" + "2 5 6 " + "\n";
		Assert.assertEquals(resultString, threeByTwo.toString());
	}
	
	@Test
	public void twoByThreeToString(){
		String resultString = "4 5 " + "\n" + "3 2 " + "\n" + "1 1 " + "\n";
		Assert.assertEquals(resultString,  twoByThree.toString());
	}
	
	//Equal
	@Test
	public void twoByTwoResultEquals(){
		Matrix mirror = new Matrix(new int[][]
                {{13, 12},
				 {29, 26}});
		Assert.assertEquals(true, twoByTwoResult.equals(mirror));
	}
	
	@Test
	public void twoByTwoResultNotEqual(){
		Assert.assertEquals(false, twoByTwoResult.equals(twoByThree));
	}
	
	@Test
	public void twoByTwoResultNotEqualSwitchedRows(){
		Matrix alpha = new Matrix(new int[][]
                {{29, 26},
				 {13, 12}});
		Assert.assertEquals(false, twoByTwoResult.equals(alpha));
	}
	
	@Test
	public void twoByTwoResultNotEqualLastPlaceWrong(){
		Matrix alpha = new Matrix(new int[][]
                {{13, 12},
				 {29, 12}});
		Assert.assertEquals(false, twoByTwoResult.equals(alpha));
	}
	
	@Test
	public void twoByTwoResultNotEqualFirstPlaceWrong(){
		Matrix alpha = new Matrix(new int[][]
                {{1, 12},
				 {29, 26}});
		Assert.assertEquals(false, twoByTwoResult.equals(alpha));
	}
	
	//Plus
	@Test
	public void threeByTwoAdd(){
		Matrix omega = threeByTwo.plus(threeByTwo);
		Assert.assertEquals(true, omega.equals(threeByTwoAResult));
	}
	
	@Test
	public void threeByTwoAddFail(){
		Matrix omega = threeByTwo.plus(twoByThree);
		Assert.assertEquals(false, threeByTwoAResult.equals(omega));
	}
	
	@Test (expected=NullPointerException.class)
	public void threeByTwoAddFailNullPointer(){
		Matrix omega = threeByTwo.plus(twoByThree);
		Assert.assertEquals(false, omega.equals(threeByTwoAResult));
	}
	
	public void threeByTwoAddNegative(){
		Matrix bravo = new Matrix(new int[][]
                {{-1, -2, -3},
				 {-2, -5, -6}});
		Matrix delta = new Matrix(new int[][]
                {{0, 0, 0},
				 {0, 0, 0}});
		Matrix omega = threeByTwo.plus(bravo);
		Assert.assertEquals(true, omega.equals(delta));
	}
	
}
