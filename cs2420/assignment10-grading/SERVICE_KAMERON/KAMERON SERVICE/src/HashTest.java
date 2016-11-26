package assignment10;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HashTest {
	
	private QuadProbeHashTable quad;
	private ChainingHashTable chain;
	private ArrayList<String> smallList, mediumList, emptyList;

	@Before
	public void setUp() throws Exception {
		quad = new QuadProbeHashTable(31, new GoodHashFunctor());
		chain = new ChainingHashTable(1000000, new GoodHashFunctor());
		smallList = Words.readFile("sample_word_list.txt");
		mediumList = Words.readFile("moderate_word_list.txt");
		emptyList = new ArrayList<String>();
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void quadAddTest() {
		quad.add("hello");
		assertEquals(quad.size(), 1);
	}
	@Test
	public void quadAddStringAlreadyInTheTable(){
		quad.add("hi");
		assertFalse(quad.add("hi"));
	}
	@Test (expected=NullPointerException.class)
	public void QuadAddNullString(){
		quad.add(null);
	}
	@Test
	public void quadAddAllSmallList(){
		quad.addAll(smallList);
		assertEquals(quad.size(), smallList.size());
	}
	@Test 
	public void quadAddAllEmptySet(){
		assertFalse(quad.addAll(emptyList));
	}
	@Test (expected=NullPointerException.class)
	public void quadAddAllNullSet(){
		quad.addAll(null);
	}
	
	@Test
	public void quadClear(){
		quad.addAll(smallList);
		quad.clear();
		assertEquals(quad.size(), 0);
	}
	@Test
	public void quadClearEmptyTable(){
		quad.clear();
		assertEquals(quad.size(), 0);
	}
	
	@Test
	public void quadContains(){
		quad.add("hi");
		assertTrue(quad.contains("hi"));
	}
	@Test
	public void quadDoesNotContain(){
		assertFalse(quad.contains("hi"));
	}
	@Test
	public void quadContainsAll(){
		quad.addAll(mediumList);
		assertTrue(quad.containsAll(mediumList));
	}
	@Test
	public void quadDoesNotContainAll(){
		quad.addAll(smallList);
		assertFalse(quad.containsAll(mediumList));
	}
	@Test (expected=NullPointerException.class)
	public void quadContainsNullObject(){
		quad.contains(null);
	}
	@Test
	public void quadContainsAllEmptySet(){
		assertTrue(quad.containsAll(emptyList));
	}
	
	@Test
	public void isEmpty(){
		assertTrue(quad.isEmpty());
	}
	@Test
	public void isNotEmpty(){
		quad.add("hi");
		assertFalse(quad.isEmpty());
	}
	
	
	@Test
	public void chainAddTest() {
		chain.add("hello");
		assertEquals(chain.size(), 1);
	}
	@Test
	public void chainAddStringAlreadyInTheTable(){
		chain.add("hi");
		assertFalse(chain.add("hi"));
	}
	@Test (expected=NullPointerException.class)
	public void chainAddNullString(){
		chain.add(null);
	}
	@Test
	public void chainAddAllSmallList(){
		chain.addAll(smallList);
		assertEquals(chain.size(), smallList.size());
	}
	@Test 
	public void chainAddAllEmptySet(){
		assertFalse(chain.addAll(emptyList));
	}
	@Test (expected=NullPointerException.class)
	public void chainAddAllNullSet(){
		chain.addAll(null);
	}
	
	@Test
	public void chainClear(){
		chain.addAll(smallList);
		chain.clear();
		assertEquals(chain.size(), 0);
	}
	@Test
	public void chainClearEmptyTable(){
		chain.clear();
		assertEquals(chain.size(), 0);
	}
	
	@Test
	public void chainContains(){
		chain.add("hi");
		assertTrue(chain.contains("hi"));
	}
	@Test
	public void chainDoesNotContain(){
		assertFalse(chain.contains("hi"));
	}
	@Test
	public void chainContainsAll(){
		chain.addAll(mediumList);
		assertTrue(chain.containsAll(mediumList));
	}
	@Test
	public void chainDoesNotContainAll(){
		chain.addAll(smallList);
		assertFalse(chain.containsAll(mediumList));
	}
	@Test (expected=NullPointerException.class)
	public void chainContainsNullObject(){
		chain.contains(null);
	}
	@Test
	public void chainContainsAllEmptySet(){
		assertTrue(chain.containsAll(emptyList));
	}
	
	@Test
	public void chainIsEmpty(){
		assertTrue(chain.isEmpty());
	}
	@Test
	public void chainIsNotEmpty(){
		chain.add("hi");
		assertFalse(chain.isEmpty());
	}
	@Test
	public void chainAddLarge(){
		Random random = new Random();
		String alph = "abcdefghijklmnopqrstuvwxyz";
		ArrayList<String> list = new ArrayList<String>();
		for(int i = 0; i < 10_000_000; i++){
			int num = random.nextInt(10)+1;
			StringBuffer sb = new StringBuffer(num);
			for(int j = 0; j < num; j++){
				int ndx = random.nextInt(26);
				sb.append(alph.charAt(ndx));
			}
			list.add(sb.toString());
		}
		
		chain.addAll(list);
		
		//assertTrue(chain.addAll(Words.readFile("wordsEn.txt")));
		
		
	}
}
