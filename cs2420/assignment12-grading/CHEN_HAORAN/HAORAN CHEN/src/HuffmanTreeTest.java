package assignment12;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Junit test to check if the decompressed file is same as the original file.
 * @author Haoran Chen
 * @uid u1060286
 *
 */
public class HuffmanTreeTest {
	
	public String checkFile(String filename) throws FileNotFoundException {
		String s = "";
		int lineNumber = 0;
		File f = new File(filename);
		Scanner scan = new Scanner(f);
		while(scan.hasNext()){
			s += scan.next();
		}
		return s;
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test1() throws FileNotFoundException {
		CompressionDemo.compressFile("sample_word_list2.txt", "compressed1.txt");
		CompressionDemo.decompressFile("compressed1.txt", "decompressed1.txt");
		String original = checkFile("sample_word_list2.txt");
		String decompressed = checkFile("decompressed1.txt");
		assertEquals(original, decompressed);
	}
	
	@Test
	public void test2() throws FileNotFoundException {
		CompressionDemo.compressFile("good_luck.txt", "compressed2.txt");
		CompressionDemo.decompressFile("compressed2.txt", "decompressed2.txt");
		String original = checkFile("good_luck.txt");
		String decompressed = checkFile("decompressed2.txt");
		assertEquals(original, decompressed);
	}
	
	@Test
	public void test3() throws FileNotFoundException {
		CompressionDemo.compressFile("dictionary.txt", "compressed3.txt");
		CompressionDemo.decompressFile("compressed3.txt", "decompressed3.txt");
		String original = checkFile("dictionary.txt");
		String decompressed = checkFile("decompressed3.txt");
		assertEquals(original, decompressed);
	}

}
