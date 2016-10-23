package assignment07;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import assignment07.BalancedSymbolChecker;

public class BalancedSymbolCheckerJunit {

	BalancedSymbolChecker tester;

	@Before
	public void setup() {
		tester = new BalancedSymbolChecker();
	}

	@Test
	public void class1() throws IOException {

		Assert.assertTrue(tester.checkFile("Class1.java")
				.equals("ERROR: Unmatched symbol at line 6 and column 1. Expected ), but read } instead."));
	}

	@Test
	public void class2() throws IOException {
		//System.out.println(tester.checkFile("Class2.java"));
		Assert.assertTrue(
				tester.checkFile("Class2.java")
				.equals("ERROR: Unmatched symbol at line 7 and column 1. Expected  , but read } instead."));
	}

	@Test
	public void class3() throws IOException {

		Assert.assertTrue(
				tester.checkFile("Class3.java").equals("No errors found. All symbols match."));
	}

	@Test
	public void class4() throws IOException {

		Assert.assertTrue(tester.checkFile("Class4.java").equals("ERROR: File ended before closing comment."));
	}

	@Test
	public void class5() throws IOException {

		Assert.assertTrue(tester.checkFile("Class5.java")
				.equals("ERROR: Unmatched symbol at line 3 and column 18. Expected ], but read } instead."));
	}

	@Test
	public void class6() throws IOException {

		Assert.assertTrue(tester.checkFile("Class6.java").equals("No errors found. All symbols match."));
	}

	@Test
	public void class7() throws IOException {

		Assert.assertTrue(tester.checkFile("Class7.java")
				.equals("ERROR: Unmatched symbol at line 3 and column 33. Expected ], but read ) instead."));
	}

	@Test
	public void class8() throws IOException {

		Assert.assertTrue(tester.checkFile("Class8.java")
				.equals("ERROR: Unmatched symbol at line 5 and column 30. Expected }, but read ) instead."));
	}

	@Test
	public void class9() throws IOException {

		Assert.assertTrue(tester.checkFile("Class9.java")
				.equals("ERROR: Unmatched symbol at line 3 and column 33. Expected ), but read ] instead."));
	}

	@Test
	public void class10() throws IOException {

		Assert.assertTrue(tester.checkFile("Class10.java")
				.equals("ERROR: Unmatched symbol at line 5 and column 10. Expected }, but read ] instead."));
	}

	@Test
	public void class11() throws IOException {

		Assert.assertTrue(
				tester.checkFile("Class11.java").equals("ERROR: Unmatched symbol at the end of file. Expected }." ));
	}
	
		
	
	@Test
		public void class12() throws IOException {

			Assert.assertTrue(tester.checkFile("Class12.java").equals("No errors found. All symbols match." ));
		}
		
	
	@Test
		public void class13() throws IOException {
			
			Assert.assertTrue(tester.checkFile("Class13.java").equals("No errors found. All symbols match." ));
		}
		
	@Test
		public void class14() throws IOException {

			Assert.assertTrue(tester.checkFile("Class14.java").equals("No errors found. All symbols match." ));
		}
	
	@Test
	public void largeTest() throws IOException {
		Assert.assertTrue(
				tester.checkFile("DoublyLinkedList.java").equals("No errors found. All symbols match." ));
	}
		
	}
	


