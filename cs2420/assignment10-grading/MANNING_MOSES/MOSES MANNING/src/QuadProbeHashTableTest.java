package assignment10;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class QuadProbeHashTableTest {
	QuadProbeHashTable quadProbeHashTableBad;
	QuadProbeHashTable quadProbeHashTableMed;
	QuadProbeHashTable quadProbeHashTableGood;
	HashFunctor ReallyBadHashFunctor;
	HashFunctor MediocreHashFunctor;
	HashFunctor GoodHashFunctor;
	private int capacity = 1;
	ArrayList<String> list;

	@Before
	public void setUp() throws Exception {
		ReallyBadHashFunctor = new ReallyBadHashFunctor();
		MediocreHashFunctor = new MediocreHashFunctor();
		GoodHashFunctor = new GoodHashFunctor();
		quadProbeHashTableBad = new QuadProbeHashTable(capacity, ReallyBadHashFunctor);
		quadProbeHashTableMed = new QuadProbeHashTable(capacity, MediocreHashFunctor);
		quadProbeHashTableGood = new QuadProbeHashTable(capacity, GoodHashFunctor);
		list = new ArrayList<String>();
		list.add("Moses");
		list.add("Bob");
		list.add("Greg");
		list.add("Andre");
		list.add("Flip Murray");
		list.add("Nick");
		list.add("dude");
		list.add("ob");
		list.add("reg");
		list.add("ndre");
		list.add("lip Murray");
		list.add("ick");
		list.add("ck");
		list.add("de");
		list.add("b");
		list.add("g");
		list.add("re");
		list.add("p Murray");
		list.add("k");
	}

	@Test
	public void testAddAllBad() {
		quadProbeHashTableBad.addAll(list);
		assertEquals(19, quadProbeHashTableBad.items);
	}

	@Test
	public void testAddAllGood() {
		quadProbeHashTableGood.addAll(list);
		assertEquals(19, quadProbeHashTableGood.items);

	}

	@Test
	public void testAddAllMed() {
		quadProbeHashTableMed.addAll(list);
		assertEquals(19, quadProbeHashTableMed.items);

	}

	@Test
	public void testAddBad() {
		for (String string : list) {
			quadProbeHashTableBad.add(string);
		}
		assertEquals(19, quadProbeHashTableBad.items);
	}

	@Test
	public void testAddGood() {
		for (String string : list) {
			quadProbeHashTableGood.add(string);
		}
		assertEquals(19, quadProbeHashTableGood.items);

	}

	@Test
	public void testAddMed() {
		for (String string : list) {
			quadProbeHashTableMed.add(string);
		}
		assertEquals(19, quadProbeHashTableMed.items);

	}

	@Test
	public void testClearBad() {
		quadProbeHashTableBad.addAll(list);
		quadProbeHashTableBad.clear();
		assertEquals(0, quadProbeHashTableBad.size());
	}

	@Test
	public void testClearGood() {
		quadProbeHashTableGood.addAll(list);
		quadProbeHashTableGood.clear();
		assertEquals(0, quadProbeHashTableGood.size());
	}

	@Test
	public void testClearMed() {
		quadProbeHashTableMed.addAll(list);
		quadProbeHashTableMed.clear();
		assertEquals(0, quadProbeHashTableMed.size());
	}

	@Test
	public void testContainsAllBad() {
		ArrayList<String> names = new ArrayList<>();
		names.add("Moses");
		names.add("Bob");
		names.add("Andre");
		quadProbeHashTableBad.addAll(list);
		assertEquals(true, quadProbeHashTableBad.containsAll(names));
	}

	@Test
	public void testContainsAllMed() {
		ArrayList<String> names = new ArrayList<>();
		names.add("Moses");
		names.add("Bob");
		names.add("Andre");
		quadProbeHashTableMed.addAll(list);
		assertEquals(true, quadProbeHashTableMed.containsAll(names));
	}

	@Test
	public void testContainsAllGood() {
		ArrayList<String> names = new ArrayList<>();
		names.add("Moses");
		names.add("Bob");
		// Billy is not in the list
		names.add("Billy");
		quadProbeHashTableGood.addAll(list);
		assertEquals(false, quadProbeHashTableGood.containsAll(names));
	}

	@Test
	public void testContainsBad() {
		quadProbeHashTableBad.addAll(list);
		quadProbeHashTableBad.clear();
		assertEquals(false, quadProbeHashTableBad.contains("Moses"));
	}

	@Test
	public void testContainsGood() {
		quadProbeHashTableGood.addAll(list);
		assertEquals(true, quadProbeHashTableGood.contains("Andre"));
	}

	@Test
	public void testContainsMed() {
		quadProbeHashTableMed.addAll(list);
		assertEquals(true, quadProbeHashTableMed.contains("Bob"));
	}

	@Test
	public void testIsEmptyBad() {
		quadProbeHashTableBad.addAll(list);
		quadProbeHashTableBad.clear();
		assertEquals(true, quadProbeHashTableBad.isEmpty());
	}

	@Test
	public void testIsEmptyGood() {
		quadProbeHashTableGood.addAll(list);
		assertEquals(false, quadProbeHashTableGood.isEmpty());
	}

	@Test
	public void testIsEmptyMed() {
		quadProbeHashTableMed.addAll(list);
		quadProbeHashTableMed.clear();
		assertEquals(true, quadProbeHashTableMed.isEmpty());
	}

	@Test
	public void testSizeBad() {
		quadProbeHashTableBad.addAll(list);
		assertEquals(19, quadProbeHashTableBad.size());
	}

	@Test
	public void testSizeGood() {
		quadProbeHashTableGood.addAll(list);
		quadProbeHashTableGood.clear();
		assertEquals(0, quadProbeHashTableGood.size());
	}

	@Test
	public void testSizeMed() {
		list.add("Ohio");
		quadProbeHashTableMed.addAll(list);
		assertEquals(20, quadProbeHashTableMed.size());
	}

}
