package assignment11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * Makes Strings for testing PriorityQueue
 * 
 * @author Amir Mohsenian
 * 
 * u0737564
 *
 */
public class StringMaker 
{

	 private static Random randomMaker= new Random();
	

	 /**
	  * Create a random String
	  * @param lngth
	  * @return
	  */ 
	public static String randomString(int lngth)
	{
		String sample;
		sample= "";
		
		int index = 0;
		while( index < lngth)
		{
			sample += (char)('a' + (randomMaker.nextInt(26)));
			index++;
		}
		return sample;
	}
	
	public static String[] readFile(String nameOfFile)
	{
		ArrayList<String> solutions;
		solutions= new ArrayList<String>();
		try
		{
			BufferedReader in;
			in= new BufferedReader(new FileReader(nameOfFile)); 
			while(in.ready())
			{
				solutions.add(in.readLine());
			}
			in.close();
		}
		catch(Exception e)
		{e.printStackTrace();}
		String[] retval = new String[1];
		return solutions.toArray(retval);
	}
}