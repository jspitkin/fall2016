package assignment01;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Matrix Class Tester
 *
 * @author Nathan Milot, last modified Aug 29, 2016
 */
public class MatrixJUnitTester {

	Matrix twoByThree, threeByTwo, twoByTwoResult, nullMatrix, zeroSizeMatrix;
	
	
	@Before
	public void setup() {
		twoByThree = new Matrix(new int[][]{{1, 2, 3}, {2, 5, 6}});
		threeByTwo = new Matrix(new int[][] {{4, 5}, {3, 2}, {1, 1}});
		// this is the known correct result of multiplying M1 by M2
		twoByTwoResult = new Matrix(new int[][]{{13, 12}, {29, 26}});
		zeroSizeMatrix = new Matrix(new int[0][0]);
	}
	
	//equals tests
	//equals tests
	@Test
	public void twoByThreeEqualsBalancedDimensions(){
		Matrix test = new Matrix(new int[][]{{4, 5}, {3, 2}, {1, 1}});
		Assert.assertTrue(threeByTwo.equals(test));
	}
	

	@Test
	public void twoByThreeDoesNotEqualBalancedDimensions(){
		Matrix test = new Matrix(new int[][]{{64, 7}, {6, 82}, {4, 12}});
		Assert.assertFalse(threeByTwo.equals(test));
	}
	
	
	@Test
	public void twoByThreeDoesNotEqualUnBalancedDimensions(){
		Assert.assertFalse(threeByTwo.equals(twoByThree));
	}
	
	
	@Test (expected = NullPointerException.class)
	public void nullMatrixExceptionEquals(){
		nullMatrix.equals(threeByTwo);
	}
	
	//toString tests
	
	//toString tests
	@Test
	public void twoByTwoToString(){
		Assert.assertEquals("13 12 \n29 26 \n", twoByTwoResult.toString());
	}
	
	
	@Test (expected = NullPointerException.class)
	public void nullMatrixExceptionToString(){
		nullMatrix.toString();
	}
	
	
	@Test
	public void zeroSizeMatrixToString(){
		Assert.assertEquals("", zeroSizeMatrix.toString());
	}
	
	
	@Test
	public void toStringAfterTimes(){
		Matrix result = twoByThree.times(threeByTwo);
		Assert.assertEquals(twoByTwoResult.toString(), result.toString());
	}
	
	//times tests
	@Test
	public void timesWithBalancedDimensions() {
		Matrix matrixProduct = twoByThree.times(threeByTwo);
		Assert.assertTrue(twoByTwoResult.equals(matrixProduct));
	}
	
	@Test
	public void timesWithUnbalancedDimensions(){
		Matrix matrixProduct = threeByTwo.times(threeByTwo);
		Assert.assertEquals(null, matrixProduct);
	}
	
	
	@Test
	public void timesZeroSizeMatrix(){
		Assert.assertEquals(zeroSizeMatrix, zeroSizeMatrix.times(zeroSizeMatrix));
	}
	
	
	@Test (expected = NullPointerException.class)
	public void nullMatrixExceptionTimes(){
		threeByTwo.times(nullMatrix);
	}
	
	//plus tests
	
	//plus tests
	@Test
	public void plusWithBalancedDimensions(){
		Matrix result = new Matrix(new int[][]{{2, 4, 6},{4, 10, 12}});
		Assert.assertEquals(result, twoByThree.plus(twoByThree));
	}
	
	
	@Test
	public void plusWithUnbalancedDimensions(){
		Assert.assertEquals(null, twoByThree.plus(threeByTwo));
	}
	
	
	@Test (expected = NullPointerException.class)
	public void nullMatrixExceptionPlus(){
		twoByThree.plus(nullMatrix);
	}

	
	@Test
	public void plusZeroMatrix(){
		int[][] zeroArr = new int[3][2];
		Matrix zeroMatrix = new Matrix(zeroArr);
		Assert.assertEquals(threeByTwo, threeByTwo.plus(zeroMatrix));
	}
}