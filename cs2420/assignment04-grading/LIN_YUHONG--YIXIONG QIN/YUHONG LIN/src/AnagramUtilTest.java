package Assignment04;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;

public class AnagramUtilTest {
	String sortString;
	String sortString2;
	String anaTest1;
	String anaTest2;
	String anaTest3;
	String[] groupTest;
	String[] expectGroup;
	String[] expectGroup2;
	String fileName;
	String[] testGroup;
	String[] testExpectGroup;
	testComparator cmp = new testComparator();

	/**
	 * initial value to the representation.
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		sortString = "bcbed";
		sortString2 = "cBaDP";
		anaTest1 = "FECAB";
		anaTest2 = "bacef";
		anaTest3 = "pdfeg";
		fileName = "aa.txt";
		groupTest = new String[] { "carets", "Caller", "eat", "cellar", "recall", "Caters", "Ate", "caster", "aspired",
				"allergy" };
		expectGroup = new String[] { "carets", "Caters", "caster", "crates", "Reacts", "recast", "traces" };
		expectGroup2 = new String[] { "carets", "Caters", "caster", "------", "Caller", "cellar", "recall" };
		testGroup = new String[0];
		testExpectGroup = new String[0];
	}

	/**
	 * It is the test for sort() method
	 */
	@Test
	public void testSort() {
		assertEquals("bbcde", AnagramUtil.sort(sortString));
		assertEquals("aBcDP", AnagramUtil.sort(sortString2));
	}

	/**
	 * This is a proving test for insertionSort method.
	 */
	@Test
	public void testInsertionSort() {
		String[] insertTest = { "abc", "bac", "efg" };
		AnagramUtil.insertionSort(insertTest, cmp);
		assertEquals("abc", insertTest[0]);
		assertEquals("bac", insertTest[1]);
		assertEquals("efg", insertTest[2]);
	}

	/**
	 * this one is for the test areAnagram method.
	 */
	@Test
	public void testAreAnagrams() {
		assertEquals(true, AnagramUtil.areAnagrams(anaTest1, anaTest2));
		assertEquals(false, AnagramUtil.areAnagrams(anaTest1, anaTest3));
	}

	/**
	 * this is the test for getLargestAnagramGroup method, When parameter is an
	 * array.
	 * 
	 */

	@Test
	public void testGetLargestAnagramGroup() {
		String[] testGroup = new String[3];
		testGroup = AnagramUtil.getLargestAnagramGroup(groupTest);
		for (int index = 0; index < testGroup.length; index++) {
			assertEquals(expectGroup2[index], testGroup[index]);
		}
	}

	/**
	 * this method is for test getLargestAnagramGroup method, when parameter is
	 * string type.
	 * 
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void testGetLargestAnagramGroupFile() throws FileNotFoundException {
		for (int i = 0; i < expectGroup2.length; i++) {
			assertEquals(expectGroup[i], AnagramUtil.getLargestAnagramGroup("sample_word_list.txt")[i]);
		}
	}

	class testComparator implements Comparator<String> {

		@Override
		public int compare(String o1, String o2) {
			return AnagramUtil.sort(o1).toLowerCase().compareTo(AnagramUtil.sort(o2).toLowerCase());
		}
	}
}