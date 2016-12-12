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
 * @author Yixiong Qin
 */
@SuppressWarnings("unchecked")
public class PriorityQueue<AnyType> {
	// Declare variables
	private int currentSize;
	private AnyType[] array;
	private Comparator<? super AnyType> cmp;

	/**
	 * Constructs an empty priority queue. Orders elements according to their
	 * natural ordering (i.e., AnyType is expected to be Comparable) AnyType is
	 * not forced to be Comparable.
	 */
	
	public PriorityQueue() {
		// Initializing variables
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
		// Initializing variables in parameter passed constructor
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
		// Reset the queue
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
		// If the size of queue equals to 0, throw the NoSuchElementException
		if (this.currentSize == 0) {
			throw new NoSuchElementException();
		} else {
			// Otherwise, return the first element in the queue
			return this.array[0];
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
		// If the queue is empty, then throw the NoSuchElementException
		if (this.currentSize == 0) {
			throw new NoSuchElementException();
		} else {
			// If there is 1 element in the queue
			// Create an "AnyType" type variable and set the variable to the
			// first item in the queue
			if (this.currentSize == 1) {
				AnyType last = this.array[0];
				// Set the first element in the queue to null
				this.array[0] = null;
				
				this.currentSize--;
				return last;
			} else {
				
				AnyType last = this.array[0];
				// Set the first item in the queue to the last item in the queue
				this.array[0] = this.array[this.currentSize - 1];
				// Set the last item in the queue to null
				this.array[this.currentSize - 1] = null;
				
				this.currentSize--;
			
				this.percolateDown(0);
				return last;
			}
		}
	}

	/**
	 * Helper method in order to swap two items in the array
	 * 
	 * @param array
	 * @param left
	 * @param right
	 */
	private void swap(AnyType[] array, int left, int right) {
		AnyType temp = array[left];
		array[left] = array[right];
		array[right] = temp;
	}

	/**
	 * Helper method "percolateDown" which need to be used in remove item in the
	 * queue
	 * 
	 * @param index
	 */
	private void percolateDown(int index) {
		// If the index number times 2 plus 1 greater or equals to the current
		// array size
		// Return
		if (index * 2 + 1 >= this.currentSize) {
			return;
		} else {
			// Else, set the integers left and right by using formula
			int left = index * 2 + 1;
			int right = index * 2 + 2;
			// If the value of left less than current array size and the value
			// of right bigger or equals to current size
			if (left < this.currentSize && right >= this.currentSize) {
				// If the item at the index position bigger than item at the
				// left position
				if (this.compare(this.array[index], this.array[left]) > 0) {
					// Swap the index and left in the array
					swap(this.array, index, left);
					return;
				} else {
					return;
				}
			} else {
				// If the item at the left position smaller than right
				if (this.compare(this.array[left], this.array[right]) < 0) {
					// If the item at the index position greater than the item
					// at the left position
					if (this.compare(this.array[index], this.array[left]) > 0) {
						// Swap the index and left in the array
						swap(this.array, index, left);
						// Call the method "percolateDown" and pass the left as
						// parameter
						this.percolateDown(left);
					}
				} else {
					// Otherwise, if the item at the index position greater than
					// the item at the right position
					if (this.compare(this.array[index], this.array[right]) > 0) {
						// Swap the index and right in the array
						swap(this.array, index, right);
						// Call the method "percolateDown" and pass the right as
						// parameter
						this.percolateDown(right);
					}
				}
			}
		}
	}

	/**
	 * Helper method to resize the array
	 */
	@SuppressWarnings("unchecked")
	private void growSize() {
		// Set a new "AnyType" type array which has two times bigger size than
		// current array size
		AnyType[] temp = (AnyType[]) new Object[this.array.length * 2];
		// Add all elements from the current array to temp array
		for (int i = 0; i < this.array.length; i++) {
			temp[i] = this.array[i];
		}
		this.array = temp;
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
		// If the size equals to the current size of the array, then resize the
		// array
		if (this.size() == this.array.length) {
			this.growSize();
		}
		// If the current size equals to 0, set the currentSize position item to
		// x
		// then increase the size
		if (this.currentSize == 0) {
			array[this.currentSize] = x;
			this.currentSize++;
		} else {
			// Otherwise, call the method "percolateUp" method and pass the
			// currentSize as parameter
			// Then increase the size value
			array[this.currentSize] = x;
			percolateUp(this.currentSize);
			this.currentSize++;
		}
	}

	/**
	 * Helper method which need be used in add method
	 * 
	 * @param index
	 */
	private void percolateUp(int index) {
		// Create a variable called temp by using formula
		int temp = (index - 1) / 2;
		if (temp == 0) {
			// If the index position element smaller than temp position element
			// Swap the index and temp elements in the array and return
			if (this.compare(this.array[index], this.array[temp]) < 0) {
				swap(this.array, index, temp);
				return;
			} else {
				return;
			}
		} else {
			// If the index position element smaller the temp position element
			// Swap the index and temp position elements and call the
			// "percolateUp" method passing temp
			if (this.compare(this.array[index], this.array[temp]) < 0) {
				swap(this.array, index, temp);
				percolateUp(temp);
			} else {
				return;
			}
		}
	}

	/**
	 * Generates a DOT file for visualizing the binary heap.
	 */
	public void generateDotFile(String filename) {
		try {
			PrintWriter out = new PrintWriter(filename);
			out.println("digraph Heap {\n\tnode [shape=record]\n");

			for (int i = 0; i < currentSize; i++) {
				out.println("\tnode" + i + " [label = \"<f0> |<f1> " + array[i]
						+ "|<f2> \"]");
				if (((i * 2) + 1) < currentSize)
					out.println("\tnode" + i + ":f0 -> node" + ((i * 2) + 1)
							+ ":f1");
				if (((i * 2) + 2) < currentSize)
					out.println("\tnode" + i + ":f2 -> node" + ((i * 2) + 2)
							+ ":f1");
			}
			out.println("}");
			out.close();
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
		if (cmp == null)
			return ((Comparable<? super AnyType>) lhs).compareTo(rhs); // safe
																		// to
																		// ignore
																		// warning
		// We won't test your code on non-Comparable types if we didn't supply a
		// Comparator
		return cmp.compare(lhs, rhs);
	}

	// LEAVE IN for grading purposes
	public Object[] toArray() {
		Object[] ret = new Object[currentSize];
		for (int i = 0; i < currentSize; i++)
			ret[i] = array[i];
		return ret;
	}
}