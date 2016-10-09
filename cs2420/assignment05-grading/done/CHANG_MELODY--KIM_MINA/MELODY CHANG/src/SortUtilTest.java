package assignment05;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Ching-Yuan Chang u0914005, Min Kim u1054673
 */


public class SortUtilTest {

	Comparator<Integer> cmp;
	Comparator<String> compare;
	ArrayList<Integer> arrayInt = new ArrayList<Integer>();
	ArrayList<String> arrayString = new ArrayList<String>();

	@Before
	public void setup() {
		cmp = new Comparator<Integer>() {
			public int compare(Integer d1, Integer d2) {
				return (d1).compareTo(d2);
			}
		};		
		compare = new Comparator<String>() {
			public int compare(String d1, String d2) {
				return (d1).compareTo(d2);
			}
		};
	}

	@Test
	public void testMergeSort() {
		ArrayList<Integer> list = SortUtil.generateAverageCase(10);
		SortUtil.mergesort(list, cmp);
		assertEquals(10, list.size());
	}

	@Test
	public void testMergeSortWithBiggerArrayInt() {
		ArrayList<Integer> list = SortUtil.generateAverageCase(30);
		SortUtil.mergesort(list, cmp);
		ArrayList<Integer> fin = SortUtil.generateBestCase(30);
		assertTrue(fin.equals(list));
		ArrayList<Integer> finInt = new ArrayList<Integer>();

		arrayInt.add(3);
		arrayInt.add(5);
		arrayInt.add(2);
		finInt.add(2);
		finInt.add(3);
		finInt.add(5);
		
		SortUtil.mergesort(arrayInt, cmp);
		assertTrue(finInt.equals(arrayInt));
		
	}	
	
	@Test
	public void testGenerateWorstCase() {
		
		int size = 5;
		int changingSize = size;
		
		ArrayList<Integer> arrayWorst = SortUtil.generateWorstCase(size);
		for (int index = 0; index < size; index++)
		{
			assertSame(changingSize--, arrayWorst.get(index));
		}
		
		size = 50;
		changingSize = 50;
		arrayWorst = SortUtil.generateWorstCase(size);
		for (int index = 0; index < size; index++)
		{
			assertSame(changingSize--, arrayWorst.get(index));
		}
	}
	

	@Test
	public void swapSingleCase()
	{
		// Integers
		arrayInt.add(5);
		SortUtil.swap(arrayInt, 0, 0);
		assertSame(arrayInt.get(0), arrayInt.get(0)); 
		
		// Strings
		arrayString.add("morgana");
		SortUtil.swap(arrayString, 0, 0);
		assertSame("morgana", "morgana"); 

	}
	
	@Test
	public void pivotsValidCase()
	{
		int pivot = SortUtil.pivotRandom(arrayString, 0, 8);
		assertTrue(0 <= pivot && pivot < 8);
		
		assertEquals(7, SortUtil.pivotMiddle(arrayString, 5, 10));
		assertEquals(4, SortUtil.pivotMiddle(arrayString, 3, 5));
		assertEquals(14, SortUtil.pivotMiddle(arrayInt, 14, 14));
		
		arrayString.add("morgana");
		pivot = SortUtil.pivotMiddleOfThree(arrayString, 0, 0, compare);	
		assertEquals(arrayString.get(pivot), "morgana");

		arrayString.add("jacopo");
		arrayString.add("nellie");
		pivot = SortUtil.pivotMiddleOfThree(arrayString, 0, 2, compare);
		assertEquals(arrayString.get(pivot), "morgana");
		pivot = SortUtil.pivotMiddleOfThree(arrayString, 0, 0, compare);
		assertEquals(arrayString.get(pivot), "jacopo");
		pivot = SortUtil.pivotMiddleOfThree(arrayString, 0, 1, compare);
		assertEquals(arrayString.get(pivot), "jacopo"); 
		arrayString.add("mel");
		pivot = SortUtil.pivotMiddleOfThree(arrayString, 0, 3, compare);
		assertEquals(arrayString.get(pivot), "mel"); 
		pivot = SortUtil.pivotMiddleOfThree(arrayString, 3, 3, compare);
		assertEquals(3, pivot);
		assertEquals(arrayString.get(pivot), "morgana"); 
		
	}

	@Test
	public void quicksortSingleCase()
	{
		ArrayList<Integer> listIntSingle = SortUtil.generateAverageCase(1);
		ArrayList<Integer> listIntSingleStore = SortUtil.generateAverageCase(1);
		SortUtil.quicksort(listIntSingle, cmp);
		assertEquals(listIntSingleStore, listIntSingle);
	}

	@Test
	public void quicksortValidCaseInt()
	{
		// Permutated integer list
		ArrayList<Integer> listInt = SortUtil.generateAverageCase(10);
		SortUtil.quicksort(listInt, cmp);
		ArrayList<Integer> fin = SortUtil.generateBestCase(10);
		assertTrue(fin.equals(listInt));
		assertEquals(10, listInt.size());	
		
		// Reversed order integer list
		listInt = SortUtil.generateWorstCase(40);
		SortUtil.quicksort(listInt, cmp);
		fin = SortUtil.generateBestCase(40);
		assertTrue(fin.equals(listInt));
		assertEquals(40, listInt.size());
		
		// Already ordered integer list
		listInt = SortUtil.generateBestCase(5);
		SortUtil.quicksort(listInt, cmp);
		fin = SortUtil.generateBestCase(5);
		assertTrue(fin.equals(listInt));	
	}
	
	@Test
	public void quicksortValidCaseString()
	{
		// Already ordered string list
		ArrayList<String> listString = new ArrayList<String>();
		ArrayList<String> listStringSorted = new ArrayList<String>();
		
		listStringSorted.add("blue");
		listStringSorted.add("green");
		listStringSorted.add("pink");
		listStringSorted.add("purple");
		
		listString.add("pink");
		listString.add("blue");
		
		listString.add("green");
		listString.add("purple"); // starts with same letter as pink
		
		SortUtil.quicksort(listString, compare); 
		assertTrue(listStringSorted.equals(listString));
		
		SortUtil.quicksort(listString, compare); // sorts already sorted string
		assertTrue(listStringSorted.equals(listString));
		
		listString.add("zebra");
		listStringSorted.add("zebra");
		SortUtil.quicksort(listString, compare);
		assertTrue(listStringSorted.equals(listString));
	}
	
	@Test
	public void quicksortWithDuplicates()
	{
		ArrayList<Integer> listIntDupe = new ArrayList<Integer>();
		ArrayList<Integer> listIntDupeSorted = new ArrayList<Integer>();
		ArrayList<String> listStringDupe = new ArrayList<String>();
		ArrayList<String> listStringDupeSorted = new ArrayList<String>();

		// Integers
		
		listIntDupe.add(4);
		listIntDupe.add(3);
		listIntDupe.add(3);
		listIntDupeSorted.add(3);
		listIntDupeSorted.add(3);
		listIntDupeSorted.add(4);
		
		SortUtil.quicksort(listIntDupe, cmp);
		assertEquals(listIntDupeSorted, listIntDupe);
		
		listIntDupe.add(10);
		listIntDupe.add(4);
		listIntDupe.add(10);
		listIntDupe.add(10);
		listIntDupeSorted.add(4);
		listIntDupeSorted.add(10);
		listIntDupeSorted.add(10);
		listIntDupeSorted.add(10);
		
		SortUtil.quicksort(listIntDupe, cmp);
		assertEquals(listIntDupeSorted, listIntDupe);
		
		listIntDupeSorted.add(10);
		assertNotEquals(listIntDupeSorted, listIntDupe);


		// Strings
		
		listStringDupe.add("Mari");
		listStringDupe.add("Ohara");
		listStringDupe.add("Ohara");
		listStringDupe.add("Mari");
		listStringDupe.add("Ohari");
		listStringDupeSorted.add("Mari");
		listStringDupeSorted.add("Mari");
		listStringDupeSorted.add("Ohara");
		listStringDupeSorted.add("Ohara");
		listStringDupeSorted.add("Ohari");
		
		SortUtil.quicksort(listStringDupe, compare);
		assertEquals(listStringDupeSorted, listStringDupe);
				
		
	}
	

	
}
