package assignment10;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ChainingHashTesting 
{
	ChainingHashTable myBadChainTable;
	ChainingHashTable myMediocreChainTable;
	ChainingHashTable myGoodChainTable;
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
		myBadChainTable = new ChainingHashTable(8, badHash);
		myMediocreChainTable = new ChainingHashTable(8, mediocreHash);
		myGoodChainTable = new ChainingHashTable(8, goodHash);
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
	public void addAllTest() 
	{
		myBadChainTable.clear();

		myBadChainTable.addAll(myStringList);
		assertTrue(myBadChainTable.containsAll(myStringList));

		assertEquals(myBadChainTable.size(), 13);

		myBadChainTable.clear();

		assertEquals(myBadChainTable.size(), 0);		
	}

}
