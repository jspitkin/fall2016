/*
 * Here is a starting point for your Matrix tester. You will have to fill in the rest of "main" with
 * more code to sufficiently test your Matrix class. We will be using our own MatrixTester for grading. 
 */
package assignment01;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author zhangtuoming
 * @class CS-2420
 */

public class MatrixJUnitTester {

	Matrix threeByTwo, twoByThree, twoByTwoResult, M1, M2, M3, M4, M5, M6, M7,
			M8, M9, M10, referenceMultiply, referenceMultiply1,
			referenceMultiply2, referenceMultiply3, referenceMultiply4,
			referenceadd, referenceadd1, referenceadd2;

	/* Initialize some matrices we can play with for every test! */

	@Before
	public void setup() {
		threeByTwo = new Matrix(new int[][] { { 1, 2, 3 }, { 2, 5, 6 } });
		twoByThree = new Matrix(new int[][] { { 4, 5 }, { 3, 2 }, { 1, 1 } });
		// this is the known correct result of multiplying M1 by M2
		twoByTwoResult = new Matrix(new int[][] { { 13, 12 }, { 29, 26 } });

		// Setting up more Matrixs to test
		M1 = new Matrix(new int[][] { { 1, 2, 3 }, { 2, 5, 6 } });

		M2 = new Matrix(new int[][] { { 4, 5 }, { 3, 2 }, { 1, 1 } });

		M3 = new Matrix(new int[][] { { 4 } });

		M4 = new Matrix(new int[][] { { 1, 2, 3, 4 }, { 2, 3, 4, 5 },
				{ 3, 4, 5, 6 }, { 4, 5, 6, 7 } });

		M5 = new Matrix(new int[][] { { 2, 3, 4, 5 }, { 5, 6, 7, 8 },
				{ 3, 4, 6, 8 }, { 1, 2, 4, 6 } });

		M6 = new Matrix(new int[][] { { 1 }, { 2 }, { 3 }, { 4 } });

		M7 = new Matrix(new int[][] { { 2 }, { 3 }, { 4 }, { 5 } });

		M8 = new Matrix(new int[][] { { 1, 2, 3, 4 } });

		M9 = new Matrix(new int[][] { { 1, 2, 3 }, { 3, 4, 5 } });
		M10 = new Matrix(new int[][] { { 1, 3 }, { 2, 4 }, { 3, 5 } });

		// this is the known result of multiplying M1 by M2
		referenceMultiply = new Matrix(new int[][] { { 13, 12 }, { 29, 26 } });
		referenceMultiply1 = new Matrix(new int[][] { { 25, 35, 52, 69 },
				{ 36, 50, 73, 96 }, { 47, 65, 94, 123 }, { 58, 80, 115, 150 } });
		referenceMultiply2 = null;// new Matrix(new int[][] { null });//
									// 6*7
		referenceMultiply3 = new Matrix(new int[][] { { 1, 2, 3, 4 },
				{ 2, 4, 6, 8 }, { 3, 6, 9, 12 }, { 4, 8, 12, 16 } });// 6*8
		referenceMultiply4 = new Matrix(new int[][] { { 4 }, { 8 }, { 12 },
				{ 16 } });
		referenceadd = new Matrix(new int[][] { { 3, 5, 7, 9 },
				{ 7, 9, 11, 13 }, { 6, 8, 11, 14 }, { 5, 7, 10, 13 } });
		referenceadd1 = new Matrix(new int[][] { { 3 }, { 5 }, { 7 }, { 9 } });// 6+7
		referenceadd2 = null;// 6+8
	}

	@Test
	public void equalTester() {
		// Test the same Matrixs
		Assert.assertTrue(M1.equals(M1));
		Assert.assertTrue(M2.equals(M2));

		// Test the different Matrixs
		Assert.assertFalse(M1.equals(M2));
		Assert.assertFalse(M3.equals(M4));
		Assert.assertFalse(M5.equals(M6));
		Assert.assertFalse(M7.equals(M8));
		Assert.assertFalse(M9.equals(M10));
	}

	@Test
	public void timesWithBalancedDimensions() {
		Matrix matrixProduct = threeByTwo.times(twoByThree);
		Assert.assertTrue(twoByTwoResult.equals(matrixProduct));

		Matrix matrixProduct1 = M1.times(M2);
		Assert.assertTrue(matrixProduct1.equals(referenceMultiply));

		Matrix matrixProduct2 = M4.times(M5);
		Assert.assertTrue(matrixProduct2.equals(referenceMultiply1));		

	}

	@Test
	public void timesWithUnbalancedDimensions() {
		// TODO: More tests like these!
		Matrix matrixProduct4 = M6.times(M3);
		Assert.assertTrue(matrixProduct4.equals(referenceMultiply4));
		
		Matrix matrixProduct3 = M6.times(M8);
		Assert.assertTrue(matrixProduct3.equals(referenceMultiply3));
	}

	@Test
	public void twoByTwoToString() {

		Assert.assertEquals(referenceMultiply.toString(),
				twoByTwoResult.toString());
		// Test the null Matrixs are equal
		Assert.assertEquals(referenceMultiply2, referenceadd2);

	}

	@Test
	public void plusTester() {
		
		Matrix matrixSums1 = M4.plus(M5);
		Assert.assertTrue(matrixSums1.equals(referenceadd));
		
		Matrix matrixSums2 = M6.plus(M7);
		Assert.assertTrue(matrixSums2.equals(referenceadd1));
		
		//test the unbalanced matrixs sum
		Matrix matrixSums3 = M6.plus(M8);
		Assert.assertEquals(matrixSums3,referenceadd2);
		
	}
}
