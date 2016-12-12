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
 * @author Hyrum Johnson
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
		if (currentSize == 0) {
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

		if (currentSize == 0)
			throw new NoSuchElementException();

		// Define newArray as anyType
		AnyType newArray = array[0];

		array[0] = array[currentSize - 1];
		array[currentSize - 1] = null;
		currentSize = currentSize - 1;

		// This will call the placement method.
		moveDown(0);

		// Return newArray.
		return newArray;
	}

	/**
	 * Helper method to recursively swap our evaluated value down the tree until
	 * it reaches the correct position
	 * 
	 * @param currentVal
	 *            - the index value of the node being evaluated.
	 */
	private void moveDown(int currentVal) {
		
		// Variables for moveDown.
		AnyType newArray = array[currentVal];
		AnyType left;
		AnyType right;

		// Where are we located.
		if (2 * currentVal + 1 > currentSize) {
			left = null;
		} else {
			left = array[2 * currentVal + 1];
		}

		if (2 * currentVal + 1 > currentSize) {
			right = null;
		} else {
			right = array[2 * currentVal + 2];
		}

		// Check if both are null
		if (right == null && left == null) {
			return;
		}

		// If children are larger than currentVal
		if ((right != null && left != null)) {
			if (compare(newArray, left) <= 0 && compare(newArray, right) <= 0) {
				return;
			}
		} else {

			// When left is null and right is larger than zero.
			if (left == null && compare(newArray, right) <= 0 == true)
				return;

			// is right less than currentVal
			else if (right == null && compare(newArray, left) <= 0 == true)
				return;
		}

		// When left is null and the compare is greater than 0.
		if (left == null && compare(newArray, right) > 0 == true) {
			array[currentVal] = right;
			array[2 * currentVal + 2] = newArray;
			moveDown(2 * currentVal + 2);
			return;
		}

		// When right is null and the compare is greater than 0.
		else if (right == null && compare(newArray, left) > 0 == true) {
			array[currentVal] = left;
			array[2 * currentVal + 1] = newArray;
			moveDown(2 * currentVal + 1);
			return;
		}

		// If right is greater than left.
		if (compare(array[2 * currentVal + 2], array[2 * currentVal + 1]) < 0 == true) {
			array[currentVal] = array[2 * currentVal + 2];
			array[2 * currentVal + 2] = newArray;
			moveDown(2 * currentVal + 2);
		}
		// If left is greater than right.
		else {
			array[currentVal] = array[2 * currentVal + 1];
			array[2 * currentVal + 1] = newArray;
			moveDown(2 * currentVal + 1);
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

		if (x == null)
			return;

		if (array[array.length - 2] != null)
			grow();

		array[currentSize] = x;

		// Calling varPlacment Method.
		moveUp(currentSize);
		currentSize++;

	}

	private void grow() {
		AnyType[] temp = (AnyType[]) new Object[array.length * 2];
		for (int i = 0; i < currentSize; i++)
			temp[i] = array[i];
		// Swap out pointers, GC will destroy old array.
		array = temp;

	}

	private void moveUp(int currentSize2) {
		// TODO Auto-generated method stub
		AnyType temp = array[currentSize2];
		// BASE CASE: if the evaluated node is larger than the parent, exit
		if (compare(temp, array[(currentSize2 - 1) / 2]) >= 0)
			return;
		// Swap evaluated node wth parent, recurse
		else {
			array[currentSize2] = array[(currentSize2 - 1) / 2];
			array[(currentSize2 - 1) / 2] = temp;
			moveUp((currentSize2 - 1) / 2);
		}

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