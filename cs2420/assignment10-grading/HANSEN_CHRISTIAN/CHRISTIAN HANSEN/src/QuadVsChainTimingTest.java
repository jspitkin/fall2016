package assignment10;

import java.util.Random;

/**
 * 
 * @author Christian Hansen, u0621884
 * 
 * TimingTestCode for QuadProbeHashTable vs ChainingHashTable. I changed the table to the type (Quad or Chain)
 * before running.
 *
 */
public class QuadVsChainTimingTest {

	public static void main(String args[]){
		
		for(int index = 2000; index < 20001; index += 2000){

			ChainingHashTable table = new ChainingHashTable(13, new GoodHashFunctor());

			long start;
			long end;
			long time = 0;

			for(int indexInner = 0; indexInner < index; indexInner++){
				start = System.nanoTime();
				table.add(randomWord());
				end = System.nanoTime();

				time += (end - start);
			}

			double averageTime = time /( (double) 2000*1000000);

			System.out.println(averageTime);
		}
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
