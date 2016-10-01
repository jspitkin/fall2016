package assignment05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

@SuppressWarnings("deprecation")
public class TATestSortUtil {
	
	static enum Method { MERGE, QUICK };

	static ArrayList<Integer> unsorted1;
	static ArrayList<Integer> unsorted2;
	static ArrayList<Integer> unsorted3;
	static ArrayList<Integer> unsorted4;
	static ArrayList<Integer> unsorted5;
	static ArrayList<Integer> unsorted6;
	static ArrayList<Integer> unsorted7;

	static int quickTestsPassed = 0;
	static int scoreQuickTest = 0;
	static int mergeTestsPassed = 0;
	static int scoreMergeTest = 0;
	static int genTestsPassed = 0;
	static int scoreGenTest = 0;
	static ArrayList<String> quickComments = new ArrayList<String>();
	static ArrayList<String> mergeComments = new ArrayList<String>();
	static ArrayList<String> genComments = new ArrayList<String>();
	
	static class ReverseIntegerComparator implements Comparator<Integer> {
		public int compare(Integer o1, Integer o2) {
		  return o2.compareTo(o1);
		}
	}
	
	/***
	 * Returns the name of the algorithm m as a String
	 */
	public static String name(Method m) {
		return (m == Method.MERGE ? "Merge" : "Quick");
	}
	
	/***
	 * Runs a test in another thread to handle infinite loops
	 */
	private static class SortTester implements Runnable {
		ArrayList<Integer> correct;
		ArrayList<Integer> unsorted;
		int points;
		Method method;
		String msg;
		
		public SortTester(ArrayList<Integer> correct, ArrayList<Integer> unsorted, int points, Method method, String msg) {
			this.correct = correct;
			this.unsorted = unsorted;
			this.points = points;
			this.method = method;
			this.msg = msg + " (" + name(method) + ")";
		}
		
		public void run() {
			try {
				switch(method) {
				case MERGE:
					SortUtil.mergesort(unsorted, new ReverseIntegerComparator());
					if(correct.equals(unsorted)){
						mergeTestsPassed++;
						scoreMergeTest += points;
					} else {
						mergeComments.add(msg);
					}
					break;
				case QUICK:
					SortUtil.quicksort(unsorted, new ReverseIntegerComparator());
					if(correct.equals(unsorted)){
						quickTestsPassed++;
						scoreQuickTest += points;
					} else {
						quickComments.add(msg);
					}
					break;
				}
			} catch(Throwable e) {
				switch(method) {
				case MERGE:
					mergeComments.add(msg + ", Exception: " + e.toString());
					break;
				case QUICK:
					quickComments.add(msg + ", Exception: " + e.toString());
					break;
				}
			}
		}
	}
	
	/***
	 * Actually run a test in another thread.
	 */
	private static void runTest(SortTester r) {
		Thread t = new Thread(r);
		t.start();
		try {
			t.join(3000);
			if(t.isAlive()) {
				switch(r.method) { 
				case MERGE:
					mergeComments.add(r.msg + ", Did not finish.");
					break;
				case QUICK:
					quickComments.add(r.msg + ", Did not finish.");
					break;
				}
				t.stop();
			}
		} catch (InterruptedException e) {
			System.out.println("Something went wrong with the grader, run again");
			System.exit(1);
		}
	}

	/***
	 * Tests the merge sort for correctness; This includes 3 different
	 * lists that are unsorted, an empty list, and a very small list 
	 */
	@SuppressWarnings("unchecked")
	private static void testSort(Method m){
		ArrayList<Integer> correct1 = (ArrayList<Integer>)unsorted1.clone();
		ArrayList<Integer> correct2 = (ArrayList<Integer>)unsorted2.clone();
		ArrayList<Integer> correct3 = (ArrayList<Integer>)unsorted3.clone();
		ArrayList<Integer> correct4 = (ArrayList<Integer>)unsorted4.clone();
		ArrayList<Integer> correct5 = (ArrayList<Integer>)unsorted5.clone();
		ArrayList<Integer> correct6 = (ArrayList<Integer>)unsorted6.clone();
		ArrayList<Integer> correct7 = (ArrayList<Integer>)unsorted7.clone();
		
		ReverseIntegerComparator ic = new ReverseIntegerComparator();
		
		Collections.sort(correct1, ic);
		Collections.sort(correct2, ic);
		Collections.sort(correct3, ic);
		Collections.sort(correct6, ic);
		Collections.sort(correct7, ic);
		
		runTest(new SortTester(correct1, unsorted1, 3, m, "TEST FAILED: Failed the random sort test"));
		runTest(new SortTester(correct6, unsorted6, 2, m, "TEST FAILED: Failed the small random sort test"));
		runTest(new SortTester(correct2, unsorted2, 3, m, "TEST FAILED: Failed the reverse sort test"));
		runTest(new SortTester(correct3, unsorted3, 3, m, "TEST FAILED: Failed the in order sort test"));
		runTest(new SortTester(correct4, unsorted4, 3, m, "TEST FAILED: Sort failed with only 1 item in the list"));
		runTest(new SortTester(correct5, unsorted5, 3, m, "TEST FAILED: Sort failed with an empty list"));
		runTest(new SortTester(correct7, unsorted7, 3, m, "TEST FAILED: Sort failed with large random list"));
	}
	
	/***
	 * Sets up the sort tests
	 */
	private static void setUpSort()
	{
		unsorted1 = new ArrayList<Integer>();
		unsorted2 = new ArrayList<Integer>();
		unsorted3 = new ArrayList<Integer>();
		unsorted4 = new ArrayList<Integer>();
		unsorted5 = new ArrayList<Integer>();
		unsorted6 = new ArrayList<Integer>();
		unsorted7 = new ArrayList<Integer>();
		
		int neg = -1;
		
		Random r = new Random(1618);
		
		for(int i = 0; i < 100; i++) {
			unsorted1.add(neg * r.nextInt());
			neg *= -1;
		}
		
		for(int i = 100; i >= 0; i--)
			unsorted2.add(i);
		
		unsorted1.addAll(unsorted2);
		Collections.shuffle(unsorted1);
		
		for(int i = -100; i < 100; i++)
			unsorted3.add(i);
		
		unsorted4.add(6);
		
		unsorted6.add(3);
		unsorted6.add(1);
		unsorted6.add(2);
		
		for(int i = 0; i < 5000; i++) {
			unsorted7.add(r.nextInt());
		}
		Collections.shuffle(unsorted7);
	}
	
	static final int BEST=0, AVE=1, WORST=2;
	
	/***
	 * Runs a test in another thread to handle infinite loops
	 */
	private static class DataTester implements Runnable {
		int size, order, points;
		
		//needs two copies of the same sequencer
		public DataTester(int size, int order, int points) {
			this.size = size;
			this.order = order;
			this.points = points;
		}
		
		public String caseName() {
			switch(order) {
			case BEST:
				return "BestCase";
			case AVE:
				return "AverageCase";
			case WORST:
				return "WorstCase";
			default:
					return null;
			}
		}
		
		public void run() {
			try {
				ArrayList<Integer> out;
				switch(order) {
				case BEST:
					out = SortUtil.generateBestCase(size);
					if(out.size() != size) {
						genComments.add("generateBestCase returned wrong number of items");
						return;
					}
					for(int i = 0; i < size; i++) {
						if(!out.get(i).equals(i+1)) {
							genComments.add("generateBestCase return incorrect items");
							return;
						}
					}
					break;
					
				case AVE:
					out = SortUtil.generateAverageCase(size);
					if(out.size() != size) {
						genComments.add("generateAverageCase returned wrong number of items");
						return;
					}
					for(int i = 1; i <= size; i++) {
						if(!out.contains(i)) {
							genComments.add("generateAverageCase return incorrect items");
							return;
						}
					}
					break;
					
				case WORST:
					out = SortUtil.generateWorstCase(size);
					if(out.size() != size) {
						genComments.add("generateWorstCase returned wrong number of items");
						return;
					}
					for(int i = 0; i < size; i++) {
						if(!out.get(i).equals(size - i)) {
							genComments.add("generateWorstCase return incorrect items");
							return;
						}
					}
					break;
				}
				genTestsPassed++;
				scoreGenTest += points;
			} catch(Throwable e) {
				genComments.add("generate" + caseName() + " threw " + e + " for size " + size);
			}
		}
	}
	
	/***
	 * Actually run a test in another thread.
	 */
	private static void runTest(DataTester r) {
		Thread t = new Thread(r);
		t.start();
		try {
			t.join(3000);
			if(t.isAlive()) {
				genComments.add("generate" + r.caseName() + " did not finish for size " + r.size);
				t.stop();
				t.join();
			}
		} catch (InterruptedException e) {
			System.out.println("Something went wrong with the grader, run again");
			System.exit(1);
		}
	}

	/***
	 * Tests generateData for correctness.
	 */
	private static void testGenerateData(){
		System.out.println("--Testing generateBestCase()--");
		runTest(new DataTester(0, BEST, 1));
		runTest(new DataTester(10, BEST, 1));
		runTest(new DataTester(500, BEST, 1));
		System.out.println("    Done.");

		System.out.println("--Testing generateAverageCase()--");
		runTest(new DataTester(0, AVE, 1));
		runTest(new DataTester(10, AVE, 1));
		runTest(new DataTester(500, AVE, 1));
		runTest(new DataTester(1000, AVE, 1));
		System.out.println("    Done.");

		System.out.println("--Testing generateWorstCase()--");
		runTest(new DataTester(10, WORST, 1));
		runTest(new DataTester(500, WORST, 1));
		runTest(new DataTester(1000, WORST, 1));
		System.out.println("    Done.");
	}
	
	public static void main(String[] args){
		
		System.out.println("--------------------------------------");
		System.out.println("--TA Assignment 4 Test Output--");
		System.out.println("--------------------------------------");
		System.out.println();
		
		System.out.println("--Testing mergeSort()--");
		setUpSort();
		testSort(Method.MERGE);
		System.out.println("    Done.");
		
		System.out.println("--Testing quickSort()--");
		setUpSort();
		testSort(Method.QUICK);
		System.out.println("    Done.");
		
		testGenerateData();
		
		System.out.println();
		System.out.println("--------------------------------------");
		System.out.println();
		System.out.println("--Results--");
		if(genComments.size() == 0 && quickComments.size() == 0 && mergeComments.size() == 0){
			System.out.println("All tests passed!");
		} else {
			if(genComments.size() != 0) {
				for(String s: genComments) {
					System.out.println(s);
				}
			}
			
			if(quickComments.size() != 0) {
				for(String s: quickComments) {
					System.out.println(s);
				}
			}
			
			if(mergeComments.size() != 0) {
				for(String s: mergeComments) {
					System.out.println(s);
				}
			}
		}
		
		System.out.println();
		System.out.println("--------------------------------------");
		System.out.println();
		System.out.println("--Scoring--");
		System.out.println("Compiles and runs:\t10/10"); //CHANGE IF NOT TRUE
		System.out.println("Generate methods:\t" + ((scoreGenTest + 1) / 2) +"/5");
		System.out.println("Merge sort:\t\t" +  scoreMergeTest + "/20");
		System.out.println("Quicksort:\t\t" +scoreQuickTest +"/20");
		System.out.println("Style & Testing:\t/5");
		System.out.println("Analysis Document:\t/40");
		System.out.println();
		System.out.println("Total:\t\t\t/100");
		
		System.out.println();
		System.out.println("--------------------------------------");
		System.out.println();
		System.out.println("TA Comments:\n");
				
		
	}
}