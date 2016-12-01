package assignment10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author Abdulaziz Aljanahi
 * @uid u0901606
 * 
 * A string generator and a read from file
 */
public class StringGenerator {

	 private static Random rand = new Random();
	
	// Create a random string [a-z] of specified length
	public static String randomString(int length)
	{
		String retval = "";
		for(int i = 0; i < length; i++)
		{
			// ASCII values a-z,A-Z are contiguous (52 characters)
			retval += (char)('a' + (rand.nextInt(26)));
		}
		return retval;
	}
	
	public static String[] readFile(String filename)
	{
		ArrayList<String> results = new ArrayList<String>();
		try
		{
			BufferedReader input = new BufferedReader(new FileReader(filename));
			while(input.ready())
			{
				results.add(input.readLine());
			}
			input.close();
		}
		catch(Exception e)
		{e.printStackTrace();}
		String[] retval = new String[1];
		return results.toArray(retval);
	}
}