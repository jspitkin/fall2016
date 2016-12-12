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
 * @author Savannah Simmons, u1086770
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
	 * Returns the index of the left child of the node at the given index
	 * @param idx
	 * @return index of left child - possibly out of range
	 */
	public int leftChildIndex(int idx) {
		return (idx*2) + 1;
	}
	
	/**
	 * Returns the index of the right child of the node at the given index
	 * @param idx
	 * @return index of right child - possibly out of range
	 */
	public int rightChildIndex(int idx) {
		return (idx*2) + 2;
	}
	
	/**
	 * Returns the index of the parent of the node at the given index
	 * @param idx
	 * @return index of parent - possibly out of range
	 */
	public int parentIndex(int idx) {
		return (idx-1) / 2;
	}

	/**
	 * @return the minimum item in this priority queue.
	 * @throws NoSuchElementException if this priority queue is empty.
	 * 
	 * (Runs in constant time.)
	 */
	public AnyType findMin() throws NoSuchElementException {		
		//If the queue is empty then there is no minimum
		if(currentSize == 0){
			throw new NoSuchElementException();
		}
		
		//Otherwise return first element, or root of tree
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
		//If the queue is empty, then there is no minimum
		if(currentSize == 0){
			throw new NoSuchElementException();
		}
		
		//Save minimum to return when finished
		AnyType min = array[0];
		
		//Move last node on tree to root
		array[0] = array[currentSize - 1];

		//Decrement size
		currentSize--;
		
		//Until we no longer need to percolate, swap the moved node down until it's in its proper place
		int currentIdx = 0;
		int newIdx = percolateDown(currentIdx);
		while(newIdx != currentIdx){
			currentIdx = newIdx;
			newIdx = percolateDown(currentIdx);
		}
		
		return min;
	}
	
	/**
	 * Evaluates if a parent node and its children satisfy the ordering constraints of the minimum heap.
	 * If they do not, the parent and its two children are rearranged so that they do.
	 * @param idx
	 * @return new index of item originally at given index
	 */
	private int percolateDown(int idx){
		AnyType parent = array[idx];
		int leftChildIdx = leftChildIndex(idx);
		int rightChildIdx = rightChildIndex(idx);
		int minIdx;
		
		//Then we are at a leaf
		if (leftChildIdx > currentSize){
			return idx;
		}
		
		//If we have a right child and it is less than the left child, then it is the minimum of the two
		if (rightChildIdx < currentSize && compare(array[leftChildIdx], array[rightChildIdx]) > 0){
			minIdx = rightChildIdx;
		
		//If there is no right child, or if there is one but it is not larger than the left child,
		//then the left child is the minimum
		} else {
			minIdx = leftChildIdx;
		}
		
	
		AnyType minChild = array[minIdx];
		
		//If the node we're examining is greater than the minimum of its children, swap them
		if (compare(parent, minChild) > 0){
			array[idx] = minChild;
			array[minIdx] = parent;
			return minIdx;
		} else{
			// Already in order
			return idx;
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
		// if the array is full, double its capacity
		if (currentSize == array.length){
			doubleCapacity();
		}
		
		// add the new item to the next available node in the tree, so that
		// complete tree structure is maintained
		array[currentSize] = x;

		// update size
		currentSize++;

		// percolate the new item up the levels of the tree until heap order is restored	
		int currentIdx = currentSize - 1;
		int newIdx = percolateUp(currentIdx);
		while(currentIdx != newIdx){
			currentIdx = newIdx;
			newIdx = percolateUp(currentIdx);
		}

	}
	/**
	 * Checks if a given node is smaller than its parent. If so, swaps them.
	 * @param idx
	 * @return new index of item originally at given index
	 */
	private int percolateUp(int idx){
		AnyType child = array[idx];
		int parentIdx = parentIndex(idx);
		
		//Then we are at root and have traversed the height of the tree
		//i.e. item must be in place
		if (parentIdx < 0){
			return idx;
		}
		
		AnyType parent = array[parentIdx];
		
		//If the child is smaller than the parent, swap them
		if (compare(child, parent) < 0){
			array[idx] = parent;
			array[parentIdx] = child;
			return parentIdx;
		
		//Otherwise they are ordered properly and no moving is necessary
		} else{
			return idx;
		}

	}
	
	/**
	 * Doubles the size of the backing array
	 */
	private void doubleCapacity(){
		AnyType[] newArray = (AnyType[]) new Object[array.length * 2];
		
		//Copy current backing array into array twice the size
		for(int idx = 0; idx < array.length; idx++){
			newArray[idx] = array[idx];
		}
		
		//Set backing array to new array
		this.array = newArray;
		
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
	 * Returns an array containing all of the elements in the heap in ascending order
	 * @return sortedArray
	 */
	public AnyType[] sortedArray(){
		AnyType[] sortedArray = (AnyType[]) new Object[currentSize];
		AnyType[] copy =  array.clone();
		
		int sizeCopy = currentSize;
		for (int idx = 0; idx < sizeCopy; idx++){
			sortedArray[idx] = deleteMin();
		}
		
		array = copy;
		currentSize = sizeCopy;
		
		return sortedArray;
	}
	
	/**
	 * Returns a string representation of the sorted array of elements in the heap
	 * @return sortedArray.toString
	 */
	public String sortedToString(){
		String string = "[ ";
		for (AnyType item : sortedArray()){
			string+= item + " ";
		}
		
		string += "]";
		
		return string;
	}



	//LEAVE IN for grading purposes
	public Object[] toArray() {    
		Object[] ret = new Object[currentSize];
		for(int i = 0; i < currentSize; i++) {
			ret[i] = array[i];
		}
		return ret;
	}
}