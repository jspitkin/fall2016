package assignment10;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Andrew Worley: u0651238
 */
public class QuadProbeHashTableJUnitTester {
	QuadProbeHashTable hashTable;
	String[] similarStrings;
	String[] smStringArr;
	ArrayList<String> medStringList;
	ArrayList<String> lrgStringList;

	@Before
	public void setUp() throws Exception {
		//using zero as an initial size is the worst choice
//		hashTable = new QuadProbeHashTable(0, new BadHashFunctor());
//		hashTable = new QuadProbeHashTable(0, new MediocreHashFunctor());
		hashTable = new QuadProbeHashTable(0, new GoodHashFunctor());
		
		String[] forSimilarStrings = {"jello", 
									  "zello",
									  "kello",
									  "vello",
									  "fellow", 
									  "hello", 
									  "hollow", 
									  "cello", 
									  "mellow", 
									  "bello", 
									  "bellows"};
		String[] forSmStringArr = {"hey","you","guys","is","there","anybody","out","there","?"};
		medStringList = new ArrayList<String>();
		lrgStringList = new ArrayList<String>();
		
		for (int index = 0; index < 100; index++) {
			String input = "";
			for (int i = 0; i < 10; i++) {
				input += ""+(char) (new Random().nextInt(122) + 33);
			}
			medStringList.add(input);
		}
		
		for (int index = 0; index < 1_000; index++) {
			String input = "";
			for (int i = 0; i < 10; i++) {
				input += ""+(char) (new Random().nextInt(122) + 33);
			}
			lrgStringList.add(input);
		}
		
		similarStrings = forSimilarStrings;
		smStringArr = forSmStringArr;
	}
	
	//add tests
	
	@Test (expected = NullPointerException.class)
	public void testAddNull() {
		hashTable.add(null);
	}
	
	@Test
	public void testAddDuplicate() {
		hashTable.add("hello");
		assertEquals(false,hashTable.add("hello"));
	}

	@Test
	public void testAddTestCollisons() {
		for (String item : similarStrings) {
			hashTable.add(item);
		}
	}
	
	//addAll tests
	
	@Test (expected = NullPointerException.class)
	public void testAddAllNull() {
		hashTable.addAll(null);
	}
	
	@Test
	public void testAddAllEmptyList() {
		ArrayList<String> empty = new ArrayList<String>();
		
		assertEquals(false,hashTable.addAll(empty));
	}
	
	@Test
	public void testAddAllMedStringList() {
		hashTable.addAll(medStringList);
		assertEquals(false,hashTable.isEmpty());
	}
	
	@Test
	public void testAddAllLrgStringList() {
		hashTable.addAll(lrgStringList);
		assertEquals(false,hashTable.isEmpty());
	}
	
	//clear tests
	
	@Test
	public void testClearWhenEmpty() {
		hashTable.clear();
		
		assertEquals(0,hashTable.size());
	}
	
	@Test
	public void testClearWhenPopulated() {
		hashTable.addAll(medStringList);
		hashTable.clear();

		assertEquals(0,hashTable.size());
	}
	
	@Test
	public void testClearThenAdd() {
		hashTable.addAll(medStringList);
		hashTable.clear();
		hashTable.addAll(medStringList);
		
		assertEquals(false, hashTable.isEmpty());
	}
	
	//contains tests
	
	@Test (expected = NullPointerException.class)
	public void testContainsNull() {
		hashTable.contains(null);
	}
	
	@Test
	public void testContainsWhenTrue() {
		hashTable.add("imInHere");
		assertEquals(true, hashTable.contains("imInHere"));
	}
	
	@Test
	public void testContainsOnEmptySet() {
		assertEquals(false, hashTable.contains("youInThere?"));
	}
	
	@Test
	public void testContainsOnFilledSetWhenFalse() {
		for (String item : similarStrings) {
			hashTable.add(item);
		}
		assertEquals(false, hashTable.contains("youInThere?"));
	}
	
	//containsAll tests
	
	@Test (expected = NullPointerException.class)
	public void testContainsAllNullFilledSet() {
		hashTable.addAll(medStringList);
		
		assertEquals(false, hashTable.containsAll(null));
	}
	
	@Test (expected = NullPointerException.class)
	public void testContainsAllNullEmptySet() {		
		assertEquals(false, hashTable.containsAll(null));
	}
	
	@Test
	public void testContainsAllAnItemIsContained() {
		for (String item : smStringArr) {
			hashTable.add(item);
		}
		
		ArrayList<String> words = new ArrayList<String>();
		words.add("word");
		words.add("is");
		words.add("for");
		words.add("there");
		
		assertEquals(true, hashTable.containsAll(words));	
	}
	
	@Test
	public void testContainsAllAnItemIsNotContained() {
		for (String item : smStringArr) {
			hashTable.add(item);
		}
		
		ArrayList<String> words = new ArrayList<String>();
		words.add("word");
		words.add("okay");
		words.add("but");
		words.add("forever");
		
		assertEquals(false, hashTable.containsAll(words));	
	}
	
	//isEmpty tests
	
	@Test
	public void testIsEmptyWhenEmpty() {
		assertEquals(true, hashTable.isEmpty());
	}
	
	@Test
	public void testIsEmptyPostAddAll() {
		hashTable.addAll(medStringList);
		
		assertEquals(false, hashTable.isEmpty());
	}
	
	@Test
	public void testIsEmptyPostClear() {
		hashTable.addAll(medStringList);
		hashTable.clear();
		
		assertEquals(true, hashTable.isEmpty());
	}
	
	//size tests
	
	@Test
	public void testSizeWhenEmpty() {
		assertEquals(0,hashTable.size());
	}
	
	@Test
	public void testSizeWithAddinOneByOne() {
		hashTable.add("hello");
		hashTable.add("this");
		hashTable.add("is");
		hashTable.add("a");
		hashTable.add("test");
		assertEquals(5, hashTable.size());
	}
	
	@Test
	public void testSizePostAddingAll() {
		hashTable.addAll(medStringList);
		assertEquals(100, hashTable.size());
	}
	
	@Test
	public void testSizePostClear() {
		hashTable.addAll(medStringList);
		hashTable.clear();
		assertEquals(0, hashTable.size());
	}
	
	//getNextPrime tests
	
	@Test
	public void testNextPrimeWithZero() {
		assertEquals(2,hashTable.getNextPrime(0));
	}
	
	@Test
	public void testNextPrimeWithOne() {
		assertEquals(2,hashTable.getNextPrime(1));
	}
	
	@Test
	public void testNextPrimeWithFour() {
		assertEquals(5,hashTable.getNextPrime(4));
	}
	
	@Test
	public void testNextPrimeWithNine() {
		assertEquals(11,hashTable.getNextPrime(9));
	}
	
	@Test
	public void testNextPrimeWithSmNum() {
		assertEquals(23,hashTable.getNextPrime(20));
	}
	
	//isPrime tests, Note: 1 and 0 are neither prime nor composite
	
	@Test
	public void testIsPrimeWithZero() {
		assertEquals(false, hashTable.isPrime(0));
	}
	
	@Test
	public void testIsPrimeWithOne() {
		assertEquals(false, hashTable.isPrime(1));
	}
	
	@Test
	public void testIsPrimeWithPrimeNum() {
		assertEquals(true, hashTable.isPrime(23));
	}
	
	@Test
	public void testIsPrimeWithNonPrimeNum() {
		assertEquals(false, hashTable.isPrime(10));
	}

}
