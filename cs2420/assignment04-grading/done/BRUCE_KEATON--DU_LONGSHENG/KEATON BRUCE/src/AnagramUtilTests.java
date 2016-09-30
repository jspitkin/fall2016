package assignment04;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Junit Testing class for AnagramUtil
 * @author Andrew Keaton Bruce, Longsheng Du
 * u1006889, u1093993
 * CS 2420
 * 9/20/2016
 */
public class AnagramUtilTests {
	//Sample anagrams
	String silent;
	String listen;
	String god;
	String dog;
	String dgo;
	
	@Before
	public void setUp() {
		silent = "silent";
		listen = "listen";
		god = "god";
		dog = "dOg";
		dgo = "dgo";
	}
	
	@Test
	public void sortListenAndSilent() {
		String listenSorted = AnagramUtil.sort(listen);
		String silentSorted = AnagramUtil.sort(silent);
		Assert.assertEquals(listenSorted, silentSorted);
	}
	
	@Test
	public void sortGodAndDog() {
		String godSorted = AnagramUtil.sort(god).toLowerCase();
		String dogSorted = AnagramUtil.sort(dog).toLowerCase();
		Assert.assertEquals(godSorted, dogSorted);
	}
	
	@Test
	public void areAnagramsTest() {
		Assert.assertTrue(AnagramUtil.areAnagrams(dog,god));
	}
	
	@Test
	public void areAnagramsFalseTest() {
		Assert.assertFalse(AnagramUtil.areAnagrams(dog,silent));
	}
	
	@Test
	public void getLargestAnagramGroupTest() {
		String[] arrayOfAnagrams = {silent, god, listen, dog, dgo};
		String[] expectedArrayOfStrings = {god,dog,dgo};
		Assert.assertTrue(Arrays.equals(expectedArrayOfStrings, AnagramUtil.getLargestAnagramGroup(arrayOfAnagrams)));
	}
	
	@Test
	public void getLargestAnagramOneTest() {
		String[] arrayOfAnagrams = {silent, god, dog};
		String[] expectedArrayOfStrings = {god, dog};
		Assert.assertTrue(Arrays.equals(expectedArrayOfStrings, AnagramUtil.getLargestAnagramGroup(arrayOfAnagrams)));
	}
	
	@Test
	public void EmptyAnagramGroupTest() {
		String[] arrayOfAnagrams = {silent, dgo};
		String[] expectedArrayOfStrings = {};
		Assert.assertTrue(Arrays.equals(expectedArrayOfStrings, AnagramUtil.getLargestAnagramGroup(arrayOfAnagrams)));
	}
	
	@Test
	public void getLargestAnagramGroupFromFileTest() {
		String[] anagramArray = AnagramUtil.getLargestAnagramGroup("sample_word_list.txt");
		String[] expectedArray = AnagramTester.readFile("expectedWords.txt");
		Assert.assertTrue(Arrays.equals(anagramArray, expectedArray));
	}

}
