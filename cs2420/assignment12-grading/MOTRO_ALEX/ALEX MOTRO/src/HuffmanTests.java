package assignment12;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Test;

public class HuffmanTests {

	public static void compressFile(String infile, String outfile) {
		HuffmanTree t = new HuffmanTree();

		t.compressFile(new File(infile), new File(outfile));

		t.huffmanToDot("huffman_tree.dot");
	}

	public static void decompressFile(String infile, String outfile) {
		HuffmanTree t = new HuffmanTree();

		t.decompressFile(new File(infile), new File(outfile));
	}

	/**
	 * Tests the compressFile and decompressFile methods from the HuffmanTree class
	 * and ensures that when a file is compressed and then decompressed, it's
	 * information is still intact (i.e. it reads exactly the same).
	 */
	@Test
	public void testIO() {
		//original is a really big text file with over 6000 characters (most of which are 'A')
		compressFile("original.txt", "compressed.txt");
		decompressFile("compressed.txt", "decompressed.txt");
		File original = new File("original.txt");
		File decomp = new File("decompressed.txt");
		
		assertTrue(fileEquals(original,decomp));
		
		//emptyfile.txt is an empty file . . . .txt
		compressFile("emptyfile.txt", "compressed.txt");
		decompressFile("compressed.txt", "decompressed.txt");
		original = new File("emptyfile.txt");
		decomp = new File("decompressed.txt");
		
		assertTrue(fileEquals(original,decomp));
		
		//harrypotterchapterone.txt is a delightful story about harrypotterchapterone. Remind me to tell you about it sometime.
		compressFile("harrypotterchapterone.txt", "compressed.txt");
		decompressFile("compressed.txt", "decompressed.txt");
		original = new File("harrypotterchapterone.txt");
		decomp = new File("decompressed.txt");
		
		assertTrue(fileEquals(original,decomp));
		
		//A.txt is a file that contains only the character 'A' . . . approx. 267000 times
		compressFile("A.txt", "compressed.txt");
		decompressFile("compressed.txt", "decompressed.txt");
		original = new File("A.txt");
		decomp = new File("decompressed.txt");
		
		assertTrue(fileEquals(original,decomp));
	}

	/**
	 * Returns true if and only if the two given files have exactly the same text.
	 * @param file1
	 * @param file2
	 * @return True if the files are identical.
	 */
	private static boolean fileEquals(File file1, File file2) {
		try (Scanner s1 = new Scanner(file1); Scanner s2 = new Scanner(file2)) {
			StringBuilder builder1 = new StringBuilder();
			StringBuilder builder2 = new StringBuilder();
			while(s1.hasNext()) 
				builder1.append(s1.next());
			while(s2.hasNext()) 
				builder2.append(s2.next());
			String string1 = builder1.toString();
			String string2 = builder2.toString();
			return string1.equals(string2);
		} catch (FileNotFoundException e) {
			return false;
		}
		
	}
}
