package assignment06;

import java.util.Iterator;
/**
 * 
 * @author Amir Mohsenian
 * 
 * u0737564
 *
 */
public class DoublyLinkedListTiming 
{
public static void main (String[]args)
{
	
	long startTime;
	long midTime;
	long endTime;
	long finalTime;
	
	DoublyLinkedList<Integer> numberList;

	DoublyLinkedList<String> stringList;

	Iterator<Integer> iterates;


	DoublyLinkedList<Integer> eleOne;

	
		stringList = new DoublyLinkedList<String>();



		numberList = new DoublyLinkedList<Integer>();

		//Used loop for timing add
		Integer index =0;
		while(index < 1_000_000)
		{
			startTime = System.nanoTime();
			numberList.addLast(index);

			endTime = System.nanoTime();
			index = index+1;
			
			finalTime = endTime-startTime;
			if(index%1000 ==0)
			System.out.println("Adds size to time is:  " +  index+ " " + finalTime);
		}

		iterates = numberList.iterator();

		eleOne = new DoublyLinkedList<Integer>();

		eleOne.addFirst(1);
		
//Used loop for getting get
		//add items to a list
		for (int i = 0; i < 1_000_000; i++)
		{
			numberList.addLast(i);
		}
	
		//Loop for remove timing method
		for (int i = 0; i < 1_000_000; i++)
		{
			startTime = System.nanoTime();
			numberList.remove(i);

			endTime = System.nanoTime();
			finalTime = endTime-startTime;
			if(i%1000 ==0)
			System.out.println("Remove size to time is:  " +  i+ " " + finalTime);
		}

		//Loop for get timing method
		for (int i = 0; i < 1_000_000; i++)
		{
			startTime = System.nanoTime();
			System.out.println();
			numberList.get(i);

			endTime = System.nanoTime();
			finalTime = endTime-startTime;
			if(i%1000 ==0)
			System.out.println("Get size to time is:  " +  i+ " " + finalTime);
		}

		iterates = numberList.iterator();

		eleOne = new DoublyLinkedList<Integer>();

		eleOne.addFirst(1);

}
}