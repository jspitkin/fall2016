package assignment10;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class HashTableTesting {

	public HashFunctor hasher;
	public QuadProbeHashTable quad;
	public ChainingHashTable chain;
	private static char[] charArray;
	private ArrayList<String> smallList;
	private ArrayList<String> mediumList;
	private ArrayList<String> bigList;
	private ArrayList<String> megaList;

	@BeforeClass
	public static void oneTimeSetUp(){
		charArray = new char[26];
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		for(int i = 0; i < 26; i++){
			charArray[i] = alphabet.charAt(i);
		}
	}
	
	@Before
	public void setUp(){
		hasher = new GoodHashFunctor();
		quad = new QuadProbeHashTable(7, hasher);
		chain = new ChainingHashTable(7, hasher);
		
		smallList = generateRandomStringArray(10, 15);
		mediumList = generateRandomStringArray(10, 100);
		bigList = generateRandomStringArray(10, 200);
		megaList = generateRandomStringArray(10, 5000);
	}
	
	@Test
	public void testQuadAdd() {
		assertTrue(quad.isEmpty());
		assertTrue(quad.add("test"));
		assertFalse(quad.add("test"));
		assertTrue(quad.size() == 1);
		assertTrue(quad.contains("test"));
		assertFalse(quad.contains("nothing"));
		assertFalse(quad.isEmpty());
	}
	
	@Test
	public void testQuadAddAll(){
		assertTrue(quad.addAll(smallList));
		assertTrue(quad.containsAll(smallList));
		assertTrue(quad.size() == 15);
		System.out.println(quad.toArray());
	}
	
	@Test
	public void testQuadAddAllMedium(){
		assertTrue(quad.addAll(mediumList));
		assertTrue(quad.containsAll(mediumList));
		assertTrue(quad.size() == 100);
		System.out.println(quad.toArray());
	}
	
	@Test
	public void testQuadAddAllBig(){
		assertTrue(quad.addAll(bigList));
		assertTrue(quad.containsAll(bigList));
		assertTrue(quad.size() == 200);
		System.out.println(quad.toArray());
	}
	
	@Test
	public void testQuadAddAllMega(){
		assertTrue(quad.addAll(megaList));
		assertTrue(quad.containsAll(megaList));
		assertTrue(quad.size() == 5000);
	}
	
	@Test
	public void testQuadEmptyString(){
		assertTrue(quad.add(""));
		assertTrue(quad.contains(""));
		assertTrue(quad.size() == 1);
	}
	
	@Test
	public void testChainAdd() {
		assertTrue(chain.isEmpty());
		assertTrue(chain.add("test"));
		assertFalse(chain.add("test"));
		assertTrue(chain.size() == 1);
		assertTrue(chain.contains("test"));
		assertFalse(chain.contains("nothing"));
		assertFalse(chain.isEmpty());
	}
	
	@Test
	public void testChainAddAll(){
		assertTrue(chain.addAll(smallList));
		assertTrue(chain.containsAll(smallList));
		assertTrue(chain.size() == 15);
	}
	
	@Test
	public void testChainAddAllMedium(){
		assertTrue(chain.addAll(mediumList));
		assertTrue(chain.containsAll(mediumList));
		assertTrue(chain.size() == 100);
	}
	
	@Test
	public void testChainAddAllBig(){
		assertTrue(chain.addAll(bigList));
		assertTrue(chain.containsAll(bigList));
		assertTrue(chain.size() == 200);
	}
	
	@Test
	public void testChainAddAllMega(){
		assertTrue(chain.addAll(megaList));
		assertTrue(chain.containsAll(megaList));
		assertTrue(chain.size() == 5000);
	}
	
	@Test
	public void testChainEmptyString(){
		assertTrue(chain.add(""));
		assertTrue(chain.contains(""));
		assertTrue(chain.size() == 1);
	}
	
	/**
	 * generates an arraylist of random strings
	 * @param maxStringLength the maximum length of the strings in the array (minimum length is 3)
	 * @param arraySize
	 * @return
	 */
	private ArrayList<String> generateRandomStringArray(int maxStringLength, int arraySize){
		ArrayList<String> retArray = new ArrayList<String>();
		
		for(int i = 1; i <= arraySize; i++){
			String string = generateRandomString(maxStringLength);
			//ensure that only unique strings are added to the list
			while(retArray.contains(string))
				string = generateRandomString(maxStringLength);
			retArray.add(string);
		}
		
		return retArray;
	}
	
	/**
	 * creates a random string between 3 and the given maxLength
	 * @param maxLength the maximum size the string can be
	 * @return a random string
	 */
	private String generateRandomString(int maxLength){
		//minimum string length is 3
		int length = (int) (Math.random()*(maxLength-3))+4;
		String newString = "";
		
		for(int i = 0; i < length; i++){
			newString += charArray[(int)(Math.random()*26)];
		}
		
		return newString;
	}

}
