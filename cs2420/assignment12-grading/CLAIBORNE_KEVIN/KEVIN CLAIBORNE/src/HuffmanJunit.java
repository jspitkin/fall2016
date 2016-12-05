package assignment12;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.junit.Before;
import org.junit.Test;

public class HuffmanJunit {
	
	@SuppressWarnings("resource")
	public static String convertToString(String fileName) {
		String result = "";
		try {
			result = new Scanner(new File(fileName)).useDelimiter("\\Z").next();
		} catch (Exception e) {
		}
		return result;
	}

	@Test
	public void dictionary() {
		CompressionDemo.compressFile("dictionary.txt", "compressed.txt");
		CompressionDemo.decompressFile("compressed.txt", "decompressed.txt");

		assertEquals(convertToString("dictionary.txt"), convertToString("decompressed.txt"));
	}
	
	@Test
	public void iHeartData() {
		CompressionDemo.compressFile("early.txt", "compressed.txt");
		CompressionDemo.decompressFile("compressed.txt", "decompressed.txt");

		assertEquals(convertToString("early.txt"), convertToString("decompressed.txt"));
	}
	
	@Test
	public void empty() {
		CompressionDemo.compressFile("Empty.txt", "compressed.txt");
		CompressionDemo.decompressFile("compressed.txt", "decompressed.txt");

		assertEquals(convertToString("Empty.txt"), convertToString("decompressed.txt"));
	}
	
	@Test
	public void goodLuck() {
		String fileName = "good_luck.txt";
		CompressionDemo.compressFile(fileName, "compressed.txt");
		CompressionDemo.decompressFile("compressed.txt", "decompressed.txt");

		assertEquals(convertToString(fileName), convertToString("decompressed.txt"));
	}
	
	@Test
	public void everything() {
		String fileName = "everything.txt";
		CompressionDemo.compressFile(fileName, "compressed.txt");
		CompressionDemo.decompressFile("compressed.txt", "decompressed.txt");

		assertEquals(convertToString(fileName), convertToString("decompressed.txt"));
	}
	
	@Test
	public void helloWorld() {
		String fileName = "hello_world.txt";
		CompressionDemo.compressFile(fileName, "compressed.txt");
		CompressionDemo.decompressFile("compressed.txt", "decompressed.txt");

		assertEquals(convertToString(fileName), convertToString("decompressed.txt"));
	}
	
	@Test
	public void sampleWordList() {
		String fileName = "sample_word_list.txt";
		CompressionDemo.compressFile(fileName, "compressed.txt");
		CompressionDemo.decompressFile("compressed.txt", "decompressed.txt");

		assertEquals(convertToString(fileName), convertToString("decompressed.txt"));
	}
	
	@Test
	public void mushroomPublishing() {
		String fileName = "Mushroom_Publishing.txt";
		CompressionDemo.compressFile(fileName, "compressed.txt");
		CompressionDemo.decompressFile("compressed.txt", "decompressed.txt");

		assertEquals(convertToString(fileName), convertToString("decompressed.txt"));
	}

}
