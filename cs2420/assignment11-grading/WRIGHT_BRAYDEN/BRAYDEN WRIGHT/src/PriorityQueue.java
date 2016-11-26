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
 * @author Brayden Wright (u0895942)
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
     * (Runs in constant time.)
     *
     * @return the minimum item in this priority queue.
     * @throws NoSuchElementException if this priority queue is empty.
     */
    public AnyType findMin() throws NoSuchElementException {
        if (currentSize == 0) throw new NoSuchElementException();
        return array[0];
    }


    /**
     * Removes and returns the minimum item in this priority queue.
     * (Runs in logarithmic time.)
     *
     * @throws NoSuchElementException if this priority queue is empty.
     */
    public AnyType deleteMin() throws NoSuchElementException {

        // if the heap is empty, throw a NoSuchElementException
        if (currentSize == 0) throw new NoSuchElementException();

        // store the minimum item so that it may be returned at the end
        AnyType min = array[0];

        // replace the item at minIndex with the last item in the tree
        swap(0, currentSize - 1);
        array[--currentSize] = null;

        // percolate the item at minIndex down the tree until heap order is restored
        percolateDown();

        // return the minimum item that was stored
        return min;
    }


    /**
     * Adds an item to this priority queue.
     * (Runs in logarithmic time.)
     *
     * @param x -- the item to be inserted
     */
    public void add(AnyType x) {

        // add the new item to the next available node in the tree, so that
        // complete tree structure is maintained
        array[currentSize++] = x;

        // percolate the new item up the levels of the tree until heap order is restored
        percolateUp();

        // if the array is full, double its capacity
        if (currentSize >= array.length - 1) {
            AnyType[] temp = (AnyType[]) new Object[array.length * 2];
            for (int index = 0; index < currentSize; index++) {
                temp[index] = array[index];
            }
            array = temp;
        }
    }

    /**
     * What it says on the tin.  Called when adding something to the queue.
     */
    private void percolateUp() {
        int currentPos = currentSize - 1;  // Subtract 1 to align with the array indices
        int parentPos = getParentIndex(currentPos);

        while (compare(array[parentPos], array[currentPos]) > 0) {

            // Swap parent and child
            swap(parentPos, currentPos);

            // Update current and parent indices
            currentPos = parentPos;
            parentPos = getParentIndex(currentPos);
        }
    }

    /**
     * Also what it says on the tin.  Called when deleting the minimum.
     */
    private void percolateDown() {
        int currentPos = 0;

        while (array[getLeftChildIndex(currentPos)] != null) {
            int leftChild = getLeftChildIndex(currentPos);
            int rightChild = getRightChildIndex(currentPos);
            int smallestChild = rightChild;

            // If right child is null, or left child is smaller than right
            // set smallest child to the left
            if (array[rightChild] == null || compare(array[leftChild], array[rightChild]) < 0)
                smallestChild = leftChild;

            // Compare to smallest child and swap if necessary
            if (compare(array[currentPos], array[smallestChild]) > 0) {
                swap(currentPos, smallestChild);
                currentPos = smallestChild;
            } else break;
        }
    }

    /**
     * Get the parent index of a given index
     *
     * @param index <- whose child is this?
     * @return the child's parent
     */
    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    /**
     * Get a parent's left child.
     *
     * @param index - the parent
     * @return the parent's left child
     */
    private int getLeftChildIndex(int index) {
        return (index * 2) + 1;
    }

    /**
     * Get a parent's right child.
     *
     * @param index - the parent
     * @return the parent's right child
     */
    private int getRightChildIndex(int index) {
        return (index * 2) + 2;
    }

    /**
     * Swap items in the backing array at index1 and index2
     * @param index1 - the position of the first item
     * @param index2 - the position of the second item
     */
    private void swap(int index1, int index2) {
        AnyType temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
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
        for (int i = 0; i < currentSize; i++) {
            ret[i] = array[i];
        }
        return ret;
    }
}