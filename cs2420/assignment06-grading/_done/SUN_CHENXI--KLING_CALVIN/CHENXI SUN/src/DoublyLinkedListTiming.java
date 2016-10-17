package assignment06;
/**
 * @arthur Chenxi Sun
 * @Uid u0455173
 * */
import java.util.ArrayList;

import assignment05.ComparatorInteger;
import assignment05.SortUtil;

public class DoublyLinkedListTiming {
	
	
	public static void main(String[] args){
		DoublyLinkedListTiming();
	}
	
	
	public static void DoublyLinkedListTiming() {
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1_000_000_000)
			;
		ArrayList<Integer> abc2=new ArrayList<Integer>();
		for (int increase = 0; increase <= 500_000; increase += 10000) {

			long totalTime = 0;
		
			// TIME IT!
			ComparatorInteger cmp = new ComparatorInteger();
			DoublyLinkedList<Integer> abc=new DoublyLinkedList<Integer>();
			for (int i = 0; i < increase; i++) {
			abc.add(1);
			abc.add(2);
			}
			
			long start = System.nanoTime();
			for (int i = 0; i < increase; i++) {
			
				//abc2.add(0, 100);;
				abc.remove(i);
				// will increase the increase by 10 and change the random string
				// length by 10 each time through the loop
			}
			long stop = System.nanoTime();
			totalTime += stop - start;
			
			System.out.println(increase + "\t" + totalTime);	
		}
	
	}

}
