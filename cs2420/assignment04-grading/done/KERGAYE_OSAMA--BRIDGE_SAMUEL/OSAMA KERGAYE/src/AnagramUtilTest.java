/**
 * @author Osama Kergaye & Samuel Bridge
 * @UID u0767339 & u0984334
 */
package assignment04;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;

import assignment04.AnagramUtil;

/**
 * @author Osama Kergaye & Samuel Bridge
 * @UID u0767339 & u0984334
 */
public class AnagramUtilTest {
	String[] medArray, smallArray, smallArrayLargestGroup, largeArray, smallArraySortedForComparison;
	String smallArryFileLocation, bca;
	StringComparator StringComparator = new StringComparator();
	StringComparatorReverse ReverseStringComparator = new StringComparatorReverse();
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
	

		
		//Sting
		bca = "bca";
		
		
		// Small String Array
		smallArray = new String[] { "carets", "caters", "caster", "crates", "reacts", "recast", "traces", "Stab",
				"stale", "tabs", "recast", "darters" };

		// Small Array Largest Group
		smallArrayLargestGroup = new String[] { "carets", "caters", "caster", "crates", "reacts", "recast", "traces",
				"recast" };

		// Small Array File location String
		smallArryFileLocation = "src\\assignment04\\smallArrayFile.txt";

		//Small Array words aplhabetized

		smallArraySortedForComparison = new String[] { "Stab", "carets", "caster", "caters", "crates", "darters", "reacts", "recast", "recast",
				"stale", "tabs", "traces" };

		// Medium String Array
		medArray = readFile("src\\assignment04\\sample_word_list.txt");
		
		
		// Large String Array
		largeArray = readFile("src\\assignment04\\sample_word_list.txt");

	}

	@Test
	public void sortStringTest() {

		assertEquals("abc", AnagramUtil.sort(bca));

	}

	@Test
	public void sortStringCapsTest() {

		String BCA = "BCA";
		assertEquals("abc", AnagramUtil.sort(BCA));

	}


	@Test
	public void sortStringNullTest() {

		String BCA = null;
		assertEquals(null, AnagramUtil.sort(BCA));

	}


	@Test
	public void sortStringEmptyStringTest() {

		String BCA = "";
		assertEquals("", AnagramUtil.sort(BCA));

	}

	@Test
	public void insertionSortSmallArrayTest() {

		AnagramUtil.insertionSort(smallArray, StringComparator);

		for (int i = 0; i < smallArray.length; i++) {
			assertEquals(true, smallArray[i].equals(smallArraySortedForComparison[i]));

		}

	}
	

	@Test
	public void insertionSortLargeArrayTest(){
		String[] lotsOfWords = new String[1000];
		for (int i = 0; i < 1000; i++) {
			
			lotsOfWords[i] = AnagramTester.randomString(5);
		}
		String[] sorted = lotsOfWords;
		Arrays.sort(sorted);
		AnagramUtil.insertionSort(lotsOfWords, StringComparator);
		
		
		
		for (int i = 0; i < lotsOfWords.length; i++) {
			assertEquals(true, lotsOfWords[i].equals(sorted[i]));

		}
	
		
			
	}


	@Test
	public void insertionSortEmtpyArrayTest() {

		String[] emptyList = new String[]{"","",""};

		String[] sorted = emptyList;
		Arrays.sort(sorted);
		AnagramUtil.insertionSort(emptyList, StringComparator);
		
			assertEquals(true, emptyList.equals(sorted));

		
	
		
		
	
	}


	@Test
	public void insertionSortOneElementArrayTest() {
		
		String[] oneWord = new String[]{"word"};
	
		String[] sorted = oneWord;
		Arrays.sort(sorted);
		AnagramUtil.insertionSort(oneWord, StringComparator);
		
		
		
		for (int i = 0; i < oneWord.length; i++) {
			assertEquals(true, oneWord[i].equals(sorted[i]));

		}
	
		
	
	}
 

	@Test(expected = NullPointerException.class)
	public void insertionSortOneWithNullElementArrayTest() {
		String[] oneWordAndNull = new String[]{"word", null};
		
		String[] sorted = oneWordAndNull;
		Arrays.sort(sorted);
		AnagramUtil.insertionSort(oneWordAndNull, StringComparator);
		
		
		
		for (int i = 0; i < oneWordAndNull.length; i++) {
			assertEquals(true, oneWordAndNull[i].equals(sorted[i]));

		}
	
	
	}
 

	@Test
	public void insertionSortTwoElementArrayTest() {
		String[] twoWords = new String[]{"word", "wordtwo"};
		
		String[] sorted = twoWords;
		Arrays.sort(sorted);
		AnagramUtil.insertionSort(twoWords, StringComparator);
		
		
		
		for (int i = 0; i < twoWords.length; i++) {
			assertEquals(true, twoWords[i].equals(sorted[i]));

		}
	
	
	}


	@Test(expected = NullPointerException.class)
	public void insertionSortTwoWithNullElementArrayTest() {
		String[] twoWordsAndNull = new String[]{"word", "wordtwo", null};
		
		String[] sorted = twoWordsAndNull;
		Arrays.sort(sorted);
		AnagramUtil.insertionSort(twoWordsAndNull, StringComparator);
		
		
		
		for (int i = 0; i < twoWordsAndNull.length; i++) {
			assertEquals(true, twoWordsAndNull[i].equals(sorted[i]));

		}
	
	
	}

	@Test
	public void insertionSortWithStringsOfIntsElementArrayTest() {

	String[] twoWordsAndNull = new String[]{"12312", "1241234", "12412342134"};
		
		String[] sorted = twoWordsAndNull;
		Arrays.sort(sorted);
		AnagramUtil.insertionSort(twoWordsAndNull, StringComparator);
		
		
		
		for (int i = 0; i < twoWordsAndNull.length; i++) {
			assertEquals(true, twoWordsAndNull[i].equals(sorted[i]));

		}
	}
	
	
		@Test
		public void insertionSortWithIntegersElementArrayTest() {

			Integer[] intArray = new Integer[]{123, 332, 1234};
			
			Integer[] sorted = intArray;
		Comparator<Integer> intCompareToComparator = new Comparator<Integer>() {
			
			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o1.compareTo(o2);
			}
		};
			Arrays.sort(sorted);
			AnagramUtil.insertionSort(intArray,intCompareToComparator );
			
			
			
			for (int i = 0; i < intArray.length; i++) {
				assertEquals(true, intArray[i].equals(sorted[i]));

			}
		
		
		
		
		
		
		
	
	}


	@Test
	public void insertionSortWithReveseElementArrayTest() {
		
		String[] smallArrayCopy = new String[smallArray.length];
		for(int i = 0; i < smallArray.length; i++)
		{
			smallArrayCopy[i] =smallArray[i];
		}
		
		AnagramUtil.insertionSort(smallArray, new StringComparatorReverse());
		Arrays.sort( smallArrayCopy  , new StringComparatorReverse());
		
		
		
		for (int i = 0; i < smallArray.length; i++) {
			assertEquals(true, smallArrayCopy[i].equals(smallArray[i]));

		}
	
	}

	@Test
	public void areAnagramsTest() {

		
		String abc = "abc";
		assertEquals(true, AnagramUtil.areAnagrams(bca, abc));

	}


	@Test
	public void areAnagramsWithNullTest() {

		String nuller = null;
		String notNul = "hiiiiii";
		
		assertEquals(false, AnagramUtil.areAnagrams(nuller, notNul));
	

	}

	@Test
	public void areAnagramsWithBothNullTest() {

		String nuller = null;
		String nullerAgain = null;
		
		assertEquals(false, AnagramUtil.areAnagrams(nuller, nullerAgain));
	
	}

	@Test
	public void getLargestAnagramGroupWithSmallArrayTestWithOneDupe() {

		for (int i = 0; i < smallArrayLargestGroup.length; i++) {
			assertEquals(true, AnagramUtil.getLargestAnagramGroup(smallArray)[i].equals(smallArrayLargestGroup[i]));
		}

	}

	@Test
	public void getLargestAnagramGroupWithOneElementArray() {
		
		String[] oneElement = new String[]{"onlyone"};
		String[] empty = new String[]{""};
		for (int i = 0; i < oneElement.length; i++) {
			assertEquals(true, AnagramUtil.getLargestAnagramGroup(oneElement)[i].equals(empty[i]));
		}

	
	}

	@Test
	public void getLargestAnagramGroupWithOneNullElementArray() {
		String[] oneNullElement = new String[]{null};
		String[] empty = new String[]{""};
		for (int i = 0; i < oneNullElement.length; i++) {
			assertEquals(true, AnagramUtil.getLargestAnagramGroup(oneNullElement)[i].equals(empty[i]));
		}

	
	
	}
	
	
	@Test
	public void getLargestAnagramGroupWithTwoElementArray() {
		String[] twoNullElements = new String[]{null, null};
		String[] empty = new String[]{""};
		for (int i = 0; i < empty.length; i++) {
			assertEquals(true, AnagramUtil.getLargestAnagramGroup(twoNullElements)[i].equals(empty[i]));
		}

	
	
	}

	@Test
	public void getLargestAnagramGroupWithTwoAndNullElementArray() {

		String[] twoNullOneString = new String[]{null, null, "stringme"};
		String[] empty = new String[]{""};
		for (int i = 0; i < empty.length; i++) {
			assertEquals(true, AnagramUtil.getLargestAnagramGroup(twoNullOneString)[i].equals(empty[i]));
		}

	
	}

	@Test
	public void getLargestAnagramGroupWithSmallArrayTestFileWithOneDupe() {

		for (int i = 0; i < smallArrayLargestGroup.length; i++) {
			assertEquals(true,
					AnagramUtil.getLargestAnagramGroup(smallArryFileLocation)[i].equals(smallArrayLargestGroup[i]));
		}

	}

	
	/**
	 * Reads words from a file (assumed to contain one word per line)
	 * @param filename
	 * @return the words as an array of strings
	 */
	private static String[] readFile(String filename) {
		ArrayList<String> results = new ArrayList<String>();
		try (BufferedReader input = new BufferedReader(new FileReader(filename))) {
			while (input.ready()) {
				results.add(input.readLine());
			}
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results.toArray(new String[results.size()]);
	}
	
	/**
	 * 
	 * A private class containing a comparator that invokes strings natural
	 * ordering compareTo method
	 *
	 */
	private static class StringComparator implements Comparator<String> {

		/**
		 * @param String lhs
		 * @param String rhs
		 * @return -- Positive value if lhs is lexicographically greater than the
		 *         string argument 
		 *         
		 *         -- 0 if lhs is equal to rhs
		 *         
		 *         -- negative value if lhs is lexicographically less than
		 *         the string argument
		 * 
		 */
		@Override
		public int compare(String lhs, String rhs) {

			return (lhs.compareTo(rhs));
		}

	}

	private static class StringComparatorReverse implements Comparator<String> {

		/**
		 * @param String lhs
		 * @param String rhs
		 * @return -- negative value if lhs is lexicographically greater than the
		 *         string argument 
		 *         
		 *         -- 0 if lhs is equal to rhs
		 *         
		 *         --Positive value if lhs is lexicographically less than
		 *         the string argument
		 * 
		 */
		@Override
		public int compare(String lhs, String rhs) {

			return -(lhs.compareTo(rhs));
		}

	}

	
}
