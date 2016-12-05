//William Frank
package assignment12;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class HuffmanTreeExperiment {
	
	public static void main(String[] args) throws IOException {
		
		HuffmanTree tree = new HuffmanTree();
		
		String[][] strings = new String[10][16];
		
		//Creates strings of text with various numbers and frequencies of unique characters
		for (int i = 0; i < strings.length; i++) {
			int frequency = (int) Math.pow(1.8, (i+1));
			
			for (int j = 0; j < strings[i].length; j++) {
				
				
				int variety = j*6;
				
				String str = "";
				
				for (int k = 0; k < frequency; k++)
				{
					for (int m = 0; m <= variety; m++)
					{
						str += (char) (m + 32);
					}
				}
				
				strings[i][j] = str;
				
			}
		}
				
		//compares the length of the original string to the length of the compressed string
		for (int i = 0; i < strings.length; i++)
		{
			for (int j = 0; j < strings[i].length; j++) {
				
				String str = strings[i][j];
				
				writeFile(str, "uncompressed.txt");
				
				tree.compressFile(new File("uncompressed.txt"), new File("compressed.txt"));
			
				String compressedData = readFile("compressed.txt");
				
				double ratio = (double) compressedData.length() / (double) str.length();
				
				System.out.print("[" + i + ", " + j + ", ");
				System.out.printf("%.2f", ratio);
				System.out.print("], ");
			}
			
			System.out.println();
		
		}
		
		
	}
	
	/**
	 * Writes text to a file
	 * @param text contents to be written to the file
	 * @param filename location of the file
	 */
	private static void writeFile(String text, String filename) {
		Writer writer = null;

		try {
		    writer = new BufferedWriter(new OutputStreamWriter(
		          new FileOutputStream(filename)));
		    writer.write(text);
		} catch (IOException e) {
		  e.printStackTrace();
		} finally {
		   try {writer.close();} catch (Exception ex) {/*ignore*/}
		}
	}
	
	/**
	 * Returns the contents of a file as a string
	 * @param filename location of the file to be read
	 * @return the contents of filename as a string
	 */
	private static String readFile(String filename) {
		String str = "";
		
		try (BufferedReader br = new BufferedReader(new FileReader(filename)))
		{

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				str+= sCurrentLine;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return str;
	}

}
