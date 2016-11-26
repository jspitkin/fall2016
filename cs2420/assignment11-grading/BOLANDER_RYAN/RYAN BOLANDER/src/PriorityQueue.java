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
 * @author 
 */
@SuppressWarnings("unchecked")
public class PriorityQueue<AnyType> 
{

	private int currentSize;

	private AnyType[] array;

	private Comparator<? super AnyType> cmp;

	/**
	 * Constructs an empty priority queue. Orders elements according
	 * to their natural ordering (i.e., AnyType is expected to be Comparable)
	 * AnyType is not forced to be Comparable.
	 */

	public PriorityQueue() 
	{
		currentSize = 0;
		cmp = null;
		array = (AnyType[]) new Object[10]; // safe to ignore warning
	}

	/**
	 * Construct an empty priority queue with a specified comparator.
	 * Orders elements according to the input Comparator (i.e., AnyType need not
	 * be Comparable).
	 */
	public PriorityQueue(Comparator<? super AnyType> c) 
	{
		currentSize = 0;
		cmp = c;
		array = (AnyType[]) new Object[10]; // safe to ignore warning
	}

	/**
	 * @return the number of items in this priority queue.
	 */
	public int size() 
	{
		return currentSize;
	}

	/**
	 * Makes this priority queue empty.
	 */
	public void clear() 
	{
		currentSize = 0;
	}

	/**
	 * @return the minimum item in this priority queue.
	 * @throws NoSuchElementException if this priority queue is empty.
	 * 
	 * (Runs in constant time.)
	 */
	public AnyType findMin() throws NoSuchElementException 
	{
		if(currentSize == 0)
		{
			throw new NoSuchElementException();
		}
		else
		{
			return array[0];
		}
	}


	/**
	 * Removes and returns the minimum item in this priority queue.
	 * 
	 * @throws NoSuchElementException if this priority queue is empty.
	 * 
	 * (Runs in logarithmic time.)
	 */
	public AnyType deleteMin() throws NoSuchElementException 
	{
		if(currentSize == 0)
		{
			throw new NoSuchElementException();
		}
		else
		{
			AnyType[] minItem = (AnyType[]) new Object[1];
			minItem[0] = array[0];

			array[0] = array[currentSize - 1];
			currentSize--;

			percolateDown(0);

			return minItem[0];
		}
	}

	/**
	 * Percolate down the graph/tree and continue swapping until structure of tree is fixed after deletion
	 * @param currentIndex - Current index to compare against its children to check to see where it should be swapped
	 */
	private void percolateDown(int currentIndex)
	{
		boolean swapped = true;
		
		while(swapped)
		{
			if(leftChildIndex(currentIndex) > currentSize - 1 || rightChildIndex(currentIndex) > currentSize - 1)
			{
				swapped = false;
			}
			else if(compare(array[leftChildIndex(currentIndex)], array[rightChildIndex(currentIndex)]) < 0)
			{
				array[currentSize] = array[currentIndex];
				array[currentIndex] = array[leftChildIndex(currentIndex)];
				array[leftChildIndex(currentIndex)] = array[currentSize];
				array[currentSize] = null;
				currentIndex = leftChildIndex(currentIndex);
			}
			else
			{
				array[currentSize] = array[currentIndex];
				array[currentIndex] = array[rightChildIndex(currentIndex)];
				array[rightChildIndex(currentIndex)] = array[currentSize];
				array[currentSize] = null;
				currentIndex = rightChildIndex(currentIndex);
			}
		}
	}


	/**
	 * Adds an item to this priority queue.
	 * 
	 * (Runs in logarithmic time.) Can sometimes terminate early.
	 * 
	 * @param x -- the item to be inserted
	 */
	public void add(AnyType x) 
	{
		if(currentSize == 0)
		{
			array[0] = x;
			currentSize++;
			return;
		}
		
		if(currentSize == array.length)
		{
			AnyType[] tempArray = (AnyType[]) new Object[(currentSize - 1) * 2];
			for(int i = 0; i < currentSize; i++)
			{
				tempArray[i] = array[i];
			}
			
			array = tempArray;
		}
		
		array[currentSize] = x;
		currentSize++;
		
		percolateUp(currentSize - 1);
	}
	
	/**
	 * Percolate up the tree/graph and swapping until graph/tree is fixed to specifications
	 * @param currentIndex - current index to be compared to parent index
	 */
	private void percolateUp(int currentIndex)
	{
		boolean swapped = true;
		AnyType[] tempArray = (AnyType[]) new Object[1];
		
		while(swapped)
		{
			if(compare(array[currentIndex], array[parentIndex(currentIndex)]) < 0)
			{
				tempArray[0] = array[currentIndex];
				array[currentIndex] = array[parentIndex(currentIndex)];
				array[parentIndex(currentIndex)] = tempArray[0];
				currentIndex = parentIndex(currentIndex);
			}
			else
			{
				swapped = false;
			}
		}
	}

	/**
	 * Generates a DOT file for visualizing the binary heap.
	 */
	public void generateDotFile(String filename) 
	{
		try(PrintWriter out = new PrintWriter(filename)) 
		{
			out.println("digraph Heap {\n\tnode [shape=record]\n");

			for(int i = 0; i < currentSize; i++) 
			{
				out.println("\tnode" + i + " [label = \"<f0> |<f1> " + array[i] + "|<f2> \"]");
				if(((i*2) + 1) < currentSize)
					out.println("\tnode" + i + ":f0 -> node" + ((i*2) + 1) + ":f1");
				if(((i*2) + 2) < currentSize)
					out.println("\tnode" + i + ":f2 -> node" + ((i*2) + 2) + ":f1");
			}
			out.println("}");
		} 
		catch (IOException e) 
		{
			System.out.println(e);
		}
	}

	/**
	 * Internal method for comparing lhs and rhs using Comparator if provided by the
	 * user at construction time, or Comparable, if no Comparator was provided.
	 */
	private int compare(AnyType lhs, AnyType rhs) 
	{
		if (cmp == null) 
		{
			return ((Comparable<? super AnyType>) lhs).compareTo(rhs); // safe to ignore warning
		}
		// We won't test your code on non-Comparable types if we didn't supply a Comparator

		return cmp.compare(lhs, rhs);
	}

	/**
	 * Get the left child index of incoming index
	 * @param i - current index
	 * @return - left child
	 */
	private int leftChildIndex(int i)
	{
		return (i * 2) + 1;
	}

	/**
	 * Get the right child index of incoming index
	 * @param i - current index
	 * @return - right child
	 */
	private int rightChildIndex(int i)
	{
		return (i * 2) + 2;
	}

	/**
	 * Get the parent index of incoming index
	 * @param i - current index
	 * @return - parent index
	 */
	private int parentIndex(int i)
	{
		return (i - 1) / 2;
	}

	//LEAVE IN for grading purposes
	public Object[] toArray() 
	{    
		Object[] ret = new Object[currentSize];
		for(int i = 0; i < currentSize; i++) 
		{
			ret[i] = array[i];
		}
		return ret;
	}
}