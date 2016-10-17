package assignment05;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.junit.Assert;
import org.junit.BeforeClass;

/**
 * Testing class for the SortUtil class. Has tests for both mergesort and quicksort.
 * 
 * @author Jared Nielson u0495206, Christian Hansen u0621884
 *
 */
public class SortUtilTester {
	
	private ArrayList<String> stringArray, resultStringArray;
	private static StringComparator cmp;
	private static ArrayList<String> randomString, randomStringResult, duplicateArray, duplicateResult;
	private static final int RANDOM_SIZE = 10000;
	private static final int DUPLICATE_RANDOM_SIZE = 1000;
	private static final int DUPLICATE_MOD = 23;
	private static final int GENERATE_TEST_SIZE = 100;


	@BeforeClass
	public static void preClass() throws Exception{
		randomString = new ArrayList<String>();
		randomStringResult = new ArrayList<String>();
		duplicateArray = new ArrayList<String>();
		duplicateResult = new ArrayList<String>();
		cmp = new StringComparator();
		
		for(int index = 0; index < RANDOM_SIZE; index++){
			randomString.add("" + index);
			randomStringResult.add("" + index);
		}
		randomStringResult.sort(cmp);
		
		for(int index = 0; index < DUPLICATE_RANDOM_SIZE; index++){
			duplicateArray.add("" + (index % DUPLICATE_MOD));
			duplicateResult.add("" + (index % DUPLICATE_MOD));
		}
		
		duplicateResult.sort(cmp);
	}
	
	@Before
	public void setUp() throws Exception {
		stringArray = new ArrayList<String>();
		stringArray.add("2");
		stringArray.add("5");
		stringArray.add("3");
		stringArray.add("9");
		stringArray.add("1");
		stringArray.add("7");
		
		resultStringArray = new ArrayList<String>();
		resultStringArray.add("1");
		resultStringArray.add("2");
		resultStringArray.add("3");
		resultStringArray.add("5");
		resultStringArray.add("7");
		resultStringArray.add("9");
		
		Random rng = new Random(35985738394857L);
		
		for(int index = 0; index < randomString.size(); index++){
			int randomIdx = rng.nextInt(RANDOM_SIZE);
			
			String tmp = randomString.get(randomIdx);
			
			randomString.set(randomIdx, randomString.get(index));
			randomString.set(index, tmp);
		}
		
		for(int index = 0; index < duplicateArray.size(); index++){
			int randomIdx = rng.nextInt(DUPLICATE_RANDOM_SIZE);
			
			String tmp = duplicateArray.get(randomIdx);
			duplicateArray.set(randomIdx, duplicateArray.get(index));
			duplicateArray.set(index, tmp);
		}
		
	}

	/************************************************************************
	 * mergesort() tests
	 ************************************************************************/
	@Test
	public void mergesortEmpty(){
		ArrayList<String> empty = new ArrayList<String>();
		ArrayList<String> result = new ArrayList<String>();
		
		SortUtil.mergesort(empty, cmp);
		
		Assert.assertEquals(0, empty.size());
		Assert.assertArrayEquals(result.toArray(), empty.toArray());
		
	}
	
	@Test
	public void mergesortOne(){
		ArrayList<String> one = new ArrayList<String>();
		one.add("I'm alone!");
		
		ArrayList<String> result = new ArrayList<String>();
		result.add("I'm alone!");
		
		SortUtil.mergesort(one, cmp);
		Assert.assertArrayEquals(result.toArray(), one.toArray());
	}
	
	@Test
	public void mergesortTwo(){
		for(int threshold = 0; threshold < 4; threshold++){
			
			SortUtil.setInsertionThreshold(threshold);
			
			ArrayList<String> two = new ArrayList<String>();
			two.add("Me Too");
			two.add("I'm Here");
			
			ArrayList<String> result = new ArrayList<String>();
			result.add("I'm Here");
			result.add("Me Too");
			
			SortUtil.mergesort(two, cmp);
			Assert.assertArrayEquals("Fail with threshold = " + threshold, result.toArray(), two.toArray());
		}
		
		SortUtil.setInsertionThreshold(10);
		
	}
	
	@Test
	public void mergesortThree(){
		for(int threshold = 0; threshold < 5; threshold++){
			
			SortUtil.setInsertionThreshold(threshold);
			
			ArrayList<String> two = new ArrayList<String>();
			two.add("Me Too");
			two.add("I'm Here");
			two.add("And Me");
			
			ArrayList<String> result = new ArrayList<String>();
			result.add("And Me");
			result.add("I'm Here");
			result.add("Me Too");
			
			SortUtil.mergesort(two, cmp);
			Assert.assertArrayEquals("Fail with threshold = " + threshold, result.toArray(), two.toArray());
		}
		
		SortUtil.setInsertionThreshold(10);
	}
	
	@Test
	public void mergeSortDebug(){
		ArrayList<String> toSort = new ArrayList<String>();
		ArrayList<String> expected = new ArrayList<String>();
		toSort.add("4");
		toSort.add("3");
		toSort.add("2");
		toSort.add("1");
		
		expected.add("1");
		expected.add("2");
		expected.add("3");
		expected.add("4");
		
		SortUtil.mergesort(toSort, cmp);
		
		Assert.assertArrayEquals(expected.toArray(new String[]{}), toSort.toArray(new String[]{}));
		
		
	}
	
	@Test
	public void MergeSortTestBasic() {
		SortUtil.mergesort(stringArray, cmp);
		
		for(int index = 0; index < stringArray.size(); index++){
			Assert.assertEquals("Results differed at " + index, resultStringArray.get(index), 
								stringArray.get(index));
		}
	}
	
	@Test
	public void mergesortLong(){
		SortUtil.mergesort(randomString, cmp);
		
		Assert.assertArrayEquals(randomStringResult.toArray(new String[]{}),
					randomString.toArray(new String[]{}));
	}
	
	@Test
	public void mergesortDuplicates(){
		ArrayList<String> duplicateArray = new ArrayList<String>();
		ArrayList<String> duplicateResult = new ArrayList<String>();
		
		for(int index = 0; index < 24; index++){
			duplicateArray.add("" + (index % 4));
			duplicateResult.add("" + (index % 4));
		}
		
		SortUtil.mergesort(duplicateArray, cmp);
		duplicateResult.sort(cmp);
		
		Assert.assertArrayEquals(duplicateResult.toArray(new String[]{}), 
									duplicateArray.toArray(new String[]{}));
		
	}
	
	/************************************************************************
	 * quicksort() tests
	 ************************************************************************/
	@Test
	public void quicksortSimple(){
		SortUtil.quicksort(stringArray, cmp);
		
		Assert.assertArrayEquals(resultStringArray.toArray(new String[]{}), 
									stringArray.toArray(new String[]{}));
	}
	
	@Test
	public void quicksortLong(){
		SortUtil.quicksort(randomString, cmp);
		
		Assert.assertArrayEquals(randomStringResult.toArray(new String[]{}), randomString.toArray(new String[]{}));
	}
	
	@Test
	public void quicksortDuplicates(){
		ArrayList<String> duplicateArray = new ArrayList<String>();
		ArrayList<String> duplicateResult = new ArrayList<String>();
		
		for(int index = 0; index < 24; index++){
			duplicateArray.add("" + (index % 4));
			duplicateResult.add("" + (index % 4));
		}
		
		SortUtil.quicksort(duplicateArray, cmp);
		duplicateResult.sort(cmp);
		
		Assert.assertArrayEquals(duplicateResult.toArray(new String[]{}), duplicateArray.toArray(new String[]{}));
	}
	
	@Test
	public void quicksortNoElementsWithMedianOfThree(){
		
		SortUtil.setPivotChoiceMethod(SortUtil.MEDIAN_OF_THREE);
		ArrayList<String> noElements = new ArrayList<String>();
		SortUtil.quicksort(noElements, cmp);
		
		Assert.assertArrayEquals(new String[] {}, noElements.toArray(new String[]{}));
	}
	
	@Test
	public void quicksortNoElementsWithRandomPivot(){
		SortUtil.setPivotChoiceMethod(2);
		ArrayList<String> noElements = new ArrayList<String>();
		SortUtil.quicksort(noElements, cmp);
		
		Assert.assertArrayEquals(new String[] {}, noElements.toArray(new String[]{}));
	}
	
	@Test
	public void quicksortNoElementsWithMiddlePivot(){
		SortUtil.setPivotChoiceMethod(1);
		ArrayList<String> noElements = new ArrayList<String>();
		SortUtil.quicksort(noElements, cmp);
		
		Assert.assertArrayEquals(new String[] {}, noElements.toArray(new String[]{}));
	}

	@Test
	public void quicksortOneElementWithMedianOfThree(){
		
		SortUtil.setPivotChoiceMethod(SortUtil.MEDIAN_OF_THREE);
		ArrayList<String> oneElementArray = new ArrayList<String>();
		oneElementArray.add("1");
		SortUtil.quicksort(oneElementArray, cmp);
		
		
		Assert.assertArrayEquals(new String[] {"1"}, oneElementArray.toArray(new String[]{}));
	}
	
	@Test
	public void quicksortOneElementWithRandomPivot(){
		SortUtil.setPivotChoiceMethod(2);
		ArrayList<String> oneElementArray = new ArrayList<String>();
		oneElementArray.add("1");
		SortUtil.quicksort(oneElementArray, cmp);
		
		Assert.assertArrayEquals(new String[] {"1"}, oneElementArray.toArray(new String[]{}));
	}
	
	@Test
	public void quicksortOneElementWithMiddlePivot(){
		SortUtil.setPivotChoiceMethod(1);
		ArrayList<String> oneElementArray = new ArrayList<String>();
		oneElementArray.add("1");
		SortUtil.quicksort(oneElementArray, cmp);
		
		Assert.assertArrayEquals(new String[] {"1"}, oneElementArray.toArray(new String[]{}));
	}
	
	@Test
	public void quicksortTwoElementsWithMedianOfThree(){
		
		SortUtil.setPivotChoiceMethod(SortUtil.MEDIAN_OF_THREE);
		ArrayList<String> twoElementArray = new ArrayList<String>();
		twoElementArray.add("2");
		twoElementArray.add("1");
		SortUtil.quicksort(twoElementArray, cmp);
		
		Assert.assertArrayEquals(new String[] {"1", "2"}, twoElementArray.toArray(new String[]{}));
	}
	
	@Test
	public void quicksortTwoElementsWithRandomPivot(){

		SortUtil.setPivotChoiceMethod(2);
		ArrayList<String> twoElementArray = new ArrayList<String>();
		twoElementArray.add("2");
		twoElementArray.add("1");
		SortUtil.quicksort(twoElementArray, cmp);
		
		Assert.assertArrayEquals(new String[] {"1", "2"}, twoElementArray.toArray(new String[]{}));
	}
	
	@Test
	public void quicksortTwoElementsWithMiddlePivot(){
		
		SortUtil.setPivotChoiceMethod(1);
		ArrayList<String> twoElementArray = new ArrayList<String>();
		twoElementArray.add("2");
		twoElementArray.add("1");
		SortUtil.quicksort(twoElementArray, cmp);
		
		Assert.assertArrayEquals(new String[] {"1", "2"}, twoElementArray.toArray(new String[]{}));
	}
	
	@Test
	public void quicksortThreeElementsWithMedianOfThree(){
		
		SortUtil.setPivotChoiceMethod(SortUtil.MEDIAN_OF_THREE);
		ArrayList<String> threeElementArray = new ArrayList<String>();
		threeElementArray.add("3");
		threeElementArray.add("2");
		threeElementArray.add("1");
		SortUtil.quicksort(threeElementArray, cmp);
		
		Assert.assertArrayEquals(new String[] {"1", "2", "3"}, threeElementArray.toArray(new String[]{}));
	}
	
	@Test
	public void quicksortThreeElementsWithRandomPivot(){
		
		SortUtil.setPivotChoiceMethod(2);
		ArrayList<String> threeElementArray = new ArrayList<String>();
		threeElementArray.add("3");
		threeElementArray.add("2");
		threeElementArray.add("1");
		SortUtil.quicksort(threeElementArray, cmp);
		
		Assert.assertArrayEquals(new String[] {"1", "2", "3"}, threeElementArray.toArray(new String[]{}));
	}
	
	@Test
	public void quicksortThreeElementsWithMiddlePivot(){
		
		SortUtil.setPivotChoiceMethod(1);
		ArrayList<String> threeElementArray = new ArrayList<String>();
		threeElementArray.add("3");
		threeElementArray.add("2");
		threeElementArray.add("1");
		SortUtil.quicksort(threeElementArray, cmp);
		
		Assert.assertArrayEquals(new String[] {"1", "2", "3"}, threeElementArray.toArray(new String[]{}));
	}
	
	@Test
	public void quicksortLargeArrayWithMedianOfThree(){
		
		SortUtil.setPivotChoiceMethod(SortUtil.MEDIAN_OF_THREE);
		ArrayList<String> largeArray = new ArrayList<String>();
		ArrayList<String> stringArrayExpected = new ArrayList<String>();
		
		for(int index = 0; index < 100; index++){
			largeArray.add("" + index);
			stringArrayExpected.add("" + index);
		}
		
		SortUtil.quicksort(largeArray, cmp);
		
		Collections.sort(stringArrayExpected, cmp);
		
		Assert.assertArrayEquals(stringArrayExpected.toArray(new String[]{}), largeArray.toArray(new String[]{}));
	}
	
	@Test
	public void quicksortLargeArrayElementsWithRandomPivot(){
		
		SortUtil.setPivotChoiceMethod(2);
		ArrayList<String> largeArray = new ArrayList<String>();
		ArrayList<String> stringArrayExpected = new ArrayList<String>();
		
		for(int index = 0; index < 100; index++){
			largeArray.add("" + index);
			stringArrayExpected.add("" + index);
		}
		
		SortUtil.quicksort(largeArray, cmp);
		
		Collections.sort(stringArrayExpected, cmp);
		
		Assert.assertArrayEquals(stringArrayExpected.toArray(new String[]{}), largeArray.toArray(new String[]{}));
	}
	
	@Test
	public void quicksortLargeArrayElementsWithMiddlePivot(){
		
		SortUtil.setPivotChoiceMethod(1);
		ArrayList<String> largeArray = new ArrayList<String>();
		ArrayList<String> stringArrayExpected = new ArrayList<String>();
		
		for(int index = 0; index < 100; index++){
			largeArray.add("" + index);
			stringArrayExpected.add("" + index);
		}
		
		SortUtil.quicksort(largeArray, cmp);
		
		Collections.sort(stringArrayExpected, cmp);
		
		Assert.assertArrayEquals(stringArrayExpected.toArray(new String[]{}), largeArray.toArray(new String[]{}));
	}
	
	/***************************************************************************
	 * generate tests
	 ***************************************************************************/
	@Test
	public void generateBestCase(){
		for(int testSize = 0; testSize < GENERATE_TEST_SIZE; testSize++){
			ArrayList<Integer> toTest = SortUtil.generateBestCase(testSize);
			
			Assert.assertEquals(testSize, toTest.size());
			
			for(int testIdx = 0; testIdx < toTest.size(); testIdx++){
				Assert.assertEquals(testIdx + 1, (long) toTest.get(testIdx));
			}
		}
	}
	
	@Test
	public void generateAverageCase(){
		for(int testSize = 0; testSize < GENERATE_TEST_SIZE; testSize++){
			ArrayList<Integer> toTest = SortUtil.generateAverageCase(testSize);
			ArrayList<Integer> result = SortUtil.generateBestCase(testSize);
			
			Assert.assertEquals(testSize, toTest.size());
			
			SortUtil.quicksort(toTest, new IntegerComparator());
			
			Assert.assertArrayEquals(result.toArray(), toTest.toArray());	
			
		}
	}
	
	@Test
	public void generateWorstCase(){
		for(int testSize = 0; testSize < GENERATE_TEST_SIZE; testSize++){
			ArrayList<Integer> toTest = SortUtil.generateBestCase(testSize);
			
			Assert.assertEquals(testSize, toTest.size());
			
			for(int testIdx = testSize - 1; testIdx >= 0; testIdx--){
				Assert.assertEquals(testIdx + 1, (long) toTest.get(testIdx));
			}
		}
	}

}








