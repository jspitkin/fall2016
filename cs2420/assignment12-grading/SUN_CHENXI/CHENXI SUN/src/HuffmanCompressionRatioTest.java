package assignment12;
/**
 * 
 * @author Chenxi Sun
 * @uid u0455173
 *
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import assignment08.BinarySearchTree;

public class HuffmanCompressionRatioTest {

	public static void main(String[] args) throws IOException{
		
		
		
		HuffmanCompressionSize();
		
	}
	
	public static void HuffmanCompressionSize() throws IOException {
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1_000_000_000)
			;

		
			int increase=26;
			long totalTime = 0;

			// TIME IT!
			
			BinarySearchTree<Integer> abc=new BinarySearchTree<Integer>();
			File decompressedfile=null;
			File compressedfile=new File("compressed.txt");
			HuffmanTree t=new HuffmanTree();
			long start = System.nanoTime();
			for (int i = 1; i <=increase; i++) {
				decompressedfile=generateRandomTextFile(randomString(i));
				
				t.compressFile(decompressedfile, compressedfile);
				System.out.println(i + "\t"+(double)compressedfile.length()/decompressedfile.length());
				
				// will increase the increase by 10 and change the random string
				// length by 10 each time through the loop
			}
			long stop = System.nanoTime();
			totalTime += stop - start;

		
	

			double averageTime = totalTime ;

			
		
	}
	
	public static File generateRandomTextFile(String a) throws IOException{
		File outputFile=new File("abc.txt");
		try (PrintWriter output = new PrintWriter(new FileWriter(outputFile))) {
			output.write(a);
		}
		return outputFile;
	}
	
	
	
	public static String randomString(int uniquechar)
	{
		StringBuilder stringBuilder = new StringBuilder();
		for(int i = 0; i < 10000; i++)
		{
			// ASCII values a-z,A-Z are contiguous (52 characters)
			Random r=new Random();
			char randomChar = (char)((r.nextInt(uniquechar)+'a'));// This will throw a null pointer! Find the bug and squash it!
			

				stringBuilder.append(randomChar);
			
			 
		}
		return stringBuilder.toString();
	}
	
	
	
}
