package assignment06;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * @author Christian Hansen
 * @UID U0621884
 * @version 10/5/2016
 * 
 * This class holds the unit tests for all the public methods in DoublyLinkedList.
 * The tests try to demonstrate safety and predicted operation in all tests.
 * Tests are organized by method with a comment describing what method the section tests.
 *
 */
public class DoublyLinkedListTester {
	
	DoublyLinkedList<String> stringList;

	@Before
	public void setUp() throws Exception {
		stringList = new DoublyLinkedList<String>();
	}
	
	//****** size() TESTS ******
	
	@Test
	public void sizeTestSizeIs0() {
		Assert.assertEquals(0, stringList.size());
	}
	
	@Test
	public void sizeTestSizeIsGreaterThan0() {
		stringList.addFirst("1");
		stringList.addFirst("1");
		stringList.addFirst("1");
		Assert.assertEquals(3, stringList.size());
	}
	
	//*******  addFirst() TESTS ******
	
	@Test
	public void addFirstTestAddingOne() {
		stringList.addFirst("first");
		Assert.assertEquals("first", stringList.getFirst());
	}

	@Test
	public void addFirstTestNewFirstPushesOtherUp() {
		stringList.addFirst("first");
		stringList.addFirst("New first");
		Assert.assertEquals("New first", stringList.getFirst());
	}
	
	// ******* getLast() TESTS *******
	
	@Test
	public void getLastTest(){
		stringList.addFirst("first");
		stringList.addFirst("New first");
		Assert.assertEquals("first", stringList.getLast());
	}
	
	@Test
	public void getLastTestSizeEquals0ThrowsException(){
		
		boolean exceptionThrown = false;
		
		try{
			stringList.getLast();
		}catch(NoSuchElementException e){
			exceptionThrown = true;
		}
		
		Assert.assertTrue(exceptionThrown);
	}
	
	//******* getFirst() TESTS *******
	
	@Test
	public void getFirstTestSizeEquals0ThrowsException(){
		
		boolean exceptionThrown = false;
		
		try{
			stringList.getFirst();
		}catch(NoSuchElementException e){
			exceptionThrown = true;
		}
		
		Assert.assertTrue(exceptionThrown);
	}
	
	@Test
	public void getFirstTestReturnsFirst(){
		
		stringList.addFirst("3");
		stringList.addFirst("2");
		
		
		Assert.assertEquals("2", stringList.getFirst());
	}
	
	//******* get() TESTS ******
	
	@Test
	public void getTestSizeEquals0ThrowsException(){
		
		boolean exceptionThrown = false;
		
		try{
			stringList.get(0);
		}catch(IndexOutOfBoundsException e){
			exceptionThrown = true;
		}
		
		Assert.assertTrue(exceptionThrown);
	}
	
	@Test
	public void getTestSizeEquals3ThrowsException(){
		
		boolean exceptionThrown = false;
		
		stringList.addFirst("1");
		stringList.addFirst("1");
		stringList.addFirst("1");
		
		try{
			stringList.get(3);
		}catch(IndexOutOfBoundsException e){
			exceptionThrown = true;
		}
		
		Assert.assertTrue(exceptionThrown);
	}
	
	@Test
	public void getTestReturnsElementUpperHalf(){
		stringList.addFirst("1");
		stringList.addLast("2");
		stringList.addLast("3");
		stringList.addLast("4");
		stringList.addLast("5");
		stringList.addLast("6");
		stringList.addLast("7");
		stringList.addLast("8");
		stringList.addLast("9");
		stringList.addLast("10");
		
		Assert.assertEquals("8", stringList.get(7));
	}
	
	@Test
	public void getTestReturnsElementAtLowerHalf(){
		stringList.addFirst("1");
		stringList.addLast("2");
		stringList.addLast("3");
		stringList.addLast("4");
		stringList.addLast("5");
		stringList.addLast("6");
		stringList.addLast("7");
		stringList.addLast("8");
		stringList.addLast("9");
		stringList.addLast("10");
		
		Assert.assertEquals("3", stringList.get(2));
	}
	
	//****** addLast() TESTS ********

	@Test
	public void addLastWithTwoElements() {
		stringList.addFirst("Some Element");
		stringList.addLast("last");
		Assert.assertEquals("last", stringList.getLast());
	}
	
	@Test
	public void addLastNoElementsToBeginWith(){
		stringList.addLast("last");
		Assert.assertEquals("last", stringList.getLast());
	}
	
	//****** add() TESTS ***************8
	
	@Test
	public void addTestFirstItemIsHeadAndTail() {
		stringList.add(0, "head");
		Assert.assertEquals( "Test that returns the head" ,"head", stringList.getFirst());
		Assert.assertEquals("Test that returns the tail" , "head", stringList.getLast());
	}
	
	@Test
	public void addTestAddingLastElement(){
		stringList.add(0, "head");
		stringList.add(1, "tail");
		Assert.assertEquals("tail", stringList.getLast());
	}
	
	@Test
	public void addTestOutOfBounds(){
		
		boolean exceptionThrown = false;
		
		try{
			stringList.add(1, "1");
		}catch(IndexOutOfBoundsException e){
			exceptionThrown = true;
		}
		
		Assert.assertTrue(exceptionThrown);
	}
	
	@Test
	public void addTestLotsOfItemsPlacingInTheMiddle(){
		stringList.add(0, "first");
		stringList.add(1, "2nd");
		stringList.add(2, "Third");
		stringList.add(3, "4th");
		stringList.add(2, "middle");
		
		Assert.assertEquals("middle", stringList.get(2));
	}
	
	//****** removeFirst() TESTS ************
	
	@Test
	public void removeFirstTestExceptionThron(){
		
		boolean exceptionThrown = false;
		
		try{
			stringList.removeFirst();
		}catch(NoSuchElementException e){
			exceptionThrown = true;
		}
		
		Assert.assertTrue(exceptionThrown);
	}
	
	@Test
	public void removeFirstTestRemovesFirstFromManyItems(){
		stringList.addFirst("4");
		stringList.addFirst("3");
		stringList.addFirst("2");
		stringList.addFirst("1");
		
		stringList.removeFirst();
		
		Assert.assertEquals("Size Test", 3, stringList.size());
		Assert.assertEquals("remove Test", "2", stringList.getFirst());
	}
	
	//****** removeLast() TESTS **********
	
	@Test
	public void removeLastTest(){
		
		boolean exceptionThrown = false;
		
		try{
			stringList.removeLast();
		}catch(NoSuchElementException e){
			exceptionThrown = true;
		}
		
		Assert.assertTrue(exceptionThrown);
	}
	
	@Test
	public void removeLastTestRemovesLastFromManyItems(){
		stringList.addFirst("4");
		stringList.addFirst("3");
		stringList.addFirst("2");
		stringList.addFirst("1");
		
		stringList.removeLast();
		
		Assert.assertEquals("Size Test", 3, stringList.size());
		Assert.assertEquals("remove Test", "3", stringList.getLast());
	}
	
	//****** remove() TESTS ************
	
	@Test
	public void removeTestExceptionThrown(){
		
		boolean exceptionThrown = false;
		
		try{
			stringList.remove(0);
		}catch(IndexOutOfBoundsException e){
			exceptionThrown = true;
		}
		
		Assert.assertTrue(exceptionThrown);
	}
	
	@Test
	public void removeTestRemovingItemFromUpperBound(){
		stringList.addFirst("8");
		stringList.addFirst("7");
		stringList.addFirst("6");
		stringList.addFirst("5");
		stringList.addFirst("4");
		stringList.addFirst("3");
		stringList.addFirst("2");
		stringList.addFirst("1");
		
		String result = stringList.remove(6);
		
		Assert.assertEquals("7", result);
	}
	
	@Test
	public void removeTestRemovingItemFromLowerBound(){
		stringList.addFirst("8");
		stringList.addFirst("7");
		stringList.addFirst("6");
		stringList.addFirst("5");
		stringList.addFirst("4");
		stringList.addFirst("3");
		stringList.addFirst("2");
		stringList.addFirst("1");
		
		String result = stringList.remove(2);
		
		Assert.assertEquals("3", result);
	}
	
	//****** toArray() TESTS *************
	
	@Test
	public void toArrayTestBasic(){
		stringList.addFirst("3");
		stringList.addFirst("2");
		stringList.addFirst("1");
		Assert.assertArrayEquals(new Object[]{"1", "2", "3"}, stringList.toArray());
	}
	
	@Test
	public void toArrayTestArrayIsEmpty(){

		Assert.assertArrayEquals(new Object[]{}, stringList.toArray());
	}

	//******** indexOf() TESTS ************
	
	@Test
	public void indexOfTest(){
		stringList.addFirst("3");
		stringList.addFirst("2");
		stringList.addFirst("1");
		Assert.assertEquals("The size test failed", 3, stringList.size());
		Assert.assertEquals("the indexOf test failed", -1, stringList.indexOf("4"));
	}
	
	//******* lastIndexOf() TESTS **********
	
	@Test
	public void lastIndexOfTest(){
		stringList.addFirst("3");
		stringList.addFirst("3");
		stringList.addFirst("2");
		stringList.addFirst("1");
		Assert.assertEquals("The size test failed", 4, stringList.size());
		Assert.assertEquals("the indexOf test failed", 3, stringList.lastIndexOf("3"));
	}
	
	//********* isEmpty() Tests *******
	
	@Test
	public void isEmptyTestListIsEmpty() {
		Assert.assertTrue(stringList.isEmpty());
	}
	
	//******** clear() Tests ********
	
	@Test
	public void clearTestAddingFourItems(){
		stringList.addFirst("3");
		stringList.addFirst("3");
		stringList.addFirst("2");
		stringList.addFirst("1");
		stringList.clear();
		Assert.assertTrue(stringList.isEmpty());
	}
	
	@Test
	public void clearTestAlreadyEmpty(){
		stringList.clear();
		Assert.assertTrue(stringList.isEmpty());
	}
	
	//********* iterator() Tests ***********
	
	@Test
	public void iteratorTestTestingHasNextReturnsFalse(){
		stringList.addFirst("1");
		stringList.addLast("2");
		stringList.addLast("3");
		Iterator<String> iter = stringList.iterator();
		iter.next();
		iter.next();
		iter.next();
		Assert.assertFalse(iter.hasNext());
	}
	
	@Test
	public void iteratorTestTestingNextReturnsItem(){
		stringList.addFirst("1");
		Iterator<String> iter = stringList.iterator();
		Assert.assertEquals("1", iter.next());
	}
	
	@Test
	public void iteratorTestRemoveMethod(){
		stringList.addFirst("1");
		stringList.addLast("2");
		stringList.addLast("3");
		Iterator<String> iter = stringList.iterator();
		iter.next();
		iter.remove();
		Assert.assertEquals(-1, stringList.indexOf("1"));
	}
	
	@Test
	public void iteratorTestNextThrowsException(){
		stringList.addFirst("1");
		Iterator<String> iter = stringList.iterator();
		iter.next();
		
		boolean exceptionCalled = false;
		
		try{
			iter.next();
		}catch(NoSuchElementException e){
			exceptionCalled = true;
		}
		
		Assert.assertTrue(exceptionCalled);
	}
	
	@Test
	public void iteratorTestRemoveThrowsException(){
		stringList.addFirst("1");
		Iterator<String> iter = stringList.iterator();
		
		boolean exceptionCalled = false;
		
		try{
			iter.remove();
		}catch(IllegalStateException e){
			exceptionCalled = true;
		}
		
		Assert.assertTrue(exceptionCalled);
	}
}
