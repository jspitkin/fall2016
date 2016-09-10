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
	
	// Returns the number of rows in this matrix
	public int getNumberRows() {
		return numRows;
	}
	
	// Returns the number of columns in this matrix
	public int getNumberColumns() {
		return numColumns;
	}
	
	// Returns the value in the specified row and column, 0 indexed
	public int getValue(int r, int c) {
		return data[r][c];
	}
	
	@Override // instruct the compiler that we do indeed intend for this method to override the superclass' (Object) version
	public boolean equals(Object other)
	{
		if(!(other instanceof Matrix)) { // make sure the Object we're comparing to is a Matrix
			return false;
		}
		
		Matrix matrix = (Matrix)other; // if the above was not true, we know it's safe to treat "other" as a Matrix
		
		if (this.toString().equals(matrix.toString()))
			return true;
		return false;
	}
	
	@Override // instruct the compiler that we do indeed intend for this method to override the superclass' (Object) version
	public String toString()
	{
		String string = "";
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				string += data[i][j] + " ";
				if (j == numColumns - 1)
					string += "\n";
			}
		}
		return string;
	}
	
	public Matrix times(Matrix matrix)
	{
		if (numColumns != matrix.getNumberRows())
			return null;
		
		int newRows = numRows;
		int newColumns = matrix.getNumberColumns();
		int[][] resultData = new int[newRows][newColumns];
		
		for (int i = 0; i < newRows; i++) {
			for (int j = 0; j < newColumns; j++) {
				int value = 0;
				for (int k = 0; k < numColumns; k++) {
					value += data[i][k] * matrix.getValue(k, j);
				}
				resultData[i][j] = value;
			}
		}
		
		return new Matrix(resultData); // placeholder
	}
	
	public Matrix plus(Matrix matrix)
	{
		if (numRows != matrix.getNumberRows() || numColumns != matrix.getNumberColumns())
			return null;
		
		int[][] resultData = new int[numRows][numColumns];
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				resultData[i][j] = data[i][j] + matrix.getValue(i, j);
			}
		}
		
		return new Matrix(resultData);
	}
}
