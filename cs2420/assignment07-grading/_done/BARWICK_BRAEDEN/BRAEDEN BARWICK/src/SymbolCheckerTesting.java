package assignment07;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Braeden Barwick u0959391
 *
 */
public class SymbolCheckerTesting {
	
	public BalancedSymbolChecker symbols;
	public LinkedListStack<Integer> stack;

	@Before
	public void setUp() throws Exception {
		symbols = new BalancedSymbolChecker();
		stack = new LinkedListStack<Integer>();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testLinkedListStack(){
		stack.push(3);
		assertTrue(3 == stack.peek());
		assertTrue(3 == stack.pop());
		assertTrue(stack.isEmpty());
		
		stack.push(1);
		stack.push(2);
		stack.push(3);
		
		//check correct push() order
		assertFalse(1 == stack.peek());
		
		//check isEmpty()
		assertFalse(stack.isEmpty());
		
		//check pop() in order
		assertTrue(3 == stack.pop());
		assertTrue(2 == stack.pop());
		assertTrue(1 == stack.pop());
		
		//check exception throwing
		try{
			stack.pop();
		}
		catch(NoSuchElementException e){
			assertTrue(true);
		}
		try{
			stack.peek();
		}
		catch(NoSuchElementException e){
			assertTrue(true);
		}
		
	}

	@Test
	public void testGivenFiles() throws FileNotFoundException {
		
		//Given correct messages for class1-14
		String pass1, pass2, pass3, pass4, pass5, pass6, pass7, pass8, pass9, pass10, pass11, pass12, pass13, pass14;
		pass1 = "ERROR: Unmatched symbol at line 6 and column 1. Expected ), but read } instead.";
		pass2 = "ERROR: Unmatched symbol at line 7 and column 1. Expected  , but read } instead.";
		pass3 = "No errors found. All symbols match.";
		pass4 = "ERROR: File ended before closing comment.";
		pass5 = "ERROR: Unmatched symbol at line 3 and column 18. Expected ], but read } instead.";
		pass6 = "No errors found. All symbols match.";
		pass7 = "ERROR: Unmatched symbol at line 3 and column 33. Expected ], but read ) instead.";
		pass8 = "ERROR: Unmatched symbol at line 5 and column 30. Expected }, but read ) instead.";
		pass9 = "ERROR: Unmatched symbol at line 3 and column 33. Expected ), but read ] instead.";
		pass10 = "ERROR: Unmatched symbol at line 5 and column 10. Expected }, but read ] instead.";
		pass11 = "ERROR: Unmatched symbol at the end of file. Expected }.";
		pass12 = "No errors found. All symbols match.";
		pass13 = "No errors found. All symbols match.";
		pass14 = "No errors found. All symbols match.";
		
		//Check each class with checkFile() against the expected message
		assertTrue(symbols.checkFile(("Class1.java")).equals(pass1));
		assertTrue(symbols.checkFile(("Class2.java")).equals(pass2));
		assertTrue(symbols.checkFile(("Class3.java")).equals(pass3));
		assertTrue(symbols.checkFile(("Class4.java")).equals(pass4));
		assertTrue(symbols.checkFile(("Class5.java")).equals(pass5));
		assertTrue(symbols.checkFile(("Class6.java")).equals(pass6));
		assertTrue(symbols.checkFile(("Class7.java")).equals(pass7));
		assertTrue(symbols.checkFile(("Class8.java")).equals(pass8));
		assertTrue(symbols.checkFile(("Class9.java")).equals(pass9));
		assertTrue(symbols.checkFile(("Class10.java")).equals(pass10));
		assertTrue(symbols.checkFile(("Class11.java")).equals(pass11));
		assertTrue(symbols.checkFile(("Class12.java")).equals(pass12));
		assertTrue(symbols.checkFile(("Class13.java")).equals(pass13));
		assertTrue(symbols.checkFile(("Class14.java")).equals(pass14));
	}

}
