package assignment07;

/**
 * 
 * Times push, pop, peek
 * 
 * @author Amir Mohsenian
 * 
 * u0737564
 *
 */
public class LinkedListStackTiming
{
public static void main (String[]args)
{
	
	long startTime;
	long endTime;
	long finalTime;
	
	LinkedListStack<Integer> numberList;

	

		numberList = new LinkedListStack<Integer>();

		//Used loop for timing peek
		Integer index =0;
		while(index < 1_000_000)
		{
			startTime = System.nanoTime();
			numberList.peek();

			endTime = System.nanoTime();
			index = index+1;
			
			finalTime = endTime-startTime;
			if(index%1000 ==0)
			System.out.println("Peek size to time is:  " +  index+ " " + finalTime);
		}

		
	
		//Loop for pop
		for (int i = 0; i < 1_000_000; i++)
		{
			startTime = System.nanoTime();
			numberList.pop();

			endTime = System.nanoTime();
			finalTime = endTime-startTime;
			if(i%1000 ==0)
			System.out.println("Pop size to time is:  " +  i+ " " + finalTime);
		}

		//Loop for push timing method
		for (int i = 0; i < 1_000_000; i++)
		{
			startTime = System.nanoTime();
			System.out.println();
			numberList.push(i);

			endTime = System.nanoTime();
			finalTime = endTime-startTime;
			if(i%1000 ==0)
			System.out.println("Push size to time is:  " +  i+ " " + finalTime);
		}


}
}