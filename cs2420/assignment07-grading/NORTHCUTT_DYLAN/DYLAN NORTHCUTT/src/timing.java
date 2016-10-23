/*
 * author: Dylan Northcutt
 */
package assignment07;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class timing {

	@SuppressWarnings("rawtypes")
	public static LinkedListStack testList = new LinkedListStack();

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		int iterCount = 10000;
	 addItems(1000);
		
		try (FileWriter fw = new FileWriter(new File("LinkedList_experiment.tsv"))) {
			for (int listSize = 1000; listSize <= 20480000; listSize *= 2) {
			
				double sum = 0;

				for (int times = 5; times < iterCount-1; times++) {
					
					
					long start = System.nanoTime();
					
					testList.peek();
					
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
		for(int i = 0; i < size; i++ ){
			testList.push(i);
		}
	}
	
	
}