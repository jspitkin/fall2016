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
 * @author 
 */
@SuppressWarnings("unchecked")
public class PriorityQueue<AnyType> {

	private int currentSize;

	private AnyType[] array;

	private Comparator<? super AnyType> cmp;

	/**
	 * Constructs an empty priority queue. Orders elements according
	 * to their natural ordering (i.e., AnyType is expected to be Comparable)
	 * AnyType is not forced to be Comparable.
	 */
	
	public PriorityQueue() {
		currentSize = 0;
		cmp = null;
		array = (AnyType[]) new Object[10]; // safe to ignore warning
	}

	/**
	 * Construct an empty priority queue with a specified comparator.
	 * Orders elements according to the input Comparator (i.e., AnyType need not
	 * be Comparable).
	 */
	public PriorityQueue(Comparator<? super AnyType> c) {
		currentSize = 0;
		cmp = c;
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
		else return array[0];
	}


	/**
	 * Removes and returns the minimum item in this priority queue.
	 * 
	 * @throws NoSuchElementException if this priority queue is empty.
	 * 
	 * (Runs in logarithmic time.)
	 */
	public AnyType deleteMin() throws NoSuchElementException {
		// if the heap is empty, throw a NoSuchElementException
		if(currentSize == 0){
			throw new NoSuchElementException();
		}
		
		// store the minimum item so that it may be returned at the end
		AnyType retVal = array[0];
		
		if(currentSize == 1){
			array[0] = null;
			currentSize--;
			return retVal;
		}
		// replace the item at minIndex with the last item in the tree
		array[0] = array[currentSize - 1];
		array[currentSize - 1] = null;
		
		// update size
		currentSize--;
		
		// percolate the item at minIndex down the tree until heap order is restored
		percolateDown(0);
		
		// return the minimum item that was stored
		return retVal;
	}
	
	/**
	 * Starting at the given index, gradually moves the value at index down until both children are greater than said value
	 * @param index - The current index at which the value is located
	 */
	private void percolateDown(int index){
		AnyType indexVal = array[index];
		if(index * 2 + 2 < array.length && array[index * 2 + 2] != null && compare(array[index * 2 + 2], array[index * 2 + 1]) < 0){
			 if(compare(array[(index * 2) + 2], indexVal) < 0){
					array[index] = array[(index * 2) + 2];
					array[(index * 2) + 2] = indexVal;
					percolateDown((index * 2) + 2);
					return;
				}
		 }
		 else if(index * 2 + 1 < array.length && array[index * 2 + 1] != null){
			 if(compare(array[(index * 2) + 1], indexVal) < 0){
				array[index] = array[(index * 2) + 1];
				array[(index * 2) + 1] = indexVal;
				percolateDown((index * 2) + 1);
				return;
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
		// if the array is full, double its capacity
		if(currentSize == array.length){
			array = doubledArray(array);
		}
		
		// add the new item to the next available node in the tree, so that
		// complete tree structure is maintained
		array[currentSize] = x;
		
		// update size
		currentSize++;
		
		// percolate the new item up the levels of the tree until heap order is restored
		percolateUp(currentSize - 1);
	}
	
	private void percolateUp(int index){
		if(index == 0){
			return;
		}
		
		if(compare(array[(index - 1) / 2], array[index]) > 0){
			AnyType indexVal = array[index];
			array[index] = array[(index - 1) / 2]; 
			array[(index - 1) / 2] = indexVal;
			percolateUp((index - 1) / 2);
			return;
		}
	}
	
	/**
	 * Returns an array twice the size of the given array, as well as holding a copy of everything within
	 * @return - The new, larger array
	 */
	private AnyType[] doubledArray(AnyType[] array){
		AnyType[] doubledArray = (AnyType[]) new Object[array.length * 2];
		
		for(int i = 0; i < array.length; i++){
			doubledArray[i] = array[i];
		}
		
		return doubledArray;
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