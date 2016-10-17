package assignment05;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import org.junit.Test;

import junit.framework.Assert;
/**
 * Tester Class for Sort Util
 *Nickolas Komarnitsky u0717854 , Shahid Bilal Razzaq u0996062
 *
 */
public class SortUtilTest {
	Comparator stringComparator = new CustomStringComparator();

	@Test
	public void testMergeSort() {
		ArrayList<String> input = new ArrayList<String>(
				Arrays.asList("A", "B", "D", "C", "F", "G", "E", "L", "Z", "P"));
		ArrayList<String> expected = new ArrayList<String>(
				Arrays.asList("A", "B", "C", "D", "E", "F", "G", "L", "P", "Z"));
		SortUtil.mergesort(input, new CustomStringComparator(), 5);
		assertEquals(expected, input);
	}

	public static String[] readFile(String filename) {
		ArrayList<String> results = new ArrayList<String>();
		try (BufferedReader input = new BufferedReader(new FileReader(filename))) {
			while (input.ready()) {
				results.add(input.readLine());
			}
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results.toArray(new String[results.size()]);
	}

	@Test
	public void testMergeSortWithWords() {
		ArrayList<String> input = new ArrayList<String>();
		ArrayList<String> expected = new ArrayList<String>();
		String[] fileArray = readFile("moderate_word_list.txt");
		for (int i = 0; i < fileArray.length; i++) {
			input.add(fileArray[i]);
			expected.add(fileArray[i]);
		}
		expected.sort(new CustomStringComparator());
		SortUtil.mergesort(input, new CustomStringComparator(), 5);
		System.out.println(expected);
		System.out.println(input);
		assertEquals(expected, input);
	}

	@Test
	public void testMergeSortWithIntegers() {
		ArrayList<Integer> input = new ArrayList<Integer>(
				Arrays.asList(6, 10, 20, 24, 30, 40, 2, 3, 1, 4, 8, 9, 20, 400, 34, 300));
		ArrayList<Integer> expected = new ArrayList<Integer>(
				Arrays.asList(1, 2, 3, 4, 6, 8, 9, 10, 20, 20, 24, 30, 34, 40, 300, 400));
		SortUtil.mergesort(input, new CustomComparator(), 5);
		assertEquals(expected, input);
	}

	@Test
	public void testQuickSort(){
		ArrayList<String> input = new ArrayList<String>(
				Arrays.asList("A", "B", "D", "C", "F", "G", "E", "L", "Z", "P"));
		ArrayList<String> expected = new ArrayList<String>(
				Arrays.asList("A", "B", "C", "D", "E", "F", "G", "L", "P", "Z"));
		SortUtil.quicksort(input, new CustomStringComparator());
		assertEquals(expected, input);
	}
	
	@Test
	public void testQuickSort2(){
		ArrayList<String> input = new ArrayList<String>(
				Arrays.asList("L", "B", "D", "C", "Z", "G", "E", "A", "F", "P"));
		ArrayList<String> expected = new ArrayList<String>(
				Arrays.asList("A", "B", "C", "D", "E", "F", "G", "L", "P", "Z"));
		SortUtil.quicksort(input, new CustomStringComparator());
		assertEquals(expected, input);
	}
	@Test
	public void testQuickSort3(){
		ArrayList<String> input = new ArrayList<String>(
				Arrays.asList("Z", "B", "D", "P", "F", "G", "E", "L", "A", "C"));
		ArrayList<String> expected = new ArrayList<String>(
				Arrays.asList("A", "B", "C", "D", "E", "F", "G", "L", "P", "Z"));
		SortUtil.quicksort(input, new CustomStringComparator());
		assertEquals(expected, input);
	}
	@Test
	public void testQuickSortWithIntegers(){
		ArrayList<Integer> input = new ArrayList<Integer>(Arrays.asList(4,5,9,20,26,0, 16,3));
		ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(0,3,4,5,9,16,20,26));
		SortUtil.quicksort(input, new CustomComparator());
		assertEquals(expected, input);
	}
	
	@Test
	public void testMedianOfThreePiviotPont(){
		ArrayList<String> input = new ArrayList<String>(
				Arrays.asList("1","2","4"));
		
		SortUtil.medianOfThreePiviot(input, 0, input.size()-1, stringComparator);
		assertEquals(1,SortUtil.medianOfThreePiviot(input, 0, input.size()-1, stringComparator));
		
	}
	
	@Test
	public void testMedianOfThreePiviotPoint2(){
		ArrayList<String> input = new ArrayList<String>(
				Arrays.asList("4","32","1","16","2"));
		
		SortUtil.medianOfThreePiviot(input, 0, input.size()-1, stringComparator);
		assertEquals(4,SortUtil.medianOfThreePiviot(input, 0, input.size()-1, new CustomComparator()));
		
	}
	@Test
	public void testMedianOfThreePiviotPoint3(){
		ArrayList<String> input = new ArrayList<String>(
				Arrays.asList("1","4","32","2","16"));
		
		SortUtil.medianOfThreePiviot(input, 0, input.size()-1, stringComparator);
		assertEquals(4,SortUtil.medianOfThreePiviot(input, 0, input.size()-1, new CustomComparator()));
		
	}
	@Test
	public void testMedianOfThreePiviotPoint4(){
		ArrayList<String> input = new ArrayList<String>(
				Arrays.asList("8","5","7","4","9"));
		
		SortUtil.medianOfThreePiviot(input, 0, input.size()-1, stringComparator);
		assertEquals(0,SortUtil.medianOfThreePiviot(input, 0, input.size()-1, new CustomComparator()));
		
	}
	@Test
	public void testMedianOfThreePiviotPoint5(){
		ArrayList<String> input = new ArrayList<String>(
				Arrays.asList("Z", "T", "R", "O", "S"));
		
		SortUtil.medianOfThreePiviot(input, 0, input.size()-1, stringComparator);
		assertEquals(4,SortUtil.medianOfThreePiviot(input, 0, input.size()-1, new CustomComparator()));
		
	}
	@Test 
	public void generateBestCaseList(){
		ArrayList<Integer> list = SortUtil.generateBestCase(10);
		ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8,9));
		assertEquals(expected, list);
	}
	@Test 
	public void generateAverageCaseList(){
		ArrayList<Integer> list = SortUtil.generateAverageCase(10);
		ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8,9));
		Assert.assertNotSame((long)expected.get(0), (long)list.get(0));
	}
	@Test 
	public void generateWorsttCaseList(){
		ArrayList<Integer> list = SortUtil.generateWorstCase(10);
		ArrayList<Integer> expected = new ArrayList<Integer>(Arrays.asList(9,8,7,6,5,4,3,2,1,0));
		assertEquals(expected, list);
	}

}
