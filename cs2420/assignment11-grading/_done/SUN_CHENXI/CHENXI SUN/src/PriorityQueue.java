package assignment11;
/**
 * 
 * @author Chenxi Sun
 * @uid u0455173
 *
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * Represents a priority queue of generically-typed items. The queue is
 * implemented as a min heap. The min heap is implemented implicitly as an
 * array.
 * 
 * @author
 */
@SuppressWarnings("unchecked")
public class PriorityQueue<AnyType> {

	public static void main(String[] args) {
		ComparatorInteger a2 = new ComparatorInteger();
		PriorityQueue<Integer> abc = new PriorityQueue<Integer>(a2);
		Random a = new Random();

		abc.add((Integer) 50);
		abc.add((Integer) 150);
		abc.add((Integer) 250);
		abc.add((Integer) 5);
		abc.add((Integer) 15);
		abc.add((Integer) 25);
//		abc.deleteMin();
//		abc.deleteMin();

		System.out.println(abc.currentSize);

		System.out.println(Arrays.toString(abc.toArray()));
		abc.generateDotFile("solution.dot");
	}

	public int currentSize;

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
		array = (AnyType[]) new Object[currentSize];
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

		if (currentSize == 0) {
			throw new NoSuchElementException();// if the heap is empty, throw a
												// NoSuchElementException
		}
		AnyType temp=array[0];
		array[0] = array[--currentSize];
		percolatedown(0);

		// store the minimum item so that it may be returned at the end

		// replace the item at minIndex with the last item in the tree

		// update size

		// percolate the item at minIndex down the tree until heap order is
		// restored
		// It is STRONGLY recommended that you write a percolateDown helper
		// method!

		// return the minimum item that was stored

		return temp;
	}

	private void percolatedown(int hole) {
		AnyType tmp = array[hole];
		int child;
		if(cmp!=null){
		for (; 2 * hole <= currentSize; hole = child) {
			child = 2 * hole;
			
			if (child != currentSize && cmp.compare(array[child], array[child + 1]) > 0)
				child++;

			if (cmp.compare(tmp, array[child]) > 0)
				array[hole] = array[child];
			else
				break;
		}
		array[hole] = tmp;
		}
		else if(cmp==null){
			for (; 2 * hole <= currentSize; hole = child) {
				child = 2 * hole;
				
				if (child != currentSize && compare(array[child], array[child + 1]) > 0)
					child++;

				if (compare(tmp, array[child]) > 0)
					array[hole] = array[child];
				else
					break;
			}
			array[hole] = tmp;
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

		if (currentSize >= array.length) {
			AnyType[] oldarray = array;
			array = (AnyType[]) new Object[2 * array.length];// if the array is
																// full, double
																// its capacity
			currentSize = 0;
			for (int i = 0; i < oldarray.length; i++) {
				if (oldarray[i] != null) {
					add(oldarray[i]);
				}
			}

		} else {
			if (currentSize == 0) {
				array[0] = x;
			}

			int index = currentSize++;

			if (cmp != null) {
				if (index % 2 == 1) {
					for (; cmp.compare(x, array[parentIndex(index)]) < 0; index = parentIndex(index)) {

						array[index] = array[parentIndex(index)];
						array[parentIndex(index)] = x;
					}

				}

				else if (index % 2 == 0) {
					for (; cmp.compare(x, array[index / 2]) < 0; index = index / 2) {

						array[index] = array[index / 2];
						array[index / 2] = x;
					}

				}

				array[index] = x;

			} else {
				if (index % 2 == 1) {
					for (; compare(x, array[parentIndex(index)]) < 0; index = parentIndex(index)) {

						array[index] = array[parentIndex(index)];
						array[parentIndex(index)] = x;
					}

				}

				else if (index % 2 == 0) {
					for (; compare(x, array[index / 2]) < 0; index = index / 2) {

						array[index] = array[index / 2];
						array[index / 2] = x;
					}

				}

				array[index] = x;
			}

		}

	}

	// add the new item to the next available node in the tree, so that
	// complete tree structure is maintained

	// update size

	// percolate the new item up the levels of the tree until heap order is
	// restored
	// It is STRONGLY recommended that you write a percolateUp helper method!

	private int leftChildIndex(int i) {
		return i * 2 + 1;
	}

	private int rightChildIndex(int i) {
		return i * 2 + 2;
	}

	private int parentIndex(int i) {
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