package assignment04;

import java.util.Random;

/**
 * Class used to time varying cases of the AnagramUtil class
 * static method AreAnagrams.
 * @author Andrew Worley: u0651238
 * @author Nicholas Kerr: u0125990
 * last update, 9/20/16 22:39
 */
public class AreAnagramsTimer {
	private static long start;
	private static long end;
	private static long total;
	private static int stringSize = 50;
	private static int exp = 10;
	private static final int TESTS = 8;
	private static final int  ITERATIONS = 10;
	
	public static void main(String[]args) {
		while (System.nanoTime() - start < 1_000_000_000);
		
		for (int i = 0; i < TESTS; i++) {
			stringSize = (int) Math.pow(2, exp);
			total = 0;
			for (int j = 0; j < ITERATIONS; j++) {
				String string1 = randomString(stringSize);
				String string2 = randomString(stringSize);
				start = System.nanoTime();
				AnagramUtil.areAnagrams(string1, string2);
				end = System.nanoTime()-start;
				total += end;
			}
			System.out.println((i+1)+":\t"+ (total/ITERATIONS) +"\t"+ stringSize);
			exp++;
		}
	}
	
	/**
	 * Use of this method is to create random strings.
	 * @author CS2420, modified by Andrew Worley: u0651238
	 * @param length -- String size
	 * @return -- A random String of the specified length
	 */
	public static String randomString(int length)
	{
		StringBuilder stringBuilder = new StringBuilder();
		for(int i = 0; i < length; i++)
		{
			// ASCII values a-z,A-Z are contiguous (52 characters)
			char randomChar = (char) ('a' + (new Random().nextInt(26)));
			stringBuilder.append(randomChar); 
		}
		return stringBuilder.toString();
	}
}
