/*************************************
 * @author 	Kevin Claiborne | u1080787
 *
 * @title 	Assignment 11 - Binary Heap
 * @date	November 16, 2016
 **************************************/

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
		if (this.size() == 0) {
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
		// FILL IN -- do not return null

		// if the heap is empty, throw a NoSuchElementException
		if (currentSize == 0) {
			throw new NoSuchElementException();
		}

		// store the minimum item so that it may be returned at the end
		AnyType minimum = array[0];

		// replace the item at minIndex with the last item in the tree
		array[0] = array[currentSize - 1];
		array[currentSize - 1] = null;

		// percolate the item at minIndex down the tree until heap order is restored
		if (currentSize > 1) {
			// It is STRONGLY recommended that you write a percolateDown helper method!
			percDown(0);
		}
		// update size
		currentSize--;

		// return the minimum item that was stored

		return minimum;
	}

	private void percDown(int startNode) {
		AnyType tempItem = array[startNode];
		int rightChildIndex = (startNode * 2) + 2;
		int leftChildIndex = (startNode * 2) + 1;
		
		if (currentSize <= 3) {
			if (array[rightChildIndex] != null) {

				if (compare(tempItem, array[rightChildIndex]) > 0) {
					array[startNode] = array[rightChildIndex];
					array[rightChildIndex] = tempItem;
				}
			}
			if (array[leftChildIndex] != null) {
				if (compare(tempItem, array[leftChildIndex]) > 0) {
					array[startNode] = array[leftChildIndex];
					array[leftChildIndex] = tempItem;
				}
			}
		} else {
			while (rightChildIndex < currentSize && leftChildIndex < currentSize
					&& array[leftChildIndex] != null && array[rightChildIndex] != null) {
				
				if (compare(array[rightChildIndex], array[leftChildIndex]) < 0) {
					
					if (compare(tempItem, array[rightChildIndex]) > 0) {
						array[startNode] = array[rightChildIndex];
						array[rightChildIndex] = tempItem;
					}
					startNode = rightChildIndex;
				} else {
					if (compare(tempItem, array[leftChildIndex]) > 0) {
						array[startNode] = array[leftChildIndex];
						array[leftChildIndex] = tempItem;
					}
					startNode = leftChildIndex;
				}
				
				rightChildIndex = (startNode * 2) + 2;
				leftChildIndex = (startNode * 2) + 1;
			}
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
		// if the array is full, double its capacity
		if (currentSize == array.length - 1) {
			AnyType[] tempArray = array;
			array = (AnyType[]) new Object[array.length * 2];
			for (int i = 0; i < tempArray.length; i++) {
				array[i] = tempArray[i];
			}
		}

		// add the new item to the next available node in the tree, so that
		// complete tree structure is maintained
		array[currentSize] = x;

		// percolate the new item up the levels of the tree until heap order is
		// restored
		if (currentSize > 0) {
			// It is STRONGLY recommended that you write a percolateUp helper
			// method!
			percUp(currentSize);
		}

		// update size
		currentSize++;
	}

	private void percUp(int startNode) {
		AnyType tempItem = array[startNode];
		int parent = (startNode - 1) / 2;

		while (compare(tempItem, array[parent]) < 0) {
			array[startNode] = array[parent];
			array[parent] = tempItem;
			startNode = parent;
			parent = (parent - 1) / 2;
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

	public String toString() {
		String print = "[";

		for (int i = 0; i < currentSize; i++) {
			print += array[i];
			if (i < currentSize - 1) {
				print += ", ";
			}
		}
		print += "]";
		return print;

	}

}