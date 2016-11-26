package assignment08;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.TreeSet;

public class BSTGrader {
	private static String filePath = "assignment8_files/";
	private static int BSTTestsPassed = 0, BSTTestCount = 0;
	private static int SpellCheckTestsPassed = 0, SpellCheckTestCount = 0;
	
	static final String[] catNames = {"EmptyBST", "OneNodeBST", "TwoNodeBST", "ThreeNodeBST",
									  "SmallBST", "MedRandBST", "MedRightHeavyBST", "MedLeftHeavyBST",
									  "LgBalancedBST",  "SpellChecker"};
	
	static final int[] testCounts = {9, 7, 8, 4, 9, 5, 6, 6, 6, 5};

	private static class CharWrapper implements Comparable<CharWrapper> {
		private char c;

		public CharWrapper(char c) {
			this.c = c;
		}

		@Override
		public boolean equals(Object o) {
			if (!(o instanceof CharWrapper)) {
				return false;
			}
			return c == ((CharWrapper) o).c;
		}

		@Override
		public int compareTo(CharWrapper o) {
			return ((Character) o.c).compareTo(c);
		}

		@Override
		public String toString() {
			return ((Character) c).toString();
		}
	}

	@SuppressWarnings("unused")
	private static class Tester implements Runnable {
		private int category, testNum;
		private String msg;

		public Tester(int _category, int _testNum) {
			category = _category;
			testNum = _testNum;
		}

		public void run() {
			try {
				if(category == catNames.length - 1) {
					SpellCheckTestCount++;
				} else {
					BSTTestCount++;
				}
				this.getClass().getMethod("test" + catNames[category] + testNum).invoke(this);
				return;
			} catch (InvocationTargetException e) {
				//This exception represents an exception in student code
				System.out.println("TEST FAILED: " + msg + " caused exception (" + e.getCause().toString() + ")");
				return;
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
			System.exit(1); //If we got here, there was a reflection exception
		}

		/*
		 * Returns 1 if student solution is the same as the reference solution,
		 * returns 0 otherwise.
		 */
		public <T> int assertEquals(T studentSoln, T referenceSoln) {
			if (studentSoln != null && studentSoln.equals(referenceSoln)) {
				//System.out.println("TEST PASSED: " + msg);
				return 1;
			}
			System.out.println("TEST FAILED: " + msg);
			return 0;
		}
		
		public <T> int iteratorCheck(Iterator<T> it, List<T> refList) {
			boolean hasNext = true, items = true, exception = false; //true or false for each part of the test
			try {
				ArrayList<T> studentList = new ArrayList<T>();
				for(int i=0; i<refList.size(); i++) {
					if(!it.hasNext()) {
						hasNext = false;
					} else {
						if(!refList.get(i).equals(it.next())) {
							items = false;
						}
					}
				}
				if(it.hasNext()) {
					hasNext = false;
				}
				try {
					it.next();
					exception = false; //should have thrown exception
				} catch (NoSuchElementException e) {
					exception = true; //out of items, so should throw exception here
				} catch (Exception e) {
					exception = false; //if some other exception is thrown here, they should only lose one of three points
				}
			} catch (NoSuchElementException e) {
				System.out.println("TEST FAILED: " + msg + ", threw NoSuchElementException early");
				return 0;
			}
			
			int res = 0;
			if(items) {
				//System.out.println("TEST PASSED: " + msg + ", items returned and ordering");
				res++;
			} else {
				System.out.println("TEST FAILED: " + msg + ", items returned and ordering");
			}
			if(hasNext) {
				//System.out.println("TEST PASSED: " + msg + ", hasNext() behavior");
				res++;
			} else {
				System.out.println("TEST FAILED: " + msg + ", hasNext() behavior");
			}
			if(exception) {
				//System.out.println("TEST PASSED: " + msg + ", final NoSuchElementException");
				res++;
			} else {
				System.out.println("TEST FAILED: " + msg + ", final NoSuchElementException");
			}
			return res;
		}

		public void testEmptyBST0() {
			msg = "contains() on empty tree";
			
			SortedSet<CharWrapper> bst = new BinarySearchTree<CharWrapper>();
			BSTTestsPassed += assertEquals(bst.contains(new CharWrapper('A')), false);
		}

		public void testEmptyBST1() {
			msg = "isEmpty() on empty tree";
			
			SortedSet<CharWrapper> bst = new BinarySearchTree<CharWrapper>();
			BSTTestsPassed += assertEquals(bst.isEmpty(), true);
		}

		public void testEmptyBST2() {
			msg = "size() on empty tree";

			SortedSet<CharWrapper> bst = new BinarySearchTree<CharWrapper>();
			BSTTestsPassed += assertEquals(bst.size(), 0);
		}

		public void testEmptyBST3() {
			msg = "toArrayList() on empty tree";

			SortedSet<CharWrapper> bst = new BinarySearchTree<CharWrapper>();
			BSTTestsPassed += assertEquals(bst.toArrayList(), new ArrayList<CharWrapper>());
		}

		public void testEmptyBST4() {
			msg = "remove('Z') on empty tree";

			SortedSet<CharWrapper> bst = new BinarySearchTree<CharWrapper>();
			BSTTestsPassed += assertEquals(bst.remove(new CharWrapper('Z')), false);
		}

		public void testEmptyBST5() {
			msg = "containsAll({}) on empty tree";

			SortedSet<CharWrapper> bst = new BinarySearchTree<CharWrapper>();
			BSTTestsPassed += assertEquals(bst.containsAll(new ArrayList<CharWrapper>()), true);
		}

		public void testEmptyBST6() {
			msg = "removeAll({}) on empty tree";

			SortedSet<CharWrapper> bst = new BinarySearchTree<CharWrapper>();
			BSTTestsPassed += assertEquals(bst.removeAll(new ArrayList<CharWrapper>()), false);
		}

		public void testEmptyBST7() {
			msg = "first() on empty tree";
			
			SortedSet<CharWrapper> bst = new BinarySearchTree<CharWrapper>();
			try {
				bst.first();
				System.out.println("TEST FAILED: " + msg + " did not throw NoSuchElementException");
			} catch (NoSuchElementException e) {
				//System.out.println("TEST PASSED: " + msg);
				BSTTestsPassed++;
			}
		}

		public void testEmptyBST8() {
			msg = "last() on empty tree";

			SortedSet<CharWrapper> bst = new BinarySearchTree<CharWrapper>();
			try {
				bst.last();
				System.out.println("TEST FAILED: " + msg + " did not throw NoSuchElementException");
			} catch (NoSuchElementException e) {
				//System.out.println("TEST PASSED: " + msg);
				BSTTestsPassed++;
			}
		}
	
		public void testOneNodeBST0() {
			msg = "add('A') on one-node tree";
			
			SortedSet<CharWrapper> bst = new BinarySearchTree<CharWrapper>();
			BSTTestsPassed += assertEquals(bst.add(new CharWrapper('A')), true);
		}
	
		public void testOneNodeBST1() {
			msg = "size() on one-node tree";

			SortedSet<CharWrapper> bst = new BinarySearchTree<CharWrapper>();
			bst.add(new CharWrapper('A'));
			BSTTestsPassed += assertEquals(bst.size(), 1);
		}
	
		public void testOneNodeBST2() {
			msg = "contains('A') on one-node tree";

			SortedSet<CharWrapper> bst = new BinarySearchTree<CharWrapper>();
			bst.add(new CharWrapper('A'));
			BSTTestsPassed += assertEquals(bst.contains(new CharWrapper('A')), true);
		}
	
		public void testOneNodeBST3() {
			msg = "first() on one-node tree";

			SortedSet<CharWrapper> bst = new BinarySearchTree<CharWrapper>();
			bst.add(new CharWrapper('A'));
			BSTTestsPassed += assertEquals(bst.first(), new CharWrapper('A'));
		}
	
		public void testOneNodeBST4() {
			msg = "last() on one-node tree";

			SortedSet<CharWrapper> bst = new BinarySearchTree<CharWrapper>();
			bst.add(new CharWrapper('A'));
			BSTTestsPassed += assertEquals(bst.last(), new CharWrapper('A'));
		}
	
		public void testOneNodeBST5() {
			msg = "toArrayList() on one-node tree";

			SortedSet<CharWrapper> bst = new BinarySearchTree<CharWrapper>();
			bst.add(new CharWrapper('A'));
			BSTTestsPassed += assertEquals(bst.toArrayList(),
						new ArrayList<CharWrapper>(Arrays.asList(new CharWrapper[] { new CharWrapper('A') })));
		}
	
		public void testOneNodeBST6() {
			msg = "clear() on one-node tree";

			SortedSet<CharWrapper> bst = new BinarySearchTree<CharWrapper>();
			bst.add(new CharWrapper('A'));
			bst.clear();
			BSTTestsPassed += assertEquals(bst.isEmpty() && !bst.contains(new CharWrapper('A')), true);
		}
	
		public void testTwoNodeBST0() {
			msg = "add(\"hello\") and add(\"hi\") on empty tree";
			
			SortedSet<String> bst = new BinarySearchTree<String>();
			BSTTestsPassed += assertEquals(bst.add("hello") && bst.add("hi"), true);
		}
	
		public void testTwoNodeBST1() {
			msg = "contains(\"hello\") on two-node tree";

			SortedSet<String> bst = new BinarySearchTree<String>();
			bst.add("hello");
			bst.add("hi");
			BSTTestsPassed += assertEquals(bst.contains("hello"), true);
		}
	
		public void testTwoNodeBST2() {
			msg = "contains(\"howdy\") on two-node tree";

			SortedSet<String> bst = new BinarySearchTree<String>();
			bst.add("hello");
			bst.add("hi");
			BSTTestsPassed += assertEquals(bst.contains("howdy"), false);
		}
	
		public void testTwoNodeBST3() {
			msg = "first() on two-node tree";

			SortedSet<String> bst = new BinarySearchTree<String>();
			bst.add("hello");
			bst.add("hi");
			BSTTestsPassed += assertEquals(bst.first(), "hello");
		}
	
		public void testTwoNodeBST4() {
			msg = "last() on two-node tree";

			SortedSet<String> bst = new BinarySearchTree<String>();
			bst.add("hello");
			bst.add("hi");
			BSTTestsPassed += assertEquals(bst.last(), "hi");
		}
	
		public void testTwoNodeBST5() {
			msg = "toArrayList() on two-node tree";

			SortedSet<String> bst = new BinarySearchTree<String>();
			bst.add("hello");
			bst.add("hi");
			BSTTestsPassed += assertEquals(bst.toArrayList(),
						new ArrayList<String>(Arrays.asList(new String[] { "hello", "hi" })));
		}
	
		public void testTwoNodeBST6() {
			msg = "remove(\"yo\") on two-node tree";

			SortedSet<String> bst = new BinarySearchTree<String>();
			bst.add("hello");
			bst.add("hi");
			BSTTestsPassed += assertEquals(bst.remove("yo"), false);
		}
	
		public void testTwoNodeBST7() {
			msg = "remove(\"hello\") on two-node tree";

			SortedSet<String> bst = new BinarySearchTree<String>();
			bst.add("hello");
			bst.add("hi");
			BSTTestsPassed += assertEquals(bst.remove("hello") && !bst.contains("hello"), true);
		}
	
		public void testThreeNodeBST0() {
			msg = "addAll(1.5, 3.14, 5.6) on empty tree";
			
			SortedSet<Double> bst = new BinarySearchTree<Double>();
			ArrayList<Double> arr = new ArrayList<Double>(Arrays.asList(new Double[] { 1.5, 3.14, 5.6 }));
			BSTTestsPassed += assertEquals(bst.addAll(arr), true);
		}
	
		public void testThreeNodeBST1() {
			msg = "containsAll(1.5, 3.14, 5.6) on three-node tree";

			SortedSet<Double> bst = new BinarySearchTree<Double>();
			ArrayList<Double> arr = new ArrayList<Double>(Arrays.asList(new Double[] { 1.5, 3.14, 5.6 }));
			bst.addAll(arr);
			BSTTestsPassed += assertEquals(bst.containsAll(arr), true);
		}
	
		public void testThreeNodeBST2() {
			msg = "toArrayList() on three-node tree";

			SortedSet<Double> bst = new BinarySearchTree<Double>();
			ArrayList<Double> arr = new ArrayList<Double>(Arrays.asList(new Double[] { 1.5, 3.14, 5.6 }));
			bst.addAll(arr);
			BSTTestsPassed += assertEquals(bst.toArrayList(), arr);
		}
	
		public void testThreeNodeBST3() {
			msg = "remove(3.14) on three-node tree";

			SortedSet<Double> bst = new BinarySearchTree<Double>();
			ArrayList<Double> arr = new ArrayList<Double>(Arrays.asList(new Double[] { 1.5, 3.14, 5.6 }));
			bst.addAll(arr);
			arr.remove(3.14);
			BSTTestsPassed += assertEquals(bst.remove(3.14) && bst.toArrayList().equals(arr), true);
		}
	
		public void testSmallBST0() {
			msg = "addAll(70, 22, 114, 20, 36, 88, 150) on empty tree";
			
			SortedSet<Integer> bst = new BinarySearchTree<Integer>();
			ArrayList<Integer> arr = new ArrayList<Integer>(
					Arrays.asList(new Integer[] { 70, 22, 114, 20, 36, 88, 150 }));
			BSTTestsPassed += assertEquals(bst.addAll(arr), true);
		}
	
		public void testSmallBST1() {
			msg = "addAll(22, 20, 88) on small tree";

			SortedSet<Integer> bst = new BinarySearchTree<Integer>();
			ArrayList<Integer> arr = new ArrayList<Integer>(
					Arrays.asList(new Integer[] { 70, 22, 114, 20, 36, 88, 150 }));
			bst.addAll(arr);
			ArrayList<Integer> arr2 = new ArrayList<Integer>(
					Arrays.asList(new Integer[] { 22, 20, 88 }));
			BSTTestsPassed += assertEquals(bst.addAll(arr2), false);
		}
	
		public void testSmallBST2() {
			msg = "containsAll(22, 21, 88) on small tree";

			SortedSet<Integer> bst = new BinarySearchTree<Integer>();
			ArrayList<Integer> arr = new ArrayList<Integer>(
					Arrays.asList(new Integer[] { 70, 22, 114, 20, 36, 88, 150 }));
			bst.addAll(arr);
			ArrayList<Integer> arr2 = new ArrayList<Integer>(
					Arrays.asList(new Integer[] { 22, 20, 88 }));
			bst.addAll(arr2);
			arr2.set(1, 21);
			BSTTestsPassed += assertEquals(bst.containsAll(arr2), false);
		}
	
		public void testSmallBST3() {
			msg = "first() on small tree";

			SortedSet<Integer> bst = new BinarySearchTree<Integer>();
			ArrayList<Integer> arr = new ArrayList<Integer>(
					Arrays.asList(new Integer[] { 70, 22, 114, 20, 36, 88, 150 }));
			bst.addAll(arr);
			ArrayList<Integer> arr2 = new ArrayList<Integer>(
					Arrays.asList(new Integer[] { 22, 20, 88 }));
			bst.addAll(arr2);
			BSTTestsPassed += assertEquals(bst.first(), 20);
		}
	
		public void testSmallBST4() {
			msg = "last() on small tree";

			SortedSet<Integer> bst = new BinarySearchTree<Integer>();
			ArrayList<Integer> arr = new ArrayList<Integer>(
					Arrays.asList(new Integer[] { 70, 22, 114, 20, 36, 88, 150 }));
			bst.addAll(arr);
			ArrayList<Integer> arr2 = new ArrayList<Integer>(
					Arrays.asList(new Integer[] { 22, 20, 88 }));
			bst.addAll(arr2);
			BSTTestsPassed += assertEquals(bst.last(), 150);
		}
	
		public void testSmallBST5() {
			msg = "size() on small tree";

			SortedSet<Integer> bst = new BinarySearchTree<Integer>();
			ArrayList<Integer> arr = new ArrayList<Integer>(
					Arrays.asList(new Integer[] { 70, 22, 114, 20, 36, 88, 150 }));
			bst.addAll(arr);
			ArrayList<Integer> arr2 = new ArrayList<Integer>(
					Arrays.asList(new Integer[] { 22, 20, 88 }));
			bst.addAll(arr2);
			BSTTestsPassed += assertEquals(bst.size(), 7);
		}
	
		public void testSmallBST6() {
			msg = "remove(0) on small tree";

			SortedSet<Integer> bst = new BinarySearchTree<Integer>();
			ArrayList<Integer> arr = new ArrayList<Integer>(
					Arrays.asList(new Integer[] { 70, 22, 114, 20, 36, 88, 150 }));
			bst.addAll(arr);
			ArrayList<Integer> arr2 = new ArrayList<Integer>(
					Arrays.asList(new Integer[] { 22, 20, 88 }));
			bst.addAll(arr2);
			BSTTestsPassed += assertEquals(bst.remove(0), false);
		}
	
		public void testSmallBST7() {
			msg = "remove(114) on small tree";

			SortedSet<Integer> bst = new BinarySearchTree<Integer>();
			ArrayList<Integer> arr = new ArrayList<Integer>(
					Arrays.asList(new Integer[] { 70, 22, 114, 20, 36, 88, 150 }));
			bst.addAll(arr);
			ArrayList<Integer> arr2 = new ArrayList<Integer>(
					Arrays.asList(new Integer[] { 22, 20, 88 }));
			bst.addAll(arr2);
			arr.remove(new Integer(114));
			Collections.sort(arr);
			BSTTestsPassed += assertEquals(bst.remove(114) && bst.toArrayList().equals(arr), true);
		}
	
		public void testSmallBST8() {
			msg = "size() on small tree";

			SortedSet<Integer> bst = new BinarySearchTree<Integer>();
			ArrayList<Integer> arr = new ArrayList<Integer>(
					Arrays.asList(new Integer[] { 70, 22, 114, 20, 36, 88, 150 }));
			bst.addAll(arr);
			ArrayList<Integer> arr2 = new ArrayList<Integer>(
					Arrays.asList(new Integer[] { 22, 20, 88 }));
			bst.addAll(arr2);
			bst.remove(114);
			BSTTestsPassed += assertEquals(bst.size(), 6);
		}
	
		public void testMedRandBST0() {
			msg = "addAll(rand_char_list_w_duplicates) on empty tree";
			
			SortedSet<CharWrapper> bst = new BinarySearchTree<CharWrapper>();
			ArrayList<CharWrapper> arr = new ArrayList<CharWrapper>();
			Random rng = new Random(749);
			for (int i = 0; i < 100; i++) {
				arr.add(new CharWrapper((char) (rng.nextInt(26) + 'a')));
			}
			
			BSTTestsPassed += assertEquals(bst.addAll(arr), true);
		}
	
		public void testMedRandBST1() {
			msg = "size() on medium tree";
			
			SortedSet<CharWrapper> bst = new BinarySearchTree<CharWrapper>();
			ArrayList<CharWrapper> arr = new ArrayList<CharWrapper>();
			Random rng = new Random(749);
			for (int i = 0; i < 100; i++) {
				arr.add(new CharWrapper((char) (rng.nextInt(26) + 'a')));
			}
			bst.addAll(arr);
			TreeSet<CharWrapper> set = new TreeSet<CharWrapper>();
			set.addAll(arr); // remove duplicates
	
			BSTTestsPassed += assertEquals(bst.size(), set.size());
		}
	
		public void testMedRandBST2() {
			msg = "contains(rand_char) on medium tree";
			
			SortedSet<CharWrapper> bst = new BinarySearchTree<CharWrapper>();
			ArrayList<CharWrapper> arr = new ArrayList<CharWrapper>();
			Random rng = new Random(749);
			for (int i = 0; i < 100; i++) {
				arr.add(new CharWrapper((char) (rng.nextInt(26) + 'a')));
			}
			bst.addAll(arr);
			TreeSet<CharWrapper> set = new TreeSet<CharWrapper>();
			set.addAll(arr); // remove duplicates
	
			CharWrapper c = new CharWrapper((char) (rng.nextInt(26) + 'a'));
			BSTTestsPassed += assertEquals(bst.contains(c), set.contains(c));
		}
	
		public void testMedRandBST3() {
			msg = "toArrayList() on medium tree";
			
			SortedSet<CharWrapper> bst = new BinarySearchTree<CharWrapper>();
			ArrayList<CharWrapper> arr = new ArrayList<CharWrapper>();
			Random rng = new Random(749);
			for (int i = 0; i < 100; i++) {
				arr.add(new CharWrapper((char) (rng.nextInt(26) + 'a')));
			}
			bst.addAll(arr);
			TreeSet<CharWrapper> set = new TreeSet<CharWrapper>();
			set.addAll(arr); // remove duplicates
	
			CharWrapper[] sortedSet = new CharWrapper[0];
			sortedSet = set.toArray(sortedSet);
			Arrays.sort(sortedSet);
			BSTTestsPassed += assertEquals(bst.toArrayList(), Arrays.asList(sortedSet));
		}
	
		public void testMedRandBST4() {
			msg = "removeAll(all_chars) on medium tree";
			
			SortedSet<CharWrapper> bst = new BinarySearchTree<CharWrapper>();
			ArrayList<CharWrapper> arr = new ArrayList<CharWrapper>();
			Random rng = new Random(749);
			for (int i = 0; i < 100; i++) {
				arr.add(new CharWrapper((char) (rng.nextInt(26) + 'a')));
			}
			bst.addAll(arr);
			TreeSet<CharWrapper> set = new TreeSet<CharWrapper>();
			set.addAll(arr); // remove duplicates
	
			BSTTestsPassed += assertEquals(bst.removeAll(set) && bst.size() == 0, true);
		}
	
		public void testMedRightHeavyBST0() {
			msg = "addAll(1 to 100) on empty tree";
			
			SortedSet<Integer> bst = new BinarySearchTree<Integer>();
			ArrayList<Integer> arr = new ArrayList<Integer>();
			for (int i = 0; i < 100; i++) {
				arr.add(i + 1);
			}
	
			BSTTestsPassed += assertEquals(bst.addAll(arr), true);
		}
	
		public void testMedRightHeavyBST1() {
			msg = "add(50) on medium tree";
			
			SortedSet<Integer> bst = new BinarySearchTree<Integer>();
			ArrayList<Integer> arr = new ArrayList<Integer>();
			for (int i = 0; i < 100; i++) {
				arr.add(i + 1);
			}
			bst.addAll(arr);
	
			BSTTestsPassed += assertEquals(bst.add(50), false);
		}
	
		public void testMedRightHeavyBST2() {
			msg = "containsAll(1 to 100) on medium tree";
			
			SortedSet<Integer> bst = new BinarySearchTree<Integer>();
			ArrayList<Integer> arr = new ArrayList<Integer>();
			for (int i = 0; i < 100; i++) {
				arr.add(i + 1);
			}
			bst.addAll(arr);
	
			arr = new ArrayList<Integer>();
			for(int i=100; i > 0; i--) {
				//recreate in hopes of getting new memory addresses, defeat ==
				arr.add(new Integer(i));
			}
			BSTTestsPassed += assertEquals(bst.containsAll(arr), true);
		}
	
		public void testMedRightHeavyBST3() {
			msg = "contains(101) on medium tree";
			
			SortedSet<Integer> bst = new BinarySearchTree<Integer>();
			ArrayList<Integer> arr = new ArrayList<Integer>();
			for (int i = 0; i < 100; i++) {
				arr.add(i + 1);
			}
			bst.addAll(arr);
	
			BSTTestsPassed += assertEquals(bst.contains(101), false);
		}
	
		public void testMedRightHeavyBST4() {
			msg = "first() on medium tree";
			
			SortedSet<Integer> bst = new BinarySearchTree<Integer>();
			ArrayList<Integer> arr = new ArrayList<Integer>();
			for (int i = 0; i < 100; i++) {
				arr.add(i + 1);
			}
			bst.addAll(arr);
	
			BSTTestsPassed += assertEquals(bst.first(), 1);
		}
	
		public void testMedRightHeavyBST5() {
			msg = "last() on medium tree";
			
			SortedSet<Integer> bst = new BinarySearchTree<Integer>();
			ArrayList<Integer> arr = new ArrayList<Integer>();
			for (int i = 0; i < 100; i++) {
				arr.add(i + 1);
			}
			bst.addAll(arr);
	
			BSTTestsPassed += assertEquals(bst.last(), 100);
		}
	
		public void testMedLeftHeavyBST0() {
			msg = "addAll(100 to 1) on empty tree";
			
			SortedSet<Integer> bst = new BinarySearchTree<Integer>();
			ArrayList<Integer> arr = new ArrayList<Integer>();
			for (int i = 100; i > 0; i--) {
				arr.add(i);
			}
	
			BSTTestsPassed += assertEquals(bst.addAll(arr), true);
		}
	
		public void testMedLeftHeavyBST1() {
			msg = "add(13) on medium tree";
			
			SortedSet<Integer> bst = new BinarySearchTree<Integer>();
			ArrayList<Integer> arr = new ArrayList<Integer>();
			for (int i = 100; i > 0; i--) {
				arr.add(i);
			}
			bst.addAll(arr);
	
			BSTTestsPassed += assertEquals(bst.add(13), false);
		}
	
		public void testMedLeftHeavyBST2() {
			msg = "containsAll(100 to 1) on medium tree";
			
			SortedSet<Integer> bst = new BinarySearchTree<Integer>();
			ArrayList<Integer> arr = new ArrayList<Integer>();
			for (int i = 100; i > 0; i--) {
				arr.add(i);
			}
			bst.addAll(arr);
	
			arr = new ArrayList<Integer>();
			for(int i=1; i < 101; i++) {
				//recreate in hopes of getting new memory addresses, defeat ==
				arr.add(new Integer(i));
			}
			BSTTestsPassed += assertEquals(bst.containsAll(arr), true);
		}
	
		public void testMedLeftHeavyBST3() {
			msg = "contains(101) on medium tree";
			
			SortedSet<Integer> bst = new BinarySearchTree<Integer>();
			ArrayList<Integer> arr = new ArrayList<Integer>();
			for (int i = 100; i > 0; i--) {
				arr.add(i);
			}
			bst.addAll(arr);
	
			BSTTestsPassed += assertEquals(bst.contains(101), false);
		}
	
		public void testMedLeftHeavyBST4() {
			msg = "first() on medium tree";
			
			SortedSet<Integer> bst = new BinarySearchTree<Integer>();
			ArrayList<Integer> arr = new ArrayList<Integer>();
			for (int i = 100; i > 0; i--) {
				arr.add(i);
			}
			bst.addAll(arr);
	
			BSTTestsPassed += assertEquals(bst.first(), 1);
		}
	
		public void testMedLeftHeavyBST5() {
			msg = "last() on medium tree";
			
			SortedSet<Integer> bst = new BinarySearchTree<Integer>();
			ArrayList<Integer> arr = new ArrayList<Integer>();
			for (int i = 100; i > 0; i--) {
				arr.add(i);
			}
			bst.addAll(arr);
	
			BSTTestsPassed += assertEquals(bst.last(), 100);
		}
		
		public ArrayList<Integer> setupLgBalancedList() {
			int[] powersOfTwo = { 1, 2, 4, 8, 16, 32, 64, 128, 256, 512 };
			ArrayList<Integer> arr = new ArrayList<Integer>();
	
			for (int i = 0; i < powersOfTwo.length; i++) {
				int x = powersOfTwo[powersOfTwo.length - 1 - i];
				arr.add(x);
				for (int j = 1; j < powersOfTwo[i]; j++) {
					x += powersOfTwo[powersOfTwo.length - i];
					arr.add(x);
				}
			}
			
			return arr;
		}
	
		public void testLgBalancedBST0() {
			msg = "addAll(1 to 1023 balanced) on empty tree";
			
			SortedSet<Integer> bst = new BinarySearchTree<Integer>();
			ArrayList<Integer> arr = setupLgBalancedList();
	
			BSTTestsPassed += assertEquals(bst.addAll(arr), true);
		}
	
		public void testLgBalancedBST1() {
			msg = "add(256) on large tree";
			
			SortedSet<Integer> bst = new BinarySearchTree<Integer>();
			ArrayList<Integer> arr = setupLgBalancedList();
			bst.addAll(arr);
	
			BSTTestsPassed += assertEquals(bst.add(256), false);
		}
	
		public void testLgBalancedBST2() {
			msg = "containsAll(1 to 1023) on large tree";
			
			SortedSet<Integer> bst = new BinarySearchTree<Integer>();
			ArrayList<Integer> arr = setupLgBalancedList();
			bst.addAll(arr);
	
			BSTTestsPassed += assertEquals(bst.containsAll(arr), true);
		}
	
		public void testLgBalancedBST3() {
			msg = "contains(1024) on large tree";
			
			SortedSet<Integer> bst = new BinarySearchTree<Integer>();
			ArrayList<Integer> arr = setupLgBalancedList();
			bst.addAll(arr);
	
			BSTTestsPassed += assertEquals(bst.contains(1024), false);
		}
	
		public void testLgBalancedBST4() {
			msg = "first() on large tree";
			
			SortedSet<Integer> bst = new BinarySearchTree<Integer>();
			ArrayList<Integer> arr = setupLgBalancedList();
			bst.addAll(arr);
	
			BSTTestsPassed += assertEquals(bst.first(), 1);
		}
	
		public void testLgBalancedBST5() {
			msg = "last() on large tree";
			
			SortedSet<Integer> bst = new BinarySearchTree<Integer>();
			ArrayList<Integer> arr = setupLgBalancedList();
			bst.addAll(arr);
	
			BSTTestsPassed += assertEquals(bst.last(), 1023);
		}
	
		public void testSpellChecker0() {
			msg = "spell check \"hello_world.txt\" against \"dictionary.txt\"";
			
			SpellChecker sc = buildSpellChecker(filePath + "dictionary.txt");
	
			List<String> misspelledWords = sc.spellCheck(findFile(filePath + "hello_world.txt"));
			SpellCheckTestsPassed += assertEquals(misspelledWords.isEmpty(), true);
		}
	
		public void testSpellChecker1() {
			msg = "spell check \"good_luck.txt\" against \"dictionary.txt\"";
			
			SpellChecker sc = buildSpellChecker(filePath + "dictionary.txt");
	
			List<String> misspelledWords = sc.spellCheck(findFile(filePath + "good_luck.txt"));
			SpellCheckTestsPassed += assertEquals(misspelledWords.size() == 2
											   && misspelledWords.contains("bst")
											   && misspelledWords.contains("danny"), true);
		}
	
		public void testSpellChecker2() {
			msg = "spell check \"good_luck.txt\" against \"dictionary.txt\" + \"bst\"";
			
			SpellChecker sc = buildSpellChecker(filePath + "dictionary.txt");
			sc.addToDictionary("bst");
			
			List<String> misspelledWords = sc.spellCheck(findFile(filePath + "good_luck.txt"));
			SpellCheckTestsPassed += assertEquals(misspelledWords.size() == 1
											   && misspelledWords.contains("danny"), true);
		}
	
		public void testSpellChecker3() {
			msg = "spell check \"good_luck.txt\" against \"dictionary.txt\" + \"bst\" + \"danny\"";
			
			SpellChecker sc = buildSpellChecker(filePath + "dictionary.txt");
			sc.addToDictionary("bst");
			sc.addToDictionary("danny");
			
			List<String> misspelledWords = sc.spellCheck(findFile(filePath + "good_luck.txt"));
			SpellCheckTestsPassed += assertEquals(misspelledWords.size(), 0);
		}
	
		public void testSpellChecker4() {
			msg = "spell check \"hello_world.txt\" against \"dictionary.txt\" - \"hello\"";
			
			SpellChecker sc = buildSpellChecker(filePath + "dictionary.txt");
			sc.removeFromDictionary("hello");
			
			List<String> misspelledWords = sc.spellCheck(findFile(filePath + "hello_world.txt"));
			SpellCheckTestsPassed += assertEquals(misspelledWords.size() == 1
											   && misspelledWords.contains("hello"), true);
		}
	}
	
	/**
	 * Used as a marker for printing a relevant error message when a student's
	 * code is running infinitely.
	 */
	static class RunningInfinitelyException extends Exception {
		private static final long serialVersionUID = 1L;
		public String toString() {
			return "Running Infinitely";
		}
	}

	/***
	 * Actually run a test in another thread.
	 */
	@SuppressWarnings("deprecation")
	private static void runTest(Runnable r) {
		Thread t = new Thread(r);
		t.start();
		try {
			t.join(3000);
			if (t.isAlive()) {
				t.stop(new RunningInfinitelyException());
			}
		} catch (InterruptedException e) {
			System.err.println("Something went wrong with the grader, run again");
			System.exit(1);
		}
	}
	
	public static void main(String[] args) {
		System.out.println("--------------------------------------");
		System.out.println("--TA Assignment 8 Test Output--");
		System.out.println("--------------------------------------");
		System.out.println("--Testing BST--");

		for(int i=0; i < catNames.length-1; i++) {
			for(int j=0; j<testCounts[i]; j++) {
				runTest(new Tester(i, j));
			}
			//System.out.println();
		}

		System.out.println();
		System.out.println("--------------------------------------");
		System.out.println("--Testing Spell Checker--");

		int i = testCounts.length - 1; //spellchecker
		for(int j=0; j<testCounts[i]; j++) {
			runTest(new Tester(i, j));
		}

		System.out.println();
		System.out.println("--------------------------------------");
		System.out.println();
		System.out.println("--Scoring--");

		System.out.println("Successful BST:\t\t\t\t\t  "
						+ (int) (Math.ceil((double) BSTTestsPassed / BSTTestCount * 40)) + "/40");

		System.out.println("Successful Spell Checker:\t\t\t  "
				+ (int) ((double) SpellCheckTestsPassed / SpellCheckTestCount * 25) + "/25");
		System.out.println("Quality of student tests and overall style:\t    /10");
		System.out.println();
		System.out.println("Total: \t\t\t\t\t\t    /75");
		System.out.println();
		System.out.println("--------------------------------------");
		System.out.println();
		System.out.println("TA Comments:");
	}
	
	private static File findFile(String fileName) {
		URL url = BSTGrader.class.getClassLoader().getResource(fileName);
		if(url != null) {
			return new File(url.getPath());
		}
		return new File(fileName);
	}
	
	private static SpellChecker buildSpellChecker(String fileName) {
		return new SpellChecker(findFile(fileName));
	}
}
