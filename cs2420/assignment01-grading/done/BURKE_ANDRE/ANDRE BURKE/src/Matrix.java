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
		for(int i = 0; i < numRows; i++) { 
			for(int j = 0; j < numColumns; j++) {
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
		
		// instantiate a new Matrix Object using this data
		Matrix compareMatrix = new Matrix(data);
		
		// compare the arg matrix with the new matrix Object
		// return the result
		return (matrix == compareMatrix);
	}
	
	@Override // instruct the compiler that we do indeed intend for this method to override the superclass' (Object) version
	public String toString()
	{
		/*
		 * TODO: replace the below return statement with the correct code, you must return a String that represents this 
		 * 			matrix, as specified on the assignment page
		 */
		
		// this is the empty string used to return the printed matrix
		String printedArray = "";
		
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				// after each iteration of i and j add a space between each character
				printedArray += data[i][j] + " ";
			}
			// after each iteration of i enter add a new line to the string
			printedArray += "\n";
		}
		//System.out.print(printedArray);
		return printedArray;
	}
	
	public Matrix times(Matrix matrix)
	{
		/*
		 * TODO: replace the below return statement with the correct code, 
		 *  This function must check if the two matrices are compatible for multiplication, if not, return null.
		 *  If they are compatible, determine the dimensions of the resulting matrix, and fill it in with
		 *  the correct values for matrix multiplication
		 */
		
		Matrix left = new Matrix(data); 
		Matrix right = (Matrix) matrix;
		
		// two int values will be used to compare if the matrices have the correct dimensions or not
		int leftColumns = left.data[0].length;
		int leftRows = left.data.length; 
		int rightRows = right.data.length;
		int rightColumns = right.data[0].length;
		
		int[][] result = new int[leftRows][rightColumns]; 
		
		if (leftColumns == rightRows) {
			for (int i = 0; i < leftRows; i++) {
				for (int j = 0; j < rightColumns; j++) {
					for (int k = 0; k < leftColumns; k++) {
						result[i][j] += left.data[i][k] * right.data[k][j];
					}
				}
			}
		    return new Matrix(result);
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
		Matrix left = new Matrix(data); 
		Matrix right = (Matrix) matrix;
		
		// two int values will be used to compare if the matrices have the correct dimensions or not
		int leftColumns = left.data[0].length;
		int leftRows = left.data.length; 
		int rightRows = right.data.length;
		int rightColumns = right.data[0].length; 
		
		// create new int array to hold the values of the two added arrays
		int[][] result = new int[leftRows][rightColumns]; 
		
		if (leftColumns == rightRows && leftRows == rightColumns) {
			for (int i = 0; i < leftRows; i++) {
				for (int j = 0; j < rightColumns; j++) {
					result[i][j] = left.data[i][j] + right.data[i][j];
				}
			}
		    return new Matrix(result);
		} else {
			return null;
		}
	}
}
