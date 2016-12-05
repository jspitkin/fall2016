package assignment12;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;

/**
 * HuffmanTreeTester.java -- A JUnit Test Suite for Huffman Compression.
 * 
 * @author Rebekah Peterson u0871657
 */
public class HuffmanTreeTester {

	@Test
	public void alphabetTest() {
		CompressionDemo.compressFile("alphabet.txt", "alphabetCompressed.txt");
		CompressionDemo.decompressFile("alphabetCompressed.txt", "alphabetOutfile.txt");
		assertTrue(areSameContent("alphabet.txt", "alphabetOutfile.txt"));
	}

	@Test
	public void duplicatesTest() {
		CompressionDemo.compressFile("duplicates.txt", "duplicatesCompressed.txt");
		CompressionDemo.decompressFile("duplicatesCompressed.txt", "duplicatesOutfile.txt");
		assertTrue(areSameContent("duplicates.txt", "duplicatesOutfile.txt"));
	}
	
	@Test
	public void emptyTest() {
		CompressionDemo.compressFile("emptyFile.txt", "emptyCompressed.txt");
		CompressionDemo.decompressFile("emptyCompressed.txt", "emptyOutfile.txt");
		assertTrue(areSameContent("emptyFile.txt", "emptyOutfile.txt"));
	}
	
	@Test
	public void largeTest() {
		CompressionDemo.compressFile("large.txt", "largeCompressed.txt");
		CompressionDemo.decompressFile("largeCompressed.txt", "largeOutfile.txt");
		assertTrue(areSameContent("large.txt", "largeOutfile.txt"));
	}
	
	@Test
	public void largeUniqueTest() {
		CompressionDemo.compressFile("largeUnique.txt", "largeUniqueCompressed.txt");
		CompressionDemo.decompressFile("largeUniqueCompressed.txt", "largeUniqueOutfile.txt");
		assertTrue(areSameContent("largeUnique.txt", "largeUniqueOutfile.txt"));
	}
	
	@Test
	public void mediumTest() {
		CompressionDemo.compressFile("medium.txt", "mediumCompressed.txt");
		CompressionDemo.decompressFile("mediumCompressed.txt", "mediumOutfile.txt");
		assertTrue(areSameContent("medium.txt", "mediumOutfile.txt"));
	}
	
	@Test
	public void numbersTest() {
		CompressionDemo.compressFile("numbers.txt", "numbersCompressed.txt");
		CompressionDemo.decompressFile("numbersCompressed.txt", "numbersOutfile.txt");
		assertTrue(areSameContent("numbers.txt", "numbersOutfile.txt"));
	}
	
	@Test
	public void oneCharTest() {
		CompressionDemo.compressFile("oneCharacter.txt", "oneCharacterCompressed.txt");
		CompressionDemo.decompressFile("oneCharacterCompressed.txt", "oneCharacterOutfile.txt");
		assertTrue(areSameContent("oneCharacter.txt", "oneCharacterOutfile.txt"));
	}
	
	@Test
	public void smallTest() {
		CompressionDemo.compressFile("small.txt", "smallCompressed.txt");
		CompressionDemo.decompressFile("smallCompressed.txt", "smallOutfile.txt");
		assertTrue(areSameContent("small.txt", "smallOutfile.txt"));
	}
	
	@Test
	public void smallUniqueTest() {
		CompressionDemo.compressFile("smallUnique.txt", "smallUniqueCompressed.txt");
		CompressionDemo.decompressFile("smallUniqueCompressed.txt", "smallUniqueOutfile.txt");
		assertTrue(areSameContent("smallUnique.txt", "smallUniqueOutfile.txt"));
	}
	
	@Test
	public void uniqueTest() {
		CompressionDemo.compressFile("unique.txt", "uniqueCompressed.txt");
		CompressionDemo.decompressFile("uniqueCompressed.txt", "uniqueOutfile.txt");
		assertTrue(areSameContent("unique.txt", "uniqueOutfile.txt"));
	}
	
	/**
	 * Borrowed code for comparing the equality of two files.
	 */
	private static boolean areSameContent(String filename1, String filename2) {
		FileInputStream fin1 = null;
		try {
			fin1 = new FileInputStream(filename1);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader myInput = new BufferedReader(new InputStreamReader(fin1));
		StringBuilder sb1 = new StringBuilder();
		String thisLine;
		try {
			while ((thisLine = myInput.readLine()) != null) {  
				sb1.append(thisLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		FileInputStream fin2 = null;
		try {
			fin2 = new FileInputStream(filename2);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader myInput1 = new BufferedReader(new InputStreamReader(fin2));
		StringBuilder sb2 = new StringBuilder();
		try {
			while ((thisLine = myInput1.readLine()) != null) {  
				sb2.append(thisLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String file1 = sb1.toString();
		String file2 = sb2.toString();
		return file1.equals(file2);
	}
}
