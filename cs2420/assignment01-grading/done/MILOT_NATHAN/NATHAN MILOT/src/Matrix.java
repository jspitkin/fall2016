package assignment01;

/**
 * Basic Matrix class that performs some basic matrix math
 *
 * @author Nathan Milot, last modified Aug 29, 2016
 */
public class Matrix {
	int numRows;
	int numColumns;
	int data[][];
	
	/**
	 * Constructor with data for new matrix (automatically determines dimensions)
	 * @param data - the data for the new matrix
	 */
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
	
	/**
	 * Determines if this Matrix object is equivalent to another object
	 */
	@Override // instruct the compiler that we do indeed intend for this method to override the superclass' (Object) version
	public boolean equals(Object other)
	{		
		if(!(other instanceof Matrix)) { // make sure the Object we're comparing to is a Matrix
			return false;
		}
		Matrix matrix = (Matrix)other; // if the above was not true, we know it's safe to treat 'o' as a Matrix
		if(this.numRows != matrix.numRows || this.numColumns != matrix.numColumns){
			return false;
		}else{
			for(int i = 0; i < this.numRows; i++){
				for(int j = 0; j < this.numColumns; j++){
					if(this.data[i][j] != matrix.data[i][j])
						return false;
				}
			}
			return true;
		}
	}
	
	/**
	 * Prints out a string representation of the matrix object
	 */
	@Override // instruct the compiler that we do indeed intend for this method to override the superclass' (Object) version
	public String toString()
	{
		String result = "";
		for(int[] arr : this.data){
			for(int i : arr){
				result += i + " ";
			}
			result += "\n";
		}
		return result;
	}
	
	/**
	 * Multiplies this matrix times another matrix
	 * @param matrix - the other matrix
	 * @return the resulting matrix
	 */
	public Matrix times(Matrix matrix)
	{
		if(this.numColumns != matrix.numRows)
			return null;
		int[][] resultArr = new int[this.numRows][matrix.numColumns];
		//
		for (int i = 0; i < this.numRows; i++) {
            for (int j = 0; j < matrix.numColumns; j++) {
                for (int k = 0; k < this.numColumns; k++) {
                    resultArr[i][j] += this.data[i][k] * matrix.data[k][j];
                }
            }
        }
		
		return new Matrix(resultArr);
	}
	
	/**
	 * Adds this matrix to another matrix
	 * @param matrix - the other matrix
	 * @return the resulting matrix
	 */
	public Matrix plus(Matrix matrix)
	{	
		if(this.numRows != matrix.numRows || this.numColumns != matrix.numColumns)
			return null;
		int[][] resultArr = new int[this.data.length][this.data[0].length];
		for(int i = 0; i < this.data.length; i++){
			for(int j = 0; j < this.data[i].length; j++){
				resultArr[i][j] = this.data[i][j] + matrix.data[i][j];
			}
		}
		return new Matrix(resultArr); // placeholder
	}
}