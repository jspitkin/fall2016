/*
 * Here is a starting point for your Matrix tester. You will have to fill in the rest of "main" with
 * more code to sufficiently test your Matrix class. We will be using our own MatrixTester for grading. 
*/
// Jiwon Nam
package assignment01;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class MatrixJUnitTester {

	Matrix oneByTwo, twoByTwo, twoByOne, threeByTwo, twoByThree, threeByTwoProductTwoByThree, 
	twoByThreeProductOneByTwo, twoByThreePlusTwoByThree, twoByThreeProductThreeByTwo, twoByOnePlusTwoByOne;
	/*Initialize some matrices we can play with for every test!*/
	
	@Before
	public void setup() {
		oneByTwo = new Matrix(new int[][]
				{{-1},
				 {6}});
		twoByTwo = new Matrix(new int[][]
				{{2, 8},
				{-4, 13}});
		twoByOne = new Matrix(new int[][]
				{{11, -3}
				});
		threeByTwo = new Matrix(new int[][]
                {{1, 2, 3},
				 {2, 5, 6}});
		twoByThree = new Matrix(new int[][]
                {{4, 5},
				 {3, 2},
				 {1, 1}});
		
		
		// this is the known correct result of multiplying M1 by M2
		twoByThreeProductOneByTwo = new Matrix(new int[][]
				{{26},
				 {9},
				 {5}});
		threeByTwoProductTwoByThree = new Matrix(new int[][]
                                    {{13, 12},
									 {29, 26}});
		
		twoByThreeProductThreeByTwo = new Matrix(new int[][]
									{{14, 33, 42},
									 {7, 16, 21},
									 {3, 7, 9}});
		
		twoByThreePlusTwoByThree = new Matrix(new int[][]
				{{8, 10},
				 {6, 4},
				 {2, 2}});
		twoByOnePlusTwoByOne = new Matrix(new int[][]
				{{22, -6}
				});
		
	}
	
	
	@Test
	public void timesWithBalancedDimensions() 
	{
		Matrix twoByThreeOneByTwoProduct = twoByThree.times(oneByTwo);
		Assert.assertTrue(twoByThreeProductOneByTwo.equals(twoByThreeOneByTwoProduct));
		
		Matrix matrixProduct = threeByTwo.times(twoByThree);
		Assert.assertTrue(threeByTwoProductTwoByThree.equals(matrixProduct));
		
		Matrix threeByThreeProduct = twoByThree.times(threeByTwo);
		Assert.assertTrue(twoByThreeProductThreeByTwo.equals(threeByThreeProduct));

		
	}
	
	@Test
	public void timesWithUnbalancedDimensions()
	{
		//TODO: More tests like these!
		Matrix matrixProduct = twoByThree.times(twoByThree);
		Assert.assertEquals(null, matrixProduct);
		
		Matrix matrixProductTwo = oneByTwo.times(twoByThree);
		Assert.assertEquals(null, matrixProductTwo);
		
		Matrix matrixProductThree = twoByTwo.times(twoByOne);
		Assert.assertEquals(null, matrixProductThree);
	}
	
	@Test 
	public void plusWithBalancedDimensions()
	{
		Matrix matrixPlus = twoByThree.plus(twoByThree);
		Assert.assertEquals(twoByThreePlusTwoByThree, matrixPlus);	
		
		Matrix matrixPlusTwo = twoByOne.plus(twoByOne);
		Assert.assertEquals(twoByOnePlusTwoByOne, matrixPlusTwo);	

	}
	
	@Test
	public void plusWithUnbalancedDimensions()
	{
		Matrix matrixPlus = threeByTwo.plus(twoByThree);
		Assert.assertEquals(null, matrixPlus);
		
		Matrix matrixPlusTwo = twoByOne.plus(oneByTwo);
		Assert.assertEquals(null, matrixPlusTwo);

	}
	@Test
	public void oneByTwoToString() {
		String resultString = "-1 \n6 \n";
		Assert.assertEquals(resultString, oneByTwo.toString());
	}
	@Test
	public void TwoByTwoToString() {
		String resultString = "2 8 \n-4 13 \n";
		Assert.assertEquals(resultString, twoByTwo.toString());
	}
	@Test
	public void twoByOneToString() {
		String resultString = "11 -3 \n";
		Assert.assertEquals(resultString, twoByOne.toString());
	}
	@Test
	public void threeByTwoProductTwoByThreeToString() {
		String resultString = "13 12 \n29 26 \n";
		Assert.assertEquals(resultString, threeByTwoProductTwoByThree.toString());
	}
	@Test
	public void twoByThreeProductThreeByTwoToString() {
		String resultString = "14 33 42 \n7 16 21 \n3 7 9 \n";
		Assert.assertEquals(resultString, twoByThreeProductThreeByTwo.toString());
	}
	@Test
	public void twoByThreePlusTwoByThreeToString() {
		String resultString = "8 10 \n6 4 \n2 2 \n";
		Assert.assertEquals(resultString, twoByThreePlusTwoByThree.toString());
	}
	@Test
	public void threeByTwoToString() {
		String resultString = "1 2 3 \n2 5 6 \n";
		Assert.assertEquals(resultString, threeByTwo.toString());
	}
	@Test
	public void twoByThreeToString() {
		String resultString = "4 5 \n3 2 \n1 1 \n";
		Assert.assertEquals(resultString, twoByThree.toString());
	}
}
