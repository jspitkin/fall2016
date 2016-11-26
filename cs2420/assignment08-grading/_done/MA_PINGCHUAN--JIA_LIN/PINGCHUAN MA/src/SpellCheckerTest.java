package assignment08;

import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
/*
 * @author Lin Jia uid:u1091732
 * @author Pingchuan Ma uid:u0805309
 */
public class SpellCheckerTest {
SpellChecker spchecker=new SpellChecker();


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		spchecker.addToDictionary("good");
		spchecker.addToDictionary("better");
		spchecker.addToDictionary("best");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddToDictionary() {
		spchecker.addToDictionary("morning");
		spchecker.addToDictionary("hello");
		spchecker.addToDictionary("good");
		Assert.assertEquals(spchecker.getDictionary().size(), 5);
	}

	@Test
	public void testRemoveFromDictionary() {
		spchecker.removeFromDictionary("good");
		spchecker.removeFromDictionary("hello");
		Assert.assertFalse(spchecker.getDictionary().contains("good"));
		Assert.assertTrue(spchecker.getDictionary().contains("better"));
		
	}

	@Test
	public void testSpellCheck() {
		SpellChecker mySC = new SpellChecker(new File("dictionary.txt"));
		File doc = new File("good_luck.txt");
		Assert.assertEquals(mySC.spellCheck(doc).get(0), "lck");
		Assert.assertEquals(mySC.spellCheck(doc).get(1), "ssignment");
		
		
	}

}
