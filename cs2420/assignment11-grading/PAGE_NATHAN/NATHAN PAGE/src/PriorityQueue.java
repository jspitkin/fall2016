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
 * @author Nathan Page
 * @uid u0741592
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
		} else {
			return array[0];
		}
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
		
		AnyType toDelete = array[0];
		
		array[0] = array[currentSize - 1];
		
		currentSize--;
		
		percolateDown(0);

		return toDelete;
	}
	
	/**
	 * Private helper method to reorder the queue after the deleted item is replaced
	 * by the last item in the queue.
	 * 
	 * @param index - 
	 * 		The index of the current node which is potentially out of order.
	 */
	private void percolateDown(int index) {		
		if(index >= currentSize || leftChildIndex(index) >= currentSize) {
			return;
		}
		
		if(rightChildIndex(index) >= currentSize || compare(array[leftChildIndex(index)], array[rightChildIndex(index)]) < 0) {
			if(compare(array[index], array[leftChildIndex(index)]) > 0) {
				swap(index, leftChildIndex(index));
				percolateDown(leftChildIndex(index));
			} else {
				return;
			}
		} else {
			if(compare(array[index], array[rightChildIndex(index)]) > 0) {
				swap(index, rightChildIndex(index));
				percolateDown(rightChildIndex(index));
			} else {
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
		if(array.length == currentSize) {
			AnyType[] temp = (AnyType[]) new Object[array.length * 2];
			
			for(int index = 0; index < currentSize; index++) {
				temp[index] = array[index];
			}
			
			array = temp;
		}
		
		array[currentSize] = x;
		currentSize++;
		
		percolateUp(currentSize - 1);
	}
	
	/**
	 * Private helper method to reorder the list when a value is added.  
	 * As there are fewer options to swap the value with this is accomplished
	 * through a while loop instead of recursion.
	 * 
	 * @param index - 
	 * 		The starting index of the out of order node.
	 */
	private void percolateUp(int index) {
		while(compare(array[index], array[parentIndex(index)]) < 0) {
			swap(index, parentIndex(index));
			if(index < 0) {
				break;
			}
			index = parentIndex(index);
		}
	}
	
	/**
	 * A simple array swapping method.
	 * 
	 * @param index1 - 
	 * 		The index of the first item to be swapped.
	 * @param index2 - 
	 * 		The index of the second item to be swapped.
	 */
	private void swap(int index1, int index2) {
		AnyType temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
	}
	
	/**
	 * Getter for the left child of a given node, this method was provided
	 * in the lecture slides.
	 * 
	 * @param index - 
	 * 		The index of the parent node of the potential child.
	 * @return the index of the left child.
	 */
	private int leftChildIndex(int index) {
		return (index * 2) + 1;
	}
	
	/**
	 * Getter for the right child of a given node, this method was provided
	 * in the lecture slides.
	 * 
	 * @param index - 
	 * 		The index of the parent node of the potential child.
	 * @return the index of the left child.
	 */
	private int rightChildIndex(int index) {
		return (index * 2) + 2;
	}
	
	/**
	 * Getter for the parent of a given node, this method was provided in 
	 * the lecture slides.
	 * 
	 * @param index - 
	 * 		The index of the child node of the potential parent.
	 * @return the index of the parent.
	 */
	private int parentIndex(int index) {
		return (index-1) / 2;
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