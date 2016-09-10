/*
 * Here is a starting point for your Matrix tester. You will have to fill in the rest of "main" with
 * more code to sufficiently test your Matrix class. We will be using our own MatrixTester for grading. 
*/
package assignment01;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class MatrixJUnitTester {

	Matrix threeByTwo, twoByThree, twoByTwoResult, sameMatrix1,
			sameMatrix2, differentMatrix, onesMatrix,twosMatrix,additionAnswerMatrix,
			threeByTwoAdd, threeByTwoAnswer;
	/*Initialize some matrices we can play with for every test!*/
	
	@Before
	public void setup() {
		threeByTwo = new Matrix(new int[][]
                {{1, 2, 3},
				 {2, 5, 6}});
		threeByTwoAdd = new Matrix(new int[][]
				{{-1, -2, -3},
			 	 {-2, -5, -5}});
		
		threeByTwoAnswer = new Matrix(new int[][]
				{{0, 0, 0},
			 	 {0, 0, 1}});
				
		twoByThree = new Matrix(new int[][]
                {{4, 5},
				 {3, 2},
				 {1, 1}});
		// this is the known correct result of multiplying M1 by M2
		twoByTwoResult = new Matrix(new int[][]
                                    {{13, 12},
									 {29, 26}});
		sameMatrix1 = new Matrix(new int[][]
									{{1, 2},
									 {3, 4}});
		sameMatrix2 = new Matrix(new int[][]
									{{1, 2},
									 {3, 4}});
		onesMatrix = new Matrix(new int[][]
									{{1, 1},
									 {1, 1}});
		twosMatrix = new Matrix(new int[][]
									{{2, 2},
				 					 {2, 2}});
		additionAnswerMatrix = new Matrix(new int[][]
									{{2, 3},
									 {4, 5}});
		differentMatrix = new Matrix(new int[][]
									{{4, 3},
									 {2, 1}});
	}
	
	@Test
	public void timesWithBalancedDimensions() {
		Matrix matrixProduct = threeByTwo.times(twoByThree);
		System.out.println(matrixProduct.toString());
		Assert.assertTrue(twoByTwoResult.equals(matrixProduct));
	}
	@Test
	public void timesWithBalancedDimensionsOnes() {
		Matrix matrixProduct = onesMatrix.times(onesMatrix);
		Assert.assertTrue(twosMatrix.equals(matrixProduct));
	}
	
	@Test
	public void timesWithUnbalancedDimensions(){
		Matrix matrixProduct = onesMatrix.times(twoByThree);
		Assert.assertTrue(matrixProduct == null);
	}
	
	@Test
	public void twoByTwoToString() {
		//String resultString = "what should it equal?";
		String resultString = "13 12 \n29 26 \n";
		Assert.assertEquals(resultString, twoByTwoResult.toString());
	}
	@Test
	public void matrixEqual(){
		Assert.assertTrue(sameMatrix1.equals(sameMatrix2));
	}
	@Test
	public void matrixNotEqual(){
		Assert.assertFalse(sameMatrix1.equals(differentMatrix));
	}
	@Test
	public void matrixAddition(){
		Matrix matrixProduct =sameMatrix2.plus(onesMatrix);
		Assert.assertTrue(additionAnswerMatrix.equals(matrixProduct));
	}
	@Test
	public void matrixAddTwoByThree(){
		Matrix matrixProduct = threeByTwo.plus(threeByTwoAdd);
		Assert.assertTrue(matrixProduct.equals(threeByTwoAnswer));
	}
	@Test
	public void matrixAddBadDimensions(){
		Matrix matrixProduct = threeByTwo.plus(twosMatrix);
		Assert.assertTrue(matrixProduct == null);
	}
	
}
