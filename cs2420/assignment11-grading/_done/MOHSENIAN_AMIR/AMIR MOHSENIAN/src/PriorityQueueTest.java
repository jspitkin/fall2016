package assignment11;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;
/**
 * 
 * @author Amir Mohsenian
 * u0737564
 *
 */
public class PriorityQueueTest {

	PriorityQueue<Integer> queueTest;
	
	ArrayList<Integer> list1; 
	
	Comparator<String> comparison; 
	
	@Before
	public void beforeClass() 
	{
		queueTest = new PriorityQueue<>();
		list1 = new ArrayList<>();
		
	
		int index = 0;
		while( index <= 100000)
		{
			list1.add(index);
			index++;
		}
		
		Collections.shuffle(list1);
		
		
		int index2 = 0;
		while( index2 <= 100000)
		{
			queueTest.add(list1.get(index2));
			index2++;
		}
		
		comparison = new Comparator<String>()
			{

				@Override
				public int compare(String other1, String other2) 
				{
					int other1Total;
					other1Total= 0;
					
					int other2Total;
					other2Total= 0;
					
					int index =0;
					while(index < other1.length())
					{
						other1Total += other1.charAt(index);
						index++;
					}
					
					int index2 = 0;
					while( index2 < other2.length())
					{
						other2Total += other2.charAt(index2);
						
						index2++;
					}
					
					if(other2Total > other1Total)
					{
						return 1;
					}
					else if(other2Total < other1Total)
					{
						return -1;
					}
					else
					{
						return 0;
					}
				}
			};
	}
	
	@Test
	public void findMin()  
	{	
		assertEquals(true, queueTest.findMin().equals(0));
		queueTest.deleteMin();
		assertEquals(true, queueTest.findMin().equals(1));
	
	}
	
	@Test
	public void deleteMin() 
	{
		int index = 0;
		while( index <=5000)
		{
			queueTest.deleteMin();
			index++;
		}
		assertEquals(true, queueTest.findMin().equals(5001));
	}
	


	@Test
	public void add()
	{ 
		PriorityQueue<Integer> queueTester;
		queueTester = new PriorityQueue<>();
		
		queueTester.add(10);
		queueTester.add(4);
		queueTester.add(6);
		queueTester.add(11);
		
		assertEquals(true, queueTester.findMin().equals(4));
	
	}
	
	
	
	@Test
	public void comparator()
	{ 
		PriorityQueue<String> queueTest;
		queueTest= new PriorityQueue<>(comparison);
		
		String [] stringArray = StringMaker.readFile("large_sample.txt");
		
		int index =0;
		while(index<3000)
		{
			queueTest.add(stringArray[index]);
			index++;
		}
		
		assertEquals(true, queueTest.findMin().equals("accommodativeness"));
	}
}
