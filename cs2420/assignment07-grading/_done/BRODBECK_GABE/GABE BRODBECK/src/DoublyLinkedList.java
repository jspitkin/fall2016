package assignment06;
import java.util.Iterator;
//Gabe Brodbeck U0847035
import java.util.NoSuchElementException;







public class DoublyLinkedList<E> implements List<E> {

	Node<E> head=null;
	Node<E> last=null;
	int length=0;
	/**
	 * Creates an empty doubly linked list
	 */
	public DoublyLinkedList(){
		
	}
	@Override
	public void addFirst(E element) {
		// TODO Auto-generated method stub
		if(head==null){
			head=new Node<E>(element);
			last=head;
			length++;
		}
		else{
			
			Node<E>newNode= new Node<E>(element);
			newNode.next=head;
			head.previous=newNode;
			head=newNode;
			length++;
		}
	
	}

	@Override
	public void addLast(E o) {
		// TODO Auto-generated method stub
		if(head==null){
			head=new Node<E>(o);
			last=head;
			length++;
		}
		else{
			Node<E>temp= head;
			while(temp.hasNext()){
				temp=temp.next;
			}
			Node<E>toAdd= new Node<E>(o);
			temp.next=toAdd;
			toAdd.previous=temp;
			last= toAdd;
			length++;
		}
	}

	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		if(index>length){
			throw new IndexOutOfBoundsException("Error: Index out of bounds. Index:"+ index+ " Length:"+length);
		}
		if(index<0){
			throw new IndexOutOfBoundsException("Error: Index less than zero. Index:"+ index);
		}
		if(index==0){
			addFirst(element);
			return;
		}
		if(index==length){
			addLast(element);
			return;
		}
		Node<E>temp= head;
		for(int i=0;i<index-1;i++){
			temp=temp.next;
		}
		
		Node<E>toAdd= new Node<E>(element);
		toAdd.next=temp.next;
		if(toAdd.next!=null){
		toAdd.next.previous=toAdd;
		}
		toAdd.previous=temp;
		temp.next=toAdd;
		length++;
	}

	@Override
	public E getFirst() throws NoSuchElementException {
		if(head==null){
			throw new NoSuchElementException("Cannot get first. List is empty");
		}
		return head.getData();
	}

	@Override
	public E getLast() throws NoSuchElementException {
		if(head==null){
			throw new NoSuchElementException("Cannot get first. List is empty");
		}
		return last.getData();
	}

	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if(index>=length){
			throw new IndexOutOfBoundsException("Error: Index out of bounds. Index:"+ index+ " Length:"+length);
		}
		if(index<0){
			throw new IndexOutOfBoundsException("Error: Index less than zero. Index:"+ index);
		}
		Node<E> temp= head;
		for(int indAt=0;indAt<index;indAt++){
		temp=temp.next;
		}
		
		
		return temp.getData();
	}

	@Override
	public E removeFirst() throws NoSuchElementException {
		if(head==null){
			throw new NoSuchElementException("Cannot remove first. List is empty");
		}
		Node<E> temp= head;
		head=head.next;
		length--;
		return temp.data;
	}

	@Override
	public E removeLast() throws NoSuchElementException {
		if(last==null){
			throw new NoSuchElementException("Cannot remove last. List is empty");
		}
		if(length==1){
			Node<E> temp=head;
			head=null;
			last=null;
			return temp.data;
		}
		Node<E> temp= last;
		last=last.previous;
		length--;
		return temp.data;
	}

	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if(index>=length){
			throw new IndexOutOfBoundsException("Error: Index out of bounds. Index:"+ index+ " Length:"+length);
		}
		if(index<0){
			throw new IndexOutOfBoundsException("Error: Index less than zero. Index:"+ index);
		}
		if(index==0){
			return removeFirst();
		}
		if(index==length-1){
			return removeLast();
			
		}
		
		Node<E> temp= head;
		for(int indAt=0;indAt<index-1;indAt++){
		temp=temp.next;
		}
		Node<E> toReturn=temp.next;
		if(toReturn.next!=null){
			temp.next=temp.next.next;
			temp.next.previous=temp;
			
		}
		else{
			temp.next=null;
		}
		length--;
		return toReturn.data;
	}

	@Override
	public int indexOf(E element) {
		Node<E>temp= head;
		for(int indAt=0;indAt<length;indAt++){
	
		if(element==null){
			if(temp.data==null){
				return indAt;
			}
		}
		if((temp.data!=null)&&temp.data.equals(element)){
			return indAt;
		}
		temp=temp.next;
		}
		return -1;
	
	}

	@Override
	public int lastIndexOf(E element) {
		// TODO Auto-generated method stub
		Node<E>temp= last;
		for(int indAt=length-1;indAt>=0;indAt--){
		
			if(element==null){
				if(temp.data==null){
					return indAt;
				}
			}
			if((temp.data!=null)&&temp.data.equals(element)){
				return indAt;
			}
			temp=temp.previous;
		}
		return -1;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return length;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if(length==0){
			return true;
		}
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		head=null;
		length=0;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		Object[] array= new Object[length];
		if(length==0){
			return array;
		}
		
		Node<E>temp= head;
		for(int indAt=0;indAt<length;indAt++){
			array[indAt]=temp.getData();
			temp=temp.next;
		}
		return array;
	}

	/**
	 * @return Returns an iterator meant to navigate the current list.
	 */

	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		Iterator<E> iteratorOfThis= new Iterator<E>(){
			
			Node<E> currentSpot=head;
		@Override
		public boolean hasNext(){
			if(currentSpot==null){
				currentSpot=head;
				return false;
			}
			if(currentSpot.hasNext()){
				return true;
			}
			return false;
		}

		@Override
		public E next() {
			if(currentSpot==null){
				throw new NoSuchElementException("The iterator is on a spot which does not exist");
			}
			E temp=currentSpot.data;
			
			currentSpot=currentSpot.next;
			return temp;
		
		}
		@Override 
		public void remove(){
			if(currentSpot==null){
				throw new NoSuchElementException("The iterator is on a spot which does not exist");
			}
			if(currentSpot.previous!=null&&currentSpot.next!=null){
			currentSpot.previous.next=currentSpot.next;
			currentSpot.next.previous=currentSpot.previous;
			length--;
			}
			else
			if(currentSpot.previous!=null&&currentSpot.next==null){
				currentSpot.previous.next=null;
				currentSpot=currentSpot.previous;		
				length--;
			}
			else if(currentSpot.previous==null&&currentSpot.next==null){
				currentSpot=null;
				length--;
			}
			else if(currentSpot.previous==null&&currentSpot.next!=null){
				head=currentSpot.next;
				currentSpot.next.previous=null;
				currentSpot=currentSpot.next;
				length--;
			}
		}
		};
	
		return iteratorOfThis;
	
	}
}
