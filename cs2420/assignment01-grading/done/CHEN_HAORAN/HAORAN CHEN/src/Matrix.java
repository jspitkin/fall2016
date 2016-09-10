package assignment01;


/**
 * A program calculating in fundamental ways for matrix ,compares two matrixes and printing out 
 * the matrix in a normal way.
 * 
 * @author Haoran Chen
 * @Date 08/31/2016
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
		if (!(other instanceof Matrix)) { // make sure the Object we're comparing to is a Matrix
			return false;
		}
		Matrix matrix = (Matrix)other; // if the above was not true, we know it's safe to treat 'o' as a Matrix
		
		if (numRows != matrix.numRows || numColumns != matrix.numColumns) 
			return false;
		for (int i=0; i < numRows; i++) { 
			for (int j=0; j < numColumns; j++) {
				if (this.data[i][j] != matrix.data[i][j])
					return false;
			}
		}
		return true;		
	}
	
	@Override // instruct the compiler that we do indeed intend for this method to override the superclass' (Object) version
	public String toString()
	{
		String result = "";
		for (int i=0; i < numRows; i++) {
			for (int j=0; j < numColumns; j++) {
				if (j == numColumns - 1) {
					result = result + this.data[i][j] + " \n";
				}
				else 
				result = result + this.data[i][j] + " ";
			}
		}
		return result;
	}
	
	/**
	 * Returns the product of two matrix after doing multiplication
	 * 
	 * @return the product of two matrix
	 */
	public Matrix times(Matrix matrix)
	{
		if (this.numColumns != matrix.numRows) {
			return null;
		}
		Matrix result = new Matrix (new int [this.numRows][matrix.numColumns]);
		for(int i=0; i < this.numRows; i++) { 
			for(int j=0; j < matrix.numColumns; j++) {
				for(int k=0; k < this.numColumns; k++) {
					result.data[i][j] += this.data[i][k] * matrix.data[k][j];
				}
			}
		}
		return result;		
	}
	
	/**
	 * Returns the sum of two matrix after doing plus
	 * 
	 * @return the sum of two matrix
	 */
	public Matrix plus(Matrix matrix)
	{
		if (this.numColumns != matrix.numColumns || this.numRows != matrix.numRows) {
			return null;
		}
		Matrix result = new Matrix (new int [this.numRows][this.numColumns]);
		for(int i=0; i < this.numRows; i++) { 
			for(int j=0; j < this.numColumns; j++) {
				result.data[i][j] = this.data[i][j] + matrix.data[i][j];
			}
		}
		return result;		
	}
}
