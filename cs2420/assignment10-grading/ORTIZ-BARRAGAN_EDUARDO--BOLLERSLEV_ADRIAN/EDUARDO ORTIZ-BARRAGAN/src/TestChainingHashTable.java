/**
Adrian
*/

package assignment10;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;

/**
 * @author Eduardo Ortiz, Adrian Bollerslev
 */
public class TestChainingHashTable {
	private ChainingHashTable cht;
	private ArrayList<String> zoo;
	private ArrayList<String> emptyzoo;
	
	@Before
	public void setup(){
		cht = new ChainingHashTable(10, new BadHashFunctor());
		zoo = new ArrayList<String>();
		emptyzoo = new ArrayList<String>();
		zoo.add("Monkey");
		zoo.add("Zebra");
		zoo.add("Panda");
		zoo.add("Koala");
		zoo.add("Zebra");
	}
	@Test
	public void testAdd() 
	{	
		assertEquals(true, cht.add("Dog"));
	}
	@Test
	public void testAddNull() 
	{	
		try {
			cht.add(null);
			fail();
		}
		catch (Exception e) {
			assertTrue(e instanceof NullPointerException);
		}
	}
	@Test
	public void testAddDuplicate() 
	{	
		cht.add("Dog");
		assertEquals(false, cht.add("Dog"));
	}
	@Test
	public void testContainsTrue() 
	{	
		cht.addAll(zoo);
		assertEquals(true, cht.contains("Monkey"));
	}
	@Test
	public void testContainsFalse() 
	{	
		cht.addAll(zoo);
		assertEquals(false, cht.contains("Dog"));
	}
	@Test
	public void testContainsNull() 
	{	
		cht.addAll(zoo);
		try {
			cht.contains(null);
			fail();
		}
		catch (Exception e) {
			assertTrue(e instanceof NullPointerException);
		}
	}
	@Test
	public void testSizeFull() 
	{
		cht.addAll(zoo);
		assertEquals(4, cht.size());
	}
	@Test
	public void testSizeEmpty() 
	{
		assertEquals(0, cht.size());
	}
	@Test
	public void testIsEmptyFalse() 
	{
		cht.addAll(zoo);
		assertEquals(false, cht.isEmpty());
	}
	@Test
	public void testIsEmptyTrue() 
	{
		assertEquals(true, cht.isEmpty());
	}
	@Test
	public void testAddAllEmpty()
	{
		assertEquals(false, cht.addAll(emptyzoo));		
	}
	@Test
	public void testAddAllNull()
	{
		try {
			cht.addAll(null);
			fail();
		}
		catch (Exception e) {
			assertTrue(e instanceof NullPointerException);
		}
	}
	@Test
	public void testAddAllTrue()
	{
		assertEquals(true, cht.addAll(zoo));
		assertEquals(true, cht.containsAll(zoo));
	}
	@Test
	public void testAddAllFalse()
	{
		cht.add("Monkey");
		cht.add("Zebra");
		cht.add("Panda");
		cht.add("Koala");
		assertEquals(false, cht.addAll(zoo));		
	}
	@Test
	public void testContainsAllTrue()
	{
		assertEquals(true, cht.addAll(zoo));
		assertEquals(true, cht.containsAll(zoo));
	}
	@Test
	public void testContainsAllFalse()
	{
		cht.add("Giraffe");
		cht.add("Chipmunk");
		assertEquals(false, cht.containsAll(zoo));		
	}
	@Test
	public void testClear()
	{
		cht.add("Giraffe");
		cht.add("Chipmunk");
		cht.clear();
		assertEquals(0, cht.size());		
	}
}



