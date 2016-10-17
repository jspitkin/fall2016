package assignment05;

import java.util.ArrayList;
import java.util.Comparator;

import junit.framework.TestCase;

/**
 * @author Noah Goetz
 * @authorUID u1046618
 * @partner Zhangtuoming Zhao
 * @partnerUID u0847610
 */


public class SortUtilJUTest extends TestCase {

	private ArrayList<Integer> numberList1 = new ArrayList<Integer>();
	private ArrayList<Integer> numberList2 = new ArrayList<Integer>();
	private ArrayList<Integer> numberList3 = new ArrayList<Integer>();
	
	private ArrayList<Character> charList1 = new ArrayList<Character>();
	private ArrayList<Character> charList2 = new ArrayList<Character>();
	private ArrayList<Character> charList3 = new ArrayList<Character>();

	private ArrayList<String> stringList1 = new ArrayList<String>();
	private ArrayList<String> stringList2 = new ArrayList<String>();
	private ArrayList<String> stringList3 = new ArrayList<String>();
	
	/**
	 * this class is going to make a interger comparator for the Tester
	 * 
	 *
	 */
	public class IntegerComparator implements Comparator<Integer> {
		/**
		 * this is a comparator for comparing the integer
		 */
		public int compare(Integer objectInteger1, Integer objectInteger2) {

			return objectInteger1.compareTo(objectInteger2);
		}

	}

	public class StringComparator implements Comparator<String> {
		/**
		 * the method is OrderByAuthor from assignment02. Which is a comparator
		 * for comparing the String
		 * 
		 * @param ojectString1
		 * @param objectString2
		 * @return
		 */
		public int compare(String ojectString1, String objectString2) {

			return (int) (ojectString1.compareTo(objectString2));

		}

	}

//	public class CharComparator implements Comparator<String> {
//		/**
//		 * the method is OrderByAuthor from assignment02. Which is a comparator
//		 * for comparing the String
//		 * 
//		 * @param ojectString1
//		 * @param objectString2
//		 * @return
//		 */
//		public int compare(char c1, char c2) {
//
//			return (int) (c1-(c2));
//
//		}
//
//	}
	
	IntegerComparator cmp1 = new IntegerComparator();
	StringComparator cmp2 = new StringComparator();
//	CharComparator cmp3 = new CharComparator();
	
	
	public void setUp()  throws Exception{
		super.setUp();
		
		// sorted number of list which we expected.
		int[] Arr1 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 22, 33, 77, 99, 99, 100 };
		for (int element : Arr1) {
			numberList1.add(element);
		}
		
		//random number of list:
		int[] Arr2 = { 2, 7, 3, 8, 6, 5, 1, 10, 4, 9, 100, 99, 22, 33, 99, 77 };
		for (int element : Arr2) {
			numberList2.add(element);
		}		
		
		//reversed number of list:
		int[] Arr3 = { 100, 99, 99, 77, 33, 22, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
		for (int element : Arr3) {
			numberList3.add(element);
		}
		
		// sorted character of list which we expected.
		char[] Arr4 = { 'a','a','a','a','b','b','d','e','g','h','l','m','o','p','s','t','z' };
		for (int i=0; i< Arr4.length;i++ ) {
			charList1.add(Arr4[i]);
		}
		
		//random character of list:
		char[] Arr5 = { 's','a','m','z','b','p','e','l','a','o','d','h','t','a','g','b','a' };
		for (int i=0; i< Arr5.length;i++ ) {
			charList2.add(Arr5[i]);
		}
		
		//reversed character of list:
		char[] Arr6 = { 'a','a','a','a','b','b','d','e','g','h','l','m','o','p','s','t','z' };
		for (int i=0; i< Arr6.length;i++ ) {
			charList3.add(Arr6[Arr6.length-1-i]);
		}
		
		//sorted strings of list which we expected.
		String[] Arr7 = {"112233", "aaa", "asd", "asvh", "awfg", "aza", "ccc", "dvtg", "h", "mmm", "mmop", "ooo", "ppp", "qwe", "sdf", "sss", "wer", "xx", "yy", "za", "zxc"};
		for (int i=0; i< Arr7.length;i++ ) {
			stringList1.add(Arr7[i]);
		}
		
		
		
		
		//random string of list.		
		stringList2.add("yy");
		stringList2.add("sdf");
		stringList2.add("dvtg");
		stringList2.add("aaa");
		stringList2.add("ccc");
		stringList2.add("h");
		stringList2.add("aza");
		stringList2.add("awfg");
		stringList2.add("za");
		stringList2.add("asvh");
		stringList2.add("ooo");
		stringList2.add("ppp");
		stringList2.add("sss");
		stringList2.add("mmm");
		stringList2.add("112233");
		stringList2.add("zxc");
		stringList2.add("mmop");
		stringList2.add("wer");
		stringList2.add("qwe");
		stringList2.add("asd");
		stringList2.add("xx");
		
		
		//reversed string of list:
		String[] Arr9 = {"112233", "aaa", "asd", "asvh", "awfg", "aza", "ccc", "dvtg", "h", "mmm", "mmop", "ooo", "ppp", "qwe", "sdf", "sss", "wer", "xx", "yy", "za", "zxc"};
		for (int i=0; i< Arr9.length;i++ ) {
			stringList3.add(Arr9[i]);
		}
		
		
	}

	
	//test for merge sort
	//sorting for int numbers:
	public void testMergeSort1() {
		SortUtil.mergesort(numberList2,cmp1);
		assertEquals(numberList1, numberList2);
	}
	
	public void testMergeSort2() {
		SortUtil.mergesort(numberList3,cmp1);
		assertEquals(numberList1, numberList3);
	}

//	//sorting for characters:
//	public void testMergeSort3() {
//		SortUtil.mergesort(charList2,cmp3);
//		assertEquals(charList1, charList2);
//	}
//	
//	public void testMergeSort4() {
//		SortUtil.mergesort(charList3,cmp3);
//		assertEquals(charList1, charList3);
//	}
	
	//sorting for strings:
	public void testMergeSort5() {
		SortUtil.mergesort(stringList2,cmp2);
		assertEquals(stringList1, stringList2);
	}
	
	public void testMergeSort6() {
		SortUtil.mergesort(stringList3,cmp2);
		assertEquals(stringList1, stringList3);
	}
	

	
	//testing for quick sort
	//sorting for int numbers:
	public void testQuickSort1() {
		SortUtil.quicksort(numberList2,cmp1);
		assertEquals(numberList1, numberList2);
	}
	
	public void testQuickSort2() {
		SortUtil.quicksort(numberList3,cmp1);
		assertEquals(numberList1, numberList3);
	}

//	sorting for characters:
//	public void testQuickSort3() {
//		SortUtil.quicksort(charList2,cmp2);
//		assertEquals(charList1, charList2);
//	}
//	
//	public void testQuickSort4() {
//		SortUtil.quicksort(charList3,cmp2);
//		assertEquals(charList1, charList3);
//	}
	
	//sorting for strings:
	public void testQuickSort5() {
		SortUtil.quicksort(stringList2,cmp2);
		assertEquals(stringList1, stringList2);
	}
	
	public void testQuickSort6() {
		SortUtil.quicksort(stringList3,cmp2);
		assertEquals(stringList1, stringList3);
	}
	
	
	
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
