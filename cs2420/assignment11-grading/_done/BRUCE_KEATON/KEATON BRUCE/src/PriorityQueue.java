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
 * @author Andrew Keaton Bruce
 * u1006889
 * 11/15/2016
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
		if (currentSize == 0) {
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
		// if the heap is empty, throw a NoSuchElementException
		if (currentSize == 0) {
			throw new NoSuchElementException();
		}

		// store the minimum item so that it may be returned at the end
		AnyType minType = array[0];

		// replace the item at minIndex with the last item in the tree
		array[0] = array[currentSize - 1];
		array[currentSize - 1] = null;
		
		// update size
		currentSize--;
		
		// percolate the item at minIndex down the tree until heap order is restored
		percolateDown();
		// return the minimum item that was stored
		return minType;
	}
	
	/**
	 * percolateDown replaces the element at the top of the min heap with
	 * the appropriate element somewhere in the rest of the min heap.
	 */
	private void percolateDown() {
		//start at the top
		int index = 0;
		//find the children and check if they are valid
		int leftChild = leftChildIndex(index);
		if (leftChild >= currentSize || array[leftChild] == null) {
			leftChild = index;
		}
		int rightChild = rightChildIndex(index);
		if (rightChild >= currentSize || array[rightChild] == null) {
			rightChild = index;
		}
		
		//Only continue this loop when the index of the percolating element is less than the size of the array (we don't want to go to far)
		//And
		//When ONE of the children is less than it
		while (index < currentSize && (compare(array[index], array[leftChild]) > 0 || compare(array[index], array[rightChild]) > 0)) {
			//Replace the percolating element with the minimum child
			int minChild = leftChild;
			if (compare(array[leftChild], array[rightChild]) > 0) {
				minChild = rightChild;
			}
			//swap the elements
			swap(index, minChild);
			//update the index and children
			index = minChild;
			//Check for valid children
			leftChild = leftChildIndex(index);
			if (leftChild >= currentSize || array[leftChild] == null) {
				leftChild = index;
			}
			rightChild = rightChildIndex(index);
			if (rightChild >= currentSize || array[rightChild] == null) {
				rightChild = index;
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
		if (currentSize == array.length) {
			AnyType[] temp = (AnyType[]) new Object[currentSize * 2];
			for (int index = 0; index < array.length; index++) {
				temp[index] = array[index];
			}
			array = temp;
		}

		// add the new item to the next available node in the tree, so that
		// complete tree structure is maintained
		array[currentSize] = x;
		
		// update size
		currentSize++;
		
		// percolate the new item up the levels of the tree until heap order is restored
		// It is STRONGLY recommended that you write a percolateUp helper method!
		percolateUp(currentSize - 1);
	}
	
	/**
	 * percolateUp starts at the desired index and percolates this element
	 * up the list until it reaches a parent that is less than it
	 * @param index
	 */
	private void percolateUp(int index) {
		// Just check for a valid index, if it is 0 then it can't go up
		if (index != 0) {
			// Get the parent
			int parentIndex = parentIndex(index);
			// Continue this loop until the index can't go higher
			// Or until the parent is less than the percolating index
			while (index != 0 && compare(array[index], array[parentIndex]) < 0) {
				// Do the swap
				swap (index, parentIndex);
				// Update the percolating element and the parent
				index = parentIndex;
				parentIndex = parentIndex(index);
			}
		}
	}
	
	/**
	 * swap swaps the elements at these two indices
	 * @param indexOne
	 * @param indexTwo
	 */
	private void swap(int indexOne, int indexTwo) {
		AnyType temp = array[indexTwo];
		array[indexTwo] = array[indexOne];
		array[indexOne] = temp;
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
			return ((Comparable<? super AnyType>) lhs).compareTo(rhs);
		}
		return cmp.compare(lhs, rhs);
	}
	
	/**
	 * Find the left child
	 * @param index
	 * @return the left child
	 */
	private int leftChildIndex(int index) {
		return (index * 2) + 1;
	}
	
	/**
	 * Find the right child
	 * @param index
	 * @return the right child
	 */
	private int rightChildIndex(int index) {
		return (index * 2) + 2;
	}
	
	/**
	 * Find the parent
	 * @param index
	 * @return the parent
	 */
	private int parentIndex(int index) {
		return (index - 1) / 2;
	}

	/**
	 * Provides a new array of this priority queue
	 * @return that array
	 */
	public Object[] toArray() {    
		Object[] ret = new Object[currentSize];
		for(int i = 0; i < currentSize; i++) {
			ret[i] = array[i];
		}
		return ret;
	}
}