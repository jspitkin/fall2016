package assignment12;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class HuffmanTreeTesting {

	@Test
	public void test() {
		compressFile("original.txt", "compressed.txt");
		decompressFile("compressed.txt", "decompressed.txt");
	}

	public static void compressFile(String infile, String outfile) {
		HuffmanTree t = new HuffmanTree();

		t.compressFile(new File(infile), new File(outfile));

		t.huffmanToDot("huffman_tree.dot");
	}

	public static void decompressFile(String infile, String outfile) {
		HuffmanTree t = new HuffmanTree();

		t.decompressFile(new File(infile), new File(outfile));

		t.huffmanToDot("huffman_tree_d.dot");
	}

}
