package assignment05;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

/**
 * 
 * @author Benjamin Allred u1090524
 * @author Amir Mohsenian u0737564
 */
public class SortUtil {

	//threshold to switch mergesort to insertion sort
	private static int mergeThresh = 0;
	//threshold to switch quicksort to insertion sort
	private static int quickThresh =0;
	//used for generating consistent averageCase
	private static long seed = 0;
	//pivot for quicksort
	private static long pivotPoint =0;

	/**
	 * driver method for the merge method
	 * @param list
	 * @param comp
	 */
	public static <T> void mergesort(ArrayList<T> list, Comparator<? super T> comp)
	{
		//allocate tmpList only once
		ArrayList<T> tmpList = new ArrayList<T>();
		//beginn recursive method
		splitUp(list, tmpList, comp, 0, list.size() - 1);
	}

	/**
	 * the recursive method to mergesort that divides the arrays into subarrays
	 * @param list
	 * @param comp
	 * @param start
	 * @param end
	 */
	private static <T> void splitUp(ArrayList<T> list, ArrayList<T> tmpList, Comparator<? super T> comp, int start, int end)
	{
		//base case
		if(start >= end)
		{
			return;
		}
		//split up array into smaller pieces
		int middle = (start + end) / 2;
		splitUp(list, tmpList, comp, start, middle);
		splitUp(list, tmpList, comp, middle + 1, end);
		//switch to insertion sort if array sublist is small enough 
		if(end - start <= mergeThresh)
		{
			insertionSort(list, comp, start, end);
		}
		//else move onto mergesort
		else
		{
			merge(list, tmpList, comp, start, middle, end);
		}


	}

	/**
	 * the conquer part of mergesort that compares two subarrays and sorts them
	 * @param list
	 * @param comp
	 * @param start
	 * @param middle
	 * @param end
	 */
	private static <T> void merge(ArrayList<T> list, ArrayList<T> tmpList, Comparator<? super T> comp, int start, int middle, int end)
	{
		//add contents to tmpList
		tmpList.addAll(list);
		int value_1 = start;
		int value_2 = middle + 1;
		int index = start;
		while(value_1 <= middle && value_2 <= end)
		{
			if(comp.compare(tmpList.get(value_2), tmpList.get(value_1)) <= 0)
			{
				list.set(index, tmpList.get(value_2));
				value_2++;
			}
			else
			{
				list.set(index, tmpList.get(value_1));
				value_1++;
			}
			index++;
		}
		while(value_1 <= middle)
		{
			list.set(index, tmpList.get(value_1));
			value_1++;
			index++;
		}
		//clear tmpList for next merge
		tmpList.clear();
	}

	/**
	 * an insertionSort that is called when the subarrays created in the divide step get to the size of the mergeThresh field
	 * @param list
	 * @param comp
	 * @param start
	 * @param end
	 */
	private static <T> void insertionSort(ArrayList<T> list, Comparator<? super T> comp, int start, int end)
	{
		for(int sorted = start + 1; sorted < end + 1; sorted++)
		{
			T index = list.get(sorted);
			int element = sorted;
			for(; element > start && comp.compare(list.get(element - 1), index) > 0; element--)
			{
				list.set(element, list.get(element - 1));
			}
			list.set(element, index);
		}
	}

	/**
	 * 
	 * The quicksort method
	 * 
	 * @param ray
	 * @param compare
	 */
	public static <T> void quicksort(ArrayList<T> ray, Comparator<? super T> compare)
	{

		//if the array size is less than or equal to one move one
		if(ray.size()<=1)
		{
			return;
		}

		//quicksort divider. Use recursion to dive array and sort "dive and conquer"
		quicksortDivider(ray, 0, ray.size()-1, compare);
	}


	/**
	 * Divides and splits up the array so it can later call to sort
	 * @param ray
	 * @param leftSide
	 * @param rightSide
	 * @param compare
	 */
	private static<T> void quicksortDivider(ArrayList<T> ray, int leftSide, int rightSide, Comparator<? super T> compare)
	{
		if(quickThresh+leftSide > rightSide)
		{
			insertionSort(ray,compare, leftSide, rightSide);
		}

		else

		{
			//Split up the pieces
			breakUp(ray, leftSide, rightSide, compare);

			T mainPivot = ray.get(rightSide);

			int leftPiece;
			leftPiece = leftSide-1;
			int rightPiece;
			rightPiece= rightSide;

			while(true)
			{
				for(@SuppressWarnings("unused")
				int index =0;leftPiece < rightSide && compare.compare(ray.get(++leftPiece),(mainPivot)) < 0; index++)
				{

				}
				for(@SuppressWarnings("unused")
				int index =0; rightPiece > leftSide && compare.compare((ray.get(--rightPiece)), mainPivot) > 0; index++)
				{

				}
				if(leftPiece > rightPiece || leftPiece == rightPiece)
				{
					break;
				}

				changeAround(ray, leftPiece, rightPiece);
			}


			changeAround(ray, leftPiece, rightSide);


			quicksortDivider(ray, leftSide, leftPiece - 1, compare);


			quicksortDivider(ray, leftPiece + 1, rightSide, compare);

		}
	}


	/**
	 * Breaks up the array so it can later be sorted
	 * @param ray
	 * @param leftSide
	 * @param rightSide
	 * @param compare
	 */
	private static<T> void breakUp(ArrayList<T> ray, int leftSide, int rightSide, Comparator<? super T> compare)
	{
		int midPoint;
		midPoint = leftSide + (rightSide-leftSide)/2;
		if(pivotPoint ==0)
		{
			changeAround(ray, midPoint, rightSide);	

		}

		else if (pivotPoint ==1)
		{
			int oneFourth;
			oneFourth= leftSide + (rightSide-leftSide/4);
			changeAround(ray, oneFourth, rightSide);

		}

		else if (pivotPoint ==2);

		@SuppressWarnings("unused")
		boolean case1 =false;
		if(compare.compare(ray.get(midPoint), ray.get(leftSide))<0)
		{
			case1=true;
		}

		if(case1=true)
		{
			changeAround(ray, leftSide, midPoint);
		}



		@SuppressWarnings("unused")
		boolean case2=false;
		if(compare.compare(ray.get(rightSide), (ray.get(leftSide)))<0)
		{
			case2 =true;
		}

		if(case2=true)
		{
			changeAround(ray, leftSide, rightSide);
		}



		@SuppressWarnings("unused")
		boolean case3=false;
		if(compare.compare(ray.get(rightSide), ray.get(midPoint))<0)
		{
			case3=true;
		}

		if(case3=true)
		{
			changeAround(ray, midPoint, rightSide);

		}
		else

			changeAround(ray, midPoint, rightSide);

	}

	/**
	 * generates an ArrayList of Integers in ascending order
	 * @param size
	 * @return
	 */
	public static ArrayList<Integer> generateBestCase(int size)
	{
		ArrayList<Integer> bestCase = new ArrayList<Integer>();
		for(int number = 0; number < size; number++)
		{
			bestCase.add(number);
		}
		return bestCase;
	}

	/**
	 * generates an ArrayList of Integers in permuted order depending on the seed
	 * @param size
	 * @return
	 */
	public static ArrayList<Integer> generateAverageCase(int size)
	{
		ArrayList<Integer> averageCase = new ArrayList<Integer>();
		Random rand = new Random();
		rand.setSeed(seed);
		for(int number = 0; number < size; number++)
		{
			int randNumber = rand.nextInt(size*10);
			if(averageCase.contains(randNumber))
			{
				number--;
			}
			else
			{
				averageCase.add(randNumber);
			}
		}
		return averageCase;
	}

	/**
	 * generates an ArrayList of Integers in reverse order
	 * @param size
	 * @return
	 */
	public static ArrayList<Integer> generateWorstCase(int size)
	{
		ArrayList<Integer> worstCase = new ArrayList<Integer>();
		Random rand = new Random();
		for(int number = size - 1; number >= 0; number--)
		{
			worstCase.add(number);
		}
		return worstCase;
	}


	/**
	 * switches the places 
	 * @param ray
	 * @param first
	 * @param second
	 */
	private static<T> void changeAround (ArrayList<T> ray, int first, int second)
	{
		T holder;
		holder = ray.get(first);
		ray.set(first, ray.get(second));
		ray.set(second,holder);


	}

	/**
	 * lets user set the mergeThresh class field
	 * @param newThresh
	 */
	public static void setMergeThreshold(int newThresh)
	{
		mergeThresh = newThresh;
	}

	/**
	 * lets user set the quickThress class field
	 * @param newThresh
	 */
	public static void setQuickThreshold (int newThresh)
	{
		quickThresh=newThresh;

	}

	/**
	 * lets user set the seed class field
	 * @param newThresh
	 */
	public static void setSeed(long newSeed)
	{
		seed = newSeed;
	}

	/**
	 * lets user set the pivot point for quicksort
	 * @param newPivot
	 */
	public static void setPivot (long newPivot)
	{
		pivotPoint=newPivot;

	}

}
