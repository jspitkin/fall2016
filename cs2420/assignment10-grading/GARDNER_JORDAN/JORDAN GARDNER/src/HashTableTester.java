package assignment10;

import java.util.Collection;
import java.util.LinkedList;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
/**
 * 
 * @author Jordan Gardner u0566259
 * Tests for ChainingHashTable and QuadProbHashTable
 *
 */
public class HashTableTester extends TestCase {
	
	ChainingHashTable cht_one;
	ChainingHashTable cht_two;
	ChainingHashTable cht_three;
	
	QuadProbeHashTable qpht_one;
	QuadProbeHashTable qpht_two;
	QuadProbeHashTable qpht_three;

	@Before
	public void setUp() throws Exception {
		
	
		cht_two =  new ChainingHashTable(10, new GoodHashFunctor());
		cht_two.add("one");
		cht_three =  new ChainingHashTable(10, new GoodHashFunctor());
		cht_one =  new ChainingHashTable(10, new GoodHashFunctor());
		cht_one.add("Hello");
		cht_one.add("cat");
		cht_one.add("fried");
		cht_one.add("butter");
		cht_one.add("smoothie");
		cht_one.add("smooch");
		cht_one.add("cauliflower");
		cht_one.add("arithmetic");
		cht_one.add("zoolander");

		qpht_two =  new QuadProbeHashTable(10, new GoodHashFunctor());
		qpht_two.add("mini");
		qpht_three =  new QuadProbeHashTable(10, new GoodHashFunctor());
		qpht_one =  new QuadProbeHashTable(10, new GoodHashFunctor());
		qpht_one.add("smart");
		qpht_one.add("dumb");
		qpht_one.add("fat");
		qpht_one.add("is");
		qpht_one.add("beautiful");
		qpht_one.add("marvelous");
		qpht_one.add("caution");
		qpht_one.add("mirror");
		qpht_one.add("asus");
	}

	@Test
	public void testMultipleLists() {
		assertEquals(1,cht_two.numOfItems);
		assertEquals(1,qpht_two.numOfItems);
		qpht_two.clear();
		assertEquals(0,qpht_two.numOfItems);
		cht_two.add("meow");
		qpht_two.add("for");
		cht_one.add("dog");
		cht_one.add("lion");
		assertEquals(true, cht_two.contains("meow"));
		assertEquals(true, cht_one.contains("dog"));
		assertEquals(false, cht_two.contains("peacock"));
		assertEquals(true, qpht_two.contains("for"));

		LinkedList<String> s=new LinkedList<String>();
		s.add("meow");
		
		assertEquals(true,cht_two.containsAll((Collection<? extends String>) s));

		s.add("mini");
		assertEquals(false,cht_two.containsAll((Collection<? extends String>) s));
		
		qpht_three.addAll((Collection<? extends String>) s);
		assertEquals(true,qpht_three.containsAll((Collection<? extends String>) s));
		assertEquals(2,qpht_three.numOfItems);
		s.remove(0);
		cht_two.add("mini");
		
		assertEquals(true,cht_two.containsAll((Collection<? extends String>) s));
		cht_two.clear();
		qpht_three.clear();
		assertEquals(false,cht_two.containsAll((Collection<? extends String>) s));
		assertEquals(false,qpht_three.containsAll((Collection<? extends String>) s));
		assertEquals(false,cht_two.size()==2);
		assertFalse(qpht_three.size()==5);
		
		assertEquals(9,qpht_one.size());
		assertEquals(11,cht_one.size());
		
	}
	
	@Test
	public void testEmptyList() {
		qpht_one.clear();
		qpht_two.clear();
		assertEquals(0,qpht_one.size());
		assertEquals(0,qpht_two.numOfItems);
		cht_one.add("no");
		assertFalse(cht_one.numOfItems<1);
		cht_one.clear();
		assertTrue(cht_one.numOfItems==0);
		LinkedList<String> s= new LinkedList<String>();
		s.add("friend");
		qpht_three.addAll((Collection<? extends String>) s);
		assertEquals(1, qpht_three.numOfItems);
		qpht_three.clear();
		assertTrue(qpht_three.size()==0);
		assertTrue(qpht_three.isEmpty());
		qpht_three.add("one");
		assertFalse(qpht_three.isEmpty());
		assertEquals(1,qpht_three.numOfItems);
		
	}

}