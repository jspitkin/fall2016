package assignment06;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test class for the DoublyLinkedList class.
 * 
 * @author Calvin Kling
 *
 */
public class DoublyLinkedListTester {

	DoublyLinkedList<Integer> list;
	
	@Before
	public void setUp()
	{
		list = new DoublyLinkedList<Integer>();
	}
	
	@Test
	public void isEmptyTest() 
	{
		assertEquals("An newly constructed list should have 0 size: ", 0, list.size());
	}
	  
    @Test
    public void addAndIndexTest()
    {
    	DoublyLinkedList<String> stringList = new DoublyLinkedList<String>();
        stringList.addFirst("Hello");
        
        assertEquals("The list should contain 'Hello' as the head: ", 0, stringList.indexOf("Hello"));  
    }
    
    @Test
    public void removeTest()
    {
    	Integer[] array = new Integer[]{0, 1, 3};
    	
    	for(int count = 0; count < 4; count++)
    		list.add(count, count);
    	
    	list.remove(2);
    	
    	assertArrayEquals("The list should contain 0, 1, 3: ", array, list.toArray());
    }
    
    @Test
    public void popHeadTest()
    {
    	Integer[] array = new Integer[]{1, 2,3};
    	
    	for(int count = 0; count < 4; count++)
    		list.add(count, count);
    	
    	list.removeFirst();
    	
    	assertArrayEquals("The list should contain 1, 2, 3: ", array, list.toArray());
    }
    
    @Test
    public void getTest()
    {
    	for(int count = 0; count < 4; count++)
    		list.add(count, count);
    	
    	assertEquals("The list should contain 1 at the first index: ", (long) 1, (long) list.get(1));
    }
    
    @Test
    public void lastIndexTest()
    {
    	for(int count = 0; count < 4; count++)
    		list.add(count, count);
    	
    	list.add(4, 2);
    	
    	assertEquals("The last index of 2 is at the fourth index: ", (long) 4, (long) list.lastIndexOf(2));
    }
}
