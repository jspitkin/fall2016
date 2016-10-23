package assignment06;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 * @author Amir Mohsenian
 * 
 * u0737564
 *
 */
public class DoublyLinkedListTest
{
	public DoublyLinkedList<Integer> numberList;

	public static DoublyLinkedList<String> stringList;

	public Iterator<Integer> iterates;


	DoublyLinkedList<Integer> eleOne;

	@BeforeClass
	public static void BeforeClass() 
	{
		stringList = new DoublyLinkedList<String>();
	}




	@Before
	public void BeforeSetup() throws Exception 
	{
		numberList = new DoublyLinkedList<Integer>();

		Integer index =0;
		while(index < 1_000)
		{
			numberList.addLast(index);

			index = index+1;
		}

		iterates = numberList.iterator();

		eleOne = new DoublyLinkedList<Integer>();

		eleOne.addFirst(1);

	}

	@Test (expected = NoSuchElementException.class)
	public void testEmptyListExceptionNoSuchElementException3()
	{
		stringList.getFirst();
	}


	@Test
	public void getMethods() 
	{
		numberList.addLast(1);
		assertEquals(true, numberList.getFirst().equals(0));
		assertEquals(true, numberList.get(9).equals(9));

	}


	@Test (expected = IndexOutOfBoundsException.class)
	public void testEmptyListExceptionIndexOutOfBoundsException()
	{
		stringList.get(12);
	}

	@Test
	public void removeFirstAndLast()
	{
		numberList.removeLast();
		assertEquals(true, numberList.getLast().equals(998));
	}

	@Test (expected = NoSuchElementException.class)
	public void testEmptyListExceptionNoSuchElementException1()
	{
		stringList.removeFirst();
	}

	@Test (expected = NoSuchElementException.class)
	public void testEmptyListExceptionNoSuchElementException2()
	{
		stringList.removeLast();
	}



	@Test (expected = NoSuchElementException.class)
	public void testEmptyListExceptionNoSuchElementException4()
	{
		stringList.getLast();
	}




	@Test
	public void getLastAndGetFirst()
	{
		DoublyLinkedList<Integer> List2;
		List2= new DoublyLinkedList<Integer>();

		List2.addFirst(0);

		assertEquals(true, List2.getFirst().equals(0));
		assertEquals(true, List2.getLast().equals(0));
	}


	@Test
	public void Clear()
	{
		numberList.clear();
		assertEquals(0, numberList.size());
		assertEquals(true, numberList.isEmpty());
	}

	@Test
	public void Size()
	{
		assertEquals(1000, numberList.size());
		assertEquals(0, stringList.size());
	}

	@Test
	public void toArray()
	{

		Object [] nodArr;
		nodArr= numberList.toArray();

		Object [] comparison;
		comparison= new Integer [nodArr.length];

		int index =0;
		while(index < comparison.length)
		{
			comparison [index] = index;

			index = index+1;
		}

		Assert.assertArrayEquals(nodArr, comparison);

		Object [] noArray;
		noArray= stringList.toArray();

		Assert.assertArrayEquals(new Object [0], noArray);
	}

}

