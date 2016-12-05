package assignment12;

import java.io.File;
/**
 * Given demo class, doubling as a testing class for me. 
 * @author Zachary Cutler u1025642
 *
 */
public class CompressionDemo {

	public static void compressFile(String infile, String outfile) {
		HuffmanTree t = new HuffmanTree();

		t.compressFile(new File(infile), new File(outfile));

		t.huffmanToDot("huffman_tree.dot");
	}

	public static void decompressFile(String infile, String outfile) {
		HuffmanTree t = new HuffmanTree();

		t.decompressFile(new File(infile), new File(outfile));
	}

	public static void main(String[] args) {
		compressFile("bigMaze.txt", "hello_world.txt");

		decompressFile("hello_world.txt", "compressed.txt");
		
		compressFile("dictionary.txt", "hello_world.txt");

		decompressFile("hello_world.txt", "compressed.txt");
		
		compressFile("Mushroom_Publishing.txt", "hello_world.txt");

		decompressFile("hello_world.txt", "compressed.txt");
		
		compressFile("Mushroom_Publishing.txt", "hello_world.txt");

		decompressFile("hello_world.txt", "compressed.txt");
	}
}