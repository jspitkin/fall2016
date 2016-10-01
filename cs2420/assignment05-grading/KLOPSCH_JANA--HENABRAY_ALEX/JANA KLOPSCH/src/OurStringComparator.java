package assignment05;

import java.util.Comparator;

/**
 * 
 * @author Alex Henabray (u0795787) & Jana Klopsch (u0854469) , last updated: 09/27/2016
 * Course: CS 2420
 * Assignment 05
 *
 */
public class OurStringComparator implements Comparator<String> {

	// Compares strings based on length.
	@Override
	public int compare(String word1, String word2) {
		
		if(word1.length() - word2.length() > 0) {
			return 1;
		}
		else if(word1.length() - word2.length() < 0) {
			return -1;
		}
		else {
			return 0;
		}
	}

}
