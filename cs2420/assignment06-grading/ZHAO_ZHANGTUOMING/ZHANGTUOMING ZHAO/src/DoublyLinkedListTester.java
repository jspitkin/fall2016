package assignment06;

import junit.framework.TestCase;

/**
 * @author Zhangtuoming Zhao
 * @UID u0847610
 */

public class DoublyLinkedListTester extends TestCase {

	private DoublyLinkedList testLinkedList1 = new DoublyLinkedList();

	private DoublyLinkedList testLinkedList2 = new DoublyLinkedList();

	private DoublyLinkedList testLinkedList3 = new DoublyLinkedList();

	protected void setUp() throws Exception {

		super.setUp();

		// adding for testLinkedList1 list.
		testLinkedList1.addFirst(1);
		testLinkedList1.addFirst(2);
		testLinkedList1.addFirst(3);
		testLinkedList1.addFirst(4);

		testLinkedList1.addLast(5);
		testLinkedList1.addLast(6);
		testLinkedList1.addLast(7);
		testLinkedList1.addLast(8);

		testLinkedList1.add(2, 9);
		testLinkedList1.add(4, 10);
		testLinkedList1.add(6, 11);
		testLinkedList1.add(8, 12);

		// adding for testLinkedList3 list.
		testLinkedList3.addFirst(1);
		testLinkedList3.addFirst(1);
		testLinkedList3.addFirst(1);

		testLinkedList3.addFirst(2);
		testLinkedList3.addFirst(2);
		testLinkedList3.addFirst(2);

		testLinkedList3.addFirst(4);
		testLinkedList3.addFirst(4);
		testLinkedList3.addFirst(4);

	}

	// test for the size of the list.
	public void test0() {
		assertEquals(12, testLinkedList1.size());
	}

	// test for getFirst method.
	public void test1() {
		assertEquals(4, testLinkedList1.getFirst());
	}

	// test for getLast method.
	public void test2() {
		assertEquals(8, testLinkedList1.getLast());
	}

	// test for get method which the index is on the 0.
	public void test3() {
		assertEquals(4, testLinkedList1.get(0));
	}

	// test for get method which the index is on the 7.
	public void test4() {
		assertEquals(5, testLinkedList1.get(7));
	}

	// test for get method which the index is on the 11.
	public void test5() {
		assertEquals(8, testLinkedList1.get(11));
	}

	// test for indexOf method, to check the integer number 1 should be on the
	// index of 5.
	public void test6() {
		assertEquals(5, testLinkedList1.indexOf(1));
	}

	// test for indexOf method, to check the integer number 7 should be on the
	// index of 10.
	public void test7() {
		assertEquals(10, testLinkedList1.indexOf(7));
	}

	// test1 list is not an empty list.
	public void test8() {
		assertEquals(false, testLinkedList1.isEmpty());
	}

	// I didn't add anything in the test2 list so it should be empty.
	public void test9() {
		assertEquals(true, testLinkedList2.isEmpty());
	}

	// test for last index of method in test3 list.
	public void test10() {
		assertEquals(5, testLinkedList3.lastIndexOf(2));
	}

	public void test11() {
		assertEquals(2, testLinkedList3.lastIndexOf(4));
	}

	public void test12() {
		assertEquals(8, testLinkedList3.lastIndexOf(1));
	}

	// test for remove method.
	public void test13() {
		assertEquals(4, testLinkedList1.remove(0));
	}

	public void test14() {
		assertEquals(2, testLinkedList1.remove(3));
	}

	public void test15() {
		assertEquals(5, testLinkedList1.remove(7));
	}

	public void test16() {
		assertEquals(8, testLinkedList1.remove(11));
	}

	// test for remove first and last method.
	public void test17() {
		assertEquals(4, testLinkedList1.removeFirst());
	}

	public void test18() {
		assertEquals(8, testLinkedList1.removeLast());
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
