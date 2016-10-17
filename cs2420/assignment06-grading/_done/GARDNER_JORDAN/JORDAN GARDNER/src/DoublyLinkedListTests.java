package assignment06;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import assignment06.DoublyLinkedList.DoublyLinkedListIterator;
/**
 * 
 * @author Jordan Gardner U0566259
 * Testing Class for assign06
 * Test different sizes of lists, including size 0 and size 1.
 * Test all remove and add methods thoroughly.
 * Test for Null pointer exceptions and Index Out of Bounds Exceptions.
 * Tests Iterator
 *
 */
public class DoublyLinkedListTests {
	
	DoublyLinkedList<Integer> tenValues= new DoublyLinkedList<Integer>();
	DoublyLinkedList<Integer> zeroValues= new DoublyLinkedList<Integer>();
	DoublyLinkedList<Integer> oneValues= new DoublyLinkedList<Integer>();
	DoublyLinkedList<Integer> twoValues= new DoublyLinkedList<Integer>();
	DoublyLinkedListIterator Iter;
	DoublyLinkedListIterator Iter2;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@Before
	public void setUp() throws Exception {
		tenValues.add(0, 0);
		tenValues.add(1,1);
		tenValues.addLast(2);
		tenValues.addLast(3);
		tenValues.addLast(4);
		tenValues.addLast(5);
		tenValues.addLast(6);
		tenValues.addLast(7);
		tenValues.addLast(8);
		tenValues.addLast(9);
		
		oneValues.addLast(0);
		
		twoValues.addLast(1);
		twoValues.add(0,0);
		
		Iter=(DoublyLinkedListIterator) twoValues.Iterator();
		Iter2=(DoublyLinkedListIterator) tenValues.Iterator();

	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testAddFirst() {
		tenValues.addFirst(15);
		assertEquals(tenValues.get(0),tenValues.getFirst());
		tenValues.addFirst(16);
		assertEquals(tenValues.get(0),tenValues.getFirst());

	}
	@Test
	public void testAddLast() {
		twoValues.addLast(180);
		assertEquals(twoValues.getLast(),twoValues.get(2));
		oneValues.addLast(2);
		assertEquals(oneValues.getLast(),oneValues.get(1));

	}
	
	@Test
	public void testRemoveFirst() {
		tenValues.removeFirst();
		tenValues.removeFirst();
		assertEquals(tenValues.get(0),(Integer)2);
		assertEquals(tenValues.getLast(),(Integer)9);

	}
	@Test
	public void testRemoveLast() {
		oneValues.removeLast();
		assertEquals(oneValues.size(),0);
//		assertEquals(oneValues.getFirst(),null);
		tenValues.removeLast();
		assertEquals(tenValues.getLast(),(Integer)8);
	}

	@Test
	public void testIndexOf() {
		assertEquals(tenValues.indexOf(6), 6);
		assertEquals(tenValues.indexOf(9), 9);
		assertEquals(tenValues.indexOf(0), 0);
		tenValues.remove(3);
		tenValues.remove(7);
		assertEquals(tenValues.indexOf((Integer)5), 4);
	}
	@Test
	public void testLastIndexOf() {
		tenValues.addLast(4);
		assertEquals(tenValues.lastIndexOf(4),10);
		tenValues.remove(10);
		assertEquals(tenValues.lastIndexOf(4),4);
		assertEquals(zeroValues.lastIndexOf(5),-1);
		assertEquals(oneValues.lastIndexOf(0),0);
	}
	@Test
	public void testSize() {
		assertEquals(tenValues.size(),10);
		assertEquals(oneValues.size(),1);
	}
	@Test
	public void testIsEmpty() {
		tenValues.clear();
		assertEquals(tenValues.isEmpty(),true);
	}
	@Test
	public void testClear() {
		tenValues.clear();
		assertEquals(tenValues.size(),0);
	}
	@Test
	public void testToArray(){
		DoublyLinkedList<Character> fourChars = new DoublyLinkedList<Character>();
		fourChars.add(0, 'a');
		fourChars.addLast('b');
		fourChars.addLast('c');
		fourChars.addLast('d');
		Object[] temp ={'a','b','c','d'};
		Object[] temp2=fourChars.toArray();
		assertEquals(temp[0],temp2[0]);
		assertEquals(temp[1],temp2[1]);
		assertEquals(temp[2],temp2[2]);
		assertEquals(temp[3],temp2[3]);

	}
	@Test
	public void testHasNext(){
		assertTrue(Iter.hasNext());
		Iter.next();
		assertTrue(Iter.hasNext());
		Iter.next();
		assertFalse(Iter.hasNext());
	}
	@Test
	public void testNext(){
		assertEquals(Iter2.next(),0);
		assertEquals(Iter2.next(),1);
		assertEquals(Iter2.next(),2);
		Iter2.next();
		Iter2.next();
		Iter2.next();
		Iter2.next();
		Iter2.next();
		Iter2.next();
		Iter2.next();
		assertFalse(Iter2.hasNext());
	}
	@Test
	public void testRemove(){
		Iter2.next();
		Iter2.remove();
		assertEquals(Iter2.next(), 1);
		Iter2.remove();
		assertEquals(Iter2.next(),2);
		assertTrue(Iter2.hasNext());
		Iter2.next();
		Iter2.next();
		Iter2.next();
		Iter2.next();
		assertEquals(Iter2.next(),7);
		Iter2.next();
		Iter2.next();
		assertFalse(Iter2.hasNext());
		
	}
}
