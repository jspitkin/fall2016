package assignment08;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

/**
 * @author Qi Luo & Anthony Iovino 
 */
public class BinarySearchTreeTiming {

	public static final int BILLION = 1000_000_000;
	public static final int MILLION = 1_000_000; 

	public static void main(String[] args) {
		containTiming();
		contain2Timing();
	}

	/**
	 * Timing code for question 3.
	 */
	public static void containTiming(){
		System.out.println("---------------contain method:------------------");
		System.out.println("size"+"\t"+"\t"+"\t"+"sorted"+"\t"+"\t"+"\t"+"random");

		for(int size=1000; size<=10000; size=size+1000 ){
			BinarySearchTree<Integer> sorted = new BinarySearchTree<Integer>();
			BinarySearchTree<Integer> random = new BinarySearchTree<Integer>();

			for(int counter=0; counter < size; counter++){
				sorted.add(counter);
			}

			ArrayList<Integer> array= new ArrayList<Integer>(size);
			for(int j = 0 ; j<size ; j++){
				array.add(j);
			}
			Collections.shuffle(array);

			random.addAll(array);		

			double total1=0;
			double total2=0;
			for(int ITER_COUNT=1; ITER_COUNT <= 1000; ITER_COUNT++){
				long start1 = System.nanoTime();
				for(int item=0 ; item<size ; item++){
					sorted.contains(item);
				}
				long end1 = System.nanoTime();
				double time1 = (end1-start1) / (double) BILLION;
				total1 = total1+ time1;

				long start2 = System.nanoTime();
				for(int item=0 ; item<size ; item++){
					random.contains(item);
				}
				long end2 = System.nanoTime();
				double time2 = (end2-start2) / (double) BILLION;
				total2 = total2+ time2;
			}

			double averageTime1 = total1 / (double) 1000 ;
			double averageTime2 = total2 / (double) 1000 ;
			System.out.print(size + "\t" + "\t" +averageTime1);
			System.out.println("\t" + "\t" +averageTime2);
		}	
	}

	/**
	 * Timing code for question 4.
	 */
	public static void contain2Timing(){
		System.out.println("---------------contain method 2:------------------");
		System.out.println("size"+"\t"+"\t"+"\t"+"TreeSet"+"\t"+"\t"+"\t"+"BST");
		for(int size=1000; size<=10000; size=size+1000 ){
			TreeSet<Integer> tree = new TreeSet<Integer>();
			BinarySearchTree<Integer> binary = new BinarySearchTree<Integer>();
			ArrayList<Integer> array= new ArrayList<Integer>(size);
			for(int j = 0 ; j<size ; j++){
				array.add(j);
			}
			Collections.shuffle(array);

			tree.addAll(array);
			binary.addAll(array);
			
			double total1=0;
			double total2=0;
			for(int ITER_COUNT=1; ITER_COUNT <= 1000; ITER_COUNT++){
				long start1 = System.nanoTime();
				for(int item=0 ; item<size ; item++){
					tree.contains(item);
				}
				long end1 = System.nanoTime();
				double time1 = (end1-start1) / (double) BILLION;
				total1 = total1+ time1;

				long start2 = System.nanoTime();
				for(int item=0 ; item<size ; item++){
					binary.contains(item);
				}
				long end2 = System.nanoTime();
				double time2 = (end2-start2) / (double) BILLION;
				total2 = total2+ time2;
			}

			double averageTime1 = total1 / (double) 1000;
			double averageTime2 = total2 / (double) 1000;
			System.out.print(size + "\t" + "\t" +averageTime1);
			System.out.println("\t" + "\t" +averageTime2);
		}
	}
}
