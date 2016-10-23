package assignment07;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.NoSuchElementException;
import java.util.Random;

@SuppressWarnings("unused")
public class BalancedSymbolGraderS15 {
	//Replace the following with the location of the files on your machine
	static final String FILE_PATH = "balanced_symbol_files_s15/";
	
	static final int SC = 0, PU = 1, PE = 2, PO = 3, CF = 4;
	static float[] pointsPossible = {5, 5, 5, 5, 55};
	static int[] testsPassed = new int[pointsPossible.length];
	static final String[] methodNames = {"clear", "push", "peek", "pop", "checkFile"};
	static final int[] testCounts = {8, 3, 6, 6, 30};
	
	static int nLinkedListStackPoints = 20;
	static int nBSCPoints = 30;
	static Random rng = new Random(1618);
	
	private static class Tester implements Runnable {
		private int method, test;

		public Tester(int _method, int _test) {
			method = _method;
			test = _test;
		}

		public void run() {
			try {
				this.getClass().getMethod("test_" + methodNames[method] + "_" + test).invoke(null);
				return;
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
			System.exit(1); //If we got here, there was a reflection exception
		}
		
		public static void test_clear_0() {
			try {
				LinkedListStack<Integer> stack = new LinkedListStack<Integer>();
				if(stack.size() == 0) {
					testsPassed[SC]++;
				} else {
					System.out.println("Failed on size of empty stack");
				}
			} catch (Throwable e) {
				System.out.println("Exception on size of empty stack : " + e);
			}
		}
		
		public static void test_clear_1() {
			try {
				LinkedListStack<Integer> stack = new LinkedListStack<Integer>();
				if(stack.isEmpty()) {
					testsPassed[SC]++;
				} else {
					System.out.println("Failed on isEmpty on empty stack");
				}
			} catch (Throwable e) {
				System.out.println("Exception on isEmpty on empty stack : " + e);
			}
		}
		
		public static void test_clear_2() {
			try {
				LinkedListStack<Integer> stack = new LinkedListStack<Integer>();
				stack.clear();
				if(stack.size() == 0) {
					testsPassed[SC]++;
				} else {
					System.out.println("Failed on size of cleared empty stack");
				}
			} catch (Throwable e) {
				System.out.println("Exception on size of cleared empty stack : " + e);
			}
		}
		
		public static void test_clear_3() {
			try {
				LinkedListStack<Integer> stack = new LinkedListStack<Integer>();
				stack.clear();
				if(stack.isEmpty()) {
					testsPassed[SC]++;
				} else {
					System.out.println("Failed on isEmpty on cleared empty stack");
				}
			} catch (Throwable e) {
				System.out.println("Exception on isEmpty on cleared empty stack : " + e);
			}
		}
		
		public static void test_clear_4() {
			try {
				LinkedListStack<Integer> stack = new LinkedListStack<Integer>();
				stack.push(3);
				stack.clear();
				if(stack.size() == 0) {
					testsPassed[SC]++;
				} else {
					System.out.println("Failed on clearing non-empty stack (size)");
				}
			} catch (Throwable e) {
				System.out.println("Exception on clearing non-empty stack (size) : " + e);
			}
		}
		
		public static void test_clear_5() {
			try {
				LinkedListStack<Integer> stack = new LinkedListStack<Integer>();
				stack.push(3);
				stack.clear();
				if(stack.isEmpty()) {
					testsPassed[SC]++;
				} else {
					System.out.println("Failed on clearing non-empty stack (isEmpty)");
				}
			} catch (Throwable e) {
				System.out.println("Exception on clearing non-empty stack (isEmpty) : " + e);
			}
		}
		
		public static void test_clear_6() {
			try {
				LinkedListStack<Integer> stack = new LinkedListStack<Integer>();
				stack.push(3);
				stack.clear();
				stack.clear();
				if(stack.size() == 0) {
					testsPassed[SC]++;
				} else {
					System.out.println("Failed on clearing stack twice (size)");
				}
			} catch (Throwable e) {
				System.out.println("Exception on clearing stack twice (size) : " + e);
			}
		}
		
		public static void test_clear_7() {
			try {
				LinkedListStack<Integer> stack = new LinkedListStack<Integer>();
				stack.push(3);
				stack.clear();
				stack.clear();
				if(stack.isEmpty()) {
					testsPassed[SC]++;
				} else {
					System.out.println("Failed on clearing stack twice (isEmpty)");
				}
			} catch (Throwable e) {
				System.out.println("Exception on clearing stack twice (isEmpty) : " + e);
			}
		}
		
		public static void test_push_0() {
			try {
				LinkedListStack<Integer> stack = new LinkedListStack<Integer>();
				stack.push(12);
				if(stack.size() == 1) {
					testsPassed[PU]++;
				}
			} catch (Throwable e) {
				System.out.println("Exception on pushing to stack : " + e);
			}
		}
		
		public static void test_push_1() {
			try {
				LinkedListStack<Integer> stack = new LinkedListStack<Integer>();
				stack.push(12);
				stack.push(15);
				stack.push(9);
				stack.push(3);
				stack.push(19);
				if(stack.size() == 5) {
					testsPassed[PU]++;
				}
			} catch (Throwable e) {
				System.out.println("Exception on multiple pushes to stack : " + e);
			}
		}
		
		public static void test_push_2() {
			try {
				LinkedListStack<Integer> stack = new LinkedListStack<Integer>();
				for (int j = 0; j < 1000; j++) {
					stack.push(rng.nextInt(10000));
				}
				if(stack.size() == 1000) {
					testsPassed[PU]++;
				}
			} catch (Throwable e) {
				System.out.println("Exception on pushing stress test : " + e);
			}
		}
		
		public static void test_peek_0() {
			try {
				LinkedListStack<Integer> stack = new LinkedListStack<Integer>();
				stack.peek(); // Try to peek an empty stack, should get NoSuchElementException
				System.out.println("Failed to throw NoSuchElementException when peeking empty stack.");
			} catch (NoSuchElementException e) {
				testsPassed[PE]++;
			} catch (Throwable e) {
				System.out.println("Threw wrong exception when peeking empty stack: " + e);
			}
		}
		
		public static void test_peek_1() {
			try {
				LinkedListStack<Integer> stack = new LinkedListStack<Integer>();
				stack.push(12);
				if(stack.peek() == 12 && stack.size() == 1) {
					testsPassed[PE]++;
				}
			} catch (Throwable e) {
				System.out.println("Exception on peek, singleton stack : " + e);
			}
		}
		
		public static void test_peek_2() {
			try {
				LinkedListStack<Integer> stack = new LinkedListStack<Integer>();
				stack.push(12);
				stack.push(15);
				stack.push(9);
				stack.push(3);
				stack.push(19);
				if(stack.peek() == 19 && stack.size() == 5) {
					testsPassed[PE]++;
				}
			} catch (Throwable e) {
				System.out.println("Exception on peek, medium stack : " + e);
			}
		}
		
		public static void test_peek_3() {
			try {
				LinkedListStack<Integer> stack = new LinkedListStack<Integer>();
				for (int j = 0; j < 1000; j++) {
					stack.push(rng.nextInt(10000));
				}
				stack.push(27);
				if(stack.peek() == 27 && stack.size() == 1001) {
					testsPassed[PE]++;
				}
			} catch (Throwable e) {
				System.out.println("Exception on peek, large stack : " + e);
			}
		}
		
		public static void test_peek_4() {
			try {
				LinkedListStack<Integer> stack = new LinkedListStack<Integer>();
				stack.push(12);
				stack.push(15);
				stack.push(9);
				stack.push(3);
				stack.push(19);
				stack.pop();
				if(stack.peek() == 3 && stack.size() == 4) {
					testsPassed[PE]++;
				}
			} catch (Throwable e) {
				System.out.println("Exception on peek after pop  : " + e);
			}
		}
		
		public static void test_peek_5() {
			try {
				LinkedListStack<Integer> stack = new LinkedListStack<Integer>();
				stack.push(8);
				stack.pop();
				stack.peek(); // Try to peek an empty stack, should get NoSuchElementException
				System.out.println("Failed to throw NoSuchElementException when peeking empty stack.");
			} catch (NoSuchElementException e) {
				testsPassed[PE]++;
			} catch (Throwable e) {
				System.out.println("Threw wrong exception when peeking empty stack: " + e);
			}
		}
		
		public static void test_pop_0() {
			try {
				LinkedListStack<Integer> stack = new LinkedListStack<Integer>();
				stack.pop(); // Try to pop an empty stack, should get NoSuchElementException
				System.out.println("Failed to throw NoSuchElementException when popping empty stack.");
			} catch (NoSuchElementException e) {
				testsPassed[PO]++;
			} catch (Throwable e) {
				System.out.println("Threw wrong exception when peeking empty stack: " + e);
			}
		}
		
		public static void test_pop_1() {
			try {
				LinkedListStack<Integer> stack = new LinkedListStack<Integer>();
				stack.push(12);
				if(stack.pop() == 12 && stack.size() == 0) {
					testsPassed[PO]++;
				}
			} catch (Throwable e) {
				System.out.println("Exception on pop, singleton stack : " + e);
			}
		}
		
		public static void test_pop_2() {
			try {
				LinkedListStack<Integer> stack = new LinkedListStack<Integer>();
				stack.push(12);
				stack.push(15);
				stack.push(9);
				stack.push(3);
				stack.push(19);
				if(stack.pop() == 19 && stack.size() == 4 && stack.peek() == 3) {
					testsPassed[PO]++;
				}
			} catch (Throwable e) {
				System.out.println("Exception on pop, medium stack : " + e);
			}
		}
		
		public static void test_pop_3() {
			try {
				LinkedListStack<Integer> stack = new LinkedListStack<Integer>();
				for (int j = 0; j < 1000; j++) {
					stack.push(rng.nextInt(10000));
				}
				stack.push(27);
				if(stack.pop() == 27 && stack.size() == 1000) {
					testsPassed[PO]++;
				}
			} catch (Throwable e) {
				System.out.println("Exception on pop, large stack : " + e);
			}
		}
		
		public static void test_pop_4() {
			try {
				LinkedListStack<Integer> stack = new LinkedListStack<Integer>();
				stack.push(12);
				stack.push(15);
				stack.push(9);
				stack.push(3);
				stack.push(19);
				stack.pop();
				if(stack.pop() == 3 && stack.size() == 3) {
					testsPassed[PO]++;
				}
			} catch (Throwable e) {
				System.out.println("Exception on popping twice  : " + e);
			}
		}
		
		public static void test_pop_5() {
			try {
				LinkedListStack<Integer> stack = new LinkedListStack<Integer>();
				stack.push(8);
				stack.pop();
				stack.pop(); // Try to pop an empty stack, should get NoSuchElementException
				System.out.println("Failed to throw NoSuchElementException when peeking empty stack.");
			} catch (NoSuchElementException e) {
				testsPassed[PO]++;
			} catch (Throwable e) {
				System.out.println("Threw wrong exception when popping empty stack: " + e);
			}
		}
	}

	private static void testBalancedSymbolChecker() {
		try {
			// Non-existent file
			BalancedSymbolChecker checker = new BalancedSymbolChecker();
			checker.checkFile("Class0.java");
			System.out.println("Did not throw FileNotFoundException for non-existent file.");
		} catch(Throwable e) {
			if(e instanceof FileNotFoundException) {
				testsPassed[CF]++;
			} else {
				System.out.println("Threw incorrect exception for non-existent file.");
			}
		}
		
		testFile("Class1.java", "ERROR: Unmatched symbol at line 6 and column 1. Expected ), but read } instead.");
		testFile("Class2.java", "ERROR: Unmatched symbol at line 7 and column 1. Expected  , but read } instead.");
		testFile("Class3.java", "No errors found. All symbols match.");
		testFile("Class4.java", "ERROR: File ended before closing comment.");
		testFile("Class5.java", "ERROR: Unmatched symbol at line 3 and column 18. Expected ], but read } instead.");
		testFile("Class6.java", "No errors found. All symbols match.");
		testFile("Class7.java", "ERROR: Unmatched symbol at line 3 and column 33. Expected ], but read ) instead.");
		testFile("Class8.java", "ERROR: Unmatched symbol at line 5 and column 30. Expected }, but read ) instead.");
		testFile("Class9.java", "ERROR: Unmatched symbol at line 3 and column 33. Expected ), but read ] instead.");
		testFile("Class10.java", "ERROR: Unmatched symbol at line 5 and column 10. Expected }, but read ] instead.");
		testFile("Class11.java", "ERROR: Unmatched symbol at the end of file. Expected }.");
		testFile("Class12.java", "No errors found. All symbols match.");
		testFile("Class13.java", "No errors found. All symbols match.");
		testFile("Class14.java", "No errors found. All symbols match.");
		testFile("Class15.java", "ERROR: Unmatched symbol at line 7 and column 1. Expected  , but read ) instead.");
		testFile("Class16.java", "ERROR: Unmatched symbol at line 7 and column 1. Expected  , but read ] instead.");
		testFile("Class17.java", "ERROR: Unmatched symbol at the end of file. Expected ).");
		testFile("Class18.java", "ERROR: Unmatched symbol at the end of file. Expected ].");
		testFile("Class19.java", "No errors found. All symbols match.");
		testFile("Class20.java", "ERROR: Unmatched symbol at line 1 and column 1. Expected  , but read ) instead.");
		testFile("Class21.java", "ERROR: Unmatched symbol at line 1 and column 1. Expected  , but read ] instead.");
		testFile("Class22.java", "No errors found. All symbols match.");
		testFile("Class23.java", "No errors found. All symbols match.");
		testFile("Class24.java", "ERROR: Unmatched symbol at line 6 and column 5. Expected ), but read } instead.");
		testFile("Class25.java", "ERROR: Unmatched symbol at line 6 and column 42. Expected ), but read } instead.");
		testFile("Class26.java", "ERROR: Unmatched symbol at line 3 and column 44. Expected }, but read ) instead.");
		testFile("Class27.java", "No errors found. All symbols match.");
		testFile("Class28.java", "No errors found. All symbols match.");
		testFile("Class29.java", "No errors found. All symbols match.");
	}

	public static class FileTester implements Runnable {
		private String filename, errormsg;
		
		public boolean result;
		
		public FileTester(String filename, String errormsg) {
			this.filename = filename;
			this.errormsg = errormsg;
			this.result = false;
		}
		
		public void run() {
			String res = " ";
			try {
				BalancedSymbolChecker checker = new BalancedSymbolChecker();
				res = checker.checkFile(filename);
			} catch (Throwable e) {
				System.out.println("Exception generated by checkFile() : " + e);
			}
			result = res.equals(errormsg);
		}
	}
	
	@SuppressWarnings("deprecation")
	private static void testFile(String filename, String errormsg) {
		String filePath = FILE_PATH + filename;
		URL resource = BalancedSymbolChecker.class.getClassLoader().getResource(filePath);
		if(resource != null ) {
			filePath = resource.getPath();
		}
		FileTester ft = new FileTester(filePath, errormsg);
		Thread t = new Thread(ft);
		t.start();
		try {
			t.join(3000);
			if(t.isAlive()) {
				System.out.println("checkFile() appears to be running infinitely.");
				t.stop();
				return;
			} else {
				if(ft.result) {
					testsPassed[CF]++;
				} else {
					System.out.println("Failed on " + filename);
				}
			}
		} catch (InterruptedException e) {
			System.err.println("Something went wrong with the grader, run again");
			System.exit(1);
		}
	}
	
	/**
	 * Used as a marker for printing a relevant error message when a student's
	 * code is running infinitely.
	 */
	static class RunningInfinitelyException extends Exception {
		private static final long serialVersionUID = 1L;
		public String toString() {
			return "Running Infinitely";
		}
	}

	/***
	 * Actually run a test in another thread.
	 */
	@SuppressWarnings("deprecation")
	private static void runTest(Runnable r) {
		Thread t = new Thread(r);
		t.start();
		try {
			t.join(3000);
			if (t.isAlive()) {
				t.stop(new RunningInfinitelyException());
			}
		} catch (InterruptedException e) {
			System.err.println("Something went wrong with the grader, run again");
			System.exit(1);
		}
	}

	public static void main(String[] args) {
		DecimalFormat df = new DecimalFormat("#.##");
		float total = 0, subtotal = 0, totalPossible = 0;
		System.out.println("---TESTING MY STACK---");
		for(int i=0; i<testCounts.length; i++) {
			if(i == CF) {
				//BalancedSymbolChecker doesn't run through the same system as everything else
				System.out.println("LinkedListStack Subtotal:\t\t\t" + df.format(subtotal) + "/20");
				total += subtotal;
				subtotal = 0;
				System.out.println("\n---TESTING BALANCED SYMBOL CHECKER---");
				testBalancedSymbolChecker();
			} else {
				System.out.println("Testing " + methodNames[i]);
				for(int j=0; j<testCounts[i]; j++) {
					runTest(new Tester(i, j));
				}
			}
			
			float points = pointsPossible[i] * testsPassed[i] / testCounts[i];
			System.out.println("Tests:\t\t\t\t\t" + testsPassed[i] + "/" + df.format(testCounts[i]));
			System.out.println("Points:\t\t\t\t\t" + df.format(points) + "/" + df.format(pointsPossible[i]));
			System.out.println("--------------------");
			
			subtotal += points;
			totalPossible += pointsPossible[i];
		}
		
		total += subtotal;
		System.out.println("Final Score\n");
		System.out.println("Points from automated tests:\t\t" + df.format(total) + "/75");
		System.out.println("Style/Testing:\t\t\t\tx/5");
		System.out.println("Analysis Document:\t\t\txx/40");
		System.out.println("Total:\t\t\t\t\t" + "xx/100");
		System.out.println("--------------------");
		System.out.println("Comments:\n");
	}
}