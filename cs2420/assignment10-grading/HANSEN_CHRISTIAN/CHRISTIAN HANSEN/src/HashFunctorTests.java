package assignment10;

import java.util.Random;

/**
 * 
 * @author Christian Hansen, u0621884
 * 
 * Time tests to show the performance of the HashFunctors. (I changed to Bad, Good, or Mediocre before running).
 *
 */
public class HashFunctorTests {

	public static void main(String args[]){
		
		
		QuadProbeHashTable table = new QuadProbeHashTable(13, new BadHashFunctor());
		
		long start;
		long end;
		long time = 0;
		
		for(int index = 0; index < 10000; index++){
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
