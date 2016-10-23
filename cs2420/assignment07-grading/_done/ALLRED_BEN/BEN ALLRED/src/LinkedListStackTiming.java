package assignment07;

import java.util.Random;

/**
 * Timing test for LinkedListStack's push, pop, and peek
 * 
 * @author Benjamin Allred u1090524
 *
 */
public class LinkedListStackTiming {

	public static void main (String[] args)
	{
		timePush();
		System.out.println("\n\n\n");
		
		timePop();
		System.out.println("\n\n\n");
		
		timePeek();
	}
	
	/**
	 * time push
	 */
	public static void timePush()
	{
		long startTime, stopTime;
		LinkedListStack<Integer> list = new LinkedListStack<Integer>();
		Random rand = new Random();

		// First, spin computing stuff until one second has gone by.
		// This allows this thread to stabilize.

		startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1000000000) { // empty block
		}
		System.out.println("Push: " );
		for(int exp = 5; exp < 18; exp++)
		{
			//setup size
			int size = (int) Math.pow(2, exp);

			//setup list
			for(int index = 0; index < size; index++)
			{
				list.push(rand.nextInt(900000));
			}

			//start timing
			startTime = System.nanoTime();
			//time push
			list.push(rand.nextInt(900000));

			//stop timing
			stopTime = System.nanoTime();

			double mili = (stopTime - startTime) / 1000000;

			//print result
			System.out.println(size + "\t" + mili);
		}
	}
	
	/**
	 * time pop
	 */
	public static void timePop()
	{
		long startTime, stopTime;
		LinkedListStack<Integer> list = new LinkedListStack<Integer>();
		Random rand = new Random();

		// First, spin computing stuff until one second has gone by.
		// This allows this thread to stabilize.

		startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1000000000) { // empty block
		}
		System.out.println("Pop: " );
		for(int exp = 5; exp < 18; exp++)
		{
			//setup size
			int size = (int) Math.pow(2, exp);

			//setup list
			for(int index = 0; index < size; index++)
			{
				list.push(rand.nextInt(900000));
			}

			//start timing
			startTime = System.nanoTime();
			//time push
			list.pop();

			//stop timing
			stopTime = System.nanoTime();

			double mili = (stopTime - startTime) / 1000000;

			//print result
			System.out.println(size + "\t" + mili);
		}
	}
	
	/**
	 * time peek
	 */
	public static void timePeek()
	{
		long startTime, stopTime;
		LinkedListStack<Integer> list = new LinkedListStack<Integer>();
		Random rand = new Random();

		// First, spin computing stuff until one second has gone by.
		// This allows this thread to stabilize.

		startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1000000000) { // empty block
		}
		System.out.println("Peek: " );
		for(int exp = 5; exp < 18; exp++)
		{
			//setup size
			int size = (int) Math.pow(2, exp);

			//setup list
			for(int index = 0; index < size; index++)
			{
				list.push(rand.nextInt(900000));
			}

			//start timing
			startTime = System.nanoTime();
			//time push
			list.peek();

			//stop timing
			stopTime = System.nanoTime();

			double mili = (stopTime - startTime) / 1000000;

			//print result
			System.out.println(size + "\t" + mili);
		}
	}

}
