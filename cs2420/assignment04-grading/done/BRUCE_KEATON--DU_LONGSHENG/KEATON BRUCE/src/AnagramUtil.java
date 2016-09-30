package assignment04;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Anagram Utilities
 * @author Andrew Keaton Bruce, Longsheng Du
 * u1006889, u1093993
 * CS 2420
 * 9/20/2016
 */
public class AnagramUtil {
	
	/**
	 * Uses insertion sort to sort the str parameter.
	 * @param str
	 * @return the sorted version of the input string.
	 */
	public static String sort(String str) {
		//Define a new character array in a manner we can use.
		char[] charArray = str.toCharArray();
		Character[] stringToBeSorted = new Character[charArray.length];
		
		//Fill the new Character array with characters in the String
		for(int i = 0; i < charArray.length; i++) {
			stringToBeSorted[i] = charArray[i];
		}
		
		//Will compare two characters, very similar to compareTo() but also uses lower case.
		Comparator<Character> comparator = new Comparator<Character>(){
			public int compare(Character o1, Character o2) {
				return ((Character) Character.toLowerCase(o1)).compareTo((Character) Character.toLowerCase(o2));
			}
		};
		
		//Sort the String by the comparator.
		insertionSort(stringToBeSorted, comparator);
		
		//Define a new string, fill it, and return it.
		String sortedString = "";
		for(int i = 0; i < stringToBeSorted.length; i++) {
			sortedString += stringToBeSorted[i];
		}
		return sortedString;
	}
	
	/**
	 * Sorts the input array using an insertion sort and the input Comparator object.
	 * @param typeArray
	 * @param comparator
	 */
	public static <T> void insertionSort(T[] typeArray, Comparator<? super T> comparator) {
		//Defines the sorted section of the array
		T sortedRightEdge;
		for(int index = 0; index < typeArray.length; index++) {
			//Set the sorted portion to the element in the array at index
			sortedRightEdge = typeArray[index];
			int comparisonIndex = index;
			//Begins comparing elements in the array to find the place to insert
			while(comparisonIndex > 0 && comparator.compare(typeArray[comparisonIndex - 1],sortedRightEdge) > 0) {
				typeArray[comparisonIndex] = typeArray[comparisonIndex - 1];
				comparisonIndex--;
			}
			typeArray[comparisonIndex] = sortedRightEdge;
		}
	}
		
	/**
	 * @param firstStr
	 * @param secondStr
	 * @return true if the two input strings are anagrams of each other, otherwise returns false.
	 */
	public static boolean areAnagrams(String firstStr, String secondStr) {
		//Use the sorting method already created and then test equality.
		//Because the sort method does not return lower cases, also compare with lower cases.
		String firstSortedStr = sort(firstStr).toLowerCase();
		String secondSortedStr = sort(secondStr).toLowerCase();
		
		return firstSortedStr.equals(secondSortedStr);
	}
	
	/**
	 * @param stringArray
	 * @return the largest group of anagrams in the input array of words, in no particular order. 
	 * It returns an empty array if there are no anagrams in the input array.
	 */
	public static String[] getLargestAnagramGroup(String[] stringArray) {
		
		//Creates a new array so we don't change data
		String[] anagramArray = new String[stringArray.length];
		
		//fill the array
		for(int i=0; i<stringArray.length; i++)
		{
			anagramArray[i] = stringArray[i];
		}
				
		//Define a new comparator that compares sorted strings (anagrams)
		//This is useful so the strings are not modified but are compared based on their modified form.
		Comparator<String> compareAnagrams = new Comparator<String>(){
			public int compare(String s1, String s2) {
				String sortedS1 = sort(s1).toLowerCase();
				String sortedS2 = sort(s2).toLowerCase();
				return sortedS1.compareTo(sortedS2);
			}
		};
		
		//Begin sorting the array by Strings with the above comparator
		insertionSort(anagramArray, compareAnagrams);
		
		//This sections is devoted to finding the largest group of anagrams in the array.
		int largestAnagramAmount = 0;
		int indexOfLargestAnagram = 0;
		int indexOfAnagram = 0;
		
		//Find largest group by comparing indices
		for (int i = 0; i < anagramArray.length; i++) {
			if(!sort(anagramArray[indexOfAnagram].toLowerCase()).equals(sort(anagramArray[i].toLowerCase()))) {
				if(i - indexOfAnagram > largestAnagramAmount) {
					largestAnagramAmount = i - indexOfAnagram;
					indexOfLargestAnagram = indexOfAnagram;
				}
				indexOfAnagram = i;
			}
		}
		
		//Here we want to create a new array that only contains the new large group of anagrams
		String[] largestAnagramArray = new String[largestAnagramAmount];
		
		//Check if there is not a group of anagrams
		if(largestAnagramAmount <= 1)
		{
			return new String[0];
		}
		
		//Add this group of anagrams into the new array
		for(int i=0; i < largestAnagramAmount; i++) {
			largestAnagramArray[i] = anagramArray[i+indexOfLargestAnagram];
		}

		return largestAnagramArray;
	}
	
	/**
	 * Reads the list of words from the input filename.
	 * It is assumed that the file contains one word per line.
	 * @param filename
	 * @return the largest anagram group
	 * If the file does not exist or is empty, the method returns an empty array because there are no anagrams.
	 */
	public static String[] getLargestAnagramGroup(String filename) {
		//Here we simply use the readFile method provided by AnagramTester
		return getLargestAnagramGroup(AnagramTester.readFile(filename));
	}
	

	/**
	 * @param stringArray
	 * @return the largest group of anagrams in the input array of words, in no particular order. 
	 * It returns an empty array if there are no anagrams in the input array.
	 */
	public static String[] getLargestAnagramGroupJavaSort(String[] stringArray) {
		
		//Creates a new array so we don't change data
		String[] anagramArray = new String[stringArray.length];
		
		//fill the array
		for(int i=0; i<stringArray.length; i++)
		{
			anagramArray[i] = stringArray[i];
		}
				
		//Define a new comparator that compares sorted strings (anagrams)
		//This is useful so the strings are not modified but are compared based on their modified form.
		Comparator<String> compareAnagrams = new Comparator<String>(){
			public int compare(String s1, String s2) {
				String sortedS1 = sort(s1).toLowerCase();
				String sortedS2 = sort(s2).toLowerCase();
				return sortedS1.compareTo(sortedS2);
			}
		};
		
		//Begin sorting the array by Strings with the above comparator
		Arrays.sort(anagramArray, compareAnagrams);
		
		//This sections is devoted to finding the largest group of anagrams in the array.
		int largestAnagramAmount = 0;
		int indexOfLargestAnagram = 0;
		int indexOfAnagram = 0;
		
		//Find largest group by comparing indices
		for (int i = 0; i < anagramArray.length; i++) {
			if(!sort(anagramArray[indexOfAnagram].toLowerCase()).equals(sort(anagramArray[i].toLowerCase()))) {
				if(i - indexOfAnagram > largestAnagramAmount) {
					largestAnagramAmount = i - indexOfAnagram;
					indexOfLargestAnagram = indexOfAnagram;
				}
				indexOfAnagram = i;
			}
		}
		
		//Here we want to create a new array that only contains the new large group of anagrams
		String[] largestAnagramArray = new String[largestAnagramAmount];
		
		//Check if there is not a group of anagrams
		if(largestAnagramAmount <= 1)
		{
			return new String[0];
		}
		
		//Add this group of anagrams into the new array
		for(int i=0; i < largestAnagramAmount; i++) {
			largestAnagramArray[i] = anagramArray[i+indexOfLargestAnagram];
		}

		return largestAnagramArray;
	}
}
