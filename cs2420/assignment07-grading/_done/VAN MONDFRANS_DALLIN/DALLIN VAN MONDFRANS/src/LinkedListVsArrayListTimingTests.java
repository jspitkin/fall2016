/**
 * Assignment 6 - DoublyLinkedList
 * @author Dallin Van Mondfrans
 * uID: u0717113
 * Date: October 5, 2016
 */

package assignment06;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class LinkedListVsArrayListTimingTests {

	/**
	 * Uncomment method to run the specified test
	 * @param args
	 */
	public static void main(String[] args) {
		
		//linkedListAddFirstVsArrayListAdd();
		//linkedListGetVsArrayListGet();
		//linkedListRemoveVsArrayListRemove();

	}
	
	public static void linkedListAddFirstVsArrayListAdd() {
		ArrayList<Integer> al = new ArrayList<Integer>();
		DoublyLinkedList<Integer> ll = new DoublyLinkedList<Integer>();
		int iterations = 500;
		double[] alTimes = new double[11];
		double[] llTimes = new double[11];
		double start;
		double end;
		double time;
		
		int index = 0;
		for(int size = (int) Math.pow(2, 10); size <= Math.pow(2, 20); size *= 2) {
			double alSum = 0;
			double llSum = 0;
			for(int it = 0; it < iterations; it++) {
				// Reset arrays according size
				al = new ArrayList<Integer>();
				ll = new DoublyLinkedList<Integer>();
				for(int i = 0; i < size; i++) {
					al.add(i);
					ll.addLast(i);
				}
				// Time ArrayList 
				start = System.nanoTime();
				al.add(0, 1);
				end = System.nanoTime();
				time = (end - start) / 1000000000;
				alSum += time;
				// Time LinkedList
				start = System.nanoTime();
				ll.addFirst(1);
				end = System.nanoTime();
				time = (end - start) / 1000000000;
				llSum += time;
			}
			alTimes[index] = alSum / iterations;
			llTimes[index] = llSum / iterations;
			index++;
			System.out.println("Finished iteration " + index);
		}
		double[][] data = {alTimes, llTimes};
		String[] headers = {"ArrayList.add()", "LinkedList.addFirst()"};
		writeToCsvFileForGraphing(data, "addFirstVsAdd.csv", headers);
	}
	
	public static void linkedListGetVsArrayListGet() {
		ArrayList<Integer> al = new ArrayList<Integer>();
		DoublyLinkedList<Integer> ll = new DoublyLinkedList<Integer>();
		int iterations = 500;
		double[] alTimes = new double[11];
		double[] llTimes = new double[11];
		double start;
		double end;
		double time;
		Random rand = new Random();
		
		int index = 0;
		for(int size = (int) Math.pow(2, 10); size <= Math.pow(2, 20); size *= 2) {
			double alSum = 0;
			double llSum = 0;
			for(int it = 0; it < iterations; it++) {
				// Reset arrays according size
				al = new ArrayList<Integer>();
				ll = new DoublyLinkedList<Integer>();
				for(int i = 0; i < size; i++) {
					al.add(i);
					ll.addLast(i);
				}
				int randInt = rand.nextInt(size);
				// Time ArrayList 
				start = System.nanoTime();
				al.get(randInt);
				end = System.nanoTime();
				time = (end - start) / 1000000000;
				alSum += time;
				// Time LinkedList
				start = System.nanoTime();
				ll.get(randInt);
				end = System.nanoTime();
				time = (end - start) / 1000000000;
				llSum += time;
			}
			alTimes[index] = alSum / iterations;
			llTimes[index] = llSum / iterations;
			index++;
			System.out.println("Finished iteration " + index);
		}
		double[][] data = {alTimes, llTimes};
		String[] headers = {"ArrayList.get()", "LinkedList.get()"};
		writeToCsvFileForGraphing(data, "getVsGet.csv", headers);
	}
	
	public static void linkedListRemoveVsArrayListRemove() {
		ArrayList<Integer> al = new ArrayList<Integer>();
		DoublyLinkedList<Integer> ll = new DoublyLinkedList<Integer>();
		int iterations = 500;
		double[] alTimes = new double[11];
		double[] llTimes = new double[11];
		double start;
		double end;
		double time;
		Random rand = new Random();
		
		int index = 0;
		for(int size = (int) Math.pow(2, 10); size <= Math.pow(2, 20); size *= 2) {
			double alSum = 0;
			double llSum = 0;
			for(int it = 0; it < iterations; it++) {
				// Reset arrays according size
				al = new ArrayList<Integer>();
				ll = new DoublyLinkedList<Integer>();
				for(int i = 0; i < size; i++) {
					al.add(i);
					ll.addLast(i);
				}
				int randInt = rand.nextInt(size);
				// Time ArrayList 
				start = System.nanoTime();
				al.remove(randInt);
				end = System.nanoTime();
				time = (end - start) / 1000000000;
				alSum += time;
				// Time LinkedList
				start = System.nanoTime();
				ll.remove(randInt);
				end = System.nanoTime();
				time = (end - start) / 1000000000;
				llSum += time;
			}
			alTimes[index] = alSum / iterations;
			llTimes[index] = llSum / iterations;
			index++;
			System.out.println("Finished iteration " + index);
		}
		double[][] data = {alTimes, llTimes};
		String[] headers = {"ArrayList.remove()", "LinkedList.remove()"};
		writeToCsvFileForGraphing(data, "removeVsRemove.csv", headers);
	}
	
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
