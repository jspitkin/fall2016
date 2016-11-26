package assignment08;

import java.io.File;
import java.util.List;

/**
 * A small demonstration of the SpellChecker class.
 * 
 * @author Andy Dao, uID: u0692334
 * @author Sam Bridge, uID: u0984334
 */
public class SpellCheckerDemo {

    public static void main(String[] args) {

	SpellChecker mySC = new SpellChecker(new File("dictionary.txt"));

	run_spell_check(mySC, "hello_world.txt");
	run_spell_check(mySC, "good_luck.txt");
    }

    private static void run_spell_check(SpellChecker sc, String documentFilename) {

	File doc = new File(documentFilename);
	List<String> misspelledWords = sc.spellCheck(doc);
	if (misspelledWords.size() == 0) {
	    //	    System.err.println("There are no misspelled words in file " + doc + ".");
	}
	else {
	    //	    System.err.println("The misspelled words in file " + doc + " are:");
	    for (String w : misspelledWords) {
		//		System.out.println("\t" + w);
	    }
	}
    }
}
