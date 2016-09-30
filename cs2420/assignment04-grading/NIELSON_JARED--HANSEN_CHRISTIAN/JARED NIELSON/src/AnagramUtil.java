package assignment04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Utility class that has a generic method for performing insertion sort on an array
 * as well as Anagram utility methods using insertion sort.
 * 
 * @author Jared Nielson U0495206 & Christian Hansen u0621884
 *
 */
public class AnagramUtil {
	
	/**
	 * Returns a sorted version of the passed in String lexographically.
	 * 
	 * @param toSort - The string to be sorted
	 * @return a new string who's elements are in lexographical order.
	 */
	public static String sort(String toSort){
		Character[] charArray = new Character[toSort.length()];
		for(int index = 0; index < charArray.length; index++){
			charArray[index] = toSort.charAt(index);
		}
		
		insertionSort(charArray, new CharacterComparator());
		
		String result = "";
		for(int index = 0; index < charArray.length; index++){
			result += charArray[index];
		}
		
		return result;
	}
	
	/**
	 * Generic Method that will perform an InsertionSort the given array using the ordering defined by the comparator.
	 * @param toSort - an array of type T (Generic)
	 * @param cmp - a comparator that will define the ordering.
	 */
	public static <T> void insertionSort(T[] toSort, Comparator<? super T> cmp){
		for(int outIdx = 1; outIdx < toSort.length; outIdx++){
			T tmp = toSort[outIdx];
			for(int innerIdx = outIdx; innerIdx > 0; innerIdx--){
				if(cmp.compare(tmp, toSort[innerIdx - 1]) < 0){
					toSort[innerIdx] = toSort[innerIdx - 1];
					toSort[innerIdx - 1] = tmp;
 				} else {
 					break;
 				}
			}
		}
	}
	
	/**
	 * areAnagrams method takes two strings and checks to see if they are anagrams. It makes use
	 * of the sort method contained in this class.
	 * @param s1 - a string to be compared
	 * @param s2 - a string to be compared
	 * @return true if the strings are anagrams, false if not
	 */
	public static boolean areAnagrams(String s1, String s2){
		String s1Sorted = sort(s1.toLowerCase());
		String s2Sorted = sort(s2.toLowerCase());
		
		return s1Sorted.equals(s2Sorted);
	}
	
	/**
	 * Finds the largest group of anagrams in an array using insertion sort.
	 * @param toSearch - The array to be searched for the largest group of anagrams.
	 * @return an array of strings containing each string from the largest anagram group. If two groups
	 * have equal frequency the first group encountered will be returned.
	 */
	public static String[] getLargestAnagramGroup(String[] toSearch){
		String[] sortedStrings = new String[toSearch.length];
		
		for(int arrIdx = 0; arrIdx < toSearch.length; arrIdx++){
			sortedStrings[arrIdx] = sort(toSearch[arrIdx].toLowerCase());
		}
		
		String[] sortedStringArray = Arrays.copyOf(sortedStrings, sortedStrings.length);
		insertionSort(sortedStringArray, new StringComparator());
		
		int bestAnagramCount = 0;
		int currentAnagramCount = 0;
		String bestAnagram = "";
		String currentAnagram = "";
		
		
		if(sortedStringArray.length > 0){
			currentAnagram = sortedStringArray[0];
			currentAnagramCount++;
		}
		
		if(sortedStringArray.length == 1){
			bestAnagram = currentAnagram;
			bestAnagramCount = currentAnagramCount;
		}
		
		
		for(int index = 1; index < sortedStringArray.length; index++){			
			if(currentAnagram.equals(sortedStringArray[index])){
				currentAnagramCount++;
			}else{
				if(currentAnagramCount > bestAnagramCount){
					bestAnagram = currentAnagram;
					bestAnagramCount = currentAnagramCount;
				}
				currentAnagramCount = 1;
				currentAnagram = sortedStringArray[index];
			}
		}
		
		String[] resultArray = new String[bestAnagramCount];
		int count = 0;
			
		for(int index = 0; index < sortedStrings.length; index++){
			
			if(sortedStrings[index].equals(bestAnagram)){
				resultArray[count] = toSearch[index];
				count++;
			}
		}
		
		return resultArray;
	}
	
	/**
	 * Finds the largest group of anagrams in a file.
	 * @param fileName - The text file to be searched for the largest anagram group.
	 * @return an array of strings containing each string from the largest anagram group. If two groups
	 * have equal frequency the first group encountered will be returned. Returns an empty string array if
	 * the file is not found.
	 */
	public static String[] getLargestAnagramGroup(String fileName){
		String[] arrayOfWords = readFile(fileName);
		return getLargestAnagramGroup(arrayOfWords);
	}
	
	/**
	 * Helper method that takes in a file and returns an array of strings from the text in the file.
	 * @param fileName - the name of the file with extension
	 * @return an array that contains strings which are from the text
	 */
	private static String[] readFile(String fileName){
		ArrayList<String> results = new ArrayList<String>();
		try(BufferedReader input = new BufferedReader(new FileReader(fileName))) {
			while(input.ready()) {
				results.add(input.readLine());
			}
			input.close();
		} catch(Exception e) {
			return new String[0];
		}
		return results.toArray(new String[results.size()]);
	}
}
