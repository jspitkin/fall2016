package assignment11;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * Represents a priority queue of generically-typed items. The queue is implemented as a min heap. The min heap is
 * implemented implicitly as an array.
 * 
 * @author Ho Lam Yip u1025709
 */
@SuppressWarnings("unchecked")
public class PriorityQueue<AnyType>
{
    private int currentSize;
    private AnyType[] array;
    private Comparator<? super AnyType> cmp;
    
    /**
     * Constructs an empty priority queue. Orders elements according to their natural ordering (i.e., AnyType is
     * expected to be Comparable) AnyType is not forced to be Comparable.
     */
    public PriorityQueue ()
    {
        currentSize = 0;
        cmp = null;
        array = (AnyType[]) new Object[10];
    }
    
    /**
     * Construct an empty priority queue with a specified comparator. Orders elements according to the input Comparator
     * (i.e., AnyType need not be Comparable).
     */
    public PriorityQueue (Comparator<? super AnyType> c)
    {
        currentSize = 0;
        cmp = c;
        array = (AnyType[]) new Object[10];
    }
    
    /**
     * @return the number of items in this priority queue.
     */
    public int size ()
    {
        return currentSize;
    }
    
    /**
     * Makes this priority queue empty.
     */
    public void clear ()
    {
        currentSize = 0;
    }
    
    /**
     * @return the minimum item in this priority queue.
     * @throws NoSuchElementException if this priority queue is empty.
     * 
     *             (Runs in constant time.)
     */
    public AnyType findMin () throws NoSuchElementException
    {
        if (currentSize == 0)
        {
            throw new NoSuchElementException();
        }
        return array[0];
    }
    
    /**
     * Removes and returns the minimum item in this priority queue.
     * 
     * @throws NoSuchElementException if this priority queue is empty.
     * 
     *             (Runs in logarithmic time.)
     */
    public AnyType deleteMin () throws NoSuchElementException
    {
        if (currentSize == 0)
        {
            throw new NoSuchElementException();
        }
        if (currentSize == 1)
        {
            clear();
        }
        
        AnyType min = array[0];
        array[0] = array[currentSize - 1];
        array[currentSize - 1] = null;
        currentSize--;
        
        int currentIndex = 0;
        while (currentIndex * 2 + 1 < currentSize)
        {
            if (compare(array[currentIndex], array[currentIndex * 2 + 1]) > 0 || compare(array[currentIndex], array[currentIndex * 2 + 2]) > 0)
            {
                if (compare(array[currentIndex * 2 + 1], array[currentIndex * 2 + 2]) > 0)
                {
                    AnyType temp = array[currentIndex];
                    array[currentIndex] = array[currentIndex * 2 + 2];
                    array[currentIndex * 2 + 2] = temp;
                    currentIndex = currentIndex * 2 + 2;
                }
                else
                {
                    AnyType temp = array[currentIndex];
                    array[currentIndex] = array[currentIndex * 2 + 1];
                    array[currentIndex * 2 + 1] = temp;
                    currentIndex = currentIndex * 2 + 1;
                }
            }
        }
        
        return min;
    }
    
    /**
     * Adds an item to this priority queue.
     * 
     * (Runs in logarithmic time.) Can sometimes terminate early.
     * 
     * @param x -- the item to be inserted
     */
    public void add (AnyType x)
    {
        if (currentSize == array.length)
        {
            AnyType[] tempArray = (AnyType[]) new Object[array.length * 2];
            for (int i = 0; i < array.length; i++)
            {
                tempArray[i] = array[i];
            }
            array = tempArray;
        }
        
        int currentIndex = currentSize;
        array[currentIndex] = x;
        
        while (currentIndex != 0 && compare(array[currentIndex], array[(currentIndex - 1) / 2]) < 0)
        {
            AnyType temp = array[currentIndex];
            array[currentIndex] = array[(currentIndex - 1) / 2];
            array[(currentIndex - 1) / 2] = temp;
            currentIndex = (currentIndex - 1) / 2;
        }
        
        currentSize++;
    }
    
    /**
     * Generates a DOT file for visualizing the binary heap.
     */
    public void generateDotFile (String filename)
    {
        try (PrintWriter out = new PrintWriter(filename))
        {
            out.println("digraph Heap {\n\tnode [shape=record]\n");

            for (int i = 0; i < currentSize; i++)
            {
                out.println("\tnode" + i + " [label = \"<f0> |<f1> " + array[i] + "|<f2> \"]");
                if (((i * 2) + 1) < currentSize)
                    out.println("\tnode" + i + ":f0 -> node" + ((i * 2) + 1) + ":f1");
                if (((i * 2) + 2) < currentSize)
                    out.println("\tnode" + i + ":f2 -> node" + ((i * 2) + 2) + ":f1");
            }
            out.println("}");
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
    }
    
    /**
     * Internal method for comparing lhs and rhs using Comparator if provided by the user at construction time, or
     * Comparable, if no Comparator was provided.
     */
    private int compare (AnyType lhs, AnyType rhs)
    {
        if (cmp == null)
        {
            return ((Comparable<? super AnyType>) lhs).compareTo(rhs);
        }
        
        return cmp.compare(lhs, rhs);
    }
    
    public Object[] toArray ()
    {
        Object[] ret = new Object[currentSize];
        for (int i = 0; i < currentSize; i++)
        {
            ret[i] = array[i];
        }
        return ret;
    }
}
