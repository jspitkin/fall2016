package assignment06;

import java.util.ArrayList;


/**
 * 
 * @author Christian Hansen
 * @UID U0621884
 * @version 10/5/2016
 * 
 * Timing Test
 */
public class TimingTestAddFirst {
	
	private static final int CAP_SIZE = 131073;
	private static final int COUNT_TIMES = 10;
	private static final int MICRO = 1000;
	
	public static void main(String args[]){
		
		for(int nanoCallCount = 0; nanoCallCount < 1000; nanoCallCount++){
			System.nanoTime();
		}
		
		DoublyLinkedList<String> dList = new DoublyLinkedList<String>();
		ArrayList<String> aList = new ArrayList<String>();
		
		for(int iter = 1024; iter < CAP_SIZE; iter*=2){
			
			long dListTime = 0;
			long aListTime = 0;
			
			fillIn(dList, iter);
			fillIn(aList, iter);
			
			for(int count = 0; count < COUNT_TIMES; count++){
				
				long start;
				long end;
				
				start = System.nanoTime();
				dList.addFirst("1");
				end = System.nanoTime();
				dListTime += (end - start);
				
				start = System.nanoTime();
				aList.add(0, "1");
				end = System.nanoTime();
				aListTime += (end - start);
				
				dList.removeFirst();
				aList.remove(0);
			}
			
			double dListTimeAverage = ( (double) dListTime / (COUNT_TIMES*MICRO) );
			double aListTimeAverage = ( (double) aListTime / (COUNT_TIMES*MICRO) );
			
			System.out.println(""+ iter + "\t" + dListTimeAverage + "\t" + aListTimeAverage);	
		}
	}
	
	public static void fillIn(DoublyLinkedList<String> list, int index){
		for(int iter = list.size(); iter < index; iter++){
			list.add(iter, "" + iter);
		}
	}
	
	public static void fillIn(ArrayList<String> list, int index){
		for(int iter = list.size(); iter < index; iter++){
			list.add(iter, "" + iter);
		}
	}

}
