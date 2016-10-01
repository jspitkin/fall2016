package assignment05;

import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Random;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * This is a series of stress tests/timing tests for SortUtil
 * @author Anthony Iovino (u0734680) & Jacob Brown (u0583647)
 */
public class SortUtilTester {

	static Comparator<Integer> IntegerComparator;
	static Comparator<Character> CharacterComparator;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		IntegerComparator = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				
				if (o1 < o2)
					return -1;
				
				if (o1 > o2)
					return 1;
				
				if (o1.equals(o2))
				{
					return 0;
				}
				
				return -1;
				
			}
		};
		
		CharacterComparator = new Comparator<Character>() {

			@Override
			public int compare(Character o1, Character o2) {
				
				if (o1 < o2)
					return -1;
				
				if (o1 > o2)
					return 1;
				
				if (o1.equals(o2))
				{
					return 0;
				}
				
				return -1;
				
			}
		};
		
		System.out.println("Testing Begins!");
		
	}

	
	@Test
	public void test() {
		
	}

	static Comparator<Integer> integerComparator = new Comparator<Integer>() {

		@Override
		public int compare(Integer o1, Integer o2) {
			
			if (o1 < o2)
			{
				return -1;
			}
			
			if (o1 > o2) {
				return 1;				
			}
			
			if (o1==o2) {
				return 0;
			}
			
			return -1;
			
		}
	};
	
	//Merge sort: 	
		@Test
		public void mergeSortIntegers1() {
			
			ArrayList<Integer> arraytobesorted = new ArrayList<Integer>(5);
			arraytobesorted.add(4);
			arraytobesorted.add(3);
			arraytobesorted.add(6);
			arraytobesorted.add(2);
			arraytobesorted.add(5);
			
			SortUtil.mergesort(arraytobesorted,IntegerComparator);
			
			ArrayList<Integer> sortedarray = new ArrayList<Integer>(5); 
			sortedarray.add(2);
			sortedarray.add(3);
			sortedarray.add(4);
			sortedarray.add(5);
			sortedarray.add(6);
		
			assertEquals(sortedarray,arraytobesorted);
			
		}
		
		@Test
		public void mergeSortIntegers2() {
			
			ArrayList<Integer> arraytobesorted = new ArrayList<Integer>(10);
			arraytobesorted.add(-1);
			arraytobesorted.add(-18);
			arraytobesorted.add(0);
			arraytobesorted.add(84);
			arraytobesorted.add(76);
			arraytobesorted.add(-100);
			arraytobesorted.add(2);
			arraytobesorted.add(23);
			arraytobesorted.add(-9);
			arraytobesorted.add(88);
			
			SortUtil.mergesort(arraytobesorted,IntegerComparator);
			
			ArrayList<Integer> sortedarray = new ArrayList<Integer>(5); 
		
			sortedarray.add(-100);
			sortedarray.add(-18);
			sortedarray.add(-9);
			sortedarray.add(-1);
			sortedarray.add(0);
			sortedarray.add(2);
			sortedarray.add(23);
			sortedarray.add(76);
			sortedarray.add(84);
			sortedarray.add(88);

		
			assertEquals(sortedarray,arraytobesorted);
			
		}
		
		@Test 
		public void mergeSortCharacters1(){
			ArrayList<Character> arraytobesorted = new ArrayList<Character>(5);
			arraytobesorted.add('a');
			arraytobesorted.add('e');
			arraytobesorted.add('d');
			arraytobesorted.add('c');
			arraytobesorted.add('b');
			
			
			SortUtil.mergesort(arraytobesorted, CharacterComparator);
			
			
			ArrayList<Character> sortedarray = new ArrayList<Character>(5);
			sortedarray.add('a');
			sortedarray.add('b');
			sortedarray.add('c');
			sortedarray.add('d');
			sortedarray.add('e');
			
			
			assertEquals(sortedarray,arraytobesorted);
			
			
			
			
		}
		
		@Test 
		public void mergeSortCharacters2(){
			ArrayList<Character> arraytobesorted = new ArrayList<Character>(10);
			arraytobesorted.add('Z');
			arraytobesorted.add('A');
			arraytobesorted.add('E');
			arraytobesorted.add('e');
			arraytobesorted.add('a');
			arraytobesorted.add('b');
			arraytobesorted.add('r');
			arraytobesorted.add('O');
			arraytobesorted.add('p');
			arraytobesorted.add('y');
			
			SortUtil.mergesort(arraytobesorted, CharacterComparator);
			
			ArrayList<Character> sortedarray = new ArrayList<Character>(5);
			sortedarray.add('A');
			sortedarray.add('E');
			sortedarray.add('O');
			sortedarray.add('Z');
			sortedarray.add('a');
			sortedarray.add('b');
			sortedarray.add('e');
			sortedarray.add('p');
			sortedarray.add('r');
			sortedarray.add('y');
			
			
			assertEquals(sortedarray,arraytobesorted);
			
			
			
			
		}

	//Insertion sort: 
		@Test
		public void insertionSortIntegers1() {
			
			ArrayList<Integer> arraytobesorted = new ArrayList<Integer>(5);
			arraytobesorted.add(4);
			arraytobesorted.add(3);
			arraytobesorted.add(6);
			arraytobesorted.add(2);
			arraytobesorted.add(5);
			
			SortUtil.insertionSort(arraytobesorted,0,4,IntegerComparator);
			
			ArrayList<Integer> sortedarray = new ArrayList<Integer>(5); 
			sortedarray.add(2);
			sortedarray.add(3);
			sortedarray.add(4);
			sortedarray.add(5);
			sortedarray.add(6);
		
			assertEquals(sortedarray,arraytobesorted);
			
		}
		
		@Test
		public void insertionSortIntegers2() {
			
			ArrayList<Integer> arraytobesorted = new ArrayList<Integer>(10);
			arraytobesorted.add(-1);
			arraytobesorted.add(-18);
			arraytobesorted.add(0);
			arraytobesorted.add(84);
			arraytobesorted.add(76);
			arraytobesorted.add(-100);
			arraytobesorted.add(2);
			arraytobesorted.add(23);
			arraytobesorted.add(-9);
			arraytobesorted.add(88);
			
			SortUtil.insertionSort(arraytobesorted,0,9,IntegerComparator);
			
			ArrayList<Integer> sortedarray = new ArrayList<Integer>(5); 
		
			sortedarray.add(-100);
			sortedarray.add(-18);
			sortedarray.add(-9);
			sortedarray.add(-1);
			sortedarray.add(0);
			sortedarray.add(2);
			sortedarray.add(23);
			sortedarray.add(76);
			sortedarray.add(84);
			sortedarray.add(88);

		
			assertEquals(sortedarray,arraytobesorted);
			
		}
		
		@Test 
		public void insertionSortCharacters(){
			ArrayList<Character> arraytobesorted = new ArrayList<Character>(10);
			arraytobesorted.add('Z');
			arraytobesorted.add('A');
			arraytobesorted.add('E');
			arraytobesorted.add('e');
			arraytobesorted.add('a');
			arraytobesorted.add('b');
			arraytobesorted.add('r');
			arraytobesorted.add('O');
			arraytobesorted.add('p');
			arraytobesorted.add('y');
			
			SortUtil.insertionSort(arraytobesorted,0,9, CharacterComparator);
			
			ArrayList<Character> sortedarray = new ArrayList<Character>(5);
			sortedarray.add('A');
			sortedarray.add('E');
			sortedarray.add('O');
			sortedarray.add('Z');
			sortedarray.add('a');
			sortedarray.add('b');
			sortedarray.add('e');
			sortedarray.add('p');
			sortedarray.add('r');
			sortedarray.add('y');
			
			
			assertEquals(sortedarray,arraytobesorted);
			
			
			
			
		}
		
	//Quick sort: 
		@Test 
		public void quickSortIntegers1(){
			ArrayList<Integer> temp = new ArrayList<Integer>(5);
			temp.add(4);
			temp.add(3);
			temp.add(6);
			temp.add(2);
			temp.add(5);
			
			SortUtil.quicksort(temp,IntegerComparator);
			
			ArrayList<Integer> sorted = new ArrayList<Integer>(5); 
			sorted.add(2);
			sorted.add(3);
			sorted.add(4);
			sorted.add(5);
			sorted.add(6);
		
			assertEquals(sorted,temp);

		}
		
		@Test
		public void quickSortIntegers2() {
			
			ArrayList<Integer> arraytobesorted = new ArrayList<Integer>(10);
			arraytobesorted.add(-1);
			arraytobesorted.add(-18);
			arraytobesorted.add(0);
			arraytobesorted.add(84);
			arraytobesorted.add(76);
			arraytobesorted.add(-100);
			arraytobesorted.add(2);
			arraytobesorted.add(23);
			arraytobesorted.add(-9);
			arraytobesorted.add(88);
			
			SortUtil.quicksort(arraytobesorted,IntegerComparator);
			
			ArrayList<Integer> sortedarray = new ArrayList<Integer>(5); 
		
			sortedarray.add(-100);
			sortedarray.add(-18);
			sortedarray.add(-9);
			sortedarray.add(-1);
			sortedarray.add(0);
			sortedarray.add(2);
			sortedarray.add(23);
			sortedarray.add(76);
			sortedarray.add(84);
			sortedarray.add(88);

		
			assertEquals(sortedarray,arraytobesorted);
			
		}
		
		@Test 
		public void quickSortCharacters1(){
			ArrayList<Character> arraytobesorted = new ArrayList<Character>(5);
			arraytobesorted.add('a');
			arraytobesorted.add('e');
			arraytobesorted.add('d');
			arraytobesorted.add('c');
			arraytobesorted.add('b');
			
			
			SortUtil.quicksort(arraytobesorted, CharacterComparator);
			
			
			ArrayList<Character> sortedarray = new ArrayList<Character>(5);
			sortedarray.add('a');
			sortedarray.add('b');
			sortedarray.add('c');
			sortedarray.add('d');
			sortedarray.add('e');
			
			
			assertEquals(sortedarray,arraytobesorted);
			
			
			
			
		}

		@Test 
		public void quickSortCharacters2(){
			ArrayList<Character> arraytobesorted = new ArrayList<Character>(10);
			arraytobesorted.add('Z');
			arraytobesorted.add('A');
			arraytobesorted.add('E');
			arraytobesorted.add('e');
			arraytobesorted.add('a');
			arraytobesorted.add('b');
			arraytobesorted.add('r');
			arraytobesorted.add('O');
			arraytobesorted.add('p');
			arraytobesorted.add('y');
			
			SortUtil.quicksort(arraytobesorted, CharacterComparator);
			
			ArrayList<Character> sortedarray = new ArrayList<Character>(5);
			sortedarray.add('A');
			sortedarray.add('E');
			sortedarray.add('O');
			sortedarray.add('Z');
			sortedarray.add('a');
			sortedarray.add('b');
			sortedarray.add('e');
			sortedarray.add('p');
			sortedarray.add('r');
			sortedarray.add('y');
			
			
			assertEquals(sortedarray,arraytobesorted);
			
			
			
			
		}
		
	//Best Case: 
		@Test 
		public void bestCaseIntegers1(){
			ArrayList<Integer> best = new ArrayList<Integer>(5); 
			best.add(1);
			best.add(2);
			best.add(3);
			best.add(4);
			best.add(5);
		
		assertEquals(SortUtil.generateBestCase(5),best);
		
		}
		
		@Test 
		public void worstCaseIntegers(){
			ArrayList<Integer> worst = new ArrayList<Integer>(5); 
			worst.add(5);
			worst.add(4);
			worst.add(3);
			worst.add(2);
			worst.add(1);

			assertEquals(SortUtil.generateWorstCase(5),worst);
			
		}
		
	//Average Case: 	
		@Test 
		public void averageCaseIntegers(){
			ArrayList<Integer> average = new ArrayList<Integer>(5); 
			assertEquals(SortUtil.generateAverageCase(5).size(),5);//test array size
			
		}
		
		@Test 
		public void averageCaseIntegers2(){
			ArrayList<Integer> average = new ArrayList<Integer>(1); 
			assertEquals(SortUtil.generateAverageCase(1).size(),1);//test array size
			
		}
	
	// TIMING TESTS
	public static void testMergeSortFractional() {
		
		long spoolTime = System.nanoTime();
	    while (System.nanoTime() - spoolTime < 1000000000) { 
	    }
		
		int N;
		
		long loops = 100;
	    
		int numofTests = 13;
		
		Random random = new Random();
		
		for (int testNumber = 0; testNumber < numofTests; testNumber++ )
		
		{
			
			N = (int) Math.pow(2,testNumber + 5);
			
			ArrayList<Integer> testArray = SortUtil.generateAverageCase(N);
			
			
			for (double factor = .0; factor < 0.6; factor = factor + .1) {
	
				double accumTime =  0;
				
				ArrayList<Integer> cloneArray = new ArrayList<Integer>();
				
				for (Integer item : testArray) {
					
					cloneArray.add(item);
					
				}
				
				
				
				for (int iteration = 0; iteration < loops; iteration++) {
	
					SortUtil.mergeThreshold = (int) (N * factor);
	
					
					long startTime = System.nanoTime();
					
					SortUtil.mergesort(cloneArray, integerComparator);
					
					long stopTime = System.nanoTime();
				
					
					accumTime+= stopTime - startTime;
				}
				
			    double averageTime = (accumTime) / loops;
			    
			    System.out.println(N + "	" + averageTime  + "	" + factor );
			    
			}
		}
	}

	public static void testMergeSortLinear() {
		
		long spoolTime = System.nanoTime();
	    while (System.nanoTime() - spoolTime < 1000000000) { 
	    }
		
		int N;
		
		long loops = 100;
	    
		int numofTests = 13;
		
		Random random = new Random();
		
		for (int testNumber = 0; testNumber < numofTests; testNumber++ )
		
		{
			
			N = (int) Math.pow(2,testNumber + 5);
			
			ArrayList<Integer> testArray = SortUtil.generateAverageCase(N);
			
			for (double factor = 0; factor < Math.pow(2,8); factor = factor*2) { 
				
				double accumTime =  0;
				
				ArrayList<Integer> cloneArray = new ArrayList<Integer>();
				
				for (Integer item : testArray) {
					
					cloneArray.add(item);
					
				}
				

				
				for (int iteration = 0; iteration < loops; iteration++) {
	
					SortUtil.mergeThreshold = (int) (factor);
						
					long startTime = System.nanoTime();
					
					SortUtil.mergesort(cloneArray, integerComparator);
					
					long stopTime = System.nanoTime();
				
					
					accumTime+= stopTime - startTime;
				}
				
			    double averageTime = (accumTime) / loops;
			    
			    System.out.println(N + "	" + averageTime  + "	" + factor );
			    
				if (factor == 0)
				factor = 1;
			    
			}
		}
	}
	
	public static void testMergeSortNoThreshold() {
		
		long spoolTime = System.nanoTime();
	    while (System.nanoTime() - spoolTime < 1000000000) { 
	    }
		
		int N;
		
		long loops = 100;
	    
		int numofTests = 30;
		
		Random random = new Random();
		
		double accumTime =  0;
		
		for (int testNumber = 0; testNumber < numofTests; testNumber++ )
		
		{

			N = (int) Math.pow(2,testNumber + 1);
			
			ArrayList<Integer> testArray = SortUtil.generateAverageCase(N);
			
				for (int iteration = 0; iteration < loops; iteration++) {
			
					ArrayList<Integer> cloneArray = new ArrayList<Integer>();
					
					for (Integer item : testArray) {
						
						cloneArray.add(item);
						
					}
					
					long startTime = System.nanoTime();
					
					SortUtil.mergesort(cloneArray, integerComparator);
					
					long stopTime = System.nanoTime();
				
					
					accumTime+= stopTime - startTime;
				}
			    
			double averageTime = (accumTime) / loops;
	    
			System.out.println(N + "	" + averageTime  + "	");
			
		}
	}

	public static void testQuickSortStrategy1() {
		
		long spoolTime = System.nanoTime();
	    while (System.nanoTime() - spoolTime < 1000000000) { 
	    }
		
		int N;
		
		long loops = 100;
	    
		int numofTests = 30;
		
		Random random = new Random();
		
		double accumTime =  0;
		
		for (int testNumber = 5; testNumber < numofTests; testNumber++ )
		
		{

			N = (int) Math.pow(2,testNumber + 1);
			
			ArrayList<Integer> testArray = SortUtil.generateAverageCase(N);
			
				for (int iteration = 0; iteration < loops; iteration++) {
			
					ArrayList<Integer> cloneArray = new ArrayList<Integer>();
					
					for (Integer item : testArray) {
						
						cloneArray.add(item);
						
					}
					
					long startTime = System.nanoTime();
					
					SortUtil.quickSortStrategy = 1;
					SortUtil.quicksort(cloneArray, integerComparator);
					
					long stopTime = System.nanoTime();
				
					
					accumTime+= stopTime - startTime;
				}
			    
			double averageTime = (accumTime) / loops;
	    
			System.out.println(N + "	" + averageTime +  "	Average");
			
		}
	}
	
	public static void testQuickSortStrategy2() {
		
		long spoolTime = System.nanoTime();
	    while (System.nanoTime() - spoolTime < 1000000000) { 
	    }
		
		int N;
		
		long loops = 100;
	    
		int numofTests = 30;
		
		Random random = new Random();
		
		double accumTime =  0;
		
		for (int testNumber = 5; testNumber < numofTests; testNumber++ )
		
		{

			N = (int) Math.pow(2,testNumber + 1);
			
			ArrayList<Integer> testArray = SortUtil.generateAverageCase(N);
			
				for (int iteration = 0; iteration < loops; iteration++) {
			
					ArrayList<Integer> cloneArray = new ArrayList<Integer>();
					
					for (Integer item : testArray) {
						
						cloneArray.add(item);
						
					}
					
					long startTime = System.nanoTime();
					
					SortUtil.quickSortStrategy = 2;
					SortUtil.quicksort(cloneArray, integerComparator);
					
					long stopTime = System.nanoTime();
				
					
					accumTime+= stopTime - startTime;
				}
			    
			double averageTime = (accumTime) / loops;
	    
			System.out.println(N + "	" + averageTime);
			
		}
	}
	
	public static void testQuickSortStrategy3() {
		
		long spoolTime = System.nanoTime();
	    while (System.nanoTime() - spoolTime < 1000000000) { 
	    }
		
		int N;
		
		long loops = 100;
	    
		int numofTests = 30;
		
		Random random = new Random();
		
		double accumTime =  0;
		
		for (int testNumber = 5; testNumber < numofTests; testNumber++ )
		
		{

			N = (int) Math.pow(2,testNumber + 1);
			
			ArrayList<Integer> testArray = SortUtil.generateAverageCase(N);
			
				for (int iteration = 0; iteration < loops; iteration++) {
			
					ArrayList<Integer> cloneArray = new ArrayList<Integer>();
					
					for (Integer item : testArray) {
						
						cloneArray.add(item);
						
					}
					
					long startTime = System.nanoTime();
					
					SortUtil.quickSortStrategy = 3;
					SortUtil.quicksort(cloneArray, integerComparator);
					
					long stopTime = System.nanoTime();

					accumTime+= stopTime - startTime;
				}
			    
			double averageTime = (accumTime) / loops;
	    
			System.out.println(N + "	" + averageTime);
			
		}
	}
	
	public static void testMergeSortThresholdOfZero(int caseVal) {
		
		long spoolTime = System.nanoTime();
	    while (System.nanoTime() - spoolTime < 1000000000) { 
	    }
		
		int N;
		
		long loops = 25;
	    
		int numofTests = 18;
		
		Random random = new Random();
		
		for (int testNumber = 6; testNumber < numofTests; testNumber++ ) {
			
			N = (int) Math.pow(2,testNumber + 1);
			
			String typeTest = "";
			ArrayList<Integer> testArray = null;
			
			
			switch (caseVal) {
			
				case 1:
					testArray = SortUtil.generateWorstCase(N);
					typeTest = "Worst Case";
					break;
				case 2:			
					testArray = SortUtil.generateAverageCase(N);
					typeTest = "Average Case";
					break;
				case 3:			
					testArray = SortUtil.generateBestCase(N);
					typeTest = "Best Case";
					break;
				default:
					testArray = null;
					typeTest = "NULL Case";
					break;
			}
				double accumTime =  0;
				
				ArrayList<Integer> cloneArray = new ArrayList<Integer>();
				ArrayList<Integer> checkArray = new ArrayList<Integer>();
				
				for (int i = 1; i <= N; i++) {
					checkArray.add(i);
				}
				
				for (Integer item : testArray) {
					cloneArray.add(item);
					
				}
				
				
				
				for (int iteration = 0; iteration < loops; iteration++) {
	
					SortUtil.mergeThreshold = 0;
					
					long startTime = System.nanoTime();
					
					SortUtil.mergesort(cloneArray, integerComparator);
					
					long stopTime = System.nanoTime();
				
					if (!cloneArray.equals(checkArray)) {
						System.out.print("ERROR");
						break;
					}
					
					accumTime+= stopTime - startTime;
				}
				
			    double averageTime = (accumTime) / loops;
			    
			    System.out.println(N + "	" + averageTime  + "	" + typeTest);
			}
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {

	}
	
}
