//William Frank
package assignment12;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

public class HuffmanTreeTest {

	@Test
	public void shortFile() throws FileNotFoundException {
		compressFile("originalShort.txt", "compressedShort.txt");

		decompressFile("compressedShort.txt", "decompressedShort.txt");

		assertTrue(readFile("originalShort.txt").equals(readFile("decompressedShort.txt")));

	}

	@Test
	public void longFile() throws FileNotFoundException {
		compressFile("originalLong.txt", "compressedLong.txt");

		decompressFile("compressedLong.txt", "decompressedLong.txt");

		assertTrue(readFile("originalLong.txt").equals(readFile("decompressedLong.txt")));

	}


	/**
	 * Compresses a file using Huffman's algorithm
	 * @param infile location of the input file
	 * @param outfile location of the output file
	 */
	private void compressFile(String infile, String outfile) {
		HuffmanTree t = new HuffmanTree();

		t.compressFile(new File(infile), new File(outfile));
	}

	/**
	 * Decompresses a file that was compressed using Huffman's algorithm
	 * @param infile location of the input file
	 * @param outfile location of the output file
	 */
	private void decompressFile(String infile, String outfile) {
		HuffmanTree t = new HuffmanTree();

		t.decompressFile(new File(infile), new File(outfile));
	}

	/**
	 * Returns the contents of a file as a string
	 * @param filename location of the file to be read
	 * @return the contents of filename as a string
	 * @throws FileNotFoundException
	 */
	private String readFile (String filename) throws FileNotFoundException {
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
