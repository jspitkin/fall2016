package assignment10;

import static org.junit.Assert.*;
import java.lang.NullPointerException;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

/**
 * Testing class for hash tables
 * @author Kira Parker u1073760
 *
 */
public class JUnitTestSuite {
	GoodHashFunctor ghf;
	MediocreHashFunctor mhf;
	BadHashFunctor bhf;
	QuadProbeHashTable emptyQuadTable, quadTable;
	ChainingHashTable emptyChainTable, chainTable;

	@Before
	public void setUp() throws Exception {
		ghf = new GoodHashFunctor();
		mhf = new MediocreHashFunctor();
		bhf = new BadHashFunctor();
		emptyQuadTable = new QuadProbeHashTable(10,ghf);
		quadTable = new QuadProbeHashTable(20, ghf);
		String[] items = {"apple", "elephant", "bear", "pikachu", "blueberry", "porkchop", "apple",
				"banana", "carrot", "pickle", "bread", "ice cream", "<:)", ":*", "moooo"};
		quadTable.addAll(Arrays.asList(items));
		emptyChainTable = new ChainingHashTable(10,ghf);
		chainTable = new ChainingHashTable(10, ghf);
		chainTable.addAll(Arrays.asList(items));
	}
	
	@Test
	public void sizeQuadTable(){
		assertEquals(14, quadTable.size());
	}
	
	@Test
	public void addemptyQuadTable(){
		assertTrue(emptyQuadTable.add("kira"));
		assertFalse(emptyQuadTable.add("kira"));
		assertTrue(emptyQuadTable.add("kiva"));
		assertTrue(emptyQuadTable.add("moooooo"));
		assertTrue(emptyQuadTable.add("345_`13'"));
		assertTrue(!emptyQuadTable.isEmpty() && emptyQuadTable.size() == 4);
	}
	
	@Test
	public void rehashEmptyQuadTable(){
		assertTrue(emptyQuadTable.add("one") && emptyQuadTable.add("two") && emptyQuadTable.add("three")
				&& emptyQuadTable.add("four") && emptyQuadTable.add("five") && emptyQuadTable.add("six")
				&& emptyQuadTable.add("seven"));
		assertEquals(7, emptyQuadTable.size());
		assertFalse(emptyQuadTable.add("seven"));
	}
	
	@Test
	public void addAllEmptyQuadTableTrue(){
		String[] items = {"apple", "elephant", "bear", "pikachu", "blueberry", "porkchop", "apple"};
		assertTrue(emptyQuadTable.addAll(Arrays.asList(items)));
		assertEquals(6, emptyQuadTable.size());
	}
	
	@Test
	public void addAllEmptyQuadTableFalse(){
		emptyQuadTable.add("alpha");
		String[] items = {"alpha", "alpha", "alpha"};
		assertFalse(emptyQuadTable.addAll(Arrays.asList(items)));
	}
	
	@Test
	public void addAllEmptyListEmptyQuadTable(){
		assertFalse(emptyQuadTable.addAll(new ArrayList<String>()));
	}
	
	@Test
	public void containsQuadTable(){
		assertTrue(quadTable.contains("apple") && quadTable.contains("banana") && 
				quadTable.contains("pickle") && quadTable.contains("<:)"));
		assertFalse(quadTable.contains("nmnm") && quadTable.contains("abple"));
	}
	
	@Test
	public void containsAllQuadTable(){
		String[] items = {"apple", "elephant", "bear", "pikachu", "blueberry", "porkchop", "apple",
				"banana", "carrot", "pickle", "bread", "ice cream", "<:)", ":*", "moooo"};
		assertTrue(quadTable.containsAll(Arrays.asList(items)));
	}
	
	@Test(expected = NullPointerException.class)
	public void addNullQuadTable(){
		quadTable.add(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void containsNullQuadTable(){
		quadTable.contains(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void containsAllNullQuadTable(){
		quadTable.containsAll(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void addAllNullQuadTable(){
		quadTable.addAll(null);
	}

	
	//CHAINING HASH TABLE TESTS
	@Test
	public void addEmptyChainTable(){
		assertTrue(emptyChainTable.add("kira"));
		assertTrue(emptyChainTable.add("kiva"));
		assertFalse(emptyChainTable.add("kira"));
	}
	
	@Test
	public void addAllEmptyChainTable(){
		assertTrue(emptyChainTable.isEmpty() && emptyChainTable.size() == 0);
		String[] items = {"apple", "elephant", "bear", "pikachu", "blueberry", "porkchop", "apple",
				"banana", "carrot", "pickle", "bread", "ice cream", "<:)", ":*", "moooo"};
		assertTrue(emptyChainTable.addAll(Arrays.asList(items)));
		assertEquals(14, emptyChainTable.size());
		assertFalse(emptyChainTable.addAll(Arrays.asList(items)));
		assertEquals(14, emptyChainTable.size());
	}
	
	@Test
	public void containsChainTable(){
		String[] items = {"apple", "elephant", "bear", "pikachu", "blueberry", "porkchop", "apple",
				"banana", "carrot", "pickle", "bread", "ice cream", "<:)", ":*", "moooo"};
		for(String item: items){
			assertTrue(chainTable.contains(item));
		}
		assertFalse(chainTable.contains("kira"));
	}
	
	@Test
	public void containsAllChainTable(){
		String[] items = {"apple", "elephant", "bear", "pikachu", "blueberry", "porkchop", "apple",
				"banana", "carrot", "pickle", "bread", "ice cream", "<:)", ":*", "moooo"};
		assertTrue(chainTable.containsAll(Arrays.asList(items)));
		String[] items2 = {"a", "b", "c", "d", "e", "apple"};
		assertFalse(chainTable.containsAll(Arrays.asList(items2)));
	}
	
	@Test(expected = NullPointerException.class)
	public void addNullChainTable(){
		chainTable.add(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void addAllNullChainTable(){
		chainTable.addAll(null);
	}
	
	@Test(expected = NullPointerException.class)
	public void containsAllNullChainTable(){
		chainTable.containsAll(null);
	}
	
	
	@Test(expected = NullPointerException.class)
	public void containsNullChainTable(){
		assertFalse(chainTable.contains(null));
	}

}
