package assignment11;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * testing class for PriorityQueue.java
 * @author Braeden Barwick u0959391
 */
public class PQTester {
	private ReverseStringComparator testCmp;
	private PriorityQueue<String> pq;
	private PriorityQueue<String> pqCmp;
	
	@Before
	public void setUp(){
		testCmp = new ReverseStringComparator();
		pq = new PriorityQueue<String>();
		pqCmp = new PriorityQueue<String>(testCmp);
	}
	
	@Test
	public void addEmpty() {
		pq.add("test");
		assertTrue(pq.findMin().compareTo("test") == 0);
	}
	
	@Test
	public void add(){
		pq.add("b");
		pq.add("c");
		pq.add("a");
		assertTrue(pq.findMin().compareTo("a") == 0);
	}
	
	@Test
	public void addMore(){
		pq.add("b");
		pq.add("c");
		pq.add("f");
		pq.add("g");
		pq.add("j");
		pq.add("h");
		pq.add("e");
		pq.add("o");
		pq.add("x");
		pq.add("t");
		pq.add("u");
		pq.add("i");
		pq.add("d");
		pq.add("a");
		pq.generateDotFile("addMore.dot");
		assertTrue(pq.findMin().compareTo("a") == 0);
	}
	
	@Test
	public void remove(){
		pq.add("b");
		pq.add("c");
		pq.add("f");
		pq.add("g");
		pq.add("j");
		pq.add("h");
		pq.add("e");
		pq.add("o");
		pq.add("x");
		pq.add("t");
		pq.add("u");
		pq.add("i");
		pq.add("d");
		pq.add("a");
		pq.generateDotFile("removeBefore.dot");
		pq.deleteMin();
		pq.generateDotFile("removeAfter.dot");
		assertTrue(pq.findMin().compareTo("b") == 0);
	}

	@Test
	public void removeMore(){
		pq.add("b");
		pq.add("c");
		pq.add("f");
		pq.add("g");
		pq.add("j");
		pq.add("h");
		pq.add("e");
		pq.add("o");
		pq.add("x");
		pq.add("t");
		pq.add("u");
		pq.add("i");
		pq.add("d");
		pq.add("a");
		pq.generateDotFile("remove1.dot");
		pq.deleteMin();
		pq.generateDotFile("remove2.dot");
		assertTrue(pq.findMin().compareTo("b") == 0);
		pq.deleteMin();
		pq.generateDotFile("remove3.dot");
		assertTrue(pq.findMin().compareTo("c") == 0);
		pq.deleteMin();
		pq.generateDotFile("remove4.dot");
		assertTrue(pq.findMin().compareTo("d") == 0);
		pq.deleteMin();
		pq.generateDotFile("remove5.dot");
		assertTrue(pq.findMin().compareTo("e") == 0);
	}
	
	@Test
	public void checkStructure(){
		pq.add("b");
		pq.add("c");
		pq.add("f");
		pq.add("g");
		pq.add("j");
		pq.add("h");
		pq.add("e");
		pq.add("o");
		pq.add("x");
		pq.add("t");
		pq.add("u");
		pq.add("i");
		pq.add("d");
		pq.add("a");
		assertTrue(pq.checkStructure(0));
	}
	
	// now start tests with a comparator. I use a reverse-order string comparator, for easy reading of .dot files
	
	@Test
	public void addCmp(){
		pqCmp.add("b");
		pqCmp.add("c");
		pqCmp.add("a");
		assertTrue(pqCmp.findMin().compareTo("c") == 0);
	}
	
	@Test
	public void addMoreCmp(){
		pqCmp.add("b");
		pqCmp.add("c");
		pqCmp.add("f");
		pqCmp.add("g");
		pqCmp.add("j");
		pqCmp.add("h");
		pqCmp.add("e");
		pqCmp.add("o");
		pqCmp.add("x");
		pqCmp.add("t");
		pqCmp.add("u");
		pqCmp.add("i");
		pqCmp.add("d");
		pqCmp.add("a");
		pqCmp.generateDotFile("addMoreCmp.dot");
		assertTrue(pqCmp.findMin().compareTo("x") == 0);
	}
	
	@Test
	public void removeCmp(){
		pqCmp.add("b");
		pqCmp.add("c");
		pqCmp.add("f");
		pqCmp.add("g");
		pqCmp.add("j");
		pqCmp.add("h");
		pqCmp.add("e");
		pqCmp.add("o");
		pqCmp.add("x");
		pqCmp.add("t");
		pqCmp.add("u");
		pqCmp.add("i");
		pqCmp.add("d");
		pqCmp.add("a");
		pqCmp.generateDotFile("removeBeforeCmp.dot");
		pqCmp.deleteMin();
		pqCmp.generateDotFile("removeAfterCmp.dot");
		assertTrue(pqCmp.findMin().compareTo("u") == 0);
	}

	@Test
	public void removeMoreCmp(){
		pqCmp.add("b");
		pqCmp.add("c");
		pqCmp.add("f");
		pqCmp.add("g");
		pqCmp.add("j");
		pqCmp.add("h");
		pqCmp.add("e");
		pqCmp.add("o");
		pqCmp.add("x");
		pqCmp.add("t");
		pqCmp.add("u");
		pqCmp.add("i");
		pqCmp.add("d");
		pqCmp.add("a");
		pqCmp.generateDotFile("removeCmp1.dot");
		pqCmp.deleteMin();
		pqCmp.generateDotFile("removeCmp2.dot");
		assertTrue(pqCmp.findMin().compareTo("u") == 0);
		pqCmp.deleteMin();
		pqCmp.generateDotFile("removeCmp3.dot");
		assertTrue(pqCmp.findMin().compareTo("t") == 0);
		pqCmp.deleteMin();
		pqCmp.generateDotFile("removeCmp4.dot");
		assertTrue(pqCmp.findMin().compareTo("o") == 0);
		pqCmp.deleteMin();
		pqCmp.generateDotFile("remove5Cmp.dot");
		assertTrue(pqCmp.findMin().compareTo("j") == 0);
	}
	
	@Test
	public void checkStructureCmp(){
		pqCmp.add("b");
		pqCmp.add("c");
		pqCmp.add("f");
		pqCmp.add("g");
		pqCmp.add("j");
		pqCmp.add("h");
		pqCmp.add("e");
		pqCmp.add("o");
		pqCmp.add("x");
		pqCmp.add("t");
		pqCmp.add("u");
		pqCmp.add("i");
		pqCmp.add("d");
		pqCmp.add("a");
		assertTrue(pqCmp.checkStructure(0));
	}
}
