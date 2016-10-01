package assignment04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Stream;

/**
 * Utility for finding anagrams.
 * 
 * @author Anthony Chyr (u0627375)
 * @author Philippe David (u0989696)
 *
 */
public class AnagramUtil {
	/**
	 * Takes a word, sorts the letters, and returns the sorted letters. The
	 * sorting ignores case while sorting, however the method will retain the
	 * original case of the word when returning.
	 * 
	 * @param word
	 *            with letters to be sorted
	 * @return word with letters sorted while retaining the same case as word
	 */
	public static String sort(String word) {
		// Handle the trivial case, null interpreted as part of trivial case
		if (word == null || word.isEmpty()) {
			return "";
		}

		// Convert String to a Character array
		Character[] letters = AnagramUtil.stringToCharacter(word);

		// Sort the letters
		AnagramUtil.insertionSort(letters, new Comparator<Character>() {
			@Override
			public int compare(Character o1, Character o2) {
				// Use toLower to ignore case
				return Character.compare(Character.toLowerCase(o1),
						Character.toLowerCase(o2));
			}
		});

		// Convert Character array back to String
		return AnagramUtil.characterToString(letters);
	}

	/**
	 * Sort arbitrary arrays of objects using insertion sort.
	 * 
	 * @param array
	 *            to be sorted
	 * @param comparator
	 *            comparator to determine order
	 */
	public static <T> void insertionSort(T[] array,
			Comparator<? super T> comparator) {
		/*
		 * Handle the trivial case, null interpreted as part of the trivial
		 * case, an array with no elements or one element is already sorted
		 */
		if (array == null || array.length < 2) {
			return;
		}

		/*
		 * Iterate over each element in the unsorted part of the array, the
		 * unsorted part of the array starts on the second element initially
		 */
		for (int i = 1; i < array.length; i++) {
			// Make a copy of the current unsorted element
			T temp = array[i];

			/*
			 * Shift the elements in the sorted part of the array individually
			 * one space over to the right (overriding the current unsorted
			 * element) until the current unsorted element should no longer go
			 * before the element in the sorted part of the array.
			 */
			int j = i;
			for (; j > 0 && comparator.compare(temp, array[j - 1]) < 0; j--) {
				array[j] = array[j - 1];
			}

			/*
			 * Put the copy of the current unsorted element into the space
			 * created while shifting elements to the right
			 */
			array[j] = temp;
		}
	}

	/**
	 * Test whether two words are anagrams, ignores case. Two words are
	 * considered anagrams if with their letters sorted, they are equal.
	 * 
	 * @param o1
	 *            word to test
	 * @param o2
	 *            word to test
	 * @return True if the two words are anagrams, false otherwise
	 */
	public static boolean areAnagrams(String o1, String o2) {
		String o1Sorted = AnagramUtil.sort(o1);
		String o2Sorted = AnagramUtil.sort(o2);
		return o1Sorted.toLowerCase().equals(o2Sorted.toLowerCase());
	}

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
			lettersSorted[i] = AnagramUtil.sort(words[i].toLowerCase());
		}

		/*
		 * In another copy, sort the input words with the letters sorted.
		 */
		String[] anagramSorted = lettersSorted.clone();
		AnagramUtil.insertionSort(anagramSorted, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.toLowerCase().compareTo(o2.toLowerCase());
			}
		});

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

	/**
	 * Find the largest group of anagrams in a list of words stored in a file.
	 * Each word is stored on a separate line. Ignores case while sorting and
	 * comparing. If no words are anagrams of one another, an empty list is
	 * returned.
	 * 
	 * @param filename
	 *            of the file with the words in it, assume no empty lines,
	 *            phrases, or duplicates
	 * @return the largest group of anagrams in a list with no particular order,
	 *         an empty list if no words are anagrams of one another.
	 */
	public static String[] getLargestAnagramGroup(String filename) {
		ArrayList<String> words = new ArrayList<String>();

		try (Stream<String> stream = Files.lines(Paths.get(filename))) {
			stream.forEach(v -> words.add(v.trim())); // trim whitespace
		} catch (IOException e) {
			/*
			 * Should let the caller handle this exception, but I don't want to
			 * mess with the method signature requiring a try-catch block for
			 * the caller.
			 */
			e.printStackTrace();
		}

		return AnagramUtil.getLargestAnagramGroup(
				words.toArray(new String[words.size()]));
	}

	/**
	 * Converts a String into a Character Object array.
	 * 
	 * @param word
	 *            string to be converted to a Character Object array.
	 * @return Character Object array of the string.
	 */
	private static Character[] stringToCharacter(String word) {
		Character[] letters = new Character[word.length()];
		for (int i = 0; i < word.length(); i++) {
			letters[i] = word.charAt(i);
		}
		return letters;
	}

	/**
	 * Converts a Character Object array into a String
	 * 
	 * @param letters
	 *            Character Object array to be converted into a String
	 * @return String of the Character Object array
	 */
	private static String characterToString(Character[] letters) {
		// Create a char array and copy the Characters array over
		char[] letters_char = new char[letters.length];
		for (int i = 0; i < letters.length; i++) {
			letters_char[i] = letters[i];
		}

		/*
		 * Build the string, do this rather than concatenating strings because
		 * this scales O(N)
		 */
		String word = new String(letters_char);
		return word;
	}
}
