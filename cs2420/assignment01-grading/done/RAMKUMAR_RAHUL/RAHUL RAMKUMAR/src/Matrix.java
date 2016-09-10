package assignment01;

public class Matrix {
	int numRows;
	int numColumns;
	int data[][];

	// Constructor with data for new matrix (automatically determines dimensions)
	public Matrix(int data[][]){
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
     * This function returns a boolean indicating whether the matrices are equal or not.
     * @param other
     * @return boolean
     */
	@Override // instruct the compiler that we do indeed intend for this method to override the superclass' (Object) version
	public boolean equals(Object other){
		if(!(other instanceof Matrix)) { // make sure the Object we're comparing to is a Matrix
			return false;
		}
		Matrix matrix = (Matrix)other; // if the above was not true, we know it's safe to treat 'o' as a Matrix

		if(this.numColumns == matrix.numColumns && this.numRows == matrix.numRows){
			for(int i = 0; i < numRows; i++){
				for(int j = 0; j < numColumns; j++){
					if(this.data[i][j] != matrix.data[i][j]){
						return false; // return on first found cell mismatch
					}
				}
			}
			return true; // if this is reached then every cell of the matrix must match
		}
		return false; // return false because not even matrix size is equal
	}

    /**
     * This function returns a string representation of the matrix object.
     * @return String
     */
	@Override // instruct the compiler that we do indeed intend for this method to override the superclass' (Object) version
	public String toString(){
		StringBuilder outputString = new StringBuilder(); // StringBuilder is faster than String concatenation ( with + ) and String.concat
		for(int i = 0; i < numRows; i++){
			for(int j = 0; j < numColumns; j++){
				outputString.append(data[i][j]);
				if(j == numColumns - 1){
					outputString.append(" \n"); // added space and newline to end of line
				}
				else{
					outputString.append(" ");
				}
			}
		}

		return outputString.toString(); // placeholder
	}

    /**
     *  This function checks if the two matrices are compatible for multiplication, if not, return null.
     *  If they are compatible, determine the dimensions of the resulting matrix, and fill it in with
     *  the correct values for matrix multiplication.
     * @param matrix
     * @return Matrix object
     */
	public Matrix times(Matrix matrix){
		/*

		 */

		if(this.numColumns == matrix.numRows){
			int[][] outputData = new int[this.numRows][matrix.numColumns];
            for(int i = 0; i < this.numRows; i++){
                for(int j = 0; j < matrix.numColumns; j++){
					for(int k = 0; k < this.numColumns; k++){
						outputData[i][j] += this.data[i][k] * matrix.data[k][j];
					}
                }
            }
            return new Matrix(outputData);
		}
		return null; // placeholder
	}

    /**
     * This function checks if the two matrices are compatible for addition, if not, return null.
     * If they are compatible, return the matrix representing the sum of the input matrices.
     * @param matrix
     * @return Matrix object
     */
	public Matrix plus(Matrix matrix){
        if(this.numColumns == matrix.numColumns && this.numRows == matrix.numRows){
            for(int i = 0; i < matrix.numRows; i++){
                for(int j = 0; j < matrix.numColumns; j++){
                    matrix.data[i][j] += this.data[i][j]; // Java is pass-by-value, why use a new matrix?
                }
            }
            return matrix;
        }
		return null; // dimensions aren't balanced so return null
	}
}
