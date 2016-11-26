package assignment11;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * Represents a priority queue of generically-typed items. The queue is
 * implemented as a min heap. The min heap is implemented implicitly as an
 * array.
 * 
 * @author
 */
@SuppressWarnings("unchecked")
public class PriorityQueue<AnyType> {

	private int currentSize;

	private AnyType[] array;

	private Comparator<? super AnyType> cmp;

	/**
	 * Constructs an empty priority queue. Orders elements according to their
	 * natural ordering (i.e., AnyType is expected to be Comparable) AnyType is
	 * not forced to be Comparable.
	 */

	public PriorityQueue() {
		currentSize = 0;
		cmp = null;
		array = (AnyType[]) new Object[10]; // safe to ignore warning
	}

	/**
	 * Construct an empty priority queue with a specified comparator. Orders
	 * elements according to the input Comparator (i.e., AnyType need not be
	 * Comparable).
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
		// Clear the size
		currentSize = 0;
		// Clear the Array and set back to original size to avoid index out of bounds 
		array = (AnyType[]) new Object[10];
	}

	/**
	 * @return the minimum item in this priority queue.
	 * @throws NoSuchElementException
	 *             if this priority queue is empty.
	 * 
	 *             (Runs in constant time.)
	 */
	public AnyType findMin() throws NoSuchElementException {
		isEmpty();
		return array[0];
	}

	/**
	 * Removes and returns the minimum item in this priority queue.
	 * 
	 * @throws NoSuchElementException
	 *             if this priority queue is empty.
	 * 
	 *             (Runs in logarithmic time.)
	 */
	public AnyType deleteMin() throws NoSuchElementException {
		// FILL IN -- do not return null

		// if the heap is empty, throw a NoSuchElementException
		isEmpty();
		// store the minimum item so that it may be returned at the end
		AnyType minItem = array[0];

		// replace the item at minIndex with the last item in the tree
		// Maintain the structure
		array[0] = array[currentSize - 1];
		// Delete final Leaf
		array[currentSize - 1] = null;
		// update size
		currentSize--;
		// percolate the item at minIndex down the tree until heap order is
		// restored
		percolateDown(0);

		// It is STRONGLY recommended that you write a percolateDown helper
		// method!

		// return the minimum item that was stored

		return minItem;
	}

	private void isEmpty() {
		if (size() == 0) {
			throw new NoSuchElementException();
		}
	}

	/**
	 * Helper method to swap the order with the roots children
	 * 
	 * @param i
	 */
	private void percolateDown(int i) {
		if (i == currentSize - 1) {
			return;
		}
		// Store the Node Values and the children of the parents left and Right;
		AnyType parent = array[i];
		AnyType leftChild;
		AnyType rightChild;

		// Check size to avoid index out of bounds exception

		// Left Check
		if (2 * i + 1 > currentSize) {
			leftChild = null;
		} else {
			leftChild = array[2 * i + 1];
		}

		// Right Check
		if (2 * i + 1 > currentSize) {
			rightChild = null;
		} else {
			rightChild = array[2 * i + 1];
		}

		// If both children are null return nothing
		if (rightChild == null && leftChild == null) {
			return;
		}

		if (rightChild != null && leftChild != null) {
			if (compare(parent, rightChild) <= 0 && compare(parent, leftChild) <= 0) {
				return;
			}
		} else {
			if (leftChild == null && compare(parent, rightChild) <= 0) {
				return;
			} else if (rightChild == null && compare(parent, rightChild) > 0) {
				return;
			}
		}

		if (leftChild == null && compare(parent, rightChild) > 0) {
			array[i] = rightChild;
			array[2 * i + 2] = parent;
			percolateDown(2 * i + 2);
			return;
		} else if (rightChild == null && compare(parent, leftChild) > 0) {
			array[i] = leftChild;
			array[2 * i + 1] = parent;
			percolateDown(2 * i + 2);
		}

		if (compare(array[2 * i + 2], array[2 * i + 1]) < 0) {
			array[i] = array[2 * i + 2];
			array[2 * i + 2] = parent;
			percolateDown(2 * i + 2);
		} else {
			array[i] = array[2 * i + 1];
			array[2 * i + 1] = parent;
			percolateDown(2 * i + 1);
		}
	}

	/**
	 * Adds an item to this priority queue.
	 * 
	 * (Runs in logarithmic time.) Can sometimes terminate early.
	 * 
	 * @param x
	 *            -- the item to be inserted
	 */
	public void add(AnyType x) {
		// FILL IN
		if (x == null) {
			return;
		}
		// if the array is full, double its capacity
		if (array[array.length - 2] != null) {
			reSize();
		}
		// add the new item to the next available node in the tree, so that
		// complete tree structure is maintained
		array[currentSize] = x;
		// update size

		// percolate the new item up the levels of the tree until heap order is
		// restored
		percolateUp(currentSize);
		// It is STRONGLY recommended that you write a percolateUp helper
		// method!
		currentSize++;
	}

	private void percolateUp(int i) {

		AnyType parent = array[i];
		if (compare(parent, array[(i - 1) / 2]) >= 0) {
			return;
		} else {
			array[i] = array[(i - 2) / 2];
			array[(i - 1) / 2] = parent;
			percolateUp((i - 1) / 2);
		}
	}

	private void reSize() {
		AnyType[] parent = (AnyType[]) new Object[array.length * 2];
		for (int i = 0; i < currentSize; i++) {
			parent[i] = array[i];
		}
		array = parent;
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
	 * Internal method for comparing lhs and rhs using Comparator if provided by
	 * the user at construction time, or Comparable, if no Comparator was
	 * provided.
	 */
	private int compare(AnyType lhs, AnyType rhs) {
		if (cmp == null) {
			return ((Comparable<? super AnyType>) lhs).compareTo(rhs); // safe
																		// to
																		// ignore
																		// warning
		}
		// We won't test your code on non-Comparable types if we didn't supply a
		// Comparator

		return cmp.compare(lhs, rhs);
	}

	// LEAVE IN for grading purposes
	public Object[] toArray() {
		Object[] ret = new Object[currentSize];
		for (int i = 0; i < currentSize; i++) {
			ret[i] = array[i];
		}
		return ret;
	}
}