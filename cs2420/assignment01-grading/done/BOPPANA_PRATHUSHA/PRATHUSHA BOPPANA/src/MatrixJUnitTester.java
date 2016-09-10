/*
 * Here is a starting point for your Matrix tester. You will have to fill in the rest of "main" with
 * more code to sufficiently test your Matrix class. We will be using our own MatrixTester for grading. 
*/
package assignment01;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class MatrixJUnitTester {

	Matrix threeByTwo, twoByThree, threeByTwoOnes, twoByTwoIdentity, twoByTwoResult;
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
		threeByTwoOnes = new Matrix(new int[][]
				{{1, 1},
				 {1, 1},
				 {1, 1}});
		twoByTwoIdentity = new Matrix(new int[][]
				{{1, 0},
				 {0, 1}});
		// this is the known correct result of multiplying M1 by M2
		twoByTwoResult = new Matrix(new int[][]
                {{13, 12},
				 {29, 26}});
	}
	
	@Test
	public void equalsWithSameMatrix(){
		Assert.assertTrue(twoByThree.equals(twoByThree));
	}
	
	@Test
	public void equalsWithIdenticalMatricies(){
		Assert.assertTrue(threeByTwo.equals(new Matrix(new int[][]
                {{1, 2, 3},
				 {2, 5, 6}})));
	}
	
	@Test
	public void equalsWithDiffMatrix(){
		Assert.assertFalse(twoByThree.equals(threeByTwo));
	}
	
	@Test
	public void threeByTwoToString(){
		Assert.assertEquals("1 2 3 \n2 5 6 \n", threeByTwo.toString());
	}
	
	@Test
	public void threeByTwoOnesToString(){
		Assert.assertEquals("1 1 \n1 1 \n1 1 \n", threeByTwoOnes.toString());
	}
	
	@Test
	public void twoByTwoResultToString() {
		String resultString = "13 12 \n29 26 \n";
		Assert.assertEquals(resultString, twoByTwoResult.toString());
	}
	
	@Test
	public void plusWithSquareMatrix(){
		Assert.assertTrue(twoByTwoIdentity.plus(twoByTwoResult).equals(
				new Matrix(new int[][]{{14,12},{29,27}})));
	}
	
	@Test
	public void plusWithDiffMatricies(){
		Assert.assertNull(threeByTwo.plus(twoByThree));
	}
	
	@Test
	public void plusWithNonSquareMatricies(){
		Assert.assertTrue(twoByThree.plus(threeByTwoOnes).equals(
				new Matrix(new int[][]{{5, 6},{4, 3},{2, 2}})));
	}
	
	@Test
	public void timesWithBalancedDimensions() {
		Matrix matrixProduct = threeByTwo.times(twoByThree);
		Assert.assertTrue(twoByTwoResult.equals(matrixProduct));
	}
	
	@Test
	public void timesWithUnbalancedDimensions(){
		//TODO: More tests like these!
		Assert.assertNull(twoByTwoResult.times(twoByThree));
	}
	
	@Test
	public void timesWithIdentity(){
		Assert.assertTrue(twoByTwoResult.times(twoByTwoIdentity).equals(twoByTwoResult));
	}
}
