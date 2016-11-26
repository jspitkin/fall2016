package assignment08;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mina Kim u1054673 & Miao Yu u0827659
 */

import org.junit.Test;

public class SpellCheckerTest 
{
    SpellChecker dictionary = new SpellChecker(new File("dictionary.txt"));
	List<String> misspelling = new ArrayList<String>();

	@Test (expected = NullPointerException.class)
	public void testRemoveFromDictionaryThrowNullPointerException()
	{
		dictionary.removeFromDictionary(null);
	}
	
	@Test
	public void spellCheck()
	{
		misspelling = dictionary.spellCheck(new File("spellCheckThis.txt"));
		assertEquals(misspelling.get(3), "abhorreint");
		assertEquals(misspelling.get(4), "padloccck");
	}
	
	@Test
	public void spellCheckCaseInsensitive()
	{
		misspelling = dictionary.spellCheck(new File("spellCheckThisToo.txt"));
		assertTrue(misspelling.isEmpty());
	}
	
	
	
	
	
	
	
	
}
