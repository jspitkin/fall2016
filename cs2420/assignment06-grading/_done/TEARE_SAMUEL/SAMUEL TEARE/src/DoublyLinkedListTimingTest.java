package assignment06;

import java.util.ArrayList;

/**
 * This class is for testing the run-time of the DoublyLinkedList
 * 
 * @author Samuel Teare | UID: u0663592
 */
public class DoublyLinkedListTimingTest {

	private final static int MILLION = 1000_000;
	
	public static void main(String[] args) {
		
		long start = 0;
		long stop = 0;
		
		DoublyLinkedList<Integer> largeDLL = new DoublyLinkedList<Integer>();
		for(int index = 1; index <= MILLION; index++) {
			largeDLL.addLast(index);
		}
		
		ArrayList<Integer> largeAL = new ArrayList<Integer>();
		for(int index = 1; index <= MILLION; index++) {
			largeAL.add(index);
		}
		
		long total = 0;
		
		for(int index = 0; index < 21; index++) {
			start = System.nanoTime();
			largeDLL.addFirst(0);
			stop = System.nanoTime();
			total += stop - start;
			if(index == 0) {
				total = 0;
			}
		}
		System.out.println((total / 20) /(double) MILLION);
		
		System.out.println();
		
		for(int index = 0; index < 21; index++) {
			start = System.nanoTime();
			largeAL.add(0, 0);
			stop = System.nanoTime();
			total += stop - start;
			if(index == 0) {
				total = 0;
			}
		}
		System.out.println((total / 20) /(double) MILLION);
	
		largeDLL.clear();
		largeAL.clear();
		for(int counter = 1; counter <= 10; counter++) {
			for(int value = (MILLION * (counter - 1)) + 1; value < MILLION * counter; value++) {
//					largeDLL.addLast(value);
					largeAL.add(value);
			}
			for(int index = 0; index < 21; index++) {
				start = System.nanoTime();
//				largeDLL.get(largeDLL.size() / 2);
				largeAL.get(largeAL.size()/2);
				stop = System.nanoTime();
				total += stop - start;
				if(index == 0) {
					total = 0;
				}
			}
			System.out.println((total / 20) /(double) MILLION);
		}
		
		for(int counter = 1; counter <= 10; counter++) {
			for(int value = (MILLION * (counter - 1)) + 1; value < MILLION * counter; value++) {
//					largeDLL.addLast(value);
					largeAL.add(value);
			}
			for(int index = 0; index < 21; index++) {
				start = System.nanoTime();
//				largeDLL.remove(largeDLL.size() / 2);
				largeAL.remove(largeAL.size()/2);
				stop = System.nanoTime();
				total += stop - start;
				if(index == 0) {
					total = 0;
				}
			}
			System.out.println((total / 20) /(double) MILLION);
		}
	}
}
