package assignment01;

/**
 * Matrix - is a class that creates and manipulates an obect called Matrix. The values inside 
 * the Matrix are held by a 2D array.
 * 
 * 08/29/2016
 * 
 * @author Kyle Price
 *
 */
public class Matrix { 
	
	int numRows;
	int numColumns;
	int data[][];

	public Matrix(int data[][]) {
		numRows = data.length;
		if (numRows == 0) {
			numColumns = 0;
		} else {
			numColumns = data[0].length;
		}
		this.data = new int[numRows][numColumns];
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				this.data[i][j] = data[i][j];
			}
		}
	}
	
	/**
	 * equals - determines whether two Matrix objects have the same integer elements. It will simply return false 
	 * if the elements aren't the same OR the matrices don't have the same dimensions. Otherwise, it will return true. 
	 * 
	 * @return - boolean (true or false)
	 */
	public boolean equals(Object other) {
		if (!(other instanceof Matrix)) {
			return false;
		}
		Matrix matrix = (Matrix) other;

		if (matrix.numRows != this.numRows || matrix.numColumns != this.numColumns) {
			return false;
		}
		for (int row = 0; row < matrix.numRows; row++) {
			for (int col = 0; col < matrix.numColumns; col++) {
				if (this.data[row][col] != matrix.data[row][col]) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * toString - takes a 2D array and converts it into a String (finalString) that is formatted into rows and columns
	 * similar to how the actual 2D array is setup. 
	 * 
	 * @return - a String containing a formatted version of the 2D array 'data'.
	 */
	public String toString() {
		String finalString = "";
		boolean hasIterated = false; 
		// hasIterated is a flag that indicates if there has been at least one loop. This
		// ensures that the nested loop won't make an unnecessary "/n" gap mistake.  
		for (int row = 0; row < data.length; row++) {
			if (hasIterated) {
				finalString += "\n";
			}
			for (int col = 0; col < data[row].length; col++) {
				finalString += data[row][col] + " ";
				hasIterated = true;
			}
		}
		return finalString;
	}
	
	/**
	 * times - performs matrix multiplication on two Matrix objects. The argument matrix must have
	 * the same number of rows as the columns of the matrix the method is called upon otherwise an 
	 * IllegalArgumentException will be thrown. 
	 * 
	 * @param matrix - a Matrix object with a 2D array of integers. 
	 * @return newMat - newMat is the newly created matrix containing the products from the argument 'matrix' and
	 * the matrix the method is being called upon. 
	 */
	public Matrix times(Matrix matrix) {
		Matrix newMat;
		if (this.numColumns != matrix.numRows) {
			throw new IllegalArgumentException("The argument matrix number of rows must be equal to"
					+ "the number of columns from the matrix that is calling the function.");
		}
		else {
			newMat = new Matrix(new int[this.numRows][matrix.numColumns]);
			int temp = 0;
			for (int i = 0; i < this.numRows; i++) { // i moves though the row of the left matrix (fills in newMat).
				for (int j = 0; j < matrix.numColumns; j++) { // j moves through the columns of the right matrix.
					temp = 0;
					for (int k = 0; k < this.numColumns; k++) { // k through the columns of the left matrix. 
						temp += (this.data[i][k] * matrix.data[k][j]);

					}
					newMat.data[i][j] = temp;
				}
			}
		}
		return newMat;

	}
	
	/**
	 * plus - performs matrix addition on two matrices. Their dimensions must be 
	 * the exact same (row/column). 
	 * 
	 * @param matrix - a Matrix object containing a 2D array of integers.
	 * @return newMat - the newly created matrix containing the sum of the integers from 
	 * matrix and the instance of the Matrix object the method was called upon. 
	 */
	public Matrix plus(Matrix matrix) {
		if (matrix.numRows != this.numRows || matrix.numColumns != this.numColumns) {
			return null;
		}
		Matrix newMat = new Matrix(new int[this.numRows][this.numColumns]);
		for (int row = 0; row < data.length; row++) {
			for (int col = 0; col < data[row].length; col++) {
				newMat.data[row][col] = this.data[row][col] + matrix.data[row][col];
			}
		}

		return newMat;
	}
}
