package assignment12;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit testing suite to ensure the correctness of a Huffman Compression program.
 * @author Dylan Johnson - u1024233
 * 11/21/16
 */
public class JUnitTestingCompression {

	HuffmanTree tree;

	@Before
	public void setUp() throws Exception {
		tree = new HuffmanTree();
	}

	@Test
	public void compressSmallFile() {

		File originalFile = new File("compressed_files/smallOriginal.txt");
		File compressedFile = new File("compressed_files/smallCompressed");
		File decompressedFile = new File("compressed_files/smallDecompressed");

		tree.compressFile(originalFile, compressedFile);
		tree.decompressFile(compressedFile, decompressedFile);

		assertTrue(originalFile.length() < compressedFile.length());
		// with all different values, the compression takes up more space
		compareFiles(originalFile, decompressedFile);

	}

	@Test
	public void compressLargeFile() {

		File originalFile = new File("compressed_files/largeOriginal.txt");
		File compressedFile = new File("compressed_files/largeCompressed");
		File decompressedFile = new File("compressed_files/largeDecompressed");

		tree.compressFile(originalFile, compressedFile);
		tree.decompressFile(compressedFile, decompressedFile);

		assertTrue(originalFile.length() > compressedFile.length());
		compareFiles(originalFile, decompressedFile);
	}

	@Test
	public void compressMassiveFile() {

		File originalFile = generateRandomFile(123123, "compressed_files/massiveOriginal", false);
		File compressedFile = new File("compressed_files/massiveCompressed");
		File decompressedFile = new File("compressed_files/massiveDecompressed");

		tree.compressFile(originalFile, compressedFile);
		tree.decompressFile(compressedFile, decompressedFile);

		//tree.huffmanToDot("compressed_files/massive.dot");

		assertTrue(originalFile.length() > compressedFile.length());
		compareFiles(originalFile, decompressedFile);

	}

	@Test
	public void compressFileOfAllRepeats() {

		File originalFile = new File("compressed_files/repeatsOriginal.txt");
		File compressedFile = new File("compressed_files/repeatsCompressed");
		File decompressedFile = new File("compressed_files/repeatsDecompressed");

		tree.compressFile(originalFile, compressedFile);
		tree.decompressFile(compressedFile, decompressedFile);

		assertTrue(originalFile.length() > compressedFile.length());
		compareFiles(originalFile, decompressedFile);
	}

	@Test
	public void compressFileOfALotOfDifferentRepeats() {

		File originalFile = generateRandomFile(123123, "compressed_files/massiveOriginal", true);
		File compressedFile = new File("compressed_files/massiveCompressed");
		File decompressedFile = new File("compressed_files/massiveDecompressed");

		tree.compressFile(originalFile, compressedFile);
		tree.decompressFile(compressedFile, decompressedFile);

		assertTrue(originalFile.length() > compressedFile.length());
		compareFiles(originalFile, decompressedFile);
	}

	private static void compareFiles(File original, File decompressed) {

		assertEquals(original.length(), decompressed.length());

		Scanner file1 = null;
		Scanner file2 = null;

		try {
			file1 = new Scanner(original);
			file2 = new Scanner(decompressed);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		while (file1.hasNext() && file2.hasNext()) {
			String token1 = file1.next();
			String token2 = file2.next();
			assertEquals(token1, token2);
		}

		if (file1.hasNext() || file2.hasNext()) {
			fail();
		}
	}

	private static File generateRandomFile(int size, String fileName, boolean onlyLowerCase) {

		File randomFile = new File(fileName);

		PrintWriter writer = null;

		try {
			writer = new PrintWriter(randomFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		if (!onlyLowerCase) {
			for (int i = 0; i < size; i++) {
				if (Math.random() < .001) {
					writer.append(' ');
				}
				if (Math.random() < .001) {
					writer.append('\n');
				}
				if (Math.random() < .001) {
					writer.append('\t');
				}
				char character = (char) (Math.random() * 115 + 11);
				writer.append(character);
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (Math.random() < .001) {
					writer.append(' ');
				}
				if (Math.random() < .001) {
					writer.append('\n');
				}
				if (Math.random() < .001) {
					writer.append('\t');
				}
				char character = (char) (Math.random() * (122 - 97) + 97);
				writer.append(character);
			}
		}
		return randomFile;
	}

}
