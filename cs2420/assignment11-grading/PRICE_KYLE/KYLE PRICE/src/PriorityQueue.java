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
 * @author Kyle Price
 * 11/12/2016
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
		array = (AnyType[]) new Object[10];
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
		if (currentSize == 0) {
			throw new NoSuchElementException();
		}
		AnyType minValue = array[0];
		if (currentSize == 1) {
			array = (AnyType[]) new Object[10];
			currentSize = 0;
			return minValue;
		}
		array[0] = array[currentSize - 1];
		array[currentSize - 1] = null;
		currentSize--;
		this.percolateDown(0);
		return minValue;
	}

	/**
	 * percolateDown - moves the new item from the root node down to its correct 
	 * location in the heap.
	 * @param index
	 */
	public void percolateDown(int index) {
		while (true) {
			if ((index * 2) + 1 > currentSize - 1) { // If no children.
				return;
			} else if ((index * 2) + 1 <= currentSize - 1 && (index * 2) + 2 > currentSize - 1) { // If only left child.
				if (this.compare(array[index], array[(index * 2) + 1]) > 0) {
					AnyType temp = array[index];
					array[index] = array[(index * 2) + 1];
					array[(index * 2) + 1] = temp;
					return;
				} else {
					return;
				}
			} else { // If both children exist.
				int smallerChild = this.smallestChild(index);
				if (this.compare(array[index], array[smallerChild]) > 0) {
					AnyType temp = array[index];
					array[index] = array[smallerChild];
					array[smallerChild] = temp;
					index = smallerChild;
				} else {
					return;
				}
			}
		}
	}

	/**
	 * smallestChild - finds a parents node's smallest child. 
	 * @param index
	 * @return
	 */
	public int smallestChild(int index) {
		if (this.compare(array[this.leftIndex(index)], array[this.rightIndex(index)]) < 0) {
			return this.leftIndex(index);
		} else {
			return this.rightIndex(index);
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
		if (currentSize == 0) {
			array[0] = x;
			currentSize++;
			return;
		}
		if (currentSize >= array.length) {
			this.doubleArray();
		}
		array[currentSize] = x;
		currentSize++;
		this.percolateUp(currentSize - 1, (currentSize - 2) / 2);
	}

	/**
	 * percolateUp - swaps the item with the correct ancestor.
	 * @param item - item's index.
	 * @param parent - parent's index.
	 */
	public void percolateUp(int item, int parent) {
		while (this.compare(array[item], array[parent]) < 0) {
			AnyType temp = array[item];
			array[item] = array[parent];
			array[parent] = temp;
			item = parent;
			parent = parentIndex(parent);
		}
	}

	/**
	 * doubleArray - doubles the size of the array and copies the values over. 
	 */
	public void doubleArray() {
		AnyType[] temp = (AnyType[]) new Object[array.length * 2];
		for (int i = 0; i < array.length; i++) {
			temp[i] = array[i];
		}
		array = temp;
	}

	/**
	 * rightIndex - finds the index of the right child.
	 * @param index
	 * @return
	 */
	public int rightIndex(int index) {
		return (index * 2) + 2;
	}

	/**
	 * leftIndex - finds the index of the left child.
	 * @param index
	 * @return
	 */
	public int leftIndex(int index) {
		return (index * 2) + 1;
	}

	/**
	 * parentIndex - finds the index of the parent node.
	 * @param index
	 * @return
	 */
	public int parentIndex(int index) {
		return (index - 1) / 2;
	}

	/**
	 * Generates a DOT file for visualizing the binary heap.
	 */
	public void generateDotFile(String filename) {
		try (PrintWriter out = new PrintWriter(filename)) {
			out.println("digraph Heap {\n\tnode [shape=record]\n");

			for (int i = 0; i < currentSize; i++) {
				out.println("\tnode" + i + " [label = \"<f0> |<f1> " + array[i] + "|<f2> \"]");
				if (((i * 2) + 1) < currentSize)
					out.println("\tnode" + i + ":f0 -> node" + ((i * 2) + 1) + ":f1");
				if (((i * 2) + 2) < currentSize)
					out.println("\tnode" + i + ":f2 -> node" + ((i * 2) + 2) + ":f1");
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
	 * toArray - converts the heap into an array of objects. 
	 * @return
	 */
	public Object[] toArray() {
		Object[] ret = new Object[currentSize];
		for (int i = 0; i < currentSize; i++) {
			ret[i] = array[i];
		}
		return ret;
	}
}