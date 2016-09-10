package assignment01;

import java.text.DecimalFormat;
import java.util.Arrays;

public class Grader {
	static int testsRun = 0, testsPassed = 0;
	static Matrix m1, m1b, m2, m2b, m3, m3b, m4, m4b, m5, m6, m7;
	static ReferenceMatrix r1, r1b, r2, r2b, r3, r3b, r4, r4b, r5, r6, r7;
	
	/**
	 * Checks a single test case and updates static test counts accordingly
	 * 
	 * @param expected Expected result of the test
	 * @param actual Result returned by the students code
	 * @param description Brief description of test to be output in case of failure
	 * @return true if test passed, false if failed
	 */
	static boolean checkRes(Object expected, Object actual, String description) {
		testsRun++;
		boolean pass;
		
		if(expected == null) {
			pass = actual == null;
		} else {
			pass = expected.equals(actual);
		}
		
		if(pass) {
			return true;
		} else {
			System.out.println("Failed " + description + ":");
			System.out.println("\tExpected \"" + expected + "\"");
			System.out.println("\tGot \"" + actual + "\"");
			return false;
		}
	}
	
	/**
	 * Resets static test counts before each test category
	 */
	static void reset() {
		testsRun = 0;
		testsPassed = 0;
	}
	
	/**
	 * Checks a single instance of toString()
	 */
	static void checkOneToString(ReferenceMatrix r, Matrix m, String description) {
		try {
			ReferenceMatrix pre = new ReferenceMatrix(m);
			if(checkRes(r.toString(), m.toString(), description)) {
				if(pre.equals(m)) {
					testsPassed++;
				} else {
					System.out.println("Failed " + description + ":");
					System.out.println("\tInitial Matrix Altered");
					init();
				}
			} else {
				if(!pre.equals(m)) {
					System.out.println("\tInitial Matrix Altered");
					init();
				}
			}
		} catch (Exception e) {
			testsRun++;
			System.out.println("Failed " + description + ":");
			System.out.println("\tThrew Exception: " + e.getClass().getName());
		}
	}
	
	/**
	 * Checks a single instance of toString() without whitespace
	 */
	static void checkOneToStringNoWhitespace(ReferenceMatrix r, Matrix m, String description) {
		try {
			ReferenceMatrix pre = new ReferenceMatrix(m);
			if(checkRes(r.toString().replaceAll("\\W", ""), m.toString().replaceAll("\\W", ""), description)) {
				if(pre.equals(m)) {
					testsPassed++;
				} else {
					System.out.println("Failed " + description + ":");
					System.out.println("\tInitial Matrix Altered");
					init();
				}
			} else {
				if(!pre.equals(m)) {
					System.out.println("\tInitial Matrix Altered");
					init();
				}
			}
		} catch (Exception e) {
			testsRun++;
			System.out.println("Failed " + description + ":");
			System.out.println("\tThrew Exception: " + e.getClass().getName());
		}
	}
	
	/**
	 * Checks a single instance of equals() - r1.equals(r2) is compared to m1.equals(m2)
	 */
	static void checkOneEquals(ReferenceMatrix r1, ReferenceMatrix r2,
			Matrix m1, Matrix m2, String description) {
		try {
			ReferenceMatrix pre1 = new ReferenceMatrix(m1);
			ReferenceMatrix pre2 = new ReferenceMatrix(m2);
			if(checkRes(r1.equals(r2), m1.equals(m2), description)) {
				if(pre1.equals(m1) && pre2.equals(pre2)) {
					testsPassed++;
				} else {
					System.out.println("Failed " + description + ":");
					System.out.println("\tInitial Matrix Altered");
					init();
				}
			} else {
				if(!(pre1.equals(m1) && pre2.equals(m2))) {
					System.out.println("\tInitial Matrix Altered");
					init();
				}
			}
		} catch (Exception e) {
			testsRun++;
			System.out.println("Failed " + description + ":");
			System.out.println("\tThrew Exception: " + e.getClass().getName());
		}
	}
	
	/**
	 * Checks a single instance of times - r1.times(r2) is compared to m1.times(m2)
	 */
	static void checkOneTimes(ReferenceMatrix r1, ReferenceMatrix r2,
			Matrix m1, Matrix m2, String description) {
		try {
			ReferenceMatrix pre1 = new ReferenceMatrix(m1);
			ReferenceMatrix pre2 = new ReferenceMatrix(m2);
			if(checkRes(r1.times(r2), m1.times(m2), description)) {
				if(pre1.equals(m1) && pre2.equals(pre2)) {
					testsPassed++;
				} else {
					System.out.println("Failed " + description + ":");
					System.out.println("\tInitial Matrix Altered");
					init();
				}
			} else {
				if(!(pre1.equals(m1) && pre2.equals(m2))) {
					System.out.println("\tInitial Matrix Altered");
					init();
				}
			}
		} catch (Exception e) {
			testsRun++;
			System.out.println("Failed " + description + ":");
			System.out.println("\tThrew " + e.getClass().getName());
		}
	}
	
	/**
	 * Checks a single instance of plus - r1.plus(r2) is compared to m1.plus(m2)
	 */
	static void checkOnePlus(ReferenceMatrix r1, ReferenceMatrix r2,
			Matrix m1, Matrix m2, String description) {
		try {
			ReferenceMatrix pre1 = new ReferenceMatrix(m1);
			ReferenceMatrix pre2 = new ReferenceMatrix(m2);
			if(checkRes(r1.plus(r2), m1.plus(m2), description)) {
				if(pre1.equals(m1) && pre2.equals(pre2)) {
					testsPassed++;
				} else {
					System.out.println("Failed " + description + ":");
					System.out.println("\tInitial Matrix Altered");
					init();
				}
			} else {
				if(!(pre1.equals(m1) && pre2.equals(m2))) {
					System.out.println("\tInitial Matrix Altered");
					init();
				}
			}
		} catch (Exception e) {
			testsRun++;
			System.out.println("Failed " + description + ":");
			System.out.println("\tThrew " + e.getClass().getName());
		}
	}
	
	/**
	 * Runs all tests for the toString method
	 * 
	 * @return percentage of tests passed in the range [0,1]
	 */
	static double checkToString() {
		reset();
		
		checkOneToString(r1, m1, "2x3 toString 1");
		checkOneToString(r2, m2, "3x2 toString 1");
		//checkOneToString(r3, m3, "1x0 toString");
		checkOneToString(r4, m4, "2x2 toString");
		checkOneToString(r5, m5, "2x3 toString 2");
		checkOneToString(r6, m6, "2x2 toString 2");
		checkOneToStringNoWhitespace(r1, m1, "2x3 toString stripped whitespace"); // A looser test just in case they got
		//checkOneToStringNoWhitespace(r3, m3, "1x0 toString stripped whitespace"); // got the whitespace wrong
		
		return ((float)testsPassed)/testsRun;
	}

	/**
	 * Runs all tests for the equals method
	 * 
	 * @return percentage of tests passed in the range [0,1]
	 */
	static double checkEquals() {
		reset();
		
		checkOneEquals(r1, r1, m1, m1, "2x3 == itself");  //true
		checkOneEquals(r2, r2, m2, m2, "3x2 == itself");  //true
		//checkOneEquals(r3, r3, m3, m3, "1x0 == itself");  //true
		checkOneEquals(r4, r4, m4, m4, "2x2 == itself");  //true
		
		checkOneEquals(r1, r1b, m1, m1b, "2x3 == 2x3");   //true
		checkOneEquals(r2, r2b, m2, m2b, "3x2 == 3x2");   //true
	//	checkOneEquals(r3, r3b, m3, m3b, "1x0 == 1x0");   //true
		checkOneEquals(r4, r4b, m4, m4b, "2x2 == 2x2");   //true
		
		checkOneEquals(r1, r2, m1, m2, "2x3 != 3x2");   //false
		//checkOneEquals(r1, r3, m1, m3, "2x3 != 1x0");   //false
		checkOneEquals(r4, r1, m4, m1, "2x2 != 2x3");   //false
		checkOneEquals(r5, r1, m5, m1, "2x3 != 2x3");   //false
		checkOneEquals(r5, r2, m5, m2, "2x3 != 3x2");   //false
		checkOneEquals(r6, r4, m6, m4, "2x2 != 2x2");   //false
		//checkOneEquals(r3, r5, m3, m5, "1x0 != 2x3");   //false
		//checkOneEquals(r3, r6, m3, m6, "1x0 != 2x2");   //false
		
		return ((float)testsPassed)/testsRun;
	}

	/**
	 * Runs all tests for the times method
	 * 
	 * @return percentage of tests passed in the range [0,1]
	 */
	static double checkTimes() {
		reset();
		
		checkOneTimes(r1, r2, m1, m2, "2x3 * 3x2");    //valid
		checkOneTimes(r2, r1, m2, m1, "3x2 * 2x3");    //valid
		checkOneTimes(r1, r1, m1, m1, "2x3 * itself"); //invalid
		checkOneTimes(r2, r2, m2, m2, "3x2 * itself"); //invalid
		//checkOneTimes(r3, r3, m3, m3, "1x0 * itself"); //invalid
		//checkOneTimes(r2, r3, m2, m3, "3x2 * 1x0");    //invalid
		//checkOneTimes(r3, r1, m3, m1, "1x0 * 2x3");    //invalid
		checkOneTimes(r4, r4, m4, m4, "2x2 * itself"); //valid
		checkOneTimes(r4, r1, m4, m1, "2x2 * 2x3");    //valid
		checkOneTimes(r1, r4, m1, m4, "2x3 * 2x2");    //invalid
		
		return ((float)testsPassed)/testsRun;
	}

	/**
	 * Runs all tests for the plus method
	 * 
	 * @return percentage of tests passed in the range [0,1]
	 */
	static double checkPlus() {
		reset();

		checkOnePlus(r1, r1, m1, m1, "2x3 + itself"); //valid
		checkOnePlus(r2, r2, m2, m2, "3x2 + itself"); //valid
		//checkOnePlus(r3, r3, m3, m3, "1x0 + itself"); //valid
		checkOnePlus(r4, r4, m4, m4, "2x2 + itself"); //valid
		
		checkOnePlus(r1, r5, m1, m5, "2x3 + 2x3");    //valid
		checkOnePlus(r4, r6, m4, m6, "2x2 + 2x2");    //valid
		checkOnePlus(r2, r7, m2, m7, "3x2 + 3x2");    //valid
		
		checkOnePlus(r1, r2, m1, m2, "2x3 + 3x2");    //invalid
		//checkOnePlus(r2, r3, m2, m3, "3x2 + 1x0");    //invalid
		//checkOnePlus(r3, r1, m3, m1, "1x0 + 2x3");    //invalid
		checkOnePlus(r4, r1, m4, m1, "2x2 + 2x3");    //invalid
		checkOnePlus(r1, r4, m1, m4, "2x3 + 2x2");    //invalid
		
		return ((float)testsPassed)/testsRun;
	}
	
	/**
	 * Sets up test data
	 */
	static void init() {
		//2x3
		int d1[][] = new int[][] {{1, 2, 3}, {2, 5, 6}};
		m1 = new Matrix(d1);
		m1b = new Matrix(d1);
		r1 = new ReferenceMatrix(d1);
		r1b = new ReferenceMatrix(d1);
		
		//3x2
		int d2[][] = new int[][] {{-4, 5}, {3, 2}, {1, 1}};
		m2 = new Matrix(d2);
		m2b = new Matrix(d2);
		r2 = new ReferenceMatrix(d2);
		r2b = new ReferenceMatrix(d2);
		
		//1x0 - empty
		int d3[][] = new int[][] {{}};
		m3 = new Matrix(d3);
		m3b = new Matrix(d3);
		r3 = new ReferenceMatrix(d3);
		r3b = new ReferenceMatrix(d3);
		
		//2x2
		int d4[][] = new int[][] {{4, 6}, {3, 1}};
		m4 = new Matrix(d4);
		m4b = new Matrix(d4);
		r4 = new ReferenceMatrix(d4);
		r4b = new ReferenceMatrix(d4);
		
		//another 2x3
		int d5[][] = new int[][] {{-4, 3, 1}, {5, 2, 1}};
		m5 = new Matrix(d5);
		r5 = new ReferenceMatrix(d5);
		
		//another 2x2
		int d6[][] = new int[][] {{1, 3}, {6, -4}};
		m6 = new Matrix(d6);
		r6 = new ReferenceMatrix(d6);
		
		//another 3x2
		int d7[][] = new int[][] {{0, 0}, {0, 0}, {0, 0}};
		m7 = new Matrix(d7);
		r7 = new ReferenceMatrix(d7);
	}
	
	public static void main(String[] args) {
		DecimalFormat df = new DecimalFormat("#.##");
		double score = 0.0, toStringScore, equalsScore, timesScore, plusScore;
		init();
		score += toStringScore = Grader.checkToString()*15.0;
		score += equalsScore = Grader.checkEquals()*15.0;
		score += timesScore = Grader.checkTimes()*30.0;
		score += plusScore = Grader.checkPlus()*30.0;
		System.out.println("toString:\t" + df.format(toStringScore) + "/15");
		System.out.println("equals:\t\t" + df.format(equalsScore) + "/15");
		System.out.println("times:\t\t" + df.format(timesScore) + "/30");
		System.out.println("plus:\t\t" + df.format(plusScore) + "/30");
		System.out.println("Total:\t\t" + df.format(score) + "/90");
	}
	
	public static class ReferenceMatrix {
		int numRows;
		int numColumns;
		int data[][];

		// Constructor with data for new matrix (automatically determines
		// dimensions)
		public ReferenceMatrix(int d[][]) {
			numRows = d.length; // d.length is the number of 1D arrays in the 2D
								// array
			if (numRows == 0)
				numColumns = 0;
			else
				numColumns = d[0].length; // d[0] is the first 1D array
			data = new int[numRows][numColumns]; // create a new matrix to hold the
													// data
			// copy the data over
			for (int i = 0; i < numRows; i++)
				for (int j = 0; j < numColumns; j++)
					data[i][j] = d[i][j];
		}

		public ReferenceMatrix(Matrix m) {
			numRows = m.numRows;
			if (numRows == 0)
				numColumns = 0;
			else
				numColumns = m.numColumns;

			data = new int[numRows][numColumns];
			// copy the data over
			for (int i = 0; i < numRows; i++)
				for (int j = 0; j < numColumns; j++)
					data[i][j] = m.data[i][j];
		}

		/**
		 * returns the boolean value after determining if this matrix is equal to
		 * the imput matrix
		 */
		@Override // instruct the compiler that we do indeed intend for this method
					// to override the superclass' (Object) version
		public boolean equals(Object o) {

			// make sure the Object we're comparing to is a Matrix
			if ((o instanceof Matrix)) {
				Matrix m = (Matrix) o;
				if (Arrays.deepEquals(this.data, m.data)) {
					return true;
				} else {
					return false;
				}
			}
			// Check if its a ReferenceMatrix
			else if ((o instanceof ReferenceMatrix)) {
				ReferenceMatrix m = (ReferenceMatrix) o;
				if (Arrays.deepEquals(this.data, m.data)) {
					return true;
				} else {
					return false;
				}
			}
			// Object is neither a ReferenceMatrix or Matrix
			else {
				return false;
			}
		}

		/**
		 * returns a string that represents this Matrix
		 */
		@Override // instruct the compiler that we do indeed intend for this method
					// to override the superclass' (Object) version
		public String toString() {
			// create an empty string
			String s = "";

			// for each cell in the matrix, add it to the empty string.
			for (int x = 0; x < numRows; x++) {
				for (int y = 0; y < numColumns; y++)

					// adds the number to the string with a space after each number
					s += data[x][y] + " ";

				// adds a new line to the string after each row is completed
				s += "\n";
			}

			// returns the string
			return s;
		}

		/**
		 * This function checks if the two matrices are compatible for
		 * multiplication, if not, returns null. If they are compatible, determines
		 * the dimensions of the resulting matrix, and fills it in with the correct
		 * values for matrix multiplication
		 */
		public ReferenceMatrix times(ReferenceMatrix m) {
			// if matrices are not compatible for multiplication, returns null.
			if (numColumns != m.numRows)
				return null;
			else {
				// creates an empty matrix, determines the dimensions of resulting
				// matrix.
				ReferenceMatrix multiply = new ReferenceMatrix(new int[numRows][m.numColumns]);

				// algorithm for multiplicating the two matrices
				for (int x = 0; x < numRows; x++)
					for (int y = 0; y < m.numColumns; y++)
						for (int i = 0; i < numColumns; i++)

							// put the multiplication results in the new matrix
							multiply.data[x][y] += (data[x][i] * m.data[i][y]);

				// return the matrix with the result
				return multiply;
			}
		}

		/**
		 * This function checks if the two matrices are compatible for addition, if
		 * not, returns null. If they are compatible, creates a new matrix and fill
		 * it in with the correct values for matrix addition
		 */
		public ReferenceMatrix plus(ReferenceMatrix m) {
			// if dimensions are not compatible, returns null.
			if ((numRows != m.numRows) || (numColumns != m.numColumns))
				return null;
			else {
				// create new matrix with correct dimensions
				ReferenceMatrix add = new ReferenceMatrix(data);

				// algorithm for adding two matrices together
				for (int y = 0; y < numColumns; y++)
					for (int x = 0; x < numRows; x++)
						add.data[x][y] += m.data[x][y];

				// return the matrix
				return add;
			}

		}
	}
}
