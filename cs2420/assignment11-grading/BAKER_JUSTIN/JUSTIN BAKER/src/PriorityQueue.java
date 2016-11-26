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
		// TODO: check
		if(currentSize==0){
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
		// TODO: check
		if (currentSize==0) {
			throw new NoSuchElementException();
		}
		// store the minimum item so that it may be returned at the end
		AnyType min = array[0];
		// replace the item at minIndex with the last item in the tree
		// update size
		array[0]=array[--currentSize];
		// percolate the item at minIndex down the tree until heap order is restored
		percolateDown(0);
		// return the minimum item that was stored
		return min;
	}

	/**
	 * Orders a complete tree given one item has been deleted
	 * @param hole - the item to begin checking
	 * 
	 */
	private void percolateDown(int hole) {
		// TODO: check 
		int child;
		AnyType tmp = array[hole];
		for(; (hole*2)+1<=currentSize-1;hole =child)
		{
			//update child index
			child = hole*2+1;
			//Get minimum of the children
			if(child!=currentSize &&
					compare(array[child+1],array[child])<0){
				child++;
					}
			//compare minimum child to tmp
			//replace hole with selected child
			if(compare(array[child], tmp)<0){
				array[hole]=array[child];
			}else{
				break;
			}
		}
		//fill the hole with the tmp value
		array[hole]=tmp;
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
		//TODO: check
		if(x==null)
			throw new NullPointerException();
		if(array.length==currentSize){
			grow();
		}
		array[currentSize++]=x;	
		percolateUp(x);
		
	}
	
/**
 * Orders a complete tree by moving the last item (order unknown) up the tree
 */
	private void percolateUp(AnyType x) {
		//TODO: check
		int hole = currentSize-1;
		for(; compare(x, array[(hole-1)/2])<0;hole=(hole-1)/2){
			AnyType tmp = array[hole];
			array[hole]=array[(hole-1)/2];
			array[(hole-1)/2]=tmp;
		}
	}

	/**
	 * doubles the array size
	 * copies everything in the current binary heap 
	 */
	private void grow() {
		//TODO: check
		int new_size = array.length*2;
		AnyType [] new_array = (AnyType[]) new Object [new_size];
		int i =0;
		while(i<currentSize){
			new_array[i]=array[i++];
		}
		array=new_array;
		
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
