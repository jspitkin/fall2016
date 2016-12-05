package assignment10;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Christian Hansen, U0621884
 * 
 * This class contains the unit tests for the ChainingHashTable. The tests
 * are grouped by method.
 *
 */
public class ChainingHashTableTester {

	ChainingHashTable t;
	ArrayList<String> listStrings;

	@Before
	public void setUp() throws Exception {
		t = new ChainingHashTable(13, new GoodHashFunctor());
		listStrings = new ArrayList<String>();
	}
	
	// ----- Add Tests ------
	
	@Test
	public void addTestSimple(){
		Assert.assertTrue(t.add("test"));
	}
	
	@Test
	public void addTestSameWordTwice(){
		t.add("test");
		Assert.assertFalse(t.add("test"));
	}
	
	@Test
	public void addTestDoesTheTableSuccessfullyExpand(){
		for(int index = 0; index < 30; index++){
			Assert.assertTrue(t.add("" + index));
		}
	}
	
	@Test
	public void addTestCantAddNull(){
		boolean thrown = false;
		
		try{
			t.add(null);
		}catch(NullPointerException e){
			thrown = true;
		}
		
		Assert.assertTrue(thrown);
	}
	
	// ----- AddAll Tests ------

	@Test
	public void addAllTestSimple(){
		listStrings.add("first");
		listStrings.add("last");
		
		Assert.assertTrue(t.addAll(listStrings));
	}
	
	@Test
	public void addAllTestSomeItemsAlreadyPresent(){
		listStrings.add("first");
		listStrings.add("last");
		listStrings.add("anotherOne");
		
		t.add("first");
		
		Assert.assertTrue(t.addAll(listStrings));
	}
	
	@Test
	public void addAllTestUnchangedFromAdding(){
		listStrings.add("first");
		listStrings.add("last");
		listStrings.add("anotherOne");
		
		t.add("first");
		t.add("last");
		t.add("anotherOne");
		
		Assert.assertFalse(t.addAll(listStrings));
	}
	
	// ----- clear Tests ------
	
	@Test
	public void clearTest(){
		t.add("first");
		t.add("last");
		t.add("anotherOne");
		
		t.clear();
		
		Assert.assertEquals( 0, t.size());
	}
	
	// ----- size Tests --------
	
	@Test
	public void sizeTestSizeIsOne(){
		t.add("first");
		Assert.assertEquals(1, t.size());
	}
	
	@Test
	public void sizeTestSizeIsFive(){
		
		t.add("first");
		t.add("Next");
		t.add("Last");
		t.add("Horray");
		t.add("Working?");
		
		Assert.assertEquals(5, t.size());
	}
	
	// ----- contains Tests --------
	
	@Test
	public void containsTestItemFound(){
		t.add("first");
		t.add("Next");
		t.add("Last");
		t.add("Horray");
		t.add("Working?");
		
		Assert.assertTrue(t.contains("first"));
	}
	
	@Test
	public void containsTestExceptionThrown(){
		boolean thrown = false;
		
		try{
			t.contains(null);
		}catch(NullPointerException e){
			thrown = true;
		}
		
		Assert.assertTrue(thrown);
	}
	
	// ----- containsAll Tests --------
	
	@Test
	public void containsAllTestEmptyList(){
		Assert.assertTrue(t.containsAll(new ArrayList<String>()));
		
	}
	
	@Test
	public void containsAllTestAllItemsPresent(){
		listStrings.add("first");
		listStrings.add("last");
		listStrings.add("anotherOne");
		
		t.add("first");
		t.add("last");
		t.add("anotherOne");
		
		Assert.assertTrue(t.containsAll(listStrings));
	}
	
	@Test
	public void containsAllTestNotAllItemsPresent(){
		listStrings.add("first");
		listStrings.add("last");
		listStrings.add("anotherOne");
		
		t.add("first");
		t.add("anotherOne");
		
		Assert.assertFalse(t.containsAll(listStrings));
	}
	
	// ----- isEmpty Tests --------
	
	@Test
	public void isEmptyTestNothingEverAdded(){
		Assert.assertTrue(t.isEmpty());
	}
	
	@Test
	public void isEmptyTestFalseItemsPresent(){
		t.add("items");
		
		Assert.assertFalse(t.isEmpty());
	}
}
