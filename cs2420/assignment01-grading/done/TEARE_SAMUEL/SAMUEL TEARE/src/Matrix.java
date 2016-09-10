package assignment01;

/**
 * Program for creating Matrices. It also includes methods for checking two matrices for equality and adding and multiplying two matrices.
 * @author Mariah Meyer and Samuel Teare
 * <br>Last Updated: 08/29/2016
 */
public class Matrix {
	int numRows;
	int numColumns;
	int data[][];

	// Constructor with data for new matrix (automatically determines
	// dimensions)
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

	@Override // instruct the compiler that we do indeed intend for this method
				// to override the superclass' (Object) version
	public boolean equals(Object other) {
		if (!(other instanceof Matrix)) { // make sure the Object we're
											// comparing to is a Matrix
			return false;
		}
		Matrix matrix = (Matrix) other; // if the above was not true, we know
										// it's safe to treat 'o' as a Matrix

		// Checks to see if the two matrices have the same number of rows and
		// columns.
		if (this.numRows == matrix.numRows && this.numColumns == matrix.numColumns) {
			/*
			 * If the two matrices have the same number of rows and columns,
			 * check each value of the matrices to check if they match.
			 */
			for (int i = 0; i < this.numRows; i++) {
				for (int j = 0; j < this.numColumns; j++) {
					// If at any point of the check the two values of the
					// matrices do not match, returns false.
					if (this.data[i][j] != matrix.data[i][j]) {
						return false;

					}
				}
			}
			// If the check goes through all of the values of the two matrices
			// and there are no discrepancies, returns true.
			return true;
		} else
			return false;
	}

	@Override // instruct the compiler that we do indeed intend for this method
				// to override the superclass' (Object) version
	public String toString() {
		
		String matrixString = "";
		
		for (int i = 0; i < this.numRows; i++) {
			for (int j = 0; j < this.numColumns; j++) {
				matrixString = matrixString + this.data[i][j] + " ";
			}
			if (i < (this.numRows - 1))
				matrixString = matrixString + "\n";
		}
		
		return matrixString;
	}

	public Matrix times(Matrix matrix) {
		/*
		 * TODO: replace the below return statement with the correct code, This
		 * function must check if the two matrices are compatible for
		 * multiplication, if not, return null. If they are compatible,
		 * determine the dimensions of the resulting matrix, and fill it in with
		 * the correct values for matrix multiplication
		 */
		
		/*
		 * Checks to see if the provided matrix is compatible for multiplication with this matrix. 
		 * If the provided matrix is incompatible null is returned.
		 */
		if (this.numRows != matrix.numColumns || this.numColumns != matrix.numRows) {
			return null;
		}
		
		Matrix matrixProduct = new Matrix(new int[this.numRows][matrix.numColumns]);
		
		//Loops through each of the values for the new matrix
		for (int i = 0; i < this.numRows; i++) {
			for (int j = 0; j < matrix.numColumns; j++) {
				for (int h = 0; h < this.numColumns; h++) {
					//Calculates the product of the two matrices and assigns the value to the new matrix.
					matrixProduct.data[i][j] = matrixProduct.data[i][j] + this.data[i][h] * matrix.data[h][j];
				}
			}
		}
		
		return matrixProduct;
	}

	public Matrix plus(Matrix matrix) {
		
		/*
		 * Checks to see if the provided matrix is compatible for addition with this matrix.
		 * If the provided matrix is incompatible null is returned.
		 */
		if (this.numRows != matrix.numRows || this.numColumns != matrix.numColumns) {
			return null;
		}
		
		/*
		 * If the two matrices are compatible, the two matrices are added together and a new matrix is returned.
		 */
		Matrix matrixSum = new Matrix(new int[this.numRows][this.numColumns]);
		for (int i = 0; i < this.numRows; i++) {
			for (int j = 0; j < this.numColumns; j++) {
				matrixSum.data[i][j] = this.data[i][j] + matrix.data[i][j];
			}
		}
		return matrixSum;
	}
}
