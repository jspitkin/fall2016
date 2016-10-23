package assignment07;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the BalancedSymbolChecker class
 * 
 * @author Nickolas Lee
 * Created: Oct 18, 2016
 */
public class BalancedSymbolCheckerTest {

	BalancedSymbolChecker checker;

	@Before
	public void setUp() throws Exception {
		checker = new BalancedSymbolChecker();
	}

	@Test
	public void testReadFile() {
		ArrayList<String> answer = new ArrayList<String>();
		answer.add("this is a test");
		assertArrayEquals(answer.toArray(), checker.readFile("test.txt").toArray());
	}

	@Test
	public void testCheckFile() throws FileNotFoundException {
		assertEquals("No errors found. All symbols match.", checker.checkFile("test.txt"));
	}

	@Test
	public void testCheckFile1() throws FileNotFoundException {
		String file = "A7_examples/class1.java";
		assertEquals(8, checker.readFile(file).size());
		String mess = "ERROR: Unmatched symbol at line 6 and column 1. Expected ), but read } instead.";
		assertEquals(mess, checker.checkFile(file));
	}

	@Test
	public void testCheckFile2() throws FileNotFoundException {
		String file = "A7_examples/class2.java";
		assertEquals(8, checker.readFile(file).size());
		String mess = "ERROR: Unmatched symbol at line 7 and column 1. Expected  , but read } instead.";
		assertEquals(mess, checker.checkFile(file));
	}

	@Test
	public void testCheckFile3() throws FileNotFoundException {
		String file = "A7_examples/class3.java";
		assertEquals(10, checker.readFile(file).size());
		String mess = "No errors found. All symbols match.";
		assertEquals(mess, checker.checkFile(file));
	}

	@Test
	public void testCheckFile4() throws FileNotFoundException{
		String file = "A7_examples/class4.java";
		assertEquals(11, checker.readFile(file).size());
		String mess = "ERROR: File ended before closing comment.";
		assertEquals(mess, checker.checkFile(file));
	}

	@Test
	public void testCheckFile5() throws FileNotFoundException{
		String file = "A7_examples/class5.java";
		assertEquals(10, checker.readFile(file).size());
		String mess = "ERROR: Unmatched symbol at line 3 and column 18. Expected ], but read } instead.";
		assertEquals(mess, checker.checkFile(file));
	}

	@Test
	public void testCheckFile6() throws FileNotFoundException{
		String file = "A7_examples/class6.java";
		assertEquals(11, checker.readFile(file).size());
		String mess = "No errors found. All symbols match.";
		assertEquals(mess, checker.checkFile(file));
	}

	@Test
	public void testCheckFile7() throws FileNotFoundException{
		String file = "A7_examples/class7.java";
		assertEquals(10, checker.readFile(file).size());
		String mess = "ERROR: Unmatched symbol at line 3 and column 33. Expected ], but read ) instead.";
		assertEquals(mess, checker.checkFile(file));
	}

	@Test
	public void testCheckFile8() throws FileNotFoundException{
		String file = "A7_examples/class8.java";
		assertEquals(10, checker.readFile(file).size());
		String mess =  "ERROR: Unmatched symbol at line 5 and column 30. Expected }, but read ) instead.";
		assertEquals(mess, checker.checkFile(file));
	}

	@Test
	public void testCheckFile9() throws FileNotFoundException{
		String file = "A7_examples/class9.java";
		assertEquals(10, checker.readFile(file).size());
		String mess = "ERROR: Unmatched symbol at line 3 and column 33. Expected ), but read ] instead.";
		assertEquals(mess, checker.checkFile(file));
	}

	@Test
	public void testCheckFile10() throws FileNotFoundException{
		String file = "A7_examples/class10.java";
		assertEquals(10, checker.readFile(file).size());
		String mess = "ERROR: Unmatched symbol at line 5 and column 10. Expected }, but read ] instead.";
		assertEquals(mess, checker.checkFile(file));
	}

	@Test
	public void testCheckFile11() throws FileNotFoundException{
		String file = "A7_examples/class11.java";
		assertEquals(4, checker.readFile(file).size());
		String mess = "ERROR: Unmatched symbol at the end of file. Expected }.";
		assertEquals(mess, checker.checkFile(file));
	}

	@Test
	public void testCheckFile12() throws FileNotFoundException{
		String file = "A7_examples/class12.java";
		assertEquals(10, checker.readFile(file).size());
		String mess = "No errors found. All symbols match.";
		assertEquals(mess, checker.checkFile(file));
	}

	@Test
	public void testCheckFile13() throws FileNotFoundException{
		String file = "A7_examples/class13.java";
		assertEquals(11, checker.readFile(file).size());
		String mess = "No errors found. All symbols match.";
		assertEquals(mess, checker.checkFile(file));
	}

	@Test
	public void testCheckFile14() throws FileNotFoundException{
		String file = "A7_examples/class14.java";
		assertEquals(7, checker.readFile(file).size());
		String mess = "No errors found. All symbols match.";
		assertEquals(mess, checker.checkFile(file));
	}
}

