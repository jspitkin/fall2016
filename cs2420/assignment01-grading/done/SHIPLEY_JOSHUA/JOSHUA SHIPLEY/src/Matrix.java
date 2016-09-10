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
	
		boolean isEquals = false;
		
		//Checks to see if they are the same size
		if(matrix.numRows == numRows && matrix.numColumns == numColumns){
			isEquals = true;
			
			int rowPlace = 0, colPlace = 0;
			
			//As long as there's a row and column to check, go through each place and compare the values
			while(rowPlace < numRows && numColumns != 0){
				if(data[rowPlace][colPlace] != matrix.data[rowPlace][colPlace]){
					isEquals = false;
					break;
				}
				else{
					colPlace++;
					//At the end of each row, move down to the next
					if(colPlace == numColumns){
						colPlace = 0;
						rowPlace++;
					}
				}
			}
		}
		
		return isEquals;
	}
	
	@Override // instruct the compiler that we do indeed intend for this method to override the superclass' (Object) version
	public String toString()
	{
		String matrixString = "";
		
		for(int[] row: data){
			
			for(int col: row){
				matrixString += col + " ";
			}
			
			matrixString += '\n';
		}
		
		return matrixString; // placeholder
	}
	
	public Matrix times(Matrix matrix)
	{
		if(matrix == null){
			throw new NullPointerException();
		}
		
		//If the matrices are not compatible, returns null
		if(numColumns != matrix.numRows){
			return null;
		}
		
		int[][] product = new int[numRows][matrix.numColumns];
		
		//Goes through each row and multiplies it by the corresponding column appropriately, then adds it to the product array
		for(int row = 0; row < numRows; row++){
			for(int col = 0; col < matrix.numColumns; col++){
				for(int multiplyNumber = 0; multiplyNumber < numColumns; multiplyNumber++){
					product[row][col] += data[row][multiplyNumber] * matrix.data[multiplyNumber][col];
				}
			}
		}
		
		return new Matrix(product);
	}
	
	public Matrix plus(Matrix matrix)
	{
		if(matrix == null){
			throw new NullPointerException();
		}
		
		//If the matrices are not the same size, returns null
		if(matrix.numColumns != numColumns || matrix.numRows != numRows){
			return null;
		}
		
		int[][] sum = new int[numRows][numColumns];
		
		//Adds each corresponding value and inserts it into the sum array
		for(int rowPlace = 0; rowPlace < numRows; rowPlace++){
			for(int colPlace = 0; colPlace < numColumns; colPlace++){
				sum[rowPlace][colPlace] = matrix.data[rowPlace][colPlace] + data[rowPlace][colPlace];
			}
		}
		
		return new Matrix(sum);
	}
}
