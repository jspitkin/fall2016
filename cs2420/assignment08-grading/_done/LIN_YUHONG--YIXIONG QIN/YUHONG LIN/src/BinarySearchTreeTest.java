package Assignment08;


import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
/**
 * 
 * @author Yuhong Lin && Yixiong Qin
 * 
 *
 */
public class BinarySearchTreeTest {

	BinarySearchTree<Integer> A;
	ArrayList<Integer> addList1;
	ArrayList<Integer> addList2;
	ArrayList<Integer> List;

	@Before
	public void setUp() throws Exception {
		A = new BinarySearchTree<Integer>();
		A.add(5);
		A.add(3);
		addList1 = new ArrayList<Integer>();
		addList1.add(9);
		addList1.add(0);
		addList1.add(11);
		addList1.add(-11);
		addList1.add(32321);
		addList2 = new ArrayList<Integer>();
		addList2.add(9);
		addList2.add(9);
		addList2.add(3);
		addList2.add(5);
		List = new ArrayList<Integer>();
		List.add(3);
		List.add(5);
		A.writeDot("GG");
	}

	@Test
	public void testAdd() {
		Assert.assertTrue(A.add(-33));
		Assert.assertFalse(A.add(-33));
		A.clear();
		Assert.assertTrue(A.add(-33));

	}

	@Test
	public void testAddAll() {
		Assert.assertTrue(A.addAll(addList1));
		Assert.assertFalse(A.addAll(addList2));
	}

	@Test
	public void testClear() {
		A.clear();
		Assert.assertEquals(0, A.size());
	}

	@Test
	public void testContains() {
		Assert.assertTrue(A.contains(5));
		Assert.assertFalse(A.contains(0));
		A.clear();
		Assert.assertFalse(A.contains(5));
	}

	@Test
	public void testContainsAll() {
		Assert.assertFalse(A.containsAll(addList1));
		A.addAll(addList1);
		Assert.assertTrue(A.containsAll(addList1));
	}

	@Test
	public void testFirst() {
		Assert.assertEquals(3, (int) A.first());

	}

	@Test(expected = NoSuchElementException.class)
	public void testFirstException() {
		A.clear();
		A.first();
	}

	@Test
	public void testIsEmpty() {
		Assert.assertFalse(A.isEmpty());
		A.clear();
		Assert.assertTrue(A.isEmpty());
	}

	@Test
	public void testLast() {
		Assert.assertEquals(5, (int) A.last());

	}

	@Test(expected = NoSuchElementException.class)
	public void testLastException() {
		A.clear();
		A.last();
	}

	@Test
	public void testRemove() {
		Assert.assertTrue(A.remove(3));
		Assert.assertFalse(A.remove(3));
	}

	@Test(expected = NullPointerException.class)
	public void testRemoveException() {
		A.remove(null);
	}

	@Test
	public void testRemoveAll() {
		Assert.assertFalse(A.removeAll(addList1));
		A.addAll(addList1);
		Assert.assertTrue(A.removeAll(addList1));
	}

	@Test
	public void testSize() {
		Assert.assertEquals(2, A.size());
	}

	@Test
	public void testToArrayList() {
		Assert.assertEquals(List, A.toArrayList());

	}
}
