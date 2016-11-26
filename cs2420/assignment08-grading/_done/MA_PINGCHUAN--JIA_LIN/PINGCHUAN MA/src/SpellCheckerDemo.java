package assignment08;

import java.io.File;
import java.util.List;

/**
 * A small demonstration of the SpellChecker class.
 * 
 * @author
 */
public class SpellCheckerDemo {

  public static void main(String[] args) {

    SpellChecker mySC = new SpellChecker(new File("dictionary.txt"));
    System.out.println(mySC.getDictionary().size());
    //mySC.getDictionary().remove("good");
    System.out.println(mySC.getDictionary().contains("good"));
    mySC.removeFromDictionary("thief");
    System.out.println(mySC.getDictionary().contains("thief"));
    
    run_spell_check(mySC, "hello_world.txt");
    run_spell_check(mySC, "good_luck.txt");
    mySC.getDictionary().writeDot("test.dot");
  }

  private static void run_spell_check(SpellChecker sc, String documentFilename) {
	  //sc.getDictionary().clear();
	  //System.out.println(sc.getDictionary().contains("a"));
	  
    File doc = new File(documentFilename);
    List<String> misspelledWords = sc.spellCheck(doc);
    if (misspelledWords.size() == 0) {
      System.out.println("There are no misspelled words in file " + doc + ".");
    } else {
      System.out.println("The misspelled words in file " + doc + " are:");
      for (String w : misspelledWords) {
        System.out.println("\t" + w);
      }
    }
  }
}
