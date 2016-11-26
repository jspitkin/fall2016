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
 * @author Ben Figlin (u1115949)
 * 
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
		// if the heap is empty, throw a NoSuchElementException
		// store the minimum item so that it may be returned at the end
		AnyType minItem = findMin();

		// replace the item at minIndex with the last item in the tree
		array[0] = array[currentSize - 1];
		
		// update size
		currentSize--;
		
		// percolate the item at minIndex down the tree until heap order is restored
		// It is STRONGLY recommended that you write a percolateDown helper method!
		percolateDown(0);
		
		// return the minimum item that was stored
		return minItem;
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
		if (currentSize >= (array.length - 1)) {
			resizeArray();
		}
		
		// add the new item to the next available node in the tree, so that
		// complete tree structure is maintained
		array[currentSize] = x;
		
		// percolate the new item up the levels of the tree until heap order is restored
		// It is STRONGLY recommended that you write a percolateUp helper method!
		percolateUp(currentSize);

		// update size
		currentSize++;
	}

	/**
	 * Moves the item up to its appropriate order in the binary heap.
	 * 
	 * @param index - the index of the item to percolate
	 */
	private void percolateUp(int index) {
		int parent = (index-1)/2;
		while(index > 0 && compare(array[index], array[parent]) < 0) {
			swap(index, parent);
			
			index = parent;
			parent = (index-1)/2;
		}
	}
	
	/**
	 * Moves the item down to its appropriate order in the binary heap.
	 * 
	 * @param index - the index of the item to percolate
	 */
	private void percolateDown(int index) {
		while (true) {
			int left = (index*2)+1;
			int right = (index*2)+2;
			int child;
			
			if (right < currentSize && compare(array[right], array[index]) < 0) {
				if (compare(array[right], array[left]) < 0) {
					child = right;
				} else {
					child = left;
				}
			} else if (left < currentSize && compare(array[left], array[index]) < 0) {
				child = left;
			} else {
				break;
			}
			
			swap(child, index);
			index = child;
		}
	}
	
	/**
	 * swaps two elements in the heap array
	 * 
	 * @param a - the index of the first element
	 * @param b - the index of the second element
	 */
	private void swap(int a, int b) {
		AnyType temp = array[a];
		array[a] = array[b];
		array[b] = temp;
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

	/**
	 * resizes the array by a factor of 2 and copies the contents of the previous 
	 * array to the new one.
	 * 
	 */
	private void resizeArray() {
		AnyType[] temp = (AnyType[]) new Object[array.length * 2]; 
		
		for (int i = 0; i < array.length; i++) {
			temp[i] = array[i];
		}
		
		array = temp;
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