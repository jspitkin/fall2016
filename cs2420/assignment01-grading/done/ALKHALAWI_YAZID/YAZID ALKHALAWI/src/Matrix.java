package assignment01;
/**
 * 
 * @author Yazid Alkhalawi
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
		Matrix matrix = (Matrix)other; // if the above was not true, we know it's safe to treat 'o' as a Matrix
		
		/*
		 * TODO: replace the below return statement with the correct code, you must return the correct value
		 * 			after determining if this matrix is equal to the input matrix
		 */
		boolean same=true; // boolean value to return
		for(int i=0;i<numRows;i++) // loop for rows to check the equality
		{
		   for(int j=0;j<numColumns;j++) // loop for coloumns
		   {
		       if(matrix.data[i][j]!=this.data[i][j]) // checking equality
		       {
		           same=false;   // if not equal then storing false in boolean variable
		           i=numRows;    // to break outer loop
		           j=numColumns; // to break inner loop
		       }
		  
		   }
		}
		return same; 
		}
	
	
	@Override // instruct the compiler that we do indeed intend for this method to override the superclass' (Object) version
	public String toString()
	{
		/*
		 * TODO: replace the below return statement with the correct code, you must return a String that represents this 
		 * 			matrix, as specified on the assignment page
		 */
		
		   StringBuffer sbf=new StringBuffer(); // string buffer to append elements of matrix
		   for(int i=0;i<numRows;i++) // loop through elements
		   {
		       for(int j=0;j<numColumns;j++) // loop through coloumns
		       {
		           sbf.append(data[i][j]+" "); // appending element
		       }
		       sbf.append(";"); // appending ; to represent a line is finished
		   }
		return sbf.toString();  // returning string using to string method of stringbuffer
		}
	
	
	public Matrix times(Matrix matrix)
	{
		/*
		 * TODO: replace the below return statement with the correct code, 
		 *  This function must check if the two matrices are compatible for multiplication, if not, return null.
		 *  If they are compatible, determine the dimensions of the resulting matrix, and fill it in with
		 *  the correct values for matrix multiplication
		 */
		   int[][] result = new int [numRows][matrix.numColumns]; // varible declation to store resultant matrix
		   if(numColumns == matrix.numRows) // checking if matrix is possible
		   {
		       result=new int[numRows][matrix.numColumns]; // assigning new array with resultent elements
		       int temp=0; // temp variable to calculate
		       for(int i=0;i<numRows;i++) //loop throu elements
		    	 
		       {
		          
		           for(int j=0;j<numColumns;j++) // loop through coloumns
		        	 
		           {
		               for(int k=0;k<matrix.numColumns;k++) // loop to performe calculation
		               {
		            	  
		               temp+= this.data[i][k]*matrix.data[k][j]; // calculation
		               }
		               result[i][j]=temp; // storing resulting element
		               temp=0; // temp to 0 to make calculation again
		           }
		          
		       }
		       return new Matrix(result); // returning object that has result
		   }
		    else throw new IllegalArgumentException("Rows from the first matrix : " + numColumns + " did not match the Columns from the second matrix" + matrix.numRows + ".");
             // else returning null and printing not possible
		   
		 
		       
		   
		  
		}
	public Matrix plus(Matrix matrix)
	{
		   int[][] result; // 2d array to store the result
		   if(matrix.numRows==numRows && matrix.numColumns==numColumns) // checking if rows and columns are equal
		   {
		       result=new int[numRows][numColumns]; // initializing the array with rows and columns
		       for(int i=0;i<numRows;i++) // loop through elements
		       {
		           for(int j=0;j<numColumns;j++)
		           {
		               result[i][j]=matrix.data[i][j]+data[i][j]; //performing calculation
		           }
		       }
		       return new Matrix(result); // returning matrix object with the resultant matrix
		       }
		   else throw new IllegalArgumentException("The size of the matries didn't match");
		}
	}
