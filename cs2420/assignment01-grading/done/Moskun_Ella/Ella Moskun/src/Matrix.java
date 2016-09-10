package assignment01;

import java.util.Arrays;

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

		boolean equalDimensions = (this.numRows == matrix.numRows) && (this.numColumns == matrix.numColumns);
		return equalDimensions && Arrays.deepEquals(this.data, matrix.data);
		
	}
	
	@Override // instruct the compiler that we do indeed intend for this method to override the superclass' (Object) version
	public String toString()
	{
		String result = "";
		for (int i = 0; i < numRows; i++)
		{
			for (int j = 0; j < numColumns; j++)
			{
				result += (data[i][j] + " ");
			}
			result += "\n";
		}
		return result;
	}
	
	public Matrix times(Matrix matrix)
	{
		if (this.numColumns == matrix.numRows)  //check for compatible dimensions
		{
			//determine dimensions
			int newRows = this.numRows;
			int newColumns = matrix.numColumns;
			
			//fill data for return matrix
			int[][] newData = new int[newRows][newColumns];
			for (int i = 0; i < newRows; i++)
			{
				for (int j = 0; j < newColumns; j++)
				{
					for (int k = 0; k < this.numColumns; k++)
					{
						newData[i][j] += this.data[i][k] * matrix.data[k][j];
					}
				}
			}
			
			//return matrix created with the filled data
			return new Matrix(newData);
		}
		else
		{
			return null; //return null for incompatible dimensions
		}
	}
	
	public Matrix plus(Matrix matrix)
	{
		if (this.numRows == matrix.numRows && this.numColumns == matrix.numColumns) //check for compatible dimensions
		{
			//fill data for return matrix
			int[][] newData = new int[numRows][numColumns];
			for (int i = 0; i < this.numRows; i++)
			{
				for (int j = 0; j < this.numColumns; j++)
				{
					newData[i][j] = this.data[i][j] + matrix.data[i][j]; 
				}
			}
			
			//rectun matrix created with the filled data
			return new Matrix(newData);
		}
		else
		{
			return null; //return null for incompatible dimensions
		}
	}
}
