package assignment03;

import static org.junit.Assert.*;

import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the BinarySearchSet class by testing the various methods with both a Comparable and Comparator
 * @author Patrick Ekel and Kyle Price
 * @date 9/14/16 
 *
 */


public class BinarySearchSetTest {

	double x1 = 10;
	double y1 = 50;
	double x2 = 40;
	double y2 = 50;
	double xCorr =15;
	double yCorr =15;

	BinarySearchSet<Integer> set;
	ArrayList<Integer> intList;
	ArrayList<String> stringList;
	Iterator<Integer> iterator;
	BinarySearchSet<String> stringSet;

	ArrayList<Shape> shapeList;
	ArrayList<Shape> smallShapeList;
	BinarySearchSet<Shape> rectanglesList;
	BinarySearchSet<Shape> shapesList;

	Shape square;
	Shape rectangle;
	Shape smallSquare;
	Shape largeRectangle;

	// Create Comparator object for shape class with compare method
	Comparator<Shape> comp = new Comparator<Shape>() {
		@Override
		public int compare(Shape lhs, Shape rhs) {
			if (lhs.contains(xCorr, yCorr) == (rhs.contains(xCorr, yCorr))) {
				return 0;
			} else if (lhs.contains(xCorr, yCorr) != (rhs.contains(xCorr, yCorr))) {
				return -1;
			} else {
				return 1;
			}
		}
	};

	// Set up some sets which will be used in tests

	@Before
	public void setUp() throws Exception {
		set = new BinarySearchSet<>();
		set.add(6);
		set.add(9);
		set.add(9);
		set.add(77);
		set.add(77);
		set.add(6);
		set.add(9);
		set.add(6);
		set.add(77);
		set.add(77);
		set.add(3);
		set.add(99);
		set.add(5);

		intList = new ArrayList<Integer>();
		intList.add(1);
		intList.add(10);
		intList.add(20);
		intList.add(15);

		stringSet = new BinarySearchSet<>();
		stringSet.add("frodo");
		stringSet.add("sam");
		stringSet.add("smeagol");
		stringSet.add("treebeard");
		stringSet.add("gandalf");

		stringList = new ArrayList<String>();
		stringList.add("smeagol");
		stringList.add("gandalf");

		shapeList = new ArrayList<Shape>();
		rectanglesList = new BinarySearchSet<Shape>(comp);
		shapesList = new BinarySearchSet<Shape>(comp);


		square = new Rectangle.Double(0, 0, 5, 5);
		rectangle = new Rectangle.Double(0, 0, 50, 20);
		smallSquare = new Rectangle.Double(0,0,2,2);
		largeRectangle = new Rectangle.Double(0,0,70, 90);
		smallShapeList = new ArrayList<Shape>();
		smallShapeList.add(square);
		smallShapeList.add(rectangle);

	}
	@After
	public void tearDown() throws Exception {
	}

	
	// remove() tests using Comparator and Comparable
	@Test
	public void testRemoveAgain() {
		assertTrue(set.contains(5));
		set.remove(5);
		assertEquals(false, set.contains(5));
	}
	@Test
	public void testRemoveMore() {
		set.remove(100);
	}
	@Test
	public void testRemoveOnSquare() {
		smallShapeList.remove(square);
		smallShapeList.remove(rectangle);
		assertTrue(smallShapeList.size() == 0);
	}
	@Test
	public void testRemoveOnRectangle() {
		smallShapeList.add(smallSquare);
		smallShapeList.add(largeRectangle);
		assertTrue(smallShapeList.size() == 4);
	}
	@Test
	public void testRemoveWithStringObject() {
		assertTrue(stringSet.contains("frodo"));
		stringSet.remove("frodo");
		assertFalse(stringSet.contains("frodo"));
	}
	
	// removeAll tests using Comparator and Comparable
	@Test
	public void testRemoveAllInteger() {
		intList.add(99);
		set.removeAll(intList);
		assertFalse(set.contains(99));
	}
	@Test
	public void testRemoveAllStrings() {
		assertTrue(stringSet.contains("smeagol"));
		stringSet.removeAll(stringList);
		assertFalse(stringSet.contains("smeagol"));
	}
	@Test
	public void testRemoveAllShapes() {
		shapeList.add(rectangle);
		smallShapeList.removeAll(shapeList);
		assertFalse(smallShapeList.contains(rectangle));
	}
	public void testRemoveAllMultipleShapes() {
		shapeList.add(rectangle);
		shapeList.add(square);
		smallShapeList.removeAll(shapeList);
		assertFalse(smallShapeList.contains(rectangle) || smallShapeList.contains(square));
	}
	
	// first tests using Comparator and Comparable
	@Test
	public void testFirstSimple() {
		assertTrue(set.first().equals(3));
	}

	@Test
	public void testFirstAfterAdding0() {
		set.add(0);
		assertTrue(set.first().equals(0));
	}

	@Test(expected = NoSuchElementException.class)
	public void testFirstException() {
		set.clear();
		set.first();
	}
	@Test
	public void testFirstAddCorrectPlaceMultiple() {
		set.clear();
		set.add(1);
		set.add(1);
		set.add(1);
		assertEquals(1, set.size());
	}
	@Test
	public void testFirstRectangles() {
		rectanglesList.add(square);
		Object [] squareArr = new Object[2];
		squareArr[0] = square;
		assertEquals(squareArr[0], rectanglesList.first());
	}
	
	//last tests using Comparator and Comparable
	@Test
	public void testLastShapes() {
		rectanglesList.add(largeRectangle);
		rectanglesList.add(square);
		Object [] rectArr = new Object[2];
		rectArr[0] = square;
		assertEquals(largeRectangle, rectanglesList.last());
	}
	@Test(expected = NoSuchElementException.class)
	public void testLastException() {
		set.clear();
		set.last();
	}
	@Test
	public void testLastIntegers() {
		set.add(1000);
		assertTrue(set.last().equals(1000));
	}
	@Test
	public void testLastIntegersClear() {
		set.add(1000);
		set.clear();
		set.add(8);
		assertEquals(set.first(), set.last());
	}

	// addAll tests using Comparator and Comparable 
	@Test
	public void testAddAllIntList() {
		set.clear();
		set.addAll(intList);
		assertEquals(4, set.size());
	}

	@Test
	public void testAddAllSpecific() {
		set.clear();
		intList.add(1000);
		set.addAll(intList);
		assertEquals(true, set.contains(1000));
	}
	@Test
	public void testAddAllShapes() {
		shapeList.addAll(smallShapeList);
		assertEquals(true, shapeList.contains(rectangle));

	}
	@Test
	public void testAddAllShapesLackOf() {
		smallShapeList.add(smallSquare);
		shapeList.addAll(smallShapeList);
		assertEquals(true, shapeList.contains(smallSquare));
	}
	@Test
	public void testAddAllShapesRemoved() {
		smallShapeList.add(smallSquare);
		shapeList.addAll(smallShapeList);
		shapeList.remove(smallSquare);
		assertEquals(false, shapeList.contains(smallSquare));
	}

	// clear tests for Comparator and Comparable
	@Test
	public void testClearSet() {
		set.clear();
		assertEquals(0, set.size());
	}
	@Test
	public void testClearBiggerSet() {
		set.clear(); // clear set first
		for (int i = 0; i < 150; i++) {
			set.add(i); // insert 150 items
		}
		assertTrue(150 == set.size()); // verify that
		set.clear();
		assertTrue(0 == set.size()); // and finally it should be cleared
	}
	@Test
	public void testClearShapes() {
		smallShapeList.add(square);
		smallShapeList.add(smallSquare);
		smallShapeList.add(largeRectangle);	
		smallShapeList.clear();
		assertEquals(0, smallShapeList.size());
	}
	
	// contains tests using comparable and comparator
	@Test
	public void testContainsBasic() {
		assertTrue(set.contains(99));
	}
	@Test
	public void testContainsLow() {
		assertTrue(set.contains(6));
	}
	@Test
	public void testContainsNothing() {
		set.clear();
		assertFalse(set.contains(6));
	}

	@Test
	public void testContainsAllBasic() {
		assertFalse(set.containsAll(intList));
	}

	@Test
	public void testContainsAllHasTheElements() {
		set.addAll(intList);
		assertTrue(set.containsAll(intList));
	}

	@Test
	public void testContainsAllMost() {
		set.addAll(intList);
		set.remove(20);
		assertFalse(set.containsAll(intList));
	}
	@Test
	public void testContainsRectangles() {
		smallShapeList.add(smallSquare);
		shapeList.addAll(smallShapeList);
		assertTrue(shapeList.containsAll(smallShapeList));
	}

	// isEmpty tests Comparable and Comparator
	@Test
	public void testIsEmtpyBasicInt() {
		set.clear();
		assertTrue(set.isEmpty());
	}

	@Test
	public void testIsEmptyHasOneItem() {
		set.clear();
		set.add(0);
		assertFalse(set.isEmpty());
	}
	@Test
	public void testIsEmptyectangles() {
		shapeList.addAll(smallShapeList);
		assertFalse(shapeList.isEmpty());
	}
	// Iterator tests using Comparable and Comparator
	@Test
	public void testIteratorIntHasNext() {
		assertTrue(set.iterator().hasNext());
	}
	@Test
	public void testIteratorIntEmptyt() {
		set.clear();
		assertFalse(set.iterator().hasNext());
	}
	@Test
	public void testIteratorIntNext() {
		set.clear();
		set.addAll(intList);
		assertTrue(set.iterator().next().equals(1));
	}
	@Test
	public void testIteratorIntNextShape() {
		shapesList.add(square);
		shapesList.add(smallSquare);
		assertEquals(square, shapesList.iterator().next());
	}

	// size tests using Comparable and Comparator
	@Test
	public void testSizeInt() {
		set.clear();
		set.add(0);
		set.add(1);
		set.add(2);
		assertEquals(3, set.size());
	}
	@Test
	public void testSizeRectangles() {
		shapeList.add(smallSquare);
		assertTrue(shapeList.size()==1);
	}
	@Test
	public void testSizeRemovedRectangles() {
		shapeList.add(smallSquare);
		shapeList.add(square);
		shapeList.removeAll(smallShapeList);
		assertTrue(shapeList.size()==1);
	}
	@Test
	public void testSizeGenericShapeMultipleSame() {
		rectanglesList.add(rectangle);
		rectanglesList.add(rectangle);
		rectanglesList.add(rectangle);
		assertEquals(1, rectanglesList.size());
	} 
	@Test
	public void testSizeGenericShapeMultipleDiff() {
		shapeList.add(square);
		shapeList.add(rectangle);
		assertTrue(shapeList.size() == 2);
	}
	@Test
	public void testAddAllRemoveGenericObject() {
		shapeList.addAll(smallShapeList);

		assertEquals(smallShapeList, shapeList);
	}

	@Test
	public void testAddAllRemoveeGenericObject() {
		shapeList.addAll(smallShapeList);
		assertTrue(shapeList.size() == 2);
		shapeList.removeAll(smallShapeList);
		assertTrue(shapeList.size() == 0);
	}
	@Test
	public void testSizeLarge() {
		set.clear();
		for (int i = 0; i < 100; i++) {
			set.add(i);
		}
		assertEquals(100, set.size());
	}

	// toArray tests using Comparator and Comparable
	@Test
	public void testToArrayInt() { /// getting an error from this one
		set.clear();
		set.add(0);
		set.add(1);
		set.add(2);
		set.add(0);
		set.add(0);
		Object[] arr = { 0, 1, 2 };
		Object[] arrTwo = set.toArray();
		assertTrue(arr[1] == arrTwo[1]);
	}
	@Test
	public void testToArrayIntLaterIndex() { /// getting an error from this one
		set.clear();
		set.add(0);
		set.add(1);
		set.add(2);
		set.add(0);
		set.add(0);
		Object[] arr = { 0, 1, 2 };
		Object[] arrTwo = set.toArray();
		assertTrue(arr[2] == arrTwo[2]);
	}
	@Test
	public void testToArrayAfterAddingSquare() {
		smallShapeList.add(smallSquare);
		Object [] squareArr = new Object[2];
		squareArr[0] = square;
		Object [] toSquareArr = smallShapeList.toArray();
		assertEquals(squareArr[0], toSquareArr[0]);
	}
	
	
	// binSearch tests with Comparator and Comparable

	@Test
	public void testBinSearchShape() {
		shapesList.add(square);
		assertEquals(0, shapesList.binSearch(square));
	}
	@Test
	public void testBinSearchMultipleShapes() {
		shapesList.add(smallSquare);
		shapesList.add(square);
		assertEquals(0, shapesList.binSearch(square));
	}
	@Test
	public void testBinSearchMultipleShapesSecondIndex() {
		shapesList.add(smallSquare);
		shapesList.add(square);
		shapesList.add(rectangle);
		assertEquals(1, shapesList.binSearch(smallSquare));
	}
	
	@Test
	public void testBinSearchInt() {
		BinarySearchSet<Integer> smallSet = new BinarySearchSet<Integer>();
		smallSet.add(1);
		smallSet.add(2);
		smallSet.add(4);
		smallSet.add(5);
		assertTrue(smallSet.binSearch(3)==2);
	}
	@Test
	public void testBinSearchIntAlreadyPresent() {
		BinarySearchSet<Integer> smallSet = new BinarySearchSet<Integer>();
		smallSet.add(1);
		smallSet.add(2);
		smallSet.add(4);
		smallSet.add(5);
		assertTrue(smallSet.binSearch(1)==0);
	}
}