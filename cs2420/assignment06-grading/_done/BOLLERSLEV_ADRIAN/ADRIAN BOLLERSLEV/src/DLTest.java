/*
 * @Author:Adrian Bollerslev
 * uid:u1115021
 */
package assignment06;

import org.junit.*;


import java.util.NoSuchElementException;


public class DLTest {
	
	DoublyLinkedList<String> dll; 

	Object[] ourArray = {"Susan", "Bob", "Bill"};
	
	@Before
	public void clearlist(){
		dll = new DoublyLinkedList<String>();
	}
	@Test
	public void addFirstTest1() {
		dll = new DoublyLinkedList<String>();
		dll.addFirst((String) "Susan");
		dll.addLast("Stanley");
		Assert.assertEquals("Susan", dll.getFirst());
	}
	
	@Test
	public void addFirstTest2() {
		dll = new DoublyLinkedList<String>();
		dll.addFirst((String) "Jill");
		dll.addLast((String) "Johnny");
		Assert.assertEquals("Jill", dll.getFirst());
	}
	
	


	@Test
	public void addLastTest1() {
		dll.addLast("Stanley");
		Assert.assertEquals("Stanley", dll.getLast());
	}
	

	@Test
	public void addLastTest2() {
		dll.addLast((String) "Bob");
		dll.addLast((String) "Johnny");
		Assert.assertEquals("Bob", dll.getFirst());
	}
	
	

	
	@Test
	public void addAtTest1() {
		
		dll.addFirst((String) "Susan");
		dll.addLast((String) "Bob");
        dll.add(1, "Jean");
		     
		Assert.assertEquals("Jean", dll.get(1));
	}
	
	@Test
	public void addAtTest2() {
		dll.addFirst((String) "Susan");
		dll.addLast((String) "Bob");
		dll.addLast((String) "Bob");
		dll.addLast((String) "Johnny");
        dll.add(2, "Jean");
		
		Assert.assertEquals("Jean", dll.get(2));
	}

	@Test
	public void removeAtTest1() {
		dll.addFirst((String) "Susan");
		dll.addLast((String) "Bob");
        dll.add(1, "Jean");
		dll.remove(1);
		
		Assert.assertEquals("Bob", dll.get(1));
	}
	@Test
	public void removeFirstTest1() {
		dll.addFirst((String) "Susan");
		dll.addLast((String) "Bob");
		
		dll.removeFirst();
				
		Assert.assertEquals("Bob", dll.getFirst());
	}
	

	
	
	@Test
	public void removeLastTest1() {
		dll.addFirst((String) "Susan");
		dll.addLast((String) "Bob");
		dll.removeLast();
			
		Assert.assertEquals("Susan", dll.getLast());
	}
	


	@Test 
	public void indexOfElementTest1() {
		dll.addFirst((String) "Susan");
		dll.addLast((String) "Bob");
		dll.add(0 ,"Bill");
		System.out.println(dll.getFirst().toString());  
		Assert.assertEquals(0, dll.indexOf("Bill"));
	}
	
	@Test 
	public void indexOfElementTest2() {
		dll.addFirst((String) "Susan");
		dll.addLast((String) "Bob");
		dll.add(0 ,"Bill");

			
		Assert.assertEquals(2, dll.indexOf("Bob"));
	}
	
	
	@Test 
	public void sizeTest1() {
		dll.addFirst((String) "Susan");
		dll.addLast((String) "Bob");
		dll.add( 1 ,"Bill");
			
		Assert.assertEquals(3, dll.size());
	}
	
	
	@Test 
	public void isEmptyTest1() {
		dll.addFirst((String) "Susan");
		dll.addLast((String) "Bob");
		dll.clear();
		
		Assert.assertEquals(true , dll.isEmpty());
	}
	
	@Test 
	public void isEmptyTest2() {
		dll.addFirst((String) "Susan");
		dll.addLast((String) "Bob");
			
		Assert.assertEquals(false, dll.isEmpty());
	}
	
	@Test 
	public void toArrayTest1() {
		dll.addFirst((String)"Susan");
		dll.addLast((String)"Bob");
		dll.addLast((String)"Bill");
		//System.out.println("dll.toArray()");
		Assert.assertArrayEquals(ourArray, dll.toArray());
		
		//Assert.assertEquals(ourArray, dll.toArray());
	}
	
	@Test 
	public void itTest1() {
		dll.addFirst((String) "Susan");
		dll.addLast((String) "Bob");
		dll.add( 1 ,"Bill");
		
		Assert.assertTrue(dll.hasNext());
	}
	@Test 
	public void itTest3() {
		dll.addFirst((String) "Susan");
		dll.addLast((String) "Bob");
		//dll.add( 1 ,"Bill");
		dll.addLast("element");
		dll.next();
		System.out.print(dll.next());
		System.out.print(dll.next());
		System.out.print(dll.hasNext());
		Assert.assertFalse(dll.hasNext());
	}
	@Test 
	public void itTest2() {
		dll.addFirst((String) "Susan");
		dll.addLast((String) "Bob");
		dll.add( 1 ,"Bill");
		dll.addLast("element");
		dll.next();
		dll.next();
		dll.next();
		
		Assert.assertEquals("element",dll.next());
	}
	

}
