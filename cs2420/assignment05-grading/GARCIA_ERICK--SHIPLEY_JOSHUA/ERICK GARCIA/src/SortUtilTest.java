/**
 * 
 * 	@author Erick Daniel Garcia

 *	@author Joshua Shipley
 */

package assignment05;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class SortUtilTest {

	Random rand = new Random(123456789);
	
	//Test to generate an Array with a sorted Array
	@Test
	public void generateBestCaseTest(){
		ArrayList<Integer> testArray = SortUtil.generateBestCase(100);
		ArrayList<Integer> checkArray = new ArrayList<Integer>();
		
		for(int i = 0; i < 100; i++){
			checkArray.add(i);
		}
		
		assertEquals(checkArray, testArray);
	}
	// Test to create an array with a reverse ordered array
	@Test
	public void generateWorstCaseTest(){
		ArrayList<Integer> testArray = SortUtil.generateWorstCase(100);
		ArrayList<Integer> checkArray = new ArrayList<Integer>();
		
		for(int i = 99; i >= 0; i--){
			checkArray.add(i);
		}
		
		assertEquals(checkArray, testArray);
	}
	//Test to Generate an array with shuffled elements
	@Test
	public void generateAverageCaseTest(){
		ArrayList<Integer> testArray = SortUtil.generateAverageCase(10);
		ArrayList<Integer> checkArray = new ArrayList<Integer>();
		//TODO: Finish Test
		
		for(int i = 0; i < 10; i++){
			checkArray.add(i);
		}
		
		assertEquals(10, testArray.size());
		assertTrue(testArray.containsAll(checkArray));
	}
	//Testing mergeSort with a best Case Array
	@Test
	public void mergeSortTestBestCase() {
		ArrayList<Integer> testArray = new ArrayList<Integer>();
		SortUtil.generateBestCase(10);
		
		ArrayList<Integer> checkArray = new ArrayList<Integer>();
		SortUtil.generateBestCase(10);
		
		SortUtil.mergesort(testArray, null);
		
		assertEquals(checkArray, testArray);
	}
	//Testing mergeSort with an average Case Array
	@Test
	public void mergeSortTestAverageCase() {
		ArrayList<Integer> testArray = new ArrayList<Integer>();
		SortUtil.generateAverageCase(10);
		
		ArrayList<Integer> checkArray = new ArrayList<Integer>();
		SortUtil.generateBestCase(10);
		
		SortUtil.mergesort(testArray, null);
		
		assertEquals(checkArray, testArray);
	}
	//Testing mergeSort with a worst Case Array
	@Test
	public void mergeSortTestWorstCase() {
		ArrayList<Integer> testArray = new ArrayList<Integer>();
		SortUtil.generateWorstCase(10);
		
		ArrayList<Integer> checkArray = new ArrayList<Integer>();
		SortUtil.generateBestCase(10);
		
		SortUtil.mergesort(testArray, null);
		
		assertEquals(checkArray, testArray);
	}
	//Testing quickSort with a best Case Array
	@Test
	public void quickSortTestBestCase() {
		ArrayList<Integer> testArray = new ArrayList<Integer>();
		SortUtil.generateBestCase(10);
		
		ArrayList<Integer> checkArray = new ArrayList<Integer>();
		SortUtil.generateBestCase(10);
		
		SortUtil.quicksort(testArray, null);
		
		assertEquals(checkArray, testArray);
	}
	//Testing quickSort with an Average Case Array
	@Test
	public void quickSortTestAverageCase() {
		ArrayList<Integer> testArray = new ArrayList<Integer>();
		SortUtil.generateAverageCase(10);
		
		ArrayList<Integer> checkArray = new ArrayList<Integer>();
		SortUtil.generateBestCase(10);
		
		SortUtil.quicksort(testArray, null);
		
		assertEquals(checkArray, testArray);
	}
	//Testing quickSort with a worst Case Array
	@Test
	public void quickSortTestWorstCase() {
		ArrayList<Integer> testArray = new ArrayList<Integer>();
		SortUtil.generateWorstCase(10);
		
		ArrayList<Integer> checkArray = new ArrayList<Integer>();
		SortUtil.generateBestCase(10);
		
		SortUtil.quicksort(testArray, null);
		
		assertEquals(checkArray, testArray);
	}

}
