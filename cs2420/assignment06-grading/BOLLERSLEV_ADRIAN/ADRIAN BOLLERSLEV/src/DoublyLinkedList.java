/*
 * @Author:Adrian Bollerslev
 * uid:u1115021
 */


package assignment06;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<E> implements List<E>,Iterator<E>{
	private Node<E> header;                    // beginning node
	
	private Node<E> trailer;                   //end node

	private int size = 0;
	//iterator current place and iterator remove boolean
	private Node<E> itercur;
	private Boolean itcanremove = true;
	
	public DoublyLinkedList() {
	    header = new Node<>(null, null, null);      
	    trailer = new Node<>(null, header, null);   
	    header.setNext(trailer);                    
	}
	/**
	 * Inserts the specified element at the beginning of the list.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public void addFirst(E element){
		if(header.getElement() == null ){ 
			Node<E> newh = new Node<E>(element, null,null);
	        header = newh;
	        trailer = newh;
	        size++;
		}
	    else{
	    	Node<E> newh = new Node<E>(element, null,header); 
	    	header.setPrev(newh);
	        header = newh;
	        size++;
	    }
	}
	/**
	 * Inserts the specified element at the end of the list.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public void addLast(E o) {
		if(isEmpty()){
			Node<E> newNode = new Node<E>(o, null,null);
			header = newNode;
			trailer = newNode;
			size++;
		}
		else{
			Node<E> newNode = new Node<E>(o, trailer,null);
			trailer.setNext(newNode);
	        trailer = newNode;
	        size++;
		}
	}
	/**
	 * Inserts the specified element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 || index > size())
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException {
		if(index < 0 || index > size()){
			throw new IndexOutOfBoundsException();
		}
		if(index == 0){
			addFirst(element);
		}
		else{
			Node<E> temp = header;
			int count = 0;
			while((count < index-1 )&& (temp.getNext() != null)){
				count++;
				temp = temp.getNext();
			}
			Node<E> insrt = new Node<E>(element, temp,temp.getNext());
			temp.setNext(insrt);
			size++;
		}
	}
	/**
	 * Returns the first element in the list.
	 * Throws NoSuchElementException if the list is empty.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public E getFirst() throws NoSuchElementException {
		return header.getElement();
	}

	/**
	 * Returns the last element in the list.
	 * Throws NoSuchElementException if the list is empty.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public E getLast() throws NoSuchElementException {
		return trailer.getElement();
	}
	/**
	 * Returns the element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if((index>=size)||index<0 ){
			throw new IndexOutOfBoundsException();
		}
		int count = 0;
		Node<E> current = header;
		
		while(current.getNext()!=null){
			current = current.getNext();
			count++;
			if(count == index){
				return current.getElement();
			}
		}
		return null;
	}
	/**
	 * Removes and returns the first element from the list.
	 * Throws NoSuchElementException if the list is empty.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public E removeFirst() throws NoSuchElementException {
		if(isEmpty()){
			throw new NoSuchElementException();
		}
		E nodeh = header.getElement();
		header = header.getNext();
		size--;
		header.setPrev(null);
		return nodeh;
	}
	/**
	 * Removes and returns the last element from the list.
	 * Throws NoSuchElementException if the list is empty.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public E removeLast() throws NoSuchElementException {
		if(isEmpty()){
			throw new NoSuchElementException();
		}
		E nodeh = trailer.getElement();
		trailer = trailer.getPrev();
		size--;
		trailer.setNext(null);
		return nodeh;
	}
	/**
	 * Removes and returns the element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		Node<E> current = header;
		int count=0;
        while ((current != null) && (count < index-1)){
        	current = current.getNext();
            count++;
        }
        if (current != null){
        	count--;  
        	if (current.getElement() != getFirst()){
        		current.getPrev().setNext(current.getNext());
            } 
        	else{
                header = current.getNext();
            }
            if (current.getElement() != getFirst()){
            	current.getNext().setPrev(current.getPrev());
            } 
            else{
                trailer = current.getPrev();
            }
                    
            return current.getElement();
        }
        return null;
	}
	/**
	 * Returns the index of the first occurrence of the specified element in the list, 
	 * or -1 if this list does not contain the element.
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public int indexOf(E element) {
		int count = 0;
		Node<E> current = header;
		
		while(current.getElement()!=null){
			if(current.getElement().equals(element)){
				return count;
			}
			current = current.getNext();
			count++;
		}
		return -1;
	}
	/**
	 * Returns the index of the last occurrence of the specified element in this list, 
	 * or -1 if this list does not contain the element.
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public int lastIndexOf(E element) {
		int count = 0;
		int temp = -1;
		Node<E> current = header;
		while(current.getElement()!=null){
			if(current.getElement().equals(element)){
				temp = count;
			}
			current = current.getNext();
			count++;
		}
		return temp;
	}
	/**
	 * Returns the number of elements in this list.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public int size() {
		return size;
	}
	/**
	 * Returns true if this collection contains no elements.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}
	/**
	 * Removes all of the elements from this list.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public void clear() {
		header = new Node<>(null, null, null);      // create header
	    trailer = new Node<>(null, header, null);   // trailer is preceded by header
	    header.setNext(trailer);
	    size = 0;
	}
	/**
	 * Returns an array containing all of the elements in this list in proper sequence 
	 * (from first to last element).
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public Object[] toArray() {
		Object[] ary = new Object[size()];
		Node<E> current = header;
		int count=0;
		while(count<=size()-1){
			ary[count] = current.getElement();
			count++;
			current = current.getNext();
		}
		return ary;
		/*
		ArrayList<E> ary = new ArrayList<E>();
		Node<E> current = header;
		while(current.getElement()!=null){
			ary.add(current.getElement());
			current = current.getNext();
			
		}
		// TODO Auto-generated method stub
		return (E[]) ary.toArray();
		*/
	}
	/**
	 * Returns true or false depending on whether the iterator can move forward
	 */
	@Override
	public boolean hasNext() {
		if(itercur!=null){
			return (itercur.getElement() != getLast());
		}
		return (itercur != getLast());
		
	}
	/**
	 * Moves the iterator forward one step and returns the new element it has moved too as well
	 */
	@Override
	public E next() throws NoSuchElementException {
		if(hasNext()){
			if(itercur == (null)){
				itercur = header;
				itcanremove = true;
				return itercur.getElement();
			}
			itercur = itercur.getNext();
			itcanremove = true;
			return itercur.getElement();
		}
		else{
			throw new IndexOutOfBoundsException();
		}
	}
	/**
	 * If remove has not been called since the last time next was called, the iterator will remove
	 * the current element and shift all of the items into the correct place
	 */
	@Override
	public void remove() throws IllegalStateException{
		if(itcanremove){
			if(itercur.equals(header)){
				if(itercur != null){
					itercur = itercur.getNext();
					removeFirst();
					
				}
			}
			else if(itercur.equals(getLast())){
				itercur = itercur.getPrev();
				removeLast();
				
			}
			else{
				Node<E> tempc = itercur.getPrev();
				itercur.getPrev().setNext(itercur.getNext());
				itercur.getNext().setPrev(tempc);
			}
			itcanremove = false;
		}
		else{
			throw new IllegalStateException();
		}
	}

}
