package assignment05;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author Ching-Yuan Chang u0914005, Min Kim u1054673
 */

public class timing {

	public static void main(String[] args) {
		long startTime = 0;
		long stopTime = 0;
		long totalTime = 0;
		long totalTime1 = 0;
		// int wordSize = 10;
		int size = 1000;
		Comparator<Integer> cmp;

		cmp = new Comparator<Integer>() {
			public int compare(Integer d1, Integer d2) {
				return (d1).compareTo(d2);
			}
		};

		for (int j = 0; j < 10; j++) {
			ArrayList<Integer> list = new ArrayList<Integer>();
			list = SortUtil.generateWorstCase(size);

			ArrayList<Integer> tmp = new ArrayList<Integer>();
			ArrayList<Integer> sort = new ArrayList<Integer>();
			ArrayList<Integer> Qsort = new ArrayList<Integer>();
			for (int i = 0; i < list.size(); i++) {
				tmp.add(list.get(i));
//				sort.add(list.get(i));
			}

			for (int k = 0; k < 3; k++) {

				for (int i = 0; i < list.size(); i++) {
					sort.add(tmp.get(i));
					Qsort.add(tmp.get(i));
				}

				//SortUtil.setPivot(k);
				//int pivot = SortUtil.getPivot();
				//int thresh = SortUtil.getThreshold(k);
				startTime = System.nanoTime();
//				SortUtil.mergesort(sort, cmp);
				SortUtil.quicksort(sort, cmp);

				stopTime = System.nanoTime();
				totalTime += stopTime - startTime;

//				double averageTime = totalTime / 3;
//
//				System.out.println("MergeSort Best" + "\t" + size + "\t" + averageTime);
////				
//				
//				startTime = System.nanoTime();
//				SortUtil.quicksort(sort, cmp);
//				//SortUtil.quicksort(tmp, cmp);
//
//				stopTime = System.nanoTime();
//				totalTime1 += stopTime - startTime;
////
//				double averageTime = totalTime / size;

//				System.out.println("QuickSort Best" + "\t" + size + "\t" + totalTime);
				
				sort.clear();
				Qsort.clear();
			}
			
			double averageTime = totalTime / 3;
//			double averageTime1 = totalTime / 3;

//			System.out.println("MergeSort Average" + "\t" + size + "\t" + averageTime);
			
			System.out.println("QuickSort Best" + "\t" + size + "\t" + averageTime);
			size *= 2;
		}
	}

}
