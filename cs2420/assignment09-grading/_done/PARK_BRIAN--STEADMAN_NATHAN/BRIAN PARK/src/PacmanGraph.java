package assignment09;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


/**
 * This class creates a graph of a input file that contains 1 'S', 1 'G',
 * multiple 'X'(representing walls) and ' ' to indicate spaces that can be 
 * traversed and puts that into a 2D array of PacmanNodes. We then perform a 
 * Breadth First Search on our graph of Nodes to find the most efficient path 
 * between 'S' start and 'G' goal by traversing the ' 's to Goal. It then takes 
 * traverses backwards to Start, printing '.' to indicate the best path to goal 
 * from start. If more than one most efficient path exists, it is guaranteed to
 * print on of the most efficient routes, otherwise there is no route possible 
 * and it returns the .txt file unchanged under a different filename.
 * 
 * @authors Brian Park u0735732, Nathan Steadman u0738818
 *
 */
public class PacmanGraph {
	
	PacmanNode[][] grid; //2D array of nodes
	int  sRow; //Holder for row of Start node
	int sCol;  //Holder for column of Start node	
	int gRow;  //Holder for row of Goal node
	int gCol;  //Holder for column of Goal node
	int columns, rows = 0; //Create holder for columns and rows
	Queue<PacmanNode> queue = new LinkedList<PacmanNode>(); //Queue for PacmanNodes 
	
	public PacmanGraph(File f, String fileName){
	try {
		Scanner scan = new Scanner(f);		//Open scanner to read in new file
		String rowByCol = scan.nextLine();  //Store first Line in string
		String stringRows= "";
		String stringCols = "";
		boolean flag = true;				//Create flag for setting Rows and Columns
			for(int i = 0; i < rowByCol.length(); i ++){
				if (rowByCol.charAt(i) == ' '){	
					flag = false;
				}
				if(flag){
					stringRows += rowByCol.charAt(i); //First Integer in First Line is Rows
				}
				if(!flag && rowByCol.charAt(i)!= ' '){//Next Integer after space is Columns
					stringCols += rowByCol.charAt(i);
				}
			}										
			rows = Integer.parseInt(stringRows)+1;  //Add one to rows to account for Row and Column string row
			columns = Integer.parseInt(stringCols);	
		scan.close();							
		grid = new PacmanNode[rows][columns];	//Create a grid based on rows and columns from our scan of the first line
			int currentLine = 0;		//Create counter for Row
			Scanner s = new Scanner(f);	
			String line = "";
			
			
			while(s.hasNextLine()){
				line = s.nextLine();
		
				for(int i = 0; i < line.length(); i ++){
					PacmanNode cur = new PacmanNode();
					cur.setElement(line.charAt(i));
					grid[currentLine][i] = cur;
					
					//set left
					if( i > 0){	//Left exists as long as we are passed first char
						cur.setLeft(grid[currentLine][i-1]);
					}
					//sets right
					if( i > 0){ //Right exists as long as a left exists
						cur.left.setRight(cur); //go to left then set right as current
					}
					//set up
					if(currentLine>0){//Up exists as long as we are passed first line
						cur.setUp(grid[currentLine -1][i]);
					}
					//set down
					if(currentLine> 1){	//Down Exists if we are on line three or greater
						grid[currentLine-1][i].setDown(cur); //Go up a line and connect down as current
					}
					
						if(line.charAt(i) == 'S'){	//If we are at start 
							sCol = i;				//Set sCol to current column(i)
							sRow = currentLine;		//Set sRow to currentLine 
						}
						if(line.charAt(i) == 'G'){	//If we are at goal
							gCol = i;				//Set gCol to current column(i)
							gRow = currentLine;		//Set gRow to currentLine
						}
					
				}		
				currentLine++;	//Increment Line
			}
			breadthFirst(grid);	//Perform a breadth first search
			try{
				String writeLine= "";	
				FileWriter writer = new FileWriter(fileName);	
				
				int count= 0;	// counter for columns
				while (grid [0][count]!= null){			//While there are items in grids first line
					writer.write(grid[0][count].getElement());	//Write each element into our writer
					count ++;
				}
				writer.write("\n");		//Create new line in writer
				
				for(int i = 1; i < grid.length; i ++){	//While there is rows in our grid, starting on row 2 (0 indexing)
					for(int currentChar = 0; currentChar < grid[i].length; currentChar++){	//While there is columns in each row
						writeLine = writeLine + grid[i][currentChar].getElement();	//Write each line into our filewriter
					}
					writer.write(writeLine + "\n");	// add new line as we fill each line
					writeLine = "";					
				}
				writer.close();		
				s.close();			
			}
			catch(IOException e){ 
				System.err.println("Problem Writing File");
			}
			
		}
	catch (FileNotFoundException e) {	
			System.err.println("File Not Found!");
			e.printStackTrace();	
		}	
	}
	
	/**
	 * This method performs a Breadth First Search on a given 2d array of PacmanNodes.
	 * This is accomplished by starting at a start point held by class instances of integers
	 * row and column. The method then goes outward from this point to all adjacent spaces simultaneously
	 * that are not blocked, indicated by 'X', which can be UP DOWN LEFT OR RIGHT, setting previous on each
	 * to the previous node(Bread Crumbs) and setting each flag to visited. We continue to move through 
	 * the graph until we reach our goal node where we then follow our bread crumbs stored in each node, 
	 * setting the element from ' ' to '.' to indicate one of the most efficient paths if more than one is
	 * possible, else it stores the most efficient path.
	 * 
	 * @param grid2 - 2D array holding our Start, Goal, Walls and Spaces to navigate
	 */
	private void breadthFirst(PacmanNode[][] grid2){
		
		PacmanNode currentNode = grid2[sRow][sCol]; //Create node to allow traversal of our grid via nodes
		
		queue.add(currentNode);
		currentNode.setVisited(true); //Flip Starts visited flag to true
		while (queue.size() > 0){  //While nodes are in the queue
			
			currentNode = queue.remove(); //Dequeue and set as currentNode
			
			
			if(currentNode.getElement() == 'G'){ //If current nodes element is Goal 
				currentNode = currentNode.previous;	
				while(currentNode.getElement()!= 'S'){ //Cycle until currentNode element is Start
					currentNode.setElement('.');	//Change each element '.' to ' '
					currentNode = currentNode.previous;//Go to next previous
				}
				queue.clear();	//Clear queue, we have completed the task
				break;	//Break out of while loop
			}												//If there is a node to the left
			if(currentNode.left!= null && currentNode.left.getElement() != 'X'){  //thats not X aka wall
				if(!currentNode.left.visited()){	//Check if left has been visited							
					queue.add(currentNode.left);	//Add to queue if not visited yet
					currentNode.left.setPrevious(currentNode); //Set currentNode as lefts previous
					currentNode.left.setVisited(true);	//Set lefts visited to true
				}
			}												//If there is a node to the up
			if(currentNode.up!= null && currentNode.up.getElement() != 'X'){//Thats not a wall 
				if(!currentNode.up.visited()){		//Check if up has been visited
					queue.add(currentNode.up);		//Add to queue if unvisited
					currentNode.up.setPrevious(currentNode); //Set currentNode as up's previous
					currentNode.up.setVisited(true);	//Set ups visited flag to true
				}	
			}												//If there is a node to the right
			if(currentNode.right!= null && currentNode.right.getElement() != 'X'){//Thats not a wall
				if(!currentNode.right.visited()){	//Check if right has been visited
					queue.add(currentNode.right);	//Add to queue
					currentNode.right.setPrevious(currentNode);	//Set currentnode as rights previous
					currentNode.right.setVisited(true);	//Flip visited flag to true
				}	
			}												//If there is a node down 
			if(currentNode.down!= null && currentNode.down.getElement() != 'X'){//Thats not a wall
				if(!currentNode.down.visited()){	//Check if down has been visited
					queue.add(currentNode.down);	//Add down to queue
					currentNode.down.setPrevious(currentNode); //Set down's previous to currentnode
					currentNode.down.setVisited(true);	//Flip visited flag to true
				}		
			}
		}	
	}
	
	
	
	private class PacmanNode{
		char element;	//Char stored in each instance of Node
		PacmanNode left;//Pointer to the node to the LEFT of current Node
		PacmanNode right;//Pointer to the node to the RIGHT of current Node
		PacmanNode up;//Pointer to the node to the UP of current Node
		PacmanNode down;//Pointer to the node to the DOWN of current Node
		PacmanNode previous; //Pointer to the node we traversed from 
		boolean visited = false; //Flag for if the node has been visited
		
		public PacmanNode left(){ //Allows manipulation of LEFT Node
			return left;
		}
		public PacmanNode right() {//Allows manipulation of RIGHT Node
			return right;
		}
		public PacmanNode up() {//Allows manipulation of UP Node
			return up;
		}
		public PacmanNode down() {//Allows manipulation of DOWN Node
			return down;
		}
		public PacmanNode previous() {//Allows manipulation of PREVIOUS Node
			return previous;
		}
		public char getElement(){//Returns this nodes element
			return element;
		}
		public void setElement(char c){//Allows changing or setting of Element(CHAR)
			element = c;
		}
		public void setRight(PacmanNode n){//Allows changing or setting of Right Node
			right = n;
		}
		public void setLeft(PacmanNode n){//Allows changing or setting of Left Node
			left = n;
		}
		public void setUp(PacmanNode n){//Allows changing or setting of Up Node
			up = n;
		}
		public void setDown(PacmanNode n){//Allows changing or setting of Down Node
			down = n;
		}
		public boolean visited(){//visited flag for traversal
			return visited;
		}
		public void setVisited(boolean bool){ //Allows for changing or setting of visited flag
			visited = bool;
		}
		public void setPrevious(PacmanNode n){ //Allows changing or setting of Previous Node
			previous = n;
		}
		
		
	}
}