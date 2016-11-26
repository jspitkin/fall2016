package assignment11;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
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
		currentSize = 0;
	}

	/**
	 * @return the minimum item in this priority queue.
	 * @throws NoSuchElementException
	 *             if this priority queue is empty.
	 * 
	 *             (Runs in constant time.)
	 */
	public AnyType findMin() throws NoSuchElementException {
		if (size() == 0) {
			throw new NoSuchElementException();
		}

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
		if (size() == 0) {
			throw new NoSuchElementException();
		}

		AnyType minItem = array[0];

		array[0] = array[currentSize - 1];
		array[currentSize - 1] = null;

		currentSize--;

		percolateDown();

		return minItem;
	}

	private void percolateDown() {
		percolateDownRecursive(0, leftChild(0), rightChild(0));
	}

	private void percolateDownRecursive(int root, int left, int right) {
		if (left >= currentSize) {
			return;
		}
		
		if (right >= currentSize) {
			if (compare(array[root], array[left]) > 0) {
				swap(root, left);
				return;
			}
			
			return;
		}
		
		int swapIndex = compare(array[left], array[right]) < 0 ? left : right;
		
		if (compare(array[root], array[swapIndex]) > 0) {
			swap(root, swapIndex);
			percolateDownRecursive(swapIndex, leftChild(swapIndex), rightChild(swapIndex));
		} else {
			return;
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
		if (currentSize == array.length) {
			array = Arrays.copyOf(array, array.length * 2);
		}

		array[currentSize] = x;
		currentSize++;

		percolateUp();
	}

	private void percolateUp() {
		percolateUpRecursive(currentSize - 1, parent(currentSize - 1));
	}

	private void percolateUpRecursive(int current, int parent) {
		if (parent < 0) {
			return;
		}
		
		int compare = compare(array[current], array[parent]);
		
		if (compare < 0) {
			swap(current, parent);
			percolateUpRecursive(parent, parent(parent));
		} else {
			return;
		}
	}

	private int leftChild(int index) {
		return (index * 2) + 1;
	}

	private int rightChild(int index) {
		return (index * 2) + 2;
	}

	private int parent(int index) {
		return (index - 1) / 2;
	}

	private void swap(int index, int toSwap) {
		AnyType temp = array[index];
		array[index] = array[toSwap];
		array[toSwap] = temp;
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