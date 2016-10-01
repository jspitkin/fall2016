package assignment05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

//mergesort timing tests
/**
 * 
 * @author Benjamin Allred u1090524
 * @author Amir Mohsenian u0737564
 */
public class SortUtilTimingTests {

	@SuppressWarnings("unused")
	public static void main (String[] argmatey)
	{
		timeMergeSort();
		quickSortTiming();
		timeMergeVsQuick();

	}

	public static void timeMergeSort()
	{
		long startTime, midpointTime, stopTime;
		ArrayList<Integer> intList;
		IntegerComparator intComp = new IntegerComparator();
		Random rand = new Random();

		// First, spin computing stuff until one second has gone by.
		// This allows this thread to stabilize.

		startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1000000000) { // empty block
		}

		//set seed to ensure same permuted order
		SortUtil.setSeed(1234208209786L);

		for(int mergeThresh = 0; mergeThresh < 60000; mergeThresh *= 2)
		{
			//set merge threshold
			SortUtil.setMergeThreshold(mergeThresh);
			System.out.println("MergeThreshold: " + mergeThresh);
			for(int exp = 5; exp < 14; exp++)
			{
				//setup ArrayList
				int size = (int) Math.pow(2, exp);
				intList = SortUtil.generateAverageCase(size);

				//start timing
				startTime = System.nanoTime();

				SortUtil.mergesort(intList, intComp);

				//stop timing
				stopTime = System.nanoTime();

				double inMili = (stopTime - startTime) / 1000000;

				//print result
				System.out.println(size + "\t" + inMili);
			}
			System.out.println("\n\n\n");
			if(mergeThresh == 0)
			{
				mergeThresh = 2;
			}
		}
	}

	public static void timeMergeVsQuick()
	{
		long startTime, midpointTime, stopTime;
		ArrayList<Integer> intList;
		IntegerComparator intComp = new IntegerComparator();
		Random rand = new Random();

		// First, spin computing stuff until one second has gone by.
		// This allows this thread to stabilize.

		startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1000000000) { // empty block
		}

		//set seed to ensure same permuted order
		SortUtil.setSeed(1234208209786L);

		//set merge threshold
		SortUtil.setMergeThreshold(512);
		SortUtil.setQuickThreshold(2048);
		
		System.out.println("QuickSort - Average Case");
		for(int exp = 5; exp < 14; exp++)
		{
			//setup ArrayList
			int size = (int) Math.pow(2, exp);
			intList = SortUtil.generateAverageCase(size);
			SortUtil.setPivot(intList.get(size/2));

			//start timing
			startTime = System.nanoTime();

			SortUtil.quicksort(intList, intComp);

			//stop timing
			stopTime = System.nanoTime();

			double inMili = (stopTime - startTime) / 1000000;

			//print result
			System.out.println(size + "\t" + inMili);
		}
		System.out.println("\n\n\n");
		
		System.out.println("QuickSort - Best Case");
		for(int exp = 5; exp < 14; exp++)
		{
			//setup ArrayList
			int size = (int) Math.pow(2, exp);
			intList = SortUtil.generateBestCase(size);
			SortUtil.setPivot(intList.get(size/2));

			//start timing
			startTime = System.nanoTime();

			SortUtil.quicksort(intList, intComp);

			//stop timing
			stopTime = System.nanoTime();

			double inMili = (stopTime - startTime) / 1000000;

			//print result
			System.out.println(size + "\t" + inMili);
		}
		System.out.println("\n\n\n");
		
		System.out.println("QuickSort - Worst Case");
		for(int exp = 5; exp < 14; exp++)
		{
			//setup ArrayList
			int size = (int) Math.pow(2, exp);
			intList = SortUtil.generateWorstCase(size);
			SortUtil.setPivot(intList.get(rand.nextInt(size)));

			//start timing
			startTime = System.nanoTime();

			SortUtil.quicksort(intList, intComp);

			//stop timing
			stopTime = System.nanoTime();

			double inMili = (stopTime - startTime) / 1000000;

			//print result
			System.out.println(size + "\t" + inMili);
		}
		System.out.println("\n\n\n");
		
		System.out.println("MergesSort - Average Case");
		for(int exp = 5; exp < 14; exp++)
		{
			//setup ArrayList
			int size = (int) Math.pow(2, exp);
			intList = SortUtil.generateAverageCase(size);

			//start timing
			startTime = System.nanoTime();

			SortUtil.mergesort(intList, intComp);

			//stop timing
			stopTime = System.nanoTime();

			double inMili = (stopTime - startTime) / 1000000;

			//print result
			System.out.println(size + "\t" + inMili);
		}
		System.out.println("\n\n\n");

		System.out.println("MergesSort - Worst Case");
		for(int exp = 5; exp < 14; exp++)
		{
			//setup ArrayList
			int size = (int) Math.pow(2, exp);
			intList = SortUtil.generateWorstCase(size);

			//start timing
			startTime = System.nanoTime();

			SortUtil.mergesort(intList, intComp);

			//stop timing
			stopTime = System.nanoTime();

			double inMili = (stopTime - startTime) / 1000000;

			//print result
			System.out.println(size + "\t" + inMili);
		}
		System.out.println("\n\n\n");

		System.out.println("MergesSort - Best Case");
		for(int exp = 5; exp < 14; exp++)
		{
			//setup ArrayList
			int size = (int) Math.pow(2, exp);
			intList = SortUtil.generateBestCase(size);

			//start timing
			startTime = System.nanoTime();

			SortUtil.mergesort(intList, intComp);

			//stop timing
			stopTime = System.nanoTime();

			double inMili = (stopTime - startTime) / 1000000;

			//print result
			System.out.println(size + "\t" + inMili);
		}
		System.out.println("\n\n\n");
	
	}

	public static void quickSortTiming()
	{
		long startTime;
		long midpointTime, stopTime;
		ArrayList<Integer> intList;
		ArrayList<Integer> intList2;
		IntegerComparator intComp = new IntegerComparator();
		Random rand = new Random();

		// First, spin computing stuff until one second has gone by.
		// This allows this thread to stabilize.

		startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1000000000) { // empty block
		}

		//set seed to ensure same permuted order
		SortUtil.setSeed(1234208209786L);

		//SortUtil.setPivot(intList.size()/2);
		SortUtil.setQuickThreshold(0);
		//set quick threshold
		System.out.println("Pivot Middle");
		for(int exp = 5; exp < 17; exp++)
		{
			//setup ArrayList
			int size = (int) Math.pow(2, exp);
			intList = SortUtil.generateAverageCase(size);
			SortUtil.setPivot(intList.get(size/2));

			//start timing
			startTime = System.nanoTime();

			SortUtil.quicksort(intList, intComp);

			//stop timing
			stopTime = System.nanoTime();

			double inMili = (stopTime - startTime) / 1000000;

			//print result
			System.out.println(size + "\t" + inMili);
		}
		System.out.println("\n\n\n");


		System.out.println("Random Pivot");

		for(int exp = 5; exp < 17; exp++)
		{
			//setup ArrayList
			int size = (int) Math.pow(2, exp);
			intList = SortUtil.generateAverageCase(size);

			SortUtil.setPivot((int)Math.random()*size);

			//start timing
			startTime = System.nanoTime();

			SortUtil.quicksort(intList, intComp);

			//stop timing
			stopTime = System.nanoTime();

			double inMili = (stopTime - startTime) / 1000000;

			//print result
			System.out.println(size + "\t" + inMili);
		}
		System.out.println("\n\n\n");



		System.out.println("Pivot Medium");
		for(int exp = 5; exp < 17; exp++)
		{
			//setup ArrayList
			int size = (int) Math.pow(2, exp);
			intList = SortUtil.generateAverageCase(size);

			intList2 = SortUtil.generateAverageCase(size);


			//SortUtil.setPivot((int)Math.random()*size);

			//start timing
			startTime = System.nanoTime();
			Collections.sort(intList);
			SortUtil.setPivot(intList.get(intList.size()/2));
			SortUtil.quicksort(intList2, intComp);

			//middle time
			midpointTime = System.nanoTime();
			SortUtil.setPivot(intList.get(intList.size()/2));

			//stop timing
			stopTime = System.nanoTime();

			double inMili = ((midpointTime - startTime) - (stopTime - midpointTime)) / 2000000;

			//print result
			System.out.println(size + "\t" + inMili);
		}
		System.out.println("\n\n\n");

	}
}
