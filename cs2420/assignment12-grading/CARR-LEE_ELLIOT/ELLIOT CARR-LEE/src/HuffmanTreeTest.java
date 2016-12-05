package assignment12;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import assignment09.PathFinder;
/**
 * Tests for Huffman Tree.
 * @author Elliot Carr-Lee
 *
 */
public class HuffmanTreeTest {

	@Test
	public void test() {
		try {
			String directory = "src/assignment12/testCases";
			File[] testCases = new File(directory).listFiles();
			for (File test : testCases) {
				String in = test.getAbsolutePath();
				if (!in.endsWith("compressed.txt") && !in.endsWith("decompressed.txt") && !in.endsWith("tree")){
				String name = in.substring(0, in.length() - 4); //strip '.txt'
				File compressedFile = new File(name + "compressed.txt");
				File decompressedFile = new File(name + "decompressed.txt");
				HuffmanTree tree = new HuffmanTree();
				tree.compressFile(test, compressedFile);
				tree.decompressFile(compressedFile, decompressedFile);
				assertTrue(verify(test.getAbsolutePath(), decompressedFile.getAbsolutePath()));
				tree.huffmanToDot(name + "tree");  
				}
			}
		} catch (Exception exc) {
			exc.printStackTrace();
			Assert.fail();
		}
	}
	
	public static boolean verify (String expectedFile, String result){
		BufferedReader expectedreader = null;
		BufferedReader resultReader = null;
		try {
			expectedreader = new BufferedReader(new FileReader(expectedFile));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			resultReader = new BufferedReader(new FileReader(result));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (expectedreader.ready()){
				if (!(expectedreader.readLine().equals(resultReader.readLine()))){
					return false;
				}
			}
		} catch (IOException e) {
			return false;
		}
		return true; 
		
	}

}
