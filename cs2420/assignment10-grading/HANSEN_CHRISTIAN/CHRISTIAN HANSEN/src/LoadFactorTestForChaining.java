package assignment10;

import java.util.Random;

/**
 * 
 * @author Christian Hansen, U0621884
 * 
 * Tests for determining best load factor threshold
 *
 */
public class LoadFactorTestForChaining {
	
	public static void main(String args[]){
		
		
		ChainingHashTable table = new ChainingHashTable(13, new GoodHashFunctor());
		
		long start;
		long end;
		long time = 0;
		
		for(int index = 0; index < 2000; index++){
			start = System.nanoTime();
			table.add(randomWord());
			end = System.nanoTime();
			
			time += (end - start);
		}

		double averageTime = time /( (double) 2000*1000000);
		
		System.out.println(averageTime);
		
	}
	
	private static String randomWord(){
		
		Random rObject = new Random();
		int wordSize = rObject.nextInt(10) + 4;
		
		String word = "";
		
		for(int index = 0; index < wordSize; index++ ){
			word += randomLetter();
		}
		
		return word;
	}
	
	private static char randomLetter(){
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		char[] alArray = alphabet.toCharArray();
		
		Random rObject = new Random();
		
		char letter = alArray[rObject.nextInt(26)];
		
		return letter;
	}

}
