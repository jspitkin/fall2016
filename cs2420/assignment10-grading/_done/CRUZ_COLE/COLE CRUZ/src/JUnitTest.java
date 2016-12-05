//Cole Cruz
package assignment10;

import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test class for hash tables.
 * 
 * @author Cole Cruz
 *
 */
public class JUnitTest {

	private QuadProbeHashTable BQPHash, MQPHash, GQPHash;
	private ChainingHashTable BCHash, MCHash, GCHash;
	private BadHashFunctor BHFunctor = new BadHashFunctor();
	private MediocreHashFunctor MHFunctor = new MediocreHashFunctor();
	private GoodHashFunctor GHFunctor = new GoodHashFunctor();

	/**
	 * Set up before each test.
	 */
	@Before
	public void setUp() {
		BQPHash = new QuadProbeHashTable(2, BHFunctor);
		MQPHash = new QuadProbeHashTable(10, MHFunctor);
		GQPHash = new QuadProbeHashTable(11, GHFunctor);
		BCHash = new ChainingHashTable(3, BHFunctor);
		MCHash = new ChainingHashTable(12, MHFunctor);
		GCHash = new ChainingHashTable(13, GHFunctor);
	}

	/**
	 * A variety of tests for multiple classes.
	 */
	@Test
	public void addTrue() {
		Assert.assertTrue(BQPHash.add("hello"));
		Assert.assertTrue(MQPHash.add("hello"));
		Assert.assertTrue(GQPHash.add("hello"));
		Assert.assertTrue(BCHash.add("hello"));
		Assert.assertTrue(MCHash.add("hello"));
		Assert.assertTrue(GCHash.add("hello"));
	}

	@Test
	public void addFalse() {
		Assert.assertTrue(BQPHash.add("hello"));
		Assert.assertFalse(BQPHash.add("hello"));
		Assert.assertTrue(MQPHash.add("hello"));
		Assert.assertFalse(MQPHash.add("hello"));
		Assert.assertTrue(GQPHash.add("hello"));
		Assert.assertFalse(GQPHash.add("hello"));
		Assert.assertTrue(BCHash.add("hello"));
		Assert.assertFalse(BCHash.add("hello"));
		Assert.assertTrue(MCHash.add("hello"));
		Assert.assertFalse(MCHash.add("hello"));
		Assert.assertTrue(GCHash.add("hello"));
		Assert.assertFalse(GCHash.add("hello"));
	}

	@Test
	public void addAllTrue() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("hello");
		list.add("hey");
		Assert.assertTrue(BQPHash.add("hello"));
		Assert.assertTrue(BQPHash.addAll(list));
		Assert.assertTrue(MQPHash.add("hello"));
		Assert.assertTrue(MQPHash.addAll(list));
		Assert.assertTrue(GQPHash.add("hello"));
		Assert.assertTrue(GQPHash.addAll(list));
		Assert.assertTrue(BCHash.add("hello"));
		Assert.assertTrue(BCHash.addAll(list));
		Assert.assertTrue(MCHash.add("hello"));
		Assert.assertTrue(MCHash.addAll(list));
		Assert.assertTrue(GCHash.add("hello"));
		Assert.assertTrue(GCHash.addAll(list));
	}

	@Test
	public void addAllFalse() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("hello");
		list.add("hello");
		Assert.assertTrue(BQPHash.add("hello"));
		Assert.assertFalse(BQPHash.addAll(list));
		Assert.assertTrue(MQPHash.add("hello"));
		Assert.assertFalse(MQPHash.addAll(list));
		Assert.assertTrue(GQPHash.add("hello"));
		Assert.assertFalse(GQPHash.addAll(list));
		Assert.assertTrue(BCHash.add("hello"));
		Assert.assertFalse(BCHash.addAll(list));
		Assert.assertTrue(MCHash.add("hello"));
		Assert.assertFalse(MCHash.addAll(list));
		Assert.assertTrue(GCHash.add("hello"));
		Assert.assertFalse(GCHash.addAll(list));
	}

	@Test
	public void clear() {
		BQPHash.clear();
		MQPHash.clear();
		GQPHash.clear();
		BCHash.clear();
		MCHash.clear();
		GCHash.clear();
	}

	@Test
	public void containsTrue() {
		Assert.assertTrue(BQPHash.add("hello"));
		Assert.assertTrue(BQPHash.contains("hello"));
		Assert.assertTrue(MQPHash.add("hello"));
		Assert.assertTrue(MQPHash.contains("hello"));
		Assert.assertTrue(GQPHash.add("hello"));
		Assert.assertTrue(GQPHash.contains("hello"));
		Assert.assertTrue(BCHash.add("hello"));
		Assert.assertTrue(BCHash.contains("hello"));
		Assert.assertTrue(MCHash.add("hello"));
		Assert.assertTrue(MCHash.contains("hello"));
		Assert.assertTrue(GCHash.add("hello"));
		Assert.assertTrue(GCHash.contains("hello"));
	}

	@Test
	public void containsFalse() {
		Assert.assertTrue(BQPHash.add("hello"));
		Assert.assertFalse(BQPHash.contains("hey"));
		Assert.assertTrue(MQPHash.add("hello"));
		Assert.assertFalse(MQPHash.contains("hey"));
		Assert.assertTrue(GQPHash.add("hello"));
		Assert.assertFalse(GQPHash.contains("hey"));
		Assert.assertTrue(BCHash.add("hello"));
		Assert.assertFalse(BCHash.contains("hey"));
		Assert.assertTrue(MCHash.add("hello"));
		Assert.assertFalse(MCHash.contains("hey"));
		Assert.assertTrue(GCHash.add("hello"));
		Assert.assertFalse(GCHash.contains("hey"));
	}

	@Test
	public void containsAllTrue() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("hello");
		list.add("hello");
		Assert.assertTrue(BQPHash.add("hello"));
		Assert.assertTrue(BQPHash.containsAll(list));
		Assert.assertTrue(MQPHash.add("hello"));
		Assert.assertTrue(MQPHash.containsAll(list));
		Assert.assertTrue(GQPHash.add("hello"));
		Assert.assertTrue(GQPHash.containsAll(list));
		Assert.assertTrue(BCHash.add("hello"));
		Assert.assertTrue(BCHash.containsAll(list));
		Assert.assertTrue(MCHash.add("hello"));
		Assert.assertTrue(MCHash.containsAll(list));
		Assert.assertTrue(GCHash.add("hello"));
		Assert.assertTrue(GCHash.containsAll(list));
	}

	@Test
	public void containsAllFalse() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("hello");
		list.add("hey");
		Assert.assertTrue(BQPHash.add("hello"));
		Assert.assertFalse(BQPHash.containsAll(list));
		Assert.assertTrue(MQPHash.add("hello"));
		Assert.assertFalse(MQPHash.containsAll(list));
		Assert.assertTrue(GQPHash.add("hello"));
		Assert.assertFalse(GQPHash.containsAll(list));
		Assert.assertTrue(BCHash.add("hello"));
		Assert.assertFalse(BCHash.containsAll(list));
		Assert.assertTrue(MCHash.add("hello"));
		Assert.assertFalse(MCHash.containsAll(list));
		Assert.assertTrue(GCHash.add("hello"));
		Assert.assertFalse(GCHash.containsAll(list));
	}

	@Test
	public void isEmptyTrue() {
		Assert.assertTrue(BQPHash.isEmpty());
		Assert.assertTrue(MQPHash.isEmpty());
		Assert.assertTrue(GQPHash.isEmpty());
		Assert.assertTrue(BCHash.isEmpty());
		Assert.assertTrue(MCHash.isEmpty());
		Assert.assertTrue(GCHash.isEmpty());
		Assert.assertTrue(BQPHash.add("hello"));
		Assert.assertTrue(MQPHash.add("hello"));
		Assert.assertTrue(GQPHash.add("hello"));
		Assert.assertTrue(BCHash.add("hello"));
		Assert.assertTrue(MCHash.add("hello"));
		Assert.assertTrue(GCHash.add("hello"));
		BQPHash.clear();
		MQPHash.clear();
		GQPHash.clear();
		BCHash.clear();
		MCHash.clear();
		GCHash.clear();
		Assert.assertTrue(BQPHash.isEmpty());
		Assert.assertTrue(MQPHash.isEmpty());
		Assert.assertTrue(GQPHash.isEmpty());
		Assert.assertTrue(BCHash.isEmpty());
		Assert.assertTrue(MCHash.isEmpty());
		Assert.assertTrue(GCHash.isEmpty());
	}

	@Test
	public void isEmptyFalse() {
		Assert.assertTrue(BQPHash.add("hello"));
		Assert.assertTrue(MQPHash.add("hello"));
		Assert.assertTrue(GQPHash.add("hello"));
		Assert.assertTrue(BCHash.add("hello"));
		Assert.assertTrue(MCHash.add("hello"));
		Assert.assertTrue(GCHash.add("hello"));
		Assert.assertFalse(BQPHash.isEmpty());
		Assert.assertFalse(MQPHash.isEmpty());
		Assert.assertFalse(GQPHash.isEmpty());
		Assert.assertFalse(BCHash.isEmpty());
		Assert.assertFalse(MCHash.isEmpty());
		Assert.assertFalse(GCHash.isEmpty());
	}

	@Test
	public void size() {
		Assert.assertEquals(0, BQPHash.size());
		Assert.assertEquals(0, MQPHash.size());
		Assert.assertEquals(0, GQPHash.size());
		Assert.assertEquals(0, BCHash.size());
		Assert.assertEquals(0, MCHash.size());
		Assert.assertEquals(0, GCHash.size());
		Assert.assertTrue(BQPHash.add("hello"));
		Assert.assertTrue(MQPHash.add("hello"));
		Assert.assertTrue(GQPHash.add("hello"));
		Assert.assertTrue(BCHash.add("hello"));
		Assert.assertTrue(MCHash.add("hello"));
		Assert.assertTrue(GCHash.add("hello"));
		Assert.assertTrue(BQPHash.add("hey"));
		Assert.assertTrue(MQPHash.add("hey"));
		Assert.assertTrue(GQPHash.add("hey"));
		Assert.assertTrue(BCHash.add("hey"));
		Assert.assertTrue(MCHash.add("hey"));
		Assert.assertTrue(GCHash.add("hey"));
		Assert.assertEquals(2, BQPHash.size());
		Assert.assertEquals(2, MQPHash.size());
		Assert.assertEquals(2, GQPHash.size());
		Assert.assertEquals(2, BCHash.size());
		Assert.assertEquals(2, MCHash.size());
		Assert.assertEquals(2, GCHash.size());
	}

	@Test
	public void prime() {
		Assert.assertEquals(2, BQPHash.prime(2));
		Assert.assertEquals(3, MQPHash.prime(3));
		Assert.assertEquals(23, GQPHash.prime(21));
		Assert.assertEquals(67, BCHash.prime(63));
		Assert.assertEquals(181, MCHash.prime(180));
		Assert.assertEquals(199, GCHash.prime(199));
	}

	@Test
	public void isPrimeTrue() {
		Assert.assertTrue(BQPHash.isPrime(2));
		Assert.assertTrue(MQPHash.isPrime(3));
		Assert.assertTrue(GQPHash.isPrime(167));
		Assert.assertTrue(BCHash.isPrime(61));
		Assert.assertTrue(MCHash.isPrime(997));
		Assert.assertTrue(GCHash.isPrime(89));
	}

	@Test
	public void isPrimeFalse() {
		Assert.assertFalse(BQPHash.isPrime(4));
		Assert.assertFalse(MQPHash.isPrime(9));
		Assert.assertFalse(GQPHash.isPrime(21));
		Assert.assertFalse(BCHash.isPrime(69));
		Assert.assertFalse(MCHash.isPrime(420));
		Assert.assertFalse(GCHash.isPrime(999));
	}

	@Test
	public void rehashNeededTrue() {
		Assert.assertTrue(BQPHash.add("hello"));
		Assert.assertFalse(BQPHash.rehashNeeded());
	}

	@Test
	public void rehashNeededFalse() {
		Assert.assertTrue(MQPHash.add("hello"));
		Assert.assertFalse(MQPHash.rehashNeeded());
		Assert.assertTrue(GQPHash.add("hello"));
		Assert.assertFalse(GQPHash.rehashNeeded());
		Assert.assertTrue(BCHash.add("hello"));
		Assert.assertFalse(BCHash.rehashNeeded());
		Assert.assertTrue(MCHash.add("hello"));
		Assert.assertFalse(MCHash.rehashNeeded());
		Assert.assertTrue(GCHash.add("hello"));
		Assert.assertFalse(GCHash.rehashNeeded());
	}

	@Test
	public void rehash() {
		BQPHash.rehash();
		MQPHash.rehash();
		GQPHash.rehash();
		BCHash.rehash();
		MCHash.rehash();
		GCHash.rehash();
	}

	@Test
	public void hash() {
		Assert.assertEquals(5, BHFunctor.hash("hello"));
		Assert.assertEquals(215, MHFunctor.hash("hello"));
		Assert.assertEquals(532, GHFunctor.hash("hello"));
	}

	@Test
	public void qpHashTest() {
		GQPHash.add("");
		GQPHash.add(" ");
		GQPHash.add("hello");
		GQPHash.add("hey");
		GQPHash.add("hi");
		GQPHash.add("h");
		GQPHash.add("yeh");
		GQPHash.add("?");
		GQPHash.add("hi");
		GQPHash.add("helol");
		GQPHash.add("haha");
		GQPHash.add("lolhe");
		GQPHash.add(" ");
		GQPHash.add("");
		GQPHash.contains("lolhe");
	}

	@Test
	public void cHashTest() {
		GCHash.add("");
		GCHash.add(" ");
		GCHash.add("hello");
		GCHash.add("hey");
		GCHash.add("hi");
		GCHash.add("h");
		GCHash.add("yeh");
		GCHash.add("?");
		GCHash.add("hi");
		GCHash.add("helol");
		GCHash.add("haha");
		GCHash.add("lolhe");
		GCHash.add(" ");
		GCHash.add("");
		GCHash.contains("lolhe");
	}

}
