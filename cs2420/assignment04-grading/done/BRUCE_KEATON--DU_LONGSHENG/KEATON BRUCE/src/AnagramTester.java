package assignment04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

/**
 * AnagramTester provides random Strings and a realFile method
 * @author Andrew Keaton Bruce, Longsheng Du
 * u1006889, u1093993
 * CS 2420
 * 9/20/2016
 */
public class AnagramTester {

	private static Random rand = new Random();
	private static Random randPicker = new Random();
	
	// Create a random string [a-z] of specified length
	public static String randomString(int length)
	{
		StringBuilder stringBuilder = new StringBuilder();
		for(int i = 0; i < length; i++)
		{
			char randomChar;
			// ASCII values a-z,A-Z are contiguous (52 characters)
			//Variations between upper case and lower case are randomly decided
			//0-49 are upper case, 50-99 are lower case
			if (randPicker.nextInt(100) < 50) {
				randomChar = (char)('A' + (rand.nextInt(25)));
			}
			else {
				randomChar = (char)('a' + (rand.nextInt(25)));
			}
			stringBuilder.append(randomChar); 
		}
		return stringBuilder.toString();
	}
	
	
	// Reads words from a file (assumed to contain one word per line)
	// Returns the words as an array of strings.
	public static String[] readFile(String filename) {
		ArrayList<String> results = new ArrayList<String>();
		try(BufferedReader input = new BufferedReader(new FileReader(filename))) {
			while(input.ready()) {
				results.add(input.readLine());
			}
			input.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return results.toArray(new String[results.size()]);
	}
}
