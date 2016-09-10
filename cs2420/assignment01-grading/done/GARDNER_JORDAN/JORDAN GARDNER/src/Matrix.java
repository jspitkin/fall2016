package assignment01;
/**
 * 
 * @author Jordan Gardner u0566259 
 *
 */
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
		Matrix matrix = (Matrix)other;// if the above was not true, we know it's safe to treat 'o' as a Matrix
		
		if((matrix.numColumns!=numColumns)||(matrix.numRows!=numRows)){
			return false;//Checks the dimensions of the matrices to make sure they are the same
		}
		for(int i=0; i < numRows; i++) { 
			for(int j=0; j < numColumns; j++) {
				if(data[i][j]== matrix.data[i][j]){
					continue;
				}else{
					return false;
				}
			}
		}
		return true;
		
	}
	
	@Override // instruct the compiler that we do indeed intend for this method to override the superclass' (Object) version
	public String toString()
	{	String str = "";
		for(int i=0; i < numRows; i++) { 
			for(int j=0; j < numColumns; j++) {
				str=str+data[i][j]+" ";
			}
			if(i!=numRows-1){
				str=str+"\n";
			}
		}
		return str; 
	}
	
	public Matrix times(Matrix matrix)
	{	
		if(numColumns!=matrix.numRows){
			return null;
		}		 
		 Matrix C = new Matrix(new int[numRows][matrix.numColumns]);
		 
		for(int i=0; i < numRows; i++) { 
			for(int j=0; j < matrix.numColumns; j++) {
				for(int k=0; k < numColumns; k++) {
				 C.data[i][j]+= data[i][k]*matrix.data[k][j];
				}
			}
		}
		return C;
	}
	
	public Matrix plus(Matrix matrix)
	{
		if((numColumns!=matrix.numColumns)||(numRows!=matrix.numRows)){
			return null;
		}
		for(int i=0; i < numRows; i++) { 
			for(int j=0; j < numColumns; j++) {
				matrix.data[i][j] = data[i][j]+matrix.data[i][j];
			}
		}
		return matrix;
	}
	
}
