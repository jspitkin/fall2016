package assignment12;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

/**
 * JUnit tests for the HuffmanTree compression algorithm
 * 
 * @author lindseyloveland
 *
 */
public class HuffmanTreeTest {

	HuffmanTree varyingTree = new HuffmanTree();
	HuffmanTree repTree = new HuffmanTree();
	HuffmanTree emptyTree = new HuffmanTree();
	
	
	/**
	 * Test to evaluate a file with little to no repeating characters
	 * @throws IOException
	 */
	@Test
	public void smallVaryingTreeTest() throws IOException {
		varyingTree.compressFile(new File("vary.txt"), new File("varyCompressed.txt"));
		
		varyingTree.decompressFile(new File("varyCompressed.txt"), new File("varyDecompressed.txt"));
	
		BufferedReader reader1 = null;
		BufferedReader reader2 = null;
		
		reader1 = new BufferedReader(new FileReader("vary.txt"));
		reader2 = new BufferedReader(new FileReader("varyDecompressed.txt"));
		
		int char1; // from first file
		int char2; // from second file
		// loop until the end of either file is reached
		while ((char1 = reader1.read()) != -1 && (char2 = reader2.read()) != -1) {
		    // convert current characters from both files to lowerCase
		    char lowerCase1 = Character.toLowerCase((char) char1);
		    char lowerCase2 = Character.toLowerCase((char) char2);
		   
		    assertEquals(lowerCase1, lowerCase2);
		} 
	}
	/**
	 * Test to evaluate a large file with little to no repeating characters
	 * @throws IOException
	 */
	@Test
	public void largeVaryingTreeTest() throws IOException {
		varyingTree.compressFile(new File("largeVary.txt"), new File("largeVaryCompressed.txt"));
		
		varyingTree.decompressFile(new File("largeVaryCompressed.txt"), new File("largeVaryDecompressed.txt"));
	
		BufferedReader reader1 = null;
		BufferedReader reader2 = null;
		
		reader1 = new BufferedReader(new FileReader("largeVary.txt"));
		reader2 = new BufferedReader(new FileReader("largeVaryDecompressed.txt"));
		
		int char1; // from first file
		int char2; // from second file
		// loop until the end of either file is reached
		while ((char1 = reader1.read()) != -1 && (char2 = reader2.read()) != -1) {
		    // convert current characters from both files to lowerCase
		    char lowerCase1 = Character.toLowerCase((char) char1);
		    char lowerCase2 = Character.toLowerCase((char) char2);
		   
		    assertEquals(lowerCase1, lowerCase2);
		} 
	}
	
	/**
	 * Test to evaluate a file with lots of repeating characters
	 * @throws IOException
	 */
	@Test
	public void similarTreeTest() throws IOException {
		varyingTree.compressFile(new File("repetitive.txt"), new File("repetitiveCompressed.txt"));
		
		varyingTree.decompressFile(new File("repetitiveCompressed.txt"), new File("repetitiveDecompressed.txt"));
	
		BufferedReader reader1 = null;
		BufferedReader reader2 = null;
		
		reader1 = new BufferedReader(new FileReader("repetitive.txt"));
		reader2 = new BufferedReader(new FileReader("repetitiveDecompressed.txt"));
		
		int char1; // from first file
		int char2; // from second file
		// loop until the end of either file is reached
		while ((char1 = reader1.read()) != -1 && (char2 = reader2.read()) != -1) {
		    // convert current characters from both files to lowerCase
		    char lowerCase1 = Character.toLowerCase((char) char1);
		    char lowerCase2 = Character.toLowerCase((char) char2);
		   
		    assertEquals(lowerCase1, lowerCase2);
		} 
	}
	/**
	 * Test to evaluate a large file with lots of repeating characters
	 * @throws IOException
	 */
	@Test
	public void largeSimilarTreeTest() throws IOException {
		varyingTree.compressFile(new File("largeRepetitive.txt"), new File("largeRepetitiveCompressed.txt"));
		
		varyingTree.decompressFile(new File("largeRepetitiveCompressed.txt"), new File("largeRepetitiveDecompressed.txt"));
	
		BufferedReader reader1 = null;
		BufferedReader reader2 = null;
		
		reader1 = new BufferedReader(new FileReader("largeRepetitive.txt"));
		reader2 = new BufferedReader(new FileReader("largeRepetitiveDecompressed.txt"));
		
		int char1; // from first file
		int char2; // from second file
		// loop until the end of either file is reached
		while ((char1 = reader1.read()) != -1 && (char2 = reader2.read()) != -1) {
		    // convert current characters from both files to lowerCase
		    char lowerCase1 = Character.toLowerCase((char) char1);
		    char lowerCase2 = Character.toLowerCase((char) char2);
		   
		    assertEquals(lowerCase1, lowerCase2);
		} 
	}

}
