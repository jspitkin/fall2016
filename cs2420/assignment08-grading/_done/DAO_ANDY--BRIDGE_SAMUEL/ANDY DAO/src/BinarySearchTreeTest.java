package assignment08;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit Class for BinarySearchTree
 * 
 * @author Andy Dao, uID: u0692334
 * @author Sam Bridge, uID: u0984334
 */
public class BinarySearchTreeTest {

    private static final int RIGHT_HEAVY_SET_SIZE = 15;

    // Declare some BST's && ArrayLists
    BinarySearchTree<Integer> emptyTree;

    ArrayList<Integer> emptyList, rightHeavyList, listWithNullObject, randomOrder;

    @Before
    public void setUp() throws Exception {
	emptyTree = new BinarySearchTree<Integer>();
	emptyList = new ArrayList<Integer>();

	// Setup a right heavy list 
	rightHeavyList = new ArrayList<Integer>();
	for (int i = 0; i < RIGHT_HEAVY_SET_SIZE; i++) {
	    rightHeavyList.add(i); // Add all integers from 0 to declare set size
	}

	// Setup a list that has a null object in it
	listWithNullObject = new ArrayList<Integer>();
	listWithNullObject.add(5);
	listWithNullObject.add(10);
	listWithNullObject.add(15);
	listWithNullObject.add(20);
	listWithNullObject.add(null); // The null object that will cause an NPE
	listWithNullObject.add(25);
	listWithNullObject.add(30);

    }

    @After
    public void tearDown() throws Exception {
	emptyTree = null;
	rightHeavyList = null;
	listWithNullObject = null;
    }

    @Test
    public void add() {
	assertTrue(emptyTree.add(5));
	assertTrue(emptyTree.add(10));
	assertTrue(emptyTree.add(15));
	assertTrue(emptyTree.add(20));
	assertTrue(emptyTree.add(25));
    }

    @Test
    public void addDuplicate() {
	emptyTree.add(5);
	assertFalse(emptyTree.add(5));
    }

    @Test
    public void addAll() {
	assertTrue(emptyTree.addAll(rightHeavyList));
    }

    @Test
    public void clear() {
	emptyTree.addAll(rightHeavyList);
	emptyTree.clear();
	assertEquals(0, emptyTree.size());
    }

    @Test
    public void contains() {
	emptyTree.add(25);
	emptyTree.add(20);
	emptyTree.add(5);
	emptyTree.add(10);
	emptyTree.add(15);
	assertTrue(emptyTree.contains(5));
    }

    @Test
    public void contains_NotInBST() {
	emptyTree.add(25);
	emptyTree.add(20);
	emptyTree.add(5);
	emptyTree.add(10);
	emptyTree.add(15);
	assertFalse(emptyTree.contains(100));
    }

    @Test
    public void containsAll() {
	emptyTree.addAll(rightHeavyList);
	assertTrue(emptyTree.containsAll(rightHeavyList));
    }

    @Test
    public void containsAll_EmptyList() {
	assertTrue(emptyTree.containsAll(emptyList));
    }

    @Test
    public void first() {
	emptyTree.add(25);
	emptyTree.add(20);
	emptyTree.add(5);
	emptyTree.add(10);
	emptyTree.add(15);
	assertEquals((Integer) 5, emptyTree.first()); // Remember, doesn't return the root. Returns the smallest item in BST
    }

    @Test
    public void isEmpty() {
	assertTrue(emptyTree.isEmpty());
    }

    @Test
    public void isEmpty_NotEmpty() {
	emptyTree.add(10);
	assertFalse(emptyTree.isEmpty());
    }

    @Test
    public void last() {
	emptyTree.add(25);
	emptyTree.add(20);
	emptyTree.add(5);
	emptyTree.add(10);
	emptyTree.add(15);
	assertEquals((Integer) 25, emptyTree.last()); // Remember, doesn't return the root. Returns the largest item in BST
    }

    @Test
    public void remove() {
	emptyTree.add(25);
	emptyTree.add(20);
	emptyTree.add(5);
	emptyTree.add(10);
	emptyTree.add(15);

	assertTrue(emptyTree.remove(5)); // Able to remove 5
	assertEquals(4, emptyTree.size()); // Also check size
    }

    @Test
    public void remove_NotInBST() {
	emptyTree.add(25);
	emptyTree.add(20);
	emptyTree.add(5);
	emptyTree.add(10);
	emptyTree.add(15);

	assertFalse(emptyTree.remove(100)); // Return false as it doesn't exist in the BST
	assertEquals(5, emptyTree.size()); // Also check size
    }

    @Test
    public void removeAll() {
	emptyTree.add(15);
	emptyTree.add(7);
	emptyTree.add(5);
	emptyTree.add(13);
	emptyTree.add(2);

	assertTrue(emptyTree.removeAll(rightHeavyList)); // Returns true because it it was able to remove at least 1 item, with the input collection list
	assertEquals(1, emptyTree.size());
	assertEquals((Integer) 15, emptyTree.first());
    }

    @Test
    public void size() {
	emptyTree.add(5);
	emptyTree.add(10);
	emptyTree.add(15);
	emptyTree.add(20);
	emptyTree.add(25);
	assertEquals(5, emptyTree.size());
    }

    @Test
    public void sizeDealingWithDuplicates() {
	emptyTree.add(5);
	emptyTree.add(10);
	emptyTree.add(10); // Duplicate
	emptyTree.add(20);
	assertEquals(3, emptyTree.size());
    }

    @Test
    public void toArrayList() {
	// Create an sorted order (right heavy) list 
	ArrayList<Integer> sortedOrder = new ArrayList<Integer>();
	sortedOrder.addAll(rightHeavyList); // by adding all of rightHeavyList

	// Randomize / Shuffle the rightHeavyList
	Collections.shuffle(rightHeavyList);
	emptyTree.addAll(rightHeavyList); // The emptyTree adds all of rightHeavy list

	// When emptyTree.toArrayList get's called, it should turn the BST into an array list that's sorted order
	for (int i = 0; i < sortedOrder.size(); i++) {
	    // This is where we check if it's in sorted order (after it was randomized just before the for-loop
	    assertTrue(sortedOrder.get(i) == emptyTree.toArrayList().get(i));
	}

    }

    // ========== THROW EXCEPTIONS TESTS ========== //

    @Test(expected = NullPointerException.class)
    public void add_NPE() {
	emptyTree.add(null);
    }

    @Test(expected = NullPointerException.class)
    public void addAll_NPE() {
	emptyTree.addAll(listWithNullObject);
    }

    @Test(expected = NullPointerException.class)
    public void contains_NPE() {
	emptyTree.add(5);
	emptyTree.contains(null);
    }

    @Test(expected = NullPointerException.class)
    public void containsAll_NPE() {
	// Adding manually all items so that we don't throw exception on an addAll method call
	emptyTree.add(5);
	emptyTree.add(10);
	emptyTree.add(15);
	emptyTree.add(20);
	emptyTree.add(25);
	emptyTree.add(30);

	emptyTree.containsAll(listWithNullObject);
    }

    @Test(expected = NoSuchElementException.class)
    public void first_NSEE() {
	emptyTree.first();
    }

    @Test(expected = NoSuchElementException.class)
    public void last_NSEE() {
	emptyTree.last();
    }

    @Test(expected = NullPointerException.class)
    public void remove_NPE() {
	emptyTree.remove(null);
    }

    @Test(expected = NullPointerException.class)
    public void removeAll_NPE() {
	emptyTree.removeAll(listWithNullObject);
    }
}
