package assignment01;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class MatrixJUnitTester {

	Matrix twoByThree, twoByThreeIdentical, twoByThreeOther, threeByTwo, twoByFour, wrongDimensions, twoByTwoProduct, threeByFourProduct, twoByThreeSum, twoByThreeDoubled;
	String twoByTwoString, threeByFourString;
	
	/*Initialize some matrices we can play with for every test!*/
	@Before
	public void setup() {
		twoByThree = new Matrix(new int[][]
                {{1, 2, 3},
				 {2, 5, 6}});
		twoByThreeIdentical = new Matrix(new int[][]
                {{1, 2, 3},
				 {2, 5, 6}});
		twoByThreeOther = new Matrix(new int[][]
                {{2, 3, 5},
				 {1, 8, 9}});
		threeByTwo = new Matrix(new int[][]
                {{4, 5},
				 {3, 2},
				 {1, 1}});
		twoByFour = new Matrix(new int[][]
				{{1, 2, 3, 4},
				 {5, 6, 7, 8}});
		// like twoByThree with wrong dimensions only
		wrongDimensions = new Matrix(new int[][]
				{{1, 2},
				 {3, 2},
				 {5, 6}});
		// this is the known correct result of multiplying twoByThree by threeByTwo
		twoByTwoProduct = new Matrix(new int[][]
				{{13, 12},
				 {29, 26}});
		// this is the known correct result of multiplying threeByTwo by twoByFour
		threeByFourProduct = new Matrix(new int[][]
				{{29, 38, 47, 56},
			 	 {13, 18, 23, 28},
			 	 { 6,  8, 10, 12}});
		// this is the known correct result of adding twoByThree with twoByThreeOther
		twoByThreeSum = new Matrix(new int[][]
                {{3,  5,  8},
				 {3, 13, 15}});
		// this is the known correct result of adding twoByThree with twoByThree
		twoByThreeDoubled = new Matrix(new int[][]
                {{2, 4,  6},
				 {4, 10, 12}});
		// this is the known correct string representation of twoByTwoResult
		twoByTwoString = "13 12 \n" + "29 26 \n";
		// this is the known correct string representation of threeByFourResult
		threeByFourString = "29 38 47 56 \n" + "13 18 23 28 \n" + "6 8 10 12 \n";
	}
	
	@Test
	public void equalsWithOtherType() {
		Assert.assertFalse(twoByThree.equals(new Object()));
	}
	
	@Test
	public void equalsWithSelf() {
		Assert.assertTrue(twoByThree.equals(twoByThree));
	}
	
	@Test
	public void equalsWithIdentical() {
		Assert.assertTrue(twoByThree.equals(twoByThreeIdentical));
	}
	
	@Test
	public void notEquals() {
		Assert.assertFalse(threeByTwo.equals(twoByThree));
	}
	
	@Test
	public void equalsWithWrongDimensionsOnly() {
		Assert.assertFalse(twoByThree.equals(wrongDimensions));
	}
	
	@Test
	public void twoByTwoToString() {
		Assert.assertEquals(twoByTwoString, twoByTwoProduct.toString());
	}
	
	@Test
	public void threeByFourToString() {
		Assert.assertEquals(threeByFourString, threeByFourProduct.toString());
	}	
	
	@Test
	public void timesWithBalancedDimensions() {
		Matrix matrixProduct = twoByThree.times(threeByTwo);
		Assert.assertTrue(twoByTwoProduct.equals(matrixProduct));
	}
	
	@Test
	public void timesWithUnbalancedDimensions() {
		Matrix matrixProduct = threeByTwo.times(twoByFour);
		Assert.assertEquals(threeByFourProduct, matrixProduct);
	}
	
	@Test
	public void timesWithIllegalDimensions() {
		Matrix matrixProduct = twoByThree.times(twoByFour);
		Assert.assertNull(matrixProduct);
	}
	
	@Test
	public void plusWithLegalDimensions() {
		Matrix matrixSum = twoByThree.plus(twoByThreeOther);
		Assert.assertEquals(matrixSum, twoByThreeSum);
	}
	
	@Test
	public void plusWithIllegalDimensions() {
		Matrix matrixSum = twoByThree.plus(threeByTwo);
		Assert.assertNull(matrixSum);
	}
	
	@Test
	public void plusWithSelf() {
		Matrix matrixSum = twoByThree.plus(twoByThree);
		Assert.assertEquals(matrixSum, twoByThreeDoubled);
	}
}
