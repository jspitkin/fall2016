package assignment01;
/**
 * assignment01
 * @author Ryan Cantera
 * u0855101
 * 8/31/2016
 * This class contains a set of methods that are used to manipulate
 * sets of data that are given in the form of matrices. 
 */

public class Matrix {
	int numRows;
	int numColumns;
	int data[][];

	/**
	 * This is the constructor for the Matrix class. The only parameter it takes is a two-dimensional
	 * array of integers called data. This constructor also automatically determines dimensions. 
	 * @param data 
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
	 * This equals method overrides the equals method found in the inherited Object class.
	 * This equals method is specifically used to check if both the dimensions and values of 
	 * the matrices are equal.  
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
		
		int x = 0;
		if (this.numRows == matrix.numRows && this.numColumns == matrix.numColumns) {
			for (int i = 0; i < this.numRows; i++) {//counter for rows and columns
				for (int j = 0; j < this.numColumns; j++) {
					if (this.data[i][j] == matrix.data[i][j]) {
						x++;//counter to check if values are equivalent. If so, x increases
					}
				}
			}
		}
		if (x == this.numColumns * this.numRows && x == matrix.numColumns * matrix.numRows)
			return true;//only if they are the exact same size and value because x is counting 
						//the equivalent values. x will be the same value as the area of the matrix
						//if and only if all values are the same also.
		else
			return false;
	}
	/**
	 * This toString method overrides the toString method found in the inherited Object class.
	 * This method neatly displays the matrix to the user.
	 */
	@Override 
	public String toString() {
		String s = "";
		for (int i = 0; i < this.numRows; i++) {
			for (int j = 0; j < this.numColumns; j++) {
				s += this.data[i][j] + " "; //displays each row with a space in between each value
			}
			s += "\n"; //adds a new line after last column is reached at the end of each row
		}
		return s;
	}
	
	/**
	 * This method is used to multiply two matrices together. 
	 * @param matrix - the second matrix that is to be multiplied to the first.
	 * @return the resulting matrix, or null if the first matrix does not 
	 * have the same number of columns as the second does rows.
	 */
	public Matrix times(Matrix matrix) {
		
		if (this.numColumns != matrix.numRows)
			return null; //return null if the first matrix's number of columns is not
						//the same as the second matrix's number of rows
		else {
			int[][] finalArray = new int[this.numRows][matrix.numColumns];//the resulting array will have the same number
																		//of rows as the first matrix, and columns as the second
			for (int i = 0; i < this.numRows; i++) {
				for (int h = 0; h < matrix.numColumns; h++) {//this counter is the number of columns in the resulting array
					for (int j = 0; j < this.numColumns; j++) {
						finalArray[i][h] += this.data[i][j] * matrix.data[j][h];
					}
				}
			}
		Matrix newMatrix = new Matrix(finalArray);//construct a matrix out of the array
		return newMatrix;
		}
	}
	
	/**
	 * This method adds two matrices together
	 * @param matrix - the second matrix that is to be multiplied by the first
	 * @return the resulting matrix, or null if the two matrices do not have
	 * the same number of rows and columns.
	 */
	public Matrix plus(Matrix matrix) {
		
		if (this.numColumns != matrix.numColumns || this.numRows != matrix.numRows)
			return null; //must have the same number of rows and columns
		else {
			int[][] finalArray = new int[this.numRows][this.numColumns];
			int newValue;
			for (int i = 0; i < this.numRows; i++) {//counters for the rows and columns
				for (int j = 0; j < this.numColumns; j++) {
					newValue = this.data[i][j] + matrix.data[i][j];
					finalArray[i][j] = newValue;//fill the result with the new values
				}
			}
			Matrix newMatrix = new Matrix(finalArray);
			return newMatrix;
		}
	}
}
