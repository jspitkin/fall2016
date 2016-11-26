/**
 * Assignment 08 - BinarySearchTree
 * Names: Nicholas Kerr & Dallin Van Mondfrans
 * Nicholas's uID: u0125990
 * Dallin's uID: u0717113
 * Date: October 26, 2016
 */

package assignment08;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.TreeSet;

import myResources.DataToFileWriter;

public class Assignment08TimingTests {

	/**
	 * Uncomment the test that you wish to run
	 * @param args
	 */
	public static void main(String[] args) {
		
//		containsOnSortedTree();
//		containsOnRandomTree();
//		javaTreeVsBinarySearchTreeAddMethod();
//		javaTreeVsBinarySearchTreeContainsMethod();
		
	}
	
	/**
	 * This method tests the contains method for a tree whose data is
	 * inserted in sorted order. It then writes the data to a csv file.
	 */
	public static void containsOnSortedTree() {
		
		int iterations = 100;
		double[][] data = new double[1][21];
		int i = 0;
		
		for( int size = 500; size <= 10000; size += 500 ) {
			
			Integer[] sortedArray = getSortedIntegerArray(size);
			
			BinarySearchTree<Integer> sortedBST = new BinarySearchTree<Integer>();
			BinarySearchTree<Integer> randomBST = new BinarySearchTree<Integer>();
			for( int index = 0; index < size; index++ ) {
				sortedBST.add(sortedArray[index]);
			}
			
			double start;
			double end;
			double time = 0;
			
			for( int it = 0; it < iterations; it++ ) {
				
				
				for( int item = 0; item < size; item++ ) {
					
					start = System.nanoTime();
					sortedBST.contains(sortedArray[item]);
					end = System.nanoTime();
					
					time += (end - start) / 1000000000;
				}
				
			}
			
			data[0][i] = time / iterations;
			i++;
			System.out.println(i + "/" + 20);
		}
		
		String[] headers = {"contains()"};
		writeToCsvFileForGraphing(data, "containsOnSortedTree.csv", headers);
		
	}
	
	/**
	 * This method tests the contains method for a tree whose data is
	 * inserted in random order. It then writes the data to a csv file.
	 */
	public static void containsOnRandomTree() {
		
		int iterations = 100;
		double[][] data = new double[1][21];
		int i = 0;
		
		for( int size = 500; size <= 10000; size += 500 ) {
			
			Integer[] sortedArray = getSortedIntegerArray(size);
			Object[] randomArray = getRandomIntegerArray(size);
			
			BinarySearchTree<Integer> randomBST = new BinarySearchTree<Integer>();
			for( int index = 0; index < size; index++ ) {
				randomBST.add((Integer) randomArray[index]);
			}
			
			double start;
			double end;
			double time = 0;
			
			for( int it = 0; it < iterations; it++ ) {
				
				for( int item = 0; item < size; item++ ) {
					
					start = System.nanoTime();
					randomBST.contains(sortedArray[item]);
					end = System.nanoTime();
					
					time += (end - start) / 1000000000;
				}
			}
			
			data[0][i] = time / iterations;
			i++;
			System.out.println(i + "/" + 20);
		}
		
		String[] headers = {"contains()"};
		writeToCsvFileForGraphing(data, "containsOnRandomTree.csv", headers);
		
	}
	
	/**
	 * This method tests java's TreeSet vs. our BinarySearchTree add method
	 * and writes the data to a csv file.
	 */
	public static void javaTreeVsBinarySearchTreeAddMethod() {
		
		int iterations = 100;
		double[][] data = new double[2][11];
		int i = 0;
		
		for( int size = (int) Math.pow(2, 10); size <= Math.pow(2, 20); size *= 2 ) {
			
			//Integer[] sortedArray = getSortedIntegerArray(size);
			Object[] randomArray = getRandomIntegerArrayFast(size);
			
			BinarySearchTree<Integer> randomBST = new BinarySearchTree<Integer>();
			TreeSet<Integer> javaTree = new TreeSet<Integer>();
			
			double BSTstart;
			double BSTend;
			double BSTtime = 0;
			double TreeStart;
			double TreeEnd;
			double TreeTime = 0;
			
			for( int it = 0; it < iterations; it++ ) {
				
				// Time our BinarySearchTree add() method
				BSTstart = System.nanoTime();
				for( int index = 0; index < size; index++ ) {
					randomBST.add((Integer) randomArray[index]);
				}
				BSTend = System.nanoTime();
				
				BSTtime += (BSTend - BSTstart) / 1000000000;
				
				// Time java's TreeSet add() method
				TreeStart = System.nanoTime();
				for( int index = 0; index < size; index++ ) {
					javaTree.add((Integer) randomArray[index]);
				}
				TreeEnd = System.nanoTime();
				
				TreeTime += (TreeEnd - TreeStart) / 1000000000;
				
			}
			
			data[0][i] = BSTtime / iterations;
			data[1][i] = TreeTime / iterations;
			i++;
			System.out.println(i + "/" + 11);
		}
		
		String[] headers = {"BST add()", "TreeSet add()"};
		writeToCsvFileForGraphing(data, "TreeSetVsBSTaddMethod.csv", headers);
		
	}
	
	/**
	 * This method tests java's TreeSet vs. our BinarySearchTree contains method
	 * and writes the data to a csv file.
	 */
	public static void javaTreeVsBinarySearchTreeContainsMethod() {
		
		int iterations = 100;
		double[][] data = new double[2][11];
		int i = 0;
		
		for( int size = (int) Math.pow(2, 7); size <= Math.pow(2, 17); size *= 2 ) {
			
			Integer[] sortedArray = getSortedIntegerArray(size);
			Object[] randomArray = getRandomIntegerArray(size);
			
			BinarySearchTree<Integer> randomBST = new BinarySearchTree<Integer>();
			TreeSet<Integer> javaTree = new TreeSet<Integer>();
			for( int index = 0; index < size; index++ ) {
				randomBST.add((Integer) randomArray[index]);
				javaTree.add((Integer) randomArray[index]);
			}
			
			double BSTstart;
			double BSTend;
			double BSTtime = 0;
			double TreeStart;
			double TreeEnd;
			double TreeTime = 0;
			
			for( int it = 0; it < iterations; it++ ) {
				
				for( int item = 0; item < size; item++ ) {
					
					// Test contains method on BST
					BSTstart = System.nanoTime();
					randomBST.contains(sortedArray[item]);
					BSTend = System.nanoTime();
					
					BSTtime += (BSTend - BSTstart) / 1000000000;
					
					// Test contains method on TreeSet
					TreeStart = System.nanoTime();
					javaTree.contains(sortedArray[item]);
					TreeEnd = System.nanoTime();
					
					TreeTime += (TreeEnd - TreeStart) / 1000000000;
				}
			}
			
			data[0][i] = BSTtime / iterations;
			data[1][i] = TreeTime / iterations;
			i++;
			System.out.println(i + "/" + 11);
		}
		
		String[] headers = {"BST contains()", "TreeSet contains()"};
		writeToCsvFileForGraphing(data, "TreeSetVsBSTcontainsMethod.csv", headers);
		
	}
	
	/**
	 * generates an array of integers in order, starting from 
	 * zero, to the number passed
	 * @param size - the size of the array to be generated
	 * @return a sorted integer array of the specified size
	 */
	public static Integer[] getSortedIntegerArray(int size) {
		Integer[] intArray = new Integer[size];
		for( int i = 0; i < size; i++ ) {
			intArray[i] = i;
		}
		return intArray;
	}
	
	/**
	 * generates a random array of integers between zero and the number passed
	 * @param size - the size of the array to be generated
	 * @return a random integer array of the specified size
	 */
	public static Object[] getRandomIntegerArray(int size) {
		//Integer[] intArray = new Integer[size];
		ArrayList<Integer> al = new ArrayList<Integer>();
		
		Random rand = new Random();
		int temp;
		while( al.size() < size ) {
			temp = rand.nextInt(size);
			if(!al.contains(temp)) {
				al.add(temp);
			}
		}
		
		return al.toArray();
	}
	
	/**
	 * generates a random array of integers between zero and the number passed
	 * @param size - the size of the array to be generated
	 * @return a random integer array of the specified size
	 */
	public static Object[] getRandomIntegerArrayFast(int size) {
		//Integer[] intArray = intArray = getSortedIntegerArray(size);
		ArrayList<Integer> al = new ArrayList<Integer>();
		
		for( int i = 0; i < size; i++ ) {
			al.add(i);
		}
		
		Collections.shuffle(al);
		
		return al.toArray();
		
	}
	
	/**
	 * This method is written specifically to work with the dimpleChart class provided by
	 * Ryan. The data will be formatted so that a graph can be immediately generated after 
	 * adding a title and axis labels
	 * 
	 * @param data - 2D array of data. Each element in data will be written to a 
	 * 		  		separate row of the second column
	 * @param fileName - The name of the csv file that will be saved to the current
	 * 		  		java project (example --> "testData.csv")
	 * @param headers - a string array with the data set titles. The first 
	 * 		  		element in headers will correspond to the first row of data
	 */
	public static void writeToCsvFileForGraphing(double[][] data, String fileName, String[] headers) {

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
			StringBuilder sb = new StringBuilder();

			for(int i = 0; i < headers.length; i++) {
				for(int j = 0; j < data[0].length; j++) {
					sb.append(headers[i] + "," + data[i][j]);
					bw.write(sb.toString());
					bw.newLine();
					sb.delete(0, sb.length());
				}
			}

			bw.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
