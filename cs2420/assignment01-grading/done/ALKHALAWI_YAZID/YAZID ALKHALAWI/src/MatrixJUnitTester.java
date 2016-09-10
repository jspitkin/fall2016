package assignment01;
/**
 * 
 * @author Yazid Alkhalawi
 *
 */
import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class MatrixJUnitTester {
	
	

	Matrix threeByTwo, twoByThree, twoByTwoResult, 
	twoByTwo, twoByTwoResultPlus,twoByThreeResultPlus,
	twoBythreeResult,twoByTwoResult1;
	
	//in Matrix it's row x column 
	/*Initialize some matrices we can play with for every test!*/
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
		
	}
	@Before
	public void setup() throws Exception {
		twoByThree = new Matrix(new int[][]
                {{1, 2, 3},
				 {2, 5, 6}});
		threeByTwo = new Matrix(new int[][]
                {{4, 5},
				 {3, 2},
				 {1, 1}});
		// this is the known correct result of multiplying M1 by M2
		twoByTwoResult = new Matrix(new int[][]
                                    {{13, 12},
									 {29, 26}});
		
		twoByTwo = new Matrix(new int[][]
				                          {{2,5},
			                               {4,4}});
		
		
		twoByTwoResultPlus = new Matrix(new int[][]
											{{4,10},
												{8,8}});
		
		
		twoByThreeResultPlus= new Matrix(new int[][]{{2,4,6},
														{4,10,12}});
		
		
		twoBythreeResult = new Matrix(new int[][]{{12,29,36},
													{12,28,36}});
		
		
		twoByTwoResult1= new Matrix(new int[][] {{24,30},
													{24,36}});
		
											
		
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void timesWithBalancedDimensions()  {
		Matrix matrixProduct = twoByThree.times(threeByTwo);
		Assert.assertTrue(twoByTwoResult.equals(matrixProduct));
	}
	@Test (expected=IndexOutOfBoundsException.class)
	public void timesWithBalancedDimensions1()
	{
		Matrix matrixProduct = twoByTwo.times(twoByThree);
		assertEquals(twoBythreeResult,matrixProduct);
	}
	@Test
	public void timesTwoByTwo()
	{
		Matrix matrixProduct = twoByTwo.times(twoByTwo);
		assertEquals(twoByTwoResult1,matrixProduct);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void timesWithUnbalancedDimensions(){
		
		Matrix matrixProduct = twoByThree.times(twoByThree); 
		assertEquals(twoByTwoResult1,matrixProduct);
	}
	
	@Test
	public void plusMatrixTwoByTwo() {
		Matrix matrixPlus = twoByTwo.plus(twoByTwo);
		Assert.assertTrue(twoByTwoResultPlus.equals(matrixPlus));
	}
	
	@Test
	public void plusMatrixhreeByTwo(){
		Matrix matrixPlus = twoByThree.plus(twoByThree);
		Assert.assertTrue(twoByThreeResultPlus.equals(matrixPlus));
	}
	
	@Test
	public void twoByTwoToString() {
		String resultString = ""+twoByTwoResult;
		
		Assert.assertEquals(resultString, twoByTwoResult.toString());
	}

}
