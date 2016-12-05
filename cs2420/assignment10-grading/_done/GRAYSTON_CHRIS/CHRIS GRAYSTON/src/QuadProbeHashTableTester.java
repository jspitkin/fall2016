package assignment10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

import org.junit.Test;

import junit.framework.TestCase;

/**
 * This class tests QuadHashTable which stores Strings
 * into an Array of ArrayList objects and allows the user to
 * pass in a way to resolve collisions.
 * 
 * @author Chris Grayston u0906710
 *
 */
public class QuadProbeHashTableTester extends TestCase {

	private static final int ITERATION_COUNT = 10;
	private QuadProbeHashTable qph1;
	private ArrayList<String> firstTest, secondTest, thirdTest;


	protected void setUp() throws Exception 
	{
		super.setUp();

		qph1 = new QuadProbeHashTable(11, new GoodHashFunctor());
		firstTest = new ArrayList<String>();
		secondTest = new ArrayList<String>();
		firstTest.add("one");
		firstTest.add("two");
		firstTest.add("three");
		firstTest.add("four");
		firstTest.add("two");	// test1 contains duplicates
	}

	protected void tearDown() throws Exception 
	{
		super.tearDown();
	}


	// test the add method
	public void testAdd1() 
	{	
		assertEquals(true, qph1.add("GoodBye"));
	}

	// test the add method: duplicate element
	public void testAdd2() 
	{	
		qph1.add("GoodBye");
		assertEquals(false, qph1.add("GoodBye"));
	}

	// test the add method: try to add null
	public void testAdd3() 
	{	
		try {
			assertFalse(qph1.add(null));

		}
		catch (Exception e) {
			assertTrue(e instanceof NullPointerException);
		}
	}

	// test the contains method: element in the set
	public void testContains1() 
	{
		qph1.add("GoodBye");
		qph1.add("World");
		qph1.add("Monkey");
		qph1.add("Orange");
		assertEquals(true, qph1.contains("Orange"));
	}

	// test the contains method: element not in the set
	public void testContains2() 
	{
		qph1.add("GoodBye");
		qph1.add("World");
		qph1.add("Monkey");
		qph1.add("Orange");
		assertEquals(false, qph1.contains("Grape"));
	}

	// contains null?
	public void testContains3() 
	{
		qph1.add("GoodBye");
		qph1.add("World");
		qph1.add("Monkey");
		qph1.add("Orange");
		try {
			qph1.contains(null);
			fail();
		}
		catch (Exception e) {
			assertTrue(e instanceof NullPointerException);
		}
	}


	// test the size method: nonempty set
	public void testSize1() 
	{
		qph1.add("GoodBye");
		qph1.add("World");
		qph1.add("Monkey");
		qph1.add("Orange");
		assertEquals(4, qph1.size());
	}

	// test the size method: empty set
	public void testSize2() 
	{
		assertEquals(0, qph1.size());
	}

	// test the empty method: empty set
	public void testIsEmpty1() 
	{
		assertEquals(true, qph1.isEmpty());
	}

	// test the empty method: nonempty set
	public void testIsEmpty2() 
	{
		qph1.add("Orange");
		assertEquals(false, qph1.isEmpty());
	}

	// test the addAll method: add a nonempty collection to an empty set
	public void testAddAll1()
	{
		assertEquals(true, qph1.addAll(firstTest));
		assertEquals(true, qph1.contains("one"));
		assertEquals(true, qph1.contains("two"));
		assertEquals(true, qph1.contains("three"));
		assertEquals(true, qph1.contains("four"));
	}

	// test the addAll method: add an empty collection
	public void testAddAll2()
	{
		assertEquals(false, qph1.addAll(secondTest));		
	}

	// try to add null
	public void testAddAll3()
	{
		try {
			qph1.addAll(thirdTest);
			fail();
		}
		catch (Exception e) {
			assertTrue(e instanceof NullPointerException);
		}
	}

	// test the addAll method: add a nonempty collection to a set which contains all those elements
	public void testAddAll4()
	{
		qph1.add("one");
		qph1.add("two");
		qph1.add("three");
		qph1.add("four");
		assertEquals(false, qph1.addAll(firstTest));		
	}

	// use size method to test the addAll method
	public void testAddAll5()
	{
		qph1.addAll(firstTest);
		assertEquals(4, qph1.size() );		
	}


	// test the containsAll method: not containing all elements
	public void testContainsAll1()
	{
		qph1.add("four");
		qph1.add("five");
		qph1.add("six");
		//assertFalse(set1.containsAll(test1));		
	}

	// test the containsAll method: containing all elements
	public void testContainsAll2()
	{
		qph1.addAll(firstTest);
		assertEquals(true, qph1.containsAll(firstTest));		
	}

	// use the size method to test the clear method
	public void testClear1()
	{
		qph1.addAll(firstTest);
		qph1.clear();
		assertEquals(0, qph1.size());
		for (String str : firstTest)
			assertFalse(qph1.contains(str));
	}
	
	/*
	 * Timing Code
	 */
	@Test
	public static void main(String[] args) {

//		QuadProbeHashAddCollisionEfficiencyTimingCode(new BadHashFunctor());
//		QuadProbeHashAddCollisionEfficiencyTimingCode(new MediocreHashFunctor());
//		QuadProbeHashAddCollisionEfficiencyTimingCode(new GoodHashFunctor());
		
//		collisionOfTheQuadProbeHashTable(new GoodHashFunctor());
	}
	

	
	@Test
	private static void QuadProbeHashAddCollisionEfficiencyTimingCode(HashFunctor functor) {
		// for loop for each problem size 1000 - 10000 by 1000 n steps
		for (int sampleSize = 1000; sampleSize <= 10000; sampleSize += 1000) {
			int total = 0;

			for (int i=0;i<(ITERATION_COUNT);i++) {
				
				// adds a random collection to the hash set
				QuadProbeHashTable set = new QuadProbeHashTable(sampleSize, functor);
				set.setCountCollision(true);
				Collection<String> c = generateRandomCollection(sampleSize, ITERATION_COUNT); 
				for (String str : c) {
					set.add(str);
					total += set.getCollisions();	// updates number of collisions
				}
			}
			// Compute average number of collisions for the add method
			double averageCollision = ( (double) total / ITERATION_COUNT ) / sampleSize;
			System.out.println(sampleSize + "\t" + averageCollision);	
		}
		
		System.out.println("\n\n\n");	

	}
	
	/**
	 * Counts the number of collisions of contains method for 
	 * QuadProbeHashTable with a given hash function.
	 */
	private static void collisionOfTheQuadProbeHashTable(HashFunctor hash) {
		System.out.println("\n" + "QB Table " + hash.getClass() + "\n");
		// Experiment timesToLoop times and use the average number of collisions
		int timesToLoop = 10;

		// For each problem size n . . .
		for (int n = 1000; n <= 10000; n += 1000) {
			int total = 0;

			for (int i=0;i<(timesToLoop);i++) {
				// adds a random collection to the hash set
				Collection<String> c = generateRandomCollection(n, timesToLoop);
				QuadProbeHashTable set = new QuadProbeHashTable(89, hash);
				set.setCountCollision(true);
				set.addAll(c);
				// calls contains method for each item in the collection
				for(String k : c) {
					set.contains(k);
					total += set.getCollisions();
				}
			}
			// Compute average number of collisions for the contains method
			double averageCollision = ( (double) total / timesToLoop ) / n;
			System.out.println(n + "\t" + averageCollision);	
		}
	}
	
	/**
	 * Generates a collection of random strings.
	 * @param size - the size of the generated collection, i.e. number of strings
	 * @param seed - the seed that controls the randomness of the collection
	 */
	public static Collection<String> generateRandomCollection(int size, int seed) {

		String[] arr = new String[size];
		Random rng = new Random(seed);
		
		for (int i=0; i<arr.length; i++) {
			int strLength = rng.nextInt(50);
			char[] c = new char[strLength]; 
			for(int j = 0; j < strLength; j++){
				c[j] = (char) ('a'+rng.nextInt(26) );  	
			}
			arr[i] = c.toString();
		}
		return Arrays.asList(arr);
	}
}
