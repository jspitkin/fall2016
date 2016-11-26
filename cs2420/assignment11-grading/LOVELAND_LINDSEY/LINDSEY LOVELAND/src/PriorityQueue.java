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
 * @author Lindsey Loveland (u0970740)
 */
@SuppressWarnings("unchecked")
public class PriorityQueue<AnyType> {

	private int currentSize;

	private AnyType[] array;

	private Comparator<? super AnyType> cp;

	/**
	 * Constructs an empty priority queue. Orders elements according
	 * to their natural ordering (i.e., AnyType is expected to be Comparable)
	 * AnyType is not forced to be Comparable.
	 */
	
	public PriorityQueue() {
		currentSize = 0;
		cp = null;
		array = (AnyType[]) new Object[10]; // safe to ignore warning
	}

	/**
	 * Construct an empty priority queue with a specified comparator.
	 * Orders elements according to the input Comparator (i.e., AnyType need not
	 * be Comparable).
	 */
	public PriorityQueue(Comparator<? super AnyType> c) {
		currentSize = 0;
		cp = c;
		array = (AnyType[]) new Object[10]; // safe to ignore warning
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
		
		if(currentSize == 0){
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
		
		// if the heap is empty, throw exception
		if(currentSize == 0){
			throw new NoSuchElementException();
		}
		
		// store the minimum item so it can be returned
		AnyType min = array[0];
		
		//move the minimum to end of the array
		array[0] = array[currentSize-1];
		
		//set it to null (so garbage collector can grab it)
		array[currentSize-1] = null;
		
		// update size
		currentSize--;
		
		//percolate down to make sure everything is in correct order
		percolateDown(0);
		

		// return stored minimum
		return min;
	}
	
	/**
	 * Method to recursively swap a value down the heap until it's in the correct position
	 * @param - index of node being percolated
	 */
	private void percolateDown(int index){
		
		//if there is no children, return
		if ((index * 2) + 1 >= currentSize) {
			return;
		}

		int child = (index * 2) + 1;

		AnyType temp = array[index];
		
		//compare children to get greater child
		if ((child + 1 < currentSize) && (compare(array[(child)],array[(child + 1)]) > 0)) {
			child++;
		}
		
		//if array at index is greater than its child, swap values
		if (compare(array[index], array[(child)]) > 0){
			array[index] = array[child];
			array[child] = temp;
			
			//and recurse
			percolateDown(child);
		}
		
	}
	
	/**
	 * Method to percolate up the heap from any given index. Restores min-heap order
	 * 
	 * @param index - starting index 
	 */
	public void percolateUp(int index){
		
		//make sure we have a valid index
		if (index <= 0) {
			return;
		}

		//if the value at the current index is less than it's parent, swap values
		if (compare(array[index], array[(index - 1) / 2] ) < 0) {
		
			AnyType temp = array[index];
			array[index] = array[(index - 1) / 2];
			array[(index - 1) / 2] = temp;
			
			//and recurse
			percolateUp((index - 1) / 2);
		
		}
		
	}
	
	/**
	 * Doubles the capacity of the queue
	 */
	public void resize(){
		AnyType[] temp = (AnyType[]) new Object[array.length*2];
		
		//copy every item in temp to array
		for(int i = 0; i < currentSize; i++){
			temp[i] = array[i];
		}
		
		array = temp;
		
	}

	/**
	 * Adds an item to this priority queue.
	 * 
	 * (Runs in logarithmic time.) Can sometimes terminate early.
	 * 
	 * @param x -- the item to be inserted
	 */
	public void add(AnyType x) {

		//check if we need to resize the array
		if(currentSize + 1 >= array.length){
			resize();
		}

		// add the item to the next available spot in the heap
		array[currentSize] = x;
		
		//percolate up to make sure everything is in the correct position
		percolateUp(currentSize);
	
		//update size
		currentSize++;

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
					out.println("\tnode" + i + ":f0 -> node" + ((i*2) + 1) + ":f1");
				if(((i*2) + 2) < currentSize)
					out.println("\tnode" + i + ":f2 -> node" + ((i*2) + 2) + ":f1");
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
		if (cp == null) {
			return ((Comparable<? super AnyType>) lhs).compareTo(rhs); // safe to ignore warning
		}
		// We won't test your code on non-Comparable types if we didn't supply a Comparator

		return cp.compare(lhs, rhs);
	}

	public void printQueue() {
		
		System.out.println(arrayToString(array));
		
	}
	
	public static String arrayToString(Object[] array) {
		
		String outputString = "[";
		
		for (int i = 0; i < array.length; i++) {
			if ( array[i] != null) {
				outputString += "(" + i + ")" + array[i].toString() + ", ";
			}
		}
		
		return outputString + "]";
		
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