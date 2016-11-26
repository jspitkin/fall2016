package assignment10;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Eduardo Ortiz
 * u0922628
 */
public class QuadProbeHashTableTest extends TestCase {
	QuadProbeHashTable table;
	QuadProbeHashTable tableSmall;
	QuadProbeHashTable tableEmpty;
	QuadProbeHashTable testTable;
	@Before
	public void setUp() throws Exception {
		
		testTable = new QuadProbeHashTable(10,new GoodHashFunctor()); 
		tableSmall =  new QuadProbeHashTable(10, new GoodHashFunctor());
		tableSmall.add("one");
		tableEmpty =  new QuadProbeHashTable(10, new GoodHashFunctor());
		table =  new QuadProbeHashTable(10, new GoodHashFunctor());
		ArrayList<String> words = readWordsFromFile("randomwords.txt");
				for(String s : words)
					table.add(s);
			
	}
	@Test
	public void testSingleItem() {
		assertEquals(1, tableSmall.currentSize);
		tableSmall.clear();
		assertEquals(0, tableSmall.currentSize);
		tableSmall.add("one");
		
		assertEquals(true,tableSmall.add("two"));
		assertEquals(true, tableSmall.contains("one"));
		assertEquals(false, tableSmall.add("one"));
		LinkedList<String> s = new LinkedList<String>();
		
		s.add("one");
		s.add("two");
		assertEquals(true, tableSmall.containsAll(s));
		
		tableSmall.clear();
		assertEquals(true, tableSmall.isEmpty());
	}
	@Test 
	public void testEmptyHash() {
		assertEquals(0, tableEmpty.currentSize);
		assertEquals(false, tableEmpty.contains("one"));
		assertEquals(0, tableEmpty.size());
		LinkedList<String> s = new LinkedList();
		assertEquals(true,tableEmpty.containsAll(s));
		s.add("hi");		
	}
	
	@Test
	public void testLargeHash() {
		
		assertEquals(12, table.currentSize);
		table.add("testingString");
		assertEquals(true, table.contains("testingString"));
		assertEquals(false, table.contains("two"));
		
		LinkedList<String> s = new LinkedList<String>();
		s.add("hola");
		s.add("bye");
		s.add("chao");
		assertEquals(false, table.containsAll(s));
		table.addAll(s);
		assertEquals(true, table.containsAll(s));
		assertEquals(false,table.add("testingString"));
		assertEquals(true, table.containsAll(s));
	
		table.clear();
		assertEquals(0, table.currentSize);
		assertEquals(true, table.isEmpty());	
	}

	@Test 
	public void testEdgeCases(){
		LinkedList<String> s = new LinkedList<String>();
		assertEquals(true,tableEmpty.containsAll(s));
		assertEquals(false,tableEmpty.addAll(s));
		table.add("hi");
		assertEquals(true,tableEmpty.containsAll(s));
		table.clear();
	}
	@Test (expected = NullPointerException.class)
	public void testNullCase1(){
		LinkedList<String> s = new LinkedList<String>();
		assertEquals(false,tableEmpty.add(null));
	}
	@Test (expected = NullPointerException.class)
	public void testNullCase2(){
		LinkedList<String> s = new LinkedList<String>();
		s.add(null);
		assertEquals(false,tableEmpty.addAll(s));
	}
	@Test (expected = NullPointerException.class)
	public void testNullCase3(){
		LinkedList<String> s = new LinkedList<String>();
		s.add(null);
		assertEquals(false,tableEmpty.contains(null));
	}
	@Test (expected = NullPointerException.class)
	public void testNullCase4(){
		LinkedList<String> s = new LinkedList<String>();
		s.add(null);
		table.add("hi");
		assertEquals(false,tableEmpty.contains(null));
	}
	@Test (expected = NullPointerException.class)
	public void testNullCase5(){
		LinkedList<String> s = new LinkedList<String>();
		s.add(null);
		table.add("hi");
		assertEquals(false,tableEmpty.containsAll(s));
	}
	@Test (expected = NullPointerException.class)
	public void testNullCase6(){
		LinkedList<String> s = new LinkedList<String>();
		s.add(null);
		assertEquals(false,tableEmpty.containsAll(s));
	}
	private static ArrayList<String> readWordsFromFile(String filename)
	{
		ArrayList<String> retVal = new ArrayList<String>();
		try 
		{
			BufferedReader input = new BufferedReader(new FileReader(filename));

			while(input.ready())
			{
				retVal.add(input.readLine());
			}
			input.close();
		} 
		catch (FileNotFoundException e) 
		{	
			e.printStackTrace();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		return retVal;
	}

}
