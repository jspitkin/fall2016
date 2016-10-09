package assignment06;

import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * @author Jordan Gardner u0566259
 * Timing Code use to test Assignment 6
 *
 */
public class DoublyLinkedListTiming {

	private static int ITER_COUNT = 1_000_000;
	private static Random rand;
	public static void main(String[] args) {

		ArrayList temp;
		DoublyLinkedList linkedList;
		rand=new Random();
		int random = new Integer(rand.nextInt());
//		rand.setSeed(100000000);				//can be seeded or not
		
		long startTime = System.nanoTime();


				temp = new ArrayList<>(10_000_000);//allocate enough memory
				linkedList = new DoublyLinkedList<>();
				
				int count = 0;
				long totalTime = 0;

				for (int iter = 0; iter < ITER_COUNT; iter++) {
					
					temp.add(random);		// creates lists of size itercount
					linkedList.addFirst(random);
				}
				for(int size=0;size<=10_000_000;size+=1_000_000){
					
					
					long start = System.nanoTime();
					for(int i=0;i<20_000;i++){
						temp.add(0,random);
//						linkedList.addFirst(random);		//comment out what you dont want to use
//						temp.get(0);
//						linkedList.get(0);			// these also dont have to remove at index 0 can remove random etc
//						temp.remove(0);
//						linkedList.remove(0);
					}
					long stop=System.nanoTime();
				double averageTime=(start-stop)/-200000;// can play around with time here, depending on what your looking for
//					double averageTime=(start-stop)/-1;
				System.out.println(size + "\t" + averageTime/10000);
				}

	}
	
}
