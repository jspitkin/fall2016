package assignment10;

import static org.junit.Assert.*;

import java.util.ArrayList;

import assignment10.ChainingHashTable;
import assignment10.ReallyBadHashFunctor;
import assignment10.MediocreHashFunctor;
import assignment10.GoodHashFunctor;
import org.junit.Before;
import org.junit.Test;

public class ChainingHashTableTest {

	ChainingHashTable chainingHashTableBad;
	ChainingHashTable chainingHashTableMediocre;
	ChainingHashTable chainingHashTableGood;
	HashFunctor ReallyBadHashFunctor;
	HashFunctor MediocreHashFunctor;
	HashFunctor GoodHashFunctor;
	private int capacity=1;
	ArrayList<String> list;

	@Before
	public void setUp() throws Exception {
		ReallyBadHashFunctor = new ReallyBadHashFunctor();
		MediocreHashFunctor = new MediocreHashFunctor();
		GoodHashFunctor = new GoodHashFunctor();
		chainingHashTableBad = new ChainingHashTable(capacity, ReallyBadHashFunctor);
		chainingHashTableMediocre = new ChainingHashTable(capacity, MediocreHashFunctor);
		chainingHashTableGood = new ChainingHashTable(capacity, GoodHashFunctor);
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

		chainingHashTableBad.addAll(list);
		System.out.println(chainingHashTableBad.collisions);
		assertEquals(19, chainingHashTableBad.itemsInArray);
	}

	@Test
	public void testAddAllGood() {
		chainingHashTableGood.addAll(list);
		System.out.println(chainingHashTableGood.collisions);
		assertEquals(19, chainingHashTableGood.itemsInArray);
	}

	@Test
	public void testAddAllMed() {
		chainingHashTableMediocre.addAll(list);
		System.out.println(chainingHashTableMediocre.collisions);
		assertEquals(19, chainingHashTableMediocre.itemsInArray);
	}

	@Test
	public void testAddBad() {
		for (String f : list) {
			chainingHashTableBad.add(f);
		}
		assertEquals(19, chainingHashTableBad.itemsInArray);
	}

	@Test
	public void testAddGood() {
		for (String f : list) {
			chainingHashTableGood.add(f);
		}
		assertEquals(19, chainingHashTableGood.itemsInArray);
	}

	@Test
	public void testAddMediocre() {
		for (String f : list) {
			chainingHashTableMediocre.add(f);
		}
		assertEquals(19, chainingHashTableMediocre.itemsInArray);
	}

	@Test
	public void testClearBad() {
		chainingHashTableBad.addAll(list);
		chainingHashTableBad.clear();
		assertEquals(0, chainingHashTableBad.itemsInArray);
	}

	@Test
	public void testClearGood() {
		chainingHashTableGood.addAll(list);
		chainingHashTableGood.clear();
		assertEquals(0, chainingHashTableGood.itemsInArray);
	}

	@Test
	public void testClearMed() {
		chainingHashTableMediocre.addAll(list);
		chainingHashTableMediocre.clear();
		assertEquals(0, chainingHashTableMediocre.itemsInArray);
	}

	@Test
	public void testContainsAllBad() {
		chainingHashTableBad.addAll(list);
		assertEquals(true, chainingHashTableBad.containsAll(list));
	}

	@Test
	public void testContainsAllGood() {
		ArrayList<String> listGood = new ArrayList<>();
		listGood.add("Moses");
		// This is false
		listGood.add("Ruffio");
		listGood.add("Andre");
		listGood.add("Flip Murray");
		assertEquals(false, chainingHashTableGood.containsAll(listGood));
	}

	@Test
	public void testContainsAllMed() {
		ArrayList<String> listMed = new ArrayList<>();
		listMed.add("Moses");
		listMed.add("Greg");
		listMed.add("Andre");
		listMed.add("Flip Murray");
		chainingHashTableMediocre.addAll(list);
		assertEquals(true, chainingHashTableMediocre.containsAll(list));
	}

	@Test
	public void testContainsBad() {
		chainingHashTableBad.addAll(list);
		chainingHashTableBad.clear();
		assertEquals(false, chainingHashTableBad.contains("Moses"));
	}

	@Test
	public void testContainsGood() {
		chainingHashTableGood.addAll(list);
		assertEquals(true, chainingHashTableGood.contains("Andre"));
	}

	@Test
	public void testContainsMed() {
		chainingHashTableMediocre.addAll(list);
		assertEquals(true, chainingHashTableMediocre.contains("Bob"));
	}

	@Test
	public void testIsEmptyBad() {
		chainingHashTableBad.addAll(list);
		assertEquals(false, chainingHashTableBad.isEmpty());
	}

	@Test
	public void testIsEmptyGood() {
		ArrayList<String> listMed = new ArrayList<>();
		chainingHashTableGood.addAll(listMed);
		assertEquals(true, chainingHashTableGood.isEmpty());
	}

	@Test
	public void testIsEmptyMed() {
		chainingHashTableMediocre.addAll(list);
		chainingHashTableMediocre.clear();
		assertEquals(true, chainingHashTableMediocre.isEmpty());
	}

	@Test
	public void testSizeBad() {
		for (String f : list) {
			chainingHashTableBad.add(f);
		}
		assertEquals(41, chainingHashTableBad.size);
	}

	@Test
	public void testSizeGood() {
		for (String f : list) {
			chainingHashTableMediocre.add(f);
		}
		chainingHashTableGood.clear();
		assertEquals(0, chainingHashTableGood.itemsInArray);
	}

	@Test
	public void testSizeMed() {
		for (String f : list) {
			chainingHashTableMediocre.add(f);
		}
		assertEquals(19, chainingHashTableMediocre.itemsInArray);
	}

}
