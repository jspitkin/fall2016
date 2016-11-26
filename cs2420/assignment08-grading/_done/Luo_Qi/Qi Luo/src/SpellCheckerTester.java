package assignment08;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SpellCheckerTester {

	public static void main(String[] args) {
		//add method
		System.out.println("----------------add-----------");
		SpellChecker sc_add = new SpellChecker();
		addTest(sc_add);
		run_spell_check(sc_add, "hello_world.txt");
		
		//remove method
		System.out.println("----------------remove-----------");
		SpellChecker sc_remove = new SpellChecker();
		removeTest(sc_remove);
		run_spell_check(sc_remove, "hello_world.txt");
		
		//test in file
		System.out.println("----------------file-----------");
	    SpellChecker mySC = new SpellChecker(new File("smalltester.txt"));
	    run_spell_check(mySC, "hello_world.txt");
	    run_spell_check(mySC, "good_luck.txt");
	}

	private static void run_spell_check(SpellChecker sc, String documentFilename) {

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
	
	private static void addTest(SpellChecker spc){
		spc.addToDictionary("hello");
		spc.addToDictionary("how");
		spc.addToDictionary("are");
		spc.addToDictionary("you");
		spc.addToDictionary("NICE");
		spc.addToDictionary("to");
		spc.addToDictionary("see");
		spc.addToDictionary("You");
	}
	

	private static void removeTest(SpellChecker spc) {
		spc.addToDictionary("hello");
		spc.addToDictionary("how");
		spc.addToDictionary("are");
		spc.addToDictionary("you");
		spc.addToDictionary("NICE");
		spc.addToDictionary("to");
		spc.addToDictionary("see");
		spc.addToDictionary("You");
		
		spc.removeFromDictionary("You");
		spc.removeFromDictionary("WORLD");
	}

}
