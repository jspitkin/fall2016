package assignment04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 * Utility class that determines if two strings are anagrams of each other, with a custom insertion sort sorting algorithm
 * 
 * @author Axel Kerksiek u0691509 and Nathan Milot u1063587
 * @since Sep 20, 2016
 */
public class AnagramUtil {

	/**
	 * This sets up the comparator to the generic compareTo()
	 */
	private static Comparator<String> comparator = new Comparator<String>() {
		@Override
		public int compare(String o1, String o2) {
			return o1.compareTo(o2);
		}
	};
	
	/**
	 * Default Constructor
	 */
	public AnagramUtil(){
		//Intentionally Empty
	}
	
	/**
	 * This method returns the sorted version of the input string. The sorting must be accomplished using an insertion sort.
	 * 
	 * @param s1 - the string to sort
	 * @return result - the string sorted in lexicographical order
	 */
	public static String sort(String s1){
		// call insertion sort
		// return the string from insertionSort
		String[] arr = new String[s1.length()];
		s1 = s1.toLowerCase();
		for(int i = 0; i <= s1.length() - 1; i++){
			arr[i] = s1.substring(i, i + 1);
		}
		insertionSort(arr, comparator);
		String result = "";
		for(String item : arr){
			result += item;
		}
		return result;
	}
	
	/**
	 * This uses Java's Sort Function instead of our insertionSort
	 * 
	 * @param s1 - the string to sort
	 * @return result - the string sorted in lexicographical order
	 */
	public static String sortJava(String s1){
		// call insertion sort
		// return the string from insertionSort
		String[] arr = new String[s1.length()];
		s1 = s1.toLowerCase();
		for(int i = 0; i <= s1.length() - 1; i++){
			arr[i] = s1.substring(i, i + 1);
		}
		Arrays.sort(arr, comparator);
		String result = "";
		for(String item : arr){
			result += item;
		}
		return result;
	}
	
	/**
	 * This generic method sorts the input array using an insertion sort and the input Comparator object.
	 * 
	 * @param arr - the generic array to be sorted
	 * @param comparator - the comparator that defines the order
	 */
	public static <T> void insertionSort(T[] arr, Comparator<? super T> comparator){
		for(int arrPos = 1; arrPos < arr.length; arrPos++){
			T index = arr[arrPos];
			int sortMarker = arrPos;
			while(sortMarker > 0 && comparator.compare(arr[sortMarker - 1], index) > 0){
				arr[sortMarker] = arr[sortMarker - 1];
				sortMarker--;
			}
			arr[sortMarker] = index;
		}
	}
	
	/**
	 * This method returns true if the two input strings are anagrams of each other, otherwise returns false.
	 * 
	 * @param s1 - the first string
	 * @param s2 - the second string
	 * @return boolean - whether or not the two strings are anagrams
	 */
	public static boolean areAnagrams(String s1, String s2){
		if(sort(s1).equals(sort(s2)))
			return true;
		return false;
	}
	
	/**
	 * This method returns the largest group of anagrams in the input array of words, in no particular order. 
	 * It returns an empty array if there are no anagrams in the input array.
	 * 
	 * @param list - the list of strings to search through
	 * @return - an array of strings that are anagrams of each other, if no anagrams are found or the list passed is empty, returns an empty list
	 */
	public static String[] getLargestAnagramGroup(String[] list){
		if(list.length > 0){
			Map<String, Integer> tmpMap = new HashMap<String, Integer>();
			for(int i = 0; i < list.length; i++){
				if(list[i] == null)
					continue;
				if(tmpMap.containsKey(sort(list[i])))
					tmpMap.put(sort(list[i]), tmpMap.get(sort(list[i])) + 1);
				else
					tmpMap.put(sort(list[i]), 1);
			}
			String key = "";
			int highValue = 0;
			for(String tmpKey : tmpMap.keySet()){
				if(tmpMap.get(tmpKey) > highValue){
					highValue = tmpMap.get(tmpKey);
					key = tmpKey;
				}
			}
			if(tmpMap.get(key) == null){
				return new String[0];
			}
			String[] result = new String[tmpMap.get(key)];
			int count = 0;
			for(int i = 0; i < list.length; i++){
				if(list[i] == null)
					continue;
				if(sort(list[i]).equals(key))
					result[count++] = list[i];
			}
			return result;
		}
		return new String[0];
	}
	
	/**
	 * This method returns the largest group of anagrams in the input array of words, in no particular order. 
	 * It returns an empty array if there are no anagrams in the input array. 
	 * 
	 * IT USES SORTJAVA(), WHICH USES ARRAYS.SORT
	 * {@link #sortJava()} 
	 * 
	 * @param list - the list of strings to search through
	 * @return - an array of strings that are anagrams of each other, if no anagrams are found or the list passed is empty, returns an empty list
	 */
	public static String[] getLargestAnagramGroupJavaSort(String[] list){
		if(list.length > 0){
			Map<String, Integer> tmpMap = new HashMap<String, Integer>();
			for(int i = 0; i < list.length; i++){
				if(list[i] == null)
					continue;
				if(tmpMap.containsKey(sortJava(list[i])))
					tmpMap.put(sortJava(list[i]), tmpMap.get(sortJava(list[i])) + 1);
				else
					tmpMap.put(sortJava(list[i]), 1);
			}
			String key = "";
			int highValue = 0;
			for(String tmpKey : tmpMap.keySet()){
				if(tmpMap.get(tmpKey) > highValue){
					highValue = tmpMap.get(tmpKey);
					key = tmpKey;
				}
			}
			if(tmpMap.get(key) == null){
				return new String[0];
			}
			String[] result = new String[tmpMap.get(key)];
			int count = 0;
			for(int i = 0; i < list.length; i++){
				if(list[i] == null)
					continue;
				if(sort(list[i]).equals(key))
					result[count++] = list[i];
			}
			return result;
		}
		return new String[0];
	}
	
	/**
	 * 	Behaves the same as the previous method, but reads the list of words from the input filename. 
	 *  It is assumed that the file contains one word per line. 
	 *  If the file does not exist or is empty, the method returns an empty array because there are no anagrams.
	 * 
	 * @param filename - the file name that contains the list of words
	 * @return - an array of strings that are anagrams of each other, if no anagrams are found or the list passed is empty, returns an empty list
	 */
	public static String[] getLargestAnagramGroup(String filename){
		// make list of words from the file
		// pass the list into the other method and return it
		ArrayList<String> listOfWords = new ArrayList<String>();
		try {
			Scanner s = new Scanner(new File(filename));
			while(s.hasNext()){
				listOfWords.add(s.next());
			}
			s.close();
			String[] arrayOfWords = new String[listOfWords.size()];
			int count = 0;
			for(String tempString : listOfWords){
				arrayOfWords[count++] = tempString;
			}
			return getLargestAnagramGroup(arrayOfWords);
		} catch (FileNotFoundException e) {
			return new String[0];
		}
	}
	
	/**
	 * Create a random string [a-z] of specified length
	 * 
	 * @param length - the specified length
	 * @return - a string of the specified length with random characters
	 */
	public static String randomString(int length)
	{
		Random rand = new Random();
		StringBuilder stringBuilder = new StringBuilder();
		for(int i = 0; i < length; i++)
		{
			// ASCII values a-z,A-Z are contiguous (52 characters)
			char randomChar = (char)('a' + (rand.nextInt(26)));
			stringBuilder.append(randomChar); 
		}
		return stringBuilder.toString();
	}
	
}
