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
		boolean equalsMatrix = false;
	
		if((numColumns == matrix.numColumns) && (numRows == matrix.numRows)){
			for(int i = 0; i < numRows; i++){
				for(int j = 0; j < numColumns; j++){
					if(matrix.data[i][j] == data[i][j]){
						 equalsMatrix = true;
					}else{
						equalsMatrix = false;
					}
				}
			}
		}else{
			equalsMatrix = false;
		}
		return equalsMatrix;
	}
	
	@Override // instruct the compiler that we do indeed intend for this method to override the superclass' (Object) version
	public String toString()
	{
		//Creates a string to use for the return statement
		String matrixOutput = "";
		
		//generates the matrix and assigns each cell to the string
		for(int i=0; i < numRows; i++) { 
			for(int j=0; j < numColumns; j++) {
				matrixOutput += data[i][j] + " ";
			}
			//starts new line every new row
			matrixOutput+= "\n";
		}
		return matrixOutput;

	}
	
	public Matrix times(Matrix matrix){
		Matrix timesMatrix = new Matrix(new int[numRows][matrix.numColumns]);
		
		if(numColumns == matrix.numRows){
			for(int i = 0; i < numRows; i++){
				for(int j = 0; j < matrix.numColumns; j++){
					for(int k = 0; k < numColumns; k++){
						timesMatrix.data[i][j] += data[i][k] * matrix.data[k][j];
					}
				}
			}
			return timesMatrix;
		}else
		return null;
		
	}
	
	public Matrix plus(Matrix matrix)
	{
	
		Matrix addMatrix = matrix;
		
		if((numColumns == matrix.numColumns) && (numRows == matrix.numRows)){
			for(int i = 0; i < numRows; i++){
				for(int j = 0; j < numColumns; j++){
					addMatrix.data[i][j] = data[i][j] + matrix.data[i][j];
				}
			}
			return addMatrix;
		}else{
			return null;
		}
	}
}
