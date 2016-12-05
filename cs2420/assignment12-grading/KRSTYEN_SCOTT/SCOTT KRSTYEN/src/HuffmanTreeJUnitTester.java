package assignment12;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 * @author Scott Krstyen U0760822
 *
 */

public class HuffmanTreeJUnitTester {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Compresses a file.
	 * @param infile
	 * 			- File to be compressed.
	 * @param outfile
	 * 			- Name of outputted compressed file. 
	 */
	public static void compressFile(String infile, String outfile) {
		HuffmanTree t = new HuffmanTree();

		t.compressFile(new File(infile), new File(outfile));

		t.huffmanToDot("huffman_tree.dot");
	}

	/**
	 * Decompresses a file.
	 * @param infile
	 * 			- File to be decompressed.
	 * @param outfile
	 * 			- Name of outputted decompressed file.
	 */
	public static void decompressFile(String infile, String outfile) {
		HuffmanTree t = new HuffmanTree();

		t.decompressFile(new File(infile), new File(outfile));
	}
	
	/**
	 * Reads int text files and returns a string of them. 
	 * @param file 
	 * 			- The file to be read from.
	 * @return
	 * 			- A string of the text in the file.
	 * @throws IOException
	 */
	public static String readFileToString(String file) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(file));
		StringBuilder sb = new StringBuilder();
		
		while(br.ready()){
			sb.append(br.readLine());
		}
		
		br.close();
		
		return sb.toString();
	}

	@Test
	public void simpleTextFile() throws IOException {
		compressFile("simple.txt", "simpleCom.txt");
		decompressFile("simpleCom.txt", "simpleDe.txt");
		
		String fileOne = readFileToString("simple.txt");
		String fileTwo = readFileToString("simpleDe.txt");
		
		assertTrue(fileOne.equals(fileTwo));
	}
	
	@Test
	public void originalTextFile() throws IOException {
		compressFile("original.txt", "originalCom.txt");
		decompressFile("originalCom.txt", "originalDe.txt");
		
		String fileOne = readFileToString("original.txt");
		String fileTwo = readFileToString("originalDe.txt");
		
		assertTrue(fileOne.equals(fileTwo));
	}
	
	@Test
	public void smallTextFile() throws IOException {
		compressFile("small.txt", "smallCom.txt");
		decompressFile("smallCom.txt", "smallDe.txt");
		
		String fileOne = readFileToString("small.txt");
		String fileTwo = readFileToString("smallDe.txt");
		
		assertTrue(fileOne.equals(fileTwo));
	}
	
	@Test
	public void mediumTextFile() throws IOException {
		compressFile("medium.txt", "mediumCom.txt");
		decompressFile("mediumCom.txt", "mediumDe.txt");
		
		String fileOne = readFileToString("medium.txt");
		String fileTwo = readFileToString("mediumDe.txt");
		
		assertTrue(fileOne.equals(fileTwo));
	}
	
	@Test
	public void largeTextFile() throws IOException {
		compressFile("large.txt", "largeCom.txt");
		decompressFile("largeCom.txt", "largeDe.txt");
		
		String fileOne = readFileToString("large.txt");
		String fileTwo = readFileToString("largeDe.txt");
		
		assertTrue(fileOne.equals(fileTwo));
	}

}
