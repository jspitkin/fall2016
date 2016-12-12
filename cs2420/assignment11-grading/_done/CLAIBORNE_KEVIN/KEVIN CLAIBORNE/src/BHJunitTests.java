package assignment11;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import org.junit.Before;
import org.junit.Test;

public class BHJunitTests {
	
	Comparator<Integer> intComparator = new Comparator<Integer>() {
		@Override
		public int compare(Integer arg0, Integer arg1) {
			return arg0.compareTo(arg1);
		}
	};

	Comparator<String> compString = new Comparator<String>() {

		@Override
		public int compare(String o1, String o2) {
			return o1.compareTo(o2);
		}
	};

	PriorityQueue<String> pqStringComp;
	PriorityQueue<Integer> pqIntComp;
	PriorityQueue<String> pqString;
	PriorityQueue<Integer> pqInt;

	@Before
	public void setUp() throws Exception {
		pqStringComp = new PriorityQueue<String>(compString);
		pqIntComp = new PriorityQueue<Integer>(intComparator);
		pqString = new PriorityQueue<String>();
		pqInt = new PriorityQueue<Integer>();
	}

	@Test
	public void addingAndRemovingNoComp() {
		ArrayList<String> array = new ArrayList<String>();
		
		for (int i = 10000; i >= 0; i--) {
			pqString.add(i+"");
			array.add(i+"");
			pqInt.add(i);
		}
		
		array.sort(compString);
		
		for (int i = 0; i < pqString.size(); i++) {
			int result1 = pqInt.deleteMin();
			String result2 = pqString.deleteMin();
			String expected = array.get(0);
			array.remove(array.get(0));
			
			assertEquals(result1, i);
			assertEquals(result2, expected);
		}
	}

	@Test
	public void addingAndRemovingWithComp() {
		ArrayList<String> array = new ArrayList<String>();
		
		for (int i = 10000; i >= 0; i--) {
			pqStringComp.add(i+"");
			array.add(i+"");
			pqIntComp.add(i);
		}
		
		array.sort(compString);
		
		for (int i = 0; i < pqStringComp.size(); i++) {
			int result1 = pqIntComp.deleteMin();
			String result2 = pqStringComp.deleteMin();
			String expected = array.get(0);
			array.remove(array.get(0));
			
			assertEquals(result1, i);
			assertEquals(result2, expected);
		}
	}
	
	@Test
	public void clear() {
		String[] arrayString = new String[0];
		Integer[] arrayInt = new Integer[0];
		
		for (int i = 10000; i >= 0; i--) {
			pqString.add(i+"");
			pqInt.add(i);
		}
		
		pqString.clear();
		pqInt.clear();
		
		assertArrayEquals(pqString.toArray(), arrayString);
		assertArrayEquals(pqInt.toArray(), arrayInt);
		
		for (int i = 10000; i >= 0; i--) {
			pqString.add(i+"");
			pqInt.add(i);
		}
		
		assertEquals(pqString.size(), 10001);
		assertEquals(pqInt.size(), 10001);
	}
	
	
}
