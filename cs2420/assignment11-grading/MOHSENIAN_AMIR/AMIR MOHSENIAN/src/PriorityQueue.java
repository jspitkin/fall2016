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
 * @author Amir Mohsenian
 * u0737564
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
	 * @return the minimum item in this priority queue.
	 * @throws NoSuchElementException if this priority queue is empty.
	 * 
	 * (Runs in constant time.)
	 */
	public AnyType findMin() throws NoSuchElementException {


		if (currentSize ==0)
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
	public AnyType deleteMin() throws NoSuchElementException {

		// if the heap is empty, throw a NoSuchElementException

		if (currentSize==0)
		{
			throw new NoSuchElementException();
		}


		// store the minimum item so that it may be returned at the end

		AnyType lowestItem;
		lowestItem = array[0];

		// replace the item at minIndex with the last item in the tree

		array[0] = array[currentSize-1];

		array[currentSize-1] = null; //Set position to null

		// update size

		currentSize = currentSize-1;

		// percolate the item at minIndex down the tree until heap order is restored
		// It is STRONGLY recommended that you write a percolateDown helper method!

		percolateDown(0);

		// return the minimum item that was stored

		return lowestItem;
	}


	/**
	 * percolateDown helper method
	 * @param i
	 */
	private void percolateDown(int index2)
    {
        if(index2==currentSize-1)
        {
            return;
        } 
        
        AnyType placeHolder;
        placeHolder=array[index2];
        AnyType leftSide;  
        AnyType rightSide;
        
        
        if(index2*2+1>currentSize)
        {
            leftSide=null;
        }
        else
        {
            leftSide=array[index2*2+1];
        }
        if(index2*2+2>currentSize)
        {
            rightSide=null;
        }
        else
        {
            rightSide=array[index2*2+2];
        }
        
        if(rightSide == null&&leftSide == null)
        {
            return;
        }
        if((leftSide!=null&&rightSide!=null))
        {
            if(compare(placeHolder,rightSide)<=0 &&compare(placeHolder,leftSide)<=0)
            {
                return;
            }
            else if(compare(rightSide,leftSide)<0)
            {
                array[index2]=array[index2*2+2];
                array[index2*2+2]=placeHolder;
                percolateDown(index2*2+2);     
            }
            else
            {
                array[index2]=array[index2*2+1];
                array[index2*2+1]=placeHolder;
                percolateDown(index2*2+1);
            }
        }
        else
        {
            if(leftSide==null&&compare(placeHolder,rightSide)<=0)
            {
                return;
            }
            else if(leftSide==null&&compare(placeHolder,rightSide)>0)
            {
                array[index2]=rightSide;
                array[index2*2+2]=placeHolder;
                percolateDown(2*index2+2);
                return;
            }
            if(rightSide==null&&compare(placeHolder,leftSide)<=0)
            {
                return;
            }
            else if(rightSide==null&&compare(placeHolder,leftSide)>0)
            {
                array[index2]=leftSide;
                array[index2*2+ 1]=placeHolder;
                percolateDown(index2*2+1);
                return;
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
	public void add(AnyType x) {
		// if the array is full, double its capacity
		
		// add the new item to the next available node in the tree, so that
		// complete tree structure is maintained

        if(x == null)
        {
            return;
        }
        if(currentSize == array.length)
        {
            AnyType[] placeHolder;
            placeHolder = (AnyType[]) new Object[array.length * 2];
            
            int index=0;
            while( index < currentSize)
            {
                placeHolder[index] = array[index];
                
                index=index+1;
            }
            array = placeHolder;
        }
        
    	// update size

		// percolate the new item up the levels of the tree until heap order is restored
		// It is STRONGLY recommended that you write a percolateUp helper method!
        array[currentSize] = x;
        percolateUp(currentSize);
        currentSize = currentSize+1;
		
		
	}

	/**
	 * percolateUp helper method
	 * 
	 * @param currentSize2
	 */
	private void percolateUp(int currentSize2) 
	{
        AnyType placeHolder;
        placeHolder=array[currentSize2];
        if(compare(placeHolder, array[(currentSize2 - 1)/2])>0 ||compare(placeHolder, array[(currentSize2 - 1)/2])==0)
        {
            return;
        }
        else
        {
            array[currentSize2]=array[(currentSize2-1)/2];
            array[(currentSize2-1)/2]=placeHolder;
            percolateUp((currentSize2-1)/2);
        }
		
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



	//LEAVE IN for grading purposes
	public Object[] toArray() {    
		Object[] ret = new Object[currentSize];
		for(int i = 0; i < currentSize; i++) {
			ret[i] = array[i];
		}
		return ret;
	}
}