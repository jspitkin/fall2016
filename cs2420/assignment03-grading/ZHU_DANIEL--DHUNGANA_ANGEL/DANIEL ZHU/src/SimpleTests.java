package assignment03;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SimpleTests {
	//Create BinaraySearchSet Objects, of different sizes, and are ordered or unordered
	
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
	public void test() {
	//	fail("Not yet implemented");
	}
	@Test 
public final void main(){ 
	        BinarySearchSet<Integer> data = new BinarySearchSet<Integer>( );
	        
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
	     
	       
	      
	        data.add(4);
//	        List<Integer> data2 =  new ArrayList<Integer>();
//	        data2.add(4);
//	        data2.add(14);
//	        data2.add(15);
//	        data2.add(16);
//	        data.addAll(data2);
//	        for(int value: data2)
//	        {
//	        	System.out.println(value);
//	        }
	      
	     
	 //   assertTrue(data.containsAll(data2));
	    }

}


