/*
 * author: Dylan Northcutt
 */
package assignment06;

import java.util.Arrays;
import java.util.Iterator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DoublyLinkedListJUnit {

	@SuppressWarnings("rawtypes")
	DoublyLinkedList emptyList, testList, testList2;
	Object[] arr = new Object[] { "Place 1", "Place 2", "Place 3" };

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Before
	public void setup() {
		emptyList = new DoublyLinkedList();
		testList = new DoublyLinkedList();
		testList.addFirst("Place 1");
		testList2 = new DoublyLinkedList();
		testList2.addFirst("Place 1");
		testList2.add(1, "Place 2");
		testList2.add(2, "Place 3");

	}

	@Test
	public void addFirstElementEmptyList() {
		emptyList.addFirst("String1");
		Assert.assertTrue(emptyList.get(0) == "String1");
	}

	@Test
	public void addFirstElementNotEmptyList() {
		testList.addFirst("String1");
		Assert.assertTrue(testList.get(0) == "String1");
	}

	@SuppressWarnings("unchecked")
	@Test
	public void BasicAddTest() {

		testList.add(1, "String2");
		Assert.assertTrue(testList.get(1) == "String2");
	}

	@SuppressWarnings("unchecked")
	@Test
	public void BasicAddTestFirstElement() {

		testList.add(0, "String2");
		Assert.assertTrue(testList.get(0) == "String2" && testList.get(1) == "Place 1");
	}

	@Test
	public void BasicAddLastTest() {

		testList2.addLast("DatBoi");
		Assert.assertTrue(testList2.get(3) == "DatBoi");
	}

	@Test
	public void AddLastEmptyList() {
		testList2.addLast("DatBoi");
		Assert.assertTrue(testList2.get(3) == "DatBoi");

	}

	@Test
	public void BasicCheckLast() {

		testList2.addLast("DatBoi");
		Assert.assertTrue(testList2.get(3) == "DatBoi");
	}

	@Test
	public void BasicGetLast() {

		Assert.assertTrue(testList2.getLast() == "Place 3");
	}

	@Test
	public void BasicGetFirst() {

		Assert.assertTrue(testList2.getFirst() == "Place 1");
	}

	@Test
	public void RemoveFirstTest() {

		testList2.removeFirst();
		Assert.assertTrue(testList2.get(0) == "Place 2");
	}

	@Test
	public void isEmptyTest() {

		Assert.assertTrue(emptyList.isEmpty());

	}

	@Test
	public void isEmptyTestFalse() {

		Assert.assertFalse(testList2.isEmpty());
	}

	@Test
	public void removeTest() {
		testList2.remove(1);
		Assert.assertTrue(testList2.get(1) == "Place 3");
	}

	@Test
	public void ClearTest() {
		testList2.clear();
		Assert.assertTrue(testList2.isEmpty());
	}

	@Test
	public void toArrayTest() {
		Object[] testArr = new Object[3];
		testArr = testList2.toArray();
		Assert.assertEquals(Arrays.toString(testArr), Arrays.toString(arr));
	}

	@SuppressWarnings("unchecked")
	@Test

	public <E> void IteratorHasNext() {

		Iterator<E> iter = testList2.iterator();

		Assert.assertTrue(iter.hasNext());

	}

	@Test

	public <E> void IteratorNext() {

		@SuppressWarnings("unchecked")
		Iterator<E> iter = testList2.iterator();
		iter.next();
		Assert.assertTrue(iter.next() == "Place 2");

	}
	@Test

	public <E> void IteratorRemove() {

		@SuppressWarnings("unchecked")
		Iterator<E> iter = testList2.iterator();

		iter.remove();
		Assert.assertTrue(iter.next() == "Place 1");

	}

}
