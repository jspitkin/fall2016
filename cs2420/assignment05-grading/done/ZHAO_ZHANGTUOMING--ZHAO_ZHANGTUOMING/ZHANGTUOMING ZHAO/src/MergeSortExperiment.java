package assignment05;

import java.util.ArrayList;
import java.util.Comparator;
/**
 * @author Noah Goetz
 * @authorUID u1046618
 * 
 * @partner Zhangtuoming Zhao
 * @partnerUID u0847610
 */
public class MergeSortExperiment extends SortUtil {
	
	
	
	public static void main(String[] aegs) {
		
		/**
		 * this class is going to make a interger comparator for the Tester
		 * 
		 *
		 */
		 class IntegerComparator implements Comparator<Integer> {
			/**
			 * this is a comparator for comparing the integer
			 */
			public int compare(Integer objectInteger1, Integer objectInteger2) {

				return objectInteger1.compareTo(objectInteger2);
			}

		}

		 class StringComparator implements Comparator<String> {
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
		
		
		IntegerComparator cmp1 =  new IntegerComparator();
		StringComparator cmp2 = new StringComparator();
		
		
		
		int[] Arr = { 2, 7, 3, 8, 6, 5, 1, 10, 4, 9, 100, 99, 22, 33, 99, 77 };
		ArrayList<Integer> numberList = new ArrayList<Integer>();
		for (int element : Arr) {
			numberList.add(element);
		}
		mergesort(numberList,cmp1);
		System.out.println(numberList);

		
//		char[] Arr2 = { 's','a','m','z','b','p','e','l','a','o','d','h','t','a','g','b','a' };
//		ArrayList<Character> charList = new ArrayList<Character>();
//		for (int i=0; i< Arr2.length;i++ ) {
//			charList.add(Arr2[i]);
//		}
//		mergesort(charList,cmp);
//		System.out.println(charList);
		
		String[] Arr3 = {"aaa","sss","wer","sdf","dvtg","asvh","awfg","112233","mmop","qwe","asd","zxc","za","xx","yy", "ooo","ppp","aza","ccc","mmm","h",};
		ArrayList<String> StringList = new ArrayList<String>();
		for (int i=0; i< Arr3.length;i++ ) {
			StringList.add(Arr3[i]);
		}
		mergesort(StringList,cmp2);
		System.out.println(StringList);
		
	}

}
