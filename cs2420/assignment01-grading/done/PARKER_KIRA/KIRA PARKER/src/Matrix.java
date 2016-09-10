package assignment01;

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
		if(numRows != matrix.numRows || numColumns != matrix.numColumns) //check if dimensions match
			return false;
		for(int row=0; row<numRows; row++){
			for(int col=0; col<numColumns; col++){
				if(data[row][col] != matrix.data[row][col]) //check if all entries in the matrix match
					return false; //if they don't match return false
			}
		}
		return true;
	}
	
	@Override // instruct the compiler that we do indeed intend for this method to override the superclass' (Object) version
	public String toString()
	{
		String formatted = "";
		for(int row=0; row<numRows; row++){ //loops through matrix and adds number to string, followed by a space
			for(int col=0; col<numColumns; col++){
				formatted += data[row][col] + " ";
			}
			formatted += "\n"; //puts a new line at the end of each row
		}
		return formatted;
	}
	
	/**
	  * @param matrix -- input 2d array of integers
	  * @return the resulting matrix when the current matrix is multiplied by the input matrix. If the 
	  * two matrices are incompatible for multiplication, the method returns null
	  */
	public Matrix times(Matrix matrix) //right multiply current matrix by this matrix
	{
		if(numColumns != matrix.numRows) //not compatible for matrix multiplication
			return null;
		int[][] array = new int[numRows][matrix.numColumns]; //array with correct dimensions to store data for matrix
		int sum=0;
		for(int row=0; row<numRows; row++){ //row to move across in first matrix, also row in array where sum will end up
			for(int col=0; col<matrix.numColumns; col++){ //column to move down in second matrix, also column in array where sum will end up
				for(int i=0; i<numColumns; i++){ //loops through numbers that will be multiplied and then added together (across row/down column)
					sum+= data[row][i]*matrix.data[i][col];
				}
				array[row][col] = sum; //put sum in appropriate spot in array
				sum=0;
			}
		}
		return new Matrix(array);
	}
	
	/**
	  * @param matrix -- input 2d array of integers
	  * @return the resulting matrix when the current matrix is added to the input matrix. If the 
	  * two matrices are different dimensions, the method returns null
	  */
	public Matrix plus(Matrix matrix)
	{
		if(numRows != matrix.numRows || numColumns != matrix.numColumns) //check if dimensions match
			return null; //if they don't match return null
		int[][] array = new int[numRows][numColumns]; //array to store data for the matrix
		for(int row=0; row<numRows; row++){
			for(int col=0; col<numColumns; col++){
				array[row][col] = data[row][col] + matrix.data[row][col]; //add corresponding entries of the two matrice and put in new array
			}
		}
		return new Matrix(array);
	}
}
