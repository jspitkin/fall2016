/*
 * Here is a starting point for your Matrix tester. You will have to fill in the rest of "main" with
 * more code to sufficiently test your Matrix class. We will be using our own MatrixTester for grading. 
*/
package assignment01;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class MatrixJUnitTester {

	Matrix threeByTwo, twoByThree, twoByTwoResult;
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
		// this is the known correct result of multiplying M1 by M2
		twoByTwoResult = new Matrix(new int[][]
                                    {{13, 12},
									 {29, 26}});
	}
	
	@Test
	public void testEqualsTrue1() {
		Matrix m = new Matrix(new int[][]
                {{1, 2, 3},
				 {2, 5, 6}});
		Assert.assertTrue(m.equals(threeByTwo));
	}
	
	@Test
	public void testEqualsTrue2() {
		Matrix m = new Matrix(new int[][]
                {{4, 5},
				 {3, 2},
				 {1, 1}});
		Assert.assertTrue(m.equals(twoByThree));
	}
	
	@Test
	public void testEqualsFalse1() {
		Matrix m = new Matrix(new int[][]
                {{1, 2, 3},
				 {2, 5, 6}});
		Assert.assertFalse(m.equals(twoByThree));
	}
	
	@Test
	public void testEqualsFalse2() {
		Matrix m = new Matrix(new int[][]
                {{4, 5},
				 {3, 2},
				 {1, 1}});
		Assert.assertFalse(m.equals(threeByTwo));
	}
	
	@Test
	public void testEqualsFalse3() {
		Matrix m = new Matrix(new int[][]
                {{4, 5},
				 {3, 2},
				 {1, 1}});
		Assert.assertFalse(m.equals(new int[3][2]));
	}
	
	@Test
	public void testToString1() {
		Matrix m = new Matrix(new int[][]
                {{1, 2, 3},
				 {2, 5, 6}});
		String s = "1 2 3 \n2 5 6 \n";
		Assert.assertEquals(s, m.toString());
	}
	
	@Test
	public void testToString2() {
		Matrix m = new Matrix(new int[][]
                {{4, 5},
				 {3, 2},
				 {1, 1}});
		String s = "4 5 \n3 2 \n1 1 \n";
		Assert.assertEquals(s, m.toString());
	}
	
	@Test
	public void timesWithBalancedDimensions() {
		Matrix matrixProduct = threeByTwo.times(twoByThree);
		Assert.assertTrue(twoByTwoResult.equals(matrixProduct));
	}
	
	@Test
	public void timesWithBalancedDimesnions2() {
		Matrix m1 = new Matrix(new int[][]
				{{1, 2, 3},
				 {4, 5, 6}});
		Matrix m2 = new Matrix(new int[][]
				{{7, 8},
				 {9, 10},
				 {11, 12}});
		Matrix product = new Matrix(new int[][]
				{{58, 64},
				 {139, 154}});
		Matrix testProduct = m1.times(m2);
		Assert.assertTrue(testProduct.equals(product));
	}
	
	@Test
	public void timesWithUnbalancedDimensions() {
		Matrix m1 = new Matrix(new int[][]
				{{1, 2},
				 {4, 5}});
		Matrix m2 = new Matrix(new int[][]
				{{7, 8},
				 {9, 10},
				 {11, 12}});
		Assert.assertEquals(null, m1.times(m2));
	}
	
	@Test
	public void twoByTwoToString() {
		String resultString = "13 12 \n29 26 \n";
		Assert.assertEquals(resultString, twoByTwoResult.toString());
	}
	
	@Test
	public void plusWithUnbalancedDimensions() {
		Assert.assertEquals(null, threeByTwo.plus(twoByThree));
	}
	
	@Test
	public void plusWithBalancedDimesions1() {
		Matrix m1 = new Matrix(new int[][]
				{{1, 2},
				 {4, 5}});
		Matrix m2 = new Matrix(new int[][]
				{{7, 8},
				 {9, 10}});
		Matrix result = m1.plus(m2);
		Matrix expected = new Matrix(new int[][]
				{{8, 10},
			 	 {13, 15}});
		Assert.assertTrue(result.equals(expected));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
