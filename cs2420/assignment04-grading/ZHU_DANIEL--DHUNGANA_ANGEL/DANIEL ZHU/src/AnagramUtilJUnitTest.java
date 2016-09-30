package assignment04;

import static org.junit.Assert.*;

import org.junit.Test;

public class AnagramUtilJUnitTest {
	AnagramUtil util = new AnagramUtil();
	@Test
	public void sortTest()
	{
		String name = "baaef";
		String name2 = "aaebf";
		String sortName=util.sort(name);
		String sortName2= util.sort(name2);
		assertEquals(sortName,sortName2);
	}
	@Test
	public void areAnagramsTest()
	{
		String word1 = "act";
		String word2 = "cat";
		assertTrue(util.areAnagrams(word1, word2));
	}
	@Test
	public void areAnagramsTest2()
	{
		String word1 = "AcT";
		String word2 = "Cat";
		assertTrue(util.areAnagrams(word1, word2));
	}
	@Test
	public void areAnagramsTest3()
	{
		String word1 = "act";
		String word2 = "CAT";
		assertTrue(util.areAnagrams(word1, word2));
	}
	@Test
	public void sortTest2()
	{
		String name = "baaef";
		String name2 = "AAebf";
		String sortName=util.sort(name);
		String sortName2= util.sort(name2);
		assertEquals(sortName,sortName2);
	}
	@Test
	public void largestAnagramTest()
	{
		String [] array = new String [0];
		assertTrue(array.length==0);
	}
	@Test
	public void largestAnagramTest2()
	{
		String[] array = new String []{"meme","emem","mama","amam","vat", "aMaM"};
		assertTrue(array.length==6);
		String [] array2 = new String []{"mama", "amam", "aMaM"};
		assertTrue(array2.length==3);
		
		
	}
	
	
	 
	
	
}
