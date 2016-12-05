package assignment12;

import java.io.File;

/**
 * HuffmanCompresssionRatioTest - by using files with the same number of total characters but
 * varying numbers of unique characters, this class tests the effectiveness of 
 * Huffman Compression (the resulting output file sizes are the unit of measurement). 
 * @author Kyle Price
 * 11/22/2016
 *
 */
public class HuffmanCompresssionRatioTest {

	public static void main(String[] args) {
		HuffmanTree unique94 = new HuffmanTree();
		HuffmanTree unique75 = new HuffmanTree();
		HuffmanTree unique50 = new HuffmanTree();
		HuffmanTree unique25 = new HuffmanTree();
		HuffmanTree unique1 = new HuffmanTree();
		
		unique94.compressFile(new File("bigUnique94.txt"), new File("CompressedBigUnique94.txt"));
		unique75.compressFile(new File("bigUnique75.txt"), new File("CompressedBigUnique75.txt"));
		unique50.compressFile(new File("bigUnique50.txt"), new File("CompressedBigUnique50.txt"));
		unique25.compressFile(new File("bigUnique25.txt"), new File("CompressedBigUnique25.txt"));
		unique1.compressFile(new File("bigUnique1.txt"), new File("CompressedBigUnique1.txt"));
	}
}
