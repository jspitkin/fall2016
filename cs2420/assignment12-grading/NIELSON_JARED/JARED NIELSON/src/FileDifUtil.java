package assignment12;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Utility class that performs comparisons on file contents and sizes.
 * Can also be used to create randomly generated files.
 * 
 * @author Jared Nielson u0495206
 *
 */
public class FileDifUtil {
	/**
	 * Determines if the contents of the two files are uniuqe.
	 * @param file1 - Path and name to the first file
	 * @param file2 - Path and name to the second file
	 * @return - True if the entire contents of both files are identical, false otherwise.
	 */
	public static boolean difFiles(String file1, String file2){
		
		try {
			BufferedReader reader1 = new BufferedReader(new FileReader(new File(file1)));
			BufferedReader reader2 = new BufferedReader(new FileReader(new File(file2)));
			
			String line1 = reader1.readLine();
			String line2 = reader2.readLine();
			
			while(line1 != null || line2 !=null){
				if((line1 == null && line2 != null) ||
						(line1 != null && line2 == null)){
					reader1.close();
					reader2.close();
					
					return false;
				}
				
				if(!line1.equals(line2)){
					reader1.close();
					reader2.close();
					
					return false;
				}
				
				line1 = reader1.readLine();
				line2 = reader2.readLine();
				
			}
			reader1.close();
			reader2.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return true;
	}
	
	/**
	 * Finds the size in bytes of the givien file.
	 * @param file - path to the file
	 * @return - the file size in bytes
	 * @throws FileNotFoundException - If the file cannot be found or is a directory
	 */
	public static long fileSize(String file) throws FileNotFoundException{
		File input = new File(file);
		
		if(!input.exists() || input.isDirectory()){
			throw new FileNotFoundException();
		}
		return input.length();
	}
	
	/**
	 * Compares two file sizes using a ratio. The ratio is uncompressedFile Size / compressedFile size
	 * @param uncompressedFile - The path to the uncompressedFile
	 * @param compressedFile - The path to the compressed file
	 * @return The ratio of the two file sizes
	 * @throws FileNotFoundException if one or the other file cannot be found.
	 */
	public static double compressionRatio(String uncompressedFile, String compressedFile) throws FileNotFoundException{
		return fileSize(compressedFile) / (double) fileSize(uncompressedFile);
	}
	
	/**
	 * Generates a text file with the characters indicated at the frequency specified
	 * @param fileName - Full path to the created file including name
	 * @param chars - The ASCII chars 0 - 127 to be included in the text file.
	 * @param frequency - The number of times a char will appear in the text file. The value of frequency[i] will be 
	 * the number of times the character char[i] is included in the text file.
	 */
	public static void generateRandomTxt(String fileName, int[] chars, int[] frequency){
		if(chars.length != frequency.length){
			throw new IndexOutOfBoundsException("Array Dimmension Mismatch");
		}
		
		int sum = 0;
		for(int index = 0; index < frequency.length; index++){
			sum += frequency[index];
		}
		
		int[] toWrite = new int[sum];
		int toWriteIdx = 0;
		for(int index = 0; index < chars.length; index++){
			for(int inIdx = 0; inIdx < frequency[index]; inIdx++){
				toWrite[toWriteIdx++] = chars[index];
			}
		}
		
		Random rng = new Random();
		
		for(int index = 0; index < toWrite.length; index++){
			int rdmIdx = rng.nextInt(toWrite.length);
			
			int tmp = toWrite[rdmIdx];
			toWrite[rdmIdx] = toWrite[index];
			toWrite[index] = tmp;
		}
		
		try {
			FileWriter fw = new FileWriter(new File(fileName));
			
			for(int index = 0; index < toWrite.length; index++){
				fw.write((char) toWrite[index]);
			}
			
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
















