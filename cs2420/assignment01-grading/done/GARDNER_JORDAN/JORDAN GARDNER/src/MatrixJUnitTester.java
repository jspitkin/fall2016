/**
 * Jordan Gardner U0566259 Tests
 */
package assignment01;

import static org.junit.Assert.*;

import org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class MatrixJUnitTester {
	//Initialization of matrices
	Matrix threeByTwo, twoByThree, twoByTwoResult, twoByTwo, fiveByTwo, threeByTwoResult, twoByTwoSumResult, twoByThreeSumResult, oneByOne, fiveByThreeResult;
	
	@Before
	public void setup() {
		twoByThree = new Matrix(new int[][]
                {{1, 2, 3},
				 {2, 5, 6}});
		threeByTwo = new Matrix(new int[][]
                {{4, 5},
				 {3, 2},
				 {1, 1}});
		twoByTwoResult = new Matrix(new int[][]
                                    {{13, 12},
									 {29, 26}});
		twoByTwo = new Matrix(new int[][]
                {{1, 2},
				 {2, 5}});
		fiveByTwo = new Matrix(new int[][]
                {{4, 5},
				 {3, 2},
				 {1, 1},
				 {5, 1},
				 {3, 7}});
		threeByTwoResult = new Matrix(new int[][]
				{{14, 33},
				 {7, 16},
				 {3, 7}});
		twoByTwoSumResult = new Matrix(new int[][]
                {{2, 4},
				 {4, 10}});
		twoByThreeSumResult = new Matrix(new int[][]
                {{2, 4, 6},
				 {4, 10, 12}});
		oneByOne = new Matrix(new int[][]
				{{2}});
		fiveByThreeResult = new Matrix(new int[][]
                {{14, 33, 42},
				 {7, 16, 21},
				 {3, 7, 9},
				 {7, 15, 21},
				 {17, 41, 51}});
		
		
	}
	
	@Test
	public void timesWithBalancedDimensions() {
		Matrix matrixProduct = twoByThree.times(threeByTwo);
		Assert.assertTrue(twoByTwoResult.equals(matrixProduct));
		Matrix matrixProduct2 = threeByTwo.times(twoByTwo);
		Assert.assertTrue(threeByTwoResult.equals(matrixProduct2));
		Matrix matrixProduct3 = fiveByTwo.times(twoByThree);
		Assert.assertTrue(fiveByThreeResult.equals(matrixProduct3));
		
	}
	
	@Test
	public void timesWithUnbalancedDimensions(){
		Matrix matrixProduct = oneByOne.times(twoByThree);
		Assert.assertEquals(null,matrixProduct);
		Matrix matrixProduct2 = twoByThree.times(twoByThree);
		Assert.assertEquals(null,matrixProduct2);
	}
	
	@Test
	public void twoByTwoToString() {
		String resultString = "13 12 "+"\n"+"29 26 ";
		Assert.assertEquals(resultString, twoByTwoResult.toString());
		String resultString2 = "4 5 "+"\n"+"3 2 "+"\n"+"1 1 ";
		Assert.assertEquals(resultString2, threeByTwo.toString());
		String resultString3 = "2 4 6 "+"\n"+"4 10 12 ";
		Assert.assertEquals(resultString3, twoByThreeSumResult.toString());
	}
	
	@Test
	public void plusWithBalancedDimensions(){
		Matrix matrixProduct = twoByThree.plus(twoByThree);
		Assert.assertTrue(twoByThreeSumResult.equals(matrixProduct));
		Matrix matrixProduct2 = twoByTwo.plus(twoByTwo);
		Assert.assertTrue(twoByTwoSumResult.equals(matrixProduct2));
	}
	
	@Test
	public void plusWithUnbalancedDimensions(){
		Matrix matrixProduct = twoByTwo.plus(threeByTwo);
		Assert.assertEquals(null,matrixProduct);
		Matrix matrixProduct2 = twoByTwo.plus(fiveByTwo);
		Assert.assertEquals(null,matrixProduct2);
	}
}
