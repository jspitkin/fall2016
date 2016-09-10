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

	Matrix threeByTwo, twoByThree, threeByThree, twoByTwoResult, threeByTwoResult;
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
		threeByThree = new Matrix(new int[][]
				{{46, 19, -92},
				 {-24, -49, -89},
				 {-42, -45, 22}});
				
		// this is the known correct result of multiplying M1 by M2
		twoByTwoResult = new Matrix(new int[][]
                                    {{13, 12},
									 {29, 26}});
		threeByTwoResult = new Matrix(new int[][]
									{{-128, -214, -204},
									 {-280, -477, -497}});
	}
	
	@Test
	public void timesWithBalancedDimensions() {
		Matrix matrixProduct = threeByTwo.times(twoByThree);
		Assert.assertTrue(twoByTwoResult.equals(matrixProduct));
	}
	
	@Test
	public void timesWithBalancedDimensionsMatriciesReversed(){
		Matrix matrixProduct = twoByThree.times(threeByTwo);
		assertEquals(new Matrix(new int[][]{{14,33,42},{7,16,21},{3,7,9}}),matrixProduct);
	}
	
	@Test
	public void timesWithUnbalancedDimensions(){
		Matrix matrixProduct = threeByTwo.times(twoByTwoResult);
		Assert.assertEquals(null,matrixProduct);
	}
	
	@Test
	public void timesResultHasUnbalancedDimensions(){
		Matrix matrixProduct = threeByTwo.times(threeByThree);
		Assert.assertTrue(threeByTwoResult.equals(matrixProduct));
	}
	
	@Test(expected = NullPointerException.class)
	public void timesWithNullData(){
		threeByTwo.times(new Matrix(null));
	}
	
	@Test
	public void timesWithSpecialIntegers(){
		Matrix special = new Matrix(new int[][]{{Integer.MAX_VALUE,Integer.MIN_VALUE}});
		assertEquals(null, twoByTwoResult.times(special));
	}
	
	@Test
	public void plusWithoutEqualDimensions(){
		Matrix matrixProduct = threeByTwo.plus(twoByThree);
		assertEquals(null,matrixProduct);
	}
	
	@Test
	public void plusWithEqualDimensions(){
		Matrix matrixResult = threeByTwo.plus(threeByTwoResult);
		assertEquals(new Matrix(new int[][]{{-127,-212,-201},{-278,-472,-491}}), matrixResult);
	}
	
	@Test(expected = NullPointerException.class)
	public void plusWithNullData(){
		threeByTwo.plus(new Matrix(null));
	}
	
	@Test
	public void plusWithSpecialIntegers(){
		Matrix matrixResult = new Matrix(new int[][]{{Integer.MAX_VALUE,Integer.MIN_VALUE}}).plus(
				new Matrix(new int[][]{{Integer.MIN_VALUE,Integer.MAX_VALUE}}));
		assertEquals(new Matrix(new int[][] {{-1,-1}}), matrixResult);
	}
	
	@Test
	public void plusWithZeroMatrix(){
		Matrix zero = new Matrix(new int[][]{{0,0,0},{0,0,0}});
		assertEquals(threeByTwo,threeByTwo.plus(zero));
	}
	
	@Test
	public void twoByTwoToString() {
		String resultString = "13 12 \n29 26 \n";
		Assert.assertEquals(resultString, twoByTwoResult.toString());
	}

	@Test
	public void fourByOneToString(){
		String resultString = "34 \n-75 \n-88 \n-57 \n";
		Assert.assertEquals(resultString, new Matrix(new int[][]{{34},{-75},{-88},{-57}}).toString());
	}
	
	@Test
	public void threeByThreeToString(){
		String result = "46 19 -92 \n-24 -49 -89 \n-42 -45 22 \n";
		assertEquals(result,threeByThree.toString());
	}
	
	@Test(expected = NullPointerException.class)
	public void nullDataToString(){
		new Matrix(null).toString();
	}
	
	@Test
	public void specialIntMatrixToString(){
		String result = Integer.MAX_VALUE+" "+Integer.MIN_VALUE+" \n";
		assertEquals(result, new Matrix(new int[][]{{Integer.MAX_VALUE, Integer.MIN_VALUE}}).toString());
	}

	@Test
	public void equalsOnSameMatrcies(){
		assertTrue(threeByTwo.equals(threeByTwo));
	}
	
	@Test
	public void equalsOnDifferentMatrices(){
		assertFalse(threeByTwo.equals(twoByThree));
	}
	
	@Test
	public void equalsOnNotAMatrix(){
		assertFalse(threeByTwo.equals(new Object()));
	}
	
	@Test(expected = NullPointerException.class)
	public void equalsOnNullData(){
		threeByTwo.equals(new Matrix(null));
	}
	
	@Test
	public void equalsOnSpecialIntegers(){
		Matrix special = new Matrix(new int[][]{{Integer.MAX_VALUE,Integer.MIN_VALUE}});
		assertTrue(special.equals(special));
	}
	
}
