package assignment04;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * AnagramUtil implementing Java Array's sort method instead of insertion sort
 * 
 * @author Anthony Chyr (u0627375)
 * @author Philippe David (u0989696)
 *
 */
public class AnagramUtilAlt extends AnagramUtil {
	/**
	 * Find the largest group of anagrams in a list of words, ignores case. If
	 * no words are anagrams of one another, an empty list will be returned. If
	 * there are multiple largest groups of anagrams of equal size.
	 * 
	 * @param words
	 *            array of words to find anagrams in, assumes no duplicates,
	 *            empty strings, or nulls
	 * @return the largest group of anagrams in a list of words in no particular
	 *         order, an empty list if there are no anagrams
	 */
	public static String[] getLargestAnagramGroup(String[] words) {
		/*
		 * Handle the trivial case. There must be at least two words for a
		 * meaningful anagram to exist. null interpreted as the trivial case.
		 */
		if (words == null || words.length < 2) {
			return new String[0];
		}

		/*
		 * Get a copy of the input words with the letters sorted. The case will
		 * be removed while sorting and comparing.
		 */
		String[] lettersSorted = new String[words.length];
		for (int i = 0; i < words.length; i++) {
			// When sorting and comparing, use only lower case
			char[] letters = words[i].toLowerCase().toCharArray();
			Arrays.sort(letters);
			lettersSorted[i] = new String(letters);
		}

		/*
		 * In another copy, sort the input words with the letters sorted.
		 */
		String[] anagramSorted = lettersSorted.clone();
		Arrays.sort(anagramSorted);

		/*
		 * With all the anagrams sorted, they are are also grouped. Iterate
		 * through the list and find the largest group.
		 */
		String prevAnagram = anagramSorted[0];
		String mostCommonAnagram = anagramSorted[0];
		int groupSize = 1;
		int largestGroupSize = 1;
		for (int i = 1; i < words.length; i++) {
			if (prevAnagram.equals(anagramSorted[i])) {
				// Current anagram is the same as previous
				groupSize++;
			} else {
				// A new anagram, reset the group size
				groupSize = 1;
			}

			if (groupSize > largestGroupSize) {
				// New largest group
				largestGroupSize = groupSize;
				mostCommonAnagram = anagramSorted[i];
			}

			prevAnagram = anagramSorted[i];
		}

		/*
		 * There must be at least two words for there to be a meaningful
		 * anagram. Return an empty string array to handle the trivial case (a
		 * word being an anagram of itself).
		 */
		if (largestGroupSize < 2) {
			return new String[0];
		}

		/*
		 * Get the original words back, restoring the case and the ordering of
		 * the letters.
		 */
		ArrayList<String> largestAnagramGroup = new ArrayList<String>();
		for (int i = 0; i < words.length; i++) {
			if (mostCommonAnagram.equals(lettersSorted[i])) {
				largestAnagramGroup.add(words[i]);
			}
		}

		return largestAnagramGroup
				.toArray(new String[largestAnagramGroup.size()]);
	}
}

