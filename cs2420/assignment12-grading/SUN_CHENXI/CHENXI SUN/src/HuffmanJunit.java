package assignment12;
/**
 * 
 * @author Chenxi Sun
 * @uid u0455173
 *
 */
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

public class HuffmanJunit {
	
	
	@Test
	public void HuffmanJunit() throws Exception {
		compressFile("Class1.java", "compressedfile.txt");
		decompressFile("compressedfile.txt", "decompressed.txt");
		assertEquals(readFromFile(new File("Class1.java")),readFromFile(new File("decompressed.txt")));
		compressFile("Class10.java", "compressedfile.txt");
		decompressFile("compressedfile.txt", "decompressed.txt");
		assertEquals(readFromFile(new File("Class10.java")),readFromFile(new File("decompressed.txt")));
		compressFile("Class11.java", "compressedfile.txt");
		decompressFile("compressedfile.txt", "decompressed.txt");
		assertEquals(readFromFile(new File("Class11.java")),readFromFile(new File("decompressed.txt")));
	
	}
	
	public List<String> readFromFile(File file) {
		ArrayList<String> words = new ArrayList<String>();

		try (Scanner fileInput = new Scanner(file)) {
			/*
			 * Java's Scanner class is a simple lexer for Strings and primitive
			 * types (see the Java API, if you are unfamiliar).
			 */

			/*
			 * The scanner can be directed how to delimit (or divide) the input.
			 * By default, it uses whitespace as the delimiter. The following
			 * statement specifies anything other than alphabetic characters as
			 * a delimiter (so that punctuation and such will be ignored). The
			 * string argument is a regular expression that specifies "anything
			 * but an alphabetic character". You need not understand any of this
			 * for the assignment.
			 */
			fileInput.useDelimiter("\\s*[^a-zA-Z]\\s*");

			while (fileInput.hasNext()) {
				String s = fileInput.next();
				if (!s.equals("")) {
					words.add(s.toLowerCase());
				}
			}

		} catch (FileNotFoundException e) {
			System.err.println("File " + file + " cannot be found.");
		}

		

		return words;
	}

	
	public static void compressFile(String infile, String outfile) {
		    HuffmanTree t = new HuffmanTree();

		    t.compressFile(new File(infile), new File(outfile));
		    
		    
		    t.huffmanToDot("huffman_tree.dot");

		  }
		  
		  public static void decompressFile(String infile, String outfile) {
		    HuffmanTree t = new HuffmanTree();

		    t.decompressFile(new File(infile), new File(outfile));
		  }
	
}
