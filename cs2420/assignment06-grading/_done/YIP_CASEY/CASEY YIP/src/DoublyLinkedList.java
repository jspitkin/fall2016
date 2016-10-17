package assignment06;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementation of a doubly linked list which contains a head node and a tail
 * node. Traversing the list will be faster than a singly linked list if the
 * index we are searching for is on the second half of the list, since we can
 * start searching from the tail instead of the head.
 * 
 * @author Ho Lam Yip u1025709
 */
public class DoublyLinkedList<E> implements List<E>, Iterable<E>
{
    private Node head;
    private Node tail;
    private int listSize;
    
    public DoublyLinkedList ()
    {
        head = null;
        tail = null;
        listSize = 0;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void addFirst (E element)
    {
        add(0, element);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void addLast (E o)
    {
        add(listSize, o);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void add (int index, E element) throws IndexOutOfBoundsException
    {
        if (index < 0 || index > listSize)
        {
            throw new IndexOutOfBoundsException();
        }
        
        Node newNode = new Node();
        if (isEmpty())
        {
            head = newNode;
            tail = newNode;
            newNode.data = element;
        }
        else if (index == 0)
        {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
            newNode.data = element;
        }
        else if (index == listSize)
        {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
            newNode.data = element;
        }
        else
        {
            Node prevNode = indexSearch(index - 1);
            
            newNode.next = prevNode.next;
            newNode.prev = prevNode;
            newNode.next.prev = newNode;
            newNode.prev.next = newNode;
            newNode.data = element;
        }
        listSize++;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public E getFirst () throws NoSuchElementException
    {
        if (isEmpty())
        {
            throw new NoSuchElementException();
        }
        return head.data;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public E getLast () throws NoSuchElementException
    {
        if (isEmpty())
        {
            throw new NoSuchElementException();
        }
        return tail.data;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public E get (int index) throws IndexOutOfBoundsException
    {
        if (index < 0 || index > listSize)
        {
            throw new IndexOutOfBoundsException();
        }
        return indexSearch(index).data;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public E removeFirst () throws NoSuchElementException
    {
        if (isEmpty())
        {
            throw new NoSuchElementException();
        }
        return remove(0);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public E removeLast () throws NoSuchElementException
    {
        if (isEmpty())
        {
            throw new NoSuchElementException();
        }
        return remove(listSize - 1);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public E remove (int index) throws IndexOutOfBoundsException
    {
        if (index < 0 || index >= listSize)
        {
            throw new IndexOutOfBoundsException();
        }
        
        E element = null;
        if (listSize == 1)
        {
            element = head.data;
            clear();
            return element;
        }
        else if (index == 0)
        {
            element = head.data;
            head.next.prev = null;
            head = head.next;
        }
        else if (index == listSize - 1)
        {
            element = tail.data;
            tail.prev.next = null;
            tail = tail.prev;
        }
        else
        {
            element = indexSearch(index).data;
            indexSearch(index).next.prev = indexSearch(index).prev;
            indexSearch(index).prev.next = indexSearch(index).next;
        }
        listSize--;
        return element;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int indexOf (E element)
    {
        for (int i = 0; i < listSize; i++)
        {
            if (indexSearchFromHead(i).data.equals(element))
            {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int lastIndexOf (E element)
    {
        for (int i = listSize - 1; i >= 0; i--)
        {
            if (indexSearchFromTail(i).data.equals(element))
            {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int size ()
    {
        return listSize;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty ()
    {
        return listSize == 0;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void clear ()
    {
        head = null;
        tail = null;
        listSize = 0;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Object[] toArray ()
    {
        Object objArray[] = new Object[listSize];
        for (int i = 0; i < listSize; i++)
        {
            objArray[i] = indexSearch(i).data;
        }
        return objArray;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<E> iterator ()
    {
        Iterator<E> iterator = new Iterator<E>()
        {
            boolean elementRemoved = false;
            private int index = 0;
            
            @Override
            public boolean hasNext ()
            {
                return index + 1 < listSize;
            }
            
            @Override
            public E next () throws NoSuchElementException
            {
                if (index + 1 >= listSize)
                {
                    throw new NoSuchElementException();
                }
                index++;
                elementRemoved = false;
                return indexSearch(index).data;
            }
            
            public void remove () throws IndexOutOfBoundsException, IllegalStateException
            {
                if (!elementRemoved)
                {
                    if (index < 0 || index >= listSize)
                    {
                        throw new IndexOutOfBoundsException();
                    }
                    
                    if (listSize == 1)
                    {
                        clear();
                    }
                    else if (index == 0)
                    {
                        head.next.prev = null;
                        head = head.next;
                    }
                    else if (index == listSize - 1)
                    {
                        tail.prev.next = null;
                        tail = tail.prev;
                    }
                    else
                    {
                        indexSearch(index).next.prev = indexSearch(index).prev;
                        indexSearch(index).prev.next = indexSearch(index).next;
                    }
                    listSize--;
                    elementRemoved = true;
                }
                else
                {
                    throw new IllegalStateException();
                }
            }
        };
        return iterator;
    }
    
    /**
     * An inner class representing a Node object. A node has some data, a next node,
     * and a previous node.
     */
    private class Node
    {
        private E data;
        private Node next;
        private Node prev;
    }
    
    /**
     * A private searching method. Given the index, returns the node at that number.
     * If the index is smaller than or equal to half the size of the list, start the
     * search from head. Otherwise, start from tail.
     * 
     * @param index -- index of the node we want to find
     * @return node at specified index
     */
    private Node indexSearch (int index) throws IndexOutOfBoundsException
    {
        if (index < 0 || index >= listSize)
        {
            throw new IndexOutOfBoundsException();
        }
        
        Node target = null;
        if (listSize/2 >= index) // Start search from head
        {
            target = head;
            for (int i = 0; i < index; i++)
            {
                target = target.next;
            }
        }
        else // Start search from tail
        {
            target = tail;
            for (int i = listSize - 1; i > index; i--)
            {
                target = target.prev;
            }
        }
        return target;
    }
    
    /**
     * A special case of index search. This search only goes from head to tail.
     * 
     * @param index -- index of the node we want to find
     * @return node at specified index
     */
    private Node indexSearchFromHead (int index) throws IndexOutOfBoundsException
    {
        if (index < 0 || index >= listSize)
        {
            throw new IndexOutOfBoundsException();
        }
        
        Node target = head;
        for (int i = 0; i < index; i++)
        {
            target = target.next;
        }
        return target;
    }
    
    /**
     * A special case of index search. This search only goes from tail to head.
     * 
     * @param index -- index of the node we want to find
     * @return node at specified index
     */
    private Node indexSearchFromTail (int index) throws IndexOutOfBoundsException
    {
        if (index < 0 || index >= listSize)
        {
            throw new IndexOutOfBoundsException();
        }
        
        Node target = tail;
        for (int i = listSize - 1; i > index; i--)
        {
            target = target.prev;
        }
        return target;
    }
}
