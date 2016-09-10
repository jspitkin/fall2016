package assignment01;

/**
 * 
 *@author Joshua Homer
 *unid: u0915498
 *assignment: assignment01
 *Matrix.java
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
		if((this.numColumns != matrix.numColumns) || (this.numRows != matrix.numRows)){//ensuring they have the same dimensions
			return false;
		}
		else{
			for(int i=0; i<this.numRows;i++){
				for(int j=0; j<matrix.numColumns;j++){
					if(this.data[i][j] != matrix.data[i][j]){//checking every element and seeing if they match
						return false;
					}
				}
			}
		}
		return true; //if it gets down to here without returning false then they are equal
	}

	@Override // instruct the compiler that we do indeed intend for this method to override the superclass' (Object) version
	public String toString()
	{
		/*
		 * TODO: replace the below return statement with the correct code, you must return a String that represents this 
		 * 			matrix, as specified on the assignment page
		 */

		String lineHolder = ""; //a variable to store the output string
		for(int i=0; i < numRows; i++){
			for(int j=0; j < numColumns; j++){
				lineHolder += data[i][j];
				lineHolder += " ";
				if((j == numColumns - 1)){ //if we hit the end of a row
					lineHolder += "\n";
				}
			}
		}

		return lineHolder; //the completed string that represents our matrix
	}

	public Matrix times(Matrix matrix)
	{
		/*
		 * TODO: replace the below return statement with the correct code, 
		 *  This function must check if the two matrices are compatible for multiplication, if not, return null.
		 *  If they are compatible, determine the dimensions of the resulting matrix, and fill it in with
		 *  the correct values for matrix multiplication
		 */

		if(this.numColumns == matrix.numRows){//if the matrices can be multiplied
			Matrix resultant = new Matrix(new int [this.numRows][matrix.numColumns]);
			for(int i=0; i<this.numRows; i++){ //rows from matrix on left
				for(int j=0; j<matrix.numColumns; j++){ //columns from matrix on right
					for(int k=0; k<this.numColumns; k++){ //columns from matrix on left
						resultant.data[i][j] += this.data[i][k] * matrix.data[k][j];
					}
				}
			}
			return resultant;
		}
		else{ //matrices could not be multiplied
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

		if((this.numRows == matrix.numRows) && (this.numColumns == matrix.numColumns)){
			Matrix resultant = new Matrix(new int [numRows][numColumns]);
			for(int i=0; i<numRows; i++){
				for(int j=0; j<numColumns; j++){
					resultant.data[i][j] = this.data[i][j] + matrix.data[i][j];
				}
			}
			return resultant;
		}
		else{ //matrices could not be added
			return null;
		}
	}

	/**
	 * main method used for initial testing
	 * @param args
	 */
	public static void main(String[] args){
		Matrix threeByTwo, threeByTwo2, twoByThree, twoByTwoResult, simple, threeByThree;

		threeByTwo = new Matrix(new int[][]
				{{1, 2, 3},
			{2, 5, 6}});
		threeByTwo2 = new Matrix(new int[][]
				{{1, 2, 3},
			{2, 5, 6}});
		twoByThree = new Matrix(new int[][]
				{{4, 5},
			{3, 2},
			{1, 1}});
		simple = new Matrix(new int[][]
				{{1,2},
			{3,4}});
		threeByThree = new Matrix(new int [][]{
			{1, 2, 3},
			{4, 5, 6},
			{7, 8, 9}});

		System.out.println(threeByThree);

		if(threeByTwo.equals(threeByTwo2)){
			System.out.println("Yay");
		}

		System.out.println(threeByTwo.plus(threeByTwo2).toString());

		System.out.println(threeByTwo.times(twoByThree));
	}
}
