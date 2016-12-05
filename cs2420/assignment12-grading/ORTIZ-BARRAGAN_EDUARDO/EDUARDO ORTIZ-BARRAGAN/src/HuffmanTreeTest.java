package assignment12;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

/**
 * @author Eduardo ORtiz
 * u0922628 MW 3-4
 *
 */
public class HuffmanTreeTest {
	  public static void compressFile(String infile, String outfile) {
		 
		  HuffmanTree t = new HuffmanTree();

	    t.compressFile(new File(infile), new File(outfile));
	    
	    t.huffmanToDot("huffman_tree.dot");
	  }
	  File f = new File("testingFile.txt");
	  File f1 = new File("LargeFile.txt");
	  File f2 = new File("singleCharFile.txt");
	  File f3 = new File("emptyFile.txt");
	  public static void decompressFile(String infile, String outfile) {
	    HuffmanTree t = new HuffmanTree();

	    t.decompressFile(new File(infile), new File(outfile));
	   
	  }

	
	
	@Test
	public void test() {
		compressFile("testingFile.txt","compTestingFile.txt");
		decompressFile("compTestingFile.txt","deCompTestingFile.txt");
		File f4 = new File("testingFile.txt");
		File f5 = new File("deCompTestingFile.txt");
		double answer = f4.length();
		assertEquals(f5.length(),answer,1);
	}
	@Test
	public void testEmptyFile() {
		compressFile(f3.toString(),"compTestingFile.txt");
		decompressFile("compTestingFile.txt","deCompTestingFile.txt");
		File f4 = new File("testingFile.txt");
		File f5 = new File("deCompTestingFile.txt");
		double answer = f3.length();
		assertEquals(f5.length(),answer,1);
	}
	@Test
	public void testSingleChar() {
		compressFile(f2.toString(),"compTestingFile.txt");
		decompressFile("compTestingFile.txt","deCompTestingFile.txt");
		File f4 = new File("testingFile.txt");
		File f5 = new File("deCompTestingFile.txt");
		double answer = f2.length();
		assertEquals(f5.length(),answer,1);
	}

	@Test
	public void testLarge() {
		compressFile(f1.toString(),"compTestingFile.txt");
		decompressFile("compTestingFile.txt","deCompTestingFile.txt");
		File f4 = new File("testingFile.txt");
		File f5 = new File("deCompTestingFile.txt");
		double answer = f1.length();
		assertEquals(f5.length(),answer,1);
	}}
