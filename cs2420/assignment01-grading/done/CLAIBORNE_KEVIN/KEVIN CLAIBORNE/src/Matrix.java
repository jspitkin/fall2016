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
		
		if (numRows != matrix.data.length || numColumns != matrix.data[0].length) {
			return false;
		}
		else {
			boolean isEqual = true; //boolean to return
			
			for (int i = 0; i < numRows; i++) {
				for (int j = 0; j < numColumns; j++) {
					if (data[i][j] != matrix.data[i][j]) {
						isEqual = false;
						break;					
					}
				}
			}
			return isEqual; // placeholder
		}	
	}

	@Override // instruct the compiler that we do indeed intend for this method
				// to override the superclass' (Object) version
	public String toString() {
		String result = "";
		
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				result += data[i][j] + " ";
			}
			result += "\n";
		}
		return result; // placeholder
	}

	public Matrix times(Matrix matrix) {
		if (numColumns == matrix.numRows) {		
			Matrix multi = new Matrix(new int[numRows][matrix.numColumns]);
			for (int i = 0; i < numRows; i++) {
				for (int j = 0; j < matrix.numColumns; j++) {
					for (int k=0; k<matrix.numRows; k++) {
						multi.data[i][j] += data[i][k] * matrix.data[k][j]; 
				    }
				}
			}
			return multi;
		}
		else {
			return null;
		}
	}

	public Matrix plus(Matrix matrix) {	
		if (numRows == matrix.numRows && numColumns == matrix.numColumns) {		
			Matrix sum = new Matrix(new int[numRows][numColumns]);
			for (int i = 0; i < numRows; i++) {
				for (int j = 0; j < numColumns; j++) {
					sum.data[i][j] = data[i][j] + matrix.data[i][j];
				}
			}
			return sum;
		}
		else {
			return null;
			
		}
	}
}
