package assignment11;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

/**
 * This class is to test timing for add, findMin, and deleteMin methods
 * 
 * @author Jiwon Nam
 *
 */
public class PriorityQueueTimer {

	public static void main(String[] args) {
		// integer comparator
		Comparator<Integer> cmp = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o1.compareTo(o2);
			}
		};
		addTimer(cmp);
		findMinTimer(cmp);
		deleteMinTimer(cmp);
	}
	
	/**
	 * add timing test method
	 * 
	 * @param cmp, integer comparator
	 */
	public static void addTimer(Comparator<Integer> cmp) {
		long start, end;
		int loop = 100;
		int N = 10000;
		
		System.out.println("add() method size 10,000 to 100,000");
		for (int j = N; j < N * 10; j += N) {
			long total = 0;
			for (int i = 0; i < loop; i++) {
//				ArrayList<Integer> ranList = generateRanNum(j);
//				ArrayList<Integer> ascendList = generateAscending(j);
				ArrayList<Integer> descendList = generateDescending(j);
				PriorityQueue<Integer> pq = new PriorityQueue<Integer>(cmp);
				for (int k = 0; k < j; k++) {
					start = System.nanoTime();
//					pq.add(ranList.get(k));		// average case
//					pq.add(ascendList.get(k));	// best case
					pq.add(descendList.get(k));	// worst case
					end = System.nanoTime();
					total += end - start;
				}
			}
			long avg = total / j / loop;
			
			System.out.println(avg);
		}	
	}
	
	/**
	 * find min timing test method
	 * 
	 * @param cmp, integer comparator
	 */
	public static void findMinTimer(Comparator<Integer> cmp) {
		long start, end;
		int loop = 1000;
		int N = 10000;
		System.out.println("findMin() method size 10,000 to 100,000");
		for(int j = N; j < N * 10; j += N) {
			long findMinTotal = 0;
//			ArrayList<Integer> ranList = generateRanNum(j);
//			ArrayList<Integer> ascendList = generateAscending(j);
			ArrayList<Integer> descendList = generateDescending(j);
			PriorityQueue<Integer> pq = new PriorityQueue<Integer>(cmp);
			// build priority queue for three cases
			for (int k = 0; k < j; k++) {
//				pq.add(ranList.get(k));		// average case
//				pq.add(ascendList.get(k));	// best case
				pq.add(descendList.get(k));	// worst case		
			}
			for(int i = 0; i < loop; i++) {
				start = System.nanoTime();
				pq.findMin();
				end = System.nanoTime();
				findMinTotal += end - start;
			}
			long averageFindMin = findMinTotal / loop;
			System.out.println(averageFindMin);
		}	
	}
	
	/**
	 * delete min timing test method
	 * 
	 * @param cmp, integer comparator
	 */
	public static void deleteMinTimer(Comparator<Integer> cmp) {
		long start, end;
		int loop = 100;
		int N = 10000;
		System.out.println("deleteMin() method size 10,000 to 100,000");
		for(int j = N; j < N * 10; j += N) {
			long deleteMinTotal = 0;
			for (int i = 0; i < loop; i++) {
//				ArrayList<Integer> ranList = generateRanNum(j);
//				ArrayList<Integer> ascendList = generateAscending(j);
				ArrayList<Integer> descendList = generateDescending(j);
				// every loop recreate priority queue
				PriorityQueue<Integer> pq = new PriorityQueue<Integer>(cmp);
				for (int k = 0; k < j; k++) {
//					pq.add(ranList.get(k));		// average case
//					pq.add(ascendList.get(k));	// best case
					pq.add(descendList.get(k));	// worst case			
				}
				
				for(int k = 0; k < j; k++) {
					start = System.nanoTime();
					pq.deleteMin();
					end = System.nanoTime();
					deleteMinTotal += end - start;		
				}
			}
			long averageDeleteMin = deleteMinTotal / j / loop;
			System.out.println(averageDeleteMin);
		}
	}
	
	/**
	 * helper method to generate ascending number order array list
	 * 
	 * @param N size of generating array list
	 * @return array list
	 */
	public static ArrayList<Integer> generateAscending(int N) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for(int i = 1; i <= N; i++) {
			result.add(i);
		}
		return result;
	}
	
	/**
	 * helper method to generate descending number order array list
	 * 
	 * @param N size of generating array list
	 * @return array list
	 */
	public static ArrayList<Integer> generateDescending(int N) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for(int i = N; i >= 1; i--) {
			result.add(i);
		}
		return result;
	}
	/**
	 * helper method to generate random number order array list
	 * 
	 * @param N size of generating array list
	 * @return array list
	 */
	public static ArrayList<Integer> generateRanNum(int N) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		Random ran = new Random();
		result.add(new Integer(0));
		for (int i = 1; i <= N; i++) {
			// Inserts integers at random points
			int place = ran.nextInt(i);
			result.add(place, new Integer(i));
		}
		return result;
	}

}
