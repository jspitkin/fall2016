/*
 * author: Dylan Northcutt
 */
package assignment06;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class timing {

	@SuppressWarnings("rawtypes")
	public static DoublyLinkedList testList = new DoublyLinkedList();

	public static ArrayList<Object> testArr = new ArrayList<Object>();
	public static void main(String[] args) {
		
		int iterCount = 10000;
	
		
		try (FileWriter fw = new FileWriter(new File("LinkedList_experiment.tsv"))) {
			for (int listSize = 10000; listSize <= 20480000; listSize *= 2) {
			
				addItems(listSize);
				
				double sum = 0;

				for (int times = 5; times < iterCount-1; times++) {
					
					
					long start = System.nanoTime();
					
					testArr.remove(times);
					
					long stop = System.nanoTime();
					
					sum += stop - start;
				}
				sum /= iterCount;
				sum /= 1000000;
				System.out.println(listSize + "\t" + sum); // print to console
				fw.write(listSize + "\t" + sum + "\n"); // write to file.

			}
			}catch (IOException e) {
				e.printStackTrace();
			}
	}
	public static void addItems(int size){
		for(int i = testArr.size(); i < size; i++ ){
			testArr.add(i);
		}
	}
	
	
}