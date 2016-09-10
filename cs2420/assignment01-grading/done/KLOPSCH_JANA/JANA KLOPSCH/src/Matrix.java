package assignment01;

public class Matrix {
	int numRows;
	int numColumns;
	int data[][];
	
	/**
	 * Constructor with data for new matrix (automatically determines dimensions)
	 * @param data - size of matrix, and stored values within matrix
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
	 * Determines if two matrices are equal - that is, they are the same size, and contain the same values
	 * in the same places
	 */
	@Override // instruct the compiler that we do indeed intend for this method to override the superclass' (Object) version
	public boolean equals(Object other)
	{
		if(!(other instanceof Matrix)) { // make sure the Object we're comparing to is a Matrix
			return false;
		}
		Matrix matrix = (Matrix)other; // if the above was not true, we know it's safe to treat 'o' as a Matrix
		
		if(!(data.length == matrix.numRows)){ //Matrices must be the same size to be equal
			return false;
		}
		if(!(data[0].length == matrix.numColumns)){
			return false;
		}
		
		for(int i=0; i < matrix.numRows; i++) { // Ensure all values in each matrix are equal
			for(int j=0; j < matrix.numColumns; j++) {
				if(!(data[i][j] == matrix.data[i][j]))
					return false;
			}
		}
		return true;
	}
	
	
	/**
	 * Returns the matrix as a String, with the values in the matrix in order.  Each row is in
	 * its own line, and there is a space character following each data point.
	 */
	@Override // instruct the compiler that we do indeed intend for this method to override the superclass' (Object) version
	public String toString()
	{
		String matrixData = "";
		
		for(int i=0; i < numRows; i++) { 
			if(i>0){
				matrixData = matrixData + "\n";
			}
			for(int j=0; j < numColumns; j++) {
				matrixData = matrixData + data[i][j] + " ";
			}
		}
		
		return matrixData;
	}
	
	/**
	 * Computes the product of two matrices, of compatible sizes.
	 * @param matrix- the input matrix we multiply data matrix by
	 * @return- the product in matrix form if applicable, null if the two matrices are incompatible
	 * for multiplication
	 */
	public Matrix times(Matrix matrix)
	{
		if(!(numColumns == matrix.numRows)){ //must be true for matrix multiplication
			return null;
		}
		
		Matrix product = new Matrix(new int[numRows][matrix.numColumns]); //new matrix to hold values of product
		
		for(int i=0; i < numRows; i++){
			for(int l=0; l < matrix.numColumns; l++){
				for(int k=0; k < matrix.numRows;){
					for (int j=0; j < numColumns;){
						product.data[i][l] = product.data[i][l] + data[i][j]*matrix.data[k][l];
						j++;
						k++;
					}
				}
			}
		}
		return product;
	}
	
	/**
	 * Finds the sum of two matrices, of compatible size.
	 * @param matrix- the input matrix being added to the data matrix
	 * @return- the sum in matrix form if applicable, if the matrices are of differing sizes returns null
	 */
	public Matrix plus(Matrix matrix)
	{
		if(!(data.length == matrix.numRows)){  //Matrices must be the same size in order to add them together
			return null;
		}
		if(!(data[0].length == matrix.numColumns)){
			return null;
		}
		
		Matrix sum = new Matrix(new int[numRows][numColumns]); //new matrix to hold values of sum
		
		for(int i=0; i < numRows; i++){
			for(int j=0; j < numColumns; j++){
				sum.data[i][j] = (data[i][j] + matrix.data[i][j]);
			}
		}
		return sum;
	}
	
	
}
