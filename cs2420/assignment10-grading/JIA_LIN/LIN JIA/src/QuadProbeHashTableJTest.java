package assignment10;

import static org.junit.Assert.*;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
/**
 * QuadProbeHashTableJTest 
 * @author LinJia
 *uid: u1091732
 */
public class QuadProbeHashTableJTest {

	QuadProbeHashTable emptyTable;
	QuadProbeHashTable smallTable;
	QuadProbeHashTable bigTable;
	@Before
	public void setUp(){
		
		smallTable = new QuadProbeHashTable(6, new GoodHashFunctor());
		emptyTable = new QuadProbeHashTable(6, new GoodHashFunctor());
		bigTable = new QuadProbeHashTable(6, new GoodHashFunctor());
		smallTable.add("dog");
	}
	@Test
	public void testSize() {
		assertEquals(0, emptyTable.size);
		assertEquals(1, smallTable.size);
		System.out.println(smallTable.capacity);
//		assertEquals(7, smallTable.capacity);
	}
	@Test
	public void testEmpty(){
		assertEquals(true, emptyTable.isEmpty());
	}
	@Test
	public void testClear(){
		smallTable.clear();
		assertEquals(true, smallTable.isEmpty());
	}
	@Test
	public void testAdd(){
		smallTable.add("cat");
		assertEquals(2, smallTable.size);
		assertEquals(false, smallTable.isEmpty());
	}
	@Test
	public void testAddAll(){
		ArrayList<String> list = new ArrayList<String>();
		list.add("dog");
		list.add("bird");
		list.add("bee");
		list.add("tiger");
		list.add("monkey");
		bigTable.addAll(list);
		assertEquals(5, bigTable.size);
		ArrayList<String> list2 = new ArrayList<String>();
		list2.add("dog");
		list2.add("bird");
		list2.add("bee");
		list2.add("bear");
		list2.add("camel");
		bigTable.addAll(list2);
		assertEquals(7, bigTable.size);
	}
	@Test
	public void testcontains(){
		assertEquals(true, smallTable.contains("dog"));
		assertEquals(false, smallTable.contains("eye"));

	}
	@Test
	public void testcontainsAll(){
		ArrayList<String> list = new ArrayList<String>();
		list.add("dog");
		list.add("bird");
		list.add("bee");
		list.add("tiger");
		list.add("monkey");
		bigTable.addAll(list);
		assertEquals(true, bigTable.containsAll(list));
	}

}

