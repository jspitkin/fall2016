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
 * @author Daxton Wilson u0264580 
 * (based on assignment11 skeleton provided by CS2420 Course)
 */
@SuppressWarnings("unchecked")
public class PriorityQueue<AnyType> {

	private int currentSize;
	AnyType[] array;
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
	 * @param Comparator of any type
	 */
	public PriorityQueue(Comparator<? super AnyType> c) {
		currentSize = 0;
		cmp = c;
		array = (AnyType[]) new Object[10]; // safe to ignore warning
	}

	/**
	 * Returns the number of items in the priority queue that are not null.
	 * @return the number of items in this priority queue.
	 */
	public int size() {
		return currentSize;
	}

	/**
	 * Makes this priority queue empty.
	 */
	public void clear() {
		this.array = (AnyType[]) new Object[10];
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
		if (this.array == null || this.array[0] == null){
			throw new NoSuchElementException();
		}
		return this.array[0];
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
		// if the heap is empty, throw a NoSuchElementException
		if (this.currentSize == 0) {
			throw new NoSuchElementException();
		}
		// store the minimum item so that it may be returned at the end
		AnyType minItem = this.findMin();

		// replace the item at minIndex with the last item in the tree
		AnyType newMin = this.array[currentSize - 1];
		this.array[currentSize - 1] = null;
		this.array[0] = newMin;
		currentSize--;
		this.percolateDown(0);

		return minItem;
	}

	/**
	 * Recursive method to ensure that the smallest item is at index 0 in the priority queue.
	 * Further ensures that every parent is bigger than its' children.
	 * @param index of item you are checking against its' children
	 */
	public void percolateDown(int index) {
		if (index >= currentSize-1){
			return;
		}
		AnyType leftChild = (leftChildIndex(index) < this.array.length-1) ? this.array[leftChildIndex(index)] : null;
		AnyType rightChild = (rightChildIndex(index) < this.array.length-1) ? this.array[rightChildIndex(index)] : null;
		if (leftChild == null && rightChild == null) {
			return;
		}
		AnyType child;
		int childIndex;
		AnyType parent = this.array[index];
		if (leftChild == null) {
			child = rightChild;
			childIndex = rightChildIndex(index);
		} 
		else if (rightChild == null){
			child = leftChild;
			childIndex = leftChildIndex(index);
		}
		else if (this.compare(leftChild, rightChild) > 0) {
			child = rightChild;
			childIndex = rightChildIndex(index);
		}
		else {
			child = leftChild;
			childIndex = leftChildIndex(index);
		}
		
		if (this.compare(parent, child) > 0) {
			this.array[childIndex] = parent;
			this.array[index] = child;
		}
		percolateDown(leftChildIndex(index));
		percolateDown(rightChildIndex(index));
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
		if (x == null) {
			throw new NullPointerException();
		}
		
		if (currentSize == 0) {
			this.array[0] = x;
			currentSize++;
			return;
		}
		
		// if the array is full, double its capacity
		if (currentSize == (this.array.length - 1)) {
			AnyType[] oldArray = this.array;
			this.array = (AnyType[]) new Object[this.array.length * 2];
			for (int i = 0; i < oldArray.length; i++) {
				this.array[i] = oldArray[i];
			}
		}
		
		int nextFreeChild = currentSize;
		this.array[nextFreeChild] = x;
		currentSize++;
		this.percolateUp(nextFreeChild);
	}

	/**
	 * Method to percolate up a min-heap data structure for addition of new
	 * elements. Terminates once a child is greater than its parent.
	 * 
	 * @param start index to percolate up from
	 */
	public void percolateUp(int index) {
		if (index == 0) {
			return;
		}
		AnyType child = this.array[index];
		AnyType parent = this.array[parentIndex(index)];
		if (this.compare(parent, child) < 0) {
			return;
		}
		this.array[parentIndex(index)] = child;
		this.array[index] = parent;
		percolateUp(parentIndex(index));
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

	/**
	 * Method to find the left child of a given index in the priority queue.
	 * @param i (current index)
	 * @return left child of i index
	 */
	public int leftChildIndex(int i) {
		return (i * 2) + 1;
	}

	/**
	 * Method to find the right child of a given index in the priority queue.
	 * @param i (current index)
	 * @return right child of i index
	 */
	public int rightChildIndex(int i) {
		return (i * 2) + 2;
	}

	/**
	 * Method to find the parent of a given index in the priority queue.
	 * @param i (current index)
	 * @return parent of i index
	 */
	public int parentIndex(int i) {
		return (i - 1) / 2;
	}

	// LEAVE IN for grading purposes
	/**
	 * Method left in for grading purposes.
	 * Trollolololololol
	 * @return an array
	 */
	public Object[] toArray() {
		Object[] ret = new Object[currentSize];
		for (int i = 0; i < currentSize; i++) {
			ret[i] = array[i];
		}
		return ret;
	}
}