package assignment04;

import java.util.Random;

/**
 * Timing class for the areAnagrams method
 * @author Christian Hansen u0621884 Jared Nielson u0495206
 *
 */
public class AreAnagramsTimer {
	
	private static final int ITER_COUNT = 5;
	private static final int CONV_TO_MILI = 1000000;

	public static void main(String[] args) {
		
		
		for(int index = 1024; index < 1048577; index *=2){
			
			long totalTime = 0;
			
			for(int iter = 0; iter < ITER_COUNT; iter++){
				
				String experiementString1 = randomString(index);
				String experiementString2 = randomString(index);
				long start;
				long end;
				start = System.nanoTime();
				AnagramUtil.areAnagrams(experiementString1, experiementString2);
				end = System.nanoTime();
				
				long tempTime = (end - start);
				
				totalTime += tempTime;
			}
			
			double averageTime = totalTime / ((double)(ITER_COUNT)*(CONV_TO_MILI));
			
			System.out.println("" + index + "\t" + averageTime);
			
		}


	}
	
	/**
	 * Creates a string with random characters [A-z] of a given length
	 * @param length - The desired length of the string
	 * @return a new string of the given length.
	 */
	public static String randomString(int length){
		
		Random rand = new Random();
		
		StringBuilder stringBuilder = new StringBuilder();
		for(int i = 0; i < length; i++){
			// ASCII values a-z,A-Z are contiguous (52 characters)
			char randomChar = (char)('a' + (rand.nextInt(26)));// This will throw a null pointer! Find the bug and squash it!
			stringBuilder.append(randomChar); 
		}
		return stringBuilder.toString();
	}
	

}
