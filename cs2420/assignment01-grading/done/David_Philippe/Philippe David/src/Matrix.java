package assignment01;

/**
 * Matrix.java -- A program that represents matrices expressed as 2d arrays and allows for
 * a number of operations to be performed using them.
 * <p>
 * Written to complete assignment 1 for CS 2420.
 * 
 * @author Philippe David, last update 9/29/16
 */
public class Matrix {
	
	// Private member variables for the class
	int numRows;
	int numColumns;
	int data[][];

	/**
	 * Constructor with data for new matrix (automatically determines dimensions)
	 * 
	 * @param a 2d integer array
	 */
	public Matrix(int data[][]) {
		numRows = data.length; // d.length is the number of 1D arrays in the 2D
								// array
		if (numRows == 0) {
			numColumns = 0;
		} else {
			numColumns = data[0].length; // d[0] is the first 1D array
		}
		this.data = new int[numRows][numColumns]; // create a new matrix to hold
													// the data
		// copy the data over
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				this.data[i][j] = data[i][j];
			}
		}
	}

	/**
	 * Compares "this" object to another Matrix object for equality.
	 * 
	 * @param other
	 *            -- the other object
	 * @return true if the contents of the objects are the same, false otherwise
	 */
	@Override /* instruct the compiler that we do indeed intend for this method
				 to override the superclass' (Object) version */
	public boolean equals(Object other) {
		
		if (!(other instanceof Matrix)) { /* make sure the Object we're
											 comparing to is a Matrix */
			return false;
		}
		
		Matrix matrix = (Matrix) other; /* if the above was not true, we know
										   it's safe to treat 'o' as a Matrix */

		// check if dimensions are equal
		if (this.numRows != matrix.numRows || this.numColumns != matrix.numColumns)
			return false;

		// check if the content of both matrices are equal
		for (int i = 0; i < this.numRows; i++) {
			for (int j = 0; j < this.numColumns; j++) {
				if (this.data[i][j] != matrix.data[i][j])
					return false;

			}
		}

		return true;
	}

	/**
	 * This method converts the Matrix object to a string.
	 * 
	 * @return returns the matrix represented by this object as a string
	 */
	@Override /* instruct the compiler that we do indeed intend for this method
				 to override the superclass' (Object) version */
	public String toString() {

		// Initialize a string
		String matrix_string = "";

		
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				matrix_string += data[i][j] + " ";	
			}
			matrix_string += "\n";
		}
		
		return matrix_string;
	}

	/**
	 * This method multiplies "this" object from the other object and returns a
	 * new Matrix object whose value is (this * other).
	 * 
	 * @param other
	 *            -- the other object
	 * @return a new Matrix object whose value is (this * other)
	 */
	public Matrix times(Matrix matrix) {

		// Check if dimensions are equal
		if (this.numColumns != matrix.numRows)
			return null;

		Matrix result_matrix = new Matrix(new int[this.numRows][matrix.numColumns]);

		for (int i = 0; i < this.numRows; i++) {
			for (int j = 0; j < matrix.numColumns; j++) {
				int sum = 0;
				for (int k = 0; k < this.numColumns; k++) {
					sum += this.data[i][k] * matrix.data[k][j];
				}
				result_matrix.data[i][j] = sum;
			}
		}

		return result_matrix; 
	}

	/**
	 * This method adds "this" object from the other object and returns a new
	 * Matrix object whose value is (this + other).
	 * 
	 * @param other
	 *            -- the other object
	 * @return a new Matrix object whose value is (this + other)
	 */
	public Matrix plus(Matrix matrix) {
		
		if (this.numRows != matrix.numRows || this.numColumns != matrix.numColumns)
			return null;

		Matrix result_matrix = new Matrix(this.data);

		for (int i = 0; i < this.numRows; i++) {
			for (int j = 0; j < this.numColumns; j++) {
				result_matrix.data[i][j] += matrix.data[i][j];
			}
		}
		
		return result_matrix; 
	}
}
