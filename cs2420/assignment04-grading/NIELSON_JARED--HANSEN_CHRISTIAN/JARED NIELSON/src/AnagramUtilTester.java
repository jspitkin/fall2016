package assignment04;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.junit.Assert;

/**
 * Testing class for the AnagramUtil class.
 * @author Jared Nielson U0495206 & Christian Hansen u0621884
 *
 */
public class AnagramUtilTester {
	
	protected class IntegerComp implements Comparator<Integer> {

		@Override
		public int compare(Integer lhs, Integer rhs) {
			return lhs - rhs;
		}


	}
	
	private Integer[] intArray, expectedArray;
	private String s1, s1Result;

	@Before
	public void setUp() throws Exception {
		
		intArray = new Integer[4];
		intArray[0] = 2;
		intArray[1] = 1;
		intArray[2] = 0;
		intArray[3] = 1;
		
		expectedArray = new Integer[4];
		expectedArray[0] = 0;
		expectedArray[1] = 1;
		expectedArray[2] = 1;
		expectedArray[3] = 2;
		
		s1 = "bad";
		s1Result = "abd";		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void insertionSortIntegerTest() {
		
		AnagramUtil.insertionSort(intArray, new IntegerComp());
		Assert.assertArrayEquals(expectedArray, intArray);
	}
	
	@Test
	public void insertionSortEmptyArray() {
		String[] toSort = new String[0];
		String[] expected = new String[0];
		
		AnagramUtil.insertionSort(toSort, new StringComparator());
		Assert.assertArrayEquals(expected, toSort);
	}
	
	@Test
	public void insertionSortOneElementArray(){
		Integer[] toSort = new Integer[1];
		Integer[] expected = new Integer[1];
		
		toSort[0] = 5;
		expected[0] = 5;
		
		AnagramUtil.insertionSort(toSort, new IntegerComp());
		Assert.assertArrayEquals(expected, toSort);
	}
	
	@Test
	public void insertionSortString(){
		int TEST_SIZE = 100;
		
		String[] toSort = new String[TEST_SIZE];
		Random rng = new Random();
		String[] expected = new String[TEST_SIZE];
		
		for(int arrayIdx = 0; arrayIdx < TEST_SIZE; arrayIdx++){
			int stringLength = rng.nextInt(20);
			String toAdd = "";
			for(int stringIdx = 0; stringIdx < stringLength; stringIdx++){
				toAdd += (char) (rng.nextInt(57) + 65);
			}
			
			toSort[arrayIdx] = toAdd;
			expected[arrayIdx] = toAdd;
		}
		
		Arrays.sort(expected, new StringComparator());
		AnagramUtil.insertionSort(toSort, new StringComparator());
		
		Assert.assertArrayEquals(expected, toSort);
	}
	
	@Test
	public void sortTest(){
		Assert.assertEquals(s1Result, AnagramUtil.sort(s1));
	}
	
	@Test
	public void sortEmptyString(){
		Assert.assertEquals("", AnagramUtil.sort(""));
	}
	
	@Test
	public void sortOneCharString(){
		Assert.assertEquals("Z", AnagramUtil.sort("Z"));
	}
	
	@Test
	public void sortCasing(){
		Assert.assertEquals("Zaber", AnagramUtil.sort("Zebra"));
		Assert.assertEquals("XYZabc", AnagramUtil.sort("cbaZYX"));
		Assert.assertEquals("ABCXYZabcxyz", AnagramUtil.sort("aAbBcCxXyYzZ"));
	}
	
	@Test
	public void areAnagramsTest(){
		Assert.assertTrue(AnagramUtil.areAnagrams(s1, s1Result));
	}
	
	@Test
	public void areAnagramsDifferentCasing(){
		Assert.assertTrue("Same word separate casing", AnagramUtil.areAnagrams("Toyota", "toYota"));
		
		Assert.assertTrue("Different words same casing", AnagramUtil.areAnagrams("swIftly", "Ilyftsw"));
		
		Assert.assertTrue("Different words different casing", AnagramUtil.areAnagrams("Assert", "Stares"));
	}
	
	@Test
	public void areAnagramsFalse(){
		Assert.assertFalse("Similar Words different length", AnagramUtil.areAnagrams("MyCar", "MyCars"));
		Assert.assertFalse("Letters off by one", AnagramUtil.areAnagrams("creamie", "icecream"));
	}
	
	@Test
	public void getLargestAnagram(){
		String[] anagramList = new String[]{"alerted", "aligned", "altered", "dealing", "related", "treadle", "leading"};
		String[] expectedList = new String[]{"alerted", "altered", "related", "treadle"};
		
		Assert.assertArrayEquals(expectedList, AnagramUtil.getLargestAnagramGroup(anagramList));
	}
	
	@Test
	public void getLargestAnagramEmptyArray(){
		String[] anagramList = new String[0];
		String[] expectedList = new String[0];
		
		Assert.assertArrayEquals(expectedList, AnagramUtil.getLargestAnagramGroup(anagramList));
	}
	
	@Test
	public void getLargestAnagramSingleArray(){
		String[] anagramList = new String[]{"Anagram"};
		String[] expectedList = new String[]{"Anagram"};
		
		Assert.assertArrayEquals(expectedList, AnagramUtil.getLargestAnagramGroup(anagramList));
	}
	
	@Test
	public void getLargestAnagramMultipleGroupsLengthOne() {
		String[] anagramList = new String[]{"Boy", "Am", "I", "Glad"};
		String[] expectedList = new String[]{"Glad"};
		
		Assert.assertArrayEquals(expectedList, AnagramUtil.getLargestAnagramGroup(anagramList));
	}
	
	@Test
	public void getLargestAnagramMultipleGroups() {
		String[] anagramList = new String[]{"aridest", "tirades", "below", "beats", "go", "tardies", "og", 
											"bowel", "baste", "astride", "betas",
											"straider", "beast", "run", "abets", "elbow"};
		
		String[] expectedList = new String[]{"beats", "baste", "betas", "beast", "abets"};
		
		Assert.assertArrayEquals(expectedList, AnagramUtil.getLargestAnagramGroup(anagramList));
	}
	
	@Test
	public void getLargestAnagramPreserveCasing() {
		String[] anagramList = new String[]{"aridest", "tirades", "below", "Beats", "go", "tardies", "og", 
				"bowel", "baSTe", "astride", "bEtas",
				"straider", "beasT", "run", "abETs", "elbow"};

String[] expectedList = new String[]{"Beats", "baSTe", "bEtas", "beasT", "abETs"};

Assert.assertArrayEquals(expectedList, AnagramUtil.getLargestAnagramGroup(anagramList));
	}
	@Test
	public void readFileTestBasicTest(){
		
		String[] resultArray = new String[7];
		resultArray[0] = "carets";
		resultArray[1] = "Caters";
		resultArray[2] = "caster";
		resultArray[3] = "crates";
		resultArray[4] = "Reacts";
		resultArray[5] = "recast";
		resultArray[6] = "traces";
		
		Assert.assertArrayEquals(AnagramUtil.getLargestAnagramGroup("sample_word_list.txt"), resultArray);
	}
	
	@Test
	public void readFileNotFound(){
		Assert.assertArrayEquals(new String[0], AnagramUtil.getLargestAnagramGroup("noSuchFile.txt"));
	}
	

}















