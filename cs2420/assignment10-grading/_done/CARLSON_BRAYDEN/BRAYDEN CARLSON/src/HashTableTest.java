package assignment10;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit class for testing QuadProbeHashTable and ChainingHashTable
 * @author Brayden Carlson
 *
 */
public class HashTableTest {
	
	//****QuadProbeHashTable testing****
	
	QuadProbeHashTable emptyTable;
	QuadProbeHashTable table;
	
	@Before
	public void quadProbeSetup() {
		emptyTable = new QuadProbeHashTable(11, new BadHashFunctor());
		table = new QuadProbeHashTable(11, new BadHashFunctor());
		table.add("a");
		table.add("aa");
		table.add("aaa");
		table.add("b");
	}
	
	@Test
	public void quadProbeConstructionTest() {
		QuadProbeHashTable table = new QuadProbeHashTable(8, null);
		assertEquals(11, table.capacity());
	}
	
	@Test
	public void quadProbeAddTest() {
		assertEquals(4, table.size());
		assertEquals(false, table.add("aa"));
		assertEquals(4, table.size());
		assertEquals(true, table.contains("aa"));
		assertEquals(true, table.contains("b"));
	}
	
	@Test
	public void quadProbeAddAllTest() {
		ArrayList<String> array = new ArrayList<String>();
		array.add("a");
		array.add("aa");
		array.add("aaa");
		array.add("b");
		array.add("aaaaa");
		assertEquals(false, emptyTable.containsAll(array));
		emptyTable.addAll(array);
		assertEquals(5, emptyTable.size());
		assertEquals(true, emptyTable.contains("a"));
		assertEquals(true, emptyTable.containsAll(array));
		array = new ArrayList<String>();
		emptyTable.clear();
		assertEquals(false, emptyTable.addAll(array));
	}
	
	@Test
	public void quadProbeContainsTest() {
		assertFalse(emptyTable.contains("a"));
		assertFalse(emptyTable.contains(""));
		emptyTable.add("a");
		assertTrue(emptyTable.contains("a"));
		ArrayList<String> array = new ArrayList<String>();
		assertTrue(emptyTable.containsAll(array));
	}
	
	@Test
	public void quadProbeClearTest() {
		assertFalse(table.isEmpty());
		table.clear();
		assertTrue(table.isEmpty());
		
	}
	
	@Test
	public void quadProbeSizeTest() {
		assertEquals(0, emptyTable.size());
		emptyTable.add("a");
		assertEquals(1, emptyTable.size());
		emptyTable.add("aa");
		assertEquals(2, emptyTable.size());
		emptyTable.add("bb");
		assertEquals(3, emptyTable.size());
		emptyTable.add("a");
		assertEquals(3, emptyTable.size());
		emptyTable.add("aaa");
		emptyTable.add("aaaa");
		emptyTable.add("aaaaa");
		assertEquals(6, emptyTable.size());
		emptyTable.add("b");
		assertEquals(7, emptyTable.size());
		ArrayList<String> array = new ArrayList<String>();
		array.add("1");
		array.add("22");
		array.add("333");
		emptyTable.addAll(array);
		assertEquals(10, emptyTable.size());
	}
	
	@Test
	public void quadProbeResizeTest() {
		QuadProbeHashTable table = new QuadProbeHashTable(3, new BadHashFunctor());
		assertEquals(3, table.capacity());
		table.add("");
		table.add("1");
		assertEquals(5, table.capacity());
		table.add("22");
		assertEquals(7, table.capacity());
		table.add("333");
		assertEquals(11, table.capacity());
		table.add("44444444444");
		table.add("55555555555");
	}
	
	//****ChainingHashTable testing****
	ChainingHashTable chainTable;
	
	@Before
	public void chainingSetup() {
		chainTable = new ChainingHashTable(11, new BadHashFunctor());
		chainTable = new ChainingHashTable(11, new BadHashFunctor());
		chainTable.add("a");
		chainTable.add("aa");
		chainTable.add("aaa");
		chainTable.add("b");
	}
	
	@Test
	public void chainingAddTest() {
		assertEquals(4, chainTable.size());
		assertEquals(false, chainTable.add("aa"));
		assertEquals(4, chainTable.size());
		assertEquals(true, chainTable.contains("aa"));
		assertEquals(true, chainTable.contains("b"));
	}
	
	@Test
	public void chainingAddAllTest() {
		ArrayList<String> array = new ArrayList<String>();
		array.add("a");
		array.add("aa");
		array.add("aaa");
		array.add("b");
		array.add("aaaaa");
		assertEquals(false, chainTable.containsAll(array));
		chainTable.clear();
		chainTable.addAll(array);
		assertEquals(5, chainTable.size());
		assertEquals(true, chainTable.contains("a"));
		assertEquals(true, chainTable.containsAll(array));
	}
	
	@Test
	public void chainingContainsTest() {
		chainTable.clear();
		assertFalse(chainTable.contains("a"));
		assertFalse(chainTable.contains(""));
		chainTable.add("a");
		assertTrue(chainTable.contains("a"));
		ArrayList<String> array = new ArrayList<String>();
		assertTrue(chainTable.containsAll(array));
	}
	
	@Test
	public void chainingClearTest() {
		assertFalse(chainTable.isEmpty());
		chainTable.clear();
		assertTrue(chainTable.isEmpty());
		
	}
	
	@Test
	public void chainingSizeTest() {
		chainTable.clear();
		assertEquals(0, chainTable.size());
		chainTable.add("a");
		assertEquals(1, chainTable.size());
		chainTable.add("aa");
		ArrayList<String> array = new ArrayList<String>();
		array.add("b");
		array.add("aa");
		array.add("aaa");
		chainTable.addAll(array);
		assertEquals(4, chainTable.size());
	}
}
