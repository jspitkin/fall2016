package assignment07;

/**
 * Timing class for the LinkedListStack methods push, peek and pop.
 * @author Jared Nielson u0495206
 *
 */
public class LinkedListStackTimer {

	private static final int CAP_SIZE = 1048577;
	private static final int COUNT_TIMES = 1000;

	
	public static void main(String[] args) {
		//Optimize System.nanoTime()
		for(int index = 0; index < 100000; index++){
			System.nanoTime();
		}
		
		
		
		for(int testSize = 1024; testSize < CAP_SIZE; testSize *= 2){
			
			long pushTotalTime = 0;
			long popTotalTime = 0;
			long peekTotalTime = 0;
			
			LinkedListStack<String> toTime = new LinkedListStack<String>();
			for(int index = 0; index < testSize; index++){
				toTime.push("" + index);
			}
			
			for(int iter = 0; iter < COUNT_TIMES; iter++){
				long peekStart;
				long peekStop;
				
				long pushStart;
				long pushStop;
				
				long popStart;
				long popStop;
				
				
				peekStart = System.nanoTime();
				toTime.peek();
				peekStop = System.nanoTime();
				
				pushStart = System.nanoTime();
				toTime.push("a");
				pushStop = System.nanoTime();
				
				popStart = System.nanoTime();
				toTime.pop();
				popStop = System.nanoTime();
				
				
				peekTotalTime += peekStop - peekStart;
				pushTotalTime += pushStop - pushStart;
				popTotalTime += popStop - popStart;
			}
			
			double peekAverageTime = peekTotalTime / (double)(COUNT_TIMES);
			double pushAverageTime = pushTotalTime / (double)(COUNT_TIMES);
			double popAverageTime = popTotalTime / (double)(COUNT_TIMES);
			
			System.out.println(testSize + "\t" + peekAverageTime + "\t" + 
								pushAverageTime + "\t" + popAverageTime);
		}

	}

}