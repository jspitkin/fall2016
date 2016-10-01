package Assignment04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;

public class AnagramUtil {

	/**
	 * This method returns the sorted version of the input string. The sorting
	 * must be accomplished using an insertion sort.
	 * 
	 * @return Sorted string
	 */
	public static String sort(String _sort) {
		char temp;

		String result = "";
		char[] sort1 = _sort.toCharArray();
		for (int i = 1; i < _sort.length(); i++) {
			temp = sort1[i];
			int j = i - 1;
			while (j > -1 && Character.toLowerCase(temp) < Character.toLowerCase(sort1[j])) {
				sort1[j + 1] = sort1[j];
				j--;
			}
			sort1[j + 1] = temp;
		}

		for (int i = 0; i < sort1.length; i++) {
			result += sort1[i];
		}
		return result;
	}

	/**
	 * This generic method sorts the input array using an insertion sort and the
	 * input Comparator object.
	 * 
	 * @param A
	 * @param B
	 */
	public static <T> void insertionSort(T[] A, Comparator<? super T> B) {
		for (int i = 0; i < A.length; i++) {
			for (int j = i; j > 0; j++) {
				if (B.compare(A[j - 1], A[j]) > 0) {
					T C = A[j - 1];
					A[j - 1] = A[j];
					A[j] = C;
				} else {
					break;
				}
			}
		}
	}

	/**
	 * This method returns true if the two input strings are anagrams of each
	 * other, otherwise returns false.
	 * 
	 * @param a
	 * @param b
	 * @return true if two input strings are anagrams, false otherwise.
	 */
	public static boolean areAnagrams(String a, String b) {
		if (a == null && b == null) {
			return false;
		}
		
		if (sort(a).toLowerCase().equals(sort(b).toLowerCase())) {
			return true;
		}
		return false;
	}

	/**
	 * This method returns the largest group of anagrams in the input array of
	 * words, in no particular order. It returns an empty array if there are no
	 * anagrams in the input array.
	 * 
	 * @param anagrams
	 *            - input an array to run.
	 * @return the largest group of anagrams. return as many groups as possible
	 *         when the length of groups are equal.
	 */
	public static String[] getLargestAnagramGroup(String[] anagrams) {
		ArrayList<String> temp1 = new ArrayList<String>();
		ArrayList<String> temp2 = new ArrayList<String>();
		String[] anagram = new String[anagrams.length];
		for (int i = 0; i < anagrams.length; i++) {
			anagram[i] = anagrams[i];
		}
		for (int i = 0; i < anagram.length; i++) {
			if (anagram[i] == null && i < anagram.length - 1) {
				break;
			} else {
				temp2.add(anagram[i]);
				anagram[i] = null;
				if (i < anagram.length - 1) {
					for (int j = i; j < anagram.length - 1; j++) {
						if ((anagram[j + 1] != null) && areAnagrams(temp2.get(0), anagram[j + 1])) {
							temp2.add(anagram[j + 1]);
							anagram[j + 1] = null;
						}
					}
				}
				if (temp1.size() < temp2.size()) {
					temp1.addAll(temp2);
					temp2.clear();
				} else if (temp1.size() > temp2.size()) {
					temp2.clear();
				} else if (temp1.size() == temp2.size()) {
					temp1.add("------");
					temp1.addAll(temp2);
					temp2.clear();
				}
			}
		}
		String[] result = new String[temp1.size()];
		for (int i = 0; i < temp1.size(); i++) {
			result[i] = temp1.get(i);
		}
		return result;
	}

	/**
	 * Behaves the same as the previous method, but reads the list of words from
	 * the input filename. It is assumed that the file contains one word per
	 * line. If the file does not exist or is empty, the method returns an empty
	 * array because there are no anagrams.
	 */
	/**
	 * 
	 * @param filename
	 *            - input a file to run.
	 * @return the largest group of anagrams. Return as many groups as possible
	 *         when the length of groups are equal.
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
		return getLargestAnagramGroup(results.toArray(new String[results.size()]));
	}

	class Comparators implements Comparator<String> {

		@Override
		public int compare(String o1, String o2) {
			return AnagramUtil.sort(o1).toLowerCase().compareTo(AnagramUtil.sort(o2).toLowerCase());
		}
	}
}