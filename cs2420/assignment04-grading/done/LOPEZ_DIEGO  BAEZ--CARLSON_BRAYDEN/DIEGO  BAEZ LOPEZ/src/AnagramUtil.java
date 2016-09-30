package assignment04;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Our implementation of the AnagramUtil class
 *
 * @author Diego Baez Lopez uID: u0759247
 * @author Brayden Carlson uID:
 */
public class AnagramUtil {
	
	public static <T> void insertionSort(T[] input, Comparator<? super T> _comparator) {
		// Check for null
		if (input == null) {
			return;
		}
		// Check if nothing needs to be sorted
		if (input.length < 2) {
			return;
		}

		int insertionSpot = 0;
		int testerIndex = 0; // index to walk through the test array

		// Prime the algorithm
		// Don't use the [0] index as it has nothing to compare to
		testerIndex++;

		// Create Object array to hold the insertions
		// pull the element out to make an insertion point
		T holder = input[testerIndex];

		while (testerIndex < input.length) {
			holder = input[testerIndex];
			for (int i = testerIndex - 1; i >= 0; i--) {
				// If object to insert is larger than largest in holder array,
				// end this process
				if ((i == testerIndex - 1) && (_comparator.compare(input[i], holder) <= 0)) {
					insertionSpot = testerIndex;
					break;
				}
				if (_comparator.compare(input[i], holder) > 0) {
					// Shift holder value (insertion spot)
					input[i + 1] = input[i];
					insertionSpot = i;
				} else {
					insertionSpot = i + 1;
					break;
				}
			}
			// insertion spot is where we should put the value
			input[insertionSpot] = holder;
			testerIndex++;
		}
		return;
	}
	
	/**
	 * This method checks if two strings are anagrams
	 * 
	 * @param firstWord
	 *            -- first string to be compared
	 * @param secondWord
	 *            -- second string to be compared 
	 * @return -- true if the two input strings are anagrams,
	 *         otherwise returns false.
	 */
	public static boolean areAnagrams(String firstWord, String secondWord) {
		// check for null
		if (firstWord == null || secondWord == null) {
			return false;
		}
		// check for empty strings
		if (firstWord.length() == 0 || secondWord.length() == 0) {
			return false;
		}

		// Sort the strings and make sure to convert the string to lower case
		String firstWordSorted = sort(firstWord.toLowerCase());
		String secondWordSorted = sort(secondWord.toLowerCase());

		// Ignore capitalization on comparisons
		if (firstWordSorted.equalsIgnoreCase(secondWordSorted)) {
			return true;
		}
		// not anagrams
		return false;
	}
	
	/**
	 * Finds the largest collection of anagrams within a collection
	 * of anagrams (in no particular order).
	 * 
	 * @param collection
	 * 		-- group of anagrams 
	 * @return 
	 * 		-- method returns the largest group of anagrams in the input array
	 *         of words. It returns an empty array if there are no anagrams.
	 */
	public static String[] getLargestAnagramGroup(String[] collection) {
		// Check for null 
		if (collection == null) {
			return null;
		}
		// Check for less than two strings
		if (collection.length < 2) {
			return new String[0];
		}

		final ArrayList<String> holders = new ArrayList<String>();
		int count = 0;
		int index = 0;
		int hi = 0;
		int hiIndex = 0;
		AnagramUtil.insertionSort(collection, new Comparator<String>() {
			// This sorts the initial array; combines the
			// anagrams to one another
			public int compare(String stringOne, String stringTwo) {
				String stringOneA = AnagramUtil.sort(stringOne.toLowerCase());
				String stringTwoA = AnagramUtil.sort(stringTwo.toLowerCase());
				if (AnagramUtil.areAnagrams(stringOne, stringTwo)) {
					// populate an array list with the anagram values to compare
					if (!holders.contains(stringOneA)) {
						holders.add(stringOneA);
					}
				}
				// return an array of anagrams next to one another
				return stringOneA.compareTo(stringTwoA);
			}
		});

		for (String holder : holders) {
			for (int b = 0; b < collection.length; b++) {
				// compare what is in our reference list to the collection
				if (AnagramUtil.areAnagrams(holder, collection[b])) {
					index = b;
					count++;
				}
			}
			if (count > hi || count == hi) {
				hi = count;
				hiIndex = index;
			}
			count = 0;
		}
		if (hi == 0) {
			String[] nil = new String[0];
			return nil;
		} else {
			int start = hiIndex - hi + 1;
			int end = hiIndex + 1;
			hiIndex++;
			return Arrays.copyOfRange(collection, start, end);
		}

	}

	/**
	 * This method sorts the input string. Insertion sort handles the sorting.
	 * 
	 * @param toBeSorted
	 *            -- the input string to be sorted lexicographically
	 * @return -- the sorted input string
	 */
	public static String sort(String toBeSorted) {
		// check for null String
		if (toBeSorted == null) {
			return null;
		}
		// check if nothing needs to be sorted
		if (toBeSorted.length() < 2) {
			return toBeSorted;
		}
		// Convert from String to an array of characters
		Character[] characterArray = toCharacterArray(toBeSorted.toCharArray());

		// Sort using insertion sort and a simple character comparator
		insertionSort(characterArray, new Comparator<Character>() {
			@Override
			public int compare(Character c1, Character c2) {
				return c1.compareTo(c2);
			}
		});
		// Convert the character array back to String
		String sorted = new String(characterToString(characterArray));

		return sorted;
	}

	/**
	 * This method finds the largest collection of anagrams within a collection
	 * of anagrams (in no particular order) from a file this method is
	 * essentially an extension of the method above.
	 * 
	 * @param filename
	 *            -- file to be read in and put into an array to be examined
	 * @return -- method returns the largest group of anagrams in the input
	 *         array of words, in no particular order. It returns an empty array
	 *         if there are no anagrams in the input array.
	 */
	@SuppressWarnings("resource")
	public static String[] getLargestAnagramGroup(String filename) {
		ArrayList<String> fileInArray = new ArrayList<String>();
		String[] empty = new String[] {};
		try {
			Scanner fileIn = new Scanner(new File(filename));
			int lineNumber = 1;

			while (fileIn.hasNext()) {
				String line = fileIn.nextLine();
				String[] lin = line.split(" ");
				if (lin.length != 1) {
					throw new ParseException("File", lineNumber);
				}
				Scanner readLine = new Scanner(line);

				if (!readLine.hasNext())
					throw new ParseException("Anagram", lineNumber);
				String anagram = readLine.next();

				fileInArray.add(anagram);
				lineNumber++;

			}
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage() + " Could not read in file");
			return empty;
		} catch (ParseException e) {
			System.err.println(e.getLocalizedMessage() + " formatted incorrectly at line " + e.getErrorOffset()
					+ " . File could not be added to an array");
			return empty;
		}

		String[] readInput = fileInArray.toArray(new String[fileInArray.size()]);
		if (readInput.length == 0) {
			return empty;
		} else {
			return AnagramUtil.getLargestAnagramGroup(readInput);
		}

	}
	
	/**
	 * A private toString method we wrote to get our character array back to
	 * string format
	 * 
	 * @param charArr
	 *            -- the input character array to be converted
	 * @return -- the string version of the input character array
	 */
	private static String characterToString(Character[] charArr) {
		String outputString = charArr[0].toString();
		for (int i = 1; i < charArr.length; i++) {
			outputString = outputString + charArr[i];
		}
		return outputString;
	}
	
	/**
	 * A private helper method we wrote to convert primitive characters into
	 * character objects
	 * 
	 * @param toBeConverted
	 *            -- the array of chars we are looking to covert
	 * @return -- the converted array of character objects
	 */
	private static Character[] toCharacterArray(char[] toBeConverted) {
		Character[] chars = new Character[toBeConverted.length];
		for (int i = 0; i < toBeConverted.length; i++) {
			chars[i] = toBeConverted[i];
		}
		return chars;
	}
}
