package assignment08;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit Test Class for SpellChecker
 * 
 * @author Andy Dao, uID: u0692334
 * @author Sam Bridge, uID: u0984334
 */
public class SpellCheckerTest {

    SpellChecker dictionary, helloWorld, emptyDictionary;
    ArrayList<Integer> arrList;

    @Before
    public void setUp() throws Exception {
	// Create the dictionary with the dictionary.txt file
	dictionary = new SpellChecker(new File("dictionary.txt"));
	helloWorld = new SpellChecker(new File("hello_world.txt"));
	emptyDictionary = new SpellChecker();
	arrList = new ArrayList<Integer>();
    }

    @After
    public void tearDown() throws Exception {
	dictionary = null;
	helloWorld = null;
	emptyDictionary = null;
    }

    @Test
    public void addToDictionary() {
	emptyDictionary.addToDictionary("hello");
	emptyDictionary.addToDictionary("there");
	emptyDictionary.addToDictionary("world");
	emptyDictionary.addToDictionary("nice");
	emptyDictionary.addToDictionary("to");
	emptyDictionary.addToDictionary("meet");
	emptyDictionary.addToDictionary("you");
	// Should be size 0 since empty dictionary contains all of hello_world.txt's words
	assertEquals(0, run_spell_check(emptyDictionary, "hello_world.txt").size());
    }

    @Test
    public void removeFromDictionary() {
	emptyDictionary.addToDictionary("hello");
	emptyDictionary.addToDictionary("there");
	emptyDictionary.addToDictionary("world");
	emptyDictionary.addToDictionary("nice");
	emptyDictionary.addToDictionary("to");
	emptyDictionary.addToDictionary("meet");
	emptyDictionary.addToDictionary("you");

	// hello_world.txt contains no misspellings compared with the emptyDictionary list
	assertFalse(run_spell_check(emptyDictionary, "hello_world.txt").contains("hello"));
	emptyDictionary.removeFromDictionary("hello"); // remove "hello" from emptyDictionary
	// hello_world.txt thinks the word "hello" is misspelled
	assertTrue(run_spell_check(emptyDictionary, "hello_world.txt").contains("hello"));

    }

    @Test
    public void spellCheck() {
	assertEquals(0, run_spell_check(dictionary, "hello_world.txt").size());
	assertFalse(run_spell_check(dictionary, "hello_world.txt").contains("hello"));

	// good_luck.txt has a misspelled word of "bst"
	assertEquals(1, run_spell_check(dictionary, "good_luck.txt").size());
    }

    /**
     * Runs the spell checker method with a dictionary
     * 
     * @param sc
     *            - the spell check list to be checked
     * @param documentFilename
     *            - the dictionary to be compared with
     */
    private static List<String> run_spell_check(SpellChecker sc, String documentFilename) {

	File doc = new File(documentFilename);
	List<String> misspelledWords = sc.spellCheck(doc);
	if (misspelledWords.size() == 0) {
	}
	else {
	    for (String w : misspelledWords) {
	    }
	}

	return misspelledWords;
    }

}
