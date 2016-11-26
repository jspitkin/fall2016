package assignment11;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * Represents a priority queue of generically-typed items. 
 * The queue is implemented as a min heap. 
 * The min heap is implemented implicitly as an array.
 * 
 * @author Jacob Brown (u0583647)
 */
@SuppressWarnings("unchecked")
public class PriorityQueue<AnyType> {

	// Size of the queue.
	private int currentSize;

	// Initialize array.
	private AnyType[] array;

	// Initialize a comparator.
	private Comparator<? super AnyType> cmp;

	/**
	 * Constructs an empty priority queue. Orders elements according
	 * to their natural ordering (i.e., AnyType is expected to be Comparable)
	 * AnyType is not forced to be Comparable.
	 */
	public PriorityQueue() {
		currentSize = 0;
		cmp = null;
		array = (AnyType[]) new Object[10];
	}

	/**
	 * Construct an empty priority queue with a specified comparator.
	 * Orders elements according to the input Comparator (i.e., AnyType need not
	 * be Comparable).
	 */
	public PriorityQueue(Comparator<? super AnyType> c) {
		currentSize = 0;
		cmp = c;
		array = (AnyType[]) new Object[10];
	}

	/**
	 * @return the number of items in this priority queue.
	 */
	public int size() {
		return currentSize;
	}

	/**
	 * Makes this priority queue empty.
	 */
	public void clear() {
		currentSize = 0;
	}

	/**
	 * @return the minimum item in this priority queue.
	 * @throws NoSuchElementException if this priority queue is empty.
	 * 
	 * (Runs in constant time.)
	 */
	public AnyType findMin() throws NoSuchElementException {
		
		// If size is zero or else, throw exception.
		if (currentSize <= 0) {
			throw new NoSuchElementException();
		}
		
		return array[0];
		
	}


	/**
	 * Removes and returns the minimum item in this priority queue.
	 * 
	 * @throws NoSuchElementException if this priority queue is empty.
	 * 
	 * (Runs in logarithmic time.)
	 */
	public AnyType deleteMin() throws NoSuchElementException {

		// If size is zero or else, throw exception.
		if (currentSize <= 0) {
			throw new NoSuchElementException();
		}
		
		// Get min value object.
		AnyType minObject = array[0];
		
		// The last position in queue.
		int endOfArray = currentSize - 1;
		
		// Set last object as the minObject.
		array[0] = array[endOfArray];
		
		// Set that item to null.
		array[endOfArray] = null;
		
		// Decrease size.
		currentSize--;
		
		// begin percolating the new value, downwards.
		percolateDown(0);
		
		// Return minObject (Object with minimum value).
		return minObject;
	}
	
	/**
	 * Method to swap a value down the tree until it reaches the correct position
	 * @param - index of node being percolated
	 */
	private void percolateDown(int index){
		
		// While index is less than size of the queue.
		while (((index * 2) + 1) < currentSize) {
	
			// Set child index.
			int childIndex = (index * 2) + 1;
	
			// Save the parent object.
			AnyType parentObject = array[index];
			
			// If the right child is in bounds, and is smaller than the left child. Change the index to the right child.
			if ((childIndex + 1 < currentSize) && (compare(array[(childIndex)],array[(childIndex + 1)]) > 0)) {
				childIndex++;
			}
			
			// If the parent index is greater than the child index. Swap, Repeat.
			if (compare(array[index], array[(childIndex)]) > 0)
			{
				
				array[index] = array[childIndex];
				array[childIndex] = parentObject;
				
				index = childIndex;
				
			}
			else {
				
				break;
				
			}
		}
		
	}
	
	/**
	 * Method to percolate up the heap from any given index. Restores min-heap order;
	 * 
	 * @param index - starting index 
	 */
	public void percolateUp(int index){
		
		// While index is inbounds.
		while (index > 0) {

			// If child is smaller than parent, swap.
			if (compare( array[index], array[(index - 1) / 2] ) < 0) {
			
				// Save the child, then swap the two objects.
				AnyType childObject = array[index];
				array[index] = array[(index - 1) / 2];
				array[(index - 1) / 2] = childObject;
				
				index = ((index - 1) / 2);
			
			}
			
			else {
				break;
			}
			
		}
		
	}

	/**
	 * Adds an item to this priority queue.
	 * 
	 * (Runs in logarithmic time.) Can sometimes terminate early.
	 * 
	 * @param x -- the item to be inserted
	 */
	public void add(AnyType x) {

		// Double array if extra space is needed.
		if ((currentSize + 1) >= array.length) {
			doubleArray();
		}
		
		// Add object to end of queue.
		array[currentSize] = x;
		
		// Percolate that value upwards.
		percolateUp(currentSize);
	
		// Increase the size of queue.
		currentSize++;

	}
	
	/**
	 * Resizes the array to double its capacity
	 */
	public void doubleArray(){
		
		AnyType[] temp = (AnyType[]) new Object[array.length * 2];
		
		for(int i = 0; i < currentSize; i++){
			temp[i] = array[i];
		}
		
		array = temp;
		
	}

	/**
	 * Generates a DOT file for visualizing the binary heap.
	 */
	public void generateDotFile(String filename) {
		try(PrintWriter out = new PrintWriter(filename)) {
			out.println("digraph Heap {\n\tnode [shape=record]\n");

			for(int i = 0; i < currentSize; i++) {
				
				out.println("\tnode" + i + " [label = \"<f0> |<f1> " + array[i] + "|<f2> \"]");
				
				if(((i*2) + 1) < currentSize)
					{
					out.println("\tnode" + i + ":f0 -> node" + ((i*2) + 1) + ":f1");
					}
				if(((i*2) + 2) < currentSize)
				{
					out.println("\tnode" + i + ":f2 -> node" + ((i*2) + 2) + ":f1");
				}
			}
			
			out.println("}");
			
		} catch (IOException e) {
			System.out.println(e);
		}
		
	}

	/**
	 * Internal method for comparing lhs and rhs using Comparator if provided by the
	 * user at construction time, or Comparable, if no Comparator was provided.
	 */
	private int compare(AnyType lhs, AnyType rhs) {
		if (cmp == null) {
			return ((Comparable<? super AnyType>) lhs).compareTo(rhs); // safe to ignore warning
		}
		
		// We won't test your code on non-Comparable types if we didn't supply a Comparator

		return cmp.compare(lhs, rhs);
	}

	//LEAVE IN for grading purposes
	public Object[] toArray() {    
		Object[] ret = new Object[currentSize];
		for(int i = 0; i < currentSize; i++) {
			ret[i] = array[i];
		}
		return ret;
	}
	
}