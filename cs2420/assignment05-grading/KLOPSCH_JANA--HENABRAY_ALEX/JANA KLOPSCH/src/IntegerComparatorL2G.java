package assignment05;

import java.util.Comparator;


/**
 * 
 * @author Jana Klopsch (u0854469) & Alex Henabray (u0795787), last updated: 09/27/2016
 * Course: CS 2420
 * Assignment 05
 * This class creates a Comparator object that sorts integers from least to greatest
 */
public class IntegerComparatorL2G implements Comparator<Integer> {

	
	public IntegerComparatorL2G() {
	}
	
	@Override
	public int compare(Integer int1, Integer int2) {
		
		if(int1.intValue() - int2.intValue() > 0) {
			return 1;
		}
		else if(int1.intValue() - int2.intValue() < 0) {
			return -1;
		}
		else {
			return 0;
		}
		
	}

}
