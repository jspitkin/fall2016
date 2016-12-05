package assignment10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * A class that represents a HashTable with Separate Chaining collision control
 *
 * @author Thomas Osimitz U0970671
 */
public class ChainingHashTable implements Set<String> {

    private LinkedList<String>[] storage;
    private int capacity, itemCount;
    private HashFunctor functor;


    public ChainingHashTable(int _capacity, HashFunctor _functor) {
        capacity = _capacity;
        storage = (LinkedList<String>[]) new LinkedList[capacity];
        itemCount = 0;
        functor = _functor;
    }

    /**
     * Ensures that this set contains the specified item.
     *
     * @param item -
     *             the item whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is,
     * if the input item was actually inserted); otherwise, returns false
     */
    @Override
    public boolean add(String item) {
        FindObject toPlace = find(item);

        if(!toPlace.doesExist())
        {
            storage[toPlace.getLocation()].add(item);
            itemCount++;
            return true;
        }
        return false;
    }

    /**
     * Searches the hash table using QuadProbing to find either the location of the item or where it should go.
     * @param item - the item being searched for
     * @return - index of where the item should be
     * @implNote - Method calling this must check if the item at the index equals the item to find.
     * If it does, then the table contains the item, if the item at the index equals null then it doesn't contain
     * the item but should be inserted at that spot.
     */
    private FindObject find(String item) {
        int spotToCheck = functor.hash(item) % storage.length;
        if(storage[spotToCheck] == null) {
            storage[spotToCheck] = new LinkedList<String>();
        }
        for (String temp : storage[spotToCheck]) {
            if(temp.equals(item)) {
                return new FindObject(spotToCheck, true);
            }
        }
        return new FindObject(spotToCheck, false);
    }

    /**
     * Ensures that this set contains all items in the specified collection.
     *
     * @param items -
     *              the collection of items whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is,
     * if any item in the input collection was actually inserted);
     * otherwise, returns false
     */
    @Override
    public boolean addAll(Collection<? extends String> items) {
        boolean wasChanged = false;
        for (String currentItem : items) {
            if(this.add(currentItem)){
                wasChanged = true;
            }
        }
        return wasChanged;
    }

    /**
     * Removes all items from this set. The set will be empty after this method
     * call.
     */
    @Override
    public void clear() {
        storage = (LinkedList<String>[]) new LinkedList[capacity];
        itemCount = 0;
    }

    /**
     * Determines if there is an item in this set that is equal to the specified
     * item.
     *
     * @param item -
     *             the item sought in this set
     * @return true if there is an item in this set that is equal to the input
     * item; otherwise, returns false
     */
    @Override
    public boolean contains(String item) {
        return find(item).doesExist();
    }

    /**
     * Determines if for each item in the specified collection, there is an item
     * in this set that is equal to it.
     *
     * @param items -
     *              the collection of items sought in this set
     * @return true if for each item in the specified collection, there is an item
     * in this set that is equal to it; otherwise, returns false
     */
    @Override
    public boolean containsAll(Collection<? extends String> items) {
        for (String item : items) {
            if(!contains(item)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns true if this set contains no items.
     */
    @Override
    public boolean isEmpty() {
        return itemCount == 0;
    }

    /**
     * Returns the number of items in this set.
     */
    @Override
    public int size() {
        return itemCount;
    }

    /**
     * Taken from assignment09. Using for populating larger data sets in testing.
     *
     * Opens and extracts the data from a file into a list of strings.
     * One String per line. If the file does not exist it prints an error message.
     * @param filename - name/path to the file that is to be read in
     */
    public ArrayList<String> readFile(String filename) {
        ArrayList<String> toBeAdded = new ArrayList<String>();

        try (Scanner fileIn = new Scanner(new File(filename))) {
            while (fileIn.hasNextLine()) {
                String line = fileIn.nextLine();
                toBeAdded.add(line);
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            return null;
        }

        return toBeAdded;
    }
}
