package assignment10;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class QuadProbeHashTesting 
{
	QuadProbeHashTable myBadQuadTable;
	QuadProbeHashTable myMediocreQuadTable;
	QuadProbeHashTable myGoodQuadTable;
	BadHashFunctor badHash;
	MediocreHashFunctor mediocreHash;
	GoodHashFunctor goodHash;
	List<String> myStringList;

	@Before
	public void setUp() throws Exception 
	{
		badHash = new BadHashFunctor();
		mediocreHash = new MediocreHashFunctor();
		goodHash = new GoodHashFunctor();
		myBadQuadTable = new QuadProbeHashTable(8, badHash);
		myMediocreQuadTable = new QuadProbeHashTable(8, mediocreHash);
		myGoodQuadTable = new QuadProbeHashTable(8, goodHash);
		myStringList = new ArrayList<String>();
		myStringList.add("Hello");
		myStringList.add("Hi");
		myStringList.add("Good");
		myStringList.add("No");
		myStringList.add("New");
		myStringList.add("Ape");
		myStringList.add("Apples");
		myStringList.add("My Name is");
		myStringList.add("What");
		myStringList.add("Bolander");
		myStringList.add("Ryan");
		myStringList.add("Oranges");
		myStringList.add("Bananas");
	}

	@Test
	public void addContainTestBadHash() 
	{
		myBadQuadTable.add("Hello");
		myBadQuadTable.add("Hi");
		myBadQuadTable.add("Good");
		myBadQuadTable.add("No");
		myBadQuadTable.add("New");
		myBadQuadTable.add("Ape");
		myBadQuadTable.add("Apples");
		myBadQuadTable.add("My Name is");
		myBadQuadTable.add("Ryan");
		myBadQuadTable.add("Bolander");
		myBadQuadTable.add("What");
		myBadQuadTable.add("Oranges");
		myBadQuadTable.add("Bananas");
		assertTrue(myBadQuadTable.contains("Hello"));
		assertTrue(myBadQuadTable.contains("Hi"));
		assertTrue(myBadQuadTable.contains("Good"));
		assertTrue(myBadQuadTable.contains("No"));
		assertTrue(myBadQuadTable.contains("New"));
		assertTrue(myBadQuadTable.contains("Ape"));
		assertTrue(myBadQuadTable.contains("Apples"));
		assertTrue(myBadQuadTable.contains("My Name is"));
		assertTrue(myBadQuadTable.contains("Ryan"));
		assertTrue(myBadQuadTable.contains("Bolander"));
		assertTrue(myBadQuadTable.contains("What"));
		assertTrue(myBadQuadTable.contains("Oranges"));
		
		assertEquals(myBadQuadTable.size(), 13);
		
		myBadQuadTable.clear();
		
		assertEquals(myBadQuadTable.size(), 0);
	}
	
	@Test
	public void addContainTestMediocreHash() 
	{
		myMediocreQuadTable.add("Hello");
		myMediocreQuadTable.add("Hi");
		myMediocreQuadTable.add("Good");
		myMediocreQuadTable.add("No");
		myMediocreQuadTable.add("New");
		myMediocreQuadTable.add("Ape");
		myMediocreQuadTable.add("Apples");
		myMediocreQuadTable.add("My Name is");
		myMediocreQuadTable.add("Ryan");
		myMediocreQuadTable.add("Bolander");
		myMediocreQuadTable.add("What");
		myMediocreQuadTable.add("Oranges");
		myMediocreQuadTable.add("Bananas");
		assertTrue(myMediocreQuadTable.contains("Hello"));
		assertTrue(myMediocreQuadTable.contains("Hi"));
		assertTrue(myMediocreQuadTable.contains("Good"));
		assertTrue(myMediocreQuadTable.contains("No"));
		assertTrue(myMediocreQuadTable.contains("New"));
		assertTrue(myMediocreQuadTable.contains("Ape"));
		assertTrue(myMediocreQuadTable.contains("Apples"));
		assertTrue(myMediocreQuadTable.contains("My Name is"));
		assertTrue(myMediocreQuadTable.contains("Ryan"));
		assertTrue(myMediocreQuadTable.contains("Bolander"));
		assertTrue(myMediocreQuadTable.contains("What"));
		assertTrue(myMediocreQuadTable.contains("Oranges"));
		
		assertEquals(myMediocreQuadTable.size(), 13);
		
		myMediocreQuadTable.clear();
		
		assertEquals(myMediocreQuadTable.size(), 0);
	}
	
	@Test
	public void isEmptyTest()
	{
		assertTrue(myBadQuadTable.isEmpty());
	}
	
	@Test
	public void addAllTest()
	{
		myBadQuadTable.clear();
		
		myBadQuadTable.addAll(myStringList);
		assertTrue(myBadQuadTable.containsAll(myStringList));
		
		assertEquals(myBadQuadTable.size(), 13);
		
		myBadQuadTable.clear();
		
		assertEquals(myBadQuadTable.size(), 0);		
	}
	
	@Test
	public void addAllGoodHashTest()
	{
		myGoodQuadTable.clear();
		
		myGoodQuadTable.addAll(myStringList);
		assertTrue(myGoodQuadTable.containsAll(myStringList));
		
		assertEquals(myGoodQuadTable.size(), 13);
		
		myGoodQuadTable.clear();
		
		assertEquals(myGoodQuadTable.size(), 0);		
	}

}
