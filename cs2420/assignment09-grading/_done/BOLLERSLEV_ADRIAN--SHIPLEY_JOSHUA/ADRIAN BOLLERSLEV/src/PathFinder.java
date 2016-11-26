package assignment09;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;


/**
 * @author Adrian Bollerslev
 * u1115021
 * Joshua Shipley
 * u919708
 *
 *This class finds the shortest path from S-G in a text file
 * X = wall
 *  ' ' = free space
 *  '.' = path
 */
public class PathFinder {
	
	public static int rows, cols;
	public static char[][] grid = null;
	public static int[][] numsteps = null;
	public static int startR;
	public static int startC;
	public static int endR;
	public static int endC;
	
	/**
	 * Reads an input file
	 * Finds the shortest path from S to G 
	 * Outputs the file with the path represented by multiple '.'
	 * @param infile input filename
	 * @param outfile output filename
	 */
	public static void solveMaze(String infile, String outfile){
		try{
			BufferedReader input = new BufferedReader(new FileReader(infile));
			String[] dimensions = input.readLine().split(" ");
			rows = Integer.parseInt(dimensions[0]);
			cols = Integer.parseInt(dimensions[1]);
			//initialize grid for maze and a boolean to determine if we have checked the place
			grid = new char[rows][cols];          
	        numsteps = new int[rows][cols];  
	        //traverse every element through each row and each col for each row
	        for(int r = 0; r < rows; r++){
	            for(int c = 0; c < cols; c++){
	            	//save the char in the grid
	                grid[r][c] = (char) input.read();
	                //if start or end is found mark it as such
	                if(grid[r][c]=='S'){
	                	startR=r;
	                	startC=c;
	                }
	                else if(grid[r][c]=='G'){
	                	endR=r;
	                	endC=c;
	                }
	                //set initial value to -1 for each occasion
	                //(we have not checked distance yet)
	                numsteps[r][c] = -1;
	             }
	              //return key after the line has been checked
	              input.readLine();
	           }
	           //close the reader
	           input.close();
		}
		//specific error messages depending on the specified error
		catch(FileNotFoundException e){
			System.out.println("Cannot find specified input file");
		}
		catch (IOException e)
        {
            System.out.println("Error reading specified input file");
        }
		//initialize starting point with 0 steps
	    numsteps[startR][startC] = 0;
		findRoute();
	    finalRoute(numsteps);
	    
		//begin outputting the maze in the same format
		
		try(PrintWriter output = new PrintWriter(new FileWriter(outfile))) {
			//start with the dimensions of the grid on the first line
			output.println(rows + " " + cols);
		    //traverse through each row 
		    for(int r = 0; r < rows; r++){
		    	//traverse through each column for each row
		    	char[] lin = new char[cols];
		        for(int c = 0; c < cols; c++){
		        	//save every character in a char array
		        	lin[c] = grid[r][c];
		        }
		        //join the characters in a single string
		        output.println(new String(lin));
		    }
		} 
		catch (IOException e) {
			System.out.println("Error outputting from file.");
			e.printStackTrace();
		}
		
	}
	/**
	 * This method is used to find the shortest path from S to G
	 * It also records the number of steps on the possible path to make
	 * backtracking the path easier
	 */
	private static void findRoute(){
	    //initialize queue and add starting point
	    Queue<int[]> nextP = new LinkedList<int[]>();
	    nextP.add(new int[]{startR,startC});
	    //while loop to count each path
	    while(!nextP.isEmpty() && numsteps[endR][endC] == -1){
	    	//save current location to be analyzed
	    	int[] pnt = nextP.remove();
	        int cRow = pnt[0];
	        int cCol = pnt[1];
	        //save number of steps
	        int cSteps = numsteps[cRow][cCol];
	        //if you can currently move up
	        //and the point has not yet been traversed
	        //do so and add that point to queue
	        if((cRow > 0)&&(numsteps[cRow - 1][cCol] == -1)){
	            //make sure cell is not a wall
	        	if(grid[cRow - 1][cCol] != 'X'){
	        		//save number of steps and add to queue
	        		numsteps[cRow - 1][cCol] = cSteps + 1;
	        		nextP.add(new int[]{cRow-1,cCol});
	            }
	            else{  //if wall mark with -1
	              numsteps[cRow-1][cCol] = -1;  
	            }
	        
	          
	        }
	        
	        //if you can currently move down
	        //and the point has not yet been traversed
	        //do so and add that point to queue
	        if((cRow < rows - 1)&&(numsteps[cRow + 1][cCol] == -1)){
	        	//make sure cell is not a wall
	            if(grid[cRow + 1][cCol] != 'X'){
	            	//save number of steps and add to queue
	            	numsteps[cRow + 1][cCol] = cSteps + 1;
	            	nextP.add(new int[]{cRow+1,cCol});
	            }
	            else{  //otherwise it's a wall - so mark it with a -1
	            	numsteps[cRow+1][cCol] = -1;  
	            }
	          
	        }
	    	//if you can currently move right
	    	//and the point has not yet been traversed
	    	//do so and add that point to queue
	        if((cCol < cols - 1)&&(numsteps[cRow][cCol + 1] == -1)){
	        	//make sure cell is not a wall
	            if(grid[cRow][cCol + 1] != 'X'){
	            	//save number of steps and add to queue
	            	numsteps[cRow][cCol + 1] = cSteps + 1;
	            	nextP.add(new int[]{cRow,cCol+1});
	            }     
	            else{  //otherwise it's a wall - so mark it with a -1
	              numsteps[cRow][cCol+ 1] = -1;  
	            }
	          
	        }
	    	//if you can currently move left
	    	//and the point has not yet been traversed
	    	//do so and add that point to queue
	        if((cCol > 0)&&(numsteps[cRow][cCol - 1] == -1)){
	        	//make sure cell is not a wall
	            if(grid[cRow][cCol - 1] != 'X'){
	            	//save number of steps and add to queue
	            	numsteps[cRow][cCol - 1] = cSteps + 1;
	             	nextP.add(new int[]{cRow,cCol-1});
	            }
	            else{  //otherwise it's a wall - so mark it with a -1
	              numsteps[cRow][cCol - 1] = -1;  
	            }
	          
	        }   
	      }
	}
	/**
	 * This method backtracks from the end place and replaces the appropriate
	 * ' ' with '.'
	 * @param steps the number of steps from start for each cell in the grid 
	 */
	public static void finalRoute(int[][] steps){
		//Similar process as before but moving backwards
		//save current location
	    int currRow = endR;     
	    int currCol = endC;     
	    int stepsFromS;
	    //We have already recorded how many steps are necessary
	    //run through once for minimum step count
	    for(int i = 1; i < steps[endR][endC]; i++){
	    	stepsFromS = steps[currRow][currCol];
	    	//look up to see if solution is this direction
	    	if(currRow > 0 && steps[currRow - 1][currCol] == stepsFromS - 1 ){
	    		grid[currRow - 1][currCol] = '.';
	    		currRow--;
	        }
	        //look down to see if solution is this direction
	        else if(currRow < rows - 1 && steps[currRow + 1][currCol] == stepsFromS - 1 ){
	          grid[currRow + 1][currCol] = '.';
	          currRow++;
	        }
	        //look right to see if solution is this direction
	        else if(currCol < cols - 1 && steps[currRow][currCol + 1] == stepsFromS - 1 ){
	          grid[currRow][currCol + 1] = '.';
	          currCol++;
	        }
	        //look left to see if solution is this direction
	        else if(currCol > 0 && steps[currRow][currCol - 1] == stepsFromS - 1 ){
	          grid[currRow][currCol - 1] = '.';
	          currCol--;
	        }
	      }
	}
}