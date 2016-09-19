package assignment03;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * Test class for the BinarySearchSet<E> class thats tests many scenarios of the methods within 
 * being called.
 * 
 * @author Andrew Worley / u0651238
 * @author Brian Park / u0735732
 *
 */
public class BinarySearchSetJUnitTester {

	BinarySearchSet<Integer> testIntegerArr = new BinarySearchSet<Integer>();
	BinarySearchSet<Integer> testIntegerArrEmpty = new BinarySearchSet<Integer>();
	BinarySearchSet<String> testStringArr = new BinarySearchSet<String>();
	BinarySearchSet<Character> testCharArr = new BinarySearchSet<Character>();
	BinarySearchSet<String> testStringArrNull;

	PersonComparators comparingPersons = new PersonComparators();
	Iterator intIterator = testIntegerArr.iterator();

	BinarySearchSet<Person> birthdateCollection = new BinarySearchSet<Person>(comparingPersons.getBirthdateComparator());
	BinarySearchSet<Person> nameCollection = new BinarySearchSet<Person>(comparingPersons.getNameComparator());
	BinarySearchSet<Person> heightCollection = new BinarySearchSet<Person>(comparingPersons.getHeightComparator());

	Iterator nameCollIterator = nameCollection.iterator();
	
	ArrayList<Integer> smallIntArrayList = new ArrayList<Integer>();
	ArrayList<Integer> largeIntArrayList = new ArrayList<Integer>();
	Object[] expected = new Object[5];

	@Before
	public void setUp() throws Exception {

		for (int i = 0; i < 25; i++) {

			testIntegerArr.add(i);
		}

		expected[0] = 1;
		expected[1] = 2;
		expected[2] = 3;
		expected[3] = 4;
		expected[4] = 5;

		smallIntArrayList.add(1);
		smallIntArrayList.add(2);
		smallIntArrayList.add(3);
		smallIntArrayList.add(4);
		smallIntArrayList.add(5);

		largeIntArrayList.add(100);
		largeIntArrayList.add(200);
		largeIntArrayList.add(300);
		largeIntArrayList.add(400);
		largeIntArrayList.add(500);

		testStringArr.add("cat");
		testStringArr.add("dog");
		testStringArr.add("mouse");
		testStringArr.add("snake");
		testStringArr.add("cat");

		testCharArr.add('e');
		testCharArr.add('d');
		testCharArr.add('c');
		testCharArr.add('b');
		testCharArr.add('a');
		testCharArr.add('a');

		birthdateCollection.add(new Person(new GregorianCalendar(2090, 11, 5)));
		birthdateCollection.add(new Person(new GregorianCalendar(2088, 1, 21)));
		birthdateCollection.add(new Person(new GregorianCalendar(2094, 4, 5)));
		birthdateCollection.add(new Person(new GregorianCalendar(1995, 2, 10)));
		birthdateCollection.add(new Person(new GregorianCalendar(1995, 2, 10)));

		nameCollection.add(new Person("Ender"));
		nameCollection.add(new Person("Valentine"));
		nameCollection.add(new Person("Peter"));
		nameCollection.add(new Person("Peter"));
		nameCollection.add(new Person("Bean"));
		nameCollection.add(new Person("Grimm"));

		heightCollection.add(new Person(68));
		heightCollection.add(new Person(69));
		heightCollection.add(new Person(48));
		heightCollection.add(new Person(24));
		heightCollection.add(new Person(48));
		heightCollection.add(new Person(33));

	}

	@After
	public void tearDown() throws Exception {
	}

	// Classes with comparators
	@Test
	public void checkDataAtFirstIndexOfBirthdatesCollection() {
		assertEquals(2094, (int) birthdateCollection.get(0).getBirthdate().get(Calendar.YEAR));
	}

	@Test
	public void checkDataAtLastIndexOfBirthdatesCollection() {
		assertEquals(1995,
				(int) birthdateCollection.get(birthdateCollection.size() - 1).getBirthdate().get(Calendar.YEAR));
	}

	@Test
	public void checkDataAtFirstIndexOfNamesdatesCollection() {
		assertEquals("Bean", nameCollection.get(0).getName());
	}

	@Test
	public void checkDataAtLastIndexOfNamesdatesCollection() {
		assertEquals("Valentine", nameCollection.get(nameCollection.size() - 1).getName());
	}

	@Test
	public void checkDataAtFirstIndexOfHeightsdatesCollection() {
		assertEquals(24, (int) heightCollection.get(0).getHeight());
	}

	@Test
	public void checkDataAtLastIndexOfHeightsdatesCollection() {
		assertEquals(69, (int) heightCollection.get(heightCollection.size() - 1).getHeight());
	}

	@Test
	public void testBirthdatesCollectionSizeForDuplicates() {
		assertEquals(4, birthdateCollection.size());
	}

	@Test
	public void testNamesCollectionSizeForDuplicates() {
		assertEquals(5, nameCollection.size());
	}

	@Test
	public void testHeightsCollectionSizeForDuplicates() {
		assertEquals(5, heightCollection.size());
	}

	@Test
	public void testRemoveElementBirthdateCollection() {
		Person thisPerson = new Person(new GregorianCalendar(1995, 2, 10));
		birthdateCollection.remove(thisPerson);
		assertEquals(2088,
				(int) birthdateCollection.get(birthdateCollection.size() - 1).getBirthdate().get(Calendar.YEAR));
	}

	@Test
	public void testRemoveElementNameCollection() {
		Person thisPerson = new Person("Peter");
		nameCollection.remove(thisPerson);
		assertEquals("Valentine", nameCollection.get(nameCollection.size() - 1).getName());
	}

	@Test
	public void testRemoveElementHeightCollection() {
		Person thisPerson = new Person(24);
		heightCollection.remove(thisPerson);
		assertEquals(69, heightCollection.get(heightCollection.size() - 1).getHeight());
	}

	@Test
	public void testAddElementsToNameCollection() {
		for (int i = 0; i < 1000; i++) {
			nameCollection.add(new Person("Juan" + i));
		}
		assertEquals(1005, nameCollection.size());
	}

	// Iterator tests
	@Test
	public void iteratorThruNameCollection() {

	}

	@Test
	public void intIteratorHasNextTest() {
		assertTrue(intIterator.hasNext());
	}

	@Test
	public void intIteratorNextTestFirstElement() {
		assertEquals(0, intIterator.next());
	}

	@Test
	public void intIterationNextTestToLastElement() {
		intIterator.next();
		intIterator.next();
		intIterator.next();
		intIterator.next();
		intIterator.next();
		assertEquals(5, intIterator.next());
	}

	@Test
	public void intIteratorFunctionTest() {
		if (intIterator.hasNext()) {
			intIterator.next();
		}
		assertEquals(1, intIterator.next());
	}

	@Test(expected = IllegalStateException.class)
	public void intIteratorRemoveBeforeNextTest() {
		intIterator.remove();
		assertEquals(0, intIterator.next());
	}

	@Test
	public void intIteratorRemoveAfterNextTest() {
		intIterator.next();
		intIterator.remove();
		assertEquals(1, intIterator.next());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void intIteratorFullIterationTest() {
		while (intIterator.hasNext()) {
			intIterator.next();
		}
		assertEquals(0, intIterator.next());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void intIteratorRemoveAll() {
		while (intIterator.hasNext()) {
			intIterator.next();
			intIterator.remove();
		}
		assertEquals(0, intIterator.next());
	}

	// Add to comparable objects
	@Test
	public void addToIntCollection() {
		for (int i = 0; i < 1000; i++) {
			testIntegerArr.add(i);
		}
		assertEquals(1000, testIntegerArr.size());
	}

	@Test
	public void addToStringCollection() {
		for (int i = 0; i < 1000; i++) {
			testStringArr.add("z" + i);
		}
		assertEquals(1004, testStringArr.size());
	}

	@Test
	public void getFirstTest() {
		Assert.assertEquals((int) testIntegerArr.first(), 0);
		testIntegerArr.remove(0);
		Assert.assertEquals((int) testIntegerArr.first(), 1);
	}

	@Test(expected = NoSuchElementException.class)
	public void getFirstEmpty() {
		Assert.assertEquals((int) testIntegerArrEmpty.first(), 0);

	}

	@Test
	public void getFirstTestString() {
		Assert.assertEquals(testStringArr.first(), "cat");
	}

	@Test
	public void getFirstTestSizeOne() {
		testIntegerArrEmpty.add(1);
		Assert.assertTrue(testIntegerArrEmpty.first() == 1);

	}

	@Test
	public void getLastTest() {
		Assert.assertEquals(testStringArr.last(), "snake");
	}

	@Test
	public void getLastsizeOne() {
		testIntegerArrEmpty.add(1);
		Assert.assertTrue(testIntegerArrEmpty.last() == 1);
	}

	@Test(expected = NullPointerException.class)
	public void getLastNull() {
		String s = testStringArrNull.last();
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void clearAllTest() {
		testIntegerArr.get(1);
		testIntegerArr.clear();
		testIntegerArr.get(0);

	}

	@Test
	public void clearAllTestSizeZero() {
		testIntegerArr.clear();
		Assert.assertArrayEquals(testIntegerArrEmpty.toArray(), testIntegerArr.toArray());
	}

	@Test
	public void clearAllTestInitialZero() {
		testIntegerArrEmpty.clear();
		Assert.assertArrayEquals(testIntegerArrEmpty.toArray(), new BinarySearchSet<Integer>().toArray());
	}

	@Test
	public void containsTestTrue() {
		Assert.assertTrue(testIntegerArr.contains(3));
		Assert.assertTrue(testIntegerArr.contains(4));
		Assert.assertTrue(testIntegerArr.contains(5));
		Assert.assertTrue(testIntegerArr.contains(6));
	}

	@Test
	public void containsTestFalse() {
		Assert.assertFalse(testIntegerArr.contains(36));
		Assert.assertFalse(testIntegerArr.contains(26));
		Assert.assertFalse(testIntegerArr.contains(27));
		Assert.assertFalse(testIntegerArr.contains(28));
	}

	@Test
	public void contains2TestTypeMismatch() {
		testIntegerArr.contains("cat");
	}

	@Test
	public void addTestGrowing() {
		for (int i = 0; i < 300; i++) {
			testIntegerArr.add(i);
		}
	}

	@Test
	public void addAllTestFalse() {
		Assert.assertTrue(testIntegerArr.addAll(largeIntArrayList));
	}

	@Test
	public void addAllTestTrue() {
		Assert.assertFalse(testIntegerArr.addAll(smallIntArrayList));
	}

	@Test
	public void containsAllEmptyCollection() {
		Assert.assertFalse(testIntegerArrEmpty.containsAll(smallIntArrayList));
	}

	@Test
	public void containsAllCollectionTrue() {
		Assert.assertTrue(testIntegerArr.containsAll(smallIntArrayList));
	}

	@Test
	public void containsAllCollectionTrueOnlyOne() {
		testIntegerArrEmpty.add(0);
		testIntegerArrEmpty.add(1);
		testIntegerArrEmpty.add(2);
		testIntegerArrEmpty.add(3);
		Assert.assertFalse(testIntegerArrEmpty.containsAll(smallIntArrayList));

	}

	@Test
	public void isEmptyTestEmpty() {
		Assert.assertTrue(testIntegerArrEmpty.isEmpty());

	}

	@Test
	public void isEmptyTestNotEmpty() {
		Assert.assertFalse(testIntegerArr.isEmpty());

	}

	// iterator tests
	@Test
	public void removeIteratorTestAboveFive() {
		int count = 0;
		testIntegerArr.remove(0);

		while (intIterator.hasNext()) {
			intIterator.next();

			if (count > 4) {
				intIterator.remove();
			}

			count++;
		}
		testIntegerArr.remove(0);
		Assert.assertArrayEquals(expected, testIntegerArr.toArray());

	}

	@Test
	public void removeIteratorTest2() {
		int count = 0;
		while (intIterator.hasNext()) {

			if (count > 5) {
				intIterator.remove();
			}
			intIterator.next();
			count++;
		}

	}

	@Test
	public void nextTest() {
		for (int i = 0; i < 5; i++) {
			if (i == 0) {
				Assert.assertTrue(intIterator.next() == (Object) 0);
			} else if (i == 1) {
				Assert.assertTrue(intIterator.next() == (Object) 1);
			} else if (i == 2) {
				Assert.assertTrue(intIterator.next() == (Object) 2);
			} else if (i == 3) {
				Assert.assertTrue(intIterator.next() == (Object) 3);
			} else if (i == 4) {
				Assert.assertTrue(intIterator.next() == (Object) 4);
			}
		}

	}

	@Test
	public void hasNextTest() {
		for (int i = 0; i < 25; i++) {
			if (i < 25)
				Assert.assertTrue(intIterator.hasNext());
			else {
				Assert.assertFalse(intIterator.hasNext());
			}
			intIterator.next();
		}

	}

	@Test
	public void removeTestSuccessfulRemove() {
		Assert.assertTrue(testIntegerArr.remove(2));
		Assert.assertTrue(testIntegerArr.get(2).equals(3));

	}

	@Test
	public void removeTest() {

		Assert.assertTrue(testIntegerArr.remove(0));
	}

	@Test
	public void removeTestNothingRemoved() {
		Assert.assertFalse(testIntegerArr.remove(30));

	}

	@Test
	public void removeAllTestSuccessfull() {
		smallIntArrayList.add(1);
		Assert.assertTrue(testIntegerArr.removeAll(smallIntArrayList));
	}

	@Test
	public void removeAllDuplicates() {
		smallIntArrayList.add(1);
		smallIntArrayList.add(1);
		smallIntArrayList.add(4);
		smallIntArrayList.add(4);

		Assert.assertTrue(testIntegerArr.removeAll(smallIntArrayList));
	}

	@Test
	public void removeAllTestNothingRemoved() {
		smallIntArrayList.add(31);
		smallIntArrayList.add(32);
		smallIntArrayList.add(42);
		smallIntArrayList.add(45);

		Assert.assertFalse(testIntegerArr.removeAll(largeIntArrayList));
	}

	@Test
	public void toArrayTest() {

		testIntegerArrEmpty.add(1);
		testIntegerArrEmpty.add(2);
		testIntegerArrEmpty.add(3);
		testIntegerArrEmpty.add(4);
		testIntegerArrEmpty.add(5);

		Assert.assertArrayEquals(testIntegerArrEmpty.toArray(), expected);

	}

	@Test
	public void size() {
		Assert.assertTrue(testIntegerArr.size() == 25);

	}

	@Test
	public void sizeEmpty() {
		Assert.assertTrue(testIntegerArrEmpty.size() == 0);

	}
	
	@Test
	public void iterateNameCollection() {
		Person testName = (Person) nameCollIterator.next();
		assertEquals("Bean",testName.getName());
	}
	
	@Test
	public void removeName() {
		Person testName = new Person("Peter");
		assertTrue(nameCollection.remove(testName));
	}
	
	@Test
	public void containName() {
		Person testName = new Person("Valentine");
		assertTrue(nameCollection.contains(testName));
	}
	
	@Test
	public void hasNextNameTest() {
		for (int i = 0; i < 5; i++) {
			nameCollIterator.next();
		}
		assertFalse(nameCollIterator.hasNext());
	}
}
