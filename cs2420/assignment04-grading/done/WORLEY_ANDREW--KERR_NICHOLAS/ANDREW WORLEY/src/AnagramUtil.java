package assignment04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Class contains methods to sort strings and generic lists in ascending order (a-z)
 * For generic lists, a comparator is required to sort its contents.
 * @author Andrew Worley: u0651238
 * @author Nicholas Kerr: u0125990
 * last update, 9/20/16 16:10
 */
public class AnagramUtil {
	
	/**
	 * Method returns the sorted version (a being least, and z being greatest)
	 * of the input string using insertion sort.
	 * @param input -- String value
	 * @return -- Sorted string
	 */
	public static String sort(String input) {
		input = input.toLowerCase();
		char[] sorted = new char[input.length()];
		if (input.length() > 0) {
			sorted[0] = input.charAt(0);
		}
		for (int index = 1; index < input.length(); index++) {
			if (input.charAt(index) >= sorted[index-1]) {
				sorted[index] = input.charAt(index);
			}
			int innerIndex = index;
			while(innerIndex > 0 && input.charAt(index) < sorted[innerIndex-1]) {
				char holder = sorted[innerIndex-1];
				sorted[innerIndex-1] = input.charAt(index);
				sorted[innerIndex] = holder;
				innerIndex--;
			}
		}
		return new String(sorted);
	}
	
	/**
	 * This generic method sorts an input list using an 
	 * insertion sort and the input Comparator object.
	 * @param list -- Generic list of objects
	 * @param comparator -- Comparator for objects held within the specified list
	 */
	public static <T> void insertionSort(T[] list, Comparator<? super T> comparator) {		
		for (int index = 1; index < list.length; index++) {
			int innerIndex = index;
			int cmpIndex = index;
			while(innerIndex > 0 && comparator.compare(list[cmpIndex], list[innerIndex - 1]) < 0) {
				T holder = list[innerIndex-1];
				list[innerIndex-1] = list[cmpIndex];
				list[innerIndex] = holder;
				cmpIndex--;
				innerIndex--;
			}
		}
	}
	
	/**
	 * This method takes two strings and determines if they are
	 * anagrams of each other.
	 * @param string1 -- any string value
	 * @param string2 -- any string value
	 * @return -- True if the specified strings are anagrams of each other
	 * 			  : False otherwise.
	 */
	public static boolean areAnagrams(String string1, String string2) {
		return sort(string1).equals(sort(string2));
	}
	
	/**
	 * Method finds the largest group of words that are anagrams in an
	 * array of Strings.
	 * @param array -- an array of Strings
	 * @return -- String array containing the original words that make up the largest
	 * 				group of anagrams from the passed String array
	 */
	public static String[] getLargestAnagramGroup(String[] array) {
		int startIndex = 0;
		int endIndex = 0;
		int sliceStart = 0;
		int largestGroup = 0;
		int cmpIndex = 0;
		
		insertionSort(array, new StringAnagramComparator());
//		Arrays.sort(array, new StringAnagramComparator());
		
		while (startIndex < array.length-1) {
			cmpIndex = startIndex;
			endIndex = startIndex;
			while(cmpIndex < array.length-1 && areAnagrams(array[cmpIndex], array[cmpIndex+1])) {
				endIndex++;
				cmpIndex++;
			}
			if (endIndex - startIndex != 0 && ((endIndex - startIndex) +1) > largestGroup) {
				sliceStart = startIndex;
				largestGroup = (endIndex - startIndex) + 1;
			}
			startIndex = endIndex+1;
		}
		String[] result = new String[largestGroup];
		if (largestGroup > 0) {
			for (int index = 0; index < largestGroup; index++) {
				result[index] = array[index+sliceStart];
			}
		}
		return result;
	}
	
	/**
	 * Method finds the largest group of words that are anagrams from a .txt file.
	 * It is assumed that the file contains one word per line. 
	 * If the file does not exist or is empty,the method returns an empty array 
	 * because there are no anagrams.
	 * @param filename -- String filename with a .txt extension
	 * @return -- String array containing the original words that make up the largest
	 * 				group of anagrams from the .txt file
	 */
	public static String[] getLargestAnagramGroup(String filename) {
		ArrayList<String> stringArray = new ArrayList<String>();
		try(BufferedReader file = new BufferedReader(new FileReader(filename))) {
			while(file.ready()) {
				stringArray.add(file.readLine());
			}
			file.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return getLargestAnagramGroup(stringArray.toArray(new String[stringArray.size()]));
	}
}
