package assignment10;
/**
 * 
 * @author Taylor Cassity
 *
 */
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class QuadProbeHashTableTest {

	GoodHashFunctor ghf = new GoodHashFunctor();
	QuadProbeHashTable table = new QuadProbeHashTable(10, ghf);
	
	@Before
	public void setUp(){
		table.add("apple");
		table.add("apple2");
		table.add("apple3");
		table.add("banana");
		table.add("cheese");
		table.add("delta");
	}
	//add, resize, adall, clear, contains, containsall, isempty, size
	@Test
	public void add() {
		assertTrue(table.add(" "));
		for(int x = 0; x < 10; x++)
			assertTrue(table.add("delta"));
		assertTrue(table.contains("delta"));
		assertTrue(table.contains("apple"));
		assertTrue(table.contains("banana"));
		assertTrue(table.contains("cheese"));
		assertTrue(table.contains(" "));

	}
	
	@Test
	public void contains() {
		assertTrue(table.contains("apple"));
		assertTrue(table.contains("banana"));
		assertTrue(table.contains("cheese"));
		assertTrue(table.contains("delta"));
	}
	
	@Test
	public void resize() {
		int x = table.size();
		table.add("something");
		assertTrue(x < table.toArray().length);
	}
	@Test
	public void empty() {
		table.clear();
		assertTrue(table.isEmpty());
	}
	
	
	
	@Test
	public void print(){
		table.print();
	}

}
