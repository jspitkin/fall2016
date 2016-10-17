package assignment_06;
/**
 * Anthony Iovino 
 */

import java.util.Arrays;
import java.util.Iterator;

public class DoublyLinkedListTester {
	
	
//addFirst: 	
	public static void main(String[] args) {
		System.out.println("addFirst() & toArray()");
		DoublyLinkedList<Integer> list1 = new DoublyLinkedList<Integer>();
		for (int x = 0; x < 5; x++) {
			list1.addFirst(x);
			System.out.println(list1);
			System.out.println(Arrays.toString(list1.toArray()));
		}
//addLast:
		System.out.println("\naddLast()");
		DoublyLinkedList<Integer> list2 = new DoublyLinkedList<Integer>();
		for (int x = 0; x < 5; x++) {
			list2.addLast(x);
			System.out.println(list2);
		}
//get:
		System.out.println("\n get()");
		for (int x = 0; x < 5; x++) {
			System.out.println(list2.get(x) == x);
		}
//getFirst:
		System.out.println("\ngetFirst()");
		System.out.println(list1.getFirst() == 4);
		System.out.println(list2.getFirst() == 0);
//getLast
		System.out.println("\ngetLast()");
		System.out.println(list1.getLast() == 0);
		System.out.println(list2.getLast() == 4);
//add
		System.out.println("\nadd()");
		for (int x = 0; x < 5; x++) {
			list2.add(x * 2, x);
			System.out.println(list2);
		}
//indexOf
		System.out.println("\nindexOf()");
		System.out.println(list2.indexOf(1) == 2);

		System.out.println("\nlastIndexOf()");
		System.out.println(list2.lastIndexOf(1) == 3);
//iterator 
		System.out.println("\niterator()");
		Iterator<Integer> iter = list1.iterator();
		System.out.println(list1);
		while (iter.hasNext()) {
			Integer current = iter.next();
			System.out.println(current);
			iter.remove();
			System.out.println(list1 + "\t removed=" + current);
		}
		
		
		for (int x = 0; x < 5; x++) {// Add items to list1 before removing more
			list1.addFirst(x);
		}
//removeFirst:			
		System.out.println("\nremoveFirst()");
		System.out.println(list1);
		for (int x = 0; x < 5; x++) {
			Integer data = list1.removeFirst();
			System.out.println(list1 + "\t removed=" + data);
		}

	
		for (int x = 0; x < 5; x++) {	// Add more items list1 before removing more
			list1.addFirst(x);
		}
//removeLast:
		System.out.println("\nremoveLast()");
		System.out.println(list1);
		for (int x = 0; x < 5; x++) {
			Integer data = list1.removeLast();
			System.out.println(list1 + "\t removed=" + data);
		}

		
		for (int x = 0; x < 5; x++) {// re-add to list1 before removing more
			list1.addFirst(x);
		}
//remove: 		
		System.out.println("\nremove() off beginning");
		System.out.println(list1);
		for (int x = 0; x < 5; x++) {
			Integer data = list1.remove(0);
			System.out.println(list1 + "\t removed=" + data);
		}

		
		for (int x = 0; x < 5; x++) {// add more items to list1 before removing more
			list1.addFirst(x);
		}

		System.out.println("\nremove() off end");
		System.out.println(list1);
		for (int x = 4; x >= 0; x--) {
			Integer data = list1.remove(x);
			System.out.println(list1 + "\t removed=" + data);
		}

		
		for (int x = 0; x < 5; x++) {// add more items list1 before removing more
			list1.addFirst(x);
		}

		System.out.println("\nremove() off middle");
		System.out.println(list1);
		for (int x = 4; x >= 0; x--) {
			Integer data = list1.remove(x/2);
			System.out.println(list1 + "\t removed=" + data);
		}

		
		for (int x = 0; x < 5; x++) {// refill list1 before removing more
			list1.addFirst(x);
		}
//clear:
		System.out.println("\nclear()");
		System.out.println(list1);
		list1.clear();
		System.out.println(list1);
	}
}
