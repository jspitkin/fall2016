package assignment04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Class with static methods that determine if two words are anagrams and 
 * finds the largest group of anagrams in an array of words.
 * @author Kira Parker u1073760, Nathaniel Coleman u0913541
 *
 */
@SuppressWarnings("unused")
public class AnagramUtil {

	/**
	 * Sorting is accomplished using an insertion sort.
	 * @param sortMe -- string to be sorted
	 * @return the sorted version of the input string
	 * 
	 */
	public static String sort(String sortMe){
		String[] word = new String[sortMe.length()];
		for(int index = 0; index<sortMe.length(); index++){
			word[index] = sortMe.substring(index,index+1);
		}
		insertionSort(word, new OrderString());
		String sorted = "";
		for(int index=0; index<word.length; index++){
			sorted += word[index];
		}
		return sorted;
	}
	
	/**
	 * This generic method sorts the input array using an insertion sort 
	 * and the input Comparator object.
	 * @param arr -- array of elements to be sorted
	 * @param comparator -- comparator to sort elements by
	 */
	public static <T> void insertionSort(T[] arr, Comparator<? super T> comparator){ //DO NOT CHANGE SIGNITURE
		for(int index=1; index<arr.length; index++){ //index of element
			int testIndex = index-1; //index of element below element that is being inserted
			while(testIndex>=0 && comparator.compare(arr[testIndex],arr[testIndex+1])>0){
				//swap arr[testIndex] and arr[testIndex+1]
				T temp = arr[testIndex];
				arr[testIndex] = arr[testIndex+1];
				arr[testIndex+1] = temp;
				testIndex--;
			}
		}
	}
	
	/**
	 * @param stringOne, stringTwo
	 * @return true if the two input strings are anagrams of eachother, false otherwise
	 */
	public static boolean areAnagrams(String stringOne, String stringTwo){ //DO NOT CHANGE SIGNITURE
		//copy strings so that stringOne and stringTwo don't change will call toLowerCase()
		String stringOneLC = stringOne.toLowerCase();
		String stringTwoLC = stringTwo.toLowerCase();
		return sort(stringOneLC).equals(sort(stringTwoLC).toLowerCase());
	}
	
	/**
	 * @param words -- array of Strings, will find the largest group of anagrams in the array
	 * @return the largest group of anagrams in the input array of words, in no particular order. Returns an empty
	 * array if there are no anagrams in the input array.
	 */
	public static String[] getLargestAnagramGroup(String[] words){
		String[] wordsCopy = new String[words.length]; //copy words so input array is not changed
		for(int index=0; index<words.length; index++){
			wordsCopy[index] = words[index];
		}
		Comparator<String> comparator = new OrderSortedWords();
		insertionSort(wordsCopy, comparator); //sort the array
		//Arrays.sort(words, comparator);
		int startIndex = 0, bestStartIndex = 0; //index where the largest group of anagrams starts
		int num = 0, bestNum = 0; //number of anagrams in the largest group
		while(startIndex<wordsCopy.length){
			num=0;
			while(startIndex+num<wordsCopy.length && areAnagrams(wordsCopy[startIndex+num],wordsCopy[startIndex])){
				num++; //next word is an anagram of the current word so increase num
			}
			//end of current list of anagram, now see if it was longer than the previous list of anagrams
			if(num>bestNum){
				bestNum = num;
				bestStartIndex = startIndex;
			}
			startIndex += num;
		}
		//copy longest group of anagrams over to a new array
		if(bestNum<2){ //no anagrams so return empty array
			String[] group = {};
			return group;
		}
		String[] group = new String[bestNum];
		for(int index = bestStartIndex; index<bestStartIndex+bestNum; index++){
			group[index-bestStartIndex] = wordsCopy[index];
		}
		return group;
	}
	
	/**
	 * Reads the list of words from the input filename. 
	 * It is assumed that the file contains one word per line.
	 * @param filename -- file that contains words
	 * @return the largest group of anagrams in the specified file in no particular order. 
	 * Returns an empty array if there are no anagrams in the file or if the file does not exist.
	 */
	public static String[] getLargestAnagramGroup(String filename){
		ArrayList<String> results = new ArrayList<String>();
		try(BufferedReader input = new BufferedReader(new FileReader(filename))) {
			while(input.ready()) {
				results.add(input.readLine());
			}
			input.close();
		} catch(Exception e) {
			//e.printStackTrace();
			return new String[0];
		}
		String[] words = results.toArray(new String[results.size()]);
		return getLargestAnagramGroup(words);
	}
	
	/**
	 * Compares two strings based on the String compareTo method once the strings are sorted
	 */
	protected static class OrderSortedWords implements Comparator<String> {

		/**
		 * Returns a negative value if lhs is earlier in the alphabet than rhs. Returns a
		 * positive value if lhs is later in the alphabet than rhs. Returns 0 if lhs and rhs
		 * are equal.
		 */
		public int compare(String lhs, String rhs) {
			String lhsLowerCase = lhs.toLowerCase();
			String rhsLowerCase = rhs.toLowerCase();
			return sort(lhsLowerCase).compareTo(sort(rhsLowerCase));
		}
	}
	
	protected static class OrderString implements Comparator<String> {

		/**
		 * Returns a negative value if lhs is earlier in the alphabet than rhs. Returns a
		 * positive value if lhs is later in the alphabet than rhs. Returns 0 if lhs and rhs
		 * are equal.
		 */
		public int compare(String lhs, String rhs) {
			return lhs.compareTo(rhs);
		}
	}
}


