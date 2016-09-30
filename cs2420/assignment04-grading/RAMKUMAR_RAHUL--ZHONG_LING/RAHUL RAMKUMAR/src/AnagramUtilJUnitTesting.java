package assignment04;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Rahul Ramkumar and Lingxi Zhong Last Edited: 9/15/16.
 */

public class AnagramUtilJUnitTesting {
	String[] stringTestArray;


	@Before
	public void setup() {
		stringTestArray = new String[] { "carets", "Caller", "eat", "cellar", "recall", "Caters", "Ate", "caster",
				"aspired", "allergy", "despair", "asp", "pas", "least", "sap", "spa", "diapers", "praised", "crates",
				"Reacts", "bats", "tea", "Stab", "stale", "tabs", "recast", "darters", "Gallery", "retards", "starred",
				"code", "Coed", "deco", "traders", "traces", };

	}

	@Test
	public void sortedStringTestWithSimpleLetters() {
		Assert.assertEquals("aabbccdd", AnagramUtil.sort("abcdabcd"));
	}
	
	@Test
	public void sortingAStringThatHasNothingInIt() {
		String result = "";
		result = AnagramUtil.sort(result);
		assertEquals("", result);
	}

	@Test
	public void sortedStringTestWithAWord() {
		String result = AnagramUtil.sort("SomeGenericWord");
		assertEquals(result, "cdeeegimnoorrsw");
	}

	@Test
	public void sortedStringTestWithALongPhrase() {
		String result = AnagramUtil.sort("The cow jumped over the moon");
		assertEquals(result, "     cdeeeehhjmmnooooprttuvw");
	}

	@Test
	public void areAnagramsTestWithTruePair() {
		Assert.assertTrue(AnagramUtil.areAnagrams("Claimed", "Medical"));
	}
	
	@Test
	public void areAnagramsTrueWithEmptyPair() {
		assertTrue(AnagramUtil.areAnagrams("", ""));
	}

	@Test
	public void areAnagramsTestWithFalsePair() {
		Assert.assertFalse(AnagramUtil.areAnagrams("Claimed", "notclaimed"));
	}

	@Test
	public void areAnagramsTestForAPhrase() {
		boolean result = AnagramUtil.areAnagrams("I Am Lord Voldemort", "Tom Marvolo Riddle ");
		assertEquals(result, true);
	}

	@Test
	public void getLargeAnagramSetArrayTestWithBasicSetsOfData() {
		String[] result = AnagramUtil.getLargestAnagramGroup(stringTestArray);
		String[] expected = new String[] { "carets", "Caters", "caster", "crates", "Reacts", "recast", "traces" };
		assertArrayEquals(expected, result);
	}

	@Test
	public void getLargestAnagramGroupTestWithFileBasicSetOfDataTest() {
		String[] result = AnagramUtil.getLargestAnagramGroup("sample_word_list.txt");
		String[] expected = new String[] { "carets", "Caters", "caster", "crates", "Reacts", "recast", "traces" };
		assertArrayEquals(expected, result);
	}

	@Test
	public void getLargestAnagramGroupTestWithTheMediumWordListTest() {
		String[] result = AnagramUtil.getLargestAnagramGroup("moderate_word_list.txt");
		String[] expected = new String[] { "act", "cat" };
		assertArrayEquals(expected, result);
	}


	@Test
	public void insertionSortTestWithSetOfIntegers() {
		Integer[] result = new Integer[] {5, 3, 4, 1, 2};
		Comparator<Integer> intcomp = new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1.compareTo(o2);
			}
		};
		AnagramUtil.insertionSort(result, intcomp);
		assertArrayEquals(new Integer[] {1, 2, 3, 4, 5}, result);
	}
	
	@Test(expected = NullPointerException.class)
	public void insertionSortTestWithEmptyArray() {
		Integer[] result = new Integer[5];
		Comparator<Integer> intcomp = new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1.compareTo(o2);
			}
		};
		AnagramUtil.insertionSort(result, intcomp);
		assertArrayEquals(new Integer[5], result);
	}
}
