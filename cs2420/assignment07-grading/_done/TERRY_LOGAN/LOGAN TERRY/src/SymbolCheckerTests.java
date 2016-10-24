package assignment07;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

/**
 * Testing class for BalancedSymbolChecker.
 * @author Logan Terry u0973436
 *
 */
public class SymbolCheckerTests {

	@Test
	public void SymbolMatchClass1() {
		assertEquals("ERROR: Unmatched symbol at line 6 and column 1. Expected ), but read } instead.",
				getResult("Class1.java"));
	}
	
	@Test
	public void SymbolMatchClass2(){
		assertEquals("ERROR: Unmatched symbol at line 7 and column 1. Expected  , but read } instead.",
				getResult("Class2.java"));
	}
	
	@Test
	public void SymbolMatchClass3(){
		assertEquals("No errors found. All symbols match.", getResult("Class3.java"));
	}
	
	@Test
	public void SymbolMatchClass4(){
		assertEquals("ERROR: File ended before closing comment.", getResult("Class4.java"));
	}
	
	@Test
	public void SymbolMatchClass5(){
		assertEquals("ERROR: Unmatched symbol at line 3 and column 18. Expected ], but read } instead.",
				getResult("Class5.java"));
	}
	
	@Test
	public void SymbolMatchClass6(){
		assertEquals("No errors found. All symbols match.", getResult("Class6.java"));
	}
	
	@Test
	public void SymbolMatchClass7(){
		assertEquals("ERROR: Unmatched symbol at line 3 and column 33. Expected ], but read ) instead.",getResult("Class7.java"));
	}
	
	@Test
	public void SymbolMatchClass8(){
		assertEquals("ERROR: Unmatched symbol at line 5 and column 30. Expected }, but read ) instead.",getResult("Class8.java"));
	}
	
	@Test
	public void SymbolMatchClass9(){
		assertEquals("ERROR: Unmatched symbol at line 3 and column 33. Expected ), but read ] instead.",getResult("Class9.java"));
	}
	
	@Test
	public void SymbolMatchClass10(){
		assertEquals("ERROR: Unmatched symbol at line 5 and column 10. Expected }, but read ] instead.",getResult("Class10.java"));
	}
	
	@Test
	public void SymbolMatchClass11(){
		assertEquals("ERROR: Unmatched symbol at the end of file. Expected }.",getResult("Class11.java"));
	}
	
	@Test
	public void SymbolMatchClass12(){
		assertEquals("No errors found. All symbols match.",getResult("Class12.java"));
	}
	
	@Test
	public void SymbolMatchClass13(){
		assertEquals("No errors found. All symbols match.",getResult("Class13.java"));
	}
	
	@Test
	public void SymbolMatchClass14(){
		assertEquals("No errors found. All symbols match.",getResult("Class14.java"));
	}
	
	@Test (expected = FileNotFoundException.class)
	public void SymbolMatchNonExistintFile() throws FileNotFoundException{
		try {
			new BalancedSymbolChecker().checkFile("something");
		} catch (FileNotFoundException e) {
			throw e;
		}
	}
	
	private String getResult(String filename){
		String result = "";
		try{
			result = new BalancedSymbolChecker().checkFile("Files/"+filename);
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}
		return result;
	}

}
