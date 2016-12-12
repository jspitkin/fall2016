package assignment11;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PriorityQueueTest {

	public PriorityQueue<Integer> pq_e = new PriorityQueue<>();
	public PriorityQueue<Integer> pq_s = new PriorityQueue<>();
	public PriorityQueue<Integer> pq_m = new PriorityQueue<>();
	public PriorityQueue<Integer> pq_l = new PriorityQueue<>();

	@Before
	public void setUp() throws Exception {
		pq_e = new PriorityQueue<>();
		pq_s = new PriorityQueue<>();
		pq_m = new PriorityQueue<>();
		pq_l = new PriorityQueue<>();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void add_e() {
		pq_e.add(0);
	}

	@Test
	public void add_s_ordered() {
		pq_s.add(0);
		assertTrue(pq_s.size() == 1);
		pq_s.add(1);
		assertTrue(pq_s.size() == 2);
		for (int i = 2; i < 20; i++)
			pq_s.add(i);
		pq_s.generateDotFile("pqdot.dot");
	}

	@Test
	public void add_m_unordered() {
		ArrayList<Integer> al = new ArrayList<Integer>();
		for (int i = 0; i < 1000; i++) {
			al.add(i);
		}
		Collections.shuffle(al);
		for (int i = 0; i < 1000; i++) {
			pq_m.add(al.get(i));
		}
		pq_m.generateDotFile("med_rand.dot");
	}

	@Test
	public void add_l_reverse_ordered() {
		ArrayList<Integer> al = new ArrayList<Integer>();
		for (int i = 0; i < 1_000_000; i++) {
			al.add(999_999 - i);
		}
		for (int i = 0; i < 1_000_000; i++) {
			pq_l.add(al.get(i));
		}
		pq_l.generateDotFile("lar_rev.dot");
	}

	@Test(expected = NoSuchElementException.class)
	public void delete_e() {
		pq_e.deleteMin();
	}

	@Test
	public void delete_s() {
		for (int i = 0; i < 20; i++)
			pq_s.add(i);
		for (int i = 0; i < 20; i++)
			assertTrue(pq_s.deleteMin().equals(i));
	}

	@Test
	public void delete_m() {
		ArrayList<Integer> al = new ArrayList<Integer>();
		for (int i = 0; i < 1000; i++)
			al.add(i);
		Collections.shuffle(al);
		for (int i = 0; i < 1000; i++)
			pq_m.add(al.get(i));
		for (int i = 0; i < 1000; i++)
			assertTrue(pq_m.deleteMin().equals(i));
	}

	@Test
	public void delete_l() {
		ArrayList<Integer> al = new ArrayList<Integer>();
		for (int i = 0; i < 1_000_000; i++)
			al.add(999_999 - i);
		for (int i = 0; i < 1_000_000; i++)
			pq_l.add(al.get(i));
		for (int i = 0; i < 1000; i++)
			assertTrue(pq_l.deleteMin().equals(i));
	}

	@Test(expected = NoSuchElementException.class)
	public void min_e() {
		pq_e.findMin();
	}

	@Test
	public void min_s() {
		for (int i = 0; i < 20; i++)
			pq_s.add(i);
		for (int i = 0; i < 20; i++) {
			assertTrue(pq_s.findMin().equals(i));
			assertTrue(pq_s.deleteMin().equals(i));
		}
	}

	@Test
	public void min_m() {
		ArrayList<Integer> al = new ArrayList<Integer>();
		for (int i = 0; i < 1000; i++)
			al.add(i);
		Collections.shuffle(al);
		for (int i = 0; i < 1000; i++)
			pq_m.add(al.get(i));
		for (int i = 0; i < 1000; i++) {
			assertTrue(pq_m.findMin().equals(i));
			assertTrue(pq_m.deleteMin().equals(i));
		}
	}

	@Test
	public void min_l() {
		ArrayList<Integer> al = new ArrayList<Integer>();
		for (int i = 0; i < 1_000_000; i++)
			al.add(999_999 - i);
		for (int i = 0; i < 1_000_000; i++)
			pq_l.add(al.get(i));
		for (int i = 0; i < 1_000_000; i++) {
			assertTrue(pq_l.findMin().equals(i));
			assertTrue(pq_l.deleteMin().equals(i));
		}
	}

	@Test(expected = NoSuchElementException.class)
	public void clear_s() {
		for (int i = 0; i < 20; i++)
			pq_s.add(i);
		assertTrue(pq_m.size() == 0);
		pq_s.clear();
		for (int i = 0; i < 20; i++)
			pq_s.deleteMin();
	}

	@Test(expected = NoSuchElementException.class)
	public void clear_m() {
		ArrayList<Integer> al = new ArrayList<Integer>();
		for (int i = 0; i < 1000; i++)
			al.add(i);
		Collections.shuffle(al);
		for (int i = 0; i < 1000; i++)
			pq_m.add(al.get(i));
		pq_m.clear();
		assertTrue(pq_m.size() == 0);
		for (int i = 0; i < 1000; i++)
			pq_m.deleteMin();
	}

	@Test(expected = NoSuchElementException.class)
	public void clear_l() {
		ArrayList<Integer> al = new ArrayList<Integer>();
		for (int i = 0; i < 1_000_000; i++)
			al.add(999_999 - i);
		for (int i = 0; i < 1_000_000; i++)
			pq_l.add(al.get(i));
		pq_l.clear();
		assertTrue(pq_l.size() == 0);
		for (int i = 0; i < 1_000_000; i++)
			pq_l.deleteMin();
	}

	@Test
	public void clear_add_l() {
		ArrayList<Integer> al = new ArrayList<Integer>();
		for (int i = 0; i < 1_000_000; i++)
			al.add(i);
		Collections.shuffle(al);
		for (int i = 0; i < 1_000_000; i++)
			pq_l.add(al.get(i));
		pq_l.clear();
		assertTrue(pq_l.size() == 0);
		al.clear();
		for (int i = 0; i < 1000; i++)
			al.add(i + 1_000_000);
		Collections.shuffle(al);
		for (int i = 0; i < 1000; i++)
			pq_l.add(al.get(i));
		for (int i = 0; i < 1000; i++)
			assertTrue(pq_l.deleteMin().equals(i + 1_000_000));
	}

	@Test
	public void test_comparator() {
		PriorityQueue<String> pq_c = new PriorityQueue<>(new stringComparator());
		PriorityQueue<String> pq = new PriorityQueue<>(new stringComparator());
		ArrayList<String> al = new ArrayList<String>();
		for (int i = 0; i < 1_000_000; i++)
			al.add("" + i);
		Collections.shuffle(al);
		for (int i = 0; i < 1_000_000; i++) {
			pq_c.add(al.get(i));
			pq.add(al.get(i));
		}
		for (int i = 0; i < 1_000_000; i++) {
			assertTrue(pq_c.findMin().equals(pq.findMin()));
			assertTrue(pq_c.deleteMin().equals(pq.deleteMin()));
		}
	}

	@Test
	public void visualize_comparator() {
		PriorityQueue<Integer> pq_c = new PriorityQueue<>(new intComparator());
		ArrayList<Integer> al = new ArrayList<Integer>();
		for (int i = 0; i < 100; i++)
			al.add(i);
		Collections.shuffle(al);
		for (int i = 0; i < 100; i++)
			pq_c.add(al.get(i));
		pq_c.generateDotFile("comparator.dot");
	}

	@Test // Max Heap!
	public void visualize_revcomparator() {
		PriorityQueue<Integer> pq_c = new PriorityQueue<>(new revintComparator());
		ArrayList<Integer> al = new ArrayList<Integer>();
		for (int i = 0; i < 100; i++)
			al.add(i);
		Collections.shuffle(al);
		for (int i = 0; i < 100; i++)
			pq_c.add(al.get(i));
		pq_c.generateDotFile("revcomparator.dot");
	}

	class stringComparator implements Comparator<String> {
		public int compare(String string01, String string02) {
			return string01.compareTo(string02);
		}
	}

	class intComparator implements Comparator<Integer> {
		public int compare(Integer Integer01, Integer Integer02) {
			return Integer01.compareTo(Integer02);
		}
	}

	class revintComparator implements Comparator<Integer> {
		public int compare(Integer Integer01, Integer Integer02) {
			return -Integer01.compareTo(Integer02);
		}
	}

	@Test
	public void duplicates(){
		for(int i=0;i<2;i++)
			for(int j=0;j<10;j++)
				pq_s.add(j);
		// the data in any node is less than or equal to the data of its children.
		pq_s.generateDotFile("duplicates.dot"); 
		for(int i=0;i<20;i++)
			assertTrue(pq_s.deleteMin().equals(i/2));
	}
	
	@Test (expected = NullPointerException.class)
	public void add_null(){
		pq_e.add(null);
	}
}
