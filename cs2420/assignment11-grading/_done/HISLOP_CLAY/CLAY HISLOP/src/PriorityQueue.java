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
 * @author Clayton Hislop
 * uID: u0515744
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
		AnyType min = findMin();
		
		// replace the item at minIndex with the last item in the tree
		array[0] = array[currentSize - 1];
		// update size
		currentSize--;
		// percolate the item at minIndex down the tree until heap order is restored
		// starting at index 0 because that is where the last item has now been placed
		percolateDown(0);
		
		// return the minimum item that was stored
		return min;
	}


	/**
	 * Adds an item to this priority queue.
	 * 
	 * (Runs in logarithmic time.) Can sometimes terminate early.
	 * 
	 * @param x -- the item to be inserted
	 */
	public void add(AnyType x) {
		if (currentSize == array.length) {
			AnyType[] temp = (AnyType[]) new Object[array.length * 2];
			
			for (int i = 0; i < array.length; i++) {
				temp[i] = array[i];
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
		if (currentSize != 1) {
			percolateUp(currentSize - 1);
		}
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
	
	/**
	 * Finds the index of the parent Node using the equation (index - 1) / 2
	 * @param child  - The child index for the parent to be searched for
	 * @return - The index of the parent Node
	 */
	private int getParentIndex(int child) {
		return (child -1)/2;
	}
	
	/**
	 * Gets the index for the left child of parent index using the equation (i*2) +1;
	 * @param parent - The parent index for determining the child
	 * @return - The index of the left child
	 */
	private int getLeftChildIndex(int parent) {
		return (parent*2)+1;
	}
	
	/**
	 * Gets the index for the right child of parent index using the equation (i*2) +2;
	 * @param parent - The parent index for determining the child
	 * @return - The index of the right child
	 */
	private int getRightChildIndex(int parent) {
		return (parent*2)+2;
	}
	
	/**
	 * Percolates the item  at the given index down the tree until it is in the correct position.
	 * @param index - The index of the item to be percolated down the tree
	 */
	private void percolateDown(int index) {
		int leftIndex = getLeftChildIndex(index);
		if (leftIndex >= currentSize) {
			return;
		}
		
		int rightIndex = getRightChildIndex(index);
		int smallestChild = leftIndex;
		
		if (rightIndex < currentSize && compare(array[rightIndex], array[leftIndex]) < 0) {
			smallestChild = rightIndex;
		}
		
		if (compare(array[index], array[smallestChild]) > 0) {
			AnyType temp = array[smallestChild];
			array[smallestChild] = array[index];
			array[index] = temp;
			percolateDown(smallestChild);
		}
		
	}
	
	/**
	 * Percolates the item at the given index up the tree until it is in the correct position.
	 * @param index - The index of the item to be percolated up the tree
	 */
	private void percolateUp(int index) {
		int parentIndex = getParentIndex(index);
		if (compare(array[index], array[parentIndex]) < 0) {
			AnyType temp = array[parentIndex];
			array[parentIndex] = array[index];
			array[index] = temp;
			percolateUp(parentIndex);
		}
	}
	
}