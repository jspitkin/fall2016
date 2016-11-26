package assignment11;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Random;

public class PriorityQueueGrader {
	private static final int SM_SIZE_MAX = 15;

	private static final int MED_SIZE = 100;

	private static final int LG_SIZE = 1000;

	private static Random rng = new Random(314);

	private static int totalAddTests = 0;
	private static int addTestsPassed = 0;

	private static int totalFindMinTests = 0;
	private static int findMinTestsPassed = 0;

	private static int totalDelMinTests = 0;
	private static int delMinTestsPassed = 0;

	private static enum Test {
		FILL_PQ, FIND_MIN, DEL_MIN
	}

	private static class Tester<T> implements Runnable {
		private Test test;

		private PriorityQueue<T> pq;
		private String msg;
		private List<T> arr, arr2;
		private T item;
		private Comparator<? super T> cmp;

		public Tester(PriorityQueue<T> pq, List<T> arr, List<T> arr2, String msg) {
			test = Test.FILL_PQ;
			this.pq = pq;
			this.arr = arr;
			this.arr2 = arr2;
			this.msg = msg;
		}

		public Tester(PriorityQueue<T> pq, List<T> arr, List<T> arr2, String msg, Comparator<? super T> cmp) {
			this(pq, arr, arr2, msg);
			this.cmp = cmp;
		}

		// Used for testFindMin and testFindMax
		public Tester(PriorityQueue<T> pq, T item, String msg, Test test) {
			this.pq = pq;
			this.item = item;
			this.msg = msg;
			this.test = test;
		}

		// Used for tryDelMin and tryDelMax
		public Tester(PriorityQueue<T> pq, T item, List<T> arr, String msg, Test test) {
			this.pq = pq;
			this.item = item;
			this.arr = arr;
			this.msg = msg;
			this.test = test;
		}

		// Used for tryDelMin and tryDelMax
		public Tester(PriorityQueue<T> pq, T item, List<T> arr, String msg, Test test, Comparator<? super T> cmp) {
			this(pq, item, arr, msg, test);
			this.cmp = cmp;
		}

		@SuppressWarnings("unchecked")
		private void fillPQ(PriorityQueue<T> pq, List<T> arr, List<T> arr2, String msg) {
			totalAddTests++;
			try {
				for (T item : arr) {
					pq.add(item);
				}
				if (arr2.size() <= SM_SIZE_MAX) {
					if(Arrays.equals(pq.toArray(), arr2.toArray())) {
						addTestsPassed++;
					} else {
						System.out.println("TEST FAILED: add() for " + msg + " heap resulted in wrong contents");
					}
				} else {
					if (sameArrayContents(pq.toArray(), arr2.toArray()) && validateMinHeap((T[])pq.toArray(), cmp)) {
						addTestsPassed++;
					} else {
						System.out.println("TEST FAILED: add() for " + msg + " heap resulted in wrong contents");
					}
				}
			} catch (Throwable e) {
				System.out.println("TEST FAILED: add() for " + msg
						+ " heap caused exception (" + e.getClass().getName()
						+ ")");
			}
		}

		private static <T> void testFindMin(PriorityQueue<T> pq, T min, String msg) {
			totalFindMinTests++;
			try {
				if (pq.findMin().equals(min)) {
					findMinTestsPassed++;
				} else {
					System.out.println("TEST FAILED: findMin() for " + msg + " heap returned wrong value");
				}
			} catch (Throwable e) {
				System.out.println("TEST FAILED: findMin() for " + msg
						+ " heap caused exception (" + e.getClass().getName()
						+ ")");
			}
		}

		@SuppressWarnings("unchecked")
		private void tryDelMin(PriorityQueue<T> pq, T min, List<T> arr, String msg) {
			totalDelMinTests++;
			try {
				if (pq.deleteMin().equals(min)) {
					if (arr.size() <= SM_SIZE_MAX) {
						if (Arrays.equals(pq.toArray(), arr.toArray())) {
							delMinTestsPassed++;
						} else {
							System.out.println("TEST FAILED: wrong contents after deleteMin() for " + msg + " heap");
						}
					} else {
						if (sameArrayContents(pq.toArray(), arr.toArray()) && validateMinHeap((T[])pq.toArray(), cmp)) {
							delMinTestsPassed++;
						} else {
							System.out.println("TEST FAILED: wrong contents after deleteMin() for " + msg + " heap");
						}
					}
				} else {
					System.out.println("TEST FAILED: deleteMin() for " + msg + " heap returned wrong value");
				}
			} catch (Throwable e) {
				System.out.println("TEST FAILED: deleteMin() for " + msg
						+ " heap caused exception (" + e.getClass().getName()
						+ ")");
			}
		}

		public void run() {
			switch (test) {
			case FILL_PQ:
				fillPQ(pq, arr, arr2, msg);
				break;
			case FIND_MIN:
				testFindMin(pq, item, msg);
				break;
			case DEL_MIN:
				tryDelMin(pq, item, arr, msg);
				break;
			}
		}
	}

	private static String getName(Test t) {
		switch (t) {
		case FILL_PQ:
			return "add()";
		case FIND_MIN:
			return "findMin()";
		case DEL_MIN:
			return "deleteMin()";
		default:
			return "";
		}
	}

	@SuppressWarnings("deprecation")
	private static <T> void runTest(Tester<T> r) {
		Thread t = new Thread(r);
		t.start();
		try {
			t.join(3000);
			if (t.isAlive()) {
				System.out.println("TEST FAILED: " + getName(r.test) + " for "
						+ r.msg + " is running infinitely");
			}
			t.stop();
		} catch (InterruptedException e) {
			System.err.println("Something went wrong with the grader, run again");
			System.exit(1);
		}
	}

	private static <T> void fillPQ(PriorityQueue<T> pq, List<T> arr, List<T> arr2, String msg) {
		runTest(new Tester<T>(pq, arr, arr2, msg));
	}

	private static <T> void fillPQ(PriorityQueue<T> pq, List<T> arr, List<T> arr2, String msg, Comparator<? super T> cmp) {
		runTest(new Tester<T>(pq, arr, arr2, msg, cmp));
	}

	private static <T> void testFindMin(PriorityQueue<T> pq, T min, String msg) {
		runTest(new Tester<T>(pq, min, msg, Test.FIND_MIN));
	}

	private static <T> void tryDelMin(PriorityQueue<T> pq, T min, List<T> arr, String msg) {
		runTest(new Tester<T>(pq, min, arr, msg, Test.DEL_MIN));
	}

	private static <T> void tryDelMin(PriorityQueue<T> pq, T min, List<T> arr, String msg, Comparator<? super T> cmp) {
		runTest(new Tester<T>(pq, min, arr, msg, Test.DEL_MIN, cmp));
	}

	public static void main(String[] args) {

		// SMALL TESTS
		PriorityQueue<String> pq = new PriorityQueue<String>();

		// empty heap
		totalFindMinTests++;
		try {
			pq.findMin();
			System.out.println("TEST FAILED: findMin() for empty heap did not cause exception");
		} catch (NoSuchElementException e) {
			findMinTestsPassed++;
		} catch (Exception e) {
			System.out.println("TEST FAILED: findMin() for empty heap caused exception ("
									+ e.getClass().getName() + ")");
		}

		totalDelMinTests++;
		try {
			pq.findMin();
			System.out.println("TEST FAILED: deleteMin() for empty heap did not cause exception");
		} catch (NoSuchElementException e) {
			delMinTestsPassed++;
		} catch (Exception e) {
			System.out.println("TEST FAILED: deleteMin() for empty heap caused exception ("
									+ e.getClass().getName() + ")");
		}

		// one-item heap
		String msg = "one-item String";
		pq = new PriorityQueue<String>();
		List<String> oneItemArr = Arrays.asList(new String[] { "hello" });

		fillPQ(pq, oneItemArr, oneItemArr, msg);
		testFindMin(pq, "hello", msg);
		tryDelMin(pq, "hello", Arrays.asList(new String[0]), msg);

		// three-item heap
		msg = "three-item String";
		pq = new PriorityQueue<String>();
		List<String> threeItemArr = Arrays.asList(new String[] { "hi", "hello", "howdy" });

		fillPQ(pq, threeItemArr, Arrays.asList(new String[] { "hello", "hi", "howdy" }), msg);
		testFindMin(pq, "hello", msg);
		tryDelMin(pq, "hello", Arrays.asList(new String[] { "hi", "howdy" }), msg);

		// six-item heap
		msg = "six-item String";
		pq = new PriorityQueue<String>();
		List<String> sixItemArr = Arrays.asList(new String[] { "cat", "bear", "canary", "dog",
															   "giraffe", "antelope" });
		fillPQ(pq, sixItemArr, Arrays.asList(new String[] { "antelope", "cat", "bear",
															"dog", "giraffe", "canary" }), msg);

		testFindMin(pq, "antelope", msg);
		tryDelMin(pq, "antelope", Arrays.asList(new String[] { "bear", "cat", "canary",
															   "dog", "giraffe" }), msg);

		fillPQ(pq, Arrays.asList(new String[] { "anteater" }),
				   Arrays.asList(new String[] { "anteater", "cat", "bear", "dog", "giraffe", "canary" }), msg);

		// thirteen-item heap
		msg = "thirteen-item String w/ Comparator";
		ReverseStringComparator rsc = new ReverseStringComparator();
		pq = new PriorityQueue<String>(rsc);
		List<String> thirteenItemArr = Arrays.asList(new String[] { "white",
				"blue", "red", "orange", "yellow", "green", "black", "brown",
				"violet", "gray", "teal", "pink", "magenta" });

		fillPQ(pq, thirteenItemArr,
				Arrays.asList(new String[] { "yellow", "white", "red",
						"violet", "teal", "pink", "black", "blue", "brown",
						"gray", "orange", "green", "magenta" }), msg, rsc);

		testFindMin(pq, "yellow", msg);

		tryDelMin(pq, "yellow",
				Arrays.asList(new String[] { "white", "violet", "red",
						"magenta", "teal", "pink", "black", "blue", "brown",
						"gray", "orange", "green" }), msg, rsc);

		// medium random heap
		msg = MED_SIZE + "-item random Character";
		PriorityQueue<Character> pq2 = new PriorityQueue<Character>();
		List<Character> mediumArr = new ArrayList<Character>();
		for (int i = 0; i < MED_SIZE; i++)
			mediumArr.add((char) (rng.nextInt(94) + ' '));

		fillPQ(pq2, mediumArr, mediumArr, msg);

		Character medMin = findMin(mediumArr);
		testFindMin(pq2, medMin, msg);
		
		mediumArr.remove(medMin);
		tryDelMin(pq2, medMin, mediumArr, msg);

		for(int i = 0; i < 2; i++) {
			Character c = (char) (rng.nextInt(94) + ' ');
			mediumArr.add(c);
			fillPQ(pq2, Arrays.asList(new Character[] { c }), mediumArr, msg);
	
			medMin = findMin(mediumArr);
			testFindMin(pq2, medMin, msg);
			
			mediumArr.remove(medMin);
			tryDelMin(pq2, medMin, mediumArr, msg);
		}

		// large random heap
		msg = LG_SIZE + "-item random Integer w/ Comparator";
		ReverseIntegerComparator ric = new ReverseIntegerComparator();
		PriorityQueue<Integer> pq3 = new PriorityQueue<Integer>(ric);
		List<Integer> largeArr = new ArrayList<Integer>();
		for (int i = 0; i < LG_SIZE; i++)
			largeArr.add(rng.nextInt(LG_SIZE / 2) - LG_SIZE / 2);

		fillPQ(pq3, largeArr, largeArr, msg, ric);

		Integer lgMin = findMax(largeArr); //max/min don't match because of reverse comparator
		testFindMin(pq3, lgMin, msg);
		
		largeArr.remove(lgMin);
		tryDelMin(pq3, lgMin, largeArr, msg, ric);

		Integer i = rng.nextInt(LG_SIZE / 2) - LG_SIZE / 2;
		largeArr.add(i);

		fillPQ(pq3, Arrays.asList(new Integer[] { i }), largeArr, msg, ric);

		lgMin = findMax(largeArr);
		testFindMin(pq3, lgMin, msg);

		largeArr.remove(lgMin);
		tryDelMin(pq3, lgMin, largeArr, msg, ric);

		// SCORING
		System.out.println("\n\n---- Scoring ----------------");

		System.out.println("add() tests passed:\t\t" + addTestsPassed + "/"
				+ totalAddTests);
		System.out.println("add() points:\t\t\t" + 3 * addTestsPassed + "/"
				+ 3 * totalAddTests);
		System.out.println();
		
		System.out.println("findMin() tests passed:\t\t"
				+ findMinTestsPassed + "/" + totalFindMinTests);
		System.out.println("findMin() points:\t\t"
				+ findMinTestsPassed + "/" + totalFindMinTests);
		System.out.println();
		
		System.out.println("deleteMin() tests passed:\t"
				+ delMinTestsPassed + "/" + totalDelMinTests);
		System.out.println("deleteMin() points:\t\t"
				+ Math.round(3.5 * delMinTestsPassed) + "/" + Math.round(3.5 * totalDelMinTests));
		System.out.println();

		int totalPoints = 3 * addTestsPassed + findMinTestsPassed + (int)Math.round(3.5 * delMinTestsPassed);
		int totalPossible = 3 * totalAddTests + totalFindMinTests + (int)Math.round(3.5 * totalDelMinTests);
		System.out.println("Subtotal:\t\t\t" + totalPoints + "/" + totalPossible);
		System.out.println("Testing and style:\t\t  /5");
		System.out.println("Analysis document:\t\t  /20");
		System.out.println("Total: \t\t\t\t  /100");
		System.out.println();
		System.out.println("--------------------------------------");
		System.out.println();
		System.out.println("TA Comments:");
	}

	private static boolean sameArrayContents(Object[] arr1, Object[] arr2) {
		if (arr1.length != arr2.length)
			return false;

		Arrays.sort(arr1);
		Arrays.sort(arr2);

		for (int i = 0; i < arr1.length; i++)
			if (!arr1[i].equals(arr2[i]))
				return false;

		return true;
	}

	@SuppressWarnings("unchecked")
	private static <T> boolean validateMinHeap(T[] arr) {
		for (int i = 1; i < arr.length; i++) {
			if (((Comparable<? super T>)arr[i]).compareTo(arr[(i - 1) / 2]) < 0) {
				return false;
			}
		}
		return true;
	}

	private static <T> boolean validateMinHeap(T[] arr, Comparator<? super T> cmp) {
		if(cmp == null) {
			return validateMinHeap(arr);
		}
		
		for (int i = 1; i < arr.length; i++) {
			if (cmp.compare(arr[i], arr[(i - 1) / 2]) < 0) {
				return false;
			}
		}
		return true;
	}

	private static <T extends Comparable<? super T>> T findMin(List<T> a) {
		T min = a.get(0);
		for (int i = 1; i < a.size(); i++)
			if (a.get(i).compareTo(min) < 0)
				min = a.get(i);
		return min;
	}

	private static <T extends Comparable<? super T>> T findMax(List<T> a) {
		T max = a.get(0);
		for (int i = 1; i < a.size(); i++)
			if (a.get(i).compareTo(max) > 0)
				max = a.get(i);
		return max;
	}

	private static class ReverseStringComparator implements Comparator<String> {
		public int compare(String o1, String o2) {
			if (o1.compareTo(o2) > 0)
				return -1;
			if (o1.compareTo(o2) < 0)
				return 1;
			return 0;
		}
	}

	private static class ReverseIntegerComparator implements Comparator<Integer> {
		public int compare(Integer o1, Integer o2) {
			if (o1.compareTo(o2) > 0)
				return -1;
			if (o1.compareTo(o2) < 0)
				return 1;
			return 0;
		}
	}
}