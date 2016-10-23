package assignment07;
/**
 * @author Justin Baker, u0726589
 */
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<E> implements List<E>, Iterable<E> {
	protected DoublyLinkedListNode<E> header;
	protected DoublyLinkedListNode<E> tail;
	protected int size;

	DoublyLinkedList() {
		size = 0;
		header = new DoublyLinkedListNode<E>(null);
		tail = new DoublyLinkedListNode<E>(null);
	}

	@Override
	public void addFirst(E element) {
		// TODO Auto-generated method stub
		DoublyLinkedListNode<E> temp = new DoublyLinkedListNode<E>(element);
		if (size==0) {
			header.next = temp;
			tail.previous = temp;
			temp.previous=header;
			temp.next=tail;
			size++;
		} else {
			temp.next = header.next;
			header.next.previous = temp;
			header.next = temp;
			temp.previous=header;
			size++;
		}
	}

	@Override
	public void addLast(E o) {
		// TODO Auto-generated method stub
		DoublyLinkedListNode<E> temp = new DoublyLinkedListNode<E>(o);
		if (size==0) {
			header.next = temp;
			tail.previous = temp;
			temp.previous=header;
			temp.next=tail;
			size++;
		} else {
			tail.previous.next = temp;
			temp.previous = tail.previous;
			temp.next=tail;
			tail.previous=temp;
			size++;
		}
	}

	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		if (index == size) {
			addLast(element);
			return;
		}
		if (index == 0) {
			addFirst(element);
			return;
		}
		DoublyLinkedListNode<E> pointer = getNode(index);
		DoublyLinkedListNode<E> toAdd = new DoublyLinkedListNode<E>(element);
		pointer.previous.next = toAdd;
		toAdd.previous=pointer.previous;
		pointer.previous = toAdd;
		toAdd.next=pointer;
		size++;
	}

	@Override
	public E getFirst() throws NoSuchElementException {
		// TODO Auto-generated method stub
		if (size!=0) {
			return header.next.element;
		} else {
			throw new NoSuchElementException();
		}
	}

	@Override
	public E getLast() throws NoSuchElementException {
		// TODO Auto-generated method stub
		if (size != 0) {
			return tail.previous.element;
		} else {
			throw new NoSuchElementException();
		}
	}

	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		return getNode(index).element;
	}

	@Override
	public E removeFirst() throws NoSuchElementException {
		// TODO Auto-generated method stub
		if (size == 0) {
			throw new NoSuchElementException();

		}
		if (size == 1) {
			E temp = header.next.element;
			header.next = tail;
			tail.previous = header;
			size--;
			return temp;
		} else {
			E temp = header.next.element;
			header.next = header.next.next;
			header.next.previous = header;
			size--;
			return temp;
		}
	}

	@Override
	public E removeLast() throws NoSuchElementException {
		// TODO Auto-generated method stub
		if(size==0){
			throw new NoSuchElementException();
		}
		if(size==1){
			E temp = tail.previous.element;
			tail.previous = header;
			header.next=tail;
			size--;
			return temp;
		}
		else {
			E temp = tail.element;
			tail.previous = tail.previous.previous;
			tail.previous.next=tail;
			size--;
			return temp;
		}
	}

	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		if (index == size-1) {
			return removeLast();
		}
		if (index == 0) {
			return removeFirst();
		}
		DoublyLinkedListNode<E> toRemove = getNode(index);
		toRemove.previous.next = toRemove.next;
		toRemove.next.previous = toRemove.previous;
		size--;
		return toRemove.element;
	}

	@Override
	public int indexOf(E element) {
		Iterator<E> iterator = iterator();
		int i = 0;
		while (iterator.hasNext()) {
			if (iterator.next().equals(element)) {
				return i;
			}
			i++;
		}
		return -1;
	}

	@Override
	public int lastIndexOf(E element) {
		Iterator<E> iterator = iterator();
		int returnindex = -1;
		int index = 0;
		while (iterator.hasNext()) {
			if (iterator.next().equals(element)) {
				returnindex = index;
			}
			index++;
		}
		return returnindex;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void clear() {
		header.next = tail;
		tail.previous = header;
		size=0;
	}

	@Override
	public Object[] toArray() {
		Object[] return_array = new Object[size];
		Iterator<E> iterator = iterator();
		for (int i = 0; i < size; i++) {
			return_array[i] = iterator.next();
		}
		return return_array;
	}

	private DoublyLinkedListNode<E> getNode(int index) {
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		DoublyLinkedListIterator<E> iterator = new DoublyLinkedListIterator<>(header);
		for (int i = 0; i < index; i++) {
			iterator.next();
		}
		return iterator.current.next;
	}
	
	protected void decrementsize(){
		size--;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return new DoublyLinkedListIterator<E>(header);
	}
	
	class DoublyLinkedListIterator<E> implements Iterator<E> {

		protected DoublyLinkedListNode<E> current;
		private DoublyLinkedListNode<E> lastVisited;
		private boolean calledNext = false;

		DoublyLinkedListIterator(DoublyLinkedListNode<E> Node) {
			current = Node;
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return current.next.element != null;
		}

		@Override
		public E next() {
			// TODO Auto-generated method stub
			if(!hasNext()){
				throw new NoSuchElementException();
			}
			E nextItem = current.next.element;
			lastVisited=current;
			current = current.next;
			calledNext=true;
			return nextItem;
		}

		@Override
		public void remove() {
			if(calledNext){
				current.previous.next=current.next;
				current.next.previous=current.previous;
				current=current.previous;
				DoublyLinkedList.this.size--;
			}
			calledNext=false;
		}
	}
}


