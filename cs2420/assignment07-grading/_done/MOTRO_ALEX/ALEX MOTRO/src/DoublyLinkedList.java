package assignment06;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class DoublyLinkedList<E> implements List<E>, Iterable<E>{
	private int size = 0;
	private LinkedNode head = null;
	private LinkedNode end = null;
	
	public DoublyLinkedList(){
	
	}
	@Override
	public void addFirst(E element) {
		if (size ==0){
			head = new LinkedNode(element);
			end = head;
		}
		else{
			head.prev = new LinkedNode(element, null, head);
			head = head.prev;
		}
		size++;
	}

	@Override
	public void addLast(E o) {
		if (size == 0){
			end = new LinkedNode(o);
			head = end;
		}
		else{
			end.next = new LinkedNode(o, end, null);
			end = end.next;
		}
		size++;
	}

	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException {
		if(index<0 || index>size){
			throw new IndexOutOfBoundsException();
		}
		if(size ==0){
			addFirst(element);
		}
		else if(index == size){
			addLast(element);
		}
		else{
			int place = 0;
			LinkedNode temp = head;
			while(place<index){
				place++;
				temp = temp.next;
			}
			temp.prev.next= new LinkedNode(element, temp.prev, temp);
			temp.prev = temp.prev.next;
			size++;
		}
		
	}

	@Override
	public E getFirst() throws NoSuchElementException {
		if(size == 0){
			throw new NoSuchElementException();
		}
		else{
			return head.nodeData;
		}
	}

	@Override
	public E getLast() throws NoSuchElementException {
		if(size == 0){
			throw new NoSuchElementException();
		}
		else{
			return end.nodeData;
		}
	}

	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if(index<0 || index>size){
			throw new IndexOutOfBoundsException();
		}
		int place = 0;
		LinkedNode temp = head;
		//goes through each node till it hits the index
		while (place < index){
			place ++;
			temp = temp.next;
		}
		return temp.nodeData;
	}

	@Override
	public E removeFirst() throws NoSuchElementException {
		if(size == 0){
			throw new NoSuchElementException();
		}
		LinkedNode temp = head;
		if(size == 1){
			head = null;
			end = null;
		}
		else{
			head.next.prev = null;
			head = head.next;
		}
		size--;
		return temp.nodeData;
	}

	@Override
	public E removeLast() throws NoSuchElementException {
		if(size == 0){
			throw new NoSuchElementException();
		}
		LinkedNode temp = end;
		if(size == 1){
			head = null;
			end = null;
		}
		else{
			end.prev.next = null;
			end = end.prev;
		}
		size--;
		return temp.nodeData;
	}

	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if(index<0 || index>size){
			throw new IndexOutOfBoundsException();
		}
		if (size == 1){
			return removeFirst();
		}
		int place = 0;
		LinkedNode temp = head;
		while (place<index){
			place++;
			temp = temp.next;
		}
		LinkedNode removeThis = temp;
		if (index > 0){
			temp.prev.next = temp.next;
		}
		else{
			head = temp.next;
		}
		
		if(index<(size-1)){
			temp.next.prev = temp.prev;
		}
		else{
			end = temp.prev;
		}
		size --;
		return removeThis.nodeData;
	}

	@Override
	public int indexOf(E element) {
		LinkedNode itemAtIndex = head;
		for (int index = 0; index<size; index++){
			if (itemAtIndex.nodeData == element){
				return index;
			}
			itemAtIndex = itemAtIndex.next;
		}
		return -1;
	}

	@Override
	public int lastIndexOf(E element) {
		int spot = -1;
		LinkedNode itemAtIndex = head;
		for (int index = 0; index<size; index++){
			if(itemAtIndex.nodeData == element){
				spot = index;
			}
			itemAtIndex = itemAtIndex.next;
		}
		return spot;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return(size == 0);
	}

	@Override
	public void clear() {
		head = null;
		end = null;
		size = 0;
	}

	@Override
	public Object[] toArray() {
		Object[] linkedListItems = new Object[size];
		LinkedNode itemAtIndex = head;
		for (int index = 0; index<size; index++){
			linkedListItems[index] = itemAtIndex.nodeData;
			itemAtIndex = itemAtIndex.next;
		}
		return linkedListItems;
	}
	
	private class LinkedNode{
		E nodeData;
		public LinkedNode next = null;
		public LinkedNode prev = null;
		
		public LinkedNode(E info){
			nodeData = info;
		}
		
		public LinkedNode(E info, LinkedNode newPrev, LinkedNode newNext) {
			nodeData = info;
			prev = newPrev;
			next = newNext;
		}
	}

	@Override
	public Iterator<E> iterator() {
		return  new DoublyLinkedListIterator();
	}
	
	private class DoublyLinkedListIterator implements Iterator<E>{
		LinkedNode current = head;
		
		public DoublyLinkedListIterator(){
			
		}

		@Override
		public boolean hasNext() {
			if(current == null){
				return false;
			}
			else{
				if(size == 1){
					return true;
				}
				return(current.next != null);
			}
		}

		@Override
		public E next() {
			if (this.hasNext()){
				LinkedNode nextReturn = current;
				current = current.next;
				return nextReturn.nodeData;
			}
			throw new NoSuchElementException();
		}
		
		@Override
		public void remove(){
			if(size == 0 || current == head){
				throw new UnsupportedOperationException();
			}
			if(size == 1){
				current = head = end = null;
			}
			else{
				current.prev.prev.next = current;
				current.prev = current.prev.prev;
			}
		}
		
	}

}
