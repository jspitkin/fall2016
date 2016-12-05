package assignment12;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.junit.Test;

/**
 * HuffmanTreeTest - contains JUnit tests that step through a compressed/decompressed file, 
 * character by character to ensure they are being correctly compressed/decompressed.
 * @author Kyle Price
 * 11/22/2016
 */
public class HuffmanTreeTest {
	HuffmanTree tree = new HuffmanTree();


	@SuppressWarnings("resource")
	@Test
	public void testBasicFunctionality() throws FileNotFoundException {
		tree.compressFile(new File("gandalf.txt"), new File("compressedGandalf.txt"));
		tree.decompressFile(new File("compressedGandalf.txt"), new File("decompressedGandalf.txt"));
		Scanner s = new Scanner(new File("decompressedGandalf.txt"));
		Scanner key = new Scanner(new File("gandalfSol.txt"));
		char[] keyChars;
		char[] chars;

		while (s.hasNextLine()) {
			chars = s.nextLine().toCharArray();
			keyChars = key.nextLine().toCharArray();
			for (int i = 0; i < chars.length; i++) {
				if (chars[i] != keyChars[i]) {
					assertTrue(false);
				}
			}
			assertTrue(true);
		}
	}
	@SuppressWarnings("resource")
	@Test
	public void testDictionary() throws FileNotFoundException {
		tree.compressFile(new File("dictionary.txt"), new File("compressedDictionary.txt"));
		tree.huffmanToDot("huffman_tree.dot");
		tree.decompressFile(new File("compressedDictionary.txt"), new File("decompressedDictionary.txt"));
		Scanner s = new Scanner(new File("decompressedDictionary.txt"));
		Scanner key = new Scanner(new File("dictionarySol.txt"));
		char[] keyChars;
		char[] chars;

		while (s.hasNextLine()) {
			chars = s.nextLine().toCharArray();
			keyChars = key.nextLine().toCharArray();
			for (int i = 0; i < chars.length; i++) {
				if (chars[i] != keyChars[i]) {
					assertTrue(false);
				}
			}
			assertTrue(true);
		}
	}
	@SuppressWarnings("resource")
	@Test
	public void testMaze() throws FileNotFoundException {
		tree.compressFile(new File("bigMaze.txt"), new File("compressedBigMaze.txt"));
		tree.decompressFile(new File("compressedBigMaze.txt"), new File("decompressedBigMaze.txt"));
		Scanner s = new Scanner(new File("decompressedBigMaze.txt"));
		Scanner key = new Scanner(new File("bigMazeSol.txt"));
		char[] keyChars;
		char[] chars;

		while (s.hasNextLine()) {
			chars = s.nextLine().toCharArray();
			keyChars = key.nextLine().toCharArray();
			for (int i = 0; i < chars.length; i++) {
				if (chars[i] != keyChars[i]) {
					assertTrue(false);
				}
			}
			assertTrue(true);
		}
	}
}
