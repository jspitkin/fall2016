package assignment06;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 * @author Jacob Virgin, u0832933
 *
 */
public class DoublyLinkedListTester {
	
	DoublyLinkedList<Integer> theList;
	String s0to24;
	String s24to0;
	String surprise100;
	String no14;
	long startTime;
	long endTime;
	double totalTime = 0.0;
	
	ArrayList<Integer> arrList;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		theList = new DoublyLinkedList<Integer>();
		
		s0to24 = new String();
		for(int i = 0; i < 25; i++){
			s0to24 += i + "\n";
		}
		
		s24to0 = new String();
		for(int i = 24; i >= 0; i--){
			s24to0 += i + "\n";
		}
		
		surprise100 = new String();
		for(int i = 0; i < 25; i++){
			if(i == 14){
				surprise100 += 100 + "\n";
			}
			surprise100 += i + "\n";
		}
		
		no14 = new String();
		for(int i = 0; i < 25; i++){
			if(i != 14){
				no14 += i + "\n";
			}
			
		}
		
		
		arrList = new ArrayList<Integer>();
		
	}

	@After
	public void tearDown() throws Exception {
	}

	
	
	@Test
	public void addFirstTester(){
		for(int i = 0; i < 25; i++){
			theList.addFirst(Integer.valueOf(i));
		}
		
		assertTrue(s24to0.equals(theList.toString()));
	}
	
	@Test
	public void addLastTester(){
		for(int i = 0; i < 25; i++){
			theList.addLast(Integer.valueOf(i));
		}
		
		assertTrue(s0to24.equals(theList.toString()));
	}
	
	@Test
	public void addTester(){
		for(int i = 0; i < 25; i++){
			theList.addLast(Integer.valueOf(i));
		}
		theList.add(14, 100);
		assertTrue(surprise100.equals(theList.toString()));
		
	}
	
	@Test
	public void removeTester(){
		System.out.println(no14);
		for(int i = 0; i < 25; i++){
			theList.addLast(Integer.valueOf(i));
		}
		theList.remove(14);
		assertTrue(no14.equals(theList.toString()));
		
	}
	
	@Test
	public void addFirstTimer(){
		for(int n = 5000; n <= 100000; n += 5000){
			for(int i = 0; i < n; i++){
				theList.addLast(Integer.valueOf(i));
			}
			for(int i = 0; i < 10; i++){
				startTime = System.nanoTime();
				theList.addFirst(i);
				endTime = System.nanoTime();
				totalTime += endTime - startTime;
			}
			totalTime /= 10.0;
			totalTime /= 1000000.0;
			//System.out.println(totalTime);

		}
		
	}
	
	
	@Test
	public void addArrayListTimer(){
		for(int n = 50000; n <= 1000000; n+=50000){
			for(int i = 0; i < n; i++){
				arrList.add(Integer.valueOf(i));
			}
			for(int avg = 0; avg < 10; avg++){
				startTime = System.nanoTime();
				arrList.add(0, Integer.valueOf(avg));
				endTime = System.nanoTime();
				totalTime += endTime - startTime;
			}
			totalTime /= 10.0;
			totalTime /= 1000000.0;
			//System.out.println(totalTime);
			
		}
		
	}
	
	@Test
	public void getTimer(){
		//System.out.println("Doubly Linked List -");
		for(int n = 5000; n <= 100000; n += 5000){
			for(int i = 0; i < n; i++){
				theList.addLast(Integer.valueOf(i));
			}
			for(int i = 0; i < 10; i++){
				Random rand = new Random();
				int number = rand.nextInt(n);
				startTime = System.nanoTime();
				theList.getLast();
				endTime = System.nanoTime();
				totalTime += endTime - startTime;
			}
			totalTime /= 10.0;
			totalTime /= 1000000.0;
			//System.out.println(totalTime);

		}
		
	}
	
	
	@Test
	public void getArrayListTimer(){
		//System.out.println("ArrayList -");
		for(int n = 5000; n <= 100000; n += 5000){
			for(int i = 0; i < n; i++){
				arrList.add(Integer.valueOf(i));
			}
			for(int i = 0; i < 10; i++){
				Random rand = new Random();
				int number = rand.nextInt(n);
				startTime = System.nanoTime();
				arrList.get(arrList.size() - 1);
				endTime = System.nanoTime();
				totalTime += endTime - startTime;
			}
			totalTime /= 10.0;
			totalTime /= 1000000.0;
			//System.out.println(totalTime);

		}
		
	}
	
	@Test
	public void removeTimer(){
		//System.out.println("Doubly Linked List -");
		for(int n = 5000; n <= 100000; n += 5000){
			for(int i = 0; i < n; i++){
				theList.addLast(Integer.valueOf(i));
			}
			for(int i = 0; i < 10; i++){
				Random rand = new Random();
				int number = rand.nextInt(n);
				startTime = System.nanoTime();
				theList.remove(number);
				endTime = System.nanoTime();
				totalTime += endTime - startTime;
			}
			totalTime /= 10.0;
			totalTime /= 1000000.0;
			//System.out.println(totalTime);

		}
		
	}
	
	
	@Test
	public void removeArrayListTimer(){
		//System.out.println("ArrayList -");
		for(int n = 5000; n <= 100000; n += 5000){
			for(int i = 0; i < n; i++){
				arrList.add(Integer.valueOf(i));
			}
			for(int i = 0; i < 10; i++){
				Random rand = new Random();
				int number = rand.nextInt(n);
				startTime = System.nanoTime();
				arrList.remove(number);
				endTime = System.nanoTime();
				totalTime += endTime - startTime;
			}
			totalTime /= 10.0;
			totalTime /= 1000000.0;
			//System.out.println(totalTime);

		}
		
	}
	
	
	
	
	
	
	

}
