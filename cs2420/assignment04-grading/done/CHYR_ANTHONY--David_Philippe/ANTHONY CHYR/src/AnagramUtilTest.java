package assignment04;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for AnagramUtil
 * 
 * @author Anthony Chyr (u0627375)
 * @author Philippe David (u0989696)
 *
 */
public class AnagramUtilTest {
	Comparator<Character> compareChar;
	Character[] characterArray;
	Character[] characterArraySorted;
	String[] sampleWords;
	String[] sampleWordsLargestAnagramGroup;
	String[] moderateWordsLargestAnagramGroup;

	@Before
	public void setUp() throws Exception {
		compareChar = new Comparator<Character>() {
			@Override
			public int compare(Character o1, Character o2) {
				return Character.compare(o1, o2);
			}
		};

		characterArray = new Character[3];
		characterArray[0] = 'b';
		characterArray[1] = 'a';
		characterArray[2] = 'c';

		characterArraySorted = new Character[3];
		characterArraySorted[0] = 'a';
		characterArraySorted[1] = 'b';
		characterArraySorted[2] = 'c';

		sampleWords = new String[35];
		sampleWords[0] = "carets";
		sampleWords[1] = "Caller";
		sampleWords[2] = "eat";
		sampleWords[3] = "cellar";
		sampleWords[4] = "recall";
		sampleWords[5] = "Caters";
		sampleWords[6] = "Ate";
		sampleWords[7] = "caster";
		sampleWords[8] = "aspired";
		sampleWords[9] = "allergy";
		sampleWords[10] = "despair";
		sampleWords[11] = "asp";
		sampleWords[12] = "pas";
		sampleWords[13] = "least";
		sampleWords[14] = "sap";
		sampleWords[15] = "spa";
		sampleWords[16] = "diapers";
		sampleWords[17] = "praised";
		sampleWords[18] = "crates";
		sampleWords[19] = "Reacts";
		sampleWords[20] = "bats";
		sampleWords[21] = "tea";
		sampleWords[22] = "Stab";
		sampleWords[23] = "stale";
		sampleWords[24] = "tabs";
		sampleWords[25] = "recast";
		sampleWords[26] = "darters";
		sampleWords[27] = "Gallery";
		sampleWords[28] = "retards";
		sampleWords[29] = "starred";
		sampleWords[30] = "code";
		sampleWords[31] = "Coed";
		sampleWords[32] = "deco";
		sampleWords[33] = "traders";
		sampleWords[34] = "traces";

		sampleWordsLargestAnagramGroup = new String[7];
		sampleWordsLargestAnagramGroup[0] = "carets";
		sampleWordsLargestAnagramGroup[1] = "Caters";
		sampleWordsLargestAnagramGroup[2] = "caster";
		sampleWordsLargestAnagramGroup[3] = "crates";
		sampleWordsLargestAnagramGroup[4] = "Reacts";
		sampleWordsLargestAnagramGroup[5] = "recast";
		sampleWordsLargestAnagramGroup[6] = "traces";

		moderateWordsLargestAnagramGroup = new String[2];
		moderateWordsLargestAnagramGroup[0] = "act";
		moderateWordsLargestAnagramGroup[1] = "cat";
	}

	@Test
	public void testSortNull() {
		assertEquals(AnagramUtil.sort(null), "");
	}

	@Test
	public void testSortEmptyString() {
		assertEquals(AnagramUtil.sort(""), "");
	}

	@Test
	public void testSortOneCharacter() {
		assertEquals(AnagramUtil.sort("a"), "a");
	}

	@Test
	public void testSortSameCase() {
		assertEquals(AnagramUtil.sort("bac"), "abc");
	}

	@Test
	public void testSortDifferentCase() {
		assertEquals(AnagramUtil.sort("BaC"), "aBC");
	}

	@Test
	public void testInsertionSortNull() {
		AnagramUtil.insertionSort(null, null);
	}

	@Test
	public void testInsertionSortNullArray() {
		AnagramUtil.insertionSort(null, compareChar);
	}

	@Test(expected = NullPointerException.class)
	public void testInsertionSortNullComparator() {
		AnagramUtil.insertionSort(characterArray, null);
	}

	@Test
	public void testInsertionSortEmptyArray() {
		AnagramUtil.insertionSort(new Character[0], compareChar);
	}

	@Test
	public void testInsertionSortOneElement() {
		AnagramUtil.insertionSort(new Character[1], compareChar);
	}

	@Test
	public void testInsertionSort() {
		AnagramUtil.insertionSort(characterArray, compareChar);
		assertArrayEquals(characterArray, characterArraySorted);
	}

	@Test
	public void testAreAnagramsNullNull() {
		assertTrue(AnagramUtil.areAnagrams(null, null));
	}

	@Test
	public void testAreAnagramsEmptyString() {
		assertTrue(AnagramUtil.areAnagrams("", ""));
	}

	@Test
	public void testAreAnagramsSameCaseTrue() {
		assertTrue(AnagramUtil.areAnagrams("bac", "abc"));
	}

	@Test
	public void testAreAnagramsDifferentCaseTrue() {
		assertTrue(AnagramUtil.areAnagrams("BaC", "abc"));
	}

	@Test
	public void testAreAnagramsDifferentCaseFalse() {
		assertFalse(AnagramUtil.areAnagrams("aBc", "abcd"));
	}

	@Test
	public void testAreAnagramsSameCaseFalse() {
		assertFalse(AnagramUtil.areAnagrams("abc", "abcd"));
	}

	@Test
	public void testGetLargestAnagramGroupNullArray() {
		String[] nullStringArray = null;
		assertArrayEquals(AnagramUtil.getLargestAnagramGroup(nullStringArray),
				new String[0]);
	}

	@Test(expected = NullPointerException.class)
	public void testGetLargestAnagramGroupNullElement() {
		/*
		 * If the user ends up with null in their string array, don't handle it,
		 * let it throw an exception.
		 */
		String[] nullStringArray = new String[3];
		assertArrayEquals(AnagramUtil.getLargestAnagramGroup(nullStringArray),
				new String[0]);
	}

	@Test
	public void testGetLargestAnagramGroupEmptyArray() {
		assertArrayEquals(AnagramUtil.getLargestAnagramGroup(new String[0]),
				new String[0]);
	}

	@Test
	public void testGetLargestAnagramGroupOneElement() {
		String[] words = new String[1];
		words[0] = "abc";

		assertArrayEquals(AnagramUtil.getLargestAnagramGroup(words),
				new String[0]);
	}

	@Test
	public void testGetLargestAnagramSampleWordsTrue() {
		assertArrayEquals(AnagramUtil.getLargestAnagramGroup(sampleWords),
				sampleWordsLargestAnagramGroup);
	}

	@Test
	public void testGetLargestAnagramModerateWordsTrue() {
		ArrayList<String> results = new ArrayList<String>();
		try (BufferedReader input = new BufferedReader(
				new FileReader("moderate_word_list.txt"))) {
			while (input.ready()) {
				results.add(input.readLine());
			}
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] words = (String[]) results.toArray(new String[results.size()]);

		assertArrayEquals(AnagramUtil.getLargestAnagramGroup(words),
				moderateWordsLargestAnagramGroup);
	}

	@Test(expected = NullPointerException.class)
	public void testGetLargestAnagramGroupStringFileNull() {
		String nullFile = null;
		AnagramUtil.getLargestAnagramGroup(nullFile);
	}

//	@Test
//	public void testGetLargestAnagramGroupStringBadFile() {
//		AnagramUtil.getLargestAnagramGroup("");
//	}

	@Test
	public void testGetLargestAnagramGroupStringFileSampleWordList() {
		assertArrayEquals(
				AnagramUtil.getLargestAnagramGroup("sample_word_list.txt"),
				sampleWordsLargestAnagramGroup);
	}

	@Test
	public void testGetLargestAnagramGroupStringFileModerateWordList() {
		assertArrayEquals(
				AnagramUtil.getLargestAnagramGroup("moderate_word_list.txt"),
				moderateWordsLargestAnagramGroup);
	}
}
