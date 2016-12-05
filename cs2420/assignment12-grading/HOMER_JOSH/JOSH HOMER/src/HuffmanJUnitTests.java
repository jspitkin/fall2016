package assignment12;

import static org.junit.Assert.*;
import java.io.File;
import org.junit.Test;

/**
 * This class compresses a number of different text files.
 * @author Joshua Homer
 * uid: u0915498
 *
 */

public class HuffmanJUnitTests {
	
	HuffmanTree testTree;
	/**
	 * This method compresses a 2MB .txt file.
	 * The assertions make sure that the decompressed file is the 
	 * same size as the original file, and that the compressed file
	 * is indeed smaller than the original file.
	 */
	@Test
	public void testCompressHugeFile(){
		testTree = new HuffmanTree();
		testTree.compressFile(new File("largeOnlineText.txt"), new File("largeCompressedOnlineText.txt"));
		testTree.decompressFile(new File("largeCompressedOnlineText.txt"), new File("largeDecompressedOnlineText.txt"));
		File test = new File("largeOnlineText.txt");
	    File test2 = new File("largeDecompressedOnlineText.txt");
	    File test3 = new File("largeCompressedOnlineText.txt");
	    //System.out.println(test.length()+" vs "+test2.length()+" means that the compressed file is "
	    //		+((double)(test2.length())/(double)(test.length())*100)+"% as big as the original file.");
		assertTrue(test.length() == test2.length());
		assertTrue(test.length() > test3.length());
	}
	/**
	 ************************************************
	 *THE REST OF THE TESTS ARE FOR TIMING PURPOSES*
	 ************************************************
	 */
	@Test
	public void testNonUniqueFiles(){
		testTree = new HuffmanTree();
		testTree.compressFile(new File("nonUnique10.txt"), new File("nonUnique10C.txt"));
		testTree.compressFile(new File("nonUnique1000.txt"), new File("nonUnique1000C.txt"));
		testTree.compressFile(new File("nonUnique10000.txt"), new File("nonUnique10000C.txt"));
		testTree.compressFile(new File("nonUnique100000.txt"), new File("nonUnique100000C.txt"));
		testTree.compressFile(new File("nonUnique1000000.txt"), new File("nonUnique1000000C.txt"));
	}
	@Test
	public void testMidUniqueFiles(){
		testTree = new HuffmanTree();
		testTree.compressFile(new File("midUnique10.txt"), new File("midUnique10C.txt"));
		testTree.compressFile(new File("midUnique1000.txt"), new File("midUnique1000C.txt"));
		testTree.compressFile(new File("midUnique10000.txt"), new File("midUnique10000C.txt"));
		testTree.compressFile(new File("midUnique100000.txt"), new File("midUnique100000C.txt"));
		testTree.compressFile(new File("midUnique1000000.txt"), new File("midUnique1000000C.txt"));
	}
	@Test
	public void testUniqueFiles(){
		testTree = new HuffmanTree();
		testTree.compressFile(new File("unique10.txt"), new File("unique10C.txt"));
		testTree.compressFile(new File("unique1000.txt"), new File("unique1000C.txt"));
		testTree.compressFile(new File("unique10000.txt"), new File("unique10000C.txt"));
		testTree.compressFile(new File("unique100000.txt"), new File("unique100000C.txt"));
		testTree.compressFile(new File("unique1000000.txt"), new File("unique1000000C.txt"));
	}
}
