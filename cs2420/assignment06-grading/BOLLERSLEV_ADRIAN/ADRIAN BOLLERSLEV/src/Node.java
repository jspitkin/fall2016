/*
 * @Author:Adrian Bollerslev
 * uid:u1115021
 */
package assignment06;

/**
 * Node of a doubly linked list, which stores a reference to its
 * element and to both the previous and next node in the list.
 */
public class Node<E> {

    private E current;               

    private Node<E> prev;          

    private Node<E> next;            

    /**
     * Creates a node 
     *
     * @param e  the element to be stored
     * @param p  previous node
     * @param n  next node
     */
    public Node(E c, Node<E> p, Node<E> n) {
        current = c;
        prev = p;
        next = n;
    }

    /**
     * 
     * @return the element stored inside
     */
    public E getElement() {
        return current;
    }

    /**
     * 
     * @return the previous node
     */
    public Node<E> getPrev() {
        return prev;
    }

    /**
     * 
     * @return the next node
     */
    public Node<E> getNext() {
        return next;
    }

    // Update methods
    /**
     * 
     * @param p   set previous node
     */
    public void setPrev(Node<E> p) {
        prev = p;
    }

    /**
     * Sets the node's next reference to point to Node n.
     * @param set next node
     */
    public void setNext(Node<E> n) {
        next = n;
    }
} 
