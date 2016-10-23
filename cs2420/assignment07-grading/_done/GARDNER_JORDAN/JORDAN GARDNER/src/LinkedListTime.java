package assignment07;

import java.util.Random;
/**
 * 
 * @author Jordan Gardner u0566259
 *
 */
public class LinkedListTime {

		
		private static Random rand;
		public static void main(String[] args) {

				for(int size=10_000;size<=110_001;size+=10_000){
					
					int ITER_COUNT=size;
					LinkedListStack<Integer> linkedList;
					rand=new Random();
					int random = new Integer(rand.nextInt());
					rand.setSeed(System.currentTimeMillis());
					linkedList = new LinkedListStack<>();
					for (int iter = 0; iter < ITER_COUNT; iter++) {
						
						linkedList.push(random);
					}
					
						
						
						long start = System.nanoTime();
						for(int i=0;i<5_000;i++){
//							linkedList.peek();
//							linkedList.pop();
							linkedList.push(1);
						}
						long stop=System.nanoTime();
					double averageTime=(stop-start)/5_000;// can play around with time here, depending on what your looking for
//						double averageTime=(start-stop)/-1;
					System.out.println(size + "\t" + averageTime/1000);
					
				}

		}
		
	}


