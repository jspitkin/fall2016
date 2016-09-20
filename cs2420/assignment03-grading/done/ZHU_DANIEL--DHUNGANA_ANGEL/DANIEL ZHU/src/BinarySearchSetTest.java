package assignment03;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BinarySearchSetTest {
	//Create BinaraySearchSet Objects, of different sizes, and are ordered or unordered
	BinarySearchSet<Integer> data = new BinarySearchSet<Integer>( );
	BinarySearchSet<String> dataString = new BinarySearchSet<String>( );
	 List<Integer> data2 =  new ArrayList<Integer>();
	 List<String> dataString2 =  new ArrayList<String>();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	
	@Test 
public void testingAddingAndRemovingAndSeeingifDataContainsElement(){ 
			data.add(1);
	        data.add(2);
	        data.add(4);
	        data.add(3);
	        data.add(10);
	        data.add(9);
	        data.add(8);
	        data.add(7);
	        data.add(6);
	        data.add(5);
	        data.add(11);
	        data.add(12);
	        data.add(13);
	        
	       
	      data.remove(10);
	      assertFalse(data.contains(10));
	      
	   
	}
	@Test
	public void testingAddAllandSeeingIfItContainsElement(){ 
	       
	       
	        data2.add(4);
	        data2.add(14);
	        data2.add(15);
	        data2.add(16);
	        data.addAll(data2);
	        
	        	assertTrue(data.contains(15));
	        
	}
	@Test
	public void testingIterator(){
			data2.add(4);
	        data2.add(14);
	        data2.add(15);
	        data2.add(16);
	        data2.add(17);
	        data2.add(18);
	        data2.add(20);
	        data2.add(6);
	        Iterator<Integer> itr = data.iterator();
	        itr.next();
	     //   itr.remove();
	        itr.hasNext();
	      
	}
	
	@Test
	public void testingContainsAll()
	{
		List<Integer> newData = new ArrayList<Integer>();
		newData.add(1);
		newData.add(2);
		newData.add(3);
		data.addAll(newData);
		assertEquals(data.size(),3);
	}
	@Test
	public void testingClear()
	{
		List<Integer> newData3=  new ArrayList<Integer>();
		data.add(4);
        data.add(14);
        data.add(15);
        data.add(16);
        data.clear();
        assertTrue(data.isEmpty());
        
        
	}
	
	@Test
	public void testingRemoveAll()
	{
		List<Integer> newData3=  new ArrayList<Integer>();
		data.add(4);
        data.add(14);
        data.add(15);
        data.add(16);
		List<Integer> newData2=  new ArrayList<Integer>();
			newData2.add(14);
			newData2.add(15);
	        data.removeAll(newData2);
	        
	        newData3.add(14);
	        newData3.add(15);
	        assertFalse(data.containsAll(newData3));
	}
	@Test
	public void testingString()
	{
		dataString.add("a");
		dataString.add("c");
		dataString.add("j");
		dataString.add("b");
		dataString.add("d");
		dataString.add("h");
		
		//dataString.remove("a");
		
	}
	@Test
	public void testingAddAllString()
	{
		dataString2.add("z");
		dataString2.add("x");
		dataString2.add("e");
		
		dataString.addAll(dataString2);
		assertEquals(dataString.size(),3);
	}
	
	
}
	        
	        
	        
	      



