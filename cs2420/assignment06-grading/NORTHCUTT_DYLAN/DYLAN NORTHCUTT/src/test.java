package assignment06;

public class test {

	public static <E> void main(String[] args) {
		DoublyLinkedList<E> newList;
		newList  = new DoublyLinkedList<E>();
		newList.addFirst("String1");
		System.out.println(newList.get(0));

	}

}
