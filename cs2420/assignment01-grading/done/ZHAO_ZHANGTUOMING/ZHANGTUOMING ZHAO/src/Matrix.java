package assignment01;

import java.util.Arrays;


/**
 * This is the assigment 01
 * @author zhangtuoming
 * @class CS-2420
 *
 */

public class Matrix {
	int numRows;
	int numColumns;
	int data[][];
	
	// Constructor with data for new matrix (automatically determines dimensions)
	public Matrix(int data[][])
	{
		numRows = data.length; // d.length is the number of 1D arrays in the 2D array
		if(numRows == 0) {
			numColumns = 0;
		} else {
			numColumns = data[0].length; // d[0] is the first 1D array
		}
		this.data = new int[numRows][numColumns]; // create a new matrix to hold the data
		// copy the data over
		for(int i=0; i < numRows; i++) { 
			for(int j=0; j < numColumns; j++) {
				this.data[i][j] = data[i][j];
			}
		}
	}
	
	@Override // instruct the compiler that we do indeed intend for this method to override the superclass' (Object) version
	public boolean equals(Object other)
	{
		if(!(other instanceof Matrix)) { // make sure the Object we're comparing to is a Matrix
			return false;
		}
		Matrix matrix = (Matrix)other; // if the above was not true, we know it's safe to treat 'o' as a Matrix
		
		/*
		 * TODO: replace the below return statement with the correct code, you must return the correct value
		 * 			after determining if this matrix is equal to the input matrix
		 */
		
		// first check the dimension of the matrix, if it is equal, check the
				// data inside of the matrix
				if (this.numColumns == (matrix.numColumns) && this.numRows == (matrix.numRows)) {

					// set up 2 for loop to go through every data point inside of
					// matrix.
					for (int i = 0; i < this.numColumns; i++) {
						for (int j = 0; j < this.numRows; j++) {
							if (this.data[i][j] == matrix.data[i][j]) {
								return true;
							}else{
								return false;
							}
						}
					}
				} else {
					return false;
				}
				return false;
		
	}
	
	@Override // instruct the compiler that we do indeed intend for this method to override the superclass' (Object) version
	public String toString()
	{
		/*
		 * TODO: replace the below return statement with the correct code, you must return a String that represents this 
		 * 			matrix, as specified on the assignment page
		 */ 
		String output = "";

		//if it is one by one matrix
				if (this.numColumns == 1 && this.numRows == 1) {
					output = data[0][0] + " " + "\n";
				} else if (this.numColumns == 1) { // if it is n by 1 matrix
					for (int i = 0; i < this.numRows; i++) {
						output = output + data[i][0] + " " + "\n";
					}
				} else if (this.numRows == 1) { //  if it is 1 by n matrix
					for (int j = 0; j < this.numColumns; j++) {
						output = output + data[0][j] + " ";
					}
				} else { //  if it is n by n matrix
					for (int i = 0; i < this.numRows; i++) { 
						for (int j = 0; j < this.numColumns; j++) {

							if (j == this.numColumns - 1) { // if it is the last
															// element, make a new line
															// by \n
								output = output + this.data[i][j] + " " + "\n";
							} else if (i == 0 && j == 0) { // if it is the first element
								output = this.data[i][j] + " ";
							} else {
								output = output + this.data[i][j] + " ";
							}

						}
					}
				}
							
		return output;
	}
	
	public Matrix times(Matrix matrix)
	{
		/*
		 * TODO: replace the below return statement with the correct code, 
		 *  This function must check if the two matrices are compatible for multiplication, if not, return null.
		 *  If they are compatible, determine the dimensions of the resulting matrix, and fill it in with
		 *  the correct values for matrix multiplication
		 */
		// new arrays of data to store the result
				int[][] ma = new int[this.numRows][matrix.numColumns];
				
				// first check the dimension
				if (this.numColumns == matrix.numRows) {
					
					// set up 3 for loop to go through these 2 matrices
					for (int i = 0; i < this.numRows; i++) {
						for (int j = 0; j < matrix.numColumns; j++) {
							for (int k = 0; k < this.numColumns; k++) {
								// sum up the result of each data point
								ma[i][j] = (int) (ma[i][j] + this.data[i][k]
										* matrix.data[k][j]);

							}
						}
					}
					return new Matrix(ma);

				} else {
					return null; 
				}
	}
	
	public Matrix plus(Matrix matrix)
	{
		
		/*
		 * TODO: replace the below return statement with the correct code, 
		 *  This function must check if the two matrices are compatible for addition, if not, return null.
		 *  If they are compatible, create a new matrix and fill it in with
		 *  the correct values for matrix addition
		 */
		// first check the dimenstion of the matrix
				if (matrix.numColumns == this.numColumns && matrix.numRows == this.numRows) {

					int[][] d = new int[numRows][numColumns];
					
					// set up 2 for loops to go through data points
					for (int i = 0; i < numRows; i++) {
						for (int j = 0; j < numColumns; j++) {
							// get the result
							int a = 0;
							a = this.data[i][j] + matrix.data[i][j];// add the value
							d[i][j] = a;
						}
					}
					return new Matrix(d);
				} else {
					return null; 
				}
			}
}
