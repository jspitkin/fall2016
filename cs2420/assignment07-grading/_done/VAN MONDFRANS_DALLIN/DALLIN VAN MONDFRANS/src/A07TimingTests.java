/**
 * Assignment 7 - Symbol Checker
 * Name: Dallin Van Mondfrans
 * uID: u0717113
 * Date: October 19, 2016
 */

package assignment07;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class A07TimingTests {

	/**
	 * main will run the timing tests designated by the 3 helper methods.
	 * Uncomment the test that you wish to run
	 * @param args
	 */
	public static void main(String[] args) {
		
//		popTimingTest();
//		peekTimingTest();
//		pushTimingTest();
		
	}
	
	/**
	 * Timing test for the pop() method
	 */
	public static void popTimingTest() {
		
		int iterations = 1000;
		LinkedListStack<Integer> lls = new LinkedListStack<Integer>();
		double[][] data = new double[1][12];
		
		int j = 0;
		for( int size = (int) Math.pow(2, 10); size < Math.pow(2, 22); size*=2 ) {
			double sum = 0;
			for( int it = 0; it < iterations; it++ ) {
				
				
				lls.clear();
				for( int i = 0; i < size; i++ ) {
					lls.push(1);
				}
				double start = System.nanoTime();
				lls.pop();
				double end = System.nanoTime();
				
				sum += (end - start) / 1000000000;
				
			}
			data[0][j] = sum / iterations;
			j++;
		}
		
		String[] headers = {"pop()"};
		writeToCsvFileForGraphing(data, "popData.csv", headers);
		
	}
	
	/**
	 * Timing test for the peek() method
	 */
	public static void peekTimingTest() {
		
		int iterations = 1000;
		LinkedListStack<Integer> lls = new LinkedListStack<Integer>();
		double[][] data = new double[1][12];
		
		int j = 0;
		for( int size = (int) Math.pow(2, 10); size < Math.pow(2, 22); size*=2 ) {
			double sum = 0;
			for( int it = 0; it < iterations; it++ ) {
				
				
				lls.clear();
				for( int i = 0; i < size; i++ ) {
					lls.push(1);
				}
				double start = System.nanoTime();
				lls.peek();
				double end = System.nanoTime();
				
				sum += (end - start) / 1000000000;
				
			}
			data[0][j] = sum / iterations;
			j++;
		}
		
		String[] headers = {"peek()"};
		writeToCsvFileForGraphing(data, "peekData.csv", headers);
		
	}
	
	/**
	 * Timing test for the push() method
	 */
	public static void pushTimingTest() {
		
		int iterations = 1000;
		LinkedListStack<Integer> lls = new LinkedListStack<Integer>();
		double[][] data = new double[1][12];
		
		int j = 0;
		for( int size = (int) Math.pow(2, 10); size < Math.pow(2, 22); size*=2 ) {
			double sum = 0;
			for( int it = 0; it < iterations; it++ ) {
				
				
				lls.clear();
				for( int i = 0; i < size; i++ ) {
					lls.push(1);
				}
				double start = System.nanoTime();
				lls.push(1);
				double end = System.nanoTime();
				
				sum += (end - start) / 1000000000;
				
			}
			data[0][j] = sum / iterations;
			j++;
		}
		
		String[] headers = {"push()"};
		writeToCsvFileForGraphing(data, "pushData.csv", headers);
		
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
