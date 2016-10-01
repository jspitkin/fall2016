package assignment04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class containing static methods for use in testing the AnagramUtil class.
 * @author CS 2420 Staff, Jared Nielson u0495206, Christian Hansen u0621884
 *
 */
public class AnagramTester {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		
		//Empty main method
	}

	
	/**
	 * Returns a random string [A-z] of specified length	
	 * @param length - The lenght of the desired string
	 * @param rand An object of type Random
	 * @return a new string with random characters of the given length.
	 */
	public static String randomString(int length, Random rand)
	{
		StringBuilder stringBuilder = new StringBuilder();
		for(int i = 0; i < length; i++)
		{
			// ASCII values a-z,A-Z are contiguous (52 characters)
			char randomChar = (char)('a' + (rand.nextInt(26)));
			stringBuilder.append(randomChar); 
		}
		return stringBuilder.toString();
	}
	
	
	 
	/**
	 * Reads words from a file (assumed to contain one word per line)
	 * Returns the words as an array of strings.
	 * 
	 * @param filename - File name including extension
	 * @return an array of strings read from the given file using white space as a delimiter.
	 */
	public static String[] readFile(String filename) {
		ArrayList<String> results = new ArrayList<String>();
		try(BufferedReader input = new BufferedReader(new FileReader(filename))) {
			while(input.ready()) {
				results.add(input.readLine());
			}
			input.close();
		} catch(Exception e) {
			
		}
		return results.toArray(new String[results.size()]);
	}
}
