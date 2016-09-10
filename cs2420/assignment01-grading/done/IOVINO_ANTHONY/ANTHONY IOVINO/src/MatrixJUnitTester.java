package assignment01;
/**
 * Anthony Iovino
 */

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MatrixJUnitTester {

	Matrix threeByTwo, twoByThree, twoByTwoResult, twoByTwo, oneByTwo, twoByOne, oneByOne1, oneByOne2, twoByOneSum, twoByTwoSum, threeByTwoSum, oneByOneSum;
	/*Initialize some matrices we can play with for every test!*/
	
	@Before
	public void setup() {
		threeByTwo = new Matrix(new int[][]
                 {{1, 2, 3},
				  {2, 5, 6}});
		
		threeByTwoSum = new Matrix(new int[][] {{2,4,6},{4,10,12}});
		
		twoByThree = new Matrix(new int[][]
                {{4, 5},
				 {3, 2},
				 {1, 1}});
		
		twoByTwo = new Matrix(new int[][] {{2,3},
			                               {5,6}});
		
		oneByTwo = new Matrix(new int[][] {{2},
										   {1}});

		oneByOne1 = new Matrix(new int[][]{{4}});
		
		oneByOne2 = new Matrix(new int[][]{{7}});
		
		oneByOneSum = new Matrix(new int[][]{{11}});
		
		twoByOne = new Matrix(new int[][] {{2,3}});

		twoByOneSum = new Matrix(new int[][]{{4,6}}); // what results from adding the above twoByOne matrix to itself.  
		
		twoByTwoSum = new Matrix(new int[][]{{4,6},//sum of twoByTwo and itself 
											 {2,3}});
		
		twoByTwoResult = new Matrix(new int[][] // this is the known correct result of multiplying M1 by M2
                                    {{13, 12},
									 {29, 26}});
	}
	
//equals() Tests: 
	@Test
	public void equalstest1() {
		Assert.assertTrue(oneByOne1.equals(oneByOne1));			
	}
	@Test
	public void equalstest2() {
		Assert.assertFalse(oneByOne1.equals(oneByTwo));		
	}
	@Test
	public void equalstest3() {
		Assert.assertTrue(threeByTwo.equals(threeByTwo));			
	}
	

//plus() Tests:(add two more to each type)  
	@Test
	public void plusWithBalancedDimensions1() {
		Matrix matrixSum = twoByOne.plus(twoByOne);
		Assert.assertTrue(twoByOneSum.equals(matrixSum));
	}
	@Test
	public void plusWithBalancedDimensions2() {
		Matrix matrixSum = threeByTwo.plus(threeByTwo);
		Assert.assertTrue(threeByTwoSum.equals(matrixSum));
	}
	@Test
	public void plusWithBalancedDimensinos3(){
		Matrix matrixSum = oneByOne1.plus(oneByOne2);
		Assert.assertTrue(oneByOneSum.equals(matrixSum));
	}
	@Test
	public void plusWithUnbalancedDimensions1(){
		Matrix matrixSum = twoByOne.plus(oneByOne1);
		Assert.assertEquals(matrixSum, null);
	}	
	@Test
	public void plusWithUnbalancedDimensions2(){
		Matrix matrixSum = threeByTwo.plus(oneByOne1);
		Assert.assertEquals(matrixSum, null);
	}
	
		
//times() Tests:(add one more to each type)  	
	@Test
	public void timesWithBalancedDimensions() {
		Matrix matrixProduct = threeByTwo.times(twoByThree);
		Assert.assertTrue(twoByTwoResult.equals(matrixProduct));
	}
	@Test
	public void timesWithBalancedDimensions2() {
		Matrix matrixProduct = oneByTwo.times(twoByOne);  // 
		Assert.assertTrue(twoByTwoSum.equals(matrixProduct));
	}
	@Test
	public void timesWithBalancedDimensions3() {
		Matrix matrixProduct = oneByTwo.times(twoByOne);  // 
		Assert.assertTrue(twoByTwoSum.equals(matrixProduct));
	}
	@Test
	public void timesWithUnbalancedDimensions1(){
		Matrix matrixProduct = threeByTwo.times(threeByTwo);
		Assert.assertEquals(matrixProduct, null);	
	}
	@Test
	public void timesWithUnbalancedDimensions2(){
		Matrix matrixProduct = oneByTwo.times(threeByTwo);
		Assert.assertEquals(matrixProduct, null);
	}
	
	
//toString() Tests: 
	@Test
	public void twoByTwoToString() {
		String resultString = "13 12 "+"\n"+"29 26 ";
		Assert.assertEquals(resultString, twoByTwoResult.toString());
		
	}
	@Test
	public void threeByTwoToString() {
		String resultString = "1 2 3 "+"\n"+"2 5 6 ";
		Assert.assertEquals(resultString, threeByTwo.toString());
	}	
	@Test
	public void oneByTwoToString() {
		String resultString = "2 "+"\n"+"1 ";
		Assert.assertEquals(resultString, oneByTwo.toString());
	}
	@Test
	public void twoByOneToString() {
		String resultString = "2 3 ";
		Assert.assertEquals(resultString, twoByOne.toString());
	}	
	@Test
	public void oneByOne1(){
		String resultString = "4 ";
		Assert.assertEquals(resultString, oneByOne1.toString());
	}
}
