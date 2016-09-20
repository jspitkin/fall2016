//Cole Cruz and Colton Haacke
package assignment03;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * JUnit tester for the BinarySearchSet class.
 * 
 * @author Cole Cruz and Colton Haacke
 */
public class BinarySearchSetJUnitTester {
	
    Comparator<Integer> comp = new SearchSetComparator<>();
    Iterator<Object> iter;
    BinarySearchSet<Integer> mainSet;
    BinarySearchSet<Integer> emptySet;
    static ArrayList<Integer> evens = new ArrayList<>();
    static ArrayList<Integer> multiplesOf3 = new ArrayList<>();
    static ArrayList<Integer> oddMultiplesOf3 = new ArrayList<>();
    static ArrayList<Integer> multiplesOf5 = new ArrayList<>();
    static ArrayList<Integer> nullCollection = new ArrayList<>();
	BinarySearchSet<Object> testSet1 = new BinarySearchSet<>();
	BinarySearchSet<Integer> testSet2 = new BinarySearchSet<>(comp);
	ArrayList<Object> collection1 = new ArrayList<>();
	ArrayList<Object> collection2 = new ArrayList<>();
	
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
	for (int numToBeAdded = 2; numToBeAdded < 1000; numToBeAdded += 2) {
	    evens.add(numToBeAdded);
	}
	
	for (int numToBeAdded = 3; numToBeAdded < 1000; numToBeAdded += 3) {
	    multiplesOf3.add(numToBeAdded);
	}
	
	for (int numToBeAdded = 3; numToBeAdded < 1000; numToBeAdded += 6) {
	    oddMultiplesOf3.add(numToBeAdded);
	}
	
	for (int numToBeAdded = 5; numToBeAdded < 1000; numToBeAdded += 5) {
	    multiplesOf5.add(numToBeAdded);
	}
	
	for (int count = 0; count < 10; count++) {
	    nullCollection.add(null);
	}
    }
	
	/**
	 * Whatever setup needs to be done before each test method.
	 */
	@Before
	public void setup() {
		collection1.add(1);
		collection1.add(29834);
		testSet1.addAll(collection1);
		testSet1.add(10985);
		mainSet = new BinarySearchSet<Integer>(comp);
		emptySet = new BinarySearchSet<Integer>(comp);	
		mainSet.addAll(evens);
		mainSet.addAll(multiplesOf3);
	}
	
	//Various tests for each method in the BinarySearchSet class
	@Test
	public void binarySearchSetWithoutComparator() {
		Assert.assertTrue(testSet1.getCompareType() == "comparable");
	}
	
	@Test
	public void binarySearchSetWithComparator() {
		Assert.assertTrue(testSet2.getCompareType()=="comparator");
		Assert.assertEquals(comp, testSet2.comparator());
	}
	
	@Test
	public void comparatorTest() {
		Assert.assertEquals(comp, emptySet.comparator());
	}
	
	@Test
	public void first() {
		Assert.assertEquals(1, testSet1.first());
	}
	
	@Test (expected=NoSuchElementException.class)
	public void firstThrow() {
		testSet2.first();
	}
	
	@Test
	public void last() {
		assertTrue(999 == mainSet.last());
	}
	
	@Test (expected=NoSuchElementException.class)
	public void lastThrow() {
		emptySet.last();
	}
	
	@Test
	public void add() {
		Assert.assertTrue(testSet1.add(31));
		Assert.assertFalse(testSet1.add(1));
	}
	
    @Test
    public void addAllChangesSet() {
	assertFalse(mainSet.contains(5));
	assertTrue(mainSet.addAll(multiplesOf5));
	assertTrue(mainSet.contains(5));
    }

    @Test
    public void addAllDoesntChangeSet() {
	assertTrue(mainSet.contains(3));
	assertFalse(mainSet.addAll(multiplesOf3));
	assertTrue(mainSet.contains(3));
    }
    
    @Test
    public void addAllNulls() {
	assertFalse(mainSet.addAll(nullCollection));
    }
    
	@Test
	public void clear() {
		testSet1.clear();
		Assert.assertTrue(testSet1.isEmpty());
	}
	
    @Test
    public void containsExists() {
	assertTrue(mainSet.contains(333));
	assertTrue(mainSet.contains(500));
    }
    
    @Test
    public void containsNotExists() {
	assertFalse(mainSet.contains(335));
	assertFalse(mainSet.contains(503));
    }
    
    @Test
    public void containsNull() {
	assertFalse(mainSet.contains(null));
    }
	
	@Test
	public void containsAll() {
		Assert.assertTrue(testSet1.containsAll(collection1));
	}
	
    @Test
    public void isEmptyTrue() {
	assertTrue(emptySet.isEmpty());
    }
    
    @Test
    public void isEmptyFalse() {
	assertFalse(mainSet.isEmpty());
    }
	
	@Test
	public void iterator() {
	    	iter = testSet1.iterator();
	    	assertTrue(iter.hasNext());
	    	assertEquals(1, iter.next());
	    	assertTrue(iter.hasNext());
	    	assertEquals(10985, iter.next());
	    	assertTrue(iter.hasNext());
	    	assertEquals(29834, iter.next());
	    	assertFalse(iter.hasNext());
	}
	
    @Test
    public void removeExists() {
	assertTrue(mainSet.contains(6));
	int mainSetSize = mainSet.size();
	assertTrue(mainSet.remove(6));
	assertFalse(mainSet.contains(6));
	assertEquals(mainSet.size(), mainSetSize - 1);
    }
    
    @Test
    public void removeNotExists() {
	assertFalse(mainSet.contains(5));
	int mainSetSize = mainSet.size();
	assertFalse(mainSet.remove(5));
	assertFalse(mainSet.contains(5));
	assertEquals(mainSet.size(), mainSetSize);
    }
    
    @Test
    public void removeNull() {
	assertFalse(mainSet.contains(null));
	int mainSetSize = mainSet.size();
	assertFalse(mainSet.remove(null));
	assertFalse(mainSet.contains(null));
	assertEquals(mainSet.size(), mainSetSize);
    }
	
	@Test
	public void removeAll() {
		Assert.assertFalse(testSet2.removeAll(collection2));
	}
	
    @Test
    public void testSize() {
	assertEquals(0, emptySet.size());
	emptySet.add(2);
	assertEquals(1, emptySet.size());
	emptySet.addAll(multiplesOf5);
	assertEquals(200, emptySet.size());
	emptySet.removeAll(multiplesOf5);
	assertEquals(1, emptySet.size());
	emptySet.add(3);
	assertEquals(2, emptySet.size());
	emptySet.clear();
	assertEquals(0, emptySet.size());
    }
	
	@Test
	public void toArray() {
		Assert.assertArrayEquals(new Object[]{1,10985,29834}, testSet1.toArray());
	}
	
}
