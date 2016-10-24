package assignment07;

import org.junit.Assert;

import java.io.FileNotFoundException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Testing class for the BalancedSymbolChecker class
 * @author Jared Nielson u0495206
 *
 */
public class BalancedSymbolCheckerTester {
	private BalancedSymbolChecker symbolChecker;
	
	@Before
	public void setUp() throws Exception {
		symbolChecker = new BalancedSymbolChecker();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void classOne() {
		try{			
			String result = symbolChecker.checkFile("Class1.java");
			
			Assert.assertEquals("ERROR: Unmatched symbol at line 6 and column 1. Expected ), but read } instead.", result);
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void classTwo() {
		try{			
			String result = symbolChecker.checkFile("Class2.java");
			
			Assert.assertEquals("ERROR: Unmatched symbol at line 7 and column 1. Expected  , but read } instead.", result);
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void classThree(){
		try{			
			String result = symbolChecker.checkFile("Class3.java");
			
			Assert.assertEquals("No errors found. All symbols match.", result);
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void classFour(){
		try{			
			String result = symbolChecker.checkFile("Class4.java");
			
			Assert.assertEquals("ERROR: File ended before closing comment.", result);
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void classFive(){
		try{			
			String result = symbolChecker.checkFile("Class5.java");
			
			Assert.assertEquals("ERROR: Unmatched symbol at line 3 and column 18. Expected ], but read } instead.", result);
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void classSix(){
		try{			
			String result = symbolChecker.checkFile("Class6.java");
			
			Assert.assertEquals("No errors found. All symbols match.", result);
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void classSeven(){
		try{			
			String result = symbolChecker.checkFile("Class7.java");
			
			Assert.assertEquals("ERROR: Unmatched symbol at line 3 and column 33. Expected ], but read ) instead.", result);
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void classEight(){
		try{			
			String result = symbolChecker.checkFile("Class8.java");
			
			Assert.assertEquals("ERROR: Unmatched symbol at line 5 and column 30. Expected }, but read ) instead.", result);
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void classNine(){
		try{			
			String result = symbolChecker.checkFile("Class9.java");
			
			Assert.assertEquals("ERROR: Unmatched symbol at line 3 and column 33. Expected ), but read ] instead.", result);
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void classTen(){
		try{			
			String result = symbolChecker.checkFile("Class10.java");
			
			Assert.assertEquals("ERROR: Unmatched symbol at line 5 and column 10. Expected }, but read ] instead.", result);
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void classEleven(){
		try{			
			String result = symbolChecker.checkFile("Class11.java");
			
			Assert.assertEquals("ERROR: Unmatched symbol at the end of file. Expected }.", result);
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void classTwelve(){
		try{			
			String result = symbolChecker.checkFile("Class12.java");
			
			Assert.assertEquals("No errors found. All symbols match.", result);
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void classThirteen(){
		try{			
			String result = symbolChecker.checkFile("Class13.java");
			
			Assert.assertEquals("No errors found. All symbols match.", result);
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void classFourteen(){
		try{			
			String result = symbolChecker.checkFile("Class14.java");
			
			Assert.assertEquals("No errors found. All symbols match.", result);
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void openCommentBlock(){
		try{
			String result = symbolChecker.checkFile("Open Comment.txt");
			
			Assert.assertEquals("ERROR: File ended before closing comment.", result);
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void nestedComments(){
		try{
			String result = symbolChecker.checkFile("Nested Comments.txt");
			
			Assert.assertEquals("No errors found. All symbols match.", result);
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
	}
}
