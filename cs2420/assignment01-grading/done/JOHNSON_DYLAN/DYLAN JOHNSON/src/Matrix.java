package assignment01;

/**
 * Assignment 1 for CS 2420
 * performs basic functions for matrices
 * @author Dylan Johnson -- u1024233
 * 8/31/16
 */
public class Matrix {
	int numRows;
	int numColumns;
	int data[][];

	// Constructor with data for new matrix (automatically determines dimensions)
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

	@Override
	/**
	 * overrides superclass's (Object) version of equals to compare matrices
	 * @return true if Matrices are equal, false otherwise
	 */
	public boolean equals(Object other) {
		if (!(other instanceof Matrix)) { // make sure the Object we're comparing to is a Matrix
			return false;
		}
		Matrix matrix = (Matrix) other; // if the above was not true, we know
										// it's safe to treat 'o' as a Matrix)
		if (this == matrix) // if they have the same reference value
			return true;	// they must be equal
		
		if (matrix.numRows != this.numRows || matrix.numColumns != this.numColumns)
			return false; // cannot be equal if different dimensions
		for (int i = 0; i < this.numRows; i++) {
			for (int j = 0; j < this.numColumns; j++) {
				if (this.data[i][j] != matrix.data[i][j])
					return false; // false if even one value is different
			}
		}
		return true; // passed all of the tests
	}

	@Override
	// instruct the compiler that we do indeed intend for this method to
	// override the superclass' (Object) version
	/**
	 * creates a String with spaces between each value in the Matrix and 
	 * new lines at the end of each row of the Matrix
	 * @return the String representation of the Matrix
	 */
	public String toString() {

		String matrix = new String();
		for (int i = 0; i < this.numRows; i++) {
			for (int j = 0; j < this.numColumns; j++) {
				matrix += this.data[i][j]; // add data element
				matrix += " "; // add space after element
				if (j == this.numColumns - 1)
					matrix += "\n"; // add new line if end of row
			}
		}
		return matrix;
	}

	/**
	 * multiplies two Matrices and returns the product Matrix
	 * @param matrix the Matrix to be multiplied with this Matrix
	 * @return the product of the Matrices, or null if they cannot be multiplied
	 */
	public Matrix times(Matrix matrix) {
		if (matrix == null)
			return null;
		if (this.numColumns != matrix.numRows)
			return null;
		int[][] data = new int[this.numRows][matrix.numColumns];

		for (int r = 0; r < this.numRows; r++) { // row 
			for (int c = 0; c < matrix.numColumns; c++) { // column
				for (int j = 0; j < this.numColumns; j++) { // each value
						data[r][c] += this.data[r][j]*matrix.data[j][c];
						// iterate over row of this and corresponding 
						// column of matrix, multiply these values
						// and sum these products in data matrix
				}
			}
		}
		Matrix product = new Matrix(data); // create Matrix from data
		return product; 
	}

	/**
	 * adds two Matrices and returns the sum Matrix
	 * @param matrix the matrix to be added to this
	 * @return the sum of the Matrices, or null if they cannot be added
	 */
	public Matrix plus(Matrix matrix) {
		if (matrix == null)
			return null;
		if (this.numRows != matrix.numRows || this.numColumns != matrix.numColumns)
			return null; // cannot be added if different sizes
		
		int[][] result = new int[this.numRows][this.numColumns]; // store results
		for (int i = 0; i < this.numRows; i++) {
			for (int j = 0; j < this.numColumns; j++) {
				result[i][j] = this.data[i][j] + matrix.data[i][j]; // add
			}
		}
		Matrix sum = new Matrix(result);
		return sum;
	}
}
