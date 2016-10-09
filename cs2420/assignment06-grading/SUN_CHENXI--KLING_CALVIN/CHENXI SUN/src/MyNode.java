package assignment06;
/**
 * @arthur Chenxi Sun
 * @Uid u0455173
 * */
public class MyNode<E> {
public MyNode<E> prev;
public MyNode<E> next;
public E data;
	
	public MyNode(E item, MyNode<E> Prev,MyNode<E> Next){
		data=item;
		prev=Prev;
		next=Next;
		
		
	}
	
	
}
