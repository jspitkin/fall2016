package assignment10;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for methods in QuadProbeHashTable class
 * @author Jana Klopsch (u0854469)
 * Last Modified 11-9-16
 *
 */
public class QuadProbeHashTableTest {
	
	int smallPrime;
	int medPrime;
	int bigPrime;
	QuadProbeHashTable basicHash;
	HashFunctor badHash;
	ArrayList<String> wordList;
	ArrayList<String> collisionList;
	ArrayList<String> rehashingList;

	@Before
	public void setUp() throws Exception {
		smallPrime = 5;
		medPrime = 13;
		bigPrime = 37;
		
		badHash = new BadHashFunctor();
		basicHash = new QuadProbeHashTable(medPrime, badHash);
		
		wordList = new ArrayList<String>();
		wordList.add("bees");
		wordList.add("dog");
		wordList.add("mouse");
		
		collisionList = new ArrayList<String>();
		collisionList.addAll(wordList);
		collisionList.add("cat");
		collisionList.add("bird");
		collisionList.add("bat");
		
		rehashingList = new ArrayList<String>();
		rehashingList.addAll(collisionList); 
		rehashingList.add("duck");
		rehashingList.add("hippopotamus");//new table of 29
		rehashingList.add("reallyLongWordThatWillBeLongerThanCapacity");//42 chars % 29 = 13
	}

	@After
	public void tearDown() throws Exception {
	}

	//isPrime
	@Test
	public void testIsPrimeBasic() {
		assertTrue(basicHash.isPrime(smallPrime));
		assertTrue(basicHash.isPrime(bigPrime));
	}
	
	@Test
	public void testIsPrimeEdges() {
		assertFalse(basicHash.isPrime(99));
		assertTrue(basicHash.isPrime(0));
	}
	//nextPrime
	@Test
	public void testNextPrimeBasic() {
		assertTrue(basicHash.nextPrime(89) == 97);
	}
	
	@Test
	public void testnextPrimeBasic() {
		assertTrue(basicHash.nextPrime(-1) == 0);
		assertTrue(basicHash.nextPrime(0) == 1);
	}
	
	//add(String item)
	@Test
	public void testAddBasic() {
		basicHash.add("dog");
		basicHash.add("hippopotamus");
		basicHash.add("fish");
		assertTrue(basicHash.size() == 3);
		assertTrue(basicHash.contains("dog"));
	}
	@Test
	public void testAddWithCollisions() {
		basicHash.add("dog");
		basicHash.add("cat");
		basicHash.add("big");
		basicHash.add("key");
		assertTrue(basicHash.size() == 4);
		assertTrue(basicHash.contains("key"));
	}
	
	
	//addAll
	@Test
	public void testAddAllBasic() {
		assertTrue(basicHash.addAll(wordList));
		assertTrue(basicHash.size() == 3);
	}
	@Test
	public void testAddAllWithCollisions() {
		assertTrue(basicHash.addAll(collisionList));
		assertFalse(basicHash.isEmpty());
	}
	@Test
	public void testAddWithRehashingTable(){
		assertTrue(basicHash.addAll(rehashingList));
		assertTrue(basicHash.contains("duck"));
	}
	
	//clear
	@Test
	public void testClearBasic() {
		basicHash.addAll(wordList);
		basicHash.clear();
		assertTrue(basicHash.isEmpty());
		assertFalse(basicHash.contains("dog"));
	}
	@Test
	public void testClearEmptyCase() {
		basicHash.clear();
		assertFalse(basicHash.contains("moose"));
		assertTrue(basicHash.isEmpty());
	}
	
	//contains
	@Test
	public void testConatinsBasic(){
		basicHash.addAll(wordList);
		assertTrue(basicHash.contains("dog"));
	}
	@Test
	public void testContainsWithCollisions(){
		basicHash.addAll(collisionList);
		assertTrue(basicHash.contains("bat"));
	}
	@Test
	public void testContainsWithRehashing(){
		basicHash.addAll(rehashingList);
		assertTrue(basicHash.contains("duck"));
		assertTrue(basicHash.contains("hippopotamus"));
	}
	
	
	//containsAll
	@Test
	public void testContainsAllBasic(){
		basicHash.addAll(wordList);
		assertTrue(basicHash.containsAll(wordList));
		assertFalse(basicHash.containsAll(collisionList));
	}
	@Test
	public void testContainsAllCollisions(){
		basicHash.addAll(collisionList);
		assertTrue(basicHash.containsAll(wordList));
		assertTrue(basicHash.containsAll(collisionList));
	}
	@Test
	public void testContainsAllRehashList(){
		basicHash.addAll(rehashingList);
		assertTrue(basicHash.containsAll(rehashingList));
	}
	
	//isEmpty
	@Test
	public void testIsEmptyBaisc(){
		assertTrue(basicHash.isEmpty());
		basicHash.add("item");
		assertFalse(basicHash.isEmpty());
	}
	@Test
	public void testIsEmptyAfterClear(){
		basicHash.addAll(wordList);
		assertFalse(basicHash.isEmpty());
		basicHash.clear();
		assertTrue(basicHash.isEmpty());
	}
	//size
	@Test
	public void testSizeBasic(){
		assertTrue(basicHash.size() == 0);
		basicHash.addAll(wordList);
		assertTrue(basicHash.size() == 3);
	}
	@Test
	public void testSizeWithCollisions(){
		assertTrue(basicHash.size() == 0);
		basicHash.addAll(collisionList);
		assertTrue(basicHash.size() == 6);
	}

	
}
