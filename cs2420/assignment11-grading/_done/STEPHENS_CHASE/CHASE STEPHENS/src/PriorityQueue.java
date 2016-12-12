package assignment11;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.NoSuchElementException;

import org.omg.CORBA.Any;

/**
 * Represents a priority queue of generically-typed items. The queue is
 * implemented as a min heap. The min heap is implemented implicitly as an
 * array.
 * 
 * @author Chase Stephens
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
		currentSize = 0;
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

		if (currentSize == array.length) {
			grow();
		}

		array[currentSize] = x;

		percolateUp(currentSize++);
	}

	/**
	 * @return the minimum item in this priority queue.
	 * @throws NoSuchElementException
	 *             if this priority queue is empty.
	 * 
	 *             (Runs in constant time.)
	 */
	public AnyType findMin() throws NoSuchElementException {
		if (currentSize == 0) {
			throw new NoSuchElementException();
		} else {
			return array[0];
		}
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
		if (currentSize == 0) {
			throw new NoSuchElementException();
		}

		if (currentSize == 1) {
			clear();
			return array[0];
		}
		AnyType minItem = array[0];

		array[0] = array[--currentSize];

		percolateDown(0);

		return minItem;

	}

	/**
	 * Helper method for deleteMin. Takes the root and moves it down until it is
	 * the smallest in it's sub tree.
	 */
	private void percolateDown(int currentIndex) {
		AnyType left, right, current;
		int leftIndex = leftChildIndex(currentIndex);
		int rightIndex = rightChildIndex(currentIndex);
		// finds left child
		if (leftIndex < currentSize) {
			left = array[leftIndex];
		} else {
			left = null;
		}
		// finds right child
		if (rightIndex < currentSize) {
			right = array[rightIndex];
		} else {
			right = null;
		}

		current = array[currentIndex];

		// no children
		if (left == null && right == null) {
			return;
		}

		// case with two children
		if (left != null && right != null) {

			if (compare(left, right) < 0) {
				if (compare(left, current) < 0) {
					swap(leftIndex, currentIndex);
					percolateDown(leftIndex);
				}
			} else {
				if (compare(right, current) < 0) {
					swap(rightIndex, currentIndex);
					percolateDown(rightIndex);
				}
			}
		}
		// one child case
		else {
			if (left != null) {
				if (compare(left, current) < 0) {
					swap(leftIndex, currentIndex);
					percolateDown(leftIndex);
				}
			} else {
				if (compare(right, current) < 0) {
					swap(rightIndex, currentIndex);
					percolateDown(rightIndex);
				}
			}
		}
	}

	/**
	 * Swaps two items in the heap.
	 * 
	 * @param i
	 *            - index of first item
	 * @param j
	 *            - index of second item
	 */
	private void swap(int i, int j) {
		AnyType temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	/**
	 * Helper method to percolate items up the tree
	 * 
	 * @param i
	 *            - index of item.
	 */
	private void percolateUp(int i) {
		int currentIndex = i;
		int parentIndex = parentIndex(i);
		while ((parentIndex >= 0) && compare(array[currentIndex], array[parentIndex]) < 0) {
			// swapps items
			swap(currentIndex, parentIndex);
			// incrementing parent index
			currentIndex = parentIndex;
			parentIndex = parentIndex(parentIndex);
		}
	}

	/**
	 * Method used to grow array
	 */
	private void grow() {
		Object[] arr = array;
		array = (AnyType[]) new Object[array.length * 2];
		for (int i = 0; i < arr.length; i++) {
			array[i] = (AnyType) arr[i];
		}
	}

	/**
	 * Finds the left child of an item in the heap.
	 * 
	 * @param i
	 *            - index of item
	 * @return - item's left child
	 */
	int leftChildIndex(int i) {
		return (i * 2) + 1;
	}

	/**
	 * Finds the right child of an item in the heap.
	 * 
	 * @param i
	 *            - index of item
	 * @return - item's right child
	 */
	int rightChildIndex(int i) {
		return (i * 2) + 2;
	}

	/**
	 * Finds the parent of an item in the heap.
	 * 
	 * @param i
	 *            - index of item
	 * @return - item's parent
	 */
	int parentIndex(int i) {
		return (i - 1) / 2;
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