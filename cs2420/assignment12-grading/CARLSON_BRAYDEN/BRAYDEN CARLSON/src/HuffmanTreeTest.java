package assignment12;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.Test;

/**
 * JUnit class for testing HuffmanTree
 * @author Brayden Carlson
 *
 */
public class HuffmanTreeTest {

	/**
	 * Tests to see if the original file and the decompressed file are the same
	 */
	@Test
	public void sameFileTest() {
		HuffmanTree t = new HuffmanTree();
		t.compressFile(new File("huffman_experiment/testing/original.txt"), new File("huffman_experiment/testing/compressed.txt"));
		t.decompressFile(new File("huffman_experiment/testing/compressed.txt"), new File("huffman_experiment/testing/decompressed.txt"));
		
		Path path1 = Paths.get("huffman_experiment/testing/original.txt");
		Path path2 = Paths.get("huffman_experiment/testing/decompressed.txt");
		byte[] a1 = null;
		byte[] a2 = null;
		try {
			a1 = Files.readAllBytes(path1);
			a2 = Files.readAllBytes(path2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		assertEquals(true, Arrays.equals(a1, a2));
	}
	
	/**
	 * Tests to see if the original file which is empty, is the same when decompressed
	 */
	@Test
	public void sameFileBlankTest() {
		HuffmanTree t = new HuffmanTree();
		t.compressFile(new File("huffman_experiment/testing/blank.txt"), new File("huffman_experiment/testing/blankcompressed.txt"));
		t.decompressFile(new File("huffman_experiment/testing/blankcompressed.txt"), new File("huffman_experiment/testing/blankdecompressed.txt"));
		
		Path path1 = Paths.get("huffman_experiment/testing/blank.txt");
		Path path2 = Paths.get("huffman_experiment/testing/blankdecompressed.txt");
		byte[] a1 = null;
		byte[] a2 = null;
		try {
			a1 = Files.readAllBytes(path1);
			a2 = Files.readAllBytes(path2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		assertEquals(true, Arrays.equals(a1, a2));
	}
	
	/**
	 * Tests to see if compressing results in a smaller file
	 */
	@Test
	public void smallerFileTest() {
		HuffmanTree t = new HuffmanTree();
		t.compressFile(new File("huffman_experiment/testing/original.txt"), new File("huffman_experiment/testing/compressed.txt"));
		t.decompressFile(new File("huffman_experiment/testing/compressed.txt"), new File("huffman_experiment/testing/decompressed.txt"));
		
		Path path1 = Paths.get("huffman_experiment/testing/original.txt");
		Path path2 = Paths.get("huffman_experiment/testing/compressed.txt");
		byte[] a1 = null;
		byte[] a2 = null;
		try {
			a1 = Files.readAllBytes(path1);
			a2 = Files.readAllBytes(path2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		assertEquals(true, a1.length > a2.length);
	}
}
