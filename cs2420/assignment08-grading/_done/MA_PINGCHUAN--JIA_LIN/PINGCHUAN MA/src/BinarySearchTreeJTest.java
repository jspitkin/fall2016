package assignment08;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
/*
 * @author Lin Jia uid:u1091732
 * @author Pingchuan Ma uid:u0805309
 */
public class BinarySearchTreeJTest extends TestCase{
	BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
	BinarySearchTree<Integer> emptyTree = new BinarySearchTree<Integer>();
	@Before
	public void setUp(){
		tree.add(20);
		tree.add(10);
		tree.add(4);
		tree.add(2);
		tree.add(1);
		tree.add(3);
		tree.add(7);
		tree.add(5);
		tree.add(6);
		tree.add(9);
		tree.add(8);
		tree.add(16);
		tree.add(12);
		tree.add(11);
		tree.add(14);
		tree.add(13);
		tree.add(15);
		tree.add(17);
		tree.add(19);
		tree.add(18);
		tree.add(30);
		tree.add(25);
		tree.add(22);
		tree.add(21);
		tree.add(24);
		tree.add(23);
		tree.add(27);
		tree.add(26);
		tree.add(28);
		tree.add(29);
		tree.add(31);
		
		
	}
	@Test
	public void testSize(){
		assertEquals(0, emptyTree.size());
		assertEquals(31, tree.size());
	}

	@Test
	public void testContains(){
		assertEquals(true, tree.contains(1));
		assertEquals(false, tree.contains(0));
		assertEquals(false, emptyTree.contains(0));
	}
	@Test
	public void testContainsAll(){
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(3);
		list.add(5);
		list.add(7);
		list.add(9);
		assertEquals(true, tree.containsAll(list));

	}
	

	@Test
	public void testToArrayList(){
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		list.add(8);
		list.add(9);
		list.add(10);
		list.add(11);
		list.add(12);
		list.add(13);
		list.add(14);
		list.add(15);
		list.add(16);
		list.add(17);
		list.add(18);
		list.add(19);
		list.add(20);
		list.add(21);
		list.add(22);
		list.add(23);
		list.add(24);
		list.add(25);
		list.add(26);
		list.add(27);
		list.add(28);
		list.add(29);
		list.add(30);
		list.add(31);
		assertEquals(list, tree.toArrayList());
		
		ArrayList<Integer> temp = emptyTree.toArrayList();
		assertEquals(temp.size(), emptyTree.size());

	}
	@Test
	public void testClear(){
		tree.clear();
		assertEquals(tree.size(), emptyTree.size());
	}
	@Test
	public void testFirst(){
		assertTrue(tree.first().equals(1));

	}
	@Test
	public void testLast(){
		assertTrue(tree.last().equals(31));
	}
	@Test
	public void testIsEmpty(){
		assertEquals(true, emptyTree.isEmpty());
		tree.clear();
		assertEquals(true, tree.isEmpty());

	}
	@Test
	public void testAdd(){
		tree.add(32);
		tree.add(33);
		assertEquals(33, tree.size());

	}
	@Test
	public void testAddAll(){
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		list.add(8);
		list.add(9);
		list.add(10);
		list.add(11);
		list.add(12);
		list.add(13);
		list.add(14);
		list.add(15);
		list.add(16);
		list.add(17);
		list.add(18);
		list.add(19);
		list.add(20);
		list.add(21);
		list.add(22);
		list.add(23);
		list.add(24);
		list.add(25);
		list.add(26);
		list.add(27);
		list.add(28);
		list.add(29);
		list.add(30);
		list.add(31);
		emptyTree.addAll(list);
		assertEquals(tree.toArrayList(), emptyTree.toArrayList());

	}
	@Test
	public void testRemove(){
		tree.remove(10);
		tree.remove(2);
		System.out.println(tree.size());
		assertEquals(29, tree.size());
		

	}
	@Test
	public void testRemoveAll(){
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		list.add(8);
		list.add(9);
		list.add(10);
		list.add(11);
		list.add(12);
		list.add(13);
		list.add(14);
		list.add(15);
		list.add(16);
		list.add(17);
		list.add(18);
		list.add(19);
		list.add(20);
		list.add(21);
		list.add(22);
		list.add(23);
		list.add(24);
		list.add(25);
		list.add(26);
		list.add(27);
		list.add(28);
		list.add(29);
		list.add(30);
		list.add(31);
		tree.removeAll(list);
		System.out.println(tree.size());
		assertEquals(tree.toArrayList(), emptyTree.toArrayList());
	}
	@Test
	public void test() {
		tree.writeDot("treeJTest.dot");
		
		
	}
	

}
