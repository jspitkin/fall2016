package assignment06;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.NoSuchElementException;
import java.util.Iterator;


@SuppressWarnings("unused")
public class LinkedListGrader {
	static final int AF = 0, AL = 1, A = 2, GF = 3, GL = 4, G = 5, //GR = 6,
			RF = 6, RL = 7, R = 8, IO = 9, LIO = 10, S = 11, IE = 12, C = 13,
			TA = 14, IT = 15;
	static int[] pointsPossible = {3, 3, 5, 3, 3, 5, /*2,*/ 4, 4, 5, 4, 4, 4, 2, 3, 3, 5};
	static int[] testsPassed = new int[pointsPossible.length];
	static final String[] methodNames = {"addFirst", "addLast", "add", "getFirst",
			"getLast", "get", /*"getRandom",*/ "removeFirst", "removeLast", "remove",
			"indexOf", "lastIndexOf", "size", "isEmpty", "clear", "toArray", "iterator"};
	static final int[] testCounts = {3, 3, 7, 3, 3, 5, /*2,*/ 4, 4, 7, 4, 4, 4, 3, 5, 3, 7};

	private static class Tester implements Runnable {
		private int method, test;

		public Tester(int _method, int _test) {
			method = _method;
			test = _test;
		}

		public void run() {
			try {
				this.getClass().getMethod("test_" + methodNames[method] + "_" + test).invoke(null);
				return;
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
			System.exit(1); //If we got here, there was a reflection exception
		}

		public static void test_addFirst_0() {
			DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();

			// Add to empty list
			try {
				testList.addFirst(7); // If it doesn't error assume it is
										// correct, for now
				testsPassed[AF]++; // (they will lose enough points later, so might as
			} // well give them what we can now)

			catch (Throwable e) {
				System.out.println("Failed to add first to empty list");
				System.out.println("\tThrew: " + e);
			}
		}
		
		public static void test_addFirst_1() {
			// Add to one element list
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.addFirst(7);
				testList.addFirst(3);
				testsPassed[AF]++;
			} catch (Throwable e) {
				System.out.println("Failed to add first to one element list");
				System.out.println("\tThrew: " + e);
			}
		}
		
		public static void test_addFirst_2() {
			// Add multiple elements to list
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.addFirst(7);
				testList.addFirst(3);
				testList.addFirst(1);
				testList.addFirst(6);
				testList.addFirst(88);
				testsPassed[AF]++;
			} catch (Throwable e) {
				System.out.println("Failed to add first multiple times to multi-element list");
				System.out.println("\tThrew: " + e);
			}
		}

		public static void test_addLast_0() {
			// Add to empty list
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.addLast(7);
				testsPassed[AL]++;
			} catch (Throwable e) {
				System.out.println("Failed to add last to empty list, Exception: "
								+ e);
			}
		}
		
		public static void test_addLast_1() {
			// Add to one element list
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.addLast(7);
				testList.addLast(3);
				testsPassed[AL]++;
			} catch (Throwable e) {
				System.out.println("Failed to add last to one element list");
				System.out.println("\tThrew: " + e);
			}
		}
		
		public static void test_addLast_2() {
			// Add multiple elements to list
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.addLast(7);
				testList.addLast(3);
				testList.addLast(1);
				testList.addLast(6);
				testList.addLast(88);
				testsPassed[AL]++;
			} catch (Throwable e) {
				System.out.println("Failed to add last multiple times to multi-element list");
				System.out.println("\tThrew: " + e);
			}
		}

		public static void test_add_0() {
			// Add to empty list
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.add(0, 7); // If it doesn't error assume it is correct
				testsPassed[A]++;
			} catch (Throwable e) {
				System.out.println("Failed to add a single item : " + e);
			}
		}
		
		public static void test_add_1() {
			// Add index too low for list
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.add(-1, 7);
				System.out.println("Failed to throw IndexOutOfBoundsException with add(-1, 7).");
			} catch (IndexOutOfBoundsException ie) {
				testsPassed[A]++;
			} catch (Throwable e) {
				System.out.println("Failed to throw IndexOutOfBoundsException with add(-1, 7).");
				System.out.println("\tInstead threw: " + e);
			}
		}
		
		public static void test_add_2() {
			// Add index too high for list
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.add(3, 7);
				System.out.println("Failed to throw IndexOutOfBoundsException with add(3, 7) for empty list.");
			} catch (IndexOutOfBoundsException ie) {
				testsPassed[A]++;
			} catch (Throwable e) {
				System.out.println("Failed to throw IndexOutOfBoundsException with add(3, 7) for empty list.");
				System.out.println("\tInstead threw: " + e);
			}
		}
		
		public static void test_add_3() {
			// Add to beginning of list
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.addFirst(7);
				testList.add(0, 3);
				if (!(testList.getFirst().equals(3) && testList.getLast().equals(7))) {
					System.out.println("Failed to add at beginning of list (using getFirst & getLast to verify)");
				} else {
					testsPassed[A]++;
				}
			} catch (Throwable e) {
				System.out.println("Failed to add at beginning of list (using getFirst & getLast to verify).");
				System.out.println("\tThrew: " + e);
			}
		}
		
		public static void test_add_4() {
			// Add to end of list
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.addFirst(7);
				testList.add(1, 3);
				if (!(testList.getFirst().equals(7) && testList.getLast().equals(3))) {
					System.out.println("Failed to add at end of list (using getFirst & getLast to verify)");
				} else {
					testsPassed[A]++;
				}
			} catch (Throwable e) {
				System.out.println("Failed to add at end of list (using getFirst & getLast to verify)");
				System.out.println("\tThrew: " + e);
			}
		}
		
		public static void test_add_5() {
			// Add to middle
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.addFirst(3);
				testList.addFirst(7);
				testList.add(1, 5);
				if (!(testList.getFirst().equals(7) && testList.get(1).equals(5)
						&& testList.getLast().equals(3))) {
					System.out.println("Failed to add to middle of list (using getFirst, get, & getLast to verify)");
				} else {
					testsPassed[A]++;
				}
			} catch (Throwable e) {
				System.out.println("Failed to add to middle of list (using getFirst, get, & getLast to verify)");
				System.out.println("\tThrew: " + e);
			}
		}
		
		public static void test_add_6() {
			// Add to middle of longer list
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.addFirst(10);
				testList.addFirst(8);
				testList.addFirst(4);
				testList.addFirst(2);
				testList.add(2, 6);
				if (!(testList.getFirst().equals(2) && testList.get(1).equals(4)
						&& testList.get(2).equals(6) && testList.get(3).equals(8)
						&& testList.getLast().equals(10))) {
					System.out.println("Failed to add to middle of longer list (using getFirst, get, & getLast to verify)");
				} else {
					testsPassed[A]++;
				}
			} catch (Throwable e) {
				System.out.println("Failed to add to middle of list (using getFirst, get, & getLast to verify)");
				System.out.println("\tThrew: " + e);
			}
		}

		public static void test_getFirst_0() {
			// getFirst on empty list
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.getFirst();
				System.out.println("Failed to throw NoSuchElementException on getFirst with empty list.");
			} catch (NoSuchElementException ne) {
				testsPassed[GF]++;
			} catch (Throwable e) {
				System.out.println("Failed to throw NoSuchElementException on getFirst with empty list.");
				System.out.println("\tInstead threw, Exception: " + e);
			}
		}
		
		public static void test_getFirst_1() {
			// Add one element and get it
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.addFirst(3);
				if (testList.getFirst().equals(3))
					testsPassed[GF]++;
				else
					System.out.println("Failed to add first to empty list and retrieve it using getFirst.");
			} catch (Throwable e) {
				System.out.println("Failed to add first to empty list and retrieve it using getFirst, Exception: "
								+ e);
			}
		}
		
		public static void test_getFirst_2() {
			// Add multiple elements and get first from it
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.addFirst(1);
				testList.addFirst(6);
				testList.addFirst(88);
				if (testList.getFirst().equals(88)) {
					testsPassed[GF]++;
				} else {
					System.out.println("Failed to retrieve first using getFirst after adding several times with addFirst.");
				}
			} catch (Throwable e) {
				System.out.println("Failed to retrieve first using getFirst after adding several times with addFirst, Exception: "
								+ e);
			}
		}

		public static void test_getLast_0() {
			// getFirst on empty list
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.getLast();
				System.out.println("Failed to throw NoSuchElementException on getLast with empty list.");
			} catch (NoSuchElementException ne) {
				testsPassed[GL]++;
			} catch (Throwable e) {
				System.out.println("Failed to throw NoSuchElementException on getLast with empty list.");
				System.out.println("\tInstead threw, Exception: " + e);
			}
		}
		
		public static void test_getLast_1() {
			// Add one element and get it
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.addFirst(3);
				if (testList.getLast().equals(3))
					testsPassed[GL]++;
				else
					System.out.println("Failed to add first to empty list and retrieve it using getLast.");
			} catch (Throwable e) {
				System.out.println("Failed to add first to empty list and retrieve it using getLast, Exception: "
								+ e);
			}
		}
		
		public static void test_getLast_2() {
			// Add multiple elements and get last from it
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.addFirst(1);
				testList.addFirst(6);
				testList.addFirst(88);
				if (testList.getLast().equals(1))
					testsPassed[GL]++;
				else
					System.out.println("Failed to retrieve first using getLast after adding several times with addLast.");
			} catch (Throwable e) {
				System.out.println("Failed to retrieve first using getFirst after adding several times with addFirst, Exception: "
								+ e);
			}
		}

		public static void test_get_0() {
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.get(0);
				System.out.println("Failed to throw IndexOutOfBoundsException on get of list size zero.");
			} catch (IndexOutOfBoundsException e) {
				testsPassed[G]++;
			} catch (Throwable e) {
				System.out.println("Failed to throw IndexOutOfBoundsException on get of list size zero.");
				System.out.println("\tInstead threw: " + e);
			}
		}
		
		public static void test_get_1() {
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.addFirst(18);
				if (testList.get(0).equals(18)) {
					testsPassed[G]++;
				} else {
					System.out.println("Failed to get single item of list size one (using addFirst to insert item).");
				}
			} catch (Throwable e) {
				System.out.println("Failed to get single item of list size one.");
				System.out.println("\tThrew: " + e);
			}
		}
		
		public static void test_get_2() {
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.addFirst(19);
				testList.addFirst(36);
				testList.addFirst(13);
				if (testList.get(1).equals(36)) {
					testsPassed[G]++;
				} else {
					System.out.println("Failed to get first item of list size three (using addFirst to insert items).");
				}
			} catch (Throwable e) {
				System.out.println("Failed to get 3 items in appropriate index of list size three (using addFirst to insert items).");
				System.out.println("\tThrew: " + e);
			}
		}
		
		public static void test_get_3() {
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.addFirst(19);
				testList.addFirst(36);
				testList.addFirst(13);
				if (testList.get(0).equals(13)) {
					testsPassed[G]++;
				} else {
					System.out.println("Failed to get second item of list size three (using addFirst to insert items).");
				}
			} catch (Throwable e) {
				System.out.println("Failed to get 3 items in appropriate index of list size three (using addFirst to insert items).");
				System.out.println("\tThrew: " + e);
			}
		}
		
		public static void test_get_4() {
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.addFirst(19);
				testList.addFirst(36);
				testList.addFirst(13);
				if (testList.get(2).equals(19)) {
					testsPassed[G]++;
				} else {
					System.out.println("Failed to get third item of list size three (using addFirst to insert items).");
				}
			} catch (Throwable e) {
				System.out.println("Failed to get 3 items in appropriate index of list size three (using addFirst to insert items).");
				System.out.println("\tThrew: " + e);
			}
		}

//		public static void test_getRandom_0() {
//			try {
//				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
//				testList.getRandom();
//				System.out.println("Failed to throw NoSuchElementException on getRandom with empty list.");
//			} catch (NoSuchElementException ne) {
//				points[GR]++;
//			} catch (Throwable e) {
//				System.out.println("Failed to throw NoSuchElementException on getRandom with empty list.");
//				System.out.println("\tInstead threw, Exception: " + e);
//			}
//		}
//		
//		public static void test_getRandom_1() {
//			// Add multiple elements and get random from it
//			try {
//				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
//				testList.addFirst(2);
//				testList.addFirst(3);
//				testList.addFirst(1);
//				testList.addFirst(5);
//				testList.addFirst(4);
//				int rand = testList.getRandom();
//				if (rand > 0 && rand < 6) {
//					points[GR]++;
//				} else {
//					System.out.println("getRandom returned something that was not in the list.");
//				}
//			} catch (Throwable e) {
//				System.out.println("getRandom failed on list with several items: "
//								+ e);
//			}
//		}

		public static void test_removeFirst_0() {
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.removeFirst();
				System.out.println("Failed to throw NoSuchElementException on removeFirst of list size zero.");
			} catch (NoSuchElementException ne) {
				testsPassed[RF]++;
			} catch (Throwable e) {
				System.out.println("Failed to throw NoSuchElementException on removeFirst of list size zero.");
				System.out.println("\tInstead threw: " + e);
			}
		}
		
		public static void test_removeFirst_1() {
			try {
				// Point if they can call remove first without an exception
				// being generated
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList = new DoublyLinkedList<Integer>();
				testList.addFirst(25);
				testList.removeFirst();
				testsPassed[RF]++;
			} catch (Throwable e) {
				System.out.println("Failed to remove item from list size one.");
				System.out.println("\tThrew: " + e);
			}
		}
		
		public static void test_removeFirst_2() {
			try {
				// Point if they can call add stuff to empty list after removing
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList = new DoublyLinkedList<Integer>();
				testList.addFirst(25);
				testList.removeFirst();
				testList.addFirst(25);
				if (testList.removeFirst().equals(25)) {
					testsPassed[RF]++;
				} else {
					System.out.println("Failed to remove item from list size one.");
				}
			} catch (Throwable e) {
				System.out.println("Failed to remove item from list size one.");
				System.out.println("\tThrew: " + e);
			}
		}
		
		public static void test_removeFirst_3() {
			try {
				// Point if they can call remove first and have the correct new
				// item being the head
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.addFirst(25);
				testList.addFirst(85);
				testList.removeFirst();

				if (testList.getFirst().equals(25)) {
					testsPassed[RF]++;
				} else {
					System.out.println("Failed to get the correct value after removeFirst using getFirst.");
				}
			} catch (Throwable e) {
				System.out.println("Failed to get the correct value after removeFirst using getFirst.");
				System.out.println("\tThrew: " + e);
			}
		}

		public static void test_removeLast_0() {
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.removeLast();
				System.out.println("Failed to throw NoSuchElementException on removeLast of list size zero.");
			} catch (NoSuchElementException ne) {
				testsPassed[RL]++;
			} catch (Throwable e) {
				System.out.println("Failed to throw NoSuchElementException on removeLast of list size zero.");
				System.out.println("\tInstead threw: " + e);
			}
		}
		
		public static void test_removeLast_1() {
			try {
				// Point if they can call remove last without an exception being
				// generated
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.addFirst(25);
				testList.removeLast();
				testsPassed[RL]++;
			} catch (Throwable e) {
				System.out.println("Failed to remove item from list size one.");
				System.out.println("\tThrew: " + e);
			}
		}
		
		public static void test_removeLast_2() {
			try {
				// Point if they can call add stuff to empty list after removing
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.addFirst(25);
				testList.removeLast();
				testList.addFirst(25);
				if (testList.removeLast().equals(25)) {
					testsPassed[RL]++;
				} else {
					System.out.println("Failed to remove item from list size one.");
				}
			} catch (Throwable e) {
				System.out.println("Failed to remove item from list size one.");
				System.out.println("\tThrew: " + e);
			}
		}
		
		public static void test_removeLast_3() {
			try {
				// Point if they can call remove last and have the correct new
				// item being the tail
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.addFirst(25);
				testList.addFirst(85);
				testList.removeLast();

				if (testList.getLast().equals(85)) {
					testsPassed[RL]++;
				} else {
					System.out.println("Failed to get the correct value after removeLast using getLast.");
				}
			} catch (Throwable e) {
				System.out.println("Failed to get the correct value after removeFirst using getLast.");
				System.out.println("\tThrew: " + e);
			}
		}

		public static void test_remove_0() {
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.remove(0);
				System.out.println("Failed to throw IndexOutOfBoundsException on remove of list size zero.");
			} catch (IndexOutOfBoundsException ne) {
				testsPassed[R]++;
			} catch (Throwable e) {
				System.out.println("Failed to throw IndexOutOfBoundsException on remove of list size zero.");
				System.out.println("\tInstead threw: " + e);
			}
		}
		
		public static void test_remove_1() {
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.addFirst(18);
				if (testList.remove(0).equals(18)) {
					testsPassed[R]++;
				} else {
					System.out.println("Failed to remove single item of list size one (using addFirst to insert item).");
				}
			} catch (Throwable e) {
				System.out.println("Failed to remove single item of list size one (using addFirst to insert item).");
				System.out.println("\tThrew: " + e);
			}
		}
		
		public static void test_remove_2() {
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.addFirst(19);
				testList.addFirst(36);
				testList.addFirst(13);
				if (testList.remove(1).equals(36)) {
					testsPassed[R]++;
				} else {
					System.out.println("Failed to remove middle item of list size three (using addFirst to insert items).");
				}
			} catch (Throwable e) {
				System.out.println("Failed to remove 3 items in appropriate index of list size three (using addFirst to insert items).");
				System.out.println("\tThrew: " + e);
			}

		}
		
		public static void test_remove_3() {
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.addFirst(19);
				testList.addFirst(36);
				testList.addFirst(13);
				testList.remove(1);
				
				if (testList.get(1).equals(19)) {
					testsPassed[R]++;
				} else {
					System.out.println("Failed to update list appropriately after removal (using addFirst to insert items).");
				}
			} catch (Throwable e) {
				System.out.println("Failed to remove 3 items in appropriate index of list size three (using addFirst to insert items).");
				System.out.println("\tThrew: " + e);
			}

		}
		
		public static void test_remove_4() {
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.addFirst(19);
				testList.addFirst(36);
				testList.addFirst(13);
				testList.remove(1);
				
				if (testList.remove(1).equals(19)) {
					testsPassed[R]++;
				} else {
					System.out.println("Failed to remove last item in list (using addFirst to insert items).");
				}
			} catch (Throwable e) {
				System.out.println("Failed to remove 3 items in appropriate index of list size three (using addFirst to insert items).");
				System.out.println("\tThrew: " + e);
			}

		}
		
		public static void test_remove_5() {
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.addFirst(19);
				testList.addFirst(36);
				testList.addFirst(13);
				testList.remove(1);
				testList.remove(1);
				testList.addFirst(19);
				
				if (testList.remove(0).equals(19)) {
					testsPassed[R]++;
				} else {
					System.out.println("Failed to remove first item in list (using addFirst to insert items).");
				}
			} catch (Throwable e) {
				System.out.println("Failed to remove 3 items in appropriate index of list size three (using addFirst to insert items).");
				System.out.println("\tThrew: " + e);
			}

		}
		
		public static void test_remove_6() {
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.addFirst(19);
				testList.addFirst(36);
				testList.addFirst(13);
				testList.remove(1);
				testList.remove(1);
				testList.addFirst(19);
				testList.remove(0);
				
				if (testList.getFirst().equals(13)) {
					testsPassed[R]++;
				} else {
					System.out.println("Failed to update list appropriately after removal (using addFirst to insert items).");
				}
			} catch (Throwable e) {
				System.out.println("Failed to remove 3 items in appropriate index of list size three (using addFirst to insert items).");
				System.out.println("\tThrew: " + e);
			}

		}

		public static void test_indexOf_0() {
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				if (testList.indexOf(85) == -1) {
					testsPassed[IO]++;
				} else {
					System.out.println("Failed to report -1 for index of item not in list.");
				}
			} catch (Throwable e) {
				System.out.println("Failed to report -1 for index of item not in list.");
				System.out.println("\tThrew: " + e);
			}
		}
		
		public static void test_indexOf_1() {
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.addFirst(25);
				if (testList.indexOf(25) == 0) {
					testsPassed[IO]++;
				} else {
					System.out.println("Failed to report correct index of only item in list.");
				}
			} catch (Throwable e) {
				System.out.println("Failed to report correct index of only item in list.");
				System.out.println("\tThrew: " + e);
			}
		}
		
		public static void test_indexOf_2() {
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.addFirst(25);
				testList.addFirst(25);
				testList.addFirst(37);
				testList.addFirst(25);
				testList.addFirst(18);
				testList.addFirst(37);

				if (testList.indexOf(25) == 2) {
					testsPassed[IO]++;
				} else {
					System.out.println("Failed to report correct first index of item occuring multiple times in list.");
				}
			} catch (Throwable e) {
				System.out.println("Failed to report correct first index of item occuring multiple times in list.");
				System.out.println("\tThrew: " + e);
			}
		}
		
		public static void test_indexOf_3() {
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.addFirst(25);
				testList.addFirst(25);
				testList.addFirst(37);
				testList.addFirst(25);
				testList.addFirst(18);
				testList.addFirst(37);
				testList.addFirst(4);
				testList.addFirst(37);
				testList.addFirst(25);
				testList.addFirst(18);
				testList.addFirst(37);

				if (testList.indexOf(4) == 4) {
					testsPassed[IO]++;
				} else {
					System.out.println("Failed to report correct index of item occuring at last position in list.");
				}
			} catch (Throwable e) {
				System.out.println("Failed to report correct index of item occuring at last position in list.");
				System.out.println("\tThrew: " + e);
			}
		}

		public static void test_lastIndexOf_0() {
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				if (testList.lastIndexOf(85) == -1) {
					testsPassed[LIO]++;
				} else {
					System.out.println("Failed to report -1 for index of item not in list.");
				}
			} catch (Throwable e) {
				System.out.println("Failed to report -1 for index of item not in list.");
				System.out.println("\tThrew: " + e);
			}
		}
		
		public static void test_lastIndexOf_1() {
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.addFirst(25);
				if (testList.lastIndexOf(25) == 0) {
					testsPassed[LIO]++;
				} else {
					System.out.println("Failed to report correct index of only item in list.");
				}
			} catch (Throwable e) {
				System.out.println("Failed to report correct index of only item in list.");
				System.out.println("\tThrew: " + e);
			}
		}
		
		public static void test_lastIndexOf_2() {
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.addFirst(25);
				testList.addFirst(37);
				testList.addFirst(25);
				testList.addFirst(18);
				testList.addFirst(37);

				if (testList.lastIndexOf(25) == 4) {
					testsPassed[LIO]++;
				} else {
					System.out.println("Failed to report correct last index of item occuring multiple times in list.");
				}
			} catch (Throwable e) {
				System.out.println("Failed to report correct last index of item occuring multiple times in list.");
				System.out.println("\tThrew: " + e);
			}
		}
		
		public static void test_lastIndexOf_3() {
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.addFirst(25);
				testList.addFirst(37);
				testList.addFirst(25);
				testList.addFirst(18);
				testList.addFirst(37);
				testList.addFirst(25);
				testList.addFirst(37);
				testList.addFirst(25);
				testList.addFirst(18);
				testList.addFirst(4);

				if (testList.lastIndexOf(4) == 0) {
					testsPassed[LIO]++;
				} else {
					System.out.println("Failed to report correct index of item occuring at last position in list.");
				}
			} catch (Throwable e) {
				System.out.println("Failed to report correct index of item occuring at last position in list.");
				System.out.println("\tThrew: " + e);
			}
		}

		public static void test_size_0() {
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				if (testList.size() == 0) {
					testsPassed[S]++;
				} else {
					System.out.println("Failed to report correct size of empty list.");
				}
			} catch (Throwable e) {
				System.out.println("Failed to report correct size of empty list.");
				System.out.println("\tThrew: " + e);
			}
		}
		
		public static void test_size_1() {
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.addFirst(1);
				if (testList.size() == 1) {
					testsPassed[S]++;
				} else {
					System.out.println("Failed to report correct size of one element list.");
				}
			} catch (Throwable e) {
				System.out.println("Failed to report correct size of one element list.");
				System.out.println("\tThrew: " + e);
			}
		}
		
		public static void test_size_2() {
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.addFirst(1);
				testList.addFirst(2);
				testList.addFirst(3);
				testList.addFirst(4);
				testList.addFirst(5);
				if (testList.size() == 5) {
					testsPassed[S]++;
				} else {
					System.out.println("Failed to report correct size of multi-element list.");
				}
			} catch (Throwable e) {
				System.out.println("Failed to report correct size of multi-element list.");
				System.out.println("\tThrew: " + e);
			}
		}
		
		public static void test_size_3() {
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.addFirst(1);
				testList.addFirst(2);
				testList.addFirst(3);
				testList.addFirst(4);
				testList.addFirst(5);
				testList.removeFirst();
				if (testList.size() == 4) {
					testsPassed[S]++;
				} else {
					System.out.println("Failed to report correct size of multi-element list after removing one element.");
				}
			} catch (Throwable e) {
				System.out.println("Failed to report correct size of multi-element list.");
				System.out.println("\tThrew: " + e);
			}
		}

		public static void test_isEmpty_0() {
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				if (testList.isEmpty()) {
					testsPassed[IE]++;
				} else {
					System.out.println("Failed to report empty list is empty.");
				}
			} catch (Throwable e) {
				System.out.println("Failed to report empty list is empty.");
				System.out.println("\tThrew: " + e);
			}
		}
		
		public static void test_isEmpty_1() {
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.addFirst(25);
				if (!testList.isEmpty()) {
					testsPassed[IE]++;
				} else {
					System.out.println("Failed to report non-empty list is not empty.");
				}
			} catch (Throwable e) {
				System.out.println("Failed to report non-empty list is not empty.");
				System.out.println("\tThrew: " + e);
			}
		}
		
		public static void test_isEmpty_2() {
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.addFirst(25);
				testList.removeFirst();
				if (testList.isEmpty()) {
					testsPassed[IE]++;
				} else {
					System.out.println("Failed to report non-empty list is not empty.");
				}
			} catch (Throwable e) {
				System.out.println("Failed to report non-empty list is not empty.");
				System.out.println("\tThrew: " + e);
			}
		}

		public static void test_clear_0() {
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.addFirst(25);
				testList.addFirst(35);
				testList.addFirst(100);
				testList.clear();
				// Point if clear does not error when called
				testsPassed[C]++;
				
			} catch (Throwable e) {
				System.out.println("Failed to clear list with elements.");
				System.out.println("\tThrew: " + e);
			}
		}

		public static void test_clear_1() {
			try {
				// Point if clear does not error when called
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.addFirst(25);
				testList.addFirst(35);
				testList.addFirst(100);
				testList.clear();

				if (testList.size() == 0 && testList.isEmpty()) {
					testsPassed[C]++;
				} else if (testList.size() != 0) {
					System.out.println("Failed to reset size when clearing list.");
				} else {
					System.out.println("Failed to report list is empty after clearing.");
				}
			} catch (Throwable e) {
				System.out.println("Failed to clear list with elements.");
				System.out.println("\tThrew: " + e);
			}
		}
		
		public static void test_clear_2() {
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.addFirst(25);
				testList.addFirst(35);
				testList.addFirst(100);
				testList.clear();
				testList.getFirst();
				System.out.println("getFirst should throw NoSuchElementException after clearing list.");
			} catch (NoSuchElementException ne) {
				testsPassed[C]++;
			} catch (Throwable e) {
				System.out.println("getFirst should throw NoSuchElementException after clearing list.");
				System.out.println("\tInstead threw: " + e);
			}
		}
		
		public static void test_clear_3() {
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.addFirst(25);
				testList.addFirst(35);
				testList.addFirst(100);
				testList.clear();
				testList.addFirst(42);
				if (testList.getFirst().equals(42)) {
					testsPassed[C]++;
				} else {
					System.out.println("addFirst does not work after clearing list.");
				}
			} catch (Throwable e) {
				System.out.println("addFirst after clearing the list threw: " + e);
			}
		}
		
		public static void test_clear_4() {
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.addFirst(25);
				testList.addFirst(35);
				testList.addFirst(100);
				testList.clear();
				testList.addLast(18);
				if (testList.getLast().equals(18)) {
					testsPassed[C]++;
				} else {
					System.out.println("addLast does not work after clearing list.");
				}
			} catch (Throwable e) {
				System.out.println("addLast after clearing the list threw: " + e);
			}
		}
		
		public static void test_toArray_0() {
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				Object[] iArray = testList.toArray();
				if(iArray.length == 0) {
					testsPassed[TA]++;
				} else {
					System.out.println("Problem with toArray on empty list");
				}
			} catch (Throwable e) {
				System.out.println("toArray on empty list threw " + e);
			}
		}

		public static void test_toArray_1() {
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.addFirst(9);
				testList.addFirst(8);
				testList.addFirst(7);
				testList.addFirst(6);
				testList.addFirst(5);
				testList.addFirst(4);
				testList.addFirst(3);
				testList.addFirst(2);
				testList.addFirst(1);
				testList.addFirst(0);
				Object[] iArray = testList.toArray();

				if (iArray.length == 10) {
					testsPassed[TA]++;
				} else {
					System.out.println("Size of array is incorrect.");
				}
			} catch (Throwable e) {
				System.out.println("toArray threw " + e);
			}
		}

		public static void test_toArray_2() {
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.addFirst(9);
				testList.addFirst(8);
				testList.addFirst(7);
				testList.addFirst(6);
				testList.addFirst(5);
				testList.addFirst(4);
				testList.addFirst(3);
				testList.addFirst(2);
				testList.addFirst(1);
				testList.addFirst(0);
				Object[] iArray = testList.toArray();

				if (iArray.length != 10) {
					System.out.println("Size of array is incorrect.");
					return;
				}

				if (iArray[0].equals(0) && iArray[1].equals(1)
						&& iArray[2].equals(2) && iArray[3].equals(3)
						&& iArray[4].equals(4) && iArray[5].equals(5)
						&& iArray[6].equals(6) && iArray[7].equals(7)
						&& iArray[8].equals(8) && iArray[9].equals(9)) {
					testsPassed[TA]++;
				} else {
					System.out.println("Not all elements of array are correct.");
				}
			} catch (Throwable e) {
				System.out.println("toArray threw " + e);
			}
		}
		
		/**
		 * Calling hasNext on an iterator when the list is empty should return false
		 */
		public static void test_iterator_0() {
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				Iterator<Integer> testIterator = testList.iterator();
				if(!testIterator.hasNext()) {
					testsPassed[IT]++;
				}
			}
			catch (Exception e) {
				System.out.println("iterator threw " + e);
			}
		}
		
		/**
		 * Calling hasNext on an iterator when the list isn't empty should return true
		 */
		public static void test_iterator_1() {
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.addFirst(5);
				Iterator<Integer> testIterator = testList.iterator();
				if(testIterator.hasNext()) {
					testsPassed[IT]++;
				}
			}
			catch (Exception e) {
				System.out.println("iterator threw " + e);
			}
		}
		
		/**
		 * Calling next on an iterator once should return the first item in the LinkedList
		 */
		public static void test_iterator_2() {
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.addFirst(5);
				Iterator<Integer> testIterator = testList.iterator();
				if(testIterator.next() == 5) {
					testsPassed[IT]++;
				}
			}
			catch (Exception e) {
				System.out.println("iterator threw " + e);
			}
		}
		
		/**
		 * Calling next on an iterator twice should return the second item in the LinkedList
		 */
		public static void test_iterator_3() {
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.add(0,5);
				testList.add(1,7);
				Iterator<Integer> testIterator = testList.iterator();
				testIterator.next();
				if(testIterator.next() == 7) {
					testsPassed[IT]++;
				}
			}
			catch (Exception e) {
				System.out.println("iterator threw " + e);
			}
		}
		
		/**
		 * Calling next on an iterator when at the end of the list should throw a NoSuchElementException
		 */
		public static void test_iterator_4() {
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.add(0,5);
				testList.add(1,7);
				Iterator<Integer> testIterator = testList.iterator();
				testIterator.next();
				testIterator.next();
				testIterator.next();
			}
			catch (NoSuchElementException e) {
				testsPassed[IT]++;
			}
			
		}
		
		/**
		 * Calling remove on an iterator should remove from the LinkedList the last item returned from next
		 */
		public static void test_iterator_5() {
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.add(0,5);
				testList.add(1,7);
				Iterator<Integer> testIterator = testList.iterator();
				testIterator.next();
				testIterator.remove();
				int expectedRemovedValue = testList.get(0);
				if (expectedRemovedValue == 7) {
					testsPassed[IT]++;
				}
			}
			catch (Exception e) {
				System.out.println("iterator threw " + e);
			}
		}
		
		/**
		 * Calling remove on an iterator should throw an IllegalStateException if you haven't called add since last calling remove.
		 */
		public static void test_iterator_6() {
			try {
				DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
				testList.add(0,5);
				testList.add(1,7);
				Iterator<Integer> testIterator = testList.iterator();
				testIterator.next();
				testIterator.remove();
				testIterator.remove();
			}
			catch (IllegalStateException e) {
				testsPassed[IT]++;
			}
		}
	}
	
	/**
	 * Used as a marker for printing a relevant error message when a student's
	 * code is running infinitely.
	 */
	static class RunningInfinitelyException extends Exception {
		private static final long serialVersionUID = 1L;

		public String toString() {
			return "Running Infinitely";
		}
	}

	/***
	 * Actually run a test in another thread.
	 */
	@SuppressWarnings("deprecation")
	private static void runTest(Runnable r) {
		Thread t = new Thread(r);
		t.start();
		try {
			t.join(3000);
			if (t.isAlive()) {
				t.stop(new RunningInfinitelyException());
			}
		} catch (InterruptedException e) {
			System.err.println("Something went wrong with the grader, run again");
			System.exit(1);
		}
	}

	public static void main(String[] args) {
		DecimalFormat df = new DecimalFormat("#.##");
		int total = 0, totalPossible = 0;
		for(int i=0; i<methodNames.length; i++) {
			System.out.println("Testing " + methodNames[i]);
			for(int j=0; j<testCounts[i]; j++) {
				runTest(new Tester(i, j));
			}
			float points = pointsPossible[i] * testsPassed[i] / testCounts[i];
			System.out.println("Tests:\t\t\t\t" + testsPassed[i] + "/" + df.format(testCounts[i]));
			System.out.println("Points:\t\t\t\t" + df.format(points) + "/" + df.format(pointsPossible[i]));
			System.out.println("--------------------");
			total += points;
			totalPossible += pointsPossible[i];
		}
		
		System.out.println("Final Score\n");
		System.out.println("Points from automated tests:\t" + df.format(total) + "/" + totalPossible);
		System.out.println("Style & Testing:\t\t" + "xx/10");
		System.out.println("Analysis Document:\t\t" + "xx/30");
		System.out.println("Total:\t\t\t\t" + "xx/100");
		System.out.println("--------------------");
		System.out.println("Comments:\n");
		
		if (testsPassed[AF] != pointsPossible[AF] || testsPassed[GF] != pointsPossible[GF] ||
			testsPassed[G] !=  pointsPossible[G]  || testsPassed[GL] != pointsPossible[GL]) {
			System.out.println("You missed points on one or more of addFirst, getFirst, getLast, or get.  If");
			System.out.println("these methods are broken we cannot accurately test your other methods, and you");
			System.out.println("may have mistakenly lost points on other methods as a result.  If mistakes in");
			System.out.println("these four methods affected your score in other methods, you can send email to");
			System.out.println("the teaching staff explaining exactly what went wrong and how to fix your code");
			System.out.println("and you may receive some points back for those other methods.");
		}
	}
}