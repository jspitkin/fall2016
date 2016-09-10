/**
 * Author Alan Hansing
 */
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
		
		//If the matrices are not the same dimensions they cannot be equal.
		if(this.numColumns != matrix.numColumns || this.numRows != matrix.numRows){
			return false;
		}
		
		//Check each location of each array for equality. If not equal, break and return false.
		for(int i = 0; i < this.numRows; i++){
			for(int j = 0; j < this.numColumns; j++){
				if(this.data[i][j] != matrix.data[i][j]){
					return false;
				}
			}
		}
		return true; 
	}
	
	@Override // instruct the compiler that we do indeed intend for this method to override the superclass' (Object) version
	public String toString()
	{
		String toReturn = "";
		for(int i = 0; i < this.numRows; i++){
			for(int j = 0; j < this.numColumns; j++){
				toReturn += this.data[i][j] + " ";
			}
			toReturn += '\n';
		}
		return toReturn; // placeholder
	}
	
	public Matrix times(Matrix matrix)
	{
		if(!(matrix instanceof Matrix)) { // make sure the Object we're comparing to is a Matrix
			return null;
		}
		
		if(this.numColumns != matrix.numRows){
			return null;
		}
		
		
		Matrix toReturn = new Matrix(new int[this.numRows][matrix.numColumns]);
		//Multiply locations of each Matrix to find the product.
		for(int i = 0; i < this.numRows; i++){
			for(int j = 0; j < matrix.numColumns; j++){
				int total = 0;
				for(int k = 0; k < this.numColumns; k++){
					total += this.data[i][k] * matrix.data[k][j];
				}
				toReturn.data[i][j] = total;
			}
		}
		return toReturn; 
	}
	
	public Matrix plus(Matrix matrix)
	{
		if(!(this instanceof Matrix)) { // make sure the Object we're comparing to is a Matrix
			return null;
		}
		if(this.numColumns != matrix.numColumns || this.numRows != matrix.numRows){
			return null;
		}
		
		Matrix toReturn = new Matrix(new int[this.numRows][this.numColumns]);
		//Make sure to add all corresponding locations and store in the same location in the new matrix.
		for(int i = 0; i < this.numRows; i++){
			for(int j = 0; j < this.numColumns; j++){
				toReturn.data[i][j] = this.data[i][j] + matrix.data[i][j];
			}
		}
		return toReturn; 	
		}
	
}
