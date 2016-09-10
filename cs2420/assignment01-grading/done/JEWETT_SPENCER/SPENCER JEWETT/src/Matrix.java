package assignment01;
/**
 * A simple class to do the following to matrices
 * -Compare and tell if two matrices are equal
 * -multiply matrices
 * -add matrices
 * -print a matrix as a formatted string
 * 
 * @author cs2420 Teaching staff and Spencer Jewett
 * last update: 8/30/2016
 *
 */
public class Matrix {
	int numRows;
	int numColumns;
	int data[][];
	int delta[][];
	
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
	
	/**
	 * checks to see if two matrices are equal
	 */
	@Override // instruct the compiler that we do indeed intend for this method to override the superclass' (Object) version
	public boolean equals(Object other)
	{
		if(!(other instanceof Matrix)) { // make sure the Object we're comparing to is a Matrix
			return false;
		}
		Matrix matrix = (Matrix)other; // if the above was not true, we know it's safe to treat 'o' as a Matrix
		
		if(numRows != matrix.numRows || numColumns != matrix.numColumns){
			return false;
		}
		
		for(int i = 0; i<numRows; i++){
			for(int j=0; j<numColumns; j++){
				if (data[i][j] != matrix.data[i][j]){
					return false;
				}
			}
		}
		return true; 
	}

	/**
	 * puts the data of a matrix into a formatted string
	 */
	@Override // instruct the compiler that we do indeed intend for this method to override the superclass' (Object) version
	public String toString()
	{
		String result = "";
		
		for(int i=0; i < numRows; i++) { 
			for(int j=0; j < numColumns; j++) {
				result += data[i][j] + " ";
			}
			result += "\n";
		}
		return result; 
	}
	
	/**
	 * Multiplies matrices if possible
	 * @param matrix - the matrix to multiply
	 * @return a matrix with the solution if possible, if not returns null
	 */
	public Matrix times(Matrix matrix){		
		if (numColumns != matrix.numRows){
			return null;
		}
		
		else{
			delta = new int[numRows][matrix.numColumns];
			for(int i=0; i < numRows; i++) { 
				for(int j=0; j < matrix.numColumns; j++) {
					for(int k = 0; k < numColumns; k++){
						delta[i][j] += data[i][k] * matrix.data[k][j]; 
					}	
				}
			}
			Matrix solution = new Matrix(delta);
			return solution;
		}
	}
	
	/**
	 * Adds the two matrices together if they are compatible
	 * @param matrix - the matrix to be added
	 * @return - a new matrix of the solution
	 */
	public Matrix plus(Matrix matrix)
	{
		if(numColumns != matrix.numColumns || numRows != matrix.numRows){
			return null;
		}
		else{
			delta = new int[numRows][numColumns];
			delta = data;
			for(int i=0; i<numRows; i++){
				for(int j=0; j<numColumns; j++){
					delta[i][j] += matrix.data[i][j];
				}
			}
		}
		Matrix solution = new Matrix (delta);
		return solution;
	}
	
}
