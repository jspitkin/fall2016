package assignment04;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AnagramUtilJUnitTester<T> {
	String[] smStringArr = new String[5];
	String[] singleStringArr = new String[1];
	String[] allAnagramsArr = new String[5];
	String[] evenSizeList = new String[8];
	String[] noAnagramsArr = new String[7];

	@Before
	public void setUp() throws Exception {
		smStringArr[0] = "later";
		smStringArr[1] = "dunes";
		smStringArr[2] = "word";
		smStringArr[3] = "pat";
		smStringArr[4] = "ALERT";
		
		singleStringArr[0] = "a";
		
		allAnagramsArr[0] = "same";
		allAnagramsArr[1] = "same";
		allAnagramsArr[2] = "same";
		allAnagramsArr[3] = "same";
		allAnagramsArr[4] = "same";
		
		evenSizeList[0] = "death";
		evenSizeList[1] = "ideals";
		evenSizeList[2] = "downhill";
		evenSizeList[3] = "stable";
		evenSizeList[4] = "strain";
		evenSizeList[5] = "sailed";
		evenSizeList[6] = "uphill";
		evenSizeList[7] = "pain";
		
		
		noAnagramsArr[0] = "so";
		noAnagramsArr[1] = "heres";
		noAnagramsArr[2] = "my";
		noAnagramsArr[3] = "number";
		noAnagramsArr[4] = "call";
		noAnagramsArr[5] = "me";
		noAnagramsArr[6] = "maybe";
	}
	
	//sort tests
	@Test
	public void testSortEmptyString() {
		Assert.assertEquals("", AnagramUtil.sort(""));
	}
	
	@Test
	public void testSortSmallString() {
		Assert.assertEquals("achilnos", AnagramUtil.sort("NiCHOlaS"));
	}
	
	@Test
	public void testSortLargeString() {
		Assert.assertEquals("abcdefghijklmnopqrstuvwxyz", AnagramUtil.sort("zyxwvutsrqpomnlkjihgfedcba"));
	}
	
	@Test
	public void testSortSingleCharString() {
		Assert.assertEquals("a", AnagramUtil.sort("A"));
	}
	
	//areAnagrams tests
	@Test
	public void testAreAnagramsSingleChar() {
		Assert.assertTrue(AnagramUtil.areAnagrams("a", "A"));
	}
	
	@Test
	public void testAreAnagramsNoChars() {
		Assert.assertTrue(AnagramUtil.areAnagrams("", ""));
	}
	
	@Test
	public void testAreAnagramsSimple() {
		Assert.assertTrue(AnagramUtil.areAnagrams("alert", "later"));
	}
	
	@Test
	public void testAreAnagramsMixedCapitals() {
		Assert.assertTrue(AnagramUtil.areAnagrams("AlErT", "LaTeR"));
	}
	
	@Test
	public void testAreAnagramsSimpleFalse() {
		Assert.assertFalse(AnagramUtil.areAnagrams("Nicholas", "Andrew"));
	}
	
	//getLargestAnagramGroup tests from String array
	@Test
	public void testGetLargestAnagramGroupSmList() {
		Assert.assertArrayEquals("FAIL",new String[]{"later","ALERT"}, AnagramUtil.getLargestAnagramGroup(smStringArr));
	}
	
	@Test
	public void testGetLargestAnagramGroupSingleStringList() {
		Assert.assertArrayEquals("FAIL",new String[0], AnagramUtil.getLargestAnagramGroup(singleStringArr));
	}
	
	@Test
	public void testGetLargestAnagramGroupAllAnagramsList() {
		Assert.assertArrayEquals("FAIL",new String[]{"same","same","same","same","same"}, 
				  AnagramUtil.getLargestAnagramGroup(allAnagramsArr));
	}
	
	@Test
	public void testGetLargestAnagramGroupNoAnagrams() {
		Assert.assertArrayEquals("FAIL",new String[0], AnagramUtil.getLargestAnagramGroup(noAnagramsArr));
	}
	
	@Test
	public void testGetLargestAnagramGroupEvenSizeList() {
		Assert.assertArrayEquals("FAIL",new String[]{"ideals","sailed"}, 
				  AnagramUtil.getLargestAnagramGroup(evenSizeList));
	}
	
	//getLargestAnagramGroup tests from .txt file
	@Test
	public void testGetLargestAnagramGroupFromSampleFile() {
		Assert.assertArrayEquals("FAIL",new String[]{"carets","Caters","caster","crates","Reacts","recast","traces"}, 
				  AnagramUtil.getLargestAnagramGroup("sample_word_list.txt"));
	}
	
	@Test
	public void testGetLargestAnagramGroupFromModerateFile() {
		Assert.assertArrayEquals("FAIL",new String[]{"act","cat"}, 
				  AnagramUtil.getLargestAnagramGroup("moderate_word_list.txt"));
	}
	
	@Test
	public void testGetLargestAnagramGroupFromEmptyFile() {
		Assert.assertArrayEquals("FAIL",new String[0], AnagramUtil.getLargestAnagramGroup("empty.txt"));
	}
	
	@Test
	public void testGetLargestAnagramGroupFromOneWordFile() {
		Assert.assertArrayEquals("FAIL",new String[0], AnagramUtil.getLargestAnagramGroup("oneWord.txt"));
	}
	
	@Test
	public void testGetLargestAnagramGroupFromOneAnagramFile() {
		Assert.assertArrayEquals("FAIL",new String[]{"lAmE","MeAl"}, AnagramUtil.getLargestAnagramGroup("oneAnagram.txt"));
	}
	
}
