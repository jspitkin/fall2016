package assignment05;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 * @author Jana Klopsch (u0854469) & Alex Henabray (u0795787), last updated: 09/27/2016
 * Course: CS 2420
 * Assignment 05
 * 
 */
public class SortUtilTests {

	ArrayList<Integer> evenIntArray;
	ArrayList<Integer> oddIntArray;
	ArrayList<Integer> emptyIntArray;
	ArrayList<Integer> singleIntArray;
	ArrayList<Integer> duplicateIntArray;
	
	ArrayList<String> bestList;
	ArrayList<String> randomList;
	ArrayList<String> worstList;
	ArrayList<String> listOfTwo;
	ArrayList<String> listOfOne;
	ArrayList<String> emptyStringArray;
	
	String empty;
	String cat;
	String dog;
	String book;
	String mouse;
	String window;
	String speaker;
	String mountain;
	String telescope;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {

		empty = "";
		cat = "cat";
		dog = "dog";
		book = "book";
		mouse = "mouse";
		window = "window";
		speaker = "speaker";
		mountain = "mountain";
		telescope = "telescope";

		// {20, -3, 21, 19, 5, 44, 134, 7, 99, 8};
		evenIntArray  = new ArrayList<Integer>(); 
		evenIntArray.add(20);
		evenIntArray.add(-3);
		evenIntArray.add(21);
		evenIntArray.add(19);
		evenIntArray.add(5);
		evenIntArray.add(44);
		evenIntArray.add(134);
		evenIntArray.add(7);
		evenIntArray.add(99);
		evenIntArray.add(8);

		// {20, -3, 21, 19, 5, 44, 134, 7, 99};
		oddIntArray = new ArrayList<Integer>(); 
		oddIntArray.add(20);
		oddIntArray.add(-3);
		oddIntArray.add(21);
		oddIntArray.add(19);
		oddIntArray.add(5);
		oddIntArray.add(44);
		oddIntArray.add(134);
		oddIntArray.add(7);
		oddIntArray.add(99);

		emptyIntArray = new ArrayList<>();

		singleIntArray = new ArrayList<Integer>();
		singleIntArray.add(5);

		// {20, -3, 21, 19, 5, 44, 134, 7, 99, 8, 8, 21, 44};
		duplicateIntArray  = new ArrayList<Integer>(); 
		duplicateIntArray.add(20);
		duplicateIntArray.add(-3);
		duplicateIntArray.add(21);
		duplicateIntArray.add(19);
		duplicateIntArray.add(5);
		duplicateIntArray.add(44);
		duplicateIntArray.add(134);
		duplicateIntArray.add(7);
		duplicateIntArray.add(99);
		duplicateIntArray.add(8);
		duplicateIntArray.add(8);
		duplicateIntArray.add(21);
		duplicateIntArray.add(44);
		
		//cat, dog, book, mouse, window, speaker, mountain, telescope
				bestList = new ArrayList<String>();
				bestList.add(cat);
				bestList.add(dog);
				bestList.add(book);
				bestList.add(mouse);
				bestList.add(window);
				bestList.add(speaker);
				bestList.add(mountain);
				bestList.add(telescope);
				
				//telescope, mountain, speaker, window, mouse, book, dog, cat
				worstList = new ArrayList<String>();
				worstList.add(telescope);
				worstList.add(mountain);
				worstList.add(speaker);
				worstList.add(window);
				worstList.add(mouse);
				worstList.add(book);
				worstList.add(dog);
				worstList.add(cat);
								
				//book, window, telescope, mountain, cat, speaker, mouse, dog		
				randomList = new ArrayList<String>();
				randomList.add(book);
				randomList.add(window);
				randomList.add(telescope);
				randomList.add(mountain);
				randomList.add(cat);
				randomList.add(speaker);
				randomList.add(mouse);
				randomList.add(dog);
				
				listOfTwo = new ArrayList<String>();
				listOfTwo.add(mouse);
				listOfTwo.add(cat);
				
				listOfOne = new ArrayList<String>();
				listOfOne.add(book);
				
				emptyStringArray = new ArrayList<String>();
				emptyStringArray.add(empty);	

	}

	@After
	public void tearDown() throws Exception {
	}

	// Tests for sorting Integer objects using mergesort
	@Test
	public void testMergeSortEvenArrayL2G() {
		SortUtil.mergesort(evenIntArray, new IntegerComparatorL2G());

		ArrayList<Integer> sortedArray = new ArrayList<Integer>();
		sortedArray.add(-3);
		sortedArray.add(5);
		sortedArray.add(7);
		sortedArray.add(8);
		sortedArray.add(19);
		sortedArray.add(20);
		sortedArray.add(21);
		sortedArray.add(44);
		sortedArray.add(99);
		sortedArray.add(134);

		Assert.assertTrue(evenIntArray.equals(sortedArray));
	}

	@Test
	public void testMergeSortEvenArrayG2L() {
		// {20, -3, 21, 19, 5, 44, 134, 7, 99, 8};
		SortUtil.mergesort(evenIntArray, new IntegerComparatorG2L());

		ArrayList<Integer> sortedArray = new ArrayList<Integer>();
		sortedArray.add(134);
		sortedArray.add(99);
		sortedArray.add(44);
		sortedArray.add(21);
		sortedArray.add(20);
		sortedArray.add(19);
		sortedArray.add(8);
		sortedArray.add(7);
		sortedArray.add(5);
		sortedArray.add(-3);

		Assert.assertTrue(evenIntArray.equals(sortedArray));
	}

	@Test
	public void testMergeSortOddArrayL2G() {
		SortUtil.mergesort(oddIntArray, new IntegerComparatorL2G());

		ArrayList<Integer> sortedArray = new ArrayList<Integer>();
		sortedArray.add(-3);
		sortedArray.add(5);
		sortedArray.add(7);
		sortedArray.add(19);
		sortedArray.add(20);
		sortedArray.add(21);
		sortedArray.add(44);
		sortedArray.add(99);
		sortedArray.add(134);
		
		Assert.assertTrue(oddIntArray.equals(sortedArray));
	}
	
	@Test
	public void testMergeSortOddArrayG2L() {
		SortUtil.mergesort(oddIntArray, new IntegerComparatorG2L());
		
		ArrayList<Integer> sortedArray = new ArrayList<Integer>();
		sortedArray.add(134);
		sortedArray.add(99);
		sortedArray.add(44);
		sortedArray.add(21);
		sortedArray.add(20);
		sortedArray.add(19);
		sortedArray.add(7);
		sortedArray.add(5);
		sortedArray.add(-3);
		
		Assert.assertTrue(oddIntArray.equals(sortedArray));
	}
	
	@Test
	public void testMergeSortDuplicateArrayL2G() {
		SortUtil.mergesort(duplicateIntArray, new IntegerComparatorL2G());
		
		ArrayList<Integer> sortedArray = new ArrayList<Integer>(); 
		sortedArray.add(-3);
		sortedArray.add(5);
		sortedArray.add(7);
		sortedArray.add(8);
		sortedArray.add(8);
		sortedArray.add(19);
		sortedArray.add(20);
		sortedArray.add(21);
		sortedArray.add(21);
		sortedArray.add(44);
		sortedArray.add(44);
		sortedArray.add(99);
		sortedArray.add(134);
		
		Assert.assertTrue(duplicateIntArray.equals(sortedArray));
	}
	
	@Test
	public void testMergeSortDuplicateArrayG2L() {
		SortUtil.mergesort(duplicateIntArray, new IntegerComparatorG2L());
		
		ArrayList<Integer> sortedArray = new ArrayList<Integer>(); 

		sortedArray.add(134);
		sortedArray.add(99);
		sortedArray.add(44);
		sortedArray.add(44);
		sortedArray.add(21);
		sortedArray.add(21);
		sortedArray.add(20);
		sortedArray.add(19);
		sortedArray.add(8);
		sortedArray.add(8);
		sortedArray.add(7);
		sortedArray.add(5);
		sortedArray.add(-3);
		
		Assert.assertTrue(duplicateIntArray.equals(sortedArray));
	}
	
	@Test
	public void testMergeSortEmptyArrayL2G() {
		SortUtil.mergesort(emptyIntArray, new IntegerComparatorL2G());
		Assert.assertTrue(emptyIntArray.equals(emptyIntArray));
	}

	@Test
	public void testMergeSortEmptyArrayG2L() {
		SortUtil.mergesort(emptyIntArray, new IntegerComparatorG2L());
		Assert.assertTrue(emptyIntArray.equals(emptyIntArray));
	}
	
	@Test
	public void testMergeSingleArrayL2G() {
		SortUtil.mergesort(singleIntArray, new IntegerComparatorL2G());
		Assert.assertTrue(singleIntArray.equals(singleIntArray) );
	}
	
	@Test
	public void testMergeSingleArrayG2L() {
		SortUtil.mergesort(singleIntArray, new IntegerComparatorG2L());
	}
	
	//Tests for sorting String Objects in mergesort
		@Test
		public void testMargeSortAverageStrings() {
			SortUtil.mergesort(randomList, new OurStringComparator());
			
			ArrayList<String> sortedArray = new ArrayList<String>();
			sortedArray.add(dog); //Before cat in random list, so will be sorted before cat
			sortedArray.add(cat);
			sortedArray.add(book);
			sortedArray.add(mouse);
			sortedArray.add(window);
			sortedArray.add(speaker);
			sortedArray.add(mountain);
			sortedArray.add(telescope);
			
			Assert.assertTrue(randomList.equals(sortedArray));
		}
		
		@Test
		public void testMergeSortSmallStrings(){
			SortUtil.mergesort(listOfTwo, new OurStringComparator());
			
			ArrayList<String> sortedSmall = new ArrayList<String>();
			sortedSmall.add(cat);
			sortedSmall.add(mouse);
			
			Assert.assertTrue(listOfTwo.equals(sortedSmall));
		}
		
		@Test
		public void testMergeSortOneString(){
			SortUtil.mergesort(listOfOne, new OurStringComparator());
			
			ArrayList<String> sortedSmall = new ArrayList<String>();
			sortedSmall.add(book);
			
			Assert.assertTrue(listOfOne.equals(sortedSmall));
		}
		
		@Test
		public void testMergeSortEmptyString(){
			SortUtil.mergesort(emptyStringArray, new OurStringComparator());
			ArrayList<String> empty = new ArrayList<String>();
			empty.add("");
			
			Assert.assertTrue(emptyStringArray.equals(empty));
		}
	
	
	//Tests for sorting Integer Objects using quicksort
	@Test
	public void testQuickSortEvenArrayL2G() {
		SortUtil.quicksort(evenIntArray, new IntegerComparatorL2G());

		ArrayList<Integer> sortedArray = new ArrayList<Integer>();
		sortedArray.add(-3);
		sortedArray.add(5);
		sortedArray.add(7);
		sortedArray.add(8);
		sortedArray.add(19);
		sortedArray.add(20);
		sortedArray.add(21);
		sortedArray.add(44);
		sortedArray.add(99);
		sortedArray.add(134);

		Assert.assertTrue(evenIntArray.equals(sortedArray));
	}

	@Test
	public void testQuickSortEvenArrayG2L() {
		SortUtil.quicksort(evenIntArray, new IntegerComparatorG2L());

		ArrayList<Integer> sortedArray = new ArrayList<Integer>();
		sortedArray.add(134);
		sortedArray.add(99);
		sortedArray.add(44);
		sortedArray.add(21);
		sortedArray.add(20);
		sortedArray.add(19);
		sortedArray.add(8);
		sortedArray.add(7);
		sortedArray.add(5);
		sortedArray.add(-3);

		Assert.assertTrue(evenIntArray.equals(sortedArray));
	}

	@Test
	public void testQuickSortOddArrayL2G() {
		SortUtil.quicksort(oddIntArray, new IntegerComparatorL2G());

		ArrayList<Integer> sortedArray = new ArrayList<Integer>();
		sortedArray.add(-3);
		sortedArray.add(5);
		sortedArray.add(7);
		sortedArray.add(19);
		sortedArray.add(20);
		sortedArray.add(21);
		sortedArray.add(44);
		sortedArray.add(99);
		sortedArray.add(134);
		
		Assert.assertTrue(oddIntArray.equals(sortedArray));
	}
	
	@Test
	public void testQuickSortOddArrayG2L() {
		SortUtil.quicksort(oddIntArray, new IntegerComparatorG2L());
		
		ArrayList<Integer> sortedArray = new ArrayList<Integer>();
		sortedArray.add(134);
		sortedArray.add(99);
		sortedArray.add(44);
		sortedArray.add(21);
		sortedArray.add(20);
		sortedArray.add(19);
		sortedArray.add(7);
		sortedArray.add(5);
		sortedArray.add(-3);
		
		Assert.assertTrue(oddIntArray.equals(sortedArray));
	}
	
	@Test
	public void testQuickSortDuplicateArrayL2G() {
		SortUtil.quicksort(duplicateIntArray, new IntegerComparatorL2G());
		
		ArrayList<Integer> sortedArray = new ArrayList<Integer>(); 
		sortedArray.add(-3);
		sortedArray.add(5);
		sortedArray.add(7);
		sortedArray.add(8);
		sortedArray.add(8);
		sortedArray.add(19);
		sortedArray.add(20);
		sortedArray.add(21);
		sortedArray.add(21);
		sortedArray.add(44);
		sortedArray.add(44);
		sortedArray.add(99);
		sortedArray.add(134);
		
		Assert.assertTrue(duplicateIntArray.equals(sortedArray));
	}
	
	@Test
	public void testQuickSortDuplicateArrayG2L() {
		SortUtil.quicksort(duplicateIntArray, new IntegerComparatorG2L());
		
		ArrayList<Integer> sortedArray = new ArrayList<Integer>(); 

		sortedArray.add(134);
		sortedArray.add(99);
		sortedArray.add(44);
		sortedArray.add(44);
		sortedArray.add(21);
		sortedArray.add(21);
		sortedArray.add(20);
		sortedArray.add(19);
		sortedArray.add(8);
		sortedArray.add(8);
		sortedArray.add(7);
		sortedArray.add(5);
		sortedArray.add(-3);
		
		Assert.assertTrue(duplicateIntArray.equals(sortedArray));
	}
	
	@Test
	public void testQuickSortEmptyArrayL2G() {
		SortUtil.quicksort(emptyIntArray, new IntegerComparatorL2G());
		Assert.assertTrue(emptyIntArray.equals(emptyIntArray));
	}

	@Test
	public void testQuickSortEmptyArrayG2L() {
		SortUtil.quicksort(emptyIntArray, new IntegerComparatorG2L());
		Assert.assertTrue(emptyIntArray.equals(emptyIntArray));
	}
	
	@Test
	public void testQuickSingleArrayL2G() {
		SortUtil.quicksort(singleIntArray, new IntegerComparatorL2G());
		Assert.assertTrue(singleIntArray.equals(singleIntArray) );
	}
	
	@Test
	public void testQuickSingleArrayG2L() {
		SortUtil.quicksort(singleIntArray, new IntegerComparatorG2L());
	}
	
	
	//Tests for sorting String Objects using quicksort
		@Test
		public void testQuickSortAverageStrings() {
			SortUtil.quicksort(randomList, new OurStringComparator());
			
			ArrayList<String> sortedArray = new ArrayList<String>();
			sortedArray.add(cat);
			sortedArray.add(dog);
			sortedArray.add(book);
			sortedArray.add(mouse);
			sortedArray.add(window);
			sortedArray.add(speaker);
			sortedArray.add(mountain);
			sortedArray.add(telescope);
			
			Assert.assertTrue(randomList.equals(sortedArray));
		}
		
		@Test
		public void testQuickSortSmallStrings(){
			SortUtil.quicksort(listOfTwo, new OurStringComparator());
			
			ArrayList<String> sortedSmall = new ArrayList<String>();
			sortedSmall.add(cat);
			sortedSmall.add(mouse);
			
			Assert.assertTrue(listOfTwo.equals(sortedSmall));
		}
		
		@Test
		public void testQuickSortOneString(){
			SortUtil.quicksort(listOfOne, new OurStringComparator());
			
			ArrayList<String> sortedSmall = new ArrayList<String>();
			sortedSmall.add(book);
			
			Assert.assertTrue(listOfOne.equals(sortedSmall));
		}
		
		@Test
		public void testQuickSortEmptyString(){
			SortUtil.quicksort(emptyStringArray, new OurStringComparator());
			ArrayList<String> empty = new ArrayList<String>();
			empty.add("");
			
			Assert.assertTrue(emptyStringArray.equals(empty));
		}
		
	
	@Test
	public void testGenerateBestCase() {
		
		ArrayList<Integer> sorted = new ArrayList<Integer>();
		for(int index = 1; index <= 10; index++) {
			sorted.add(index);
		}
		
		Assert.assertTrue(SortUtil.generateBestCase(10).equals(sorted));
		
	}
	
	@Test
	public void testGenerateAverageCaseEvenArray() {
		ArrayList<Integer> average = SortUtil.generateAverageCase(25);
		
		Assert.assertTrue(average.size() == 25);
	}

	
	@Test
	public void testWorstCaseEvenArray() {
		ArrayList<Integer> sorted = new ArrayList<Integer>();
		for(int index = 147; index >= 1; index--) {
			sorted.add(index);
		}
		
		Assert.assertTrue(SortUtil.generateWorstCase(147).equals(sorted));
		
	}
	
	
	
}

