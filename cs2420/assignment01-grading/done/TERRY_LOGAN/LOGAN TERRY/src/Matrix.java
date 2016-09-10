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
		
		if(matrix.numRows != numRows || matrix.numColumns != numColumns)
			return false;
		for(int y = 0; y < numRows; y++)
			for(int x = 0; x < numColumns; x++)
				if(matrix.data[y][x] != data[y][x])
					return false;
		
		return true;
	}
	
	@Override // instruct the compiler that we do indeed intend for this method to override the superclass' (Object) version
	public String toString()
	{
		String out = "";
		for(int y = 0; y < numRows; y++){
			for(int x = 0; x < numColumns; x++)
				out += data[y][x] + " ";
			out += "\n";
		}
		
		return out;
	}
	
	public Matrix times(Matrix matrix)
	{
		if(numColumns != matrix.numRows)
			return null;
		int[][] out = new int[numRows][matrix.numColumns];
		for(int y = 0; y < out.length; y++)
			for(int x = 0; x < out[y].length; x++)
				for(int i = 0; i < numColumns; i++)
					out[y][x] += data[y][i] * matrix.data[i][x];
		
		return new Matrix(out);
	}
	
	public Matrix plus(Matrix matrix)
	{
		if(matrix.numRows != numRows || matrix.numColumns != numColumns)
			return null;
		int[][] out = new int[numRows][numColumns];
		for(int y = 0; y < numRows; y++)
			for(int x = 0; x < numColumns; x++)
				out[y][x] = matrix.data[y][x] + data[y][x];
		
		return new Matrix(out);
	}
}
