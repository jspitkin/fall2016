package assignment05;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test suite for SortUtil.java.
 * 
 * @author Chasen Chamberlain u0583257 & Rebekah Peterson u0871657
 */
public class SortUtilTester {

    ArrayList<Integer> bestCase, worstCase, avgCase, sorted, nullList, emptyList, 
    largeInput, smallInput, sortedLarge, sortedSmall;
    IntComparator cmp;
    
    @Before
    public void setUp() throws Exception {
	bestCase = SortUtil.generateBestCase(33);
	avgCase = SortUtil.generateAverageCase(33);
	worstCase = SortUtil.generateWorstCase(33);
	sorted = SortUtil.generateBestCase(33);
	
	nullList = null;
	emptyList = new ArrayList<Integer>();
	
	largeInput = SortUtil.generateAverageCase(54321);
	smallInput = SortUtil.generateAverageCase(13);
	sortedLarge = SortUtil.generateBestCase(54321);
	sortedSmall = SortUtil.generateBestCase(13);
	
	cmp = new IntComparator();
    }
    
    // generate best case, worst case, and average case lists
    @Test
    public void bestCaseTest() {
	assertEquals(33, bestCase.size());
    }
    
    @Test
    public void averageCaseTest() {
	assertEquals(33, avgCase.size());
    }
    
    @Test
    public void worstCaseTest() {
	assertEquals(33, worstCase.size());
    }
     
    // mergesort
    @Test
    public void mergeSortTestWithBest() {
	SortUtil.mergesort(bestCase, cmp);
	assertTrue(bestCase.equals(sorted));
    }
     
    @Test
    public void mergeSortTestWithAvg() {
	SortUtil.mergesort(avgCase, cmp);
	assertTrue(avgCase.equals(sorted));
    }
 
    @Test
    public void mergeSortTestWithWorst() {
	SortUtil.mergesort(worstCase, cmp);
	assertTrue(worstCase.equals(sorted));
    }
    
    @Test
    public void mergeSortTestWithNull() {
	SortUtil.mergesort(nullList, cmp);
	// no exception thrown
	assertNull(nullList);
    }
    
    @Test
    public void mergeSortTestWithEmpty() {
	SortUtil.mergesort(emptyList, cmp);
	// no exception thrown
	assertTrue(emptyList.isEmpty());
    }
    
    @Test
    public void mergeSortTestWithLarge() {
	SortUtil.mergesort(largeInput, cmp);
	assertTrue(largeInput.equals(sortedLarge));
    }
    
    @Test
    public void mergeSortTestWithSmall() {
	SortUtil.mergesort(smallInput, cmp);
	assertTrue(smallInput.equals(sortedSmall));
    }
    
    // quicksort
    @Test
    public void quickSortTestWithBest() {
	SortUtil.quicksort(bestCase, cmp);
	assertTrue(bestCase.equals(sorted));
    }
    
    @Test
    public void quickSortTestWithAvg() {
	SortUtil.quicksort(avgCase, cmp);
	assertTrue(avgCase.equals(sorted));
    }
    
    @Test
    public void quickSortTestWithWorst() {
	SortUtil.quicksort(worstCase, cmp);
	assertTrue(worstCase.equals(sorted));
    }
    
    @Test
    public void quickSortTestWithNull() {
	SortUtil.quicksort(nullList, cmp);
	// no exception thrown
	assertNull(nullList);
    }
    
    @Test
    public void quickSortTestWithEmpty() {
	SortUtil.quicksort(emptyList, cmp);
	// no exception thrown
	assertTrue(emptyList.isEmpty());
    }
    
    @Test
    public void quickSortTestWithLarge() {
	SortUtil.quicksort(largeInput, cmp);
	assertTrue(largeInput.equals(sortedLarge));
    }
    
    @Test
    public void quickSortTestWithSmall() {
	SortUtil.quicksort(smallInput, cmp);
	assertTrue(smallInput.equals(sortedSmall));
    }

}
