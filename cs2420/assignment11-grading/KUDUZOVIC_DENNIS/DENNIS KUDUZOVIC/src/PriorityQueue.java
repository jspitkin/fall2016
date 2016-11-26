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
		if(currentSize == 0) {
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
		if(currentSize == 0) {
			throw new NoSuchElementException();
		}
		
		AnyType minItem = array[0];
		array[0] = array[--currentSize];
		array[currentSize] = null;
		
		percolateDown(array);
		
		// FILL IN -- do not return null

		// if the heap is empty, throw a NoSuchElementException

		// store the minimum item so that it may be returned at the end

		// replace the item at minIndex with the last item in the tree

		// update size
		
		// percolate the item at minIndex down the tree until heap order is restored
		// It is STRONGLY recommended that you write a percolateDown helper method!

		// return the minimum item that was stored

		return minItem;
	}
	
	/**
	 * Percolates down the list to sort the binary heap array
	 * 
	 * @param arr -- the array to percolate down
	 */
	private void percolateDown(AnyType[] arr) {
		int index = 0;
		while(true) {
			AnyType currentNode, leftChild, rightChild;
			
			if(index * 2 + 1 >= currentSize) {
				return;
			} else if(index * 2 + 2 >= currentSize) {
				currentNode = arr[index];
				leftChild = arr[index * 2 + 1];
				if(compare(arr[index], arr[index * 2 + 1]) > 0) {
					arr[index * 2 + 1] = currentNode;
					arr[index] = leftChild;
					return;
				} else {
					return;
				}
			}
			
			currentNode = arr[index];
			leftChild = arr[index * 2 + 1];
			rightChild = arr[index * 2 + 2];
			
			if(compare(currentNode, leftChild) > 0 && compare(leftChild, rightChild) < 0) {
				arr[index * 2 + 1] = currentNode;
				arr[index] = leftChild;
				index = index * 2 + 1;
			} else if(compare(currentNode, rightChild) > 0 && compare(leftChild, rightChild) > 0) {
				arr[index * 2 + 2] = currentNode;
				arr[index] = rightChild;
				index = index * 2 + 2;
			} else {
				return;
			}
		}
	}
	
	/**
	 * Percolates up the list to sort the binary heap array
	 * 
	 * @param arr -- the array to percolate up
	 */
	private void percolateUp(AnyType[] arr) {
		int index = currentSize - 1;
		while(true) {
			AnyType currentNode = arr[index];
			AnyType parent = arr[(index - 1) / 2];
			if(compare(currentNode, parent) < 0) {
				arr[(index - 1) / 2] = currentNode;
				arr[index] = parent;
			} else {
				return;
			}
			
			index = (index - 1) / 2;
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
		if(currentSize == array.length) {
			AnyType[] tempArray = array;
			array = (AnyType[]) new Object[currentSize * 2];
			System.arraycopy(tempArray, 0, array, 0, currentSize);
		}
		
		if(currentSize == 0) {
			array[0] = x;
			currentSize++;
			return;
		}
		
		array[currentSize++] = x;
		
		percolateUp(array);
		
		// FILL IN

		// if the array is full, double its capacity

		// add the new item to the next available node in the tree, so that
		// complete tree structure is maintained

		// update size

		// percolate the new item up the levels of the tree until heap order is restored
		// It is STRONGLY recommended that you write a percolateUp helper method!

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