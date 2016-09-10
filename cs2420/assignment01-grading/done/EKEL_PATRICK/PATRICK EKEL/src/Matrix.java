package assignment01;

/**
 * this classes allows for operations to be performed on matrices
 * @author pat ekel
 * 8/31/16
 *
 */
public class Matrix {
	int numRows;
	int numColumns;
	int data[][];

	/**
	 * Constructor with data for new matrix (automatically determines
	 * dimensions)
	 * 
	 * @param data the content for the matrix
	 */
	public Matrix(int data[][]) {
		numRows = data.length; // d.atalength is the number of 1D arrays in the 2D
								// array
		if (numRows == 0) {
			numColumns = 0;
		} else {
			numColumns = data[0].length; // data[0] is the first 1D array
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
	 * checks if two matrixes are equivalent
	 */
	@Override // instruct the compiler that we do indeed intend for this method
		      // to override the superclass' (Object) version
	public boolean equals(Object other) {
		if (!(other instanceof Matrix)) { // make sure the Object we're
											// comparing to is a Matrix
			return false;
		}
		Matrix matrix = (Matrix) other; // if the above was not true, we know
										// it's safe to treat 'o' as a Matrix

		for (int i = 0; i < numRows; i++) { //check for equality
			for (int j = 0; j < numColumns; j++) {
				this.data[i][j] = data[i][j];
				if (this.data[i][j] != matrix.data[i][j])
					return false;
			}
		}
		return true;
	}

	/**
	 * creates a string representation of the matrix
	 */
	@Override // instruct the compiler that we do indeed intend for this method
			  // to override the superclass' (Object) version
	public String toString() {
		String print = "";
		
		for (int i = 0; i < numRows; i++) { // create string
			for (int j = 0; j < numColumns; j++) {
				print += this.data[i][j] + " ";
			}
			print += "\n";
		}
		return print;
	}

	/**
	 * Multiplies two matrices together
	 * 
	 * @param right hand matrix
	 * @return a new matrix that is the the product of the two matrices or null
	 *         if they cann't be multiplied
	 */
	public Matrix times(Matrix matrix) {
		if (this.numColumns != matrix.numRows) 
			return null; //return null if matrix dimensions are not compatible
		
		int[][] newMatrix = new int[this.numRows][matrix.numColumns];
		Matrix timesMatrix = new Matrix(newMatrix);
		
		for (int i = 0; i < this.numRows; i++) { //create product matrix
			for (int j = 0; j < matrix.numColumns; j++) {
				for (int k = 0; k < this.numColumns; k++) {
					timesMatrix.data[i][j] += this.data[i][k] * matrix.data[k][j];
				}
			}
		}

		return timesMatrix;
	}

	/**
	 * Adds two matrices together
	 * 
	 * @param right hand matrix
	 * @return a new matrix which is the the sum of the two matrices or null if
	 *         they cann't be added
	 */
	public Matrix plus(Matrix matrix) {
		if ((this.numRows != matrix.numRows) || (this.numColumns != matrix.numColumns))
			return null; //return null if matrix dimensions are not compatible
		
		Matrix sumMatrix = (Matrix) matrix;
		
		for (int i = 0; i < numRows; i++) { //create sum matrix
			for (int j = 0; j < numColumns; j++) {
				sumMatrix.data[i][j] = this.data[i][j] + matrix.data[i][j]; 
			}
		}
		return sumMatrix;
	}

}
