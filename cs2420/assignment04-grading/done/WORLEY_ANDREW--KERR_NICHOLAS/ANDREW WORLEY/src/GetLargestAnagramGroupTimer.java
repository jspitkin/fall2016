package assignment04;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 * Class used to time varying cases of the AnagramUtil class 
 * static method GetLargestAnagramGroup.
 * @author Andrew Worley: u0651238
 * @author Nicholas Kerr: u0125990
 * last update, 9/20/16 22:39
 */
public class GetLargestAnagramGroupTimer {
	private static long start;
	private static long end;
	private static long total;
	private static int exp = 10;
	private static int length;
	private static final int TESTS = 11;
	private static final int  ITERATIONS = 10;
	private static DateFormat dateTime = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
	private static String displayDateTime = dateTime.format(new Date());
	
	public static void main(String[]args) {
		while (System.nanoTime() - start < 1_000_000_000);
		
		ArrayList<String[]> stringLists = new ArrayList<String[]>();
		for (int i = 0; i < TESTS; i++) {
			int collectionSize = (int) Math.pow(2, exp);
			stringLists.add(populateStringArr(collectionSize));
			exp++;
		}
		System.out.println(displayDateTime);
		for (int i = 0; i < TESTS; i++) {
			total = 0;
			String[] list = null;
			for (int j = 0; j < ITERATIONS; j++) {
				list = stringLists.get(i).clone();
				start = System.nanoTime();
				AnagramUtil.getLargestAnagramGroup(list);
				end = System.nanoTime()-start;
				total += end;
			}
			System.out.println((i+1)+":\t"+ (total/ITERATIONS) +"\t" +list.length);
			exp++;
		}
	}
	
	/**
	 * Method populates a String array to be stored
	 * in a list of String arrays.
	 * @param size -- amount of Strings in array
	 * @return -- A String array containing Strings of 5 - 15 length
	 */
	public static String[] populateStringArr(int size) {
		String[] result = new String[size];
		for(int index = 0; index < size; index++) {
			length = new Random().nextInt(11) + 5;
			result[index] = randomString(length);
		}
		return result;
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
