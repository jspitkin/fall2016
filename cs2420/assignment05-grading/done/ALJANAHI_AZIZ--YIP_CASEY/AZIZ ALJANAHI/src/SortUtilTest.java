package assignment05;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
/**
 * * 
 * @author Ho Lam Yip u1025709
 * @author Abdulaziz Aljanahi u0901606
 */


public class SortUtilTest {

	public static Comparator<Integer> comp;
	public static ArrayList<Integer> arr = new ArrayList<Integer>();
	public static ArrayList<Integer> compare = new ArrayList<Integer>();

	@BeforeClass
	public static void comparators() {

		comp = new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if (o1 == null || o2 == null) {

				}

				return o1.compareTo(o2);
			}

		};
	}

	@Test
	public void testMergesort1() { // normal sort
		arr.clear();
		compare.clear();
		arr.addAll(Arrays.asList(21, 1, 2, 2, 5, 1, 8, 13, 3));
		compare.addAll(Arrays.asList(1, 1, 2, 2, 3, 5, 8, 13, 21));
		SortUtil.mergesort(arr, comp);
		assertEquals(compare, arr);

	}

	@Test
	public void testMergesort2() { // empty
		arr.clear();
		SortUtil.mergesort(arr, comp);
		assertEquals("[]", arr.toString());
	}

	@Test
	public void testMergesort3() { // big numbers
		arr.clear();
		compare.clear();
		popArrDescend(arr);
		popArr(compare);
		SortUtil.mergesort(arr, comp);
		assertEquals(arr, compare);
	}

	@Test
	public void testMergesort4() { // one element
		arr.clear();
		arr.add(1);
		compare.clear();
		compare.add(1);
		SortUtil.mergesort(arr, comp);
		assertEquals(arr, compare);
	}

	@Test
	public void testMergesort5() { // duplicates
		arr.clear();
		popDups(arr);
		compare.clear();
		popDups(compare);
		SortUtil.mergesort(arr, comp);
		assertEquals(arr, compare);
	}

	@Test
	public void testMergesort6() { // two elements to see that merge and sort
									// don't break if they have very few
									// elements to work with
		arr.clear();
		arr.add(3);
		arr.add(1);
		compare.clear();
		compare.add(1);
		compare.add(3);
		SortUtil.mergesort(arr, comp);
		assertEquals(arr, compare);
	}

	@Test
	public void testQuicksort1() {// normal sort
		arr.clear();
		compare.clear();
		arr.addAll(Arrays.asList(21, 1, 2, 2, 5, 1, 8, 13, 3));
		compare.addAll(Arrays.asList(1, 1, 2, 2, 3, 5, 8, 13, 21));
		SortUtil.quicksort(arr, comp);
		assertEquals(compare, arr);

	}

	@Test
	public void testQuicksort2() {// empty
		arr.clear();
		SortUtil.quicksort(arr, comp);
		assertEquals("[]", arr.toString());
	}

	@Test
	public void testQuicksort3() {// big lists
		arr.clear();
		compare.clear();
		popArrDescend(arr);
		popArr(compare);
		SortUtil.quicksort(arr, comp);
		assertEquals(arr, compare);
	}

	@Test
	public void testQuicksort4() { // one element
		arr.clear();
		arr.add(1);
		compare.clear();
		compare.add(1);
		SortUtil.quicksort(arr, comp);
		assertEquals(arr, compare);

	}

	@Test
	public void testQuicksort5() {// duplicates
		arr.clear();
		popDups(arr);
		compare.clear();
		popDups(compare);
		SortUtil.quicksort(arr, comp);
		assertEquals(arr, compare);

	}

	@Test
	public void testQuicksort6() { // two elements to see that sort does not
									// break if they have very few elements to
									// work with
		arr.clear();
		arr.add(3);
		arr.add(1);
		compare.clear();
		compare.add(1);
		compare.add(3);
		SortUtil.quicksort(arr, comp);
		assertEquals(arr, compare);

	}

	@Test
	public void testGenerateBestCase1() {
		compare.clear();
		popArr(compare);
		assertEquals(compare, SortUtil.generateBestCase(100000));

	}

	@Test
	public void testGenerateBestCase2() {// empty
		compare.clear();
		popArr(compare);
		assertEquals("[]", SortUtil.generateBestCase(0).toString());

	}

	@Test
	public void testGenerateBestCase3() {// one element
		compare.clear();
		compare.add(1);
		assertEquals(compare, SortUtil.generateBestCase(1));

	}

	@Test
	public void testGenerateAverageCase1() { // makes sure that list is random
												// everytime
		assertFalse(SortUtil.generateAverageCase(10) == SortUtil.generateAverageCase(10));
	}

	@Test
	public void testGenerateAverageCase2() { // empty
		assertEquals("[]", SortUtil.generateAverageCase(0).toString());
	}

	@Test
	public void testGenerateAverageCase3() {// one element
		compare.clear();
		compare.add(1);
		assertEquals(1, SortUtil.generateAverageCase(1).size());

	}

	@Test
	public void testGenerateWorstCase1() {
		compare.clear();
		popArrDescend(compare);
		assertEquals(compare, SortUtil.generateWorstCase(100000));
	}

	@Test
	public void testGenerateWorstCase2() {
		assertEquals("[]", SortUtil.generateWorstCase(0).toString());
	}

	@Test
	public void testGenerateWorstCase3() {
		compare.clear();
		compare.add(1);
		assertEquals(compare, SortUtil.generateWorstCase(1));

	}

	/**
	 * populates an array in ascending order
	 * 
	 * @param arr
	 *            - array
	 */
	private static void popArr(ArrayList<Integer> arr) {
		for (int i = 1; i <= 100000; i++) {
			arr.add(i);

		}
	}

	/**
	 * populates an array in descending order
	 * 
	 * @param arr
	 *            - array
	 */
	private static void popArrDescend(ArrayList<Integer> arr) {
		for (int i = 100000; i >= 1; i--) {
			arr.add(i);

		}
	}

	/**
	 * populates an array with duplicates
	 * 
	 * @param arr
	 *            - array
	 */
	private static void popDups(ArrayList<Integer> arr) {
		for (int i = 1; i <= 100000; i++) {
			arr.add(1);

		}

	}

}
