package assignment04;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * Testing suite for Assignment04
 * 
 * @author Axel Kerksiek u0691509 and Nathan Milot u1063587
 * @since Sep 20, 2016
 */

@SuppressWarnings("static-access")
public class Assignment04Tests {

	AnagramUtil util;
	
	@Before
	public void setUp() throws Exception {
		util = new AnagramUtil();
	}

	@After
	public void tearDown() throws Exception {
	}

	
	@Test
	public void insertionSortTest(){
		Integer[] arr = new Integer[6];
		arr[0] = 7;
		arr[1] = 5;
		arr[2] = 6;
		arr[3] = 55;
		arr[4] = 0;
		arr[5] = 15;
		
		Comparator<Integer> c = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
			
		};
		
		util.insertionSort(arr, c);
		
		Assert.assertArrayEquals(new Integer[]{0, 5, 6, 7, 15, 55}, arr);
	}
	
	
	@Test
	public void sortTest() {
		Assert.assertEquals("act", util.sort("cat"));
	}
	
	
	@Test
	public void sortTestDuplicateLetters() {
		Assert.assertEquals("bkoos", util.sort("books"));
	}
	
	
	@Test
	public void areAnagramsTest(){
		String s1 = "cautioned";
		String s2 = "auctioned";
		String s3 = "education";
		
		Assert.assertEquals(true, util.areAnagrams(s1, s2));
		Assert.assertEquals(true, util.areAnagrams(s1, s3));
	}
	
	
	@Test
	public void areAnagramsTestEmptyStrings(){
		String s1 = "";
		String s2 = "";
		
		Assert.assertEquals(true, util.areAnagrams(s1, s2));
	}
	
	
	@Test
	public void areAnagramsTestSimilarWords(){
		String s1 = "books";
		String s2 = "looks";
		
		Assert.assertEquals(false, util.areAnagrams(s1, s2));
	}
	
	
	@Test
	public void areAnagramsTestFailDifferentSize(){
		String s1 = "cat";
		String s2 = "capital";
		
		Assert.assertEquals(false, util.areAnagrams(s1, s2));
	}
	
	
	@Test
	public void areAnagramsTestFailSameSize(){
		String s1 = "bats";
		String s2 = "arcs";
		
		Assert.assertEquals(false, util.areAnagrams(s1, s2));
	}
	
	
	@Test
	public void getLargestAnagramGroupTest(){
		String[] testArray = new String[6];
		testArray[0] = "dare";
		testArray[1] = "dear";
		testArray[2] = "read";
		testArray[3] = "act";
		testArray[4] = "tac";
		testArray[5] = "bat";

		Assert.assertEquals("[dare, dear, read]", Arrays.toString(util.getLargestAnagramGroup(testArray)));
	}
	
	
	@Test
	public void getLargestAnagramGroupTestNullArrayWithWords(){
		String[] testArray = new String[6];
		testArray[0] = null;
		testArray[1] = null;
		testArray[2] = "bat";
		testArray[3] = "tab";
		testArray[4] = null;
		testArray[5] = null;

		Assert.assertEquals("[bat, tab]", Arrays.toString(util.getLargestAnagramGroup(testArray)));
	}
	
	
	@Test
	public void getLargestAnagramGroupTestNullArray(){
		String[] testArray = new String[6];
		testArray[0] = null;
		testArray[1] = null;
		testArray[2] = null;
		testArray[3] = null;
		testArray[4] = null;
		testArray[5] = null;

		Assert.assertEquals("[]", Arrays.toString(util.getLargestAnagramGroup(testArray)));
	}
	
	
	@Test
	public void getLargestAnagramGroupTestEmptyArray(){
		String[] testArray = new String[0];

		Assert.assertEquals("[]", Arrays.toString(util.getLargestAnagramGroup(testArray)));
	}
	
	
	@Test
	public void getLargestAnagramGroupFileTest(){
		Assert.assertEquals("[apers, apres, asper, pares, parse, pears, rapes, reaps, spare, spear]",Arrays.toString(util.getLargestAnagramGroup("wordsEn.txt")));
	}
	
	
	@Test
	public void getLargestAnagramGroupBadFileTest(){
		Assert.assertEquals("[]", Arrays.toString(util.getLargestAnagramGroup("stupidFileName.txt")));
	}
	
	@Test
	public void areAnagramsSpeedTest(){
		int iterCount = 100;	
		try(FileWriter fileWriter = new FileWriter(new File("areAnagrams_experiment.tsv"))) { 
			for(int exponent = 10; exponent <= 14; exponent++) { 
				int setSize = (int) Math.pow(2, exponent);   
				
				String randomWord1 = util.randomString(setSize);
				String randomWord2 = util.randomString(setSize);
				
				long totalTime = 0;
				
				for (int iter = 0; iter < iterCount; iter++){
					long startTime = System.nanoTime();
					util.areAnagrams(randomWord1, randomWord2);
					long endTime = System.nanoTime();
					totalTime += endTime - startTime;
				}
				
				double averageTime = (totalTime / (double)iterCount)/1000000000; 
				fileWriter.write(setSize + "\t" + averageTime + "\n"); 
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getLargestAnagramGroupSpeedTest(){
		int iterCount = 100;	
		try(FileWriter fileWriter = new FileWriter(new File("ourSort_experiment.tsv"))) { 

			for(int exponent = 10; exponent <= 20; exponent++) {
				int setSize = (int) Math.pow(2, exponent);   
				
				String[] array = new String[setSize];
				for(int i = 0; i < setSize; i++){
					array[i] = util.randomString(7);	
				}
				
				long totalTime = 0;
				
				for (int iter = 0; iter < iterCount; iter++) {

					long startTime = System.nanoTime();
					util.getLargestAnagramGroup(array);
					long endTime = System.nanoTime();
					totalTime += endTime - startTime;
				}
				
				double averageTime = (totalTime / (double)iterCount)/1000000000;
				fileWriter.write(setSize + "\t" + averageTime + "\n"); 
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void getLargestAnagramGroupSpeedTestJavaSort(){
		int iterCount = 100;	
		try(FileWriter fileWriter = new FileWriter(new File("JavaSort_experiment.tsv"))) { 

			for(int exponent = 10; exponent <= 20; exponent++) { 
				int setSize = (int) Math.pow(2, exponent);   
				
				String[] array = new String[setSize];
				for(int i = 0; i < setSize; i++){
					array[i] = util.randomString(7);	
				}
				
				long totalTime = 0;
				
				for (int iter = 0; iter < iterCount; iter++) {

					long startTime = System.nanoTime();
					util.getLargestAnagramGroupJavaSort(array);
					long endTime = System.nanoTime();
					totalTime += endTime - startTime;
				}
				
				double averageTime = (totalTime / (double)iterCount)/1000000000;
				fileWriter.write(setSize + "\t" + averageTime + "\n"); 
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
