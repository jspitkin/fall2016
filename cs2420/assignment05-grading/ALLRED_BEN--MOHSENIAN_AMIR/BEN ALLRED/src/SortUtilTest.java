package assignment05;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Benjamin Allred u1090524
 * @author Amir Mohsenian u0737564
 */
public class SortUtilTest {

	ArrayList<Integer> intList;
	ArrayList<String> strList;
	
	/**
	 * method from AnagramTester. Used to take words from a text file and put them into an array
	 * @param filename
	 * @return
	 */
	public String[] readFile(String filename) {
		ArrayList<String> results = new ArrayList<String>();
		try(BufferedReader input = new BufferedReader(new FileReader(filename))) {
			while(input.ready()) {
				results.add(input.readLine());
			}
			input.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return results.toArray(new String[results.size()]);
	}

	@Before
	public void setUp() throws Exception {
		
	}
	
	@Test
	/**
	 * verifies that generateBestCase generates a sorted ArrayList
	 */
	public void testGenerateBestCase() {
		intList = SortUtil.generateBestCase(100);
		ArrayList<Integer> sortedList = new ArrayList<Integer>();
		sortedList.addAll(intList);
		sortedList.sort(new IntegerComparator());
		assertTrue(intList.equals(sortedList));
		
		
	}
	
	@Test
	/**
	 * verifies that generateWorstCase generates a reverse sorted ArrayList
	 */
	public void testGenerateWorstCase() {
		intList = SortUtil.generateWorstCase(100);
		ArrayList<Integer> sortedList = new ArrayList<Integer>();
		sortedList.addAll(intList);
		sortedList.sort(new ReverseIntComparator());
		assertTrue(intList.equals(sortedList));
		
		
		
		
		
	}
	
	@Test
	/**
	 * verifies that generateAverageCase will generate the same list when used with the same seed
	 */
	public void testGenerateAverageCase() {
		SortUtil.setSeed(126545645121L);
		intList = SortUtil.generateAverageCase(100);
		ArrayList<Integer> anotherIntList = SortUtil.generateAverageCase(100);
		assertTrue(intList.equals(anotherIntList));
	}
	
	@Test
	/**
	 * tests mergeSort with a worst case ArrayList of Integers
	 */
	public void testMergeWithWorst() {
		intList = SortUtil.generateWorstCase(100);
		ArrayList<Integer> sortedList = new ArrayList<Integer>();
		sortedList.addAll(intList);
		sortedList.sort(new IntegerComparator());
		SortUtil.mergesort(intList, new IntegerComparator());
		assertTrue(intList.equals(sortedList));
	}
	
	/**
	 * tests quickSort with a worst case ArrayList of Integers
	 */
	public void testQuickWithWorst() {
		intList = SortUtil.generateWorstCase(10000);
		ArrayList<Integer> sortedList = new ArrayList<Integer>();
		sortedList.addAll(intList);
		sortedList.sort(new IntegerComparator());
		SortUtil.quicksort(intList, new IntegerComparator());
		for(int i = 0; i < intList.size(); i ++)
		{
			System.out.println(intList.get(i));
		}
		assertTrue(intList.equals(sortedList));
	}
	
	
	
	@Test
	/**
	 * tests mergeSort with Strings from text file
	 */
	public void testMergeWithStrings() {
		strList = new ArrayList<String>();
		String[] strArray = readFile("moderate_word_list.txt");
		for(int index = 0; index < strArray.length; index++)
		{
			strList.add(strArray[index]);
		}
		
		Collections.shuffle(strList);
		
		ArrayList<String> sortedList = new ArrayList<String>();
		sortedList.addAll(strList);
		sortedList.sort(new StringComparator());
		
		SortUtil.mergesort(strList, new StringComparator());
		assertTrue(strList.equals(sortedList));
	}
	
	
	@Test
	/**
	 * tests quickSort with Strings from text file
	 */
	public void testQuickWithStrings() {
		strList = new ArrayList<String>();
		String[] strArray = readFile("moderate_word_list.txt");
		for(int index = 0; index < strArray.length; index++)
		{
			strList.add(strArray[index]);
		}
		
		Collections.shuffle(strList);
		
		ArrayList<String> sortedList = new ArrayList<String>();
		sortedList.addAll(strList);
		sortedList.sort(new StringComparator());
		
		SortUtil.quicksort(strList, new StringComparator());
		assertTrue(strList.equals(sortedList));
		
	}
	
	@Test
	/**
	 * tests mergeSort with Strings from text file using a mergeThresh of 20
	 */
	public void testMergeThresh() {
		SortUtil.setMergeThreshold(20);
		strList = new ArrayList<String>();
		String[] strArray = readFile("moderate_word_list.txt");
		for(int index = 0; index < strArray.length; index++)
		{
			strList.add(strArray[index]);
		}
		
		Collections.shuffle(strList);
		
		ArrayList<String> sortedList = new ArrayList<String>();
		sortedList.addAll(strList);
		sortedList.sort(new StringComparator());
		
		SortUtil.mergesort(strList, new StringComparator());
		assertTrue(strList.equals(sortedList));
	}
	
	
	@Test
	/**
	 * tests mergeSort with Strings from text file using a mergeThresh of 20
	 */
	public void testQuickThresh() {
		SortUtil.setQuickThreshold(20);
		strList = new ArrayList<String>();
		String[] strArray = readFile("moderate_word_list.txt");
		for(int index = 0; index < strArray.length; index++)
		{
			strList.add(strArray[index]);
		}
		
		Collections.shuffle(strList);
		
		ArrayList<String> sortedList = new ArrayList<String>();
		sortedList.addAll(strList);
		sortedList.sort(new StringComparator());
		
		SortUtil.quicksort(strList, new StringComparator());
		assertTrue(strList.equals(sortedList));
		
	
	}

}
