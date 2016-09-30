package assignment04;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Test;

/**
 * Test class for AnagramUtil Class
 * @author Diego Baez Lopez uID: 0759247
 * @author Brayden Carlson uID: u0959889
 *
 */

public class AnagramUtilTester {
	Random rand = new Random();
	
	//Constants
	private int characterMinimum = 33;
	private int characterRange = 126 - 33;
	private int numStrings = 2000;
	private int stringLength = 4;

	/**
	 * Tests the areAnagrams method
	 */
	@Test
	public void areAnagramsTest() {
		assertTrue(AnagramUtil.areAnagrams("abcde", "DEaCb"));
		assertTrue(AnagramUtil.areAnagrams("wxyz", "YXzW"));
		assertTrue(AnagramUtil.areAnagrams("DEaCb", "DEaCb"));
		assertFalse(AnagramUtil.areAnagrams("abcde", "abcdee"));
		assertFalse(AnagramUtil.areAnagrams("", ""));
	}
	
	/**
	 * Tests the sort method
	 */
	@Test
	public void sortTest() {
		assertEquals("acde", AnagramUtil.sort("cdea"));
		assertEquals("CEade", AnagramUtil.sort("CedEa"));
		assertEquals("CEaade", AnagramUtil.sort("CedEaa"));
		assertEquals("acdee", AnagramUtil.sort("cedea"));
	}
	
	/**
	 * Tests the getLargestAnagram method
	 */
	@Test
	public void getLargestAnagramTest() {
		String[] testArray = new String[] { "abed", "bade", "bead", "abet", "beat", "beta", "abets", "baste", "betas",
				"beast", "beats", "abut", "tabu", "tuba", "acme", "came", "mace", "acre", "care", "race", "acres",
				"cares", "races", "scare", "actors", "costar", "castor", "actress", "casters", "recasts", "airmen",
				"marine", "remain" };
		String[] resultArray = AnagramUtil.getLargestAnagramGroup(testArray);
		assertEquals(5, resultArray.length);
		
		String[] testArray2 = new String[] { "abCde", "Mot", "abc", "cde", "tom", "a", "b", "to", "OT", "Otm", "TOm", "oo",
				"TOM", "A", "B", "TOM" };
		String[] resultArray2 = AnagramUtil.getLargestAnagramGroup(testArray2);
		assertEquals(6, resultArray2.length);

		// Test that an empty array is returned when there isn't any anagrams
		String[] testArray3 = new String[] { "ab", "ac", "af", "ap", "adc" };
		String[] resultArray3 = AnagramUtil.getLargestAnagramGroup(testArray3);
		assertEquals(0, resultArray3.length);
		
		// Test that a collection with 2 groups of the same size is handled correctly
		String[] testArray4 = new String[] { "tom", "tom", "tom", "tom", "tom", "mat", "mat", "mat", "mat", "mat" };
		String[] resultArray4 = AnagramUtil.getLargestAnagramGroup(testArray4);
		assertEquals(5, resultArray4.length);

		// Test that an empty array is handled appropriately
		String[] testArray5 = new String[] {};
		String[] resultArray5 = AnagramUtil.getLargestAnagramGroup(testArray5);
		assertEquals(0, resultArray5.length);

	}
	
	/**
	 * 
	 */
	@Test
	public void randomAnagramFinderTest() {
		ArrayList<String> stringArray = new ArrayList<>();
		for(int i = 0; i < numStrings; i++) {
			stringArray.add(getRandString(stringLength));
		}
		for(int i = 0; i < stringArray.size() - 1; i++) {
			for(int j = i + 1; j < stringArray.size(); j++) {
				if(AnagramUtil.areAnagrams(stringArray.get(i), stringArray.get(j))) {
					System.out.println("*** Anagrams! : " + stringArray.get(i) + " : " + stringArray.get(j));
				}
			}
		}
	}

	/**
	 * Tests the getLargestAnagramGroup method with files
	 */
	@Test
	public void ReadInFileTest() {
		// Check it is getting the right number of anagrams per assignment file
		String[] file = AnagramUtil.getLargestAnagramGroup("sample_word_list.txt");
		assertEquals(7, file.length);
		// Check the formatting of the file
		String[] file2 = AnagramUtil.getLargestAnagramGroup("word_list_bad_format.txt");
		assertEquals(0, file2.length);
		// Make sure it returns an empty array when the file does not exist
		String[] noFile = AnagramUtil.getLargestAnagramGroup("word.txt");
		assertEquals(0, noFile.length);
		// Make sure it returns an empty array when the file is empty
		String[] noWords = AnagramUtil.getLargestAnagramGroup("empty_list.txt");
		assertEquals(0, noWords.length);
	}
	
	/**
	 * Returns a random character
	 */
	public char getRandChar() {
		int randNum = characterMinimum + rand.nextInt(characterRange);
		return (char) randNum;
	}

	/**
	 * Returns a random string
	 */
	public String getRandString(int length) {
		StringBuilder randString = new StringBuilder(length);
		if(length == 0) {
			return randString.toString();
		} else {
			for(int i = 0; i < length; i++) {
				randString.append(getRandChar());
			}
		}
		return randString.toString();
	}
}
