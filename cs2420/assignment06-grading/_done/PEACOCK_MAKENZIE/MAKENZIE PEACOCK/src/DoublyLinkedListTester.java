package assignment06;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * This JUnit class test the DoublyLinkedList class.
 * 
 * @author Makenzie Peacock (u0873188)
 *
 */
public class DoublyLinkedListTester {

	DoublyLinkedList<Integer> intList;
	DoublyLinkedList<String> stringList;
	DoublyLinkedList<Object> objectList;

	@Before
	public void setUp() throws Exception {
		intList = new DoublyLinkedList<Integer>();
		stringList = new DoublyLinkedList<String>();
		objectList = new DoublyLinkedList<Object>();
	}

	//addFirst method tests
	//Integer List
	@Test
	public void addFirstMethodIntTestBasic() {
		intList.addFirst(4);

		Integer[] result = new Integer[1];
		result[0] = 4;

		assertArrayEquals(result, intList.toArray());
	}

	@Test
	public void addFirstMethodOneIntElementInList() {
		intList.addFirst(4);
		intList.addFirst(2);

		Integer[] result = new Integer[2];
		result[0] = 2;
		result[1] = 4;

		assertArrayEquals(result, intList.toArray());
	}

	@Test
	public void addFirstMethodIntAddNull() {
		intList.addFirst(4);
		intList.addFirst(2);
		intList.addFirst(null);

		Integer[] result = new Integer[3];
		result[0] = null;
		result[1] = 2;
		result[2] = 4;

		assertArrayEquals(result, intList.toArray());
	}

	//String List
	@Test
	public void addFirstMethodStringTestBasic() {
		stringList.addFirst("February");

		String[] result = new String[1];
		result[0] = "February";

		assertArrayEquals(result, stringList.toArray());
	}

	@Test
	public void addFirstMethodOneStringElementInList() {
		stringList.addFirst("February");
		stringList.addFirst("January");

		String[] result = new String[2];
		result[0] = "January";
		result[1] = "February";

		assertArrayEquals(result, stringList.toArray());
	}

	@Test
	public void addFirstMethodStringAddNull() {
		stringList.addFirst("February");
		stringList.addFirst("January");
		stringList.addFirst(null);

		String[] result = new String[3];
		result[0] = null;
		result[1] = "January";
		result[2] = "February";

		assertArrayEquals(result, stringList.toArray());
	}

	//Object List
	@Test
	public void addFirstMethodObjTestBasic() {
		objectList.addFirst(0.080);

		Object[] result = new Object[1];
		result[0] = 0.080;

		assertArrayEquals(result, objectList.toArray());
	}

	@Test
	public void addFirstMethodOneObjElementInList() {
		objectList.addFirst(0.080);
		objectList.addFirst("April");

		Object[] result = new Object[2];
		result[0] = "April";
		result[1] = 0.080;

		assertArrayEquals(result, objectList.toArray());
	}

	@Test
	public void addFirstMethodObjAddNull() {
		objectList.addFirst(0.080);
		objectList.addFirst("April");
		objectList.addFirst(null);

		Object[] result = new Object[3];
		result[0] = null;
		result[1] = "April";
		result[2] = 0.080;

		assertArrayEquals(result, objectList.toArray());
	}

	//addLast method tests
	//Integer List
	@Test
	public void addLastMethodIntTestBasic() {
		intList.addLast(4);

		Integer[] result = new Integer[1];
		result[0] = 4;

		assertArrayEquals(result, intList.toArray());
	}

	@Test
	public void addLastMethodOneIntElementInList() {
		intList.addLast(4);
		intList.addLast(2);

		Integer[] result = new Integer[2];
		result[0] = 4;
		result[1] = 2;

		assertArrayEquals(result, intList.toArray());
	}

	@Test
	public void addLastMethodIntAddNull() {
		intList.addLast(4);
		intList.addLast(2);
		intList.addLast(null);

		Integer[] result = new Integer[3];
		result[0] = 4;
		result[1] = 2;
		result[2] = null;

		assertArrayEquals(result, intList.toArray());
	}

	//String List
	@Test
	public void addLastMethodStringTestBasic() {
		stringList.addLast("February");

		String[] result = new String[1];
		result[0] = "February";

		assertArrayEquals(result, stringList.toArray());
	}

	@Test
	public void addLastMethodOneStringElementInList() {
		stringList.addLast("February");
		stringList.addLast("January");

		String[] result = new String[2];
		result[0] = "February";
		result[1] = "January";

		assertArrayEquals(result, stringList.toArray());
	}
	
	@Test
	public void addLastMethodTwoStringsInList() {
		stringList.addLast("February");
		stringList.addLast("January");

		String[] result = new String[2];
		result[0] = "February";
		result[1] = "January";

		String firstNode = stringList.getFirst();
		String lastNode = stringList.getLast();
		
		assertTrue(firstNode != lastNode);
	}

	@Test
	public void addLastMethodStringAddNull() {
		stringList.addLast("February");
		stringList.addLast("January");
		stringList.addLast(null);

		String[] result = new String[3];
		result[0] = "February";
		result[1] = "January";
		result[2] = null;

		assertArrayEquals(result, stringList.toArray());
	}

	//Object List
	@Test
	public void addLastMethodObjTestBasic() {
		objectList.addLast(0.080);

		Object[] result = new Object[1];
		result[0] = 0.080;

		assertArrayEquals(result, objectList.toArray());
	}

	@Test
	public void addLastMethodOneObjElementInList() {
		objectList.addLast(0.080);
		objectList.addLast("April");

		Object[] result = new Object[2];
		result[0] = 0.080;
		result[1] = "April";

		assertArrayEquals(result, objectList.toArray());
	}

	@Test
	public void addLastMethodObjAddNull() {
		objectList.addLast(0.080);
		objectList.addLast("April");
		objectList.addLast(null);

		Object[] result = new Object[3];
		result[0] = 0.080;
		result[1] = "April";
		result[2] = null;

		assertArrayEquals(result, objectList.toArray());
	}

	//add method tests
	//Integer List
	@Test
	public void addIntTestBasic() {
		intList.addFirst(4);
		intList.addFirst(2);
		intList.addLast(8);
		intList.addLast(10);

		intList.add(2, 6);

		Integer[] result = new Integer[5];
		result[0] = 2;
		result[1] = 4;
		result[2] = 6;
		result[3] = 8;
		result[4] = 10;

		assertArrayEquals(result, intList.toArray());
	}

	@Test
	public void addIntOnEmptyList(){
		intList.add(0, 5);

		Integer[] result = new Integer[1];
		result[0] = 5;

		assertArrayEquals(result, intList.toArray());
	}

	@Test (expected = IndexOutOfBoundsException.class)
	public void addIntOutofBoundsTest(){
		intList.add(5, 0);
	}

	@Test
	public void addNullToIntList(){
		intList.add(0, null);

		Integer[] result = new Integer[1];
		result[0] = null;

		assertArrayEquals(result, intList.toArray());
	}

	//String List
	@Test
	public void addStringTestBasic() {
		stringList.addFirst("February");
		stringList.addFirst("January");
		stringList.addLast("November");
		stringList.addLast("December");

		stringList.add(2, "June");

		String[] result = new String[5];
		result[0] = "January";
		result[1] = "February";
		result[2] = "June";
		result[3] = "November";
		result[4] = "December";

		assertArrayEquals(result, stringList.toArray());
	}

	@Test
	public void addStringOnEmptyList(){
		stringList.add(0, "January");

		String[] result = new String[1];
		result[0] = "January";

		assertArrayEquals(result, stringList.toArray());
	}

	@Test (expected = IndexOutOfBoundsException.class)
	public void addStringOutofBoundsTest(){
		stringList.add(5, "January");
	}

	@Test
	public void addNullToStringList(){
		stringList.add(0, null);

		String[] result = new String[1];
		result[0] = null;

		assertArrayEquals(result, stringList.toArray());
	}

	//Object List
	@Test
	public void addObjectTestBasic() {
		objectList.addFirst(0.080);
		objectList.addFirst('m');
		objectList.addLast("April");
		objectList.addLast(10);

		objectList.add(2, 'n');

		Object[] result = new Object[5];
		result[0] = 'm';
		result[1] = 0.080;
		result[2] = 'n';
		result[3] = "April";
		result[4] = 10;

		assertArrayEquals(result, objectList.toArray());
	}

	@Test
	public void addObjectOnEmptyList(){
		objectList.add(0, 'm');

		Object[] result = new Object[1];
		result[0] = 'm';

		assertArrayEquals(result, objectList.toArray());
	}

	@Test (expected = IndexOutOfBoundsException.class)
	public void addObjectOutofBoundsTest(){
		objectList.add(5, "January");
	}

	//size method tests
	@Test
	public void sizeIntTestBasic() {
		intList.addFirst(4);
		intList.addFirst(2);
		intList.addLast(8);
		intList.addLast(10);

		intList.add(2, 6);

		assertEquals(5, intList.size());
	}

	@Test
	public void sizeIntTestZero() {
		intList.addFirst(4);
		intList.addFirst(2);
		intList.addLast(8);
		intList.addLast(10);

		intList.add(2, 6);

		intList.clear();

		assertEquals(0, intList.size());
	}

	//getFirst method tests
	@Test
	public void getFirstMethodIntBasic() {
		intList.addFirst(2);
		Integer result = 2;
		assertEquals(result, intList.getFirst());
	}

	@Test (expected = NoSuchElementException.class)
	public void getFirstOnEmptyIntList() {
		intList.getFirst();
	}

	@Test
	public void getFirstMethodOneIntElementInList() {
		intList.addFirst(2);
		assertEquals(intList.getFirst(), intList.getLast());
	}

	//getLast method tests
	@Test
	public void getLastMethodBasic() {
		intList.addLast(2);
		Integer result = 2;
		assertEquals(result, intList.getLast());
	}

	@Test (expected = NoSuchElementException.class)
	public void getLastOnEmptyIntList() {
		intList.getLast();
	}

	@Test
	public void getLastMethodOneIntElementInList() {
		intList.addLast(2);
		assertEquals(intList.getLast(), intList.getFirst());
	}

	//get method tests
	@Test
	public void getMethodIntBasic() {
		intList.addFirst(4);
		intList.addFirst(2);
		intList.addLast(8);
		intList.addLast(10);

		intList.add(2, 6);

		assertTrue(4 == intList.get(1));
	}

	@Test (expected = IndexOutOfBoundsException.class)
	public void getMethodIntOutOfBounds() {
		intList.addFirst(4);
		intList.addFirst(2);
		intList.addLast(8);
		intList.addLast(10);

		intList.add(2, 6);

		intList.get(-1);
	}

	//indexOf method tests
	//Integer List
	@Test
	public void indexOfMethodIntBasic() {
		intList.addFirst(4);
		intList.addFirst(2);
		intList.addLast(8);
		intList.addLast(10);

		intList.add(2, 6);

		assertTrue(3 == intList.indexOf(8));
	}

	@Test
	public void indexOfMethodIntWithDuplicates() {
		intList.addFirst(4);
		intList.addFirst(2);
		intList.addLast(8);
		intList.addLast(2);

		intList.add(2, 2);

		assertTrue(0 == intList.indexOf(2));
	}

	@Test
	public void indexOfMethodIntElementIsNotInList() {
		intList.addFirst(4);
		intList.addFirst(2);
		intList.addLast(8);
		intList.addLast(10);

		intList.add(2, 6);

		assertTrue(-1 == intList.indexOf(0));
	}

	//String List
	@Test
	public void indexOfMethodStringBasic() {
		stringList.addFirst("February");
		stringList.addFirst("January");
		stringList.addLast("November");
		stringList.addLast("December");

		stringList.add(2, "June");

		assertTrue(1 == stringList.indexOf("February"));
	}

	@Test
	public void indexOfMethodStringWithDuplicates() {
		stringList.addFirst("February");
		stringList.addFirst("January");
		stringList.addLast("February");
		stringList.addLast("December");

		stringList.add(2, "February");

		assertTrue(1 == stringList.indexOf("February"));
	}

	@Test
	public void indexOfMethodStringElementIsNotInList() {
		stringList.addFirst("February");
		stringList.addFirst("January");
		stringList.addLast("November");
		stringList.addLast("December");

		stringList.add(2, "June");

		assertTrue(-1 == stringList.indexOf("April"));
	}

	//Object List
	@Test
	public void indexOfMethodObjectBasic() {
		objectList.addFirst(0.080);
		objectList.addFirst('m');
		objectList.addLast("April");
		objectList.addLast(10);

		objectList.add(2, 'n');

		assertTrue(0 == objectList.indexOf('m'));
	}

	@Test
	public void indexOfMethodObjectWithDuplicates() {
		objectList.addFirst(0.080);
		objectList.addFirst('m');
		objectList.addLast('m');
		objectList.addLast(10);

		objectList.add(2, 'm');

		assertTrue(0 == objectList.indexOf('m'));
	}

	@Test
	public void indexOfMethodObjectElementIsNotInList() {
		objectList.addFirst(0.080);
		objectList.addFirst('m');
		objectList.addLast("April");
		objectList.addLast(10);

		objectList.add(2, 'n');

		assertTrue(-1 == objectList.indexOf('o'));
	}

	//lastIndexOf method tests
	//Integer List
	@Test
	public void lastIndexOfMethodIntBasic() {
		intList.addFirst(4);
		intList.addFirst(2);
		intList.addLast(8);
		intList.addLast(10);

		intList.add(2, 6);

		assertTrue(3 == intList.lastIndexOf(8));
	}

	@Test
	public void lastindexOfMethodIntWithDuplicates() {
		intList.addFirst(4);
		intList.addFirst(2);
		intList.addLast(8);
		intList.addLast(2);

		intList.add(2, 2);

		assertTrue(4 == intList.lastIndexOf(2));
	}

	@Test
	public void lastindexOfMethodIntElementIsNotInList() {
		intList.addFirst(4);
		intList.addFirst(2);
		intList.addLast(8);
		intList.addLast(10);

		intList.add(2, 6);

		assertTrue(-1 == intList.lastIndexOf(0));
	}

	//String List
	@Test
	public void lastIndexOfMethodStringBasic() {
		stringList.addFirst("February");
		stringList.addFirst("January");
		stringList.addLast("November");
		stringList.addLast("December");

		stringList.add(2, "June");

		assertTrue(3 == stringList.lastIndexOf("November"));
	}

	@Test
	public void lastindexOfMethodStringWithDuplicates() {
		stringList.addFirst("February");
		stringList.addFirst("January");
		stringList.addLast("February");
		stringList.addLast("December");

		stringList.add(2, "February");

		assertTrue(3 == stringList.lastIndexOf("February"));
	}

	@Test
	public void lastindexOfMethodStringElementIsNotInList() {
		stringList.addFirst("February");
		stringList.addFirst("January");
		stringList.addLast("November");
		stringList.addLast("December");

		stringList.add(2, "June");

		assertTrue(-1 == stringList.lastIndexOf("April"));
	}

	//Object List
	@Test
	public void lastIndexOfMethodObjBasic() {
		objectList.addFirst(0.080);
		objectList.addFirst('m');
		objectList.addLast("April");
		objectList.addLast(10);

		objectList.add(2, 'n');

		assertTrue(0 == objectList.lastIndexOf('m'));
	}

	@Test
	public void lastindexOfMethodObjWithDuplicates() {
		objectList.addFirst(0.080);
		objectList.addFirst('m');
		objectList.addLast('m');
		objectList.addLast(10);

		objectList.add(2, 'm');

		assertTrue(3 == objectList.lastIndexOf('m'));
	}

	@Test
	public void lastindexOfMethodObjElementIsNotInList() {
		objectList.addFirst(0.080);
		objectList.addFirst('m');
		objectList.addLast("April");
		objectList.addLast(10);

		objectList.add(2, 'n');

		assertTrue(-1 == objectList.lastIndexOf(11));
	}


	//removeFirst method tests
	@Test
	public void removeFirstMethodIntTestBasic() {
		intList.addFirst(4);
		intList.addFirst(2);
		intList.addLast(8);
		intList.addLast(10);

		intList.add(2, 6);

		intList.removeFirst();

		Integer[] result = new Integer[4];
		result[0] = 4;
		result[1] = 6;
		result[2] = 8;
		result[3] = 10;

		assertArrayEquals(result, intList.toArray());
	}

	@Test
	public void removeFirstMethodOneIntElementInList() {
		intList.addFirst(4);

		assertTrue(4 == intList.removeFirst());
	}

	@Test
	public void removeFirstMethodIntRemoveNull() {
		intList.addFirst(4);
		intList.addFirst(2);
		intList.addFirst(null);

		assertTrue(null == intList.removeFirst());
	}

	@Test (expected = NoSuchElementException.class)
	public void removeFirstOnEmptyIntList() {
		intList.addFirst(4);

		intList.clear();

		intList.removeFirst();
	}

	//String List
	@Test
	public void removeFirstMethodStringTestBasic() {
		stringList.addFirst("February");
		stringList.addFirst("January");

		stringList.removeFirst();

		String[] result = new String[1];
		result[0] = "February";

		assertArrayEquals(result, stringList.toArray());
	}

	@Test
	public void removeFirstMethodOneStringElementInList() {
		stringList.addFirst("February");
		stringList.addFirst("January");

		assertTrue("January" == stringList.removeFirst());
	}

	@Test
	public void removeFirstMethodStringRemoveNull() {
		stringList.addFirst("February");
		stringList.addFirst("January");
		stringList.addFirst(null);

		assertTrue(null == stringList.removeFirst());
	}

	//Object List
	@Test
	public void removeFirstMethodObjTestBasic() {
		objectList.addFirst(0.080);
		objectList.addFirst('m');
		objectList.addLast("April");
		objectList.addLast(10);

		objectList.add(2, 'n');

		objectList.removeFirst();

		Object[] result = new Object[4];
		result[0] = 0.080;
		result[1] = 'n';
		result[2] = "April";
		result[3] = 10;

		assertArrayEquals(result, objectList.toArray());
	}

	@Test
	public void removeFirstMethodOneObjElementInList() {
		objectList.addFirst(0.080);
		objectList.addFirst("April");

		assertTrue("April" == objectList.removeFirst());
	}

	@Test
	public void removeFirstMethodObjAddNull() {
		objectList.addFirst(0.080);
		objectList.addFirst("April");
		objectList.addFirst(null);

		assertTrue(null == objectList.removeFirst());
	}

	//removeLast method tests
	//Integer List
	@Test
	public void removeLastMethodIntTestBasic() {
		intList.addFirst(4);
		intList.addFirst(2);
		intList.addLast(8);
		intList.addLast(10);

		intList.add(2, 6);

		intList.removeLast();

		Integer[] result = new Integer[4];
		result[0] = 2;
		result[1] = 4;
		result[2] = 6;
		result[3] = 8;

		assertArrayEquals(result, intList.toArray());
	}

	@Test
	public void removeLastMethodOneIntElementInList() {
		intList.addLast(4);
		intList.addLast(2);

		assertTrue(2 == intList.removeLast());
	}

	@Test
	public void removeLastMethodIntAddNull() {
		intList.addLast(4);
		intList.addLast(2);
		intList.addLast(null);

		assertTrue(null == intList.removeLast());
	}

	@Test (expected = NoSuchElementException.class)
	public void removeLastOnEmptyIntList() {
		intList.addFirst(4);

		intList.clear();

		intList.removeLast();
	}

	//String List
	@Test
	public void removeLastMethodStringTestBasic() {
		stringList.addFirst("February");
		stringList.addFirst("January");

		stringList.removeLast();

		String[] result = new String[1];
		result[0] = "January";

		assertArrayEquals(result, stringList.toArray());
	}

	@Test
	public void removeLastMethodOneStringElementInList() {
		stringList.addFirst("February");
		stringList.addFirst("January");

		assertTrue("February" == stringList.removeLast());
	}

	@Test
	public void removeLastMethodStringAddNull() {
		stringList.addFirst("February");
		stringList.addFirst("January");
		stringList.addLast(null);

		assertTrue(null == stringList.removeLast());
	}

	//Object List
	@Test
	public void removeLastMethodObjTestBasic() {
		objectList.addFirst(0.080);
		objectList.addFirst('m');
		objectList.addLast("April");
		objectList.addLast(10);

		objectList.add(2, 'n');

		objectList.removeLast();

		Object[] result = new Object[4];
		result[0] = 'm';
		result[1] = 0.080;
		result[2] = 'n';
		result[3] = "April";

		assertArrayEquals(result, objectList.toArray());
	}

	@Test
	public void removeLastMethodOneObjElementInList() {
		objectList.addLast(0.080);
		objectList.addLast("April");

		assertTrue("April" == objectList.removeLast());
	}

	@Test
	public void removeLastMethodObjAddNull() {
		objectList.addLast(0.080);
		objectList.addLast("April");
		objectList.addLast(null);

		assertTrue(null == objectList.removeLast());
	}

	//clear method tests
	//Integer List
	@Test
	public void clearIntList() {
		intList.addFirst(4);
		intList.addFirst(2);
		intList.addLast(8);
		intList.addLast(10);

		intList.add(2, 6);

		intList.clear();

		assertTrue(intList.isEmpty());
	}

	//String List
	@Test
	public void clearStringList(){
		stringList.addFirst("February");
		stringList.addFirst("January");
		stringList.addLast("November");
		stringList.addLast("December");

		stringList.add(2, "June");

		stringList.clear();

		assertTrue(stringList.isEmpty());
	}

	//Object List
	@Test
	public void clearObjectList(){
		objectList.addFirst(0.080);
		objectList.addFirst('m');
		objectList.addLast("April");
		objectList.addLast(10);

		objectList.add(2, 'n');

		objectList.clear();

		assertTrue(objectList.isEmpty());
	}



	//isEmpty method tests
	@Test
	public void isEmptyOnNonEmptyTest() {
		intList.addFirst(4);
		intList.addFirst(2);
		intList.addLast(8);
		intList.addLast(10);

		intList.add(2, 6);

		assertFalse(intList.isEmpty());
	}



	//remove method tests
	//Integer List
	@Test
	public void removeIntTestBasic() {
		intList.addFirst(4);
		intList.addFirst(2);
		intList.addLast(8);
		intList.addLast(10);

		intList.remove(2);

		Integer[] result = new Integer[3];
		result[0] = 2;
		result[1] = 4;
		result[2] = 10;

		assertArrayEquals(result, intList.toArray());
	}

	@Test (expected = IndexOutOfBoundsException.class)
	public void removeIntOnEmptyList(){
		intList.remove(0);
	}

	@Test (expected = IndexOutOfBoundsException.class)
	public void removeIntOutofBoundsTest(){
		intList.add(0, 0);
		intList.remove(1);
	}

	@Test
	public void removeNullFromIntList(){
		intList.add(0, null);

		assertTrue(null == intList.remove(0));
	}
	
	@Test
	public void removeLastElement(){
		intList.addFirst(4);
		intList.addFirst(2);
		intList.addLast(8);
		intList.addLast(10);
		
		assertTrue(10 == intList.remove(3));
	}

	//String List
	@Test
	public void removeStringTestBasic() {
		stringList.addFirst("February");
		stringList.addFirst("January");
		stringList.addLast("November");
		stringList.addLast("December");

		stringList.remove(2);

		String[] result = new String[3];
		result[0] = "January";
		result[1] = "February";
		result[2] = "December";

		assertArrayEquals(result, stringList.toArray());
	}

	@Test (expected = IndexOutOfBoundsException.class)
	public void removeStringFromEmptyList(){
		stringList.remove(0);
	}

	@Test (expected = IndexOutOfBoundsException.class)
	public void removeStringOutofBoundsTest(){
		stringList.add(0, "January");
		stringList.remove(1);
	}

	@Test
	public void removeNullFromStringList(){
		stringList.add(0, null);

		assertTrue(null == stringList.remove(0));
	}

	//Object List
	@Test
	public void removeObjectTestBasic() {
		objectList.addFirst(0.080);
		objectList.addFirst('m');
		objectList.addLast("April");
		objectList.addLast(10);

		objectList.remove(2);

		Object[] result = new Object[3];
		result[0] = 'm';
		result[1] = 0.080;
		result[2] = 10;

		assertArrayEquals(result, objectList.toArray());
	}

	@Test (expected = IndexOutOfBoundsException.class)
	public void removeObjectFromEmptyList(){
		objectList.remove(0);
	}

	@Test (expected = IndexOutOfBoundsException.class)
	public void removeObjectOutofBoundsTest(){
		objectList.add(0, 'm');
		objectList.remove(1);
	}

}
