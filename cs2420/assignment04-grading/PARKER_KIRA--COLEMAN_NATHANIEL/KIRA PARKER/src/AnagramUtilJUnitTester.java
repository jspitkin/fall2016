package assignment04;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import assignment02.LibraryBookGeneric;

/**
 * Test class for AnagramUtil
 * @author Kira Parker u1073760, Nathaniel Coleman u0913541
 *
 */
public class AnagramUtilJUnitTester {
	public LibraryBookGeneric<String> book1, book2, book3;
	public Integer[] array;

	@Before
	public void setUp() throws Exception {
		array = new Integer[3];
		array[0] = 1;
		array[1] = 2;
		array[2] = 0;
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSortMethod() {
		assertEquals("aikr", AnagramUtil.sort("kira"));
	}
	
	@Test
	public void testSortMethodWithCapitals(){
		assertEquals("HLOel", AnagramUtil.sort("HeLlO"));
	}
	
	@Test
	public void testSortEmptyString(){
		assertEquals("", AnagramUtil.sort(""));
	}
	
	@Test
	public void testAreAnagramsTrue(){
		assertTrue(AnagramUtil.areAnagrams("apple", "pleap"));
	}
	
	@Test 
	public void testAreAnagramsFalse(){
		assertFalse(AnagramUtil.areAnagrams("moo", "HELLO"));
	}
	
	@Test
	public void testAreAnagramsEmpty(){
		assertTrue(AnagramUtil.areAnagrams("", ""));
	}
	
	@Test
	public void testInsertionSortIntegers(){
		Integer[] correct = {0,1,2};
		AnagramUtil.insertionSort(array, new OrderInteger());
		for(int i=0; i<3; i++){
			assertTrue(correct[i] == array[i]);
		}
	}
	
	@Test
	public void getLargestAnagramGroupFour(){
		String[] words = {"aba", "baa", "aab", "cdc", "aba"};
		String[] result = AnagramUtil.getLargestAnagramGroup(words);
		String[] correct = {"aba", "baa", "aab", "aba"};
		for(int i=0; i<4; i++){
			assertEquals(correct[i], result[i]);
		}
	}
	
	@Test
	public void getLargestAnagramGroupOne(){
		String[] words = {"abc", "bcd", "cde", "def"};
		String[] result = AnagramUtil.getLargestAnagramGroup(words);
		assertTrue(result.length == 0);
	}
	
	@Test
	public void getLargestAnagramGroupEmpty(){
		String[] words = {};
		assertTrue(AnagramUtil.getLargestAnagramGroup(words).length == 0);
	}
	
	@Test
	public void getLargestAnagramGroupTen(){
		String[] words = {"abcd", "acbd", "e", "f", "gh", "abcd", "dcba", "bdca", "dcab", "erg", 
				"bdca", "abcd", "fgh", "kira", "bdca", "abcd"};
		String[] result = AnagramUtil.getLargestAnagramGroup(words);
		String[] correct = {"abcd", "acbd", "abcd", "dcba", "bdca", "dcab", "bdca", "abcd", "bdca", "abcd"};
		assertTrue(result.length == 10);
		for(int i=0; i<10; i++){
			assertEquals(correct[i], result[i]);
		}
	}
	
	@Test
	public void testGetLargestAnagramGroupEmptyInputArray(){
		assertTrue(AnagramUtil.getLargestAnagramGroup(new String[0]).length == 0);
	}
	
	@Test
	public void testGetLargestAnagramGroupFileNotFound(){
		assertTrue(AnagramUtil.getLargestAnagramGroup("file.txt").length == 0);
	}
	
	@Test
	public void testGetLargestAnagramGroupFileEmpty(){
		assertTrue(AnagramUtil.getLargestAnagramGroup("emptyFile.txt").length == 0);
	}
	
	
	@Test
	public void getLargestAnagramGroupSampleWordList(){
		String[] arr = AnagramUtil.getLargestAnagramGroup("sample_word_list.txt");
		ArrayList<String> list = new ArrayList<String>(Arrays.asList(arr));
		assertTrue(list.size() == 7 && list.contains("carets") && list.contains("Caters") && 
				list.contains("caster") && list.contains("crates") && list.contains("Reacts") && 
				list.contains("recast") && list.contains("traces"));
	}
	
	@Test
	public void getLargestAnagramGroupAnagramList(){
		String[] arr = AnagramUtil.getLargestAnagramGroup("anagramList.txt");
		ArrayList<String> list = new ArrayList<String>(Arrays.asList(arr));
		assertTrue(list.size() == 5 && list.contains("Esprits") && list.contains("pErsist") &&
				list.contains("spRiest") && list.contains("sprItes") && list.contains("striPes"));
	}
	
	/**
	 * Comparator that defines an order on integers (for testing purposes)
	 */
	protected class OrderInteger implements Comparator<Integer> {

		/**
		 * Returns a negative value if lhs is smaller than rhs. Returns a
		 * positive value if lhs is larger than rhs. Returns 0 if lhs and rhs
		 * are equal.
		 */
		public int compare(Integer lhs, Integer rhs) {
			return lhs-rhs;
		}
	}

}
