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
 * @author Samuel Teare | UID: u0663592
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

		// store the minimum item so that it may be returned at the end
		AnyType results = array[0];

		// replace the item at minIndex with the last item in the tree
		swap(0, currentSize - 1);

		// update size
		currentSize--;
		
		// percolate the item at minIndex down the tree until heap order is restored
		percolateDown(0);
		
		// return the minimum item that was stored
		return results;
	}
	
	/**
	 * Ensures that the item at the given index is moved to a location 
	 * where its children are greater/larger than itself.
	 * 
	 * @param index - index of the given item
	 */
	private void percolateDown(int index) {
		
		int tempIndex = index;
		int tempLC = leftChildIndex(tempIndex);
		int tempRC = rightChildIndex(tempIndex);
		
		//Compares with the lesser of the left and right child as long as both exist.
		//Swaps if this item is greater than the lesser of the two children.
		while(tempRC < currentSize) {
			if(compare(array[tempLC], array[tempRC]) < 1) {
				if(compare(array[tempIndex], array[tempLC]) > 0) {
					swap(tempIndex, tempLC);
					tempIndex = tempLC;
					tempLC = leftChildIndex(tempIndex);
					tempRC = rightChildIndex(tempIndex);
				}
				else {
					return;
				}
			}
			else {
				if(compare(array[tempIndex], array[tempRC]) > 0) {
					swap(tempIndex, tempRC);
					tempIndex = tempRC;
					tempLC = leftChildIndex(tempIndex);
					tempRC = rightChildIndex(tempIndex);
				}
				else {
					return;
				}
			}
		}
		
		//compares with the left child if it exists.
		if(tempLC < currentSize) {
			if(compare(array[tempIndex], array[tempLC]) > 0) {
				swap(tempIndex, tempLC);
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
		// FILL IN

		// if the array is full, double its capacity
		if(currentSize == array.length) {
			increaseArraySize();
		}

		// add the new item to the next available node in the tree, so that
		// complete tree structure is maintained
		array[currentSize] = x;

		// update size
		currentSize++;

		// percolate the new item up the levels of the tree until heap order is restored
		percolateUp(currentSize - 1);

	}
	
	/**
	 * Creates a new array that is double the current array's capacity 
	 * and copies the values over to the new array
	 */
	private void increaseArraySize() {
		AnyType[] tempArray = (AnyType[]) new Object[array.length * 2];
		for(int index = 0; index < array.length; index++) {
			tempArray[index] = array[index];
		}
		array = tempArray;
	}

	/**
	 * Ensures that the item at the given index is not less than its parent.
	 * Item at the given index is compared with its parent. 
	 * This item is swap with its parent if it is less than the parent.
	 * This process repeats until the item is greater than its parent.
	 * 
	 * @param index - index of the given item
	 */
	private void percolateUp(int index) {
		if(index == 0) {
			return;
		}
		int tempIndex = index;
		while(parentIndex(tempIndex) != -1) {
			if(compare(array[tempIndex], array[parentIndex(tempIndex)]) < 0) {
				swap(tempIndex, parentIndex(tempIndex));
				tempIndex = parentIndex(tempIndex);
			}
			else {
				return;
			}
		}
	}
	
	/**
	 * Returns the index of the parent to this item
	 * 
	 * @param index - index of this item
	 * @return - returns the index of the parent to this item. If this item does not have a parent, it will return -1;
	 */
	private int parentIndex(int index) {
		if(index <= 0) {
			return -1;
		}
		return (index - 1)/2;
	}
	
	/**
	 * Returns the index of left child of this item.
	 * 
	 * @param index - index of this item
	 */
	private int leftChildIndex(int index) {
		return (index * 2) + 1;
	}
	
	/**
	 * Returns the index of right child of this item.
	 * 
	 * @param index - index of this item
	 */
	private int rightChildIndex(int index) {
		return (index * 2) + 2;
	}
	
	/**
	 * Swaps two items within an array at the given indices.
	 * 
	 * @param index1 - index of the first item being switched
	 * @param index2 - index of the second item being switched
	 */
	private void swap(int index1, int index2) {
		AnyType temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
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
	 * Returns an Array representation of this Priority Queue.
	 */
	public Object[] toArray() {    
		Object[] ret = new Object[currentSize];
		for(int i = 0; i < currentSize; i++) {
			ret[i] = array[i];
		}
		return ret;
	}
}