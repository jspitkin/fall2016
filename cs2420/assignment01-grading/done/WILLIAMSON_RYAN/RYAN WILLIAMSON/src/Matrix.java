package assignment01;

/**
 * This class contains a variety of methods for working with matrices such as: toString (override), multiplication, addition,
 * getters, setters and an equals (override) method.
 * @author Ryan-KW
 * Last Updated: Aug 31, 2016
 *
 */
public class Matrix {
	int numRows;
	int numColumns;
	int data[][];
	int value;

	// Constructor with data for new matrix (automatically determines dimensions)
	/**
	 * This is the constructor for the Matrix class.
	 * Be sure to call .data after any Matrix object in order to get access to the [][].
	 * @param data - Must pass in a 2d array.
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
	 * Sets a specific value at the desired location.
	 * @param row index in array.
	 * @param column index in array.
	 * @param new value.
	 */
	public void setValue(int numRows, int numColumns, int value){

		data[numRows][numColumns] = value;
	}

	/**
	 * Returns the value at the specified index.
	 * @param row index.
	 * @param column index.
	 * @return value at specific index.
	 */
	public int getValue(int numRows, int numColumns){

		return data[numRows][numColumns];
	}

	/**
	 * This method overrides the equals method from object in order to compare matrices.
	 * @param Any object can be passed; although it should be an object of the Matrix class or
	 * the method will return null.
	 *  @return returns true or false. True if equal, false otherwise.
	 */
	public boolean equals(Object other)
	{
		// make sure the Object we're comparing to is a Matrix
		if(!(other instanceof Matrix)) { 
			return false;
		}
		// if the above was not true, we know it's safe to treat other as a Matrix
		Matrix matrix = (Matrix)other; 

		for(int i = 0; i < numRows; i++){
			for(int j = 0; j < numColumns; j++){
				if(this.data[i][j] == matrix.data[i][j]){

					return true;
				}
			}
		}

		return false; 
	}

	/**
	 * This method overrides the original toString method from the Object class in order to
	 * print out all of the numbers found within the matrix by row and indenting to the next line at the end
	 * of each column. A space is found after every number; even prior to the indent \n.
	 * Ex: 
	 * 5 5 5 
	 * 3 3 3 
	 * 2 2 2 
	 */
	public String toString()
	{
		// Creates an empty string to hold values from the 2d array.
		String holder = "";

		for(int i = 0; i < numRows; i++){
			for(int j = 0; j < numColumns; j++){
				if(j < numColumns - 1){
					holder += this.data[i][j] + " ";
				}
				else{
					holder += this.data[i][j] + " "+ "\n";
				}
			}
		}
		return holder; 
	}

	/**
	 * This method will perform multiplication of matrices. If you are unfamiliar with the process of 
	 * matrix multiplication, a refresher is found here: http://www.mathwarehouse.com/algebra/matrix/multiply-matrix.php
	 * 
	 * @param matrix - matrix passed into method. **This is the right hand matrix (2nd).
	 * @return New matrix after multiplication.
	 */
	public Matrix times(Matrix matrix){

		//Checks to insure the matrices are compatible for multiplication.
		if(this.numColumns != matrix.numRows){
			return null;
		}
		//New matrix with proper dimensions to hold new values.
		Matrix multiMatrix = new Matrix(new int[this.numRows][matrix.numColumns]);
		int sum = 0;
		{
			//loops through rows of left hand matrix.
			for(int i = 0; i < this.numRows; i++){
				//loops through columns of right hand matrix.
				for(int j = 0; j < matrix.numColumns; j++){
					//loops through columns of left hand matrix.
					for(int k = 0; k < this.numColumns; k++ ){

						sum += this.getValue(i, k) * matrix.getValue(k, j);
					}
					// sets value
					multiMatrix.setValue(i, j, sum);

					// resets sum
					sum = 0;
				}
			}
			return multiMatrix;
		}
	}
	/**
	 * This method adds matrices of the same dimensions.
	 * Matrices cannot be added if their dimensions don't match up.
	 * @param matrix - A matrix pass into the method.
	 * @return - Left hand matrix + right hand matrix = new matrix of same dimensions.
	 */

	public Matrix plus(Matrix matrix)
	{
		//Checks to insure that both matrices are the exact same.
		if(this.numRows != matrix.numRows && this.numColumns != matrix.numColumns){
			return null;
		}

		// New matrix to hold new values.
		Matrix addMatrix = new Matrix(new int[this.numRows][this.numColumns]);

		for(int i = 0; i < numRows; i++){
			for(int j = 0; j < numColumns; j++){
				addMatrix.data[i][j] = this.data[i][j] + matrix.data[i][j];
			}
		}
		return addMatrix; 
	}

}
