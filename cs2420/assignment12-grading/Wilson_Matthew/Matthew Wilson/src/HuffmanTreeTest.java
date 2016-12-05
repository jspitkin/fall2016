package assignment12;

import static org.junit.Assert.*;

import java.io.File;
import java.io.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HuffmanTreeTest {

	HuffmanTree ht = new HuffmanTree();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	public void compressFile(String infile, String outfile) {
		HuffmanTree t = new HuffmanTree();
		t.compressFile(new File(infile), new File(outfile));
		t.huffmanToDot("huffman_tree.dot");
	}

	public void decompressFile(String infile, String outfile) {
		HuffmanTree t = new HuffmanTree();
		t.decompressFile(new File(infile), new File(outfile));
	}

	public boolean compareFiles(String inputFile, String outputFile) {

		try {
			BufferedReader original = new BufferedReader(new FileReader(inputFile));
			BufferedReader decomp = new BufferedReader(new FileReader(outputFile));
			String ogLine;
			String dcLine;
			while (((ogLine = original.readLine()) != null) && ((dcLine = decomp.readLine()) != null)) {
				if (!ogLine.equals(dcLine)) {
					return false;
				}
			}
			original.close();
			decomp.close();
			return true;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

	}
	
	@Test
	public void example() {
		String og = "original.txt";
		String comp = "compressed.txt";
		String decomp = "decompressed.txt";
		
		compressFile(og, comp);
		decompressFile(comp, decomp);
		
		Assert.assertTrue(compareFiles(og, decomp));
	}

	//It was funny: the first time I tried it, I thought my code was broken. 
	// Turns out I had a unicode charcter (em dash: –) 
	// and that was throwing off my encoding. lol
	@Test
	public void file1() {
		String prefix = "tf1";
		String og = prefix + ".txt";
		String comp = prefix + "comp.txt";
		String decomp = prefix + "decomp.txt";
		
		compressFile(og, comp);
		decompressFile(comp, decomp);
		
		Assert.assertTrue(compareFiles(og, decomp));
	}
	
	@Test
	public void file2() {
		String prefix = "tf2";
		String og = prefix + ".txt";
		String comp = prefix + "comp.txt";
		String decomp = prefix + "decomp.txt";
		
		compressFile(og, comp);
		decompressFile(comp, decomp);
		
		Assert.assertTrue(compareFiles(og, decomp));
	}
	
	@Test
	public void file3() {
		String prefix = "tf3";
		String og = prefix + ".txt";
		String comp = prefix + "comp.txt";
		String decomp = prefix + "decomp.txt";
		
		compressFile(og, comp);
		decompressFile(comp, decomp);
		
		Assert.assertTrue(compareFiles(og, decomp));
	}
	
	

}
