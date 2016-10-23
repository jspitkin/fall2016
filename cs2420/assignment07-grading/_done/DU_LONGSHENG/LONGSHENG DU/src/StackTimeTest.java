package assignment07;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * DoublyLinkedList
 * @author Longsheng Du
 * u1093993
 * CS 2420
 * 10/14/2016
 */


public class StackTimeTest {

	public static final int BILLION = 1_000_000_000;
	public static final int MILLION = 1_000_000;
	public static final int THOUSAND = 1_000;
	public static final int ITER_COUNT = 1_000_000;

	
	public static void main(String args[]) {		
		try 
		{
			FileWriter fwcon = new FileWriter(new File("StackTimeTest.tsv"));			
											
			for(int exp = 10; exp <= 20 ; exp++) {
				LinkedListStack<Integer> testStack = new LinkedListStack<Integer>();
				
				int size = (int) Math.pow(2, exp);
				
				for(int i=0; i<size; i++) {
					testStack.push(i);
				}

				long peekTotalTime = 0;
				long poptotalTime = 0;
				long pushtotalTime = 0;
				
				for (int iter = 0; iter < ITER_COUNT; iter++) {
					long peekstart = System.nanoTime();
					Integer in = testStack.peek();
					long peekend = System.nanoTime();
					peekTotalTime += (peekend - peekstart);
					
					long popstart = System.nanoTime();
					testStack.pop();
					long popend = System.nanoTime();
					poptotalTime += (popend - popstart);

					long pushstart = System.nanoTime();
					testStack.push(in);
					long pushend = System.nanoTime();
					pushtotalTime += (pushend - pushstart);
				}
				
				double PeekAvgTime = peekTotalTime / (double) ITER_COUNT;
				double PopAvgTime = poptotalTime / (double) ITER_COUNT;
				double PushAvgTime = pushtotalTime / (double) ITER_COUNT;
				
				System.out.println(size + "\t" + PeekAvgTime + "\t" + PopAvgTime + "\t" + PushAvgTime ); 

				fwcon.write(size + "\t" + PeekAvgTime + "\t" + PopAvgTime + "\t" + PushAvgTime + "\n"); 
				
			}

			fwcon.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
