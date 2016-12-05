package assignment12;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Junit Suite for Huffman Tree Class
 * 
 * @author ShahidBilal Razzaq u0996062
 *
 */
public class HuffmanTreeJunit {
	//Set up a compression and decompression tree
	static HuffmanTree huffmanCompressionTree = new HuffmanTree();
	static HuffmanTree huffmanDeCompressionTree = new HuffmanTree();
	

	@Before
	public void setUp() throws Exception {

	}

	/**
	 * Compresses a file
	 * @param inputFile -- input file
	 * @param outputFile --outputfile
	 */
	public static void compressFile(String inputFile, String outputFile) {
		

		huffmanCompressionTree.compressFile(new File(inputFile), new File(outputFile));

		
	}
	
	public static void generateDotFile(String name){
		huffmanCompressionTree.huffmanToDot(name);
	}

	/**
	 * decompresses an input file
	 * @param inputFile
	 * @param outputFile
	 */
	public static void decompressFile(String inputFile, String outputFile) {
		

		huffmanDeCompressionTree.decompressFile(new File(inputFile), new File(outputFile));
	}
	
	//------------------------------Tests--------------------------------------------//

	@Test
	public void testCompressFile() {
		compressFile("1_original.txt", "1_original_compressed.txt");
	}
	
	@Test
	public void testDecompressFile(){
		//assumes the orignal file has been compressed in previous method
		decompressFile("1_original_compressed.txt", "1_original_decompressed");
	}
	

	@Test
	public void testCompressFileNodeCount(){
		compressFile("2_original.txt", "2_original_compressed.txt");
		assertEquals(12, huffmanCompressionTree.getDataSize());
	}
	
	
	@Test
	public void getRootNodeWeight(){
		compressFile("2_original.txt", "2_original_compressed.txt");
		assertEquals(12, huffmanCompressionTree.getRootNodeWeight());
		
	}
	
	
	
	
	
	
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

}
