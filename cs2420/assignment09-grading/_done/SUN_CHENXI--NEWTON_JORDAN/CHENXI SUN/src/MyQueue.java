package assignment09;
/**
 * Chenxi Sun and Jordan Newton
 * u0455173 and u1018840
 * 
 */
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class MyQueue<Vertex> implements Queue<Vertex> {

	private LinkedList<Vertex> queue;
	
	public static void main(String[] args){
		MyQueue<String> abc=new MyQueue<String>();
		abc.enqueue("taylor");
		System.out.println(abc.dequeue());
		
		
	}
	
	public MyQueue() {
		queue = new LinkedList<Vertex>();

	}

	@Override
	public int size() {

		return queue.size();
	}

	@Override
	public boolean isEmpty() {

		return queue.size()==0;
	}

	@Override
	public boolean contains(Object o) {

		return queue.contains((Vertex) o);
	}

	@Override
	public Iterator<Vertex> iterator() {

		return queue.iterator();
	}

	@Override
	public Object[] toArray() {

		return queue.toArray();
	}

	@Override
	public <Vertex> Vertex[] toArray(Vertex[] a) {

		return queue.toArray(a);
	}

	@Override
	public boolean remove(Object o) {

		return queue.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {

		return queue.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends Vertex> c) {

		return queue.addAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {

		return queue.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {

		return queue.retainAll(c);
	}

	@Override
	public void clear() {
		queue.clear();

	}

	@Override
	public boolean add(Vertex e) {

		return queue.add(e);
	}

	@Override
	public boolean offer(Vertex e) {

		return queue.add(e);
	}

	@Override
	public Vertex remove() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return queue.remove();
	}

	@Override
	public Vertex poll() {
		if (isEmpty()) {
			return null;
		}
		return queue.removeFirst();
	}

	@Override
	public Vertex element() {

		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return queue.getFirst();
	}

	@Override
	public Vertex peek() {
		if (isEmpty()) {
			return null;
		}
		return queue.getFirst();
	}
	
	/**
	 * enqueue the vertex to the front of a linkedlist
	 * 
	 * 
	 * @param Vertex
	 * @return void
	 * 
	 */
	public void enqueue(Vertex x){//
		 queue.addFirst(x);
	}
	
	/**
	 * 
	 * dequeue the vertex which means return the Vertex and delete the Vertex at the end of a linkedlist
	 * 
	 * 
	 * @return Vertex
	 */
	
	
	public Vertex dequeue(){
		return queue.removeLast();
	}

}
