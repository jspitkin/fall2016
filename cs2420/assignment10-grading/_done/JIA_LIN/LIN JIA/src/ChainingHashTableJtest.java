package assignment10;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
/**
 * ChainingHashTableJtest
 * @author Lin Jia
 * uid: u1091732
 *
 */
public class ChainingHashTableJtest {
	ChainingHashTable emptyTable;
	ChainingHashTable smallTable;
	ChainingHashTable bigTable;
	@Before
	public void setUp(){
		
		smallTable = new ChainingHashTable(6, new GoodHashFunctor());
		emptyTable = new ChainingHashTable(6, new GoodHashFunctor());
		bigTable = new ChainingHashTable(6, new GoodHashFunctor());
		smallTable.add("dog");
	}
	@Test
	public void testSize() {
		assertEquals(0, emptyTable.size);
		assertEquals(1, smallTable.size);
		assertEquals(7, smallTable.capacity);
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
		assertEquals(5, bigTable.size);
		assertEquals(true, bigTable.containsAll(list));
	}

}
