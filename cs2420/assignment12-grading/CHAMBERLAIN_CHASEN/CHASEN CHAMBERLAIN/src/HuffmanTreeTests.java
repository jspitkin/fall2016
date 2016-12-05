package assignment12;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.Test;

/**
 * JUnit test cases for HuffmanTree
 * @author Chasen Chamberlain, u0583257
 *
 */
public class HuffmanTreeTests {

	@Test
	public void testingIHeartDataTxtFile() throws IOException {
		CompressionDemo.compressFile("original.txt", "compressed.txt");

		CompressionDemo.decompressFile("compressed.txt", "decompressed.txt");
		Path og = Paths.get("original.txt");
		Path decomp = Paths.get("decompressed.txt");
		assertTrue(sameContent(og, decomp));
	}

	@Test
	public void testingABookTxtFile() throws IOException {
		CompressionDemo.compressFile("book.txt", "compressed.txt");

		CompressionDemo.decompressFile("compressed.txt", "decompressed.txt");
		Path og = Paths.get("book.txt");
		Path decomp = Paths.get("decompressed.txt");
		assertTrue(sameContent(og, decomp));
	}

	@Test
	public void testingUniqueCharacters() throws IOException {
		CompressionDemo.compressFile("unique.txt", "compressed.txt");

		CompressionDemo.decompressFile("compressed.txt", "decompressed.txt");
		Path og = Paths.get("unique.txt");
		Path decomp = Paths.get("decompressed.txt");
		assertTrue(sameContent(og, decomp));
	}

	@Test
	public void testingLotsAndLotsOfDuplicates() throws IOException {
		CompressionDemo.compressFile("repeat.txt", "compressed.txt");

		CompressionDemo.decompressFile("compressed.txt", "decompressed.txt");
		Path og = Paths.get("repeat.txt");
		Path decomp = Paths.get("decompressed.txt");
		assertTrue(sameContent(og, decomp));
	}
	
	@Test
	public void testingMedSizeFile() throws IOException {
		CompressionDemo.compressFile("med.txt", "compressed.txt");

		CompressionDemo.decompressFile("compressed.txt", "decompressed.txt");
		Path og = Paths.get("med.txt");
		Path decomp = Paths.get("decompressed.txt");
		assertTrue(sameContent(og, decomp));
	}

	// Took from Stackoverflow, compares if 2 files are exactly the same. 
	private boolean sameContent(Path file1, Path file2) throws IOException {

		if (Files.size(file1) != Files.size(file2)) {
			return false;
		}
		if (Files.size(file1) < 10000000) {
			return Arrays.equals(Files.readAllBytes(file1), Files.readAllBytes(file2));
		}
		try (InputStream is1 = Files.newInputStream(file1); InputStream is2 = Files.newInputStream(file2)) {
			// Compare byte-by-byte.
			// Note that this can be sped up drastically by reading large chunks
			// (e.g. 16 KBs) but care must be taken as InputStream.read(byte[])
			// does not neccessarily read a whole array!
			int data;
			while ((data = is1.read()) != -1) {
				if (data != is2.read())
					return false;
			}
		}
		return true;
	}

}
