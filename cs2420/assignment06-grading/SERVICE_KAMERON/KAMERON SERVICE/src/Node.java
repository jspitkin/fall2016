package assignment06;

public class Node<E> {
		
		public E item;
		public Node<E> next;
		public Node<E> prev;
		
		public Node(E _item, Node<E> _prev, Node<E> _next){
			item = _item;
			prev = _prev;
			next = _next;
			
		}

}
