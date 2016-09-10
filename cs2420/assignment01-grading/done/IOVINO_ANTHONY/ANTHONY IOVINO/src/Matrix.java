package assignment01;
/**
 * 
 * @author Anthony Iovino
 *
 */
public class Matrix {
	int numRows;
	int numColumns;  
	int data[][]; 
	
	public Matrix(int data[][])// Constructor with data for new matrix (automatically determines dimensions)
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
		
		boolean b = true;
		for(int i=0; i < numRows; i++) { 
			for(int j=0; j < numColumns; j++) {
				if(this.data[i][j] != matrix.data[i][j]){ 
					b=false;
					break;
				}
			}
		}
		return b;
	

		
		
		
		
		
		
		
		
		
		
		
		/*
		 * TODO: replace the below return statement with the correct code, you must return the correct value
		 * 			after determining if this matrix is equal to the input matrix
		 */
		
	}
	
	@Override // instruct the compiler that we do indeed intend for this method to override the superclass' (Object) version
	public String toString() {
		String s;
		int rowindex = 0; 
		s="";
		for(int i = 0;i< numRows;i++){
			for(int j= 0;j<numColumns;j++){
				s+=data[i][j] +" ";
			}
			rowindex++; 
			if(rowindex<(numRows))
				s+="\n";	
		}
		return s; 
	}
	
	public Matrix times(Matrix matrix){
		if(this.numRows!=matrix.numColumns) { //check for compatibility 
			return null; 
		}
		int[][] array = new int[this.numRows][matrix.numColumns]; //create array for temporary storage of the product with proper dimensions ( [2x3]+[3x4] => 2x4 )
		int sum = 0; //temporary sum variable 
		for(int i=0; i < this.numRows; i++){  // i for numRows // use three for loops 
			for(int j=0; j < matrix.numColumns; j++){ // j for numColumns 
				for(int k=0; k< this.numColumns;k++){
					sum = sum + this.data[i][k]*matrix.data[k][j];
				}
				array[i][j] = sum; 
				sum =0; 
			}
		}
		Matrix product = new Matrix(array);
		return product; 
	}

	public Matrix plus(Matrix matrix){
		if(this.numColumns!=matrix.numColumns || this.numRows != matrix.numRows){ //compatibility check 
			return null; 
		}
		
		int[][] array = new int[numRows][numColumns];//create new array for temp storage with proper dimensions 
	
		for(int i=0; i < numRows; i++){ //traverse the array row by row
			for(int j=0; j < numColumns; j++){//traverse columns 
				array[i][j]= this.data[i][j] + matrix.data[i][j];
			}
		}	
		Matrix sum = new Matrix(array);
		return sum;
	}
}
