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
 * @author Jiwon Nam
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
		array = (AnyType[]) new Object[10];
	}

	/**
	 * @return the minimum item in this priority queue.
	 * @throws NoSuchElementException if this priority queue is empty.
	 * 
	 * (Runs in constant time.)
	 */
	public AnyType findMin() throws NoSuchElementException {
		if(currentSize == 0)
			throw new NoSuchElementException();
		
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
		if(currentSize == 0)
			throw new NoSuchElementException();
		// store the minimum item so that it may be returned at the end
		AnyType item = array[0];
		// replace the item at minIndex with the last item in the tree
		array[0] = array[currentSize - 1];
		array[currentSize - 1] = null;
		// update size
		currentSize--;
		// percolate the item at minIndex down the tree until heap order is restored
		// It is STRONGLY recommended that you write a percolateDown helper method!
		percolateDown(0, array[0]);
		// return the minimum item that was stored
		return item;
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
		if(currentSize == array.length)
			resize();
		// add the new item to the next available node in the tree, so that
		// complete tree structure is maintained
		array[currentSize] = x;
		// update size
		currentSize++;
		// percolate the new item up the levels of the tree until heap order is restored
		// It is STRONGLY recommended that you write a percolateUp helper method!
		percolateUp(currentSize - 1, x);
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
	 * helper method to percolate down with recursive method
	 * 
	 * @param parent, the item's parent index
	 * @param item, the element that should percolate down
	 */
	private void percolateDown(int parent, AnyType item) {
		int leftChild = (parent * 2) + 1;
		int rightChild = (parent * 2) + 2;
		int index = 0;
		if(leftChild >= currentSize)
			return;
		else if(rightChild >= currentSize) {
			if(cmp.compare(item, array[leftChild]) < 0)
				return;
			else {
				array[parent] = array[leftChild];
				array[leftChild] = item;
				index = leftChild;
			}
		}
		else {
			if(cmp.compare(array[leftChild], array[rightChild]) > 0) {
				if(cmp.compare(item, array[rightChild]) < 0)
					return;
				else {
					array[parent] = array[rightChild];
					array[rightChild] = item;
					index = rightChild;
				}
			}
			else {
				if(cmp.compare(item, array[leftChild]) < 0)
					return;
				else {
					array[parent] = array[leftChild];
					array[leftChild] = item;
					index = leftChild;
				}
			}
		}	
		percolateDown(index, item);
	}
	
	/**
	 * helper method to percolate up with recursive strategy
	 * 
	 * @param child, child's index
	 * @param item, the element that should percolate up item
	 */
	private void percolateUp(int child, AnyType item) {
		int parent = (child - 1) / 2;
		
		if(child == 0 || cmp.compare(item, array[parent]) > 0)
			return;
		else {
			array[child] = array[parent];
			array[parent] = item;
		}
		percolateUp(parent, item);
	}

	/**
	 * this is method to resize the array if the array is filled full
	 */
	private void resize() {
		int capacity = array.length;
		AnyType[] temp = (AnyType[]) new Object[capacity * 2];
		for(int i = 0; i < capacity; i++)
			temp[i] = array[i];
		
		array = temp;
	}
}