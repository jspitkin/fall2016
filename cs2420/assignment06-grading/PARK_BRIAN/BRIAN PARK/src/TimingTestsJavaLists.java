package assignment06;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import assignment05.SortUtil;

/**
 * This is a timing class to test the methods of Java Lists
 * @author Brian Park
 *
 */
public class TimingTestsJavaLists {
	private static final int ITERATIONS = 1000000;
	
	public static void main(String[] args){
		try(FileWriter writer = new FileWriter(new File("quicksort_timing.tsv"))){

			for(int exponent = 22; exponent < 24; exponent++){	
				int length = (int) Math.pow(2,  exponent);
				double totalTime = 0;
				ArrayList<Integer> arrList = new ArrayList<Integer>();
				LinkedList<Integer> arrLinked = new LinkedList<Integer>();
				
				for(int i = 0 ; i < length; i ++){
					arrLinked.add(i);
				}
				
				for (int iter = 0; iter < ITERATIONS; iter++){	
					
					double start = System.nanoTime();
					arrLinked.addFirst(iter);
					double stop = System.nanoTime();
					
					totalTime+= stop - start;
				}
				
				double averageTime = totalTime / (double) ITERATIONS;
				System.out.print(length + "\t" + averageTime + "\n");
				writer.write(length + "\t" + averageTime + "\n");
			}
		}
		catch( IOException e){
			e.printStackTrace();
			System.err.println("IO exception: failed to write");
		}
	}
}
