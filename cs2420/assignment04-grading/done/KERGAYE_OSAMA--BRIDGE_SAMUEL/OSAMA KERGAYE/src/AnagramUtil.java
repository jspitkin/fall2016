/**
 * @author Osama Kergaye & Samuel Bridge
 * @UID u0767339 & u0984334
 */
package assignment04;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;


/**
 * 
 * @author Osama Kergaye & Samuel Bridge
 * @UID u0767339 & u0984334
 * 
 */
public class AnagramUtil {

	/**
	 * 
	 * This method returns the sorted version of the input string. The sorting
	 * must be accomplished using an insertion sort.
	 * 
	 * @param String element
	 * @return String of elements letters alphabetized
	 */
	public static String sort(String element) {

		if(element == null)
		{
			return null;
		}
		
		
		
		String lowerd = element.toLowerCase();
		String[] letterArray = lowerd.split("");

		
		
		insertionSort(letterArray, new StringComparator());

		
		//For timing, use javasort
		//javaSortForTimingQuestion(letterArray, new StringComparator());
		
		String result = "";

		for (int i = 0; i < letterArray.length; i++) {
			result += letterArray[i];
		}

		return result;

	}

	/**
	 * 
	 * This generic method sorts the input array using an insertion sort and the
	 * input Comparator object.
	 * 
	 * @param generic[] genericElement
	 * @param Comparator<? super T> comp
	 */
	public static <T> void insertionSort(T[] genericArray, Comparator<? super T> comp) {
		
		for (int i = 1; i < genericArray.length; i++) {
			T temp;
			for (int j = i; j > 0; j--) {

				if (comp.compare(genericArray[j], genericArray[j - 1]) < 0) {
					temp = genericArray[j];
					genericArray[j] = genericArray[j - 1];
					genericArray[j - 1] = temp;
				}
			}
		}

	}

	
	
	public static <T> void javaSortForTimingQuestion(T[] genericArray, Comparator<? super T> comp) {


		
		Arrays.sort(genericArray, comp);

	}
	/**
	 * 
	 * 
	 * This method returns true if the two input strings are anagrams of each
	 * other, otherwise returns false.
	 * 
	 * @param String lhs
	 * @param String rhs
	 * @return boolean
	 */
	public static boolean areAnagrams(String lhs, String rhs) {

		
		if(lhs == null || rhs == null)
		{
			return false;
		}
		
		
		
		
		if (sort(lhs).compareTo(sort(rhs)) == 0) {
			return true;
		}

		return false;

	}

	/**
	 * This method returns the largest group of anagrams in the input array of
	 * words, in no particular order. It returns an empty array if there are no
	 * anagrams in the input array.
	 * 
	 * @param String[] arrayOfWords
	 * @return String[] containing the words belonging to the largest group of anagrams in arrayOfWords
	 */
	public static String[] getLargestAnagramGroup(String[] arrayOfWords) {

	
		
		
		// Section 1. returns an array with the words letters alphabetized
		String[] anagramedVersionArray = new String[arrayOfWords.length];
		int counter = 0;
		for (String word : arrayOfWords) {
			anagramedVersionArray[counter] = sort(word);
			counter++;
		}
		// End section 1

		
		
		/*
		 * Section 2. Next, the anagramed version of the words is then
		 * alphabetized
		 * 
		 * If insertoinSort gets a null, return emtpy string
		 */
		try {
			insertionSort(anagramedVersionArray, new StringComparator());
		} catch (NullPointerException name) {

			return new String[]{""};
		} 
		//For timing, use javasort
		//javaSortForTimingQuestion(anagramedVersionArray, new StringComparator());
		// End section 2

		
		/*
		 * Section 3. Then, the largest group of anagrams word is stored in
		 * largestAnagramWord
		 */
		String largestAnagramWord = "";
		int currentCount = 1;
		int biggestCount = 0;

		for (int i = 0; i < anagramedVersionArray.length - 1; i++) {

			if (anagramedVersionArray[i].equals(anagramedVersionArray[i + 1])) {
				currentCount += 1;
			}
			if (currentCount > biggestCount && currentCount > 1) {

				biggestCount = currentCount;
				largestAnagramWord = anagramedVersionArray[i];
			}

		}
		// End section 3

		
		/*
		 * Section 4. The word is then compared to the original array, and if
		 * the word is an anagram of a word in the original array, it is added
		 * to largestAnagramGroup
		 */
		String[] largestAnagramGroup = new String[biggestCount];
		counter = 0;
		for (int i = 0; i < arrayOfWords.length - 1; i++) {

			if (areAnagrams(largestAnagramWord, arrayOfWords[i])) {

				largestAnagramGroup[counter] = arrayOfWords[i];
				counter++;

			}

		}
		// End section 4
		
		//if there are no anagrams, return empty
		if(biggestCount == 0)
		{
			
			return new String[]{""};
			
		}
		

		return largestAnagramGroup;

	}

	/**
	 * 
	 * 
	 * Behaves the same as the previous method, but reads the list of words from
	 * the input filename. It is assumed that the file contains one word per
	 * line. If the file does not exist or is empty, the method returns an empty
	 * array because there are no anagrams.
	 * 
	 * @param String filename
	 * @return String[] containing the words belonging to the largest group of anagrams in filename
	 */
	public static String[] getLargestAnagramGroup(String filename) {
		ArrayList<String> results = new ArrayList<String>();

		try (BufferedReader input = new BufferedReader(new FileReader(filename))) {
			while (input.ready()) {
				results.add(input.readLine());
			}
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String[] regularArray = results.toArray(new String[results.size()]);

		return getLargestAnagramGroup(regularArray);

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
		 * @return  -- Positive value if lhs is lexicographically greater than the
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

}
