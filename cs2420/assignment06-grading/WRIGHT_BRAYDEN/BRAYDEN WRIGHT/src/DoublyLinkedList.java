package assignment06;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Brayden Wright (u0895942)
 */
// @SuppressWarnings("unchecked")
public class DoublyLinkedList<E> implements List<E>, Iterable<E> {
    private Node head, tail;
    private int size;

    /**
     * The requested empty constructor.
     */
    public DoublyLinkedList() {
        this.size = 0;
        this.head = new Node(null);
        this.tail = new Node(null);

        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    /**
     * Inserts the specified element at the beginning of the list.
     *
     * @param element - the element to insert
     */
    @Override
    public void addFirst(E element) {
        Node newNode = new Node(element);
        newNode.prev = this.head;
        newNode.next = this.head.next;

        this.head.next.prev = newNode;
        this.head.next = newNode;
        size++;
    }

    /**
     * Inserts the specified element at the end of the list.
     *
     * @param o - the element to insert
     */
    @Override
    public void addLast(E o) {
        Node newNode = new Node(o);
        newNode.next = this.tail;
        newNode.prev = this.tail.prev;

        this.tail.prev.next = newNode;
        this.tail.prev = newNode;
        size++;
    }

    /**
     * Inserts the specified element at the specified position in the list.
     *
     * @param index   - the position in the list to insert element
     * @param element - the element to insert
     * @throws IndexOutOfBoundsException if index is out of range (index < 0 || index > size())
     */
    @Override
    public void add(int index, E element) throws IndexOutOfBoundsException {

        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();

        Node current = tail;
        Node newNode = new Node(element);

        // Slight optimization
        if (index <= size / 2) {
            current = head;

            for (int count = 0; count < size; count++) {
                current = current.next;
                if (count == index) {
                    newNode.next = current;
                    newNode.prev = current.prev;

                    current.prev.next = newNode;
                    current.prev = newNode;
                    break;
                }
            }

            size++;
            return;  // Leave method
        }

        for (int count = size; count > 0; count--) {
            current = current.prev;
            if (count == index) {
                newNode.prev = current;
                newNode.next = current.next;

                current.next.prev = newNode;
                current.next = newNode;
                break;
            }
        }
        size++;
    }

    /**
     * Returns the first element in the list.
     *
     * @return the first element in the list
     * @throws NoSuchElementException if the list is empty
     */
    @Override
    public E getFirst() throws NoSuchElementException {
        if (isEmpty())
            throw new NoSuchElementException();

        return (E) head.next.data;
    }

    /**
     * Returns the last element in the list.
     *
     * @return the last element in the list
     * @throws NoSuchElementException if the list is empty
     */
    @Override
    public E getLast() throws NoSuchElementException {
        if (isEmpty())
            throw new NoSuchElementException();

        return (E) tail.prev.data;
    }

    /**
     * Returns the element at the specified position in the list.
     *
     * @param index - the index from which to get an element
     * @return the element at index
     * @throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
     */
    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        // Size only needs to be calculated once per method run
//        int size = size();

        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        // Small optimization
        if (index == size - 1)
            return getLast();

        Node current = tail;

        if (index <= size / 2) {
            current = head;

            for (int count = 0; count < size; count++) {
                current = current.next;
                if (count == index)
                    return (E) current.data;
            }
        }

        for (int count = size; count > 0; count--) {
            if (count == index)
                return (E) current.data;

            current = current.prev;
        }

        // Should never happen, but need something here...
        return null;
    }

    /**
     * Removes and returns the first element from the list.
     *
     * @return the first element in the list
     * @throws NoSuchElementException if the list is empty
     */
    @Override
    public E removeFirst() throws NoSuchElementException {
        if (head.next == tail)
            throw new NoSuchElementException("Cannot remove from empty list!");

        Node first = head.next;
        head.next = head.next.next;

        size--;
        return (E) first.data;
    }

    /**
     * Removes and returns the last element from the list.
     *
     * @return the last element in the list
     * @throws NoSuchElementException if the list is empty
     */
    @Override
    public E removeLast() throws NoSuchElementException {
        if (tail.prev == head)
            throw new NoSuchElementException("Cannot remove from empty list!");

        Node last = tail.prev;
        tail.prev = tail.prev.prev;

        size--;
        return (E) last.data;
    }

    /**
     * Removes and returns the element at the specified position in the list.
     *
     * @param index - the index from which to remove
     * @return the element at index
     * @throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
     */
    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        Node current = tail;

        if (index <= size / 2) {
            current = head;

            for (int count = 0; count < size; count++) {
                current = current.next;

                if (count == index) {
                    Node toRemove = current;
                    toRemove.next.prev = toRemove.prev;
                    toRemove.prev.next = toRemove.next;

                    size--;
                    return (E) toRemove.data;
                }
            }
        }

        for (int count = size; count > 0; count--) {
            current = current.prev;

            if (count == index) {
                Node toRemove = current;
                toRemove.next.prev = toRemove.prev;
                toRemove.prev.next = toRemove.next;

                size--;
                return (E) toRemove.data;
            }
        }

        //  Again, this should never happen, but something needs to be here...
        return null;
    }

    /**
     * Returns the index of the first occurrence of the specified element in the list.
     *
     * @param element - the element from which to find the index
     * @return the first occurrence of element, -1 if DNE
     */
    @Override
    public int indexOf(E element) {
        int index = 0;
        Node current = head;

        while (current.next != tail) {
            current = current.next;

            if (current.data.equals(element))
                return index;

            index++;
        }

        return -1;
    }

    /**
     * Returns the index of the last occurrence of the specified element in this list.
     *
     * @param element - the element from which to find the index
     * @return the last occurrence of element, -1 if DNE
     */
    @Override
    public int lastIndexOf(E element) {
        int index = size;
        Node current = tail;

        while (current.prev != head) {
            current = current.prev;

            if (current.data.equals(element))
                return --index;

            index--;
        }

        return -1;
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the size of the list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns true if this collection contains no elements.
     *
     * @return true if list is empty
     */
    @Override
    public boolean isEmpty() {
        return (head.next == tail);
    }

    /**
     * Removes all of the elements from this list.
     */
    @Override
    public void clear() {
        this.head.next = this.tail;
        this.tail.prev = this.head;
        size = 0;
    }

    /**
     * Returns an array containing all of the elements in this list
     *
     * @return the list as an array
     */
    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        Node current = head;

        for (int index = 0; index < size; index++) {
            current = current.next;
            array[index] = current.data;
        }

        return array;
    }

    /**
     * Returns an iterator of the elements in the list
     *
     * @return an iterator of the elements in the list
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node current = head;
            private boolean calledMove = false;
            private boolean calledRemove = false;

            @Override
            public boolean hasNext() {
                return current.next != tail;
            }

            @Override
            public E next() {
                if (hasNext()) {
                    current = current.next;
                    calledMove = true;
                    calledRemove = false;
                    return (E) current;
                }
                throw new NoSuchElementException("No more elements in the list!");
            }

            @Override
            public void remove() {
                if (!calledMove)
                    throw new IllegalStateException("Calling remove before next not allowed!");
                if (calledRemove)
                    throw new IllegalStateException("Consecutive remove calls not allowed!");

                if (current != head && current != tail) {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                } else if (current == head)
                    removeFirst();
                else
                    removeLast();

                calledRemove = true;
                size--;
            }
        };
    }
}
