package assignment10;

import java.util.Collection;

/**
 * @author Brayden Wright (u0895942)
 */
public class QuadProbeHashTable implements Set<String> {
    private HashFunctor functor;
    private String[] backend;
    private int size;

    /**
     * Initialize the table with the passed values
     *
     * @param capacity - the minimum initial capacity
     * @param functor  - the hash functor to use
     */
    public QuadProbeHashTable(int capacity, HashFunctor functor) {

        // A simple loop to ensure we start with a prime number capacity.
        while (!isPrime(capacity)) capacity++;

        this.backend = new String[capacity];
        this.functor = functor;
        this.size = 0;
    }

    /**
     * Attempt to add an item to this table.
     *
     * @param item - the item whose presence is ensured in this set
     * @return true if this table changed as a result of calling this method, false otherwise
     */
    @Override
    public boolean add(String item) {
        int hash = functor.hash(item);
        int pos = hash % backend.length;

        // Just return false if item is already in this table
        if (contains(item)) return false;

        // Determine new position if a something already exists here
        int count = 0;
        while (backend[pos] != null) {
            count++;
            pos = (int) ((hash + Math.pow(count, 2)) % backend.length);
        }

        // Add to the table, increase size, resize if needed
        backend[pos] = item;
        size++;
        if (size >= backend.length / 2) resize();
        return true;
    }

    /**
     * Attempt to add all items from the passed collection to this table.
     *
     * @param items - the collection of items whose presence is ensured in this set
     * @return true if this table changed as a result of calling this method, false otherwise
     */
    @Override
    public boolean addAll(Collection<? extends String> items) {
        boolean changed = false;
        for (String item : items) {
            changed |= add(item); // ORs 'changed' with the result of adding the item.
        }
        return changed;
    }

    /**
     * A method to resize this table if it gets too full.
     * Sort of a slightly modified version of the above 'add' method.
     */
    private void resize() {
        String[] oldData = backend;
        int newCapacity = backend.length * 2;
        while (!isPrime(newCapacity)) newCapacity++;
        backend = new String[newCapacity];
        for (String item : oldData) {
            if (item == null) continue;

            int hash = functor.hash(item);
            int pos = hash % backend.length;
            int count = 0;
            while (backend[pos] != null) {
                count++;
                pos = (int) ((hash + Math.pow(count, 2)) % backend.length);
            }
            backend[pos] = item;
        }
    }

    /**
     * A simple check to see if a number is prime.
     * Used to help find a new prime capacity.
     *
     * @param num - the number to check
     * @return true if 'num' is prime, false otherwise
     */
    private boolean isPrime(int num) {
        int sqrt = (int) Math.sqrt(num);
        for (int i = 2; i <= sqrt; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Simply reset the size and backend array.
     */
    @Override
    public void clear() {
        backend = new String[13];
        size = 0;
    }

    /**
     * Check if this table contains 'item'
     *
     * @param item - the item sought in this set
     * @return true if 'item' exists in this table, false otherwise
     */
    @Override
    public boolean contains(String item) {
        int hash = functor.hash(item);
        int pos = hash % backend.length;
        int count = 0;
        if (backend[pos] == null) return false;
        while (backend[pos] != null) {
            if (backend[pos].equals(item)) return true;
            count++;
            pos = (int) ((hash + Math.pow(count, 2)) % backend.length);
        }
        return (backend[pos] != null);
    }

    /**
     * Check if the table contains all items from the passed collection.
     *
     * @param items - the collection of items sought in this set
     * @return true if all items are from the collection are contained within this table
     */
    @Override
    public boolean containsAll(Collection<? extends String> items) {
        boolean allContained = true;
        for (String item : items) {
            allContained &= contains(item); // ANDs 'allContained' against result of contains check
        }
        return allContained;
    }

    /**
     * @return true if the table is empty, otherwise false
     */
    @Override
    public boolean isEmpty() {
        return (this.size == 0);
    }

    /**
     * @return the size of the table
     */
    @Override
    public int size() {
        return this.size;
    }

}
