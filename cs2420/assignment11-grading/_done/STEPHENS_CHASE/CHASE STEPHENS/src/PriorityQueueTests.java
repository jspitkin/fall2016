package assignment11;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 * @author Chase Stephens
 * 
 *         Tests for Priority Queue.
 *
 */

public class PriorityQueueTests {

	PriorityQueue q1, q2, q3, q4, q5;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception {
		q1 = new PriorityQueue<String>();
		q2 = new PriorityQueue<String>();
		q3 = new PriorityQueue<Integer>();
		q5 = new PriorityQueue<Integer>();
		q4 = new PriorityQueue<Character>();

		q1.add("mno");
		q1.add("jkl");
		q1.add("ghi");
		q1.add("def");
		q1.add("abc");

		q2.add("99");
		q2.add("49");
		q2.add("48");
		q2.add("30");
		q2.add("26");
		q2.add("50");
		q2.add("50");
		q2.add("43");
		q2.add("10");

		q4.add('j');
		q4.add('s');
		q4.add('e');
		q4.add('u');
		q4.add('m');
		q4.add('a');
		q4.add('f');
		q4.add('d');
		q4.add('j');
		q4.add('k');
		q4.add('l');
		q4.add('o');
		q4.add('x');
		q4.add('v');
		q4.add('a');
		q4.add('n');
		q4.add('z');
		q4.add('c');
		q4.add('b');
		q4.add('m');
		q4.add('q');
		q4.add('w');
		q4.add('r');
		q4.add('t');
		q4.add('y');
		q4.add('i');
		q4.add('t');
		q4.add('y');
		q4.add('i');
		q4.generateDotFile("q4.dot");

		for (int i = -500; i < 100000; i += 3) {
			q3.add(i);
		}

		for (int i = 0; i < 10; i++) {
			q5.add(i);
		}
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindMinq1() {
		q1.generateDotFile("q1.dot");
		assertEquals("abc", q1.findMin());
	}

	@Test
	public void testFindMinq2() {
		q2.generateDotFile("q2.dot");
		assertEquals("10", q2.findMin());
	}

	@Test
	public void testFindMinq3() {
		q3.generateDotFile("q3.dot");
		assertEquals(-500, q3.findMin());
	}

	@Test
	public void testFindMinq4() {
		assertEquals('a', q4.findMin());
	}

	@Test
	public void deleteMinq3() {
		for (int i = -500; i < 99990; i += 3) {
			assertEquals(i, q3.deleteMin());
		}
	}

	@Test
	public void deleteMinq1() {
		assertEquals("abc", q1.deleteMin());
		assertEquals("def", q1.deleteMin());
		assertEquals("ghi", q1.deleteMin());
		q1.generateDotFile("q1Min.dot");
		assertEquals("jkl", q1.deleteMin());
		assertEquals("mno", q1.deleteMin());
	}

	@Test
	public void testFindMinAfterClearing() {
		q3.clear();
		q3.add(5);
		q3.add(1);
		q3.add(10);
		q3.generateDotFile("q3cleared.dot");
		assertEquals(1, q3.findMin());
	}

	@Test
	public void deleteMinq4() {
		assertEquals('a', q4.deleteMin());
		assertEquals('a', q4.deleteMin());
		assertEquals('b', q4.deleteMin());
		assertEquals('c', q4.deleteMin());
		assertEquals('d', q4.deleteMin());
		assertEquals('e', q4.deleteMin());
		q4.generateDotFile("q4DeleteMin.dot");
	}

	@Test
	public void deleteMinq2() {
		assertEquals("10", q2.deleteMin());
		assertEquals("26", q2.deleteMin());
		assertEquals("30", q2.deleteMin());
		assertEquals("43", q2.deleteMin());
		assertEquals("48", q2.deleteMin());
		assertEquals("49", q2.deleteMin());
		assertEquals("50", q2.deleteMin());
		assertEquals("50", q2.deleteMin());
		assertEquals("99", q2.deleteMin());
		q2.generateDotFile("empty.dot");
	}

	@Test
	public void AddAndDelete() {
		q5.clear();
		q5.add(100);
		q5.add(50);
		q5.add(25);
		assertEquals(25, q5.findMin());
		assertEquals(25, q5.deleteMin());
		q5.add(30);
		assertEquals(30, q5.findMin());
		assertEquals(30, q5.deleteMin());
		assertEquals(50, q5.deleteMin());
	}

	@Test
	public void AddAndDelete2() {
		q5.deleteMin();
		q5.deleteMin();
		q5.deleteMin();
		assertEquals(3, q5.deleteMin());
		q5.add(-5);
		assertEquals(-5, q5.deleteMin());
		q5.deleteMin();
		assertEquals(5, q5.findMin());
	}

}
