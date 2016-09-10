package assignment01;

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

		// Checks if Matrices are same size
		if (matrix.numColumns != this.numColumns || matrix.numRows != this.numRows) {
			return false;
		}

		// Checks if any element in matrix does not equal
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				if (matrix.data[i][j] != this.data[i][j]) {
					return false;
				}
			}
		}
		return true; // Return true if passes all conditions
	}

	@Override // instruct the compiler that we do indeed intend for this method
				// to override the superclass' (Object) version
	public String toString() {

		String result = "";

		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				result += this.data[i][j] + " ";
			}
			result += "\n";
		}

		return result;
	}

	public Matrix times(Matrix matrix) {

		if (this.numColumns == matrix.numRows) {

			Matrix result = new Matrix(new int[this.numRows][matrix.numColumns]);

			for (int i = 0; i < result.numRows; i++) {
				for (int j = 0; j < result.numColumns; j++) {

					for (int k = 0; k < this.numColumns; k++) {

						result.data[i][j] += (this.data[i][k] * matrix.data[k][j]);
						
					}

				}
			}

			return result;
		}

		return null; // placeholder
	}

	public Matrix plus(Matrix matrix) {

		if (matrix.numColumns == this.numColumns && matrix.numRows == this.numRows) {

			int result[][] = new int[numRows][numColumns];

			for (int i = 0; i < numRows; i++) {
				for (int j = 0; j < numColumns; j++) {
					result[i][j] = this.data[i][j] + matrix.data[i][j];
				}
			}

			return new Matrix(result);

		}

		return null; // placeholder
	}
}
